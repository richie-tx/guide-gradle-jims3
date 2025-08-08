/*
 * Created on Jun 20, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.supervision.SupervisionOptions.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import messaging.supervisionoptions.DeleteCSTaskEvent;
import messaging.supervisionoptions.GetCSTaskEvent;
import messaging.supervisionoptions.SaveCSTaskEvent;
import messaging.supervisionoptions.reply.CSTaskResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import ui.supervision.SupervisionOptions.form.CSTaskConfiguration;
import ui.supervision.SupervisionOptions.form.CSTaskItem;
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SupervisionOptionsHelper
{
	
	/**
	 * Method retrieves all the task items for a given taskId, or condition Id, or court Id. Mutually exclusive
	 * @param aTaskId
	 * @param aConditionId
	 * @param aCourtId
	 * @param isCopy== if set to true the task id's are not set when retrieved
	 * @return null if there are no tasks, else TaskConfiguration
	 * @throws ReturnException
	 */
	public static CSTaskConfiguration getTaskConfiguration(String aTaskId, String aConditionId, String aCourtId, boolean isCopy, String aAgency) throws ReturnException{
		GetCSTaskEvent evt=new GetCSTaskEvent();
		evt.setConditionId(aConditionId);
		evt.setTaskId(aTaskId);
		evt.setCourtId(aCourtId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(evt);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		ReturnException returnException =
			(ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
		if (returnException != null)
		{
			throw returnException;
		}
		Object tasksObjs=MessageUtil.compositeToCollection(response, CSTaskResponseEvent.class);
		if(tasksObjs!=null){
			Collection tasks=(Collection)tasksObjs;
			if(tasks.size()>0){
				Iterator tasksIter=tasks.iterator();
				CSTaskConfiguration taskConfig=new CSTaskConfiguration(false,aAgency);
				boolean init=false;
				while(tasksIter.hasNext()){
					CSTaskResponseEvent respEvt=(CSTaskResponseEvent)tasksIter.next();
					if(!init){
						taskConfig.setSubject2(respEvt.getSubject2());
						if(respEvt.getDueBy()==0){
							taskConfig.setDueBy("");
						}
						else
							taskConfig.setDueBy(Integer.toString(respEvt.getDueBy()));
						init=true;
					}
					CSTaskItem taskItem=mapCSTaskRespEvtToCSTaskItem(respEvt, isCopy);
					taskItem.setOrignallySelected(true);
					taskItem.setSelected(true);
					taskItem.setDeleted(false);
					if(taskItem.getTaskNotificationTypeId() != null 
							&& taskItem.getTaskNotificationTypeId().equalsIgnoreCase(PDCodeTableConstants.TASK_NOTIFICATION_TYPE_EMAIL)){
						taskConfig.removeAndReplaceEmailTaskItem(taskItem.getRecipientId(),taskItem);
					}
					else{
						taskConfig.addTaskItem(taskItem);
					}
				}
				return taskConfig;
			}
			else
				return null;
		}
		else{
			return null;
		}
	}
	
	public static void processUpdatedCSTaskConfiguration(CSTaskConfiguration aTaskConfiguration) throws ReturnException{
			
			if(aTaskConfiguration==null)
				return;
			String subject2=aTaskConfiguration.getSubject2();
			String dueBy=aTaskConfiguration.getDueBy();

			Collection toDeleteTasks=new ArrayList();
			Collection toSaveTasks=new ArrayList();

			Collection tasks;
			
			tasks=aTaskConfiguration.getEmailTaskItems();
			if(tasks!=null && tasks.size()>0)
			{
				Iterator iter=tasks.iterator();
				while(iter.hasNext()){
					CSTaskItem taskItem=(CSTaskItem)iter.next();
					if(taskItem.isSelected()){
						toSaveTasks.add(taskItem);	
					}
					else if(taskItem.isOrignallySelected() && !taskItem.isSelected()){
						toDeleteTasks.add(taskItem);
					}
					else if(taskItem.isDeleted()){
						toDeleteTasks.add(taskItem);
					}
					taskItem=null;
				}
			}
			tasks=aTaskConfiguration.getTaskItems();
			if(tasks!=null && tasks.size()>0)
			{
				Iterator iter=tasks.iterator();
				while(iter.hasNext()){
					CSTaskItem taskItem=(CSTaskItem)iter.next();
					if(!taskItem.isDeleted() && taskItem.getRecipientId()!=null && !taskItem.getRecipientId().equals("")){
						toSaveTasks.add(taskItem);
					}
					else if(taskItem.isDeleted() && taskItem.getTaskId()!=null && !(taskItem.getTaskId().equals(""))){
						toDeleteTasks.add(taskItem);
					}
					taskItem=null;
				}
			}
			
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			Iterator deleteIter=toDeleteTasks.iterator();
			while(deleteIter.hasNext()){
				CSTaskItem taskItem=(CSTaskItem)deleteIter.next();
				DeleteCSTaskEvent event=new DeleteCSTaskEvent();
				event.setTaskId(taskItem.getTaskId());
				dispatch.postEvent(event);
				CompositeResponse response = (CompositeResponse) dispatch.getReply();
				ReturnException returnException =
					(ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
				if (returnException != null)
				{
					throw returnException;
				}
			}
			Iterator saveIter=toSaveTasks.iterator();
			while(saveIter.hasNext()){
				CSTaskItem taskItem=(CSTaskItem)saveIter.next();
				SaveCSTaskEvent saveEvt=mapCSTaskItemToCSSaveEvent(taskItem);
				if(aTaskConfiguration.getConditionId()!=null && !(aTaskConfiguration.getConditionId().equals("")))
					saveEvt.setConditionId(aTaskConfiguration.getConditionId());
				if(aTaskConfiguration.getCourtId()!=null && !(aTaskConfiguration.getCourtId().equals("")))
					saveEvt.setCourtId(aTaskConfiguration.getCourtId());
				saveEvt.setDueBy(dueBy);
				saveEvt.setSubject2(subject2);
				dispatch.postEvent(saveEvt);
				CompositeResponse response = (CompositeResponse) dispatch.getReply();
				ReturnException returnException =
					(ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
				if (returnException != null)
				{
					throw returnException;
				}
			}					
	}
	
	public static SaveCSTaskEvent mapCSTaskItemToCSSaveEvent(CSTaskItem aTaskItem){
		SaveCSTaskEvent saveEvt=new SaveCSTaskEvent();
		saveEvt.setEmailAddress(aTaskItem.getEmailAddress());
		saveEvt.setRecipientId(aTaskItem.getRecipientId());
		saveEvt.setTaskId(aTaskItem.getTaskId());
		saveEvt.setTaskListTypeId(aTaskItem.getTaskListTypeId());
		saveEvt.setTaskNotificationTypeId(aTaskItem.getTaskNotificationTypeId());
		return saveEvt;
	}
	
	public static CSTaskItem mapCSTaskRespEvtToCSTaskItem(CSTaskResponseEvent aEvent, boolean isCopy){
		CSTaskItem taskItem=new CSTaskItem();
		taskItem.setEmailAddress(aEvent.getEmailAddress());
		taskItem.setRecipientId(aEvent.getRecipientId());
		if(!isCopy){
			taskItem.setTaskId(aEvent.getTaskId());
		}
		else{
			taskItem.setTaskId(null);
		}
		taskItem.setTaskListTypeId(aEvent.getTaskListTypeId());
		taskItem.setTaskNotificationTypeId(aEvent.getTaskNotificationTypeId());
		return taskItem;
	}
	
	public static  void getTaskConfiguration(CourtPolicyForm form,  HttpServletRequest aRequest,boolean isCopy){
			form.clearTaskConfiguation();
			if(form.getPolicyId()==null || form.getPolicyId().trim().equalsIgnoreCase("")){
				form.getTasks().initCreate(true);
			}
			else{  // have a condition ID so retrieve all the tasks associated to this and set them 
				CSTaskConfiguration taskConfig=null;
				taskConfig=SupervisionOptionsHelper.getTaskConfiguration(null,null,form.getPolicyId(),isCopy, form.getAgencyId());
				if(taskConfig==null){
					form.getTasks().initCreate(true);
				}
				else{
					form.setTasks(taskConfig);
				}
			}
		}
	
	public static  void getTaskConfiguration(SupervisionConditionForm form,  HttpServletRequest aRequest,boolean isCopy){
		form.clearTaskConfiguation();
		if(form.getConditionId()==null || form.getConditionId().trim().equalsIgnoreCase("")){
			form.getTasks().initCreate(true);
		}
		else{  // have a condition ID so retrieve all the tasks associated to this and set them 
			CSTaskConfiguration taskConfig=null;
			taskConfig=SupervisionOptionsHelper.getTaskConfiguration(null,form.getConditionId(),null,isCopy,form.getAgencyId());
			if(taskConfig==null){
				form.getTasks().initCreate(true);
			}
			else{
				form.setTasks(taskConfig);
			}
		}
	}
	
	
	public static void deleteCSTaskConfiguration(CSTaskConfiguration aTaskConfiguration) throws ReturnException{
		if(aTaskConfiguration==null)
			return;
		
		Collection toDeleteTasks=new ArrayList();
		Collection tasks;
			
		tasks=aTaskConfiguration.getEmailTaskItems();
		if(tasks!=null && tasks.size()>0)
		{
			Iterator iter=tasks.iterator();
			while(iter.hasNext()){
				CSTaskItem taskItem=(CSTaskItem)iter.next();
				taskItem.setDeleted(true);
				if(taskItem.isDeleted() && taskItem.getTaskId()!=null && !(taskItem.getTaskId().equals(""))){
				   toDeleteTasks.add(taskItem);
			   }
				taskItem=null;
			}
		}
		tasks=aTaskConfiguration.getTaskItems();
		if(tasks!=null && tasks.size()>0)
		{
			Iterator iter=tasks.iterator();
			while(iter.hasNext()){
				CSTaskItem taskItem=(CSTaskItem)iter.next();
				taskItem.setDeleted(true);
				if(taskItem.isDeleted() && taskItem.getTaskId()!=null && !(taskItem.getTaskId().equals(""))){
					toDeleteTasks.add(taskItem);
				}
				taskItem=null;
			}
		}
			
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		Iterator deleteIter=toDeleteTasks.iterator();
		while(deleteIter.hasNext()){
			CSTaskItem taskItem=(CSTaskItem)deleteIter.next();
			DeleteCSTaskEvent event=new DeleteCSTaskEvent();
			event.setTaskId(taskItem.getTaskId());
			dispatch.postEvent(event);
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			ReturnException returnException =
				(ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
			if (returnException != null)
			{
				throw returnException;
			}
		}
		
}
	
	
	
}// END CLASS
