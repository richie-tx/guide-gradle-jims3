//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\DisplayUserGroupSAAddUsersAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.GetDepartmentsEvent;
import messaging.agency.GetDepartmentsForASAEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.security.form.UserGroupForm;

public class DisplayUserGroupSAAddUsersAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42971FCD008C
	 */
	public DisplayUserGroupSAAddUsersAction()
	{

	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.mainPage", "mainPage");
		buttonMap.put("button.addUsers", "addUsers");

		return buttonMap;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD0158
	 */
	public ActionForward addUsers(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		if (userGroupForm.getUserType().equals(UIConstants.SA_ROLETYPE))
		{
			Collection departments = fetchDepartments(userGroupForm.getAgencyId());
			userGroupForm.setDepartments(departments);
		}
		if (userGroupForm.getUserType().equals(UIConstants.ASA_ROLETYPE))
		{
			Collection departments = fetchASADepartments(userGroupForm.getAgencyId());
			userGroupForm.setDepartments(departments);
		}
		userGroupForm.setLastName("");
		userGroupForm.setFirstName("");
		userGroupForm.setUserId("");
		userGroupForm.setDepartment("");
		Collection emptyColl = new ArrayList();
		Collection col = MessageUtil.processEmptyCollection(emptyColl);
		userGroupForm.setAvailableUsers(col);
		userGroupForm.setCurrentUsers(col);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD0158
	 */
	public ActionForward mainPage(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		String userType = userGroupForm.getUserType();
		String agencyId = userGroupForm.getAgencyId();
		userGroupForm.clear();
		userGroupForm.setUserType(userType);
		userGroupForm.setAgencyId(agencyId);
		return aMapping.findForward(UIConstants.MAIN_MENU);
	}

	/**
	 * @param agencyId
	 * @return collectoin departments
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

	/**
	 * @param agencyId
	 * @return collectoin departments
	 * @roseuid 428B82BD0158
	 */
	public Collection fetchASADepartments(String agencyId)
	{
		GetDepartmentsForASAEvent deptRequestEvent = (GetDepartmentsForASAEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTSFORASA);
		deptRequestEvent.setAgencyId(agencyId);
		ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		IUserInfo user = manager.getIUserInfo();
		String logonId = user.getJIMSLogonId();
		deptRequestEvent.setLogonId(logonId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(deptRequestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection departments = MessageUtil.compositeToCollection(compositeResponse, DepartmentResponseEvent.class);
		departments = MessageUtil.processEmptyCollection(departments);
		return departments;
	}
}