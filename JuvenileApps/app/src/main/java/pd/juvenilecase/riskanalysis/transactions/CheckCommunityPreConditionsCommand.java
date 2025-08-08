//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\CheckResidentialPreConditionsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import messaging.juvenilecase.reply.CommunityPreConditionFailedResponseEvent;
import messaging.riskanalysis.CheckCommunityPreConditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.MessageUtil;
import naming.PDCodeTableConstants;
import naming.RiskAnalysisConstants;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskFormula;

public class CheckCommunityPreConditionsCommand implements ICommand 
{
   
   /**
    * @roseuid 4357DD180205
    */
   public CheckCommunityPreConditionsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4357D9AF0229
    */
   public void execute(IEvent event) 
   {
	    /*
		Casefile case status = active and supervision type=community supervision;intensive supervision
		JPO should complete Part 4a anytime < 14 days of casefile assignment, and can be by same JPO as Part1.  
		If Part 4a is done, part 4b will not be since Community and Residential supervision are mutally exclusive.
		It is not required that 1, 2 or 3 be done prior to doing parts 4a, 4 b or 5.
		*/
		CheckCommunityPreConditionsEvent preCondEvent = (CheckCommunityPreConditionsEvent) event;
		JuvenileCasefile juvCaseFile = JuvenileCasefile.find(preCondEvent.getCaseFileId());
				
		if(juvCaseFile != null)
		{
			if(	juvCaseFile.getCaseStatusId().equals("A") 
				&& 
				(juvCaseFile.getSupervisionCategoryId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM) 
						|| juvCaseFile.getSupervisionCategoryId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ)))
			{
				boolean riskAnalysisExist = PDRiskAnalysisHelper.doesRiskAnalysisExistWithinTheHour(juvCaseFile.getOID(), RiskAnalysisConstants.RISK_TYPE_COMMUNITY);
				if (riskAnalysisExist) {
					this.postFailureRespEvent(RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE);
				} else {
					RiskFormula activeFormula = PDRiskAnalysisHelper.getActiveFormulaByAssessmentType(RiskAnalysisConstants.RISK_TYPE_COMMUNITY);
					if (activeFormula != null){
						PDRiskAnalysisHelper.retrieveRiskQuestionsByFormulaId(activeFormula.getOID());
					} else {
						this.postFailureRespEvent(RiskAnalysisConstants.NO_ACTIVE_FORMULA);
					}
				}
			}
			else
			{
				this.postFailureRespEvent("CaseFile " + preCondEvent.getCaseFileId() + " does not have case/supervision status Active for the Juvenile Number " 
								+ preCondEvent.getJuvenileNumber());
			}
		}
		else
		{
			this.postFailureRespEvent("CaseFile " + preCondEvent.getCaseFileId() + " does not have case/supervision status Active for the Juvenile Number " + preCondEvent.getJuvenileNumber());
		}
   }
	private void postFailureRespEvent(String msg) {
		CommunityPreConditionFailedResponseEvent preCondFailedEvent = new CommunityPreConditionFailedResponseEvent();
		preCondFailedEvent.setMessage(msg);
		MessageUtil.postReply(preCondFailedEvent);
	}
  
  
   /**
    * @param event
    * @roseuid 4357D9AF022B
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4357D9AF022D
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4357D9AF0233
    */
   public void update(Object anObject) 
   {
    
   }
}
