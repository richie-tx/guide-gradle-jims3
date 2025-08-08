/*
 * Created on Oct 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.common.calendar;

import java.util.Date;
import messaging.calendar.ICalendarAttribute;
import mojo.km.messaging.IEvent;
import messaging.calendar.ICalendarContext;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ICalendarRetriever
{
	Date getStartDate();
	
	void setStartDate(Date startDate);
	
	Date getEndDate();
	
	void setEndDate(Date endDate);
	
	ICalendarContext[] getCalendarContexts();
	
	void setCalendarContexts(ICalendarContext[] contexts);
	
	ICalendarAttribute[] getCalendarAttributes();
	
	void setCalendarAttributes(ICalendarAttribute[] attributes);
	
	Object retrieve();	
	
	String buildWhereClause(IEvent event);
}
