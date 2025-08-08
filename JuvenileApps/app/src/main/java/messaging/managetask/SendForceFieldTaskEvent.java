// Source file:

package messaging.managetask;

import java.util.Date;
import mojo.km.messaging.PersistentEvent;

public class SendForceFieldTaskEvent extends PersistentEvent {
	
	private String assessmentId;

	private String subject;

	private String notificationMessage;	
	
	private String noticeTopic;
	
	private String taskId;

	private String taskTopic;

	private Date dueDate;

	private String statusCode;

	private Integer severityLevel;

	private String taskSubject;
	
	private String defendantId;
	
	private String staffPositionId;
	
	private String superviseeName;




	public String getStaffPositionId() {
		return staffPositionId;
	}

	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}

	public String getSuperviseeName() {
		return superviseeName;
	}

	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}

	/**
	 * @return the assessmentId
	 */
	public String getAssessmentId() {
		return assessmentId;
	}

	/**
	 * @param assessmentId the assessmentId to set
	 */
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	/**
	 * @roseuid 46C1B35601B0
	 */
	public SendForceFieldTaskEvent() {

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
	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	
	
}
