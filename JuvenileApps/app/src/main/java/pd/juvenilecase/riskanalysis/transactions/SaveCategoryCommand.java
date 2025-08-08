package pd.juvenilecase.riskanalysis.transactions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pd.juvenilecase.riskanalysis.PDRiskAnalysisVersioningHelper;
import pd.juvenilecase.riskanalysis.PDRiskQuestionHelper;
import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskQuestions;
import naming.PDConstants;
import naming.RiskAnalysisConstants;
import messaging.riskanalysis.SaveCategoryEvent;
import messaging.riskanalysis.SaveQuestionEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import mojo.km.context.ICommand;
import mojo.km.context.multidatasource.Home;

public class SaveCategoryCommand implements ICommand
{
	private static final String FORMULA_MSG = "NEW RISK FORMULA CREATED-UPDATE OF CATEGORY";
	private static final String CATEGORY_MSG = "NEW CATEGORY CREATED-UPDATE OF CATEGORY";

	public void execute(IEvent anEvent)
	{
		SaveCategoryEvent reqEvent = (SaveCategoryEvent) anEvent;
		RiskCategory category = null;
		boolean createNewVersion = false;

		RiskFormula riskFormula = null;
		String oldFormulaId = null;
		String newFormulaId = null;
				
		if (reqEvent.getCategoryId() != null && !reqEvent.getCategoryId().equals(PDConstants.BLANK)){
			category = RiskCategory.find(reqEvent.getCategoryId());
			createNewVersion = this.isCreateNewVersion(category, reqEvent);
			if (createNewVersion){
				oldFormulaId = category.getFormulaId();
				riskFormula = PDRiskAnalysisVersioningHelper.getNewRiskFormulaVersion(category.getFormulaId());
				riskFormula.setModificationReason(FORMULA_MSG);
				newFormulaId = riskFormula.getOID();
			}
		} else {
			category = new RiskCategory();
			category.setCreateTimestamp(new Timestamp(new Date().getTime()));
			category.setVersion(category.getNextVersion());
			createNewVersion = true;
		}

		RiskQuestions question = null;
		Map oldCategoryToNewCategoryMap = new HashMap();
		Map oldQuestionToNewQuestionMap = new HashMap();
		Map oldAnswerToNewAnswerMap = new HashMap();
		Map oldRecommendationToNewRecommendationMap = new HashMap();

		if (!createNewVersion){
			if (category.getOID() != null){
				category.setUpdateTimestamp(new Timestamp(new Date().getTime()));
			}
		} 
		
		List <RiskQuestions> questions = null;

		if (createNewVersion && category.getOID() != null){
			PDRiskAnalysisVersioningHelper.
				cloneFormulaRecommendationsCategoriesAndQuestions(
						oldFormulaId, newFormulaId, oldCategoryToNewCategoryMap, 
						oldQuestionToNewQuestionMap, oldAnswerToNewAnswerMap, 
						oldRecommendationToNewRecommendationMap, CATEGORY_MSG);
			String newCategoryId = (String) oldCategoryToNewCategoryMap.get(category.getOID());
			category = RiskCategory.find(newCategoryId);
			questions = this.updateCategory(category, reqEvent, oldQuestionToNewQuestionMap, true);
			newCategoryId = null;
		}  else {
			questions = this.updateCategory(category, reqEvent, oldQuestionToNewQuestionMap, false);
		}
		
		if (questions != null){
			for (int i = 0; i < questions.size(); i++) {
				question = questions.get(i);
				MessageUtil.postReply(PDRiskQuestionHelper.getQuestionResponseEvent(question));
			}
		}
		MessageUtil.postReply(category.getResponseEvent());
		
		reqEvent = null;
		category = null;
		question = null;
		oldQuestionToNewQuestionMap = null;
		questions = null;
	}

