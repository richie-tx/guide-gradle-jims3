//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\DisplayUserProfileHistoryAction.java

package ui.contact.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.user.form.UserProfileForm;

public class DisplayUserProfileHistoryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 43F4FC400171
	 */
	public DisplayUserProfileHistoryAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 43F4EE39039F
	 */
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.back", "back");
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
	
	public ActionForward searchUserProfile(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			UserProfileForm profileForm =(UserProfileForm)aForm;
			profileForm.clear();
			return aMapping.findForward("search");
		}
		
	public ActionForward userProfileSearchResults(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			UserProfileForm profileForm =(UserProfileForm)aForm;
			String forward = "searchResults";
			if (profileForm.getNumUsers().equalsIgnoreCase("single")){
				forward = UIConstants.VIEW_DETAIL;
			}
			return aMapping.findForward(forward);
		}
}
