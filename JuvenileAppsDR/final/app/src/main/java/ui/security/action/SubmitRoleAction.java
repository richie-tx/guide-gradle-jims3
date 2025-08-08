//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\SubmitRoleAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.security.CreateRoleEvent;
import messaging.security.DeleteRoleEvent;
import messaging.security.GetRoleUsersAndUserGroupsEvent;
import messaging.security.UpdateRoleEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import messaging.security.reply.FeaturesResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.naming.SecurityConstants;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.security.SecurityUIHelper;
import ui.security.form.RoleForm;

public class SubmitRoleAction extends LookupDispatchAction
{

	/**
	 * @roseuid 425AB72B0213
	 */
	public SubmitRoleAction()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.mainPage", "mainpage");
		buttonMap.put("button.delete", "delete");
		buttonMap.put("button.continueDelete", "finish");
		buttonMap.put("button.cancel", "cancel");

		return buttonMap;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 425551F90223
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		String forward = UIConstants.FAILURE;
		String action = roleForm.getAction();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		IEvent iEvent = null;
		if (action != null)
		{
			if (action.equalsIgnoreCase(UIConstants.CREATE) || action.equalsIgnoreCase(UIConstants.COPY))
			{
				CreateRoleEvent createEvent = new CreateRoleEvent();
				createEvent.setRoleName(roleForm.getRoleName());
				createEvent.setRoleDescription(roleForm.getRoleDescription());
				createEvent.setRoleCreatorId(SecurityUIHelper.getJIMSLogonId());
				if (SecurityUIHelper.isUserMA())
					createEvent.setRoleType(UIConstants.ROLETYPE_CREATEDBY_MA);
				else
					if (SecurityUIHelper.isUserSA())
						createEvent.setRoleType(UIConstants.ROLETYPE_CREATEDBY_SA);
					else
						return aMapping.findForward(UIConstants.FAILURE);

				Collection currentAgencies = roleForm.getCurrentAgencies();
				if (!(SecurityUIHelper.isUserMA()) && (currentAgencies == null || currentAgencies.isEmpty()))
				{
					return aMapping.findForward(UIConstants.FAILURE);
				}
				Collection agencyIds = new ArrayList();
				Iterator iter = currentAgencies.iterator();
				while (iter.hasNext())
				{
					AgencyResponseEvent event = (AgencyResponseEvent) iter.next();
					agencyIds.add(event.getAgencyId());
				}
				createEvent.setAgenciesList(agencyIds);

				Collection featureList = roleForm.getCurrentFeatures();
				if (featureList == null || featureList.isEmpty())
				{
					return aMapping.findForward(UIConstants.FAILURE);
				}
				Iterator featuresIter = featureList.iterator();
				Collection featureIdList = new ArrayList();
				while (featuresIter.hasNext())
				{
					FeaturesResponseEvent fEvent = (FeaturesResponseEvent) featuresIter.next();
					featureIdList.add(fEvent.getFeatureId());
				}
				createEvent.setFeaturesList(featureIdList);
				iEvent = createEvent;
				forward = UIConstants.CREATE_SUCCESS;
			}
			else
				if (action.equalsIgnoreCase(UIConstants.DELETE))
				{
					// delete the role
					DeleteRoleEvent deleteEvent = new DeleteRoleEvent();
					deleteEvent.setRoleId(roleForm.getRoleId());
					iEvent = deleteEvent;
					forward = UIConstants.DELETE_SUCCESS;
				}
				else
					if (action.equalsIgnoreCase(UIConstants.UPDATE))
					{
						UpdateRoleEvent updateEvent = new UpdateRoleEvent();
						updateEvent.setRoleName(roleForm.getRoleName());
						updateEvent.setRoleDescription(roleForm.getRoleDescription());
						updateEvent.setRoleCreatorId(SecurityUIHelper.getJIMSLogonId());
						updateEvent.setRoleId(roleForm.getRoleId());

						Collection currentAgencies = roleForm.getCurrentAgencies();
						if (!(SecurityUIHelper.isUserMA()) && (currentAgencies == null || currentAgencies.isEmpty()))
						{
							return aMapping.findForward(UIConstants.FAILURE);
						}
						Collection agencyIds = new ArrayList();
						Iterator currentAgenciesIter = currentAgencies.iterator();
						while (currentAgenciesIter.hasNext())
						{
							AgencyResponseEvent event = (AgencyResponseEvent) currentAgenciesIter.next();
							agencyIds.add(event.getAgencyId());
						}
						updateEvent.setAgenciesList(agencyIds);

						Collection featureList = roleForm.getCurrentFeatures();
						if (featureList == null || featureList.isEmpty())
						{
							return aMapping.findForward(UIConstants.FAILURE);
						}
						Iterator featuresIter = featureList.iterator();
						Collection featureIdList = new ArrayList();
						while (featuresIter.hasNext())
						{
							FeaturesResponseEvent fEvent = (FeaturesResponseEvent) featuresIter.next();
							featureIdList.add(fEvent.getFeatureId());
						}
						updateEvent.setFeaturesList(featureIdList);
						iEvent = updateEvent;
						forward = UIConstants.UPDATE_SUCCESS;
					}
					else
						if (action.equalsIgnoreCase(UIConstants.COPY))
						{
							forward = UIConstants.COPY_SUCCESS;
						}
						else
						{
							return aMapping.findForward(UIConstants.FAILURE);
						}
			dispatch.postEvent(iEvent);
			
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);

