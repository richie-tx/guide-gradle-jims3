//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SaveMAYSIMetadataEvent.java

package messaging.mentalhealth;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveMAYSIMetadataEvent extends RequestEvent
{
	private String casefileId;
	private String assessmentOption;
	private String requestingOfficerId;
	private Date requestDate;
	private String referralNumber;
	private boolean hasPreviousMAYSI;
	private boolean administered;
	private String locationId;
	private String lengthOfStay;
	private String facilityType;
	private String juvenileNumber;
	private String reasonNotDone;
	private String raceId;
	private String sexId;
	private String maysiId;
	private int testAge;
	private Date scheduledOffIntDate;
	private String othReasonNotDone;
	//private String reasonNotDoneId;
	

	

	/**
	 * @roseuid 42790E0900BB
	 */
	public SaveMAYSIMetadataEvent()
	{

	}

	/**
	 * @return Returns the administered.
	 */
	public boolean isAdministered() {
		return administered;
	}
	/**
	 * @param administered The administered to set.
	 */
	public void setAdministered(boolean administered) {
		this.administered = administered;
	}
	/**
	 * @return Returns the assessmentOption.
	 */
	public String getAssessmentOption() {
		return assessmentOption;
	}
	/**
	 * @param assessmentOption The assessmentOption to set.
	 */
	public void setAssessmentOption(String assessmentOption) {
		this.assessmentOption = assessmentOption;
	}
	/**
	 * @return Returns the facilityType.
	 */
	public String getFacilityType() {
		return facilityType;
	}
	/**
	 * @param facilityType The facilityType to set.
	 */
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}
	/**
	 * @return Returns the hasPreviousMAYSI.
	 */
	public boolean isHasPreviousMAYSI() {
		return hasPreviousMAYSI;
	}
	/**
	 * @param hasPreviousMAYSI The hasPreviousMAYSI to set.
	 */
	public void setHasPreviousMAYSI(boolean hasPreviousMAYSI) {
		this.hasPreviousMAYSI = hasPreviousMAYSI;
	}
	/**
	 * @return Returns the juvenileNumber.
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}
	/**
	 * @param juvenileNumber The juvenileNumber to set.
	 */
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}
	/**
	 * @return Returns the lengthOfStay.
	 */
	public String getLengthOfStay() {
		return lengthOfStay;
	}
	/**
	 * @param lengthOfStay The lengthOfStay to set.
	 */
	public void setLengthOfStay(String lengthOfStay) {
		this.lengthOfStay = lengthOfStay;
	}
	/**
	 * @return Returns the locationId.
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId The locationId to set.
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return Returns the reasonNotDone.
	 */
	public String getReasonNotDone() {
		return reasonNotDone;
	}
	/**
	 * @param reasonNotDone The reasonNotDone to set.
	 */
	public void setReasonNotDone(String reasonNotDone) {
		this.reasonNotDone = reasonNotDone;
	}
	/**
	 * @return Returns the referralNumber.
	 */
	public String getReferralNumber() {
		return referralNumber;
	}
	/**
	 * @param referralNumber The referralNumber to set.
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	/**
	 * @return Returns the requestDate.
	 */
	public Date getRequestDate() {
		return requestDate;
	}
	/**
	 * @param requestDate The requestDate to set.
	 */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * @return Returns the requestingOfficerId.
	 */
	public String getRequestingOfficerId() {
		return requestingOfficerId;
	}
	/**
	 * @param requestingOfficerId The requestingOfficerId to set.
	 */
	public void setRequestingOfficerId(String requestingOfficerId) {
		this.requestingOfficerId = requestingOfficerId;
	}
	/**
	 * @return Returns the raceId.
	 */
	public String getRaceId() {
		return raceId;
	}
	/**
	 * @param raceId The raceId to set.
	 */
	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}
	/**
	 * @return Returns the sexId.
	 */
	public String getSexId() {
		return sexId;
	}
	/**
	 * @param sexId The sexId to set.
	 */
	public void setSexId(String sexId) {
		this.sexId = sexId;
	}
	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	/**
	 * @return Returns the maysiId.
	 */
	public String getMaysiId() {
		return maysiId;
	}
	/**
	 * @param maysiId The maysiId to set.
	 */
	public void setMaysiId(String maysiId) {
		this.maysiId = maysiId;
	}
	/**
	 * @return Returns the testAge.
	 */
	public int getTestAge() {
		return testAge;
	}
	/**
	 * @param testAge The testAge to set.
	 */
	public void setTestAge(int testAge) {
		this.testAge = testAge;
	}
	/**
	 * @return the scheduledOffIntDate
	 */
	public Date getScheduledOffIntDate() {
		return scheduledOffIntDate;
	}
	/**
	 * @param scheduledOffIntDate the scheduledOffIntDate to set
	 */
	public void setScheduledOffIntDate(Date scheduledOffIntDate) {
		this.scheduledOffIntDate = scheduledOffIntDate;
	}

	public String getOthReasonNotDone()
	{
	    return othReasonNotDone;
	}

	public void setOthReasonNotDone(String othReasonNotDone)
	{
	    this.othReasonNotDone = othReasonNotDone;
	}
	/*public String getReasonNotDoneId()
	{
	    return reasonNotDoneId;
	}

	public void setReasonNotDoneId(String reasonNotDoneId)
	{
	    this.reasonNotDoneId = reasonNotDoneId;
	}*/
}