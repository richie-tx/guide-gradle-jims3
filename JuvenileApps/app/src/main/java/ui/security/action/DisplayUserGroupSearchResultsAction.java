//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\DisplayUserGroupSearchResultsAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.form.UserGroupForm;

public class DisplayUserGroupSearchResultsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42971FCD00FA
	 */
	public DisplayUserGroupSearchResultsAction()
	{

	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.find", "find");
		buttonMap.put("button.refresh", "refresh");
		buttonMap.put("button.createNewUserGroup", "create");

		return buttonMap;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC013A
	 */
	public ActionForward find(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		//87191
/*
		*//** Fetch USER GROUPS based on search request criteria *//*
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUserGroupsEvent requestEvent = (GetUserGroupsEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETUSERGROUPS);

		String userGroupName = userGroupForm.getUserGroupName();
		if (userGroupName != null)
		{
			userGroupName = userGroupName.trim();
		}
		String userGroupDescription = userGroupForm.getUserGroupDescription();
		if (userGroupDescription != null)
		{
			userGroupDescription = userGroupDescription.trim();
		}
		String agencyName = userGroupForm.getUserGroupDescription(); 
		String agencyName = userGroupForm.getAgencyName();		
		if (agencyName != null)
		{
			agencyName = agencyName.trim();
		}
		requestEvent.setUserGroupName(userGroupName);
		requestEvent.setUserGroupDescription(userGroupDescription);
		requestEvent.setStatusId(userGroupForm.getStatusId());
		requestEvent.setAgencyName(agencyName);
		if (!userGroupForm.getUserType().equalsIgnoreCase("MA"))
		{
			requestEvent.setAgencyId(userGroupForm.getAgencyId());
		}
		dispatch.postEvent(requestEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection userGroups = (Collection) dataMap.get(PDSecurityConstants.USER_GROUP_EVENT_TOPIC);
		userGroups = MessageUtil.processEmptyCollection(userGroups);

		int size = 0;
		size = userGroups.size();
		if (size == 0 || userGroups == null)
		{
			CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
	       	if (iMessage != null ){
	       		ActionErrors errors = new ActionErrors();
	       		ActionError error = new ActionError("error.max.limit.exceeded");
	       		errors.add(ActionErrors.GLOBAL_ERROR, error);
	       		saveErrors(aRequest, errors);
	       		return aMapping.findForward(UIConstants.SEARCH_FAILURE);
	       	} 
	       	else
	       	{			
	       		ActionErrors errors = new ActionErrors();
	       		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.no.user.group.found"));
	       		saveErrors(aRequest, errors);
	       	}
		}
		userGroupForm.setUserSearchResultSize(String.valueOf(size));
		userGroupForm.setUserGroups(SecurityUIHelper.sortAssignRolesUserGroups(userGroups));*/	 //87191	
		
		return aMapping.findForward(UIConstants.LISTSUCCESS);
	}

	/**
	  * @param aMapping
	  * @param aForm
	  * @param aRequest
	  * @param aResponse
	  * @return ActionForward
	  */
	public ActionForward create(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		userGroupForm.setUserGroupName("");
		userGroupForm.setUserGroupDescription("");
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		userGroupForm.setCurrentAgencies(emptyColl);
		userGroupForm.setAgencies(emptyColl);
		userGroupForm.setAction("create");
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
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
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		/** save user profile info on Form */
		String userType = userGroupForm.getUserType();
		String agencyId = userGroupForm.getAgencyId();
		userGroupForm.clear();
		/** restore user profile info to Form */
		userGroupForm.setUserType(userType);
		userGroupForm.setAgencyId(agencyId);
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}
}