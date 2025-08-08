/*
 * Created on Mar 15, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.task.transactions;

import java.util.Collection;
import java.util.Iterator;

import pd.task.Task;
import pd.task.TaskJournal;
import pd.task.exception.TaskNotFoundException;
import messaging.task.GetTaskEvent;
import messaging.task.domintf.ITask;
import messaging.task.domintf.ITaskJournal;
import messaging.task.reply.TaskJournalResponseEvent;
import messaging.task.reply.TaskResponseEvent;
import messaging.task.to.TaskBean;
import messaging.task.to.TaskJournalBean;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author Jim Fisher
 *
 */
public class GetTaskCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetTaskEvent getEvent = (GetTaskEvent) event;

		Task task = Task.find(getEvent.getTaskId());
		ITask taskBean = new TaskBean();
		task.fill(taskBean, Task.WITH_STATE);

		if (task == null)
		{
			this.throwTaskNotFound(getEvent.getTaskId());
		}

		this.sendTask(taskBean);

		this.sendTaskJournalEntries(task);
	}

	public void throwTaskNotFound(String taskId)
	{
		// TODO Configure this exception
		throw new TaskNotFoundException("Task id: " + taskId + " was not found in the system.");
	}

	/**
	 * @param task
	 */
	private void sendTaskJournalEntries(Task task)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Collection taskEntries = task.getTaskJournals();
		Iterator i = taskEntries.iterator();
		while (i.hasNext())
		{
			TaskJournal journalEntry = (TaskJournal) i.next();
			ITaskJournal journalEntryBean = new TaskJournalBean();
			journalEntry.fill(journalEntryBean);
			TaskJournalResponseEvent journalResponse = new TaskJournalResponseEvent();
			journalResponse.setTaskJournal(journalEntryBean);
			dispatch.postEvent(journalResponse);
		}
	}

	/**
	 * @param taskBean
	 */
	private void sendTask(ITask taskBean)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		TaskResponseEvent taskResponse = new TaskResponseEvent();
		taskResponse.setTask(taskBean);
		dispatch.postEvent(taskResponse);
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub

	}

}
