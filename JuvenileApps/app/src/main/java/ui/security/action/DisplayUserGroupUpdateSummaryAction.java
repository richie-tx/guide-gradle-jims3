//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\DisplayUserGroupUpdateSummaryAction.java

package ui.security.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.user.GetUsersEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
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
import ui.security.form.UserGroupForm;

public class DisplayUserGroupUpdateSummaryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42971FCD0157
	 */
	public DisplayUserGroupUpdateSummaryAction()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.findUsers", "findUsers");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD039B
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		userGroupForm.setAction(UIConstants.CREATE);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD039B
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
	 * @roseuid 428B82BD039B
	 */
	public ActionForward findUsers(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.FAILURE;
		UserGroupForm userGroupForm = (UserGroupForm) aForm;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUsersEvent requestEvent = (GetUsersEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETUSERGROUPUSERS);
		requestEvent.setLastName(userGroupForm.getLastName());
		requestEvent.setFirstName(userGroupForm.getFirstName());
		requestEvent.setUserID(userGroupForm.getUserId());
		requestEvent.setAgencyName(userGroupForm.getAgencyName());
		requestEvent.setDepartmentName(userGroupForm.getDepartment());
		requestEvent.setDepartmentId(userGroupForm.getDepartmentId());
		dispatch.postEvent(requestEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection availableUsers = MessageUtil.compositeToCollection(compositeResponse, SecurityUserResponseEvent.class);
		availableUsers = MessageUtil.processEmptyCollection(availableUsers);
		userGroupForm.setAvailableUsers(availableUsers);
		int size = 0;
		if (availableUsers != null)
		{
			size = availableUsers.size();
		}
		userGroupForm.setUserSearchResultSize(String.valueOf(size));
		forward = UIConstants.FIND_USERS_SUCCESS;
		if (size == 0)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.user.found"));
			saveErrors(aRequest, errors);
			forward = UIConstants.SEARCH_FAILURE;
		}
		return aMapping.findForward(forward);
	}
}