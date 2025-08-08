package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

/**
 * This is a bean.
 *
 */
public class GetProductionSupportCalendarEventsEvent extends RequestEvent
{
	private Integer calendarEventId;
	
	public GetProductionSupportCalendarEventsEvent() {
		
	}
	
	/**
	 * @return calendarEventId
	 */
	public Integer getCalendarEventId()
	{
		return calendarEventId;
	}
	/**
	 * @param integer
	 */
	public void setCalendarEventId(Integer integer)
	{
		calendarEventId = integer;
	}	
	
}
