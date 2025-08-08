/*
 * Created on Oct 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.common.calendar;

import java.util.Date;
import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarEventContextView extends PersistentObject
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
	private String timezone;
//	private String contextType;
//	private String contextId;
	
//	/**
//	* Properties for calendarEventContexts
//	* @referencedType pd.common.calendar.CalendarEventContext
//	* @detailerDoNotGenerate true
//	*/
//	private java.util.Collection calendarEventContexts = null;	
	
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
		return this.calendarEventId;
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
	public void setBodyText(String text)
	{
		bodyText = text;
	}

	/**
	 * @param string
	 */
	public void setCalendarEventId(Integer id)
	{
		this.calendarEventId = id;
	}

	/**
	 * @param string
	 */
	public void setCalendarEventType(String type)
	{
		calendarEventType = type;
	}

	/**
	 * @param string
	 */
	public void setCreatedBy(String created)
	{
		createdBy = created;
	}

	/**
	 * @param date
	 */
	public void setEndDatetime(Date end)
	{
		endDatetime = end;
	}

	/**
	 * @param string
	 */
	public void setLocation(String loc)
	{
		location = loc;
	}

	/**
	 * @param date
	 */
	public void setStartDatetime(Date start)
	{
		startDatetime = start;
	}

	/**
	 * @param string
	 */
	public void setStatus(String stat)
	{
		status = stat;
	}

	/**
	 * @param string
	 */
	public void setSubject(String subj)
	{
		subject = subj;
	}
	
	public String getTimeZone() 
	{
		return timezone;	
	}
	
	public void setTimeZone(String zone) 
	{
		timezone = zone;
	}
	
//	public void setContextType(String type) {
//		this.contextType = type;
//	}
//	
//	public String getContextType() {
//		return this.contextType;
//	}
//	
//	public void setContextId(String id) {
//		this.contextId = id;
//	}
//	
//	public String getContextId() {
//		return this.contextId;
//	}
	
	public static Iterator findAll() 
	{
		IHome home = new Home();
		Iterator iter = home.findAll(new mojo.km.persistence.AllQueryEvent(), CalendarEventContextView.class);
		return iter;
	}
}
