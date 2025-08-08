package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.productionsupport.GetProductionSupportCalendarEventContextEvent;
import messaging.productionsupport.GetProductionSupportCalendarServiceEventsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;
import messaging.calendar.reply.CalendarEventContextResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


public class UpdateCaleventQueryAction extends Action {

	private Logger log = Logger.getLogger("UpdateCaleventQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
			regform.clearAllResults();
			regform.setServeventId("");
			regform.setMsg("");
			return mapping.findForward("error");
		}

		String serveventId = regform.getServeventId() != null ? regform.getServeventId().trim() : null;

		if (serveventId == null || serveventId.equals("")) {
			regform.setMsg("Calendar Service Event ID was null.");
			return (mapping.findForward("error"));
		}

		// Clear the form
		regform.clearAllResults();

		// Reset the ID value.
		regform.setServeventId(serveventId);

		// Log the query attempt
		log.info("Calendar Service Event Query ID: "+ serveventId + " LogonId: " + SecurityUIHelper.getLogonId());
		
		/**
		 * Search for caservevents.
		 */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		
		// get calendar service events for most data
		GetProductionSupportCalendarServiceEventsEvent getCalendarEvent = (GetProductionSupportCalendarServiceEventsEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCALENDARSERVICEEVENTS);
		getCalendarEvent.setServiceEventId(serveventId);
		CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent) MessageUtil.postRequest(getCalendarEvent, CalendarServiceEventResponseEvent.class);
			
		// Add location values and event status cd values to the bean. 
		ArrayList serviceEventStatusCodes = (ArrayList) CodeHelper.getCodes("SERVICE_EVENT_STATUS");
		ArrayList locationUnitCodes = (ArrayList) CodeHelper.getLocationUnitCodes();
		if (serviceEventStatusCodes != null)
			regform.setEventStatusCodes(serviceEventStatusCodes);
		else {
			regform.setMsg("Error - Couldn't retrieve Event Statis Code Codetable Values.");
			regform.setEventStatusCodes(null);
			return mapping.findForward("error");
		}
		if (locationUnitCodes != null)
			regform.setJuvLocationUnitCodes(locationUnitCodes);
		else {
			regform.setMsg("Error - Couldn't retrieve Juv Location Unit Codetable Values.");
			regform.setJuvLocationUnitCodes(null);
			return mapping.findForward("error");
		}
		
		ArrayList<CalendarServiceEventResponseEvent> calendarServiceEvents = new ArrayList<CalendarServiceEventResponseEvent>();
		if (resp!=null && resp.getServiceEventId()!= null && resp.getServiceEventId().length() > 0)
		{
			calendarServiceEvents.add(resp);
			regform.setServevents(calendarServiceEvents);
			regform.setServeventCount(calendarServiceEvents.size());
		}
		else
		{
			regform.setServevents(null);
			regform.setServeventCount(0);
			regform.setMsg("No records returned for serviceEventId." + serveventId);
			return mapping.findForward("error");
		}
		// calculate session length
		calculateSessionLength(calendarServiceEvents);
		
			
		regform.setMsg("");
		return mapping.findForward("success");
		
	}
	
	/**
	 * 
	 * @param serviceEventsList
	 */
	private void calculateSessionLength(ArrayList serviceEventsList){
		CalendarServiceEventResponseEvent serviceEventResponse = null;
		String sessionLength = "";
		if(serviceEventsList != null && serviceEventsList.size() >0){
			serviceEventResponse = (CalendarServiceEventResponseEvent)serviceEventsList.get(0);
			Date startDateTime = serviceEventResponse.getStartDatetime();
			Date endDateTime = serviceEventResponse.getEndDatetime();
			if(startDateTime != null && endDateTime != null){
		      long milsecs1= startDateTime.getTime();
		      long milsecs2 = endDateTime.getTime();
		      double diff = milsecs2 - milsecs1;
		      double dhours = diff / (60.0 * 60.0 * 1000.0);
		      sessionLength = new Float(dhours).toString();
		      serviceEventResponse.setEventSessionLength(sessionLength + " Hours");
			}
		}
	}
		
}
