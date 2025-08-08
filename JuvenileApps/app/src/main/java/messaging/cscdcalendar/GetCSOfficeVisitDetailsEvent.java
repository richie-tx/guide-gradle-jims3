//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\GetCSOfficeVisitDetailsEvent.java

package messaging.cscdcalendar;

import mojo.km.messaging.RequestEvent;


public class GetCSOfficeVisitDetailsEvent extends RequestEvent {
   
	private String eventId;
   
	/**
	 * @roseuid 479A0E2000F6
	 */
	public GetCSOfficeVisitDetailsEvent() {
    
	}
   
	/**
	 * @param eventId
	 * @roseuid 4798EEA5001C
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
   
	/**
	 * @return String
	 * @roseuid 4798EEA5001E
	 */
	public String getEventId() {
		return eventId;
	}
	
	
}
