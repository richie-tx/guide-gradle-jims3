/*
 * Created on Apr 4, 2006
 *
 */
package messaging.notification.reply;

import messaging.notification.domintf.INotification;
import mojo.km.messaging.ResponseEvent;

/**
 * @author Jim Fisher
 *
 */
public class NotificationResponseEvent extends ResponseEvent
{
	private String sourceIdentity;
	private String destinationIdentity;
	private INotification notification;
	
	/**
	 * @return
	 */
	public INotification getNotification()
	{
		return notification;
	}

	/**
	 * @param notification
	 */
	public void setNotification(INotification notification)
	{
		this.notification = notification;
	}

	/**
	 * @return
	 */
	public String getSourceIdentity()
	{
		return sourceIdentity;
	}

	/**
	 * @param string
	 */
	public void setSourceIdentity(String string)
	{
		sourceIdentity = string;
	}

	/**
	 * @return
	 */
	public String getDestinationIdentity()
	{
		return destinationIdentity;
	}

	/**
	 * @param string
	 */
	public void setDestinationIdentity(String string)
	{
		destinationIdentity = string;
	}

}
