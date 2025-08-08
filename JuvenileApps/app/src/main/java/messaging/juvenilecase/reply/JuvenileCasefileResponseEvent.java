/*
 * Created on Apr 25, 2005
 */
package messaging.juvenilecase.reply;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ui.common.PhoneNumber;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

/**
 * A fully populated JuvenileCasefile value object
 * 
 * @author glyons
 */
@SuppressWarnings("unused")
public class JuvenileCasefileResponseEvent extends ResponseEvent implements IAddressable,Comparable{
	private String supervisionNum;
	
	private String supervisionNumWithSupervisionType;
	
	private String sequenceNum;

	private String supervisionTypeId;
	
	private String supervisionType;

	private String supervisionCategoryId;
	
	private String juvenileNum;

	private String juvenileFirstName;

	private String juvenileMiddleName;

	private String juvenileLastName;
	
	private String juvenilePreferredFirstName;
	
	private String juvenileNameSuffix;

	private String juvenileCurrentAge;

	private String juvenileRaceId;
	
	private String juvenileOriginalRaceId;
	
	private String juvenileRaceDescription;

	private String juvenileSexId;

	private String probationOfficeId;

	private String probationOfficerLogonId;

	private String probationOfficerFirstName;

	private String probationOfficerMiddleName;

	private String probationOfficerLastName;

	private String caseStatusId;
	
	private String caseStatus;

	private Date activationDate;

	private boolean MAYSINeeded;

	private Date assignmentDate;

	private String caseloadManagerId;

	private String caseloadManagerFirstName;

	private String caseloadManagerMiddleName;

	private String caseloadManagerLastName;

	private String supervisionOutcome;
	
	private String supervisionOutcomeDescriptionId;

	private String closingEvaluation;

	private String detentionFacility;

	private String detentionFacilityId;

	private String detentionStatus;

	private String detentionStatusId;

	//added for Modify Expected Supervision End Date
	private Date supervisionEndDate;
	
	private Date courtOrderedProbationStartDate;

	private boolean benefitsAssessmentNeeded;

	private boolean referralRiskNeeded;

	private boolean interviewRiskNeeded;

	private boolean testingRiskNeeded;

	private boolean residentialRiskNeeded;

	private boolean communityRiskNeeded;

	private boolean progressRiskNeeded;

	private boolean riskAnalysisNeeded;
	
	private boolean moreThanOneReferralNum;

	//used for calendar events
	private boolean calendarAssociatedCasefile;

	//used for notification message to JPO on cancellation of service event
	private String subject;

	private String identity;

	private String notificationMessage;
	
	private Date closingDate;
	
	private String controllingReferral;
	
	private String controllingReferralId;

	//added for Production Support
	private String caseplanId;
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	//added for User Story 14888
	
	private boolean resProgressRiskNeeded;
	
	//US 90880
	private String dobStr;
	
	private String probationFlag;

	private String caseloadManagerEmail ;
	

	private PhoneNumber caseloadManagerWorkPhone = new PhoneNumber("");
	
	private String assignedReferrals;
	
	private boolean subabuse;
	private boolean hispanic;
	private boolean vop;
	private boolean school;
	private boolean riskNeed;

	/**
	 * @return activationDate
	 */
	public Date getActivationDate() {
		return activationDate;
	}

	/**
	 * @return caseStatusId
	 */
	public String getCaseStatusId() {
		return caseStatusId;
	}

	//	/**
	//	 * @return closingEvaluation
	//	 */
	//	public String getClosingEvaluation()
	//	{
	//		return closingEvaluation;
	//	}

	//	/**
	//	 * @return expectedSupervisionDate
	//	 */
	//	public Date getExpectedSupervisionDate()
	//	{
	//		return expectedSupervisionDate;
	//	}

	/**
	 * @return juvenileCurrentAge
	 */
	public String getJuvenileCurrentAge() {
		return juvenileCurrentAge;
	}

	/**
	 * @return juvenileFirstName
	 */
	public String getJuvenileFirstName() {
		return juvenileFirstName;
	}

	/**
	 * @return juvenileLastName
	 */
	public String getJuvenileLastName() {
		return juvenileLastName;
	}

	/**
	 * @return juvenileMiddleName
	 */
	public String getJuvenileMiddleName() {
		return juvenileMiddleName;
	}

