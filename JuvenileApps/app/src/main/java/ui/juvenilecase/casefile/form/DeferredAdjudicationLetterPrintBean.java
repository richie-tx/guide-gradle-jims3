/*
 * Created on Aug 29, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.casefile.form;

import ui.common.Address;
import ui.common.Name;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeferredAdjudicationLetterPrintBean {

	private Name probationOfficer;
	private Name juvenile;

	private String memberRelationship;
	
	private Name guardianName;
	private Address guardianAddress;

	private String currentDate;

	private String petitionNumber;
	
	private String locationUnitName;
	private Address locationAddress;
	
	private String officerLastName;
	private String officerMiddleName;
	private String officerFirstName;
	private String officerPhone;

	private String managerLastName;
	private String managerMiddleName;
	private String managerFirstName;
	private String managerPhone;
	
	public void clearAll() {
		probationOfficer = new Name();
		juvenile = new Name();

		memberRelationship = "";
		
		currentDate = "";
		petitionNumber = "";
		guardianName = new Name();
		guardianAddress = new Address();
		
	}
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
	 * @return Returns the juvenile.
	 */
	public Name getJuvenile() {
		return juvenile;
	}
	/**
	 * @param juvenile The juvenile to set.
	 */
	public void setJuvenile(Name juvenile) {
		this.juvenile = juvenile;
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
	 * @return Returns the petitionNumber.
	 */
	public String getPetitionNumber() {
		return petitionNumber;
	}
	/**
	 * @param petitionNumber The petitionNumber to set.
	 */
	public void setPetitionNumber(String petitionNumber) {
		this.petitionNumber = petitionNumber;
	}
	/**
	 * @return Returns the probationOfficer.
	 */
	public Name getProbationOfficer() {
		return probationOfficer;
	}
	/**
	 * @param probationOfficer The probationOfficer to set.
	 */
	public void setProbationOfficer(Name probationOfficer) {
		this.probationOfficer = probationOfficer;
	}
	
	/**
	 * @return Returns the guardianAddress.
	 */
	public Address getGuardianAddress() {
		return guardianAddress;
	}
	/**
	 * @param guardianAddress The guardianAddress to set.
	 */
	public void setGuardianAddress(Address guardianAddress) {
		this.guardianAddress = guardianAddress;
	}
	/**
	 * @return Returns the guardianName.
	 */
	public Name getGuardianName() {
		return guardianName;
	}
	/**
	 * @param guardianName The guardianName to set.
	 */
	public void setGuardianName(Name guardianName) {
		this.guardianName = guardianName;
	}
	public String getLocationUnitName() {
		return locationUnitName;
	}
	public void setLocationUnitName(String locationUnitName) {
		this.locationUnitName = locationUnitName;
	}
	public Address getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(Address locationAddress) {
		this.locationAddress = locationAddress;
	}
	public String getOfficerLastName() {
		return officerLastName;
	}
	public void setOfficerLastName(String officerLastName) {
		this.officerLastName = officerLastName;
	}
	public String getOfficerMiddleName() {
		return officerMiddleName;
	}
	public void setOfficerMiddleName(String officerMiddleName) {
		this.officerMiddleName = officerMiddleName;
	}
	public String getOfficerFirstName() {
		return officerFirstName;
	}
	public void setOfficerFirstName(String officerFirstName) {
		this.officerFirstName = officerFirstName;
	}
	public String getOfficerPhone() {
		return officerPhone;
	}
	public void setOfficerPhone(String officerPhone) {
		this.officerPhone = officerPhone;
	}
	public String getManagerLastName() {
		return managerLastName;
	}
	public void setManagerLastName(String managerLastName) {
		this.managerLastName = managerLastName;
	}
	public String getManagerMiddleName() {
		return managerMiddleName;
	}
	public void setManagerMiddleName(String managerMiddleName) {
		this.managerMiddleName = managerMiddleName;
	}
	public String getManagerFirstName() {
		return managerFirstName;
	}
	public void setManagerFirstName(String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
}
