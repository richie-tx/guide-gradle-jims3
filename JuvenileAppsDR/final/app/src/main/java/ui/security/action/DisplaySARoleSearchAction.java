//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\DisplaySARoleSearchAction.java

package ui.security.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.LoadSecurityCodeTables;
import ui.security.SecurityUIHelper;
import ui.security.form.RoleSAForm;

public class DisplaySARoleSearchAction extends Action
{

	/**
	 * @roseuid 425AB70A0196
	 */
	public DisplaySARoleSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D63002C0
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.FAILURE;
		if (!SecurityUIHelper.isUserMA())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.MAOnly.login"));
			saveErrors(aRequest, errors);
		} else {
			RoleSAForm saRoleForm = (RoleSAForm) aForm;
			saRoleForm.clear();
			LoadSecurityCodeTables load = LoadSecurityCodeTables.getInstance();
			load.setRoleSAForm(saRoleForm);
//	   	load.setRoleForm(roleForm);
			forward = UIConstants.SUCCESS;
		}	
		return aMapping.findForward(forward);
	}
}
