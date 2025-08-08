/*
 * Created on Jan 10, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.calendar;

import java.util.Collection;
import java.util.Date;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ICalendarEvent
{
	Date getStartDatetime();
	void setStartDatetime(Date start);
	Date getEndDatetime();
	void setEndDatetime(Date end);
	Collection getCalendarAttributes();
	void setCalendarAttributes(Collection attributes);
	CalendarContextEvent getCalendarContextEvent();
	void setCalendarContextEvent(CalendarContextEvent calendarContextEvent);
	String getRetriever();
	void setRetriever(String retriever);
}
