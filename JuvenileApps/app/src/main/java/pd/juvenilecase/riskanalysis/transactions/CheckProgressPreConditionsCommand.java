// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\CheckResidentialPreConditionsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import messaging.juvenilecase.reply.ProgressPreConditionFailedResponseEvent;
import messaging.riskanalysis.CheckProgressPreConditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.MessageUtil;
import naming.PDCodeTableConstants;
import naming.RiskAnalysisConstants;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskFormula;

public class CheckProgressPreConditionsCommand implements ICommand {

	/**
	 * @roseuid 4357DD180205
	 */
	public CheckProgressPreConditionsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4357D9AF0229
	 */
	public void execute(IEvent event) {
		/*
		 * JUVENILE_CASEFILE.CaseStatus = active and
		 * JUVENILE.CASEFILE.SupervisionType=Community supervision,Residential
		 * supervision, Intensive Supervision,Enhanced Supervision.
		 * JUVENILE_RISK_ANALYSIS.PartNumber=4a or 4b must be completed. It is
		 * not required that 1, 2 or 3 be done prior to doing parts 4a, 4 b or
		 * 5.
		 */

		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		CheckProgressPreConditionsEvent preCondEvent = (CheckProgressPreConditionsEvent) event;
		if (!preCondEvent.isBypassPreconditionEdit()) {
			JuvenileCasefile juvCaseFile = JuvenileCasefile.find(preCondEvent.getCaseFileId());

			boolean contFlag=true;
			if (juvCaseFile != null && juvCaseFile.getCaseStatusId().equals("A")) {
				
				boolean riskAnalysisExist = false;
				if(preCondEvent.getFormula()!=null && preCondEvent.getFormula().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS))
					riskAnalysisExist = PDRiskAnalysisHelper.doesRiskAnalysisExistWithinTheHour(juvCaseFile.getOID(), RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS);
				else
					riskAnalysisExist = PDRiskAnalysisHelper.doesRiskAnalysisExistWithinTheHour(juvCaseFile.getOID(), RiskAnalysisConstants.RISK_TYPE_PROGRESS);
				if (riskAnalysisExist) {				
					this.postFailureRespEvent(RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE);
				}
				
				if (juvCaseFile.getSupervisionTypeId().equals("ACS")){
					if (!juvCaseFile.getIsCommunityRiskNeeded() && !juvCaseFile.getIsResidentialRiskNeeded()) {
						contFlag=false;
						this.postSuccessRespEvent(preCondEvent);
					}else{
						if (juvCaseFile.getIsCommunityRiskNeeded() && juvCaseFile.getIsResidentialRiskNeeded()){
							contFlag=false;
							this.postFailureRespEvent("4");
						}
						else{
							
						}
					}
				}
				if(contFlag){
					if (juvCaseFile.getSupervisionCategoryId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM) ||
							juvCaseFile.getSupervisionCategoryId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ))
					{
						if (!juvCaseFile.getIsCommunityRiskNeeded()) {
							this.postSuccessRespEvent(preCondEvent);
						} else {
							this.postFailureRespEvent("1");
						}
					}
					else if (juvCaseFile.getSupervisionCategoryId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES)) {
						if (!juvCaseFile.getIsResidentialRiskNeeded()) {
							this.postSuccessRespEvent(preCondEvent);
						} else {
							this.postFailureRespEvent("2");
						}
					}
					else{
						this.postFailureRespEvent("3");
					}
				}
			}
			else {
				this.postFailureRespEvent("3");
			}
		} else {
			PDRiskAnalysisHelper.retrieveRiskQuestionsByFormulaId(preCondEvent.getFormulaId());			
		}	
	}

	/**
	 * @param string
	 * @param dispatch
	 */
	private void postFailureRespEvent(String msg) {
		ProgressPreConditionFailedResponseEvent preCondFailedEvent = new ProgressPreConditionFailedResponseEvent();
		preCondFailedEvent.setMessage(msg);
		MessageUtil.postReply(preCondFailedEvent);
	}

	/**
	 * @param preCondEvent
	 * @param dispatch
	 */
	private void postSuccessRespEvent(CheckProgressPreConditionsEvent preCondEvent) {
		RiskFormula activeFormula;
		if(preCondEvent.getFormula()!=null && preCondEvent.getFormula().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS))
			activeFormula = PDRiskAnalysisHelper.getActiveFormulaByAssessmentType(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS);
		else
			activeFormula = PDRiskAnalysisHelper.getActiveFormulaByAssessmentType(RiskAnalysisConstants.RISK_TYPE_PROGRESS);
		if (activeFormula != null){
			PDRiskAnalysisHelper.sendProgressPrefillInfo(preCondEvent);
			PDRiskAnalysisHelper.retrieveRiskQuestionsByFormulaId(activeFormula.getOID());
		} else {
			this.postFailureRespEvent(RiskAnalysisConstants.NO_ACTIVE_FORMULA);
		}
	}

	/**
	 * @param event
	 * @roseuid 4357D9AF022B
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4357D9AF022D
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4357D9AF0233
	 */
	public void update(Object anObject) {

	}
}
