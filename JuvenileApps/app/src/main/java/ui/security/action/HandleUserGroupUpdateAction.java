//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\HandleUserGroupUpdateAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.GetAgenciesEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.security.ValidateUserGroupDetailsEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import messaging.user.GetUsersEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.PDContactConstants;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.common.IShoppingCart;
import ui.security.SecurityUIHelper;
import ui.common.ShoppingCartImpl;
import ui.security.form.UserGroupForm;

public class HandleUserGroupUpdateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42971FE10232
	 */
	public HandleUserGroupUpdateAction()
	{

	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.findAgencies", "findAgencies");
		buttonMap.put("button.findUsers", "findUsers");
		buttonMap.put("button.addSelectedUsers", "addUsers");
		buttonMap.put("button.refresh", "refresh");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4294C18E03D1
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		String returnString = UIConstants.UPDATE_SUCCESS;
		if (!userGroupForm.getUserGroupName().equalsIgnoreCase(userGroupForm.getOriginalUserGroupName()))
		{	
			String duplicateNameFound = validateUserGroup(userGroupForm);
			if (duplicateNameFound.equalsIgnoreCase("true"))
			{
				returnString = UIConstants.DUPLICATES;
			}
		}	
		if (userGroupForm.getUsers() == null || userGroupForm.getUsers().isEmpty())
		{
			String selectedAgencyId = null;
			if (userGroupForm.getSelectedAgencies() != null && userGroupForm.getSelectedAgencies().length > 0)
			{
				selectedAgencyId = userGroupForm.getSelectedAgencies()[0];
				userGroupForm.setSelectedAgencies(null);
			}
			else
			{
				selectedAgencyId = userGroupForm.getAgencyId();
			}
			Collection availableAgencies = userGroupForm.getAvailableAgencies();
			if (availableAgencies != null)
			{
				Iterator iter = availableAgencies.iterator();
				while (iter.hasNext())
				{
					AgencyResponseEvent aEvent = (AgencyResponseEvent) iter.next();
					if (aEvent.getAgencyId().equals(selectedAgencyId))
					{
						userGroupForm.setAgencyId(aEvent.getAgencyId());
						userGroupForm.setAgencyName(aEvent.getAgencyName());
						break;
					}
				}
			}
		}
		userGroupForm.setAction(UIConstants.UPDATE);
		return aMapping.findForward(returnString);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4294C18E03D1
	 */
	public ActionForward findAgencies(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		userGroupForm.setAgencySearchErrorMessage("");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetAgenciesEvent event = new GetAgenciesEvent();
		event.setAgencyTypeId(userGroupForm.getAgencyTypeId());
		event.setAgencyId(userGroupForm.getAgencyCode().trim());
		String searchAgencyName = userGroupForm.getSearchAgencyName();
		String returnString = UIConstants.FIND_AGENCIES_SUCCESS;
		if (searchAgencyName != null)
		{
			searchAgencyName = searchAgencyName.trim();
		}
		event.setAgencyName(searchAgencyName);
		dispatch.postEvent(event);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection agencies = MessageUtil.compositeToCollection(compositeResponse, AgencyResponseEvent.class);
		agencies = MessageUtil.processEmptyCollection(agencies);
		int size = agencies.size();
//		userGroupForm.setAvailableAgencies(agencies);
		if (size == 0 || agencies == null)
		{
			CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
	       	if (iMessage != null ){
	       		ActionErrors errors = new ActionErrors();
	       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	       		saveErrors(aRequest, errors);
	       		String msg=getResources( aRequest ).getMessage("error.max.limit.exceeded");   	       		
	       		userGroupForm.setAgencySearchErrorMessage(msg);  
	       	} 
	       	else
	       	{			
	       		String msg=getResources( aRequest ).getMessage("error.no.agency.found");
	       		userGroupForm.setAgencySearchErrorMessage(msg);	       		
	       	}
	       	returnString = UIConstants.SEARCH_FAILURE;
		}
		else
		{
			Collections.sort((List) agencies);
			userGroupForm.setAvailableAgencies(agencies);
			userGroupForm.setAgencySearchResultSize(String.valueOf(size));
		}
		return aMapping.findForward(returnString);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4294C18E03D1
	 */
	public ActionForward findUsers(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		userGroupForm.setUserSearchErrorMessage("");
		Collection emptyColl = new ArrayList();
		Collection col = MessageUtil.processEmptyCollection(emptyColl);
		userGroupForm.setAvailableUsers(col);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUsersEvent requestEvent = (GetUsersEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERS);
	
		String firstName = userGroupForm.getFirstName();
		String returnString = UIConstants.FIND_USERS_SUCCESS;
		if (firstName != null)
		{
			firstName = firstName.trim();
		}
		String lastName = userGroupForm.getLastName();
		if (lastName != null)
		{
			lastName = lastName.trim();
		}
		String middleName = userGroupForm.getMiddleName();
		if (middleName != null)
		{
			middleName = middleName.trim();
		}
		String userId = userGroupForm.getUserId();
		if (userId != null)
		{
			userId = userId.trim();
		}
		requestEvent.setFirstName(firstName);
		requestEvent.setLastName(lastName);
		requestEvent.setMiddleName(middleName);
		requestEvent.setLogonId(userId);

		if (SecurityUIHelper.isUserMA())
		{
			String departmentName = userGroupForm.getDepartment();
			if (departmentName != null)
			{
				departmentName = departmentName.trim();
			}			
			requestEvent.setDepartmentName(departmentName);
			if (userGroupForm.getSelectedAgencies() != null && userGroupForm.getSelectedAgencies().length > 0)
			{
				requestEvent.setAgencyId(userGroupForm.getSelectedAgencies()[0]);
			}
			else
				if (userGroupForm.getAgencyId() != null && !(userGroupForm.getAgencyId().equals("")))
				{
					requestEvent.setAgencyId(userGroupForm.getAgencyId());
				}
				else
				{
					String userAgencyName = userGroupForm.getUserAgencyName();
					if (userAgencyName != null)
					{
						userAgencyName = userAgencyName.trim();
					}
					requestEvent.setAgencyName(userAgencyName);
				}
		}
		else
			if (SecurityUIHelper.isUserSA())
			{
				requestEvent.setDepartmentId(userGroupForm.getDepartmentId());
				requestEvent.setAgencyId(userGroupForm.getAgencyId());
			}
			else
				if (SecurityUIHelper.isUserASA())
				{
					//TODO
				}
				else
				{
					returnString = UIConstants.FAILURE;
				}
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection users = (Collection) dataMap.get(PDContactConstants.USER_EVENT_TOPIC);

		int size = (users == null) ? 0 : users.size();
		if (size == 0 || users == null)
		{
			CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
	       	if (iMessage != null ){
	       		String msg=getResources( aRequest ).getMessage("error.max.limit.exceeded");   	       		
	       		userGroupForm.setUserSearchErrorMessage(msg);  
	       	} 
	       	else	
	       	{	
	       		String msg=getResources( aRequest ).getMessage("error.no.user.found");   	       		
	       		userGroupForm.setUserSearchErrorMessage(msg);  
	       	}	
	       	returnString = UIConstants.SEARCH_FAILURE;
		}
		userGroupForm.setUserSearchResultSize(String.valueOf(size));
		IShoppingCart sCart = new ShoppingCartImpl();
		String userEventName = UIConstants.USER_RESPONSE_EVENT;
		String userEventIdName = UIConstants.USER_RESPONSE_EVENT_ID;
		if ( users != null && size > 0 ) {
		  try
		  {
			users =
				sCart.removeFromAvailableShoppingItemList(
					userEventName,
					userEventIdName,
					userGroupForm.getCurrentUsers(),
					users);
			userGroupForm.setAvailableUsers(users);
			size = users.size();
			if (size == 0)
			{
				userGroupForm.setUserSearchErrorMessage("User found is already on Current Users List");
				returnString = UIConstants.SEARCH_FAILURE;				
			}
		  }
		  catch (Exception e)
		  {
			e.printStackTrace();
			ReturnException returnException = new ReturnException(e.getMessage());
			throw returnException;
		  }
		}
		userGroupForm.setErrorMessage("");
		return aMapping.findForward(returnString);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4294C18E03D1
	 */
	public ActionForward addUsers(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		Collection availableUsers = userGroupForm.getAvailableUsers();
		Collection currentUsers = userGroupForm.getCurrentUsers();

		String[] selectedUserIds = userGroupForm.getSelectedUsers();
		if (selectedUserIds != null && selectedUserIds.length > 0)
		{
			try
			{
				String responseEventName = UIConstants.USER_RESPONSE_EVENT;
				String responseEventItemId = UIConstants.USER_RESPONSE_EVENT_ID;
				IShoppingCart sCart = new ShoppingCartImpl();

				currentUsers =
					sCart.addToShoppingCart(
						responseEventName,
						responseEventItemId,
						selectedUserIds,
						currentUsers,
						availableUsers);
				userGroupForm.setCurrentUsers(this.sortCurrentUsers(currentUsers));
				availableUsers =
					sCart.removeFromAvailableShoppingItemList(
						responseEventName,
						responseEventItemId,
						currentUsers,
						availableUsers);
				userGroupForm.setAvailableUsers(availableUsers);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ReturnException returnException = new ReturnException(e.getMessage());
				throw returnException;
			}
		}
		return aMapping.findForward(UIConstants.UPDATE_USERS_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4294C18E03D1
	 */
	public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;		
		String refreshType = userGroupForm.getPageType();
		userGroupForm.setPageType("");
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		if (refreshType.equalsIgnoreCase("RefreshAgency"))
		{
			userGroupForm.setSearchAgencyName("");
			userGroupForm.setAvailableAgencies(emptyColl);
//			userGroupForm.setAgencyId("");
			userGroupForm.setAgencyTypeId("");
			userGroupForm.setAgencyCode("");
			userGroupForm.setAgencySearchErrorMessage("");				
		}
		if (refreshType.equalsIgnoreCase("RefreshMAUsers"))
		{
			userGroupForm.setLastName("");
			userGroupForm.setFirstName("");
			userGroupForm.setMiddleName("");
			userGroupForm.setUserId("");
			userGroupForm.setUserAgencyName("");
			userGroupForm.setDepartment("");
			userGroupForm.setAvailableUsers(emptyColl);	
			userGroupForm.setUserSearchErrorMessage("");		
		}	
		if (refreshType.equalsIgnoreCase("RefreshUsers"))
		{
			userGroupForm.setLastName("");
			userGroupForm.setFirstName("");
			userGroupForm.setMiddleName("");
			userGroupForm.setUserId("");
			userGroupForm.setDepartmentId("");
			userGroupForm.setAvailableUsers(emptyColl);		
			userGroupForm.setUserSearchErrorMessage("");	
		}	
		return aMapping.findForward(UIConstants.FIND_USERS_SUCCESS);		
/**		return aMapping.findForward(UIConstants.RESET_SUCCESS); */
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4294C18E03D1
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	public String validateUserGroup(UserGroupForm userGroupForm)
	{
		ValidateUserGroupDetailsEvent validateEvent = (ValidateUserGroupDetailsEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.VALIDATEUSERGROUPDETAILS);
		validateEvent.setAgencyId(userGroupForm.getAgencyId());
//		validateEvent.setUserGroupDescription(userGroupForm.getUserGroupDescription());
		validateEvent.setUserGroupDescription("*");		
		validateEvent.setUserGroupName(userGroupForm.getUserGroupName().toUpperCase());
 		if (userGroupForm.getUserType().equals(UIConstants.SA_ROLETYPE))
		{
			validateEvent.setCategory(UIConstants.ROLETYPE_CREATEDBY_SA);
		}
		else
		{
			validateEvent.setCategory(UIConstants.ROLETYPE_CREATEDBY_MA);
		}
 
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(validateEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		String msg = "false";
		DuplicateRecordErrorResponseEvent duplicateErrorMessage = (DuplicateRecordErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
		if (duplicateErrorMessage != null)
		{
			userGroupForm.setErrorMessage(duplicateErrorMessage.getMessage());
			msg = "true";
		}
		return msg;
	}

	private Collection sortCurrentUsers(Collection currentUsers)
	{
        SortedMap map = new TreeMap();
        Iterator iter = currentUsers.iterator();
        while (iter.hasNext())
        {
            SecurityUserResponseEvent userEvent = (SecurityUserResponseEvent) iter.next();
            String firstName = userEvent.getFirstName();
            String lastName = userEvent.getLastName();
            String middleName = userEvent.getMiddleName();
            if (firstName == null)
            {
                firstName = "";
            }
            if (lastName == null)
            {
                lastName = "";
            }
            if (middleName == null)
            {
                middleName = "";
            }
            map.put(lastName + firstName + middleName + userEvent.getLogonId(), userEvent);
        }
        return map.values();
    }		
}