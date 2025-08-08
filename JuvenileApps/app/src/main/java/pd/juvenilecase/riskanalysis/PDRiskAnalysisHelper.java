/*
 * Created on Oct 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.riskanalysis;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang.StringUtils ;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.ujac.util.BeanComparator;

import messaging.juvenilecase.GetJuvenileCasefilesEvent;
import messaging.juvenilecase.GetJuvenileTraitTypesEvent;
import messaging.juvenilecase.GetProbationStatusForRiskAssessmentEvent;
import messaging.juvenilecase.reply.CommunityAssessmentEvent;
import messaging.juvenilecase.reply.CourtReferralAssessmentEvent;
import messaging.juvenilecase.reply.InterviewAssessmentEvent;
import messaging.juvenilecase.reply.JuvenileDeliquencyHistoryEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilecase.reply.NoJuvenileCasefilesResponseEvent;
import messaging.juvenilecase.reply.ProgressAssessmentEvent;
import messaging.juvenilecase.reply.ProgressPrefillResponseEvent;
import messaging.juvenilecase.reply.ReferralAssessmentEvent;
import messaging.juvenilecase.reply.ResidentialAssessmentEvent;
import messaging.juvenilecase.reply.RiskAnswerResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.juvenilecase.reply.RiskRecommendationResponseEvent;
import messaging.juvenilecase.reply.RiskSuggestedCasePlanDomainResponseEvent;
import messaging.juvenilecase.reply.RiskWeightedResponseEvent;
import messaging.juvenilecase.reply.TestingAssessmentEvent;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.riskanalysis.CheckPreConditionsForJuvenileEvent;
import messaging.riskanalysis.CheckProgressPreConditionsEvent;
import messaging.riskanalysis.GetLatestRiskAnalysisEvent;
import messaging.riskanalysis.GetRecentReferralRiskAnalysisByDateEvent;
import messaging.riskanalysis.GetRecentRiskAnalysisForJuvenileEvent;
import messaging.riskanalysis.GetRiskAnalysisByCasefileIdAndAssmtTypeEvent;
import messaging.riskanalysis.GetRiskAssessmentDetailsEvent;
import messaging.riskanalysis.RiskQuestionAnswerEvent;
import messaging.riskanalysis.SaveCommunityAssessmentEvent;
import messaging.riskanalysis.SaveCourtReferralAssessmentEvent;
import messaging.riskanalysis.SaveInterviewAssessmentEvent;
import messaging.riskanalysis.SaveProgressAssessmentEvent;
import messaging.riskanalysis.SaveReferralAssessmentEvent;
import messaging.riskanalysis.SaveResidentialAssessmentEvent;
import messaging.riskanalysis.SaveRiskAssessmentEvent;
import messaging.riskanalysis.SaveTestingAssessmentEvent;
import messaging.riskanalysis.query.GetActiveFormulaByAssessmentType;
import messaging.riskanalysis.reply.CourtReferralRiskComputationReponseEvent;
import messaging.riskanalysis.reply.CustodyReferralRiskComputationReponseEvent;
import messaging.riskanalysis.reply.NonCustodyReferralComputationResponseEvent;
import messaging.riskanalysis.reply.CommunityRiskComputationReponseEvent;
import messaging.riskanalysis.reply.InterviewRiskComputationReponseEvent;
import messaging.riskanalysis.reply.ProgressRiskComputationReponseEvent;
import messaging.riskanalysis.reply.ResidentialRiskComputationReponseEvent;
import messaging.riskanalysis.reply.RiskAssessmentResponseEvent;
import messaging.riskanalysis.reply.RiskComputationReponseEvent;
import messaging.riskanalysis.reply.TestingRiskComputationReponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.RequestEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.MessageUtil;
import mojo.km.utilities.CollectionUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.RiskAnalysisConstants;
import naming.UIConstants;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.common.util.DateFormatter;
import pd.exception.ComputationValidationException;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.JuvenileTrait;
import pd.juvenilecase.TraitType;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.riskanalysis.riskanalysiscomputation.RiskComputationContext;
import pd.juvenilecase.rules.RuleGroupConditionView;
import pd.juvenilecase.riskanalysis.RiskAnalysisRecommendation;


/**
 * @author kmurthy To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PDRiskAnalysisHelper
{
	
public static boolean valueIsAllDigits(String value)
{
	boolean isAllDigits = true;
	for (int i = 0; i < value.length(); i++)
	{
		if (!Character.isDigit(value.charAt(i)))
		{
			isAllDigits = false;
			break;
        }
	}

	return isAllDigits;
}
	
public static boolean doesRiskAnalysisExistWithinTheHour(String casefile, String assessmentType) {
		
		GetLatestRiskAnalysisEvent event = new GetLatestRiskAnalysisEvent();
		event.setCasefileID( casefile );
		event.setAssessmentType( assessmentType );
		Iterator<RiskAnalysis> i = RiskAnalysis.findAll( event );
		boolean bool = false;
		
		if( i != null && i.hasNext() )
		{
			while(i.hasNext())
			{
				RiskAnalysis riskAnalysis = i.next();
				
				//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar enteredDate = Calendar.getInstance();
				enteredDate.setTime(riskAnalysis.getEnteredDate());
				//System.out.println("Entered Date Time : " + dateFormat.format(enteredDate.getTime()));
				
				Calendar currentDateMinusOneHour = Calendar.getInstance();
				currentDateMinusOneHour.add(Calendar.HOUR, -1);
				//System.out.println("Current Date Time Minus One Hour : " + dateFormat.format(currentDateMinusOneHour.getTime()));
				
				if (currentDateMinusOneHour.after(enteredDate)) {
					//A casefile does not exist within the hour of the same type with same casefile #
					bool = false;
				} else {
					//A casefile exist within the hour of the same type with same casefile #
					bool = true;
				}
				
			}
			
		} else {
			bool = false;
		}
		
		return bool;
	}
	
	/*
	 * This method will set the risk analysis flags on all casefiles to false or
	 * not required juvenileNum is the juv num riskAssessmentType - is the type of
	 * flag to flip apply to Lower flags means that if a for instance interview is
	 * being set to no, and applyToLowerFlags is true then the Referral flag will
	 * also be set to no.
	 */
	/// @Deprecated
	/// @param aJuvenileNum
	/// @param aRiskAssessmentType
	/// @param aSupervisionCategory
	public static void turnRiskFlagOffOnAllCasefiles( String aJuvenileNum, String aRiskAssessmentType, String aSupervisionCategory )
	{
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REPLY );
		String errorMsg = null;

		if( aJuvenileNum != null && !aJuvenileNum.equals( UIConstants.EMPTY_STRING ) )
		{
			if( aRiskAssessmentType != null )
			{
				GetJuvenileCasefilesEvent getEvent = new GetJuvenileCasefilesEvent();
				getEvent.setJuvenileNum( aJuvenileNum );

				Iterator<JuvenileCasefile> i = JuvenileCasefile.findAll( getEvent );
				if( i != null && i.hasNext() )
				{
					while(i.hasNext())
					{
						JuvenileCasefile casefile = i.next();
						if( RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL.equals( aRiskAssessmentType ) )
						{
							casefile.setIsReferralRiskNeeded( false );
						}
						else if( RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL.equals( aRiskAssessmentType ) )
						{
							casefile.setIsReferralRiskNeeded( false );
						}
						
					
						else if( RiskAnalysisConstants.RISK_TYPE_PROGRESS.equals( aRiskAssessmentType ) )
						{
							casefile.setIsProgressRiskNeeded( false );
						}
						else if(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS.equals(aRiskAssessmentType))
						{
							casefile.setIsResProgressRiskNeeded(false);
						}
						else if( RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL.equals( aRiskAssessmentType ) )
						{
							casefile.setIsResidentialRiskNeeded( false );
						}
						else if( RiskAnalysisConstants.RISK_TYPE_COMMUNITY.equals( aRiskAssessmentType ) )
						{
							casefile.setIsCommunityRiskNeeded( false );
						}

						String supervisionCategoty = casefile.getSupervisionCategoryId();
						if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ.equalsIgnoreCase( supervisionCategoty ) )
						{
							if( RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL.equals( aRiskAssessmentType ) )
							{
								casefile.setIsReferralRiskNeeded( false );
							}
							else if( RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL.equals( aRiskAssessmentType ) )
							{
								casefile.setIsReferralRiskNeeded( false );
							}
						}
						else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_PRE_PETITION.equalsIgnoreCase( supervisionCategoty ) )
						{
							if( RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL.equals( aRiskAssessmentType ) )
							{
								casefile.setIsReferralRiskNeeded( false );
							}
							else if( RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL.equals( aRiskAssessmentType ) )
							{
								casefile.setIsReferralRiskNeeded( false );
							}
						}
						else if( PDCodeTableConstants.CASEFILE_SUPERVISION_TYPE_ENHANCED_SUPERVISION.equals( casefile.getSupervisionTypeId() ) )
						{
							if( RiskAnalysisConstants.RISK_TYPE_COMMUNITY.equals( aRiskAssessmentType ) )
							{
								casefile.setIsCommunityRiskNeeded( false );
								casefile.setIsResidentialRiskNeeded( false );
							}
							else if( RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL.equals( aRiskAssessmentType ) )
							{
								casefile.setIsCommunityRiskNeeded( false );
								casefile.setIsResidentialRiskNeeded( false );
							}
						}
						else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM.equalsIgnoreCase( supervisionCategoty ) )
						{
							if( RiskAnalysisConstants.RISK_TYPE_COMMUNITY.equals( aRiskAssessmentType ) )
							{
								casefile.setIsCommunityRiskNeeded( false );
							}
						}
						else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ.equalsIgnoreCase( supervisionCategoty ) )
						{
							if( RiskAnalysisConstants.RISK_TYPE_COMMUNITY.equals( aRiskAssessmentType ) )
							{
								casefile.setIsCommunityRiskNeeded( false );
							}
						}
						else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES.equalsIgnoreCase( supervisionCategoty ) )
						{
							if( RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL.equals( aRiskAssessmentType ) )
							{
								casefile.setIsResidentialRiskNeeded( false );
							}
						}
					}// end while
				}// end if null
				else
				{
					errorMsg = "No casefiles found for Juvenile Number: " + getEvent.getJuvenileNum();
				}
			}
			else
			{
				errorMsg = "Risk Assessment Type is invalid.";
			}
		}
		else
		{
			errorMsg = "Juvenile Number is invalid.";
		}

		if( errorMsg != null )
		{
			NoJuvenileCasefilesResponseEvent none = new NoJuvenileCasefilesResponseEvent();
			none.setMessage( errorMsg );
			dispatch.postEvent( none );
		}

		return;
	}
	/**
	 * @param thisRiskAnalysis
	 */
	public static void turnRiskFlagOffOnAllCasefiles( RiskAnalysis thisRiskAnalysis )
	{
		GetJuvenileCasefilesEvent getEvent = new GetJuvenileCasefilesEvent();
		getEvent.setJuvenileNum( thisRiskAnalysis.getJuvenileNum() );
		
		Iterator iter = JuvenileCasefile.findAll( getEvent );
		List <JuvenileCasefile> activeCaseFileList = CollectionUtil.iteratorToList(iter);
		
		for (int j = 0; j < activeCaseFileList.size(); j++) {
			JuvenileCasefile casefile = activeCaseFileList.get(j);
			
			setFlagsBasedOnNewRiskAssessmentType(casefile, thisRiskAnalysis.getAssessmentType(), null);

			setFlagsBasedOnSupervisionCategoryAndNewRiskAssessmentType(casefile, thisRiskAnalysis.getAssessmentType());
		}
	}

	private static void setFlagsBasedOnSupervisionCategoryAndNewRiskAssessmentType(
			JuvenileCasefile casefile, String newRiskAssessmentType) {
		
		if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ.equalsIgnoreCase( casefile.getSupervisionCategoryId() ) )
		{
			if( RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL.equals( newRiskAssessmentType ) )
			{
				casefile.setIsReferralRiskNeeded( false );
			}
			else if( RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL.equals( newRiskAssessmentType ) )
			{
				casefile.setIsReferralRiskNeeded( false );
			}
			//added for US 22244
			else if( RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE.equals( newRiskAssessmentType ) ||  RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE.equals( newRiskAssessmentType ))
			{
				casefile.setIsReferralRiskNeeded( false );
			}
		}
		else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_PRE_PETITION.equalsIgnoreCase( casefile.getSupervisionCategoryId() ) )
		{
			if( RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL.equals( newRiskAssessmentType ) )
			{
				casefile.setIsReferralRiskNeeded( false );
			}
			else if( RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL.equals( newRiskAssessmentType ) )
			{
				casefile.setIsReferralRiskNeeded( false );
			}
			//added for US 22244
			else if( RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE.equals( newRiskAssessmentType ) ||  RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE.equals( newRiskAssessmentType ))
			{
				casefile.setIsReferralRiskNeeded( false );
			}
		}
		else if( PDCodeTableConstants.CASEFILE_SUPERVISION_TYPE_ENHANCED_SUPERVISION.equals( casefile.getSupervisionTypeId() ) )
		{
			if( RiskAnalysisConstants.RISK_TYPE_COMMUNITY.equals( newRiskAssessmentType ) )
			{
				casefile.setIsCommunityRiskNeeded( false );
				casefile.setIsResidentialRiskNeeded( false );
			}
			else if( RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL.equals( newRiskAssessmentType ) )
			{
				casefile.setIsCommunityRiskNeeded( false );
				casefile.setIsResidentialRiskNeeded( false );
			}
		}
		else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM.equalsIgnoreCase( casefile.getSupervisionCategoryId() ) )
		{
			if( RiskAnalysisConstants.RISK_TYPE_COMMUNITY.equals( newRiskAssessmentType ) )
			{
				casefile.setIsCommunityRiskNeeded( false );
			}
		}
		else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ.equalsIgnoreCase( casefile.getSupervisionCategoryId() ) )
		{
			if( RiskAnalysisConstants.RISK_TYPE_COMMUNITY.equals( newRiskAssessmentType ) )
			{
				casefile.setIsCommunityRiskNeeded( false );
			}
		}
		else if( PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES.equalsIgnoreCase( casefile.getSupervisionCategoryId() ) )
		{
			if( RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL.equals( newRiskAssessmentType ) )
			{
				casefile.setIsResidentialRiskNeeded( false );
			}
		}	
	}

	public static void setFlagsBasedOnNewRiskAssessmentType(JuvenileCasefile casefile, String newAssessmentType, String caseFileId) {

		if (casefile == null){
			casefile = JuvenileCasefile.find(caseFileId);
		}
		
		if( RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL.equals( newAssessmentType ) )
		{
			casefile.setIsReferralRiskNeeded( false );
		}
		else if( RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL.equals( newAssessmentType ) )
		{
			casefile.setIsReferralRiskNeeded( false );
		}	
		//added for US 22244
		else if( RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE.equals( newAssessmentType ) ||  RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE.equals( newAssessmentType ))
		{
			casefile.setIsReferralRiskNeeded( false );
		}
		else if( RiskAnalysisConstants.RISK_TYPE_PROGRESS.equals( newAssessmentType ) )
		{
			casefile.setIsProgressRiskNeeded( false );
		}
		else if( RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS.equals( newAssessmentType ) )
		{
			casefile.setIsResProgressRiskNeeded( false );
		}
		else if( RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL.equals( newAssessmentType ) )
		{
			casefile.setIsResidentialRiskNeeded( false );
			if(PDCodeTableConstants.CASEFILE_SUPERVISION_TYPE_ENHANCED_SUPERVISION.equals(casefile.getSupervisionTypeId())){
				casefile.setIsCommunityRiskNeeded(false);
			}
		}
		else if( RiskAnalysisConstants.RISK_TYPE_COMMUNITY.equals( newAssessmentType ) )
		{
			casefile.setIsCommunityRiskNeeded( false );
			if(PDCodeTableConstants.CASEFILE_SUPERVISION_TYPE_ENHANCED_SUPERVISION.equals(casefile.getSupervisionTypeId())){
				casefile.setIsResidentialRiskNeeded(false);
			}
		}
	}

	/**
	 * @deprecated
	 * @param assessmenttype
	 * @param dispatch
	 */
