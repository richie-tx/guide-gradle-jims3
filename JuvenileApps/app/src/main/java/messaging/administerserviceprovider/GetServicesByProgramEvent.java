/*
 * Created on Oct 5, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetServicesByProgramEvent extends RequestEvent {
	private String providerProgramId;
	/**
	 * @return Returns the providerProgramId.
	 */
	public String getProviderProgramId() {
		return providerProgramId;
	}
	/**
	 * @param providerProgramId The providerProgramId to set.
	 */
	public void setProviderProgramId(String providerProgramId) {
		this.providerProgramId = providerProgramId;
	}
}
