/*
 * Created on Feb 15, 2006
 *
 */
package ui.action;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

/**
 * @author jfisher
 *
 */
public class ActionHelper
{
	private static final String UNRECOVERABLE_ERROR = "unrecoverableError";
	private static final String RECOVERABLE_ERROR = "recoverableError";

	static CompositeResponse handleRequest(RequestEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		dispatch.postEvent(event);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		MessageUtil.processReturnException(response);

		return response;
	}

	static void setUnrecoverableError(HttpServletRequest aRequest)
	{
		aRequest.setAttribute(UNRECOVERABLE_ERROR, "true");
	}

	static void setRecoverableError(HttpServletRequest aRequest)
	{
		aRequest.setAttribute(RECOVERABLE_ERROR, "true");
	}

	static ActionForward handleResults(
		IResultsActionTemplate template,
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		CompositeResponse event,
		Collection data)
	{
		ActionForward forward = null;
		if (data == null || data.size() == 0)
		{
			forward = template.handleZeroResults(aMapping, aForm, aRequest, aResponse, event);
		}
		else if (data.size() == 1)
		{
			Iterator i = data.iterator();
			IEvent responseEvent = (IEvent) i.next();
			forward = template.handleSingleResult(aMapping, aForm, aRequest, aResponse, event, responseEvent);
		}
		else
		{
			forward = template.handleMultipleResults(aMapping, aForm, aRequest, aResponse, event, data);
		}
		return forward;
	}

	static ActionForward handleResponse(
		IResultsActionTemplate template,
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		String topic,
		CompositeResponse response)
	{
		MessageUtil.processReturnException(response);

		Collection events = MessageUtil.compositeToCollection(response, topic);

		return ActionHelper.handleResults(template, aMapping, aForm, aRequest, aResponse, response, events);
	}

	static final ActionForward handleResponse(
		IResultsActionTemplate template,
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		Class eventFilterClass,
		CompositeResponse response)
	{
		MessageUtil.processReturnException(response);

		Collection events = MessageUtil.compositeToCollection(response, eventFilterClass);

		return ActionHelper.handleResults(template, aMapping, aForm, aRequest, aResponse, response, events);
	}
}
