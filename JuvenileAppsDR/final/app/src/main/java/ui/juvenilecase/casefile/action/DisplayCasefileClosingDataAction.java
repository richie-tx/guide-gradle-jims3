// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\DisplayCasefileClosingDataAction.java

package ui.juvenilecase.casefile.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.GetSupervisionTypeTJJDMapEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.SupervisionTypeMapResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;

public class DisplayCasefileClosingDataAction extends LookupDispatchAction
{

    /**
     * @roseuid 4396047D00FE
     */
    public DisplayCasefileClosingDataAction()
    {

    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {	
		    return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CasefileClosingForm myClosingForm = (CasefileClosingForm) aForm;
        if (myClosingForm.getControllingReferral() != null && !"".equals(myClosingForm.getControllingReferral() ) ) {
        	JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
        	casefileForm.setControllingReferralNumber(myClosingForm.getControllingReferral());
        }
        return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
    }

    public ActionForward updateClosing(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CasefileClosingForm myClosingForm = (CasefileClosingForm) aForm;
        myClosingForm.setAction(UIConstants.UPDATE);
        myClosingForm.setSecondaryAction("");
        myClosingForm.setSelectedValue("");
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * @param aRequest
     */
    private void sendToErrorPage(HttpServletRequest aRequest, String msg)
    {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
        saveErrors(aRequest, errors);
    }

    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.cancel", "cancel");
        buttonMap.put("button.back", "back");
        buttonMap.put("button.next", "next");
        buttonMap.put("button.updateClosing", "updateClosing");
        return buttonMap;
    }

}// END CLASS

