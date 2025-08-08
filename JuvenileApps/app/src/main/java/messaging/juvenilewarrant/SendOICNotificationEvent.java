//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\SendOICNotificationEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.PersistentEvent;

public class SendOICNotificationEvent extends PersistentEvent
{
	public String warrantNum;
	public String emailFrom;
	public String emailTo;
	public int notificationType;
	public String taskName;

	/**
	 * @roseuid 41BDFE8B02CC
	 */
	public SendOICNotificationEvent()
	{

	}

	/**
	 * Access method for the warrantNum property.
	 * 
	 * @return   the current value of the warrantNum property
	 */
	public String getWarrantNum()
	{
		return warrantNum;
	}

	/**
	 * Sets the value of the warrantNum property.
	 * 
	 * @param aWarrantNum the new value of the warrantNum property
	 */
	public void setWarrantNum(String aWarrantNum)
	{
		warrantNum = aWarrantNum;
	}

	/**
	 * Access method for the emailFrom property.
	 * 
	 * @return   the current value of the emailFrom property
	 */
	public String getEmailFrom()
	{
		return emailFrom;
	}

	/**
	 * Sets the value of the emailFrom property.
	 * 
	 * @param aEmailFrom the new value of the emailFrom property
	 */
	public void setEmailFrom(String aEmailFrom)
	{
		emailFrom = aEmailFrom;
	}

	/**
	 * Access method for the emailTo property.
	 * 
	 * @return   the current value of the emailTo property
	 */
	public String getEmailTo()
	{
		return emailTo;
	}

	/**
	 * Sets the value of the emailTo property.
	 * 
	 * @param aEmailTo the new value of the emailTo property
	 */
	public void setEmailTo(String aEmailTo)
	{
		emailTo = aEmailTo;
	}

	/**
	 * Access method for the notificationType property.
	 * 
	 * @return   the current value of the notificationType property
	 */
	public int getNotificationType()
	{
		return notificationType;
	}

	/**
	 * Sets the value of the notificationType property.
	 * 
	 * @param aNotificationType the new value of the notificationType property
	 */
	public void setNotificationType(int aNotificationType)
	{
		notificationType = aNotificationType;
	}
	/**
	 * @return
	 */
	public String getTaskName()
	{
		return taskName;
	}

	/**
	 * @param string
	 */
	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

}
