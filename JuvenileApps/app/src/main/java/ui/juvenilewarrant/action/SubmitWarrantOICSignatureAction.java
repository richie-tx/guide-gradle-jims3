package ui.juvenilewarrant.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.juvenilewarrant.UpdateOICSignatureStatusEvent;
import messaging.juvenilewarrant.reply.NoWarrantStatusErrorEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author ryoung
 *
 */
public class SubmitWarrantOICSignatureAction extends LookupDispatchAction
{
	// TODO Put this in a constants file.
	private final static String NO_PENDING_WARRANTS = "error.juvenilewarrant.nopendingwarrants";
	private final static String UNKNOWN_ERROR = "error.common";

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.back", UIConstants.BACK);
		buttonMap.put("button.cancel", UIConstants.CANCEL);
		buttonMap.put("button.finish", UIConstants.FINISH);
		buttonMap.put("button.mainPage","mainPage");
			return buttonMap;
		}
	
		public ActionForward mainPage(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
				throws Exception
			{
				ActionForward forward = aMapping.findForward(UIConstants.MAIN_PAGE);
				return forward;
			}
	/**
	 * @roseuid 41B888C0021F
	 */
	public SubmitWarrantOICSignatureAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 41B8887E0334
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);

		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

		jwForm.setWarrantTypeUI(UIConstants.REQSIGOICCONFIRM_SUCCESS);

		UpdateOICSignatureStatusEvent requestEvent =
			(UpdateOICSignatureStatusEvent) EventFactory.getInstance(
				JuvenileWarrantControllerServiceNames.UPDATEOICSIGNATURESTATUS);

		this.setEvent(jwForm, requestEvent);

		forward = this.setSignatureStatus(requestEvent, aMapping, aRequest);

		return forward;
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
	 * @param aForm
	 * @param anEvent
	 */
	private void setEvent(JuvenileWarrantForm aForm, UpdateOICSignatureStatusEvent anEvent)
	{
		anEvent.setWarrantNum(aForm.getWarrantNum());
		//anEvent.setSignatureOption(aForm.getWarrantSignedStatusId());
		anEvent.setSignatureOption(aForm.getSignatureCommandId());
		anEvent.setUnSendNotSignedReason(aForm.getUnsendNotSignedReason());
	}
	/**
	 * 
	 * @param anEvent
	 * @param aMapping
	 * @param aRequest
	 * @return ActionForward
	 */
	private ActionForward setSignatureStatus(
		UpdateOICSignatureStatusEvent anEvent,
		ActionMapping aMapping,
		HttpServletRequest aRequest)
	{
		ActionForward forward = null;

		CompositeResponse response = MessageUtil.postRequest(anEvent);

		NoWarrantStatusErrorEvent noWarrantEvent =
			(NoWarrantStatusErrorEvent) MessageUtil.filterComposite(response, NoWarrantStatusErrorEvent.class);

		if (noWarrantEvent != null)
		{
			ActionErrors errors = new ActionErrors();

			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(NO_PENDING_WARRANTS));

			saveErrors(aRequest, errors);

			forward = aMapping.findForward(UIConstants.CANCEL);
		}
		else
		{
			forward = aMapping.findForward(UIConstants.SUCCESS);
		}

		return forward;
	}

}
