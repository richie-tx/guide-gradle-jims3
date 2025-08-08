//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\HandleRoleSelectionAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.security.GetRoleDetailsEvent;
import messaging.security.GetRoleUsersAndUserGroupsEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.naming.SecurityConstants;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.security.SecurityUIHelper;
import ui.security.form.RoleForm;

public class HandleRoleSelectionAction extends Action
{

	/**
	 * @roseuid 425AB74701C5
	 */
	public HandleRoleSelectionAction()
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
		RoleForm roleForm = (RoleForm) aForm;
		String forward = UIConstants.FAILURE;
		String action = aRequest.getParameter("action");
		String roleId = aRequest.getParameter("roleId");
		roleForm.setAction(action);
		roleForm.setRoleId(roleId);
		
		/** use roleId to find role information from form Collection roleNames for display */
		GetRoleDetailsEvent requestEvent = new GetRoleDetailsEvent();
		requestEvent.setRoleId(roleId);
		if(action != null && action.equals(UIConstants.UPDATE)){
			requestEvent.setAction(action);
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		RoleResponseEvent responseEvent = (RoleResponseEvent) MessageUtil.filterComposite(compositeResponse, RoleResponseEvent.class);
		roleForm.setRoleName(responseEvent.getRoleName());
		roleForm.setRoleDescription(responseEvent.getRoleDescription());
		roleForm.setCurrentAgencies(responseEvent.getAgencies());
		roleForm.setFeatures(responseEvent.getFeatures());
		roleForm.setCurrentFeatures(responseEvent.getFeatures());
		roleForm.setEveryOneRoleName("");
		if (SecurityUIHelper.isUserMA()){
			roleForm.setMasterAdmin("Y");
		}
		else{
			roleForm.setMasterAdmin("");
		}
		if (action.equalsIgnoreCase(UIConstants.VIEW))
		{
			forward = UIConstants.VIEW_SUCCESS;
			if (roleForm.getRoleName().equalsIgnoreCase(UIConstants.EVERYONE))
			{
				roleForm.setEveryOneRoleName("Y");
			}
		}
		else if (action.equalsIgnoreCase(UIConstants.DELETE))
		{
			// get the user and user groups
			GetRoleUsersAndUserGroupsEvent event = new GetRoleUsersAndUserGroupsEvent();
			event.setRoleId(roleForm.getRoleId());
			dispatch.postEvent(event);

			compositeResponse = (CompositeResponse) dispatch.getReply();
			dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);

			Collection users = (Collection) dataMap.get(SecurityConstants.USER_EVENT_TOPIC);
			users = MessageUtil.processEmptyCollection(users);
			roleForm.setUsers(users);
			Collection userGroups = (Collection) dataMap.get(SecurityConstants.USERGROUP_EVENT_TOPIC);
			userGroups = MessageUtil.processEmptyCollection(userGroups);
			roleForm.setUserGroups(userGroups);
			forward = UIConstants.DELETE_SUCCESS;
		}
		else if (action.equalsIgnoreCase(UIConstants.COPY))
		{
			roleForm.clearStringArray(roleForm.getSelectedFeatures());
			roleForm.setOriginalRoleName(roleForm.getRoleName());
			forward = UIConstants.COPY_SUCCESS;
		}
		else if (action.equalsIgnoreCase(UIConstants.UPDATE))
		{
			Collection emptyColl = new ArrayList();
			emptyColl = MessageUtil.processEmptyCollection(emptyColl);
			roleForm.setFeatures(emptyColl);
			roleForm.setAvailableFeatures(emptyColl);
			roleForm.setFeatureName("");
			roleForm.setSubSystemName("");
			roleForm.setAgencyUpdatable(responseEvent.isAgencyUpdatable());
			forward = UIConstants.UPDATE_SUCCESS;
		}
		return aMapping.findForward(forward);
	}
}