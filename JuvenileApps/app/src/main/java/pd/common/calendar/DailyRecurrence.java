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
public class DailyRecurrence implements ICalendarRecurrenceType
{
	private Date duration;
	private Integer totalOccurences;
	private Integer frequencyPattern; // as in every 2 days
	private boolean everyWeekday;
	/**
	 * @return Returns the duration.
	 */
	public Date getDuration() {
		return duration;
	}
	/**
	 * @param duration The duration to set.
	 */
	public void setDuration(Date duration) {
		this.duration = duration;
	}
	/**
	 * @return Returns the everyWeekday.
	 */
	public boolean isEveryWeekday() {
		return everyWeekday;
	}
	/**
	 * @param everyWeekday The everyWeekday to set.
	 */
	public void setEveryWeekday(boolean everyWeekday) {
		this.everyWeekday = everyWeekday;
	}
	/**
	 * @return Returns the frequencyPattern.
	 */
	public Integer getFrequencyPattern() {
		return frequencyPattern;
	}
	/**
	 * @param frequencyPattern The frequencyPattern to set.
	 */
	public void setFrequencyPattern(Integer frequencyPattern) {
		this.frequencyPattern = frequencyPattern;
	}
	public String getRecurrenceType() 
	{
		return "DAILY";
	}
	
	public Date getRecurrenceEndDate() {
//		if (duration == null) {
//			return getDefault();
//		}
		return duration;
	}
	
	public void setRecurrenceEndDate(Date endRecurrence) {
		this.duration = endRecurrence;
	}
	
	private Date getDefault() {
		Calendar cal = Calendar.getInstance();
		int defaultDays = 5;
		if (this.totalOccurences != null) {
			if ((this.totalOccurences.intValue() > 0) && (this.frequencyPattern.intValue() > 0)) {
				defaultDays = (totalOccurences.intValue() * this.frequencyPattern.intValue());
			} else if (this.totalOccurences.intValue() > 0) {
				defaultDays = this.totalOccurences.intValue();
			}
		}
		
		cal.add(Calendar.DATE, defaultDays);
		return cal.getTime();
	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarRecurrenceType#getTotalOccurences()
	 */
	public Integer getTotalOccurences() {
		if (totalOccurences == null) {
			totalOccurences= new Integer(0);
		}
		return totalOccurences;
	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarRecurrenceType#setTotalOccurences(java.lang.Integer)
	 */
	public void setTotalOccurences(Integer totalOccurences) {
		this.totalOccurences = totalOccurences;
		
	}
}
