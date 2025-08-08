package messaging.casefile.reply;

import java.util.Date;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;
import naming.PDCodeTableConstants;
import ui.common.SimpleCodeTableHelper;

public class AssessmentReferralResponseEvent extends ResponseEvent implements IAddressable,Comparable<AssessmentReferralResponseEvent>{

	private String juvenileNum;
	private Date referralDate;
	private String assessmentTypeId;
	//For UI Display
	private String assessmentType;
	private String assessmentIDNumber;
	private String placementFacilityId;
	private String gangNameId;
	private String cliqueSetId;
	private String reasonForReferralId;
	private String lvlOfGangInvolvementId;
	private String recommendationsId;

	private String acceptedStatus;
	private String recommendations;
	private String comments;
	private String conclusion;
	private String otherReasonForReferral;
	private String otherGangName;
	private String otherCliqueSet;
	private String descHybrid;

	private String rejectionReason;
	private String personMakingReferral;
	private String userId;
	private String status;
	
	private String subject;
	private String identity;
	private String notificationMessage;
	private String parentNotified;
	private String parentNotifiedGangAssReq;
	private String juvenileName;
	private String jpoID;
	private String jpoOfRecord;

	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the referralDate
	 */
	public Date getReferralDate() {
		return referralDate;
	}

	/**
	 * @param referralDate the referralDate to set
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	/**
	 * @return the assessmentTypeId
	 */
	public String getAssessmentTypeId() {
		return assessmentTypeId;
	}

	/**
	 * @param assessmentTypeId the assessmentTypeId to set
	 */
	public void setAssessmentTypeId(String assessmentTypeId) {
		this.assessmentTypeId = assessmentTypeId;
	}

	/**
	 * @return the assessmentIDNumber
	 */
	public String getAssessmentIDNumber() {
		return assessmentIDNumber;
	}


	/**
	 * @param assessmentIDNumber the assessmentIDNumber to set
	 */
	public void setAssessmentIDNumber(String assessmentIDNumber) {
		this.assessmentIDNumber = assessmentIDNumber;
	}

	/**
	 * @return the placementFacilityId
	 */
	public String getPlacementFacilityId() {
		return placementFacilityId;
	}

	/**
	 * @param placementFacilityId the placementFacilityId to set
	 */
	public void setPlacementFacilityId(String placementFacilityId) {
		this.placementFacilityId = placementFacilityId;
	}

	/**
	 * @return the gangNameId
	 */
	public String getGangNameId() {
		return gangNameId;
	}

	/**
	 * @param gangNameId the gangNameId to set
	 */
	public void setGangNameId(String gangNameId) {
		this.gangNameId = gangNameId;
	}


	/**
	 * @return the cliqueSetId
	 */
	public String getCliqueSetId() {
		return cliqueSetId;
	}

	/**
	 * @param cliqueSetId the cliqueSetId to set
	 */
	public void setCliqueSetId(String cliqueSetId) {
		this.cliqueSetId = cliqueSetId;
	}

	/**
	 * @return the reasonForReferralId
	 */
	public String getReasonForReferralId() {
		return reasonForReferralId;
	}

	/**
	 * @param reasonForReferralId the reasonForReferralId to set
	 */
	public void setReasonForReferralId(String reasonForReferralId) {
		this.reasonForReferralId = reasonForReferralId;
	}


	/**
	 * @return the lvlOfGangInvolvementId
	 */
	public String getLvlOfGangInvolvementId() {
		return lvlOfGangInvolvementId;
	}

