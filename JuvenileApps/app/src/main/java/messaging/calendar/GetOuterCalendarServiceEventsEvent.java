package messaging.calendar;

/**
 * @author Jim Fisher
 *
 */
public class GetOuterCalendarServiceEventsEvent extends GetCalendarServiceEventsEvent
{
    public GetOuterCalendarServiceEventsEvent()
	{
		setRetriever("pd.supervision.calendar.OuterServiceEventRetriever");
	}
}
