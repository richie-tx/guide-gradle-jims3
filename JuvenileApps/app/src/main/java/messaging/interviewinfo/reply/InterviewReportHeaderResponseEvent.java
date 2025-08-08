package messaging.interviewinfo.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class InterviewReportHeaderResponseEvent extends ResponseEvent implements Comparable
{
	private String reportId;
	private String casefileId;
	private Date creationDate;
	private String reportType;

	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	/**
	 * @return
	 */
	public String getCasefileId()
	{
		return casefileId;
	}

	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}


	/**
	 * @return Returns the creationDate.
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate The creationDate to set.
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return Returns the reportType.
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType The reportType to set.
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return Returns the reportId.
	 */
	public String getReportId() {
		return reportId;
	}
	/**
	 * @param reportId The reportId to set.
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

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		int result = 0;
		
		InterviewReportHeaderResponseEvent input = (InterviewReportHeaderResponseEvent) o;
	
		Date eventDateA = this.getCreationDate();
		Date eventDateB = input.getCreationDate();

		if (eventDateA == null && eventDateB == null)
		{
			result = 0;
		}
		else if (eventDateA == null)
		{
			result = -1;
		}
		else if (eventDateB == null)
		{
			result = 1;
		}
		else
		{
			result = eventDateA.compareTo(eventDateB);
		}
		
		result = -result;
		
		return result;
		
	}
}
