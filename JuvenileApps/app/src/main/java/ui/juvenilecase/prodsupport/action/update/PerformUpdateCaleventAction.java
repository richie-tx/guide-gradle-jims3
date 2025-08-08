package ui.juvenilecase.prodsupport.action.update;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.productionsupport.GetProductionSupportCalendarServiceEventsEvent;
import messaging.productionsupport.SaveProductionSupportCalendarServiceEventsEvent;
import messaging.productionsupport.UpdateProductionSupportCalendarEventsEvent;
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
 *         Updates EVENTMAXIMUM column in CASERVEVENT table
 */

public class PerformUpdateCaleventAction extends Action {

	private Logger log = Logger.getLogger("PerformUpdateCaleventAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;

		String logonid = SecurityUIHelper.getLogonId(); // For readability in
														// the logs.
		String serveventId = regform.getServeventId();
		
		
		// save the calendar service event
		SaveProductionSupportCalendarServiceEventsEvent saveCalendarServiceEvent = (SaveProductionSupportCalendarServiceEventsEvent)
		EventFactory.getInstance(ProductionSupportControllerServiceNames.SAVEPRODUCTIONSUPPORTCALENDARSERVICEEVENTS);
		if (serveventId == null || serveventId.equals("")) {
			regform.setMsg("PerformCaleventUpdateAction - ServeventID was null.");
			return (mapping.findForward("error"));
		}else{
			saveCalendarServiceEvent.setServiceEventId(serveventId);
		}
		ArrayList previousCalendarEvents = regform.getServevents();
		CalendarServiceEventResponseEvent previousCalendarEvent = null;
		if(previousCalendarEvents != null && previousCalendarEvents.size() > 0){
			previousCalendarEvent = (CalendarServiceEventResponseEvent)regform.getServevents().get(0);
		}else{
			regform.setMsg("Error - There was no Calendar Service Event found.");
			return mapping.findForward("error");
		}		
		// check if any value changed
		String newMaxAttendance = regform.getNewEventMaximum();
		String newEventStatusCd = regform.getNewEventStatusCd();
		String newJuvLocationUnitId = regform.getNewJuvLocationUnitId();
		//String startDay = regform.getStartCalEventDay();
		//String startMonth = regform.getStartCalEventMonth();
		//String startYear = regform.getStartCalEventYear();
		String newStartCalDate = regform.getNewStartCalDate();
		System.out.println("newStartCalDate" + newStartCalDate);
		String startHours = regform.getStartCalTimeHours();
		String startMinutes = regform.getStartCalTimeMinutes();
		//String endDay = regform.getEndCalEventDay();
		//String endMonth = regform.getEndCalEventMonth();
		//String endYear = regform.getEndCalEventYear();
		String newEndCalDate = regform.getNewEndCalDate();
		System.out.println("newEndCalDate" + newEndCalDate);
		String endHours = regform.getEndCalTimeHours();
		String endMinutes = regform.getEndCalTimeMinutes();
		boolean isNewMaxAttendanceChanged = checkIfTwoValuesChanged(newMaxAttendance, previousCalendarEvent.getMaxAttendance());
		boolean isNewEventsStatusCodeChanged = checkIfTwoValuesChanged(newEventStatusCd, previousCalendarEvent.getEventStatusCode());
		boolean isNewJuvLocationUnitIdChanged = checkIfTwoValuesChanged(newJuvLocationUnitId, previousCalendarEvent.getJuvUnitCd());
		boolean isNewStartDate = checkDropdownEntry(newStartCalDate);
		boolean isNewEndDate = checkDropdownEntry(newEndCalDate);
		boolean isNewStartTime = checkDropdownEntry(startHours) && checkDropdownEntry(startMinutes); 
		boolean isNewEndTime = checkDropdownEntry(endHours) && checkDropdownEntry(endMinutes); 
		// if not changes, then throw back error message to the screen
		if( !isNewMaxAttendanceChanged && !isNewEventsStatusCodeChanged && !isNewJuvLocationUnitIdChanged && !isNewStartDate && !isNewEndDate && !isNewStartTime && !isNewEndTime) {
			regform.setMsg("There were no changes entered.");
			return mapping.findForward("error");				
		}
		// Handle any Start or End Date or Time Changes
		UpdateProductionSupportCalendarEventsEvent updateEvent =
				(UpdateProductionSupportCalendarEventsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTCALENDAREVENTS);
		Date finalStartDateTime = null;
		Date finalEndDateTime = null;
		if(isNewStartDate || isNewStartTime){ // if Start Date or Start Time is changed then save to UpdateProductionSupportCalendarEventsEvent
			updateEvent.setBeginDate(handleDateChange(previousCalendarEvent.getStartDatetime(), newStartCalDate, startHours, startMinutes, isNewStartDate, isNewStartTime));
			finalStartDateTime = updateEvent.getBeginDate();
		}else{
			finalStartDateTime = previousCalendarEvent.getStartDatetime();
		}
		if(isNewEndDate || isNewEndTime){ // if Start or End Date or Start or End Time is changed then save to UpdateProductionSupportCalendarEventsEvent
			updateEvent.setEndDate(handleDateChange(previousCalendarEvent.getEndDatetime(), newEndCalDate, endHours, endMinutes, isNewEndDate, isNewEndTime));
			finalEndDateTime = updateEvent.getEndDate();
		}else{
			finalEndDateTime = previousCalendarEvent.getEndDatetime();
		}
		if(finalEndDateTime.before(finalStartDateTime)){
			regform.setMsg("Start Date/Time cannot be after the End Date/Time.");
			return mapping.findForward("error");	
		}
		previousCalendarEvent.setEventSessionLength(calculateSessionLength(finalStartDateTime, finalEndDateTime));
		// check if start date is later than end date - if so throw error. Also set the session length based on the date.
		if(isNewStartDate || isNewEndDate || isNewStartTime || isNewEndTime){ // save data in UpdateProductionSupportCalendarEventsEvent to CALEVENT
			saveCalendarEvent(regform, previousCalendarEvent, updateEvent,isNewStartDate, isNewEndDate, isNewStartTime, isNewEndTime, logonid);
		}
		// Handle any Date Changes other than date or time
		if(isNewMaxAttendanceChanged || isNewEventsStatusCodeChanged || isNewJuvLocationUnitIdChanged){	// if maxAttendance, EventStatus, or JuvLocUnit changed then save to CASERVEVENT
			saveCalendarServiceEvent(regform, previousCalendarEvent, saveCalendarServiceEvent, logonid, mapping, isNewMaxAttendanceChanged, isNewEventsStatusCodeChanged, isNewJuvLocationUnitIdChanged);
		}
		
		// all went well, return success
		regform.setMsg("");
		return mapping.findForward("success");
	}
	
