/*
 * Created on Jan 3, 2006
 *
 */
package messaging.juvenilewarrant;

import mojo.km.messaging.PersistentEvent;

/**
 * @author jfisher
 *
 */
public class SendFailureToEnterReleaseDetailsNotificationEvent extends PersistentEvent
{
	private String warrantNum;
	private String emailFrom;
	private String emailTo;
	private String taskName;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private String userPhone;

	/**
	 * @return
	 */
	public String getWarrantNum()
	{
		return warrantNum;
	}

	/**
	 * @param string
	 */
	public void setWarrantNum(String string)
	{
		warrantNum = string;
	}

	/**
	 * @return
	 */
	public String getEmailFrom()
	{
		return emailFrom;
	}

	/**
	 * @return
	 */
	public String getEmailTo()
	{
		return emailTo;
	}

	/**
	 * @param string
	 */
	public void setEmailFrom(String string)
	{
		emailFrom = string;
	}

	/**
	 * @param string
	 */
	public void setEmailTo(String string)
	{
		emailTo = string;
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
	public void setTaskName(String string)
	{
		taskName = string;
	}

	/**
	 * @return
	 */
	public String getUserEmail()
	{
		return userEmail;
	}

	/**
	 * @return
	 */
	public String getUserFirstName()
	{
		return userFirstName;
	}

	/**
	 * @return
	 */
	public String getUserLastName()
	{
		return userLastName;
	}

	/**
	 * @return
	 */
	public String getUserPhone()
	{
		return userPhone;
	}

	/**
	 * @param string
	 */
	public void setUserEmail(String string)
	{
		userEmail = string;
	}

	/**
	 * @param string
	 */
	public void setUserFirstName(String string)
	{
		userFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setUserLastName(String string)
	{
		userLastName = string;
	}

	/**
	 * @param string
	 */
	public void setUserPhone(String string)
	{
		userPhone = string;
	}

}
