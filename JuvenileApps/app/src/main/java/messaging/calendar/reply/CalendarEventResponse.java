/*
 * Created on Nov 30, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.calendar.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarEventResponse extends ResponseEvent implements ICalendarResponseEvent
{
	
	private Integer calendarEventId;
	private String calendarEventType;
	private String status;
	private Date startDatetime;
	private Date endDatetime;
	private String location;
	private String subject;
	private String bodyText;
	private String createdBy;
	private Date startTime;
	
	/* (non-Javadoc)
	 * @see mojo.km.messaging.calendaring.reply.ICalendarResponseEvent#getBodyText()
	 */
	public String getBodyText()
	{
		return bodyText;
	}
	
	public void setBodyText(String text) 
	{
		this.bodyText = text;
	}

	/* (non-Javadoc)
	 * @see mojo.km.messaging.calendaring.reply.ICalendarResponseEvent#getCalendarEventId()
	 */
	public Integer getCalendarEventId()
	{
		return calendarEventId;
	}
	
	public void setCalendarEventId(Integer id) 
	{
		this.calendarEventId = id;
	}

	/* (non-Javadoc)
	 * @see mojo.km.messaging.calendaring.reply.ICalendarResponseEvent#getCalendarEventType()
	 */
	public String getCalendarEventType()
	{
		return calendarEventType;
	}
	
	public void setCalendarEventType(String type)
	{
		this.calendarEventType = type;
	}

	/* (non-Javadoc)
	 * @see mojo.km.messaging.calendaring.reply.ICalendarResponseEvent#getCreatedBy()
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}
	
	public void setCreatedBy(String created) 
	{
		this.createdBy = created;
	}

	/* (non-Javadoc)
	 * @see mojo.km.messaging.calendaring.reply.ICalendarResponseEvent#getEndDateTime()
	 */
	public Date getEndDatetime()
	{
		return endDatetime;
	}
	
	public void setEndDatetime(Date time)
	{
		this.endDatetime = time;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/* (non-Javadoc)
	 * @see mojo.km.messaging.calendaring.reply.ICalendarResponseEvent#getLocation()
	 */
	public String getLocation()
	{		
		return location;
	}
	
	public void setLocation(String loc) 
	{
		this.location = loc;
	}

	/* (non-Javadoc)
	 * @see mojo.km.messaging.calendaring.reply.ICalendarResponseEvent#getStartDatetime()
	 */
	public Date getStartDatetime()
	{
		return startDatetime;
	}
	
	public void setStartDatetime(Date time)
	{
		this.startDatetime = time;
	}

	/* (non-Javadoc)
	 * @see mojo.km.messaging.calendaring.reply.ICalendarResponseEvent#getSubject()
	 */
	public String getSubject()
	{
		return subject;
	}
	
	public void setSubject(String subj)
	{
		this.subject = subj;
	}

	/* (non-Javadoc)
	 * @see mojo.km.messaging.calendaring.reply.ICalendarResponseEvent#getStatus()
	 */
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String stat)
	{
		this.status = stat;
	}
}
