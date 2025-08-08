package pd.juvenilecase.riskanalysis.transactions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pd.juvenilecase.riskanalysis.PDRiskAnalysisVersioningHelper;
import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskQuestions;

import messaging.riskanalysis.RemoveCategoryQuestionsEvent;
import messaging.riskanalysis.SaveQuestionEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;

public class RemoveCategoryQuestionsCommand implements ICommand {

	private static final String FORMULA_MSG = "NEW RISK FORMULA CREATED-REMOVED QUESTION FROM CATEGORY";
	private static final String CATEGORY_MSG = "NEW CATEGORY CREATED-REMOVED QUESTION FROM CATEGORY";
	
	public void execute(IEvent event) {

		RemoveCategoryQuestionsEvent reqEvent = (RemoveCategoryQuestionsEvent) event;
		
		SaveQuestionEvent sqe = null;
		RiskQuestions question = null;
		
		RiskCategory category = RiskCategory.find(reqEvent.getCategoryId());
		List <SaveQuestionEvent> questionsToBeRemovedList = CollectionUtil.enumerationToList(reqEvent.getRequests());
		
		boolean createNewVersion = false;
		Map oldCategoryToNewCategoryMap = new HashMap();
		Map oldQuestionToNewQuestionMap = new HashMap();
		Map oldAnswerToNewAnswerMap = new HashMap();
		Map oldRecommendationToNewRecommendationMap = new HashMap();

		
		RiskFormula riskFormula = PDRiskAnalysisVersioningHelper.getNewRiskFormulaVersion(category.getFormulaId());
		
		if (riskFormula != null && !category.getFormulaId().equals(riskFormula.getOID())){
			riskFormula.setModificationReason(FORMULA_MSG);
			
			PDRiskAnalysisVersioningHelper.
			cloneFormulaRecommendationsCategoriesAndQuestions(
						category.getFormulaId(), riskFormula.getOID(), oldCategoryToNewCategoryMap, 
						oldQuestionToNewQuestionMap, oldAnswerToNewAnswerMap, 
						oldRecommendationToNewRecommendationMap, CATEGORY_MSG);
		
			String newCategoryId = (String) oldCategoryToNewCategoryMap.get(category.getOID());
			category = RiskCategory.find(newCategoryId);
			
			createNewVersion = true;
			
			newCategoryId = null;
		}
		
		if (createNewVersion){
			//was cloned
			for (int i = 0; i < questionsToBeRemovedList.size(); i++) {
				sqe = questionsToBeRemovedList.get(i);
				String newQuestionId = (String) oldQuestionToNewQuestionMap.get(sqe.getRiskQuestionId());
				question = RiskQuestions.find(newQuestionId);
				PDRiskAnalysisVersioningHelper.deleteQuestionAndAnswers(question);
				newQuestionId = null;
			}
		} else {
			for (int i = 0; i < questionsToBeRemovedList.size(); i++) {
				sqe = questionsToBeRemovedList.get(i);
				question = RiskQuestions.find(sqe.getRiskQuestionId());
				PDRiskAnalysisVersioningHelper.removeRiskCategoryIdFromQuestions(question);
			}
		}
		
		MessageUtil.postReply(category.getResponseEvent());
		
		questionsToBeRemovedList = null;
		reqEvent = null;
		sqe = null;
		question = null;
		category = null;
		oldCategoryToNewCategoryMap = null;
		oldQuestionToNewQuestionMap = null;
		oldAnswerToNewAnswerMap = null;
		riskFormula = null;

	}


}
