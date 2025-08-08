package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.GetServContextByServiceEventIdEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventContext;
import pd.supervision.programreferral.JuvenileEventReferral;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

public class MoveEventToNewProgramAction extends Action
{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

	Logger log = Logger.getLogger("ReferralDeleteQueryAction");
	ProdSupportForm regform = (ProdSupportForm) form;
	regform.setAction(""); //clear action
	regform.setNewProgramRefId(null); //clear the new ProgRefID
	String clrChk = request.getParameter("clr");
	regform.setMsg("");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{
	    regform.clearAllResults();
	    regform.setMsg("");
	    return mapping.findForward("error");
	}

	String juvProgRefId = regform.getJuvprogrefId();
	try
	{
	    Integer.parseInt(juvProgRefId);
	}
	catch (Exception e)
	{
	    regform.setMsg("Please enter a valid Program Referral ID.");
	    return (mapping.findForward("error"));
	}

	//Clear the form, then reset the key value
	regform.clearAllResults();
	regform.setJuvprogrefId(juvProgRefId);

	//Log the query attempt
	log.info("[" + new Date()
		+ "] ProdSupport Retrieve Query Move Event to New Program Referral (LogonId: "
		+ SecurityUIHelper.getLogonId().toUpperCase()
		+ ") - Program Referral Query ID: " + juvProgRefId);

	/**
	 * Search for juvprogrefs
	 */
	//ArrayList<JuvenileEventReferral> juvprogrefs = getProgramReferralsById(juvProgRefId);

	ArrayList<CalendarServiceEventResponseEvent> juvprogrefs = getProgramReferralsById(juvProgRefId);
	if (juvprogrefs != null && juvprogrefs.size() > 0)
	{
	    regform.setJuvprogrefCount(juvprogrefs.size());
	    regform.setJuvprogrefs(juvprogrefs);
	}
	else
	{
	    regform.setMsg("No program referral records found for referral " + juvProgRefId + ".");
	    regform.setJuvprogrefId("");
	    return mapping.findForward("error");
	}
	regform.setAction("getNewProgRefId");
	return mapping.findForward("success");
    }

    /**
     * Retrieve the Program Referral Record
     * 
     * @param juvProgRefId
     * @return
     */
    public static ArrayList<CalendarServiceEventResponseEvent> getProgramReferralsById(String juvProgRefId)
    {
	ArrayList juvprogrefs = null;
	ArrayList<CalendarServiceEventResponseEvent> calendarServiceEventResponseEvents = new ArrayList<CalendarServiceEventResponseEvent>();

	// retrieve all CSEVENTREFERRAL by JUVPROGREF_ID and place in a collection
	JuvenileEventReferral programEventReferral = null;
	ArrayList<JuvenileEventReferral> juvenileEventReferralList = new ArrayList<JuvenileEventReferral>();
	
	if (juvProgRefId != null)
	{
	    Iterator<JuvenileEventReferral> eventReferralIter = JuvenileEventReferral.findAll("programReferralId", juvProgRefId);
	    if (eventReferralIter != null)
	    {
		while (eventReferralIter.hasNext())
		{
		    programEventReferral = (JuvenileEventReferral) eventReferralIter.next();
		    juvenileEventReferralList.add(programEventReferral);
		}
	    }
	}
	//new code begins
	//retrieve the CALEVENT_IDs  from T_CASERVEVENT 
	ArrayList<CalendarServiceEventResponseEvent> calServRespList = new ArrayList<CalendarServiceEventResponseEvent>();
	if (juvenileEventReferralList != null)
	{
	    for (JuvenileEventReferral juvenileEventReferral : juvenileEventReferralList)
	    {
		if (juvenileEventReferral != null)
		{
		    //call to CASERVEVENT to get the CALEVENT_ID

		    ServiceEvent serviceEvent = (ServiceEvent) ServiceEvent.find(juvenileEventReferral.getServiceEventId());
		    //call to CALEVENT to get the STARTDATETIME
		    List<ServiceEventContext> serviceContexts = new ArrayList<>();
		    CalendarServiceEventResponseEvent response = serviceEvent.getCalendarResponseEvent();
		    GetServContextByServiceEventIdEvent getServContextEvent = new GetServContextByServiceEventIdEvent();
		    getServContextEvent.setServiceEventId(response.getServiceEventId());
		    Iterator servContexIter = ServiceEventContext.findAll(getServContextEvent);
		    while (servContexIter.hasNext())
		    {
			ServiceEventContext servContext = (ServiceEventContext) servContexIter.next();
			serviceContexts.add(servContext);
		    }

		    if (serviceContexts != null && serviceContexts.size() > 0)
		    {
			response.setServiceContexts(serviceContexts);
			response.setStartDatetime(serviceContexts.get(0).getStartDatetime());
		    }
		    calServRespList.add(response);
		}
	    }
	}
	 // Sorting the list using a comparator that compares the ServiceEventId of each CalendarServiceEventResponseEvent object
        Collections.sort((List<CalendarServiceEventResponseEvent>) calServRespList, Collections.reverseOrder(new Comparator<CalendarServiceEventResponseEvent>() {
            @Override
            public int compare(CalendarServiceEventResponseEvent event1, CalendarServiceEventResponseEvent event2) {
        	return event1.getServiceEventId().compareTo(event2.getServiceEventId());
            }
        }));
	return calServRespList;
	
	//new code ends
	
	/*GetProgramReferralServiceEventsByProgRefIdEvent gprs = (GetProgramReferralServiceEventsByProgRefIdEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALSERVICEEVENTSBYPROGREFID);
	gprs.setProgramReferralId(juvProgRefId);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(gprs);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	//compositeResponse = (CompositeResponse) MessageUtil.postRequest(gprs);
	List calendarEvents = (List) MessageUtil.compositeToCollection(compositeResponse, CalendarServiceEventResponseEvent.class);
	if (calendarEvents != null)
	{
	    Iterator events = calendarEvents.iterator();
	    while (events.hasNext())
	    {
		CalendarServiceEventResponseEvent serv = (CalendarServiceEventResponseEvent) events.next();
		calendarServiceEventResponseEvents.add(serv);
	    }
	}
	 // Sorting the list using a comparator that compares the eventId of each CalendarServiceEventResponseEvent object
        Collections.sort((List<CalendarServiceEventResponseEvent>) calendarServiceEventResponseEvents, Collections.reverseOrder(new Comparator<CalendarServiceEventResponseEvent>() {
            @Override
            public int compare(CalendarServiceEventResponseEvent event1, CalendarServiceEventResponseEvent event2) {
        	return event1.getEventId().compareTo(event2.getEventId());
            }
        }));
	return calendarServiceEventResponseEvents;*/
    }

}
