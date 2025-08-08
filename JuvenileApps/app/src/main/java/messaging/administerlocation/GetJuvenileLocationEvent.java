package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileLocationEvent extends RequestEvent {

	private String locationId;

	/**
	 * @return Returns the locationId.
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId
	 *            The locationId to set.
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
}
