/*
 * Created on Apr 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administercompliance.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NCEmploymentResponseEvent extends ResponseEvent{
	private String employerName;
	private String jobTitle;
	private String statusId;
	private String employmentId;
	private String ncResponseId;
// values needed for UI display	
	private String startDateStr;
	private String statusDesc;
	private boolean manualAdded;
	private String seqNum;

	/**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	/**
	 * @return Returns the employerName.
	 */
	public String getEmployerName() {
		return employerName;
	}
	/**
	 * @param employerName The employerName to set.
	 */
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	/**
	 * @return Returns the employmentId.
	 */
	public String getEmploymentId() {
		return employmentId;
	}
	/**
	 * @param employmentId The employmentId to set.
	 */
	public void setEmploymentId(String employmentId) {
		this.employmentId = employmentId;
	}
	/**
	 * @return Returns the jobTitle.
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle The jobTitle to set.
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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
	 * @param startDateStr The startDateStr to set.
	 */
	public String getStartDateStr() {
		return startDateStr;
	}
	/**
	 * @param startDateStr The startDateStr to set.
	 */
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	/**
	 * @param statusDesc The statusDesc to set.
	 */
	public String getStatusDesc() {
		return statusDesc;
	}
	/**
	 * @param statusDesc The statusDesc to set.
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	/**
	 * @return the manualAdded
	 */
	public boolean isManualAdded() {
		return manualAdded;
	}
	/**
	 * @param manualAdded the manualAdded to set
	 */
	public void setManualAdded(boolean manualAdded) {
		this.manualAdded = manualAdded;
	}
	public String getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	
}
