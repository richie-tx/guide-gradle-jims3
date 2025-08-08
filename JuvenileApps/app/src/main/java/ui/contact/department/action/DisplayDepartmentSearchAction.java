//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\DisplayDepartmentSearchAction.java

package ui.contact.department.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.contact.department.LoadManageDepartmentCodeTables;
import ui.contact.department.form.DepartmentForm;

public class DisplayDepartmentSearchAction extends Action
{

	/**
	 * @roseuid 430630E9025B
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
	 * @roseuid 430628E501A8
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		if (!ui.security.SecurityUIHelper.isUserMA())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.MAOnly.login"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}

		DepartmentForm departmentForm = (DepartmentForm) aForm;
		departmentForm.clear();
		// Load all the code tables if that has not been done yet
		LoadManageDepartmentCodeTables instance = LoadManageDepartmentCodeTables.getInstance();
		instance.setDepartmentForm(departmentForm);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
}
