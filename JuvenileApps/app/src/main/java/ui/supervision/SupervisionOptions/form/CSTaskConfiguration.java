/*
 * Created on Jun 5, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.supervision.SupervisionOptions.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import naming.PDCodeTableConstants;
import naming.UIConstants;


import ui.common.CodeHelper;

import messaging.codetable.reply.CodeResponseEvent;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CSTaskConfiguration
{
	private Collection taskItems=null;
	private String subject2=null;
	private String dueBy=null;
	private String courtId=null;
	private String conditionId=null;
	private Collection emailTaskItems=null;
	private String agency=UIConstants.CSC;
	public static Collection toReceipientList;
	public static Collection emailReceipientList;
	public static Collection taskListTypeList;
	
	
	
	public void resetListsToAgency(boolean addOneTask, boolean doCreate){
		if(this.agency!=null){
			if(this.agency.equals(UIConstants.PTR)){
				CSTaskConfiguration.initalizeListsPTR();
				
			}
			else {
				CSTaskConfiguration.initalizeListsCSC();
			}
			
		}
		else{
			agency=UIConstants.CSC;
			CSTaskConfiguration.initalizeListsCSC();
		}
		if(doCreate)
			initCreate(addOneTask);
	}
	
	public static void initalizeListsPTR(){
			CSTaskConfiguration.taskListTypeList=CodeHelper.getCodes(PDCodeTableConstants.TASK_LIST_TYPES,true);
			CSTaskConfiguration.toReceipientList=CodeHelper.getCodes(PDCodeTableConstants.TASK_RECIPIENTS_PTS,true);
			CSTaskConfiguration.emailReceipientList=CodeHelper.getCodes(PDCodeTableConstants.EMAIL_TASK_RECIPIENTS_PTS,true);
			//CSTaskConfiguration.toReceipientList=CodeHelper.getCodes(PDCodeTableConstants.TASK_RECIPIENTS,true);
			//CSTaskConfiguration.emailReceipientList=CodeHelper.getCodes(PDCodeTableConstants.EMAIL_TASK_RECIPIENTS,true);
			removeWorkGroupTypeFromList(taskListTypeList);
		}
	
	public static void initalizeListsCSC(){
			CSTaskConfiguration.taskListTypeList=CodeHelper.getCodes(PDCodeTableConstants.TASK_LIST_TYPES,true);
			CSTaskConfiguration.toReceipientList=CodeHelper.getCodes(PDCodeTableConstants.TASK_RECIPIENTS,true);
			CSTaskConfiguration.emailReceipientList=CodeHelper.getCodes(PDCodeTableConstants.EMAIL_TASK_RECIPIENTS,true);
			removeWorkGroupTypeFromList(taskListTypeList);
	}

	public static void removeWorkGroupTypeFromList(Collection aTaskListTypeList){
		if(aTaskListTypeList!=null && aTaskListTypeList.size()>0){
			Iterator iter=aTaskListTypeList.iterator();
			while(iter.hasNext()){
				Object obj=iter.next();
				if(obj!=null && obj instanceof CodeResponseEvent){
					CodeResponseEvent myCodeResp=(CodeResponseEvent)obj;
					if(myCodeResp!=null){
						if(PDCodeTableConstants.CS_TASK_LIST_TYPE_WORKGROUP.equalsIgnoreCase(myCodeResp.getCode())){
							iter.remove();
							return;
						}
					}
				}
			}
		}
	}
	
	public static void initalizeLists(){
		if(emailReceipientList==null || toReceipientList==null || taskListTypeList==null){
			CSTaskConfiguration.initalizeListsCSC();
			
		}
}
	
	
	
	public CSTaskConfiguration(boolean addOneTask, String aAgencyId){
		if(aAgencyId==null || aAgencyId.equals("")){
			this.agency=UIConstants.CSC;
		}
		else{
			this.agency=aAgencyId;
		}
		resetListsToAgency(addOneTask,true);
	
	}
	
	
	public void initCreate(boolean addOneTask){
		clearAll();
		initEmailNotificationTasks();
		if(addOneTask)
			addTaskItem();
	}
	
	public void addAtLeastOneTask(){
		if(taskItems==null)
			taskItems=new ArrayList();
		if(taskItems.size()<1){
			CSTaskItem tempItem= new CSTaskItem();
			taskItems.add(tempItem);	
		}
	}
	
	public void initEmailNotificationTasks(){
		if(getEmailReceipientList()!=null){
			emailTaskItems=new ArrayList();
			Iterator iter=emailReceipientList.iterator();
			while(iter != null && iter.hasNext()){
				CodeResponseEvent tempItem=(CodeResponseEvent)iter.next();
				if (tempItem.getCode() != null){
					addEmailTaskItem(tempItem.getCode(), false);
				}
			}	
		}
	}
	
	public void clearAll(){
		taskItems=null;
		subject2=null;
		dueBy=null;
		courtId=null;
		conditionId=null;
		emailTaskItems=null;
	}
	
	public void clearEmailTaskItemCheckBoxes(){
		if(emailTaskItems!=null && emailTaskItems.size()>0){
			Iterator iter=emailTaskItems.iterator();
			while(iter.hasNext()){
				CSTaskItem tempItem=(CSTaskItem)iter.next();
				tempItem.setSelected(false);
			}
		}
	}


	public void removeAndReplaceEmailTaskItem(String aReceipientId, CSTaskItem aTaskItem){
		if(emailTaskItems==null || emailTaskItems.size()<=0)
			return;
		CSTaskItem tempItem=null;
		Iterator iter=emailTaskItems.iterator();
		while(iter.hasNext()){
			tempItem=(CSTaskItem)iter.next();
			if(tempItem.getRecipientId().equalsIgnoreCase(aReceipientId)){
				iter.remove();
				break;
			}
		}
		iter=null;
		emailTaskItems.add(aTaskItem);
		Collections.sort((List)emailTaskItems);
	}
	
	public void addEmailTaskItem(String aReceipientId,boolean aSelected){
			if(emailTaskItems==null)
				emailTaskItems=new ArrayList();
			CSTaskItem tempItem= new CSTaskItem();
			tempItem.setSelected(aSelected);
			tempItem.setRecipientId(aReceipientId);
			tempItem.setTaskListTypeId("");
			tempItem.setTaskNotificationTypeId(PDCodeTableConstants.TASK_NOTIFICATION_TYPE_EMAIL);
			emailTaskItems.add(tempItem);
			Collections.sort((List)emailTaskItems);
		}
	
	public void addTaskItem(CSTaskItem aTaskItem){
			if(taskItems==null)
				taskItems=new ArrayList();
			taskItems.add(aTaskItem);
		Collections.sort((List)taskItems);
		}
	
	public void addTaskItem(){
		if(taskItems==null)
			taskItems=new ArrayList();
		CSTaskItem tempItem= new CSTaskItem();
		tempItem.setTaskNotificationTypeId(PDCodeTableConstants.TASK_NOTIFICATION_TYPE_TASK);
		taskItems.add(tempItem);
		Collections.sort((List)taskItems);
	}
	
	public void removeTaskItem(int aIndex){
			if(taskItems==null || taskItems.size()<=0 || taskItems.size()<=aIndex)
				return;
			((ArrayList)taskItems).remove(aIndex);
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
	public String getSubject2()
	{
		return subject2;
	}

	/**
	 * @return
	 */
	public Collection getTaskItems()
	{
		if(taskItems==null){
			addTaskItem();
		}
		return taskItems;
	}



	/**
	 * @param i
	 */
	public void setDueBy(String i)
	{
		dueBy = i;
	}



	/**
	 * @param string
	 */
	public void setSubject2(String string)
	{
		subject2 = string;
	}

	/**
	 * @param collection
	 */
	public void setTaskItems(Collection collection)
	{
		taskItems = collection;
	}



	/**
	 * @return
	 */
	public Collection getTaskListTypeList()
	{
		if(CSTaskConfiguration.taskListTypeList==null){
			CSTaskConfiguration.taskListTypeList=CodeHelper.getCodes(PDCodeTableConstants.TASK_LIST_TYPES,true);
		}
		return CSTaskConfiguration.taskListTypeList;
	}



	/**
	 * @param list
	 */
	public void setTaskListTypeList(ArrayList list)
	{
		taskListTypeList = list;
	}

	/**
	 * @return
	 */
	public Collection getEmailReceipientList()
	{
		if(CSTaskConfiguration.emailReceipientList==null){
			CSTaskConfiguration.emailReceipientList=CodeHelper.getCodes(PDCodeTableConstants.EMAIL_TASK_RECIPIENTS,true);
		}
		return CSTaskConfiguration.emailReceipientList;
	}

	/**
	 * @return
	 */
	public Collection getToReceipientList()
	{
		if(CSTaskConfiguration.toReceipientList==null){
			CSTaskConfiguration.toReceipientList=CodeHelper.getCodes(PDCodeTableConstants.TASK_RECIPIENTS,true);
		}
		return CSTaskConfiguration.toReceipientList;
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
	public Collection getEmailTaskItems()
	{
		return emailTaskItems;
	}

	/**
	 * @param list
	 */
	public void setEmailReceipientList(ArrayList list)
	{
		emailReceipientList = list;
	}

	/**
	 * @param list
	 */
	public void setToReceipientList(ArrayList list)
	{
		toReceipientList = list;
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
	 * @param collection
	 */
	public void setEmailTaskItems(Collection collection)
	{
		emailTaskItems = collection;
	}

	/**
	 * @return Returns the agency.
	 */
	public String getAgency() {
		return agency;
	}
	/**
	 * @param agency The agency to set.
	 */
	public void setAgency(String aAgency) {
		if(aAgency!=null && !aAgency.equals(this.agency)){
			this.agency = aAgency;
			clearAll();
			resetListsToAgency(false,false);
		}
	}
}
