/*
 * Created on Dec 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.calendar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import messaging.calendar.ICalendarAttribute;
import mojo.km.messaging.RequestEvent;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetCalendarEventsEvent extends RequestEvent implements ICalendarEvent
{
	private Date startDatetime;
	private Date endDatetime;
	private CalendarContextEvent calendarContextEvent;
	private Collection calendarAttributes = new ArrayList();
	/* Default Retriever */
	private String retriever = "pd.common.calendar.CalendarEventRetriever";
	
	/**
	 * @return Collection
	 */
	public Collection getCalendarAttributes()
	{
		return calendarAttributes;
	}

	/**
	 * @return Collection
	 */
	public CalendarContextEvent getCalendarContextEvent()
	{
		return calendarContextEvent;
	}
	
	/**
	 * @return Date
	 */
	public Date getEndDatetime()
	{
		return endDatetime;
	}

	/**
	 * @return Date
	 */
	public Date getStartDatetime()
	{
		return startDatetime;
	}

	/**
	 * @param collection
	 */
	public void setCalendarAttributes(Collection collection)
	{
		calendarAttributes = collection;
	}
	
	/**
	 * Adds an calendar attribute to the list of constraints
	 * @param attribute
	 */
	public void addCalendarAttribute(ICalendarAttribute attribute)
	{
		calendarAttributes.add(attribute);
	}

	/**
	 * @param collection
	 */
	public void setCalendarContextEvent(CalendarContextEvent calendarContextEvent)
	{
		this.calendarContextEvent = calendarContextEvent;
	}

	/**
	 * @param date
	 */
	public void setEndDatetime(Date date)
	{
		endDatetime = date;
	}

	/**
	 * @param date
	 */
	public void setStartDatetime(Date date)
	{
		startDatetime = date;
	}

	/**
	 * @return String
	 */
	public String getRetriever()
	{
		return retriever;
	}

	/**
	 * @param string
	 */
	public void setRetriever(String string)
	{
		retriever = string;
	}

}
