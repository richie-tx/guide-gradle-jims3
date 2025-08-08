package messaging.productionsupport.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author rcarter
 */
public class ProductionSupportEventTaskResponseEvent extends ResponseEvent
{
	
	private String eventTaskId;
	private Short executed;
	private Date nextNoticeDate;
	private Date firstNoticeDate;
	private String eventName;
	private String scheduleName;
	private String taskName;
	private String taskStatus;
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;

	
	/**
	 * @return the executed
	 */
	public Short getExecuted() {
		return executed;
	}

	/**
	 * @param executed the executed to set
	 */
	public void setExecuted(Short executed) {
		this.executed = executed;
	}

	

	/**
	 * @return the nextNoticeDate
	 */
	public Date getNextNoticeDate() {
		return nextNoticeDate;
	}

	/**
	 * @param nextNoticeDate the nextNoticeDate to set
	 */
	public void setNextNoticeDate(Date nextNoticeDate) {
		this.nextNoticeDate = nextNoticeDate;
	}

	/**
	 * @return the firstNoticeDate
	 */
	public Date getFirstNoticeDate() {
		return firstNoticeDate;
	}

	/**
	 * @param firstNoticeDate the firstNoticeDate to set
	 */
	public void setFirstNoticeDate(Date firstNoticeDate) {
		this.firstNoticeDate = firstNoticeDate;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the scheduleName
	 */
	public String getScheduleName() {
		return scheduleName;
	}

	/**
	 * @param scheduleName the scheduleName to set
	 */
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the taskStatus
	 */
	public String getTaskStatus() {
		return taskStatus;
	}

	/**
	 * @param taskStatus the taskStatus to set
	 */
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	/**
	 * @return the eventTaskId
	 */
	public String getEventTaskId() {
		return eventTaskId;
	}

	/**
	 * @param eventTaskId the eventTaskId to set
	 */
	public void setEventTaskId(String eventTaskId) {
		this.eventTaskId = eventTaskId;
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



