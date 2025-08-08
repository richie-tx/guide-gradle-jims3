/*
 * Created on Aug 31, 2007
 *
 */
package messaging.organization;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_mdsouza
 *
 */
public class GetStaffPositionEvent extends RequestEvent {

	String staffPositionId ; 
	
	
	
	/**
	 * @return Returns the staffPositionId.
	 */
	public String getStaffPositionId() {
		return staffPositionId;
	}
	/**
	 * @param staffPositionId The staffPositionId to set.
	 */
	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
}
