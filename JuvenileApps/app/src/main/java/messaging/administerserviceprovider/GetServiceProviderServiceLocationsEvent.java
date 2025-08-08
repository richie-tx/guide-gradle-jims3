//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServiceLocationsEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetServiceProviderServiceLocationsEvent extends RequestEvent
{
	private String serviceProviderId;
	private String serviceStatusCd;
	private String programStatusCd;
	private boolean inHouse;

	/**
	 * @roseuid 44805C9400DF
	 */
	public GetServiceProviderServiceLocationsEvent()
	{

	}

	/**
	 * Access method for the serviceProviderId property.
	 * 
	 * @return   the current value of the serviceProviderId property
	 */
	public String getServiceProviderId()
	{
		return serviceProviderId;
	}

	/**
	 * Sets the value of the serviceProviderId property.
	 * 
	 * @param aServiceProviderId the new value of the serviceProviderId property
	 */
	public void setServiceProviderId(String aServiceProviderId)
	{
		serviceProviderId = aServiceProviderId;
	}
	/**
	 * @return Returns the programStatusCd.
	 */
	public String getProgramStatusCd() {
		return programStatusCd;
	}
	/**
	 * @param programStatusCd The programStatusCd to set.
	 */
	public void setProgramStatusCd(String programStatusCd) {
		this.programStatusCd = programStatusCd;
	}
	/**
	 * @return Returns the serviceStatusCd.
	 */
	public String getServiceStatusCd() {
		return serviceStatusCd;
	}
	/**
	 * @param serviceStatusCd The serviceStatusCd to set.
	 */
	public void setServiceStatusCd(String serviceStatusCd) {
		this.serviceStatusCd = serviceStatusCd;
	}
	/**
	 * @return Returns the inHouse.
	 */
	public boolean isInHouse() {
		return inHouse;
	}
	/**
	 * @param inHouse The inHouse to set.
	 */
	public void setInHouse(boolean inHouse) {
		this.inHouse = inHouse;
	}
}