	/**
	 * @param lvlOfGangInvolvementId the lvlOfGangInvolvementId to set
	 */
	public void setLvlOfGangInvolvementId(String lvlOfGangInvolvementId) {
		this.lvlOfGangInvolvementId = lvlOfGangInvolvementId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the recommendationsId
	 */
	public String getRecommendationsId() {
		return recommendationsId;
	}

	/**
	 * @param recommendationsId the recommendationsId to set
	 */
	public void setRecommendationsId(String recommendationsId) {
		this.recommendationsId = recommendationsId;
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
	 * @return the conclusion
	 */
	public String getConclusion() {
		return conclusion;
	}

	/**
	 * @param conclusion the conclusion to set
	 */
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}


	/**
	 * @return the otherReasonForReferral
	 */
	public String getOtherReasonForReferral() {
		return otherReasonForReferral;
	}

	/**
	 * @param otherReasonForReferral the otherReasonForReferral to set
	 */
	public void setOtherReasonForReferral(String otherReasonForReferral) {
		this.otherReasonForReferral = otherReasonForReferral;
	}

	/**
	 * @return the otherGangName
	 */
	public String getOtherGangName() {
		return otherGangName;
	}

	/**
	 * @param otherGangName the otherGangName to set
	 */
	public void setOtherGangName(String otherGangName) {
		this.otherGangName = otherGangName;
	}

	/**
	 * @return the descHybrid
	 */
	public String getDescHybrid() {
		return descHybrid;
	}

	/**
	 * @param descHybrid the descHybrid to set
	 */
	public void setDescHybrid(String descHybrid) {
		this.descHybrid = descHybrid;
	}

	/**
	 * @return the otherCliqueSet
	 */
	public String getOtherCliqueSet() {
		return otherCliqueSet;
	}

	/**
	 * @param otherCliqueSet the otherCliqueSet to set
	 */
	public void setOtherCliqueSet(String otherCliqueSet) {
		this.otherCliqueSet = otherCliqueSet;
	}

	/**
	 * @param rejectionReason the rejectionReason to set
	 */
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	/**
	 * @return the rejectionReason
	 */
	public String getRejectionReason() {
		return rejectionReason;
	}
	

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param personMakingReferral the personMakingReferral to set
	 */
	public void setPersonMakingReferral(String personMakingReferral) {
		this.personMakingReferral = personMakingReferral;
	}

	/**
	 * @return the personMakingReferral
	 */
	public String getPersonMakingReferral() {
		return personMakingReferral;
	}

	/**
	 * @param recommendations the recommendations to set
	 */
	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	/**
	 * @return the recommendations
	 */
	public String getRecommendations() {
		this.recommendations = SimpleCodeTableHelper.
		getDescrByCode(PDCodeTableConstants.RECOMMENDATIONS,this.getRecommendationsId());
		return recommendations;
	}

	/**
	 * @param assessmentType the assessmentType to set
	 */
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	/**
	 * @return the assessmentType
	 */
	public String getAssessmentType() {
		this.assessmentType = SimpleCodeTableHelper.
		getDescrByCode(PDCodeTableConstants.ASSESSMENT_REFERRAL_TYPE,this.getAssessmentTypeId());
		return assessmentType;
	}

	/**
	 * @param acceptedStatus the acceptedStatus to set
	 */
	public void setAcceptedStatus(String acceptedStatus) {
		this.acceptedStatus = acceptedStatus;
	}

	/**
	 * @return the acceptedStatus
	 */
	public String getAcceptedStatus() {
		return acceptedStatus;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * @return the identity
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * @param identity the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * @return the notificationMessage
	 */
	public String getNotificationMessage() {
		return notificationMessage;
	}

	/**
	 * @param notificationMessage the notificationMessage to set
	 */
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	
	@Override
	public int compareTo(AssessmentReferralResponseEvent evt) {
		if (evt == null)
			return -1;
		if (this.assessmentIDNumber == null)
			return 1;
		return Integer.parseInt(evt.getAssessmentIDNumber())- Integer.parseInt(this.assessmentIDNumber);
	}
	public String getParentNotified()
	{
	    return parentNotified;
	}

	public void setParentNotified(String parentNotified)
	{
	    this.parentNotified = parentNotified;
	}
	
	public String getParentNotifiedGangAssReq()
	{
	    return this.parentNotifiedGangAssReq;
	}

	public void setParentNotifiedGangAssReq(String parentNotifiedGangAssReq)
	{
	    this.parentNotifiedGangAssReq = parentNotifiedGangAssReq;
	}

	public String getJuvenileName()
	{
	    return juvenileName;
	}

	public void setJuvenileName(String juvenileName)
	{
	    this.juvenileName = juvenileName;
	}

	public String getJpoID()
	{
	    return jpoID;
	}

	public void setJpoID(String jpoID)
	{
	    this.jpoID = jpoID;
	}

	public String getJpoOfRecord()
	{
	    return jpoOfRecord;
	}

	public void setJpoOfRecord(String jpoOfRecord)
	{
	    this.jpoOfRecord = jpoOfRecord;
	}

}
