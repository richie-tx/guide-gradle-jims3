/*
 * Created on Dec 20, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile;

import java.util.Date;
import java.util.List;

import naming.PDJuvenileCaseConstants;

import messaging.contact.domintf.IName;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import ui.juvenilecase.casefile.form.GuardianBean;

/**
 * @author jjose To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MaysiDataReportBean
{
    private String assessFullId;  // the primary key to the view that holds all the data for the records.
	
    	private String juvenileName;
	private int juvenileAge;
	private String juvenileStatus;
	// ------------------------------------------------------------------------
	// --- fields    for the assessment                                                        ---
	// ------------------------------------------------------------------------

	private String assessmentId;
	private String referralNumber;
	private String assessmentDate;
	private String assessmentTime;
	private IName assessOfficerName;
	private String assessOfficerId; 
	private String assessmentOption;
	private String assessmentOptionId;
	private String hasPreviousMAYSI;
	private String administered;
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
	private String assessmentReviewdate;
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
	private String screenDate;
	private String alcoholDrug;
	private String angryIrritable;
	private String depressionAnxiety;
	private String somaticComplaint;
	private String suicideIdetaion;
	private String thoughtDisturbance;
	private String traumaticExpression;
	
	private String detailRaceId;
	private String detailSexId;
	private Date scheduledOffIntDate;
	private String priorPreviousMaysi;

	private String maysiId;

	private String assessmentOfficerName;

	private String administer;

	private String scheduledOffIntDateStr;

	private boolean hasDetails;

	private boolean alcoholDrugCaution;

	private boolean hasSubAssessment;

	private String suicideIdeation;

	//private Date assessmentReviewDate;

	//private String assessmentReviewTime;

	private String subsAssessmentReferral;

	private String providerReferredType;

	private String wasSubsAssessmentCompleted;

	private String subsAssessmentComments;

	private boolean alcoholDrugWarning;

	private boolean angryIrritableCaution;

	private boolean angryIrritableWarning;

	private boolean depressionAnxietyCaution;

	private boolean depressionAnxietyWarning;

	private boolean somaticComplaintCaution;

	private boolean somaticComplaintWarning;

	private boolean thoughtDisturbanceCaution;

	private boolean thoughtDisturbanceWarning;

	private boolean suicideIdeationCaution;

	private boolean suicideIdeationWarning;
	

	/**
	 * @return Returns the administered.
	 */
	public String getAdministered() {
		return administered;
	}
	/**
	 * @param administered The administered to set.
	 */
	public void setAdministered(String administered) {
		this.administered = administered;
	}
	/**
	 * @return Returns the alcoholDrug.
	 */
	public String getAlcoholDrug() {
		return alcoholDrug;
	}
	/**
	 * @param alcoholDrug The alcoholDrug to set.
	 */
	public void setAlcoholDrug(String alcoholDrug) {
		this.alcoholDrug = alcoholDrug;
	}
	/**
	 * @return Returns the angryIrritable.
	 */
	public String getAngryIrritable() {
		return angryIrritable;
	}
	/**
	 * @param angryIrritable The angryIrritable to set.
	 */
	public void setAngryIrritable(String angryIrritable) {
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
	public String getAssessmentDate() {
		return assessmentDate;
	}
	/**
	 * @param assessmentDate The assessmentDate to set.
	 */
	public void setAssessmentDate(String assessmentDate) {
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
	public String getAssessmentReviewDate() {
		return assessmentReviewdate;
	}
	/**
	 * @param assessmentReviewdate The assessmentReviewdate to set.
	 */
	public void setAssessmentReviewDate(String assessmentReviewdate) {
	    this.assessmentReviewdate = assessmentReviewdate;
	}
	/**
	 * @return Returns the assessmentReviewtime.
	 */
	public String getAssessmentReviewTime() {
		return assessmentReviewtime;
	}
	/**
	 * @param assessmentReviewtime The assessmentReviewtime to set.
	 */
	public void setAssessmentReviewTime(String assessmentReviewtime) {
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
	public String getDepressionAnxiety() {
		return depressionAnxiety;
	}
	/**
	 * @param depressionAnxiety The depressionAnxiety to set.
	 */
	public void setDepressionAnxiety(String depressionAnxiety) {
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
	public String getHasPreviousMAYSI() {
		return hasPreviousMAYSI;
	}
	/**
	 * @param hasPreviousMAYSI The hasPreviousMAYSI to set.
	 */
	public void setHasPreviousMAYSI(String hasPreviousMAYSI) {
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
	public String getScreenDate() {
		return screenDate;
	}
	/**
	 * @param screenDate The screenDate to set.
	 */
	public void setScreenDate(String screenDate) {
		this.screenDate = screenDate;
	}
	/**
	 * @return Returns the somaticComplaint.
	 */
	public String getSomaticComplaint() {
		return somaticComplaint;
	}
	/**
	 * @param somaticComplaint The somaticComplaint to set.
	 */
	public void setSomaticComplaint(String somaticComplaint) {
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
	public String getSuicideIdetaion() {
		return suicideIdetaion;
	}
	/**
	 * @param suicideIdetaion The suicideIdetaion to set.
	 */
	public void setSuicideIdetaion(String suicideIdetaion) {
		this.suicideIdetaion = suicideIdetaion;
	}
	/**
	 * @return Returns the thoughtDisturbance.
	 */
	public String getThoughtDisturbance() {
		return thoughtDisturbance;
	}
	/**
	 * @param thoughtDisturbance The thoughtDisturbance to set.
	 */
	public void setThoughtDisturbance(String thoughtDisturbance) {
		this.thoughtDisturbance = thoughtDisturbance;
	}
	/**
	 * @return Returns the traumaticExpression.
	 */
	public String getTraumaticExpression() {
		return traumaticExpression;
	}
	/**
	 * @param traumaticExpression The traumaticExpression to set.
	 */
	public void setTraumaticExpression(String traumaticExpression) {
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

	public String getOtherReasonNotDone()
	{
	    return otherReasonNotDone;
	}

	public void setOtherReasonNotDone(String otherReasonNotDone)
	{
	    this.otherReasonNotDone = otherReasonNotDone;
	}
	
	public String getMaysiId()
	{
		return maysiId;
	}

	/**
	 * @param maysiID
	 *          The maysiID to set.
	 */
	public void setMaysiId(String maysiID)
	{
		this.maysiId = maysiID;
	}
	
	public String getAssessmentOfficerName()
	{
		return this.assessmentOfficerName;
	}
	
	public void setAssessmentOfficerName(String string)
	{
		this.assessmentOfficerName = string;
	}
	
	public String getAdminister()
	{
		return this.administer;
	}

	/**
	 * @param b
	 */
	public void setAdminister(String b)
	{
		this.administer = b;
	}
	
	public String getScheduledOffIntDateStr() {
		return this.scheduledOffIntDateStr;
	}
	
	public void setScheduledOffIntDateStr(String scheduledOffIntDateStr) {
		this.scheduledOffIntDateStr = scheduledOffIntDateStr;
	}
	
	public boolean getHasDetails()
	{
		return hasDetails;
	}

	public void setHasDetails(boolean hasDetails)
	{
		this.hasDetails = hasDetails;
	}
	
	public boolean getAlcoholDrugCaution()
	{
		return this.alcoholDrugCaution;
	}

	public void setAlcoholDrugCaution(boolean alcoholDrugCaution)
	{
		this.alcoholDrugCaution = alcoholDrugCaution;
	}
	
	public boolean getHasSubAssessment()
	{
		return hasSubAssessment;
	}

	public void setHasSubAssessment(boolean hasSubAssessment)
	{
		this.hasSubAssessment = hasSubAssessment;
	}
	
	public String getSuicideIdeation()
	{
		return this.suicideIdeation;
	}

	public void setSuicideIdeation(String sIdeation)
	{
		this.suicideIdeation = sIdeation;
	}
	
	//public String getAssessmentReviewDate()
	//{
	//	return this.assessmentReviewDate;
	//}
	
	//public void setAssessmentReviewDate(String date)
	//{
	//	this.assessmentReviewDate = date;
	//}
	
	//public String getAssessmentReviewTime()
	//{
	//	return assessmentReviewTime;
	//}

	//public void setAssessmentReviewTime(String assessmentReviewTime)
	//{
	//	this.assessmentReviewTime = assessmentReviewTime;
	//}
	
	public String getSubsAssessmentReferral()
	{
		return this.subsAssessmentReferral;
	}

	public void setSubsAssessmentReferral(String subAssementRef)
	{
		this.subsAssessmentReferral = subAssementRef;
	}
	
	public String getProviderReferredType()
	{
		return providerReferredType;
	}
	
	public void setProviderReferredType(String providerReferredType)
	{
		this.providerReferredType = providerReferredType;
	}
	
	public String getWasSubsAssessmentCompleted()
	{
	    return this.wasSubsAssessmentCompleted;
	}
	public void setWasSubsAssessmentCompleted(String completed)
	{
	    this.wasSubsAssessmentCompleted = completed;
	}
	
	public String getSubsAssessmentComments()
	{
		return this.subsAssessmentComments;
	}
	public void setSubsAssessmentComments(String comments)
	{
		this.subsAssessmentComments = comments;
	}
	
	public boolean getAlcoholDrugWarning()
	{
		return alcoholDrugWarning;
	}

	public void setAlcoholDrugWarning(boolean alcoholDrugWarning)
	{
		this.alcoholDrugWarning = alcoholDrugWarning;
	}

	public boolean isAngryIrritableCaution()
	{
		return angryIrritableCaution;
	}

	public void setAngryIrritableCaution(boolean angryIrritableCaution)
	{
		this.angryIrritableCaution = angryIrritableCaution;
	}

	public boolean isAngryIrritableWarning()
	{
		return angryIrritableWarning;
	}

	public void setAngryIrritableWarning(boolean angryIrritableWarning)
	{
		this.angryIrritableWarning = angryIrritableWarning;
	}
	
	public boolean getDepressionAnxietyCaution()
	{
		return this.depressionAnxietyCaution;
	}

	public void setDepressionAnxietyCaution(boolean depressionAnxietyCaution)
	{
		this.depressionAnxietyCaution = depressionAnxietyCaution;
	}

	public boolean getDepressionAnxietyWarning()
	{
		return this.depressionAnxietyWarning;
	}

	public void setDepressionAnxietyWarning(boolean depressionAnxietyWarning)
	{
		this.depressionAnxietyWarning = depressionAnxietyWarning;
	}

	public boolean getSomaticComplaintCaution()
	{
		return this.somaticComplaintCaution;
	}

	public void setSomaticComplaintCaution(boolean somaticComplaintCaution)
	{
		this.somaticComplaintCaution = somaticComplaintCaution;
	}

	public boolean getSomaticComplaintWarning()
	{
		return this.somaticComplaintWarning;
	}

	public void setSomaticComplaintWarning(boolean somaticComplaintWarning)
	{
		this.somaticComplaintWarning = somaticComplaintWarning;
	}
	
	public boolean getThoughtDisturbanceCaution()
	{
		return this.thoughtDisturbanceCaution;
	}

	public void setThoughtDisturbanceCaution(boolean thoughtDisturbanceCaution)
	{
		this.thoughtDisturbanceCaution = thoughtDisturbanceCaution;
	}

	public boolean getThoughtDisturbanceWarning()
	{
		return this.thoughtDisturbanceWarning;
	}

	public void setThoughtDisturbanceWarning(boolean thoughtDisturbanceWarning)
	{
		this.thoughtDisturbanceWarning = thoughtDisturbanceWarning;
	}
	
	public boolean getSuicideIdeationCaution() {
		return this.suicideIdeationCaution;
	}

	public void setSuicideIdeationCaution(boolean suicideIdeationCaution) {
		this.suicideIdeationCaution = suicideIdeationCaution;
	}

	public boolean getSuicideIdeationWarning() {
		return this.suicideIdeationWarning;
	}

	public void setSuicideIdeationWarning(boolean suicideIdeationWarning) {
		this.suicideIdeationWarning = suicideIdeationWarning;
	}
	
	public String getJuvenileName()
	{
	    return this.juvenileName;
	}

	public void setJuvenileName(String juvName)
	{
	    this.juvenileName = juvName;
	}
	
	public String getJuvenileStatus()
	{
	    return this.juvenileStatus;
	}

	public void setJuvenileStatus(String juvStatus)
	{
	    this.juvenileStatus = juvStatus;
	}
	
	public int getJuvenileAge()
	{
	    return this.juvenileAge;
	}

	public void setJuvenileAge(int juvAge)
	{
	    this.juvenileAge = juvAge;
	}

}