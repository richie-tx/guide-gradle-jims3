//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilewarrant\\action\\SubmitWarrantInactivateAction.java
//
//HRodriguez - 02/16/2005 - Create action 
//LDeen 	 - 03/25/2005 - Revise action and create new method setWarrantInactivateProperties in jwForm
package ui.juvenilewarrant.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenilewarrant.InactivateJuvenileWarrantEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
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
 * @author ryoung
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SubmitWarrantInactivateAction extends LookupDispatchAction
{

    /**
     * @roseuid 41F7C397020B
     */
    public SubmitWarrantInactivateAction()
    {
    }

    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.finish", UIConstants.FINISH);
        buttonMap.put("button.next", UIConstants.NEXT);        
        buttonMap.put("button.cancel", UIConstants.CANCEL);
        buttonMap.put("button.mainPage", "mainPage");
        return buttonMap;
    }

    public ActionForward mainPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception
    {
         	
        return aMapping.findForward(UIConstants.MAIN_PAGE);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 41F7B68A0279
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = null;
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        InactivateJuvenileWarrantEvent requestEvent = (InactivateJuvenileWarrantEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.INACTIVATEJUVENILEWARRANT);

        requestEvent.setWarrantNum(jwForm.getWarrantNum());
        requestEvent.setRecallReason(jwForm.getRecallReasonId());

        CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);

        JuvenileWarrantResponseEvent warrantResponse = (JuvenileWarrantResponseEvent) MessageUtil.filterComposite(replyEvent,
                JuvenileWarrantResponseEvent.class);
        jwForm.setWarrantInactivateProperties(warrantResponse);
        jwForm.setAction("");        
        forward = aMapping.findForward(UIConstants.SUCCESS);
        jwForm.setBackToWarrantUrl(forward.getPath());
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
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = null;
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
        jwForm.setAction("summary");
        forward = aMapping.findForward(UIConstants.NEXT);
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
        ActionForward forward = null;
        forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }
}