/*	public static void retrieveRiskQuestions( 
			String assessmenttype, IDispatch dispatch)
	{
		Iterator questions = RiskQuestions.findAll( RiskAnalysisConstants.RISK_ASSESSMENT_TYPE, assessmenttype );

		while(questions.hasNext())
		{
			RiskQuestions riskQuestion = (RiskQuestions)questions.next();
			RiskQuestionResponseEvent riskQuesResponse = new RiskQuestionResponseEvent();
			riskQuesResponse.setAssessmentType( riskQuestion.getAssessmentType() );

			try
			{
				riskQuesResponse.setQuestionID( Integer.parseInt( riskQuestion.getOID().toString() ) );
			}
			catch(NumberFormatException nfe)
			{
				riskQuesResponse.setQuestionID( 0 );
			}

			riskQuesResponse.setQuestionText( riskQuestion.getQuestionText() );
			riskQuesResponse.setUiControlType( riskQuestion.getUiControlType() );
			riskQuesResponse.setControlCode( riskQuestion.getControlCode() );
			int thisQNum = new Integer( riskQuestion.getQuestionNbr() );
			riskQuesResponse.setQuestionNbr( thisQNum );
			riskQuesResponse.setInitialAction(riskQuestion.getInitialAction());
			riskQuesResponse.setHelpFileId(riskQuestion.getHelpFileId());
			riskQuesResponse.setRequired(riskQuestion.isRequired());
			riskQuesResponse.setNumeric(riskQuestion.isNumeric());
			riskQuesResponse.setAllowsFutureDates(riskQuestion.isAllowsFutureDates());
			riskQuesResponse.setDefaultToSystemDate(riskQuestion.isDefaultToSystemDate());
			riskQuesResponse.setRefreshable(riskQuestion.isRefreshable());
			
			dispatch.postEvent( riskQuesResponse );

			Iterator<RiskWeightedResponse> weightedResponses = RiskWeightedResponse.findAll( "riskQuestionId", riskQuestion.getOID().toString() );
			
			List wr = new ArrayList();
			while(weightedResponses.hasNext())
			{
				RiskWeightedResponse riskWeightedResponse = weightedResponses.next();
				RiskWeightedResponseEvent riskWeightedAnsResponse = new RiskWeightedResponseEvent();
				riskWeightedAnsResponse.setWeightedResponseID( 
						Integer.parseInt( riskWeightedResponse.getOID().toString() ) );
				riskWeightedAnsResponse.setAnswerText( riskWeightedResponse.getResponse() );
				riskWeightedAnsResponse.setRiskQuestionsId( String.valueOf(riskWeightedResponse.getRiskQuestionId()) );
				riskWeightedAnsResponse.setWeight( riskWeightedResponse.getWeight() );
				riskWeightedAnsResponse.setSortOrder( riskWeightedResponse.getSortOrder() );
				riskWeightedAnsResponse.setSubordinateQuestionId( String.valueOf(riskWeightedResponse.getSubordinateQuestionId()) );
				riskWeightedAnsResponse.setAction( riskWeightedResponse.getAction() );
				wr.add(riskWeightedAnsResponse);
			}
		
			Collections.sort(wr);
			SortedMap map = new TreeMap();
		
			for(java.util.Iterator itr = wr.iterator(); itr.hasNext();){
				RiskWeightedResponseEvent riskWeightedResponseEvent = (RiskWeightedResponseEvent)itr.next();
				map.put( riskWeightedResponseEvent.getAnswerText(), riskWeightedResponseEvent );
			}
			postEvent( map, dispatch );
		}
	}*/
	/**
	 * @param assessmentType
	 * @return
	 */
	public static RiskFormula getActiveFormulaByAssessmentType(String assessmentType){
		GetActiveFormulaByAssessmentType reqEvt = new GetActiveFormulaByAssessmentType();
		reqEvt.setAssessmentType(assessmentType);
		RiskFormula activeFormula = null;
		
		List <RiskFormula> aList = RiskFormula.findAll(reqEvt);
		
		if (aList.size() > 0){
			activeFormula = aList.get(0);
		}
		
		return activeFormula;
	}
	
	public static void retrieveRiskQuestionsByFormulaId(String formulaId)
	{
		Iterator iter = RiskAnalysisFormulaCategoryQuestion.findAll( RiskAnalysisConstants.RISK_FORMULA_ID, formulaId );
		List <RiskAnalysisFormulaCategoryQuestion> questions = CollectionUtil.iteratorToList(iter);
		RiskAnalysisFormulaCategoryQuestion riskQuestion = null;
		
		for (int i = 0; i < questions.size(); i++) 
		{
			riskQuestion = questions.get(i);
			RiskQuestionResponseEvent riskQuesResponse = new RiskQuestionResponseEvent();
			riskQuesResponse.setAssessmentType( riskQuestion.getAssessmentType() );

			try
			{
				riskQuesResponse.setQuestionID( Integer.parseInt( riskQuestion.getOID().toString() ) );
			}
			catch(NumberFormatException nfe)
			{
				riskQuesResponse.setQuestionID( 0 );
			}
			
			riskQuesResponse.setQuestionName(riskQuestion.getQuestionName());
			riskQuesResponse.setQuestionText( riskQuestion.getQuestionText() );
			riskQuesResponse.setUiControlType( riskQuestion.getUiControlType() );
			riskQuesResponse.setControlCode( riskQuestion.getControlCode() );
			int thisQNum = new Integer( riskQuestion.getQuestionNbr() );
			riskQuesResponse.setQuestionNbr( thisQNum );
			riskQuesResponse.setInitialAction(riskQuestion.getInitialAction());
			riskQuesResponse.setHelpFileId(riskQuestion.getHelpFileId());
			riskQuesResponse.setRequired(riskQuestion.isRequired());
			riskQuesResponse.setNumeric(riskQuestion.isNumeric());
			riskQuesResponse.setAllowsFutureDates(riskQuestion.isAllowsFutureDates());
			riskQuesResponse.setDefaultToSystemDate(riskQuestion.isDefaultToSystemDate());
			riskQuesResponse.setRefreshable(riskQuestion.isRefreshable());
			riskQuesResponse.setRiskFormulaId(riskQuestion.getRiskFormulaId());
			riskQuesResponse.setIsAllowPrint( isAllowPrint(riskQuestion.getOID().toString()) );
			
			MessageUtil.postReply( riskQuesResponse );

			Iterator <RiskWeightedResponse> weightedResponses = RiskWeightedResponse.findAll( "riskQuestionId", riskQuestion.getOID().toString() );
			List <RiskWeightedResponse> rList = CollectionUtil.iteratorToList(weightedResponses);
			
			List wr = new ArrayList();
			for (int j = 0; j < rList.size(); j++) 
			{
				RiskWeightedResponse riskWeightedResponse = rList.get(j);
				RiskWeightedResponseEvent riskWeightedAnsResponse = new RiskWeightedResponseEvent();
				riskWeightedAnsResponse.setWeightedResponseID( 
						Integer.parseInt( riskWeightedResponse.getOID().toString() ) );
				riskWeightedAnsResponse.setAnswerText( riskWeightedResponse.getResponse() );
				riskWeightedAnsResponse.setRiskQuestionsId( String.valueOf(riskWeightedResponse.getRiskQuestionId()) );
				riskWeightedAnsResponse.setWeight( riskWeightedResponse.getWeight() );
				riskWeightedAnsResponse.setSortOrder( riskWeightedResponse.getSortOrder() );
				riskWeightedAnsResponse.setSubordinateQuestionId( String.valueOf(riskWeightedResponse.getSubordinateQuestionId()) );
				riskWeightedAnsResponse.setAction( riskWeightedResponse.getAction() );
				wr.add(riskWeightedAnsResponse);
			}
		
			Collections.sort(wr);
			
			SortedMap map = new TreeMap(new CustomComparator()); // 108895
		
			for(Iterator itr = wr.iterator(); itr.hasNext();){
				RiskWeightedResponseEvent riskWeightedResponseEvent = (RiskWeightedResponseEvent)itr.next();
				map.put( riskWeightedResponseEvent.getAnswerText(), riskWeightedResponseEvent );
			}
			postEvent( map );
		}
	}
	
	
	public static String isAllowPrint(String questionId){
	    String allowPrint = "false";
	    RiskQuestions question = RiskQuestions.find(questionId);
	    
	    if (question != null ) {
		allowPrint = new Boolean(question.isAllowPrint()). toString();
	    }
	    
	    return allowPrint;
	}
	/*
	 * 
	 */
	public static Iterator retrieveTraits( String traitTypeName, String riskQuestionsId )
	{
		List list = new ArrayList();
		TraitType parentTrait = TraitType.findByAttributeName( "name", traitTypeName );

		GetJuvenileTraitTypesEvent juvTraitEvent = (GetJuvenileTraitTypesEvent)
				EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILETRAITTYPES );
		juvTraitEvent.setTraitType( parentTrait.getOID().toString() );

		Iterator<TraitType> i = TraitType.findByType( juvTraitEvent );
		while(i.hasNext())
		{
			TraitType traitType = i.next();
			RiskWeightedResponse wResp = new RiskWeightedResponse();
			wResp.setResponse( traitType.getName() );
			wResp.setRiskQuestionId(riskQuestionsId);

			try
			{
				wResp.setWeight( Integer.parseInt( traitType.getRiskPoints() ) );
			}
			catch(NumberFormatException nfe)
			{
				wResp.setWeight( 0 );
			}

			wResp.setOID( "0" );
			list.add( wResp );
		}

		return list.iterator();
	}

	/**
	 * @deprecated
	 * @param map
	 * @param dispatch
	 */
