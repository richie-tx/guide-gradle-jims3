package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileByCasefileIdEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.productionsupport.GetProductionSupportAssociatedJuvenileEventReferralsEvent;
import messaging.productionsupport.GetProductionSupportCalendarEventContextEvent;
import messaging.productionsupport.GetProductionSupportCalendarServiceEventsEvent;
import messaging.productionsupport.GetProductionSupportJuvenileAttendanceEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralAssignmentHistoryEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralCommentsEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import messaging.programreferral.reply.ProgramAssociatedJuvenileEventReferralResponseEvent;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import messaging.programreferral.reply.ProgramReferralCommentResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import messaging.calendar.reply.CalendarEventContextResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


/**
 * @author rcarter
 * 
 * CSJUVPROGREF has child records in:
 * 		CSPRGREFCOMMENTS
 * 		CSEVENTREFERRAL
 * 		CSPROGRFASNHIST
 */

public class ReferralDeleteQueryAction extends Action {

	private Logger log = Logger.getLogger("ReferralDeleteQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
	
		
		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk!=null && clrChk.equalsIgnoreCase("Y"))
		{
			regform.clearAllResults();
			regform.setMsg("");
			return mapping.findForward("error");		
		}
		
		
		String juvprogrefId = regform.getJuvprogrefId();
		
		try {
		    Integer.parseInt(juvprogrefId);
		} catch (Exception e ){
		    regform.setMsg("You must enter a valid Program Referral ID.");
			return (mapping.findForward("error"));
		}
		//Clear the form, then reset the key value
		regform.clearAllResults();		
		regform.setJuvprogrefId(juvprogrefId);
		
		//Log the query attempt
		log.info("["+new Date()+"] ProdSupport Retrieve Query Delete Referral (LogonId: "+SecurityUIHelper.getLogonId().toUpperCase()+") - Referral Query ID: " + juvprogrefId);	
		
		/**
		 * Search for juvprogrefs
		 */
		ArrayList<ProductionSupportJuvenileProgramReferralResponseEvent> juvprogrefs = getProgramReferralsById(juvprogrefId);

		if (juvprogrefs!=null && juvprogrefs.size() > 0){
			for(ProductionSupportJuvenileProgramReferralResponseEvent event: juvprogrefs){
				if(event != null && event.getJuvenileProgramReferralId() != null){
					regform.setJuvprogrefCount(juvprogrefs.size());
					regform.setJuvprogrefs(juvprogrefs);
					break;
				}else{
					regform.setJuvprogrefCount(0);
					regform.setJuvprogrefs(null);
				}
			}			
		}	
		else
		{
			regform.setMsg("No program referral records found for referral "+juvprogrefId+".");
			return mapping.findForward("error");
		}
		
		/**
		 * Search for progrefcomments
		 */
		ArrayList progrefcomments = getProgramReferralCommentsById(juvprogrefId);
		if (progrefcomments!=null){
			regform.setProgrefcommentsCount(progrefcomments.size());		
			regform.setProgrefcomments(progrefcomments);
		}

		/**
		 * Search for CSEVENTREFERRALs
		 */
		ArrayList eventreferrals = getAssociatedEventReferralsById(juvprogrefId);
		if (eventreferrals!=null){
			regform.setEventreferralCount(eventreferrals.size());
			regform.setEventreferrals(eventreferrals);
		}
		
		/**
		 * Search for CSPROGRFASGNHISTs
		 */
		ArrayList csprogrfasgnhists = getProgramReferralAssignmentsById(juvprogrefId);
		if (csprogrfasgnhists!=null){
			regform.setCsprogrfasgnhistCount(csprogrfasgnhists.size());
			regform.setCsprogrfasgnhists(csprogrfasgnhists);
		}
		
		/**
		 * Search for CSSERVATTENDs
		 */
		ArrayList csServAttendsList = getServiceAttendanceEvents(regform.getEventreferrals(),regform.getJuvprogrefs());
		if (csServAttendsList!=null){
			regform.setServattendCount(csServAttendsList.size());
			regform.setServattends(csServAttendsList);
		}
		
		/**
		 * Search for JCCALEVENTCONTs
		 */
		ArrayList jcCalendarEventContextList = getCalendarEventContexts(regform.getEventreferrals(),regform.getJuvprogrefs());
		if (jcCalendarEventContextList != null){
			regform.setCaleventcontCount(jcCalendarEventContextList.size());
			regform.setCaleventconts(jcCalendarEventContextList);
		}
		
