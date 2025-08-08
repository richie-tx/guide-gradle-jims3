/*
 * Created on May 7, 2007
 *
 */
package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

/**
 * @author ryoung
 *
 */
public class GetLightProgramUnitEvent extends RequestEvent {
	
	private String organizationId;

	/**
	 * 
	 * @return
	 */
	public String getOrganizationId() {
		return organizationId;
	}

	/**
	 * 
	 * @param organizationId
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
	
}
