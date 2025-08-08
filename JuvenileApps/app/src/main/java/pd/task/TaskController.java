/*
 * Created on Mar 14, 2006
 *
 */
package pd.task;

import java.util.Date;

import messaging.task.to.TaskStateBean;

/**
 * @author Jim Fisher
 * @stereotype control
 */
public class TaskController
{
	/**
	 * @stereotype design
	 */
	public void createTaskJournalEntry(String journalEntry)
	{
	}		
	
	/**
	 * @stereotype design
	 */
	public void getTask(Integer taskId)
	{
	}

	/**
	 * @stereotype design
	 */
	public void getUserTasksByStatus(String statusCode)
	{
	}
	
	/**
	 * @stereotype design
	 */
	public void getNotClosedUserTasks(String statusCode)
	{
	}

	/**
	 * @stereotype design
	 */
	public void createTask(String topic, String creatorId, String ownerId, TaskStateBean taskState, Date dueDate)
	{
	}

	/**
	 * @stereotype design
	 */
	public void transferTask(String taskId, String ownerId)
	{
	}

	/**
	 * @stereotype design
	 */
	public void updateTaskStatus(String taskId, String statusCode)
	{
	}

	/**
	 * @stereotype design
	 */
	public void getUnassignedTasks()
	{
	}
	
	/**
	 * @stereotype design
	 */
	public void getUserTasks()
	{
	}
	
	/**
	 * @stereotype design
	 */
	public void getNextTaskAction()
	{
	}	
}
