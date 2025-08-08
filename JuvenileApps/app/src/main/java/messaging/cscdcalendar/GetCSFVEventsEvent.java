// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\GetCSFVEventsEvent.java

package messaging.cscdcalendar;

import java.util.Date;

import messaging.calendar.GetCalendarEventsEvent;

public class GetCSFVEventsEvent extends GetCalendarEventsEvent {

	private String currentContext;

	private String positionId;

	private String superviseeId;

	private String fvIteneraryId;

	private Date eventDate;

	/**
	 * @roseuid 479A0E1F0367
	 */
	public GetCSFVEventsEvent() {

	}

	/**
	 * @return Returns the currentContext.
	 */
	public String getCurrentContext() {
		return currentContext;
	}

	/**
	 * @param currentContext
	 *            The currentContext to set.
	 */
	public void setCurrentContext(String currentContext) {
		this.currentContext = currentContext;
	}

	/**
	 * @return Returns the positionId.
	 */
	public String getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId
	 *            The positionId to set.
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}

	/**
	 * @param superviseeId
	 *            The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	/**
	 * @return Returns the fvIteneraryId.
	 */
	public String getFvIteneraryId() {
		return fvIteneraryId;
	}

	/**
	 * @param fvIteneraryId
	 *            The fvIteneraryId to set.
	 */
	public void setFvIteneraryId(String fvIteneraryId) {
		this.fvIteneraryId = fvIteneraryId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
}
