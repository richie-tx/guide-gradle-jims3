package messaging.casefile.reply;

import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class CasefileNonComplianceNoticeResponseEvent extends ResponseEvent{
	private String casefileNonComplianceNoticeId;
	private String casefileId;
	private Date nonComplianceDate;
	private Date sanctionAssignedDate;
	private Date completeSanctionByDate;
	private boolean parentInformed;
	private Date signedDate;
	private Date completionDate;
	private String completionComments;
	private String actionTakenComments;
	private String actionTakenOtherText;
	private String violationLevelId;
	private String sanctionLevelId;
	private String signatureStatusId;
	private String completionStatusId;
	private String actionTakenId;
	private String documentId;
	private String noticeSignatureStatusLit;  // for UI display only
	private String juvenileCompletedLit;      // for UI display only
	private String violationLevelLit;         // for UI display only
	private Date generatedDate;
	private Collection probationViolations;
	private Collection sanctions;
	//added for ProdSupport
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate = null;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
		
	public Date getNonComplianceDate() {
		return nonComplianceDate;
	}
	public void setNonComplianceDate(Date nonComplianceDate) {
		this.nonComplianceDate = nonComplianceDate;
	}
	public Date getSanctionAssignedDate() {
		return sanctionAssignedDate;
	}
	public void setSanctionAssignedDate(Date sanctionAssignedDate) {
		this.sanctionAssignedDate = sanctionAssignedDate;
	}
	public Date getCompleteSanctionByDate() {
		return completeSanctionByDate;
	}
	public void setCompleteSanctionByDate(Date completeSanctionByDate) {
		this.completeSanctionByDate = completeSanctionByDate;
	}
	public boolean isParentInformed() {
		return parentInformed;
	}
	public void setParentInformed(boolean parentInformed) {
		this.parentInformed = parentInformed;
	}
	public Date getSignedDate() {
		return signedDate;
	}
	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	public String getCompletionComments() {
		return completionComments;
	}
	public void setCompletionComments(String completionComments) {
		this.completionComments = completionComments;
	}
	public String getActionTakenComments() {
		return actionTakenComments;
	}
	public void setActionTakenComments(String actionTakenComments) {
		this.actionTakenComments = actionTakenComments;
	}
	public String getCasefileNonComplianceNoticeId() {
		return casefileNonComplianceNoticeId;
	}
	public void setCasefileNonComplianceNoticeId(
			String casefileNonComplianceNoticeId) {
		this.casefileNonComplianceNoticeId = casefileNonComplianceNoticeId;
	}
	public String getViolationLevelId() {
		return violationLevelId;
	}
	public void setViolationLevelId(String violationLevelId) {
		this.violationLevelId = violationLevelId;
	}
	public String getSignatureStatusId() {
		return signatureStatusId;
	}
	public void setSignatureStatusId(String signatureStatusId) {
		this.signatureStatusId = signatureStatusId;
	}
	public String getCompletionStatusId() {
		return completionStatusId;
	}
	public void setCompletionStatusId(String completionStatusId) {
		this.completionStatusId = completionStatusId;
	}
	public String getActionTakenId() {
		return actionTakenId;
	}
	public void setActionTakenId(String actionTakenId) {
		this.actionTakenId = actionTakenId;
	}
	public Collection getProbationViolations() {
		return probationViolations;
	}
	public void setProbationViolations(Collection probationViolations) {
		this.probationViolations = probationViolations;
	}
	public Collection getSanctions() {
		return sanctions;
	}
	public void setSanctions(Collection sanctions) {
		this.sanctions = sanctions;
	}
	public String getCasefileId() {
		return casefileId;
	}
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	public String getSanctionLevelId() {
		return sanctionLevelId;
	}
	public void setSanctionLevelId(String sanctionLevelId) {
		this.sanctionLevelId = sanctionLevelId;
	}
	public Date getGeneratedDate() {
		return generatedDate;
	}
	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}
	public String getNoticeSignatureStatusLit() {
		return noticeSignatureStatusLit;
	}
	public void setNoticeSignatureStatusLit(String noticeSignatureStatusLit) {
		this.noticeSignatureStatusLit = noticeSignatureStatusLit;
	}
	public String getJuvenileCompletedLit() {
		return juvenileCompletedLit;
	}
	public void setJuvenileCompletedLit(String juvenileCompletedLit) {
		this.juvenileCompletedLit = juvenileCompletedLit;
	}	
	
	/**
	 * @return the violationLevelLit
	 */
	public String getViolationLevelLit() {
		return violationLevelLit;
	}
	/**
	 * @param violationLevelLit the violationLevelLit to set
	 */
	public void setViolationLevelLit(String violationLevelLit) {
		this.violationLevelLit = violationLevelLit;
	}
	public String getActionTakenOtherText() {
		return actionTakenOtherText;
	}
	public void setActionTakenOtherText(String actionTakenOtherText) {
		this.actionTakenOtherText = actionTakenOtherText;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
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
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the createJIMS2UserID
	 */
	public String getCreateJIMS2UserID() {
		return createJIMS2UserID;
	}
	/**
	 * @param createJIMS2UserID the createJIMS2UserID to set
	 */
	public void setCreateJIMS2UserID(String createJIMS2UserID) {
		this.createJIMS2UserID = createJIMS2UserID;
	}
	/**
	 * @return the updateJIMS2UserID
	 */
	public String getUpdateJIMS2UserID() {
		return updateJIMS2UserID;
	}
	/**
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}
	
	
}
