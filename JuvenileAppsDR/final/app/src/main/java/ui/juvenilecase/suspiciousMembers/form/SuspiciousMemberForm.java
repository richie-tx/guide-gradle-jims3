/*
 * Created on Oct 3, 2011
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.suspiciousMembers.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.contact.domintf.IName;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.Name;

/**
 * @author cShimek
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SuspiciousMemberForm extends ActionForm
{
	
//	general field values
	private List emptyList = new ArrayList();
	private String returnToSearch;
	private String secondaryAction = UIConstants.EMPTY_STRING;
	private String selectedValue = UIConstants.EMPTY_STRING;

// begin Search variables	
	private String lastName;
	private String firstName;
	private String socialSecurityNumber;
	private String ssn1;
	private String ssn2;
	private String ssn3;
	private String juvenileNumber;
	private String searchTypeId;
	private List searchResultsList;
	private String selectedOrigMemberNum;
	private boolean multipleOrigMatches;
	private String searchMemberAId;
	private String searchMemberBId;
	private boolean noMembersFound;
// end Search variables	
	
// begin profile header variables
	private String dateOfBirth;
	private String ethnicityLit;
	private String genderLit;
	private String juvenileName;	
	private String raceLit;
// end profile header variables	

// begin member info variables
	private List associatedJuvenilesList;
	private List associatedJuvenilesListOrig;  
	private List allAssociatedJuvenilesList;
	private String confirmationMsg;
	private List familyMemberInfoList;
	private String familyNumber;
	private String guardianLit;
	private List maritalStatusDetailsList;
	private List matchingMembersList;
	private List mergeMembersList;
	private List mergeMemberToList;
	private String memberDOB;
	private String memberGenderLit;
	private String memberName;
	private String memberNumber;
	private String memberSSN;
	private List processList;
	private String relationshipLit;
	private String selectedFromId;
	private String selectedToId;
	private String selFromId; 
	private String selToId; 
// end member info variables
	
// drop down select list
	private List causeOfDeathList;
	private List ethnicityList;
	private List dlClassList;
	private List languageList;	
	private List maritalStatusList;
	private List nationalityList;
	private List relationshipToJuvenileList;
	private List relationshipWithList;
	private List sexList;
	private List stateList;
	private List usCitizenList;
	
// correct/update member variables
	private String alienRegNumber;
	private String causeOfDeathId;
	private String causeOfDeathLit;
	private String comments;
	private String dateOfBirthStr;
	private String deceased;
	private String deceasedLit;
	private String divorceDateStr;
	private String dlClassId;	
	private String dlClassIdLit;
	private String dlExpDateStr;
	private String dlNumber;
	private String dlStateId;
	private String dlStateLit;
	private String ethnicityId;
	private String incarcerated;
	private String incarcetatedLit;
	private String juvenileAgeAtDeath; 
	private String maritalStatusId;
	private String maritalStatusLit;
	private String marriageDateStr;
	private String memberFirstName;	
	private String memberLastName;
	private String memberMiddleName;
	private String memberSSN1;
	private String memberSSN2;
	private String memberSSN3;
	private String nationalityId;
	private String nationalityLit;
	private String numberOfChildren;
	private String popup;
	private String primaryLanguageId;
	private String primaryLanguageLit;
	private String[] relationTypeId;
	private String relationshipWithId;
	private String relationshipWithLit;
	private String secondaryLanguageId;
	private String secondaryLanguageLit;
	private List similarMembers;
	private String sexId;
	private String sexLit;
	private String sidNum;
	private String stateIssuedCardNumber;
	private String stateIssuedCardStateId;
	private String stateIssuedCardStateLit;
	private String usCitizenId;
	private String usCitizenLit;
	private String memToName;
	private String address;
	private String email;
	private String phone;
	
	/*
	 * 
	 */
	public void clear()
	{
		this.clearMemberInfo();
		this.clearProfile();
		this.clearSearch();
	}

	public void clearSearch()
	{
		this.lastName = UIConstants.EMPTY_STRING;
		this.firstName = UIConstants.EMPTY_STRING;
		this.socialSecurityNumber = UIConstants.EMPTY_STRING;
		this.ssn1 = UIConstants.EMPTY_STRING;
		this.ssn2 = UIConstants.EMPTY_STRING;
		this.ssn3 = UIConstants.EMPTY_STRING;		
		this.juvenileNumber = UIConstants.EMPTY_STRING;
		this.searchTypeId = UIConstants.EMPTY_STRING;
		this.searchResultsList = emptyList;
		this.selectedOrigMemberNum = UIConstants.EMPTY_STRING;
		this.multipleOrigMatches = false;
		this.searchMemberAId = UIConstants.EMPTY_STRING;
	}
	
	public void clearProfile()
	{
		this.juvenileName = UIConstants.EMPTY_STRING;
		this.dateOfBirth = UIConstants.EMPTY_STRING;
		this.raceLit = UIConstants.EMPTY_STRING;
		this.genderLit = UIConstants.EMPTY_STRING;
		this.ethnicityLit = UIConstants.EMPTY_STRING;
	}
	
	public void clearMemberInfo()
	{
		this.alienRegNumber = UIConstants.EMPTY_STRING;
		this.causeOfDeathId = UIConstants.EMPTY_STRING;
		this.causeOfDeathLit = UIConstants.EMPTY_STRING;
		this.comments = UIConstants.EMPTY_STRING;
		this.dateOfBirthStr = UIConstants.EMPTY_STRING;
		this.deceased = UIConstants.EMPTY_STRING;
		this.deceasedLit = UIConstants.EMPTY_STRING;
		this.divorceDateStr = UIConstants.EMPTY_STRING;
		this.dlClassId = UIConstants.EMPTY_STRING;
		this.dlClassIdLit = UIConstants.EMPTY_STRING;
		this.dlExpDateStr = UIConstants.EMPTY_STRING;
		this.dlNumber = UIConstants.EMPTY_STRING;
		this.dlStateId = UIConstants.EMPTY_STRING;
		this.dlStateLit = UIConstants.EMPTY_STRING;
		this.ethnicityId = UIConstants.EMPTY_STRING;
		this.incarcerated = UIConstants.EMPTY_STRING;
		this.incarcetatedLit = UIConstants.EMPTY_STRING;
		this.maritalStatusId = UIConstants.EMPTY_STRING;
		this.maritalStatusLit = UIConstants.EMPTY_STRING;
		this.marriageDateStr = UIConstants.EMPTY_STRING;
		this.memberFirstName = UIConstants.EMPTY_STRING;	
		this.memberLastName = UIConstants.EMPTY_STRING;
		this.memberMiddleName = UIConstants.EMPTY_STRING;
		this.memberSSN1 = UIConstants.EMPTY_STRING;
		this.memberSSN2 = UIConstants.EMPTY_STRING;
		this.memberSSN3 = UIConstants.EMPTY_STRING;
		this.nationalityId = UIConstants.EMPTY_STRING;
		this.nationalityLit = UIConstants.EMPTY_STRING;
		this.numberOfChildren = UIConstants.EMPTY_STRING;
		this.popup = UIConstants.EMPTY_STRING;
		this.primaryLanguageId = UIConstants.EMPTY_STRING;
		this.primaryLanguageLit = UIConstants.EMPTY_STRING;
		this.relationshipWithId = UIConstants.EMPTY_STRING;
		this.relationshipWithLit = UIConstants.EMPTY_STRING;
		this.secondaryLanguageId = UIConstants.EMPTY_STRING;
		this.secondaryLanguageLit = UIConstants.EMPTY_STRING;
		this.similarMembers = emptyList;
		this.sexId = UIConstants.EMPTY_STRING;
		this.sexLit = UIConstants.EMPTY_STRING;
		this.sidNum = UIConstants.EMPTY_STRING;
		this.stateIssuedCardNumber = UIConstants.EMPTY_STRING;
		this.stateIssuedCardStateId = UIConstants.EMPTY_STRING;
		this.stateIssuedCardStateLit = UIConstants.EMPTY_STRING;
		this.usCitizenId = UIConstants.EMPTY_STRING;
		this.usCitizenLit = UIConstants.EMPTY_STRING;
		this.address = UIConstants.EMPTY_STRING;
		this.email = UIConstants.EMPTY_STRING;;
		this.phone = UIConstants.EMPTY_STRING;;
	}
	
