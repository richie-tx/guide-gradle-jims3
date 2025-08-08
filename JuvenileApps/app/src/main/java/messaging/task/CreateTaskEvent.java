/*
 * Created on Mar 14, 2006
 *
 */
package messaging.task;

import java.util.Date;

import messaging.task.domintf.ICreateTask;
import messaging.task.domintf.ITaskState;
import messaging.task.to.TaskStateBean;
import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class CreateTaskEvent extends RequestEvent implements ICreateTask
{
	private String taskId;
	private ITaskState taskState;
	private String taskTopic;
	private Date dueDate;
	private String creatorId;
	private String ownerId;
	private String statusCode;
	private Integer severityLevel;
	private String taskSubject;

	public CreateTaskEvent()
	{
		this.taskState = new TaskStateBean();
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
	public String getTaskTopic()
	{
		return taskTopic;
	}

	/**
	 * @param date
	 */
	public void setDueDate(Date date)
	{
		dueDate = date;
	}

	/**
	 * @param string
	 */
	public void setTaskTopic(String string)
	{
		taskTopic = string;
	}

	public ITaskState getTaskState()
	{
		return this.taskState;
	}

	public void addTaskStateItem(String key, String item)
	{
		this.taskState.addItem(key, item);
	}

	public void addTaskStateItem(String key, Integer item)
	{
		this.taskState.addItem(key, item);
	}

	public void addTaskStateItem(String key, Date item)
	{
		this.taskState.addItem(key, item);
	}

	/**
	 * @return
	 */
	public String getOwnerId()
	{
		return ownerId;
	}

	/**
	 * @return
	 */
	public String getCreatorId()
	{
		return creatorId;
	}

	/**
	 * @param string
	 */
	public void setOwnerId(String string)
	{
		ownerId = string;
	}

	/**
	 * @param string
	 */
	public void setCreatorId(String string)
	{
		creatorId = string;
	}

	/**
	 * @return
	 */
	public String getStatusCode()
	{
		return statusCode;
	}

	/**
	 * @param string
	 */
	public void setStatusCode(String string)
	{
		statusCode = string;
	}

	public String getTaskId()
	{
		return this.taskId;
	}

	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}

	/**
	 * @return
	 */
	public Integer getSeverityLevel()
	{
		return severityLevel;
	}

	/**
	 * @param integer
	 */
	public void setSeverityLevel(Integer integer)
	{
		severityLevel = integer;
	}

	/**
	 * @return Returns the taskSubject.
	 */
	public String getTaskSubject() {
		return taskSubject;
	}
	/**
	 * @param taskSubject The taskSubject to set.
	 */
	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}
}
