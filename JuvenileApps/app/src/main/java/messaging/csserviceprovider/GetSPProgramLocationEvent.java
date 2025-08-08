package messaging.csserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetSPProgramLocationEvent extends RequestEvent
{
	private String progLocationId;
	
	private String serviceProviderName;
	private String agencyId;
	private String isInHouse;
	private String locationStatusCode;
	private String regionCode;
	private String locationName;
	private String streetNum;
	private String streetName;
	private String city;
	private String stateCode;
	private String zipCode;
	private String locationCode;
	
	
	/**
	 * @return the progLocationId
	 */
	public String getProgLocationId() {
		return progLocationId;
	}
	/**
	 * @param progLocationId the progLocationId to set
	 */
	public void setProgLocationId(String progLocationId) {
		this.progLocationId = progLocationId;
	}
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public String getIsInHouse() {
		return isInHouse;
	}
	public void setIsInHouse(String isInHouse) {
		this.isInHouse = isInHouse;
	}
	public String getLocationStatusCode() {
		return locationStatusCode;
	}
	public void setLocationStatusCode(String locationStatusCode) {
		this.locationStatusCode = locationStatusCode;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getStreetNum() {
		return streetNum;
	}
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	
}