/*	private static void postEvent( SortedMap map, IDispatch dispatch )
	{
		Iterator<RiskWeightedResponseEvent> iter = map.values().iterator();
		while(iter.hasNext())
		{
			RiskWeightedResponseEvent riskWeightedAnsResponse = iter.next();
			dispatch.postEvent( riskWeightedAnsResponse );
		}
	}*/
	/**
	 * @param map
	 */
	private static void postEvent( SortedMap map )
	{
		List <RiskWeightedResponseEvent> responses = CollectionUtil.iteratorToList(map.values().iterator());

		MessageUtil.postReplies(responses);
	}

	/**
	 * @Deprecated
	 * @param riskReferral
	 * @param saveRefAssessEvent
	 * @throws ComputationValidationException
	 */
	public static void setCalculatedScoresReferral( 
			RiskAnalysisReferral riskReferral, 
			SaveReferralAssessmentEvent saveRefAssessEvent ) throws ComputationValidationException
	{
		RiskComputationContext riskComputationContext = new RiskComputationContext();
		NonCustodyReferralComputationResponseEvent nonCustodyRefRiskCompRespEvent = 
				(NonCustodyReferralComputationResponseEvent)riskComputationContext.computeRiskAnalysis( saveRefAssessEvent );

		riskReferral.setCustodyStatus( nonCustodyRefRiskCompRespEvent.getCustodyStatus() );
		riskReferral.setTotalAttitude( nonCustodyRefRiskCompRespEvent.getTotalAttitude() );
		riskReferral.setTotalCapitalFelony( nonCustodyRefRiskCompRespEvent.getTotalCapitalFelony() );
		riskReferral.setTotalClassAB( nonCustodyRefRiskCompRespEvent.getTotalClassABScore() );
		riskReferral.setTotalClassC( nonCustodyRefRiskCompRespEvent.getTotalClassC() );
		riskReferral.setTotalCurrentStatus( nonCustodyRefRiskCompRespEvent.getTotalCurrentStatus() );
		riskReferral.setTotalFelony1( nonCustodyRefRiskCompRespEvent.getTotalFelony1() );
		riskReferral.setTotalFelony2( nonCustodyRefRiskCompRespEvent.getTotalFelony2() );
		riskReferral.setTotalFelony3( nonCustodyRefRiskCompRespEvent.getTotalFelony3() );
		riskReferral.setTotalLevel( nonCustodyRefRiskCompRespEvent.getTotalLevel() );
		riskReferral.setTotalOffenseNature( nonCustodyRefRiskCompRespEvent.getTotalOffenseNature() );
		riskReferral.setTotalOffenses( nonCustodyRefRiskCompRespEvent.getTotalOffenses() );
		riskReferral.setTotalReferralsHistory( nonCustodyRefRiskCompRespEvent.getTotalReferralsHistory() );
		riskReferral.setTotalStateJailFelony( nonCustodyRefRiskCompRespEvent.getTotalStateJailFelony() );
		riskReferral.setTotalStatusCO( nonCustodyRefRiskCompRespEvent.getTotalStatusCO() );
		riskReferral.setTotalSupervision( nonCustodyRefRiskCompRespEvent.getTotalSupervision() );
		riskReferral.setSeriousnessIndex( nonCustodyRefRiskCompRespEvent.getSeriousnessIndex() );
		
		riskReferral.setAdditionalCharges( nonCustodyRefRiskCompRespEvent.getAdditionalCharges() );
		riskReferral.setOnProbation( nonCustodyRefRiskCompRespEvent.isOnProbation() );
		riskReferral.setVOPPendingCourt(nonCustodyRefRiskCompRespEvent.isVOPPendingCourt());
		riskReferral.setPendingCourt(nonCustodyRefRiskCompRespEvent.isPendingCourt());
		
		//riskReferral.getRiskAnalysis().setFinalScore( nonCustodyRefRiskCompRespEvent.getTotalScore() );
	}

	/**
	 * @Deprecated
	 * @param riskAnalysisRefr
	 * @param saveRefAssessEvent
	 * @param jdh
	 * @throws ComputationValidationException
	 */
	public static void setCalculatedScoresNewReferral( RiskAnalysisReferral riskReferral, SaveReferralAssessmentEvent saveRefAssessEvent ) throws ComputationValidationException
	{
		RiskComputationContext riskComputationContext = new RiskComputationContext();
		CustodyReferralRiskComputationReponseEvent custRefRiskCompRespEvent = 
				(CustodyReferralRiskComputationReponseEvent)riskComputationContext.computeRiskAnalysis( saveRefAssessEvent );

		riskReferral.setCustodyStatus( custRefRiskCompRespEvent.getCustodyStatus() );
		riskReferral.setTotalCapitalFelony( custRefRiskCompRespEvent.getTotalCapitalFelony() );
		riskReferral.setTotalClassAB( custRefRiskCompRespEvent.getTotalClassABScore() );
		riskReferral.setTotalClassC( custRefRiskCompRespEvent.getTotalClassC() );
		riskReferral.setTotalCurrentStatus( custRefRiskCompRespEvent.getTotalCurrentStatus() );
		riskReferral.setTotalFelony1( custRefRiskCompRespEvent.getTotalFelony1() );
		riskReferral.setTotalFelony2( custRefRiskCompRespEvent.getTotalFelony2() );
		riskReferral.setTotalFelony3( custRefRiskCompRespEvent.getTotalFelony3() );
		riskReferral.setTotalLevel( custRefRiskCompRespEvent.getTotalLevel() );
		riskReferral.setTotalReferralsHistory( custRefRiskCompRespEvent.getTotalReferralsHistory() );
		riskReferral.setTotalStateJailFelony( custRefRiskCompRespEvent.getTotalStateJailFelony() );
		riskReferral.setTotalStatusCO( custRefRiskCompRespEvent.getTotalStatusCO() );
		riskReferral.setTotalSupervision( custRefRiskCompRespEvent.getTotalSupervision() );
		riskReferral.setSeriousnessIndex( custRefRiskCompRespEvent.getSeriousnessIndex() );
		
		riskReferral.setAdditionalCharges( custRefRiskCompRespEvent.getAdditionalCharges() );
		riskReferral.setOnProbation( custRefRiskCompRespEvent.isOnProbation() );
		riskReferral.setVOPPendingCourt(custRefRiskCompRespEvent.isVOPPendingCourt());
		riskReferral.setPendingCourt(custRefRiskCompRespEvent.isPendingCourt());
		
		//riskReferral.getRiskAnalysis().setFinalScore( custRefRiskCompRespEvent.getTotalScore() );
	}
	
	/**
	 * @param riskAnalysis
	 */
	public static void bindDeleteRiskAnalysisRecommendations(RiskAnalysis riskAnalysis) {
		Collection recommendationsDelete = riskAnalysis.getRecommendations();
		Iterator<RiskAnalysisRecommendation> iteRecommendations = recommendationsDelete.iterator();
		while(iteRecommendations.hasNext()) {
			RiskAnalysisRecommendation riskAnalysisRecommendation = iteRecommendations.next();
			riskAnalysisRecommendation.delete();
			IHome homeRiskAnalysisRecommendation=new Home();
			homeRiskAnalysisRecommendation.bind(riskAnalysisRecommendation);
		}
		riskAnalysis.clearRecommendations();
	}

	/**
	 * @param riskAnalysis
	 */
	public static void bindDeleteRiskFinalScores(RiskAnalysis riskAnalysis) {
		Collection finalscoresDelete = riskAnalysis.getFinalScores();
		Iterator<RiskFinalScore> iteFinalscoresDelete = finalscoresDelete.iterator();
		while(iteFinalscoresDelete.hasNext()) {
			RiskFinalScore riskFinalScore = iteFinalscoresDelete.next();
			riskFinalScore.delete();
			IHome homeRiskFinalScore=new Home();
			homeRiskFinalScore.bind(riskFinalScore);
		}
		riskAnalysis.clearFinalScores();
	}

	/**
	 * @param riskAnalysis
	 * @param forcedRecommendations
	 */
	public static List bindCreateRiskAnalysisRecommendationsDynamicallyWithForcedRecommendationsByAssessmentType( RiskAnalysis riskAnalysis, List <String> forcedRecommendations)
	{
		RiskRecommendation rr = null;
		List recommendationList = new ArrayList();
		
		for (String assessRecomm : forcedRecommendations) {
			
			Iterator <RiskRecommendation> iter = RiskRecommendation.findAllByAssessmentType( RiskAnalysisConstants.RISK_ASSESSMENT_TYPE, assessRecomm );
//			Iterator <RiskRecommendation> iter = RiskRecommendation.findAll("riskFormulaId", Integer.toString(riskAnalysis.getRiskFormulaId()));
			
			while(iter.hasNext())
			{
				rr = iter.next();
				RiskAnalysisRecommendation riskAnalysisRecommendation = 
					   new RiskAnalysisRecommendation();
				riskAnalysisRecommendation.setRiskAnalysisId(Integer.parseInt(riskAnalysis.getOID()));
				riskAnalysisRecommendation.setRecommendationId(Integer.parseInt(rr.getOID()));
				riskAnalysisRecommendation.setRiskResultGroupId(rr.getRiskResultGroupId());
					
				//Explicitly bind to database so that a Risk Analysis OID is generated
			    IHome home=new Home();
			    home.bind(riskAnalysisRecommendation);
			    recommendationList.add(riskAnalysisRecommendation);
			}
		}
		return recommendationList;	
			
	}
	
	/**
	 * @param riskAnalysis
	 * @param forcedRecommendationLiterals
	 * @return
	 */
	public static List bindCreateRiskAnalysisRecommendationsDynamicallyWithForcedRecommendationsAssessmentTypeAndRecommendationLiteral( RiskAnalysis riskAnalysis, List <String> forcedRecommendationLiterals)
	{
		RiskRecommendation rr = null;
		//Iterator <RiskRecommendation> iter = RiskRecommendation.findAllByAssessmentType( RiskAnalysisConstants.RISK_ASSESSMENT_TYPE, riskAnalysis.getAssessmentType() );
		Iterator <RiskRecommendation> iter = RiskRecommendation.findAll("riskFormulaId", Integer.toString(riskAnalysis.getRiskFormulaId()));
		
		List <RiskRecommendation> assessmentTypeRecommendations = CollectionUtil.iteratorToList(iter);
		List <RiskAnalysisRecommendation> recommendationList = new ArrayList();
		
		for (String recommLiteral : forcedRecommendationLiterals) {
			
			for (int i = 0; i < assessmentTypeRecommendations.size(); i++) {
				rr = assessmentTypeRecommendations.get(i);
				
				if (rr.getRecommendation().equalsIgnoreCase(recommLiteral)){
				
					RiskAnalysisRecommendation riskAnalysisRecommendation = 
					   new RiskAnalysisRecommendation();
					riskAnalysisRecommendation.setRiskAnalysisId(Integer.parseInt(riskAnalysis.getOID()));
					riskAnalysisRecommendation.setRecommendationId(Integer.parseInt(rr.getOID()));
					riskAnalysisRecommendation.setRiskResultGroupId(rr.getRiskResultGroupId());
					
					//Explicitly bind to database so that a Risk Analysis OID is generated
					IHome home=new Home();
					home.bind(riskAnalysisRecommendation);
					recommendationList.add(riskAnalysisRecommendation);
				}
			}
		}
		return recommendationList;
	}


	/**
	 * @deprecated
	 * @param riskAnalysis
	 * @param assessmentType
	 * @param isCustody
	 */
	public static void bindCreateRiskAnalysisRecommendationsDynamically( RiskAnalysis riskAnalysis, String assessmentType, boolean isCustody)
	{
		Collection finalscores = riskAnalysis.getFinalScores();
		Iterator <RiskFinalScore> iteFinalscores = finalscores.iterator();
		while(iteFinalscores.hasNext()) 
		{
			RiskFinalScore riskFinalScore = iteFinalscores.next();
			
			// Retrieve all the recommendations for the Assessment Type
			Iterator <RiskRecommendation> iter = RiskRecommendation.findAllByAssessmentType( RiskAnalysisConstants.RISK_ASSESSMENT_TYPE, assessmentType );
			while(iter.hasNext())
			{
				RiskRecommendation riskRecommend = iter.next();
				
				if (riskFinalScore.getRiskResultGroupId() == riskRecommend.getRiskResultGroupId())
				{
					if( riskRecommend.isCustody() != isCustody )
					{
						continue;
					}
					if( riskFinalScore.getFinalScore() >= riskRecommend.getMinValue() && 
						riskFinalScore.getFinalScore() <= riskRecommend.getMaxValue() )
					{
						
						RiskAnalysisRecommendation riskAnalysisRecommendation = 
							new RiskAnalysisRecommendation();
						riskAnalysisRecommendation.setRiskAnalysisId(Integer.parseInt(riskAnalysis.getOID()));
						riskAnalysisRecommendation.setRecommendationId(Integer.parseInt(riskRecommend.getOID()));
					
						//Explicitly bind to database so that a Risk Analysis OID is generated
						IHome home=new Home();
						home.bind(riskAnalysisRecommendation);
				   
						break;
					}
				
				}
			}
		}
	}
	
	/**
	 * @param scoreList
	 * @param assessmentType
	 * @param isCustody
	 * @return
	 */
	public static List bindCreateRiskAnalysisRecommendationsDynamically( List <RiskFinalScore> scoreList, String riskFormulaId, boolean isCustody )
	{
		RiskFinalScore riskFinalScore = null;
		RiskRecommendation riskRecommend = null;
		List recommendations = new ArrayList();
		
		for (int i = 0; i < scoreList.size(); i++) {
			
			riskFinalScore = scoreList.get(i);
			
			//Iterator iter = RiskRecommendation.findAllByAssessmentType( RiskAnalysisConstants.RISK_ASSESSMENT_TYPE, assessmentType );
			Iterator <RiskRecommendation> iter = RiskRecommendation.findAll("riskFormulaId", riskFormulaId);

			List <RiskRecommendation> recommendationList = CollectionUtil.iteratorToList(iter);
			
			for (int j = 0; j < recommendationList.size(); j++) 
			{
				riskRecommend = recommendationList.get(j);
				
				if (riskFinalScore.getRiskResultGroupId() == riskRecommend.getRiskResultGroupId())
				{
					if( riskRecommend.isCustody() != isCustody )
					{
						continue;
					}
					if( riskFinalScore.getFinalScore() >= riskRecommend.getMinValue() && 
						riskFinalScore.getFinalScore() <= riskRecommend.getMaxValue() )
					{
						
						RiskAnalysisRecommendation riskAnalysisRecommendation = 
							new RiskAnalysisRecommendation();
						riskAnalysisRecommendation.setRiskAnalysisId(riskFinalScore.getRiskAnalysisId());
						riskAnalysisRecommendation.setRiskResultGroupId(riskFinalScore.getRiskResultGroupId());
						riskAnalysisRecommendation.setRecommendationId(Integer.parseInt(riskRecommend.getOID()));
					
						//Explicitly bind to database so that a Risk Analysis OID is generated
						IHome home=new Home();
						home.bind(riskAnalysisRecommendation);
						recommendations.add(riskAnalysisRecommendation);
				   
						break;
					}
				
				}
			}
		}
		return recommendations;
	}

	/**
	 * @deprecated
	 * @param riskAnalysis
	 * @param dispatch
	 */
	public static void retrieveRefrAssessmentDetails( RiskAnalysis riskAnalysis, IDispatch dispatch )
	{
		ReferralAssessmentEvent refAssessEvent = new ReferralAssessmentEvent();
		String OID = riskAnalysis.getOID();
		String assessType = riskAnalysis.getAssessmentType().trim() ;
		RiskAnalysisReferral riskReferral = RiskAnalysisReferral.findByRiskAnalysisId( OID );
		
		
		refAssessEvent.setCasefileId(String.valueOf(riskAnalysis.getCasefileID()));
		if( assessType.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL ) )
		{
			refAssessEvent.setNewReferral( riskReferral.isNewReferral() );
			refAssessEvent.setRiskMandatoryDetentionCd( riskReferral.getRiskMandatoryDetentionCd() );
			refAssessEvent.setMoreThanOneFailure(riskReferral.isMoreThanOneFailure());
		}
		
		refAssessEvent.setPendingCourt( riskReferral.isPendingCourt() ? "Yes" : "No");
		refAssessEvent.setCurrentlyOnProbation( riskReferral.isOnProbation() ? "Yes" : "No" );
		refAssessEvent.setPendingCourtVOP( riskReferral.isVOPPendingCourt() ? "Yes" : "No" );

		refAssessEvent.setNumberOfCharges(String.valueOf(riskReferral.getAdditionalCharges())); 

		refAssessEvent.setAssessmentType( assessType );
		refAssessEvent.setEnteredDate( riskAnalysis.getEnteredDate() );
