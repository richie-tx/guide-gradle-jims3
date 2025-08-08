/*
 * Project: JIMS2
 * Class:   messaging.juvenilecase.reply.MAYSIDetailsResponseEvent
 * Version: 1.0.0
 *
 * Date:    2005-05-10
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.mentalhealth.reply;

import java.util.Date;
import messaging.contact.domintf.IName;
import naming.PDJuvenileCaseConstants;

/**
 *  
 * @author  athorat
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * @version  1.0.0
 */
public class MAYSIDetailsResponseEvent extends mojo.km.messaging.ResponseEvent
{
	
//	 ------------------------------------------------------------------------
	// --- global fields                                                     ---
	// ------------------------------------------------------------------------

	private String assessFullId;  // the primary key to the view that holds all the data for the records.
	
	// ------------------------------------------------------------------------
	// --- fields    for the assessment                                                        ---
	// ------------------------------------------------------------------------

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
	private String facilityName;
	private String juvenileNum;
	private String otherReasonNotDone;
	private String reasonNotDone;
	private String reasonNotDoneId;
	private String raceId;
	private String sexId;
	private String race = null;
	private String sex = null;
	private String testAge;
	private String hispanic; //U.S 88526
	private String ethnicity;
	
	//	 ------------------------------------------------------------------------
	// --- fields    for the sub assessment                                                        ---
	// ------------------------------------------------------------------------

	private String subAssessId;
	private String reviewComments;
	private Date assessmentReviewdate;
	private String assessmentReviewtime;
	private IName subAssessOfficerName;
	private String subAssessOfficerId; 
	private String providerType;
	private String providerTypeId;
	private boolean subReferral;
	private boolean assessComplete;
	private boolean subAssessComplete;
	private boolean assessmentFound;
	
//	 ------------------------------------------------------------------------
	// --- fields    for the maysi detail                                                   ---
	// ------------------------------------------------------------------------
	
	private String maysiDetailId;
	private Date screenDate;
	private int alcoholDrug;
	private int angryIrritable;
	private int depressionAnxiety;
	private int somaticComplaint;
	private int suicideIdetaion;
	private int thoughtDisturbance;
	private int traumaticExpression;
	
	private String detailRaceId;
	private String detailSexId;
	private Date scheduledOffIntDate;
	private String priorPreviousMaysi;
	
	// production support
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJims2User;
	private String updateJims2User;
	


