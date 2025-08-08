/*
 * Created on Mar 24, 2006
 *
 */
package pd.task.transactions;

import java.util.Iterator;

import pd.task.Task;

import messaging.task.GetUserTasksEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.identityaddress.IdentityAddress;
import mojo.km.messaging.IEvent;

/**
 * @author Jim Fisher
 *
 */
public class GetUserTasksCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent anEvent) throws Exception
	{
		GetUserTasksEvent getTasksEvent = (GetUserTasksEvent) anEvent;

		// A user can not have a task unless they have an identity address
		IdentityAddress address = IdentityAddress.findByValue(getTasksEvent.getOwnerId());

		// No address, no task
		if (address != null)
		{
			getTasksEvent.setOwnerId(address.getOID().toString());
			Iterator i = Task.findAll(getTasksEvent);

			IEvent responseEvent = TaskResponseFactory.taskResponseFactory(getTasksEvent, i, Task.WITH_STATE);

			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(responseEvent);
		}
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
