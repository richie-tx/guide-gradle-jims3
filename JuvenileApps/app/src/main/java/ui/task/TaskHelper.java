/*
 * Created on Mar 15, 2006
 *
 */
package ui.task;

import java.util.List;

import naming.TaskControllerServiceNames;

import messaging.task.GetNotClosedUserTasksEvent;
import messaging.task.GetUnassignedTasksEvent;
import messaging.task.GetUserTasksByStatusEvent;
import messaging.task.UpdateTaskStatusEvent;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;

/**
 * @author Jim Fisher
 *
 */
public class TaskHelper
{
	static public List getUserTasksByStatus(String ownerId, String status)
	{
		GetUserTasksByStatusEvent getTasks =
			(GetUserTasksByStatusEvent) EventFactory.getInstance(TaskControllerServiceNames.GETUSERTASKSBYSTATUS);
		getTasks.setOwnerId(ownerId);
		getTasks.setStatusCode(status);
		List tasks = MessageUtil.postRequestListFilter(getTasks, TaskResponseEvent.class);
		return tasks;
	}
	
	static public List getNotClosedUserTasks(String ownerId, String status)
	{
		GetNotClosedUserTasksEvent getTasks =
			(GetNotClosedUserTasksEvent) EventFactory.getInstance(TaskControllerServiceNames.GETNOTCLOSEDUSERTASKS);
		getTasks.setOwnerId(ownerId);
		getTasks.setStatusCode(status);
		List tasks = MessageUtil.postRequestListFilter(getTasks, TaskResponseEvent.class);
		return tasks;
	}

	static public List getUnassignedTasks()
	{
		GetUnassignedTasksEvent getTasks =
			(GetUnassignedTasksEvent) EventFactory.getInstance(TaskControllerServiceNames.GETUNASSIGNEDTASKS);
		List tasks = MessageUtil.postRequestListFilter(getTasks, TaskResponseEvent.class);
		return tasks;
	}

	static public void updateTaskStatus(String statusCode)
	{
		UpdateTaskStatusEvent updateTaskEvent =
			(UpdateTaskStatusEvent) EventFactory.getInstance(TaskControllerServiceNames.UPDATETASKSTATUS);
		updateTaskEvent.setStatusCode(statusCode);
		MessageUtil.postRequest(updateTaskEvent);
	}	
}
