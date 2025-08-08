/*
 * Created on Mar 1, 2013
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;


/**
 * @author cShimek
 */
public class FamilyMemberforTEAReportResponseEvent extends ResponseEvent{

    private String firstName;
    private String lastName;
    private String middleName;
    private String memberId;
    private boolean deceased;
    private boolean incarcerated;
    private boolean guardian;
    private boolean inHome;
	private String relationToJuvenileCd;
	private String streetName;
	private String streetNumber;
	private String streetNumberSuffix;
	private String streetTypeCd;
	private String apartmentNumber;
	private String streetAddress2;
	private String stateCd;
	private String city;
	private String zipCode;
	private String zipCodeExtension;
	private String phoneNumber;
	// display only variables
	private String fullName;
	private String relationToJuvenile;
	private String formattedZipCode;
	private String streetType;
	private String state;
	private String formattedAddress1;  // streetnumber streetname apartmentnumber
	private String formattedAddress2;  // city state zip
    private String formattedPhoneNumber;
    private String deceasedYes;
    private String incarceratedYes;
    private String guardianYes;
    private String inHomeYes;

	/**
	 * 
	 */
	public FamilyMemberforTEAReportResponseEvent()
	{
		super();
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the deceased
	 */
	public boolean isDeceased() {
		return deceased;
	}

	/**
	 * @param deceased the deceased to set
	 */
	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}

	/**
	 * @return the incarcerated
	 */
	public boolean isIncarcerated() {
		return incarcerated;
	}

	/**
	 * @param incarcerated the incarcerated to set
	 */
	public void setIncarcerated(boolean incarcerated) {
		this.incarcerated = incarcerated;
	}

	/**
	 * @return the guardian
	 */
	public boolean isGuardian() {
		return guardian;
	}

	/**
	 * @param guardian the guardian to set
	 */
	public void setGuardian(boolean guardian) {
		this.guardian = guardian;
	}

	/**
	 * @return the inHome
	 */
	public boolean isInHome() {
		return inHome;
	}

	/**
	 * @param inHome the inHome to set
	 */
	public void setInHome(boolean inHome) {
		this.inHome = inHome;
	}

	/**
	 * @return the relationToJuvenileCd
	 */
	public String getRelationToJuvenileCd() {
		return relationToJuvenileCd;
	}

	/**
	 * @param relationToJuvenileCd the relationToJuvenileCd to set
	 */
	public void setRelationToJuvenileCd(String relationToJuvenileCd) {
		this.relationToJuvenileCd = relationToJuvenileCd;
	}

	/**
	 * @return the relationToJuvenile
	 */
	public String getRelationToJuvenile() {
		return relationToJuvenile;
	}

	/**
	 * @param relationToJuvenile the relationToJuvenile to set
	 */
	public void setRelationToJuvenile(String relationToJuvenile) {
		this.relationToJuvenile = relationToJuvenile;
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
	 * @return the streetNumberSuffix
	 */
	public String getStreetNumberSuffix() {
		return streetNumberSuffix;
	}

	/**
	 * @param streetNumberSuffix the streetNumberSuffix to set
	 */
	public void setStreetNumberSuffix(String streetNumberSuffix) {
		this.streetNumberSuffix = streetNumberSuffix;
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
	 * @return the apartmentNumber
	 */
	public String getApartmentNumber() {
		return apartmentNumber;
	}

	/**
	 * @param apartmentNumber the apartmentNumber to set
	 */
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	/**
	 * @return the streetAddress2
	 */
	public String getStreetAddress2() {
		return streetAddress2;
	}

	/**
	 * @param streetAddress2 the streetAddress2 to set
	 */
	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	/**
	 * @return the stateCd
	 */
	public String getStateCd() {
		return stateCd;
	}

	/**
	 * @param stateCd the stateCd to set
	 */
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @param stateCd the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
	 * @return the zipCodeExtension
	 */
	public String getZipCodeExtension() {
		return zipCodeExtension;
	}

	/**
	 * @param zipCodeExtension the zipCodeExtension to set
	 */
	public void setZipCodeExtension(String zipCodeExtension) {
		this.zipCodeExtension = zipCodeExtension;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the formattedZipCode
	 */
	public String getFormattedZipCode() {
		return formattedZipCode;
	}

	/**
	 * @param formattedZipCode the formattedZipCode to set
	 */
	public void setFormattedZipCode(String formattedZipCode) {
		this.formattedZipCode = formattedZipCode;
	}

	/**
	 * @return the streetType
	 */
	public String getStreetType() {
		return streetType;
	}

	/**
	 * @param streetType the streetType to set
	 */
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	/**
	 * @return the formattedAddress1
	 */
	public String getFormattedAddress1() {
		return formattedAddress1;
	}

	/**
	 * @param formattedAddress1 the formattedAddress1 to set
	 */
	public void setFormattedAddress1(String formattedAddress1) {
		this.formattedAddress1 = formattedAddress1;
	}

	/**
	 * @return the formattedAddress2
	 */
	public String getFormattedAddress2() {
		return formattedAddress2;
	}

	/**
	 * @param formattedAddress2 the formattedAddress2 to set
	 */
	public void setFormattedAddress2(String formattedAddress2) {
		this.formattedAddress2 = formattedAddress2;
	}

	/**
	 * @return the formattedPhoneNumber
	 */
	public String getFormattedPhoneNumber() {
		return formattedPhoneNumber;
	}

	/**
	 * @param formattedPhoneNumber the formattedPhoneNumber to set
	 */
	public void setFormattedPhoneNumber(String formattedPhoneNumber) {
		this.formattedPhoneNumber = formattedPhoneNumber;
	}

	/**
	 * @return the deceasedYes
	 */
	public String getDeceasedYes() {
		return deceasedYes;
	}

	/**
	 * @param deceasedYes the deceasedYes to set
	 */
	public void setDeceasedYes(String deceasedYes) {
		this.deceasedYes = deceasedYes;
	}

	/**
	 * @return the incarceratedYes
	 */
	public String getIncarceratedYes() {
		return incarceratedYes;
	}

	/**
	 * @param incarceratedYes the incarceratedYes to set
	 */
	public void setIncarceratedYes(String incarceratedYes) {
		this.incarceratedYes = incarceratedYes;
	}

	/**
	 * @return the guardianYes
	 */
	public String getGuardianYes() {
		return guardianYes;
	}

	/**
	 * @param guardianYes the guardianYes to set
	 */
	public void setGuardianYes(String guardianYes) {
		this.guardianYes = guardianYes;
	}

	/**
	 * @return the inHomeYes
	 */
	public String getInHomeYes() {
		return inHomeYes;
	}

	/**
	 * @param inHomeYes the inHomeYes to set
	 */
	public void setInHomeYes(String inHomeYes) {
		this.inHomeYes = inHomeYes;
	}
}