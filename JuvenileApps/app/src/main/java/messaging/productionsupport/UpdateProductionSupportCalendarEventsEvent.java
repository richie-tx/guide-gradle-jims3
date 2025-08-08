package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * This is a bean.
 *
 */
public class UpdateProductionSupportCalendarEventsEvent extends RequestEvent
{
	private String calendarEventId;
	private Date beginDate;
	private Date endDate;
	
	public UpdateProductionSupportCalendarEventsEvent() {
		
	}
	
	/**
	 * @return calendarEventId
	 */
	public String getCalendarEventId()
	{
		return calendarEventId;
	}
	/**
	 * @param integer
	 */
	public void setCalendarEventId(String eventId)
	{
		calendarEventId = eventId;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}	
	
}
