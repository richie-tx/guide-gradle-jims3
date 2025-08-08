//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\SubmitUserGroupUpdateAction.java

package ui.security.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.security.ActivateUserGroupEvent;
import messaging.security.DeleteUserGroupEvent;
import messaging.security.InactivateUserGroupEvent;
import messaging.security.UpdateUserGroupEvent;
import messaging.security.UpdateUserGroupUsersEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import messaging.security.UserGroupUserRequestEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.security.form.UserGroupForm;

public class SubmitUserGroupUpdateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42971FF60158
	 */
	public SubmitUserGroupUpdateAction()
	{

	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.addUser", "addUser");
		buttonMap.put("button.mainPage", "mainPage");
		return buttonMap;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC015C
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.FAILURE;
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		String action = userGroupForm.getAction();
		if (action.equalsIgnoreCase(UIConstants.DELETE))
		{
			this.deleteUserGroup(userGroupForm);
			forward = UIConstants.UPDATE_SUCCESS;
		}
		if (action.equalsIgnoreCase(UIConstants.ACTIVATE))
		{
			this.activateUserGroup(userGroupForm);
			forward = UIConstants.UPDATE_SUCCESS;
		}
		if (action.equalsIgnoreCase(UIConstants.INACTIVATE))
		{
			this.inactivateUserGroup(userGroupForm);
			forward = UIConstants.UPDATE_SUCCESS;
		}
		if (action.equalsIgnoreCase(UIConstants.UPDATE))
		{
			this.updateUserGroup(userGroupForm);
			forward = UIConstants.UPDATE_SUCCESS;
		}
		return aMapping.findForward(forward);
	}

	/**
	 * @param userGroupForm
	 */
	private void inactivateUserGroup(UserGroupForm userGroupForm)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		InactivateUserGroupEvent requestEvent = (InactivateUserGroupEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.INACTIVATEUSERGROUP);
		requestEvent.setUserGroupId(userGroupForm.getGroupId());
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		userGroupForm.setGroupStatus("INACTIVE");
	}

	/**
	 * @param userGroupForm
	 */
	private void activateUserGroup(UserGroupForm userGroupForm)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		ActivateUserGroupEvent requestEvent = (ActivateUserGroupEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.ACTIVATEUSERGROUP);
		/*requestEvent.setUserGroupId(userGroupForm.getGroupId());*/ //87191
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		userGroupForm.setGroupStatus("ACTIVE");
	}

	/**
	 * @param userGroupForm
	 */
	private void deleteUserGroup(UserGroupForm userGroupForm)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		DeleteUserGroupEvent requestEvent =	(DeleteUserGroupEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.DELETEUSERGROUP);
		requestEvent.setUserGroupId(userGroupForm.getGroupId());
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
	}

	/**
	 * @param userGroupForm
	 */
	private void updateUserGroup(UserGroupForm userGroupForm)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UpdateUserGroupEvent requestEvent =	(UpdateUserGroupEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.UPDATEUSERGROUP);
		requestEvent.setUserGroupId(userGroupForm.getGroupId());
		requestEvent.setUserGroupName(userGroupForm.getUserGroupName());
		requestEvent.setUserGroupDescription(userGroupForm.getUserGroupDescription());
		requestEvent.setAgencyId(userGroupForm.getAgencyId());
		dispatch.postEvent(requestEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		userGroupForm.setErrorMessage("");
		UpdateUserGroupUsersEvent aEvent = (UpdateUserGroupUsersEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.UPDATEUSERGROUPUSERS);
		aEvent.setUserGroupId(userGroupForm.getGroupId());
		Collection currentUsers = userGroupForm.getCurrentUsers();
		Iterator iter = currentUsers.iterator();
		while (iter.hasNext())
		{
			SecurityUserResponseEvent userResponseEvent = (SecurityUserResponseEvent) iter.next();
			UserGroupUserRequestEvent userEvent = new UserGroupUserRequestEvent();
			userEvent.setLogonId(userResponseEvent.getLogonId());
			aEvent.addRequest(userEvent);
		}
		dispatch.postEvent(aEvent);
		
		compositeResponse = (CompositeResponse) dispatch.getReply();
		dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		DuplicateRecordErrorResponseEvent dupEvent = (DuplicateRecordErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
		if (dupEvent != null)
		{
			userGroupForm.setErrorMessage(dupEvent.getMessage());
		}
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC015C
	 */
	public ActionForward addUser(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC015C
	 */
	public ActionForward mainPage(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.MAIN_MENU);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC015C
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}
}