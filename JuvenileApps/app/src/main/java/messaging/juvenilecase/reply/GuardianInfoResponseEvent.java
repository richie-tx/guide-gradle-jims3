/*
 * Created on Jul 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GuardianInfoResponseEvent extends ResponseEvent {

	private Date entryDate;
	private String lastName;
	private String middleName;
	private String firstName;
	private String guardianName;
	private String juvenileId;
	private String primaryContact;
	
	//Guardian Phones
	private String phone;
	private String extension;
	private String homePhone = "";
	private String mobilePhone = "";
	private String juvMobilePhone = "";
	private String workPhone = "";
	private String phoneType;
	private String famMemberId;
	private String homeExtn = "";
	private String workExtn = "";
	
	//Guardian Address
	private String addressId;
	private String addressType;
	private String addressTypeId;
	private String streetNumber;
	private String streetNumSuffix;
	private String streetNumSuffixId;
	private String streetName;
	private String streetType;
	private String streetTypeId;
	private String state;
	private String stateId;
	private String city;
	private String zipCode;
	private String county;
	private String countyId;
	private String additionalZipCode;
	private String aptNumber;
	private String validated;
	
	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId
	 *            The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            The middleName to set.
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return Returns the homePhone.
	 */
	public String getHomePhone() {
		return homePhone;
	}

	/**
	 * @param homePhone
	 *            The homePhone to set.
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	
	/**
	 * @return Returns the mobilePhone.
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * @param mobilePhone The mobilePhone to set.
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * @return Returns the workPhone.
	 */
	public String getWorkPhone() {
		return workPhone;
	}

	/**
	 * @param workPhone
	 *            The workPhone to set.
	 */
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	/**
	 * @return Returns the guardianName.
	 */
	public String getGuardianName() {
		return guardianName;
	}

	/**
	 * @param guardianName
	 *            The guardianName to set.
	 */
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	/**
	 * @return Returns the extension.
	 */
	public String getExtension() {
		return extension;
	}
	/**
	 * @param extension The extension to set.
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	/**
	 * @return Returns the phoneType.
	 */
	public String getPhoneType() {
		return phoneType;
	}
	/**
	 * @param phoneType The phoneType to set.
	 */
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	/**
	 * @return Returns the famMemberId.
	 */
	public String getFamMemberId() {
		return famMemberId;
	}
	/**
	 * @param famMemberId The famMemberId to set.
	 */
	public void setFamMemberId(String famMemberId) {
		this.famMemberId = famMemberId;
	}
	/**
	 * @return Returns the homeExtn.
	 */
	public String getHomeExtn() {
		return homeExtn;
	}
	/**
	 * @param homeExtn The homeExtn to set.
	 */
	public void setHomeExtn(String homeExtn) {
		this.homeExtn = homeExtn;
	}
	/**
	 * @return Returns the workExtn.
	 */
	public String getWorkExtn() {
		return workExtn;
	}
	/**
	 * @param workExtn The workExtn to set.
	 */
	public void setWorkExtn(String workExtn) {
		this.workExtn = workExtn;
	}
	
	/**
	 * @return the addressId
	 */
	public String getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the addressType
	 */
	public String getAddressType() {
		return addressType;
	}

	/**
	 * @param addressType the addressType to set
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	/**
	 * @return the addressTypeId
	 */
	public String getAddressTypeId() {
		return addressTypeId;
	}

	/**
	 * @param addressTypeId the addressTypeId to set
	 */
	public void setAddressTypeId(String addressTypeId) {
		this.addressTypeId = addressTypeId;
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
	 * @return the state
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @param state the state to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
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
	 * @return the streetTypeId
	 */
	public String getStreetTypeId() {
		return streetTypeId;
	}

	/**
	 * @param streetTypeId the streetTypeId to set
	 */
	public void setStreetTypeId(String streetTypeId) {
		this.streetTypeId = streetTypeId;
	}

	/**
	 * @return the streetTypeType
	 */
	public String getStreetType() {
		return streetType;
	}

	/**
	 * @param streetType the streetTypeType to set
	 */
	public void setStreetType(String streetType) {
		this.streetType = streetType;
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
	 * @return the additionalZipCode
	 */
	public String getAdditionalZipCode() {
		return additionalZipCode;
	}

	/**
	 * @param additionalZipCode the additionalZipCode to set
	 */
	public void setAdditionalZipCode(String additionalZipCode) {
		this.additionalZipCode = additionalZipCode;
	}
	
	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	
	/**
	 * @return the countyId
	 */
	public String getCountyId() {
		return countyId;
	}

	/**
	 * @param countyId the countyId to set
	 */
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	
	/**
	 * @return the aptNumber
	 */
	public String getAptNumber() {
		return aptNumber;
	}

	/**
	 * @param aptNumber the aptNumber to set
	 */
	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}

	public String getJuvMobilePhone() {
		return juvMobilePhone;
	}

	public void setJuvMobilePhone(String juvMobilePhone) {
		this.juvMobilePhone = juvMobilePhone;
	}

	public String getValidated() {
		return validated;
	}

	public void setValidated(String validated) {
		this.validated = validated;
	}

	public void setStreetNumSuffix(String streetNumSuffix) {
		this.streetNumSuffix = streetNumSuffix;
	}

	public String getStreetNumSuffix() {
		return streetNumSuffix;
	}

	public void setStreetNumSuffixId(String streetNumSuffixId) {
		this.streetNumSuffixId = streetNumSuffixId;
	}

	public String getStreetNumSuffixId() {
		return streetNumSuffixId;
	}

	/**
	 * @return the primaryContact
	 */
	public String getPrimaryContact() {
		return primaryContact;
	}

	/**
	 * @param primaryContact the primaryContact to set
	 */
	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}
	
}
