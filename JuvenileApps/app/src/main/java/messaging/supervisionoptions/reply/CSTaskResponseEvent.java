/*
 * Created on Jun 19, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.supervisionoptions.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CSTaskResponseEvent extends ResponseEvent implements Comparable
{
	
	private String subject2=null;
	private int dueBy;
	private String courtId;
	private String conditionId;
	private String taskId;
	private String recipientId;
	private String emailAddress;
	private String taskListTypeId;
	private String taskNotificationTypeId;   // Email or Task or Notice
	private Date createDate;
	private Date updateDate;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		if(arg0==null)
			return 1;
		CSTaskResponseEvent csTaskRespEvt = (CSTaskResponseEvent) arg0;
		if(csTaskRespEvt.getCreateDate()==null)
			return 1;
		if(this.getCreateDate()==null)
			return -1;
		return this.getCreateDate().compareTo(csTaskRespEvt.getCreateDate());
	}

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
	public Date getCreateDate()
	{
		return createDate;
	}

	/**
	 * @return
	 */
	public int getDueBy()
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
	 * @return
	 */
	public Date getUpdateDate()
	{
		return updateDate;
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
	 * @param date
	 */
	public void setCreateDate(Date date)
	{
		createDate = date;
	}

	/**
	 * @param string
	 */
	public void setDueBy(int aDueBy)
	{
		dueBy = aDueBy;
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

	/**
	 * @param date
	 */
	public void setUpdateDate(Date date)
	{
		updateDate = date;
	}

}
