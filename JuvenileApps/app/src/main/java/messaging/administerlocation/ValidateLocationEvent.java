/*
 * Created on Sep 26, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;


public class ValidateLocationEvent extends RequestEvent {
	
	public String agencyId;
	public String locationName;
	public String locationCd;

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
	/**
	 * @return Returns the locationName.
	 */
	public String getLocationName() {
		return locationName;
	}
	/**
	 * @param locationName The locationName to set.
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	/**
	 * @return Returns the locationCd.
	 */
	public String getLocationCd() {
		return locationCd;
	}
	/**
	 * @param locationCd The locationCd to set.
	 */
	public void setLocationCd(String locationCd) {
		this.locationCd = locationCd;
	}
}
