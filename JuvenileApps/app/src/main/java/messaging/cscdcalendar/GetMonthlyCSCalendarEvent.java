//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\GetMonthlyCSCalendarEvent.java

package messaging.cscdcalendar;

import messaging.calendar.GetCalendarEventsEvent;

public class GetMonthlyCSCalendarEvent extends GetCalendarEventsEvent {

	private String currentContext;

	private String superviseeId;

	private int positionId;

	/**
	 * @roseuid 479A0E200396
	 */
	public GetMonthlyCSCalendarEvent() {

	}

	public String getCurrentContext() {
		return currentContext;
	}

	public void setCurrentContext(String currentContext) {
		this.currentContext = currentContext;
	}

	
	/**
	 * @return Returns the positionId.
	 */
	public int getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId The positionId to set.
	 */
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}

	/**
	 * @param superviseeId The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

}
