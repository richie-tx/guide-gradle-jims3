/*
 * Created on Jun 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.mentalhealth.reply;

import mojo.km.messaging.ResponseEvent;
import java.util.Date;
import messaging.contact.domintf.IName;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MAYSIAssessResponseEvent extends ResponseEvent {
	private String assessmentId;
	private String referralNumber;
	private Date assessmentDate;
	private String assessmentTime;
	private IName assessOfficerName;
	private String assessOfficerId; 
	private String assessmentOption;
	private String assessmentOptionId;
	private boolean hasPreviousMAYSI;
	private boolean administered;
	private String locationUnitId;
	private String locationUnit;
	private String lengthOfStay;
	private String lengthOfStayId;
	private String facilityType;
	private String facilityTypeId;
	private String juvenileNum;
	private String otherReasonNotDone;
	private String reasonNotDone;
	private String reasonNotDoneId;
	private String raceId;
	private String race = null;
	private String sexId;
	private String sex = null;
	private String requestingOfficerId;
	private int testAge;
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
	 * @return Returns the assessmentDate.
	 */
	public Date getAssessmentDate() {
		return assessmentDate;
	}
	/**
	 * @param assessmentDate The assessmentDate to set.
	 */
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	/**
	 * @return Returns the assessmentId.
	 */
	public String getAssessmentId() {
		return assessmentId;
	}
	/**
	 * @param assessmentId The assessmentId to set.
	 */
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
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
	 * @return Returns the assessmentOptionId.
	 */
	public String getAssessmentOptionId() {
		return assessmentOptionId;
	}
	/**
	 * @param assessmentOptionId The assessmentOptionId to set.
	 */
	public void setAssessmentOptionId(String assessmentOptionId) {
		this.assessmentOptionId = assessmentOptionId;
	}
	/**
	 * @return Returns the assessmentTime.
	 */
	public String getAssessmentTime() {
		return assessmentTime;
	}
	/**
	 * @param assessmentTime The assessmentTime to set.
	 */
	public void setAssessmentTime(String assessmentTime) {
		this.assessmentTime = assessmentTime;
	}
	/**
	 * @return Returns the assessOfficerId.
	 */
	public String getAssessOfficerId() {
		return assessOfficerId;
	}
	/**
	 * @param assessOfficerId The assessOfficerId to set.
	 */
	public void setAssessOfficerId(String assessOfficerId) {
		this.assessOfficerId = assessOfficerId;
	}
	/**
	 * @return Returns the assessOfficerName.
	 */
	public IName getAssessOfficerName() {
		return assessOfficerName;
	}
	/**
	 * @param assessOfficerName The assessOfficerName to set.
	 */
	public void setAssessOfficerName(IName assessOfficerName) {
		this.assessOfficerName = assessOfficerName;
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
	 * @return Returns the facilityTypeId.
	 */
	public String getFacilityTypeId() {
		return facilityTypeId;
	}
	/**
	 * @param facilityTypeId The facilityTypeId to set.
	 */
	public void setFacilityTypeId(String facilityTypeId) {
		this.facilityTypeId = facilityTypeId;
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
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
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
	 * @return Returns the lengthOfStayId.
	 */
	public String getLengthOfStayId() {
		return lengthOfStayId;
	}
	/**
	 * @param lengthOfStayId The lengthOfStayId to set.
	 */
	public void setLengthOfStayId(String lengthOfStayId) {
		this.lengthOfStayId = lengthOfStayId;
	}
	/**
	 * @return Returns the location.
	 */
	public String getLocationUnit() {
		return locationUnit;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocationUnit(String location) {
		this.locationUnit = location;
	}
	/**
	 * @return Returns the locationId.
	 */
	public String getLocationUnitId() {
		return locationUnitId;
	}
	/**
	 * @param locationId The locationId to set.
	 */
	public void setLocationUnitId(String locationId) {
		this.locationUnitId = locationId;
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
	 * @return Returns the reasonNotDoneId.
	 */
	public String getReasonNotDoneId() {
		return reasonNotDoneId;
	}
	/**
	 * @param reasonNotDoneId The reasonNotDoneId to set.
	 */
	public void setReasonNotDoneId(String reasonNotDoneId) {
		this.reasonNotDoneId = reasonNotDoneId;
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
	public String getOtherReasonNotDone()
	{
	    return otherReasonNotDone;
	}
	public void setOtherReasonNotDone(String otherReasonNotDone)
	{
	    this.otherReasonNotDone = otherReasonNotDone;
	}
}
