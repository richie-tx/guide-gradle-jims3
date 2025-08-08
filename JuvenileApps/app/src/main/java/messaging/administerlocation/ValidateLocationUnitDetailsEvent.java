// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\administerlocation\\ValidateLocationUnitDetailsEvent.java

package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

public class ValidateLocationUnitDetailsEvent extends RequestEvent {
	public String locationUnitName;

	public String juvUnitCd;

	public String locationId;
	
	public boolean isUpdate;
	
	public String locationUnitId;

	/**
	 * @roseuid 466466B2009A
	 */
	public ValidateLocationUnitDetailsEvent() {

	}

	/**
	 * @return Returns the juvUnitCd.
	 */
	public String getJuvUnitCd() {
		return juvUnitCd;
	}

	/**
	 * @param juvUnitCd
	 *            The juvUnitCd to set.
	 */
	public void setJuvUnitCd(String juvUnitCd) {
		this.juvUnitCd = juvUnitCd;
	}

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
	 * @return Returns the locationUnitName.
	 */
	public String getLocationUnitName() {
		return locationUnitName;
	}

	/**
	 * @param locationUnitName
	 *            The locationUnitName to set.
	 */
	public void setLocationUnitName(String locationUnitName) {
		this.locationUnitName = locationUnitName;
	}
	
	/**
	 * @return Returns the isUpdate.
	 */
	public boolean isUpdate() {
		return isUpdate;
	}
	/**
	 * @param isUpdate The isUpdate to set.
	 */
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	
	/**
	 * @return Returns the locationUnitId.
	 */
	public String getLocationUnitId() {
		return locationUnitId;
	}
	/**
	 * @param locationUnitId The locationUnitId to set.
	 */
	public void setLocationUnitId(String locationUnitId) {
		this.locationUnitId = locationUnitId;
	}
}
