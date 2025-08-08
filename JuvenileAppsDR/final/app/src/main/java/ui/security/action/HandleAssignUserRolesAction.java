//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\HandleAssignUserRolesAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetAgenciesEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.security.GetRoleAgencyInfoEvent;
import messaging.security.GetRolesByConstraintsEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.IShoppingCart;
import ui.common.ShoppingCartImpl;
import ui.security.SecurityUIHelper;
import ui.security.form.AssignRolesForm;

/**
 * 
 * 
 * @author awidjaja
 * @description This action finds role(s) depending on role Name, role description, or agency name.
 * It also handle addition of roles to the user's current roles list.
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HandleAssignUserRolesAction extends LookupDispatchAction
{

	/**
	 * description public constructor
	 * @roseuid 4297202202EF
	 */
	public HandleAssignUserRolesAction()
	{

	}
	/**
	 * @return Map
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.find", "find");
		buttonMap.put("button.refresh", "refresh");
		buttonMap.put("button.addToList", "addToList");
		buttonMap.put("button.next", "next");
		buttonMap.put("button.returnProfileSearchResults", "returnToUser");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward find(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AssignRolesForm assignRolesForm = (AssignRolesForm) aForm;
		Collection emptyColl = new ArrayList();
		assignRolesForm.setAvailableRoles(MessageUtil.processEmptyCollection(emptyColl));

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetRolesByConstraintsEvent requestEvent =
			(GetRolesByConstraintsEvent) EventFactory.getInstance(
				naming.SecurityAdminControllerServiceNames.GETROLESBYCONSTRAINTS);
		requestEvent.setRoleName(assignRolesForm.getRoleName());
		requestEvent.setRoleDescription(assignRolesForm.getRoleDescription());
		requestEvent.setRoleType(UIConstants.ROLETYPE_CREATEDBY_SA);

		if (SecurityUIHelper.isUserSA())
		{
			requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
			GetAgenciesEvent agencyEvent =
				(GetAgenciesEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCIES);
			agencyEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
			dispatch.postEvent(agencyEvent);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			
			MessageUtil.processReturnException(dataMap);

			AgencyResponseEvent agencyResponseEvent =
				(AgencyResponseEvent) MessageUtil.filterComposite(compositeResponse, AgencyResponseEvent.class);
			assignRolesForm.setRoleAgencyName(agencyResponseEvent.getAgencyName());
		}
		else
		{
			String agencyName = assignRolesForm.getRoleAgencyName();
			if (agencyName != null)
			{
				agencyName = agencyName.trim();
			}
			requestEvent.setAgencyName(agencyName);
		}
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection availableRoles = MessageUtil.compositeToCollection(compositeResponse, RoleResponseEvent.class);
		if (availableRoles == null || availableRoles.size() < 1)
		{
	       	CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
	       	if (iMessage != null ){
	       		ActionErrors errors = new ActionErrors();
	       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	       		saveErrors(aRequest, errors);
	       		return aMapping.findForward(UIConstants.SEARCH_FAILURE);
	       	} 
		}   	
		requestEvent.setRoleType(UIConstants.ROLETYPE_CREATEDBY_MA);
		if (SecurityUIHelper.isUserMA())
		{
			// re initialize the agencyId
			requestEvent.setAgencyId("");
		}
		dispatch.postEvent(requestEvent);

		compositeResponse = (CompositeResponse) dispatch.getReply();
		dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection availableMARoles = MessageUtil.compositeToCollection(compositeResponse, RoleResponseEvent.class);
		Collection availableRolesList = new ArrayList();
		availableRoles.addAll(availableMARoles);
		//get agency names associated with each of those roles for display purpose
		dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetRoleAgencyInfoEvent getRoleAgencyInfoRequestEvent =
			(GetRoleAgencyInfoEvent) EventFactory.getInstance(
				naming.SecurityAdminControllerServiceNames.GETROLEAGENCYINFO);
		RoleResponseEvent eachRoleEvent = null;
		for (Iterator iter = availableRoles.iterator(); iter.hasNext();)
		{
			eachRoleEvent = (RoleResponseEvent) iter.next();
			getRoleAgencyInfoRequestEvent.setRoleId(eachRoleEvent.getRoleId());
			dispatch.postEvent(getRoleAgencyInfoRequestEvent);

			compositeResponse = (CompositeResponse) dispatch.getReply();
			dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);

			eachRoleEvent = (RoleResponseEvent) MessageUtil.filterComposite(compositeResponse, RoleResponseEvent.class);
			availableRolesList.add(eachRoleEvent);
		}
		availableRoles = availableRolesList;
		if (SecurityUIHelper.isUserSA())
		{
			availableRolesList = new ArrayList();
			Iterator iter = availableRoles.iterator();
			while (iter.hasNext())
			{
				RoleResponseEvent responseEvent = (RoleResponseEvent) iter.next();
				responseEvent.setAgencyName(assignRolesForm.getRoleAgencyName());
				availableRolesList.add(responseEvent);
			}
			availableRoles = availableRolesList;
		}
		String responseEventName = UIConstants.ROLE_RESPONSE_EVENT;
		String responseEventItemId = UIConstants.ROLE_RESPONSE_EVENT_ID;
		IShoppingCart sCart = new ShoppingCartImpl();
		try
		{
			availableRoles =
				sCart.removeFromAvailableShoppingItemList(
					responseEventName,
					responseEventItemId,
					assignRolesForm.getCurrentRoles(),
					availableRoles);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ReturnException returnException = new ReturnException(e.getMessage());
			throw returnException;
		}
		assignRolesForm.setAvailableRoles(SecurityUIHelper.sortRoleNames(availableRoles));
		assignRolesForm.setErrorMessage("");
		if (availableRoles.size() == 0)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.roles.found"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}
		return aMapping.findForward(UIConstants.ASSIGN_USER_ROLES_FIND_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward addToList(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AssignRolesForm assignRolesForm = (AssignRolesForm) aForm;
		Collection availableRoles = assignRolesForm.getAvailableRoles();
		Collection currentRoles = assignRolesForm.getCurrentRoles();

		String[] selectedRoleIds = assignRolesForm.getSelectedRoles();
		if (selectedRoleIds != null && selectedRoleIds.length > 0)
		{
			try
			{
				String responseEventName = UIConstants.ROLE_RESPONSE_EVENT;
				String responseEventItemId = UIConstants.ROLE_RESPONSE_EVENT_ID;
				IShoppingCart sCart = new ShoppingCartImpl();

				currentRoles =
					sCart.addToShoppingCart(
						responseEventName,
						responseEventItemId,
						selectedRoleIds,
						currentRoles,
						availableRoles);
				assignRolesForm.setCurrentRoles(SecurityUIHelper.sortRoleNames(currentRoles));
				availableRoles =
					sCart.removeFromAvailableShoppingItemList(
						responseEventName,
						responseEventItemId,
						currentRoles,
						availableRoles);
				assignRolesForm.setAvailableRoles(SecurityUIHelper.sortRoleNames(availableRoles));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ReturnException returnException = new ReturnException(e.getMessage());
				throw returnException;
			}
		}
		return aMapping.findForward(UIConstants.ASSIGN_USER_ROLES_ADD_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.SUCCESS);
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
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	public ActionForward returnToUser(
	    ActionMapping aMapping, 
		ActionForm aForm, HttpServletRequest aRequest, 
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.LIST_SUCCESS);
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
		String userType = assignRolesForm.getUserType();
		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		assignRolesForm.setAvailableRoles(emptyColl);
		assignRolesForm.setRoleName("");
		assignRolesForm.setRoleDescription("");
		if (userType.equalsIgnoreCase("MA")){
			assignRolesForm.setRoleAgencyName("");
		}
		
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}	
}