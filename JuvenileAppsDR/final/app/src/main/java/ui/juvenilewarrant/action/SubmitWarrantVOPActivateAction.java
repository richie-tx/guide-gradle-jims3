package ui.juvenilewarrant.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenilewarrant.ActivateViolationOfProbationEvent;

import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.security.authentication.form.LoginForm;

/**
 * @author hrodriguez
 *  
 */
public class SubmitWarrantVOPActivateAction extends LookupDispatchAction
{

    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.back", UIConstants.BACK);
        buttonMap.put("button.cancel", UIConstants.CANCEL);
        buttonMap.put("button.finish", UIConstants.FINISH);
        buttonMap.put("button.mainPage", "mainPage");
        return buttonMap;
    }

    public ActionForward mainPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception
    {
    	 HttpSession session = aRequest.getSession();
  	     LoginForm myForm = (LoginForm) session.getAttribute("loginForm");    
         	if (myForm == null)
         	{    
         		myForm = new LoginForm();
         		session.setAttribute("loginForm", myForm);
         	}
         	
       return aMapping.findForward(UIConstants.MAIN_PAGE);
    }

    /**
     * @roseuid 4211008E006A
     */
    public SubmitWarrantVOPActivateAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4210F85F03A8
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        // TODO Need to handle back and cancel

        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);

        JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;

        ActivateViolationOfProbationEvent event = (ActivateViolationOfProbationEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.ACTIVATEVIOLATIONOFPROBATION);

        event.setWarrantNum(form.getWarrantNum());
        event.setWarrantActivationStatus(form.getWarrantActivationStatusId());
        event.setUnSendNotSignedReason(form.getUnsendNotSignedReason());

        MessageUtil.postRequest(event);

        form.setAction(UIConstants.CONFIRM);

        return forward;
    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
        form.setWarrantTypeUI(UIConstants.ACTVOP_SUMMARY);
        return aMapping.findForward(UIConstants.BACK);
    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
        form.setWarrantTypeUI(UIConstants.ACTVOP_SUMMARY);
        return aMapping.findForward(UIConstants.CANCEL);
    }
}