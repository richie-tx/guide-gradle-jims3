/*
 * Created on Dec 11, 2006
 *
 */
package pd.task.transactions;

import java.util.Iterator;

import pd.task.Task;
import messaging.task.GetNotClosedUserTasksEvent;
import messaging.task.GetUserTasksByStatusEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.identityaddress.IdentityAddress;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 */
public class GetNotClosedUserTasksCommand implements ICommand
{
	public void execute(IEvent event) throws Exception
	{
		GetNotClosedUserTasksEvent getTasksEvent = (GetNotClosedUserTasksEvent) event;

		// A user can not have a task unless they have an identity address
		IdentityAddress address = IdentityAddress.findByValue(getTasksEvent.getOwnerId());
		
		// No address, no task
		if (address != null)
		{
			getTasksEvent.setOwnerId(address.getOID().toString());	
			Iterator i = Task.findAll(getTasksEvent);

			IEvent responseEvent = TaskResponseFactory.taskResponseFactory(i, Task.WITH_STATE);

			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(responseEvent);
		}
	}

	public void onRegister(IEvent event)
	{
	}

	public void onUnregister(IEvent event)
	{
	}

	public void update(Object updateObject)
	{
	}
}
