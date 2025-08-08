// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\administerlocation\\GetLocationsEvent.java

package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

public class GetLocationsByAgencyEvent extends RequestEvent {
	public String agencyId;

	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}

	/**
	 * @param agencyId
	 *            The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
}
