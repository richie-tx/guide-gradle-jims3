/*
 * Created on Apr 4, 2006
 *
 */
package mojo.km.notification.exception;

import java.util.Iterator;
import java.util.Locale;

import javax.mail.internet.AddressException;

import mojo.km.exceptionhandling.ExceptionCallback;
import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.messaging.exception.MessagingException;
import mojo.km.naming.NotificationConstants;
import mojo.km.notification.Notification;
import mojo.km.notification.NotificationIdentityAddress;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * @author Jim Fisher
 *
 */
public class NotificationExceptionCallback implements ExceptionCallback
{

	/* (non-Javadoc)
	 * @see mojo.km.exceptionhandling.ExceptionCallback#execute(java.util.Locale, java.lang.Throwable)
	 */
	public void execute(Locale locale, Throwable t)
	{
		String reason;
		String diagnosis;
		String solution;

		if (t instanceof MessagingException)
		{
			MessagingException e = (MessagingException) t;
			if (e != null)
			{
				String msg = this.messageFactory(e);
				System.err.println(msg);
				this.updateMessage(e);
			}
		}
		else
		{
			ExceptionHandler.executeCallbacks(
				new RuntimeException("Invalid notification exception configuration: " + t.getClass().getName()));
		}
	}

	/**
	 * @param e
	 */
	private void updateMessage(MessagingException e)
	{		
		IHome home = new Home();
		Notification notification = (Notification) home.find(e.getMessageId(), Notification.class);
		notification.setStatusCode(NotificationConstants.ERROR);
		home.bind(notification);
		
		Iterator notifications = home.findAll("notificationId", e.getMessageId(), NotificationIdentityAddress.class);
		while(notifications.hasNext())
		{
			NotificationIdentityAddress notifIdent = (NotificationIdentityAddress) notifications.next();
			notifIdent.setStatus(NotificationConstants.ERROR);
			home.bind(notifIdent);
		}
	}

	private String messageFactory(MessagingException e)
	{
		StringBuffer buffer = new StringBuffer();

		if (e.getMessagingException() instanceof javax.mail.internet.AddressException)
		{
			javax.mail.internet.AddressException memberException = (AddressException) e.getMessagingException();
			buffer.append("Address exception (notificationId=" + e.getMessageId() + ")\n");
			buffer.append("Invalid notification address: " + memberException.getRef() + "\n");
			buffer.append(memberException.getMessage());
		}
		else if (e.getMessagingException() instanceof javax.mail.MessagingException)
		{
			javax.mail.MessagingException memberException = (javax.mail.MessagingException) e.getMessagingException();
			buffer.append("Address exception (notificationId=" + e.getMessageId() + ")\n");
			buffer.append(memberException.getMessage());
		}

		return buffer.toString();
	}

}