//		refAssessEvent.setFinalScore( riskAnalysis.getFinalScore() );
//		refAssessEvent.setRecommendation( riskAnalysis.getRecommendation() );
		List recommendations = retrieveRiskRecommendationResponseEvents(riskAnalysis);
		refAssessEvent.setRecommendations(recommendations);
		
		refAssessEvent.setRecommendationOverridden( riskAnalysis.isRecommendationOveridden() );
		refAssessEvent.setOverRiddenReasonCd( riskAnalysis.getOveriddenReasonCd() );
		refAssessEvent.setOverRiddenReasonOther( riskAnalysis.getOveriddenReasonOther() );
		refAssessEvent.setModReason( riskAnalysis.getModReason() );
		
		JuvenileDelinquencyHistory history = JuvenileDelinquencyHistory.findbyRiskAnalysisId( OID );
		if( history != null )
		{
			refAssessEvent.setAgeFirstReferred(String.valueOf(history.getAgeFirstReferral()));
			refAssessEvent.setSeriousnessIndex(String.valueOf(history.getSeriousnessIndex()));
			refAssessEvent.setTotalCapitalFelony(String.valueOf(history.getCapFelonyTotal()));
			refAssessEvent.setTotalClassAB(String.valueOf(history.getMisdABTotal()));
			refAssessEvent.setTotalClassC(String.valueOf(history.getMisdCTotal()));
			refAssessEvent.setTotalFelony1(String.valueOf(history.getFelony1Total()));
			refAssessEvent.setTotalFelony2(String.valueOf(history.getFelony2Total()));
			refAssessEvent.setTotalFelony3(String.valueOf(history.getFelony3Total()));
			refAssessEvent.setTotalLevel(String.valueOf(history.getLevelTotal()));
			refAssessEvent.setTotalReferralsHistory(String.valueOf(history.getReferralHistoryTotal()));
			refAssessEvent.setTotalOffenses(String.valueOf(history.getTotalOffenses()));
			refAssessEvent.setTotalStatusCO(String.valueOf(history.getScoOffensesTotal()));
			refAssessEvent.setTotalStateJailFelony(String.valueOf(history.getJailFelonyTotal()));
			
		}
		dispatch.postEvent( refAssessEvent );
	}
	
	/**
	 * @param riskAnalysis
	 */
	public static void retrieveRefrAssessmentDetails( RiskAnalysis riskAnalysis )
	{

		String OID = riskAnalysis.getOID();
		String assessType = riskAnalysis.getAssessmentType().trim() ;
		RiskAnalysisReferral riskReferral = RiskAnalysisReferral.findByRiskAnalysisId( OID );
		ReferralAssessmentEvent refAssessEvent = new ReferralAssessmentEvent();		;
		if (riskReferral != null){
			refAssessEvent.setCasefileId(String.valueOf(riskAnalysis.getCasefileID()));
			if( assessType.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL ) )
			{
				refAssessEvent.setNewReferral( riskReferral.isNewReferral() );
				refAssessEvent.setRiskMandatoryDetentionCd( riskReferral.getRiskMandatoryDetentionCd() );
				refAssessEvent.setMoreThanOneFailure(riskReferral.isMoreThanOneFailure());
			}
			
			refAssessEvent.setPendingCourt( riskReferral.isPendingCourt() ? "Yes" : "No");
			refAssessEvent.setCurrentlyOnProbation( riskReferral.isOnProbation() ? "Yes" : "No" );
			refAssessEvent.setPendingCourtVOP( riskReferral.isVOPPendingCourt() ? "Yes" : "No" );
	
			refAssessEvent.setNumberOfCharges(String.valueOf(riskReferral.getAdditionalCharges())); 
	
	//		refAssessEvent.setAssessmentType( assessType );
	//		refAssessEvent.setEnteredDate( riskAnalysis.getEnteredDate() );
	//		List recommendations = retrieveRiskRecommendationResponseEvents(riskAnalysis);
	//		refAssessEvent.setRecommendations(recommendations);
	//		
	//		refAssessEvent.setRecommendationOverridden( riskAnalysis.isRecommendationOveridden() );
	//		refAssessEvent.setOverRiddenReasonCd( riskAnalysis.getOveriddenReasonCd() );
	//		refAssessEvent.setOverRiddenReasonOther( riskAnalysis.getOveriddenReasonOther() );
	//		refAssessEvent.setModReason( riskAnalysis.getModReason() );
		} else {
			if( assessType.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL ) )
			{
				refAssessEvent.setNewReferral( true );
			}
		}
		JuvenileDelinquencyHistory history = JuvenileDelinquencyHistory.findbyRiskAnalysisId( OID );
		
		if( history != null )
		{
			refAssessEvent.setAgeFirstReferred(String.valueOf(history.getAgeFirstReferral()));
			refAssessEvent.setSeriousnessIndex(String.valueOf(history.getSeriousnessIndex()));
			refAssessEvent.setTotalCapitalFelony(String.valueOf(history.getCapFelonyTotal()));
			refAssessEvent.setTotalClassAB(String.valueOf(history.getMisdABTotal()));
			refAssessEvent.setTotalClassC(String.valueOf(history.getMisdCTotal()));
			refAssessEvent.setTotalFelony1(String.valueOf(history.getFelony1Total()));
			refAssessEvent.setTotalFelony2(String.valueOf(history.getFelony2Total()));
			refAssessEvent.setTotalFelony3(String.valueOf(history.getFelony3Total()));
			refAssessEvent.setTotalLevel(String.valueOf(history.getLevelTotal()));
			refAssessEvent.setTotalReferralsHistory(String.valueOf(history.getReferralHistoryTotal()));
			refAssessEvent.setTotalOffenses(String.valueOf(history.getTotalOffenses()));
			refAssessEvent.setTotalStatusCO(String.valueOf(history.getScoOffensesTotal()));
			refAssessEvent.setTotalStateJailFelony(String.valueOf(history.getJailFelonyTotal()));
			
		}
		MessageUtil.postReply( refAssessEvent );
	}	
	/**
	 * @deprecated
	 * @param riskAnalysis
	 * @param dispatch
	 */
	public static void retrieveIntrwAssessmentDetails( RiskAnalysis riskAnalysis, IDispatch dispatch )
	{
		InterviewAssessmentEvent intrwAssessEvent = new InterviewAssessmentEvent();

		RiskAnalysisInterview riskInterview  = RiskAnalysisInterview.findByRiskAnalysisId(riskAnalysis.getOID());
		
		intrwAssessEvent.setCasefileId(String.valueOf(riskAnalysis.getCasefileID()));
		intrwAssessEvent.setAssessmentType( riskAnalysis.getAssessmentType() );
		intrwAssessEvent.setEnteredDate( riskAnalysis.getEnteredDate() );
//		intrwAssessEvent.setFinalScore( riskAnalysis.getFinalScore() );
//		intrwAssessEvent.setRecommendation( riskAnalysis.getRecommendation() );

		intrwAssessEvent.setOnSetAge(String.valueOf(riskInterview.getOnsetAge()));
		intrwAssessEvent.setSexCd(riskInterview.getSexCd());
		intrwAssessEvent.setRecommendationOverridden( riskAnalysis.isRecommendationOveridden() );
		intrwAssessEvent.setOverRiddenReasonCd( riskAnalysis.getOveriddenReasonCd() );
		intrwAssessEvent.setOverRiddenReasonOther( riskAnalysis.getOveriddenReasonOther() );
		intrwAssessEvent.setModReason( riskAnalysis.getModReason() );
		
		List recommendations = retrieveRiskRecommendationResponseEvents(riskAnalysis);
		intrwAssessEvent.setRecommendations(recommendations);
		
		dispatch.postEvent( intrwAssessEvent );
	}

	/**
	 * @deprecated
	 * @param riskAnalysis
	 * @param dispatch
	 */
	public static void retrieveResidentialAssessmentDetails( RiskAnalysis riskAnalysis, IDispatch dispatch )
	{
		ResidentialAssessmentEvent residentialAssessEvent = new ResidentialAssessmentEvent();
		residentialAssessEvent.setCasefileId(String.valueOf(riskAnalysis.getCasefileID()));
		residentialAssessEvent.setAssessmentType( riskAnalysis.getAssessmentType() );
		residentialAssessEvent.setEnteredDate( riskAnalysis.getEnteredDate() );
//		residentialAssessEvent.setFinalScore( riskAnalysis.getFinalScore() );
//		residentialAssessEvent.setRecommendation( riskAnalysis.getRecommendation() );
		residentialAssessEvent.setRecommendationOverridden( riskAnalysis.isRecommendationOveridden() );
		residentialAssessEvent.setOverRiddenReasonCd( riskAnalysis.getOveriddenReasonCd() );
		residentialAssessEvent.setOverRiddenReasonOther( riskAnalysis.getOveriddenReasonOther() );
		residentialAssessEvent.setModReason( riskAnalysis.getModReason() );
		
		List recommendations = retrieveRiskRecommendationResponseEvents(riskAnalysis);
		residentialAssessEvent.setRecommendations(recommendations);
		
		dispatch.postEvent( residentialAssessEvent );
	}
	public static List retrieveRiskRecommendationResponseEvents(RiskAnalysis riskAnalysis){
		List riskRecommendationResponseEvents = new ArrayList();
		Collection recommendations = riskAnalysis.getRecommendations();
		Collection finalscores = riskAnalysis.getFinalScores();
		//Iterator <RiskFinalScore> iteFinalscores = finalscores.iterator();
		List <RiskFinalScore> finalScoreList = CollectionUtil.iteratorToList(finalscores.iterator());
		List <RiskAnalysisRecommendation> recommendationList = CollectionUtil.iteratorToList(recommendations.iterator());
		RiskFinalScore riskFinalScore = null;
		RiskAnalysisRecommendation riskAnalysisRecommendation = null;
		RiskRecommendation riskRecommend = null;
		RiskRecommendationResponseEvent riskRecommendScore = null;
		String displayDesc = null;
		
		for (int i = 0; i < finalScoreList.size(); i++)
		{
			riskFinalScore = finalScoreList.get(i);
			
			//Iterator <RiskAnalysisRecommendation> iterRecommendations = recommendations.iterator();
			for (int j = 0; j < recommendationList.size(); j++)
			{
				riskAnalysisRecommendation = recommendationList.get(j);
				riskRecommend = riskAnalysisRecommendation.getRecommendation();
				
				if (riskFinalScore.getRiskResultGroupId() == riskRecommend.getRiskResultGroupId()) 
				{
					riskRecommendScore = new RiskRecommendationResponseEvent();
					riskRecommendScore.setRiskAnalysisRecommendation(riskRecommend.getRecommendation());
					riskRecommendScore.setRiskAnalysisScore(riskFinalScore.getFinalScore());
					riskRecommendScore.setRiskAnalysisId(riskAnalysis.getOID());
					riskRecommendScore.setResultGroup(riskRecommend.getRiskResultGroup().getDescription());
					displayDesc = riskRecommend.getRiskResultGroup().getDisplayDescription();
					if (displayDesc != null){
						riskRecommendScore.setResultGroupDisplayDesc(displayDesc);
					} else {
						riskRecommendScore.setResultGroupDisplayDesc(PDConstants.BLANK);
					}
					riskRecommendationResponseEvents.add(riskRecommendScore);
				}
				
			}
		}
		if (riskRecommendationResponseEvents != null && riskRecommendationResponseEvents.size() > 1){
			List sortedList = new ArrayList(riskRecommendationResponseEvents);
			ArrayList sortFields = new ArrayList();
			sortFields.add(new ComparatorChain(new BeanComparator("resultGroupDisplayDesc")));
			ComparatorChain multiSort = new ComparatorChain(sortFields);
			Collections.sort(sortedList, multiSort);
			riskRecommendationResponseEvents = sortedList;
		} 
		return riskRecommendationResponseEvents;
	}
	/**
	 * @deprecated
	 * @param riskAnalysis
	 * @param dispatch
	 */
	public static void retrieveCommunityAssessmentDetails( RiskAnalysis riskAnalysis, IDispatch dispatch )
	{
		CommunityAssessmentEvent communityAssessEvent = new CommunityAssessmentEvent();
		communityAssessEvent.setCasefileId(String.valueOf(riskAnalysis.getCasefileID()));
		communityAssessEvent.setAssessmentType( riskAnalysis.getAssessmentType() );
		communityAssessEvent.setEnteredDate( riskAnalysis.getEnteredDate() );
//		communityAssessEvent.setFinalScore( riskAnalysis.getFinalScore() );
//		communityAssessEvent.setRecommendation( riskAnalysis.getRecommendation() );
		communityAssessEvent.setRecommendationOverridden( riskAnalysis.isRecommendationOveridden() );
		communityAssessEvent.setOverRiddenReasonCd( riskAnalysis.getOveriddenReasonCd() );
		communityAssessEvent.setOverRiddenReasonOther( riskAnalysis.getOveriddenReasonOther() );
		communityAssessEvent.setModReason( riskAnalysis.getModReason() );
		
		List riskRecommendationResponseEvents = retrieveRiskRecommendationResponseEvents(riskAnalysis);

		communityAssessEvent.setRecommendations(riskRecommendationResponseEvents);
		
		dispatch.postEvent( communityAssessEvent );
	}

	/**
	 * @param courtReferralAssessmentEvent
	 * @param riskAnalysis
	 */
	public static void retrieveCourtReferralCompletedInformation(CourtReferralAssessmentEvent courtReferralAssessmentEvent,
			RiskAnalysis riskAnalysis) {

		RiskAnalysisCourtReferral riskCourtReferral = null;
		Iterator<RiskAnalysisCourtReferral> riskCourtReferralIter = RiskAnalysisCourtReferral.findAllByAttributeName( RiskAnalysisConstants.RISK_ANALYSIS_ID, riskAnalysis.getOID() );
		while(riskCourtReferralIter.hasNext()) {
			riskCourtReferral = riskCourtReferralIter.next();
			break;
		}
		
		courtReferralAssessmentEvent.setCasefileId(String.valueOf(riskAnalysis.getCasefileID()));
		courtReferralAssessmentEvent.setAssessmentType( riskAnalysis.getAssessmentType() );
		courtReferralAssessmentEvent.setEnteredDate( riskAnalysis.getEnteredDate() );
		
		if (riskCourtReferral.getCollateralVisits() > -1) 
		{
			courtReferralAssessmentEvent.setCollateralVisits( String.valueOf(riskCourtReferral.getCollateralVisits()) ) ;
		}
		if (riskCourtReferral.getFace2FaceVisits() > -1) 
		{
			courtReferralAssessmentEvent.setFace2FaceVisits( String.valueOf(riskCourtReferral.getFace2FaceVisits()) ) ;
		}
		courtReferralAssessmentEvent.setCourtDispositionTJPC(riskCourtReferral.getCourtDispositionTJPC());
		//courtReferralAssessmentEvent.setJjsCourtDecision(riskCourtReferral.getJjsCourtDecision());
		courtReferralAssessmentEvent.setJjsCourtDisposition(riskCourtReferral.getJjsCourtDisposition());
		
		/** Start - Get Latest Court Decision ***************************************************************************************/
		
		//Get Case File information
		JuvenileCasefile juvCaseFile = JuvenileCasefile.find(String.valueOf(riskAnalysis.getCasefileID()));
		
		String reportingReferral = "";
		String jjsCourtDecision = "";
		
		// Retrieves all the responses associated with the RISK_ANALYSIS_ID
		Iterator<RiskAnswer> riskAnswers = RiskAnswer.findAll( 
				RiskAnalysisConstants.RISK_ANALYSIS_ID, riskAnalysis.getOID() );
		while(riskAnswers.hasNext())
		{
			RiskAnswer riskAnswer = riskAnswers.next();
			if (riskAnswer.getQuestionText().equalsIgnoreCase("Reporting Referral #:")) 
			{
				reportingReferral = riskAnswer.getText();
				break;
			}
		}
		
		JJSReferral referral = null;
		if (reportingReferral.length() > 0)
		{
			Iterator<JJSReferral> casefileReferralsIter1 = InterviewHelper.getReferralsForJuvenilesCasefile(juvCaseFile.getJuvenile(), juvCaseFile.getOID()).iterator();
		    while (casefileReferralsIter1.hasNext())
			{
				referral = casefileReferralsIter1.next();
				if (reportingReferral.equalsIgnoreCase(referral.getReferralNum()))
				{
					break; //match found, referral is set
				}
			}
		}
		
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
					jjsCourtDecision = juvenileReferralDispositionCode.getDescription();
				}
			}
		}
		
		courtReferralAssessmentEvent.setJjsCourtDecision(jjsCourtDecision);
		
		/** End - Get Latest Court Decision ***************************************************************************************/
		
	}
	public static void retrieveCourtReferralCompletedInformation(RiskAnalysis riskAnalysis) {

		CourtReferralAssessmentEvent courtReferralAssessmentEvent = new CourtReferralAssessmentEvent();
		courtReferralAssessmentEvent.setCompleted(riskAnalysis.isCompleted());

		RiskAnalysisCourtReferral riskCourtReferral = null;
		Iterator<RiskAnalysisCourtReferral> riskCourtReferralIter = RiskAnalysisCourtReferral.findAllByAttributeName( RiskAnalysisConstants.RISK_ANALYSIS_ID, riskAnalysis.getOID() );
		while(riskCourtReferralIter.hasNext()) {
			riskCourtReferral = riskCourtReferralIter.next();
			break;
		}
		
		if (riskCourtReferral != null){
			if (riskCourtReferral.getCollateralVisits() > -1) 
			{
				courtReferralAssessmentEvent.setCollateralVisits( String.valueOf(riskCourtReferral.getCollateralVisits()) ) ;
			}
			if (riskCourtReferral.getFace2FaceVisits() > -1) 
			{
				courtReferralAssessmentEvent.setFace2FaceVisits( String.valueOf(riskCourtReferral.getFace2FaceVisits()) ) ;
			}
			courtReferralAssessmentEvent.setCourtDispositionTJPC(riskCourtReferral.getCourtDispositionTJPC());
			courtReferralAssessmentEvent.setJjsCourtDisposition(riskCourtReferral.getJjsCourtDisposition());
			courtReferralAssessmentEvent.setJjsCourtDecision(riskCourtReferral.getJjsCourtDecision());
		} else {
			courtReferralAssessmentEvent.setCollateralVisits("0");
			courtReferralAssessmentEvent.setFace2FaceVisits("0");
			courtReferralAssessmentEvent.setCourtDispositionTJPC(null);
			courtReferralAssessmentEvent.setJjsCourtDisposition(null);
			courtReferralAssessmentEvent.setJjsCourtDecision(null);
		}
		MessageUtil.postReply(courtReferralAssessmentEvent);
	}	
	/*
	 * 
	 */
	/**
	 * 
	 * @deprecated
	 * @param riskAnalysis
	 * @param dispatch
	 */
	public static void retrieveCourtReferralAssessmentDetails( RiskAnalysis riskAnalysis, IDispatch dispatch )
	{
		CourtReferralAssessmentEvent courtReferralAssessmentEvent = new CourtReferralAssessmentEvent();
		courtReferralAssessmentEvent.setCasefileId(String.valueOf(riskAnalysis.getCasefileID()));
		courtReferralAssessmentEvent.setAssessmentType( riskAnalysis.getAssessmentType() );
		courtReferralAssessmentEvent.setEnteredDate( riskAnalysis.getEnteredDate() );
		courtReferralAssessmentEvent.setRecommendationOverridden( riskAnalysis.isRecommendationOveridden() );
		courtReferralAssessmentEvent.setOverRiddenReasonCd( riskAnalysis.getOveriddenReasonCd() );
		courtReferralAssessmentEvent.setOverRiddenReasonOther( riskAnalysis.getOveriddenReasonOther() );
		courtReferralAssessmentEvent.setModReason( riskAnalysis.getModReason() );
		
/*		List riskRecommendationResponseEvents = new ArrayList();
		Collection recommendations = riskAnalysis.getRecommendations();
		Collection finalscores = riskAnalysis.getFinalScores();
		Iterator<RiskFinalScore> iteFinalscores = finalscores.iterator();
		while(iteFinalscores.hasNext()) 
		{
			RiskFinalScore riskFinalScore = iteFinalscores.next();
			
			Iterator<RiskAnalysisRecommendation> iterRecommendations = recommendations.iterator();
			while(iterRecommendations.hasNext())
			{
				RiskAnalysisRecommendation riskAnalysisRecommendation = iterRecommendations.next();
				RiskRecommendation riskRecommend = riskAnalysisRecommendation.getRecommendation();
				
				if (riskFinalScore.getRiskResultGroup().getDescription()
						.equalsIgnoreCase(riskRecommend.getRiskResultGroup().getDescription())) 
				{
					RiskRecommendationResponseEvent riskRecommendScore = new RiskRecommendationResponseEvent();
					riskRecommendScore.setRiskAnalysisRecommendation(riskRecommend.getRecommendation());
					riskRecommendScore.setRiskAnalysisScore(riskFinalScore.getFinalScore());
					riskRecommendScore.setRiskAnalysisId(riskAnalysis.getOID().toString());
					riskRecommendScore.setResultGroup(riskRecommend.getRiskResultGroup().getDescription());
					riskRecommendationResponseEvents.add(riskRecommendScore);
				}
				
			}
			
		}
*/		
		List riskRecommendationResponseEvents = retrieveRiskRecommendationResponseEvents(riskAnalysis);
		
		courtReferralAssessmentEvent.setRecommendations(riskRecommendationResponseEvents);
		
		if (riskAnalysis.isCompleted()) 
		{
			courtReferralAssessmentEvent.setCompleted(true);
			retrieveCourtReferralCompletedInformation(courtReferralAssessmentEvent, riskAnalysis);
		} 
		else 
		{
			courtReferralAssessmentEvent.setCompleted(false);
		}
		
		dispatch.postEvent( courtReferralAssessmentEvent );
	}

	public static void updateCourtReferralRiskAnalysisOverrideStatus( RequestEvent event )
	{
		SaveCourtReferralAssessmentEvent saveCourtReferralAssessmentEvent = (SaveCourtReferralAssessmentEvent)event;
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveCourtReferralAssessmentEvent.getAssessmentID() );

		boolean recommendatationOverridden = saveCourtReferralAssessmentEvent.isRecommendationOveridden();
		riskAnalysis.setRecommendationOveridden( recommendatationOverridden );

		if( recommendatationOverridden )
		{
			String overiddenReasonCd = saveCourtReferralAssessmentEvent.getOveriddenReasonCd();
			riskAnalysis.setOveriddenReasonCd( overiddenReasonCd );
			String overiddenReasonFirstTwo = overiddenReasonCd.substring(0, 2);
			
			if( (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO) ) ||
					(StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO)) ) 
			{
				riskAnalysis.setOveriddenReasonOther( saveCourtReferralAssessmentEvent.getOveriddenReasonOther() );
			} 
			else 
			{
				riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
			}

		}
		else
		{
			riskAnalysis.setOveriddenReasonCd( UIConstants.EMPTY_STRING );
			riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
		}

		IHome home = new Home();
		home.bind( riskAnalysis );
	}
	public static void retrieveRiskAssessmentDetails(RiskAnalysis riskAnalysis){
		RiskAssessmentResponseEvent re = new RiskAssessmentResponseEvent();
		re.setCasefileId(String.valueOf(riskAnalysis.getCasefileID()));
		re.setAssessmentType( riskAnalysis.getAssessmentType() );
		Code aCode = Code.find(RiskAnalysisConstants.JUV_RISK_ASSESSMENT_TYPE, riskAnalysis.getAssessmentType());
		if (aCode != null){
			re.setAssessmentTypeDesc(aCode.getDescription());
		} else {
			re.setAssessmentTypeDesc(UIConstants.EMPTY_STRING);
		}
		re.setEnteredDate( riskAnalysis.getEnteredDate() );
		re.setRecommendationOverridden( riskAnalysis.isRecommendationOveridden() );
		re.setOverRiddenReasonCd( riskAnalysis.getOveriddenReasonCd() );
		re.setOverRiddenReasonOther( riskAnalysis.getOveriddenReasonOther() );
		re.setModReason( riskAnalysis.getModReason() );
		re.setRiskFormulaId(riskAnalysis.getRiskFormulaId());
		re.setRiskAnalysisId(riskAnalysis.getOID());
		
		List recommendations = retrieveRiskRecommendationResponseEvents(riskAnalysis);
		re.setRecommendations(recommendations);
		MessageUtil.postReply(re);
		MessageUtil.postReplies(recommendations);
	
	}
	public static void retrieveProgressAssessmentDetails( RiskAnalysis riskAnalysis, IDispatch dispatch )
	{
		ProgressAssessmentEvent progressAssessEvent = new ProgressAssessmentEvent();
		progressAssessEvent.setCasefileId(String.valueOf(riskAnalysis.getCasefileID()));
		progressAssessEvent.setAssessmentType( riskAnalysis.getAssessmentType() );
		progressAssessEvent.setEnteredDate( riskAnalysis.getEnteredDate() );
//		progressAssessEvent.setFinalScore( riskAnalysis.getFinalScore() );
//		progressAssessEvent.setRecommendation( riskAnalysis.getRecommendation() );
		progressAssessEvent.setRecommendationOverridden( riskAnalysis.isRecommendationOveridden() );
		progressAssessEvent.setOverRiddenReasonCd( riskAnalysis.getOveriddenReasonCd() );
		progressAssessEvent.setOverRiddenReasonOther( riskAnalysis.getOveriddenReasonOther() );
		progressAssessEvent.setModReason( riskAnalysis.getModReason() );
		
		List recommendations = retrieveRiskRecommendationResponseEvents(riskAnalysis);
		progressAssessEvent.setRecommendations(recommendations);
		
		dispatch.postEvent( progressAssessEvent );
	}

	public static void retrieveTestingAssessmentDetails( RiskAnalysis riskAnalysis, IDispatch dispatch )
	{
		TestingAssessmentEvent testingAssessEvent = new TestingAssessmentEvent();
		testingAssessEvent.setCasefileId(String.valueOf(riskAnalysis.getCasefileID()));
		testingAssessEvent.setAssessmentType( riskAnalysis.getAssessmentType() );
		testingAssessEvent.setEnteredDate( riskAnalysis.getEnteredDate() );
//		testingAssessEvent.setFinalScore( riskAnalysis.getFinalScore() );
//		testingAssessEvent.setRecommendation( riskAnalysis.getRecommendation() );
		testingAssessEvent.setRecommendationOverridden( riskAnalysis.isRecommendationOveridden() );
		testingAssessEvent.setOverRiddenReasonCd( riskAnalysis.getOveriddenReasonCd() );
		testingAssessEvent.setOverRiddenReasonOther( riskAnalysis.getOveriddenReasonOther() );
		testingAssessEvent.setModReason( riskAnalysis.getModReason() );
		
		List recommendations = retrieveRiskRecommendationResponseEvents(riskAnalysis);
		testingAssessEvent.setRecommendations(recommendations);
		
		dispatch.postEvent( testingAssessEvent );
	}

	public static void updateResidentialRiskAnalysisOverrideStatus( RequestEvent event )
	{
		SaveResidentialAssessmentEvent saveResidentialAssessmentEvent = (SaveResidentialAssessmentEvent)event;
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveResidentialAssessmentEvent.getAssessmentID() );

		boolean recommendatationOverridden = saveResidentialAssessmentEvent.isRecommendationOveridden();
		riskAnalysis.setRecommendationOveridden( recommendatationOverridden );

		if( recommendatationOverridden )
		{
			String overiddenReasonCd = saveResidentialAssessmentEvent.getOveriddenReasonCd();
			riskAnalysis.setOveriddenReasonCd( overiddenReasonCd );
			String overiddenReasonFirstTwo = overiddenReasonCd.substring(0, 2);
			
			if( (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO) ) ||
					(StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO)) ) 
			{
				riskAnalysis.setOveriddenReasonOther( saveResidentialAssessmentEvent.getOveriddenReasonOther() );
			} 
			else 
			{
				riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
			}

		}
		else
		{
			riskAnalysis.setOveriddenReasonCd( UIConstants.EMPTY_STRING );
			riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
		}

		IHome home = new Home();
		home.bind( riskAnalysis );
	}
	
	public static void updateTestingRiskAnalysisOverrideStatus( RequestEvent event )
	{
		SaveTestingAssessmentEvent saveTestAssessEvent = (SaveTestingAssessmentEvent)event;
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveTestAssessEvent.getAssessmentID() );

		boolean recommendatationOverridden = saveTestAssessEvent.isRecommendationOveridden();
		riskAnalysis.setRecommendationOveridden( recommendatationOverridden );

		if( recommendatationOverridden )
		{
			String overiddenReasonCd = saveTestAssessEvent.getOveriddenReasonCd();
			riskAnalysis.setOveriddenReasonCd( overiddenReasonCd );
			String overiddenReasonFirstTwo = overiddenReasonCd.substring(0, 2);
			
			if( (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO) ) ||
					(StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO)) ) 
			{
				riskAnalysis.setOveriddenReasonOther( saveTestAssessEvent.getOveriddenReasonOther() );
			} 
			else 
			{
				riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
			}

		}
		else
		{
			riskAnalysis.setOveriddenReasonCd( UIConstants.EMPTY_STRING );
			riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
		}

		IHome home = new Home();
		home.bind( riskAnalysis );
	}
	
	public static void updateCommunityRiskAnalysisOverrideStatus( RequestEvent event )
	{
		SaveCommunityAssessmentEvent saveCommunityAssessmentEvent = (SaveCommunityAssessmentEvent)event;
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveCommunityAssessmentEvent.getAssessmentID() );

		boolean recommendatationOverridden = saveCommunityAssessmentEvent.isRecommendationOveridden();
		riskAnalysis.setRecommendationOveridden( recommendatationOverridden );

		if( recommendatationOverridden )
		{
			String overiddenReasonCd = saveCommunityAssessmentEvent.getOveriddenReasonCd();
			riskAnalysis.setOveriddenReasonCd( overiddenReasonCd );
			String overiddenReasonFirstTwo = overiddenReasonCd.substring(0, 2);
			
			if( (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO) ) ||
					(StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO)) ) 
			{
				riskAnalysis.setOveriddenReasonOther( saveCommunityAssessmentEvent.getOveriddenReasonOther() );
			} 
			else 
			{
				riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
			}

		}
		else
		{
			riskAnalysis.setOveriddenReasonCd( UIConstants.EMPTY_STRING );
			riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
		}

		IHome home = new Home();
		home.bind( riskAnalysis );
	}
	
	/**
	 * @deprecated
	 * @param event
	 */
 	public static void updateProgressRiskAnalysisOverrideStatus( RequestEvent event )
	{
		SaveProgressAssessmentEvent saveProgressAssessmentEvent = (SaveProgressAssessmentEvent)event;
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveProgressAssessmentEvent.getAssessmentID() );

		boolean recommendatationOverridden = saveProgressAssessmentEvent.isRecommendationOveridden();
		riskAnalysis.setRecommendationOveridden( recommendatationOverridden );

		if( recommendatationOverridden )
		{
			String overiddenReasonCd = saveProgressAssessmentEvent.getOveriddenReasonCd();
			riskAnalysis.setOveriddenReasonCd( overiddenReasonCd );
			String overiddenReasonFirstTwo = overiddenReasonCd.substring(0, 2);
			
			if( (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO) ) ||
					(StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO)) ) 
			{
				riskAnalysis.setOveriddenReasonOther( saveProgressAssessmentEvent.getOveriddenReasonOther() );
			} 
			else 
			{
				riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
			}

		}
		else
		{
			riskAnalysis.setOveriddenReasonCd( UIConstants.EMPTY_STRING );
			riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
		}

		IHome home = new Home();
		home.bind( riskAnalysis );
	}
	public static void updateRiskAnalysisOverrideStatus( RequestEvent event )
	{
		SaveRiskAssessmentEvent saveProgressAssessmentEvent = (SaveRiskAssessmentEvent)event;
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveProgressAssessmentEvent.getAssessmentID() );

		boolean recommendatationOverridden = saveProgressAssessmentEvent.isRecommendationOveridden();
		riskAnalysis.setRecommendationOveridden( recommendatationOverridden );

		if( recommendatationOverridden )
		{
			String overiddenReasonCd = saveProgressAssessmentEvent.getOveriddenReasonCd();
			riskAnalysis.setOveriddenReasonCd( overiddenReasonCd );
			String overiddenReasonFirstTwo = overiddenReasonCd.substring(0, 2);
			
			if( (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO) ) ||
					(StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO)) ) 
			{
				riskAnalysis.setOveriddenReasonOther( saveProgressAssessmentEvent.getOveriddenReasonOther() );
			} 
			else 
			{
				riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
			}

		}
		else
		{
			riskAnalysis.setOveriddenReasonCd( UIConstants.EMPTY_STRING );
			riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
		}

		IHome home = new Home();
		home.bind( riskAnalysis );
	}
	
	public static void updateInterviewRiskAnalysisOverrideStatus( RequestEvent event )
	{
		SaveInterviewAssessmentEvent saveInterviewAssessmentEvent = (SaveInterviewAssessmentEvent)event;
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveInterviewAssessmentEvent.getAssessmentID() );

		boolean recommendatationOverridden = saveInterviewAssessmentEvent.isRecommendationOveridden();
		riskAnalysis.setRecommendationOveridden( recommendatationOverridden );

		if( recommendatationOverridden )
		{
			String overiddenReasonCd = saveInterviewAssessmentEvent.getOveriddenReasonCd();
			riskAnalysis.setOveriddenReasonCd( overiddenReasonCd );
			String overiddenReasonFirstTwo = overiddenReasonCd.substring(0, 2);
			
			if( (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO) ) ||
					(StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO)) ) 
			{
				riskAnalysis.setOveriddenReasonOther( saveInterviewAssessmentEvent.getOveriddenReasonOther() );
			} 
			else 
			{
				riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
			}

		}
		else
		{
			riskAnalysis.setOveriddenReasonCd( UIConstants.EMPTY_STRING );
			riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
		}

		IHome home = new Home();
		home.bind( riskAnalysis );
	}
	

	public static void updateReferralRiskAnalysisOverrideStatus( RequestEvent event )
	{
		SaveReferralAssessmentEvent saveReferralAssessmentEvent = (SaveReferralAssessmentEvent)event;
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveReferralAssessmentEvent.getAssessmentID() );

		boolean recommendatationOverridden = saveReferralAssessmentEvent.isRecommendationOveridden();
		riskAnalysis.setRecommendationOveridden( recommendatationOverridden );

		if( recommendatationOverridden )
		{
			String overiddenReasonCd = saveReferralAssessmentEvent.getOveriddenReasonCd();
			riskAnalysis.setOveriddenReasonCd( overiddenReasonCd );
			
			String overiddenReasonFirstTwo = overiddenReasonCd.substring(0, 2);
			
			if( (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO) ) ||
					(StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO)) ) 
			{
				riskAnalysis.setOveriddenReasonOther( saveReferralAssessmentEvent.getOveriddenReasonOther() );
			} 
			else 
			{
				riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
			}
			
		}
		else
		{
			riskAnalysis.setOveriddenReasonCd( UIConstants.EMPTY_STRING );
			riskAnalysis.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
		}

		IHome home = new Home();
		home.bind( riskAnalysis );
	}
	private static final String COURT_REFERRAL = "COURTREFERRAL";

	public static void retrieveRiskAnalysisDetails( GetRiskAssessmentDetailsEvent reqEvent )
	{
		RiskAnalysis riskAnalysis = RiskAnalysis.find( reqEvent.getAssessmentID() );

		retrieveRiskAssessmentDetails(riskAnalysis);
		
		//Dispatch the Question and Answers for a RiskAnalysis in for views only mode
		retrieveRiskAssessmentQuestionAnswers(reqEvent);
		
		if (riskAnalysis.getAssessmentType().startsWith(COURT_REFERRAL)){
			PDRiskAnalysisHelper.sendSuggestedCasePlanDomains(riskAnalysis.getOID());
			retrieveCourtReferralCompletedInformation(riskAnalysis);
		} else if (riskAnalysis.getAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL)
				|| riskAnalysis.getAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL)){
			PDRiskAnalysisHelper.retrieveRefrAssessmentDetails(riskAnalysis);
		}
	}

	/**
	 * Consolidates Questions/Answers based on Question Number for viewing purposes only
	 * @param riskAssessDetails
	 * @param dispatch
	 */
	private static void retrieveRiskAssessmentQuestionAnswers(
			GetRiskAssessmentDetailsEvent riskAssessDetails) 
	{
		//Temporary hashmap and vector to store Responses
		SortedMap map = new TreeMap();
		Vector vec = new Vector();
		
		RiskAnalysis riskAnalysis = RiskAnalysis.find(riskAssessDetails.getAssessmentID());
		String createUser = "";
		if (riskAnalysis != null) {
			createUser = riskAnalysis.getCreateUserID();
		}
		
		// Retrieves all the responses associated with the RISK_ANALYSIS_ID
		Iterator<RiskAnswer> riskAnswersIter = RiskAnswer.findAll( 
				RiskAnalysisConstants.RISK_ANALYSIS_ID, riskAssessDetails.getAssessmentID() );
		List <RiskAnswer> riskAnswers = CollectionUtil.iteratorToList(riskAnswersIter);
		
		Map uiControlTypesMap = new HashMap();
		if (riskAnswers.size() > 0){
			Code aCode = null;
			Collection coll = Code.findAll(RiskAnalysisConstants.JUV_RISK_UI_CONTROL_TYPE);
			List <Code> aList = CollectionUtil.iteratorToList(coll.iterator());
			for (int i = 0; i < aList.size(); i++) {
				aCode = aList.get(i);
				uiControlTypesMap.put(aCode.getCode(), aCode.getDescription());
			}
		}
		//String lastAnswerId = "";
		//while(riskAnswers.hasNext())
		for (int i = 0; i < riskAnswers.size(); i++) 
		{
			RiskAnswer riskAnswer = riskAnswers.get(i);
			
			//Create new Question/Answer
			RiskAnswerResponseEvent riskAnsResponse = new RiskAnswerResponseEvent();
			
			//Set RiskAnswerResponseEvent Attributes
			riskAnsResponse.setWeightID( String.valueOf(riskAnswer.getWeightedResponseID() ) );
			riskAnsResponse.setWeightIDs( new ArrayList() );
			riskAnsResponse.getWeightIDs().add(String.valueOf(riskAnswer.getWeightedResponseID()));
			riskAnsResponse.setChronicIDs(new ArrayList());
			riskAnsResponse.setUiControlTypeDesc((String) uiControlTypesMap.get(riskAnswer.getUiControlType()));
			
			riskAnsResponse.setAssessmentType(riskAnalysis.getAssessmentType());

//			if (riskAnswer.getUiControlType().equalsIgnoreCase(UIConstants.CHECKBOXWITHCHRONIC)){
//				
//				riskAnsResponse.getChronicIDs().add(String.valueOf(riskAnswer.getWeightedResponseID()));
//			}
			riskAnsResponse.setRiskAnalysisId( String.valueOf(riskAnswer.getRiskAnalysisId() ) );
			riskAnsResponse.setEntryDate( riskAnswer.getCreateTimestamp() );
			riskAnsResponse.setCreateUserID(createUser);
			
			//Add +1 to weight if chronic
			int weight = riskAnswer.getWeight();
			boolean isChronic = false;
			if( StringUtils.equalsIgnoreCase( riskAnswer.getText(), UIConstants.ISCHRONIC) 
					&& riskAnswer.getUiControlType().equalsIgnoreCase(UIConstants.CHECKBOXWITHCHRONIC)) 
			{
				weight++ ;
				isChronic = true ;
				riskAnsResponse.getChronicIDs().add(String.valueOf(riskAnswer.getWeightedResponseID()));
			}
			
			//Get Control Type
			String uiControlType = riskAnswer.getUiControlType() ;
			//Set Answer Text based on UI Type
			if (uiControlType.equalsIgnoreCase(UIConstants.DATE) || 
         	uiControlType.equalsIgnoreCase(UIConstants.TEXTBOX) ||
         	uiControlType.equalsIgnoreCase(UIConstants.TEXTAREA)) 
			{
				 riskAnsResponse.setAnswerText( riskAnswer.getText() );
			    //riskAnsResponse.setAnswerText( riskAnswer.getText().toString().replaceAll("\\s*\\[.*?\\]\\s*", " ") );
			} 
			else {
				StringBuffer answerText = new StringBuffer( riskAnswer.getAnswerText() );
				if(isChronic) 
				{
					answerText.append(" [Chronic]");
					//riskAnsResponse.setChronicID( riskAnswer.getOID() );
					//riskAnsResponse.getChronicIDs().add(riskAnswer.getOID());
					riskAnsResponse.setChronicID(String.valueOf(riskAnswer.getWeightedResponseID()));
					/* riskAnsResponse.setChronicID(String.valueOf(riskAnswer.getWeightedResponseID()));
					if (riskAnsResponse.getChronicIDs() == null){
						riskAnsResponse.setChronicIDs( new ArrayList() );
					}
					//riskAnsResponse.getChronicIDs().add(String.valueOf(riskAnswer.getOID()));
					riskAnsResponse.getChronicIDs().add(String.valueOf(riskAnswer.getWeightedResponseID()));
					*/
				}
				riskAnsResponse.setAnswerText( answerText.toString() );
			}
			
			//Continue setting RiskAnswerResponseEvent Attributes
			riskAnsResponse.setText( riskAnswer.getText() ); //For Dates, Text and UICONSTANTS.ISCHRONIC (Freeform)
			riskAnsResponse.setWeight( weight );
			riskAnsResponse.setQuestionID( String.valueOf(riskAnswer.getQuestionId()) );
			riskAnsResponse.setQuestionNumber( riskAnswer.getQuestionNumber() );
			riskAnsResponse.setRiskQuestionText( riskAnswer.getQuestionText() );
			riskAnsResponse.setUiControlType( riskAnswer.getUiControlType() );
			
			//Checks to see if RiskAnswerResponseEvent already exists in the hashmap
			RiskAnswerResponseEvent priorRiskAnsResponse = (RiskAnswerResponseEvent)map.get(riskAnswer.getQuestionNumber());
			
			//Consolidates RiskAnswerResponseEvent if it exists in the hashmap
			if( priorRiskAnsResponse != null ) 
			{
				priorRiskAnsResponse.setWeightID( UIConstants.EMPTY_STRING );
				priorRiskAnsResponse.getWeightIDs().add( String.valueOf(riskAnswer.getWeightedResponseID()));
				
				//New Weight To add
				int newWeight = riskAnswer.getWeight();
				if( isChronic ) 
				{
					newWeight++;
				}
				
				//Prior Weight to carry over
				{ int priorWeightTotal = priorRiskAnsResponse.getWeight();
					//Add New Weight and Prior Weight total, set new weight total
					priorRiskAnsResponse.setWeight(newWeight + priorWeightTotal);
					
					//Add New Answer Text To Answer Text
					StringBuffer answerTextsForQuestionNumber = new StringBuffer ( priorRiskAnsResponse.getAnswerText() );
					answerTextsForQuestionNumber.append(", " ).append(riskAnswer.getAnswerText());
					
					//Add isChronic to answer when needed
					if (isChronic) 
					{
						answerTextsForQuestionNumber.append(" [Chronic]");
						priorRiskAnsResponse.setChronicID( UIConstants.EMPTY_STRING );
						//priorRiskAnsResponse.getChronicIDs().add(riskAnswer.getOID());
						priorRiskAnsResponse.getChronicIDs().add(String.valueOf(riskAnswer.getWeightedResponseID()));
					}
					
					//Set consolidated answer text for viewing purposes
					priorRiskAnsResponse.setAnswerText(answerTextsForQuestionNumber.toString());
				}
				
				//Place new consolidated RiskAnswerResponseEvent in hashmap
				map.put( riskAnswer.getQuestionNumber(), priorRiskAnsResponse );
			} 
			else 
			{
				//Place new RiskAnswerResponseEvent in hashmap/vector
				String qNum = riskAnswer.getQuestionNumber() ;
				try
				{
					Integer qNumInt = new Integer( qNum ) ;
					map.put( qNum, riskAnsResponse );
					vec.addElement( qNumInt );
				}
				catch( NumberFormatException nfe )
				{
				}
			}
		} // while

		//Sort Vector
		if( vec.size() > 1 )
		{
			Collections.sort( vec );
		}

		postRiskResponseEvents(map, vec);
	}

	/**
	 * @param map
	 * @param vec
	 */
	private static void postRiskResponseEvents( SortedMap map, Vector vec)
	{
		RiskAnswerResponseEvent riskAnsResponse = null ;
		List respList = new ArrayList();
		int count = vec.size() ;
		
		for(int i = 0; i < count; i++)
		{
			String key = (vec.elementAt( i )).toString();
			if( ( riskAnsResponse = (RiskAnswerResponseEvent)map.get( key ) ) != null )
			{
				respList.add(riskAnsResponse);
			}
		}
		MessageUtil.postReplies(respList);
	}

	/**
	 * @deprecated
	 * @param riskAnalysisIntrw
	 * @param saveIntervAssessEvent
	 * @param riskAnalysis
	 * @throws ComputationValidationException
	 */
	public static void setCalculatedScoresInterview( 
			RiskAnalysisInterview riskAnalysisIntrw, 
			SaveInterviewAssessmentEvent saveIntervAssessEvent,
			RiskAnalysis riskAnalysis ) throws ComputationValidationException
	{
		RiskComputationContext riskComputationContext = new RiskComputationContext();
		InterviewRiskComputationReponseEvent interviewRiskCompRespEvent = 
				(InterviewRiskComputationReponseEvent)riskComputationContext.computeRiskAnalysis( saveIntervAssessEvent );

		RiskAnalysisInterview riskInterview = (RiskAnalysisInterview)riskAnalysisIntrw;
		riskInterview.setOnsetAge( interviewRiskCompRespEvent.getOnSetAge() );
		riskInterview.setTotalChild( interviewRiskCompRespEvent.getTotalChild() );
		riskInterview.setTotalChildHomeAttitude( interviewRiskCompRespEvent.getTotalChildHomeAttitude() );
		riskInterview.setTotalDeceasedParents( interviewRiskCompRespEvent.getTotalDeceasedParents() );
		riskInterview.setTotalExpulsions( interviewRiskCompRespEvent.getTotalExpulsions() );
		riskInterview.setTotalFailingClasses( interviewRiskCompRespEvent.getTotalFailingClasses() );
		riskInterview.setTotalFamily( interviewRiskCompRespEvent.getTotalFamily() );
		riskInterview.setTotalFamilyDynamics( interviewRiskCompRespEvent.getTotalFamilyDynamics() );
		riskInterview.setTotalGradesRepeated( interviewRiskCompRespEvent.getTotalGradesRepeated() );
		riskInterview.setTotalSchool( interviewRiskCompRespEvent.getTotalSchool() );
		riskInterview.setTotalSchoolBehavior( interviewRiskCompRespEvent.getTotalInterviewSchoolBehavior() );
		riskInterview.setTotalSiblings( interviewRiskCompRespEvent.getTotalSibling() );
		riskInterview.setTotalSuspensions( interviewRiskCompRespEvent.getTotalSuspensions() );
		//riskAnalysis.setFinalScore( interviewRiskCompRespEvent.getTotalScore() );
	}

	/**
	 * @deprecated
	 * @param riskResidential
	 * @param saveResAssessEvent
	 * @param riskAnalysis
	 */
	public static void setCalculatedScoresResidential( 
			RiskAnalysisResidential riskResidential, 
			SaveResidentialAssessmentEvent saveResAssessEvent, 
			RiskAnalysis riskAnalysis ) throws ComputationValidationException
	{
		RiskComputationContext riskComputationContext = new RiskComputationContext();
		ResidentialRiskComputationReponseEvent residentialRiskComputationReponseEvent = 
				(ResidentialRiskComputationReponseEvent)riskComputationContext.computeRiskAnalysis( saveResAssessEvent );

		riskResidential.setTotalEvaluation( residentialRiskComputationReponseEvent.getTotalEvaluation() );
		riskResidential.setTotalReview( residentialRiskComputationReponseEvent.getTotalReview() );
		riskResidential.setTotalSchoolRecords( residentialRiskComputationReponseEvent.getTotalSchoolRecords() );
		//riskAnalysis.setFinalScore( residentialRiskComputationReponseEvent.getTotalScore() );
	}
	
	/**
	 * @deprecated
	 * @param riskResidential
	 * @param saveResAssessEvent
	 * @param riskAnalysis
	 */
	public static void setCalculatedScoresCourtReferral( 
			RiskAnalysisCourtReferral riskCourtReferral, 
			SaveCourtReferralAssessmentEvent saveCourtReferralAssessEvent, 
			RiskAnalysis riskAnalysis ) throws ComputationValidationException
	{
		RiskComputationContext riskComputationContext = new RiskComputationContext();
		CourtReferralRiskComputationReponseEvent courtReferralRiskCompRespEvent = 
				(CourtReferralRiskComputationReponseEvent)riskComputationContext.computeRiskAnalysis( saveCourtReferralAssessEvent );
		
		// if any needs to be added to the riskCourtReferral entity, it would be done here
		
	}

	/**
	 * @deprecated
	 * @param riskResidential
	 * @param saveResAssessEvent
	 * @param riskAnalysis
	 */
	public static void setCalculatedScoresCommunity( 
			RiskAnalysisCommunity riskCommunity, 
			SaveCommunityAssessmentEvent saveCommAssessEvent, 
			RiskAnalysis riskAnalysis ) throws ComputationValidationException
	{
		RiskComputationContext riskComputationContext = new RiskComputationContext();
		CommunityRiskComputationReponseEvent communityRiskCompRespEvent = 
				(CommunityRiskComputationReponseEvent)riskComputationContext.computeRiskAnalysis( saveCommAssessEvent );

		riskCommunity.setTotalPeer( communityRiskCompRespEvent.getTotalPeer() );
		riskCommunity.setTotalAttitude( communityRiskCompRespEvent.getTotalAttitude() );
		riskCommunity.setTotalSubstanceAbuse( communityRiskCompRespEvent.getTotalSubstanceAbuse() );
		riskCommunity.setTotalClassesFailing( communityRiskCompRespEvent.getTotalClassesFailing() );
		riskCommunity.setTotalGradeLevel( communityRiskCompRespEvent.getTotalGradeLevel() );
		riskCommunity.setTotalChild( communityRiskCompRespEvent.getTotalChild() );
		riskCommunity.setTotalSchoolBehavior( communityRiskCompRespEvent.getTotalSchoolBehavior() );
		riskCommunity.setTotalEducationStatus( communityRiskCompRespEvent.getTotalEducationStatus() );
		riskCommunity.setTotalChildParent( communityRiskCompRespEvent.getTotalChildParent() );
		riskCommunity.setTotalFamilyAttitude( communityRiskCompRespEvent.getTotalFamilyAttitude() );
		//riskAnalysis.setFinalScore( communityRiskCompRespEvent.getTotalScore() );
	}
	
	/**
	 * @param saveRiskAssessEvent
	 * @return
	 */
	public static RiskComputationReponseEvent setCalculatedScores(SaveRiskAssessmentEvent saveRiskAssessEvent){
		
		GenericRiskComputation grc = new GenericRiskComputation(saveRiskAssessEvent);
		RiskComputationReponseEvent rcre = grc.compute();	
		
		return rcre;
	}
	/**
	 * @deprecated
	 * @param riskResidential
	 * @param saveResAssessEvent
	 * @param riskAnalysis
	 */
	public static void setCalculatedScoresProgress( 
			RiskAnalysisProgress riskProgress, 
			SaveProgressAssessmentEvent saveProgAssessEvent, 
			RiskAnalysis riskAnalysis ) throws ComputationValidationException
	{
		RiskComputationContext riskComputationContext = new RiskComputationContext();
		ProgressRiskComputationReponseEvent progressRiskCompRespEvent = (ProgressRiskComputationReponseEvent)
				riskComputationContext.computeRiskAnalysis( saveProgAssessEvent );

		 riskProgress.setTotalCurrentAttitude( progressRiskCompRespEvent.getTotalCurrentAttitude() );
		riskProgress.setTotalFamilyRelationship( progressRiskCompRespEvent.getTotalFamilyRelationship() );
		riskProgress.setTotalSchoolAttendance( progressRiskCompRespEvent.getTotalSchoolAttendance() );
		riskProgress.setTotalSchoolBehavior( progressRiskCompRespEvent.getTotalSchoolBehavior() );
		riskProgress.setTotalSupervision( progressRiskCompRespEvent.getTotalSupervision() );
		riskProgress.setTotalSupervisionRules( progressRiskCompRespEvent.getTotalSupervisionRules() ); 
		//riskAnalysis.setFinalScore( progressRiskCompRespEvent.getTotalScore() );
	
		GenericRiskComputation grc = new GenericRiskComputation(saveProgAssessEvent);
		RiskComputationReponseEvent rcre = grc.compute();
	}
	/**
	 * @deprecated
	 * @param riskResidential
	 * @param saveResAssessEvent
	 * @param riskAnalysis
	 */
	public static void setCalculatedScoresTesting( 
			RiskAnalysisTest riskTesting, 
			SaveTestingAssessmentEvent saveTestAssessEvent, 
			RiskAnalysis riskAnalysis ) throws ComputationValidationException
	{
		RiskComputationContext riskComputationContext = new RiskComputationContext();
		TestingRiskComputationReponseEvent testingRiskCompRespEvent = (TestingRiskComputationReponseEvent)
				riskComputationContext.computeRiskAnalysis( saveTestAssessEvent );

		riskTesting.setTotalAbuseHistory( testingRiskCompRespEvent.getTotalAbuseHistory() );
		riskTesting.setTotalAppearence( testingRiskCompRespEvent.getTotalAppearence() );
		riskTesting.setTotalBehviorHistory( testingRiskCompRespEvent.getTotalBehviorHistory() );
		riskTesting.setTotalDevelopmental( testingRiskCompRespEvent.getTotalDevelopmental() );
		riskTesting.setTotalFamilyProblems( testingRiskCompRespEvent.getTotalFamilyProblems() );
		riskTesting.setTotalFamilyRelationship( testingRiskCompRespEvent.getTotalFamilyRelationship() );
		riskTesting.setTotalPeerRelationship( testingRiskCompRespEvent.getTotalPeerRelationship() );
		riskTesting.setTotalSchoolAcademic( testingRiskCompRespEvent.getTotalSchoolAcademic() );
		riskTesting.setTotalSchoolBehavior( testingRiskCompRespEvent.getTotalSchoolBehavior() );
		riskTesting.setTotalSchoolAttendance( testingRiskCompRespEvent.getTotalSchoolAttendance() );
		riskTesting.setTotalSelfImage( testingRiskCompRespEvent.getTotalSelfImage() );
		riskTesting.setTotalSubstance( testingRiskCompRespEvent.getTotalSubstance() );
		riskTesting.setTotalStrengths( testingRiskCompRespEvent.getTotalStrengths() );
		//riskAnalysis.setFinalScore( testingRiskCompRespEvent.getTotalScore() );
	}

	/*
	 * 
	 */
	public static int getSupervisionRulesTotal( String casefileID )
	{
		int totalRules = 0;

		Iterator rulesIterator = RuleGroupConditionView.findAll( "casefileId", casefileID );
		while(rulesIterator.hasNext())
		{
			RuleGroupConditionView ruleView = (RuleGroupConditionView)rulesIterator.next();
			if( ruleView.getCompletionStatusId().equalsIgnoreCase( "N" ) )
			{ // check the actual code for non-compliant
				totalRules += 4;
			}
		}

		return totalRules;
	}

	/*
	 * 
	 */
	public static int getEducationTotalForResidential( String juvenileNum )
	{
		int total = 0;
		Collection eduTraits = new ArrayList();

		retrieveTraitsAndDescriptionsByType( RiskAnalysisConstants.RESIDENTIAL_SCHOOLATTENDANCE_TRAIT, eduTraits );
		retrieveTraitsAndDescriptionsByType( RiskAnalysisConstants.RESIDENTIAL_SCHOOLBEHAVIOR_TRAIT, eduTraits );
		retrieveTraitsAndDescriptionsByType( RiskAnalysisConstants.RESIDENTIAL_EDUCATIONALPERFORMANCE_TRAIT, eduTraits );

		Map juvTraitMap = retrieveAllTraitsForJuvenile( juvenileNum );
		Iterator<TraitTypeResponseEvent> ite = eduTraits.iterator();
		while(ite.hasNext())
		{
			TraitTypeResponseEvent tEvt = ite.next();
			if( juvTraitMap.containsKey( tEvt.getTraitTypeId() ) )
			{
				try
				{
					total += Integer.parseInt( tEvt.getRiskPoints() );
				}
				catch(NumberFormatException nfe)
				{ /* just fall through and let 'total' stay as is */
				}
			}
		}

		return total;
	}

	/*
	 * 
	 */
	public static Map retrieveAllTraitsForJuvenile( String juvenileNum )
	{
		Map juvTraitMap = new HashMap();

		Iterator<JuvenileTrait> iter = JuvenileTrait.findAllByAttributeName( "juvenileNum", juvenileNum );
		while(iter.hasNext())
		{
			JuvenileTrait trait = iter.next();
			JuvenileTraitResponseEvent replyEvent = trait.getValueObject();
			juvTraitMap.put( replyEvent.getTraitTypeId(), replyEvent );
		}

		return juvTraitMap;
	}

	/*
	 * 
	 */
	private static void retrieveTraitsAndDescriptionsByType( 
			String traitTypeName, Collection eduTraits )
	{
		TraitType parentTrait = TraitType.findByAttributeName( "name", traitTypeName );
		GetJuvenileTraitTypesEvent juvTraitEvent = (GetJuvenileTraitTypesEvent)
				EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILETRAITTYPES );

		juvTraitEvent.setTraitType( parentTrait.getOID().toString() );

		Iterator<TraitType> i = TraitType.findByType( juvTraitEvent );
		while(i.hasNext())
		{
			TraitType traitType = i.next();
			TraitTypeResponseEvent replyEvent = traitType.getValueObject();
			replyEvent.setTopic( traitType.getParentTypeId() );
			eduTraits.add( replyEvent );
		}
	}

	/*
	 * 
	 */
	public static boolean getProbationStatus( String JuvenileNum )
	{
		boolean status = false;
		GetProbationStatusForRiskAssessmentEvent event = new GetProbationStatusForRiskAssessmentEvent();

		event.setJuvenileNum( JuvenileNum );

		Iterator files = JuvenileCasefile.findAll( event );
		if( files.hasNext() )
		{
			status = true;
		}

		return status;
	}

	/**
	 * @param saveIntAssessEvent
	 * @param assessmentType
	 * @return riskAnalysis
	 */
	public static RiskAnalysis getLatestRiskAnalysisUpTo30Days( String juvenileNum, String assessmentType )
	{
		GetRecentRiskAnalysisForJuvenileEvent event = new GetRecentRiskAnalysisForJuvenileEvent();
		event.setJuvenileNumber( juvenileNum );
		event.setAssessmentType( assessmentType );
		RiskAnalysis riskAnalysis = null;
		Iterator riskAnalysisIter = RiskAnalysis.findAll( event );
		if( riskAnalysisIter.hasNext() )
		{
			riskAnalysis = (RiskAnalysis)riskAnalysisIter.next();
		}

		return riskAnalysis;
	}
	
	/**
	 * @param saveIntAssessEvent
	 * @param assessmentType
	 * @return riskAnalysis
	 */
	public static RiskAnalysis getLatestRiskAnalysisByCaseFile( String casefileId, String assessmentType )
	{
		RiskAnalysis riskAnalysis = null;
		
		GetLatestRiskAnalysisEvent getLatestRiskAnalysisEventByCaseFile = new GetLatestRiskAnalysisEvent();
		getLatestRiskAnalysisEventByCaseFile.setCasefileID( casefileId );
		getLatestRiskAnalysisEventByCaseFile.setAssessmentType( assessmentType );
				
		Iterator<RiskAnalysis> riskAnalysisIter = RiskAnalysis.findAll( getLatestRiskAnalysisEventByCaseFile );
		if( riskAnalysisIter != null && riskAnalysisIter.hasNext() )
		{
			while(riskAnalysisIter.hasNext())
			{
				riskAnalysis = riskAnalysisIter.next();
			}
		}

		return riskAnalysis;
	}
	
	/**
	 * @param saveIntAssessEvent
	 * @param assessmentType
	 * @return riskAnalysis
	 */
	public static RiskAnalysis getLatestRiskAnalysisByJuvenile( String juvenileNum, String assessmentType )
	{
		RiskAnalysis riskAnalysis = null;
		
		CheckPreConditionsForJuvenileEvent getLatestRiskAnalysisEventByJuvenile = new CheckPreConditionsForJuvenileEvent();
		getLatestRiskAnalysisEventByJuvenile.setAssessmentType(assessmentType);
		getLatestRiskAnalysisEventByJuvenile.setJuvenileNumber(juvenileNum);
		
		Iterator<RiskAnalysis> riskAnalysisIter = RiskAnalysis.findAll( getLatestRiskAnalysisEventByJuvenile );
		if( riskAnalysisIter != null && riskAnalysisIter.hasNext() )
		{
			while(riskAnalysisIter.hasNext())
			{
				riskAnalysis = riskAnalysisIter.next();
			}
		}

		return riskAnalysis;
	}
	/**
	 * Find latest referral assessment entered before a given date.
	 * @param juvenileNum
	 * @param assessmentType
	 * @param aRiskDate
	 * @return
	 */
	public static RiskAnalysis getLatestRiskAnalysisByJuvenile( String juvenileNum, String assessmentType, Date aRiskDate )
	{
		RiskAnalysis riskAnalysis = null;
		
		GetRecentReferralRiskAnalysisByDateEvent getLatestRiskAnalysisEventByJuvenile = new GetRecentReferralRiskAnalysisByDateEvent();
		getLatestRiskAnalysisEventByJuvenile.setAssessmentType(assessmentType);
		getLatestRiskAnalysisEventByJuvenile.setJuvenileNum(juvenileNum);
		Timestamp ts = new Timestamp(aRiskDate.getTime());
		getLatestRiskAnalysisEventByJuvenile.setRiskDate(ts);
		
		Iterator<RiskAnalysis> riskAnalysisIter = RiskAnalysis.findAll( getLatestRiskAnalysisEventByJuvenile );
		if( riskAnalysisIter != null && riskAnalysisIter.hasNext() )
		{
			while(riskAnalysisIter.hasNext())
			{
				riskAnalysis = riskAnalysisIter.next();
			}
		}

		return riskAnalysis;
	}
	/**
	 * @param saveIntAssessEvent
	 * @param assessmentType
	 * @return riskAnalysis
	 */
	public static Iterator getRiskAnalysisByCasefileIdAndAssessmentType( 
			String casefileId, String assessmentType )
	{
		GetRiskAnalysisByCasefileIdAndAssmtTypeEvent gEvent = new GetRiskAnalysisByCasefileIdAndAssmtTypeEvent();
		gEvent.setAssessmentType( assessmentType );
		gEvent.setCasefileID( casefileId );
		Iterator iter = RiskAnalysis.findAll( gEvent );

		return iter;
	}
	
	/**
	 * @param preCondEvent
	 * @return
	 */
	public static boolean doesTJPCRiskAnalysisExistForCasefile(
			String casefileId) {
		boolean tjpcExistOnCasefile = false;
		Iterator<RiskAnalysis> iterTJCPMale = PDRiskAnalysisHelper.getRiskAnalysisByCasefileIdAndAssessmentType(casefileId, RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE);
		if( iterTJCPMale != null && iterTJCPMale.hasNext() ) {
			tjpcExistOnCasefile = true;
		} else {
			Iterator<RiskAnalysis> iterTJCPFemale = PDRiskAnalysisHelper.getRiskAnalysisByCasefileIdAndAssessmentType(casefileId, RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE);
			if( iterTJCPFemale != null && iterTJCPFemale.hasNext() ) {
				tjpcExistOnCasefile = true;
			}
		}
		return tjpcExistOnCasefile;
	}

	/**
	 * @param riskAnalysis
	 * @param latestReferralRiskAnalysis
	 */
	/* public static void setInterviewRiskAnalysisRecommendation( 
			RiskAnalysis riskAnalysis, boolean isCustody )
	{
		Iterator<RiskRecommendation> iter = RiskRecommendation.findAllByAssessmentType( RiskAnalysisConstants.RISK_ASSESSMENT_TYPE, RiskAnalysisConstants.RISK_TYPE_INTERVIEW );

		while(iter.hasNext())
		{
			RiskRecommendation riskRecommend = iter.next();

			if( riskRecommend.isCustody() != isCustody )
			{
				continue;
			}

			if( riskAnalysis.getFinalScore() >= riskRecommend.getMinValue() && 
					riskAnalysis.getFinalScore() < (riskRecommend.getMaxValue() + 1) )
			{
				riskAnalysis.setRecommendation( riskRecommend.getRecommendation() );
				break;
			}
		}
	}*/

	/**
	 * @param riskAnalysis
	 * @param isCustody
	 */
	/* public static void setProgressRiskAnalysisRecommendation( 
			RiskAnalysis riskAnalysis, boolean isCustody )
	{
		Iterator<RiskRecommendation> iter = RiskRecommendation.findAllByAssessmentType( RiskAnalysisConstants.RISK_ASSESSMENT_TYPE, RiskAnalysisConstants.RISK_TYPE_PROGRESS );

		while(iter.hasNext())
		{
			RiskRecommendation riskRecommend = iter.next();

			if( riskRecommend.isCustody() != isCustody )
			{
				continue;
			}

			if( riskAnalysis.getFinalScore() >= riskRecommend.getMinValue() && 
					riskAnalysis.getFinalScore() < (riskRecommend.getMaxValue() + 1) )
			{
				riskAnalysis.setRecommendation( riskRecommend.getRecommendation() );
				break;
			}
		}
	}*/

	/**
	 * @param dispatch
	 * @param b
	 */
	public static void retrieveTestingTraitQuestions( IDispatch dispatch, boolean preConditions )
	{
	}

	/**
	 * @param preCondEvent
	 * @param dispatch
	 */
	public static void sendProgressPrefillInfo( CheckProgressPreConditionsEvent preCondEvent)
	{
		ProgressPrefillResponseEvent prefill = new ProgressPrefillResponseEvent();

		JuvenileCasefile caseFile = JuvenileCasefile.find(preCondEvent.getCaseFileId());

		if (caseFile != null){
			Collection coll = caseFile.getAssignments();
			List <Assignment>  assignments = CollectionUtil.iteratorToList(coll.iterator());
			Assignment assignment = null;
			Date oldestAssignmentDate = null;
			for (int i = 0; i < assignments.size(); i++) {
				assignment = assignments.get(i);
				if (oldestAssignmentDate == null){
					oldestAssignmentDate = assignment.getAssignmentAddDate();
				} else if (assignment.getAssignmentAddDate().before(oldestAssignmentDate)){
					oldestAssignmentDate = assignment.getAssignmentAddDate();
				}
			}
			if (oldestAssignmentDate != null){
				int days = DateFormatter.getDayDiff(oldestAssignmentDate, preCondEvent.getAssessmentDate());
				prefill.setSupervisionMonths(DateFormatter.getMonths(days));
			} else {
				prefill.setSupervisionMonths(0);
			}
		}

		MessageUtil.postReply(prefill);
	}
	
	/**
	 * @param riskAnalysis
	 */
	public static void bindDeleteRiskResponses(RiskAnalysis riskAnalysis) {
		Collection reponses = riskAnalysis.getRiskResponses();
		Iterator<RiskResponse> iteResponses = reponses.iterator();
		while(iteResponses.hasNext()) {
			RiskResponse riskResponse = iteResponses.next();
			riskResponse.delete();
			IHome homeRiskResponse=new Home();
			homeRiskResponse.bind(riskResponse);
		}
		riskAnalysis.clearRiskResponses();
	}
	
	/**
	 * @param riskAnalysis
	 * @param events
	 */
	public static void bindCreateRiskReponses(String riskAnalysisId, Enumeration events) {
		
		List respList = new ArrayList();
		
		while (events.hasMoreElements())
		{
			Object obj = events.nextElement();
			if( obj instanceof RiskQuestionAnswerEvent ) {	
				RiskQuestionAnswerEvent riskReqEvent = (RiskQuestionAnswerEvent)obj;
				
				RiskResponse riskResponse =  new RiskResponse();
				riskResponse.setRiskAnalysisId(riskAnalysisId.toString());
				riskResponse.setText(riskReqEvent.getText());
				riskResponse.setWeightedResponseID(String.valueOf(riskReqEvent.getWeightedResponseID()));
				IHome homeRiskResponse=new Home();
				homeRiskResponse.bind(riskResponse);
				respList.add(riskResponse);
			}
		}
	}
	
	/**
	 * @deprecated
	 * @param riskAnalysis
	 * @param dispatch
	 */
	public static void sendRecommendationsAndFinalScores(RiskAnalysis riskAnalysis) {

		Collection recommendations = riskAnalysis.getRecommendations();
		Collection finalscores = riskAnalysis.getFinalScores();
		Iterator<RiskFinalScore> iteFinalscores = finalscores.iterator();
		List respList = new ArrayList();
		while(iteFinalscores.hasNext()) 
		{
			RiskFinalScore riskFinalScore = iteFinalscores.next();
			
			Iterator<RiskAnalysisRecommendation> iterRecommendations = recommendations.iterator();
			while(iterRecommendations.hasNext())
			{
				RiskAnalysisRecommendation riskAnalysisRecommendation = iterRecommendations.next();
				RiskRecommendation riskRecommend = riskAnalysisRecommendation.getRecommendation();
				
				if (riskFinalScore.getRiskResultGroupId() == riskRecommend.getRiskResultGroupId()) 
				{
					
					RiskRecommendationResponseEvent riskRecommendScore = new RiskRecommendationResponseEvent();
					riskRecommendScore.setRiskAnalysisRecommendation(riskRecommend.getRecommendation());
					riskRecommendScore.setRiskAnalysisScore(riskFinalScore.getFinalScore());
					riskRecommendScore.setRiskAnalysisId(riskAnalysis.getOID());
					riskRecommendScore.setResultGroup(riskRecommend.getRiskResultGroup().getDescription());
					riskRecommendScore.setResultGroupDisplayDesc(riskRecommend.getRiskResultGroup().getDisplayDescription());
					respList.add(riskRecommendScore);
				}
			}
		}
		MessageUtil.postReplies(respList);
	}
	
	public static void sendRecommendationsAndFinalScores(List <RiskFinalScore> scoreList, List <RiskAnalysisRecommendation> recommendationList) {

		List respList = new ArrayList();
		RiskFinalScore riskFinalScore = null;
		RiskRecommendation riskRecommend = null;
		RiskAnalysisRecommendation riskAnalysisRecommendation = null;
		RiskResultGroup riskResultGroup = null;
		
		for (int i = 0; i < scoreList.size(); i++) 
		{
			riskFinalScore = scoreList.get(i);
			
			for (int j = 0; j < recommendationList.size(); j++) 
			{
				riskAnalysisRecommendation = recommendationList.get(j);
				riskRecommend = riskAnalysisRecommendation.getRecommendation();
				
				if (riskFinalScore.getRiskResultGroupId() == riskRecommend.getRiskResultGroupId()) 
				{
					
					RiskRecommendationResponseEvent riskRecommendScore = new RiskRecommendationResponseEvent();
					riskRecommendScore.setRiskAnalysisRecommendation(riskRecommend.getRecommendation());
					riskRecommendScore.setRiskAnalysisScore(riskFinalScore.getFinalScore());
					riskRecommendScore.setRiskAnalysisId(new Integer(riskFinalScore.getRiskAnalysisId()).toString());

					riskResultGroup = RiskResultGroup.find(new Integer(riskFinalScore.getRiskResultGroupId()).toString());
					riskRecommendScore.setResultGroup(riskResultGroup.getDescription());
					riskRecommendScore.setResultGroupDisplayDesc(riskResultGroup.getDisplayDescription());
					respList.add(riskRecommendScore);
				}
			}
		}
		MessageUtil.postReplies(respList);
	}

