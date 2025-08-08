package ui.juvenilecase.interviewinfo.action;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.interviewinfo.GetInterviewReportsEvent;
import messaging.interviewinfo.reply.InterviewReportHeaderResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayReportHistoryAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.link", "link");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
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
		
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	
	
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
		
		GetInterviewReportsEvent reqEvent = new GetInterviewReportsEvent();
		
		//Get casefileId from juvenileCasefileForm that's in session
		HttpSession session = aRequest.getSession();
		JuvenileCasefileForm casefileForm = 
			(JuvenileCasefileForm) session.getAttribute("juvenileCasefileForm");
		form.setCasefileId(casefileForm.getSupervisionNum());
		reqEvent.setCasefileId( form.getCasefileId() );
		
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(reqEvent);

        CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
        
        
        Collection reportHistory = MessageUtil.compositeToCollection(compResponse, InterviewReportHeaderResponseEvent.class);
        Collections.sort((List)reportHistory);
        form.setReportHistory(reportHistory);
        	
		return aMapping.findForward(UIConstants.SUCCESS);
	}


}