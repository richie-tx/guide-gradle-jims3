//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\DisplaySARoleSearchResultsAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetAgenciesByJmcRepEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.security.GetRolesByConstraintsEvent;
import messaging.security.GetSARoleAgencyInfoEvent;
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
//import org.apache.struts.actions.LookupDispatchAction;

import ui.action.JIMSBaseAction;
import ui.security.CommonRoleResponseEventLocator;
import ui.security.SecurityUIHelper;
import ui.security.form.RoleSAForm;

public class DisplaySARoleSearchResultsAction extends JIMSBaseAction
{

	/**
	 * @roseuid 425AB70101C5
	 */
	public DisplaySARoleSearchResultsAction()
	{
	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.find", "find");
		buttonMap.put("button.refresh", "refresh");
		buttonMap.put("button.createNewSARole", "create");
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
		boolean doesAllSecurityAdminRoleExist = false;
		RoleResponseEvent responseAllSecurityAdminRoleEvent = null;
		//String forward = UIConstants.FAILURE;
		String forward = null;
		RoleSAForm saRoleForm = (RoleSAForm) aForm;

		/** Fetch ROLES based on search request criteria */

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetRolesByConstraintsEvent aEvent = (GetRolesByConstraintsEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETROLESBYCONSTRAINTS);

		String agName = saRoleForm.getAgencyName();
		if (agName != null && !(agName.equals("")))
		{
			agName = agName.trim();
		}
		aEvent.setAgencyName(agName);
		aEvent.setRoleName(saRoleForm.getRoleName());
		aEvent.setRoleType(UIConstants.SA_ROLETYPE);
		dispatch.postEvent(aEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection roles = MessageUtil.compositeToCollection(compositeResponse, RoleResponseEvent.class);
		//roles = this.reOrganizeRoles(roles);
		dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		//compositeResponse = null;
		RoleResponseEvent respEvent = null;
		Collection organizedRoles = new ArrayList();
		Iterator iter = (Iterator) roles.iterator();
		while (iter.hasNext())
		{
			RoleResponseEvent responseEvent = (RoleResponseEvent) iter.next();
			// Get the Agency Info
			GetSARoleAgencyInfoEvent event = new GetSARoleAgencyInfoEvent();
			event.setRoleId(responseEvent.getRoleId());
			dispatch.postEvent(event);

			compositeResponse = (CompositeResponse) dispatch.getReply();
			dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);

			respEvent = (RoleResponseEvent) MessageUtil.filterComposite(compositeResponse, RoleResponseEvent.class);
			String name = responseEvent.getRoleName();
			if (name.equalsIgnoreCase(UIConstants.ALL_SECURITIES_ADMINISTRATORS))
			{
				doesAllSecurityAdminRoleExist = true;
				responseAllSecurityAdminRoleEvent = responseEvent;
			}
			else
			{
				responseEvent.setAgencyName(respEvent.getAgencyName());
				organizedRoles.add(responseEvent);
			}
		}

		if (!doesAllSecurityAdminRoleExist)
		{
			responseAllSecurityAdminRoleEvent = CommonRoleResponseEventLocator.getSpecialRoleResponseEvent(UIConstants.ALL_SECURITIES_ADMINISTRATORS);
		}
		roles = organizedRoles;
		
		//
		int size = 0;
		if (roles != null)
		{
			size = roles.size();
		}
		if (size == 0 || roles == null)
		{
       		Collection blank = new ArrayList();
       		saRoleForm.setRoles(blank);
       		saRoleForm.setAllSecurityAdminRoles(blank);	       
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
	       		ActionErrors errors = new ActionErrors();
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.roles.found"));
	       		saveErrors(aRequest, errors);
	       	}
	       	forward = UIConstants.SEARCH_FAILURE;
       	//	return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}		
		if ((responseAllSecurityAdminRoleEvent != null) && (forward == null))
		{
			Collection allSecurityAdminRoleList = new ArrayList();
			allSecurityAdminRoleList.add(responseAllSecurityAdminRoleEvent);
			saRoleForm.setAllSecurityAdminRoles(allSecurityAdminRoleList);
			String inputRoleName = saRoleForm.getRoleName().toUpperCase();
			String compareName = UIConstants.ALL_SECURITIES_ADMINISTRATORS.toUpperCase();
			if (size == 0 && compareName.indexOf(inputRoleName) == 0)
			{
				size = 1;
			}
		} 
		else if (forward == null)
		{
			CountInfoMessage infoEvent = new CountInfoMessage();
			CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
			if (iMessage != null ){
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.max.limit.exceeded");
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);
				forward = UIConstants.FAILURE;
				//return aMapping.findForward(UIConstants.FAILURE);
			}
		}
		if (forward == null){
			saRoleForm.setRoles(SecurityUIHelper.sortRoleNames(roles));
			saRoleForm.setSearchResultSize(String.valueOf(size));
			forward = UIConstants.LISTSUCCESS;
		}
	
