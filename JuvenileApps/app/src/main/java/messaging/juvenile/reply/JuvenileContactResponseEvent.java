/*
 * Created on Jun 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileContactResponseEvent extends ResponseEvent implements Comparable
{
	private String contactNum;
	private String addressTypeId;
	private String addressType;
	private String agencyNameId;
	private String agencyName;
	private String apartmentNum;
	private String cellPhone;
	private String countyId;
	private String county;
	private String city;
	private String currentAgencyInvolvementId;
	private String currentAgencyInvolvement;
	private String eMail;
	private String phonePriorityInd="";
	private Date entryDate;
	private String fax;
	private String firstName;
	private String juvenileNum;
	private String lastName;
	private String middleName;
	private String pager;
	private String previousAgencyInvolvementId;
	private String previousAgencyInvolvement;
	private String relationshipId;
	private String relationship;
	private String stateId;
	private String state;
	private String streetName;
	private String streetNum;
	private String streetNumSuffixId;
	private String streetNumSuffix;
	private String streetTypeId;
	private String streetType;
	private String titleId;
	private String title;
	private String workPhone;
	private String workPhoneExtn;
	private String zipCode;
	private String additionalZipCode;
	private String validated;
	private boolean detentionVisit;
	private boolean ageOver21;
	private String driverLicenseNum = "";
	private String driverLicenseState = "";
	private String driverLicenseStateId = "";
	private String driverLicenseClassId = "";
	private String driverLicenseClass = "";
	private String driverLicenseExpirationDate = "";
	private String issuedByState = "";
	private String issuedByStateId = "";
	private String stateIssuedIdNum = "";
	private String passportNum = ""; 
	private String countryOfIssuance = ""; 
	private String countryOfIssuanceId = ""; 
	private String passportExpirationDate = ""; 
	private static List emptyColl = new ArrayList();
	private static List countryOfIssuanceList = emptyColl;
	private String contactMemberComments;
	/**
	 * @return
	 */
	public String getAddressType()
	{
		return addressType;
	}

	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @return
	 */
	public String getApartmentNum()
	{
		return apartmentNum;
	}

	/**
	 * @return
	 */
	public String getCellPhone()
	{
		return cellPhone;
	}

	/**
	 * @return
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @return
	 */
	public String getCounty()
	{
		return county;
	}

	/**
	 * @return
	 */
	public String getCurrentAgencyInvolvementId()
	{
		return currentAgencyInvolvementId;
	}

	/**
	 * @return
	 */
	public String getEMail()
	{
		return eMail;
	}

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @return
	 */
	public String getFax()
	{
		return fax;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return
	 */
	public String getPager()
	{
		return pager;
	}

	/**
	 * @return
	 */
	public String getPreviousAgencyInvolvementId()
	{
		return previousAgencyInvolvementId;
	}

	/**
	 * @return
	 */
	public String getRelationshipId()
	{
		return relationshipId;
	}

	/**
	 * @return
	 */
	public String getState()
	{
		return state;
	}
	/**
	 * @return
	 */
	public String getStreetNumSuffix()
	{
		return streetNumSuffix;
	}

	/**
	 * @return
	 */
	public String getStreetName()
	{
		return streetName;
	}

	/**
	 * @return
	 */
	public String getStreetNum()
	{
		return streetNum;
	}

	/**
	 * @return
	 */
	public String getStreetType()
	{
		return streetType;
	}

	/**
	 * @return
	 */
	public String getWorkPhone()
	{
		return workPhone;
	}

	/**
	 * @return
	 */
	public String getZipCode()
	{
		return zipCode;
	}

	public boolean isDetentionVisit() {
	    return detentionVisit;
	}

	public boolean isAgeOver21() {
	    return ageOver21;
	}

	public void setDetentionVisit(boolean detVisit) {
	    detentionVisit = detVisit;
	}

	public void setAgeOver21(boolean over21) {
	    ageOver21 = over21;
	}

	public String getDriverLicenseNum() {
	    return driverLicenseNum;
	}

	public String getDriverLicenseState() {
	    return driverLicenseState;
	}

	public String getDriverLicenseClass() {
	    return driverLicenseClass;
	}

	public String getDriverLicenseExpirationDate() {
	    return driverLicenseExpirationDate;
	}

	public String getIssuedByState() {
	    return issuedByState;
	}

	public String getStateIssuedIdNum() {
	    return stateIssuedIdNum;
	}

	public String getPassportNum() {
	    return passportNum;
	}

	public String getCountryOfIssuance() {
	    return countryOfIssuance;
	}

	public String getCountryOfIssuanceId() {
	    return countryOfIssuanceId;
	}

	public String getPassportExpirationDate() {
	    return passportExpirationDate;
	}

	public static List getEmptyColl() {
	    return emptyColl;
	}

	public static List getCountryOfIssuanceList() {
	    return countryOfIssuanceList;
	}

	

	public void setDriverLicenseNum(String driverLicenseNum) {
	    this.driverLicenseNum = driverLicenseNum;
	}

	public void setDriverLicenseState(String driverLicenseState) {
	    this.driverLicenseState = driverLicenseState;
	}

	public void setDriverLicenseClass(String driverLicenseClass) {
	    this.driverLicenseClass = driverLicenseClass;
	}

	public void setDriverLicenseExpirationDate(String driverLicenseExpirationDate) {
	    this.driverLicenseExpirationDate = driverLicenseExpirationDate;
	}

	public void setIssuedByState(String issuedByState) {
	    this.issuedByState = issuedByState;
	}

	public void setStateIssuedIdNum(String stateIssuedIdNum) {
	    this.stateIssuedIdNum = stateIssuedIdNum;
	}

	public void setPassportNum(String passportNum) {
	    this.passportNum = passportNum;
	}

	public void setCountryOfIssuance(String countryOfIssuance) {
	    this.countryOfIssuance = countryOfIssuance;
	}

	public void setCountryOfIssuanceId(String countryOfIssuanceId) {
	    this.countryOfIssuanceId = countryOfIssuanceId;
	}

	public void setPassportExpirationDate(String passportExpirationDate) {
	    this.passportExpirationDate = passportExpirationDate;
	}

	public static void setEmptyColl(List emptyColl) {
	    JuvenileContactResponseEvent.emptyColl = emptyColl;
	}

	public static void setCountryOfIssuanceList(List countryOfIssuanceList) {
	    JuvenileContactResponseEvent.countryOfIssuanceList = countryOfIssuanceList;
	}

	/**
	 * @param string
	 */
	public void setAddressType(String string)
	{
		addressType = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

	/**
	 * @param string
	 */
	public void setApartmentNum(String string)
	{
		apartmentNum = string;
	}

	/**
	 * @param string
	 */
	public void setCellPhone(String string)
	{
		cellPhone = string;
	}

	/**
	 * @param string
	 */
	public void setCity(String string)
	{
		city = string;
	}

	/**
	 * @param string
	 */
	public void setCounty(String string)
	{
		county = string;
	}

	/**
	 * @param b
	 */
	public void setCurrentAgencyInvolvementId(String b)
	{
		currentAgencyInvolvementId = b;
	}

	/**
	 * @param string
	 */
	public void setEMail(String string)
	{
		eMail = string;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @param string
	 */
	public void setFax(String string)
	{
		fax = string;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	/**
	 * @param string
	 */
	public void setPager(String string)
	{
		pager = string;
	}

	/**
	 * @param b
	 */
	public void setPreviousAgencyInvolvementId(String b)
	{
		previousAgencyInvolvementId = b;
	}

	/**
	 * @param string
	 */
	public void setRelationshipId(String string)
	{
		relationshipId = string;
	}

	/**
	 * @param string
	 */
	public void setState(String string)
	{
		state = string;
	}
	/**
	 * @param string
	 */
	public void setStreetNumSuffix(String string)
	{
		streetNumSuffix = string;
	}

	/**
	 * @param string
	 */
	public void setStreetName(String string)
	{
		streetName = string;
	}

	/**
	 * @param string
	 */
	public void setStreetNum(String string)
	{
		streetNum = string;
	}

	/**
	 * @param string
	 */
	public void setStreetType(String string)
	{
		streetType = string;
	}

	/**
	 * @param string
	 */
	public void setWorkPhone(String string)
	{
		workPhone = string;
	}

	/**
	 * @param string
	 */
	public void setZipCode(String string)
	{
		zipCode = string;
	}

	/**
	 * @return
	 */
	public String getAddressTypeId()
	{
		return addressTypeId;
	}

	/**
	 * @return
	 */
	public String getAgencyNameId()
	{
		return agencyNameId;
	}

	/**
	 * @return
	 */
	public String getCountyId()
	{
		return countyId;
	}

	/**
	 * @return
	 */
	public String getRelationship()
	{
		return relationship;
	}

	/**
	 * @return
	 */
	public String getStateId()
	{
		return stateId;
	}
	/**
	 * @return
	 */
	public String getStreetNumSuffixId()
	{
		return streetNumSuffixId;
	}

	/**
	 * @return
	 */
	public String getStreetTypeId()
	{
		return streetTypeId;
	}

	/**
	 * @return
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @return
	 */
	public String getTitleId()
	{
		return titleId;
	}

	/**
	 * @param string
	 */
	public void setAddressTypeId(String string)
	{
		addressTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyNameId(String string)
	{
		agencyNameId = string;
	}

	/**
	 * @param string
	 */
	public void setCountyId(String string)
	{
		countyId = string;
	}

	/**
	 * @param string
	 */
	public void setRelationship(String string)
	{
		relationship = string;
	}

	/**
	 * @param string
	 */
	public void setStateId(String string)
	{
		stateId = string;
	}
	/**
	 * @param string
	 */
	public void setStreetNumSuffixId(String string)
	{
		streetNumSuffixId = string;
	}

	/**
	 * @param string
	 */
	public void setStreetTypeId(String string)
	{
		streetTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string)
	{
		title = string;
	}

	/**
	 * @param string
	 */
	public void setTitleId(String string)
	{
		titleId = string;
	}

	/**
	 * @return
	 */
	public String getAdditionalZipCode()
	{
		return additionalZipCode;
	}

	/**
	 * @param string
	 */
	public void setAdditionalZipCode(String string)
	{
		additionalZipCode = string;
	}

	/**
	 * @return
	 */
	public String getContactNum()
	{
		return contactNum;
	}

	/**
	 * @param string
	 */
	public void setContactNum(String string)
	{
		contactNum = string;
	}

	public String getFormattedName(){
		StringBuffer full = new StringBuffer();
		if(lastName != null){
			full.append(lastName);
		}
		if(firstName != null && !firstName.equals(""))
		{
			full.append(", ");
			full.append(firstName);
			if (middleName != null  && !middleName.equals(""))
			{
				full.append(" " + middleName);
			}

		}
		return full.toString();
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * TO BE USED via a sorting routine such as Collection.sort()
	 */
	public int compareTo(Object arg0)
	{
		if(arg0==null)
			return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		if(this.entryDate==null)
			return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
			JuvenileContactResponseEvent evt=(JuvenileContactResponseEvent)arg0;
			if(evt.getEntryDate() == null)
				return 1;
		return entryDate.compareTo(evt.getEntryDate());
	}

	/**
	 * @return Returns the currentAgencyInvolvement.
	 */
	public String getCurrentAgencyInvolvement() {
		return currentAgencyInvolvement;
	}
	/**
	 * @param currentAgencyInvolvement The currentAgencyInvolvement to set.
	 */
	public void setCurrentAgencyInvolvement(String currentAgencyInvolvement) {
		this.currentAgencyInvolvement = currentAgencyInvolvement;
	}
	/**
	 * @return Returns the previousAgencyInvolvement.
	 */
	public String getPreviousAgencyInvolvement() {
		return previousAgencyInvolvement;
	}
	/**
	 * @param previousAgencyInvolvement The previousAgencyInvolvement to set.
	 */
	public void setPreviousAgencyInvolvement(String previousAgencyInvolvement) {
		this.previousAgencyInvolvement = previousAgencyInvolvement;
	}
	/**
	 * @return Returns the phonePriorityInd.
	 */
	public String getPhonePriorityInd() {
		return phonePriorityInd;
	}
	/**
	 * @param phonePriorityInd The phonePriorityInd to set.
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
	 * @param workPhoneExtn The workPhoneExtn to set.
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

	public String getDriverLicenseStateId() {
	    return driverLicenseStateId;
	}

	public void setDriverLicenseStateId(String driverLicenseStateId) {
	    this.driverLicenseStateId = driverLicenseStateId;
	}

	public String getDriverLicenseClassId() {
	    return driverLicenseClassId;
	}

	public void setDriverLicenseClassId(String driverLicenseClassId) {
	    this.driverLicenseClassId = driverLicenseClassId;
	}

	public String getIssuedByStateId() {
	    return issuedByStateId;
	}

	public void setIssuedByStateId(String issuedByStateId) {
	    this.issuedByStateId = issuedByStateId;
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
