package pd.juvenilecase.riskanalysis.transactions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pd.juvenilecase.riskanalysis.PDRiskAnalysisVersioningHelper;
import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskRecommendation;
import naming.PDConstants;
import naming.RiskAnalysisConstants;
import messaging.riskanalysis.SaveFormulaEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;
import mojo.km.context.multidatasource.Home;

public class SaveFormulaCommand implements ICommand
{
	private static final String FORMULA_MSG = "NEW RISK FORMULA CREATED-FORMULA MODIFIED";
	private static final String CATEGORY_MSG = "NEW CATEGORY CREATED-FORMULA MODIFIED";

	
	public void execute(IEvent anEvent)
	{
		SaveFormulaEvent reqEvent = (SaveFormulaEvent) anEvent;
		
		RiskFormula riskFormula = null;
		boolean wasCloned = false;
		Map oldCategoryToNewCategoryMap = new HashMap();
		Map oldQuestionToNewQuestionMap = new HashMap();
		Map oldAnswerToNewAnswerMap = new HashMap();
		Map oldRecommendationToNewRecommendationMap = new HashMap();
		
		List categories = new ArrayList();
		List recommendations = new ArrayList();
		List replies = new ArrayList();
		
		if (reqEvent.getRiskFormulaId() != null 
				&& !reqEvent.getRiskFormulaId().equals(PDConstants.BLANK)){
			categories = RiskCategory.findAll("formulaId", reqEvent.getRiskFormulaId());
			recommendations = CollectionUtil.iteratorToList(RiskRecommendation.findAll("formulaId", reqEvent.getRiskFormulaId()));
			riskFormula = PDRiskAnalysisVersioningHelper.getNewRiskFormulaVersion(reqEvent.getRiskFormulaId());
			
			if (!reqEvent.getRiskFormulaId().equals(riskFormula.getOID())){
				//New version of formula was created.  Clone associated categories, questions, answers and recommendations.
				riskFormula.setModificationReason(FORMULA_MSG);
				PDRiskAnalysisVersioningHelper.
					cloneFormulaRecommendationsCategoriesAndQuestions(
						reqEvent.getRiskFormulaId(), riskFormula.getOID(), 
						oldCategoryToNewCategoryMap, oldQuestionToNewQuestionMap, 
						oldAnswerToNewAnswerMap, oldRecommendationToNewRecommendationMap, CATEGORY_MSG);
				wasCloned = true;
			} else {
				oldCategoryToNewCategoryMap = this.buildOIDMap(categories);
				oldRecommendationToNewRecommendationMap = this.buildOIDMap(recommendations);
			}
		} else {
			riskFormula = new RiskFormula();
			riskFormula.setAssessmentType(reqEvent.getAssessmentType());
			riskFormula.setStatusCd(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_PENDING);
			riskFormula.setVersion(riskFormula.getNextVersion());
			riskFormula.setCreateTimestamp(new Timestamp(new Date().getTime()));
		}
		
		IHome home = new Home();
		home.bind(riskFormula);
		
		replies.add(riskFormula.getResponseEvent());
		
		this.maintainFormulaCategories(
				reqEvent.getCategories(), oldCategoryToNewCategoryMap, riskFormula.getOID(), wasCloned);
		
		this.maintainFormulaRecommendations(
				reqEvent.getRecommendations(), oldRecommendationToNewRecommendationMap, 
				riskFormula.getOID(), wasCloned);
		
		categories = RiskCategory.findAll("formulaId", riskFormula.getOID());
		RiskCategory category = null;
		
		for (int i = 0; i < categories.size(); i++) {
			category = (RiskCategory) categories.get(i);
			replies.add(category.getResponseEvent());
		}
		
		recommendations = CollectionUtil.iteratorToList(RiskRecommendation.findAll("riskFormulaId", riskFormula.getOID()));
		RiskRecommendation recommendation = null;
		
		for (int i = 0; i < recommendations.size(); i++) {
			recommendation = (RiskRecommendation) recommendations.get(i);
			replies.add(recommendation.getResponseEvent());
		}
		
		MessageUtil.postReplies(replies);
		
		reqEvent = null;
		riskFormula = null;
		oldCategoryToNewCategoryMap = null;
		oldQuestionToNewQuestionMap = null;
		oldAnswerToNewAnswerMap = null;
		oldRecommendationToNewRecommendationMap = null;
		categories = null;
		recommendations = null;
		category = null;
		recommendation = null;
		riskFormula = null;
		home = null;
		replies = null;
	}

