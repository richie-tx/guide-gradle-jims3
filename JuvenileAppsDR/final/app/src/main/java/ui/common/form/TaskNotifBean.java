/*
 * Created on Oct 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.common.form;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TaskNotifBean {
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	
	boolean isTask=false;
	String subject;
	String message;
	String statusId;
	String status;
	String typeId;
	String type;
	String categoryId;
	String category;
	String ownerUserId;
	String taskId;
	String notificationId;
	String url;
	String createDate;
	String sentDate;

	/**
	 * @return Returns the category.
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category The category to set.
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return Returns the categoryId.
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId The categoryId to set.
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return Returns the isTask.
	 */
	public boolean isTask() {
		return isTask;
	}
	/**
	 * @param isTask The isTask to set.
	 */
	public void setTask(boolean isTask) {
		this.isTask = isTask;
	}
	/**
	 * @return Returns the notificationId.
	 */
	public String getNotificationId() {
		return notificationId;
	}
	/**
	 * @param notificationId The notificationId to set.
	 */
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	/**
	 * @return Returns the ownerUserId.
	 */
	public String getOwnerUserId() {
		return ownerUserId;
	}
	/**
	 * @param ownerUserId The ownerUserId to set.
	 */
	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
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
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return Returns the taskId.
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId The taskId to set.
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the typeId.
	 */
	public String getTypeId() {
		return typeId;
	}
	/**
	 * @param typeId The typeId to set.
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	/**
	 * @return Returns the createDate.
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate The createDate to set.
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return Returns the sentDate.
	 */
	public String getSentDate() {
		return sentDate;
	}
	/**
	 * @param sentDate The sentDate to set.
	 */
	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}
	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
