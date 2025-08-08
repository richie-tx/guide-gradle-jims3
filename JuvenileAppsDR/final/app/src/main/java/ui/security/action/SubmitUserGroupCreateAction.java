//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\SubmitUserGroupCreateAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.GetDepartmentsEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.security.CreateUserGroupEvent;
import messaging.security.ValidateUserGroupDetailsEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.common.Name;
import ui.security.form.UserGroupForm;

public class SubmitUserGroupCreateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42971FF6008D
	 */
	public SubmitUserGroupCreateAction()
	{

	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.addUsers", "addUsers");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.mainPage", "mainMenu");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC03AD
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		userGroupForm.setAction(UIConstants.CREATE);
		String forward = validateUserGroup(userGroupForm);
		if (forward.equals(UIConstants.CREATE_FAILURE) == false)
		{
		    forward = processUserGroup(userGroupForm);
		}
		return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC03AD
	 */
	public ActionForward addUsers(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		userGroupForm.setAction(UIConstants.CREATE);
		String forward = validateUserGroup(userGroupForm);
		if (forward.equals(UIConstants.CREATE_FAILURE)== false)
		{
			forward = processUserGroup(userGroupForm);
			Collection departments = fetchDepartments(userGroupForm.getAgencyId());
			userGroupForm.setDepartments(departments);
			userGroupForm.setLastName("");
			userGroupForm.setFirstName("");
			userGroupForm.setUserId("");
			userGroupForm.setDepartment("");
			Collection emptyColl = new ArrayList();
			Collection col = MessageUtil.processEmptyCollection(emptyColl);
			userGroupForm.setAvailableUsers(col);
			userGroupForm.setCurrentUsers(col);
			if (forward.equals(UIConstants.SUCCESS))
			{
				forward = UIConstants.ADD_SUCCESS;
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
	 * @roseuid 428B82BC03AD
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.CANCEL;
		return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC03AD
	 */
	public ActionForward mainMenu(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.MAIN_MENU;
		return aMapping.findForward(forward);
	}

	private String processUserGroup(UserGroupForm userGroupForm)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		CreateUserGroupEvent requestEvent =	(CreateUserGroupEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.CREATEUSERGROUP);

		requestEvent.setUserGroupDescription(userGroupForm.getUserGroupDescription());
		requestEvent.setUserGroupName(userGroupForm.getUserGroupName());
		requestEvent.setCategory(UIConstants.ROLETYPE_CREATEDBY_SA);
		requestEvent.setAgencyId(userGroupForm.getAgencyId());
		dispatch.postEvent(requestEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		UserGroupResponseEvent userGroupEvent =	(UserGroupResponseEvent) MessageUtil.filterComposite(compositeResponse, UserGroupResponseEvent.class);
		Name creatorName =
			new Name(
				userGroupEvent.getCreatorFirstName(),
				userGroupEvent.getCreatorMiddleName(),
				userGroupEvent.getCreatorLastName());
		userGroupForm.setCreatorName(creatorName);
		userGroupForm.setGroupId(userGroupEvent.getUserGroupId());
		userGroupForm.setUserGroupType(userGroupEvent.getUserGroupType());
		userGroupForm.setGroupStatus(userGroupEvent.getStatus());
		userGroupForm.setStatusId(userGroupEvent.getStatusId());
		return UIConstants.SUCCESS;
	}

	/**
	 * @param agencyId
	 * @return collection departments
	 * @roseuid 428B82BD0158
	 */
	public Collection fetchDepartments(String agencyId)
	{
		GetDepartmentsEvent deptRequestEvent = (GetDepartmentsEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTS);
		deptRequestEvent.setAgencyId(agencyId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(deptRequestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection departments = MessageUtil.compositeToCollection(compositeResponse, DepartmentResponseEvent.class);
		departments = MessageUtil.processEmptyCollection(departments);
		return departments;
	}

	public String validateUserGroup(UserGroupForm userGroupForm)
	{
		ValidateUserGroupDetailsEvent validateEvent = (ValidateUserGroupDetailsEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.VALIDATEUSERGROUPDETAILS);
		validateEvent.setAgencyId(userGroupForm.getAgencyId());
		validateEvent.setUserGroupDescription(userGroupForm.getUserGroupDescription());
		validateEvent.setUserGroupName(userGroupForm.getUserGroupName());
		validateEvent.setCategory(UIConstants.ROLETYPE_CREATEDBY_SA);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(validateEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		String forward = UIConstants.FAILURE;
		DuplicateRecordErrorResponseEvent duplicateErrorMessage = (DuplicateRecordErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
		if (duplicateErrorMessage != null)
		{
			userGroupForm.setErrorMessage(duplicateErrorMessage.getMessage());
			forward = UIConstants.CREATE_FAILURE;
		}
		else
		{
			userGroupForm.setErrorMessage("");
			forward = UIConstants.SUCCESS;
		}
		return forward;
	}
}