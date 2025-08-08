/*
 * Created on May 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.schedulecalendarevent;

import java.util.ArrayList;
import java.util.List;

import messaging.contact.to.Address;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IdInfoPrintBean {
	
	private String locationUnitName = "";
	private Address locationUnitAddress;
	private String memberRelationship = "";
	private String guardianFirstName = "";
	private String guardianLastName = "";
	private String guardianMiddleName = "";
	
	private String guardianStreetNo = "";
	private String guardianStreetName = "";
	private String guardianStreetType = "";
	private String guardianApt = "";
	private String guardianCity = "";
	private String guardianState = "";
	private String guardianZip = "";
	private String guardianAdditionalZip = "";
		
	private String currentDate = "";
	
	private String juvenileLastName = "";
	private String juvenileMiddleName = "";
	private String juvenileFirstName = "";
	private String juvenilePrefix = "";
	private String juvenileSuffix = "";
	
	private String race = "";
	private String sex = "";
	private String dateOfBirth = "";
	private String primaryLanguage = "";
	private String birthCity = "";
	private String birthCounty = "";
	private String birthCountry = "";
	private String citizenship = "";
	private String nationality = "";
	private String alienNumber = "";
	
	//List of type ui.juvenilecase.schedulecalendarevent.GuardianPrintBean
	private List guardians = new ArrayList(); 
		
	private String officerLastName = "";
	private String officerMiddleName = "";
	private String officerFirstName = "";
	private String officerPhone = "";

	private String managerLastName = "";
	private String managerMiddleName = "";
	private String managerFirstName = "";
	private String managerPhone = "";
	
	/**
	 * @return Returns the currentDate.
	 */
	public String getCurrentDate() {
		return currentDate;
	}
	/**
	 * @param currentDate The currentDate to set.
	 */
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	
	/**
	 * @return Returns the guardianAdditionalZip.
	 */
	public String getGuardianAdditionalZip() {
		return guardianAdditionalZip;
	}
	/**
	 * @param guardianAdditionalZip The guardianAdditionalZip to set.
	 */
	public void setGuardianAdditionalZip(String guardianAdditionalZip) {
		this.guardianAdditionalZip = guardianAdditionalZip;
	}
	/**
	 * @return Returns the guardianCity.
	 */
	public String getGuardianCity() {
		return guardianCity;
	}
	/**
	 * @param guardianCity The guardianCity to set.
	 */
	public void setGuardianCity(String guardianCity) {
		this.guardianCity = guardianCity;
	}
	/**
	 * @return Returns the guardianState.
	 */
	public String getGuardianState() {
		return guardianState;
	}
	/**
	 * @param guardianState The guardianState to set.
	 */
	public void setGuardianState(String guardianState) {
		this.guardianState = guardianState;
	}
	/**
	 * @return Returns the guardianStreetName.
	 */
	public String getGuardianStreetName() {
		return guardianStreetName;
	}
	/**
	 * @param guardianStreetName The guardianStreetName to set.
	 */
	public void setGuardianStreetName(String guardianStreetName) {
		this.guardianStreetName = guardianStreetName;
	}
	/**
	 * @return Returns the guardianStreetNo.
	 */
	public String getGuardianStreetNo() {
		return guardianStreetNo;
	}
	/**
	 * @param guardianStreetNo The guardianStreetNo to set.
	 */
	public void setGuardianStreetNo(String guardianStreetNo) {
		this.guardianStreetNo = guardianStreetNo;
	}

	/**
	 * @return Returns the guardianApt.
	 */
	public String getGuardianApt() {
		return guardianApt;
	}
	/**
	 * @param guardianApt The guardianApt to set.
	 */
	public void setGuardianApt(String guardianApt) {
		this.guardianApt = guardianApt;
	}
	/**
	 * @return Returns the guardianZip.
	 */
	public String getGuardianZip() {
		return guardianZip;
	}
	/**
	 * @param guardianZip The guardianZip to set.
	 */
	public void setGuardianZip(String guardianZip) {
		this.guardianZip = guardianZip;
	}
	/**
	 * @return Returns the guardianFirstName.
	 */
	public String getGuardianFirstName() {
		return guardianFirstName;
	}
	/**
	 * @param guardianFirstName The guardianFirstName to set.
	 */
	public void setGuardianFirstName(String guardianFirstName) {
		this.guardianFirstName = guardianFirstName;
	}
	/**
	 * @return Returns the guardianLastName.
	 */
	public String getGuardianLastName() {
		return guardianLastName;
	}
	/**
	 * @param guardianLastName The guardianLastName to set.
	 */
	public void setGuardianLastName(String guardianLastName) {
		this.guardianLastName = guardianLastName;
	}
	/**
	 * @return Returns the guardianMiddleName.
	 */
	public String getGuardianMiddleName() {
		return guardianMiddleName;
	}
	/**
	 * @param guardianMiddleName The guardianMiddleName to set.
	 */
	public void setGuardianMiddleName(String guardianMiddleName) {
		this.guardianMiddleName = guardianMiddleName;
	}

	/**
	 * @return Returns the juvenileFirstName.
	 */
	public String getJuvenileFirstName() {
		return juvenileFirstName;
	}
	/**
	 * @param juvenileFirstName The juvenileFirstName to set.
	 */
	public void setJuvenileFirstName(String juvenileFirstName) {
		this.juvenileFirstName = juvenileFirstName;
	}
	/**
	 * @return Returns the juvenileLastName.
	 */
	public String getJuvenileLastName() {
		return juvenileLastName;
	}
	/**
	 * @param juvenileLastName The juvenileLastName to set.
	 */
	public void setJuvenileLastName(String juvenileLastName) {
		this.juvenileLastName = juvenileLastName;
	}
	/**
	 * @return Returns the juvenileMiddleName.
	 */
	public String getJuvenileMiddleName() {
		return juvenileMiddleName;
	}
	/**
	 * @param juvenileMiddleName The juvenileMiddleName to set.
	 */
	public void setJuvenileMiddleName(String juvenileMiddleName) {
		this.juvenileMiddleName = juvenileMiddleName;
	}
	
	public String getJuvenilePrefix() {
		return juvenilePrefix;
	}
	public void setJuvenilePrefix(String juvenilePrefix) {
		this.juvenilePrefix = juvenilePrefix;
	}
	public String getJuvenileSuffix() {
		return juvenileSuffix;
	}
	public void setJuvenileSuffix(String juvenileSuffix) {
		this.juvenileSuffix = juvenileSuffix;
	}
	public Address getLocationUnitAddress() {
		return locationUnitAddress;
	}
	public void setLocationUnitAddress(Address locationUnitAddress) {
		this.locationUnitAddress = locationUnitAddress;
	}
	/**
	 * @return Returns the locationUnitName.
	 */
	public String getLocationUnitName() {
		return locationUnitName;
	}
	/**
	 * @param locationUnitName The locationUnitName to set.
	 */
	public void setLocationUnitName(String locationUnitName) {
		this.locationUnitName = locationUnitName;
	}
	/**
	 * @return Returns the managerFirstName.
	 */
	public String getManagerFirstName() {
		return managerFirstName;
	}
	/**
	 * @param managerFirstName The managerFirstName to set.
	 */
	public void setManagerFirstName(String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}
	/**
	 * @return Returns the managerLastName.
	 */
	public String getManagerLastName() {
		return managerLastName;
	}
	/**
	 * @param managerLastName The managerLastName to set.
	 */
	public void setManagerLastName(String managerLastName) {
		this.managerLastName = managerLastName;
	}
	/**
	 * @return Returns the managerMiddleName.
	 */
	public String getManagerMiddleName() {
		return managerMiddleName;
	}
	/**
	 * @param managerMiddleName The managerMiddleName to set.
	 */
	public void setManagerMiddleName(String managerMiddleName) {
		this.managerMiddleName = managerMiddleName;
	}
	/**
	 * @return Returns the managerPhone.
	 */
	public String getManagerPhone() {
		return managerPhone;
	}
	/**
	 * @param managerPhone The managerPhone to set.
	 */
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	/**
	 * @return Returns the memberRelationship.
	 */
	public String getMemberRelationship() {
		return memberRelationship;
	}
	/**
	 * @param memberRelationship The memberRelationship to set.
	 */
	public void setMemberRelationship(String memberRelationship) {
		this.memberRelationship = memberRelationship;
	}
	/**
	 * @return Returns the officerFirstName.
	 */
	public String getOfficerFirstName() {
		return officerFirstName;
	}
	/**
	 * @param officerFirstName The officerFirstName to set.
	 */
	public void setOfficerFirstName(String officerFirstName) {
		this.officerFirstName = officerFirstName;
	}
	/**
	 * @return Returns the officerLastName.
	 */
	public String getOfficerLastName() {
		return officerLastName;
	}
	/**
	 * @param officerLastName The officerLastName to set.
	 */
	public void setOfficerLastName(String officerLastName) {
		this.officerLastName = officerLastName;
	}
	/**
	 * @return Returns the officerMiddleName.
	 */
	public String getOfficerMiddleName() {
		return officerMiddleName;
	}
	/**
	 * @param officerMiddleName The officerMiddleName to set.
	 */
	public void setOfficerMiddleName(String officerMiddleName) {
		this.officerMiddleName = officerMiddleName;
	}
	/**
	 * @return Returns the officerPhone.
	 */
	public String getOfficerPhone() {
		return officerPhone;
	}
	/**
	 * @param officerPhone The officerPhone to set.
	 */
	public void setOfficerPhone(String officerPhone) {
		this.officerPhone = officerPhone;
	}
	/**
	 * @return Returns the guardianStreetType.
	 */
	public String getGuardianStreetType() {
		return guardianStreetType;
	}
	/**
	 * @param guardianStreetType The guardianStreetType to set.
	 */
	public void setGuardianStreetType(String guardianStreetType) {
		this.guardianStreetType = guardianStreetType;
	}
	
	/**
	 * @return Returns the alienNumber.
	 */
	public String getAlienNumber() {
		return alienNumber;
	}
	/**
	 * @param alienNumber The alienNumber to set.
	 */
	public void setAlienNumber(String alienNumber) {
		this.alienNumber = alienNumber;
	}
	/**
	 * @return Returns the birthCity.
	 */
	public String getBirthCity() {
		return birthCity;
	}
	/**
	 * @param birthCity The birthCity to set.
	 */
	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}
	/**
	 * @return Returns the birthCountry.
	 */
	public String getBirthCountry() {
		return birthCountry;
	}
	/**
	 * @param birthCountry The birthCountry to set.
	 */
	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}
	/**
	 * @return Returns the citizenship.
	 */
	public String getCitizenship() {
		return citizenship;
	}
	/**
	 * @param citizenship The citizenship to set.
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	/**
	 * @return Returns the dateOfBirth.
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth The dateOfBirth to set.
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return Returns the guardians.
	 */
	public List getGuardians() {
		return guardians;
	}
	/**
	 * @param guardians The guardians to set.
	 */
	public void setGuardians(List guardians) {
		this.guardians = guardians;
	}
	/**
	 * @return Returns the nationality.
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality The nationality to set.
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return Returns the primaryLanguage.
	 */
	public String getPrimaryLanguage() {
		return primaryLanguage;
	}
	/**
	 * @param primaryLanguage The primaryLanguage to set.
	 */
	public void setPrimaryLanguage(String primaryLanguage) {
		this.primaryLanguage = primaryLanguage;
	}
	/**
	 * @return Returns the race.
	 */
	public String getRace() {
		return race;
	}
	/**
	 * @param race The race to set.
	 */
	public void setRace(String race) {
		this.race = race;
	}
	/**
	 * @return Returns the sex.
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex The sex to set.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return Returns the birthCounty.
	 */
	public String getBirthCounty() {
		return birthCounty;
	}
	/**
	 * @param birthCounty The birthCounty to set.
	 */
	public void setBirthCounty(String birthCounty) {
		this.birthCounty = birthCounty;
	}

}
