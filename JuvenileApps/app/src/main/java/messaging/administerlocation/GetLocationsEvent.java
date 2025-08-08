// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\administerlocation\\GetLocationsEvent.java

package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

public class GetLocationsEvent extends RequestEvent {
	public String serviceProviderId;
	
	public String serviceProviderName;

	public String locationName;

	public String isInHouse;

	public String serviceTypeId;

	public String streetNum;

	public String streetName;

	public String streetType;

	public String stateId;

	public String city;

	public String zipCode;

	public String agencyId;

	public String statusId;
	
	public String locationCd;
	
	public String locationRegionId;
	
	/**
	 * @roseuid 45116AFC0232
	 */
	public GetLocationsEvent() {

	}

	/**
	 * @param serviceProviderId
	 * @roseuid 45116939025D
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	/**
	 * @return String
	 * @roseuid 45116939026C
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}

	/**
	 * @param locationName
	 * @roseuid 45116939027B
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @return String
	 * @roseuid 45116939027D
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @param isInHouse
	 * @roseuid 45116939028A
	 */
	public void setIsInHouse(String isInHouse) {
		this.isInHouse = isInHouse;
	}

	/**
	 * @return boolean
	 * @roseuid 45116939028C
	 */
	public String getIsInHouse() {
		return isInHouse;
	}

	/**
	 * @param streetNum
	 * @roseuid 4511693902B9
	 */
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}

	/**
	 * @return String
	 * @roseuid 4511693902BB
	 */
	public String getStreetNum() {
		return streetNum;
	}

	/**
	 * @param streetName
	 * @roseuid 4511693902D8
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return String
	 * @roseuid 4511693902DA
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetType
	 * @roseuid 4511693902E8
	 */
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	/**
	 * @return String
	 * @roseuid 4511693902F8
	 */
	public String getStreetType() {
		return streetType;
	}

	/**
	 * @param state
	 * @roseuid 4511693902FA
	 */
	public void setState(String state) {
		this.stateId = state;
	}

	/**
	 * @return String
	 * @roseuid 451169390308
	 */
	public String getState() {
		return stateId;
	}

	/**
	 * @param city
	 * @roseuid 451169390317
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return String
	 * @roseuid 451169390319
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param zipCode
	 * @roseuid 451169390327
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return String
	 * @roseuid 451169390336
	 */
	public String getZipCode() {
		return zipCode;
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
	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	/**
	 * @return Returns the serviceTypeId.
	 */
	public String getServiceTypeId() {
		return serviceTypeId;
	}
	/**
	 * @param serviceTypeId The serviceTypeId to set.
	 */
	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
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
	 * @return Returns the stateId.
	 */
	public String getStateId() {
		return stateId;
	}
	/**
	 * @param stateId The stateId to set.
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
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
