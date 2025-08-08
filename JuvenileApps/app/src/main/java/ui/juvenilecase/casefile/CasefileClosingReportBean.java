/*
 * Created on Dec 20, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile;

import java.util.Collection;

import ui.common.Address;
import ui.common.Name;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CasefileClosingReportBean
{
private String blank="";
private Name probationOfficer;
private Name juvenile;
private String assignmentDate;
private Collection goals;
private String supervisionEndDate;
private String currentDate;

private String petitionNumber;
private String dateOfBirth;
private String juvenileNumber;
private String programServiceProvider;

private String locationUnitName;
private Address locationAddress;

private String memberRelationship;

private Name guardianName = new Name();
private Address guardianAddress = new Address();

private String officerLastName;
private String officerMiddleName;
private String officerFirstName;
private String officerPhone;

private String managerLastName;
private String managerMiddleName;
private String managerFirstName;
private String managerPhone;

private String casefileId;
private String courtNum;

//added for creating calendar event for generating closing letter/packet ER#75517
private String source;

/**
 * @return
 */
public String getBlank()
{
	return blank;
}

/**
 * @param string
 */
public void setBlank(String string)
{
	blank = string;
}

/**
 * @return
 */
public String getAssignmentDate()
{
	return assignmentDate;
}

/**
 * @return
 */
public String getCurrentDate()
{
	return currentDate;
}

/**
 * @return
 */
public String getDateOfBirth()
{
	return dateOfBirth;
}

/**
 * @return
 */
public Collection getGoals()
{
	return goals;
}

/**
 * @return
 */
public Name getJuvenile()
{
	return juvenile;
}

/**
 * @return
 */
public String getJuvenileNumber()
{
	return juvenileNumber;
}

/**
 * @return
 */
public String getPetitionNumber()
{
	return petitionNumber;
}

/**
 * @return
 */
public Name getProbationOfficer()
{
	return probationOfficer;
}

/**
 * @return
 */
public String getProgramServiceProvider()
{
	return programServiceProvider;
}

/**
 * @return
 */
public String getSupervisionEndDate()
{
	return supervisionEndDate;
}

/**
 * @param string
 */
public void setAssignmentDate(String string)
{
	assignmentDate = string;
}

/**
 * @param string
 */
public void setCurrentDate(String string)
{
	currentDate = string;
}

/**
 * @param string
 */
public void setDateOfBirth(String string)
{
	dateOfBirth = string;
}

/**
 * @param collection
 */
public void setGoals(Collection collection)
{
	goals = collection;
}

/**
 * @param name
 */
public void setJuvenile(Name name)
{
	juvenile = name;
}

/**
 * @param string
 */
public void setJuvenileNumber(String string)
{
	juvenileNumber = string;
}

/**
 * @param string
 */
public void setPetitionNumber(String string)
{
	petitionNumber = string;
}

/**
 * @param name
 */
public void setProbationOfficer(Name name)
{
	probationOfficer = name;
}

/**
 * @param string
 */
public void setProgramServiceProvider(String string)
{
	programServiceProvider = string;
}

/**
 * @param string
 */
public void setSupervisionEndDate(String string)
{
	supervisionEndDate = string;
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
 * @return Returns the locationAddress.
 */
public Address getLocationAddress() {
	return locationAddress;
}
/**
 * @param locationAddress The locationAddress to set.
 */
public void setLocationAddress(Address locationAddress) {
	this.locationAddress = locationAddress;
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

public String getCasefileId() {
	return casefileId;
}

public void setCasefileId(String casefileId) {
	this.casefileId = casefileId;
}

/**
 * @return the courtNum
 */
public String getCourtNum() {
	return courtNum;
}

/**
 * @param courtNum the courtNum to set
 */
public void setCourtNum(String courtNum) {
	this.courtNum = courtNum;
}
/**
 * @return the source
 */
public String getSource() {
	return source;
}

/**
 * @param source the source to set
 */
public void setSource(String source) {
	this.source = source;
}
}