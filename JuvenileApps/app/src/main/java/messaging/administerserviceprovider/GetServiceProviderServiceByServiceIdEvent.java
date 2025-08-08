//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServicesEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;
/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetServiceProviderServiceByServiceIdEvent extends RequestEvent
{
	private int serviceId;

	/**
	 * @return Returns the serviceId.
	 */
	public int getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId The serviceId to set.
	 */
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
}