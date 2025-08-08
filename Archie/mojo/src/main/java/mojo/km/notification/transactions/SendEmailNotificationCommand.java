/*
 * Created on Mar 9, 2006
 *
 */
package mojo.km.notification.transactions;

import messaging.notification.SendEmailNotificationEvent;
import messaging.notification.domintf.INotification;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

import java.util.List;

import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author Jim Fisher
 *
 */
public class SendEmailNotificationCommand implements ICommand
{
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		SendEmailNotificationEvent notificationEvent = (SendEmailNotificationEvent) event;

		INotification notification = notificationEvent.getNotification();

		SendEmailEvent sendEmailEvent = new SendEmailEvent();
		
		sendEmailEvent.setMessageId(notification.getNotificationId());
		
		this.setEmailAddresses(notificationEvent, sendEmailEvent);

		sendEmailEvent.setSubject(notification.getSubject());
		sendEmailEvent.setMessage(notification.getMessage());
		sendEmailEvent.setAttachment(notification.getAttachment());		

		MessageUtil.postRequest(sendEmailEvent);
	}

	public void setEmailAddresses(SendEmailNotificationEvent notificationEvent, SendEmailEvent sendEvent)
	{
		sendEvent.setFromAddress(notificationEvent.getSourceIdentity());

		List identities = notificationEvent.getDestinationIdentities();
		int len = identities.size();
		for(int i=0;i<len;i++)
		{			
			String toAddress = (String) identities.get(i);
			sendEvent.addToAddress(toAddress);
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
