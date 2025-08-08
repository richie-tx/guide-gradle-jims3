package ui.juvenilecase.interviewinfo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
 * 
 * @author awidjaja
 *
 * This action is for listing interviews by casefiles (used in the Profile view of
 * Interviews) 
 */
public class DisplayJuvenileProfileReportHistoryAction extends LookupDispatchAction
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
		
		GetInterviewReportsEvent reqEvent = new GetInterviewReportsEvent();
		
		//Get casefileId from juvenileCasefileForm that's in session
		HttpSession session = aRequest.getSession();
		JuvenileCasefileForm casefileForm = 
			(JuvenileCasefileForm) session.getAttribute("juvenileCasefileForm");
		
		reqEvent.setJuvenileId( form.getJuvenileNum() );
		
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(reqEvent);

        CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
        
        
        Collection reportHistory = MessageUtil.compositeToCollection(compResponse, InterviewReportHeaderResponseEvent.class);
        Collections.sort((List)reportHistory);
        
        HashMap reportMap = new HashMap();
        
        for(Iterator iter = reportHistory.iterator();iter.hasNext();)
        {
        	InterviewReportHeaderResponseEvent reportHeader = 
        		(InterviewReportHeaderResponseEvent) iter.next();
        	if(reportMap.containsKey(reportHeader.getCasefileId()))
        	{
        		Collection reports = (Collection) reportMap.get(reportHeader.getCasefileId());
        		reports.add(reportHeader);
        	}
        	else
        	{
        		Collection reports = new ArrayList();
        		reports.add(reportHeader);
        		reportMap.put(reportHeader.getCasefileId(), reports);
        		
        	}
        }
        form.setReportMap(reportMap);
        form.setReportHistory(reportHistory);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.link", "link");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}
	
	
	
		
	
}