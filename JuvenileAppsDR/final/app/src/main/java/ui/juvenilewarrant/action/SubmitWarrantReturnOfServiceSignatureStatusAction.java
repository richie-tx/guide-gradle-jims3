package ui.juvenilewarrant.action;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenilewarrant.UpdateWarrantServiceSignatureStatusEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
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
public class SubmitWarrantReturnOfServiceSignatureStatusAction extends LookupDispatchAction
{

    /**
     * @roseuid 41FFE0410271
     */
    public SubmitWarrantReturnOfServiceSignatureStatusAction()
    {

    }

    protected Map getKeyMethodMap()
    {
        Map buttonMap = new Hashtable();
        buttonMap.put("button.submit", UIConstants.SUBMIT);
        buttonMap.put("button.cancel", UIConstants.CANCEL);
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
         	
        return  aMapping.findForward(UIConstants.MAIN_PAGE);

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 41FFE02B033D
     */
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        UpdateWarrantServiceSignatureStatusEvent requestEvent = (UpdateWarrantServiceSignatureStatusEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.UPDATEWARRANTSERVICESIGNATURESTATUS);

        // getting input fields from form and setting on requestEvent
        requestEvent.setWarrantNum(jwForm.getWarrantNum());
        CompositeResponse response = MessageUtil.postRequest(requestEvent);

        JuvenileWarrantResponseEvent warrantEvent = (JuvenileWarrantResponseEvent) MessageUtil.filterComposite(response,
                JuvenileWarrantResponseEvent.class);

        jwForm.setServiceReturnSignatureStatusId(warrantEvent.getServiceReturnSignatureStatusId());
        jwForm.setServiceReturnSignatureStatus(warrantEvent.getServiceReturnSignatureStatus());

        forward = aMapping.findForward(UIConstants.SUCCESS);
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
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CANCEL);
    }
}
