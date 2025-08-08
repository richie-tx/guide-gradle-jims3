//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\DisplayAssignRolesSearchResultsAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.security.GetUserGroupsEvent;
import messaging.user.GetUsersEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.InfoMessageEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDSecurityConstants;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.SecurityUIHelper;
import ui.security.form.AssignRolesForm;

/**
 * 
 * 
 * @author awidjaja
 * @description This action search users or user groups depending on the user's action
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayAssignRolesSearchResultsAction extends LookupDispatchAction
{

	/**
	 * description public constructor
	 * @roseuid 42972012032D
	 */
	public DisplayAssignRolesSearchResultsAction()
	{

	}

	/**
	 * @return Map
	 * @roseuid 4294862303BD
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.findUserGroups", "findUserGroups");
		buttonMap.put("button.findUsers", "findUsers");
		buttonMap.put("button.refresh", "refresh");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 429486240004
	 */
	public ActionForward findUserGroups(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AssignRolesForm assignRolesForm = (AssignRolesForm) aForm;

		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		assignRolesForm.setUsers(emptyColl);
		assignRolesForm.setUserGroups(emptyColl);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUserGroupsEvent requestEvent =
			(GetUserGroupsEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETUSERGROUPS);
		String userGroupName = assignRolesForm.getUserGroupName();
		if (userGroupName != null)
		{
			userGroupName = userGroupName.trim();
		}
		requestEvent.setUserGroupName(userGroupName);
		String userGroupDescription = assignRolesForm.getUserGroupDescription();
		if (userGroupDescription != null)
		{
			userGroupDescription = userGroupDescription.trim();
		}
		requestEvent.setUserGroupDescription(userGroupDescription);
//87191
		/*if (SecurityUIHelper.isUserMA())
		{
			requestEvent.setAgencyName(assignRolesForm.getUserGroupAgencyName());
			requestEvent.setAgencyId(assignRolesForm.getUserGroupAgencyCode());
		}
		else
			if (SecurityUIHelper.isUserSA() || SecurityUIHelper.isUserASA())
			{
				requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
			}
			else
			{
				return aMapping.findForward(UIConstants.FAILURE);
			}*/ //87191
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection userGroups = (Collection) dataMap.get(PDSecurityConstants.USER_GROUP_EVENT_TOPIC);
		int size = 0;
		if (userGroups != null)
		{
			size = userGroups.size();
		}
		String msg = null;
		assignRolesForm.setErrorMessage("");

		if (userGroups == null || size == 0)
		{
	       	CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = null;
	       	if (MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class) instanceof CountInfoMessage) {   
	       	  iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
	       	}
	       	if (iMessage != null ){
	       		ActionErrors errors = new ActionErrors();
	       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	       		saveErrors(aRequest, errors);
	       	} else {			
	       		ActionErrors errors = new ActionErrors();
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.user.group.found"));
	       		saveErrors(aRequest, errors);
	       	}
       		return aMapping.findForward(UIConstants.SEARCH_FAILURE);	       	
		}
		//assignRolesForm.setErrorMessage("");
		assignRolesForm.setUserGroups(SecurityUIHelper.sortAssignRolesUserGroups(userGroups));
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 429486240004
	 */
	public ActionForward findUsers(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AssignRolesForm assignRolesForm = (AssignRolesForm) aForm;

		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		assignRolesForm.setUserGroups(emptyColl);
		assignRolesForm.setUsers(emptyColl);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUsersEvent requestEvent = (GetUsersEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERS);
		requestEvent.setLastName(assignRolesForm.getUserLastName());
		requestEvent.setFirstName(assignRolesForm.getUserFirstName());
		requestEvent.setLogonId(assignRolesForm.getUserId());

		if (SecurityUIHelper.isUserMA())
		{
			requestEvent.setAgencyName(assignRolesForm.getUserAgencyNamePrompt());
			requestEvent.setDepartmentName(assignRolesForm.getDepartmentName());
			requestEvent.setAgencyId(assignRolesForm.getUserAgencyCode());
			requestEvent.setDepartmentId(assignRolesForm.getDepartmentId());
		}
		else
		{
			requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
			requestEvent.setDepartmentId(assignRolesForm.getDepartmentId());
		}
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(compositeResponse);

		Collection users =
			(Collection) MessageUtil.compositeToCollection(compositeResponse, SecurityUserResponseEvent.class);

		int size = 0;
		if (users != null)
		{
			size = users.size();
		}
//		InfoMessageEvent infoEvent =
//			(InfoMessageEvent) MessageUtil.filterComposite(compositeResponse, InfoMessageEvent.class);
		String msg = null;
		assignRolesForm.setErrorMessage("");
//		if (infoEvent != null)
//		{
//			msg = infoEvent.getMessage();
//		}
//		if (msg != null)
//		{
//			assignRolesForm.setErrorMessage(msg);
//		}
		if (users == null || size == 0)
		{
	       	CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,InfoMessageEvent.class);
	       	if (iMessage != null ){
	       		ActionErrors errors = new ActionErrors();
	       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	       		saveErrors(aRequest, errors);
	       		return aMapping.findForward(UIConstants.SEARCH_FAILURE);
	       	} else {	
	       		ActionErrors errors = new ActionErrors();
//			if (msg == null)
//			{
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.user.found"));
//			}
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.SEARCH_FAILURE);
	       	}	
		}
		assignRolesForm.setUsers(SecurityUIHelper.sortAssignRolesUsers(users));
		//assignRolesForm.setErrorMessage("");
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AssignRolesForm assignRolesForm = (AssignRolesForm) aForm;
		String refreshKey = assignRolesForm.getRefreshButton();
		assignRolesForm.setRefreshButton("");
		String userType = assignRolesForm.getUserType();
		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		assignRolesForm.setUserGroups(emptyColl);
		assignRolesForm.setUsers(emptyColl);
		if (refreshKey.equalsIgnoreCase("Users"))
		{
			assignRolesForm.setUserLastName("");
			assignRolesForm.setUserFirstName("");
			if (userType.equalsIgnoreCase("MA"))
			{
				assignRolesForm.setUserAgencyNamePrompt("");
				assignRolesForm.setUserAgencyCode("");
			}
			assignRolesForm.setDepartmentName("");
			assignRolesForm.setDepartmentId("");
			assignRolesForm.setUserId("");
		}
		else
		{
			assignRolesForm.setUserGroupName("");
			assignRolesForm.setUserGroupDescription("");
			if (userType.equalsIgnoreCase("MA"))
			{
				assignRolesForm.setUserGroupAgencyName("");
				assignRolesForm.setUserGroupAgencyCode("");
			}
		}
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}
}