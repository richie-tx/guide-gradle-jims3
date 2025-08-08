/*
 * Created on Sept 06, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.task.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.task.GetTaskEvent;
import messaging.task.domintf.ITask;
import messaging.task.domintf.ITaskState;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.TaskControllerServiceNames;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;

/**
 * @author awidjaja
 */
public class JuvenileCaseExecuteTaskAction extends Action
{
	private static final String TASK_ID_PARM = "taskId";
	private static final String NO_TASK_ID_MSG = "taskId was not specified";

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, 
	 * org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, 
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ITask task = null ;
		try
		{
			if( (task = this.getTask(aRequest)) != null )
			{
				// populate header form
				ITaskState taskState = task.getTaskState();
				initialize(aRequest, taskState);

				// get the URL to which to forward next
				String forwardPath = getForwardPath(task, taskState);
				try
				{
					aResponse.sendRedirect(forwardPath);
				}
				catch( IOException ioe )
				{
				}
			} // if
		}
		catch( IllegalArgumentException iae )
		{
		}

		return null;
	}

	/*
	 * 
	 */
	private String getForwardPath(ITask task, ITaskState taskState)
	{
		StringBuffer forwardPath = new StringBuffer();
		forwardPath.append("/");
		forwardPath.append(task.getApplication());
		forwardPath.append("/");
		forwardPath.append(task.getAction());
		forwardPath.append(".do");

		// Iterate through the keys in taskState and add all as
		// query string parameter to the forwardPath
		if( taskState != null )
		{
			forwardPath.append("?");

			for( Iterator keysIter = taskState.getKeys().iterator(); 
					keysIter.hasNext(); /*empty*/ )
			{
				String key = (String)keysIter.next();
				String value = (String)taskState.get(key);
				forwardPath.append(key);
				forwardPath.append("=");
				forwardPath.append(value);
				if( keysIter.hasNext() )
				{
					forwardPath.append("&");
				}
			} // for
		}

		// append submitAction=Link regardless...
		if( forwardPath.indexOf("?") > 0 )
		{
			forwardPath.append("&");
		}
		else
		{
			forwardPath.append("?");
		}

		// TODO: determine when to add submitAction=Link
		forwardPath.append("submitAction=Link");

		return forwardPath.toString();
	}

	/*
	 *
	 */
	private ITask getTask(HttpServletRequest aRequest)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Revive task
		String taskId = aRequest.getParameter(TASK_ID_PARM);
		if( taskId == null )
		{ // TODO Grab error msg text from App Resources
			throw new IllegalArgumentException(NO_TASK_ID_MSG);
		}

		GetTaskEvent getTask = (GetTaskEvent)
				EventFactory.getInstance(TaskControllerServiceNames.GETTASK);
		getTask.setTaskId(taskId);
		dispatch.postEvent(getTask);
		
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		TaskResponseEvent taskResponse = (TaskResponseEvent)
				MessageUtil.filterComposite(response, TaskResponseEvent.class);

		ITask task = null ;
		if( taskResponse != null )
		{
			task = taskResponse.getTask();
		}
		
		return task;
	}

	// TODO: determine when to initialize different forms for different tasks
	private void initialize(HttpServletRequest aRequest, ITaskState taskState)
	{
		String juvnum = (String)taskState.get(PDJuvenileCaseConstants.JUVENILENUM_PARAM);

		// Get juvenile details and populate header form.
		JuvenileProfileDetailResponseEvent detail = null ;
		CompositeResponse response = fetchResponse(juvnum);
		if( response != null )
		{
			detail = (JuvenileProfileDetailResponseEvent)
					MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
		}

		Map dataMap = MessageUtil.groupByTopic(response);
		if( detail == null )
		{ // ERROR OCCURRED
		}

		// populate the header form from the event and put in session
		UIJuvenileHelper.putHeaderForm(aRequest, detail);

		// initialize JuvenileCasefileForm
		JuvenileCasefileForm form = new JuvenileCasefileForm();

		String casefileId = (String)taskState.get(PDJuvenileCaseConstants.CASEFILEID_PARAM);
		UIJuvenileCaseworkHelper.populateJuvenileCasefileForm(form, casefileId);

		HttpSession session = aRequest.getSession();
		session.setAttribute("juvenileCasefileForm", form);
	}

	/*
	 * 
	 */
	private CompositeResponse fetchResponse(String juvenileNum)
	{
		GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent)
				EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

		requestEvent.setJuvenileNum(juvenileNum);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse replyEvent = (CompositeResponse)dispatch.getReply();
		ReturnException returnException = (ReturnException)
				MessageUtil.filterComposite(replyEvent, ReturnException.class);
		if( returnException != null )
		{
			return null;
		}
		
		return replyEvent;
	}
}
