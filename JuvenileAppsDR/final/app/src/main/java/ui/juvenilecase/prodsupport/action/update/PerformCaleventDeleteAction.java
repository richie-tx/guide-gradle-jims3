package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.CalendarEventContextResponse;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.interviewinfo.reply.InterviewResponseEvent;
import messaging.productionsupport.DeleteProductionSupportCalendarEventsEvent;
import messaging.productionsupport.GetProductionSupportCalendarEventsEvent;
import messaging.productionsupport.reply.ProductionSupportCalendarResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author jims2
 * 
 * Deletes for Calendar Events have to be done from the CALEVENT table, 
 * which then wipes out dependent records in CASERVEVENT, CSSERVATTEND, 
 * JCINTERVIEW, and JCCALEVENTCONT
 */

public class PerformCaleventDeleteAction extends Action {

	private Logger log = Logger.getLogger("PerformCaleventDeleteAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	ProdSupportForm regform = (ProdSupportForm) form;

		
	String serveventId = regform.getServeventId();

	if (serveventId == null || serveventId.equals("")) 
		{
			regform.setMsg("PerformCaleventDeleteAction - ServeventID was null.");
			return (mapping.findForward("error"));
		}
		
	/**Retrieve the parent table's ID **/
	Integer caleventId;
	if(regform.getCalEventId() != null){
		caleventId = new Integer(regform.getCalEventId());
		/**Delete the JCSCHOOL record **/
		DeleteProductionSupportCalendarEventsEvent deleteCalendarEvent =
			(DeleteProductionSupportCalendarEventsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTCALENDAREVENTS);
		deleteCalendarEvent.setCalendarEventId(caleventId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST); 
		/** Log for auditing purposes **/
		writeLogEntries(regform, SecurityUIHelper.getLogonId());
		dispatch.postEvent(deleteCalendarEvent);
		// check that record was deleted
		ProductionSupportCalendarResponseEvent event =  retrieveCalendarEvents(caleventId);
		Integer eventId = event.getCalendarEventId();
		if(event != null && eventId != null){
			regform.setMsg("Error - The calendar event was not successfully deleted. Send information to ITC to determine cause.");
			return mapping.findForward("error");
		}
		
		log.info("Performed a CALEVENT DELETE for caleventID=" + caleventId + " LogonId: " + SecurityUIHelper.getLogonId());
		regform.setMsg("");
		return mapping.findForward("success");
	}else{				
		regform.setMsg("Error - The calendar event was not deleted. PerformCaleventDeleteAction.java");
		return mapping.findForward("error");
	}		
}
	

	/**
	 * log all the entries associated to service event
	 * @param regform
	 * @param logonid
	 */
	private void writeLogEntries(ProdSupportForm regform, String logonid){
	/**CALEVENT, CASERVEVENT, CASERVATTEND, JCINTERVIEW, and JCCALEVENTCONT**/
						
		if (regform.getCaleventconts()!=null)
		{
			Iterator iter = regform.getCaleventconts().iterator();
			while (iter.hasNext())
			{
				CalendarEventContextResponse next = (CalendarEventContextResponse)iter.next();
				log.info("JCCALEVENTCONT ID: "
						+ next.getCalendarEventContextId() + " DELETE: " + logonid);
			}
		}
		if (regform.getServevents()!=null)
		{
			Iterator iter = regform.getServevents().iterator();
			while (iter.hasNext())
			{
				CalendarServiceEventResponseEvent next =  (CalendarServiceEventResponseEvent)iter.next();
				log.info("SERVEVENT ID: "
						+ next.getServiceEventId() + " DELETE: " + logonid);
			}
		}
		if (regform.getServattends()!=null)
		{
			Iterator iter = regform.getServattends().iterator();
			while (iter.hasNext())
			{
				ServiceEventAttendanceResponseEvent next = (ServiceEventAttendanceResponseEvent)iter.next();
				log.info("SERVATTEND ID: "
						+ next.getServiceEventAttendanceId() + " DELETE: " + logonid);
			}
		}
		if (regform.getCalevents()!=null)
		{
			Iterator iter = regform.getCalevents().iterator();
			while (iter.hasNext())
			{
				ProductionSupportCalendarResponseEvent next = (ProductionSupportCalendarResponseEvent)iter.next();
				log.info("CALEVENT ID: "
						+next.getCalendarEventId() + " DELETE:" + logonid);
			}
		}			
		
		/**Interview has 3 child tables: JCIVIEWINVREC, JCIVIEWPRSN, and JCINTASKASSO**/
		if (regform.getInterviews()!=null)
		{
			Iterator iter = regform.getInterviews().iterator();
			while (iter.hasNext())
			{
				InterviewResponseEvent next = (InterviewResponseEvent)iter.next();
				log.info("JCINTERVIEW ID: "
						+ next.getInterviewId() + " DELETE: " + logonid);
				log.info("Interview has 3 child tables: JCIVIEWINVREC, JCIVIEWPRSN, and JCINTASKASSO");
				log.info("JCIVIEWINVREC, INTERVIEW ID: "
						+  next.getInterviewId()  + " DELETE: " + logonid);
				log.info("JCIVIEWPRSN, INTERVIEW ID: "
						+ next.getInterviewId()  + " DELETE: " + logonid);
				log.info("JCINTASKASSO, INTERVIEW ID: "
						+ next.getInterviewId() + " DELETE: " + logonid);
			}
		}
	}
	
	/**
	 * retrieve calendar event CALEVENT
	 * @param calEventId
	 * @return
	 */
	private ProductionSupportCalendarResponseEvent retrieveCalendarEvents(Integer calEventId){
		/**
		 * Search for calevents.
		 */
		GetProductionSupportCalendarEventsEvent getCalendarEvents = (GetProductionSupportCalendarEventsEvent)
		EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCALENDAREVENTS);
		getCalendarEvents.setCalendarEventId(calEventId);
		ProductionSupportCalendarResponseEvent calendarEventsResp = (ProductionSupportCalendarResponseEvent) MessageUtil.postRequest(getCalendarEvents, ProductionSupportCalendarResponseEvent.class);		
		return calendarEventsResp;
	}
}
