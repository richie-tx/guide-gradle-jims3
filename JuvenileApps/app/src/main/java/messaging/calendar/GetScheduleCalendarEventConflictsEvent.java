package messaging.calendar;

import java.util.List;

import messaging.calendar.GetCalendarEventsEvent;

public class GetScheduleCalendarEventConflictsEvent extends GetCalendarEventsEvent
{
    private List checkEvents;

    /**
     * Constructor is used to set the default retriever for this event. It can be changed later if
     * required.
     *  
     */
    public GetScheduleCalendarEventConflictsEvent()
    {
        setRetriever("pd.supervision.calendar.ServiceEventRetriever");
    }

    /**
     * @return Returns the checkEvents.
     */
    public List getCheckEvents()
    {
        return checkEvents;
    }

    /**
     * @param checkEvents
     *            The checkEvents to set.
     */
    public void setCheckEvents(List checkEvents)
    {
        this.checkEvents = checkEvents;
    }
}