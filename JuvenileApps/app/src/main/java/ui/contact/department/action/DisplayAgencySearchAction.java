//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\DisplayAgencySearchAction.java

package ui.contact.department.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DisplayAgencySearchAction extends Action
{

	/**
	 * @roseuid 430630DE00A6
	 */
	public DisplayAgencySearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 430628E101AB
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CREATE_DEPT_SUCCESS);
	}
}
