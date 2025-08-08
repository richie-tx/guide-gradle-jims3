//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\HandleAssignRolesUserGroupSelectionAction.java

package ui.security.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.security.GetUserGroupUsersEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.Name;
import ui.security.form.AssignRolesForm;

public class HandleAssignRolesUserGroupSelectionAction extends Action
{

	/**
	 * @roseuid 425AB74701C5
	 */
	public HandleAssignRolesUserGroupSelectionAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D6310196
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AssignRolesForm assignRolesForm = (AssignRolesForm) aForm;

//		String forward = UIConstants.FAILURE;
		String groupId = aRequest.getParameter("groupId");

		// get the user group info here
		Collection userGroups = assignRolesForm.getUserGroups();
		Iterator iter = userGroups.iterator();
		while (iter.hasNext())
		{
			UserGroupResponseEvent responseEvent = (UserGroupResponseEvent) iter.next();
			if (responseEvent.getUserGroupId().equals(groupId))
			{
				assignRolesForm.setUserGroupName(responseEvent.getName());
				assignRolesForm.setUserGroupDescription(responseEvent.getDescription());
				assignRolesForm.setUserGroupAgencyName(responseEvent.getAgencyName());
				assignRolesForm.setAgencyId(responseEvent.getAgencyId());
				assignRolesForm.setUserGroupStatus(responseEvent.getStatus());
				Name creatorName =
					new Name(
						responseEvent.getCreatorFirstName(),
						responseEvent.getCreatorMiddleName(),
						responseEvent.getCreatorLastName());
				assignRolesForm.setUserGroupCreatorName(creatorName.getFormattedName());
				break;
			}
		}

		//get the current users list
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUserGroupUsersEvent aEvent =	(GetUserGroupUsersEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETUSERGROUPUSERS);
		aEvent.setUserGroupId(groupId);
		dispatch.postEvent(aEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		Collection userGroupUsers = MessageUtil.compositeToCollection(compositeResponse, SecurityUserResponseEvent.class);
		if (userGroupUsers != null && userGroupUsers.size() > 0)
		{
			assignRolesForm.setUserGroupUsers(userGroupUsers);
		}
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);
	}
}