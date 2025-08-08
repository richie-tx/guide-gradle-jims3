/*
 * Created on Oct 10, 2006
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
public class GetProgramByProgramIdEvent extends RequestEvent {
	   
	private String providerProgramId;
	public GetProgramByProgramIdEvent(){

	}
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
