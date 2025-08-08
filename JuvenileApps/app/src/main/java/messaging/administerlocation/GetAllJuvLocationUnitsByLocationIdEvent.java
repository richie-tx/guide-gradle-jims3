/*
 * Created on Jun 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GetAllJuvLocationUnitsByLocationIdEvent extends RequestEvent {

	public String locationId;

	public String agencyId;

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
