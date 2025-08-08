package messaging.csserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetCSServiceProvidersByLocationEvent extends RequestEvent
{
	private String locationId;

	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	
}
