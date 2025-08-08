//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\GetCSEventTypesEvent.java

package messaging.cscdcalendar;

import mojo.km.messaging.RequestEvent;


public class GetCSEventTypesEvent extends RequestEvent {
   
	private String context;
   
	/**
	 * @roseuid 479A0E1F028D
	 */
	public GetCSEventTypesEvent() {
    
	}
	
	
	/**
	 * @return Returns the context.
	 */
	public String getContext() {
		return context;
	}
	/**
	 * @param context The context to set.
	 */
	public void setContext(String context) {
		this.context = context;
	}
	
}
