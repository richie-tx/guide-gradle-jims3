package pd.juvenilecase.riskanalysis.transactions;

import java.util.HashMap;
import java.util.Map;

import naming.PDConstants;
import naming.RiskAnalysisConstants;

import pd.juvenilecase.riskanalysis.PDRiskAnalysisVersioningHelper;
import pd.juvenilecase.riskanalysis.PDRiskQuestionHelper;
import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskQuestions;
import messaging.riskanalysis.SaveQuestionEvent;
import messaging.riskanalysis.UpdateCategoryQuestionEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.MessageUtil;

public class UpdateCategoryQuestionCommand implements ICommand {
	private static final String FORMULA_MSG = "NEW RISK FORMULA CREATED-QUESTION MODIFIED";
	private static final String CATEGORY_MSG = "NEW CATEGORY CREATED-QUESTION MODIFIED";

	public void execute(IEvent event) throws Exception {
		
		UpdateCategoryQuestionEvent reqEvent = (UpdateCategoryQuestionEvent) event;

		SaveQuestionEvent sqe = (SaveQuestionEvent) reqEvent;
		
		RiskCategory category = RiskCategory.find(reqEvent.getRiskCategoryId());
		RiskQuestions question = RiskQuestions.find(sqe.getRiskQuestionId());

		Map oldQuestionToNewQuestionMap = new HashMap();
		Map oldCategoryToNewCategoryMap = new HashMap();
		Map oldAnswerToNewAnswerMap = new HashMap();
		Map oldRecommendationToNewRecommendationMap = new HashMap();
		
		boolean createNewVersion = isCreateNewVersion(question, category, reqEvent);
		
		if (createNewVersion){
			String oldFormulaId = category.getFormulaId();
			RiskFormula riskFormula = PDRiskAnalysisVersioningHelper.getNewRiskFormulaVersion(category.getFormulaId());
			riskFormula.setModificationReason(FORMULA_MSG);
			
			PDRiskAnalysisVersioningHelper.
			cloneFormulaRecommendationsCategoriesAndQuestions(
						oldFormulaId, riskFormula.getOID(), oldCategoryToNewCategoryMap, 
						oldQuestionToNewQuestionMap, oldAnswerToNewAnswerMap, 
						oldRecommendationToNewRecommendationMap, CATEGORY_MSG);
			
			String newCategoryId = (String) oldCategoryToNewCategoryMap.get(category.getOID());
			category = RiskCategory.find(newCategoryId);
			question = this.updateQuestion(category, reqEvent, oldQuestionToNewQuestionMap, true);
			
			oldFormulaId = null;
			riskFormula = null;
			newCategoryId = null;
		} else {
			question = PDRiskQuestionHelper.saveRiskQuestion(reqEvent);
		}
		
		MessageUtil.postReply(PDRiskQuestionHelper.getSaveQuestionResponseEvent(question));		
		MessageUtil.postReply(category.getResponseEvent());
		
		reqEvent = null;
		category = null;
		sqe = null;
		oldQuestionToNewQuestionMap = null;
		oldCategoryToNewCategoryMap = null;
		question = null;
		
	}
	
	private boolean isCreateNewVersion(RiskQuestions question, RiskCategory category,
			SaveQuestionEvent reqEvent) {
		
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
		
		this.initializeNulls(question);
		this.initializeNulls(reqEvent);

		if (!question.getInitialAction().equals(reqEvent.getQuestionInitialAction())
				|| !question.getQuestionText().equals(reqEvent.getQuestionText())
				|| !question.getUiControlType().equals(reqEvent.getUiControlType())
				|| !question.getControlCode().equals(reqEvent.getControlCode())
				|| question.isAllowsFutureDates() != Boolean.getBoolean(reqEvent.getAllowFutureDates())
				|| question.isCollapsibleHeader() != Boolean.getBoolean(reqEvent.getCollapsibleHeader())
				|| question.isDefaultToSystemDate() != Boolean.getBoolean(reqEvent.getDefaultToSystemDate())
				|| question.isNumeric() != Boolean.getBoolean(reqEvent.getNumericOnly())
				|| question.isQuestionTextNotModifiable() != Boolean.getBoolean(reqEvent.getHardcoded())
				|| question.isRequired() != Boolean.getBoolean(reqEvent.getRequired())){
			isNewVersion = true;
		}
	
		return isNewVersion;
		
	}
	private static final String FALSE_STRING = "false";
	
	private void initializeNulls(SaveQuestionEvent reqEvent) {
		
		if (reqEvent.getQuestionInitialAction() == null){
			reqEvent.setQuestionInitialAction(PDConstants.BLANK);
		}
		if (reqEvent.getQuestionText() == null){
			reqEvent.setQuestionText(PDConstants.BLANK);
		}
		if (reqEvent.getUiControlType() == null){
			reqEvent.setUiControlType(PDConstants.BLANK);
		}
		if (reqEvent.getControlCode() == null){
			reqEvent.setControlCode(PDConstants.BLANK);
		}
		if (reqEvent.getAllowFutureDates() == null){
			reqEvent.setAllowFutureDates(FALSE_STRING);
		}
		if (reqEvent.getCollapsibleHeader() == null 
				|| reqEvent.getCollapsibleHeader().equals(PDConstants.BLANK)){
			reqEvent.setCollapsibleHeader(FALSE_STRING);
		}
		if (reqEvent.getHardcoded() == null
				|| reqEvent.getHardcoded().equals(PDConstants.BLANK)){
			reqEvent.setHardcoded(FALSE_STRING);
		}
		if (reqEvent.getNumericOnly() == null
				|| reqEvent.getNumericOnly().equals(PDConstants.BLANK)){
			reqEvent.setNumericOnly(FALSE_STRING);
		}
		if (reqEvent.getRequired() == null
				|| reqEvent.getRequired().equals(PDConstants.BLANK)){
			reqEvent.setRequired(FALSE_STRING);
		}
		if (reqEvent.getDefaultToSystemDate() == null
				|| reqEvent.getDefaultToSystemDate().equals(PDConstants.BLANK)){
			reqEvent.setDefaultToSystemDate(FALSE_STRING);
		}

	}

	private void initializeNulls(RiskQuestions question) {
		
		if (question.getInitialAction() == null){
			question.setInitialAction(PDConstants.BLANK);
		}
		if (question.getQuestionText() == null){
			question.setQuestionText(PDConstants.BLANK);
		}
		if (question.getUiControlType() == null){
			question.setUiControlType(PDConstants.BLANK);
		}
		if (question.getControlCode() == null){
			question.setControlCode(PDConstants.BLANK);
		}

		question.setModified(false);
	}		
	private RiskQuestions updateQuestion(RiskCategory category,
			UpdateCategoryQuestionEvent reqEvent, Map oldQuestionToNewQuestionMap, boolean wasCloned) {
		
		IHome home = new Home();

		RiskQuestions question = null;
		String newQuestionId = null;
		
		if (wasCloned){
			newQuestionId = (String) oldQuestionToNewQuestionMap.get(reqEvent.getRiskQuestionId());
		} else {
			newQuestionId = reqEvent.getRiskQuestionId();
		}
		SaveQuestionEvent sqe2 = new SaveQuestionEvent(reqEvent);
		sqe2.setRiskQuestionId(newQuestionId);
		sqe2.setRiskCategoryId(category.getOID());
		sqe2.setACreate(false);
		question = PDRiskQuestionHelper.saveRiskQuestion(sqe2);
		home.bind(question);
		
		home = null;
		newQuestionId = null;
		
		return question;
	}

}
