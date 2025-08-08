//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\CreateCalendarServiceEventEvent.java

package messaging.calendar;

import messaging.interviewinfo.CreateInterviewEvent;
import mojo.km.messaging.RequestEvent;

public class CreateScheduleCalendarEventsEvent extends RequestEvent
{

    CreateCalendarServiceEventEvent createCalendarEvent;
    CreateInterviewEvent createInterviewEvent;
    String eventId;

    public String getEventId()
    {
	return eventId;
    }

    public void setEventId(String eventId)
    {
	this.eventId = eventId;
    }

    /**
     * @return Returns the createCalendarEvent.
     */
    public CreateCalendarServiceEventEvent getCreateCalendarEvent()
    {
	return createCalendarEvent;
    }

    /**
     * @param createCalendarEvent
     *            The createCalendarEvent to set.
     */
    public void setCreateCalendarEvent(CreateCalendarServiceEventEvent createCalendarEvent)
    {
	this.createCalendarEvent = createCalendarEvent;
    }

    /**
     * @return Returns the createInterviewEvent.
     */
    public CreateInterviewEvent getCreateInterviewEvent()
    {
	return createInterviewEvent;
    }

    /**
     * @param createInterviewEvent
     *            The createInterviewEvent to set.
     */
    public void setCreateInterviewEvent(CreateInterviewEvent createInterviewEvent)
    {
	this.createInterviewEvent = createInterviewEvent;
    }
}
