//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\DisplaySARoleCreate1Action.java

package ui.security.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.security.form.RoleSAForm;

public class DisplaySARoleCreate1Action extends Action
{

	/**
	 * @roseuid 425AB71C0177
	 */
	public DisplaySARoleCreate1Action()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D62F0129
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		saRoleForm.setAction(UIConstants.CREATE);
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}
}
