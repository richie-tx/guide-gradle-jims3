/*
 * Created on December 12, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.task.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.task.UpdateTaskStatusEvent;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.TaskControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.SecurityUIHelper;
import ui.security.authentication.form.LoginForm;
import ui.task.TaskHelper;

/**
 * @author mchowdhury
 *
 */
public class CloseTaskAction extends Action
{
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		LoginForm loginForm = (LoginForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UpdateTaskStatusEvent updateEvent = 
				(UpdateTaskStatusEvent) EventFactory.getInstance(TaskControllerServiceNames.UPDATETASKSTATUS);		
		
		String[] selectedTasks = loginForm.getSelectedTasks();
		for(int i=0;i<selectedTasks.length;i++){
			updateEvent.setTaskId(selectedTasks[i]);
			updateEvent.setStatusCode(UIConstants.CLOSED_STATUS_ID);
			dispatch.postEvent(updateEvent);
			
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map map = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(map);
		}
		this.callNotificationFramework(SecurityUIHelper.getLogonId(),loginForm);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param user
	 * @param loginForm
	 */
	private void callNotificationFramework(String logonId, LoginForm loginForm) {
		Collection tasks = TaskHelper.getNotClosedUserTasks(logonId, UIConstants.CLOSED_STATUS_ID);
		Iterator tasksIter = tasks.iterator();
		int submittedTaskCount = 0;
		while(tasksIter.hasNext()){
			TaskResponseEvent resp = (TaskResponseEvent) tasksIter.next();
			String statusId = resp.getTask().getStatusCode();
			if(statusId != null && statusId.equalsIgnoreCase(UIConstants.SUBMITTED_STATUS_ID)){
				submittedTaskCount++;
			}
		}
		loginForm.setTaskCount("" + submittedTaskCount + " / " + tasks.size());
		loginForm.setTaskList(tasks);
	}
}
