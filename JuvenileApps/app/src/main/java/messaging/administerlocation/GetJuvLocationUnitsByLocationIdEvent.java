// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\administerlocation\\GetLocationsEvent.java

package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

public class GetJuvLocationUnitsByLocationIdEvent extends RequestEvent {	
	
	public String locationId;
	public String agencyId;	

	/**
	 * @return Returns the locationId.
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId The locationId to set.
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
}
