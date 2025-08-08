//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\SendJuvenileArrestWarrantNotificationEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.PersistentEvent;

public class SendJuvenileArrestWarrantNotificationEvent  extends PersistentEvent
{
	private String emailFrom;
	private String emailTo;
	private int notificationType;
	private String warrantNum;
	private String taskName;

	/**
	 * @roseuid 41E6955D017D
	 */
	public SendJuvenileArrestWarrantNotificationEvent()
	{

	}

	/**
	 * @param warrantNum
	 * @roseuid 41E5746100ED
	 */
	public void setWarrantNum(String warrantNum)
	{
		this.warrantNum = warrantNum;
	}

	/**
	 * @return String
	 * @roseuid 41E5746100EF
	 */
	public String getWarrantNum()
	{
		return warrantNum;
	}

	/**
	 * @return
	 */
	public int getNotificationType()
	{
		// TODO Auto-generated method stub
		return notificationType;
	}

	/**
	 * @return
	 */
	public String getEmailTo()
	{
		// TODO Auto-generated method stub
		return emailTo;
	}

	/**
	 * @return
	 */
	public String getEmailFrom()
	{
		// TODO Auto-generated method stub
		return emailFrom;
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

}
