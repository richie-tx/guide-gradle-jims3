package ui.juvenilewarrant.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenilewarrant.UpdateJuvenileReleaseToJPInfoEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.security.authentication.form.LoginForm;

/**
 * @author ryoung
 *
 */
public class SubmitReleaseToJPAction extends LookupDispatchAction
{

	/**
	 * @roseuid 41FFE161003E
	 */
	public SubmitReleaseToJPAction()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.mainPage", "mainPage");

		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 41FFC64F007F
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);

		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

		UpdateJuvenileReleaseToJPInfoEvent requestEvent =
			(UpdateJuvenileReleaseToJPInfoEvent) EventFactory.getInstance(
				JuvenileWarrantControllerServiceNames.UPDATEJUVENILERELEASETOJPINFO);

		this.setEvent(jwForm, requestEvent);

		MessageUtil.postRequest(requestEvent);

		// set action
		jwForm.setAction(UIConstants.CONFIRM);

		return forward;
	}
	/**
	 * 
	 * @param aForm
	 * @param anEvent
	 */
	private void setEvent(JuvenileWarrantForm aForm, UpdateJuvenileReleaseToJPInfoEvent anEvent)
	{
		anEvent.setWarrantNum(aForm.getWarrantNum());
		anEvent.setTransferOfficerId(aForm.getTransferOfficerId());
		anEvent.setTransferOfficerDepartmentId(aForm.getTransferOfficerDepartmentId());
		anEvent.setTransferDate(aForm.getTransferCustodyDate());
		anEvent.setTransferLocation(aForm.getTransferLocationId());
	}
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
		jwForm.setWarrantTypeUI("summary");
		return aMapping.findForward(UIConstants.BACK);
	}
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		return aMapping.findForward(UIConstants.CANCEL);
	}
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward mainPage(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{      	
		return aMapping.findForward(UIConstants.MAIN_PAGE);
	}

}
