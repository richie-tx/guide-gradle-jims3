//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerlocation\\InactivateLocationEvent.java

package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

public class ActivateInactivateLocationEvent extends RequestEvent {
	private String locationId;
	//<KISHORE>JIMS200060421 : Inactivating a location removes agency code
	private String agencyId;
	private String action;

	/**
	 * @roseuid 451156EC0396
	 */
	public ActivateInactivateLocationEvent() {

	}

	/**
	 * Access method for the locationId property.
	 * 
	 * @return   the current value of the locationId property
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * Sets the value of the locationId property.
	 * 
	 * @param aLocationId the new value of the locationId property
	 */
	public void setLocationId(String aLocationId) {
		locationId = aLocationId;
	}

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
}
