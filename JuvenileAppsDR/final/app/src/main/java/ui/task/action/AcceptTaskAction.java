/*
 * Created on December 12, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.task.action;

import java.util.Iterator ;
import java.util.Map ;

import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import messaging.task.GetTaskEvent ;
import messaging.task.UpdateTaskStatusEvent ;
import messaging.task.reply.TaskResponseEvent ;
import mojo.km.dispatch.EventManager ;
import mojo.km.dispatch.IDispatch ;
import mojo.km.messaging.EventFactory ;
import mojo.km.messaging.Composite.CompositeResponse ;
import mojo.km.utilities.MessageUtil ;
import naming.TaskControllerServiceNames ;
import naming.UIConstants ;

import org.apache.struts.action.Action ;
import org.apache.struts.action.ActionMessage ;
import org.apache.struts.action.ActionErrors ;
import org.apache.struts.action.ActionForm ;
import org.apache.struts.action.ActionForward ;
import org.apache.struts.action.ActionMapping ;

import ui.security.authentication.form.LoginForm ;

/**
 * @author mchowdhury
 * 
 */
public class AcceptTaskAction extends Action
{
	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		LoginForm loginForm = (LoginForm)aForm ;
		String taskID = loginForm.getTaskId().trim() ;

		Iterator taskIter = loginForm.getTaskList().iterator() ;
		TaskResponseEvent resp = null ;
		while( taskIter.hasNext() )
		{
			resp = (TaskResponseEvent)taskIter.next() ;
			if( taskID.equalsIgnoreCase( resp.getTask().getTaskId() ) )
			{
				break ;
			}
			else
			{
				resp = null ;
			}
		}
		
		if( resp == null && taskID != null && taskID.length() > 0 )
		{
			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
			GetTaskEvent getTask = (GetTaskEvent)
					EventFactory.getInstance( TaskControllerServiceNames.GETTASK ) ;
			getTask.setTaskId( taskID ) ;
			dispatch.postEvent( getTask ) ;
			CompositeResponse response = (CompositeResponse)dispatch.getReply() ;
			resp = (TaskResponseEvent)MessageUtil.filterComposite( response, TaskResponseEvent.class ) ;
		}
		
		if( resp == null )
		{
			sendToErrorPage( aRequest, "error.generic", "Problem encountered while loading task." ) ;
			return null ;
		}
		
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		if( resp.getTask().getStatusCode().equalsIgnoreCase( UIConstants.SUBMITTED_STATUS_ID ) )
		{
			UpdateTaskStatusEvent updateEvent = (UpdateTaskStatusEvent)
					EventFactory.getInstance( TaskControllerServiceNames.UPDATETASKSTATUS ) ;
			updateEvent.setTaskId( taskID ) ;
			updateEvent.setStatusCode( UIConstants.ACCEPTED_STATUS_ID ) ;
			
			dispatch.postEvent( updateEvent ) ;
			CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply() ;
			Map map = MessageUtil.groupByTopic( compositeResponse ) ;
			MessageUtil.processReturnException( map ) ;
		}
		
		return this.dispatch( aRequest, resp.getTask().getUrl() ) ;
	}

	/*
	 * @param aRequest
	 * @param msgKey
	 * @param param
	 */
	protected void sendToErrorPage( HttpServletRequest aRequest, String msgKey, String param )
	{
		ActionErrors errors = new ActionErrors() ;
		errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( msgKey, param ) ) ;
		saveErrors( aRequest, errors ) ;
	}

	/**
	 * @param request
	 * @param url
	 * @return
	 */
	private ActionForward dispatch( HttpServletRequest request, String url )
	{
		StringBuffer forward = new StringBuffer( request.getScheme() ) ;
		forward.append( "://" ) ;
		forward.append( request.getServerName() ) ;
		forward.append( ":" ) ;
		forward.append( request.getServerPort() ) ;
		forward.append( url ) ;
		String fwdStr = forward.toString() ;
		ActionForward newForward = new ActionForward( fwdStr ) ;
		newForward.setRedirect( true ) ;
		
		return newForward ;
	}
}
