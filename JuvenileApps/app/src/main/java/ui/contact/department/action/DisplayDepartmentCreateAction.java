//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\DisplayDepartmentCreateAction.java

package ui.contact.department.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.contact.department.form.DepartmentForm;

public class DisplayDepartmentCreateAction extends Action
{

	/**
	 * @roseuid 430630E700A6
	 */
	public DisplayDepartmentCreateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 430628E10228
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptForm = (DepartmentForm) aForm;
		deptForm.clear();
		deptForm.setAction("deptCreate");
		deptForm.setStatusId("A"); // default to Active upon entering create
		deptForm.setAgencyName("");
		deptForm.setAgencyId("");
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}

}
