/*
 * Created on Apr 4, 2006
 *
 */
package mojo.km.notification.transactions;

import java.util.Iterator;

import messaging.notification.GetUserNoticesEvent;
import messaging.notification.domintf.INotification;
import messaging.notification.reply.NotificationResponseEvent;
import messaging.notification.to.NotificationBean;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.notification.Notification;
import mojo.km.notification.PendingNotification;

/**
 * @author Jim Fisher
 */
public class GetUserNoticesCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent anEvent) throws Exception
	{
		GetUserNoticesEvent event = (GetUserNoticesEvent) anEvent;

		Iterator i = Notification.findUserNotices(anEvent);

		while (i.hasNext())
		{
			PendingNotification notification = (PendingNotification) i.next();			

			INotification notificationBean = new NotificationBean();
			notification.fill(notificationBean);
			
			NotificationResponseEvent response = new NotificationResponseEvent();
			response.setSourceIdentity(notification.getSourceIdentityValue());
			response.setDestinationIdentity(notification.getDestinationIdentityValue());
			response.setNotification(notificationBean);
			
			EventManager.getSharedInstance(EventManager.REPLY).postEvent(response);			
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
