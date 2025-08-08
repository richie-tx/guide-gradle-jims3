/*
 * Created on Feb 17, 2005
 *
 */
package ui.juvenilewarrant.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.UpdateJuvenileWarrantEvent;
import mojo.km.messaging.EventFactory;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author jfisher
 *
 */
public class SubmitWarrantOICUpdateAction extends Action
{

	/**
		 * @param aMapping
		 * @param aForm
		 * @param aRequest
		 * @param aResponse
		 * @return ActionForward
		 * @throws Exception
		 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		ActionForward forward = aMapping.findForward(UIConstants.FAILURE);

		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

		jwForm.setWarrantTypeUI(UIConstants.REQSIGOICCONFIRM_SUCCESS);

		UpdateJuvenileWarrantEvent requestEvent =
			(UpdateJuvenileWarrantEvent) EventFactory.getInstance(
				JuvenileWarrantControllerServiceNames.UPDATEJUVENILEWARRANT);

		this.setEvent(jwForm, requestEvent);

//		boolean success = true;
//
//		if (success == true)
//		{
			forward = aMapping.findForward(UIConstants.SUCCESS);
//		}

		return forward;
	}
	/**
	 * 
	 * @param aForm
	 * @param anEvent
	 */
	private void setEvent(JuvenileWarrantForm aForm, UpdateJuvenileWarrantEvent anEvent)
	{
		anEvent.setWarrantNum(aForm.getWarrantNum());

	}
}
