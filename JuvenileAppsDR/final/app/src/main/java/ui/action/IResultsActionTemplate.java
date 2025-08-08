/*
 * Created on Feb 15, 2006
 *
 */
package ui.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author jfisher
 *
 */
public interface IResultsActionTemplate
{
	public ActionForward handleZeroResults(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		CompositeResponse event);

	public ActionForward handleSingleResult(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		CompositeResponse event,
		IEvent data);

	public ActionForward handleMultipleResults(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		CompositeResponse event,
		Collection data);

	public ActionForward handleResponse(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		String topic,
		CompositeResponse response);
}
