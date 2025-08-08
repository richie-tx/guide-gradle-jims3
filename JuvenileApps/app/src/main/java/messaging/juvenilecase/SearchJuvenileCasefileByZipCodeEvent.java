//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SearchJuvenileCasefilesEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class SearchJuvenileCasefileByZipCodeEvent extends RequestEvent {
	public String searchType;
	/*
	 * public String firstName; public String middleName; public String
	 * lastName;
	 */
	public String officerFirstName;
	public String officerMiddleName;
	public String officerLastName;
	public String juvenileNum;
	public String supervisionNum;
	public String supervisionType;
	public String caseStatus;
	public String location;
	public String supervisionTypeId;
	public String caseStatusId;
	public String expectedEndDateFrom;
	public String expectedEndDateTo;
	public String zipCode; //#32659 changes
	public String countOfJuv; //#32659 hot fix
	public String casefileControllingReferralId; //#36938
	private String dispDateFrom; // Task 50044 changes
	private String dispDateTo; // Task 50044 changes

	/**
	 * @roseuid 4278C831024E
	 */
	public SearchJuvenileCasefileByZipCodeEvent() {
	}
	
		/**
	 * @return the countOfJuv
	 */
	public String getCountOfJuv() {
		return countOfJuv;
	}

	/**
	 * @param countOfJuv the countOfJuv to set
	 */
	public void setCountOfJuv(String countOfJuv) {
		this.countOfJuv = countOfJuv;
	}

	/**
	 * @param type
	 * @roseuid 4278C7B900ED
	 */
	public void setSearchType(String aSearchType) {
		this.searchType = aSearchType;
	}

	/**
	 * @return String
	 * @roseuid 4278C7B900F8
	 */
	public String getSearchType() {
		return this.searchType;
	}

	/**
	 * @param aOfficerfirstName
	 * @roseuid 4278C7B9010B
	 */
	public void setOfficerFirstName(String aOfficerfirstName) {
		this.officerFirstName = aOfficerfirstName;
	}

	/**
	 * @return String
	 * @roseuid 4278C7B90115
	 */
	public String getOfficerFirstName() {
		return (this.officerFirstName == null) ? "" : this.officerFirstName;
	}

	/**
	 * @param aOfficerMiddleName
	 * @roseuid 4278C7B9011F
	 */
	public void setOfficerMiddleName(String aOfficerMiddleName) {
		this.officerMiddleName = aOfficerMiddleName;
	}

	/**
	 * @return String
	 * @roseuid 4278C7B9012A
	 */
	public String getOfficerMiddleName() {
		return (this.officerMiddleName == null) ? "" : this.officerMiddleName;
	}

	/**
	 * @param aOfficerLastName
	 * @roseuid 4278C7B9013D
	 */
	public void setOfficerLastName(String aOfficerLastName) {
		this.officerLastName = aOfficerLastName;
	}

	/**
	 * @return String
	 * @roseuid 4278C7B90147
	 */
	public String getOfficerLastName() {
		return this.officerLastName;
	}

	/**
	 * @param juvenileNum
	 * @roseuid 4278C7B9015B
	 */
	public void setJuvenileNum(String aJuvenileNum) {
		this.juvenileNum = aJuvenileNum;
	}

	/**
	 * @roseuid 4278C7B9015D
	 */
	public String getJuvenileNum() {
		return this.juvenileNum;
	}

	/**
	 * @param supervisionNum
	 * @roseuid 4278C7B90165
	 */
	public void setSupervisionNum(String aSupervisionNum) {
		this.supervisionNum = aSupervisionNum;
	}

	/**
	 * @return String
	 * @roseuid 4278C7B90170
	 */
	public String getSupervisionNum() {
		return this.supervisionNum;
	}

	/**
	 * @param supervisionType
	 * @roseuid 4278C7B90179
	 */
	public void setSupervisionType(String aSupervisionType) {
		this.supervisionType = aSupervisionType;
	}

	/**
	 * @roseuid 4278C7B90183
	 */
	public String getSupervisionType() {
		return this.supervisionType;
	}

	/**
	 * @param status
	 * @roseuid 4278C7B9018D
	 */
	public void setCaseStatus(String aCaseStatus) {
		this.caseStatus = aCaseStatus;
	}

	/**
	 * @roseuid 4278C7B9018F
	 */
	public String getCaseStatus() {
		return this.caseStatus;
	}

	/**
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return String
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * @roseuid 4278C831024E
	 */

	public void setSupervisionTypeId(String aSupervisionType) {
		this.supervisionTypeId = aSupervisionType;
	}

	/**
	 * @return String
	 * @roseuid 4278C7B90115
	 */
	public String getSupervisionTypeId() {
		return this.supervisionTypeId;
	}

	/**
	 * @param caseStatus
	 * @roseuid 4278C7B9011F
	 */
	public void setCaseStatusId(String aCaseStatus) {
		this.caseStatusId = aCaseStatus;
	}

	/**
	 * @return String
	 * @roseuid 4278C7B9012A
	 */
	public String getCaseStatusId() {
		return this.caseStatusId;
	}

	/**
	 * @return the expectedEndDateFrom
	 */
	public String getExpectedEndDateFrom() {
		return expectedEndDateFrom;
	}

	/**
	 * @param expectedEndDateFrom
	 *            the expectedEndDateFrom to set
	 */
	public void setExpectedEndDateFrom(String expectedEndDateFrom) {
		this.expectedEndDateFrom = expectedEndDateFrom;
	}

	/**
	 * @return the expectedEndDateTo
	 */
	public String getExpectedEndDateTo() {
		return expectedEndDateTo;
	}

	/**
	 * @param expectedEndDateTo
	 *            the expectedEndDateTo to set
	 */
	public void setExpectedEndDateTo(String expectedEndDateTo) {
		this.expectedEndDateTo = expectedEndDateTo;
	}

	public String getDispDateFrom() {
	    return dispDateFrom;
	}

	public void setDispDateFrom(String dispDateFrom) {
	    this.dispDateFrom = dispDateFrom;
	}

	public String getDispDateTo() {
	    return dispDateTo;
	}

	public void setDispDateTo(String dispDateTo) {
	    this.dispDateTo = dispDateTo;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the casefileControllingReferralId
	 */
	public String getCasefileControllingReferralId() {
		return casefileControllingReferralId;
	}

	/**
	 * @param casefileControllingReferralId the casefileControllingReferralId to set
	 */
	public void setCasefileControllingReferralId(
			String casefileControllingReferralId) {
		this.casefileControllingReferralId = casefileControllingReferralId;
	}
}