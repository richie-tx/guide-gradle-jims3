/*
 * Created on Feb 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilewarrant;

import mojo.km.messaging.PersistentEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SendViolationOfProbationNotificationEvent extends PersistentEvent
{
	public String warrantNum;
	public String emailFrom;
	public String emailTo;
	public int notificationType;
	public String taskName;
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
	 * @return
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
	 * @return
	 */
	public String getWarrantNum()
	{
		return warrantNum;
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
	 * @param i
	 */
	public void setNotificationType(int i)
	{
		notificationType = i;
	}

	/**
	 * @param string
	 */
	public void setTaskName(String string)
	{
		taskName = string;
	}

	/**
	 * @param string
	 */
	public void setWarrantNum(String string)
	{
		warrantNum = string;
	}

}
