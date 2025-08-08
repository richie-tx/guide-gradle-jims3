/*
 * Created on Mar 9, 2006
 *
 */
package mojo.km.notification;

/**
 * @author Jim Fisher
 * @stereotype control
 */
public class NotificationController
{

	/**
	 * @stereotype design
	 */
	public void createNotification()
	{
	}

	/**
	 * @stereotype design
	 */
	public void getUserNotices(String destinationIdentityId)
	{
	}

	/**
	 * @stereotype design
	 */
	public void sendNotification()
	{
	}

	/**
	 * @stereotype design
	 */
	public void sendEmailNotification()
	{
	}

	/**
	 * @stereotype design
	 */
	public void sendNoticeNotification()
	{
	}

	/**
	 * @stereotype design
	 */
	public static void pullNotifications()
	{

	}

	/**
	 * @stereotype design
	 */
	public static void updateNotificationDef(String notificationTopic, String defaultSeverity)
	{
	}

	/**
	 * @stereotype design
	 */
	public static void updateNotificationDestinations(String notificationId)
	{
	}
	
	/**
	 * @stereotype design
	 */
	public static void updateNotificationStatus(String notificationId, String status)
	{
	}	
	
	/**
	 * @stereotype design
	 */
	public void getNotice(String notificationId)
	{
	}	
}
