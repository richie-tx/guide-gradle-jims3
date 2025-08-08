/*
 * Created on Apr 6, 2006
 *
 */
package messaging.notification;

import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class UpdateNotificationStatusEvent extends RequestEvent
{
	private String notificationId;
	private String status;
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
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param string
	 */
	public void setNotificationId(String string)
	{
		notificationId = string;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
		status = string;
	}

}
