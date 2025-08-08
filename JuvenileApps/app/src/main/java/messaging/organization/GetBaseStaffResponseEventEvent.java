/*
 * Created on Aug 31, 2007
 *
 */
package messaging.organization;

import messaging.transferobjects.CSCDStaffPositionTO;
import mojo.km.messaging.RequestEvent;

/**
 * @author cc_mdsouza
 *
 */
public class GetBaseStaffResponseEventEvent extends RequestEvent {

	
	CSCDStaffPositionTO staffPositionTO ; 
	
	
	
	
	/**
	 * @return Returns the staffPositionTO.
	 */
	public CSCDStaffPositionTO getStaffPositionTO() {
		return staffPositionTO;
	}
	/**
	 * @param staffPositionTO The staffPositionTO to set.
	 */
	public void setStaffPositionTO(CSCDStaffPositionTO staffPositionTO) {
		this.staffPositionTO = staffPositionTO;
	}
}
