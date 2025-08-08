package pd.juvenilecase.riskanalysis.transactions;

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
import messaging.riskanalysis.RemoveAnswerEvent;
import messaging.riskanalysis.RemoveCategoryQuestionAnswerEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;

public class RemoveCategoryQuestionAnswerCommand implements ICommand {

		private static final String FORMULA_MSG = "NEW RISK FORMULA CREATED-ANSWER REMOVED";
		private static final String CATEGORY_MSG = "NEW CATEGORY CREATED-ANSWER REMOVED";

		public void execute(IEvent event) {

			RemoveCategoryQuestionAnswerEvent reqEvent = (RemoveCategoryQuestionAnswerEvent) event;
			
			RiskCategory category = RiskCategory.find(reqEvent.getRiskCategoryId());

			List <RemoveAnswerEvent> answersToBeRemovedList = CollectionUtil.enumerationToList(reqEvent.getRequests());
			
			boolean createNewVersion = this.isCreateNewVersion(category, answersToBeRemovedList);
			
			Map oldQuestionToNewQuestionMap = new HashMap();
			Map oldAnswerToNewAnswerMap = new HashMap();
			Map oldCategoryToNewCategoryMap = new HashMap();
			Map oldRecommendationToNewRecommendationMap = new HashMap();
			
			if (createNewVersion){
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
					newCategoryId = null;
				}
				riskFormula = null;
			}

			RiskWeightedResponse answerToBeRemoved = null;
			RemoveAnswerEvent removeAnswerEvent = null;
			RiskQuestions question = null;

			for (int i = 0; i < answersToBeRemovedList.size(); i++) {
				removeAnswerEvent = answersToBeRemovedList.get(i);
				if (createNewVersion){
					//was cloned
					String newAnswerId = (String) oldAnswerToNewAnswerMap.get(removeAnswerEvent.getRiskAnswerId());
					answerToBeRemoved = RiskWeightedResponse.find(newAnswerId);
					PDRiskAnalysisVersioningHelper.deleteAnswerAndSubordinateQuestion(answerToBeRemoved);
					newAnswerId = null;
				} else {
					answerToBeRemoved = RiskWeightedResponse.find(removeAnswerEvent.getRiskAnswerId());
					PDRiskAnalysisVersioningHelper.removeRiskCategoryIdFromSubordinateQuestions(answerToBeRemoved);
					answerToBeRemoved.delete();
					IHome home = new Home();
					home.bind(answerToBeRemoved);
					home = null;
				}
			}

			MessageUtil.postReply(category.getResponseEvent());

			String questionId = null;
			if (createNewVersion){
				questionId = (String) oldQuestionToNewQuestionMap.get(reqEvent.getRiskQuestionId());
			} else {
				questionId = reqEvent.getRiskQuestionId();
			}
			
			question = RiskQuestions.find(questionId);
			MessageUtil.postReply(PDRiskQuestionHelper.getQuestionResponseEvent(question));
			
			List <RiskWeightedResponse> qAnswers = CollectionUtil.iteratorToList(RiskWeightedResponse.findAll("riskQuestionId", question.getOID()));
			
			for (int i = 0; i < qAnswers.size(); i++) {
				MessageUtil.postReply(PDRiskQuestionHelper.getAnswerResponseEvent(qAnswers.get(i)));
			}

			
			reqEvent = null;
			category = null;
			removeAnswerEvent = null;
			answerToBeRemoved = null;
			question = null;
			questionId = null;
		}
		
		private boolean isCreateNewVersion(RiskCategory category,List <RemoveAnswerEvent> answers) {
			
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
			
			if (answers.size() > 0){
				isNewVersion = true;
			}
			
			return isNewVersion;
		}	
		
}
