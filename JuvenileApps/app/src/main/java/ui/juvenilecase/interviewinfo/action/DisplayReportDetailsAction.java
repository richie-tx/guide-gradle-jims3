package ui.juvenilecase.interviewinfo.action;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.interviewinfo.GetInterviewReportEvent;
import messaging.interviewinfo.reply.InterviewReportResponseEvent;
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
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayReportDetailsAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.link", "displayReportDetails");
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
	
	
	
	public ActionForward displayReportDetails(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
		
		String reportId = aRequest.getParameter("reportId");
		
		if(reportId != null)
		{
			GetInterviewReportEvent reqEvent = new GetInterviewReportEvent();
			reqEvent.setReportId(reportId);
			
	        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	        dispatch.postEvent(reqEvent);
	
	        CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
	        InterviewReportResponseEvent aRespEvent = (InterviewReportResponseEvent) MessageUtil.filterComposite(compResponse, InterviewReportResponseEvent.class);
	        
	        try{
	            aResponse.setContentType("application/x-file-download");
	            aResponse.setHeader("Content-disposition", "attachment; filename=" + aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/")+1) + ".pdf");
	            aResponse.setHeader("Cache-Control", "max-age=" + 1200);
	            aResponse.setContentLength(aRespEvent.getContent().length);
	            aResponse.resetBuffer();
				OutputStream os = aResponse.getOutputStream();
				os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
				os.flush();
				os.close();
					
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
		}
        	
		return null;
	}


}