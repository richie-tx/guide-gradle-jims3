package ui.supervision.programReferral;

import java.util.List;

/**
 * 
 * @author cc_bjangay
 *
 */
public class CSCServiceProviderProgramsBean 
{
	private String serviceProviderId;
	
	private String serviceProviderName;
	
	private List serviceProviderPrograms; // CSCProgramInfoBean
	
	

	/**
	 * @return the serviceProviderId
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}

	/**
	 * @param serviceProviderId the serviceProviderId to set
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	/**
	 * @return the serviceProviderName
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}

	/**
	 * @param serviceProviderName the serviceProviderName to set
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	/**
	 * @return the serviceProviderPrograms
	 */
	public List getServiceProviderPrograms() {
		return serviceProviderPrograms;
	}

	/**
	 * @param serviceProviderPrograms the serviceProviderPrograms to set
	 */
	public void setServiceProviderPrograms(List serviceProviderPrograms) {
		this.serviceProviderPrograms = serviceProviderPrograms;
	}
	
	
}
