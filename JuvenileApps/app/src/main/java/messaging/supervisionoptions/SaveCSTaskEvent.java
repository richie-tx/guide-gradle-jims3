/*
 * Created on Jun 16, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveCSTaskEvent extends RequestEvent
{
	private String subject2=null;
	private String dueBy=null;
	private String courtId;
	private String conditionId;
	private String taskId;
	private String recipientId;
	private String emailAddress;
	private String taskListTypeId;
	private String taskNotificationTypeId;   // Email or Task or Notice
	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}

	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}

	/**
	 * @return
	 */
	public String getDueBy()
	{
		return dueBy;
	}

	/**
	 * @return
	 */
	public String getEmailAddress()
	{
		return emailAddress;
	}

	/**
	 * @return
	 */
	public String getRecipientId()
	{
		return recipientId;
	}

	/**
	 * @return
	 */
	public String getSubject2()
	{
		return subject2;
	}

	/**
	 * @return
	 */
	public String getTaskId()
	{
		return taskId;
	}

	/**
	 * @return
	 */
	public String getTaskListTypeId()
	{
		return taskListTypeId;
	}

	/**
	 * @return
	 */
	public String getTaskNotificationTypeId()
	{
		return taskNotificationTypeId;
	}

	/**
	 * @param string
	 */
	public void setConditionId(String string)
	{
		conditionId = string;
	}

	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		courtId = string;
	}

	/**
	 * @param string
	 */
	public void setDueBy(String string)
	{
		dueBy = string;
	}

	/**
	 * @param string
	 */
	public void setEmailAddress(String string)
	{
		emailAddress = string;
	}

	/**
	 * @param string
	 */
	public void setRecipientId(String string)
	{
		recipientId = string;
	}

	/**
	 * @param string
	 */
	public void setSubject2(String string)
	{
		subject2 = string;
	}

	/**
	 * @param string
	 */
	public void setTaskId(String string)
	{
		taskId = string;
	}

	/**
	 * @param string
	 */
	public void setTaskListTypeId(String string)
	{
		taskListTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setTaskNotificationTypeId(String string)
	{
		taskNotificationTypeId = string;
	}

}
