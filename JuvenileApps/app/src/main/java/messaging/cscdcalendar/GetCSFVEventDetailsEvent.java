//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\GetCSFVEventDetailsEvent.java

package messaging.cscdcalendar;

import mojo.km.messaging.RequestEvent;


public class GetCSFVEventDetailsEvent extends RequestEvent {
   
	private String eventId;
   
	/**
	 * @roseuid 479A0E1F030A
	 */
	public GetCSFVEventDetailsEvent() {
    
	}
	
	
	/**
	 * @return Returns the eventId.
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * @param eventId The eventId to set.
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
}
