/*
 * Created on Feb 15, 2006
 *
 */
package ui.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

/**
 * @author Jim Fisher
 *  
 */
public abstract class AbstractLookupResultsTemplateAction extends LookupDispatchAction implements IAction,
        IErrorResultsActionHandler, IResultsActionTemplate
{
    private ActionForward handleResults(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse, CompositeResponse event, Collection data)
    {
        return ActionHelper.handleResults(this, aMapping, aForm, aRequest, aResponse, event, data);
    }

    public final ActionForward handleResponse(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse, String topic, CompositeResponse response)
    {
        return ActionHelper.handleResponse(this, aMapping, aForm, aRequest, aResponse, topic, response);
    }

    protected final ActionForward handleResponse(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse, Class eventFilterClass, CompositeResponse response)
    {
        return ActionHelper.handleResponse(this, aMapping, aForm, aRequest, aResponse, eventFilterClass, response);
    }

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
