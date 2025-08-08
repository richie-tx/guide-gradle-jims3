//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\SubmitUserGroupAddUsersAction.java

package ui.security.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.security.UpdateUserGroupUsersEvent;
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

public class SubmitUserGroupAddUsersAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42971FF60129
	 */
	public SubmitUserGroupAddUsersAction()
	{

	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.assignRoles", "assignRoles");
		buttonMap.put("button.mainPage", "mainPage");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD02B1
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		this.persist(userGroupForm,aResponse);
		userGroupForm.setPageType(UIConstants.CONFIRM);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}
	
	/**
		 * @param userGroupForm
		 * @param aResponse
		 * @roseuid 428B82BD02B1
		 */
		private void persist(
			UserGroupForm userGroupForm,
			HttpServletResponse aResponse)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			UpdateUserGroupUsersEvent requestEvent = (UpdateUserGroupUsersEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.UPDATEUSERGROUPUSERS);
			requestEvent.setUserGroupId(userGroupForm.getGroupId());

			Iterator users = userGroupForm.getCurrentUsers().iterator();
			while (users.hasNext())
			{
				SecurityUserResponseEvent userResponseEvent = (SecurityUserResponseEvent) users.next();
				UserGroupUserRequestEvent userEvent = new UserGroupUserRequestEvent();
				userEvent.setLogonId(userResponseEvent.getLogonId());
				requestEvent.addRequest(userEvent);
			}
			dispatch.postEvent(requestEvent);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
		}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD02B1
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		this.persist(userGroupForm,aResponse);
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD02B1
	 */
	public ActionForward assignRoles(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		this.persist(userGroupForm,aResponse);
		return aMapping.findForward(UIConstants.ASSIGN_ROLES_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD02B1
	 */
	public ActionForward mainPage(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.MAIN_MENU);
	}
}