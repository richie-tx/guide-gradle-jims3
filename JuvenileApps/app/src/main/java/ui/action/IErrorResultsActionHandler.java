/*
 * Created on Feb 15, 2006
 *
 */
package ui.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;

import mojo.km.messaging.Composite.CompositeResponse;

/**
 * @author jfisher
 *
 */
public interface IErrorResultsActionHandler
{
	public void processBusinessExceptions(HttpServletRequest aRequest, CompositeResponse response, ActionErrors errors);

	public void setUnrecoverableError(HttpServletRequest aRequest);

	public void setRecoverableError(HttpServletRequest aRequest);
}
