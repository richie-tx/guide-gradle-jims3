//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\DisplayContactSummaryAction.java

package ui.contact.department.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.contact.department.form.DepartmentForm;

public class DisplayContactSummaryAction extends Action
{

	/**
	 * @roseuid 430630E302A9
	 */
	public DisplayContactSummaryAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 430628E402B1
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		ActionForward forward = null;
		DepartmentForm deptForm = (DepartmentForm) aForm;
		if (deptForm.getAction().equalsIgnoreCase(UIConstants.CONTACT_CREATE))
		{
			forward = aMapping.findForward(UIConstants.CREATE_CONTACT_SUCCESS);
			return forward;
		}
		forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
		return forward;
	}
}
