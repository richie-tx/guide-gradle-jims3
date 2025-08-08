package pd.juvenilecase.riskanalysis.transactions;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import naming.PDConstants;
import naming.RiskAnalysisConstants;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisVersioningHelper;
import pd.juvenilecase.riskanalysis.PDRiskQuestionHelper;
import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskQuestions;
import pd.juvenilecase.riskanalysis.RiskWeightedResponse;
import messaging.riskanalysis.SaveAnswerEvent;
import messaging.riskanalysis.UpdateCategoryQuestionAnswerEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;

public class UpdateCategoryQuestionAnswerCommand implements ICommand {

	private static final String FORMULA_MSG = "NEW RISK FORMULA CREATED-ANSWER MODIFIED";
	private static final String CATEGORY_MSG = "NEW CATEGORY CREATED-ANSWER MODIFIED";
	private static final String ZERO = "0";

	public void execute(IEvent event) {

		UpdateCategoryQuestionAnswerEvent reqEvent = (UpdateCategoryQuestionAnswerEvent) event;
		
		RiskCategory category = RiskCategory.find(reqEvent.getRiskCategoryId());
		IHome home = new Home();

		List <SaveAnswerEvent> answers = CollectionUtil.enumerationToList(reqEvent.getRequests());
		
		boolean createNewVersion = this.isCreateNewVersion(category, answers);
		
		Map oldQuestionToNewQuestionMap = new HashMap();
		Map oldAnswerToNewAnswerMap = new HashMap();
		
		if (createNewVersion){
			category = this.createNewVersion(category, reqEvent, oldQuestionToNewQuestionMap, oldAnswerToNewAnswerMap);
		} 

		RiskWeightedResponse answerToBeModified = null;
		SaveAnswerEvent saveAnswerEvent = null;
		RiskQuestions question = null;
		String newQuestionId = null;
		
		for (int i = 0; i < answers.size(); i++) {
			
			saveAnswerEvent = answers.get(i);
			if (!createNewVersion){
				//Update of pending version
				if (!saveAnswerEvent.isACreate() || (saveAnswerEvent.getRiskAnswerId() != null 
						&& !saveAnswerEvent.getRiskAnswerId().equals(PDConstants.BLANK))){
					answerToBeModified = RiskWeightedResponse.find(saveAnswerEvent.getRiskAnswerId());
				} else {
					answerToBeModified = new RiskWeightedResponse();
					answerToBeModified.setRiskQuestionId(saveAnswerEvent.getRiskQuestionId());
					answerToBeModified.setCreateTimestamp(new Timestamp(new Date().getTime()));
				}
				if (saveAnswerEvent.getSubordinateQuestionId() != null 
						&& !saveAnswerEvent.getSubordinateQuestionId().equals(PDConstants.BLANK)
						&& !saveAnswerEvent.getSubordinateQuestionId().equals(ZERO)){
					answerToBeModified.setSubordinateQuestionId(saveAnswerEvent.getSubordinateQuestionId());
					question = RiskQuestions.find(saveAnswerEvent.getSubordinateQuestionId());
					if (question != null){
						question.setRiskCategoryId(category.getOID());
						home.bind(question);
					}
				} else {
					if (answerToBeModified.getSubordinateQuestionId() != null 
							&& !answerToBeModified.getSubordinateQuestionId().equals(PDConstants.BLANK)
							&& !answerToBeModified.getSubordinateQuestionId().equals(ZERO)){
						question = RiskQuestions.find(saveAnswerEvent.getSubordinateQuestionId());
						question.setRiskCategoryId(null);
					}
					answerToBeModified.setSubordinateQuestionId(null);
				}
			} else {
				//Was cloned
				if (!saveAnswerEvent.isACreate() || (saveAnswerEvent.getRiskAnswerId() != null 
						&& !saveAnswerEvent.getRiskAnswerId().equals(PDConstants.BLANK))){
					String newAnswerId = (String) oldAnswerToNewAnswerMap.get(saveAnswerEvent.getRiskAnswerId());
					answerToBeModified = RiskWeightedResponse.find(newAnswerId);
				} else {
					answerToBeModified = new RiskWeightedResponse();
					answerToBeModified.setCreateTimestamp(new Timestamp(new Date().getTime()));
				}
				if (saveAnswerEvent.getSubordinateQuestionId() != null 
						&& !saveAnswerEvent.getSubordinateQuestionId().equals(PDConstants.BLANK)
						&& !saveAnswerEvent.getSubordinateQuestionId().equals(ZERO)){
					String newSubQuestionId = (String) oldQuestionToNewQuestionMap.get(saveAnswerEvent.getSubordinateQuestionId());
					answerToBeModified.setSubordinateQuestionId(newSubQuestionId);
					question = RiskQuestions.find(newSubQuestionId);
					if (question != null){
						question.setRiskCategoryId(category.getOID());
						home.bind(question);
					}
					newSubQuestionId = null;
				} else {
					//This question was cloned but not needed in new version.
					if (answerToBeModified.getSubordinateQuestionId() != null 
							&& !answerToBeModified.getSubordinateQuestionId().equals(PDConstants.BLANK)
							&& !answerToBeModified.getSubordinateQuestionId().equals(ZERO)){
						question = RiskQuestions.find(answerToBeModified.getSubordinateQuestionId());
						question.delete();
						home.bind(question);
					}
					answerToBeModified.setSubordinateQuestionId(null);
				}
				newQuestionId = (String) oldQuestionToNewQuestionMap.get(saveAnswerEvent.getRiskQuestionId());;
				answerToBeModified.setRiskQuestionId(newQuestionId);
			}
			
			answerToBeModified.setAction(saveAnswerEvent.getAction());
			answerToBeModified.setResponse(saveAnswerEvent.getAnswerText());
			answerToBeModified.setSortOrder(Integer.parseInt(saveAnswerEvent.getSortOrder()));
			answerToBeModified.setWeight(Integer.parseInt(saveAnswerEvent.getWeight()));	
			home.bind(answerToBeModified);			
		}
		
		if (createNewVersion){
			newQuestionId = (String)oldQuestionToNewQuestionMap.get(reqEvent.getRiskQuestionId());
		} else {
			newQuestionId = reqEvent.getRiskQuestionId();
		}
		
		question = RiskQuestions.find(newQuestionId);
		MessageUtil.postReply(PDRiskQuestionHelper.getQuestionResponseEvent(question));
		
		List <RiskWeightedResponse> qAnswers = CollectionUtil.iteratorToList(RiskWeightedResponse.findAll("riskQuestionId", question.getOID()));
		
		for (int i = 0; i < qAnswers.size(); i++) {
			MessageUtil.postReply(PDRiskQuestionHelper.getAnswerResponseEvent(qAnswers.get(i)));
		}
		MessageUtil.postReply(category.getResponseEvent());
		
		qAnswers = null;
		home = null;
		reqEvent = null;
		category = null;
		saveAnswerEvent = null;
		answerToBeModified = null;
		question = null;
		newQuestionId = null;
	}
	
