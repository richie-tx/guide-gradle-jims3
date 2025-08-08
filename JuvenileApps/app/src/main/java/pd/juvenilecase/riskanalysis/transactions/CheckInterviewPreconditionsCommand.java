//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\CheckInterviewPreconditionsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import messaging.juvenilecase.reply.InterviewPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.InterviewPrefillResponseEvent;
import messaging.juvenilecase.reply.JuvenileDeliquencyHistoryEvent;
import messaging.riskanalysis.CheckInterviewPreconditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.MessageUtil;
import naming.RiskAnalysisConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;

import pd.codetable.Code;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilecase.riskanalysis.RiskFormula;

public class CheckInterviewPreconditionsCommand implements ICommand
{
	/**
	 * @roseuid 4342C2E4035E
	 */
	public CheckInterviewPreconditionsCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 433C3D3C0351
	 */
	public void execute( IEvent event )
	{
		/*
		 * <pre-condition> Casefile case status = active and 
		 * supervision type = intake; status offender supervision;
		 * court supervison;deferred prosecution;deferred adjudication. 
		 * JUVENILE_RISK_ANALYSIS.PartNumber = 1 for the supervision 
		 * casefile has been completed. JPO for Part 2 assessment should 
		 * complete anytime < 14 days of casefile assignment and
		 * typically not same JPO for Part1.</pre-condition>
		 */
		//IDispatch dispatch = EventManager.getSharedInstance( EventManager.REPLY );

		CheckInterviewPreconditionsEvent preCondEvent = (CheckInterviewPreconditionsEvent)event;
		JuvenileCasefile juvCaseFile = JuvenileCasefile.find( preCondEvent.getCaseFileId() );

		if( juvCaseFile != null )
		{
			
			boolean riskAnalysisExist = PDRiskAnalysisHelper.doesRiskAnalysisExistWithinTheHour(juvCaseFile.getOID(), RiskAnalysisConstants.RISK_TYPE_PREA_FOLLOW_UP);
			if (riskAnalysisExist) {
				InterviewPreConditionFailedResponseEvent preCondFailedEvent = new InterviewPreConditionFailedResponseEvent();
				preCondFailedEvent.setMessage( RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE);
				MessageUtil.postReply(preCondFailedEvent);
			}
			
			InterviewPrefillResponseEvent intwPrefillResp = new InterviewPrefillResponseEvent();
			//hot fix: #32710 starts
			if(juvCaseFile!=null && juvCaseFile.getJuvenile()!=null){
			    	// Profile stripping fix task 97536
				//Juvenile juvenile = juvCaseFile.getJuvenile();
			    	JuvenileCore juvenile = juvCaseFile.getJuvenile();
				//
				intwPrefillResp.setJuvenileName(InterviewHelper.formatNameLastFirstMiddleSuffix(juvenile.getFirstName(), juvenile.getMiddleName(), juvenile.getLastName(), juvenile.getNameSuffix()));
				intwPrefillResp.setJuvNum(juvenile.getJuvenileNum());
			}
			//hot fix: #32710 ends
			{ Code sex = juvCaseFile.getJuvenile().getSex();
				String sexDescription = (sex == null) ? UIConstants.EMPTY_STRING : sex.getDescription();
				String sexId = (sex == null) ? UIConstants.EMPTY_STRING : sex.getCode();
				
				intwPrefillResp.setJuvSex( sexDescription );
				intwPrefillResp.setJuvSexId( sexId );
			}
			
			JuvenileDeliquencyHistoryEvent juvDelqHist = PDRiskAnalysisHelper.getDelinquencyHistory( juvCaseFile.getJuvenileNum(), 0 );
			
			if( juvDelqHist != null &&
					StringUtils.isNotEmpty( juvDelqHist.getAgeFirstReferred() ) )
			{ // BTW - "juvDelqHist" will always be non-null
				try
				{
					int ageFirstReferral = Integer.parseInt( juvDelqHist.getAgeFirstReferred() ) ;
					intwPrefillResp.setOnsetAge( ageFirstReferral );
				}
				catch( NumberFormatException nfe )
				{
				}
			}
			else
			{ // if no delinquency history found, assume a value
				intwPrefillResp.setOnsetAge( 15 );
			}
			//taken out - bug #56589
			//RiskAnalysis latestReferralRiskAnalysis =  PDRiskAnalysisHelper.getLatestReferralRiskAnalysis(preCondEvent.getJuvenileNumber());
			
			//if( latestReferralRiskAnalysis != null )
			///{
				RiskFormula activeFormula = PDRiskAnalysisHelper.getActiveFormulaByAssessmentType(RiskAnalysisConstants.RISK_TYPE_PREA_FOLLOW_UP);
				if (activeFormula != null){
					//intwPrefillResp.setLatestReferralScore(PDRiskAnalysisHelper.getRiskAnalysisTotalScore(latestReferralRiskAnalysis));
					MessageUtil.postReply( intwPrefillResp );
					PDRiskAnalysisHelper.retrieveRiskQuestionsByFormulaId(activeFormula.getOID());
				} else {
					this.postFailureRespEvent(RiskAnalysisConstants.NO_ACTIVE_FORMULA);
				}
			//}
			/*if( juvCaseFile.getCaseStatusId().equals( "A" ) && 
					(juvCaseFile.getSupervisionCategoryId().equals( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_PRE_PETITION ) || 
					 juvCaseFile.getSupervisionCategoryId().equals( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ )) )
			{
				
				boolean tjpcExistOnCasefile = PDRiskAnalysisHelper.doesTJPCRiskAnalysisExistForCasefile( preCondEvent.getCaseFileId() );
				
				// check if the Referral has been done before Interview can be done.
				// The Testing preconditions event is reused since the mapping xml has
				// this event for RiskAnalysis
				if( !juvCaseFile.getIsReferralRiskNeeded() || tjpcExistOnCasefile )
				{
					
					boolean riskAnalysisExist = PDRiskAnalysisHelper.doesRiskAnalysisExistWithinTheHour(juvCaseFile.getOID(), RiskAnalysisConstants.RISK_TYPE_INTERVIEW);
					if (riskAnalysisExist) {
						InterviewPreConditionFailedResponseEvent preCondFailedEvent = new InterviewPreConditionFailedResponseEvent();
						preCondFailedEvent.setMessage( RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE);
						MessageUtil.postReply(preCondFailedEvent);
					}
					
					InterviewPrefillResponseEvent intwPrefillResp = new InterviewPrefillResponseEvent();
					
					{ Code sex = juvCaseFile.getJuvenile().getSex();
						String sexDescription = (sex == null) ? UIConstants.EMPTY_STRING : sex.getDescription();
						String sexId = (sex == null) ? UIConstants.EMPTY_STRING : sex.getCode();
						
						intwPrefillResp.setJuvSex( sexDescription );
						intwPrefillResp.setJuvSexId( sexId );
					}
					
					/*
					 * This attribute represents the number of points associated with the
					 * age of the first referral to HCJPD.
					 * Source=JUVENILE_DELINQUENCY_HISTORY.AgeFirstReferral. Required If
					 * age is 16 then points=1 if age is 14-15 then points=2if age is 13
					 * or younger then points=3
					 */

					/*JuvenileDeliquencyHistoryEvent juvDelqHist = PDRiskAnalysisHelper.getDelinquencyHistory( juvCaseFile.getJuvenileNum(), 0 );
					
					if( juvDelqHist != null &&
							StringUtils.isNotEmpty( juvDelqHist.getAgeFirstReferred() ) )
					{ // BTW - "juvDelqHist" will always be non-null
						try
						{
							int ageFirstReferral = Integer.parseInt( juvDelqHist.getAgeFirstReferred() ) ;
							intwPrefillResp.setOnsetAge( ageFirstReferral );
						}
						catch( NumberFormatException nfe )
						{
						}
					}
					else
					{ // if no delinquency history found, assume a value
						intwPrefillResp.setOnsetAge( 15 );
					}

					/* RiskAnalysis latestReferralRiskAnalysis = null;
					String juvNumber = preCondEvent.getJuvenileNumber() ;
					RiskAnalysis latestCustodyeferralRiskAnalysis 
						= PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile( juvNumber, RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL );
					RiskAnalysis latestNonCustodyReferralRiskAnalysis 
						= PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile( juvNumber, RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL );

					RiskAnalysis latestCourtReferralMaleRiskAnalysis 
						= PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile( juvNumber, RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE );
					RiskAnalysis latestCourtReferralFemaleRiskAnalysis 
						= PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile( juvNumber, RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE );

					if (latestCustodyeferralRiskAnalysis != null && latestNonCustodyReferralRiskAnalysis != null) 
					{
						if(latestCustodyeferralRiskAnalysis.getEnteredDate().compareTo(latestNonCustodyReferralRiskAnalysis.getEnteredDate()) > 0) 
						{
							latestReferralRiskAnalysis = latestCustodyeferralRiskAnalysis;
						} 
						else 
						{
							latestReferralRiskAnalysis = latestNonCustodyReferralRiskAnalysis;
						}
					} 
					else if (latestCustodyeferralRiskAnalysis != null && latestNonCustodyReferralRiskAnalysis == null) 
					{
						latestReferralRiskAnalysis = latestCustodyeferralRiskAnalysis;
					} 
					else if (latestNonCustodyReferralRiskAnalysis != null && latestCustodyeferralRiskAnalysis == null) 
					{
						latestReferralRiskAnalysis = latestNonCustodyReferralRiskAnalysis;
					} 
					else if ( (latestCustodyeferralRiskAnalysis == null && latestNonCustodyReferralRiskAnalysis == null) &&
						(latestCourtReferralMaleRiskAnalysis != null || latestCourtReferralFemaleRiskAnalysis != null)	) 
					{
						
						if (latestCourtReferralMaleRiskAnalysis != null) 
						{
							latestReferralRiskAnalysis = latestCourtReferralMaleRiskAnalysis;
						}
						
						if (latestCourtReferralFemaleRiskAnalysis != null) 
						{
							latestReferralRiskAnalysis = latestCourtReferralFemaleRiskAnalysis;
						}
						
						
					}
					*/
					/*RiskAnalysis latestReferralRiskAnalysis =  PDRiskAnalysisHelper.getLatestReferralRiskAnalysis(preCondEvent.getJuvenileNumber());
					
					if( latestReferralRiskAnalysis != null )
					{
						RiskFormula activeFormula = PDRiskAnalysisHelper.getActiveFormulaByAssessmentType(RiskAnalysisConstants.RISK_TYPE_INTERVIEW);
						if (activeFormula != null){
							intwPrefillResp.setLatestReferralScore(PDRiskAnalysisHelper.getRiskAnalysisTotalScore(latestReferralRiskAnalysis));
							MessageUtil.postReply( intwPrefillResp );
							PDRiskAnalysisHelper.retrieveRiskQuestionsByFormulaId(activeFormula.getOID());
						} else {
							this.postFailureRespEvent(RiskAnalysisConstants.NO_ACTIVE_FORMULA);
						}
					}
					else
					{
						postFailureRespEvent("5");
					}
				} // if !juvCaseFile.getIsReferralRiskNeeded()
				else
				{
					postFailureRespEvent("1");
				}
			}
			else
			{
				postFailureRespEvent("2");
			}*/
		}
	}


	private void postFailureRespEvent(String msg) {
		InterviewPreConditionFailedResponseEvent preCondFailedEvent = new InterviewPreConditionFailedResponseEvent();
		preCondFailedEvent.setMessage(msg);
		MessageUtil.postReply(preCondFailedEvent);
	}


	/**
	 * @param event
	 * @roseuid 433C3D3C0364
	 */
	public void onRegister( IEvent event )
	{
	}

	/**
	 * @param event
	 * @roseuid 433C3D3C036E
	 */
	public void onUnregister( IEvent event )
	{
	}

	/**
	 * @param anObject
	 * @roseuid 433C3D3C0370
	 */
	public void update( Object anObject )
	{
	}
}
