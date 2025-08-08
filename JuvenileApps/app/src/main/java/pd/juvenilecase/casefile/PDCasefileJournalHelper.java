/*
 * Created on May 07, 2008
 * @author ugopinath
 */
package pd.juvenilecase.casefile;

import java.util.Date;
import java.util.Iterator;

import messaging.calendar.GetJuvenileAttendanceEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.casefile.GetJournalEntriesEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.casefile.reply.FacilityActivityResponseEvent;
import messaging.casefile.reply.GoalJournalResponseEvent;
import messaging.casefile.reply.TraitJournalResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.mentalhealth.reply.MAYSISearchResultResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import messaging.riskanalysis.GetRiskAssessmentDetailsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.RiskAnalysisConstants;
import pd.codetable.Code;
import pd.codetable.PDCodeHelper;
import pd.codetable.criminal.JuvenileActivityTypeCode;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.interviewinfo.Interview;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIComplete;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.supervision.administerserviceprovider.Service;
import pd.supervision.calendar.ServiceEventAttendance;
import pd.supervision.calendar.ServiceEventContext;
import pd.supervision.programreferral.JuvenileProgramReferral;

public final class PDCasefileJournalHelper
{
	/**
	 * 
	 */
	private PDCasefileJournalHelper()
	{
	}

	/**
	 * @param entries
	 */
	public static void fetchActivityJournalEntries( GetJournalEntriesEvent entries )
	{
		Activity act = null;
		ActivityResponseEvent response = null ;
		
		Iterator<Activity> actIter = Activity.findAll( entries );
		while(actIter.hasNext())
		{
			act = actIter.next();
			if( act != null)
			{
				//U.S. #27342 changes.
			   JuvenileActivityTypeCode  activityTypeCode= ActivityHelper.getActivityTypeCode(act.getActivityCodeId());
			   //change made for Bug #34865
			  /* if( act != null && activityTypeCode !=null && activityTypeCode.getCategoryId()!=null  &&  !activityTypeCode.getCategoryId().equals("RES")){
				   response = act.valueObject();
				   sendResponseEvent( response );
			   }*/
			   //U.S. #27342
			   if( act != null &&  activityTypeCode !=null && activityTypeCode.getCategoryId()!=null && activityTypeCode.getCategoryId().equals("RES") && activityTypeCode.getTypeId().equalsIgnoreCase("CSM"))
			   {
					FacilityActivityResponseEvent facilityResponse = new  FacilityActivityResponseEvent();
					facilityResponse.setComments(act.getComments());
					facilityResponse.setCasefileId( act.getSupervisionNumber());
					facilityResponse.setActivityDesc(activityTypeCode.getDescription());
					facilityResponse.setActivityDate(act.getActivityDate());
					if (act.getActivityTime() != null
						&& act.getActivityTime().length() > 0) {
					    Date actTime= DateUtil.stringToDate(act.getActivityTime(), new String ("HH:mm"));
					    facilityResponse.setActivityTime(DateUtil.dateToString(actTime,new String("hh:mm a")));
					}
					//code below (if loop) added for Bug 51303, createdBy Missing in Journal
					if(act.getCreateUserID() != null){
					    facilityResponse.setCreateUserID(act.getCreateUserID());
					    OfficerProfile officerProfile = OfficerProfile.findByLogonId( act.getCreateUserID() );
						
						if ( officerProfile != null ){
						    OfficerProfileResponseEvent officerProfileResponseEvent = PDOfficerProfileHelper.getBasicOfficerProfileResponseEvent(officerProfile);
						    if ( officerProfileResponseEvent != null ) {
							facilityResponse.setCreatedBy(officerProfileResponseEvent.getFormattedName());
						    }
						}
					   // String fullName = PDContactHelper.getUserProfileName(act.getCreateUserID());
					   // facilityResponse.setCreatedBy(fullName);
					}
					
					sendResponseEvent( facilityResponse );
			   }
			   else
			   {
				   response = act.valueObject();
				   sendResponseEvent( response );
			   }
			}
		}
	}