		return aMapping.findForward(forward);
	}

	/**
	 * @param roles
	 * @return
	 */
//	private Collection reOrganizeRoles(Collection roles)
//	{
//		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
//		CompositeResponse compositeResponse = null;
//		RoleResponseEvent respEvent = null;
//		Collection organizedRoles = new ArrayList();
//		Iterator iter = (Iterator) roles.iterator();
//		while (iter.hasNext())
//		{
//			RoleResponseEvent responseEvent = (RoleResponseEvent) iter.next();
//			// Get the Agency Info
//			GetSARoleAgencyInfoEvent event = new GetSARoleAgencyInfoEvent();
//			event.setRoleId(responseEvent.getRoleId());
//			dispatch.postEvent(event);
//
//			compositeResponse = (CompositeResponse) dispatch.getReply();
//			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
//			MessageUtil.processReturnException(dataMap);
//
//			respEvent = (RoleResponseEvent) MessageUtil.filterComposite(compositeResponse, RoleResponseEvent.class);
//			String name = responseEvent.getRoleName();
//			if (name.equalsIgnoreCase(UIConstants.ALL_SECURITIES_ADMINISTRATORS))
//			{
//				doesAllSecurityAdminRoleExist = true;
//				responseAllSecurityAdminRoleEvent = responseEvent;
//			}
//			else
//			{
//				responseEvent.setAgencyName(respEvent.getAgencyName());
//				organizedRoles.add(responseEvent);
//			}
//		}
//
//		if (!doesAllSecurityAdminRoleExist)
//		{
//			responseAllSecurityAdminRoleEvent = CommonRoleResponseEventLocator.getSpecialRoleResponseEvent(UIConstants.ALL_SECURITIES_ADMINISTRATORS);
//		}
//		return organizedRoles;
//	}

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
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		saRoleForm.setRoleName("");
		saRoleForm.setRoleDescription("");
		saRoleForm.setAgencyId("");
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		saRoleForm.setFeatures(emptyColl);
		saRoleForm.setFeatureName("");
		saRoleForm.setSubSystemName("");

		//Retrieve Agencies which have JMC Representive = yes for drop down list
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetAgenciesByJmcRepEvent event = new GetAgenciesByJmcRepEvent();
		event.setJmcRepId("Y");
		dispatch.postEvent(event);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection agencies = MessageUtil.compositeToCollection(compositeResponse, AgencyResponseEvent.class);
		agencies = SecurityUIHelper.getAgenciesWhichDoesNotHaveSARole(agencies);
		if(agencies == null || agencies.isEmpty()){
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.saagency.notavailable"));
			saveErrors(aRequest, errors);
		}else{
			saRoleForm.setAgencies(agencies);
		}
		saRoleForm.setAction(UIConstants.CREATE);
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
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		saRoleForm.clear();
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.find", "find");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.createNewSARole", "create");
		
	}
}