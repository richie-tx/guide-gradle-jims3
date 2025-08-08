package messaging.casefile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class CasefileNonComplianceNoticeProbationViolationResponseEvent extends ResponseEvent {
	private String casefileNonComplianceNoticeId;
	private String juvenileTechnicalVOPCodesId;
	private String reportId;
	//added for ProdSupport
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate = null;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	public String getCasefileNonComplianceNoticeId() {
		return casefileNonComplianceNoticeId;
	}
	public void setCasefileNonComplianceNoticeId(
			String casefileNonComplianceNoticeId) {
		this.casefileNonComplianceNoticeId = casefileNonComplianceNoticeId;
	}
	public String getJuvenileTechnicalVOPCodesId() {
		return juvenileTechnicalVOPCodesId;
	}
	
	public void setJuvenileTechnicalVOPCodesId(String juvenileTechnicalVOPCodesId) {
		this.juvenileTechnicalVOPCodesId = juvenileTechnicalVOPCodesId;
	}
	
	/**
	 * @return the reportId
	 */
	public String getReportId() {
		return reportId;
	}
	/**
	 * @param reportId the reportId to set
	 */
	public void setReportId(String reportId) {
		this.reportId = reportId;
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
