package ui.juvenilecase.interviewinfo.action;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.interviewinfo.CreateSocialHistoryReportEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.interviewinfo.form.SocialHistoryForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitSocialHistoryDataAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.generateReport", "generateReport");
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
	
	
	public ActionForward generateReport(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SocialHistoryForm form = (SocialHistoryForm) aForm;
		
		CreateSocialHistoryReportEvent reqEvent = new CreateSocialHistoryReportEvent();
		reqEvent.setCasefileId(form.getCasefileId());
		reqEvent.setSocialHistoryReportDataTO( form.getSocialHistoryData() );
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(reqEvent);
        
        CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
        ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);

        
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
        
        return null;
        
	 }
	
}