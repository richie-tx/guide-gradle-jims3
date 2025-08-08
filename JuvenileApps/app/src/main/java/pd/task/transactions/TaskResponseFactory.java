/*
 * Created on Mar 23, 2006
 *
 */
package pd.task.transactions;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import pd.task.Task;

import messaging.task.domintf.IGetTasks;
import messaging.task.domintf.ITask;
import messaging.task.domintf.ITaskState;
import messaging.task.reply.TaskResponseEvent;
import messaging.task.to.TaskBean;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;

/**
 * @author Jim Fisher
 *
 */
public class TaskResponseFactory
{
	public TaskResponseFactory()
	{
	}

	static IEvent taskResponseFactory(Iterator tasks, boolean withState)
	{
		CompositeResponse composite = new CompositeResponse();
		while (tasks.hasNext())
		{
			Task task = (Task) tasks.next();
			ITask taskBean = new TaskBean();

			task.fill(taskBean, withState);

			TaskResponseFactory.addResponse(composite, taskBean);
		}
		return composite;
	}

	/** 
	 * @param getTasks
	 * @param tasks
	 * @param withState
	 * @return
	 */
	static IEvent taskResponseFactory(IGetTasks getTasks, Iterator tasks, boolean withState)
	{
		Map criteria = getTasks.getCriterion();
		boolean hasCriteria = (criteria.isEmpty() == false);

		CompositeResponse composite = new CompositeResponse();
		while (tasks.hasNext())
		{
			Task task = (Task) tasks.next();
			ITask taskBean = new TaskBean();

			task.fill(taskBean, withState);

			ITaskState taskState = taskBean.getTaskState();

			if (hasCriteria == true && taskState != null)
			{
				// TODO Convert to single if-block once logic is confirmed
				boolean passStatusCheck = checkStatuses(getTasks, taskBean);
				if (passStatusCheck == true)
				{
					boolean passCriteriaCheck = checkCriteria(criteria, taskState);
					if (passCriteriaCheck == true)
					{
						TaskResponseFactory.addResponse(composite, taskBean);
					}
				}
			}
			else
			{
				TaskResponseFactory.addResponse(composite, taskBean);
			}
		}
		return composite;
	}

	/**
	 * @param getTasks
	 * @param taskBean
	 * @return
	 */
	private static boolean checkStatuses(IGetTasks getTasks, ITask taskBean)
	{
		boolean pass = false;

		String taskStatus = taskBean.getStatusCode();

		String[] statusCriteria = getTasks.getStatusIds();

		if (statusCriteria == null)
		{
			pass = true;
		}
		else
		{
			for (int i = 0; i < statusCriteria.length; i++)
			{
				if (taskStatus.equals(statusCriteria[i]))
				{
					pass = true;
				}
			}
		}

		return pass;
	}

	/**
	 * @param criteria
	 * @param taskState
	 * @return
	 */
	private static boolean checkCriteria(Map criteria, ITaskState taskState)
	{
		Set keys = criteria.keySet();
		Iterator i = keys.iterator();

		// pessimistic logic, assume the task does not match criteria
		boolean pass = false;
		boolean done = false;

		while (i.hasNext() && done == false)
		{
			Object keyObj = i.next();
			Object criterionObj = criteria.get(keyObj);

			Object taskStateItem = taskState.get(keyObj);

			if (taskStateItem == null && criterionObj == null)
			{
				pass = true;
				done = true;
			}
			else if (taskStateItem != null && criterionObj != null)
			{
				if (taskStateItem.toString().equals(criterionObj.toString()))
				{
					pass = true;
				}
				else
				{
					pass = false;
					done = true;
				}
			}

		}

		return pass;
	}

	static private void addResponse(CompositeResponse response, ITask task)
	{
		TaskResponseEvent taskEvent = new TaskResponseEvent();
		taskEvent.setTask(task);
		response.addResponse(taskEvent);
	}
}
