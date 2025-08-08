//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServicesEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;


public class GetServicesByServiceProviderEvent extends RequestEvent
{
	private String serviceProviderId;

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