//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\HandleUserGroupAddUsersAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.info.reply.CountInfoMessage;
import messaging.user.GetUsersEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.PDContactConstants;
import naming.UIConstants;
import naming.UserControllerServiceNames;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.common.IShoppingCart;
import ui.common.ShoppingCartImpl;
import ui.security.SecurityUIHelper;
import ui.security.form.UserGroupForm;

public class HandleUserGroupAddUsersAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42971FE10204
	 */
	public HandleUserGroupAddUsersAction()
	{

	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.findUsers", "findUsers");
		buttonMap.put("button.addSelectedUsers", "addUsers");
		buttonMap.put("button.reset", "reset");
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
		userGroupForm.setPageType("");
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
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
		requestEvent.setFirstName(userGroupForm.getFirstName());
		requestEvent.setLastName(userGroupForm.getLastName());
		requestEvent.setMiddleName(userGroupForm.getMiddleName());
		requestEvent.setLogonId(userGroupForm.getUserId());

		if (SecurityUIHelper.isUserMA())
		{
			requestEvent.setDepartmentName(userGroupForm.getDepartment());
			if (userGroupForm.getAgencyId() != null && !(userGroupForm.getAgencyId().equals("")))
			{
				requestEvent.setAgencyId(userGroupForm.getAgencyId());
			}
			else
			{
				requestEvent.setAgencyName(userGroupForm.getSearchAgencyName());
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
					requestEvent.setDepartmentId(userGroupForm.getDepartmentId());
					requestEvent.setAgencyId(userGroupForm.getAgencyId());
				}
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
//		 check for max. limit exceeded 
       	CountInfoMessage infoEvent = new CountInfoMessage();
       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
       	if (iMessage != null ){
       		ActionErrors errors = new ActionErrors();
       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
       		saveErrors(aRequest, errors);
       		return aMapping.findForward(UIConstants.SEARCH_FAILURE);
       	} 
       	
		Collection users = (Collection) dataMap.get(PDContactConstants.USER_EVENT_TOPIC);
		int size = (users == null) ? 0 : users.size();
		if (size == 0)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.user.found"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}
		userGroupForm.setUserSearchResultSize(String.valueOf(size));
		IShoppingCart sCart = new ShoppingCartImpl();
		String userEventName = UIConstants.USER_RESPONSE_EVENT;
		String userEventIdName = UIConstants.USER_RESPONSE_EVENT_ID;
		try
		{
			users =
				sCart.removeFromAvailableShoppingItemList(
					userEventName,
					userEventIdName,
					userGroupForm.getCurrentUsers(),
					users);
			userGroupForm.setAvailableUsers(users);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ReturnException returnException = new ReturnException(e.getMessage());
			throw returnException;
		}
		userGroupForm.setErrorMessage("");
		return aMapping.findForward(UIConstants.FIND_USERS_SUCCESS);
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
				userGroupForm.setCurrentUsers(currentUsers);
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
	public ActionForward reset(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.RESET_SUCCESS);
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
		userGroupForm.setAvailableUsers(new ArrayList());
		userGroupForm.setLastName("");
		userGroupForm.setFirstName("");
		userGroupForm.setMiddleName("");
		userGroupForm.setUserId("");
		userGroupForm.setSearchAgencyName("");
		userGroupForm.setDepartment("");
		userGroupForm.setDepartmentId("");
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}	
}