/*	public static void sendProgressRecommendationsAndFinalScores(RiskAnalysis riskAnalysis,
			IDispatch dispatch) {
		
		Collection recommendations = riskAnalysis.getRecommendations();
		Collection finalscores = riskAnalysis.getFinalScores();
		Iterator <RiskFinalScore> iteFinalscores = finalscores.iterator();
		
		while(iteFinalscores.hasNext()) 
		{
			RiskFinalScore riskFinalScore = iteFinalscores.next();
			
			Iterator <RiskAnalysisRecommendation> iterRecommendations = recommendations.iterator();
			while(iterRecommendations.hasNext())
			{
				RiskAnalysisRecommendation riskAnalysisRecommendation = iterRecommendations.next();
				RiskRecommendation riskRecommend = riskAnalysisRecommendation.getRecommendation();
				
				if (riskFinalScore.getRiskResultGroupId() == riskRecommend.getRiskResultGroupId()) 
				{
					
					ProgressRiskRecommendationResponseEvent riskRecommendScore = new ProgressRiskRecommendationResponseEvent();
					riskRecommendScore.setRiskAnalysisRecommendation(riskRecommend.getRecommendation());
					riskRecommendScore.setRiskAnalysisScore(riskFinalScore.getFinalScore());
					riskRecommendScore.setRiskAnalysisId(riskAnalysis.getOID());
					riskRecommendScore.setResultGroup(riskRecommend.getRiskResultGroup().getDescription());
					riskRecommendScore.setResultGroupDisplayDesc(riskRecommend.getRiskResultGroup().getDisplayDescription());
					//riskRecommendScore.setTotalSupervisionRules(totalSupervisionRules);
					dispatch.postEvent(riskRecommendScore);
					
				}
				
			}
			
		}
	}	*/
	/**
	 * @deprecated
	 * @param riskAnalysis
	 * @param dispatch
	 */
	public static void sendSuggestedCasePlainDomains(String riskAnalysisId) {
		List suggestedDomainList = new ArrayList();
		
		// Retrieves all the responses associated with the RISK_ANALYSIS_ID
		Iterator<RiskAnswer> riskAnswers = RiskAnswer.findAll( 
				RiskAnalysisConstants.RISK_ANALYSIS_ID, riskAnalysisId );
		
		//Find reporting referral # supplied by user
		while(riskAnswers.hasNext())
		{
			RiskAnswer riskAnswer = riskAnswers.next();
			if (riskAnswer.getSuggestedCasePlainDomiainId() > 0) 
			{
				
				if (riskAnswer.getUiControlType().equalsIgnoreCase(UIConstants.TEXTBOX)) 
				{
					//Keep in mind that the rule uses a greater then or equal to the numerical rule value coming from the database
					if ( (riskAnswer.getText() != null) && (riskAnswer.getSuggestedCasePlainDomiainNumericRule() > 0)
							&& (Integer.parseInt(riskAnswer.getText()) >= riskAnswer.getSuggestedCasePlainDomiainNumericRule() ) ) 
					{
						if (!suggestedDomainList.contains(new String(riskAnswer.getSuggestedCasePlainDomiainName()))) 
						{
							suggestedDomainList.add(new String(riskAnswer.getSuggestedCasePlainDomiainName()));
						}
						
					}
					
				} else {
					if (!suggestedDomainList.contains(new String(riskAnswer.getSuggestedCasePlainDomiainName()))) 
					{
						suggestedDomainList.add(new String(riskAnswer.getSuggestedCasePlainDomiainName()));
					}
				}
			}
		}
		
		if (suggestedDomainList.size() > 0) 
		{
			RiskSuggestedCasePlanDomainResponseEvent riskSuggestedCasePlanDomainResponseEvent 
				= new RiskSuggestedCasePlanDomainResponseEvent();
			riskSuggestedCasePlanDomainResponseEvent.setSuggestedCasePlanDomainNames(suggestedDomainList);
			MessageUtil.postReply(riskSuggestedCasePlanDomainResponseEvent);
		}
	}
	/**
	 * @param riskAnalysis
	 * @param dispatch
	 */
	public static void sendSuggestedCasePlanDomains(String riskAnalysisId) {
		List suggestedDomainList = new ArrayList();
		
		// Retrieves all the responses associated with the RISK_ANALYSIS_ID
		Iterator<RiskAnswer> riskAnswers = RiskAnswer.findAll( 
				RiskAnalysisConstants.RISK_ANALYSIS_ID, riskAnalysisId );
		
		//Find reporting referral # supplied by user
		while(riskAnswers.hasNext())
		{
			RiskAnswer riskAnswer = riskAnswers.next();
			if (riskAnswer.getSuggestedCasePlainDomiainId() > 0) 
			{
				
				if (riskAnswer.getUiControlType().equalsIgnoreCase(UIConstants.TEXTBOX)) 
				{
					//Keep in mind that the rule uses a greater then or equal to the numerical rule value coming from the database
					if ( (riskAnswer.getText() != null) && (riskAnswer.getSuggestedCasePlainDomiainNumericRule() > 0)
							&& (Integer.parseInt(riskAnswer.getText()) >= riskAnswer.getSuggestedCasePlainDomiainNumericRule() ) ) 
					{
						if (!suggestedDomainList.contains(new String(riskAnswer.getSuggestedCasePlainDomiainName()))) 
						{
							suggestedDomainList.add(new String(riskAnswer.getSuggestedCasePlainDomiainName()));
						}
						
					}
					
				} else {
					if (!suggestedDomainList.contains(new String(riskAnswer.getSuggestedCasePlainDomiainName()))) 
					{
						suggestedDomainList.add(new String(riskAnswer.getSuggestedCasePlainDomiainName()));
					}
				}
			}
		}
		
		if (suggestedDomainList.size() > 0) 
		{
			RiskSuggestedCasePlanDomainResponseEvent riskSuggestedCasePlanDomainResponseEvent 
				= new RiskSuggestedCasePlanDomainResponseEvent();
			riskSuggestedCasePlanDomainResponseEvent.setSuggestedCasePlanDomainNames(suggestedDomainList);
			MessageUtil.postReply(riskSuggestedCasePlanDomainResponseEvent);
		}
	}

	public static RiskAnalysis getLatestReferralRiskAnalysis(String juvNumber){
		
		RiskAnalysis latestReferralRiskAnalysis = null;
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
		return latestReferralRiskAnalysis;
	}
	public static RiskAnalysis getLatestReferralRiskAnalysisOnUpdate(String juvNumber, Date updatedRiskDate){
		
		//Find referral assessment where assessmentDate <= updatedRiskDate;
		RiskAnalysis latestReferralRiskAnalysis = null;
		RiskAnalysis latestCustodyeferralRiskAnalysis 
			= PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile( juvNumber, RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL, updatedRiskDate );
		RiskAnalysis latestNonCustodyReferralRiskAnalysis 
			= PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile( juvNumber, RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL, updatedRiskDate );

		RiskAnalysis latestCourtReferralMaleRiskAnalysis 
			= PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile( juvNumber, RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE, updatedRiskDate );
		RiskAnalysis latestCourtReferralFemaleRiskAnalysis 
			= PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile( juvNumber, RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE, updatedRiskDate );

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
		return latestReferralRiskAnalysis;
	}	
	public static int getRiskAnalysisTotalScore(RiskAnalysis riskAnalysis){
		
		Collection scores = riskAnalysis.getFinalScores();
		List <RiskFinalScore> aList = CollectionUtil.iteratorToList(scores.iterator());
		RiskFinalScore score = null;
		int totalScore = 0;
		
		for (int i = 0; i < aList.size(); i++) {
			score = aList.get(i);
			totalScore = totalScore + score.getFinalScore();
		}
		return totalScore;
	}
	
	public static JuvenileDeliquencyHistoryEvent getDelinquencyHistory( 
			String aJuvNumber, int addtlCharges )
	{
		/* 09june2009 - mjt - if you need to alter the functionality 
		 * of this function, be sure you visit getBehaviorHistory
		 * found in GetBehavioralHistoryCommand.java, because it has
		 * much of the same functionality there. 
		 */
		JuvenileDeliquencyHistoryEvent resp = new JuvenileDeliquencyHistoryEvent();

		JJSJuvenile juvenile = JJSJuvenile.find( aJuvNumber );
		if( juvenile != null )
		{
			HashSet hashAdjudicationEvents = new HashSet();
			SortedSet setReferralDate = new TreeSet();
			HashSet hashDeferredAdjudicationEvents = new HashSet();
			HashSet hashTYCCommitments = new HashSet();

			// Calculation for Referral History
			HashSet hashReferralNums = new HashSet();
			Date courtDate ;

			for( Iterator iter = JJSReferral.findAll( "juvenileNum", aJuvNumber );
					iter.hasNext(); /*empty*/ )
			{
				JJSReferral aReferral = (JJSReferral)iter.next();

				if( StringUtils.isNotEmpty( aReferral.getReferralNum() ) )
				{
					hashReferralNums.add( aReferral.getReferralNum() );
				}
				
				if( (courtDate = aReferral.getCourtDate() ) != null )
				{
					if( "P".equalsIgnoreCase(aReferral.getPIACode() ) )
					{
						hashAdjudicationEvents.add( courtDate );
					}
					else if( "I".equalsIgnoreCase( aReferral.getPIACode()) )
					{
						hashDeferredAdjudicationEvents.add( courtDate );
					}

					if(  "B".equalsIgnoreCase( aReferral.getCourtDecisionSubgroupIndicator() ) )
					{
						hashTYCCommitments.add( courtDate );
					}
				}

				if( aReferral.getReferralDate() != null )
				{
					setReferralDate.add( aReferral.getReferralDate() );
				}
			}
			
			int age = 0;
			if ( juvenile.getBirthDate() != null && !setReferralDate.isEmpty() ) 
			{
				DateTime dateOfBirth = new DateTime(juvenile.getBirthDate());
				DateTime firstReferralDate = new DateTime((Date)setReferralDate.first());
				
				Years yearDifference = Years.yearsBetween(dateOfBirth, firstReferralDate);
				age = yearDifference.getYears();
			}
			
			// Offenses data
			int countFelony1 = 0;
			int countFelony2 = 0;
			int countFelony3 = 0;
			int countFelonyCapital = 0;
			int countFelonyStateJail = 0;
			int countNumberOfRunaways = 0;
			int countTotalCityOrd = 0;
			int countTotalStatus = 0;
			int totalStatusCO = 0;
			int aboveMisDeamA = 0;
			int countTotalMisdemeanorA = 0;
			int countTotalMisdemeanorB = 0;
			int countTotalMiddemenaorAB = 0;
			int countTotalMisdemeanorC = 0;
			int countTotalOffenses = 0;
			int countViolationOfProbations = 0;
			String offenseCat = UIConstants.EMPTY_STRING ;

			Iterator<JJSOffense> i = JJSOffense.findAll( "juvenileNum", aJuvNumber );
			while( i.hasNext() )
			{
				JJSOffense offense = i.next();
				offenseCat =  offense.getOffenseCategory() ;
				countTotalOffenses++ ;
				
				if( offenseCat.equals( "F1" ) )
				{
					countFelony1++;
				}
				else if( offenseCat.equals( "F2" ) )
				{
					countFelony2++;
				}
				else if( offenseCat.equals( "F3" ) )
				{
					countFelony3++;
				}
				else if( offenseCat.equals( "CF" ) )
				{
					countFelonyCapital++;
				}
				else if( offenseCat.equals( "JF" ) )
				{
					countFelonyStateJail++;
				}
				else if( offenseCat.equals( "CO" ) )
				{
					countTotalCityOrd++;
				}
				else if( offenseCat.equals( "SO" ) )
				{
					countTotalStatus++;
				}
				else if( offenseCat.equals( "MA" ) )
				{
					countTotalMisdemeanorA++;
				}
				else if( offenseCat.equals( "MB" ) )
				{
					countTotalMisdemeanorB++;
				}
				else if( offenseCat.equals( "MC" ) )
				{
					countTotalMisdemeanorC++;
				}
				
				if( offense.getOffenseReportGroup().equals( "17" ) )
				{
					countViolationOfProbations++;
				}
				else if( offense.getOffenseReportGroup().equals( "21" ) )
				{
					countNumberOfRunaways++;
				}
			} // while

			// Calculation for SeriousnessIndex
			double offenseSeverityTotal = 0.0;
			double offenseCount = 0.0 ;

			GetJuvenileCasefileOffensesEvent off = new GetJuvenileCasefileOffensesEvent();

			Iterator iterReferralNum = hashReferralNums.iterator();
			while(iterReferralNum.hasNext())
			{
				off.setJuvenileNum( aJuvNumber );
				off.setReferralNum( iterReferralNum.next().toString() );
				for( Iterator<JJSOffense> iOffenses = JJSOffense.findAll( off );
							iOffenses.hasNext(); /*empty*/)
				{
					JJSOffense offenseSeverity = iOffenses.next();
					if( StringUtils.isNotEmpty( offenseSeverity.getSeverity()))   
					{
						offenseSeverityTotal += Double.parseDouble( offenseSeverity.getSeverity() );
						if (!offenseSeverity.getSeverity().equals("0")) {
							offenseCount++ ;							
						}
					}
				}
			}

			double seriousnessIndex = 0.0;

			if( offenseSeverityTotal != 0.0 && offenseCount != 0.0 )
			{
				seriousnessIndex = offenseSeverityTotal / offenseCount;
			}

			countTotalMiddemenaorAB = countTotalMisdemeanorA + countTotalMisdemeanorB;
			totalStatusCO = countTotalCityOrd + countTotalStatus;
			seriousnessIndex = Math.round( seriousnessIndex * 10.0 ) / 10.0;
			
			aboveMisDeamA = countTotalMisdemeanorA + countFelony1 + countFelony2 
					+ countFelony3 + countFelonyCapital + countFelonyStateJail;

			resp.setJuvenileId( aJuvNumber );
			resp.setSeriousnessIndex( Double.toString( seriousnessIndex ) );
			resp.setTotalCapitalFelony( Integer.toString( countFelonyCapital ) );
			resp.setTotalClassAB( Integer.toString( countTotalMiddemenaorAB ) );
			resp.setTotalClassC( Integer.toString( countTotalMisdemeanorC ) );
			resp.setTotalFelony1( Integer.toString( countFelony1 ) );
			resp.setTotalFelony2( Integer.toString( countFelony2 ) );
			resp.setTotalFelony3( Integer.toString( countFelony3 ) );
			resp.setTotalLevel( Long.toString( Math.round( seriousnessIndex + addtlCharges ) ) );
			
			resp.setTotalReferralsHistory( Integer.toString(  
					countFelonyCapital + countFelony1 + countFelony2 + countFelony3 
					+ countFelonyStateJail + countTotalMisdemeanorC 
					+ countTotalMiddemenaorAB + countTotalCityOrd ) );
			
			resp.setTotalOffenses( Integer.toString( 
					countTotalMisdemeanorC + aboveMisDeamA + countViolationOfProbations ) );
			
			resp.setTotalStatusCO( Integer.toString( totalStatusCO ) );
			resp.setTotalStateJailFelony( Integer.toString( countFelonyStateJail ) );
			resp.setAddtlCharges( Integer.toString( addtlCharges ) );
			resp.setAgeFirstReferred( Integer.toString( age ) );
		}
		
		return resp;
	}
}
