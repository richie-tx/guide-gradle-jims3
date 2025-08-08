// Source file:
// C:\\views\\Security\\app\\src\\ui\\security\\authenication\\jims2Account\\DisplayJIMS2AccountCreateSearchByIdAction.java

package ui.security.authentication.jims2Account.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.GetSPProfileEvent;
import messaging.administerserviceprovider.GetServiceProviderEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.authentication.ValidateJIMS2AccountByEmpIdAndLogonIdEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfileByIdEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.LogonControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.authentication.jims2Account.form.JIMS2AccountForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplayJIMS2AccountCreateSearchByIdAction extends LookupDispatchAction {

	/**
	 * @roseuid 4562205E0208
	 */
	public DisplayJIMS2AccountCreateSearchByIdAction() {

	}

	protected Map getKeyMethodMap() {
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.reset", "reset");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		String forward = UIConstants.FAILURE;
		String userType = jaForm.getUserType();

		if (userType != null && !userType.equals("")) {
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

			if (userType.equalsIgnoreCase("L")) {
				GetOfficerProfileByIdEvent oEvent = (GetOfficerProfileByIdEvent) EventFactory
						.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILEBYID);
				oEvent.setBadgeNum(jaForm.getSearchBadgeNumber());
				oEvent.setOtherIdNum(jaForm.getSearchOtherIdNumber());
				oEvent.setDepartmentId(jaForm.getDepartmentId());
				dispatch.postEvent(oEvent);

				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				//Collection users =
				// MessageUtil.compositeToCollection(compositeResponse,
				// JIMS2AccountResponseEvent.class);
				OfficerProfileResponseEvent officer = (OfficerProfileResponseEvent) MessageUtil.filterComposite(
						compositeResponse, OfficerProfileResponseEvent.class);
				Map dataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(dataMap);

				if (officer == null) {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.officerProfile.found"));
					saveErrors(aRequest, errors);
					this.setFormForOfficer(jaForm, jaForm.getSearchLogonId(), "", "", "", "", "", "", "");
					return aMapping.findForward(UIConstants.SEARCH_FAILURE);
				}
				this.setFormForOfficer(jaForm, officer.getJIMSLogonId(), officer.getFirstName(), officer
						.getMiddleName(), officer.getLastName(), officer.getDepartmentName(), officer.getBadgeNum(),
						officer.getOtherIdNum(), officer.getOfficerId());
				forward = UIConstants.OFFICER_PROFILE_SUCCESS;
			} else if (userType.equalsIgnoreCase("S")) {
				ValidateJIMS2AccountByEmpIdAndLogonIdEvent vEvent = (ValidateJIMS2AccountByEmpIdAndLogonIdEvent) EventFactory
						.getInstance(LogonControllerServiceNames.VALIDATEJIMS2ACCOUNTBYEMPIDANDLOGONID);
				vEvent.setUserAccountId(jaForm.getSearchEmployeeId());
				vEvent.setLogonId(jaForm.getJimsLogonId());
				dispatch.postEvent(vEvent);

				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				Collection users = MessageUtil
						.compositeToCollection(compositeResponse, JIMS2AccountResponseEvent.class);
				if (users != null && !users.isEmpty()) {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Employee record found"));
					saveErrors(aRequest, errors);
					this.setFormForSP(jaForm, jaForm.getSearchLogonId(), "", "", "", "", "", "", "");
					return aMapping.findForward(UIConstants.SEARCH_FAILURE);
				}

				GetSPProfileEvent spEvent = (GetSPProfileEvent) EventFactory
						.getInstance(ServiceProviderControllerServiceNames.GETSPPROFILE);
				spEvent.setEmployeeId(jaForm.getSearchEmployeeId());
				spEvent.setDepartmentInfoRequired(true);
				spEvent.setJimsLogonId(jaForm.getJimsLogonId());
				dispatch.postEvent(spEvent);

				compositeResponse = (CompositeResponse) dispatch.getReply();
				Collection sps = MessageUtil.compositeToCollection(compositeResponse,
						ServiceProviderContactResponseEvent.class);
				Map dataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(dataMap);

				if (sps == null || sps.isEmpty()) {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.serviceProvider.found"));
					saveErrors(aRequest, errors);
					this.setFormForSP(jaForm, jaForm.getSearchLogonId(), "", "", "", "", "", "", "");
					return aMapping.findForward(UIConstants.SEARCH_FAILURE);
				}
				ServiceProviderContactResponseEvent sp = (ServiceProviderContactResponseEvent) sps.toArray()[0];
				GetServiceProviderEvent gspEvent = new GetServiceProviderEvent();
				gspEvent.setServiceProviderId(sp.getServiceProviderId());
				gspEvent.setStatusId("A");
				dispatch.postEvent(gspEvent);
				compositeResponse = (CompositeResponse) dispatch.getReply();
				Collection gsps = MessageUtil.compositeToCollection(compositeResponse,
						JuvenileServiceProviderResponseEvent.class);
				if (gsps == null || gsps.isEmpty()) {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.serviceProvider.found"));
					saveErrors(aRequest, errors);
					this.setFormForSP(jaForm, jaForm.getSearchLogonId(), "", "", "", "", "", "", "");
					return aMapping.findForward(UIConstants.SEARCH_FAILURE);
				}
				JuvenileServiceProviderResponseEvent gsp = (JuvenileServiceProviderResponseEvent) gsps.toArray()[0];

				if ((sp.isAdminContact() && gsp.getAdminUserProfileId().equals(jaForm.getJimsLogonId()) || (!sp
						.isAdminContact() && gsp.getContactUserProfileId().equals(jaForm.getJimsLogonId())))) {

					this.setFormForSP(jaForm, sp.getJIMSLogonId(), sp.getFirstName(), sp.getMiddleName(), sp
							.getLastName(), sp.getProviderName(), sp.getDepartmentName(), sp.getEmployeeId(), sp
							.getJuvServProviderProfileId());
					forward = UIConstants.SERVICE_PROVIDER_SUCCESS;
				
			} else {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.serviceProvider.found"));
				saveErrors(aRequest, errors);
				this.setFormForSP(jaForm, jaForm.getSearchLogonId(), "", "", "", "", "", "", "");
				return aMapping.findForward(UIConstants.SEARCH_FAILURE);
			}
		}}
		return aMapping.findForward(forward);
	}

	/**
	 * @param jaForm
	 * @param logonId
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param providerName
	 * @param departmentName
	 * @param employeeId
	 * @param userAccountId
	 */
	private void setFormForSP(JIMS2AccountForm jaForm, String logonId, String firstName, String middleName,
			String lastName, String providerName, String departmentName, String employeeId, String userAccountId) {
		jaForm.setFirstName(firstName);
		jaForm.setMiddleName(middleName);
		jaForm.setLastName(lastName);
		jaForm.setDepartmentName(departmentName);
		jaForm.setServiceProviderName(providerName);
		jaForm.setUserAccountId(userAccountId);
		jaForm.setEmployeeId(employeeId);
	}

	/**
	 * @param jaForm
	 * @param logonId
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param departmentName
	 * @param badgeNum
	 * @param otherIdNum
	 * @param userAccountId
	 */
	private void setFormForOfficer(JIMS2AccountForm jaForm, String logonId, String firstName, String middleName,
			String lastName, String departmentName, String badgeNum, String otherIdNum, String userAccountId) {
		jaForm.setFirstName(firstName);
		jaForm.setMiddleName(middleName);
		jaForm.setLastName(lastName);
		jaForm.setDepartmentName(departmentName);
		jaForm.setBadgeNumber(badgeNum);
		jaForm.setOtherIdNumber(otherIdNum);
		jaForm.setUserAccountId(userAccountId);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		jaForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward reset(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		jaForm.setSearchBadgeNumber("");
		jaForm.setSearchOtherIdNumber("");
		jaForm.setSearchEmployeeId("");
		return aMapping.findForward(UIConstants.RESET_SUCCESS);
	}
}