			DuplicateRecordErrorResponseEvent duplicateErrorMessage = (DuplicateRecordErrorResponseEvent) MessageUtil.filterComposite(compositeResponse,DuplicateRecordErrorResponseEvent.class);
			if (duplicateErrorMessage != null)
			{
				roleForm.setErrorMessage(duplicateErrorMessage.getMessage());
			}
			else
				roleForm.setErrorMessage("");
		}
		return aMapping.findForward(forward);
	}

	/**
	  * @param aMapping
	  * @param aForm
	  * @param aRequest
	  * @param aResponse
	  * @return ActionForward
	  */
	public ActionForward delete(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		// get the user and user groups
		GetRoleUsersAndUserGroupsEvent event = new GetRoleUsersAndUserGroupsEvent();
		event.setRoleId(roleForm.getRoleId());
		dispatch.postEvent(event);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection users = (Collection) dataMap.get(SecurityConstants.USER_EVENT_TOPIC);
		users = MessageUtil.processEmptyCollection(users);
		roleForm.setUsers(users);
		Collection userGroups = (Collection) dataMap.get(SecurityConstants.USERGROUP_EVENT_TOPIC);
		userGroups = MessageUtil.processEmptyCollection(userGroups);
		roleForm.setUserGroups(userGroups);
		if (users == null || users.isEmpty() && (userGroups == null || userGroups.isEmpty()))
		{
			finish(aMapping, aForm, aRequest, aResponse);
			return aMapping.findForward("deleteSuccess");
		}
		else
			return aMapping.findForward("deleteConfirmSuccess");
	}

	/**
	  * @param aMapping
	  * @param aForm
	  * @param aRequest
	  * @param aResponse
	  * @return ActionForward
	  */
	public ActionForward continueDelete(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward("deleteSuccess");
	}

	/**
	  * @param aMapping
	  * @param aForm
	  * @param aRequest
	  * @param aResponse
	  * @return ActionForward
	  */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		String forward = UIConstants.CANCEL;
		String action = roleForm.getAction();
		String masterAdmin = roleForm.getMasterAdmin();
		roleForm.clear();
		roleForm.setMasterAdmin(masterAdmin);			
		aRequest.setAttribute("action", "");
		if (action != null)
		{
			if (action.equalsIgnoreCase(UIConstants.CREATE))
			{
				forward = UIConstants.CANCEL_CREATE;
				roleForm.setAction(UIConstants.CREATE);
			}
			if (action.equalsIgnoreCase(UIConstants.DELETE))
			{
				forward = UIConstants.CANCEL_DELETE;
			}
		}
		return aMapping.findForward(forward);
	}

	/**
	  * @param aMapping
	  * @param aForm
	  * @param aRequest
	  * @param aResponse
	  * @return ActionForward
	  */
	public ActionForward mainpage(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		roleForm.clear();
		aRequest.setAttribute("action", "");
		return aMapping.findForward(UIConstants.MAIN_MENU);
	}
}