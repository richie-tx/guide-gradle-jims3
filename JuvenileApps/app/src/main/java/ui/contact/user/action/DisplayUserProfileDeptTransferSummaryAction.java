//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\DisplayUserProfileDeptTransferSummaryAction.java

package ui.contact.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDCodeTableConstants;
import naming.UIConstants;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.agency.GetDepartmentEvent;
import naming.AgencyControllerServiceNames;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import ui.common.CodeHelper;
import ui.contact.user.form.UserProfileForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;

public class DisplayUserProfileDeptTransferSummaryAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.back", "back");
		buttonMap.put("button.submit", "submit");
		buttonMap.put("button.reset", "reset");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.validateDepartmentCode", "validateDepartmentCode");
		buttonMap.put("button.userProfileSearchResults", "userProfileSearchResults");
		return buttonMap;
	}


	/**
	 *
	 */
	public DisplayUserProfileDeptTransferSummaryAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward submit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		GetDepartmentEvent validateEvent =
			(GetDepartmentEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENT);		
		validateEvent.setDepartmentId(userProfileForm.getNewDepartmentId());
		validateEvent.setGetAddressAndContact(false);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(validateEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		DepartmentResponseEvent responseEvent =
			(DepartmentResponseEvent) MessageUtil.filterComposite(compositeResponse, DepartmentResponseEvent.class);

		if (responseEvent == null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.user.department","Invalid Department Code."));			
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		userProfileForm.setNewDepartmentName(responseEvent.getDepartmentName());	
		userProfileForm.setNewDepartmentId(responseEvent.getDepartmentId());
		userProfileForm.setGenericUserTypes(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GENERIC_USER_TYPE, true)); //U.S #79250
		userProfileForm.setWorkDays(CodeHelper.getCodes(PDCodeTableConstants.WORK_DAY));
		userProfileForm.setAction("summary");	
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}

	
	/**
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
	
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		userProfileForm.clearDepartmentDetails();
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	public ActionForward reset(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		userProfileForm.clearDepartmentDetails();
		return aMapping.findForward("reset");
	}
	
	public ActionForward validateDepartmentCode(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
			{
				UserProfileForm upf = (UserProfileForm) aForm;

				GetDepartmentEvent validateEvent =
					(GetDepartmentEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENT);
				validateEvent.setDepartmentId(upf.getNewDepartmentId());
				validateEvent.setGetAddressAndContact(false);

				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(validateEvent);
				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				MessageUtil.processReturnException(compositeResponse);

				DepartmentResponseEvent responseEvent =
					(DepartmentResponseEvent) MessageUtil.filterComposite(compositeResponse, DepartmentResponseEvent.class);

				if (responseEvent == null)
				{
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.user.department","Invalid Department Code."));			
					saveErrors(aRequest, errors);
					return aMapping.findForward(UIConstants.FAILURE);
				}
				upf.setNewDepartmentName(responseEvent.getDepartmentName());
				upf.setNewDepartmentId(responseEvent.getDepartmentId());
				upf.setGenericUserTypes(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GENERIC_USER_TYPE, true));
				return aMapping.findForward(UIConstants.VALIDATE_DEPT_SUCCESS);
			}
	public ActionForward userProfileSearchResults(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward("searchResults");
	}
	
	
}
