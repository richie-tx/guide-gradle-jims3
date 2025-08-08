/*
 * Created on Oct 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.common.calendar;

import java.util.Date;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ICalendarRecurrenceType
{
	String getRecurrenceType();
	
	Date getRecurrenceEndDate();
	void setRecurrenceEndDate(Date endDate);
	Integer getTotalOccurences();
	void setTotalOccurences(Integer totalOccurences);
}
