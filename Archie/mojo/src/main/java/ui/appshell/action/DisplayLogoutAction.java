package ui.appshell.action;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mojo.km.caching.generic.CacheManager;
import mojo.km.context.ContextManager;
import mojo.km.context.Session;
import mojo.struts.AbstractAction;

public class DisplayLogoutAction extends AbstractAction
{

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
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        //Collection sessionCache = CacheManager.getInstances(Session.class);
        //System.out.println("BEFORE logout session count: " + sessionCache.size());

        String sessionId = ContextManager.currentContext().getCurrentSessionID();

        // remove session from cache
        CacheManager.remove(Session.class, sessionId);

        // release context from thread pool
        ContextManager.clearContext();
        ContextManager.releaseCurrent();

        // clear http session
        HttpSession session = request.getSession();
        session.invalidate();

        //sessionCache = CacheManager.getInstances(Session.class);

        //System.out.println("AFTER logout session count: " + sessionCache.size());

        return mapping.findForward("displayLogin");
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
