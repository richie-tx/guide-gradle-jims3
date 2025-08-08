//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\HandleOfficerProfileSelectionAction.java

package ui.contact.officer.action;

import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.contact.officer.form.OfficerForm;
import ui.security.SecurityUIHelper;

public class HandleOfficerProfileDepartmentSelectionAction extends Action
{

	/**
	 * @roseuid 42E6766D016E
	 */
	public HandleOfficerProfileDepartmentSelectionAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42E65EA70334
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OfficerForm officerForm = (OfficerForm) aForm;
		if (!SecurityUIHelper.isLoggedIn())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.not.logged.in"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		String selectedDepartmentId = officerForm.getSelectedDepartment();
		Iterator iter = officerForm.getDepartments().iterator();
		DepartmentResponseEvent departmentResponseEvent = null;
		while (iter.hasNext())
		{
			departmentResponseEvent = (DepartmentResponseEvent) iter.next();
			if (selectedDepartmentId.equals(departmentResponseEvent.getDepartmentId()))
			{
				break;
			}
		}
		officerForm.setDepartmentId(departmentResponseEvent.getDepartmentId());
		officerForm.setDepartmentName(departmentResponseEvent.getDepartmentName());
		return aMapping.findForward(UIConstants.SELECT_DEPARTMENT_SUCCESS);
	}
}