	/**
	 * @return the juvenileNameSuffix
	 */
	public String getJuvenileNameSuffix() {
		return juvenileNameSuffix;
	}

	/**
	 * @return juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @return juvenileRaceId
	 */
	public String getJuvenileRaceId() {
		return juvenileRaceId;
	}

	/**
	 * @return juvenileSexId
	 */
	public String getJuvenileSexId() {
		return juvenileSexId;
	}

	/**
	 * @return probationOfficerFirstName
	 */
	public String getProbationOfficerFirstName() {
		return probationOfficerFirstName;
	}

	/**
	 * @return probationOfficerLastName
	 */
	public String getProbationOfficerLastName() {
		return probationOfficerLastName;
	}

	/**
	 * @return probationOfficerMiddleName
	 */
	public String getProbationOfficerMiddleName() {
		return probationOfficerMiddleName;
	}

	/**
	 * @return supervisionNum
	 */
	public String getSupervisionNum() {
		return supervisionNum;
	}
	/**
	 * @return caseplanId
	 */
	public String getCaseplanId() {
		return caseplanId;
	}
	/**
	 * @param caseplanId
	 */
	public void setCaseplanId(String aCaseplanId) {
		caseplanId = aCaseplanId;
	}
	//	/**
	//	 * @return supervisionOutcome
	//	 */
	//	public String getSupervisionOutcome()
	//	{
	//		return supervisionOutcome;
	//	}

	/**
	 * @return supervisionTypeId
	 */
	public String getSupervisionTypeId() {
		return supervisionTypeId;
	}

	/**
	 * @param activationDate
	 */
	public void setActivationDate(Date aActivationDate) {
		activationDate = aActivationDate;
	}

	/**
	 * @param caseStatusId
	 */
	public void setCaseStatusId(String aCaseStatus) {
		caseStatusId = aCaseStatus;
	}

	//	/**
	//	 * @param closingEvaluation
	//	 */
	//	public void setClosingEvaluation(String aClosingEvaluation)
	//	{
	//		closingEvaluation = aClosingEvaluation;
	//	}

	//	/**
	//	 * @param expectedSupervisionDate
	//	 */
	//	public void setExpectedSupervisionDate(Date aExpectedSupervisionDate)
	//	{
	//		expectedSupervisionDate = aExpectedSupervisionDate;
	//	}

	/**
	 * @param juvenileCurrentAge
	 */
	public void setJuvenileCurrentAge(String aJuvenileCurrentAge) {
		juvenileCurrentAge = aJuvenileCurrentAge;
	}

	/**
	 * @param juvenileFirstName
	 */
	public void setJuvenileFirstName(String aJuvenileFirstName) {
		juvenileFirstName = aJuvenileFirstName;
	}

	/**
	 * @param juvenileLastName
	 */
	public void setJuvenileLastName(String aJuvenileLastName) {
		juvenileLastName = aJuvenileLastName;
	}

	/**
	 * @param juvenileMiddleName
	 */
	public void setJuvenileMiddleName(String aJuvenileMiddleName) {
		juvenileMiddleName = aJuvenileMiddleName;
	}

	/**
	 * @param juvenileNameSuffix the juvenileNameSuffix to set
	 */
	public void setJuvenileNameSuffix(String juvenileNameSuffix) {
		this.juvenileNameSuffix = juvenileNameSuffix;
	}

	/**
	 * @param juvenileNum
	 */
	public void setJuvenileNum(String aJuvenileNum) {
		juvenileNum = aJuvenileNum;
	}

	/**
	 * @param juvenileRaceId
	 */
	public void setJuvenileRaceId(String aJuvenileRace) {
		juvenileRaceId = aJuvenileRace;
	}

	/**
	 * @param juvenileSexId
	 */
	public void setJuvenileSexId(String aJuvenileSex) {
		juvenileSexId = aJuvenileSex;
	}

	/**
	 * @param probationOfficerFirstName
	 */
	public void setProbationOfficerFirstName(String aProbationOfficerFirstName) {
		probationOfficerFirstName = aProbationOfficerFirstName;
	}

	/**
	 * @param probationOfficerLastName
	 */
	public void setProbationOfficerLastName(String aProbationOfficerLastName) {
		probationOfficerLastName = aProbationOfficerLastName;
	}

