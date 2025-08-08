//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetCalendarServiceEventsEvent.java

package messaging.calendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetRecurringServiceEventsEvent extends RequestEvent
{			
	private Date startDatetime;
	private Date endDatetime;
	private String status;
	private String location;
	
	private String recurrencePattern;
	
	//recurrence type
	private boolean dailyRecurrence;
	private boolean weeklyRecurrence;
	private boolean monthlyRecurrence;
	private boolean yearlyRecurrence;
	
	//common parameter
	private Integer frequencyPattern;

	//daily recurrence parameters
	private boolean dailyEveryWeekDay;
	
	//weekly recurrence parameters	
	private String weeklyDay[];
	
	//monthly recurrence parameters
	private boolean monthlyRecurrenceType;
	private Integer monthlyDay;
	private Integer monthlyWeekNumber;
	private Integer monthlyWeekDay;
	
	//yearly recurrence parameters	
	private boolean yearlyRecurrenceType;
	private Integer yearlyMonthNumber;
	private Integer yearlyDay;
	private Integer yearlyWeekNumber;
	private Integer yearlyWeekDay;
	
	//range of recurrence
	private boolean numberedRecurrenceRange;
	private Integer totalOccurrences;
	private Date recurrenceEndDate;
	

	/**
	 * @return Returns the dailyEveryWeekDay.
	 */
	public boolean isDailyEveryWeekDay() {
		return dailyEveryWeekDay;
	}
	/**
	 * @param dailyEveryWeekDay The dailyEveryWeekDay to set.
	 */
	public void setDailyEveryWeekDay(boolean dailyEveryWeekDay) {
		this.dailyEveryWeekDay = dailyEveryWeekDay;
	}
	/**
	 * @return Returns the dailyRecurrence.
	 */
	public boolean isDailyRecurrence() {
		return dailyRecurrence;
	}
	/**
	 * @param dailyRecurrence The dailyRecurrence to set.
	 */
	public void setDailyRecurrence(boolean dailyRecurrence) {
		this.dailyRecurrence = dailyRecurrence;
	}
	/**
	 * @return Returns the endDatetime.
	 */
	public Date getEndDatetime() {
		return endDatetime;
	}
	/**
	 * @param endDatetime The endDatetime to set.
	 */
	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
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
	 * @return Returns the location.
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return Returns the monthlyDay.
	 */
	public Integer getMonthlyDay() {
		return monthlyDay;
	}
	/**
	 * @param monthlyDay The monthlyDay to set.
	 */
	public void setMonthlyDay(Integer monthlyDay) {
		this.monthlyDay = monthlyDay;
	}
	/**
	 * @return Returns the monthlyRecurrence.
	 */
	public boolean isMonthlyRecurrence() {
		return monthlyRecurrence;
	}
	/**
	 * @param monthlyRecurrence The monthlyRecurrence to set.
	 */
	public void setMonthlyRecurrence(boolean monthlyRecurrence) {
		this.monthlyRecurrence = monthlyRecurrence;
	}
	/**
	 * @return Returns the monthlyRecurrenceType.
	 */
	public boolean isMonthlyRecurrenceType() {
		return monthlyRecurrenceType;
	}
	/**
	 * @param monthlyRecurrenceType The monthlyRecurrenceType to set.
	 */
	public void setMonthlyRecurrenceType(boolean monthlyRecurrenceType) {
		this.monthlyRecurrenceType = monthlyRecurrenceType;
	}
	/**
	 * @return Returns the monthlyWeekDay.
	 */
	public Integer getMonthlyWeekDay() {
		return monthlyWeekDay;
	}
	/**
	 * @param monthlyWeekDay The monthlyWeekDay to set.
	 */
	public void setMonthlyWeekDay(Integer monthlyWeekDay) {
		this.monthlyWeekDay = monthlyWeekDay;
	}
	/**
	 * @return Returns the monthlyWeekNumber.
	 */
	public Integer getMonthlyWeekNumber() {
		return monthlyWeekNumber;
	}
	/**
	 * @param monthlyWeekNumber The monthlyWeekNumber to set.
	 */
	public void setMonthlyWeekNumber(Integer monthlyWeekNumber) {
		this.monthlyWeekNumber = monthlyWeekNumber;
	}
	/**
	 * @return Returns the numberedRecurrenceRange.
	 */
	public boolean isNumberedRecurrenceRange() {
		return numberedRecurrenceRange;
	}
	/**
	 * @param numberedRecurrenceRange The numberedRecurrenceRange to set.
	 */
	public void setNumberedRecurrenceRange(boolean numberedRecurrenceRange) {
		this.numberedRecurrenceRange = numberedRecurrenceRange;
	}
	/**
	 * @return Returns the recurrenceEndDate.
	 */
	public Date getRecurrenceEndDate() {
		return recurrenceEndDate;
	}
	/**
	 * @param recurrenceEndDate The recurrenceEndDate to set.
	 */
	public void setRecurrenceEndDate(Date recurrenceEndDate) {
		this.recurrenceEndDate = recurrenceEndDate;
	}
	/**
	 * @return Returns the startDatetime.
	 */
	public Date getStartDatetime() {
		return startDatetime;
	}
	/**
	 * @param startDatetime The startDatetime to set.
	 */
	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the totalOccurrences.
	 */
	public Integer getTotalOccurrences() {
		return totalOccurrences;
	}
	/**
	 * @param totalOccurrences The totalOccurrences to set.
	 */
	public void setTotalOccurrences(Integer totalOccurrences) {
		this.totalOccurrences = totalOccurrences;
	}
	/**
	 * @return Returns the weeklyDay.
	 */
	public String[] getWeeklyDay() {
		return weeklyDay;
	}
	/**
	 * @param weeklyDay The weeklyDay to set.
	 */
	public void setWeeklyDay(String[] weeklyDay) {
		this.weeklyDay = weeklyDay;
	}
	/**
	 * @return Returns the weeklyRecurrence.
	 */
	public boolean isWeeklyRecurrence() {
		return weeklyRecurrence;
	}
	/**
	 * @param weeklyRecurrence The weeklyRecurrence to set.
	 */
	public void setWeeklyRecurrence(boolean weeklyRecurrence) {
		this.weeklyRecurrence = weeklyRecurrence;
	}
	/**
	 * @return Returns the yearlyDay.
	 */
	public Integer getYearlyDay() {
		return yearlyDay;
	}
	/**
	 * @param yearlyDay The yearlyDay to set.
	 */
	public void setYearlyDay(Integer yearlyDay) {
		this.yearlyDay = yearlyDay;
	}
	/**
	 * @return Returns the yearlyMonthNumber.
	 */
	public Integer getYearlyMonthNumber() {
		return yearlyMonthNumber;
	}
	/**
	 * @param yearlyMonthNumber The yearlyMonthNumber to set.
	 */
	public void setYearlyMonthNumber(Integer yearlyMonthNumber) {
		this.yearlyMonthNumber = yearlyMonthNumber;
	}
	/**
	 * @return Returns the yearlyRecurrence.
	 */
	public boolean isYearlyRecurrence() {
		return yearlyRecurrence;
	}
	/**
	 * @param yearlyRecurrence The yearlyRecurrence to set.
	 */
	public void setYearlyRecurrence(boolean yearlyRecurrence) {
		this.yearlyRecurrence = yearlyRecurrence;
	}
	/**
	 * @return Returns the yearlyRecurrenceType.
	 */
	public boolean isYearlyRecurrenceType() {
		return yearlyRecurrenceType;
	}
	/**
	 * @param yearlyRecurrenceType The yearlyRecurrenceType to set.
	 */
	public void setYearlyRecurrenceType(boolean yearlyRecurrenceType) {
		this.yearlyRecurrenceType = yearlyRecurrenceType;
	}
	/**
	 * @return Returns the yearlyWeekDay.
	 */
	public Integer getYearlyWeekDay() {
		return yearlyWeekDay;
	}
	/**
	 * @param yearlyWeekDay The yearlyWeekDay to set.
	 */
	public void setYearlyWeekDay(Integer yearlyWeekDay) {
		this.yearlyWeekDay = yearlyWeekDay;
	}
	/**
	 * @return Returns the yearlyWeekNumber.
	 */
	public Integer getYearlyWeekNumber() {
		return yearlyWeekNumber;
	}
	/**
	 * @param yearlyWeekNumber The yearlyWeekNumber to set.
	 */
	public void setYearlyWeekNumber(Integer yearlyWeekNumber) {
		this.yearlyWeekNumber = yearlyWeekNumber;
	}
	/**
	 * @return Returns the recurrencePattern.
	 */
	public String getRecurrencePattern() {
		return recurrencePattern;
	}
	/**
	 * @param recurrencePattern The recurrencePattern to set.
	 */
	public void setRecurrencePattern(String recurrencePattern) {
		this.recurrencePattern = recurrencePattern;
	}
}