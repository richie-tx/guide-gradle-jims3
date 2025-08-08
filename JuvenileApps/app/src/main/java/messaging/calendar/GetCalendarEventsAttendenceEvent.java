/*
 * Created on Dec 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.calendar;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetCalendarEventsAttendenceEvent extends GetCalendarEventsEvent
{
	/**
	 * Constructor is used to set the default retriever for this event.  
	 * It can be changed later if required. 
	 *
	 */
	public GetCalendarEventsAttendenceEvent() {
		setRetriever("pd.common.calendar.sample.CalendarEventAttendenceRetriever");
	}
}