// begin search getter and setters
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
	 * @return the memToName
	 */
	public String getMemToName() {
		return memToName;
	}
	
	/**
	 * @param memToName the memToName to set
	 */
	public void setMemToName(String memToName) {
		this.memToName = memToName;
	}
	
	
	
	

	public String getAddress()
	{
	    return address;
	}

	public void setAddress(String address)
	{
	    this.address = address;
	}

	public String getEmail()
	{
	    return email;
	}

	public void setEmail(String email)
	{
	    this.email = email;
	}

	public String getPhone()
	{
	    return phone;
	}

	public void setPhone(String phone)
	{
	    this.phone = phone;
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
	 * @return the socialSecurityNumber
	 */
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	/**
	 * @return the ssn1
	 */
	public String getSsn1() {
		return ssn1;
	}

	/**
	 * @param ssn1 the ssn1 to set
	 */
	public void setSsn1(String ssn1) {
		this.ssn1 = ssn1;
	}

	/**
	 * @return the ssn2
	 */
	public String getSsn2() {
		return ssn2;
	}

	/**
	 * @param ssn2 the ssn2 to set
	 */
	public void setSsn2(String ssn2) {
		this.ssn2 = ssn2;
	}

	/**
	 * @return the ssn3
	 */
	public String getSsn3() {
		return ssn3;
	}

	/**
	 * @param ssn3 the ssn3 to set
	 */
	public void setSsn3(String ssn3) {
		this.ssn3 = ssn3;
	}

	/**
	 * @return the searchTypeId
	 */
	public String getSearchTypeId() {
		return searchTypeId;
	}

	/**
	 * @param searchTypeId the searchTypeId to set
	 */
	public void setSearchTypeId(String searchTypeId) {
		this.searchTypeId = searchTypeId;
	}

	/**
	 * @return the searchResultsList
	 */
	public List getSearchResultsList() {
		return searchResultsList;
	}

	/**
	 * @param searchResultsList the searchResultsList to set
	 */
	public void setSearchResultsList(List searchResultsList) {
		this.searchResultsList = searchResultsList;
	}

	/**
	 * @return the selectedOrigMemberNum
	 */
	public String getSelectedOrigMemberNum() {
		return selectedOrigMemberNum;
	}

	/**
	 * @param selectedOrigMemberNum the selectedOrigMemberNum to set
	 */
	public void setSelectedOrigMemberNum(String selectedOrigMemberNum) {
		this.selectedOrigMemberNum = selectedOrigMemberNum;
	}

	/**
	 * @param socialSecurityNumber the socialSecurityNumber to set
	 */
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	/**
	 * @return the juvenileNumber
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}

	/**
	 * @param juvenileNumber the juvenileNumber to set
	 */
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}

	/**
	 * @return the multipleOrigMatches
	 */
	public boolean isMultipleOrigMatches() {
		return multipleOrigMatches;
	}

	/**
	 * @param multipleOrigMatches the multipleOrigMatches to set
	 */
	public void setMultipleOrigMatches(boolean multipleOrigMatches) {
		this.multipleOrigMatches = multipleOrigMatches;
	}

	// end search getter and setters		
	/**
	 * @return the returnToSearch
	 */
	public String getReturnToSearch() {
		return returnToSearch;
	}

	/**
	 * @param returnToSearch the returnToSearch to set
	 */
	public void setReturnToSearch(String returnToSearch) {
		this.returnToSearch = returnToSearch;
	}

	/**
	 * @return the secondaryAction
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}

	/**
	 * @param secondaryAction the secondaryAction to set
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}

	/**
	 * @return the selectedValue
	 */
	public String getSelectedValue() {
		return selectedValue;
	}

	/**
	 * @param selectedValue the selectedValue to set
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	
// begin profile header getter and setters

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the ethnicityLit
	 */
	public String getEthnicityLit() {
		return ethnicityLit;
	}

	/**
	 * @param ethnicityLit the ethnicityLit to set
	 */
	public void setEthnicityLit(String ethnicityLit) {
		this.ethnicityLit = ethnicityLit;
	}

	/**
	 * @return the genderLit
	 */
	public String getGenderLit() {
		return genderLit;
	}

	/**
	 * @param genderLit the genderLit to set
	 */
	public void setGenderLit(String genderLit) {
		this.genderLit = genderLit;
	}

	/**
	 * @return the juvenileName
	 */
	public String getJuvenileName() {
		return juvenileName;
	}

	/**
	 * @param juvenileName the juvenileName to set
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}

	/**
	 * @return the raceLit
	 */
	public String getRaceLit() {
		return raceLit;
	}

	/**
	 * @param raceLit the raceLit to set
	 */
	public void setRaceLit(String raceLit) {
		this.raceLit = raceLit;
	}
