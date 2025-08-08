/*
 * Created on Mar 14, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.task.transactions;

import pd.supervision.managetask.PDTaskHelper;
import pd.task.Task;
import messaging.task.CreateTaskEvent;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author Jim Fisher
 *
 */
public class CreateTaskCommand implements ICommand
{
	
	/**
	 * Required fields: topic, creatorId
	 * Optional fields: ownerId, taskState, dueDate
	 */
	public void execute(IEvent anEvent) throws Exception
	{
		CreateTaskEvent event = (CreateTaskEvent) anEvent;


		//DAG removing command chaining.
		Task taskNew = PDTaskHelper.createTask(event);
		if (taskNew != null){
			this.sendTask(taskNew);
		}
		
	}

	public void onRegister(IEvent event)
	{
	}
	
	 private void sendTask(Task task)
	    {
	   
	 	TaskResponseEvent taskEvent = new TaskResponseEvent();		
		taskEvent.setTaskId(task.getOID());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(taskEvent);
		
	    }

	public void onUnregister(IEvent event)
	{
	}

	public void update(Object updateObject)
	{
	}
}
