//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\GetCSGroupOfficeVisitEvent.java

package messaging.cscdcalendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;


public class GetCSGroupOfficeVisitEvent extends RequestEvent {
   
	private Date eventDate;
	private String eventName;
	private String positionId;
   
	/**
	 * @roseuid 479A0E20004B
	 */
	public GetCSGroupOfficeVisitEvent() {
    
	}
   
	/**
	 * @param eventDate
	 * @roseuid 4798EEA50116
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
   
	/**
	 * @return Date
	 * @roseuid 4798EEA50118
	 */
	public Date getEventDate() {
		return eventDate;
	}
   
	/**
	 * @param eventName
	 * @roseuid 4798EEA50125
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
   
	/**
	 * @return String
	 * @roseuid 4798EEA50127
	 */
	public String getEventName() {
		return eventName;
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
	
}
