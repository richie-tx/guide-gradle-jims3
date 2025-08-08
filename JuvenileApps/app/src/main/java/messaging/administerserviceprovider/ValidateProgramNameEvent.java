/*
 * Created on Oct 4, 2006
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
public class ValidateProgramNameEvent extends RequestEvent {
	
	   public String serviceProviderId;
	   public String programName;	

	/**
	 * @return Returns the programName.
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param programName The programName to set.
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	/**
	 * @param serviceProviderId The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
}
