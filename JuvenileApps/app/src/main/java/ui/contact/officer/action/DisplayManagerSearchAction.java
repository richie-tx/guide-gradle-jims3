//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\DisplayManagerSearchAction.java

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

import ui.contact.officer.form.OfficerForm;
import ui.security.SecurityUIHelper;

public class DisplayManagerSearchAction extends Action
{

	/**
	 * @roseuid 42E676600064
	 */
	public DisplayManagerSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42E65EA502B5
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
		OfficerForm officerForm = (OfficerForm) aForm;
		officerForm.setLastNamePrompt("");
		officerForm.setFirstNamePrompt("");
		return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
	}
}
