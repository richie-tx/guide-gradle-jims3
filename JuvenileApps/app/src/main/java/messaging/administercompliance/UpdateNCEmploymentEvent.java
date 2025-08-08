//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\UpdateNCResponseEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateNCEmploymentEvent extends RequestEvent 
{
    private String employerName;
    private String jobTitle;
    private String statusId;
    private String seqNum;
    private String employmentId;
    private boolean manualAdded;
	
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
	 * 
	 * @return
	 */
	public String getSeqNum() {
		return seqNum;
	}
	
	/**
	 * 
	 * @param seqNum
	 */
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	
}
