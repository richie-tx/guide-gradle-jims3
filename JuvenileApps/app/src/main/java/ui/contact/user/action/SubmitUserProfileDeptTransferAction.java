//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\SubmitUserProfileDeptTransferAction.java

package ui.contact.user.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.user.TransferUserProfileEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.km.messaging.exception.ReturnException;
import naming.UIConstants;
import naming.UserControllerServiceNames;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.user.form.UserProfileForm;
import ui.contact.user.form.UserProfileSearchForm;

public class SubmitUserProfileDeptTransferAction extends LookupDispatchAction
{

	/**
	 * @roseuid 43F4FC42012E
	 */
	public SubmitUserProfileDeptTransferAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 43F4EE2F026F
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileForm userProfileForm = (UserProfileForm) aForm;

		TransferUserProfileEvent transferEvent =
			(TransferUserProfileEvent) EventFactory.getInstance(UserControllerServiceNames.TRANSFERUSERPROFILE);
		transferEvent.setLogonId(userProfileForm.getLogonId());
		transferEvent.setNewDepartmentId(userProfileForm.getNewDepartmentId());
		transferEvent.setTransferDate(DateUtil.stringToDate(userProfileForm.getTransferDate(),"MM/dd/yyyy"));
		transferEvent.setTransferTime(userProfileForm.getTransferTime());
		Date currentDate = DateUtil.getCurrentDate();
		transferEvent.setDeptTransferRequestDate(DateUtil.getCurrentDate());
		transferEvent.setDeptTransferRequestTime(DateUtil.getHHMMSSWithColonFromDate(currentDate));
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(transferEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		userProfileForm.setAction("confirm");
		ReturnException lResponse = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
		if(lResponse != null)
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.user.transfer","error.profile.general");
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);		
			userProfileForm.setAction("summary");	
			return aMapping.findForward(UIConstants.FAILURE);
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.back", "back");
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.mainPage","mainPage");
		buttonMap.put("button.searchUserProfile","searchUserProfile");
		buttonMap.put("button.userProfileSearchResults","userProfileSearchResults");
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
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			return aMapping.findForward(UIConstants.CANCEL);
		}
	public ActionForward searchUserProfile(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileForm profileForm =(UserProfileForm)aForm;
		profileForm.clear();
		UserProfileSearchForm searchForm = getUserProfileSearchForm(aRequest);
		searchForm.clear();
		searchForm.setDisplayType(UIConstants.BASIC);
		return aMapping.findForward("search");
	}

	public ActionForward userProfileSearchResults(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileSearchForm searchForm = getUserProfileSearchForm(aRequest);
		return aMapping.findForward("searchResults");
	}
	public ActionForward mainPage(
				   ActionMapping aMapping,
				   ActionForm aForm,
				   HttpServletRequest aRequest,
				   HttpServletResponse aResponse)
	  {
		return aMapping.findForward(UIConstants.MAIN_PAGE);
	  }

	
	/**
	 * just for debugging 
	 */
	private UserProfileSearchForm getUserProfileSearchForm(HttpServletRequest aRequest)
	{

		UserProfileSearchForm userProfileSearchForm = null;
		HttpSession session = aRequest.getSession();
		Object obj = session.getAttribute("userProfileSearchForm");
		if (obj != null || obj instanceof UserProfileForm)
		{
			userProfileSearchForm = (UserProfileSearchForm) obj;
		}
		else
		{
			userProfileSearchForm = new UserProfileSearchForm();
			session.setAttribute("userProfileSearchForm", userProfileSearchForm);
		}
		return userProfileSearchForm;
	}
	
	
}
