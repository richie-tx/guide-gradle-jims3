/*
 * Created on Feb 7, 2006
 *
 */
package ui.action;

import javax.servlet.http.HttpServletRequest;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import org.apache.struts.actions.LookupDispatchAction;

/**
 * @author Jim Fisher
 *  
 */
public abstract class AbstractLookupDispatchAction extends LookupDispatchAction implements IAction, IErrorResultsActionHandler
{
    public void setUnrecoverableError(HttpServletRequest aRequest)
    {
        ActionHelper.setUnrecoverableError(aRequest);
    }

    public void setRecoverableError(HttpServletRequest aRequest)
    {
        ActionHelper.setRecoverableError(aRequest);
    }

    public CompositeResponse handleRequest(RequestEvent event)
    {
        return ActionHelper.handleRequest(event);
    }
}