		regform.setMsg("");
		return mapping.findForward("success");

	}
	
	/**
	 * Retrieve the Program Referral Record
	 * @param referralNum
	 * @return
	 */
	public static ArrayList getProgramReferralsById(String referralNum){
		ArrayList juvprogrefs = null;
		
		// Get and set Associated JuvProgRefs
		GetProductionSupportJuvenileProgramReferralsEvent getJuvenileProgramRerralsEvent = (GetProductionSupportJuvenileProgramReferralsEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALS );
		getJuvenileProgramRerralsEvent.setReferralNum(referralNum );
		CompositeResponse juvenileProgramReferralsResp = MessageUtil.postRequest(getJuvenileProgramRerralsEvent);
		juvprogrefs = (ArrayList) MessageUtil.compositeToCollection(juvenileProgramReferralsResp, ProductionSupportJuvenileProgramReferralResponseEvent.class);
		
		return juvprogrefs;
	}

	/**
	 * Retrieve the Program Referral Comments
	 * @param referralNum
	 * @return
	 */
	public static ArrayList getProgramReferralCommentsById(String referralNum){
		ArrayList juvenileProgramReferralCommentsList= null;
		
		// Get referral comments
		GetProductionSupportJuvenileProgramReferralCommentsEvent getJuvenileProgramRerralCommentsEvent = (GetProductionSupportJuvenileProgramReferralCommentsEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALCOMMENTS );
		getJuvenileProgramRerralCommentsEvent.setJuvenileProgramReferralNum(referralNum );
		CompositeResponse juvenileProgramReferralsResp = MessageUtil.postRequest(getJuvenileProgramRerralCommentsEvent);
		juvenileProgramReferralCommentsList = (ArrayList) MessageUtil.compositeToCollection(juvenileProgramReferralsResp, ProgramReferralCommentResponseEvent.class);
		
		return juvenileProgramReferralCommentsList;
	}
	
	
	/**
	 * Retrieve the Associated Event Referrals
	 * @param referralNum
	 * @return
	 */
	public static ArrayList getAssociatedEventReferralsById(String referralNum){
		ArrayList associatedJuvenileEventReferralList= null;
		
		// Get referral comments
		GetProductionSupportAssociatedJuvenileEventReferralsEvent getAssociatedJuvenileRerralEvent = (GetProductionSupportAssociatedJuvenileEventReferralsEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTASSOCIATEDJUVENILEEVENTREFERRALS );
		getAssociatedJuvenileRerralEvent.setJuvenileProgramReferralNum(referralNum );
		CompositeResponse associateJuvenileReferralsResp = MessageUtil.postRequest(getAssociatedJuvenileRerralEvent);
		associatedJuvenileEventReferralList = (ArrayList) MessageUtil.compositeToCollection(associateJuvenileReferralsResp, ProgramAssociatedJuvenileEventReferralResponseEvent.class);
		
		return associatedJuvenileEventReferralList;
	}
	
	/**
	 * Retrieve the Program Referral Assignment History Records
	 * @param referralNum
	 * @return
	 */
	public static ArrayList getProgramReferralAssignmentsById(String referralNum){
		ArrayList getJuvProgramAssignmentHistoriesResponsesList = null;
		
		GetProductionSupportJuvenileProgramReferralAssignmentHistoryEvent getJuvProgramAssignmentHistoriesEvent = 
			(		GetProductionSupportJuvenileProgramReferralAssignmentHistoryEvent)EventFactory.
			getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALASSIGNMENTHISTORY );
		getJuvProgramAssignmentHistoriesEvent.setProgramReferralNumber( referralNum );
		CompositeResponse getJuvProgramAssignmentHistoriesResponse = MessageUtil.postRequest(getJuvProgramAssignmentHistoriesEvent);
		getJuvProgramAssignmentHistoriesResponsesList = 			
			(ArrayList) MessageUtil.compositeToCollection(getJuvProgramAssignmentHistoriesResponse, ProgramReferralAssignmentHistoryResponseEvent.class);
		
		return getJuvProgramAssignmentHistoriesResponsesList;
		
	}
	
	/**
	 * retrieve the service attendance events based on a service event Id
	 * @param associateEventList
	 * @param juvProgReferralList
	 * @return
	 */
	public static ArrayList getServiceAttendanceEvents(ArrayList<ProgramAssociatedJuvenileEventReferralResponseEvent> associateEventList,ArrayList<ProductionSupportJuvenileProgramReferralResponseEvent> juvProgReferralList){
		ArrayList<ServiceEventAttendanceResponseEvent> getServiceAttendanceEventResponsesList = null;		
		ProgramAssociatedJuvenileEventReferralResponseEvent juvenileEventReferralResponseEvent  = null;
		ProductionSupportJuvenileProgramReferralResponseEvent juvProgramReferralResponseEvent = null;
		ArrayList matchingServiceAttendanceEventResponses = new ArrayList();
		for(ProgramAssociatedJuvenileEventReferralResponseEvent event:associateEventList){
			juvenileEventReferralResponseEvent = event;		
			GetProductionSupportJuvenileAttendanceEvent getJuvProgramAssignmentHistoriesEvent = 
				(		GetProductionSupportJuvenileAttendanceEvent)EventFactory.
				getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEATTENDANCE);
			getJuvProgramAssignmentHistoriesEvent.setServiceEventId( juvenileEventReferralResponseEvent.getServiceEventId() );
			CompositeResponse getServiceAttendanceEventResponse = MessageUtil.postRequest(getJuvProgramAssignmentHistoriesEvent);
			getServiceAttendanceEventResponsesList = 			
				(ArrayList) MessageUtil.compositeToCollection(getServiceAttendanceEventResponse, ServiceEventAttendanceResponseEvent.class);
			
			for(ProductionSupportJuvenileProgramReferralResponseEvent programReferralReponseEvent:juvProgReferralList){
				juvProgramReferralResponseEvent = programReferralReponseEvent;
				break;
			}
			JuvenileCasefileResponseEvent casefileResponseEvent = retrieveCaseFile(juvProgramReferralResponseEvent.getCaseFileId());
			
			for(ServiceEventAttendanceResponseEvent serviceAttendanceEvent:getServiceAttendanceEventResponsesList){
				if(event != null && casefileResponseEvent != null && serviceAttendanceEvent.getJuvenileId().equals(casefileResponseEvent.getJuvenileNum())){
					matchingServiceAttendanceEventResponses.add(serviceAttendanceEvent);
				}
			}
		}
		
		return matchingServiceAttendanceEventResponses;
		
	}
	
	
	/**
	 * retrieve the calendar event context based on a service event Id
	 * @param associateEventList
	 * @param juvProgReferralList
	 * @return
	 */
	public static ArrayList getCalendarEventContexts(ArrayList<ProgramAssociatedJuvenileEventReferralResponseEvent> associateEventList, ArrayList<ProductionSupportJuvenileProgramReferralResponseEvent> juvProgReferralList){
		ArrayList<CalendarEventContextResponse> getCalendarEventContextResponsesList = null;
		ArrayList<CalendarServiceEventResponseEvent> getCalendarResponsesList = null;
		ProductionSupportJuvenileProgramReferralResponseEvent juvProgramReferralResponseEvent = null;
		// Get the service event id off the associated juvenile event referral
		ProgramAssociatedJuvenileEventReferralResponseEvent associatedJuvenileEventReferralResponseEvent = null;
		// get casefile id to limit results of caleventcont to unique record
		for(ProductionSupportJuvenileProgramReferralResponseEvent event:juvProgReferralList){
			juvProgramReferralResponseEvent = event;
			break;
		}
		String casefileId = juvProgramReferralResponseEvent.getCaseFileId();
		CalendarServiceEventResponseEvent calendarServiceResponseEvent = null;
		ArrayList<CalendarEventContextResponse> deletedCalendarEventContextList = new ArrayList();
		for(ProgramAssociatedJuvenileEventReferralResponseEvent assocatedJuvenileReferralEvent:associateEventList){
			associatedJuvenileEventReferralResponseEvent = assocatedJuvenileReferralEvent;
			// get service event off the associated event referral 
			String serviceEventId = associatedJuvenileEventReferralResponseEvent.getServiceEventId();
			// Now get Calendar Event Record			
			GetProductionSupportCalendarServiceEventsEvent getCalendarServiceEvent = 
						(GetProductionSupportCalendarServiceEventsEvent)EventFactory.getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCALENDARSERVICEEVENTS );
			getCalendarServiceEvent.setServiceEventId(serviceEventId);
			CompositeResponse getCalendarEventResponse = MessageUtil.postRequest(getCalendarServiceEvent);
			getCalendarResponsesList = 			
				(ArrayList) MessageUtil.compositeToCollection(getCalendarEventResponse, CalendarServiceEventResponseEvent.class);

			for(CalendarServiceEventResponseEvent calendarServiceEvent: getCalendarResponsesList){
				calendarServiceResponseEvent = calendarServiceEvent;		
				// retrieve all calendarEventContext records on a given calendar event
				GetProductionSupportCalendarEventContextEvent getCalendarEventContextEvent = 
					(GetProductionSupportCalendarEventContextEvent)EventFactory.
					getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCALENDAREVENTCONTEXT );
				getCalendarEventContextEvent.setCalendarEventId(calendarServiceResponseEvent.getCalendarEventId());
				CompositeResponse getCalendarEventContextResponse = MessageUtil.postRequest(getCalendarEventContextEvent);
				getCalendarEventContextResponsesList = 			
					(ArrayList) MessageUtil.compositeToCollection(getCalendarEventContextResponse, CalendarEventContextResponse.class);
				for(CalendarEventContextResponse contextResponseEvent:getCalendarEventContextResponsesList){
					if(contextResponseEvent != null && contextResponseEvent.getCaseFileId().equals(casefileId)){
						deletedCalendarEventContextList.add(contextResponseEvent);
					}
				}
			}
		}	
		
		return deletedCalendarEventContextList;
		
	}
	
	/**
	 * Retrieve a casefile
	 * @param casefileId
	 * @return
	 */
	private static JuvenileCasefileResponseEvent retrieveCaseFile(String casefileId){
	
	/**
	 * Search for Casefiles
	 */
	GetJuvenileCasefileByCasefileIdEvent getCasefileEvent = (GetJuvenileCasefileByCasefileIdEvent)
	EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEBYCASEFILEID ) ;
	getCasefileEvent.setSupervisionNumber( casefileId ) ;
	
	IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
	dispatch.postEvent( getCasefileEvent ) ;

	CompositeResponse aResponse = (CompositeResponse)dispatch.getReply( ) ;
	
	JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent)
		MessageUtil.filterComposite( aResponse, JuvenileCasefileResponseEvent.class ) ;

	return casefile;	
	}
	
}
