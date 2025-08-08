//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\GetCSOtherEventsEvent.java

package messaging.cscdcalendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;


public class GetCSOtherEventsEvent extends RequestEvent {
   
	private String currentContext;
	private Date eventDate;
	private String positionId;
	private String superviseeId;
   
	/**
	 * @roseuid 479A0E20028D
	 */
	public GetCSOtherEventsEvent() {
    
	}
   
	/**
	 * @param currentContext
	 * @roseuid 4798EE8A027D
	 */
	public void setCurrentContext(String currentContext) {
		this.currentContext = currentContext;
	}
   
	/**
	 * @return String
	 * @roseuid 4798EE8A027F
	 */
	public String getCurrentContext() {
		return currentContext;
	}
   
	/**
	 * @param eventDate
	 * @roseuid 4798EE8A028E
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
   
	/**
	 * @return Date
	 * @roseuid 4798EE8A0290
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