	/**
	 * @param probationOfficerMiddleName
	 */
	public void setProbationOfficerMiddleName(String aProbationOfficerMiddleName) {
		probationOfficerMiddleName = aProbationOfficerMiddleName;
	}

	/**
	 * @param supervisionNum
	 */
	public void setSupervisionNum(String aSupervisionNum) {
		supervisionNum = aSupervisionNum;
	}

	//	/**
	//	 * @param supervisionOutcome
	//	 */
	//	public void setSupervisionOutcome(String aSupervisionOutcome)
	//	{
	//		supervisionOutcome = aSupervisionOutcome;
	//	}

	/**
	 * @param supervisionTypeId
	 */
	public void setSupervisionTypeId(String aSupervisionType) {
		supervisionTypeId = aSupervisionType;
	}

	/**
	 * @param MAYSINeeded
	 */
	public void setIsMAYSINeeded(boolean MAYSINeeded) {
		this.MAYSINeeded = MAYSINeeded;
	}

	/**
	 * @return MAYSINeeded
	 */
	public boolean getIsMAYSINeeded() {
		return MAYSINeeded;
	}

	/**
	 * @return
	 */
	public String getCaseloadManagerFirstName() {
		return caseloadManagerFirstName;
	}

	/**
	 * @return
	 */
	public String getCaseloadManagerId() {
		return caseloadManagerId;
	}

	/**
	 * @return
	 */
	public String getCaseloadManagerLastName() {
		return caseloadManagerLastName;
	}

