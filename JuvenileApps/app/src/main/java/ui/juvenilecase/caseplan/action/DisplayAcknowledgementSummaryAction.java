package ui.juvenilecase.caseplan.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.util.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.caseplan.form.CaseplanForm;

/**
 * 
 * @author ugopinath
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class DisplayAcknowledgementSummaryAction extends JIMSBaseAction
{
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42F79A090282
     */
    public ActionForward displayAckSummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CaseplanForm form = (CaseplanForm) aForm;
        form.getCurrentJuvenileAcknowledgment().setEntryDate(DateUtil.stringToDate(form.getCurrentJuvenileAcknowledgment().getEntryDateStr(), DateUtil.DATE_FMT_1));
        form.getCurrentGuardianAcknowledgment().setEntryDate(DateUtil.stringToDate(form.getCurrentGuardianAcknowledgment().getEntryDateStr(), DateUtil.DATE_FMT_1));
        form.setStatus(UIConstants.SUMMARY);       
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
        keyMap.put("button.next", "displayAckSummary");

    }
}