	private List updateCategory(RiskCategory category,
			SaveCategoryEvent reqEvent, Map oldQuestionToNewQuestionMap, boolean wasCloned) {
		
		IHome home = new Home();
		List <RiskQuestions> existingQuestions = new ArrayList();
		Map existingQuestionsMap = new HashMap();
		
		if (category.getOID() != null){
			existingQuestions =  CollectionUtil.iteratorToList(category.getQuestions().iterator());
			existingQuestionsMap = PDRiskAnalysisVersioningHelper.buildQuestionMap(existingQuestions);
		} 

		category.setCategoryName(reqEvent.getCategoryName());
		category.setDescription(reqEvent.getDescription());
		
		if (reqEvent.getRiskResultGroupId() != null && !reqEvent.getRiskResultGroupId().equals(PDConstants.BLANK)){
			category.setRiskResultGroupId(Integer.parseInt(reqEvent.getRiskResultGroupId()));
		}
		home.bind(category);
		
		//SaveQuestionEvent will have old question OID instead of OID of cloned questions. (if the category was cloned).
		List <SaveQuestionEvent> newQuestionList = CollectionUtil.enumerationToList(reqEvent.getRequests());
		
		SaveQuestionEvent sqe = null;		
		RiskQuestions question = null;
		String newQuestionId = null;
		Map newQuestionsMap = new HashMap();
		
		for (int i = 0; i < newQuestionList.size(); i++) {
			sqe = newQuestionList.get(i);
			if (wasCloned){
				newQuestionId = (String) oldQuestionToNewQuestionMap.get(sqe.getRiskQuestionId());
			} else {
				newQuestionId = sqe.getRiskQuestionId();
			}
			newQuestionsMap.put(newQuestionId, newQuestionId);
			if (existingQuestionsMap.get(newQuestionId) != null){
				//Question was previously associated to category.
				continue;
			} else {
				//New question was added to category.
				question = RiskQuestions.find(sqe.getRiskQuestionId());
				question.setRiskCategoryId(category.getOID());
				PDRiskAnalysisVersioningHelper.addRiskCategoryIdToQuestion(question, category.getOID());
				existingQuestionsMap.put(question.getOID(), question);
				home.bind(question);
			}
		}
		//Handle questions being removed from category
		if (existingQuestions.size() > 0){
			for (int i = 0; i < existingQuestions.size(); i++) {
				question = existingQuestions.get(i);
				if (newQuestionsMap.get(question.getOID()) != null){
					continue;
				} else {
					if (wasCloned){
						//delete question, subordinate questions and answers from cloned question.
						PDRiskAnalysisVersioningHelper.deleteQuestionAndAnswers(question);
					} else {
						//disassociate category ID from questions and subordinate questions.
						PDRiskAnalysisVersioningHelper.removeRiskCategoryIdFromQuestions(question);
					}
				}
			}
		}
		
		home = null;
		existingQuestions = null;
		newQuestionList = null;
		sqe = null;
		question = null;
		newQuestionId = null;
		return CollectionUtil.iteratorToList(existingQuestionsMap.values().iterator());
	}

	private boolean isCreateNewVersion(RiskCategory category,
			SaveCategoryEvent reqEvent) {
		
		boolean isNewVersion = false;
		
		if (category.getFormulaId() == null || category.getFormulaId().equals(PDConstants.BLANK)){
			isNewVersion = false;
			return isNewVersion;
		} else {
			IHome home = new Home();
			RiskFormula rf = (RiskFormula) home.find(category.getFormulaId(), RiskFormula.class);
			if (rf.getStatusCd() != null && rf.getStatusCd().equals(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_PENDING)){
				isNewVersion = false;
				rf = null;
				home = null;
				return isNewVersion;
			}
			rf = null;
			home = null;
		}
		
		String riskResultGroupId = Integer.toString(category.getRiskResultGroupId());
		if (!riskResultGroupId.equals(reqEvent.getRiskResultGroupId())){
			isNewVersion = true;
			riskResultGroupId = null;
			return isNewVersion;
		}
		
		Map existingQuestionMap = new HashMap();
		Map newQuestionMap = new HashMap();
		RiskQuestions question = null;
		SaveQuestionEvent qe = null;
		
		List <RiskQuestions> existingQuestionList = CollectionUtil.iteratorToList(category.getQuestions().iterator());
		List <SaveQuestionEvent> newQuestionList = CollectionUtil.enumerationToList(reqEvent.getRequests());

		for (int i = 0; i < existingQuestionList.size(); i++) {
			question = existingQuestionList.get(i);
			existingQuestionMap.put(question.getOID(),question.getOID());
		}
		
		//check for new question
		for (int i = 0; i < newQuestionList.size(); i++) {
			qe = newQuestionList.get(i);
			newQuestionMap.put(qe.getRiskQuestionId(), qe.getRiskQuestionId());
			if (existingQuestionMap.get(qe.getRiskQuestionId()) == null){
				isNewVersion = true;
				existingQuestionMap = null;
				newQuestionMap = null;
				newQuestionList = null;
				existingQuestionList = null;
				question = null;
				qe = null;
				return isNewVersion;
			}
		}
		
		//check for question removed
		for (int i = 0; i < existingQuestionList.size(); i++) {
			question = existingQuestionList.get(i);
			if (newQuestionMap.get(question.getOID()) == null){
				isNewVersion = true;
				existingQuestionMap = null;
				newQuestionMap = null;
				newQuestionList = null;
				existingQuestionList = null;
				question = null;
				qe = null;
				break;
			}
		}
		riskResultGroupId = null;
		existingQuestionMap = null;
		newQuestionMap = null;
		newQuestionList = null;
		existingQuestionList = null;
		question = null;
		qe = null;
		
		return isNewVersion;
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
