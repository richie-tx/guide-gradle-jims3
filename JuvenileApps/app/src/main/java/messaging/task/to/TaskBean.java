/*
 * Created on Mar 15, 2006
 *
 */
package messaging.task.to;

import java.util.Date;

import messaging.task.domintf.ITask;
import messaging.task.domintf.ITaskState;

/**
 * @author Jim Fisher
 *
 */
public class TaskBean implements ITask
{
	private Date acceptedDate;
	private String action;
	private String application;
	private Date closedDate;
	private Date createDate;
	private String createUserId;
	private Date dueDate;
	private String ownerId;
	private Integer severityLevel;
	private String sourceId;
	private String statusCode;
	private String subject;
	private Date submittedDate;
	private String taskId;
	private ITaskState taskState;
	private String taskSubject;
	private String topic;
	private String url;
	private String status;
	/**
	 * @return
	 */
	public Date getAcceptedDate()
	{
		return acceptedDate;
	}

	/**
	 * @return
	 */
	public Date getClosedDate()
	{
		return closedDate;
	}

	/**
	 * @return
	 */
	public Date getDueDate()
	{
		return dueDate;
	}

	/**
	 * @return
	 */
	public String getOwnerId()
	{
		return ownerId;
	}

	public Integer getSeverityLevel()
	{
		return this.severityLevel;
	}

	/**
	 * @return
	 */
	public String getSourceId()
	{
		return sourceId;
	}

	public String getStatusCode()
	{
		return this.statusCode;
	}

	/**
	 * @return
	 */
	public String getSubject()
	{
		return subject;
	}

	/**
	 * @return
	 */
	public Date getSubmittedDate()
	{
		return submittedDate;
	}

	public String getTaskId()
	{
		return this.taskId;
	}

	public ITaskState getTaskState()
	{
		return this.taskState;
	}

	/**
	 * @return
	 */
	public String getTaskSubject()
	{
		return taskSubject;
	}

	/**
	 * @return
	 */
	public String getTopic()
	{
		return topic;
	}

	/**
	 * @param date
	 */
	public void setAcceptedDate(Date date)
	{
		acceptedDate = date;
	}

	/**
	 * @param date
	 */
	public void setClosedDate(Date date)
	{
		closedDate = date;
	}

	/**
	 * @param date
	 */
	public void setDueDate(Date date)
	{
		dueDate = date;
	}

	/**
	 * @param integer
	 */
	public void setOwnerId(String string)
	{
		ownerId = string;
	}

	public void setSeverityLevel(Integer integer)
	{
		this.severityLevel = integer;
	}

	/**
	 * @param integer
	 */
	public void setSourceId(String string)
	{
		sourceId = string;
	}

	public void setStatusCode(String string)
	{
		this.statusCode = string;
	}

	/**
	 * @param string
	 */
	public void setSubject(String string)
	{
		subject = string;
	}

	/**
	 * @param date
	 */
	public void setSubmittedDate(Date date)
	{
		submittedDate = date;
	}

	public void setTaskId(String string)
	{
		this.taskId = string;
	}

	public void setTaskState(ITaskState taskState)
	{
		this.taskState = taskState;
	}

	/**
	 * @param string
	 */
	public void setTaskSubject(String string)
	{
		taskSubject = string;
	}

	/**
	 * @param string
	 */
	public void setTopic(String string)
	{
		topic = string;
	}

	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @return
	 */
	public String getApplication()
	{
		return application;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @param string
	 */
	public void setApplication(String string)
	{
		application = string;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("TOPIC: " + this.topic + "\n");
		buffer.append("APPLICATION: " + this.application + "\n");
		buffer.append("ACTION: " + this.action + "\n");
		return buffer.toString();
	}
	/**
	 * @return
	 */
	public Date getCreateDate()
	{
		return createDate;
	}

	/**
	 * @param date
	 */
	public void setCreateDate(Date date)
	{
		createDate = date;
	}

	/**
	 * @return
	 */
	public String getCreateUserId()
	{
		return createUserId;
	}

	/**
	 * @param string
	 */
	public void setCreateUserId(String string)
	{
		createUserId = string;
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
}
