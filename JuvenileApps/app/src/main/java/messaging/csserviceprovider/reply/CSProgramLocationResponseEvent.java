/*
 * Created on Apr 14, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider.reply;

import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSProgramLocationResponseEvent extends ResponseEvent 
{
	private String serviceProviderId;
	private String serviceProviderName;
	private boolean isFaithBased;
	private List serviceProviderPrograms;
	
	private List serviceProviderLocations;
	
	/**
	 * @return Returns the serviceProviderPrograms.
	 */
	public List getServiceProviderPrograms() {
		return serviceProviderPrograms;
	}
	/**
	 * @param serviceProviderPrograms The serviceProviderPrograms to set.
	 */
	public void setServiceProviderPrograms(List serviceProviderPrograms) {
		this.serviceProviderPrograms = serviceProviderPrograms;
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
	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	/**
	 * @return the serviceProviderLocations
	 */
	public List getServiceProviderLocations() {
		return serviceProviderLocations;
	}
	/**
	 * @param serviceProviderLocations the serviceProviderLocations to set
	 */
	public void setServiceProviderLocations(List serviceProviderLocations) {
		this.serviceProviderLocations = serviceProviderLocations;
	}
	/**
	 * @return the isFaithBased
	 */
	public boolean isFaithBased() {
		return isFaithBased;
	}
	/**
	 * @param isFaithBased the isFaithBased to set
	 */
	public void setFaithBased(boolean isFaithBased) {
		this.isFaithBased = isFaithBased;
	}
}
