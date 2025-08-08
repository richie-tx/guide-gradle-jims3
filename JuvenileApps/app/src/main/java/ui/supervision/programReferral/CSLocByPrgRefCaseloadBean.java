package ui.supervision.programReferral;

import java.util.List;

import messaging.contact.domintf.IPhoneNumber;

public class CSLocByPrgRefCaseloadBean 
{
	private String locationId;
	private String locationName;
	private String streetNumber;
	private String streetName;
	private String streetTypeCd;
	private String aptNum;
	private String city;
	private String state;
	private String zipCode;
	private IPhoneNumber locationPhone;
	
	private List locPrgReferralList; //CSProgRefCaseloadBean

	private List openProgReferralList; //CSProgRefCaseloadBean
	private List allProgReferralList; //CSProgRefCaseloadBean
	
	
	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the streetTypeCd
	 */
	public String getStreetTypeCd() {
		return streetTypeCd;
	}

	/**
	 * @param streetTypeCd the streetTypeCd to set
	 */
	public void setStreetTypeCd(String streetTypeCd) {
		this.streetTypeCd = streetTypeCd;
	}

	/**
	 * @return the aptNum
	 */
	public String getAptNum() {
		return aptNum;
	}

	/**
	 * @param aptNum the aptNum to set
	 */
	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the locationPhone
	 */
	public IPhoneNumber getLocationPhone() {
		return locationPhone;
	}

	/**
	 * @param locationPhone the locationPhone to set
	 */
	public void setLocationPhone(IPhoneNumber locationPhone) {
		this.locationPhone = locationPhone;
	}

	/**
	 * @return the locPrgReferralList
	 */
	public List getLocPrgReferralList() {
		return locPrgReferralList;
	}

	/**
	 * @param locPrgReferralList the locPrgReferralList to set
	 */
	public void setLocPrgReferralList(List locPrgReferralList) {
		this.locPrgReferralList = locPrgReferralList;
	}

	/**
	 * @return the openProgReferralList
	 */
	public List getOpenProgReferralList() {
		return openProgReferralList;
	}

	/**
	 * @param openProgReferralList the openProgReferralList to set
	 */
	public void setOpenProgReferralList(List openProgReferralList) {
		this.openProgReferralList = openProgReferralList;
	}

	/**
	 * @return the allProgReferralList
	 */
	public List getAllProgReferralList() {
		return allProgReferralList;
	}

	/**
	 * @param allProgReferralList the allProgReferralList to set
	 */
	public void setAllProgReferralList(List allProgReferralList) {
		this.allProgReferralList = allProgReferralList;
	}
	
}
