package messaging.casefile.reply;

import java.util.Date;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class CasefileClosingResponseEvent extends ResponseEvent implements IAddressable
{
	private String petitionNumber;
	private String closingEvaluation;
	private String facilityReleaseReasonId;
	private String supervisionOutcomeId;
	private String supervisionOutcomeDescriptionId;
	private String facilityReleaseReasonName;
	private String permanencyPlanName;
	private String facilityId;
	private String facilityName;
	private String supervisionNumber;
	private Date supervisionEndDate = null;
	private String levelOfCareName;
	private String levelOfCareId;
	private String casefileClosingStatus;
	private String permanencyPlanId;
	private String specialNotes;
	private String exitPlanTemplateLocation;
	private String supervisionOutcomeName;
	private String controllingReferralId;
	private Date expectedReleaseDate = null;
	private String closingComments;
	private String casefileClosingInfoId;
	private String rejectReason;
	private String subject;
	private String identity;
	private String notificationMessage;
	private boolean isClosingPktGenerated;
	private boolean isClosingLetterGenerated;
	private String createdBy;
	private String juvLocUnitId;
	private String recordCLM;

	
	//added for ProdSupport
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate = null;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;

	
	
	/**
	 * @return
	 */
	public String getCasefileClosingStatus()
	{
		return casefileClosingStatus;
	}

	/**
	 * @return
	 */
	public String getClosingComments()
	{
		return closingComments;
	}

	/**
	 * @return
	 */
	public String getClosingEvaluation()
	{
		return closingEvaluation;
	}

	/**
	 * @return
	 */
	public String getControllingReferralId()
	{
		return controllingReferralId;
	}

	/**
	 * @return
	 */
	public String getExitPlanTemplateLocation()
	{
		return exitPlanTemplateLocation;
	}

	/**
	 * @return
	 */
	public Date getExpectedReleaseDate()
	{
		return expectedReleaseDate;
	}

	/**
	 * @return
	 */
	public String getFacilityId()
	{
		return facilityId;
	}

	/**
	 * @return
	 */
	public String getFacilityName()
	{
		return facilityName;
	}

	/**
	 * @return
	 */
	public String getFacilityReleaseReasonId()
	{
		return facilityReleaseReasonId;
	}

	/**
	 * @return
	 */
	public String getFacilityReleaseReasonName()
	{
		return facilityReleaseReasonName;
	}

	/**
	 * @return
	 */
	public String getLevelOfCareId()
	{
		return levelOfCareId;
	}

	/**
	 * @return
	 */
	public String getLevelOfCareName()
	{
		return levelOfCareName;
	}

	/**
	 * @return
	 */
	public String getPermanencyPlanId()
	{
		return permanencyPlanId;
	}

	/**
	 * @return
	 */
	public String getPermanencyPlanName()
	{
		return permanencyPlanName;
	}

	/**
	 * @return
	 */
	public String getPetitionNumber()
	{
		return petitionNumber;
	}

	/**
	 * @return
	 */
	public String getSpecialNotes()
	{
		return specialNotes;
	}

	/**
	 * @return
	 */
	public Date getSupervisionEndDate()
	{
		return supervisionEndDate;
	}

	/**
	 * @return
	 */
	public String getSupervisionNumber()
	{
		return supervisionNumber;
	}

	/**
	 * @return
	 */
	public String getSupervisionOutcomeId()
	{
		return supervisionOutcomeId;
	}

	/**
	 * @return
	 */
	public String getSupervisionOutcomeName()
	{
		return supervisionOutcomeName;
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
	 * @param string
	 */
	public void setCasefileClosingStatus(String string)
	{
		casefileClosingStatus = string;
	}

	/**
	 * @param string
	 */
	public void setClosingComments(String string)
	{
		closingComments = string;
	}

	/**
	 * @param string
	 */
	public void setClosingEvaluation(String string)
	{
		closingEvaluation = string;
	}

	/**
	 * @param string
	 */
	public void setControllingReferralId(String string)
	{
		controllingReferralId = string;
	}

	/**
	 * @param string
	 */
	public void setExitPlanTemplateLocation(String string)
	{
		exitPlanTemplateLocation = string;
	}

	/**
	 * @param date
	 */
	public void setExpectedReleaseDate(Date date)
	{
		expectedReleaseDate = date;
	}

	/**
	 * @param string
	 */
	public void setFacilityId(String string)
	{
		facilityId = string;
	}

	/**
	 * @param string
	 */
	public void setFacilityName(String string)
	{
		facilityName = string;
	}

	/**
	 * @param string
	 */
	public void setFacilityReleaseReasonId(String string)
	{
		facilityReleaseReasonId = string;
	}

	/**
	 * @param string
	 */
	public void setFacilityReleaseReasonName(String string)
	{
		facilityReleaseReasonName = string;
	}

	/**
	 * @param string
	 */
	public void setLevelOfCareId(String string)
	{
		levelOfCareId = string;
	}

	/**
	 * @param string
	 */
	public void setLevelOfCareName(String string)
	{
		levelOfCareName = string;
	}

	/**
	 * @param string
	 */
	public void setPermanencyPlanId(String string)
	{
		permanencyPlanId = string;
	}

	/**
	 * @param string
	 */
	public void setPermanencyPlanName(String string)
	{
		permanencyPlanName = string;
	}

	/**
	 * @param string
	 */
	public void setPetitionNumber(String string)
	{
		petitionNumber = string;
	}

	/**
	 * @param string
	 */
	public void setSpecialNotes(String string)
	{
		specialNotes = string;
	}

	/**
	 * @param date
	 */
	public void setSupervisionEndDate(Date date)
	{
		supervisionEndDate = date;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNumber(String string)
	{
		supervisionNumber = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisionOutcomeId(String string)
	{
		supervisionOutcomeId = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisionOutcomeName(String string)
	{
		supervisionOutcomeName = string;
	}

	/**
	 * @return
	 */
	public String getCasefileClosingInfoId()
	{
		return casefileClosingInfoId;
	}

	/**
	 * @param string
	 */
	public void setCasefileClosingInfoId(String string)
	{
		casefileClosingInfoId = string;
	}

	/**
	 * @return
	 */
	public String getRejectReason()
	{
		return rejectReason;
	}

	/**
	 * @param string
	 */
	public void setRejectReason(String string)
	{
		rejectReason = string;
	}

	/**
	 * @return Returns the identity.
	 */
	public String getIdentity() {
		return identity;
	}
	/**
	 * @param identity The identity to set.
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return Returns the notificationMessage.
	 */
	public String getNotificationMessage() {
		return notificationMessage;
	}
	/**
	 * @param notificationMessage The notificationMessage to set.
	 */
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	/**
	 * @return Returns the isClosingLetterGenerated.
	 */
	public boolean isClosingLetterGenerated() {
		return isClosingLetterGenerated;
	}
	/**
	 * @param isClosingLetterGenerated The isClosingLetterGenerated to set.
	 */
	public void setClosingLetterGenerated(boolean isClosingLetterGenerated) {
		this.isClosingLetterGenerated = isClosingLetterGenerated;
	}
	/**
	 * @return Returns the isClosingPktGenerated.
	 */
	public boolean isClosingPktGenerated() {
		return isClosingPktGenerated;
	}
	/**
	 * @param isClosingPktGenerated The isClosingPktGenerated to set.
	 */
	public void setClosingPktGenerated(boolean isClosingPktGenerated) {
		this.isClosingPktGenerated = isClosingPktGenerated;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	 * @return the createUserID
	 */
	public String getCreateUserID() {
		return createUserID;
	}

	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	public String getJuvLocUnitId()
	{
	    return juvLocUnitId;
	}

	public void setJuvLocUnitId(String juvLocUnitId)
	{
	    this.juvLocUnitId = juvLocUnitId;
	}

	public String getRecordCLM()
	{
	    return recordCLM;
	}

	public void setRecordCLM(String recordCLM)
	{
	    this.recordCLM = recordCLM;
	}
	
	
}
