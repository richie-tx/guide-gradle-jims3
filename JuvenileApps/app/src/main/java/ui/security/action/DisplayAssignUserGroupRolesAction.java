//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\DisplayAssignUserGroupRolesAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.security.GetRolesForUserGroupEvent;
import messaging.security.reply.RoleResponseEvent;
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

import ui.security.SecurityUIHelper;
import ui.security.form.AssignRolesForm;

/**
 * 
 * 
 * @author awidjaja
 * @description Get the security roles of the selected User Group. 
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayAssignUserGroupRolesAction extends Action
{

	/**
	 * description public constructor
	 * @roseuid 42972012035C
	 */
	public DisplayAssignUserGroupRolesAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 429486240063
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AssignRolesForm assignRolesForm = (AssignRolesForm) aForm;

		//reset availableRoles to empty collection to remove previous search cache
		Collection emptyColl = new ArrayList();
		assignRolesForm.setAvailableRoles(MessageUtil.processEmptyCollection(emptyColl));

		Collection userGroups = assignRolesForm.getUserGroups();
		Iterator iter = userGroups.iterator();
		while (iter.hasNext())
		{
			UserGroupResponseEvent uGResponseEvent = (UserGroupResponseEvent) iter.next();
			if (uGResponseEvent.getUserGroupId().equalsIgnoreCase(assignRolesForm.getUserGroupId()))
			{
				assignRolesForm.setUserGroupDescription(uGResponseEvent.getDescription());
				assignRolesForm.setUserGroupName(uGResponseEvent.getName());
				break;
			}
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetRolesForUserGroupEvent requestEvent = (GetRolesForUserGroupEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETROLESFORUSERGROUP);
		requestEvent.setUserGroupId(assignRolesForm.getUserGroupId());
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection roles = MessageUtil.compositeToCollection(compositeResponse, RoleResponseEvent.class);
		//no need to display error if there's no roles found because it's not a user action
		assignRolesForm.setCurrentRoles(SecurityUIHelper.sortRoleNames(roles));			
		assignRolesForm.setErrorMessage("");
// clear search fields -- found testing User Group update		
		assignRolesForm.setRoleName("");
		assignRolesForm.setRoleDescription("");
		assignRolesForm.setAgencyName("");
		return aMapping.findForward(UIConstants.SUCCESS);
	}
}
