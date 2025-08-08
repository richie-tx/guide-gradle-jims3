//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\GetCSOfficeVisitsEvent.java

package messaging.cscdcalendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;


public class GetCSOfficeVisitsEvent extends RequestEvent {
   
	private String currentContext;
	private Date eventDate;
	private String positionId;
	private String superviseeId;
   
	/**
	 * @roseuid 479A0E200183
	 */
	public GetCSOfficeVisitsEvent() {
    
	}
   
	/**
	 * @param currentContext
	 * @roseuid 4798EEA4030A
	 */
	public void setCurrentContext(String currentContext) {
		this.currentContext = currentContext;
	}
   
	/**
	 * @return String
	 * @roseuid 4798EEA40319
	 */
	public String getCurrentContext() {
		return currentContext;
	}
   
	/**
	 * @param eventDate
	 * @roseuid 4798EEA40329
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
   
	/**
	 * @return Date
	 * @roseuid 4798EEA4032B
	 */
	public Date getEventDate() {
		return eventDate;
	}
	
	
	/**
	 * @return Returns the positionId.
	 */
	public String getPositionId() {
		return positionId;
	}
	/**
	 * @param positionId The positionId to set.
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
	 * @param superviseeId The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
}
