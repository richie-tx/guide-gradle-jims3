/*
 * Created on Apr 4, 2006
 *
 */
package mojo.km.notification.transactions;

import messaging.notification.GetNoticeEvent;
import messaging.notification.domintf.INotification;
import messaging.notification.reply.NotificationResponseEvent;
import messaging.notification.to.NotificationBean;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.notification.Notification;

/**
 * @author Jim Fisher
 */
public class GetNoticeCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent anEvent) throws Exception
	{
		GetNoticeEvent event = (GetNoticeEvent) anEvent;

		Notification notif = Notification.find(event.getNotificationId());

		if(notif != null)
		{
			INotification notificationBean = new NotificationBean();
			notificationBean.setNotificationId(notif.getOID());
			notificationBean.setMessage(notif.getMessage());
			
			NotificationResponseEvent response = new NotificationResponseEvent();
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
