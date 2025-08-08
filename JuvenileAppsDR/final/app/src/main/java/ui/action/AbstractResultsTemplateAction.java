/*
 * Created on Feb 1, 2006
 */
package ui.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.messaging.Composite.CompositeResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Jim Fisher
 *
 * This class, an implementation of the "Template Method" pattern, provides a 
 * contract for handling query results in a Struts LookupDispatchAction that vary 
 * in count by zero, single, or multiple returned records.
 *  
 * For reference, the "Template Method" pattern defines the skeleton of an 
 * algorithm in terms of abstract operations which subclasses override to 
 * provide concrete behavior.
 */
public abstract class AbstractResultsTemplateAction extends AbstractAction implements IResultsActionTemplate
{
	private ActionForward handleResults(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		CompositeResponse event,
		Collection data)
	{
		return ActionHelper.handleResults(this, aMapping, aForm, aRequest, aResponse, event, data);
	}

	public final ActionForward handleResponse(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		String topic,
		CompositeResponse response)
	{
		return ActionHelper.handleResponse(this, aMapping, aForm, aRequest, aResponse, topic, response);
	}

	protected final ActionForward handleResponse(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		Class eventFilterClass,
		CompositeResponse response)
	{
		return ActionHelper.handleResponse(this, aMapping, aForm, aRequest, aResponse, eventFilterClass, response);

	}
}