	/**
	 * @param entries
	 */
	public static void fetchRiskAnalJournalEntries( GetJournalEntriesEvent entries )
	{
		RiskAnalysis risk = null ;
		String type = "" ;

		Iterator<RiskAnalysis> riskAnalysis = RiskAnalysis.findAll( entries );
		while(riskAnalysis.hasNext())
		{
			risk = riskAnalysis.next();
			type = risk.getAssessmentType().trim() ;
			if(entries.getJournalCategoryCd().equals(PDJuvenileCaseConstants.JOURNAL_CASE_REVIEW_SUMMARY))
			{
				if( type.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL ) || 
						type.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_COMMUNITY ) || 
						(type.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_PROGRESS ) && !entries.getSupervisionCategory().equalsIgnoreCase("AR"))||
						type.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_GANG ) ||
						(type.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS ) && entries.getSupervisionCategory().equalsIgnoreCase("AR")))
				{
					GetRiskAssessmentDetailsEvent riskAssessDetails = new GetRiskAssessmentDetailsEvent();
					riskAssessDetails.setAssessmentID( risk.getOID() );
					riskAssessDetails.setAssessmentType( risk.getAssessmentType() );
					PDRiskAnalysisHelper.retrieveRiskAnalysisDetails( riskAssessDetails );
				}
			}
			else
			{
				if( type.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL ) || 
						type.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_COMMUNITY ) || 
						type.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_PROGRESS )||
						type.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_GANG ) ||
						type.equalsIgnoreCase( RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS ))
				{
					GetRiskAssessmentDetailsEvent riskAssessDetails = new GetRiskAssessmentDetailsEvent();
					riskAssessDetails.setAssessmentID( risk.getOID() );
					riskAssessDetails.setAssessmentType( risk.getAssessmentType() );
					PDRiskAnalysisHelper.retrieveRiskAnalysisDetails( riskAssessDetails );
				}
			}
		}
	}

	/**
	 * @param entries
	 */
	public static void fetchClosingJournalEntries( GetJournalEntriesEvent entries )
	{
		String closingStatus = "" ;
		CasefileClosingInfo closeInfo = null ;
		
		Iterator<CasefileClosingInfo> closeIter = CasefileClosingInfo.findAll( entries );
		while(closeIter.hasNext())
		{
			closeInfo = closeIter.next();
			closingStatus = closeInfo.getCasefileClosingStatus() ;
			
			//<KISHORE>JIMS200058848 : MJCW - Journal Not displaying Closing Info
			// Closing information should be displayed for every status but pending and active.
			if( closingStatus != null && 
				(closingStatus.equalsIgnoreCase( PDJuvenileCaseConstants.CASESTATUS_PENDINGCLOSE) || 
				 closingStatus.equalsIgnoreCase( PDJuvenileCaseConstants.CASESTATUS_CLOSINGSUBMITTED ) || 
				 closingStatus.equalsIgnoreCase( PDJuvenileCaseConstants.CASESTATUS_CLOSINGAPPROVED ) ||
				 closingStatus.equalsIgnoreCase( PDJuvenileCaseConstants.CASE_STATUS_CLOSE ) ))
			{
				CasefileClosingResponseEvent resp = new CasefileClosingResponseEvent();
				resp.setExpectedReleaseDate(closeInfo.getExpectedReleaseDate());
				resp.setSupervisionEndDate(closeInfo.getSupervisionEndDate());
				resp.setClosingEvaluation( closeInfo.getClosingEvaluation() );
				resp.setClosingComments( closeInfo.getClosingComments() );
				resp.setCasefileClosingStatus( closingStatus );
				Date aDate = new Date(closeInfo.getCreateTimestamp().getTime());
				resp.setCreateDate(aDate);
				String userLogonId = closeInfo.getCreateUserID();
				 OfficerProfile officerProfile = OfficerProfile.findByLogonId( userLogonId );
					
					if ( officerProfile != null ){
					    OfficerProfileResponseEvent officerProfileResponseEvent = PDOfficerProfileHelper.getBasicOfficerProfileResponseEvent(officerProfile);
					    if ( officerProfileResponseEvent != null ) {
						resp.setCreatedBy(officerProfileResponseEvent.getFormattedName());
					    }
					}
				/*if (userLogonId != null) {
					String fullName = PDContactHelper.getUserProfileName(userLogonId);
					resp.setCreatedBy(fullName);
				}*/
				sendResponseEvent( resp );
			}
		}
	}

	/**
	 * @param entries
	 */
	public static void fetchGoalJournalEntries( GetJournalEntriesEvent entries )
	{
		GoalJournal goal = null ;
		
		Iterator<GoalJournal> ite = GoalJournal.findAll( entries );
		while(ite.hasNext())
		{
			goal = ite.next();
			GoalJournalResponseEvent goalResponse = new GoalJournalResponseEvent();
			goalResponse.setGoalID( goal.getCasegoalId() );
			goalResponse.setGoalDescription( goal.getDescription() );
			goalResponse.setEndRecommendations( goal.getEndRecommendation() );
			goalResponse.setProgressNotes( goal.getProgressNotes() );
			goalResponse.setEntryDate( goal.getEntryDate() );
			String userLogonId = goal.getCreateUserID();
			OfficerProfile officerProfile = OfficerProfile.findByLogonId( userLogonId );
				
			if ( officerProfile != null ){
			    OfficerProfileResponseEvent officerProfileResponseEvent = PDOfficerProfileHelper.getBasicOfficerProfileResponseEvent(officerProfile);
			    if ( officerProfileResponseEvent != null ) {
				goalResponse.setCreatedBy(officerProfileResponseEvent.getFormattedName());
			    }
			}
			sendResponseEvent( goalResponse );
		}
	}

	/**
	 * @param entries
	 */
	public static void fetchTraitJournalEntries( GetJournalEntriesEvent entries )
	{
		TraitJournal trait = null ;
		Code myStat = null ;
		
		Iterator<TraitJournal> ite = TraitJournal.findAll( entries );
		while(ite.hasNext())
		{
			trait = ite.next();
			TraitJournalResponseEvent traitResponse = new TraitJournalResponseEvent();
			traitResponse.setTraitId( trait.getTraitsId() );
			traitResponse.setTraitName( trait.getTraitName() );

			myStat = trait.getStatus();
			traitResponse.setTraitStatus( myStat.getDescription() );
			traitResponse.setComments( trait.getComments() );
			traitResponse.setTraitDate( trait.getCreatedDate() );
			String userLogonId = trait.getCreateUserID();
			OfficerProfile officerProfile = OfficerProfile.findByLogonId( userLogonId );
			
			if ( officerProfile != null ){
			    OfficerProfileResponseEvent officerProfileResponseEvent = PDOfficerProfileHelper.getBasicOfficerProfileResponseEvent(officerProfile);
			    if ( officerProfileResponseEvent != null ) {
				traitResponse.setCreatedBy(officerProfileResponseEvent.getFormattedName());
			    }
			}
			sendResponseEvent( traitResponse );
		}
	}

	/**
	 * @param entries
	 */
	public static void fetchProgReferralJournalEntries( GetJournalEntriesEvent entries )
	{
		JuvenileProgramReferral programReferral = null ;
		ProgramReferralResponseEvent resp = null ;
		
		Iterator<JuvenileProgramReferral> progRefIter = JuvenileProgramReferral.findAll( entries );
		while(progRefIter.hasNext())
		{
			programReferral = progRefIter.next();
			resp = programReferral.getValueObject( true );
			sendResponseEvent( resp );
		}
	}
	
	/**
	 * @param entries
	 */
	public static void fetchProgReferralSummaryJournalEntries( GetJournalEntriesEvent entries )
	{
		// for summary journal do not filter with the provided dates
		// create new event to override original event
		GetJournalEntriesEvent tempEntriesEvent = new  GetJournalEntriesEvent();
		tempEntriesEvent.setJuvenileId(entries.getJuvenileId());	
		tempEntriesEvent.setCasefileId(entries.getCasefileId());	
		JuvenileProgramReferral programReferral = null ;
		ProgramReferralResponseEvent resp = null ;
		
		Iterator<JuvenileProgramReferral> progRefIter = JuvenileProgramReferral.findAll( tempEntriesEvent );
		while(progRefIter.hasNext())
		{
			programReferral = progRefIter.next();
			resp = programReferral.getValueObject( true );
			sendResponseEvent( resp );
		}
	}
	
	/**
	 * @param entries
	 */
	public static void fetchMaysi2JournalEntries( GetJournalEntriesEvent entries )
	{
		// retrieve maysi records based on juv #
		Iterator<JuvenileMAYSIComplete> juvenileMaysiIter = JuvenileMAYSIComplete.findAll( entries );
		while(juvenileMaysiIter.hasNext()){
			JuvenileMAYSIComplete juvenileMaysi = juvenileMaysiIter.next() ;
			MAYSISearchResultResponseEvent juvenileMaysiResponseEvent = juvenileMaysi.getSearchResponseEvent();
			
			sendResponseEvent( juvenileMaysiResponseEvent );
		}
	}

	/**
	 * @param entries
	 */
	public static void fetchCalEventsJournalEntries( GetJournalEntriesEvent entries )
	{
		ServiceEventContext serviceEvent = null ;
		CalendarServiceEventResponseEvent resp = null ;
		ServiceEventAttendance eventAttendance = null ;
		String eventTypeGroup = "" ;

		Iterator<ServiceEventContext> iter = ServiceEventContext.findAll( entries );
		while(iter.hasNext())
		{
			serviceEvent = iter.next();
			resp = serviceEvent.getBasicCalendarServiceResponseEvent();

			eventTypeGroup = serviceEvent.getEventType().getGroup() ;
			if( eventTypeGroup.equalsIgnoreCase( "I" ) )
			{
				getInterviewEventsDetails( serviceEvent, resp );
			}
			else if( eventTypeGroup.equalsIgnoreCase( "P" ) )
			{
				Service service = Service.find( "" + serviceEvent.getServiceId() );
				if (serviceEvent.getServiceId() > 0) {
					resp.setServiceName( service.getServiceName() );
				}

			}

			GetJuvenileAttendanceEvent attendance = new GetJuvenileAttendanceEvent();
			attendance.setServiceEventId( serviceEvent.getServiceEventId() );
			attendance.setJuvenileId( entries.getJuvenileId() );
			
			Iterator<ServiceEventAttendance> attenIter = ServiceEventAttendance.findAll( attendance );
			if( attenIter.hasNext() )
			{
				eventAttendance = attenIter.next();
				resp.setProgressNotes( eventAttendance.getProgressNotes() );
				//resp.setJuvenileAttendanceStatus(PDCodeHelper.getCodeDescription(
						//PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS, eventAttendance.getAttendanceStatusCd()));
				resp.setJuvenileAttendanceStatus(PDCodeHelper.getCodeDescriptionByCode(
						PDCodeHelper.getCodes(PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS, false), eventAttendance.getAttendanceStatusCd()));
			}
			String userLogonId = serviceEvent.getCreateUserID();
			OfficerProfile officerProfile = OfficerProfile.findByLogonId( userLogonId );
			
			if ( officerProfile != null ){
			    OfficerProfileResponseEvent officerProfileResponseEvent = PDOfficerProfileHelper.getBasicOfficerProfileResponseEvent(officerProfile);
			    if ( officerProfileResponseEvent != null ) {
				resp.setCreatedBy(officerProfileResponseEvent.getFormattedName());
			    }
			}
			
			sendResponseEvent( resp );
		}
	}

	public static void getInterviewEventsDetails( ServiceEventContext e, 
			CalendarServiceEventResponseEvent resp )
	{
		if( e.getInterviewId() != null )
		{
			Interview interview = e.getInterview();
			resp.setInterviewId( interview.getOID() );
			if (interview.getInterviewType() != null) {
				resp.setInterviewType( interview.getInterviewType().getDescription() );				
			}
			resp.setInterviewSummaryNotes( interview.getSummaryNotes() );
		}
	}

	/**
	 * get all the values required to support the General Journal Report
	 * @param entries
	 */
	public static void getAllJournalEntries( GetJournalEntriesEvent entries )
	{
		fetchActivityJournalEntries( entries );
		fetchCalEventsJournalEntries( entries );
		fetchTraitJournalEntries( entries );
		fetchGoalJournalEntries( entries );
		fetchRiskAnalJournalEntries( entries );
		fetchProgReferralJournalEntries( entries );
		fetchClosingJournalEntries( entries );
	}
	
	/**
	 * get all the values required to support the Case Review Journal Summary Report
	 * @param entries
	 */
	public static void getCaseReviewJournalSummaryEntries( GetJournalEntriesEvent entries )
	{
		fetchActivityJournalEntries( entries );
		fetchCalEventsJournalEntries( entries );
		fetchRiskAnalJournalEntries( entries );
		fetchProgReferralSummaryJournalEntries( entries );
		fetchClosingJournalEntries( entries );
		fetchMaysi2JournalEntries ( entries );
	}

	/**
	 * @param event
	 *          IEvent
	 */
	public static void sendResponseEvent( IEvent event )
	{
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REPLY );
		dispatch.postEvent( event );
	}
}
