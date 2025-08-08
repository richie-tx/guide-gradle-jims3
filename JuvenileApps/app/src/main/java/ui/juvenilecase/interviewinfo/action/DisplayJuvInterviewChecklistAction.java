package ui.juvenilecase.interviewinfo.action;
//import java.util.Collection;
//import java.util.Collections;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
//import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import messaging.interviewinfo.GetInterviewTaskListEvent;
//import messaging.interviewinfo.reply.InterviewTaskResponseEvent;
//import mojo.km.dispatch.EventManager;
//import mojo.km.dispatch.IDispatch;
//import mojo.km.messaging.Composite.CompositeResponse;
//import mojo.km.utilities.MessageUtil;
import messaging.interviewinfo.GetInterviewTaskListEvent;
import messaging.interviewinfo.reply.InterviewTaskResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;

/**
 * 
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayJuvInterviewChecklistAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.interviewChecklist", "displayInterviewChecklist");
		return keyMap;
	}
	
	public ActionForward displayInterviewChecklist(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
		
		GetInterviewTaskListEvent event = new GetInterviewTaskListEvent();
		
		event.setInterviewId( form.getCurrentInterview().getInterviewId() );

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		Collection tasks = MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), InterviewTaskResponseEvent.class );
		Collections.sort((List)tasks);
		
		form.getCurrentInterview().setInterviewTasks(tasks);
		
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
		
		
	}
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
	
	public ActionForward back(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}
		
	
	
	
		
	
}