	private void maintainFormulaCategories(
			List <String> reqEventCategories, Map oldCategoryToNewCategoryMap, 
			String riskFormulaId, boolean wasCloned){

		String categoryId = null;
		RiskCategory category = null;
		Map reqEventCategoryMap = new HashMap();
		IHome home = new Home();
		
		for (int i = 0; i < reqEventCategories.size(); i++) {
			categoryId = reqEventCategories.get(i);
			reqEventCategoryMap.put(categoryId, categoryId);
			if (oldCategoryToNewCategoryMap.get(categoryId) == null){
				//Category needs to be added to Formula
				category = RiskCategory.find(categoryId);
				category.setFormulaId(riskFormulaId);
				reqEventCategoryMap.put(category.getOID(), category.getOID());
				home.bind(category);
			} 
		}

		//Find categories that need to be deleted or disassociated.
		Set oldKeySet = oldCategoryToNewCategoryMap.keySet();
		
		List existingCategoryIds = CollectionUtil.iteratorToList(oldKeySet.iterator());
		
		for (int i = 0; i < existingCategoryIds.size(); i++) {
			categoryId = (String) existingCategoryIds.get(i);
			String newCategoryId = null;
			if (reqEventCategoryMap.get(categoryId) == null){
				//This category was deleted from formula.
				newCategoryId = (String) oldCategoryToNewCategoryMap.get(categoryId);
				if (newCategoryId == null && wasCloned){
					PDRiskAnalysisVersioningHelper.deleteCategoryQuestionsAndAnswers(RiskCategory.find(newCategoryId));
				} else if (newCategoryId == null && !wasCloned){
					//disassociate category from formula.
					category = RiskCategory.find(newCategoryId);
					category.setFormulaId(null);
					home.bind(category);
				}
			}
			newCategoryId = null;
		}
		
		reqEventCategories = null;
		categoryId = null;
		riskFormulaId = null;
		category = null;
		reqEventCategoryMap = null;
		oldKeySet = null;
		existingCategoryIds = null;
		home = null;
	}

	private void maintainFormulaRecommendations(List <String> reqEventRecommendations,
			Map oldRecommendationToNewRecommendationMap, String riskFormulaId,
			boolean wasCloned) {

		String recommendationId = null;
		RiskRecommendation recommendation = null;
		Map reqEventRecommendationMap = new HashMap();
		IHome home = new Home();
		
		for (int i = 0; reqEventRecommendations != null && i < reqEventRecommendations.size(); i++) {
			recommendationId = reqEventRecommendations.get(i);
			reqEventRecommendationMap.put(recommendationId, recommendationId);
			if (oldRecommendationToNewRecommendationMap.get(recommendationId) == null){
				//Recommendation needs to be added to Formula
				recommendation = RiskRecommendation.find(recommendationId);
				recommendation.setRiskFormulaId(riskFormulaId);
				reqEventRecommendationMap.put(recommendation.getOID(), recommendation.getOID());
				home.bind(recommendation);
			} 
		}

		//Find recommendations that need to be deleted or disassicated.
		Set oldKeySet = oldRecommendationToNewRecommendationMap.keySet();
		
		List existingRecommendationIds = CollectionUtil.iteratorToList(oldKeySet.iterator());
		
		for (int i = 0; i < existingRecommendationIds.size(); i++) {
			recommendationId = (String) existingRecommendationIds.get(i);
			String newRecommendationId = null;
			if (reqEventRecommendationMap.get(recommendationId) == null){
				//This recommendation was deleted from formula.
				newRecommendationId = (String) oldRecommendationToNewRecommendationMap.get(recommendationId);
				if (newRecommendationId == null && wasCloned){
					PDRiskAnalysisVersioningHelper.deleteRecommendation(RiskRecommendation.find(newRecommendationId));
				} else if (newRecommendationId == null && !wasCloned){
					//disassociate recommendation from formula.
					recommendation = RiskRecommendation.find(newRecommendationId);
					recommendation.setRiskFormulaId(null);
					home.bind(recommendation);
				}
			}
			newRecommendationId = null;
		}
		
		reqEventRecommendations = null;
		recommendationId = null;
		recommendation = null;
		reqEventRecommendationMap = null;
		oldKeySet = null;
		existingRecommendationIds = null;
		home = null;
	}
	
	private Map buildOIDMap(List <PersistentObject> objs) {
		
		Map aMap = new HashMap();
		
		if (objs != null){
			PersistentObject obj = null;
			for (int i = 0; i < objs.size(); i++) {
				obj = (PersistentObject) objs.get(i);
				aMap.put(obj.getOID(), obj.getOID());
			}
		}
		
		return aMap;
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
