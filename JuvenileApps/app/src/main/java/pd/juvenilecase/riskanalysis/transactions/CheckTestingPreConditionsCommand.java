//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\CheckInterviewPreconditionsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import messaging.juvenilecase.reply.TestingPreConditionFailedResponseEvent;
import messaging.riskanalysis.CheckTestingPreConditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.MessageUtil;
import naming.PDCodeTableConstants;
import naming.RiskAnalysisConstants;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilecase.riskanalysis.RiskFormula;

public class CheckTestingPreConditionsCommand implements ICommand 
{
   
   /**
    * @roseuid 4342C2E4035E
    */
   public CheckTestingPreConditionsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3C0351
    */
   public void execute(IEvent event) 
   {
		/*
		<pre-condition>
		Casefile case status = active and supervision type=court supervison.
		JUVENILE_RISK_ANALYSIS.PartNumber=1] and [JUVENILE_RISK_ANALYSIS.PartNumber=2] for supervison casefile has been completed.
		Part 3 is only done if the results of Part 2 recommends that Part 3 be done. If recommended, Part 3 done <14 days of 
		casefile assignment
		</pre-condition>
		*/
		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			
		CheckTestingPreConditionsEvent preCondEvent = (CheckTestingPreConditionsEvent) event;
		JuvenileCasefile juvCaseFile = JuvenileCasefile.find(preCondEvent.getCaseFileId());
			
		if(juvCaseFile != null)
		{
			//taken out for US 22244
			//if( juvCaseFile.getCaseStatusId().equals("A") 
				//&& juvCaseFile.getSupervisionCategoryId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ))
		
				
				boolean riskAnalysisExist = PDRiskAnalysisHelper.doesRiskAnalysisExistWithinTheHour(juvCaseFile.getOID(), RiskAnalysisConstants.RISK_TYPE_TESTING);
				if (riskAnalysisExist) {
					postFailureResponse(RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE);
				}
				/* All pre-conditions removed for Testing Risk Assessment as per User Story 22244
				RiskAnalysis interviewRiskAnalysis = PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile(juvCaseFile.getJuvenileId(), RiskAnalysisConstants.RISK_TYPE_INTERVIEW);
				
				if (interviewRiskAnalysis == null) {
					 //no interview assessment, cannot do a testing assessment
					postFailureResponse("1");
					
				}*/
				RiskFormula activeFormula = PDRiskAnalysisHelper.getActiveFormulaByAssessmentType(RiskAnalysisConstants.RISK_TYPE_TESTING);
				if (activeFormula != null){
					// Testing Assessment is done only if Interview Assessment recommends 
					// and it will be at the discretion of the JPO (Dec 13, 2005 clarified with EVE).
					PDRiskAnalysisHelper.retrieveRiskQuestionsByFormulaId(activeFormula.getOID());
				} else {
					this.postFailureResponse(RiskAnalysisConstants.NO_ACTIVE_FORMULA);
				}
			/*}
			else
			{
				postFailureResponse("3");
			}*/
		}
   }
   private void postFailureResponse(String msg){
		TestingPreConditionFailedResponseEvent preCondFailedEvent = new TestingPreConditionFailedResponseEvent();
		preCondFailedEvent.setMessage(msg);
		MessageUtil.postReply( preCondFailedEvent );

   }
   /**
    * @param event
    * @roseuid 433C3D3C0364
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3C036E
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 433C3D3C0370
    */
   public void update(Object anObject) 
   {
    
   }
}
