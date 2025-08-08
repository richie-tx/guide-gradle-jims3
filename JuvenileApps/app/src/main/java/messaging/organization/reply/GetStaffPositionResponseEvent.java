/*
 * Created on Aug 31, 2007
 *
 */
package messaging.organization.reply;

import java.io.Serializable;

import messaging.transferobjects.CSCDStaffPositionTO;
import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_mdsouza
 *
 */
public class GetStaffPositionResponseEvent extends ResponseEvent implements Serializable  {

	
	CSCDStaffPositionTO staffPosition ; 
	
	
	/**
	 * @return Returns the staffPosition.
	 */
	public CSCDStaffPositionTO getStaffPosition() {
		return staffPosition;
	}
	/**
	 * @param staffPosition The staffPosition to set.
	 */
	public void setStaffPosition(CSCDStaffPositionTO staffPosition) {
		this.staffPosition = staffPosition;
	}
}
