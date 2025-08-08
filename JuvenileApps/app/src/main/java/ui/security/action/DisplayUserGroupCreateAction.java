//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\DisplayUserGroupCreateAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.security.form.UserGroupForm;

public class DisplayUserGroupCreateAction extends Action
{

	/**
	 * @roseuid 42971F8E0176
	 */
	public DisplayUserGroupCreateAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC02A1
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.FAILURE;
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		Collection newColl = new ArrayList();
		Collection emptyColl = MessageUtil.processEmptyCollection(newColl);
		userGroupForm.setAvailableAgencies(emptyColl);
		userGroupForm.setAvailableUsers(emptyColl);
		userGroupForm.setCurrentAgencies(emptyColl);
		userGroupForm.setCurrentUsers(emptyColl);
		userGroupForm.setUsers(emptyColl);
		userGroupForm.setUserGroupName(""); 
		userGroupForm.setUserGroupDescription("");
		userGroupForm.setSearchAgencyName("");
		userGroupForm.setFirstName("");
		userGroupForm.setLastName("");
		forward = UIConstants.SUCCESS;
		return aMapping.findForward(forward);
	}
}