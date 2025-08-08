/*
 * Created on Aug 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.transferobjects;


/**
 * @author cc_mdsouza
 *
 */
public class LocationTO
extends PersistentObjectTO 
{
	
	public String addressId;
	public String locationName;
	public String agencyId;
	public String locationId;
	public boolean inHouse;
	public String statusId;
	public String serviceProviderName;
	public String facilityTypeId;
	public String phoneNumber;
	public String streetNum;
	public String streetName;
	public String city;
	public String aptNumber;
	public String stateId;
	public String zipCode;
	public String locationCd;

//	private Address address = null;
//	private Code status = null;
//	private Code facilityType;
	
	
	public LocationTO() 
	{
	}
	
	
	
//	/**
//	 * @return Returns the address.
//	 */
//	public Address getAddress() {
//		return address;
//	}
//	/**
//	 * @param address The address to set.
//	 */
//	public void setAddress(Address address) {
//		this.address = address;
//	}
	/**
	 * @return Returns the addressId.
	 */
	public String getAddressId() {
		return addressId;
	}
	/**
	 * @param addressId The addressId to set.
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
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
	 * @return Returns the aptNumber.
	 */
	public String getAptNumber() {
		return aptNumber;
	}
	/**
	 * @param aptNumber The aptNumber to set.
	 */
	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the facilityType.
	 */
//	public Code getFacilityType() {
//		return facilityType;
//	}
//	/**
//	 * @param facilityType The facilityType to set.
//	 */
//	public void setFacilityType(Code facilityType) {
//		this.facilityType = facilityType;
//	}
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
	 * @return Returns the inHouse.
	 */
	public boolean isInHouse() {
		return inHouse;
	}
	/**
	 * @param inHouse The inHouse to set.
	 */
	public void setInHouse(boolean inHouse) {
		this.inHouse = inHouse;
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
	 * @return Returns the status.
	 */
//	public Code getStatus() {
//		return status;
//	}
//	/**
//	 * @param status The status to set.
//	 */
//	public void setStatus(Code status) {
//		this.status = status;
//	}
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
	 * @return Returns the streetName.
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName The streetName to set.
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return Returns the streetNum.
	 */
	public String getStreetNum() {
		return streetNum;
	}
	/**
	 * @param streetNum The streetNum to set.
	 */
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}
	/**
	 * @return Returns the zipCode.
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode The zipCode to set.
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
