/*
 * Created on Nov 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.calendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarEventRequestEvent extends RequestEvent
{
	private Integer calendarEventId;
	private String calendarEventType;
	private Date startDatetime;
	private Date endDatetime;
	private String subject;
	private String bodyText;
	private String location;
	private String status;
	private String createdBy;
	
	 
	
	/**
	 * @return bodyText
	 */
	public String getBodyText()
	{
		return bodyText;
	}

	/**
	 * @return calendarEventId
	 */
	public Integer getCalendarEventId()
	{
		return calendarEventId;
	}

	/** 
	 * @return calendarEventType
	 */
	public String getCalendarEventType()
	{
		return calendarEventType;
	}

	/**
	 * @return createdBy
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * @return endDatetime
	 */
	public Date getEndDatetime()
	{
		return endDatetime;
	}

	/**
	 * @return location
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * @return startDatetime
	 */
	public Date getStartDatetime()
	{
		return startDatetime;
	}

	/**
	 * @return status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @return subject
	 */
	public String getSubject()
	{
		return subject;
	}

	/**
	 * @param string
	 */
	public void setBodyText(String string)
	{
		bodyText = string;
	}

	/**
	 * @param integer
	 */
	public void setCalendarEventId(Integer integer)
	{
		calendarEventId = integer;
	}

	/**
	 * @param string
	 */
	public void setCalendarEventType(String string)
	{
		calendarEventType = string;
	}

	/**
	 * @param string
	 */
	public void setCreatedBy(String string)
	{
		createdBy = string;
	}

	/**
	 * @param date
	 */
	public void setEndDatetime(Date date)
	{
		endDatetime = date;
	}

	/**
	 * @param string
	 */
	public void setLocation(String string)
	{
		location = string;
	}

	/**
	 * @param date
	 */
	public void setStartDatetime(Date date)
	{
		startDatetime = date;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
		status = string;
	}

	/**
	 * @param string
	 */
	public void setSubject(String string)
	{
		subject = string;
	}

}
