/*
 * Created on Mar 15, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.task.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.task.GetTaskEvent;
import messaging.task.domintf.ITask;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.TaskControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ConfigHelper;

import ui.task.ITaskRestorable;

/**
 * @author Jim Fisher
 *
 */
public class ExecuteTaskAction extends Action
{
	private static final String TASK_ID_PARM = "taskId";

	private static final String NO_TASK_ID_MSG = "taskId was not specified";

	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ITask task = this.getTask(aRequest);

		ConfigHelper configHelper = this.getConfigHelper(task, aRequest, aResponse);

		ActionMapping actionMapping = this.getActionMapping(task, configHelper);

		ITaskRestorable form = (ITaskRestorable) configHelper.getActionForm();

		form.restore(task);

		this.setForm(aRequest, actionMapping, form);

		ActionForward forward = this.computeForward(configHelper, actionMapping);

		return forward;
	}

	/**
	 * @param actionMapping
	 * @param form
	 */
	private void setForm(HttpServletRequest aRequest, ActionMapping actionMapping, ITaskRestorable form)
	{
		String scope = actionMapping.getScope();
		String name = actionMapping.getName();
		if ("request".equalsIgnoreCase(scope))
		{
			aRequest.setAttribute(name, form);
		}
		else
		{
			aRequest.getSession().setAttribute(name, form);
		}
	}

	private ConfigHelper getConfigHelper(ITask task, HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		String applicationPath = "/" + task.getApplication();
		ServletContext servletContext = aRequest.getSession().getServletContext().getContext(applicationPath);

		ConfigHelper configHelper = (ConfigHelper) new ConfigHelper(servletContext, aRequest, aResponse);
		return configHelper;
	}

	private ActionMapping getActionMapping(ITask task, ConfigHelper configHelper)
	{
		String actionPath = "/" + task.getAction();
		ActionMapping actionMapping = configHelper.getActionMapping(actionPath);

		return actionMapping;
	}

	private ActionForward computeForward(ConfigHelper configHelper, ActionMapping actionMapping)
	{
		String actionPath = actionMapping.getPath();
		String actionURL = configHelper.getAction(actionPath);

		// Remove web application (i.e. /JuvenileWarrants/) from the URL 
		int lastIndex = actionURL.lastIndexOf("/");

		if (lastIndex != -1)
		{
			actionURL = actionURL.substring(lastIndex, actionURL.length());
		}

		String formName = actionMapping.getName();

		ActionForward forward = new ActionForward();
		forward.setPath(actionURL);
		forward.setName(formName);
		forward.setRedirect(true);

		return forward;
	}

	private ITask getTask(HttpServletRequest aRequest)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Revive task

		String taskId = aRequest.getParameter(TASK_ID_PARM);

		if (taskId == null)
		{
			// TODO Grab error msg text from App Resources
			throw new IllegalArgumentException(NO_TASK_ID_MSG);
		}

		GetTaskEvent getTask = (GetTaskEvent) EventFactory.getInstance(TaskControllerServiceNames.GETTASK);
		getTask.setTaskId(taskId);
		dispatch.postEvent(getTask);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		TaskResponseEvent taskResponse =
			(TaskResponseEvent) MessageUtil.filterComposite(response, TaskResponseEvent.class);

		ITask task = taskResponse.getTask();
		return task;
	}
}
