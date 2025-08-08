//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\DisplayUserGroupSummaryAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.GetDepartmentsEvent;
import messaging.agency.GetDepartmentsForASAEvent;
import messaging.codetable.GetCodesEvent;
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
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.SecurityUIHelper;
import ui.security.form.UserGroupForm;

public class DisplayUserGroupSummaryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 425AB74701C5
	 */
	public DisplayUserGroupSummaryAction()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.edit", "edit");
		buttonMap.put("button.delete", "delete");
		buttonMap.put("button.activate", "activate");
		buttonMap.put("button.inactivate", "inactivate");
		buttonMap.put("button.update", "updateUsers");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D6310196
	 */
	public ActionForward edit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.FAILURE;
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		userGroupForm.setAction(UIConstants.UPDATE);
		Collection emptyColl = new ArrayList();
		Collection col = MessageUtil.processEmptyCollection(emptyColl);
		userGroupForm.setAvailableUsers(col);
		userGroupForm.setAvailableAgencies(col);
		userGroupForm.setFirstName("");
		userGroupForm.setLastName("");
		userGroupForm.setDepartment("");
		userGroupForm.setUserId("");
		userGroupForm.setSearchAgencyName("");
		userGroupForm.setUserType("");
		if (SecurityUIHelper.isUserMA())			
		{
			Collection agencyTypes = fetchDropDownCodes(PDCodeTableConstants.AGENCY_TYPE);
			userGroupForm.setUserType(UIConstants.MA_USER_TYPE);
			userGroupForm.setAgencyTypes(agencyTypes);
		}
		if (SecurityUIHelper.isUserSA()) 			
		{
			Collection departments = fetchDepartments(userGroupForm.getAgencyId());
			userGroupForm.setUserType(UIConstants.SA_USER_TYPE);			
			userGroupForm.setDepartments(departments);
		}
		if (SecurityUIHelper.isUserASA()) 			
		{
			userGroupForm.setUserType(UIConstants.ASA_ROLETYPE);			
		}		
		forward = UIConstants.UPDATE_SUCCESS;
		return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D6310196
	 */
	public ActionForward next(
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
			forward = UIConstants.DELETE_SUCCESS;
		}
		if (action.equalsIgnoreCase(UIConstants.ACTIVATE))
		{
			forward = UIConstants.ACTIVATE_SUCCESS;
		}
		if (action.equalsIgnoreCase(UIConstants.INACTIVATE))
		{
			forward = UIConstants.INACTIVATE_SUCCESS;
		}
		return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D6310196
	 */
	public ActionForward activate(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		String forward = UIConstants.ACTIVATE_SUCCESS;
		userGroupForm.setAction(UIConstants.ACTIVATE);
		return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D6310196
	 */
	public ActionForward inactivate(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		String forward = UIConstants.INACTIVATE_SUCCESS;
		userGroupForm.setAction(UIConstants.INACTIVATE);
		return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D6310196
	 */
	public ActionForward updateUsers(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.FAILURE;
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		userGroupForm.setAction(UIConstants.UPDATE);
		forward = UIConstants.UPDATE_USERS_SUCCESS;
		Collection departments = fetchASADepartments(userGroupForm.getAgencyId());
		userGroupForm.setDepartments(departments);
		return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D6310196
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
	 * @roseuid 4256D6310196
	 */
	public ActionForward delete(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		String forward = UIConstants.DELETE_SUCCESS;
		userGroupForm.setAction(UIConstants.DELETE);
		return aMapping.findForward(forward);
	}

	/**
	 * @param codeTableName
	 * @return collection codes
	 */
	public Collection fetchDropDownCodes(String codeTableName)
	{
		GetCodesEvent codesRequestEvent = (GetCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODES);
		codesRequestEvent.setCodeTableName(codeTableName);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(codesRequestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection codes = (Collection) dataMap.get(PDCodeTableConstants.getCodeTableTopic(codeTableName));
		codes = MessageUtil.processEmptyCollection(codes);
		Collections.sort((ArrayList) codes);
		return codes;
	}

	/**
	 * @param agencyId
	 * @return collection departments
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