package pd.supervision.calendar.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import messaging.calendar.CalendarContextEvent;
import messaging.calendar.CreateCalendarServiceEventEvent;
import messaging.calendar.CreateScheduleCalendarEventsEvent;
import messaging.calendar.reply.AttendeeNameResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.interviewinfo.CreateInterviewEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCalendarConstants;
import naming.UIConstants;
import pd.codetable.criminal.JuvenileEventTypeCode;
import pd.common.calendar.CalendarEvent;
import pd.common.calendar.CalendarEventContext;
import pd.common.calendar.CalendarManager;
import pd.common.calendar.ICalendarRecurrenceType;
import pd.common.calendar.OneRecurrence;
import pd.juvenilecase.interviewinfo.Interview;
import pd.security.PDSecurityHelper;
import pd.supervision.calendar.AdditionalAttendee;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventAttendance;

public class CreateScheduleCalendarEventsCommand implements ICommand
{
    public CreateScheduleCalendarEventsCommand()
    {
    }

    /**
     * @param event
     */
    public void execute(IEvent event)
    {
	CreateScheduleCalendarEventsEvent scheduleEvent = (CreateScheduleCalendarEventsEvent) event;
	CreateCalendarServiceEventEvent createEvent = scheduleEvent.getCreateCalendarEvent();

	CalendarManager manager = new CalendarManager();

	String logonId = (PDSecurityHelper.getLogonId() == null) ? "JIMS" : PDSecurityHelper.getLogonId();
	createEvent.setCreatedBy(logonId);
	String eventCategory = createEvent.getEventTypeCategory();
	ServiceEvent serv = new ServiceEvent();

	if (eventCategory.equals(UIConstants.PRESCHEDULED_SERVICE_TYPE))
	{
	    Iterator<CalendarServiceEventResponseEvent> events = createEvent.getEvents().iterator();
	    while (events.hasNext())
	    {
		CalendarServiceEventResponseEvent sre = events.next();
		if (sre.getEventId() != null)
		    serv = (ServiceEvent) ServiceEvent.find(sre.getEventId());
		else
		    serv = (ServiceEvent) ServiceEvent.find(scheduleEvent.getEventId());
		//<KISHORE>JIMS200060401 : MJCW - Service Provider Events -999999 should be updated
		boolean pendingStatus = PDCalendarConstants.SERVICE_EVENT_STATUS_PENDING.equals(serv.getEventStatusId());

		serv.setCurrentEnrollment(serv.getCurrentEnrollment() + 1);
		if (serv.getCurrentEnrollment() == serv.getEventMaximum())
		{
		    serv.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_SCHEDULED);
		}
		else
		    if (serv.getCurrentEnrollment() < serv.getEventMaximum())
		    {
			serv.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_AVAILABLE);
		    }

		//Finds CalendarEventContexts based on the CalendarEventId and places them in an iterator
		//Iterator<CalendarEventContext> existingCalEventsIter = CalendarEventContext.findByCalendarEventId(sre.getCalendarEventId());
		Iterator<CalendarEventContext> existingCalEventsIter = null;
		if (sre.getEventId() != null)
		    existingCalEventsIter = CalendarEventContext.findByCalendarEventId(sre.getCalendarEventId());
		else
		    existingCalEventsIter = CalendarEventContext.findByCalendarEventId(serv.getCalendarEventId());
		//Calender Context Event from Event 
		CalendarContextEvent aCalContextEvent = createEvent.getCalendarContextEvent();
		String aCalContextOfficerId = aCalContextEvent.getProbationOfficerId();
		String aCalContextCaseFileId = aCalContextEvent.getCaseFileId();
		String aCalContextJuvenileId = aCalContextEvent.getJuvenileId();

		//<KISHORE>JIMS200060401 : MJCW - Service Provider Events -999999 should be updated
		//A Service program event will be in pending status when it has no juveniles scheduled for it
		//And it will have -9999999 for OfficerId,JuvenileId,CaseFileId
		//this is where its getting updated to JCCALEVENTCONT
		if (pendingStatus)
		{
		    //When a first juvenile is scheduled for it, then we should be updating the existing context record with actual values
		    //At this point there should only be one object in existingCalEventsIter
		    CalendarEventContext contextToBeUpdated = existingCalEventsIter.next();
		    contextToBeUpdated.setProbationOfficerId(aCalContextOfficerId);
		    contextToBeUpdated.setJuvenileId(aCalContextJuvenileId);
		    contextToBeUpdated.setCaseFileId(aCalContextCaseFileId);

		    //Create ServiceEventAttendance with the JuvenileId of the context
		    ServiceEventAttendance att = new ServiceEventAttendance();
		    if (sre.getEventId() != null)
			att.setServiceEventId(sre.getEventId());
		    else
			att.setServiceEventId(scheduleEvent.getEventId());

		    att.setJuvenileId(aCalContextEvent.getJuvenileId());
		    att.setAttendanceStatusCd(PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED);
		}
		else
		{
		    //Empty list of Contexts to add to database
		    List<CalendarContextEvent> contextsToAdd = new ArrayList<CalendarContextEvent>();
		    boolean theContextExists = false;
		    while (existingCalEventsIter.hasNext())
		    {
			CalendarEventContext aExistingCalContextEvent = existingCalEventsIter.next();
			String existingContextOfficerId = aExistingCalContextEvent.getProbationOfficerId();
			String existingContextCaseFileId = aExistingCalContextEvent.getCaseFileId();
			String existingContextJuvenileId = aExistingCalContextEvent.getJuvenileId();

			//If a match is found, break out of loop and set theContextExists
			//to false so it context is slated to be added to database and
			//so that a ServiceEventAttendance is not created 
			if (existingContextOfficerId.equals(aCalContextOfficerId) && existingContextCaseFileId.equals(aCalContextCaseFileId) && existingContextJuvenileId.equals(aCalContextJuvenileId))
			{
			    theContextExists = true;
			    break;
			}
		    }
		    if (theContextExists == false)
		    {
			//Add CalendarEventContext to list "of those to be added"
			contextsToAdd.add(aCalContextEvent);

			//Create ServiceEventAttendance with the JuvenileId of the context
			ServiceEventAttendance att = new ServiceEventAttendance();
			if (sre.getEventId() != null)
			    att.setServiceEventId(sre.getEventId());
			else
			    att.setServiceEventId(scheduleEvent.getEventId());
			att.setJuvenileId(aCalContextEvent.getJuvenileId());
			att.setAttendanceStatusCd(PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED);
		    }
		    if ((contextsToAdd != null && contextsToAdd.size() > 0))
		    {
			CalendarContextEvent[] cx = new CalendarContextEvent[contextsToAdd.size()];
			contextsToAdd.toArray(cx);
			Integer calendarEventId = serv.getCalendarEventId();
			manager.addCalendarEventContexts(calendarEventId, cx);
		    }
		}
	    }
	}
	else
	{
	    // If the event type is UNSCHEDULED INTERVIEW, then event status should be set to
	    // COMPLETED. If the event type is SCHEDULED INTERVIEW, then event status should be
	    // set to SCHEDULED.
	    JuvenileEventTypeCode eventCode = JuvenileEventTypeCode.find(createEvent.getEventTypeId());
	    if (eventCode.getCode().equals(UIConstants.INTERVIEW_UNSCHEDULED_CODE))
	    {
		createEvent.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED);
	    }
	    else
	    {
		createEvent.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_SCHEDULED);
	    }

	    CreateInterviewEvent createInterviewEvent = scheduleEvent.getCreateInterviewEvent();
	    ICalendarRecurrenceType recurr = new OneRecurrence();
	    String eventSeriesId = null;

	    Iterator<CalendarServiceEventResponseEvent> events = createEvent.getEvents().iterator();
	    while (events.hasNext())
	    {
		CalendarServiceEventResponseEvent sre = events.next();
		// These need to be set since the dates need to be passed from
		// CalendarServiceEventResponseEvent
		createEvent.setStartDatetime(sre.getStartDatetime());
		createEvent.setEndDatetime(sre.getEndDatetime());

		// Defect JIMS200050467
		// Creating 2 objects in the same command where 1 of the object is dependent on
		// the first object oid to be created, may fail.
		// Therefore, 2 separate commands will be called to handle this situation.

		CalendarEvent calEvent = null;
		ServiceEvent serviceEvent = null;
		TimeZone tz = Calendar.getInstance().getTimeZone();

		if (eventSeriesId == null || eventSeriesId.length() == 0)
		{
		    List col = (List) manager.saveCalendarEvent(createEvent, recurr, tz);
		    serviceEvent = this.saveServiceEvent(manager, col, createEvent, logonId);
		    sre.setEventId(serviceEvent.getOID()); // # U.S.30339 changes
		}
		else
		{
		    calEvent = manager.saveCalendarEventToExistingSeries(createEvent, eventSeriesId, tz);
		    List col = new ArrayList();
		    col.add(calEvent);
		    serviceEvent = this.saveServiceEvent(manager, col, createEvent, logonId);
		    sre.setEventId(serviceEvent.getOID()); // # U.S.30339 changes
		}

		if (eventCategory.equals(UIConstants.INTERVIEW_SERVICE_TYPE))
		{
		    //Create Interview
		    createInterviewEvent.setInterviewDate(sre.getStartDatetime());

		    createInterviewEvent.setCalEventId(String.valueOf(serviceEvent.getCalendarEventId()));
		    String interviewId = saveInterviewEvent(createInterviewEvent);

		    //Update ServiceEvent with interviewId
		    serviceEvent.setInterviewId(interviewId);

		    //Return CalendarServiceEventResponseEvent
		    sre.setInterviewId(interviewId);
		    sre.setCalendarEventId(serviceEvent.getCalendarEventId());

		    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		    dispatch.postEvent(sre);
		}
	    }
	}
    }

    /*
     * 
     */
    public String saveInterviewEvent(CreateInterviewEvent evt)
    {
	Interview interview = Interview.createDetail(evt);
	new mojo.km.persistence.Home().bind(interview);

	return interview.getOID();
    }

    /*
     * 
     */
    private ServiceEvent saveServiceEvent(CalendarManager manager, List eventCollection, CreateCalendarServiceEventEvent createEvent, String logonId)
    {
	CalendarEvent calEvent = null;
	ServiceEvent se = null;
	if (eventCollection != null)
	{
	    Iterator<CalendarEvent> iter = eventCollection.iterator();
	    while (iter.hasNext())
	    {
		calEvent = iter.next();
	    }
	    Integer calendarEventId = new Integer(calEvent.getOID().toString());

	    se = new ServiceEvent();
	    se.setServiceEvent(createEvent, logonId, calendarEventId);
	    se.createOID();

	    CalendarContextEvent calContext = createEvent.getCalendarContextEvent();
	    if (calContext != null)
	    {
		manager.addCalendarEventContext(calendarEventId, calContext);

		ServiceEventAttendance att = new ServiceEventAttendance();
		att.setServiceEventId(se.getOIDId());
		att.setJuvenileId(calContext.getJuvenileId());
		att.setProgressNotes(createEvent.getProgressNotes());
		att.setDocument(createEvent.getDocument());

		// If the event type is UNSCHEDULED INTERVIEW, then attendance status
		// should be set to ATTENDED. If the event type is SCHEDULED INTERVIEW,
		// then the attendance status should be set to CONFIRMED.
		{
		    JuvenileEventTypeCode mycode = JuvenileEventTypeCode.find(createEvent.getEventTypeId());
		    if (mycode.getCode().equals(UIConstants.INTERVIEW_UNSCHEDULED_CODE) || mycode.getCode().equals(UIConstants.SCHOOL_ADJUDICATION) || mycode.getCode().equals(UIConstants.APPOINTMENT_LETTER))
		    {
			att.setAttendanceStatusCd(PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED);
		    }
		    else
		    {
			att.setAttendanceStatusCd(PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED);
		    }
		    if (mycode.getCode().equals(UIConstants.SCHOOL_ADJUDICATION))
		    {
			att.setDocTypeCd("SAN");
		    }
		    if (mycode.getCode().equals(UIConstants.FACILITY_PARENT_ORIENTATION))// user story changes for 12253
		    {
			att.setDocTypeCd("FPO");
		    }
		    if (mycode.getCode().equals(UIConstants.APPOINTMENT_LETTER))// user story changes for 11109
		    {
			att.setDocTypeCd("APL");
		    }
		}

		//<KISHORE>JIMS200059212 : MJCW: Document attendance for all int.types(PD)-KK
		// If we schedule an event from Calendar tab, the event will not have any attendees attached to it
		if (createEvent.getAdditionalAttendeeNames() != null && !createEvent.getAdditionalAttendeeNames().isEmpty())
		{
		    att.setAddlAttendees(createEvent.getAddlAttendees());

		    Iterator<AttendeeNameResponseEvent> attendeeNames = createEvent.getAdditionalAttendeeNames().iterator();
		    while (attendeeNames.hasNext())
		    {
			AttendeeNameResponseEvent attendeeName = attendeeNames.next();
			AdditionalAttendee attendee = new AdditionalAttendee();
			attendee.setFirstName(attendeeName.getFirstName());
			attendee.setMiddleName(attendeeName.getMiddleName());
			attendee.setLastName(attendeeName.getLastName());
			attendee.setJuvenileId(calContext.getJuvenileId());
			att.insertAddlAttendeeNames(attendee);
		    }
		}
		att.createOID();
	    }
	}

	return se;
    }

    /**
     * @param event
     * @roseuid 447F49B50109
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * @param event
     * @roseuid 447F49B5010B
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * @param anObject
     * @roseuid 447F49B5010D
     */
    public void update(Object anObject)
    {
    }
}
