/*
 * Created on Apr 4, 2006
 *
 */
package messaging.notification;

import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class GetUserNoticesEvent extends RequestEvent
{
	private String destinationIdentityId;
	/**
	 * @return
	 */
	public String getDestinationIdentityId()
	{
		return destinationIdentityId;
	}

	/**
	 * @param string
	 */
	public void setDestinationIdentityId(String string)
	{
		destinationIdentityId = string;
	}

}