	/**
	 * Update the Calendar Service Event Information in the CASERVEVENT table for changed values
	 * @param regform
	 * @param previousCalendarEvent
	 * @param saveCalendarServiceEvent
	 */
	private void saveCalendarServiceEvent(ProdSupportForm regform, CalendarServiceEventResponseEvent previousCalendarEvent, SaveProductionSupportCalendarServiceEventsEvent saveCalendarServiceEvent, 
			String logonid, ActionMapping mapping, boolean maxAttendanceChanged, boolean eventStatusCdChanged, boolean juvLocationUnitChanged){
		String newMax = regform.getNewEventMaximum();
		String newEventStatusCd = regform.getNewEventStatusCd();
		String newJuvLocationUnitId = regform.getNewJuvLocationUnitId();
		
		if(maxAttendanceChanged){
			saveCalendarServiceEvent.setMaxAttendance(newMax);
			regform.setNewEventMaximum(newMax);
		}else{
			saveCalendarServiceEvent.setMaxAttendance(null);
			regform.setNewEventMaximum(null);
		}
		if(eventStatusCdChanged){
			saveCalendarServiceEvent.setEventStatusCode(newEventStatusCd);
			regform.setNewEventStatusCd(newEventStatusCd);
		}else{
			saveCalendarServiceEvent.setEventStatusCode(null);
			regform.setNewEventStatusCd(null);
		}
		if(juvLocationUnitChanged){
			saveCalendarServiceEvent.setJuvlocationUnitId(newJuvLocationUnitId);
			regform.setNewJuvLocationUnitId(newJuvLocationUnitId);
		}else{
			saveCalendarServiceEvent.setJuvlocationUnitId(null);
			regform.setNewJuvLocationUnitId(null);
		}
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(saveCalendarServiceEvent);		
		log.info("["+new Date()+"] ProdSupport  (" + logonid + "Updated caservevent record with servevent_id= " + regform.getServeventId());
		if(maxAttendanceChanged){
			log.info("["+new Date()+"] ProdSupport  (" + logonid + "Updated caservevent record with servevent_id= " + regform.getServeventId() + " and MaxAttendance = " + saveCalendarServiceEvent.getMaxAttendance() );
		}
		if(eventStatusCdChanged){
			log.info("["+new Date()+"] ProdSupport  (" + logonid + "Updated caservevent record with servevent_id= " + regform.getServeventId() + " and EventStatusCode = " + saveCalendarServiceEvent.getEventStatusCode() );
		}
		if(juvLocationUnitChanged){
			log.info("["+new Date()+"] ProdSupport  (" + logonid + "Updated caservevent record with servevent_id= " + regform.getServeventId() + " and JuvlocationUnitId = " + saveCalendarServiceEvent.getJuvlocationUnitId() );
		}
	}
	
