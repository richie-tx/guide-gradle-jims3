//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\SendDirectiveToApprehendNotificationEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.PersistentEvent;

public class SendDirectiveToApprehendNotificationEvent extends PersistentEvent
{
	public String emailFrom;
	public String emailTo;
	public int notificationType;
	public String taskName;
	public String warrantNum;
	/**
	* @roseuid 418B934300C4
	*/
	public SendDirectiveToApprehendNotificationEvent()
	{
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
	* Access method for the emailTo property.
	* 
	* @return   the current value of the emailTo property
	*/
	public String getEmailTo()
	{
		return emailTo;
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
	 * @return
	 */
	public String getTaskName()
	{
		return taskName;
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
	* Sets the value of the emailFrom property.
	* 
	* @param emailFrom the new value of the emailFrom property
	*/
	public void setEmailFrom(String emailFrom)
	{
		this.emailFrom = emailFrom;
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
	* Sets the value of the notificationType property.
	* 
	* @param notificationType the new value of the notificationType property
	*/
	public void setNotificationType(int notificationType)
	{
		this.notificationType = notificationType;
	}

	/**
	 * @param taskName
	 */
	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
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

}