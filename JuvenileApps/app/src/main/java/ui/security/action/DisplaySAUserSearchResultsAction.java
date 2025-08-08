/*
 * Created on May 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.security.action;

/**
 * @author sprakash
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.info.reply.CountInfoMessage;
import messaging.security.GetUsersForUserAdministrationEvent;
import messaging.security.reply.UserResponseforUserAdministrationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.InfoMessageEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.SecurityUIHelper;
import ui.security.form.SAUsersForm;

public class DisplaySAUserSearchResultsAction extends LookupDispatchAction
{

	/**
	 * 
	 */
	public DisplaySAUserSearchResultsAction()
	{
	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.find", "find");
		buttonMap.put("button.refresh", "refresh");

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
		String forward = UIConstants.SUCCESS;

		String create = aRequest.getParameter("create");
		/** hyperlink to create role */
		if (create != null)
		{
			if (create.equalsIgnoreCase(UIConstants.CREATE))
			{
				forward = UIConstants.CREATE_SUCCESS;
			}
			else
			{
				create = null;
			}
		}
		if (create == null)
		{
			SAUsersForm saForm = (SAUsersForm) aForm;

			GetUsersForUserAdministrationEvent requestEvent = (GetUsersForUserAdministrationEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETUSERSFORUSERADMINISTRATION);
			requestEvent.setLastName(saForm.getLastName());
			requestEvent.setFirstName(saForm.getFirstName());
			requestEvent.setAgencyName(saForm.getAgencyName());
			requestEvent.setLogonId(saForm.getLogonId());
			requestEvent.setUserTypeId(saForm.getUserTypeId());

			/** FETCH USERS BASED ON SEARCH REQUEST */
//			Collection users = (Collection) this.fetchSAUserSearchData(requestEvent);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(requestEvent);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);

			Collection users = (Collection) MessageUtil.compositeToCollection(compositeResponse, UserResponseforUserAdministrationEvent.class);			
			if (users == null)
			{
				forward = UIConstants.SEARCH_FAILURE;
			}
			else
			{
				if (users.size() > 0)
				{
					users = SecurityUIHelper.sortUserAdministrationNames(users);
					saForm.setUsers(users);					
					saForm.setSearchResultSize(String.valueOf(users.size()));
					// set the first user as the selected one
					UserResponseforUserAdministrationEvent defaultUser = (UserResponseforUserAdministrationEvent) users.iterator().next();
					saForm.setSelectedLogonId(defaultUser.getLogonId());
					saForm.setSelectedUser(defaultUser);
					//saForm.setSearchResultSize(String.valueOf(size));
					forward = UIConstants.SUCCESS;
				}
				else
				{
					if (users.size() == 0)
					{	
						CountInfoMessage infoEvent = new CountInfoMessage();
						CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,InfoMessageEvent.class);
						if (iMessage != null ){
							ActionErrors errors = new ActionErrors();
							ActionMessage error = new ActionMessage("error.max.limit.exceeded");
							errors.add(ActionErrors.GLOBAL_MESSAGE, error);
							saveErrors(aRequest, errors);
							forward = UIConstants.SEARCH_FAILURE;
						}
						else
						{	
							ActionErrors errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.user.found"));
							saveErrors(aRequest, errors);
							forward = UIConstants.SEARCH_FAILURE;
						}	
					} 
				}
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
	public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SAUsersForm saForm = (SAUsersForm) aForm;
		Collection users = new ArrayList();
		saForm.setUsers(users);
		saForm.setSearchResultSize(String.valueOf(users.size()));
		saForm.setLastName("");
		saForm.setFirstName("");
		saForm.setUserTypeId("");
		saForm.setLogonId("");
		saForm.setAgencyName("");
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}

	private Map putCollectionIntoMap(Iterator users)
	{
		Map usersMap = new HashMap();
		while (users.hasNext())
		{
			UserResponseforUserAdministrationEvent user = (UserResponseforUserAdministrationEvent) users.next();
			usersMap.put(user.getLogonId(), user);
		}
		return usersMap;
	}

//	private Collection fetchSAUserSearchData(GetUsersForUserAdministrationEvent requestEvent)
//	{
//		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
//		dispatch.postEvent(requestEvent);

//		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
//		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
//		MessageUtil.processReturnException(dataMap);

//		Collection users = (Collection) MessageUtil.compositeToCollection(compositeResponse, UserResponseforUserAdministrationEvent.class);
//		users = MessageUtil.processEmptyCollection(users);
//		return users;
//	}
}