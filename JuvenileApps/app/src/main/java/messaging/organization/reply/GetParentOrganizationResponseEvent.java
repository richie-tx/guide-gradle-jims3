/*
 * Created on Aug 20, 2007
 *
 */
package messaging.organization.reply;

import java.io.Serializable;

import messaging.transferobjects.OrganizationTO;
import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_mdsouza
 *
 * Return Event for the GetParentOrganizationEvent sent to the mojo framework 
 * 
 */
public class GetParentOrganizationResponseEvent extends ResponseEvent implements Serializable 
{

	
	OrganizationTO organizationTO ; 
	
	
	
	
	
	
	
	/**
	 * @return Returns the organizationTO.
	 */
	public OrganizationTO getOrganizationTO() {
		return organizationTO;
	}
	/**
	 * @param organizationTO The organizationTO to set.
	 */
	public void setOrganizationTO(OrganizationTO organizationTO) {
		this.organizationTO = organizationTO;
	}
}
