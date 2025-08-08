/*
 * Created on Jun 19, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.supervision.supervisionoptions;

import java.util.Iterator;

import messaging.supervisionoptions.reply.CSTaskResponseEvent;
import mojo.km.persistence.PersistentObject;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Home;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CSTask extends PersistentObject
{
	
	private String subject2=null;
	private int dueBy;
	private String courtId;
	private String conditionId;
	private int taskId;
	private String recipientId;
	private String emailAddress;
	private String taskListTypeId;
	private String taskNotificationTypeId;   // Email or Task or Notice


	public CSTask()
		{
		}
		
		/**
		* @roseuid 42F79A3902E0
		*/
		public void bind()
		{
			markModified();
		}
	
		/**
		* @param conditionId
		* @roseuid 42F79A3902DE
		*/
		static public CSTask find(String csTaskId)
		{
			IHome home = new Home();
			return (CSTask) home.find(csTaskId, CSTask.class);
		}
		/**
		* @return Iterator Condition
		* @param event
		*/
		static public Iterator findAll(IEvent event)
		{
			IHome home = new Home();
			return home.findAll(event, CSTask.class);
		}
		/**
		* Finds all Conditions by an attribute value
		* @return 
		* @param attributeName
		* @param attributeValue
		*/
		static public Iterator findAll(String attributeName, String attributeValue)
		{
			IHome home = new Home();
			Iterator csTasks = home.findAll(attributeName, attributeValue, CSTask.class);
			return csTasks;
		}
	
		
		
		/**
		* Access method for the description property.
		* @return the current value of the description property
		*/
		public String getSubject2()
		{
			fetch();
			return subject2;
		}
		
		/**
		 * Creates response event
		 * @return
		 */
		public CSTaskResponseEvent getResponseEvent()
		{
			CSTaskResponseEvent csTaskRespEvt = new CSTaskResponseEvent();
			csTaskRespEvt.setConditionId(getConditionId());
			csTaskRespEvt.setCourtId(getCourtId());
			csTaskRespEvt.setCreateDate(this.getCreateTimestamp());
			csTaskRespEvt.setDueBy(getDueBy());
			csTaskRespEvt.setEmailAddress(getEmailAddress());
			csTaskRespEvt.setRecipientId(getRecipientId());
			csTaskRespEvt.setSubject2(getSubject2());
			csTaskRespEvt.setTaskId(getTaskId());
			csTaskRespEvt.setTaskListTypeId(getTaskListTypeId());
			csTaskRespEvt.setTaskNotificationTypeId(getTaskNotificationTypeId());
			csTaskRespEvt.setUpdateDate(this.getUpdateTimestamp());
			return csTaskRespEvt;
		}
		
		
		
		
		/**
		* Sets the value of the description property.
		* @param aDescription the new value of the description property
		*/
		public void setSubject2(String aSubject2)
		{
			if (this.subject2 == null || !this.subject2.equals(aSubject2))
			{
				markModified();
			}
			subject2 = aSubject2;
		}
		
		
	/**
	 * @return
	 */
	public String getConditionId()
	{
		fetch();
		return conditionId;
	}

	/**
	 * @return
	 */
	public String getCourtId()
	{
		fetch();
		return courtId;
	}


	/**
	 * @return
	 */
	public int getDueBy()
	{
		fetch();
		return dueBy;
	}

	/**
	 * @return
	 */
	public String getEmailAddress()
	{
		fetch();
		return emailAddress;
	}

	/**
	 * @return
	 */
	public String getRecipientId()
	{
		fetch();
		return recipientId;
	}

	/**
	 * @return
	 */
	public String getTaskId()
	{
		return getOID().toString();
	}

	/**
	 * @return
	 */
	public String getTaskListTypeId()
	{
		fetch();
		return taskListTypeId;
	}

	/**
	 * @return
	 */
	public String getTaskNotificationTypeId()
	{
		fetch();
		return taskNotificationTypeId;
	}



	/**
	 * @param string
	 */
	public void setConditionId(String string)
	{
		if (this.conditionId == null || !this.conditionId.equals(string))
						{
							markModified();
						}
		conditionId = string;
	}

	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		if (this.courtId == null || !this.courtId.equals(string))
								{
									markModified();
								}
		courtId = string;
	}

	/**
	 * @param string
	 */
	public void setDueBy(int aDueBy)
	{
		if (!(this.dueBy==aDueBy))
			{
				markModified();
			}
		dueBy = aDueBy;
	}

	/**
	 * @param string
	 */
	public void setEmailAddress(String string)
	{
		if (this.emailAddress == null || !this.emailAddress.equals(string))
										{
											markModified();
										}
		emailAddress = string;
	}

	/**
	 * @param string
	 */
	public void setRecipientId(String string)
	{
		if (this.recipientId == null || !this.recipientId.equals(string))
										{
											markModified();
										}
		recipientId = string;
	}


	/**
	 * @param string
	 */
	public void setTaskId(String string)
	{
		setOID(string);
	}

	/**
	 * @param string
	 */
	public void setTaskListTypeId(String string)
	{
		if (this.taskListTypeId == null || !this.taskListTypeId.equals(string))
										{
											markModified();
										}
				taskListTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setTaskNotificationTypeId(String string)
	{
		if (this.taskNotificationTypeId == null || !this.taskNotificationTypeId.equals(string))
										{
											markModified();
										}
		taskNotificationTypeId = string;
	}

}