	/**
	 * 
	 */
	public MAYSIDetailsResponseEvent()
	{
		super();
		this.setTopic(PDJuvenileCaseConstants.MAYSI_DETAILS_RESULT_TOPIC);

	}

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	

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
	 * @return Returns the alcoholDrug.
	 */
	public int getAlcoholDrug() {
		return alcoholDrug;
	}
	/**
	 * @param alcoholDrug The alcoholDrug to set.
	 */
	public void setAlcoholDrug(int alcoholDrug) {
		this.alcoholDrug = alcoholDrug;
	}
	/**
	 * @return Returns the angryIrritable.
	 */
	public int getAngryIrritable() {
		return angryIrritable;
	}
	/**
	 * @param angryIrritable The angryIrritable to set.
	 */
	public void setAngryIrritable(int angryIrritable) {
		this.angryIrritable = angryIrritable;
	}
	/**
	 * @return Returns the assessComplete.
	 */
	public boolean isAssessComplete() {
		return assessComplete;
	}
	/**
	 * @param assessComplete The assessComplete to set.
	 */
	public void setAssessComplete(boolean assessComplete) {
		this.assessComplete = assessComplete;
	}
/**
 * @return Returns the assessFullId.
 */
public String getAssessFullId() {
	return assessFullId;
}
/**
 * @param assessFullId The assessFullId to set.
 */
public void setAssessFullId(String assessFullId) {
	this.assessFullId = assessFullId;
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
	 * @return Returns the assessmentReviewdate.
	 */
	public Date getAssessmentReviewdate() {
		return assessmentReviewdate;
	}
	/**
	 * @param assessmentReviewdate The assessmentReviewdate to set.
	 */
	public void setAssessmentReviewdate(Date assessmentReviewdate) {
		this.assessmentReviewdate = assessmentReviewdate;
	}
	/**
	 * @return Returns the assessmentReviewtime.
	 */
	public String getAssessmentReviewtime() {
		return assessmentReviewtime;
	}
	/**
	 * @param assessmentReviewtime The assessmentReviewtime to set.
	 */
	public void setAssessmentReviewtime(String assessmentReviewtime) {
		this.assessmentReviewtime = assessmentReviewtime;
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
	 * @return Returns the depressionAnxiety.
	 */
	public int getDepressionAnxiety() {
		return depressionAnxiety;
	}
	/**
	 * @param depressionAnxiety The depressionAnxiety to set.
	 */
	public void setDepressionAnxiety(int depressionAnxiety) {
		this.depressionAnxiety = depressionAnxiety;
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
 * @return Returns the maysiDetailId.
 */
public String getMaysiDetailId() {
	return maysiDetailId;
}
/**
 * @param maysiDetailId The maysiDetailId to set.
 */
public void setMaysiDetailId(String maysiDetailId) {
	this.maysiDetailId = maysiDetailId;
}
	/**
	 * @return Returns the providerType.
	 */
	public String getProviderType() {
		return providerType;
	}
	/**
	 * @param providerType The providerType to set.
	 */
	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}
	/**
	 * @return Returns the providerTypeId.
	 */
	public String getProviderTypeId() {
		return providerTypeId;
	}
	/**
	 * @param providerTypeId The providerTypeId to set.
	 */
	public void setProviderTypeId(String providerTypeId) {
		this.providerTypeId = providerTypeId;
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
	 * @return Returns the reviewComments.
	 */
	public String getReviewComments() {
		return reviewComments;
	}
	/**
	 * @param reviewComments The reviewComments to set.
	 */
	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}
	/**
	 * @return Returns the screenDate.
	 */
	public Date getScreenDate() {
		return screenDate;
	}
	/**
	 * @param screenDate The screenDate to set.
	 */
	public void setScreenDate(Date screenDate) {
		this.screenDate = screenDate;
	}
	/**
	 * @return Returns the somaticComplaint.
	 */
	public int getSomaticComplaint() {
		return somaticComplaint;
	}
	/**
	 * @param somaticComplaint The somaticComplaint to set.
	 */
	public void setSomaticComplaint(int somaticComplaint) {
		this.somaticComplaint = somaticComplaint;
	}
	/**
	 * @return Returns the subAssessId.
	 */
	public String getSubAssessId() {
		return subAssessId;
	}
	/**
	 * @param subAssessId The subAssessId to set.
	 */
	public void setSubAssessId(String subAssessId) {
		this.subAssessId = subAssessId;
	}
	/**
	 * @return Returns the subAssessOfficerId.
	 */
	public String getSubAssessOfficerId() {
		return subAssessOfficerId;
	}
	/**
	 * @param subAssessOfficerId The subAssessOfficerId to set.
	 */
	public void setSubAssessOfficerId(String subAssessOfficerId) {
		this.subAssessOfficerId = subAssessOfficerId;
	}
	/**
	 * @return Returns the subAssessOfficerName.
	 */
	public IName getSubAssessOfficerName() {
		return subAssessOfficerName;
	}
	/**
	 * @param subAssessOfficerName The subAssessOfficerName to set.
	 */
	public void setSubAssessOfficerName(IName subAssessOfficerName) {
		this.subAssessOfficerName = subAssessOfficerName;
	}
	/**
	 * @return Returns the subReferral.
	 */
	public boolean isSubReferral() {
		return subReferral;
	}
	/**
	 * @param subReferral The subReferral to set.
	 */
	public void setSubReferral(boolean subReferral) {
		this.subReferral = subReferral;
	}
	/**
	 * @return Returns the suicideIdetaion.
	 */
	public int getSuicideIdetaion() {
		return suicideIdetaion;
	}
	/**
	 * @param suicideIdetaion The suicideIdetaion to set.
	 */
	public void setSuicideIdetaion(int suicideIdetaion) {
		this.suicideIdetaion = suicideIdetaion;
	}
	/**
	 * @return Returns the thoughtDisturbance.
	 */
	public int getThoughtDisturbance() {
		return thoughtDisturbance;
	}
	/**
	 * @param thoughtDisturbance The thoughtDisturbance to set.
	 */
	public void setThoughtDisturbance(int thoughtDisturbance) {
		this.thoughtDisturbance = thoughtDisturbance;
	}
	/**
	 * @return Returns the traumaticExpression.
	 */
	public int getTraumaticExpression() {
		return traumaticExpression;
	}
	/**
	 * @param traumaticExpression The traumaticExpression to set.
	 */
	public void setTraumaticExpression(int traumaticExpression) {
		this.traumaticExpression = traumaticExpression;
	}
	
	public boolean haveMAYSIDetails(){
		if(this.maysiDetailId==null || this.maysiDetailId.equals("") || this.maysiDetailId.equals("0"))
			return false;
		else
			return true;
	}
	public boolean haveMAYSISubAssessDetails(){
		if(this.subAssessId==null || this.subAssessId.equals("") || this.subAssessId.equals("0"))
			return false;
		else
			return true;
	}
	/**
	 * @return Returns the testAge.
	 */
	public String getTestAge() {
		return testAge;
	}
	/**
	 * @param testAge The testAge to set.
	 */
	public void setTestAge(String testAge) {
		this.testAge = testAge;
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
	 * @return Returns the detailRaceId.
	 */
	public String getDetailRaceId() {
		return detailRaceId;
	}
	/**
	 * @param detailRaceId The detailRaceId to set.
	 */
	public void setDetailRaceId(String detailRaceId) {
		this.detailRaceId = detailRaceId;
	}
	/**
	 * @return Returns the detailSexId.
	 */
	public String getDetailSexId() {
		return detailSexId;
	}
	/**
	 * @param detailSexId The detailSexId to set.
	 */
	public void setDetailSexId(String detailSexId) {
		this.detailSexId = detailSexId;
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
	 * @return hispanic
	 */
	public String getHispanic()
	{
		return hispanic;
	}

	/**
	 * @param hispanic
	 */
	public void setHispanic(String hispanic)
	{
		this.hispanic = hispanic;
	}

	/**
	 * @return ethnicity
	 */
	public String getEthnicity()
	{
		return ethnicity;
	}

	/**
	 * @param ethnicity
	 */
	public void setEthnicity(String ethnicity)
	{
		this.ethnicity = ethnicity;
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

	/**
	 * 
	 * @return
	 */
	public boolean isAssessmentFound() {
		return assessmentFound;
	}

	/**
	 * 
	 * @param assessmentFound
	 */
	public void setAssessmentFound(boolean assessmentFound) {
		this.assessmentFound = assessmentFound;
	}

	/**
	 * 
	 * @return
	 */
	public String getPriorPreviousMaysi() {
		return priorPreviousMaysi;
	}

	/**
	 * 
	 * @param priorPreviousMaysi
	 */
	public void setPriorPreviousMaysi(String priorPreviousMaysi) {
		this.priorPreviousMaysi = priorPreviousMaysi;
	}

	/**
	 * 
	 * @return
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * 
	 * @param facilityName
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSubAssessComplete() {
		return subAssessComplete;
	}

	/**
	 * 
	 * @param subAssessComplete
	 */
	public void setSubAssessComplete(boolean subAssessComplete) {
		this.subAssessComplete = subAssessComplete;
	}

	/**
	 * 
	 * @return
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * 
	 * @param createUser
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 
	 * @return
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * 
	 * @param updateUser
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * 
	 * @return
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 
	 * @param updateDate
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getCreateJims2User() {
		return createJims2User;
	}

	/**
	 * 
	 * @param createJims2User
	 */
	public void setCreateJims2User(String createJims2User) {
		this.createJims2User = createJims2User;
	}

	/**
	 * 
	 * @return
	 */
	public String getUpdateJims2User() {
		return updateJims2User;
	}

	/**
	 * 
	 * @param updateJims2User
	 */
	public void setUpdateJims2User(String updateJims2User) {
		this.updateJims2User = updateJims2User;
	}

	public String getOtherReasonNotDone()
	{
	    return otherReasonNotDone;
	}

	public void setOtherReasonNotDone(String otherReasonNotDone)
	{
	    this.otherReasonNotDone = otherReasonNotDone;
	}

} // end MAYSIDetailsResponseEvent
