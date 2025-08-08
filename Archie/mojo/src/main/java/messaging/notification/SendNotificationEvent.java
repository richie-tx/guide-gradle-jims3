/*
 * Created on Mar 9, 2006
 *
 */
package messaging.notification;

import java.util.ArrayList;
import java.util.List;

import messaging.notification.domintf.INotification;
import messaging.notification.domintf.ISendNotification;
import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public abstract class SendNotificationEvent extends RequestEvent implements ISendNotification
{
	private String sourceIdentity;
	private List destinationIdentities;
	private INotification notification;
	
	public SendNotificationEvent()
	{
		super();
		this.destinationIdentities = new ArrayList();
	}
	
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
	public List getDestinationIdentities()
	{
		return destinationIdentities;
	}

	/**
	 * @return
	 */
	public String getSourceIdentity()
	{
		return sourceIdentity;
	}

	/**
	 * @param address
	 */
	public void addDestinationIdentity(String identity)
	{
		this.destinationIdentities.add(identity);
	}

	/**
	 * @param address
	 */
	public void setSourceIdentity(String identity)
	{
		sourceIdentity = identity;
	}

	/**
	 * @param collection
	 */
	public void setDestinationIdentities(List collection)
	{
		destinationIdentities = collection;
	}

}
