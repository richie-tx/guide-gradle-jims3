/*
 * Created on Apr 5, 2006
 *
 */
package messaging.notification;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class UpdateNotificationDestinationsEvent extends RequestEvent
{
	private String notificationId;
	private Map destinations;

	/**
	 * 
	 */
	public UpdateNotificationDestinationsEvent()
	{
		super();
		this.destinations = new HashMap();
	}

	/**
	 * @return
	 */
	public String getNotificationId()
	{
		return notificationId;
	}

	/**
	 * @return
	 */
	public Iterator getDestinationKeys()
	{
		return destinations.keySet().iterator();
	}

	/**
	 * @return
	 */
	public Object getDestination(String context)
	{
		return destinations.get(context);
	}

	/**
	 * @param string
	 */
	public void setNotificationId(String string)
	{
		notificationId = string;
	}

	/**
	 * @param collection
	 */
	public void addDestination(String context, IAddressable identityBean)
	{
		this.destinations.put(context, identityBean);
	}

}
