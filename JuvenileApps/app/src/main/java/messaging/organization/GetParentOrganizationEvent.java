/*
 * Created on Aug 20, 2007
 *
 */
package messaging.organization;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_mdsouza
 *
 * This event is used to query the Organization Service and retrieve the parent 
 * organization  object as a transfer object
 * 
 * The associated Response event is GetParentOrganizationResponseEvent
 * 
 */
public class GetParentOrganizationEvent 
extends RequestEvent 
{

	String organizationId ; 
	
	/**
	 * @return Returns the organizationId.
	 */
	public String getOrganizationId() {
		return organizationId;
	}
	
	
	/**
	 * @param organizationId The organizationId to set.
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
	
}
