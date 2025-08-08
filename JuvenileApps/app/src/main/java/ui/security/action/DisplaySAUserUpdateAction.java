/*
 * Created on May 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.security.action;

import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.security.reply.UserResponseforUserAdministrationEvent;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.security.form.SAUsersForm;

/**
 * @author sprakash
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplaySAUserUpdateAction extends Action
{

	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.SUCCESS;
		SAUsersForm saForm = (SAUsersForm) aForm;

		UserResponseforUserAdministrationEvent responseEvent = fetchUserResponseEvent(saForm.getUsers(), saForm.getSelectedLogonId());
		saForm.setSelectedUser(responseEvent);

		// set array to display userTypes
		String[] userTypeIds = new String[1];
		userTypeIds[0] = responseEvent.getUserTypeId();
		saForm.setSelectedUserTypes(userTypeIds);
		forward = UIConstants.SUCCESS;
		return aMapping.findForward(forward);

	}

	private UserResponseforUserAdministrationEvent fetchUserResponseEvent(Collection users, String selectedLogonId)
	{
		UserResponseforUserAdministrationEvent responseEvent = null;
		Iterator iter = users.iterator();
		while (iter.hasNext())
		{
			UserResponseforUserAdministrationEvent userEvent = (UserResponseforUserAdministrationEvent) iter.next();
			if (userEvent.getLogonId().equals(selectedLogonId))
			{
				responseEvent = userEvent;
				break;
			}
		}
		return responseEvent;
	}
}