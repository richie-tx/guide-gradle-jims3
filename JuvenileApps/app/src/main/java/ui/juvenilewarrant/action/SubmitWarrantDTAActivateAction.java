package ui.juvenilewarrant.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.juvenilewarrant.ActivateDirectiveToApprehendEvent;
import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SubmitWarrantDTAActivateAction extends Action
{

	/**
	 * @roseuid 416D2B04019D
	 */
	public SubmitWarrantDTAActivateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 416D2A4F0393
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
		ActivateDirectiveToApprehendEvent requestEvent =
			(ActivateDirectiveToApprehendEvent) EventFactory.getInstance(
				JuvenileWarrantControllerServiceNames.ACTIVATEDIRECTIVETOAPPREHEND);

		// Setting Activation Date to Current Date/Time
		String warrantActivateDate = null;
		Date todayDate = new Date();		
		jwForm.setWarrantActivationDate(todayDate);
				
		requestEvent.setWarrantNum(jwForm.getWarrantNum());

		CompositeResponse response = MessageUtil.postRequest(requestEvent);
		
		// Code to handle Active Warrant Error from Persistence Layer
		ActiveWarrantErrorEvent error =
			(ActiveWarrantErrorEvent) MessageUtil.filterComposite(response, ActiveWarrantErrorEvent.class);
		if (error != null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.active.warrant.found"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}

		JuvenileWarrantResponseEvent jwResponseEvent =
			(JuvenileWarrantResponseEvent) MessageUtil.filterComposite(response, JuvenileWarrantResponseEvent.class);

		jwForm.setWarrantActivationStatusId(jwResponseEvent.getWarrantActivationStatusId());
		jwForm.setWarrantSignedStatusId(jwResponseEvent.getWarrantSignedStatusId());
		jwForm.setWarrantStatusId(jwResponseEvent.getWarrantStatusId());		
		
		forward = aMapping.findForward(UIConstants.ACTDTA_SUCCESS);
		
		jwForm.setBackToWarrantUrl(forward.getPath());

		return forward;
	}
}
