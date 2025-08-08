//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\GetCSOtherEventDetailsEvent.java

package messaging.cscdcalendar;

import mojo.km.messaging.RequestEvent;


public class GetCSOtherEventDetailsEvent extends RequestEvent {
   
	private String eventId;
   
	/**
	 * @roseuid 479A0E20022F
	 */
	public GetCSOtherEventDetailsEvent() {
    
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
