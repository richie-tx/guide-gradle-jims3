package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

/**
 * This is a bean.
 *
 */
public class DeleteProductionSupportCalendarEventsEvent extends RequestEvent
{
	private Integer calendarEventId;
	private String juvenileId;
	private String serviceEventId;
	
	public DeleteProductionSupportCalendarEventsEvent() {
		
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

	public String getJuvenileId()
	{
	    return juvenileId;
	}

	public void setJuvenileId(String juvenileId)
	{
	    this.juvenileId = juvenileId;
	}

	public String getServiceEventId()
	{
	    return serviceEventId;
	}

	public void setServiceEventId(String serviceEventId)
	{
	    this.serviceEventId = serviceEventId;
	}	
	
}
