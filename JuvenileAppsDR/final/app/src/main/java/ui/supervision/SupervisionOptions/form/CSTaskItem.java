/*
 * Created on Jun 5, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.supervision.SupervisionOptions.form;

import naming.PDCodeTableConstants;
import ui.common.CodeHelper;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CSTaskItem implements Comparable
{
	private String taskId;
	private String recipientId;
	private String emailAddress;
	private String taskListTypeId;
	private String recipient;
	private String taskListType;
	private String taskNotificationType;  // Email or Task or Notice
	private String taskNotificationTypeId;
	private boolean isSelected=false;
	private boolean orignallySelected=false;
	private boolean isDeleted=false;
	
	
	
	public CSTaskItem(){
		taskId="";
		recipientId="";
		emailAddress="";
		taskListTypeId="";
		recipient="";
		taskListType="";
		taskNotificationType=""; // Email or Task or Notice
		isSelected=false;
		orignallySelected=false;
		isDeleted=false;
		taskNotificationTypeId=PDCodeTableConstants.TASK_NOTIFICATION_TYPE_TASK;
		CSTaskConfiguration.initalizeLists();
		
	}
	
	/**
	 * Returns true if this task item is valid and should be updated/entered
	 * @return
	 */
	private boolean isValid(){
		if(taskId!=null || recipientId!=null || !(recipientId.equals("")) || !(taskId.equals("")) ||
			taskListTypeId!=null || !(taskListTypeId.equals("")) || orignallySelected==true || isSelected==true)
			return true;
		else
			return false;
		
	}
	
	
	/**
	 * @return
	 */
	public String getRecipient()
	{
		if(recipientId==null || recipientId.equals("")){
					setRecipient("");
				}
 
				else if(!(getTaskNotificationTypeId().equalsIgnoreCase(PDCodeTableConstants.TASK_NOTIFICATION_TYPE_EMAIL))){
				  if(CSTaskConfiguration.toReceipientList !=null &&  CSTaskConfiguration.toReceipientList.size()>0){
					  setRecipient(CodeHelper.getCodeDescriptionByCode(CSTaskConfiguration.toReceipientList,recipientId));
				  }
				}
				else{
					if(CSTaskConfiguration.emailReceipientList !=null &&  CSTaskConfiguration.emailReceipientList.size()>0){
						 setRecipient(CodeHelper.getCodeDescriptionByCode(CSTaskConfiguration.emailReceipientList,recipientId));
					 }
				}
		return recipient;
	}

	/**
	 * @return
	 */
	public String getTaskListType()
	{
		return taskListType;
	}

	/**
	 * @param string
	 */
	public void setRecipient(String string)
	{
		recipient = string;
	}

	/**
	 * @param string
	 */
	public void setTaskListType(String string)
	{
		taskListType = string;
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
	public String getTaskListTypeId()
	{
		return taskListTypeId;
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
	public void setTaskListTypeId(String string)
	{
		taskListTypeId = string;
		if(taskListTypeId==null || taskListTypeId.equals("")){
			setTaskListType("");
						return;
					}
 
					  if(CSTaskConfiguration.taskListTypeList !=null &&  CSTaskConfiguration.taskListTypeList.size()>0){
						  setTaskListType(CodeHelper.getCodeDescriptionByCode(CSTaskConfiguration.taskListTypeList,taskListTypeId));
					  }
	}

	/**
	 * @return
	 */
	public String getTaskId()
	{
		return taskId;
	}

	/**
	 * @param string
	 */
	public void setTaskId(String string)
	{
		taskId = string;
	}

	/**
	 * @return
	 */
	public String getTaskNotificationType()
	{
		return taskNotificationType;
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
	public void setTaskNotificationType(String string)
	{
		taskNotificationType = string;
	}

	/**
	 * @param string
	 */
	public void setTaskNotificationTypeId(String string)
	{
		taskNotificationTypeId = string;
		
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
	public boolean isDeleted()
	{
		return isDeleted;
	}

	/**
	 * @param string
	 */
	public void setEmailAddress(String string)
	{
		emailAddress = string;
	}

	/**
	 * @param b
	 */
	public void setDeleted(boolean b)
	{
		isDeleted = b;
	}

	/**
	 * @return
	 */
	public boolean isSelected()
	{
		return isSelected;
	}

	/**
	 * @return
	 */
	public boolean isOrignallySelected()
	{
		return orignallySelected;
	}

	/**
	 * @param b
	 */
	public void setSelected(boolean b)
	{
		isSelected = b;
	}

	/**
	 * @param b
	 */
	public void setOrignallySelected(boolean b)
	{
		orignallySelected = b;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		if(arg0==null){
			return 1;
		}
		else{
			CSTaskItem itemToCompare=(CSTaskItem)arg0;
			return this.getRecipient().compareTo(itemToCompare.getRecipient());
		}
	}

}
