/*
 * Created on Nov 30, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.calendar.reply;

import java.util.Date;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ICalendarResponseEvent
{
	Date getStartDatetime();
	Date getEndDatetime();
	String getSubject();
	String getBodyText();
	String getLocation();
	String getCalendarEventType();
	Integer getCalendarEventId();
	String getCreatedBy();
	String getStatus();
}
