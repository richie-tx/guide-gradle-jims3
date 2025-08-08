// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\notification\\SendCaseplanNotificationEventEvent.java

package messaging.notification;

import java.util.Date;

import messaging.identityaddress.domintf.IAddressable;
import messaging.task.domintf.ICreateTask;
import messaging.task.domintf.ITaskState;
import messaging.task.to.TaskStateBean;
import mojo.km.messaging.PersistentEvent;

public class SendCaseplanNotificationEventEvent extends PersistentEvent implements IAddressable, ICreateTask {
	public String juvenileNum;

	public String supervisionNumber;

	public String subject;

	public String identity;

	public String notificationMessage;

	public String referralId;

	public String supervisionType;
	
	public String noticeTopic;
	
	public String identityType;

	//Testing to be deleted later
	public String caseplanId;

	public String alertType;

	private String taskId;

	private ITaskState taskState;

	private String taskTopic;

	private Date dueDate;

	private String creatorId;

	private String ownerId;

	private String statusCode;

	private Integer severityLevel;

	private String taskSubject;

	private String casefileId;

	private String submitAction;

	/**
	 * @roseuid 46C1B35601B0
	 */
	public SendCaseplanNotificationEventEvent() {
		this.taskState = new TaskStateBean();
	}

	/**
	 * Access method for the juvenileNum property.
	 * 
	 * @return the current value of the juvenileNum property
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * Sets the value of the juvenileNum property.
	 * 
	 * @param aJuvenileNum
	 *            the new value of the juvenileNum property
	 */
	public void setJuvenileNum(String aJuvenileNum) {
		juvenileNum = aJuvenileNum;
	}

	/**
	 * Access method for the supervisionNumber property.
	 * 
	 * @return the current value of the supervisionNumber property
	 */
	public String getSupervisionNumber() {
		return supervisionNumber;
	}

	/**
	 * Sets the value of the supervisionNumber property.
	 * 
	 * @param aSupervisionNumber
	 *            the new value of the supervisionNumber property
	 */
	public void setSupervisionNumber(String aSupervisionNumber) {
		supervisionNumber = aSupervisionNumber;
	}

	/**
	 * @return Returns the identity.
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * @param identity
	 *            The identity to set.
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * @return Returns the notificationMessage.
	 */
	public String getNotificationMessage() {
		return notificationMessage;
	}

	/**
	 * @param notificationMessage
	 *            The notificationMessage to set.
	 */
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	/**
	 * @return Returns the referralId.
	 */
	public String getReferralId() {
		return referralId;
	}

	/**
	 * @param referralId
	 *            The referralId to set.
	 */
	public void setReferralId(String referralId) {
		this.referralId = referralId;
	}

	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return Returns the supervisionType.
	 */
	public String getSupervisionType() {
		return supervisionType;
	}

	/**
	 * @param supervisionType
	 *            The supervisionType to set.
	 */
	public void setSupervisionType(String supervisionType) {
		this.supervisionType = supervisionType;
	}

	/**
	 * @return Returns the caseplanId.
	 */
	public String getCaseplanId() {
		return caseplanId;
	}

	/**
	 * @param caseplanId
	 *            The caseplanId to set.
	 */
	public void setCaseplanId(String caseplanId) {
		this.caseplanId = caseplanId;
	}

	/**
	 * @return Returns the alertType.
	 */
	public String getAlertType() {
		return alertType;
	}

	/**
	 * @param alertType
	 *            The alertType to set.
	 */
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	/**
	 * @return Returns the creatorId.
	 */
	public String getCreatorId() {
		return creatorId;
	}

	/**
	 * @param creatorId
	 *            The creatorId to set.
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * @return Returns the dueDate.
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            The dueDate to set.
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return Returns the ownerId.
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId
	 *            The ownerId to set.
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return Returns the severityLevel.
	 */
	public Integer getSeverityLevel() {
		return severityLevel;
	}

	/**
	 * @param severityLevel
	 *            The severityLevel to set.
	 */
	public void setSeverityLevel(Integer severityLevel) {
		this.severityLevel = severityLevel;
	}

	/**
	 * @return Returns the statusCode.
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            The statusCode to set.
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return Returns the taskId.
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            The taskId to set.
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return Returns the taskState.
	 */
	public ITaskState getTaskState() {
		return this.taskState;
	}

	/**
	 * @param taskState
	 *            The taskState to set.
	 */
	public void setTaskState(ITaskState taskState) {
		this.taskState = taskState;
	}

	/**
	 * @return Returns the taskSubject.
	 */
	public String getTaskSubject() {
		return taskSubject;
	}

	/**
	 * @param taskSubject
	 *            The taskSubject to set.
	 */
	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}

	/**
	 * @return Returns the taskTopic.
	 */
	public String getTaskTopic() {
		return taskTopic;
	}

	/**
	 * @param taskTopic
	 *            The taskTopic to set.
	 */
	public void setTaskTopic(String taskTopic) {
		this.taskTopic = taskTopic;
	}

	public void addTaskStateItem(String key, String item) {
		this.taskState.addItem(key, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.task.domintf.ICreateTask#addTaskStateItem(java.lang.String,
	 *      java.lang.Integer)
	 */
	public void addTaskStateItem(String key, Integer item) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.task.domintf.ICreateTask#addTaskStateItem(java.lang.String,
	 *      java.util.Date)
	 */
	public void addTaskStateItem(String key, Date item) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}

	/**
	 * @param casefileId
	 *            The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}

	/**
	 * @return Returns the submitAction.
	 */
	public String getSubmitAction() {
		return submitAction;
	}

	/**
	 * @param submitAction
	 *            The submitAction to set.
	 */
	public void setSubmitAction(String submitAction) {
		this.submitAction = submitAction;
	}
	/**
	 * @return Returns the noticeTopic.
	 */
	public String getNoticeTopic() {
		return noticeTopic;
	}
	/**
	 * @param noticeTopic The noticeTopic to set.
	 */
	public void setNoticeTopic(String noticeTopic) {
		this.noticeTopic = noticeTopic;
	}
	/**
	 * @return Returns the identityType.
	 */
	public String getIdentityType() {
		return identityType;
	}
	/**
	 * @param identityType The identityType to set.
	 */
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
}
