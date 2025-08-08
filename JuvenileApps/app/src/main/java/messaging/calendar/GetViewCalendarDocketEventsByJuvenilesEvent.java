/*
 * Created on Apr 20, 2007
 *
 */
package messaging.calendar;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 * @author p_alcocer
 */
public class GetViewCalendarDocketEventsByJuvenilesEvent extends RequestEvent
{

	private List juvCasefileResponses;
	
	private String eventTypeId;

	private Date startDate;

	private Date endDate;

	
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * @param endDate
	 *            The endDate to set.
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * @return Returns the eventTypeId.
	 */
	public String getEventTypeId()
	{
		return eventTypeId;
	}

	/**
	 * @param eventTypeId
	 *            The eventTypeId to set.
	 */
	public void setEventTypeId(String eventTypeId)
	{
		this.eventTypeId = eventTypeId;
	}

	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * @param startDate
	 *            The startDate to set.
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * @return juvCasefileResponses
	 */
	public List getJuvCasefileResponses() {
		return juvCasefileResponses;
	}

	/**
	 * @param juvCasefileResponses
	 */
	public void setJuvCasefileResponses(List juvCasefileResponses) {
		this.juvCasefileResponses = juvCasefileResponses;
	}
	
}
