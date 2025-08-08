//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\DisplayDepartmentSearchAction.java

package ui.contact.officer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.security.SecurityUIHelper;

public class DisplayDepartmentSearchAction extends Action
{

	/**
	 * @roseuid 42E6765802A6
	 */
	public DisplayDepartmentSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42E65EA50093
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		if (!SecurityUIHelper.isLoggedIn())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.not.logged.in"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		return aMapping.findForward(UIConstants.SEARCH_MANAGE_PROFILE_SUCCESS);
	}
}
