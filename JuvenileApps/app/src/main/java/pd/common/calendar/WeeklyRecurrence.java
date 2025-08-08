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
public class WeeklyRecurrence implements ICalendarRecurrenceType
{
	private Date duration;
	private Integer frequencyPattern; // as in every 2 Weeks 
	private Integer totalOccurences;
	//private Collection daysOfWeek; // use the java.util.calendar constants
							// 		SUNDAY= 1
							//		MONDAY=2
							//		TUESDAY=3
							//		WEDNESDAY=4
							//		THURSDAY=5
							//		FRIDAY=6	
							//		SATURDAY=7
	private boolean scheduleSunday;
	private boolean scheduleMonday;
	private boolean scheduleTuesday;
	private boolean scheduleWednesday;
	private boolean scheduleThursday;
	private boolean scheduleFriday;
	private boolean scheduleSaturday;
	
	
	public String getRecurrenceType() 
	{
		return "WEEKLY";
	}
	
	public Date getRecurrenceEndDate() {
	//	if (duration == null) {
	//		return getDefault();
	//	}
		return duration;
	}
	
	public void setRecurrenceEndDate(Date endRecurrence) {
		this.duration = endRecurrence;
	}
	
//	private Date getDefault() {
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, (7* this.getFrequencyPattern().intValue()) * this.getTotalOccurences().intValue() );
//		return cal.getTime();
//	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarRecurrenceType#getTotalOccurences()
	 */
	public Integer getTotalOccurences() {
//		if (totalOccurences == null) {
//			totalOccurences= new Integer(0);
//		}
		return totalOccurences;
	}
	/**
	 * @param totalOccurences The totalOccurences to set.
	 */
	public void setTotalOccurences(Integer totalOccurences) {
		this.totalOccurences = totalOccurences;
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
	 * @return Returns the frequencyPattern.
	 */
	public Integer getFrequencyPattern() {
		if(frequencyPattern == null){
			frequencyPattern = new Integer(1); 
		}
		return frequencyPattern;
	}
	/**
	 * @param frequencyPattern The frequencyPattern to set.
	 */
	public void setFrequencyPattern(Integer frequencyPattern) {
		this.frequencyPattern = frequencyPattern;
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
		this.scheduleWednesday = scheduleWednesday;
	}
}
