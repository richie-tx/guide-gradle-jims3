/*
 * Created on Oct 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.common.calendar;

import java.util.Calendar;
import java.util.Date;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OneRecurrence implements ICalendarRecurrenceType
{
	private Date duration; 
	
	public String getRecurrenceType() 
	{
		return "ONCE";
	}
	
	public Date getRecurrenceEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
	
	public void setRecurrenceEndDate(Date endRecurrence) {}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarRecurrenceType#getTotalOccurences()
	 */
	public Integer getTotalOccurences() {
		 Integer i = new Integer(1);
		return i;
	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarRecurrenceType#setTotalOccurences(java.lang.Integer)
	 */
	public void setTotalOccurences(Integer totalOccurences) {
		// TODO Auto-generated method stub
		
	}
}
