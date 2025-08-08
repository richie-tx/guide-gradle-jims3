package messaging.productionsupport.reply;

import java.util.Date;

import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import mojo.km.messaging.ResponseEvent;

public class ProductionSupportNonComplianceNoticeSanctionResponseEvent extends ResponseEvent implements Comparable{

	private Date entryDate;
	private String reportType;
	private String reportId;
	private Object report;
	private String CasefileNonComplianceNoticeId;
	//added for ProdSupport
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate = null;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;

	/**
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType
	 *            the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the reportId
	 */
	public String getReportId() {
		return reportId;
	}

	/**
	 * @param reportId
	 *            the reportId to set
	 */
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	/**
	 * @return the report
	 */
	public Object getReport() {
		return report;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(Object report) {
		this.report = report;
	}
	
	
	
	/**
	 * @return the casefileNonComplianceNoticeId
	 */
	public String getCasefileNonComplianceNoticeId() {
		return CasefileNonComplianceNoticeId;
	}

	/**
	 * @param casefileNonComplianceNoticeId the casefileNonComplianceNoticeId to set
	 */
	public void setCasefileNonComplianceNoticeId(
			String casefileNonComplianceNoticeId) {
		CasefileNonComplianceNoticeId = casefileNonComplianceNoticeId;
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

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o == null)
            return 1; // this makes any null objects go to the bottom change this to -1 if you want
        // the top of the list
        if (this.entryDate == null)
            return -1; // this makes any null objects go to the bottom change this to 1 if you want
        // the top of the list
        CasefileDocumentsResponseEvent evt = (CasefileDocumentsResponseEvent) o;
        return evt.getEntryDate().compareTo(entryDate);
	}
}
