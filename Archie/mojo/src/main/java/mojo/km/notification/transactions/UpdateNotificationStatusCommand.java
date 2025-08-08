/*
 * Created on Apr 6, 2006
 *
 */
package mojo.km.notification.transactions;

import java.util.Iterator;

import messaging.notification.UpdateNotificationStatusEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.notification.Notification;
import mojo.km.notification.NotificationIdentityAddress;

/**
 * @author Jim Fisher
 *
 */
public class UpdateNotificationStatusCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent anEvent) throws Exception
	{
		UpdateNotificationStatusEvent event = (UpdateNotificationStatusEvent) anEvent;

		Notification notif = Notification.find(event.getNotificationId());

		if (notif == null)
		{
			this.throwNotificationNotFound(event.getNotificationId());
		}
		else
		{
			notif.setStatusCode(event.getStatus());
			Iterator i = notif.getIdentities().iterator();
			while (i.hasNext())
			{
				NotificationIdentityAddress identityAddress = (NotificationIdentityAddress) i.next();
				identityAddress.setStatus(event.getStatus());
			}
		}
	}

	/**
	 * @param string
	 */
	private void throwNotificationNotFound(String string)
	{
		String msg = "Notification (id="+string+") not found.";
		throw new IllegalArgumentException(msg);		
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
