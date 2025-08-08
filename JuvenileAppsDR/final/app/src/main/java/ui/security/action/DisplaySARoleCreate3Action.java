//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\DisplaySARoleCreate2Action.java

package ui.security.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.form.RoleSAForm;

public class DisplaySARoleCreate3Action extends LookupDispatchAction
{

	/**
	 * @roseuid 425AB6E501F4
	 */
	public DisplaySARoleCreate3Action()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.next", "next");
		buttonMap.put("button.back", "back");

		return buttonMap;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D631031E
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		saRoleForm.setFeatureName("");
		saRoleForm.setFeatureCategoryId("");
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}
}