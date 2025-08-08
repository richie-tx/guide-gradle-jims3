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
public class MonthlyRecurrence implements ICalendarRecurrenceType
{
	private Date duration;
	private Integer totalOccurences;
	private Integer frequencyPattern; // as in every 2 mONTHS 
	private Integer dayOfMonth; 
	private boolean first; // first drop down
	private boolean second;
	private boolean third;
	private boolean fourth;
	private boolean last;
	
	 // second drop down goes with first 
	
	
	private boolean scheduleSunday;
	private boolean scheduleMonday;
	private boolean scheduleTuesday;
	private boolean scheduleWednesday;
	private boolean scheduleThursday;
	private boolean scheduleFriday;
	private boolean scheduleSaturday;
	private int dayOfWeek;
	
	
	 
	/**
	 * @return Returns the dayOfMonth.
	 */
	public Integer getDayOfMonth() {
		return dayOfMonth;
	}
	/**
	 * @param dayOfMonth The dayOfMonth to set.
	 */
	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
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
	 * @return Returns the first.
	 */
	public boolean isFirst() {
		return first;
	}
	/**
	 * @param first The first to set.
	 */
	public void setFirst(boolean first) {
		this.first = first;
	}
	/**
	 * @return Returns the fourth.
	 */
	public boolean isFourth() {
		return fourth;
	}
	/**
	 * @param fourth The fourth to set.
	 */
	public void setFourth(boolean fourth) {
		this.fourth = fourth;
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
	
	/**
	 * @return Returns the last.
	 */
	public boolean isLast() {
		return last;
	}
	/**
	 * @param last The last to set.
	 */
	public void setLast(boolean last) {
		this.last = last;
	}
	
	
	/**
	 * @return Returns the second.
	 */
	public boolean isSecond() {
		return second;
	}
	/**
	 * @param second The second to set.
	 */
	public void setSecond(boolean second) {
		this.second = second;
	}
	
	/**
	 * @return Returns the third.
	 */
	public boolean isThird() {
		return third;
	}
	/**
	 * @param third The third to set.
	 */
	public void setThird(boolean third) {
		this.third = third;
	}
	
	
	 
	public String getRecurrenceType() 
	{
		return "MONTHLY";
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
		cal.add(Calendar.MONTH, 3);
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
	/**
	 * @param totalOccurences The totalOccurences to set.
	 */
	public void setTotalOccurences(Integer totalOccurences) {
		this.totalOccurences = totalOccurences;
	}
	/**
	 * @return Returns the scheduleFriday.
	 */
	public boolean isScheduleFriday() {
		
		return scheduleFriday;
	}
	/**
	 * @param scheduleFriday The scheduleFriday to set.
	 */
	public void setScheduleFriday(boolean scheduleFriday) {
		setDayOfWeek(Calendar.FRIDAY);
		this.scheduleFriday = scheduleFriday;
	}
	/**
	 * @return Returns the scheduleMonday.
	 */
	public boolean isScheduleMonday() {
		
		return scheduleMonday;
	}
	/**
	 * @param scheduleMonday The scheduleMonday to set.
	 */
	public void setScheduleMonday(boolean scheduleMonday) {
		setDayOfWeek(Calendar.MONDAY);
		this.scheduleMonday = scheduleMonday;
	}
	/**
	 * @return Returns the scheduleSaturday.
	 */
	public boolean isScheduleSaturday() {
		return scheduleSaturday;
	}
	/**
	 * @param scheduleSaturday The scheduleSaturday to set.
	 */
	public void setScheduleSaturday(boolean scheduleSaturday) {
		setDayOfWeek(Calendar.SATURDAY);
		this.scheduleSaturday = scheduleSaturday;
	}
	/**
	 * @return Returns the scheduleSunday.
	 */
	public boolean isScheduleSunday() {
		return scheduleSunday;
	}
	/**
	 * @param scheduleSunday The scheduleSunday to set.
	 */
	public void setScheduleSunday(boolean scheduleSunday) {
		setDayOfWeek(Calendar.SUNDAY);
		this.scheduleSunday = scheduleSunday;
	}
	/**
	 * @return Returns the scheduleThursday.
	 */
	public boolean isScheduleThursday() {
		return scheduleThursday;
	}
	/**
	 * @param scheduleThursday The scheduleThursday to set.
	 */
	public void setScheduleThursday(boolean scheduleThursday) {
		setDayOfWeek(Calendar.THURSDAY);
		this.scheduleThursday = scheduleThursday;
	}
	/**
	 * @return Returns the scheduleTuesday.
	 */
	public boolean isScheduleTuesday() {
		return scheduleTuesday;
	}
	/**
	 * @param scheduleTuesday The scheduleTuesday to set.
	 */
	public void setScheduleTuesday(boolean scheduleTuesday) {
		setDayOfWeek(Calendar.TUESDAY);
		this.scheduleTuesday = scheduleTuesday;
	}
	/**
	 * @return Returns the scheduleWednesday.
	 */
	public boolean isScheduleWednesday() {
		return scheduleWednesday;
	}
	/**
	 * @param scheduleWednesday The scheduleWednesday to set.
	 */
	public void setScheduleWednesday(boolean scheduleWednesday) {
		setDayOfWeek(Calendar.WEDNESDAY);
		this.scheduleWednesday = scheduleWednesday;
	}
	/**
	 * @return Returns the dayOfWeek.
	 */
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	/**
	 * @param dayOfWeek The dayOfWeek to set.
	 */
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
}
