/*
 * Created on Aug 31, 2007
 *
 */
package messaging.organization.reply;

import java.io.Serializable;

import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_mdsouza
 *
 */
public class GetBaseStaffResponseEventResponseEvent extends ResponseEvent implements Serializable
{
	
	CSCDSupervisionStaffResponseEvent cscdSupervisionStaffResponseEvent ; 
	
	
	

	/**
	 * @return Returns the cscdSupervisionStaffResponseEvent.
	 */
	public CSCDSupervisionStaffResponseEvent getCscdSupervisionStaffResponseEvent() {
		return cscdSupervisionStaffResponseEvent;
	}
	/**
	 * @param cscdSupervisionStaffResponseEvent The cscdSupervisionStaffResponseEvent to set.
	 */
	public void setCscdSupervisionStaffResponseEvent(CSCDSupervisionStaffResponseEvent cscdSupervisionStaffResponseEvent) {
		this.cscdSupervisionStaffResponseEvent = cscdSupervisionStaffResponseEvent;
	}
}
