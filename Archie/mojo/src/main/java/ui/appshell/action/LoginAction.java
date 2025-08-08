/**
 * Created on Apr 20, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.appshell.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import messaging.appshell.UserEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.securitytransactionsevents.LoginEvent;
import mojo.messaging.securitytransactionsevents.reply.LoginResponseEvent;
import mojo.struts.AbstractAction;
import mojo.km.dispatch.EventManager;
import mojo.km.context.ContextManager;
import mojo.km.context.Session;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author dapte
 * 
 * Handles user authentication.
 */
public class LoginAction extends AbstractAction
{

    static public final String USERINFO = "userInfo";

    /**
     * Process the specified HTTP request, and create the corresponding HTTP response (or forward to another web component that
     * will create it). Return an <code>ActionForward</code> instance describing where and how control should be forwarded, or
     * <code>null</code> if the response has already been completed.
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm aForm, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LoginForm form = (LoginForm) aForm;

        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setUserID(form.getUserID());
        loginEvent.setPassword(form.getPassword());

        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(loginEvent);
        IEvent lReply = EventManager.getSharedInstance(EventManager.REQUEST).getReply();
        if (lReply instanceof mojo.km.messaging.noop.NoReply)
        {
            ActionErrors errors = new ActionErrors();
            ActionMessage error = new ActionMessage("error.profile.general");
            errors.add(ActionErrors.GLOBAL_MESSAGE, error);
            saveErrors(request, errors);
            return (mapping.findForward("error"));
        }
        else
        {
            CompositeResponse lResponses = (CompositeResponse) lReply;

            LoginResponseEvent lResponse = (LoginResponseEvent) MessageUtil.filterComposite(lResponses, LoginResponseEvent.class);

            HttpSession session = request.getSession();

            Session frameworkSession = ContextManager.getSession();

            frameworkSession.put(lResponse.getUserProfileSessionKey(), lResponse.getUserProfileImpl());
            frameworkSession.put("ISecurityManager", lResponse.getSecurityManager());
            frameworkSession.setUserID(form.getUserID());
            frameworkSession.setUserName(lResponse.getUserDisplayName());
            if (lResponse == null || lResponse.isError())
            {
                // todo: reset the login attribute from the session

                session.setAttribute("USERLOGIN", "FAILURE");
                session.removeAttribute("userInfo");
                ActionErrors errors = new ActionErrors();
                ActionMessage error = new ActionMessage(lResponse.getErrorType());
                errors.add(ActionErrors.GLOBAL_MESSAGE, error);
                saveErrors(request, errors);
                return mapping.findForward("error");
            }
            else
            {
                // user logged in successfully , get the name from the reply event, forward to success here...
                session.setAttribute("USERLOGIN", "SUCCESS");
                UserEvent user = new UserEvent(form.getUserID().toUpperCase(), lResponse.getUserDisplayName(), lResponse
                        .getServer());
                session.setAttribute("userInfo", user);
                return mapping.findForward("success");
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.struts.AbstractAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
        // TODO Auto-generated method stub

    }
}
