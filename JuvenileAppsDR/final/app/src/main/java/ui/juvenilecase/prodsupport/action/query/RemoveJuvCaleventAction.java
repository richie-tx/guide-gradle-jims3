package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.CalendarEventContextResponse;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.interviewinfo.reply.InterviewResponseEvent;
import messaging.productionsupport.GetProductionSupportAssociatedInterviewsEvent;
import messaging.productionsupport.GetProductionSupportCalendarEventContextEvent;
import messaging.productionsupport.GetProductionSupportCalendarEventsEvent;
import messaging.productionsupport.GetProductionSupportCalendarServiceEventsEvent;
import messaging.productionsupport.GetProductionSupportJuvenileAttendanceEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportCalendarResponseEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.supervision.programreferral.JuvenileEventReferral;

import ui.juvenilecase.form.ProdSupportForm;

public class RemoveJuvCaleventAction extends Action
{
    private Logger log = Logger.getLogger("RemoveJuvCaleventAction");
    public ActionForward execute(ActionMapping mapping,
	    			ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response)
				throws Exception {
	ProdSupportForm regform = (ProdSupportForm) form;

	/** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    regform.clearAllResults();
	    regform.setMsg("");
	    return mapping.findForward("error");
	}
	

	String juvenileId 	= regform.getJuvenileId().trim();
	String serviceEventId	= regform.getServeventId().trim();
	
	
	if ( juvenileId == null
		|| juvenileId.length() == 0
		|| serviceEventId == null
		|| serviceEventId.length() == 0) {
	    regform.setMsg("Juvenile Id and Calendar Service Event Id are required");
		return (mapping.findForward("error"));
	}
	
	regform.clearAllResults();
	regform.setServeventId(serviceEventId);
	regform.setJuvenileId(juvenileId);
	
	// Reset the ID value.
	
	/**
	 * Search for caservevents.
	 */
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetProductionSupportCalendarServiceEventsEvent getCalendarEvent = (GetProductionSupportCalendarServiceEventsEvent)
			EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCALENDARSERVICEEVENTS);
	getCalendarEvent.setServiceEventId(serviceEventId);
	CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent) MessageUtil.postRequest(getCalendarEvent, CalendarServiceEventResponseEvent.class);
	
	ArrayList<CalendarServiceEventResponseEvent> calendarServiceEvents = new ArrayList<CalendarServiceEventResponseEvent>();
	if (resp!=null 
		&& resp.getServiceEventId()!= null 
		&& resp.getServiceEventId().length() > 0)
	{
	    calendarServiceEvents.add(resp);
	    regform.setServevents(calendarServiceEvents);
	    regform.setServeventCount(calendarServiceEvents.size());
	}else{
	    regform.setMsg("Calendar Service Event Does Not Exist.");
	    return (mapping.findForward("error"));
	}
	
	Integer caleventId = resp.getCalendarEventId();
	regform.setCalEventId(caleventId.toString());
	
	if ( caleventId != null ) {
	    
	    	/**
		 * Search for calendar events.
		 */

	    GetProductionSupportCalendarEventsEvent getCalendarEvents = (GetProductionSupportCalendarEventsEvent)
	    EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCALENDAREVENTS);
	    getCalendarEvents.setCalendarEventId(caleventId);
	    ProductionSupportCalendarResponseEvent calendarEventsResp = (ProductionSupportCalendarResponseEvent) MessageUtil.postRequest(getCalendarEvents, ProductionSupportCalendarResponseEvent.class);		
	    ArrayList<ProductionSupportCalendarResponseEvent> calendarEvents = new ArrayList<ProductionSupportCalendarResponseEvent>();
	    
	    if (calendarEventsResp !=null) {
		calendarEvents.add(calendarEventsResp);
		regform.setCalevents(calendarEvents);
		regform.setCaleventCount(calendarEvents.size());
	    }
		
		/**
		 * Search for jccaleventconts.
		 */
	    GetProductionSupportCalendarEventContextEvent getCalendarEventContextsEvent = (GetProductionSupportCalendarEventContextEvent)
	    EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCALENDAREVENTCONTEXT);
	    getCalendarEventContextsEvent.setCalendarEventId(caleventId);
	    dispatch.postEvent(getCalendarEventContextsEvent);
	    CompositeResponse calendarEventContextResponse = (CompositeResponse) dispatch.getReply();
	    Map calendarEventContextResponseMap = MessageUtil.groupByTopic(calendarEventContextResponse);
	    MessageUtil.processReturnException(calendarEventContextResponseMap);
	    Collection calendarContextColl = MessageUtil.compositeToCollection(calendarEventContextResponse, CalendarEventContextResponse.class);
	    ArrayList<CalendarEventContextResponse> calendarContextList = new ArrayList<CalendarEventContextResponse>();
	    ArrayList<CalendarEventContextResponse> removedCalendarContextList = new ArrayList<CalendarEventContextResponse>();
	    calendarContextList.addAll(calendarContextColl);
		
	    for (CalendarEventContextResponse calContext :  calendarContextList ) {
		if ( calContext != null 
			&& !juvenileId.equals(calContext.getJuvenileId())) {
		    removedCalendarContextList.add(calContext);
		}
	    }
	    
	    if ( removedCalendarContextList != null 
		    && removedCalendarContextList.size() > 0 ) {
		calendarContextList.removeAll(removedCalendarContextList);
	    }
		
	    if (calendarContextList !=null && calendarContextList.size() > 0)
	    {
		regform.setCaleventconts(calendarContextList);
		regform.setCaleventcontCount(calendarContextList.size());
			
		for (CalendarEventContextResponse calendarContext : calendarContextList ){
		    if ( calendarContext.getCaseFileId() != null ) {

			GetProductionSupportJuvenileProgramReferralsEvent getJuvenileProgramRerralsEvent = (GetProductionSupportJuvenileProgramReferralsEvent)EventFactory.
			getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALS );
			getJuvenileProgramRerralsEvent.setCasefileId( calendarContext.getCaseFileId() );
			CompositeResponse juvenileProgramReferralsResp = MessageUtil.postRequest(getJuvenileProgramRerralsEvent);
			List<ProductionSupportJuvenileProgramReferralResponseEvent> juvenileProgramReferralsList = (ArrayList) MessageUtil.compositeToCollection(juvenileProgramReferralsResp, ProductionSupportJuvenileProgramReferralResponseEvent.class);
			List<ProductionSupportJuvenileProgramReferralResponseEvent> filteredProgramReferralsList = new ArrayList();
			List<JuvenileEventReferral> juvenileReferrals = getJuvenileEventReferrals(serviceEventId);
			
			for (ProductionSupportJuvenileProgramReferralResponseEvent programReferral : juvenileProgramReferralsList ){
			    for (JuvenileEventReferral referral : juvenileReferrals ) {
				if ( programReferral != null
					&& referral != null
					&& programReferral.getJuvenileProgramReferralId().equals(referral.getProgramReferralId()) ){
				    filteredProgramReferralsList.add(programReferral);
				    break;
				}
			    } 
			}
			
			calendarContext.setProgramReferralRespList(filteredProgramReferralsList);
			
		    }
			    
		}
	    } else {
		regform.setMsg("No records found for servevent Id " + serviceEventId +" and juvenile Id " + juvenileId);
		return mapping.findForward("error");	
	    }
	    
	    
	    /**
	     * Search for jcinterview.
	     */
