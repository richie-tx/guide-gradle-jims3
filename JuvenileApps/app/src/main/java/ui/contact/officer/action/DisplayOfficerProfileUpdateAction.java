//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\DisplayOfficerProfileUpdateAction.java

package ui.contact.officer.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.GetJuvLocationUnitsByLocationIdEvent;
import messaging.administerlocation.GetLocationsByAgencyEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.agency.GetDepartmentsEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.officer.reply.OfficerUpdateErrorResponseEvent;
import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.contact.user.reply.UserNotFoundResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.officer.GetOfficerProfileEvent;
import messaging.officer.GetOfficerProfilesEvent;
import messaging.officer.ValidateOfficerProfileEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import messaging.user.GetUserProfilesEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.LocationControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import ui.contact.LoadManageOfficerCodeTables;
import ui.contact.UIContactHelper;
import ui.contact.officer.form.OfficerForm;

public class DisplayOfficerProfileUpdateAction extends LookupDispatchAction
{
	/**
	 * @roseuid 42E6766A013F
	 */
	public DisplayOfficerProfileUpdateAction()
	{

	}

	/* (non-Javadoc)
		* @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
		*/
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.cancelCreate", "cancelCreate");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.reset", "reset");
		buttonMap.put("button.next", "next");
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.validateDepartmentCode", "validateDepartmentCode");
		buttonMap.put("button.validateManagerId", "validateManagerId");
		buttonMap.put("button.createNewOfficer", "createNewOfficer");
		buttonMap.put("button.createOfficerProfile", "createOfficer");
		buttonMap.put("button.find", "findUnits");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
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
		*/
	public ActionForward cancelCreate(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL_CREATE);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OfficerForm officerForm = (OfficerForm) aForm;
		officerForm.setStatusId("");
		return aMapping.findForward(UIConstants.BACK);
	}

	/**
		   * @param aMapping
		   * @param aForm
		   * @param aRequest
		   * @param aResponse
		   * @return ActionForward
		   */
	public ActionForward reset(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.RESET_SUCCESS);
	}

		/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		*/
		public ActionForward findUnits(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			OfficerForm officerForm = (OfficerForm) aForm;
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetJuvLocationUnitsByLocationIdEvent event = 
				(GetJuvLocationUnitsByLocationIdEvent ) EventFactory.getInstance(LocationControllerServiceNames.GETJUVLOCATIONUNITSBYLOCATIONID);
			event.setLocationId(officerForm.getJuvLocationId());
			event.setAgencyId(getDepartmentId(officerForm));
			
			dispatch.postEvent(event);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			List coll = MessageUtil.compositeToList(compositeResponse, LocationResponseEvent.class);
			if (coll != null)
			{
				Collections.sort(coll, LocationResponseEvent.JuvUnitNameComparator);
				officerForm.setJuvUnits(coll);
			}
			return aMapping.findForward("createSuccess");
		}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OfficerForm officerForm = (OfficerForm) aForm;
		
		if (officerForm.getOfficerTypeId().equalsIgnoreCase("J") && !(officerForm.getDepartmentId().equalsIgnoreCase("JUV")))
		{
			ActionErrors errors = new ActionErrors();
			errors.add(
				ActionErrors.GLOBAL_MESSAGE,
				new ActionMessage(
					"error.officer.officerType"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SUMMARY_FAILURE);
		}
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		ValidateOfficerProfileEvent event =
			(ValidateOfficerProfileEvent) EventFactory.getInstance(
				OfficerProfileControllerServiceNames.VALIDATEOFFICERPROFILE);

		String originaluserId = officerForm.getOriginalUserId();
		if (originaluserId != null && originaluserId.equalsIgnoreCase(officerForm.getUserId()))
		{
			// this condition may meet during the update and no change has been done
			event.setLogonId("");
		}
		else
		{
			event.setLogonId(officerForm.getUserId());
		}

		String idNumber = officerForm.getOtherIdNum();
		if (idNumber != null && !(idNumber.equals("")))
		{
			idNumber = idNumber.toUpperCase();
		}else{
			idNumber = "";
		}

		if (idNumber.equalsIgnoreCase(officerForm.getOriginalOtherIdNumber()))
		{
			// this condition may meet during the update and no change has been done
			event.setOtherIdNum("");
		}
		else
		{
			event.setOtherIdNum(idNumber);
		}

		String badgeNumber = officerForm.getBadgeNum();
		if (badgeNumber != null && !(badgeNumber.equals("")))
		{
			badgeNumber = badgeNumber.toUpperCase();
		}else{
			badgeNumber = "";
		}

		if (badgeNumber.equalsIgnoreCase(officerForm.getOriginalBadgeNumber()))
		{
			// this condition may meet during the update and no change has been done
			event.setBadgeNum("");
		}
		else
		{
			event.setBadgeNum(badgeNumber);
		}
		
		event.setDepartmentId(officerForm.getDepartmentId().toUpperCase());
		dispatch.postEvent(event);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
        
		Object obj = MessageUtil.filterComposite(compositeResponse, UserNotFoundResponseEvent.class);
		if (obj != null)
		{
			UserNotFoundResponseEvent userNotFoundResponseEvent = (UserNotFoundResponseEvent) obj;
			ActionErrors errors = new ActionErrors();
			errors.add(
				ActionErrors.GLOBAL_MESSAGE,
				new ActionMessage(
					userNotFoundResponseEvent.getErrorKey(),
					userNotFoundResponseEvent.getUserId(),
					userNotFoundResponseEvent.getDepartmentId()));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SUMMARY_FAILURE);
		}
		
		obj = MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
		if (obj != null)
		{
			DuplicateRecordErrorResponseEvent duplicateEvent = (DuplicateRecordErrorResponseEvent) obj;
			if (duplicateEvent.getMessage() != null && duplicateEvent.getMessage().startsWith("Badge"))
			{
				this.sendToErrorPage(aRequest, "error.badge.exist");
			}
			else
				if (duplicateEvent.getMessage() != null && duplicateEvent.getMessage().startsWith("Other"))
				{
					this.sendToErrorPage(aRequest, "error.otherId.exist");
				}
				else
				{
					this.sendToErrorPage(aRequest, "error.common");
				}
			return aMapping.findForward(UIConstants.SUMMARY_FAILURE);
		}

		obj = MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);
		if (obj != null)
		{
			OfficerProfileResponseEvent officerprofileEvent = (OfficerProfileResponseEvent) obj;
			ActionErrors errors = new ActionErrors();
			errors.add(
				ActionErrors.GLOBAL_MESSAGE,
				new ActionMessage(
					"error.officer.userId.duplicate",
					officerprofileEvent.getUserId(),
					officerprofileEvent.getDepartmentName()));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SUMMARY_FAILURE);
		}
		// if logon id is generic - send message 
		obj = MessageUtil.filterComposite(compositeResponse, OfficerUpdateErrorResponseEvent.class);
		if (obj != null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.officer.generic.logon"));

			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SUMMARY_FAILURE);
		}
		//	set the values from dropdownlist
		UIContactHelper.setManageOfficerValuesFromDropDownList(officerForm);

		//  check officerType = Juv Probation / Law Enforcement
		this.checkOfficerType(officerForm, aRequest, aMapping);

		// check badge and payroll Number
		this.checkBadgeOrPayrollNumber(officerForm, aRequest, aMapping);

		// in case user have not validated department code
		if (officerForm.getDepartmentId() != null && !(officerForm.getDepartmentId().equals("")))
		{
			if (!isValidDepartmentCode(officerForm))
			{
				this.sendToErrorPage(aRequest, "error.no.department.found");
				return aMapping.findForward(UIConstants.VALIDATE_DEPARTMENT_SUCCESS);
			}
		}

		// In case user have not validated manager code
		if (officerForm.getManagerId() != null && !(officerForm.getManagerId().equals("")))
		{
			if (!isValidManagerId(officerForm,aRequest))
			{
				this.sendToErrorPage(aRequest, "error.no.managerProfile.found");
				return aMapping.findForward(UIConstants.VALIDATE_DEPARTMENT_SUCCESS);
			}
		}
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OfficerForm officerForm = (OfficerForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		ValidateOfficerProfileEvent event =
			(ValidateOfficerProfileEvent) EventFactory.getInstance(
				OfficerProfileControllerServiceNames.VALIDATEOFFICERPROFILE);

		event.setLogonId(officerForm.getUserId());
		event.setDepartmentId(officerForm.getDepartmentId());
		event.setBadgeNum(officerForm.getBadgeNum());
		event.setOtherIdNum(officerForm.getOtherIdNum());
		dispatch.postEvent(event);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Object obj = MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
		if (obj != null)
		{
			DuplicateRecordErrorResponseEvent duplicateEvent = (DuplicateRecordErrorResponseEvent) obj;
			if (duplicateEvent.getMessage() != null && duplicateEvent.getMessage().startsWith("Badge"))
			{
				this.sendToErrorPage(aRequest, "error.badge.exist");
				return null;
			}
			else
				if (duplicateEvent.getMessage() != null && duplicateEvent.getMessage().startsWith("Payroll"))
				{
					this.sendToErrorPage(aRequest, "error.payroll.exist");
				}
				else
					if (duplicateEvent.getMessage() != null && duplicateEvent.getMessage().startsWith("User"))
					{
						this.sendToErrorPage(aRequest, "error.otherId.exist");
					}
					else
					{
						this.sendToErrorPage(aRequest, "error.common");
					}
			return aMapping.findForward(UIConstants.SUMMARY_FAILURE);
		}
		return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward validateDepartmentCode(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OfficerForm officerForm = (OfficerForm) aForm;
		if (!isValidDepartmentCode(officerForm))
		{
			this.sendToErrorPage(aRequest, "error.no.department.found");
		}
		ISecurityManager securityManager = (ISecurityManager)ContextManager.getSession().get("ISecurityManager");
		IUserInfo userInfo = securityManager.getIUserInfo();
// defect#45225 - 09/19/07 - load locations only if user dept equals JUV 
		if (userInfo.getDepartmentId().equalsIgnoreCase("JUV"))
		{
			GetLocationsByAgencyEvent locationsEvent = new GetLocationsByAgencyEvent();
			locationsEvent.setAgencyId(officerForm.getDepartmentId());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(locationsEvent);
			//Getting PD Response Event		
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
			
			//Getting PD Response Event		  
			Collection locationResponses = MessageUtil.compositeToCollection(compositeResponse, LocationResponseEvent.class);
			//remove the inactive locations
			Iterator iter = locationResponses.iterator();
			Collection temp = new ArrayList();
			while(iter.hasNext())
			{
				LocationResponseEvent locResp = (LocationResponseEvent)iter.next();
				if(locResp.getStatusId().equalsIgnoreCase("A"))
					temp.add(locResp);
			}
			Collections.sort((ArrayList) temp);
			officerForm.setJuvLocations(temp);
		}	
		return aMapping.findForward(UIConstants.VALIDATE_DEPARTMENT_SUCCESS);
	}

	/**
	 * @param officerForm
	 * @return boolean
	 */
	private boolean isValidDepartmentCode(OfficerForm officerForm)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetDepartmentsEvent departmentEvent =
			(GetDepartmentsEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTS);

		departmentEvent.setDepartmentId(officerForm.getDepartmentId());
		dispatch.postEvent(departmentEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		DepartmentResponseEvent departmentResponseEvent =
			(DepartmentResponseEvent) MessageUtil.filterComposite(compositeResponse, DepartmentResponseEvent.class);
		if (departmentResponseEvent != null)
		{
			officerForm.setDepartmentName(departmentResponseEvent.getDepartmentName());
			return true;
		}
		else
		{
			officerForm.setDepartmentName("");
			return false;
		}
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward validateManagerId(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OfficerForm officerForm = (OfficerForm) aForm;
		if (!isValidDepartmentCode(officerForm))
		{
			this.sendToErrorPage(aRequest, "error.no.department.found");
		}
		else
		{
			if (!isValidManagerId(officerForm,aRequest))
			{
				
			}
		}
		return aMapping.findForward(UIConstants.VALIDATE_MANAGER_SUCCESS);
	}

	/**
	 * @param officerForm
	 * @return boolean
	 */
	private boolean isValidManagerId(OfficerForm officerForm,HttpServletRequest aRequest)
	{
		/*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUserProfilesEvent managerEvent =
			(GetUserProfilesEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILES);
		managerEvent.setLogonId(officerForm.getManagerId());
		managerEvent.setDepartmentId(officerForm.getDepartmentId());
		dispatch.postEvent(managerEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		UserResponseEvent userResponseEvent =
			(UserResponseEvent) MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);
		*/
	    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetOfficerProfilesEvent officerEvent = (GetOfficerProfilesEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILES);

	    	officerEvent.setUserID(officerForm.getManagerId());
		dispatch.postEvent(officerEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		OfficerProfileResponseEvent officerResponseEvent = (OfficerProfileResponseEvent) MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);
		this.setManagerProfile(officerForm, "", "", "", "");
		
		
		if (officerResponseEvent != null)
		{
			if (officerResponseEvent.getStatusId()==null ||  !(officerResponseEvent.getStatusId().equals("A"))){
				this.sendToErrorPage(aRequest, "error.active.user");
				return false;
			}
			this.setManagerProfile(
				officerForm,
				officerResponseEvent.getFirstName(),
				officerResponseEvent.getLastName(),
				officerResponseEvent.getMiddleName(),
				officerResponseEvent.getUserId() );
			return true;
		}
		else
		{
			this.setManagerProfile(officerForm, "", "", "", "");
			this.sendToErrorPage(aRequest, "error.no.managerProfile.found");
			return false;
		}
	}

	/**
	 * @param officerForm
	 * @param firstName
	 * @param lastName
	 * @param middleName
	 * @param logonId
	 */
	private void setManagerProfile(
		OfficerForm officerForm,
		String firstName,
		String lastName,
		String middleName,
		String logonId)
	{
		officerForm.setManagerFirstName(firstName);
		officerForm.setManagerLastName(lastName);
		officerForm.setManagerMiddleName(middleName);
		officerForm.setManagerId(logonId);
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
	public ActionForward createNewOfficer(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OfficerForm officerForm = (OfficerForm) aForm;
		//String selectedUserProfileId = officerForm.getSelectedUserProfile();

		officerForm.setJuvLocations(CodeHelper.getLocationCodes());
		officerForm.setJuvUnits(UIContactHelper.getJuvUnitCodes()); 
		
		officerForm.setJuvUnits(new ArrayList());		
		String selectedUserProfileId = aRequest.getParameter("selectedUser");

		if (selectedUserProfileId != null && !(selectedUserProfileId.equals("")))
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			ValidateOfficerProfileEvent event = (ValidateOfficerProfileEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.VALIDATEOFFICERPROFILE);
			event.setLogonId(selectedUserProfileId);
			dispatch.postEvent(event);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
			Object obj = MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);
			if (obj != null)
			{
				OfficerProfileResponseEvent officerprofileEvent = (OfficerProfileResponseEvent) obj;
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.officer.userId.duplicate",
						officerprofileEvent.getUserId(),
						officerprofileEvent.getDepartmentName()));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.CREATE_FAILURE);
			}
			
			Iterator userProfileIter = officerForm.getUserProfiles().iterator();
			SecurityUserResponseEvent responseEvent = null;
			while (userProfileIter.hasNext())
			{
				responseEvent = (SecurityUserResponseEvent) userProfileIter.next();
				if (responseEvent.getLogonId().equals(selectedUserProfileId))
				{
					break;
				}
			}
			this.setOfficerForm(officerForm, responseEvent);
		}
		else
		{
			officerForm.clear();
			officerForm.setStatusId("A");		
			officerForm.setDepartmentId(getDepartmentId(officerForm));
		}
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}

	
	public ActionForward createOfficer(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			OfficerForm myForm=(OfficerForm)aForm;
			myForm.setAction(UIConstants.CREATE);
			LoadManageOfficerCodeTables.getInstance().setOfficerForm(myForm);
			myForm.setJuvUnits(new ArrayList());			
			return aMapping.findForward("createSuccess");
		}
	/**
	 * @param officerForm
	 * @param responseEvent SecurityUserResponseEvent
	 */
	private void setOfficerForm(OfficerForm officerForm, SecurityUserResponseEvent responseEvent)
	{
		officerForm.setDepartmentName(responseEvent.getDepartmentName());
		officerForm.setDepartmentId(responseEvent.getDepartmentId());
		officerForm.setFirstName(responseEvent.getFirstName());
		officerForm.setLastName(responseEvent.getLastName());
		officerForm.setMiddleName(responseEvent.getMiddleName());
		officerForm.setUserId(responseEvent.getLogonId());
		SocialSecurity ss = new SocialSecurity(responseEvent.getSsn());
		officerForm.setSsn1(ss.getSsn1());
		officerForm.setSsn2(ss.getSsn2());
		officerForm.setSsn3(ss.getSsn3());
		PhoneNumber pn = new PhoneNumber(responseEvent.getPhoneNum());
		officerForm.setWorkPhoneAreaCode(pn.getAreaCode());
		officerForm.setWorkPhonePrefix(pn.getPrefix());
		officerForm.setWorkPhoneMain(pn.get4Digit());
		officerForm.setExtn(responseEvent.getPhoneExt());
		officerForm.setPager(responseEvent.getPager());
		officerForm.setHomePhone(responseEvent.getHomePhoneNum());
		officerForm.setCellPhone(responseEvent.getCellPhone());
		officerForm.setEmail(responseEvent.getEmail());
		officerForm.setFax(responseEvent.getFaxNum());
		officerForm.setFaxLocation(responseEvent.getFaxLocation());
	}

	/**
	 * @param aRequest
	 * @param errorKey
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, String errorKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));
		saveErrors(aRequest, errors);
	}

	/**
	 * @param officerForm
	 * @param aRequest
	 * @param aMapping
	 * @return ActionForward
	 */
	private ActionForward checkOfficerType(
		OfficerForm officerForm,
		HttpServletRequest aRequest,
		ActionMapping aMapping)
	{
		// check officertype = juv probation then user id is required
		if (officerForm.getOfficerTypeId() != null && officerForm.getOfficerTypeId().equals("J"))
		{
			if (officerForm.getUserId() == null || officerForm.getUserId().equals(""))
			{
				sendToErrorPage(aRequest, "error.userId.required");
				return aMapping.findForward(UIConstants.VALIDATE_MANAGER_SUCCESS);
			}
		}
		// check officertype = juv probation then manager id is required
		if (officerForm.getOfficerTypeId() != null && officerForm.getOfficerTypeId().equals("J"))
		{
			if (officerForm.getManagerId() == null || officerForm.getManagerId().equals(""))
			{
				sendToErrorPage(aRequest, "error.managerId.required");
				return aMapping.findForward(UIConstants.FAILURE);
			}
		}

		// check officertype = juv probation / Law Enforcement then department id is required
		if (officerForm.getOfficerTypeId() != null
			&& (officerForm.getOfficerTypeId().equals("J") || officerForm.getOfficerTypeId().equals("L")))
		{
			if (officerForm.getDepartmentId() == null || officerForm.getDepartmentId().equals(""))
			{
				sendToErrorPage(aRequest, "error.departmentId.required");
				return aMapping.findForward(UIConstants.FAILURE);
			}
		}
		return null;
	}

	/**
	 * @param officerForm
	 * @param aRequest
	 * @param aMapping
	 * @return ActionForward
	 */
	private ActionForward checkBadgeOrPayrollNumber(
		OfficerForm officerForm,
		HttpServletRequest aRequest,
		ActionMapping aMapping)
	{
		String badgeNumber = officerForm.getBadgeNum();
		String idNumber = officerForm.getOtherIdNum();
		if (badgeNumber == null || badgeNumber.equals("") && (idNumber == null || idNumber.equals("")))
		{
			sendToErrorPage(aRequest, "error.eitherBadgeOrPayrollNumberEmpty");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		return null;
	}
	
	private String getDepartmentId(OfficerForm of)
	{
		ISecurityManager securityManager = (ISecurityManager)ContextManager.getSession().get("ISecurityManager");		
		IUserInfo userInfo = securityManager.getIUserInfo();
		return userInfo.getDepartmentId();
		
	}
}