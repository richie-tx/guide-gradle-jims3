// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\notification\\SendOverdueInterviewNotificationEvent.java

package messaging.notification;

import java.util.Date;

import messaging.identityaddress.domintf.IAddressable;
import messaging.task.domintf.ICreateTask;
import messaging.task.domintf.ITaskState;
import messaging.task.to.TaskStateBean;
import mojo.km.messaging.PersistentEvent;

public class SendOverdueInterviewNotificationEvent extends PersistentEvent implements IAddressable, ICreateTask {
	public String juvenileNum;

	public String supervisionNumber;

	public String subject;

	public String identity;

	public String notificationMessage;

	public String referralId;

	public String supervisionType;

	public String noticeTopic;
	
	public String identityType;

	//	required for the task.

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

	private String officerId;

	/**
	 * @roseuid 46C1B3570087
	 */
	public SendOverdueInterviewNotificationEvent() {
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
	 * @return
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @return
	 */
	public String getTaskTopic() {
		return taskTopic;
	}

	/**
	 * @param date
	 */
	public void setDueDate(Date date) {
		dueDate = date;
	}

	/**
	 * @param string
	 */
	public void setTaskTopic(String string) {
		taskTopic = string;
	}

	public ITaskState getTaskState() {
		return this.taskState;
	}

	public void addTaskStateItem(String key, String item) {
		this.taskState.addItem(key, item);
	}

	public void addTaskStateItem(String key, Integer item) {
		this.taskState.addItem(key, item);
	}

	public void addTaskStateItem(String key, Date item) {
		this.taskState.addItem(key, item);
	}

	/**
	 * @return
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * @return
	 */
	public String getCreatorId() {
		return creatorId;
	}

	/**
	 * @param string
	 */
	public void setOwnerId(String string) {
		ownerId = string;
	}

	/**
	 * @param string
	 */
	public void setCreatorId(String string) {
		creatorId = string;
	}

	/**
	 * @return
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param string
	 */
	public void setStatusCode(String string) {
		statusCode = string;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return
	 */
	public Integer getSeverityLevel() {
		return severityLevel;
	}

	/**
	 * @param integer
	 */
	public void setSeverityLevel(Integer integer) {
		severityLevel = integer;
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
	 * @param taskState
	 *            The taskState to set.
	 */
	public void setTaskState(ITaskState taskState) {
		this.taskState = taskState;
	}

	/**
	 * @return Returns the officerId.
	 */
	public String getOfficerId() {
		return officerId;
	}

	/**
	 * @param officerId
	 *            The officerId to set.
	 */
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}

	/**
	 * @return Returns the noticeTopic.
	 */
	public String getNoticeTopic() {
		return noticeTopic;
	}

	/**
	 * @param noticeTopic
	 *            The noticeTopic to set.
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
