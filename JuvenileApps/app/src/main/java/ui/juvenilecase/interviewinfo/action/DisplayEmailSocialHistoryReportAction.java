package ui.juvenilecase.interviewinfo.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.interviewinfo.EmailInterviewReportEvent;
import messaging.interviewinfo.reply.InterviewReportHeaderResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileInterviewInfoControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;

/**
 * @author awidjaja
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences -
 * Java - Code Style - Code Templates
 */
public class DisplayEmailSocialHistoryReportAction extends LookupDispatchAction
{
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.link", "link");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        keyMap.put("button.send", "sendEmail");
        keyMap.put("button.next", "showSummary");
        keyMap.put("button.interviewList", "interviewList");
        return keyMap;
    }
    
    public ActionForward showSummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	aRequest.setAttribute("status", UIConstants.SUMMARY);
    	return aMapping.findForward(UIConstants.NEXT);
    }

    public ActionForward interviewList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	return aMapping.findForward(UIConstants.MAIN_PAGE);
    }
    
    public ActionForward sendEmail(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;

        EmailInterviewReportEvent event = (EmailInterviewReportEvent) EventFactory
                .getInstance(JuvenileInterviewInfoControllerServiceNames.EMAILINTERVIEWREPORT);
        event.setReportId(form.getCurrentReportHeaderInfo().getReportId());
        event.setToAddress(form.getEmailAddress());
        event.setSubject(form.getEmailSubject());

        MessageUtil.postRequest(event);
        
        // Adding record in activity table
        HttpSession session = aRequest.getSession();
        JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) session.getAttribute("juvenileCasefileForm");
	if(casefileForm != null)
	{
	    UIJuvenileHelper.createActivity(casefileForm.getSupervisionNum(), ActivityConstants.SOCIAL_HISTORY_EMAILED_TO_DEFENSE_ATTORNEY, "");        
	}
        
        aRequest.setAttribute("status", UIConstants.CONFIRM);
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

        JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;

        String reportId = aRequest.getParameter("reportId");

        for (Iterator iter = form.getReportHistory().iterator(); iter.hasNext();)
        {
            InterviewReportHeaderResponseEvent interviewReportHeader = (InterviewReportHeaderResponseEvent) iter.next();
            if (interviewReportHeader.getReportId().equals(reportId))
            {
                form.setCurrentReportHeaderInfo(interviewReportHeader);
            }

        }

        if (reportId != null)
        {
            //
        }

        String SUBJECT_START = "The attached Social History Report has been generated for ";
        StringBuffer sb = new StringBuffer();
        sb.append(SUBJECT_START);

        sb.append(form.getJuvenileName().getFormattedName());
        sb.append(" .");

        form.setEmailSubject(sb.toString());

        return aMapping.findForward(UIConstants.SUCCESS);
    }

}