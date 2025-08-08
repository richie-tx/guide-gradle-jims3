//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\CheckResidentialPreConditionsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.juvenilecase.reply.CourtReferralAssessmentEvent;
import messaging.juvenilecase.reply.CourtReferralCompletionPreConditionFailedResponseEvent;
import messaging.riskanalysis.CheckCourtReferralCompletionPreconditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.MessageUtil;
import naming.RiskAnalysisConstants;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilecase.riskanalysis.RiskAnswer;

public class CheckCourtReferralCompletionPreconditionsCommand implements ICommand 
{
   
   /**
    * @roseuid 4357DD180205
    */
   public CheckCourtReferralCompletionPreconditionsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4357D9AF0229
    */
   public void execute(IEvent event) 
   {
	    
		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		CheckCourtReferralCompletionPreconditionsEvent preCondEvent = (CheckCourtReferralCompletionPreconditionsEvent) event;
		
		RiskAnalysis riskAnalysis = RiskAnalysis.find(preCondEvent.getRiskAnalysisId());
		
		if (!riskAnalysis.isCompleted()) 
		{
			//Get Case File information
			JuvenileCasefile juvCaseFile = JuvenileCasefile.find(preCondEvent.getCaseFileId());
			
			/***** 1. Start - Validate Reporting Referral ***************************************************************************/
			
			boolean reportingReferralMatched = false;
			String reportingReferral = "";
			
			// Retrieves all the responses associated with the RISK_ANALYSIS_ID
			Iterator<RiskAnswer> riskAnswers = RiskAnswer.findAll( 
					RiskAnalysisConstants.RISK_ANALYSIS_ID, preCondEvent.getRiskAnalysisId() );
			
			//Find reporting referral # supplied by user
			while(riskAnswers.hasNext())
			{
				RiskAnswer riskAnswer = riskAnswers.next();
				if (riskAnswer.getQuestionText().equalsIgnoreCase("Reporting Referral #:")) 
				{
					reportingReferral = riskAnswer.getText();
					break;
				}
			}
			
			//Attempt to find match for reporting referral within
			//referrals attached to casefile
			JJSReferral referral = null;
			if (reportingReferral.length() > 0)
			{
				Iterator<JJSReferral> casefileReferralsIter1 = InterviewHelper.getReferralsForJuvenilesCasefile(juvCaseFile.getJuvenile(), juvCaseFile.getOID()).iterator();
			    while (casefileReferralsIter1.hasNext())
				{
					referral = casefileReferralsIter1.next();
					if (reportingReferral.equalsIgnoreCase(referral.getReferralNum()))
					{
						reportingReferralMatched = true;
						break;
					}
				}
			}
			
			//If reporting referral is not found within referrals attached, dispatch a failed event
			if (!reportingReferralMatched) 
			{
//				CourtReferralCompletionPreConditionFailedResponseEvent preCondFailedEvent 
//					= new CourtReferralCompletionPreConditionFailedResponseEvent();
//				preCondFailedEvent.setMessage( "reportingReferralNoMatch");
//				dispatch.postEvent( preCondFailedEvent );
				this.postError("reportingReferralNoMatch");
			}
			
			/***** 1. End - Validate Reporting Referral ********************************************************************************/
			
			/***** 2. Start - Validate Reporting Referral Date of Disposition **********************************************************/
			boolean reportingReferralDispositionDateValid = false;
			boolean reportingReferralDispositionDateEmpty = false;
			
			if (reportingReferralMatched) 
			{
				
				if ( referral.getCourtDate() == null && referral.getIntakeDate() == null )
				{
					reportingReferralDispositionDateEmpty = true;
					
//					CourtReferralCompletionPreConditionFailedResponseEvent preCondFailedEvent 
//					= new CourtReferralCompletionPreConditionFailedResponseEvent();
//					preCondFailedEvent.setMessage( "reportingReferralDateEmpty");
//					dispatch.postEvent( preCondFailedEvent );
					this.postError("reportingReferralDateEmpty");
				}
				
				
				if (!reportingReferralDispositionDateEmpty) 
				{
				
				
					//Get JPO Assignment Date
					Date jpoAssignmentDate = null;
					Iterator<Assignment> assignments = Assignment.findAll("caseFileId", juvCaseFile.getOID());
					while(assignments.hasNext())
					{
						Assignment assignment = assignments.next();
						if (referral.getReferralNum().equalsIgnoreCase(assignment.getReferralNumber())) 
						{
							jpoAssignmentDate = assignment.getAssignmentAddDate();
							break;
						}
					}
					
					//Is date of dispostion (CourtDate) is equal to or greater then JPO assignment date 
					//for the current casefile
					if ( referral.getCourtDate() != null 
							&& jpoAssignmentDate != null ) 
					{
						if (referral.getCourtDate().compareTo(jpoAssignmentDate) >= 0) {
							reportingReferralDispositionDateValid = true;
						}
					} 
					else if ( referral.getIntakeDate() != null 
							&& jpoAssignmentDate != null ) 
					{
						if (referral.getIntakeDate().compareTo(jpoAssignmentDate) >= 0) {
							reportingReferralDispositionDateValid = true;
						}
					}
					
					//int test = referral.getCourtDate().compareTo(juvCaseFile.getJpoAssignmentDate());
					
					//If date of dispostion is not equal to or greater then JPO assignment date 
					//for the current casefile, dispatch a failed event
					if (!reportingReferralDispositionDateValid) 
					{
//						CourtReferralCompletionPreConditionFailedResponseEvent preCondFailedEvent 
//							= new CourtReferralCompletionPreConditionFailedResponseEvent();
//						preCondFailedEvent.setMessage( "reportingReferralDate");
//						dispatch.postEvent( preCondFailedEvent );
						this.postError("reportingReferralDate");
					}
					
				}
				
			}
			
			/***** 2. End - Validate Reporting Referral Date of Disposition *************************************************************/
			
			/***** 3. Start - Validate Reporting Referral Final Disposition *************************************************************/
			
			
			boolean codeIsAFinalDisposition = false;
			String jjsCourtDecision = "";
			
			if (reportingReferralMatched && reportingReferralDispositionDateValid) 
			{
				//Figure which code to use to fill in JuvenileDispositionCode
				JuvenileDispositionCode juvenileDispositionCode = null;
				JuvenileReferralDispositionCode juvenileReferralDispositionCode = null;
				if (referral.getCourtDispositionId() != null 
						&& referral.getCourtDispositionId().length() > 0) {
					juvenileDispositionCode = referral.getCourtDisposition();
				} else if (referral.getCourtResultId() != null
						&& referral.getCourtResultId().length() > 0) {
					juvenileDispositionCode = referral.getCourtResult();
				} else if (referral.getIntakeDecisionId() != null
					&& referral.getIntakeDecisionId().length() > 0) {
					juvenileReferralDispositionCode = referral.getIntakeDecisionComplex();
				}
				
				//Find out if code is a final disposition
				if (juvenileDispositionCode != null) 
				{
					if (juvenileDispositionCode.getJPCCode() != null
							&& juvenileDispositionCode.getJPCCode().length() > 0)
					{
						if (Integer.parseInt(juvenileDispositionCode.getJPCCode()) > 0) 
						{
							codeIsAFinalDisposition = true;
							jjsCourtDecision = juvenileDispositionCode.getShortDesc();
						}
					}
					
				} 
				else if (juvenileReferralDispositionCode != null) 
				{
					if (juvenileReferralDispositionCode.getDispositionCode() != null 
							&& juvenileReferralDispositionCode.getDispositionCode().length() > 0)  
					{
						if (juvenileReferralDispositionCode.getDispositionCode().equalsIgnoreCase("IA")) 
						{
							codeIsAFinalDisposition = true;
							jjsCourtDecision = juvenileReferralDispositionCode.getDescription();
						}
					}
				}
				
				//if code is not a final disposition, dispatch a failed event
				if (!codeIsAFinalDisposition) 
				{
//					CourtReferralCompletionPreConditionFailedResponseEvent preCondFailedEvent 
//						= new CourtReferralCompletionPreConditionFailedResponseEvent();
//					preCondFailedEvent.setMessage( "reportingReferralNotFinalDisposition");
//					dispatch.postEvent( preCondFailedEvent );
					this.postError("reportingReferralNotFinalDisposition");
				}
				
			}
		   	
			/***** 3. End - Validate Reporting Referral Final Disposition *************************************************************/
			
			//If no errors occur, send back jjsCourtDecision for display purposes
		   	CourtReferralAssessmentEvent courtReferralAssessmentEvent = new CourtReferralAssessmentEvent();
		   	courtReferralAssessmentEvent.setCompleted(false);
		   	courtReferralAssessmentEvent.setJjsCourtDecision(jjsCourtDecision);
		   	MessageUtil.postReply( courtReferralAssessmentEvent );
		   	
			

		//Is completed 
		} else {
			
			CourtReferralAssessmentEvent courtReferralAssessmentEvent = new CourtReferralAssessmentEvent();
			courtReferralAssessmentEvent.setCompleted(true);
			PDRiskAnalysisHelper.retrieveCourtReferralCompletedInformation(courtReferralAssessmentEvent, riskAnalysis);
			MessageUtil.postReply( courtReferralAssessmentEvent );
			
		}
   }

   private void postError(String msg){
		CourtReferralCompletionPreConditionFailedResponseEvent preCondFailedEvent 
			= new CourtReferralCompletionPreConditionFailedResponseEvent();
		preCondFailedEvent.setMessage(msg);
		MessageUtil.postReply( preCondFailedEvent );

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