	/**
	 * @param string
	 */
	public void setCaseloadManagerFirstName(String string) {
		caseloadManagerFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setCaseloadManagerId(String string) {
		caseloadManagerId = string;
	}

	/**
	 * @param string
	 */
	public void setCaseloadManagerLastName(String string) {
		caseloadManagerLastName = string;
	}

	/**
	 * @return
	 */
	public String getCaseloadManagerMiddleName() {
		return caseloadManagerMiddleName;
	}

	/**
	 * @param string
	 */
	public void setCaseloadManagerMiddleName(String string) {
		caseloadManagerMiddleName = string;
	}

	/**
	 * @return
	 */
	public String getClosingEvaluation() {
		return closingEvaluation;
	}

	/**
	 * @return
	 */
	public String getSupervisionOutcome() {
		return supervisionOutcome;
	}

	/**
	 * @param string
	 */
	public void setClosingEvaluation(String string) {
		closingEvaluation = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisionOutcome(String string) {
		supervisionOutcome = string;
	}

	
	/**
	 * @return the supervisionOutcomeDescriptionId
	 */
	public String getSupervisionOutcomeDescriptionId() {
		return supervisionOutcomeDescriptionId;
	}

	/**
	 * @param supervisionOutcomeDescriptionId the supervisionOutcomeDescriptionId to set
	 */
	public void setSupervisionOutcomeDescriptionId(
			String supervisionOutcomeDescriptionId) {
		this.supervisionOutcomeDescriptionId = supervisionOutcomeDescriptionId;
	}

	/**
	 * @return
	 */
	public String getSequenceNum() {
		return sequenceNum;
	}

	/**
	 * @param string
	 */
	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	/**
	 * @return Returns the benefitsAssessmentNeeded.
	 */
	public boolean isBenefitsAssessmentNeeded() {
		return benefitsAssessmentNeeded;
	}

	/**
	 * @param benefitsAssessmentNeeded
	 *            The benefitsAssessmentNeeded to set.
	 */
	public void setBenefitsAssessmentNeeded(boolean benefitsAssessmentNeeded) {
		this.benefitsAssessmentNeeded = benefitsAssessmentNeeded;
	}

	/**
	 * @return Returns the communityRiskNeeded.
	 */
	public boolean isCommunityRiskNeeded() {
		return communityRiskNeeded;
	}

	/**
	 * @param communityRiskNeeded
	 *            The communityRiskNeeded to set.
	 */
	public void setCommunityRiskNeeded(boolean communityRiskNeeded) {
		this.communityRiskNeeded = communityRiskNeeded;
	}

	/**
	 * @return Returns the interviewRiskNeeded.
	 */
	public boolean isInterviewRiskNeeded() {
		return interviewRiskNeeded;
	}

	/**
	 * @param interviewRiskNeeded
	 *            The interviewRiskNeeded to set.
	 */
	public void setInterviewRiskNeeded(boolean interviewRiskNeeded) {
		this.interviewRiskNeeded = interviewRiskNeeded;
	}

	/**
	 * @return Returns the progressRiskNeeded.
	 */
	public boolean isProgressRiskNeeded() {
		return progressRiskNeeded;
	}

	/**
	 * @param progressRiskNeeded
	 *            The progressRiskNeeded to set.
	 */
	public void setProgressRiskNeeded(boolean progressRiskNeeded) {
		this.progressRiskNeeded = progressRiskNeeded;
	}

	/**
	 * @return Returns the resProgressRiskNeeded.
	 */
	public boolean isResProgressRiskNeeded() {
		return resProgressRiskNeeded;
	}

	/**
	 * @param resProgressRiskNeeded
	 *            The resProgressRiskNeeded to set.
	 */
	public void setResProgressRiskNeeded(boolean resProgressRiskNeeded) {
		this.resProgressRiskNeeded = resProgressRiskNeeded;
	}

	/**
	 * @return Returns the referralRiskNeeded.
	 */
	public boolean isReferralRiskNeeded() {
		return referralRiskNeeded;
	}

	/**
	 * @param referralRiskNeeded
	 *            The referralRiskNeeded to set.
	 */
	public void setReferralRiskNeeded(boolean referralRiskNeeded) {
		this.referralRiskNeeded = referralRiskNeeded;
	}

	/**
	 * @return Returns the residentialRiskNeeded.
	 */
	public boolean isResidentialRiskNeeded() {
		return residentialRiskNeeded;
	}

	/**
	 * @param residentialRiskNeeded
	 *            The residentialRiskNeeded to set.
	 */
	public void setResidentialRiskNeeded(boolean residentialRiskNeeded) {
		this.residentialRiskNeeded = residentialRiskNeeded;
	}

	/**
	 * @return Returns the testingRiskNeeded.
	 */
	public boolean isTestingRiskNeeded() {
		return testingRiskNeeded;
	}

	/**
	 * @param isTestingRiskNeeded
	 *            The isTestingRiskNeeded to set.
	 */
	public void setTestingRiskNeeded(boolean testingRiskNeeded) {
		this.testingRiskNeeded = testingRiskNeeded;
	}

	/**
	 * @return Returns the supervisionEndDate.
	 */
	public Date getSupervisionEndDate() {
		return supervisionEndDate;
	}

	/**
	 * @param supervisionEndDate
	 *            The supervisionEndDate to set.
	 */
	public void setSupervisionEndDate(Date supervisionEndDate) {
		this.supervisionEndDate = supervisionEndDate;
	}
	
	/**
	 * @return Returns the courtOrderedProbationStartDate.
	 */
	public Date getCourtOrderedProbationStartDate() {
		return courtOrderedProbationStartDate;
	}

	/**
	 * @param courtOrderedProbationStartDate
	 *            The courtOrderedProbationStartDate to set.
	 */
	public void setCourtOrderedProbationStartDate(Date courtOrderedProbationStartDate) {
		this.courtOrderedProbationStartDate = courtOrderedProbationStartDate;
	}

	/**
	 * @return Returns the riskAnalysisNeeded.
	 */
	public boolean isRiskAnalysisNeeded() {
		return riskAnalysisNeeded;
	}

	/**
	 * @param riskAnalysisNeeded
	 *            The riskAnalysisNeeded to set.
	 */
	public void setRiskAnalysisNeeded(boolean riskAnalysisNeeded) {
		this.riskAnalysisNeeded = riskAnalysisNeeded;
	}

	/**
	 * @return Returns the assignmentDate.
	 */
	public Date getAssignmentDate() {
		return assignmentDate;
	}

	/**
	 * @param assignmentDate
	 *            The assignmentDate to set.
	 */
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	/**
	 * @return Returns the probationOfficeId.
	 */
	public String getProbationOfficeId() {
		return probationOfficeId;
	}

	/**
	 * @param probationOfficeId
	 *            The probationOfficeId to set.
	 */
	public void setProbationOfficeId(String probationOfficeId) {
		this.probationOfficeId = probationOfficeId;
	}

	/**
	 * @return Returns the probationOfficerLogonId.
	 */
	public String getProbationOfficerLogonId() {
		return probationOfficerLogonId;
	}

	/**
	 * @param probationOfficerLogonId
	 *            The probationOfficerLogonId to set.
	 */
	public void setProbationOfficerLogonId(String probationOfficerLogonId) {
		this.probationOfficerLogonId = probationOfficerLogonId;
	}

	/**
	 * @return Returns the calendarAssociatedCasefile.
	 */
	public boolean isCalendarAssociatedCasefile() {
		return calendarAssociatedCasefile;
	}

	/**
	 * @param calendarAssociatedCasefile
	 *            The calendarAssociatedCasefile to set.
	 */
	public void setCalendarAssociatedCasefile(boolean calendarAssociatedCasefile) {
		this.calendarAssociatedCasefile = calendarAssociatedCasefile;
	}

	/**
	 * @return Returns the detentionFacility.
	 */
	public String getDetentionFacility() {
		return detentionFacility;
	}

	/**
	 * @param detentionFacility
	 *            The detentionFacility to set.
	 */
	public void setDetentionFacility(String detentionFacility) {
		this.detentionFacility = detentionFacility;
	}

	/**
	 * @return Returns the detentionFacilityId.
	 */
	public String getDetentionFacilityId() {
		return detentionFacilityId;
	}

	/**
	 * @param detentionFacilityId
	 *            The detentionFacilityId to set.
	 */
	public void setDetentionFacilityId(String detentionFacilityId) {
		this.detentionFacilityId = detentionFacilityId;
	}

	/**
	 * @return Returns the detentionStatus.
	 */
	public String getDetentionStatus() {
		return detentionStatus;
	}

	/**
	 * @param detentionStatus
	 *            The detentionStatus to set.
	 */
	public void setDetentionStatus(String detentionStatus) {
		this.detentionStatus = detentionStatus;
	}

	/**
	 * @return Returns the detentionStatusId.
	 */
	public String getDetentionStatusId() {
		return detentionStatusId;
	}

	/**
	 * @param detentionStatusId
	 *            The detentionStatusId to set.
	 */
	public void setDetentionStatusId(String detentionStatusId) {
		this.detentionStatusId = detentionStatusId;
	}

	/**
	 * @return Returns the identity.
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * @param identity
	 *            The identity to set.
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * @return Returns the notificationMessage.
	 */
	public String getNotificationMessage() {
		return notificationMessage;
	}

	/**
	 * @param notificationMessage
	 *            The notificationMessage to set.
	 */
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public String getControllingReferral() {
		return controllingReferral;
	}

	public void setControllingReferral(String controllingReferral) {
		this.controllingReferral = controllingReferral;
	}

	public String getControllingReferralId() {
		return controllingReferralId;
	}

	public void setControllingReferralId(String controllingReferralId) {
		this.controllingReferralId = controllingReferralId;
	}
	
	/**
	 * @return
	 */
	public String getCreateUserID()
	{
		return createUserID;
	}
	/**
	 * @param string
	 */
	public void setCreateUserID(String string)
	{
		createUserID = string;
	}
	/**
	 * @return
	 */
	public Date getCreateDate()
	{
		return createDate;
	}
	/**
	 * @param date
	 */
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	
	/**
	 * @return
	 */

	public String getUpdateJIMS2UserID()
	{
		return updateJIMS2UserID;
	}
	/**
	 * @param string
	 */

	public void setUpdateJIMS2UserID(String string)
	{
		updateJIMS2UserID = string;
	}
	
	/**
	 * @return
	 */

	public String getCreateJIMS2UserID()
	{
		return createJIMS2UserID;
	}
	
	/**
	 * @param string
	 */

	public void setCreateJIMS2UserID(String string)
	{
		createJIMS2UserID = string;
	}
	
	/**
	 * @return
	 */
	public String getUpdateUser()
	{
		return updateUser;
	}
	/**
	 * @param string
	 */
	public void setUpdateUser(String string)
	{
		updateUser = string;
	}
	
	/**
	 * @return
	 */
	public Date getUpdateDate()
	{
		return updateDate;
	}
	/**
	 * @param date
	 */
	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

	/**
	 * @param supervisionCategoryId the supervisionCategoryId to set
	 */
	public void setSupervisionCategoryId(String supervisionCategoryId) {
		this.supervisionCategoryId = supervisionCategoryId;
	}

	/**
	 * @return the supervisionCategoryId
	 */
	public String getSupervisionCategoryId() {
		return supervisionCategoryId;
	}
	
	/**
	 * @return the caseStatus
	 */
	public String getCaseStatus() {
		return caseStatus;
	}

	/**
	 * @param caseStatus the caseStatus to set
	 */
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	
	/**
	 * @return the supervisionType
	 */
	public String getSupervisionType() {
		return supervisionType;
	}

	/**
	 * @param supervisionType the supervisionType to set
	 */
	public void setSupervisionType(String supervisionType) {
		this.supervisionType = supervisionType;
	}

	@Override
	public int compareTo(Object o) {
		if(o==null)
			return -1;
		JuvenileCasefileResponseEvent evt = (JuvenileCasefileResponseEvent) o;
		return supervisionNum.compareToIgnoreCase(evt.getSupervisionNum());		
	}

	public String getDobStr()
	{
	    return dobStr;
	}

	public void setDobStr(String dobStr)
	{
	    this.dobStr = dobStr;
	}
	
	public String getJuvenileOriginalRaceId()
	{
	    return juvenileOriginalRaceId;
	}

	public void setJuvenileOriginalRaceId(String juvenileOriginalRaceId)
	{
	    this.juvenileOriginalRaceId = juvenileOriginalRaceId;
	}
	
	public String getJuvenileRaceDescription()
	{
	    return juvenileRaceDescription;
	}

	public void setJuvenileRaceDescription(String juvenileRaceDescription)
	{
	    this.juvenileRaceDescription = juvenileRaceDescription;
	}
	public String getProbationFlag()
	{
	    return probationFlag;
	}

	public void setProbationFlag(String probationFlag)
	{
	    this.probationFlag = probationFlag;
	}
	
	public String getJuvenilePreferredFirstName()
	{
	    return juvenilePreferredFirstName;
	}

	public void setJuvenilePreferredFirstName(String juvenilePreferredFirstName)
	{
	    this.juvenilePreferredFirstName = juvenilePreferredFirstName;
	}

	public boolean getMoreThanOneReferralNum()
	{
	    return moreThanOneReferralNum;
	}

	public void setMoreThanOneReferralNum(boolean moreThanOneReferralNum)
	{
	    this.moreThanOneReferralNum = moreThanOneReferralNum;
	}

	public String getCaseloadManagerEmail()
	{
	    return caseloadManagerEmail;
	}

	public void setCaseloadManagerEmail(String caseloadManagerEmail)
	{
	    this.caseloadManagerEmail = caseloadManagerEmail;
	}

	public PhoneNumber getCaseloadManagerWorkPhone()
	{
	    return caseloadManagerWorkPhone;
	}

	public void setCaseloadManagerWorkPhone(PhoneNumber caseloadManagerWorkPhone)
	{
	    this.caseloadManagerWorkPhone = caseloadManagerWorkPhone;
	}

	public String getSupervisionNumWithSupervisionType()
	{
	    return supervisionNumWithSupervisionType;
	}

	public void setSupervisionNumWithSupervisionType(String supervisionNumWithSupervisionType)
	{
	    this.supervisionNumWithSupervisionType = supervisionNumWithSupervisionType;
	}

	public String getAssignedReferrals()
	{
	    return assignedReferrals;
	}

	public void setAssignedReferrals(String assignedReferrals)
	{
	    this.assignedReferrals = assignedReferrals;
	}

	public boolean getSubabuse()
	{
	    return subabuse;
	}

	public void setSubabuse(boolean subabuse)
	{
	    this.subabuse = subabuse;
	}

	public boolean getHispanic()
	{
	    return hispanic;
	}

	public void setHispanic(boolean hispanic)
	{
	    this.hispanic = hispanic;
	}

	public boolean getVop()
	{
	    return vop;
	}

	public void setVop(boolean vop)
	{
	    this.vop = vop;
	}

	public boolean getSchool()
	{
	    return school;
	}

	public void setSchool(boolean school)
	{
	    this.school = school;
	}

	public boolean getRiskNeed()
	{
	    return riskNeed;
	}

	public void setRiskNeed(boolean riskNeed)
	{
	    this.riskNeed = riskNeed;
	}

	
	
	
	
	
	
	
	

	
	
	
	
	
	
}