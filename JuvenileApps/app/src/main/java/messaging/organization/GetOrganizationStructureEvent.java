package messaging.organization;

import mojo.km.messaging.RequestEvent;

public class GetOrganizationStructureEvent extends RequestEvent
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