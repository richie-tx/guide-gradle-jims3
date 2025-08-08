//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\DisplayOfficerProfileSearchAction.java

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
import ui.contact.LoadManageOfficerCodeTables;
import ui.contact.officer.form.OfficerForm;
import ui.security.SecurityUIHelper;

public class DisplayOfficerProfileSearchAction extends Action
{

	/**
	 * @roseuid 42E67665018D
	 */
	public DisplayOfficerProfileSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42E65EA7020A
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		/*clear out form at new search */
		OfficerForm officerForm = (OfficerForm) aForm;
		officerForm.clear();

		if (!SecurityUIHelper.isLoggedIn())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.not.logged.in"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}

		if (SecurityUIHelper.isUserSA())
		{
			officerForm.setSA(true);
		}
		else
			if (SecurityUIHelper.isUserASA())
			{
				officerForm.setASA(true);
			}
			else
				if (SecurityUIHelper.isUserMA())
				{
					officerForm.setMA(true);
				}
				else
				{
					officerForm.setOfficer(true);
					officerForm.setUserId(SecurityUIHelper.getLogonId());
				}
		// Load all the code tables if that has not been done yet
		LoadManageOfficerCodeTables instance = LoadManageOfficerCodeTables.getInstance();
		instance.setOfficerForm(officerForm);
		officerForm.setStatusId("");	
		return aMapping.findForward(UIConstants.FIND_SUCCESS);
	}
}