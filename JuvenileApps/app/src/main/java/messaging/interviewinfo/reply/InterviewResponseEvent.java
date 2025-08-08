package messaging.interviewinfo.reply;

import java.util.Date;

import messaging.interviewinfo.domintf.IInterviewSummary;
import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class InterviewResponseEvent extends ResponseEvent implements IInterviewSummary, Comparable
{
	private String casefileId;
	private String interviewId;
	private Date interviewDate;
	private String interviewTypeId;
	private String addressId;
	private String locationDescription;
	private String interviewStatusCd;
	private String interviewStatusDescription;
	private String calEventId;
	private String juvLocationUnitId;
	
	//added for Casefile Closing Interview Notes List - ugopinath
	private String summaryNotes;
	private String progressReport;
	private String userComments;
	
	//added when using InterviewEventView entity
	private String attendanceStatusCd;
	private String otherInventoryRecords;
	private Boolean customAddressValid;
	
	private int taskCount;
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJims2User;
	private String updateJims2User;
	
	//added for Production Support
	private String createUserID;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	

	/**
	 * @return
	 */
	public Date getInterviewDate()
	{
		return interviewDate;
	}

	/**
	 * @return
	 */
	public String getInterviewId()
	{
		return interviewId;
	}

	/**
	 * @return
	 */
	public String getInterviewTypeId()
	{
		return interviewTypeId;
	}

	/**
	 * @return
	 */
	public String getLocationDescription()
	{
		return locationDescription;
	}


	/**
	 * @param date
	 */
	public void setInterviewDate(Date date)
	{
		interviewDate = date;
	}

	/**
	 * @param string
	 */
	public void setInterviewId(String string)
	{
		interviewId = string;
	}

	/**
	 * @param string
	 */
	public void setInterviewTypeId(String string)
	{
		interviewTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setLocationDescription(String string)
	{
		locationDescription = string;
	}

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


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) throws ClassCastException
	{
		if(obj==null)
			return -1;
		InterviewResponseEvent evt = (InterviewResponseEvent) obj;
		
		if(evt.getInterviewDate() == null)
			return 1;
		if(this.getInterviewDate() == null)
			return -1;
		
		return evt.getInterviewDate().compareTo(this.getInterviewDate());
		
	}		

	/**
	 * @return Returns the interviewStatusCd.
	 */
	public String getInterviewStatusCd() {
		return interviewStatusCd;
	}
	/**
	 * @param interviewStatusCd The interviewStatusCd to set.
	 */
	public void setInterviewStatusCd(String interviewStatusCd) {
		this.interviewStatusCd = interviewStatusCd;
	}
	/**
	 * @return Returns the interviewStatusDescription.
	 */
	public String getInterviewStatusDescription() {
		return interviewStatusDescription;
	}
	/**
	 * @param interviewStatusDescription The interviewStatusDescription to set.
	 */
	public void setInterviewStatusDescription(String interviewStatusDescription) {
		this.interviewStatusDescription = interviewStatusDescription;
	}
	/**
	 * @return Returns the summaryNotes.
	 */
	public String getSummaryNotes() {
		return summaryNotes;
	}
	/**
	 * @param summaryNotes The summaryNotes to set.
	 */
	public void setSummaryNotes(String summaryNotes) {
		this.summaryNotes = summaryNotes;
	}
	public String getCalEventId() {
		return calEventId;
	}
	public void setCalEventId(String calEventId) {
		this.calEventId = calEventId;
	}
	public String getAttendanceStatusCd() {
		return attendanceStatusCd;
	}
	public void setAttendanceStatusCd(String attendanceStatusCd) {
		this.attendanceStatusCd = attendanceStatusCd;
	}	
	
	public int getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

	public String getProgressReport() {
		return progressReport;
	}

	public void setProgressReport(String progressReport) {
		this.progressReport = progressReport;
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateJims2User() {
		return createJims2User;
	}

	public void setCreateJims2User(String createJims2User) {
		this.createJims2User = createJims2User;
	}

	public String getUpdateJims2User() {
		return updateJims2User;
	}

	public void setUpdateJims2User(String updateJims2User) {
		this.updateJims2User = updateJims2User;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getJuvLocationUnitId() {
		return juvLocationUnitId;
	}

	public void setJuvLocationUnitId(String juvLocationUnitId) {
		this.juvLocationUnitId = juvLocationUnitId;
	}

	public String getOtherInventoryRecords() {
		return otherInventoryRecords;
	}

	public void setOtherInventoryRecords(String otherInventoryRecords) {
		this.otherInventoryRecords = otherInventoryRecords;
	}

	public Boolean getCustomAddressValid() {
		return customAddressValid;
	}

	public void setCustomAddressValid(Boolean customAddressValid) {
		this.customAddressValid = customAddressValid;
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