// end profile header getter and setters	

	/**
	 * @return the associatedJuvenilesList
	 */
	public List getAssociatedJuvenilesList() {
		return associatedJuvenilesList;
	}

	/**
	 * @param associatedJuvenilesList the associatedJuvenilesList to set
	 */
	public void setAssociatedJuvenilesList(List associatedJuvenilesList) {
		this.associatedJuvenilesList = associatedJuvenilesList;
	}

	/**
	 * @return the associatedJuvenilesListOrig
	 */
	public List getAssociatedJuvenilesListOrig() {
		return associatedJuvenilesListOrig;
	}

	/**
	 * @param associatedJuvenilesListOrig the associatedJuvenilesListOrig to set
	 */
	public void setAssociatedJuvenilesListOrig(List associatedJuvenilesListOrig) {
		this.associatedJuvenilesListOrig = associatedJuvenilesListOrig;
	}

	/**
	 * @return the confirmationMsg
	 */
	public String getConfirmationMsg() {
		return confirmationMsg;
	}

	/**
	 * @param confirmationMsg the confirmationMsg to set
	 */
	public void setConfirmationMsg(String confirmationMsg) {
		this.confirmationMsg = confirmationMsg;
	}

	/**
	 * @return the familyMemberInfoList
	 */
	public List getFamilyMemberInfoList() {
		return familyMemberInfoList;
	}

	/**
	 * @param familyMemberInfoList the familyMemberInfoList to set
	 */
	public void setFamilyMemberInfoList(List familyMemberInfoList) {
		this.familyMemberInfoList = familyMemberInfoList;
	}


	/**
	 * @return the familyNumber
	 */
	public String getFamilyNumber() {
		return familyNumber;
	}

	/**
	 * @param familyNumber the familyNumber to set
	 */
	public void setFamilyNumber(String familyNumber) {
		this.familyNumber = familyNumber;
	}

	/**
	 * @return the guardianLit
	 */
	public String getGuardianLit() {
		return guardianLit;
	}

	/**
	 * @param guardianLit the guardianLit to set
	 */
	public void setGuardianLit(String guardianLit) {
		this.guardianLit = guardianLit;
	}

	/**
	 * @return the maritalStatusDetailsList
	 */
	public List getMaritalStatusDetailsList() {
		return maritalStatusDetailsList;
	}

	/**
	 * @param maritalStatusDetailsList the maritalStatusDetailsList to set
	 */
	public void setMaritalStatusDetailsList(List maritalStatusDetailsList) {
		this.maritalStatusDetailsList = maritalStatusDetailsList;
	}

	/**
	 * @return the matchingMembersList
	 */
	public List getMatchingMembersList() {
		return matchingMembersList;
	}

	/**
	 * @param matchingMembersList the matchingMembersList to set
	 */
	public void setMatchingMembersList(List matchingMembersList) {
		this.matchingMembersList = matchingMembersList;
	}

	/**
	 * @return the processList
	 */
	public List getProcessList() {
		return processList;
	}

	/**
	 * @param processList the processList to set
	 */
	public void setProcessList(List processList) {
		this.processList = processList;
	}

	/**
	 * @return the mergeMembersList
	 */
	public List getMergeMembersList() {
		return mergeMembersList;
	}

	/**
	 * @param mergeMembersList the mergeMembersList to set
	 */
	public void setMergeMembersList(List mergeMembersList) {
		this.mergeMembersList = mergeMembersList;
	}

	/**
	 * @return the mergeMemberToList
	 */
	public List getMergeMemberToList() {
		return mergeMemberToList;
	}

	/**
	 * @param mergeMemberToList the mergeMemberToList to set
	 */
	public void setMergeMemberToList(List mergeMemberToList) {
		this.mergeMemberToList = mergeMemberToList;
	}

	/**
	 * @return the memberDOB
	 */
	public String getMemberDOB() {
		return memberDOB;
	}

	/**
	 * @param memberDOB the memberDOB to set
	 */
	public void setMemberDOB(String memberDOB) {
		this.memberDOB = memberDOB;
	}

	/**
	 * @return the memberGenderLit
	 */
	public String getMemberGenderLit() {
		return memberGenderLit;
	}

	/**
	 * @param memberGenderLit the memberGenderLit to set
	 */
	public void setMemberGenderLit(String memberGenderLit) {
		this.memberGenderLit = memberGenderLit;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the memberNumber
	 */
	public String getMemberNumber() {
		return memberNumber;
	}

	/**
	 * @param memberNumber the memberNumber to set
	 */
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	/**
	 * @return the memberSSN
	 */
	public String getMemberSSN() {
		return memberSSN;
	}

	/**
	 * @param memberSSN the memberSSN to set
	 */
	public void setMemberSSN(String memberSSN) {
		this.memberSSN = memberSSN;
	}

	/**
	 * @return the relationshipLit
	 */
	public String getRelationshipLit() {
		return relationshipLit;
	}

	/**
	 * @param relationshipLit the relationshipLit to set
	 */
	public void setRelationshipLit(String relationshipLit) {
		this.relationshipLit = relationshipLit;
	}

	/**
	 * @return the selectedFromId
	 */
	public String getSelectedFromId() {
		return selectedFromId;
	}

	/**
	 * @param selectedFromId the selectedFromId to set
	 */
	public void setSelectedFromId(String selectedFromId) {
		this.selectedFromId = selectedFromId;
	}

	/**
	 * @return the selectedToId
	 */
	public String getSelectedToId() {
		return selectedToId;
	}

	/**
	 * @param selectedToId the selectedToId to set
	 */
	public void setSelectedToId(String selectedToId) {
		this.selectedToId = selectedToId;
	}

	/**
	 * @return the selFromId
	 */
	public String getSelFromId() {
		return selFromId;
	}

	/**
	 * @param selFromId the selFromId to set
	 */
	public void setSelFromId(String selFromId) {
		this.selFromId = selFromId;
	}

	/**
	 * @return the selToId
	 */
	public String getSelToId() {
		return selToId;
	}

	/**
	 * @param selToId the selToId to set
	 */
	public void setSelToId(String selToId) {
		this.selToId = selToId;
	}
// begin drop down list getters and setters
	/**
	 * @return the sexList
	 */
	public List getSexList() {
		return sexList;
	}

	/**
	 * @param sexList the sexList to set
	 */
	public void setSexList(List sexList) {
		this.sexList = sexList;
	}

	/**
	 * @return the usCitizenList
	 */
	public List getUsCitizenList() {
		return usCitizenList;
	}

	/**
	 * @param usCitizenList the usCitizenList to set
	 */
	public void setUsCitizenList(List usCitizenList) {
		this.usCitizenList = usCitizenList;
	}

	/**
	 * @return the usCitizenLit
	 */
	public String getUsCitizenLit() {
		return usCitizenLit;
	}

	/**
	 * @param usCitizenLit the usCitizenLit to set
	 */
	public void setUsCitizenLit(String usCitizenLit) {
		this.usCitizenLit = usCitizenLit;
	}

	/**
	 * @return the nationalityList
	 */
	public List getNationalityList() {
		return nationalityList;
	}

	/**
	 * @param nationalityList the nationalityList to set
	 */
	public void setNationalityList(List nationalityList) {
		this.nationalityList = nationalityList;
	}

	/**
	 * @return the causeOfDeathList
	 */
	public List getCauseOfDeathList() {
		return causeOfDeathList;
	}

	/**
	 * @param causeOfDeathList the causeOfDeathList to set
	 */
	public void setCauseOfDeathList(List causeOfDeathList) {
		this.causeOfDeathList = causeOfDeathList;
	}

	/**
	 * @return the ethnicityList
	 */
	public List getEthnicityList() {
		return ethnicityList;
	}

	/**
	 * @param ethnicityList the ethnicityList to set
	 */
	public void setEthnicityList(List ethnicityList) {
		this.ethnicityList = ethnicityList;
	}

	/**
	 * @return the dlClassList
	 */
	public List getDlClassList() {
		return dlClassList;
	}

	/**
	 * @param dlClassList the dlClassList to set
	 */
	public void setDlClassList(List dlClassList) {
		this.dlClassList = dlClassList;
	}

	/**
	 * @return the languageList
	 */
	public List getLanguageList() {
		return languageList;
	}

	/**
	 * @param languageList the languageList to set
	 */
	public void setLanguageList(List languageList) {
		this.languageList = languageList;
	}

	/**
	 * @return the stateList
	 */
	public List getStateList() {
		return stateList;
	}

	/**
	 * @param stateList the stateList to set
	 */
	public void setStateList(List stateList) {
		this.stateList = stateList;
	}

	/**
	 * @return the relationshipToJuvenileList
	 */
	public List getRelationshipToJuvenileList() {
		return relationshipToJuvenileList;
	}

	/**
	 * @param relationshipToJuvenileList the relationshipToJuvenileList to set
	 */
	public void setRelationshipToJuvenileList(List relationshipToJuvenileList) {
		this.relationshipToJuvenileList = relationshipToJuvenileList;
	}

	/**
	 * @return the maritalStatusList
	 */
	public List getMaritalStatusList() {
		return maritalStatusList;
	}

	/**
	 * @param maritalStatusList the maritalStatusList to set
	 */
	public void setMaritalStatusList(List maritalStatusList) {
		this.maritalStatusList = maritalStatusList;
	}

	/**
	 * @return the relationshipWithList
	 */
	public List getRelationshipWithList() {
		return relationshipWithList;
	}

	/**
	 * @param relationshipWithList the relationshipWithList to set
	 */
	public void setRelationshipWithList(List relationshipWithList) {
		this.relationshipWithList = relationshipWithList;
	}
// end drop down list getters and setters
// begin getter and setters for member correction

	/**
	 * @return the alienRegNumber
	 */
	public String getAlienRegNumber() {
		return alienRegNumber;
	}

	/**
	 * @param alienRegNumber the alienRegNumber to set
	 */
	public void setAlienRegNumber(String alienRegNumber) {
		this.alienRegNumber = alienRegNumber;
	}

	/**
	 * @return the causeOfDeathId
	 */
	public String getCauseOfDeathId() {
		return causeOfDeathId;
	}

	/**
	 * @param causeOfDeathId the causeOfDeathId to set
	 */
	public void setCauseOfDeathId(String causeOfDeathId) {
		this.causeOfDeathId = causeOfDeathId;
	}

	/**
	 * @return the causeOfDeathLit
	 */
	public String getCauseOfDeathLit() {
		return causeOfDeathLit;
	}

	/**
	 * @param causeOfDeathLit the causeOfDeathLit to set
	 */
	public void setCauseOfDeathLit(String causeOfDeathLit) {
		this.causeOfDeathLit = causeOfDeathLit;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the dateOfBirthStr
	 */
	public String getDateOfBirthStr() {
		return dateOfBirthStr;
	}

	/**
	 * @param dateOfBirthStr the dateOfBirthStr to set
	 */
	public void setDateOfBirthStr(String dateOfBirthStr) {
		this.dateOfBirthStr = dateOfBirthStr;
	}

	/**
	 * @return the deceased
	 */
	public String getDeceased() {
		return deceased;
	}

	/**
	 * @param deceased the deceased to set
	 */
	public void setDeceased(String deceased) {
		this.deceased = deceased;
	}

	/**
	 * @return the deceasedLit
	 */
	public String getDeceasedLit() {
		return deceasedLit;
	}

	/**
	 * @param deceasedLit the deceasedLit to set
	 */
	public void setDeceasedLit(String deceasedLit) {
		this.deceasedLit = deceasedLit;
	}

	/**
	 * @return the divorceDateStr
	 */
	public String getDivorceDateStr() {
		return divorceDateStr;
	}

	/**
	 * @param divorceDateStr the divorceDateStr to set
	 */
	public void setDivorceDateStr(String divorceDateStr) {
		this.divorceDateStr = divorceDateStr;
	}

	/**
	 * @return the dlClassId
	 */
	public String getDlClassId() {
		return dlClassId;
	}

	/**
	 * @param dlClassId the dlClassId to set
	 */
	public void setDlClassId(String dlClassId) {
		this.dlClassId = dlClassId;
	}

	/**
	 * @return the dlClassIdLit
	 */
	public String getDlClassIdLit() {
		return dlClassIdLit;
	}

	/**
	 * @param dlClassIdLit the dlClassIdLit to set
	 */
	public void setDlClassIdLit(String dlClassIdLit) {
		this.dlClassIdLit = dlClassIdLit;
	}

	/**
	 * @return the dlExpDateStr
	 */
	public String getDlExpDateStr() {
		return dlExpDateStr;
	}

	/**
	 * @param dlExpDateStr the dlExpDateStr to set
	 */
	public void setDlExpDateStr(String dlExpDateStr) {
		this.dlExpDateStr = dlExpDateStr;
	}

	/**
	 * @return the dlNumber
	 */
	public String getDlNumber() {
		return dlNumber;
	}

	/**
	 * @param dlNumber the dlNumber to set
	 */
	public void setDlNumber(String dlNumber) {
		this.dlNumber = dlNumber;
	}

	/**
	 * @return the dlStateId
	 */
	public String getDlStateId() {
		return dlStateId;
	}

	/**
	 * @param dlStateId the dlStateId to set
	 */
	public void setDlStateId(String dlStateId) {
		this.dlStateId = dlStateId;
	}

	/**
	 * @return the dlStateLit
	 */
	public String getDlStateLit() {
		return dlStateLit;
	}

	/**
	 * @param dlStateLit the dlStateLit to set
	 */
	public void setDlStateLit(String dlStateLit) {
		this.dlStateLit = dlStateLit;
	}

	/**
	 * @return the ethnicityId
	 */
	public String getEthnicityId() {
		return ethnicityId;
	}

	/**
	 * @param ethnicityId the ethnicityId to set
	 */
	public void setEthnicityId(String ethnicityId) {
		this.ethnicityId = ethnicityId;
	}

	/**
	 * @return the incarcerated
	 */
	public String getIncarcerated() {
		return incarcerated;
	}

	/**
	 * @param incarcerated the incarcerated to set
	 */
	public void setIncarcerated(String incarcerated) {
		this.incarcerated = incarcerated;
	}

	/**
	 * @return the incarcetatedLit
	 */
	public String getIncarcetatedLit() {
		return incarcetatedLit;
	}

	/**
	 * @param incarcetatedLit the incarcetatedLit to set
	 */
	public void setIncarcetatedLit(String incarcetatedLit) {
		this.incarcetatedLit = incarcetatedLit;
	}

	/**
	 * @return the juvenileAgeAtDeath
	 */
	public String getJuvenileAgeAtDeath() {
		return juvenileAgeAtDeath;
	}

	/**
	 * @param juvenileAgeAtDeath the juvenileAgeAtDeath to set
	 */
	public void setJuvenileAgeAtDeath(String juvenileAgeAtDeath) {
		this.juvenileAgeAtDeath = juvenileAgeAtDeath;
	}

	/**
	 * @return the maritalStatusId
	 */
	public String getMaritalStatusId() {
		return maritalStatusId;
	}

	/**
	 * @param maritalStatusId the maritalStatusId to set
	 */
	public void setMaritalStatusId(String maritalStatusId) {
		this.maritalStatusId = maritalStatusId;
	}

	/**
	 * @return the maritalStatusLit
	 */
	public String getMaritalStatusLit() {
		return maritalStatusLit;
	}

	/**
	 * @param maritalStatusLit the maritalStatusLit to set
	 */
	public void setMaritalStatusLit(String maritalStatusLit) {
		this.maritalStatusLit = maritalStatusLit;
	}

	/**
	 * @return the marriageDateStr
	 */
	public String getMarriageDateStr() {
		return marriageDateStr;
	}

	/**
	 * @param marriageDateStr the marriageDateStr to set
	 */
	public void setMarriageDateStr(String marriageDateStr) {
		this.marriageDateStr = marriageDateStr;
	}

	/**
	 * @return the memberFirstName
	 */
	public String getMemberFirstName() {
		return memberFirstName;
	}

	/**
	 * @param memberFirstName the memberFirstName to set
	 */
	public void setMemberFirstName(String memberFirstName) {
		this.memberFirstName = memberFirstName;
	}

	/**
	 * @return the memberLastName
	 */
	public String getMemberLastName() {
		return memberLastName;
	}

	/**
	 * @param memberLastName the memberLastName to set
	 */
	public void setMemberLastName(String memberLastName) {
		this.memberLastName = memberLastName;
	}


	/**
	 * @return the memberMiddleName
	 */
	public String getMemberMiddleName() {
		return memberMiddleName;
	}

	/**
	 * @param memberMiddleName the memberMiddleName to set
	 */
	public void setMemberMiddleName(String memberMiddleName) {
		this.memberMiddleName = memberMiddleName;
	}

	/**
	 * @return the memberSSN1
	 */
	public String getMemberSSN1() {
		return memberSSN1;
	}

	/**
	 * @param memberSSN1 the memberSSN1 to set
	 */
	public void setMemberSSN1(String memberSSN1) {
		this.memberSSN1 = memberSSN1;
	}

	/**
	 * @return the memberSSN2
	 */
	public String getMemberSSN2() {
		return memberSSN2;
	}

	/**
	 * @param memberSSN2 the memberSSN2 to set
	 */
	public void setMemberSSN2(String memberSSN2) {
		this.memberSSN2 = memberSSN2;
	}

	/**
	 * @return the memberSSN3
	 */
	public String getMemberSSN3() {
		return memberSSN3;
	}

	/**
	 * @param memberSSN3 the memberSSN3 to set
	 */
	public void setMemberSSN3(String memberSSN3) {
		this.memberSSN3 = memberSSN3;
	}

	/**
	 * @return the nationalityId
	 */
	public String getNationalityId() {
		return nationalityId;
	}

	/**
	 * @param nationalityId the nationalityId to set
	 */
	public void setNationalityId(String nationalityId) {
		this.nationalityId = nationalityId;
	}

	/**
	 * @return the nationalityLit
	 */
	public String getNationalityLit() {
		return nationalityLit;
	}

	/**
	 * @param nationalityLit the nationalityLit to set
	 */
	public void setNationalityLit(String nationalityLit) {
		this.nationalityLit = nationalityLit;
	}

	/**
	 * @return the numberOfChildren
	 */
	public String getNumberOfChildren() {
		return numberOfChildren;
	}

	/**
	 * @param numberOfChildren the numberOfChildren to set
	 */
	public void setNumberOfChildren(String numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	/**
	 * @return the popup
	 */
	public String getPopup() {
		return popup;
	}

	/**
	 * @param popup the popup to set
	 */
	public void setPopup(String popup) {
		this.popup = popup;
	}

	/**
	 * @return the primaryLanguageId
	 */
	public String getPrimaryLanguageId() {
		return primaryLanguageId;
	}

	/**
	 * @param primaryLanguageId the primaryLanguageId to set
	 */
	public void setPrimaryLanguageId(String primaryLanguageId) {
		this.primaryLanguageId = primaryLanguageId;
	}

	/**
	 * @return the primaryLanguageLit
	 */
	public String getPrimaryLanguageLit() {
		return primaryLanguageLit;
	}

	/**
	 * @param primaryLanguageLit the primaryLanguageLit to set
	 */
	public void setPrimaryLanguageLit(String primaryLanguageLit) {
		this.primaryLanguageLit = primaryLanguageLit;
	}

	/**
	 * @return the relationTypeId
	 */
	public String[] getRelationTypeId() {
		return relationTypeId;
	}

	/**
	 * @param relationTypeId the relationTypeId to set
	 */
	public void setRelationTypeId(String[] relationTypeId) {
		this.relationTypeId = relationTypeId;
	}

	/**
	 * @return the relationshipWithId
	 */
	public String getRelationshipWithId() {
		return relationshipWithId;
	}

	/**
	 * @param relationshipWithId the relationshipWithId to set
	 */
	public void setRelationshipWithId(String relationshipWithId) {
		this.relationshipWithId = relationshipWithId;
	}

	/**
	 * @return the relationshipWithLit
	 */
	public String getRelationshipWithLit() {
		return relationshipWithLit;
	}

	/**
	 * @param relationshipWithLit the relationshipWithLit to set
	 */
	public void setRelationshipWithLit(String relationshipWithLit) {
		this.relationshipWithLit = relationshipWithLit;
	}

	/**
	 * @return the secondaryLanguageId
	 */
	public String getSecondaryLanguageId() {
		return secondaryLanguageId;
	}

	/**
	 * @param secondaryLanguageId the secondaryLanguageId to set
	 */
	public void setSecondaryLanguageId(String secondaryLanguageId) {
		this.secondaryLanguageId = secondaryLanguageId;
	}

	/**
	 * @return the secondaryLanguageLit
	 */
	public String getSecondaryLanguageLit() {
		return secondaryLanguageLit;
	}

	/**
	 * @param secondaryLanguageLit the secondaryLanguageLit to set
	 */
	public void setSecondaryLanguageLit(String secondaryLanguageLit) {
		this.secondaryLanguageLit = secondaryLanguageLit;
	}

	/**
	 * @return the sexId
	 */
	public String getSexId() {
		return sexId;
	}

	/**
	 * @param sexId the sexId to set
	 */
	public void setSexId(String sexId) {
		this.sexId = sexId;
	}

	/**
	 * @return the sexLit
	 */
	public String getSexLit() {
		return sexLit;
	}

	/**
	 * @param sexLit the sexLit to set
	 */
	public void setSexLit(String sexLit) {
		this.sexLit = sexLit;
	}

	/**
	 * @return the sidNum
	 */
	public String getSidNum() {
		return sidNum;
	}

	/**
	 * @param sid the sidNum to set
	 */
	public void setSidNum(String sidNum) {
		this.sidNum = sidNum;
	}

	public String getSearchMemberAId()
	{
	    return searchMemberAId;
	}

	public void setSearchMemberAId(String searchMemberAId)
	{
	    this.searchMemberAId = searchMemberAId;
	}

	public String getSearchMemberBId()
	{
	    return searchMemberBId;
	}

	public void setSearchMemberBId(String searchMemberBId)
	{
	    this.searchMemberBId = searchMemberBId;
	}

	/**
	 * @return the similarMembers
	 */
	public List getSimilarMembers() {
		return similarMembers;
	}

	/**
	 * @param similarMembers the similarMembers to set
	 */
	public void setSimilarMembers(List similarMembers) {
		this.similarMembers = similarMembers;
	}

	/**
	 * @return the stateIssuedCardNumber
	 */
	public String getStateIssuedCardNumber() {
		return stateIssuedCardNumber;
	}

	/**
	 * @param stateIssuedCardNumber the stateIssuedCardNumber to set
	 */
	public void setStateIssuedCardNumber(String stateIssuedCardNumber) {
		this.stateIssuedCardNumber = stateIssuedCardNumber;
	}

	/**
	 * @return the stateIssuedCardStateId
	 */
	public String getStateIssuedCardStateId() {
		return stateIssuedCardStateId;
	}

	/**
	 * @param stateIssuedCardStateId the stateIssuedCardStateId to set
	 */
	public void setStateIssuedCardStateId(String stateIssuedCardStateId) {
		this.stateIssuedCardStateId = stateIssuedCardStateId;
	}

	/**
	 * @return the stateIssuedCardStateLit
	 */
	public String getStateIssuedCardStateLit() {
		return stateIssuedCardStateLit;
	}

	/**
	 * @param stateIssuedCardStateLit the stateIssuedCardStateLit to set
	 */
	public void setStateIssuedCardStateLit(String stateIssuedCardStateLit) {
		this.stateIssuedCardStateLit = stateIssuedCardStateLit;
	}

	/**
	 * @return the usCitizenId
	 */
	public String getUsCitizenId() {
		return usCitizenId;
	}

	/**
	 * @param usCitizenId the usCitizenId to set
	 */
	public void setUsCitizenId(String usCitizenId) {
		this.usCitizenId = usCitizenId;
	}
	
    public void setAllAssociatedJuvenilesList(List allAssociatedJuvenilesList) {
		this.allAssociatedJuvenilesList = allAssociatedJuvenilesList;
	}

	public List getAllAssociatedJuvenilesList() {
		return allAssociatedJuvenilesList;
	}

	public boolean isNoMembersFound()
	{
	    return noMembersFound;
	}

	public void setNoMembersFound(boolean noMembersFound)
	{
	    this.noMembersFound = noMembersFound;
	}

	public static class MaritalList implements Comparable
    {
        private String entryDate = UIConstants.EMPTY_STRING;

        private String marriageDate = UIConstants.EMPTY_STRING;

        private String divorceDate = UIConstants.EMPTY_STRING;

        private String numOfChildren = UIConstants.EMPTY_STRING;

        private String maritalStatusId = UIConstants.EMPTY_STRING;

        private String maritalStatus = UIConstants.EMPTY_STRING;

        private String maritalId = UIConstants.EMPTY_STRING;

        private String relatedFamMemId;

        private IName relatedFamMemName = new Name();

        public int compareTo(Object obj) throws ClassCastException
        {
            if (obj == null)
                return -1;
            MaritalList evt = (MaritalList) obj;
            int result = 0;
            if (getMaritalId() != null && evt.getMaritalId() != null)
            {
                try
                {
                    if (this.entryDate != null || evt.getEntryDate() != null)
                    {
                        if (evt.getEntryDate() == null && !(evt.getEntryDate().trim().equals(UIConstants.EMPTY_STRING)))
                            return 1; // this makes any null objects go
                        // to the bottom change this to -1
                        // if you want the top of the list
                        if (this.entryDate == null && !(this.getEntryDate().trim().equals(UIConstants.EMPTY_STRING)))
                            return -1; // this makes any null objects go
                        // to the bottom change this to 1
                        // if you want the top of the
                        // list
                        Date currDate = DateUtil.stringToDate(this.entryDate, UIConstants.DATE_FMT_1);
                        Date incomingDate = DateUtil.stringToDate(evt.getEntryDate(), UIConstants.DATE_FMT_1);
                        if (currDate == null)
                            return -1;
                        if (incomingDate == null)
                            return 1;
                        result = incomingDate.compareTo(currDate); // backwards
                        // in order to get list to show up most recent first
                    }
                    else
                    {
                        result = Integer.valueOf(getMaritalId()).compareTo(Integer.valueOf(evt.getMaritalId()));
                    }
                }
                catch (NumberFormatException e)
                {
                    result = 0;
                }
            }
            return result;
        }
        /**
         * @return
         */
        public String getDivorceDate()
        {
            return divorceDate;
        }

        /**
         * @return
         */
        public String getEntryDate()
        {
            return entryDate;
        }

        /**
         * @return
         */
        public String getMaritalStatus()
        {
            return maritalStatus;
        }

        /**
         * @return
         */
        public String getMaritalStatusId()
        {
            return maritalStatusId;
        }

        /**
         * @return
         */
        public String getMarriageDate()
        {
            return marriageDate;
        }

        /**
         * @return
         */
        public String getNumOfChildren()
        {
            return numOfChildren;
        }

        /**
         * @param string
         */
        public void setDivorceDate(String string)
        {
            divorceDate = string;
        }

        /**
         * @param string
         */
        public void setEntryDate(String string)
        {
            entryDate = string;
        }

        /**
         * @param string
         */
        public void setMaritalStatus(String string)
        {
            maritalStatus = string;
        }

        /**
         * @param string
         */
        public void setMaritalStatusId(String string)
        {
            maritalStatusId = string;
//            if (maritalStatusId == null || maritalStatusId.equals(UIConstants.EMPTY_STRING))
//            {
//                setMaritalStatus(UIConstants.EMPTY_STRING);
//                return;
//            }
//            if (JuvenileMemberForm.maritalStatusList != null && JuvenileMemberForm.maritalStatusList.size() > 0)
//            {
//                maritalStatus = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.maritalStatusList,
//                        maritalStatusId);
//            }
        }

        /**
         * @param string
         */
        public void setMarriageDate(String string)
        {
            marriageDate = string;
        }

        /**
         * @param string
         */
        public void setNumOfChildren(String string)
        {
            numOfChildren = string;
        }

        /**
         * @return
         */
        public String getMaritalId()
        {
            return maritalId;
        }

        /**
         * @param string
         */
        public void setMaritalId(String string)
        {
            maritalId = string;
        }

        /**
         * @return Returns the relatedFamMemId.
         */
        public String getRelatedFamMemId()
        {
            return relatedFamMemId;
        }

        /**
         * @param relatedFamMemId
         *            The relatedFamMemId to set.
         */
        public void setRelatedFamMemId(String relatedFamMemId)
        {
            this.relatedFamMemId = relatedFamMemId;
        }

        /**
         * @return Returns the relatedFamMemName.
         */
        public IName getRelatedFamMemName()
        {
            return relatedFamMemName;
        }

        /**
         * @param relatedFamMemName
         *            The relatedFamMemName to set.
         */
        public void setRelatedFamMemName(IName relatedFamMemName)
        {
            this.relatedFamMemName = relatedFamMemName;
        }
        
        
    }
	
}// End Class
