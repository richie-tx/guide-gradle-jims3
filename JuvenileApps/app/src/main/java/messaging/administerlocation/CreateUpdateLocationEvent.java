//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerlocation\\CreateUpdateLocationEvent.java

package messaging.administerlocation;

import mojo.km.messaging.Composite.CompositeRequest;

public class CreateUpdateLocationEvent extends CompositeRequest {

	private String locationId;

	private String locationName;

	private boolean isInHouse;

	private String facilityTypeId;

	private String statusId;

	
	private String agencyId;
	
	private String phoneNumber;
	
	private String locationFax;
	
	private String locationCd;
	
	private String locationRegionId;
	
	/**
	 * @roseuid 45114A2801A7
	 */
	public CreateUpdateLocationEvent() {

	}

	/**
	 * Access method for the locationName property.
	 * 
	 * @return   the current value of the locationName property
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * Sets the value of the locationName property.
	 * 
	 * @param aLocationName the new value of the locationName property
	 */
	public void setLocationName(String aLocationName) {
		locationName = aLocationName;
	}

	/**
	 * Determines if the isInHouse property is true.
	 * 
	 * @return   <code>true<code> if the isInHouse property is true
	 */
	public boolean getIsInHouse() {
		return isInHouse;
	}



	/**
	 * @return Returns the facilityTypeId.
	 */
	public String getFacilityTypeId() {
		return facilityTypeId;
	}

	/**
	 * @param facilityTypeId The facilityTypeId to set.
	 */
	public void setFacilityTypeId(String facilityTypeId) {
		this.facilityTypeId = facilityTypeId;
	}

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
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	
	/**
	 * @return Returns the locationRegionId.
	 */
	public String getLocationRegionId() {
		return locationRegionId;
	}
	
	/**
	 * @param locationRegionId The locationRegionId to set.
	 */
	public void setLocationRegionId(String locationRegionId) {
		this.locationRegionId = locationRegionId;
	}
	/**
	 * @param isInHouse The isInHouse to set.
	 */
	public void setInHouse(boolean isInHouse) {
		this.isInHouse = isInHouse;
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
	/**
	 * @return Returns the phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return the locationFax
	 */
	public String getLocationFax() {
		return locationFax;
	}

	/**
	 * @param locationFax the locationFax to set
	 */
	public void setLocationFax(String locationFax) {
		this.locationFax = locationFax;
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
