/*
 * Created on Aug 24, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.common.calendar;

import java.util.Calendar;
import java.util.Date;

/**
 * @author racooper
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class YearlyRecurrence implements ICalendarRecurrenceType {
	private Date duration;
	private Integer totalOccurences;
	private Integer dayOfMonth; 
	private int month;
	
	/**
	 * @return Returns the month.
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month The month to set.
	 */
	public void setMonth(int month) {
		this.month = month;
	}
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
	
	private boolean scheduleJanuary; // third drop down goes with first and second 
	private boolean scheduleFebruary;
	private boolean scheduleMarch;
	private boolean scheduleApril;
	private boolean scheduleMay;
	private boolean scheduleJune;
	private boolean scheduleJuly;
	private boolean scheduleAugust;
	private boolean scheduleSeptember;
	private boolean scheduleOctober;
	private boolean scheduleNovember;
	private boolean scheduleDecember;
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
	 * @return Returns the september.
	 */
	 
	 
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
	/**
	 * @return Returns the thursday.
	 */
	 
	  
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarRecurrenceType#getRecurrenceType()
	 */
	public String getRecurrenceType() {
		return "YEARLY";
	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarRecurrenceType#getRecurrenceEndDate()
	 */
	public Date getRecurrenceEndDate() {
		
		return duration;
	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarRecurrenceType#setRecurrenceEndDate(java.util.Date)
	 */
	public void setRecurrenceEndDate(Date endRecurrence) {
		this.duration = endRecurrence;

	}
	private Date getDefault() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
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
	 
	 
	public boolean isScheduleApril() {
		return scheduleApril;
	}
	/**
	 * @param scheduleApril The scheduleApril to set.
	 */
	public void setScheduleApril(boolean scheduleApril) {
		this.month = Calendar.APRIL;
		this.scheduleApril = scheduleApril;
	}
	/**
	 * @return Returns the scheduleAugust.
	 */
	public boolean isScheduleAugust() {
		return scheduleAugust;
	}
	/**
	 * @param scheduleAugust The scheduleAugust to set.
	 */
	public void setScheduleAugust(boolean scheduleAugust) {
		this.month = Calendar.AUGUST;
		this.scheduleAugust = scheduleAugust;
	}
	/**
	 * @return Returns the scheduleDecember.
	 */
	public boolean isScheduleDecember() {
		return scheduleDecember;
	}
	/**
	 * @param scheduleDecember The scheduleDecember to set.
	 */
	public void setScheduleDecember(boolean scheduleDecember) {
		this.month = Calendar.DECEMBER;
		this.scheduleDecember = scheduleDecember;
	}
	/**
	 * @return Returns the scheduleFebruary.
	 */
	public boolean isScheduleFebruary() {
		return scheduleFebruary;
	}
	/**
	 * @param scheduleFebruary The scheduleFebruary to set.
	 */
	public void setScheduleFebruary(boolean scheduleFebruary) {
		this.month = Calendar.FEBRUARY;
		this.scheduleFebruary = scheduleFebruary;
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
	 * @return Returns the scheduleJanuary.
	 */
	public boolean isScheduleJanuary() {
		return scheduleJanuary;
	}
	/**
	 * @param scheduleJanuary The scheduleJanuary to set.
	 */
	public void setScheduleJanuary(boolean scheduleJanuary) {
		this.month = Calendar.JANUARY;
		this.scheduleJanuary = scheduleJanuary;
	}
	/**
	 * @return Returns the scheduleJuly.
	 */
	public boolean isScheduleJuly() {
		return scheduleJuly;
	}
	/**
	 * @param scheduleJuly The scheduleJuly to set.
	 */
	public void setScheduleJuly(boolean scheduleJuly) {
		this.month = Calendar.JULY;
		this.scheduleJuly = scheduleJuly;
	}
	/**
	 * @return Returns the scheduleJune.
	 */
	public boolean isScheduleJune() {
		return scheduleJune;
	}
	/**
	 * @param scheduleJune The scheduleJune to set.
	 */
	public void setScheduleJune(boolean scheduleJune) {
		this.month = Calendar.JUNE;
		this.scheduleJune = scheduleJune;
	}
	/**
	 * @return Returns the scheduleMarch.
	 */
	public boolean isScheduleMarch() {
		return scheduleMarch;
	}
	/**
	 * @param scheduleMarch The scheduleMarch to set.
	 */
	public void setScheduleMarch(boolean scheduleMarch) {
		this.month = Calendar.MARCH;
		this.scheduleMarch = scheduleMarch;
	}
	/**
	 * @return Returns the scheduleMay.
	 */
	public boolean isScheduleMay() {
		 
		return scheduleMay;
	}
	/**
	 * @param scheduleMay The scheduleMay to set.
	 */
	public void setScheduleMay(boolean scheduleMay) {
		this.month = Calendar.MAY;
		this.scheduleMay = scheduleMay;
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
	 * @return Returns the scheduleNovember.
	 */
	public boolean isScheduleNovember() {
		return scheduleNovember;
	}
	/**
	 * @param scheduleNovember The scheduleNovember to set.
	 */
	public void setScheduleNovember(boolean scheduleNovember) {
		this.month = Calendar.NOVEMBER;
		this.scheduleNovember = scheduleNovember;
	}
	/**
	 * @return Returns the scheduleOctober.
	 */
	public boolean isScheduleOctober() {
		return scheduleOctober;
	}
	/**
	 * @param scheduleOctober The scheduleOctober to set.
	 */
	public void setScheduleOctober(boolean scheduleOctober) {
		this.month = Calendar.OCTOBER;
		this.scheduleOctober = scheduleOctober;
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
	 * @return Returns the scheduleSeptember.
	 */
	public boolean isScheduleSeptember() {
		return scheduleSeptember;
	}
	/**
	 * @param scheduleSeptember The scheduleSeptember to set.
	 */
	public void setScheduleSeptember(boolean scheduleSeptember) {
		this.month = Calendar.SEPTEMBER;
		this.scheduleSeptember = scheduleSeptember;
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
		setDayOfWeek(Calendar.WEDNESDAY);
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
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public int getDayOfWeek() {
		return dayOfWeek;
	}
}
