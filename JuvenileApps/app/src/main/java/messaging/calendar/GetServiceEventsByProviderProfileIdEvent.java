/*
 * Created on Oct 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.calendar;

import mojo.km.messaging.RequestEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetServiceEventsByProviderProfileIdEvent extends RequestEvent {

	private int instructorId;
	
	public GetServiceEventsByProviderProfileIdEvent() {
		
	}

	
	/**
	 * @return Returns the instructorId.
	 */
	public int getInstructorId() {
		return instructorId;
	}
	/**
	 * @param instructorId The instructorId to set.
	 */
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
}
