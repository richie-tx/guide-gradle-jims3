//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\managesupervisioncase\\action\\DisplayOutOfCountyCaseReactivateSummaryAction.java

package ui.supervision.managesupervisioncase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.managesupervisioncase.ValidateOutOfCountyCaseEvent;
import messaging.managesupervisioncase.reply.ValidateResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OutOfCountyCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.managesupervisioncase.form.OutOfCountyCaseForm;

public class DisplayOutOfCountyCaseReactivateSummaryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 4443EFCF01FD
	 */
	public DisplayOutOfCountyCaseReactivateSummaryAction()
	{

	}
	/**
		   * @see LookupDispatchAction#getKeyMethodMap()
		   * @return Map
		   */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		OutOfCountyCaseForm ooc = (OutOfCountyCaseForm) aForm;

		String action = ooc.getAction();
		if (action.equals(UIConstants.PRETRIAL_REACTIVATE))
		{
			forward = aMapping.findForward(UIConstants.REACTIVATE_PRETRIAL_SUCCESS);
		}
		else
		if (action.equals(UIConstants.REACTIVATE))
		{
			forward = aMapping.findForward(UIConstants.REACTIVATE_SUCCESS);
			ValidateOutOfCountyCaseEvent request =
				(ValidateOutOfCountyCaseEvent) EventFactory.getInstance(
					OutOfCountyCaseControllerServiceNames.VALIDATEOUTOFCOUNTYCASE);

			// set the criteria from the form
			request.setCJISNum(ooc.getCjis());

			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(request);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);

			ValidateResponseEvent response =
				(ValidateResponseEvent) MessageUtil
					.compositeToCollection(compositeResponse, ValidateResponseEvent.class)
					.iterator()
					.next();

			if (response != null)
			{
				if (!response.isValid())
				{
					//sendToErrorPage(aRequest, response.getMessage());
					ooc.setMessage(response.getMessage());
					aRequest.setAttribute("error", ooc);
				}
			}
		}
		return forward;
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
}
