//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\DisplayUserProfileDetailsAction.java

package ui.contact.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.contact.user.reply.UserResponseEvent;
import messaging.user.GetUserProfileEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;

import ui.contact.user.form.UserProfileForm;
import ui.contact.user.helper.UIUserFormHelper;

public class DisplayUserProfileDetailsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 43F4FC40003A
	 */
	public DisplayUserProfileDetailsAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 43F4EE510097
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		//UserProfileForm userProfileForm = getUserProfileForm(aRequest);
		GetUserProfileEvent getDetailsEvent =
			(GetUserProfileEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILE);
		getDetailsEvent.setLogonId(userProfileForm.getLogonId());
		getDetailsEvent.setThinResponseInd(false);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(getDetailsEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		UserResponseEvent userResponse = (UserResponseEvent) MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);
		if (userResponse != null)
		{
			UIUserFormHelper.setUserProfileFormValues(userProfileForm, userResponse);				
			return aMapping.findForward(UIConstants.VIEW_SUCCESS);
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.user.profile","user profile not found"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.VIEW_FAILURE);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.back", "back");
		return buttonMap;
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
	
	/**
		 * put the user profile form in session
		 * @return HttpServletRequest
		 */
		private UserProfileForm getUserProfileForm(HttpServletRequest aRequest)
		{

			UserProfileForm userProfileForm = null;
			HttpSession session = aRequest.getSession();
			Object obj = session.getAttribute(UIConstants.USERPROFILE_FORM);
			if (obj != null || obj instanceof UserProfileForm)
			{
				userProfileForm = (UserProfileForm) obj;
			}
			else
			{
				userProfileForm = new UserProfileForm();
				session.setAttribute(UIConstants.USERPROFILE_FORM, userProfileForm);
			}
			return userProfileForm;
		}
}
