/*
 * Created on Nov 3, 2006
 */
package messaging.caseplan.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dapte
 *
 */
public class CaseplanListResponseEvent extends ResponseEvent implements Comparable{
	
	private String caseplanID;
	private String supervisionNumber;
	private Date createDate;
	private Date reviewDate;
	
	private String statusId;
	private String status;
	private Object report;  //byte array byte[]
	
	

	/**
	 * @return Returns the caseplanID.
	 */
	public String getCaseplanID() {
		return caseplanID;
	}
	/**
	 * @param caseplanID The caseplanID to set.
	 */
	public void setCaseplanID(String caseplanID) {
		this.caseplanID = caseplanID;
	}
	/**
	 * @return Returns the createDate.
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate The createDate to set.
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return Returns the reviewDate.
	 */
	public Date getReviewDate() {
		return reviewDate;
	}
	/**
	 * @param reviewDate The reviewDate to set.
	 */
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	/**
	 * @return Returns the supervisionNumber.
	 */
	public String getSupervisionNumber() {
		return supervisionNumber;
	}
	/**
	 * @param supervisionNumber The supervisionNumber to set.
	 */
	public void setSupervisionNumber(String supervisionNumber) {
		this.supervisionNumber = supervisionNumber;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the report.
	 */
	public Object getReport() {
		return report;
	}
	/**
	 * @param report The report to set.
	 */
	public void setReport(Object report) {
		this.report = report;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if(o == null)
			return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		if(this.createDate==null)
			return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
		CaseplanListResponseEvent evt = (CaseplanListResponseEvent)o;
		return evt.getCreateDate().compareTo(createDate);
	}
}
