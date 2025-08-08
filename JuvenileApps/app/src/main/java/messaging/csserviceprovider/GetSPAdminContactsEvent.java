package messaging.csserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetSPAdminContactsEvent extends RequestEvent
{
	private String serviceProvdrId;

	
	
	/**
	 * @return the serviceProvdrId
	 */
	public String getServiceProvdrId() {
		return serviceProvdrId;
	}

	/**
	 * @param serviceProvdrId the serviceProvdrId to set
	 */
	public void setServiceProvdrId(String serviceProvdrId) {
		this.serviceProvdrId = serviceProvdrId;
	}
	
	
}
