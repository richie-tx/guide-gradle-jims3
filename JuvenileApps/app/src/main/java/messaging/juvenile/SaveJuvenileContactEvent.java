//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\SaveJuvenileContactEvent.java

package messaging.juvenile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileContactEvent extends RequestEvent {
	private String phonePriorityInd;
	private String contactNum;
	private String agencyName;
	private String prefixId;
	private String addressType;
	private String apartmentNum;
	private String cellPhone;
	private String city;
	private String currentAgencyInvolvementId;
	private Date entryDate;
	private String eMail;
	private String fax;
	private String firstName;
	private String juvenileNum;
	private String lastName;
	private String middleName;
	private String previousAgencyInvolvementId;
	private String state;
	private String streetName;
	private String streetNum;
	private String streetNumSuffix;
	private String streetType;
	private String workPhone;
	private String workPhoneExtn;
	private String zipCode;
	private String additionalZipCode;
	private String county;
	private String relationshipId;
	private String prefix;
	private String validated;
	//the following are added for Detention Visit US 43115, Task 45932
	private boolean detentionVisit;
	private boolean ageOver21;
	private String driverLicenseNum = "";
	private String driverLicenseStateId = "";
	private String driverLicenseClassId = "";
	private String driverLicenseExpirationDate = "";
	private String issuedByStateId = "";
	private String stateIssuedIdNum = "";
	private String passportNum = ""; 
	private String countryOfIssuanceId = ""; 
	private String passportExpirationDate = "";
	private String contactMemberComments = "";

	/**
	 * @roseuid 42B1968800AB
	 */
	public SaveJuvenileContactEvent() {

	}

	/**
	 * @return
	 */
	public String getAddressType() {
		return addressType;
	}

	/**
	 * @return
	 */
	public String getApartmentNum() {
		return apartmentNum;
	}

	/**
	 * @return
	 */
	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @return
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @return
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @return
	 */
	public String getStreetNum() {
		return streetNum;
	}

	/**
	 * @return
	 */
	public String getStreetType() {
		return streetType;
	}

	/**
	 * @return
	 */
	public String getWorkPhone() {
		return workPhone;
	}

	/**
	 * @return
	 */
	public String getZipCode() {
		return zipCode;
	}

	public boolean isDetentionVisit() {
	    return detentionVisit;
	}

	public boolean isAgeOver21() {
	    return ageOver21;
	}

	public String getDriverLicenseNum() {
	    return driverLicenseNum;
	}

	public String getDriverLicenseStateId() {
	    return driverLicenseStateId;
	}

	public String getDriverLicenseClassId() {
	    return driverLicenseClassId;
	}

	public String getDriverLicenseExpirationDate() {
	    return driverLicenseExpirationDate;
	}

	public String getIssuedByStateId() {
	    return issuedByStateId;
	}

	public String getStateIssuedIdNum() {
	    return stateIssuedIdNum;
	}

	public String getPassportNum() {
	    return passportNum;
	}

	public String getCountryOfIssuanceId() {
	    return countryOfIssuanceId;
	}

	public String getPassportExpirationDate() {
	    return passportExpirationDate;
	}

	public void setDetentionVisit(boolean ddetentionVisit) {
	    detentionVisit = ddetentionVisit;
	}

	public void setAgeOver21(boolean aageOver21) {
	   ageOver21 = aageOver21;
	}

	public void setDriverLicenseNum(String driverLicenseNum) {
	    this.driverLicenseNum = driverLicenseNum;
	}

	public void setDriverLicenseStateId(String driverLicenseStateId) {
	    this.driverLicenseStateId = driverLicenseStateId;
	}

	public void setDriverLicenseClassId(String driverLicenseClassId) {
	    this.driverLicenseClassId = driverLicenseClassId;
	}

	public void setDriverLicenseExpirationDate(String driverLicenseExpirationDate) {
	    this.driverLicenseExpirationDate = driverLicenseExpirationDate;
	}

	public void setIssuedByStateId(String issuedByStateId) {
	    this.issuedByStateId = issuedByStateId;
	}

	public void setStateIssuedIdNum(String stateIssuedIdNum) {
	    this.stateIssuedIdNum = stateIssuedIdNum;
	}

	public void setPassportNum(String passportNum) {
	    this.passportNum = passportNum;
	}

	public void setCountryOfIssuanceId(String countryOfIssuanceId) {
	    this.countryOfIssuanceId = countryOfIssuanceId;
	}

	public void setPassportExpirationDate(String passportExpirationDate) {
	    this.passportExpirationDate = passportExpirationDate;
	}

	/**
	 * @param string
	 */
	public void setAddressType(String string) {
		addressType = string;
	}

	/**
	 * @param string
	 */
	public void setApartmentNum(String string) {
		apartmentNum = string;
	}

	/**
	 * @param string
	 */
	public void setCellPhone(String string) {
		cellPhone = string;
	}

	/**
	 * @param string
	 */
	public void setCity(String string) {
		city = string;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date) {
		entryDate = date;
	}

	/**
	 * @param string
	 */
	public void setFax(String string) {
		fax = string;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string) {
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string) {
		juvenileNum = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string) {
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string) {
		middleName = string;
	}

	/**
	 * @param string
	 */
	public void setState(String string) {
		state = string;
	}

	/**
	 * @param string
	 */
	public void setStreetName(String string) {
		streetName = string;
	}

	/**
	 * @param string
	 */
	public void setStreetNum(String string) {
		streetNum = string;
	}

	/**
	 * @param string
	 */
	public void setStreetType(String string) {
		streetType = string;
	}

	/**
	 * @param string
	 */
	public void setWorkPhone(String string) {
		workPhone = string;
	}

	/**
	 * @param string
	 */
	public void setZipCode(String string) {
		zipCode = string;
	}

	/**
	 * @return
	 */
	public String getCurrentAgencyInvolvementId() {
		return currentAgencyInvolvementId;
	}

	/**
	 * @return
	 */
	public String getPreviousAgencyInvolvementId() {
		return previousAgencyInvolvementId;
	}

	/**
	 * @param b
	 */
	public void setCurrentAgencyInvolvementId(String b) {
		currentAgencyInvolvementId = b;
	}

	/**
	 * @param b
	 */
	public void setPreviousAgencyInvolvementId(String b) {
		previousAgencyInvolvementId = b;
	}

	/**
	 * @return
	 */
	public String getPrefixId() {
		return prefixId;
	}

	/**
	 * @param string
	 */
	public void setPrefixId(String string) {
		prefixId = string;
	}

	/**
	 * @return
	 */
	public String getAdditionalZipCode() {
		return additionalZipCode;
	}

	/**
	 * @param string
	 */
	public void setAdditionalZipCode(String string) {
		additionalZipCode = string;
	}

	/**
	 * @return
	 */
	public String getAgencyName() {
		return agencyName;
	}

	/**
	 * @return
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * @return
	 */
	public String getEMail() {
		return eMail;
	}

	/**
	 * @return
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @return
	 */
	public String getRelationshipId() {
		return relationshipId;
	}

	/**
	 * @param string
	 */
	public void setAgencyName(String string) {
		agencyName = string;
	}

	/**
	 * @param string
	 */
	public void setCounty(String string) {
		county = string;
	}

	/**
	 * @param string
	 */
	public void setEMail(String string) {
		eMail = string;
	}

	/**
	 * @param string
	 */
	public void setPrefix(String string) {
		prefix = string;
	}

	/**
	 * @param string
	 */
	public void setRelationshipId(String string) {
		relationshipId = string;
	}

	/**
	 * @return Returns the contactNum.
	 */
	public String getContactNum() {
		return contactNum;
	}

	/**
	 * @param contactNum
	 *            The contactNum to set.
	 */
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	/**
	 * @return Returns the phonePriorityInd.
	 */
	public String getPhonePriorityInd() {
		return phonePriorityInd;
	}

	/**
	 * @param phonePriorityInd
	 *            The phonePriorityInd to set.
	 */
	public void setPhonePriorityInd(String phonePriorityInd) {
		this.phonePriorityInd = phonePriorityInd;
	}

	/**
	 * @return Returns the workPhoneExtn.
	 */
	public String getWorkPhoneExtn() {
		return workPhoneExtn;
	}

	/**
	 * @param workPhoneExtn
	 *            The workPhoneExtn to set.
	 */
	public void setWorkPhoneExtn(String workPhoneExtn) {
		this.workPhoneExtn = workPhoneExtn;
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

	public String getContactMemberComments()
	{
	    return contactMemberComments;
	}

	public void setContactMemberComments(String contactMemberComments)
	{
	    this.contactMemberComments = contactMemberComments;
	}
	
}