	/**
	 * Update the Calendar Event Information in the CALEVEVENT table for changed values
	 * @param regform
	 * @param previousCalendarEvent
	 * @param updateCalendarEvent
	 * @param isNewStartDate
	 * @param isNewEndDate
	 * @param isNewStartTime
	 * @param isNewEndTime
	 * @param logonid
	 */
	private void saveCalendarEvent(ProdSupportForm regform, CalendarServiceEventResponseEvent previousCalendarEvent, UpdateProductionSupportCalendarEventsEvent updateCalendarEvent, 
			boolean isNewStartDate, boolean isNewEndDate, boolean isNewStartTime, boolean isNewEndTime, String logonid){
			
		updateCalendarEvent.setCalendarEventId(previousCalendarEvent.getCalendarEventId().toString());	
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(updateCalendarEvent);
		log.info("["+new Date()+"] ProdSupport  (" + logonid + "Updated calevent record with calevent_id= " + previousCalendarEvent.getCalendarEventId().toString());
		if(isNewStartDate || isNewStartTime){
			regform.setNewCalEventStartDate(updateCalendarEvent.getBeginDate());
			log.info("["+new Date()+"] ProdSupport  (" + logonid + "Updated calevent record with calevent_id= " + previousCalendarEvent.getCalendarEventId().toString() + " and NewCalEventStartDate = " + updateCalendarEvent.getBeginDate());
		}
		if(isNewEndDate || isNewEndTime){
			regform.setNewCalEventEndDate(updateCalendarEvent.getEndDate());
			log.info("["+new Date()+"] ProdSupport  (" + logonid + "Updated calevent record with calevent_id= " + previousCalendarEvent.getCalendarEventId().toString() + " and NewCalEventEndDate = " + updateCalendarEvent.getEndDate() );
		}
		
		//clear out previous values of newStartCalDate & newEndCalDate - Bug 169922
		regform.setNewStartCalDate(null);
		regform.setNewEndCalDate(null);
	}
	
