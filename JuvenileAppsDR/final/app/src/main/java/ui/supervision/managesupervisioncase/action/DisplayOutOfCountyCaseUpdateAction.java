//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\managesupervisioncase\\action\\DisplayOutOfCountyCaseUpdateAction.java

package ui.supervision.managesupervisioncase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OutOfCountyCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.managesupervisioncase.form.OutOfCountyCaseForm;

public class DisplayOutOfCountyCaseUpdateAction extends Action
{

	/**
	 * @roseuid 4443EFD302DF
	 */
	public DisplayOutOfCountyCaseUpdateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 443D0CCA005C
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		OutOfCountyCaseForm caseForm = (OutOfCountyCaseForm) aForm;
		// get case types
		IEvent request = EventFactory.getInstance(OutOfCountyCaseControllerServiceNames.GETOUTOFCOUNTYCASETYPES);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(request);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);


		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
	}
}
