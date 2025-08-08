/*
 * Created on Mar 14, 2006
 *
 */
package messaging.task.reply;

import messaging.task.domintf.ITask;
import mojo.km.messaging.ResponseEvent;

/**
 * @author Jim Fisher
 *
 */
public class TaskResponseEvent extends ResponseEvent
{
	private ITask task;
	private String taskId;
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
	 * @return
	 */
	public ITask getTask()
	{
		return task;
	}

	/**
	 * @param task
	 */
	public void setTask(ITask task)
	{
		this.task = task;
	}

}
