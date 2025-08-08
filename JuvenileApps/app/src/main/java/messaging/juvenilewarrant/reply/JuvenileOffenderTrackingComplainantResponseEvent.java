/*
 * Created on July 10, 2007
 */
package messaging.juvenilewarrant.reply;

import java.util.Date;

import messaging.contact.to.PhoneNumberBean;
import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 */
public class JuvenileOffenderTrackingComplainantResponseEvent extends ResponseEvent
{
	private String associationType;
	private Date dateOfBirth;
	private String name;
	private String relationshipToJuvenile;
	private String relationshipToJuvenileId;
	private String age;
	private String transactionNum;
	private String sequenceNum;
	private String ssn;
	private String daLogNum;
	private boolean isTheStateTheComplainant;
	private String otherIDNumbers;
	private String employer;	
	private String occupation;
	private String dlStateId;
	private String dlState;
	private String dlNumber;
	
	private String otherAptNum;
	private String otherCity;
	private PhoneNumberBean otherPhone;
	private String otherStreetName;
	private String otherStreetNumber;
	private String otherStreetType;
	private String otherZip;
	private String otherState;
	private String otherCounty;
	private String otherAddressType;
	
	private String aptNum;
	private String city;
	private String streetName;
	private String streetNum;
	private String streetType;
	private String addressType;
	private String county;
	private PhoneNumberBean phone;
	private String state; 
	private String zip;
	
	private String otherInd;
	
