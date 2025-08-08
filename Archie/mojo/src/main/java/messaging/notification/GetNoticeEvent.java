/*
 * Created on Feb 1, 2008
 *
 */
package messaging.notification;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 */
public class GetNoticeEvent extends RequestEvent
{
	private String notificationId;
	/**
	 * @return Returns the notificationId.
	 */
	public String getNotificationId() {
		return notificationId;
	}
	/**
	 * @param notificationId The notificationId to set.
	 */
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
}