	/**
	 * Take original date, and determine whether to replace it based on day/month/year values, then update date with old or new timestamp based on hours/minutes values
	 * @param originalDate
	 * @param day
	 * @param month
	 * @param year
	 * @param hours
	 * @param minutes
	 * @return
	 */
	private Date handleDateChange(Date originalDate, String newCalDate, String hours, String minutes, boolean dateChanged, boolean timeChanged){
		Date newDate = null;
		Calendar aCalendar = Calendar.getInstance();
		// if both date changed, and the time changed, create a new date/time object with the value
		if(dateChanged && timeChanged){
			String newDateString = newCalDate + " " + hours + ":" + minutes;
			System.out.println("newDateString" + newDateString);
			try{
				newDate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(newDateString.trim());
				System.out.println("newDate" + newDate);
			}catch(Exception e){
				log.severe("Could not create a new Date during Prod Support Update Service Event Scenario:" + newDateString);
			}
		}else if(dateChanged){	// if just the date changed, then create a new date, but with the original timestamp from the original date value
			DateFormat df = new SimpleDateFormat("HH:mm");       
			String originalTimeString = df.format(originalDate);
			String newDateString = newCalDate  + " " + originalTimeString;
			System.out.println("newDateString" + newDateString);
			try{
				newDate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(newDateString);
				System.out.println("newDate" + newDate);
			}catch(Exception e){
				log.severe("Could not create a new Date during Prod Support Update Service Event Scenario:" + newDateString);
			}
		}else if(timeChanged){ // if just the time changed, then use the origina, date, but add new timestamp
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");       
			String originalDateString = df.format(originalDate);
			String newDateString = originalDateString +  " " + hours + ":" + minutes;
			System.out.println("newDateString" + newDateString);
			try{
				newDate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(newDateString);
				
			}catch(Exception e){
				log.severe("Could not create a new Date during Prod Support Update Service Event Scenario:" + newDateString);
			}
		}else{
			newDate = originalDate;
		}
		return newDate;
	}
	
	/**(
	 * compare two string values and determine if they are equal
	 * @param value
	 * @param compareValue
	 * @return
	 */
	private boolean checkIfTwoValuesChanged(String newValue, String OlderValue){
		boolean result = false;
		
		if(newValue != null && OlderValue != null && !newValue.equals("")){
			if(!newValue.equals(OlderValue)){
				result = true;
			}
		}else if (newValue != null && OlderValue == null){
				result = true;
		}
		
		return result;
	}
	
	/**(
	 * check dropdown value, and verify valid - not blank or 999
	 * @param value
	 * @param compareValue
	 * @return
	 */
	private boolean checkDropdownEntry(String dropdownValue){
		boolean result = false;
		
		if(dropdownValue != null  && !dropdownValue.equals("") && !dropdownValue.equals("999")){
			result = true;
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * @param serveventId
	 * @param regform
	 * @return
	 */
	private ArrayList<CalendarServiceEventResponseEvent> retrieveCurrentCalendarEvent(String serveventId, ProdSupportForm regform){
		// get updated record back and set on form	
		GetProductionSupportCalendarServiceEventsEvent getCalendarEvent = (GetProductionSupportCalendarServiceEventsEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCALENDARSERVICEEVENTS);
		getCalendarEvent.setServiceEventId(serveventId);
		CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent) MessageUtil.postRequest(getCalendarEvent, CalendarServiceEventResponseEvent.class);
		ArrayList<CalendarServiceEventResponseEvent> calendarEvents = new ArrayList<CalendarServiceEventResponseEvent>();
		if (resp!=null && resp.getServiceEventId()!= null && resp.getServiceEventId().length() > 0)
		{
			calendarEvents.add(resp);
		}
		
		return calendarEvents;
	}
	
	/**
	 * calculate the session length in hours based on the final date and time
	 * @param startDateTime
	 * @param endDateTime
	 * @return
	 */
	private String calculateSessionLength(Date startDateTime, Date endDateTime){
		String sessionLength = "";
		if(startDateTime != null && endDateTime != null){
		      long milsecs1= startDateTime.getTime();
		      long milsecs2 = endDateTime.getTime();
		      double diff = milsecs2 - milsecs1;
		      double dhours = diff / (60.0 * 60.0 * 1000.0);
		      sessionLength = new Float(dhours).toString();
		      sessionLength = sessionLength + " Hours";
		}
		return sessionLength;
	}
}