//		
		
	    GetProductionSupportAssociatedInterviewsEvent getInterviewsEvent = (GetProductionSupportAssociatedInterviewsEvent)
            EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTASSOCIATEDINTERVIEWS);
	    getInterviewsEvent.setCalendarEventId(caleventId.toString());
	    dispatch.postEvent(getInterviewsEvent);
	    CompositeResponse interviewsResponse = (CompositeResponse) dispatch.getReply();
	    Map interviewsResponseMap = MessageUtil.groupByTopic(interviewsResponse);
	    MessageUtil.processReturnException(interviewsResponseMap);
	    Collection interviewsColl = MessageUtil.compositeToCollection(interviewsResponse, InterviewResponseEvent.class);
	    ArrayList<InterviewResponseEvent> interviewsList = new ArrayList<InterviewResponseEvent>();
	    interviewsList.addAll(interviewsColl);
	    if (interviewsList !=null && interviewsList.size() > 0)
	    {
		regform.setInterviews(interviewsList);
		regform.setInterviewCount(interviewsList.size());
	    }
		
	} else {
	    regform.setMsg("No records found for servevent Id " + serviceEventId +" and juvenile Id " + juvenileId);
	    return mapping.findForward("error");
	}
	
	
	/**
	 * Search for csservattends.
	 */		
	GetProductionSupportJuvenileAttendanceEvent getAttendanceEvent = (GetProductionSupportJuvenileAttendanceEvent)
			EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEATTENDANCE);
			
	getAttendanceEvent.setServiceEventId(serviceEventId);
	dispatch.postEvent(getAttendanceEvent);		
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	
	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);
	
	Collection<ServiceEventAttendanceResponseEvent> attendanceEvents =
			MessageUtil.compositeToCollection(compositeResponse, ServiceEventAttendanceResponseEvent.class);

	ArrayList<ServiceEventAttendanceResponseEvent> servattends =  new ArrayList<ServiceEventAttendanceResponseEvent>();
	ArrayList<ServiceEventAttendanceResponseEvent> removedServattends =  new ArrayList<ServiceEventAttendanceResponseEvent>();
	if (attendanceEvents!=null && attendanceEvents.size() > 0){
	    for(ServiceEventAttendanceResponseEvent event:attendanceEvents){
		if ( event != null 
			&& ! juvenileId.equals(event.getJuvenileId()) ) {
		    removedServattends.add( event );
		    
		}
			
		if(event.getAddlAttendees()!= null && event.getAddlAttendees().equalsIgnoreCase("")){
		    event.setAddlAttendees("0");
		}
	    }
	
	    if ( removedServattends != null
		    && removedServattends.size() > 0 ) {
		attendanceEvents.removeAll(removedServattends);
	    }
	    servattends.addAll(attendanceEvents);
	    regform.setServattendCount(servattends.size());
	    regform.setServattends(servattends);
	}
	
	regform.setMsg("");
	return mapping.findForward("success");
	    
    }
    
    public List<JuvenileEventReferral> getJuvenileEventReferrals(String serviceEventId){
	List juvenileReferrals = new ArrayList();
	Iterator rferralIter =  JuvenileEventReferral.findAll("serviceEventId", serviceEventId);
	while (rferralIter.hasNext() ) {
	    juvenileReferrals.add((JuvenileEventReferral) rferralIter.next());
	}
	return juvenileReferrals;
    }
    
}
