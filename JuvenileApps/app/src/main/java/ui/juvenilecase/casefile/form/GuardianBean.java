package ui.juvenilecase.casefile.form;

import java.util.Date;

import ui.common.Address;
import ui.common.Name;
import ui.juvenilecase.form.JuvenileMemberForm;

public class GuardianBean
{

	private Name nameOfPerson;

	private String relationshipType;
	private String relationshipTypeId;

	private String memberNum;

	private String language;
	
	private String inHomeStatus;
	
	private String primaryContact;
	
	
	//facility changes
	private boolean visit;	
	private boolean detentionHearing;
	private String dob;
	private String driverLicenceNumber;
	private String driverLicenceStateId;
	private String driverLicenseState;
	private String stateIssuedIdNum;
	private String stateIssuedIdState;
	
	//court changes
	private Address guardianAddress; //for court briefing details page
	
	//added for Referral: User Story: 71650
	private String SSN;
	private String completeSSN;
	private JuvenileMemberForm.MemberContact guardianPhone;
	private boolean incarcerated;
	private boolean deceased;
	

	public GuardianBean()
	{
		nameOfPerson = new Name();
		relationshipType = "";
		memberNum = "";
		language = "";
		inHomeStatus="";
		primaryContact="";
		guardianAddress = new Address();
		SSN="";
		guardianPhone = new JuvenileMemberForm.MemberContact();
		incarcerated=false;
		deceased=false;
	}

	public String getMemberNum()
	{
		return memberNum;
	}

	public void setMemberNum(String memberNum)
	{
		this.memberNum = memberNum;
	}

	public Name getNameOfPerson()
	{
		return nameOfPerson;
	}

	public void setNameOfPerson(Name nameOfPerson)
	{
		this.nameOfPerson = nameOfPerson;
	}

	public String getRelationshipType()
	{
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType)
	{
		this.relationshipType = relationshipType;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}
	
	public String getInHomeStatus() {
		return inHomeStatus;
	}

	public void setInHomeStatus(String inHomeStatus) {
		this.inHomeStatus = inHomeStatus;
	}

	public String getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}

	

	/**
	 * @return the visit
	 */
	public boolean isVisit() {
		return visit;
	}

	/**
	 * @param visit the visit to set
	 */
	public void setVisit(boolean visit) {
		this.visit = visit;
	}

	/**
	 * @return the detentionHearing
	 */
	public boolean isDetentionHearing() {
		return detentionHearing;
	}

	/**
	 * @param detentionHearing the detentionHearing to set
	 */
	public void setDetentionHearing(boolean detentionHearing) {
		this.detentionHearing = detentionHearing;
	}

	public String getDriverLicenceNumber() {
		return driverLicenceNumber;
	}

	public void setDriverLicenceNumber(String driverLicenceNumber) {
		this.driverLicenceNumber = driverLicenceNumber;
	}

	public String getDriverLicenceStateId() {
		return driverLicenceStateId;
	}

	public void setDriverLicenceStateId(String driverLicenceStateId) {
		this.driverLicenceStateId = driverLicenceStateId;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDob() {
		return dob;
	}

	/**
	 * @return the driverLicenseState
	 */
	public String getDriverLicenseState() {
		return driverLicenseState;
	}

	/**
	 * @param driverLicenseState the driverLicenseState to set
	 */
	public void setDriverLicenseState(String driverLicenseState) {
		this.driverLicenseState = driverLicenseState;
	}

	/**
	 * @return the stateIssuedIdNum
	 */
	public String getStateIssuedIdNum() {
		return stateIssuedIdNum;
	}

	/**
	 * @param stateIssuedIdNum the stateIssuedIdNum to set
	 */
	public void setStateIssuedIdNum(String stateIssuedIdNum) {
		this.stateIssuedIdNum = stateIssuedIdNum;
	}

	/**
	 * @return the stateIssuedIdState
	 */
	public String getStateIssuedIdState() {
		return stateIssuedIdState;
	}

	/**
	 * @param stateIssuedIdState the stateIssuedIdState to set
	 */
	public void setStateIssuedIdState(String stateIssuedIdState) {
		this.stateIssuedIdState = stateIssuedIdState;
	}

	public String getRelationshipTypeId()
	{
	    return relationshipTypeId;
	}

	public void setRelationshipTypeId(String relationshipTypeId)
	{
	    this.relationshipTypeId = relationshipTypeId;
	}

	public Address getGuardianAddress() {
		return guardianAddress;
	}

	public void setGuardianAddress(Address guardianAddress) {
		this.guardianAddress = guardianAddress;
	}

	public String getSSN()
	{
	    return SSN;
	}

	public void setSSN(String sSN)
	{
	    SSN = sSN;
	}

	public JuvenileMemberForm.MemberContact getGuardianPhone()
	{
	    return guardianPhone;
	}

	public void setGuardianPhone(JuvenileMemberForm.MemberContact guardianPhone)
	{
	    this.guardianPhone = guardianPhone;
	}

	public boolean isIncarcerated()
	{
	    return incarcerated;
	}

	public void setIncarcerated(boolean incarcerated)
	{
	    this.incarcerated = incarcerated;
	}

	public boolean isDeceased()
	{
	    return deceased;
	}

	public void setDeceased(boolean deceased)
	{
	    this.deceased = deceased;
	}

	public String getCompleteSSN()
	{
	    return completeSSN;
	}

	public void setCompleteSSN(String completeSSN)
	{
	    this.completeSSN = completeSSN;
	}

	
	
}