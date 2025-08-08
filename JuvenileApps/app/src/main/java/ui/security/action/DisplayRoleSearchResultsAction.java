//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\DisplaySARoleSearchResultsAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.info.reply.CountInfoMessage;
import messaging.security.GetRolesByConstraintsEvent;
import messaging.security.reply.RoleResponseEvent;
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
import ui.security.CommonRoleResponseEventLocator;
import ui.security.SecurityUIHelper;
import ui.security.form.RoleForm;

public class DisplayRoleSearchResultsAction extends LookupDispatchAction
{

	//boolean doesEveryoneRoleExist = false;
	//RoleResponseEvent responseEveryoneRoleEvent = null;

	/**
	 * @roseuid 425AB70101C5
	 */
	public DisplayRoleSearchResultsAction()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.find", "find");
		buttonMap.put("button.refresh", "refresh");
		buttonMap.put("button.createNewRole", "create");

		return buttonMap;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D630031C
	 */
	public ActionForward find(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.FAILURE;
		RoleForm roleForm = (RoleForm) aForm;

		/** Fetch ROLES based on search request criteria */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetRolesByConstraintsEvent aEvent =	(GetRolesByConstraintsEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETROLESBYCONSTRAINTS);
		aEvent.setRoleName(roleForm.getRoleName());
		aEvent.setRoleDescription(roleForm.getRoleDescription());
		aEvent.setCreatorLastName(roleForm.getLastName());
		aEvent.setCreatorFirstName(roleForm.getFirstName());
		aEvent.setAgencyTypeId(roleForm.getAgencyTypeId());
		aEvent.setRoleType(UIConstants.ROLETYPE_CREATEDBY_SA);

		if (SecurityUIHelper.isUserSA())
		{
			String agencyId = SecurityUIHelper.getUserAgencyId();
			aEvent.setAgencyId(agencyId);
		}
		else
		{
			String agName = roleForm.getAgencyName();
			if (agName != null && !(agName.equals("")))
			{
				agName = agName.trim();
			}
			aEvent.setAgencyName(agName);
		}
		dispatch.postEvent(aEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		Collection roles = MessageUtil.compositeToCollection(compositeResponse, RoleResponseEvent.class);
		if (SecurityUIHelper.isUserMA())
		{
			// re initialize the agencyId
			aEvent.setAgencyId("");
		}
		aEvent.setRoleType(UIConstants.ROLETYPE_CREATEDBY_MA);
		dispatch.postEvent(aEvent);
		
		compositeResponse = (CompositeResponse) dispatch.getReply();
		dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		Collection rolesForMA = MessageUtil.compositeToCollection(compositeResponse, RoleResponseEvent.class);
		roles.addAll(rolesForMA);

		//
		boolean doesEveryoneRoleExist = false;
		RoleResponseEvent responseEveryoneRoleEvent = null;
		HashMap organizedRoles = new HashMap();
		Iterator iter = (Iterator) roles.iterator();
		while (iter.hasNext())
		{
			RoleResponseEvent responseEvent = (RoleResponseEvent) iter.next();
			String name = responseEvent.getRoleName();
			if (name.equalsIgnoreCase(UIConstants.EVERYONE))
			{
				doesEveryoneRoleExist = true;
				responseEveryoneRoleEvent = responseEvent;
			}
			else
			{
				if (organizedRoles == null
					|| organizedRoles.isEmpty()
					|| !(organizedRoles.containsKey(responseEvent.getRoleId())))
					organizedRoles.put(responseEvent.getRoleId(), responseEvent);
			}
		}
		if (!doesEveryoneRoleExist)
		{
			responseEveryoneRoleEvent =
				CommonRoleResponseEventLocator.getSpecialRoleResponseEvent(UIConstants.EVERYONE);
		}
		roles = organizedRoles.values();
		//roles = this.reOrganizeRoles(roles);
		int size = 0;
		if (roles != null)
		{
			size = roles.size();
		}
		if (size == 0 || roles == null)
		{
			Collection blank = new ArrayList();
			roleForm.setEveryoneRoles(blank);
			roleForm.setRoles(blank);			
			CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
	       	if (iMessage != null ){
	       		ActionErrors errors = new ActionErrors();
	       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	       		saveErrors(aRequest, errors);
	       	} 
	       	else
	       	{
	       		saveErrorMessage(aRequest, "error.no.roles.found");
	       	}
       		return aMapping.findForward(UIConstants.SEARCH_FAILURE);	       	
		}

		if (responseEveryoneRoleEvent != null)
		{
			Collection everyOneRoleList = new ArrayList();
			everyOneRoleList.add(responseEveryoneRoleEvent);
			roleForm.setEveryoneRoles(everyOneRoleList);
			String inputRoleName = roleForm.getRoleName().toUpperCase();
			String compareName = UIConstants.EVERYONE.toUpperCase();
			if (size == 0 && compareName.indexOf(inputRoleName) == 0)
			{
				size = 1;
			}
		}
		roleForm.setRoles(SecurityUIHelper.sortRoleNames(roles));
		roleForm.setSearchResultSize(String.valueOf(size));
		forward = UIConstants.LISTSUCCESS;
		return aMapping.findForward(forward);
	}

	/**
	 * @param roles
	 * @return collection
	 */
	private Collection getRolesBasedOnUserMARoleType(Collection roles)
	{
		Collection maRoles = new ArrayList();
		Iterator iter = roles.iterator();
		while (iter.hasNext())
		{
			RoleResponseEvent responseEvent = (RoleResponseEvent) iter.next();
			if (!responseEvent.getRoleType().equals("")
				&& !(responseEvent.getRoleType().equalsIgnoreCase(UIConstants.SA_ROLETYPE)))
			{
				maRoles.add(responseEvent);
			}
		}
		return maRoles;
	}

	/**
	 * @param roles
	 * @return
	 */
//	private Collection reOrganizeRoles(Collection roles)
//	{
//		boolean doesEveryoneRoleExist = false;
//		RoleResponseEvent responseEveryoneRoleEvent = null;
//		HashMap organizedRoles = new HashMap();
//		Iterator iter = (Iterator) roles.iterator();
//		while (iter.hasNext())
//		{
//			RoleResponseEvent responseEvent = (RoleResponseEvent) iter.next();
//			String name = responseEvent.getRoleName();
//			if (name.equalsIgnoreCase(UIConstants.EVERYONE))
//			{
//				doesEveryoneRoleExist = true;
//				responseEveryoneRoleEvent = responseEvent;
//			}
//			else
//			{
//				if (organizedRoles == null
//					|| organizedRoles.isEmpty()
//					|| !(organizedRoles.containsKey(responseEvent.getRoleId())))
//					organizedRoles.put(responseEvent.getRoleId(), responseEvent);
//			}
//		}
//		if (!doesEveryoneRoleExist)
//		{
//			responseEveryoneRoleEvent =
//				CommonRoleResponseEventLocator.getSpecialRoleResponseEvent(UIConstants.EVERYONE);
//		}
//		return organizedRoles.values();
//	}
//	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward create(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		roleForm.setRoleName("");
		roleForm.setRoleDescription("");
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		if (SecurityUIHelper.isUserMA())
		{
			roleForm.setCurrentAgencies(emptyColl);
		}
		roleForm.setAgencyId("");
		roleForm.setAgencyName("");
		roleForm.setFeatureName("");
		roleForm.setAvailableAgencies(emptyColl);
		roleForm.setSubSystemName("");
		roleForm.clearStringArray(roleForm.getSelectedFeatures());
		roleForm.clearStringArray(roleForm.getSelectedAgencies());
		roleForm.setFeatures(emptyColl);
		roleForm.setAvailableFeatures(emptyColl);
		roleForm.setCurrentFeatures(emptyColl);
		roleForm.setAction(UIConstants.CREATE);
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
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
		RoleForm roleForm = (RoleForm) aForm;
		String ma = roleForm.getMasterAdmin();
		roleForm.clear();
		roleForm.setMasterAdmin(ma);
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}

	/**
	 * @param aRequest
	 * @param string
	 */
	private void saveErrorMessage(HttpServletRequest aRequest, String string)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(string));
		saveErrors(aRequest, errors);
	}
}