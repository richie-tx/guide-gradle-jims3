package messaging.organization.reply;

import messaging.transferobjects.OrganizationTO;
import mojo.km.messaging.ResponseEvent;

public class OrganizationStructureResponseEvent extends ResponseEvent
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