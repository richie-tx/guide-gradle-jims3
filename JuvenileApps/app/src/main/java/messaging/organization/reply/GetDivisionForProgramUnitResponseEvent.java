/*
 * Created on Aug 31, 2007
 *
 */
package messaging.organization.reply;

import java.io.Serializable;

import messaging.transferobjects.OrganizationTO;
import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_mdsouza
 *
 */
public class GetDivisionForProgramUnitResponseEvent extends ResponseEvent implements Serializable {

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
