//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\DisplayUserProfileUpdateAction.java

package ui.contact.user.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetDepartmentsEvent;
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
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.UIUtil;
import ui.contact.user.form.UserProfileForm;

public class DisplayUserProfileCreateUpdateAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();		
		buttonMap.put("button.createNewUserProfile", "createNewUserProfile");		
		return buttonMap;
	}

	/**
	 * @roseuid 43F4FC4100B4
	 */
	public DisplayUserProfileCreateUpdateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 43F4EE4E0346
	 */
	public ActionForward createNewUserProfile(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileForm userForm = (UserProfileForm) aForm;
		userForm.clear();
		userForm.clearDepartments();
		userForm.setGenericUserType("Non-Generic");
		userForm.setGenericUserTypeId("N");
//		userForm.setPublicInd("N");
		userForm.setUvCodeGeneration("N");
		userForm.setUserTypes(CodeHelper.getCodes(PDCodeTableConstants.USER_TYPE));
		userForm.setUserStatuses(CodeHelper.getCodes(PDCodeTableConstants.AGENCY_STATUS));
		userForm.setGenericUserTypes(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GENERIC_USER_TYPE, true)); //U.S #79250
		Collection workDays= CodeHelper.getCodes(PDCodeTableConstants.WORK_DAY);
		userForm.setWorkDays(UIUtil.sortCodesByCodeId(workDays));
		ISecurityManager securityManager = (ISecurityManager)ContextManager.getSession().get("ISecurityManager");		
		IUserInfo userInfo = securityManager.getIUserInfo();		
		userForm.setRequestorName(new Name(userInfo.getFirstName(),userInfo.getMiddleName(),userInfo.getLastName()));
		userForm.setAction("create");			
		userForm.setDepartmentId(userInfo.getDepartmentId());
//		 Get users Department Name and Org Code for display
		if (userInfo.getDepartmentId() != null && !userInfo.getDepartmentId().equalsIgnoreCase("") ){
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetDepartmentsEvent departmentEvent = (GetDepartmentsEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTS);
			departmentEvent.setDepartmentId(userInfo.getDepartmentId());
			dispatch.postEvent(departmentEvent);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
		
			Collection departments = MessageUtil.compositeToCollection(compositeResponse, DepartmentResponseEvent.class);

			if (departments != null && departments.size() > 0)
			{
		      	Iterator iter = departments.iterator();
			    while (iter.hasNext())
				{
					DepartmentResponseEvent responseEvent = (DepartmentResponseEvent) iter.next();
					userForm.setDepartmentName(responseEvent.getDepartmentName());
					userForm.setOrgCode(responseEvent.getOrgCode());
				}				
			}	
		}
		userForm.setLoggedInUserType(userInfo.getUserTypeId());		
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}

}