	/**
	 * @return Returns the age.
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age The age to set.
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return Returns the associationType.
	 */
	public String getAssociationType() {
		return associationType;
	}
	/**
	 * @param associationType The associationType to set.
	 */
	public void setAssociationType(String associationType) {
		this.associationType = associationType;
	}
	/**
	 * @return Returns the daLogNum.
	 */
	public String getDaLogNum() {
		return daLogNum;
	}
	/**
	 * @param daLogNum The daLogNum to set.
	 */
	public void setDaLogNum(String daLogNum) {
		this.daLogNum = daLogNum;
	}
	/**
	 * @return Returns the dateOfBirth.
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth The dateOfBirth to set.
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return Returns the dlNumber.
	 */
	public String getDlNumber() {
		return dlNumber;
	}
	/**
	 * @param dlNumber The dlNumber to set.
	 */
	public void setDlNumber(String dlNumber) {
		this.dlNumber = dlNumber;
	}
	/**
	 * @return Returns the dlState.
	 */
	public String getDlState() {
		return dlState;
	}
	/**
	 * @param dlState The dlState to set.
	 */
	public void setDlState(String dlState) {
		this.dlState = dlState;
	}
	/**
	 * @return Returns the dlStateId.
	 */
	public String getDlStateId() {
		return dlStateId;
	}
	/**
	 * @param dlStateId The dlStateId to set.
	 */
	public void setDlStateId(String dlStateId) {
		this.dlStateId = dlStateId;
	}
	/**
	 * @return Returns the employer.
	 */
	public String getEmployer() {
		return employer;
	}
	/**
	 * @param employer The employer to set.
	 */
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	/**
	 * @return Returns the isTheStateTheComplainant.
	 */
	public boolean isTheStateTheComplainant() {
		return isTheStateTheComplainant;
	}
	/**
	 * @param isTheStateTheComplainant The isTheStateTheComplainant to set.
	 */
	public void setTheStateTheComplainant(boolean isTheStateTheComplainant) {
		this.isTheStateTheComplainant = isTheStateTheComplainant;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the occupation.
	 */
	public String getOccupation() {
		return occupation;
	}
	/**
	 * @param occupation The occupation to set.
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	/**
	 * @return Returns the otherIDNumbers.
	 */
	public String getOtherIDNumbers() {
		return otherIDNumbers;
	}
	/**
	 * @param otherIDNumbers The otherIDNumbers to set.
	 */
	public void setOtherIDNumbers(String otherIDNumbers) {
		this.otherIDNumbers = otherIDNumbers;
	}
	/**
	 * @return Returns the relationshipToJuvenile.
	 */
	public String getRelationshipToJuvenile() {
		return relationshipToJuvenile;
	}
	/**
	 * @param relationshipToJuvenile The relationshipToJuvenile to set.
	 */
	public void setRelationshipToJuvenile(String relationshipToJuvenile) {
		this.relationshipToJuvenile = relationshipToJuvenile;
	}
	/**
	 * @return Returns the relationshipToJuvenileId.
	 */
	public String getRelationshipToJuvenileId() {
		return relationshipToJuvenileId;
	}
	/**
	 * @param relationshipToJuvenileId The relationshipToJuvenileId to set.
	 */
	public void setRelationshipToJuvenileId(String relationshipToJuvenileId) {
		this.relationshipToJuvenileId = relationshipToJuvenileId;
	}
	/**
	 * @return Returns the sequenceNum.
	 */
	public String getSequenceNum() {
		return sequenceNum;
	}
	/**
	 * @param sequenceNum The sequenceNum to set.
	 */
	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	/**
	 * @return Returns the ssn.
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param ssn The ssn to set.
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	/**
	 * @return Returns the transactionNum.
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	/**
	 * @param transactionNum The transactionNum to set.
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	/**
	 * @return Returns the addressType.
	 */
	public String getAddressType() {
		return addressType;
	}
	/**
	 * @param addressType The addressType to set.
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	/**
	 * @return Returns the aptNum.
	 */
	public String getAptNum() {
		return aptNum;
	}
	/**
	 * @param aptNum The aptNum to set.
	 */
	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
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
	 * @return Returns the county.
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * @param county The county to set.
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * @return Returns the otherAddressType.
	 */
	public String getOtherAddressType() {
		return otherAddressType;
	}
	/**
	 * @param otherAddressType The otherAddressType to set.
	 */
	public void setOtherAddressType(String otherAddressType) {
		this.otherAddressType = otherAddressType;
	}
	/**
	 * @return Returns the otherAptNum.
	 */
	public String getOtherAptNum() {
		return otherAptNum;
	}
	/**
	 * @param otherAptNum The otherAptNum to set.
	 */
	public void setOtherAptNum(String otherAptNum) {
		this.otherAptNum = otherAptNum;
	}
	/**
	 * @return Returns the otherCity.
	 */
	public String getOtherCity() {
		return otherCity;
	}
	/**
	 * @param otherCity The otherCity to set.
	 */
	public void setOtherCity(String otherCity) {
		this.otherCity = otherCity;
	}
	/**
	 * @return Returns the otherCounty.
	 */
	public String getOtherCounty() {
		return otherCounty;
	}
	/**
	 * @param otherCounty The otherCounty to set.
	 */
	public void setOtherCounty(String otherCounty) {
		this.otherCounty = otherCounty;
	}

	/**
	 * @return Returns the otherState.
	 */
	public String getOtherState() {
		return otherState;
	}
	/**
	 * @param otherState The otherState to set.
	 */
	public void setOtherState(String otherState) {
		this.otherState = otherState;
	}
	/**
	 * @return Returns the otherStreetName.
	 */
	public String getOtherStreetName() {
		return otherStreetName;
	}
	/**
	 * @param otherStreetName The otherStreetName to set.
	 */
	public void setOtherStreetName(String otherStreetName) {
		this.otherStreetName = otherStreetName;
	}
	/**
	 * @return Returns the otherStreetNumber.
	 */
	public String getOtherStreetNumber() {
		return otherStreetNumber;
	}
	/**
	 * @param otherStreetNumber The otherStreetNumber to set.
	 */
	public void setOtherStreetNumber(String otherStreetNumber) {
		this.otherStreetNumber = otherStreetNumber;
	}
	/**
	 * @return Returns the otherStreetType.
	 */
	public String getOtherStreetType() {
		return otherStreetType;
	}
	/**
	 * @param otherStreetType The otherStreetType to set.
	 */
	public void setOtherStreetType(String otherStreetType) {
		this.otherStreetType = otherStreetType;
	}
	/**
	 * @return Returns the otherZip.
	 */
	public String getOtherZip() {
		return otherZip;
	}
	/**
	 * @param otherZip The otherZip to set.
	 */
	public void setOtherZip(String otherZip) {
		this.otherZip = otherZip;
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
	 * @return Returns the streetType.
	 */
	public String getStreetType() {
		return streetType;
	}
	/**
	 * @param streetType The streetType to set.
	 */
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the zip.
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip The zip to set.
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * @return Returns the otherPhone.
	 */
	public PhoneNumberBean getOtherPhone() {
		return otherPhone;
	}
	/**
	 * @param otherPhone The otherPhone to set.
	 */
	public void setOtherPhone(PhoneNumberBean otherPhone) {
		this.otherPhone = otherPhone;
	}
	/**
	 * @return Returns the phone.
	 */
	public PhoneNumberBean getPhone() {
		return phone;
	}
	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(PhoneNumberBean phone) {
		this.phone = phone;
	}
	/**
	 * @return Returns the otherInd.
	 */
	public String getOtherInd() {
		return otherInd;
	}
	/**
	 * @param otherInd The otherInd to set.
	 */
	public void setOtherInd(String otherInd) {
		this.otherInd = otherInd;
	}
}