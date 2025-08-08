/*
 * Created on Dec 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import messaging.casefile.GetCasefileClosingDetailsEvent;
import messaging.casefile.UpdateCasefileClosingEvent;
import messaging.casefile.UpdateCourtExitPlanEvent;
import messaging.casefile.UpdateResidentialExitPlanEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.casefile.reply.CasefileExceptionResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.facility.reply.JuvenileFacilityDetailsResponseEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.interviewinfo.GetInterviewsEvent;
import messaging.interviewinfo.reply.InterviewResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefilePetitionsEvent;
import messaging.juvenilecase.GetJuvenileCasefileReferralsEvent;
import messaging.juvenilecase.GetJuvenileCasefilesEvent;
import messaging.juvenilecase.GetReferralsByCasefileIdEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileCourtDispositionResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.reply.JJSChargeResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileInterviewInfoControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileWarrantConstants;
import naming.ProgramReferralConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCasefile;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.UIUtil;
import ui.juvenilecase.casefile.CasefileClosingReportBean;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.casefile.form.ResidentialExitPlanForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterview;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;

/**
 * @author jjose To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UIJuvenileCasefileClosingHelper
{
	private final static String PROGRAM_REF_STR = "PROGRAMREFERRALS" ;
	private final static String MONTH_DAY_YEAR_STR = "MM/dd/yyyy" ;

	/*
	 * 
	 */
	public static void setClosingInfoFROMClosingRespEvt( 
			CasefileClosingForm aForm, CasefileClosingResponseEvent aResponseEvent )
	{
		if( aForm == null || aResponseEvent == null )
		{
			return;
		}

		aForm.setClosingInfoId( aResponseEvent.getCasefileClosingInfoId() );
		aForm.setClosingComments( aResponseEvent.getClosingComments() );
		aForm.setClosingEvalution( aResponseEvent.getClosingEvaluation() );		
		aForm.setControllingReferral( aResponseEvent.getControllingReferralId() );
		
		if( aResponseEvent.getClosingEvaluation() == null || 
				aResponseEvent.getClosingEvaluation().equals( UIConstants.EMPTY_STRING ) )
		{
			aForm.setSupervisionEndDate( DateUtil.dateToString( new Date(), MONTH_DAY_YEAR_STR ) );
		}
		else
		{
			aForm.setSupervisionEndDate( DateUtil.dateToString( aResponseEvent.getSupervisionEndDate(), MONTH_DAY_YEAR_STR ) );
		}

		aForm.setSupervisionOutcomeId( aResponseEvent.getSupervisionOutcomeId() );
		if (aResponseEvent.getSupervisionOutcomeDescriptionId() != null)
		{
			aForm.setSupervsionOutcomeDescriptionId(aResponseEvent.getSupervisionOutcomeDescriptionId());
			aForm.setSupervsionOutcomeDescription(UIJuvenileCasefileClosingHelper.getSupervisionOutcomeDescCodesDescription( 
					aResponseEvent.getSupervisionOutcomeDescriptionId() ) );
		}	
		aForm.setRejectReason( aResponseEvent.getRejectReason() );
		aForm.setSelectedDispostion( aResponseEvent.getPetitionNumber() );

		aForm.setReportFileLocOnServer( aResponseEvent.getExitPlanTemplateLocation() );
		aForm.setClosingLetterGenerated( aResponseEvent.isClosingLetterGenerated() );
		aForm.setClosingPktGenerated( aResponseEvent.isClosingPktGenerated() );
	}

	/*
	 * 
	 */
	public static void setResidentialExitPlanInfoFROMClosingRespEvt( 
			ResidentialExitPlanForm aForm, CasefileClosingResponseEvent aResponseEvent )
	{
		if( aForm == null || aResponseEvent == null )
		{
			return;
		}
		
		aForm.setClosingInfoId( aResponseEvent.getCasefileClosingInfoId() );
		aForm.setExpectedReleaseDate( DateUtil.dateToString( aResponseEvent.getExpectedReleaseDate(), MONTH_DAY_YEAR_STR ) );
		aForm.setFacilityId( aResponseEvent.getFacilityId() );
		aForm.setFacilityReleaseReasonId( aResponseEvent.getFacilityReleaseReasonId() );
		aForm.setLevelOfCareId( aResponseEvent.getLevelOfCareId() );
		aForm.setPermanencyPlanId( aResponseEvent.getPermanencyPlanId() );
		aForm.setSpecialNotes( aResponseEvent.getSpecialNotes() );
	}

	/*
	 * 
	 */
	public static void getProfileReferrals( CasefileClosingForm aClosingForm, String juvenileNum )
	{
		GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent)
				EventFactory.getInstance( JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST );
		event.setJuvenileNum( juvenileNum );
		
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		dispatch.postEvent( event );
		
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
		MessageUtil.processReturnException( compositeResponse );
		Collection<JuvenileProfileReferralListResponseEvent> referrals = 
				MessageUtil.compositeToCollection( compositeResponse, JuvenileProfileReferralListResponseEvent.class );
		ArrayList myReferralsList = new ArrayList();
		if( referrals != null )
		{
			for( JuvenileProfileReferralListResponseEvent resp : referrals )
			{
				if( resp != null )
				{
					CasefileClosingForm.Refferal newRef = UIJuvenileCasefileClosingHelper.getRefferalFromProfileRefferalRespEvt( resp );
					newRef.setFinalDisposition(resp.getFinalDisposition());
					myReferralsList.add( newRef );
				}
			}
			aClosingForm.setProfileReferrals( myReferralsList );
		}
	}

	/*
	 * 
	 */
	public static void getReferrals( CasefileClosingForm aClosingForm, String juvenileNum )
	{
		GetJuvenileCasefileReferralsEvent casefileReferrelsEvent = new GetJuvenileCasefileReferralsEvent();

		casefileReferrelsEvent.setSupervisionNum( aClosingForm.getSupervisionNumber() );
		casefileReferrelsEvent.setJuvenileNum( juvenileNum );

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		dispatch.postEvent( casefileReferrelsEvent );

		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
		MessageUtil.processReturnException( compositeResponse );
		Map dataMap = MessageUtil.groupByTopic( compositeResponse );
		if( dataMap != null )
		{
			aClosingForm.setReferrals( new ArrayList() );
			aClosingForm.setActivereferralList(new ArrayList());
			List<String> filteredReferrals = new ArrayList<>();
			Collection myReferralsList = aClosingForm.getReferrals();
			Collection myactiveReferralsList = aClosingForm.getActivereferralList();
			Collection<JuvenileCasefileReferralsResponseEvent> referrals = (Collection)
					dataMap.get( PDJuvenileCaseConstants.JUVENILE_CASEFILE_REFERRAL_TOPIC );
			Collections.sort((List<JuvenileCasefileReferralsResponseEvent>) referrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferralsResponseEvent>() {
    			@Override
    			public int compare(JuvenileCasefileReferralsResponseEvent evt1, JuvenileCasefileReferralsResponseEvent evt2)
    			{
    			    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
    				//return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());/// sorting was wrong if same date
    				return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());
    			    else
    				return -1;
    			}
    		    	}));
			aClosingForm.setReferralList( referrals );
			//add active referralist too
			if( referrals != null && referrals.size() > 0 )
			{
				for( JuvenileCasefileReferralsResponseEvent responseEvent : referrals )
				{
					if( responseEvent != null  &&  responseEvent.isReferralFound() )
					{
						CasefileClosingForm.Refferal newRef = UIJuvenileCasefileClosingHelper.getRefferalFROMRefferalRespEvt( responseEvent );
						newRef.setFinalDisposition(responseEvent.getFinalDisposition());
						if ( !filteredReferrals.contains(newRef.getReferralNumber()) ) {
						    filteredReferrals.add(newRef.getReferralNumber());
						    myReferralsList.add( newRef );
						}
						
						String refNum = responseEvent.getReferralNumber();
						if(responseEvent.getRefCloseDate()==null||responseEvent.getRefCloseDate().toString()=="")//
						{
						    	Iterator<JuvenileCasefileReferralsResponseEvent> i = myactiveReferralsList.iterator();
						    	boolean hasIt=false;
							while(i.hasNext())
							{
							    JuvenileCasefileReferralsResponseEvent r = i.next();
								if( r.getReferralNumber().equals( refNum ) )
								{
								    hasIt=true;
								    break;
								}
							}
						    //if(!myactiveReferralsList.contains(responseEvent))
						    if(!hasIt)
							myactiveReferralsList.add(responseEvent);
						}  
					}
				}
			}
			aClosingForm.setReferrals( myReferralsList );
			aClosingForm.setActivereferralList(myactiveReferralsList);
		}
	}

	/*
	 * 
	 */
	public static void getInterview( CasefileClosingForm aClosingForm, String juvenileNum, String casefileId )
	{
		// get list of all interviews
		GetInterviewsEvent getInterviewsEvent = (GetInterviewsEvent)
				EventFactory.getInstance( JuvenileInterviewInfoControllerServiceNames.GETINTERVIEWS );

		getInterviewsEvent.setCasefileId( casefileId );
		getInterviewsEvent.setJuvenileId( juvenileNum );
		
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		dispatch.postEvent( getInterviewsEvent );
		
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		MessageUtil.processReturnException( response );
		ArrayList interviewsList = (ArrayList)MessageUtil.compositeToCollection( response, InterviewResponseEvent.class );
		Collections.sort( (ArrayList)interviewsList );

		aClosingForm.setInterviewList( JuvenileInterview.convertRE( interviewsList, null, null ) );
	}

	/*
	 * 
	 */
	public static UpdateCasefileClosingEvent getCasefileClosingEventFROMClosingForm( 
			CasefileClosingForm aForm, String aCaseStatus )
	{
		if( aForm == null )
		{
			return null;
		}
		
		UpdateCasefileClosingEvent anEvent = new UpdateCasefileClosingEvent();
		anEvent.setCasefileClosingInfoId( aForm.getClosingInfoId() );
		anEvent.setClosingComments( aForm.getClosingComments() );
		anEvent.setClosingEvaluation( aForm.getClosingEvalution() );
		String controlRef = "";
		if (aForm.getControllingReferral() != null){
		    controlRef = aForm.getControllingReferral();
		    if (controlRef.length() > 4) {
		    	controlRef = controlRef.substring(0,4);					    
		    }
		}
		anEvent.setControllingReferralId( controlRef );
//		anEvent.setControllingReferralId( aForm.getControllingReferral() );
		anEvent.setSupervisionEndDate( DateUtil.stringToDate( aForm.getSupervisionEndDate(), MONTH_DAY_YEAR_STR ) );
		anEvent.setSupervisionOutcomeId( aForm.getSupervisionOutcomeId() );
		anEvent.setSupervisionOutcomeDescriptionId( aForm.getSupervsionOutcomeDescriptionId() );
		anEvent.setCasefileClosingStatus( aCaseStatus );
		anEvent.setSupervisionNumber( aForm.getSupervisionNumber() );
		anEvent.setCasefileID(aForm.getSupervisionNumber());
		anEvent.setRejectionReason( aForm.getRejectReason() );
		anEvent.setJuvenileNum(aForm.getJuvenileNum());
		
		return anEvent;
	}

	/*
	 * 
	 */
	public static UpdateResidentialExitPlanEvent getResidentialEventFROMResidentialForm( ResidentialExitPlanForm aForm )
	{
		if( aForm == null )
		{
			return null;
		}

		UpdateResidentialExitPlanEvent anEvent = new UpdateResidentialExitPlanEvent();
		anEvent.setExpectedReleaseDate( UIUtil.getDateFromString( aForm.getExpectedReleaseDate(), null ) );
		anEvent.setFacilityId( aForm.getFacilityId() );
		anEvent.setFacilityReleaseReasonId( aForm.getFacilityReleaseReasonId() );
		anEvent.setLevelOfCareId( aForm.getLevelOfCareId() );
		anEvent.setPermanencyPlanId( aForm.getPermanencyPlanId() );
		anEvent.setSpecialNotes( aForm.getSpecialNotes() );
		anEvent.setCasefileClosingInfoId( aForm.getClosingInfoId() );
		
		return anEvent;
	}

	/*
	 * 
	 */
	public static UpdateCourtExitPlanEvent getUpdateCourtExitPlanEventFROMCommonAppForm( CommonAppForm aForm )
	{
		if( aForm == null )
		{
			return null;
		}

		UpdateCourtExitPlanEvent anEvent = new UpdateCourtExitPlanEvent();
		anEvent.setPetitionNumber( aForm.getSelectedDisposition() );
		anEvent.setCasefileClosingInfoId( aForm.getClosingInfoId() );
		
		return anEvent;
	}

	/*
	 * 
	 */
	public static CasefileClosingForm.Refferal getRefferalFROMRefferalRespEvt( 
			JuvenileCasefileReferralsResponseEvent aResponseEvent )
	{
		CasefileClosingForm.Refferal myReferral = null ;
		
		if( aResponseEvent != null )
		{
			myReferral = new CasefileClosingForm.Refferal();
			myReferral.setReferralNumber( aResponseEvent.getReferralNumber() );
			myReferral.setFinalDisposition(aResponseEvent.getFinalDisposition());
		}
		
		return myReferral;
	}

	/*
	 * 
	 */
	public static CasefileClosingForm.Refferal getRefferalFromProfileRefferalRespEvt( 
			JuvenileProfileReferralListResponseEvent aResponseEvent )
	{
		CasefileClosingForm.Refferal myReferral = null ;
		
		if( aResponseEvent != null )
		{
			myReferral = new CasefileClosingForm.Refferal();
			myReferral.setReferralNumber( aResponseEvent.getReferralNumber() );
			myReferral.setFinalDisposition(aResponseEvent.getFinalDisposition());
		}
		
		return myReferral;
	}

	/*
	 * 
	 */
	public static CasefileClosingForm.CasefileException getExceptionFROMExceptionRespEvt( 
			CasefileExceptionResponseEvent casefileExceptionResponseEvent )
	{
		CasefileClosingForm.CasefileException casefileException = null ;

		if( casefileExceptionResponseEvent != null )
		{
			casefileException = new CasefileClosingForm.CasefileException() ;
			casefileException.setExceptionType( casefileExceptionResponseEvent.getExceptionType() );
			casefileException.setExceptionId( casefileExceptionResponseEvent.getExceptionId() );
			casefileException.setExceptionMessage( casefileExceptionResponseEvent.getExceptionMessage() );
		}
		
		return casefileException;
	}

	/*
	 * 
	 */
	public static void setCasefileClosingReportBeanFromClosingForm( 
			CasefileClosingForm aClosingForm, CasefileClosingReportBean aReportBean )
	{
		if( aReportBean == null )
		{
			aReportBean = new CasefileClosingReportBean();
		}
		
		// aReportBean.setAssignmentDate(aClosingForm.get)
		aReportBean.setBlank( UIConstants.EMPTY_STRING );
		aReportBean.setCurrentDate( UIUtil.parseDate( new Date() ) );
		aReportBean.setSupervisionEndDate( aClosingForm.getSupervisionEndDate() );
	}

	/*
	 * 
	 */
	public static void setCasefileClosingReportBeanFromCasefileForm( 
			JuvenileCasefileForm aForm, CasefileClosingReportBean aReportBean )
	{
		if( aReportBean == null )
		{
			aReportBean = new CasefileClosingReportBean();
		}
		// aReportBean.setAssignmentDate(aClosingForm.get)
		aReportBean.setJuvenile( aForm.getJuvenileName() );
		aReportBean.setJuvenileNumber( aForm.getJuvenileNum() );
		aReportBean.setProbationOfficer( aForm.getProbationOfficerName() );
	}

	/*
	 * 
	 */
	public static void setCommonAppDispositionFROMRespEvts( 
			JuvenileCourtDispositionResponseEvent aDispRespEvent, CommonAppForm.Disposition aDisposition )
	{
		if( aDisposition != null  && aDispRespEvent != null )
		{
			aDisposition.setCourtDate_Time( aDispRespEvent.getCourtDateTimeFormatted() );
			aDisposition.setCourtNumber( aDispRespEvent.getCourtId() );
			aDisposition.setDispositionDate( aDispRespEvent.getDispositionDateFormatted() );
			aDisposition.setDispositionNumber( aDispRespEvent.getDispositionNumber() );
			aDisposition.setHearingTypeId( aDispRespEvent.getHearingTypeId() );
			aDisposition.setJudgementDate( aDispRespEvent.getJudgementDateFormatted() );
			aDisposition.setPetititionNumber( aDispRespEvent.getPetitionNum() );
		}
	}

	/*
	 * 
	 */
	public static void setCommonAppAbuseFROMJuvenileAbuseRespEvt( 
			CommonAppForm.Abuse aAbuse, JuvenileAbuseResponseEvent aAbuseRespEvent )
	{
		if( aAbuse != null  &&  aAbuseRespEvent != null )
		{
			aAbuse.setAbuseId( aAbuseRespEvent.getAbuseId() );
			aAbuse.setAbuseLevelId( aAbuseRespEvent.getAbuseLevelCode() );
			aAbuse.setAbuseTreatment( aAbuseRespEvent.getTreatment() );
			aAbuse.setInformationBasisId( aAbuseRespEvent.getInformationBasisId() );
			aAbuse.setTraitTypeId( aAbuseRespEvent.getTraitTypeId() );
			aAbuse.setTraitType( aAbuseRespEvent.getTraitTypeName() );
			aAbuse.setEntryDate( aAbuseRespEvent.getEntryDate() );
			aAbuse.setPerpetratorName( new Name( aAbuseRespEvent.getFirstName(), 
			aAbuseRespEvent.getMiddleName(), aAbuseRespEvent.getLastName() ) );
			aAbuse.setRelationshipToJuvenileId( aAbuseRespEvent.getRelationshipToJuvenileCode() );
		}
	}

	/*
	 * 
	 */
	public static void setCommonAppSchoolHistFROMJuvenileSchoolHistRespEvt( 
			CommonAppForm.SchoolHistory aFormObj, JuvenileSchoolHistoryResponseEvent aRespEvent )
	{
		if( aFormObj != null  &&  aRespEvent != null )
		{
			aFormObj.setAppropriateLevelId( aRespEvent.getAppropriateLevelCode() );
			aFormObj.setAppropriateLevel( aRespEvent.getAppropriateLevelDescription() );
			aFormObj.setExitTypeId( aRespEvent.getExitTypeCode() );
			aFormObj.setExitType( aRespEvent.getExitTypeDescription() );
			aFormObj.setGradeLevelId( aRespEvent.getGradeLevelCode() );
			aFormObj.setGradeLevel( aRespEvent.getGradeLevelDescription() );
			aFormObj.setLastAttendedDate( aRespEvent.getLastAttendedDate() );
			aFormObj.setSchoolDistrictId( aRespEvent.getSchoolDistrictId() );
			aFormObj.setSchoolHistoryId( aRespEvent.getSchoolHistoryId() );
			aFormObj.setSchoolId( aRespEvent.getSchoolId() );
			aFormObj.setTEASchoolNumber( aRespEvent.getTEASchoolNumber() );
			aFormObj.setSchool( aRespEvent.getSchool() );
			aFormObj.setSchoolDistrict( aRespEvent.getSchoolDistrict() );
			aFormObj.setProgramAttending( aRespEvent.getProgramAttendingDescription() );
			aFormObj.setTrauncyHistory( aRespEvent.getTruancyHistory() );
			//Bug Fix : 11239 starts
			aFormObj.setSchoolAddress(aRespEvent.getSchoolStreetName());
			aFormObj.setSchoolCity(aRespEvent.getSchoolCity());
			aFormObj.setSchoolState(aRespEvent.getSchoolState());
			aFormObj.setSchoolZip(aRespEvent.getSchoolZip());
			//Bug Fix : 11239 ends
		}
	}

	/*
	 * 
	 */
	public static String getSupervisionOutcomeCodesDescription( String passwdId )
	{
		Collection questions = CodeHelper.getSupervisionOutcomeCodes();
		Iterator<CodeResponseEvent> i = questions.iterator();
		while(i.hasNext())
		{
			CodeResponseEvent r = i.next();
			if( r.getCode().equals( passwdId ) )
			{
				return r.getDescription();
			}
		}

		return UIConstants.EMPTY_STRING;
	}
	
	/*
	 * 
	 */
	public static String getSupervisionOutcomeDescCodesDescription( String codeId )
	{
		Collection codes = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_DESC");;
		Iterator<JuvenileCodeTableChildCodesResponseEvent> i = codes.iterator();
		while(i.hasNext())
		{
			JuvenileCodeTableChildCodesResponseEvent r = i.next();
			if( r.getCode().equals( codeId ) )
			{
				return r.getDescription();
			}
		}

		return UIConstants.EMPTY_STRING;
	}
	 //bug fix for 145144
	public static String getSupervisionOutcomeRefDescCodesDescription( String codeId )
	{
		Collection codes = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_REF_DESC");
		Iterator<JuvenileCodeTableChildCodesResponseEvent> i = codes.iterator();
		while(i.hasNext())
		{
			JuvenileCodeTableChildCodesResponseEvent r = i.next();
			if( r.getCode().equals( codeId ) )
			{
				return r.getDescription();
			}
		}

		return UIConstants.EMPTY_STRING;
	}
	//
	
	/**
	 * getFacilityClosingExceptions
	 */
	public static ArrayList<CasefileClosingForm.CasefileException> getFacilityClosingExceptions(CasefileClosingForm closingForm, ArrayList<CasefileClosingForm.CasefileException> myExceptions ){
		//Add exceptions for facility.U.S #11382
		JJSJuvenile juvenile = JJSJuvenile.find(closingForm.getJuvenileNum());
		JuvenileCasefile closingCasefile = JuvenileCasefile.find( closingForm.getSupervisionNumber());
		
		int hasMoreCasefile=0;
		boolean isCasefileAssociated= false;
		closingForm.setCasefileAssociated(false);
		closingForm.setTransferCasefileId(null);
		
		/**
		 * At casefile closing, using juvenile facility status, determine if the juvenile is currently in a facility.
		 * If JUVENILE_MASTER.FacilityStatus is not null and the juvenile does not have any other active casefiles; 
		 * generate the casefile closing exception. Display message, "Juvenile is in a facility. 
		 * Casefile cannot be closed. Contact Data Correction or your Caseload Manager."  No override.
		 * Casefile cannot be closed until the exception is resolved.
		 */
		if(juvenile.getDetentionStatusId()!=null && !juvenile.getDetentionStatusId().isEmpty()){
			//check for more than one active casefile.
			GetJuvenileCasefilesEvent getEvent = new GetJuvenileCasefilesEvent();
			getEvent.setJuvenileNum(closingForm.getJuvenileNum());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(getEvent);
			IEvent replyEvent = dispatch.getReply();
			CompositeResponse responses = (CompositeResponse) replyEvent;

			Collection<JuvenileCasefileResponseEvent> juvenileCasfileResps = MessageUtil.compositeToCollection(responses, JuvenileCasefileResponseEvent.class);
			if(juvenileCasfileResps!=null){
				Collections.sort((List<JuvenileCasefileResponseEvent>)juvenileCasfileResps,new Comparator<JuvenileCasefileResponseEvent>() {
					@Override
					public int compare(JuvenileCasefileResponseEvent evt1, JuvenileCasefileResponseEvent evt2) {
						if(evt1.getSequenceNum()!=null && evt2.getSequenceNum()!=null )
							return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
						else
							return -1;
					}
				});
				JuvenileDetentionFacilitiesResponseEvent detentionRespEvent = JuvenileFacilityHelper.getInFacilityDetails(closingForm.getJuvenileNum());
				if(detentionRespEvent!=null && detentionRespEvent.getCurrentSupervisionNum()!=null && closingForm.getSupervisionNumber().equals(detentionRespEvent.getCurrentSupervisionNum())){
					//If JUVENILE_MASTER.FacilityStatus is populated and the current referral is associated to one or more active casefiles other than the current casefile, JIMS2 will re-assign the facility admission/detention record.  Allow user to continue with Closing Process.
					Iterator<JuvenileCasefileResponseEvent> juvenileCasfileRespItr = juvenileCasfileResps.iterator();
					while(juvenileCasfileRespItr.hasNext()){
						JuvenileCasefileResponseEvent juvCasefileResponse = juvenileCasfileRespItr.next();
							//check for other casefiles.
							if(juvCasefileResponse!=null && juvCasefileResponse.getSupervisionNum()!=null && UIConstants.CASEFILE_CASE_STATUS_ACTIVE.equals(juvCasefileResponse.getCaseStatusId())
									&& !closingForm.getSupervisionNumber().equals(juvCasefileResponse.getSupervisionNum())&& Integer.valueOf(juvCasefileResponse.getSequenceNum()) > Integer.valueOf(closingCasefile.getSequenceNumber())){
								hasMoreCasefile++;
								List<JuvenileCasefileReferralResponseEvent> casefileRespEvts = getReferralsByCasefileId(closingForm.getJuvenileNum(),juvCasefileResponse.getSupervisionNum());
								if(casefileRespEvts!=null){
									Iterator<JuvenileCasefileReferralResponseEvent> casefilesIter = casefileRespEvts.iterator();
									 while(casefilesIter.hasNext()) //for each referral get the referral details.
									 {
										 JuvenileCasefileReferralResponseEvent casefileReferralResp = casefilesIter.next();
										 if(casefileReferralResp!=null)
								    	 {
											 if(casefileReferralResp.getReferralNumber().equals(detentionRespEvent.getCurrentReferral())){
												isCasefileAssociated = true;
												closingForm.setCasefileAssociated(true);
												closingForm.setTransferCasefileId(juvCasefileResponse.getSupervisionNum());
												break;
											 }
								    	 }
									 } //while(2)
								}
							}
							if(isCasefileAssociated){
								break;
							}
					}//while(1)
					/**
					 * At casefile closing, using juvenile facility status, determine if the juvenile is currently in a facility. 
					 * If JUVENILE_MASTER.FacilityStatus is not null and the juvenile does not have any other active casefiles; generate the casefile closing exception.  
					 * Display message, "Juvenile is in a facility. Casefile cannot be closed. Contact Data Correction or your Caseload Manager."  
					 */
					if(hasMoreCasefile==0){
						CasefileClosingForm.CasefileException casefileException = new CasefileClosingForm.CasefileException();
						casefileException.setExceptionType(PDJuvenileCaseConstants.JUVENILE_IN_FACILITY);
						casefileException.setExceptionId("FacilityClosingException");
						casefileException.setExceptionMessage("Juvenile in a facility. Casefile cannot be closed. Contact Data Corrections or your Caseload Manager.");
						myExceptions.add( casefileException );
						closingForm.setHasJuvenileInFacility(true);
					}else{
						/**
						 * Referral associated to the juvenile's most recent admission record is not on any other active casefile. Casefile cannot be closed.
						 *  Please contact Data Corrections or your Caseload Manager."  
						 *  Do not allow user to complete process.  Casefile cannot be closed until the exception is resolved. No override. 
						 */
						if(!isCasefileAssociated){
							CasefileClosingForm.CasefileException casefileException = new CasefileClosingForm.CasefileException();
							casefileException.setExceptionType(PDJuvenileCaseConstants.JUVENILE_REFFERAL_NOT_ASSOCIATED);
							casefileException.setExceptionId("FacilityClosingException");
							casefileException.setExceptionMessage("Referral associated to the juvenile's most recent admission record is not on any other active casefile. Casefile cannot be closed. Please contact Data Corrections or your Caseload Manager.");
							myExceptions.add( casefileException );
							closingForm.setHasJuvenileInFacility(true);
						}
					}
				}
			} //	// if no association found.
		} // indetention check
		return myExceptions;
	}
	
	
	
	/*
	 * 
	 */
	public static void getProgramReferralExceptions( CasefileClosingForm casefileForm, ArrayList exceptions )
	{
		List<ProgramReferralResponseEvent> referrals = 
				UIProgramReferralHelper.getJuvenileProgramReferrals( casefileForm.getJuvenileNum(), casefileForm.getSupervisionNumber() );
		if( referrals != null )
		{
			casefileForm.setProgramReferrals( referrals );
		}

		if( referrals != null )
		{
			CasefileClosingForm.CasefileException caseFileException;
			String str = UIConstants.EMPTY_STRING ;
			String substatus = UIConstants.EMPTY_STRING;
			for( ProgramReferralResponseEvent progRef : referrals )
			{
				str = progRef.getReferralStatusCd() ;
				substatus = progRef.getReferralSubStatusCd();
				
				if( ! StringUtils.equalsIgnoreCase( str, ProgramReferralConstants.CLOSED ) )
				{ // status is not closed
					caseFileException = new CasefileClosingForm.CasefileException();
					caseFileException.setExceptionId( progRef.getReferralId() );
					caseFileException.setExceptionType( PROGRAM_REF_STR );
					caseFileException.setExceptionMessage( UIConstants.EMPTY_STRING );
					casefileForm.setHasActiveProgramReferral(true);
					exceptions.add( caseFileException );
				}
				else if( StringUtils.equalsIgnoreCase( str, ProgramReferralConstants.CLOSED ) )
				{ // it's closed - outcome is only required for substatus of completed
					str = progRef.getOutComeCd() ;
					if(( str == null || str.trim().length() == 0 ) &&
	 				(StringUtils.equalsIgnoreCase( substatus, ProgramReferralConstants.COMPLETED )))
					{
						caseFileException = new CasefileClosingForm.CasefileException();
						caseFileException.setExceptionId( progRef.getReferralId() );
						caseFileException.setExceptionType( PROGRAM_REF_STR );
						caseFileException.setExceptionMessage( "- Outcome needs to be updated." );
						casefileForm.setHasActiveProgramReferral(true);
						exceptions.add( caseFileException );
					}
					else if( progRef.getEndDate() == null )
					{
						caseFileException = new CasefileClosingForm.CasefileException();
						caseFileException.setExceptionId( progRef.getReferralId() );
						caseFileException.setExceptionType( PROGRAM_REF_STR );
						caseFileException.setExceptionMessage( "- End Date needs to be updated." );
						casefileForm.setHasActiveProgramReferral(true);
						exceptions.add( caseFileException );
					}
				}
			} // forEach in referrals
		}
	}

	/*
	 * 
	 */
	public static Collection getOffenses( String juvenileNum, String referralNum )
	{
		GetJuvenileCasefileOffensesEvent off = (GetJuvenileCasefileOffensesEvent)
				EventFactory.getInstance( JuvenileReferralControllerServiceNames.GETJUVENILECASEFILEOFFENSES );
		off.setJuvenileNum( juvenileNum );
		off.setReferralNum( referralNum );

		IDispatch dispatch1 = EventManager.getSharedInstance( EventManager.REQUEST );
		dispatch1.postEvent( off );
		CompositeResponse response1 = (CompositeResponse)dispatch1.getReply();

		Map map1 = MessageUtil.groupByTopic( response1 );
		Collection<JJSOffenseResponseEvent> offenses = (Collection)map1.get( PDJuvenileCaseConstants.JUVENILE_OFFENSES_TOPIC );
		if( offenses != null  &&  !offenses.isEmpty() )
		{
			for( JJSOffenseResponseEvent offRE : offenses )
			{
				offRE.setReferralAndCategoryDescription( 
						new StringBuilder(offRE.getReferralNum()).append(" - ").append( offRE.getCatagory()).toString() ) ;
			}
		}
		
		return offenses;
	}

	/*
	 * Get the max severity level record for each Referral
	 */
	public static void getMaxSeverityOffense( 
			Collection<CasefileClosingForm.Refferal> referralList, String juvenileNum, boolean collectOnlyPetitions )
	{
		// get petition with max severity level for each referral
		if( referralList != null )
		{
			for( CasefileClosingForm.Refferal referral : referralList )
			{
				// get petitions
				GetJuvenileCasefilePetitionsEvent pet = (GetJuvenileCasefilePetitionsEvent)
						EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEPETITIONS );

				pet.setJuvenileNum( juvenileNum );
				pet.setReferralNum( referral.getReferralNumber() );
				IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
				dispatch.postEvent( pet );
				CompositeResponse response = (CompositeResponse)dispatch.getReply();
				
				Map map = MessageUtil.groupByTopic( response );
				Collection<JJSChargeResponseEvent> petitions = (Collection)map.get( 
						PDJuvenileWarrantConstants.JJS_CHARGE_EVENT_TOPIC );
				int count = 0;
				if( petitions != null )
				{
					HashMap petitionsMap = new HashMap();
					Iterator<JJSChargeResponseEvent> petIter = petitions.iterator();
					while(petIter.hasNext())
					{
						JJSChargeResponseEvent chgResp = petIter.next();
						if( count == 0 &&  ! petIter.hasNext() )
						{
							String referralNum = referral.getReferralNumber();
							if( chgResp.getLevelDegree() != null && (chgResp.getLevelDegree().length() > 0) )
							{
								referral.setReferralNumber( 
										new StringBuffer(referralNum).append( " - ").append(chgResp.getLevelDegree()).toString() );
							}
							break;
						}
						if( petitionsMap.isEmpty() || 
								!petitionsMap.containsKey( chgResp.getLevelDegree() ) )
						{
							petitionsMap.put( chgResp.getLevelDegree(), chgResp );
						}
						else
						{
							JJSChargeResponseEvent tempResp = (JJSChargeResponseEvent)
									petitionsMap.get( chgResp.getLevelDegree() );

							if( chgResp.getPetitionDate().compareTo(tempResp.getPetitionDate()) > 0 )
							{
								petitionsMap.remove( tempResp.getLevelDegree() );
								petitionsMap.put( chgResp.getLevelDegree(), chgResp );
							}
						}
						++count ;
					} // while

					if( count != 0 )
					{
						referral.setReferralNumber( getMaxSeverity( petitionsMap, referral.getReferralNumber() ) );
					}
				} 
				else if( ! collectOnlyPetitions )
				{
					// get the offenses
					count = 0;
					Collection offenses = UIJuvenileCasefileClosingHelper.getOffenses( 
							juvenileNum, referral.getReferralNumber() );
					HashMap offenseMap = new HashMap();
					if( offenses != null )
					{
						Iterator offIter = offenses.iterator();
						while(offIter.hasNext())
						{
							JJSOffenseResponseEvent offResp = (JJSOffenseResponseEvent)offIter.next();
							if( count == 0 &&  ! offIter.hasNext() )
							{
								String referralNum = referral.getReferralNumber();
								referral.setReferralNumber( referralNum + " - " + offResp.getCatagory() );
								break;
							}
							if( offenseMap == null || 
									offenseMap.isEmpty() || 
									!offenseMap.containsKey( offResp.getCatagory() ) )
							{
								offenseMap.put( offResp.getCatagory(), offResp );
							}
							else
							{
								JJSOffenseResponseEvent tempResp = (JJSOffenseResponseEvent)
										offenseMap.get( offResp.getCatagory() );

								if( offResp.getOffenseDate().compareTo( tempResp.getOffenseDate() ) > 0 )
								{
									offenseMap.remove( tempResp.getCatagory() );
									offenseMap.put( offResp.getCatagory(), offResp );
								}
							}
							++count ;
						} // while
					}

					if( count != 0 )
					{
						referral.setReferralNumber( getMaxSeverity( offenseMap, referral.getReferralNumber() ) );
					}
				}
			} // forEach in referral
		}
			
		Collections.sort( (List)referralList );
	}

	/*
	 * Get the max severity level record for each Referral
	 */
	public static JuvenileCasefileReferralsResponseEvent getMaxSeverityForEachReferral( 
			Collection<JuvenileCasefileReferralsResponseEvent> referralList,
			 String juvenileNum )
	{
		// get petition with max severity level for each referral
		if( referralList != null )
		{
			for( JuvenileCasefileReferralsResponseEvent referral : referralList )
			{
				// get petitions
				GetJuvenileCasefilePetitionsEvent pet = (GetJuvenileCasefilePetitionsEvent)
						EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEPETITIONS );

				pet.setJuvenileNum( juvenileNum );
				pet.setReferralNum( referral.getReferralNumber() );
				IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
				dispatch.postEvent( pet );
				CompositeResponse response = (CompositeResponse)dispatch.getReply();
				
				Map map = MessageUtil.groupByTopic( response );
				Collection<JJSChargeResponseEvent> petitions = (Collection)map.get( 
						PDJuvenileWarrantConstants.JJS_CHARGE_EVENT_TOPIC );
				if( petitions != null )
				{
					HashMap petitionsMap = new HashMap();
					Iterator<JJSChargeResponseEvent> petIter = petitions.iterator();
					
					if( petitions.size() == 1 )
					{
						JJSChargeResponseEvent chgResp = petIter.next();

						if( chgResp.getLevelDegree() != null && 
								(chgResp.getLevelDegree().length() > 0) )
						{
							referral.setReferralNumberWithSeverity( referral.getReferralNumber() ) ;
							petitionsMap.put( chgResp.getLevelDegree(), chgResp );
						}
					}
					else
					{ // multiple petitions for this referral
						while(petIter.hasNext())
						{
							JJSChargeResponseEvent chgResp = petIter.next();
							if( ! petitionsMap.containsKey( chgResp.getLevelDegree() ) )
							{
								petitionsMap.put( chgResp.getLevelDegree(), chgResp );
							}
							else
							{ // possibly rule out the duplicate 
								JJSChargeResponseEvent tempResp = (JJSChargeResponseEvent)
										petitionsMap.get( chgResp.getLevelDegree() );

								if( chgResp.getPetitionDate().compareTo(tempResp.getPetitionDate()) < 0 )
								{ // current charge is newer than the charge stored in the map
									petitionsMap.remove( tempResp.getLevelDegree() );
									petitionsMap.put( chgResp.getLevelDegree(), chgResp );
								}
							}
						} // while
					}

					if( petitionsMap.size() > 0 )
					{
						String str = getMaxSeverity( petitionsMap, referral.getReferralNumber() ) ;
						referral.setReferralNumberWithSeverity( str );
					}
				} 
				else
				{ // get the offenses
					Collection offenses = UIJuvenileCasefileClosingHelper.getOffenses( 
							juvenileNum, referral.getReferralNumber() );
					HashMap offenseMap = new HashMap();
					if( offenses != null )
					{
						Iterator offIter = offenses.iterator();
						
						if( offenses.size() == 1 )
						{
							JJSOffenseResponseEvent offResp = (JJSOffenseResponseEvent)offIter.next();
							referral.setReferralNumber( referral.getReferralNumber() ) ;

							offenseMap.put( offResp.getCatagory(), offResp ); 
						}
						else
						{ // multiple offenses for this referral
							while(offIter.hasNext())
							{
								JJSOffenseResponseEvent offResp = (JJSOffenseResponseEvent)offIter.next();

								if( ! offenseMap.containsKey( offResp.getCatagory() ) )
								{
									offenseMap.put( offResp.getCatagory(), offResp );
								}
								else
								{ // possibly rule out the duplicate 
									JJSOffenseResponseEvent tempResp = (JJSOffenseResponseEvent)
											offenseMap.get( offResp.getCatagory() );

									if( offResp.getOffenseDate().compareTo( tempResp.getOffenseDate() ) < 0 )
									{
										offenseMap.remove( tempResp.getCatagory() );
										offenseMap.put( offResp.getCatagory(), offResp );
									}
								}
							} // while
						}

						if( offenseMap.size() > 0 )
						{
							String str = getMaxSeverity( offenseMap, referral.getReferralNumber() ) ;
							referral.setReferralNumberWithSeverity( str );
						}
					}
				} // else just offenses, no petitions
			} // forEach in referral
		}
			
		JuvenileCasefileReferralsResponseEvent vcRef = (JuvenileCasefileReferralsResponseEvent)
				((ArrayList)referralList).get( 0 ) ;
		
		return( vcRef ) ;
	}

	/*
	 * 
	 */
	public static String getMaxSeverity( HashMap offenseMap, String referralNum )
	{
		String toAppend = null ;
		
		if( offenseMap.size() != 0 )
		{
			if( offenseMap.get( UIConstants.OFFENSE_LEVEL_DEGREE_CF ) != null )
			{
				toAppend = "CF";
			}
			else if( offenseMap.get( UIConstants.OFFENSE_LEVEL_DEGREE_F1 ) != null )
			{
				toAppend = "F1";
			}
			else if( offenseMap.get( UIConstants.OFFENSE_LEVEL_DEGREE_F2 ) != null )
			{
				toAppend = "F2";
			}
			else if( offenseMap.get( UIConstants.OFFENSE_LEVEL_DEGREE_F3 ) != null )
			{
				toAppend = "F3";
			}
			else if( offenseMap.get( UIConstants.OFFENSE_LEVEL_DEGREE_JF ) != null )
			{
				toAppend = "JF";
			}
			else if( offenseMap.get( UIConstants.OFFENSE_LEVEL_DEGREE_MA ) != null )
			{
				toAppend = "MA";
			}
			else if( offenseMap.get( UIConstants.OFFENSE_LEVEL_DEGREE_MB ) != null )
			{
				toAppend = "MB";
			}
			else if( offenseMap.get( UIConstants.OFFENSE_LEVEL_DEGREE_MC ) != null )
			{
				toAppend = "MC";
			}
			else if( offenseMap.get( UIConstants.OFFENSE_LEVEL_DEGREE_CO ) != null )
			{
				toAppend = "CO)";
			}
			else if( offenseMap.get( UIConstants.OFFENSE_LEVEL_DEGREE_SO ) != null )
			{
				toAppend = "SO";
			}
			else if( offenseMap.get( UIConstants.OFFENSE_LEVEL_DEGREE_AC ) != null )
			{
				toAppend = "AC";
			}
		}
		
		if( toAppend != null )
		{ // string's been set, return the complete severity string
			return( new StringBuffer(referralNum).append(" - ").append(toAppend).toString() ) ;
		}
		
		return null;
	}

	/*
	 * return a collection of the referrals with the most serious offenses
	 * for the current casefile
	 */
	public static Collection getMostSeriousOffenseForCurrentCasefile( 
			String juvenileNum, JuvenileCasefileForm casefileForm, CommonAppForm commonAppForm )
	{
		Collection<JuvenileCasefileSearchResponseEvent> casefiles = null ;

		{ SearchJuvenileCasefilesEvent search = (SearchJuvenileCasefilesEvent)
			EventFactory.getInstance( JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES );
	
			search.setSearchType( PDJuvenileCaseConstants.SEARCH_SUPERVISION_NUMBER ) ;
			search.setSupervisionNum( casefileForm.getSupervisionNum() ) ;
			
			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
			dispatch.postEvent( search );
			
			CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
			MessageUtil.processReturnException( compositeResponse );
			casefiles = MessageUtil.compositeToCollection( 
					compositeResponse, JuvenileCasefileSearchResponseEvent.class );
		}
		
		Collection referralBag = new ArrayList();
		if( casefiles != null )
		{ 
			String caseStatus = UIConstants.EMPTY_STRING ;
			
			JuvenileCasefileSearchResponseEvent tCasefile = (JuvenileCasefileSearchResponseEvent)
					((ArrayList)casefiles).get(0);

			caseStatus = tCasefile.getCaseStatus() ;
			
			if( caseStatus.equals( UIConstants.CASEFILE_CASE_STATUS_ACTIVE_DESCRIPTION ) )
			{
				// get all the referrals for this casefile
				ArrayList<JuvenileCasefileReferralsResponseEvent> referrals = 
						(ArrayList)UIJuvenileCaseworkHelper.fetchJuvenileCasefileReferralsList( 
								tCasefile.getSupervisionNum(), juvenileNum );
				
				JuvenileCasefileReferralsResponseEvent jcRef = getMaxSeverityForEachReferral( referrals, juvenileNum ) ;

				referralBag.addAll( referrals ) ;
				commonAppForm.setCasefilePostAdjCommOrRes( true ) ;
			}
			else if( ! caseStatus.equals( UIConstants.CASEFILE_CASE_STATUS_ACTIVE ) &&
							! caseStatus.equals( UIConstants.CASEFILE_CASE_STATUS_PENDING ))
			{
				// if in a closing state, the controlling referral and 
				// current offense are inherited from the closing process
			}
		}

		Collections.sort( (List)referralBag ) ;
		
		return referralBag;
	}

	/*
	 * return a collection of the offenses from all 
	 * Active Post-Adj (Community or Residential) Casefiles 
	 */
	public static Collection getMostSeriousOffensesForAllCasefiles( 
			String juvenileNum, JuvenileCasefileForm casefileForm, CommonAppForm commonAppForm )
	{
		Collection<JuvenileCasefileSearchResponseEvent> casefiles = null ;
		
		{ SearchJuvenileCasefilesEvent search = (SearchJuvenileCasefilesEvent)
			EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);

			search.setJuvenileNum( juvenileNum );
			search.setSearchType( PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER ) ;
	
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(search);	
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			casefiles = MessageUtil.compositeToCollection(
						compositeResponse,JuvenileCasefileSearchResponseEvent.class);
		}

		Collection offenseBag = new ArrayList() ;
		if( casefiles != null )
		{ 
			String caseStatus = UIConstants.EMPTY_STRING ; 
			
			for( JuvenileCasefileSearchResponseEvent tCasefile : casefiles )
			{
				caseStatus = tCasefile.getCaseStatus() ;
				
				
				// get all the referrals for this casefile
				ArrayList<JuvenileCasefileReferralsResponseEvent> referrals = 
						(ArrayList)UIJuvenileCaseworkHelper.fetchJuvenileCasefileReferralsList( 
								tCasefile.getSupervisionNum(), juvenileNum );
				
				JuvenileCasefileReferralsResponseEvent jcRef = getMaxSeverityForEachReferral( referrals, juvenileNum ) ;

				/* for each referral, get all its offenses
				 */
				for( JuvenileCasefileReferralsResponseEvent jref : referrals )
				{
					if( jref.getOffenses() != null )
					{
						//offenseBag.addAll( jref.getOffenses() ) ;
						offenseBag.addAll( jref.getOffenseResponseEvents() ) ;

					}
				}
				
			} // for casefiles
		}

		// Collections.sort( (List)offenseBag ) ;
		
		return( offenseBag ) ;
	}
	
	/**
	 * Added for ER 11046
	 * @param aForm
	 * @param aRequest
	 */
	public static void saveCommonAppDetails(ActionForm aForm,HttpServletRequest aRequest){
		CommonAppForm myCommonAppForm = (CommonAppForm) aForm;
		JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		CasefileClosingForm myClosingForm = (CasefileClosingForm) UIJuvenileHelper.getJuvenileCasefileClosingForm(aRequest,casefileForm.getSupervisionNum());
		myCommonAppForm.setCasefileId(casefileForm.getSupervisionNum()); //#bug fix 35750
		myCommonAppForm.setAction(UIConstants.CONFIRM_UPDATE);
		myCommonAppForm.setSecondaryAction("");
		myCommonAppForm.setSelectedValue("");

		UpdateCourtExitPlanEvent myEvent = UIJuvenileCasefileClosingHelper.getUpdateCourtExitPlanEventFROMCommonAppForm(myCommonAppForm);

		myEvent.setPrevCasefileClosingInfoId(myClosingForm.getClosingInfoId());
		myEvent.setExitPlanTemplateLocation(myCommonAppForm.getPreviousReport());
		myEvent.setCasefileClosingStatus(casefileForm.getCaseStatusId());
		myEvent.setSupervisionNum(casefileForm.getSupervisionNum()); //#bug fix 35750
		myEvent.setControllingReferralId( myCommonAppForm.getSelectedControllingReferral() );
		myEvent.setResponses(UIUtil.getUpdateResponseEvtFromUIQuestionGroups(myCommonAppForm.getPreviousExitReportQuestions(), myClosingForm
						.getClosingInfoId(), myCommonAppForm.getPreviousTab()));

		CompositeResponse compositeResponse = MessageUtil.postRequest(myEvent);
		CasefileClosingResponseEvent response = (CasefileClosingResponseEvent) MessageUtil.filterComposite(compositeResponse,
						CasefileClosingResponseEvent.class);
		if (response != null && response.getCasefileClosingInfoId()!=null) {
			myCommonAppForm.setClosingInfoId(response.getCasefileClosingInfoId());
			myClosingForm.setClosingInfoId(response.getCasefileClosingInfoId());
		}
		myCommonAppForm.setExitReportFilled(true);
		myClosingForm.setSelectedDispostion(myCommonAppForm.getSelectedDisposition());
		myClosingForm.setReportFileLocOnServer("");
	}
	
	
	/*
	 * 
	 */
	public static CasefileClosingResponseEvent getCasefileClosingDetails( String supervisionNum )
	{
		GetCasefileClosingDetailsEvent casefileClosingDetailEvent = (GetCasefileClosingDetailsEvent)
				EventFactory.getInstance( JuvenileCasefileControllerServiceNames.GETCASEFILECLOSINGDETAILS );
		casefileClosingDetailEvent.setSupervisionNumber( supervisionNum );

		CompositeResponse compositeResponse = MessageUtil.postRequest( casefileClosingDetailEvent );

		CasefileClosingResponseEvent event = (CasefileClosingResponseEvent)
				MessageUtil.filterComposite( compositeResponse, CasefileClosingResponseEvent.class );
		
		return event;
	}
	
	/**
	 * getReferralsByCasefileId
	 * @param juvNum
	 * @param refNum
	 * @return
	 */
	public static  List<JuvenileCasefileReferralResponseEvent> getReferralsByCasefileId(String juvNum, String casefileId)
	{
		 GetReferralsByCasefileIdEvent casefileEvt = (GetReferralsByCasefileIdEvent)EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETREFERRALSBYCASEFILEID);
		 casefileEvt.setJuvenileNum(juvNum);
		 casefileEvt.setCaseFileId(casefileId);
		 CompositeResponse response = MessageUtil.postRequest(casefileEvt);
			
		 List<JuvenileCasefileReferralResponseEvent> casefiles = MessageUtil.compositeToList(response, JuvenileCasefileReferralResponseEvent.class);
	 	 return casefiles;
	}

}// END CLASS
