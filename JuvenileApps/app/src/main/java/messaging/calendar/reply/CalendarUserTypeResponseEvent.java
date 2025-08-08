/*
 * Created on Mar 19, 2007
 *
 */
package messaging.calendar.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author C_NRaveendran
 *
 */
public class CalendarUserTypeResponseEvent extends ResponseEvent {
	private String calendarType;
	private String officerId;
	private String officerLastName;
	private String officerFirstName;
	private String officerMiddleName;
		
	/**
	 * @return Returns the calendarType.
	 */
	public String getCalendarType() {
		return calendarType;
	}
	/**
	 * @param calendarType The calendarType to set.
	 */
	public void setCalendarType(String calendarType) {
		this.calendarType = calendarType;
	}

	/**
	 * @return Returns the officerId.
	 */
	public String getOfficerId() {
		return officerId;
	}
	/**
	 * @param officerId The officerId to set.
	 */
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
	/**
	 * @return Returns the officerFirstName.
	 */
	public String getOfficerFirstName() {
		return officerFirstName;
	}
	/**
	 * @param officerFirstName The officerFirstName to set.
	 */
	public void setOfficerFirstName(String officerFirstName) {
		this.officerFirstName = officerFirstName;
	}
	/**
	 * @return Returns the officerLastName.
	 */
	public String getOfficerLastName() {
		return officerLastName;
	}
	/**
	 * @param officerLastName The officerLastName to set.
	 */
	public void setOfficerLastName(String officerLastName) {
		this.officerLastName = officerLastName;
	}
	/**
	 * @return Returns the officerMiddleName.
	 */
	public String getOfficerMiddleName() {
		return officerMiddleName;
	}
	/**
	 * @param officerMiddleName The officerMiddleName to set.
	 */
	public void setOfficerMiddleName(String officerMiddleName) {
		this.officerMiddleName = officerMiddleName;
	}
}
