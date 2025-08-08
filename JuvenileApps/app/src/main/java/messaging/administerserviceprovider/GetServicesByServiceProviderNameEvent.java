//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServicesEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;


public class GetServicesByServiceProviderNameEvent extends RequestEvent
{
	private String serviceProviderName;

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
}