/*
 * Created on Jul 26, 2011
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * Returns a basic populated version of a sanction code table record
 * 
 */
public class JuvenileCasefileSanctionNoticesResponseEvent extends ResponseEvent 
{
	private Date sanctionCompleteByDate;
	private Date sanctionAssignedDate;
	private Date generatedDate;
	private String juvenileCompleted;
	private String juvenileCompletedLit;
	private Date nonComplianceDate;
	private String noticeId; 
	private String noticeSignatureStatus;
	private String noticeSignatureStatusLit;
	private Boolean parentInformed;
	private String vopLevel;
	private String sanctionId; 
	private String sanctionLevel;
	private String sanctionDesc;
	private String sanctionType;
	private String comments;

	
	/**
	 * @return the sanctionCompleteByDate
	 */
	public Date getsanctionCompleteByDate() {
		return sanctionCompleteByDate;
	}
	/**
	 * @param sanctionCompleteByDate the sanctionCompleteByDate to set
	 */
	public void setsanctionCompleteByDate(Date sanctionCompleteByDate) {
		this.sanctionCompleteByDate = sanctionCompleteByDate;
	}
	/**
	 * @return the sanctionAssignedDate
	 */
	public Date getsanctionAssignedDate() {
		return sanctionAssignedDate;
	}
	/**
	 * @param sanctionAssignedDate the sanctionAssignedDate to set
	 */
	public void setsanctionAssignedDate(Date sanctionAssignedDate) {
		this.sanctionAssignedDate = sanctionAssignedDate;
	}

	/**
	 * @return the generatedDate
	 */
	public Date getGeneratedDate() {
		return generatedDate;
	}
	/**
	 * @param generatedDate the generatedDate to set
	 */
	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}
	
	/**
	 * @return the juvenileCompleted
	 */
	public String getJuvenileCompleted() {
		return juvenileCompleted;
	}
	/**
	 * @param juvenileCompleted the juvenileCompleted to set
	 */
	public void setJuvenileCompleted(String juvenileCompleted) {
		this.juvenileCompleted = juvenileCompleted;
	}
	/**
	 * @return the juvenileCompletedLit
	 */
	public String getJuvenileCompletedLit() {
		return juvenileCompletedLit;
	}
	/**
	 * @param juvenileCompletedLit the juvenileCompletedLit to set
	 */
	public void setJuvenileCompletedLit(String juvenileCompletedLit) {
		this.juvenileCompletedLit = juvenileCompletedLit;
	}
	/**
	 * @return the nonComplianceDate
	 */
	public Date getNonComplianceDate() {
		return nonComplianceDate;
	}
	/**
	 * @param nonComplianceDate the nonComplianceDate to set
	 */
	public void setNonComplianceDate(Date nonComplianceDate) {
		this.nonComplianceDate = nonComplianceDate;
	}
	/**
	 * @return the noticeId
	 */
	public String getNoticeId() {
		return noticeId;
	}
	/**
	 * @param noticeId the noticeId to set
	 */
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	/**
	 * @return the noticeSignatureStatus
	 */
	public String getNoticeSignatureStatus() {
		return noticeSignatureStatus;
	}
	/**
	 * @param noticeSignatureStatus the noticeSignatureStatus to set
	 */
	public void setNoticeSignatureStatus(String noticeSignatureStatus) {
		this.noticeSignatureStatus = noticeSignatureStatus;
	}
	/**
	 * @return the noticeSignatureStatusLit
	 */
	public String getNoticeSignatureStatusLit() {
		return noticeSignatureStatusLit;
	}
	/**
	 * @param noticeSignatureStatusLit the noticeSignatureStatusLit to set
	 */
	public void setNoticeSignatureStatusLit(String noticeSignatureStatusLit) {
		this.noticeSignatureStatusLit = noticeSignatureStatusLit;
	}
	/**
	 * @return the parentInformed
	 */
	public Boolean getParentInformed() {
		return parentInformed;
	}
	/**
	 * @param parentInformed the parentInformed to set
	 */
	public void setParentInformed(Boolean parentInformed) {
		this.parentInformed = parentInformed;
	}
	/**
	 * @return the vopLevel
	 */
	public String getVopLevel() {
		return vopLevel;
	}
	/**
	 * @param vopLevel the vopLevel to set
	 */
	public void setVopLevel(String vopLevel) {
		this.vopLevel = vopLevel;
	}
	/**
	 * @return the sanctionId
	 */
	public String getSanctionId() {
		return sanctionId;
	}
	/**
	 * @param sanctionId the sanctionId to set
	 */
	public void setSanctionId(String sanctionId) {
		this.sanctionId = sanctionId;
	}
	/**
	 * @return the sanctionLevel
	 */
	public String getSanctionLevel() {
		return sanctionLevel;
	}
	/**
	 * @param sanctionLevel the sanctionLevel to set
	 */
	public void setSanctionLevel(String sanctionLevel) {
		this.sanctionLevel = sanctionLevel;
	}
	/**
	 * @return the sanctionDesc
	 */
	public String getSanctionDesc() {
		return sanctionDesc;
	}
	/**
	 * @param sanctionDesc the sanctionDesc to set
	 */
	public void setSanctionDesc(String sanctionDesc) {
		this.sanctionDesc = sanctionDesc;
	}
	/**
	 * @return the sanctionType
	 */
	public String getSanctionType() {
		return sanctionType;
	}
	/**
	 * @param sanctionType the sanctionType to set
	 */
	public void setSanctionType(String sanctionType) {
		this.sanctionType = sanctionType;
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
}