	private boolean isCreateNewVersion(RiskCategory category,List <SaveAnswerEvent> answers) {
		
		boolean isNewVersion = false;
		
		if (category == null || category.getFormulaId() == null || category.getFormulaId().equals(PDConstants.BLANK)){
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
		
		SaveAnswerEvent saveAnswerEvent = null;
		RiskWeightedResponse answer = null;
		
		for (int i = 0; i < answers.size(); i++) {
			saveAnswerEvent = answers.get(i);
			if (saveAnswerEvent.isACreate()){
				isNewVersion = true;
				break;
			}
			answer = RiskWeightedResponse.find(saveAnswerEvent.getRiskAnswerId());
			this.initializeNulls(answer);
			this.initializeNulls(saveAnswerEvent);
			
			if (!answer.getAction().equals(saveAnswerEvent.getAction())
					|| !answer.getSubordinateQuestionId().equals(saveAnswerEvent.getSubordinateQuestionId())
					|| !(answer.getWeight() == Integer.parseInt(saveAnswerEvent.getWeight()))){
				isNewVersion = true;
				break;
			}
		}
		
		saveAnswerEvent = null;
		answer = null;
		
		return isNewVersion;
	}	
	
	private void initializeNulls(SaveAnswerEvent reqEvent) {
		if (reqEvent.getAction() == null){
			reqEvent.setAction(PDConstants.BLANK);
		}
		if (reqEvent.getSortOrder() == null){
			reqEvent.setSortOrder(PDConstants.BLANK);
		}
		if (reqEvent.getSubordinateQuestionId() == null){
			reqEvent.setSubordinateQuestionId(PDConstants.BLANK);
		}
		if (reqEvent.getWeight() == null){
			reqEvent.setWeight(PDConstants.BLANK);
		}
		if (reqEvent.getAnswerText() == null){
			reqEvent.setAnswerText(PDConstants.BLANK);
		}
		if (reqEvent.getWeight() == null || reqEvent.getWeight().equals(PDConstants.BLANK)){
			reqEvent.setWeight("0");
		}
	}
	private void initializeNulls(RiskWeightedResponse answer) {
		if (answer.getAction() == null){
			answer.setAction(PDConstants.BLANK);
		}
		if (answer.getSubordinateQuestionId() == null){
			answer.setSubordinateQuestionId(PDConstants.BLANK);
		}
		if (answer.getSuggestedCasePlanDomainId() == null){
			answer.setSuggestedCasePlanDomainId(PDConstants.BLANK);
		}
		answer.setModified(false);
	}
	
	private RiskCategory createNewVersion(RiskCategory category, UpdateCategoryQuestionAnswerEvent reqEvent, 
			Map oldQuestionToNewQuestionMap, Map oldAnswerToNewAnswerMap){
		
		String oldFormulaId = category.getFormulaId();
		
		RiskFormula riskFormula = PDRiskAnalysisVersioningHelper.getNewRiskFormulaVersion(category.getFormulaId());
		riskFormula.setModificationReason(FORMULA_MSG);
		
		String newFormulaId = riskFormula.getOID();
		Map oldCategoryToNewCategoryMap = new HashMap();
		Map oldRecommendationToNewRecommendationMap = new HashMap();

		PDRiskAnalysisVersioningHelper.
		cloneFormulaRecommendationsCategoriesAndQuestions(
					oldFormulaId, newFormulaId, oldCategoryToNewCategoryMap, 
					oldQuestionToNewQuestionMap, oldAnswerToNewAnswerMap, 
					oldRecommendationToNewRecommendationMap, CATEGORY_MSG);
		
		String newCategoryId = (String) oldCategoryToNewCategoryMap.get(reqEvent.getRiskCategoryId());
		category = RiskCategory.find(newCategoryId);

		return category;
	}
}
