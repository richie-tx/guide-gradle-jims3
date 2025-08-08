// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\DisplayCasefileClosingRejectionAction.java

package ui.juvenilecase.casefile.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.casefile.form.CasefileClosingForm;

public class DisplayCasefileClosingRejectionAction extends LookupDispatchAction
{

    /**
     * @roseuid 4396047F0206
     */
    public DisplayCasefileClosingRejectionAction()
    {

    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CasefileClosingForm myClosingForm = (CasefileClosingForm) aForm;
        myClosingForm.setRejectReason("");
        return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CasefileClosingForm myClosingForm = (CasefileClosingForm) aForm;
        myClosingForm.setAction(UIConstants.UPDATE_SUMMARY);
        return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
    }

    public ActionForward displayCasefileClosingRejection(ActionMapping aMapping, ActionForm aForm,
            HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        CasefileClosingForm myClosingForm = (CasefileClosingForm) aForm;
        myClosingForm.clearActions();
        myClosingForm.setAction(UIConstants.UPDATE_SUMMARY);
        myClosingForm.setSecondaryAction("");
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4395C2370351
     */
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.cancel", "cancel");
        buttonMap.put("button.back", "back");
        buttonMap.put("button.link", "displayCasefileClosingRejection");
        buttonMap.put("button.next", "next");
        return buttonMap;
    }
}
