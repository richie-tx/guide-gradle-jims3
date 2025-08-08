package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class SearchJuvenileCasefileByZipCodeAttributeEvent extends RequestEvent {
	public String juvenileId;

	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
}
