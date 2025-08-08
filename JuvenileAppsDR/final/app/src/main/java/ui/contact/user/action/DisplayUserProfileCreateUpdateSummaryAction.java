//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\DisplayUserProfileUpdateSummaryAction.java

package ui.contact.user.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import naming.AgencyControllerServiceNames;
//import naming.OfficerProfileControllerServiceNames;
import naming.UserControllerServiceNames;
import messaging.agency.GetAgenciesDepartmentsEvent;
import messaging.agency.GetDepartmentEvent;
import messaging.agency.GetDepartmentsForASAEvent;
//import messaging.officer.ValidateOfficerProfileEvent;
import messaging.security.GetDepartmentConstraintsForUserAdministrationEvent;
import messaging.security.reply.DepartmentConstraintsForUserAdministrationResponseEvent;
//import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import messaging.user.ValidateUserCreateUpdateRequirementsEvent;
import messaging.user.reply.DuplicateUserResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;
import naming.PDCodeTableConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import ui.contact.user.form.UserProfileForm;
import ui.security.SecurityUIHelper;

public class DisplayUserProfileCreateUpdateSummaryAction extends LookupDispatchAction
{

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.reset", "reset");
		buttonMap.put("button.next", "next");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.continueUpdate", "continueUpdate");
		buttonMap.put("button.continueCreate", "continueCreate");
		buttonMap.put("button.validateDepartmentCode", "validateDepartmentCode");
		return buttonMap;
	}
		

	/**
	 *
	 */
	public DisplayUserProfileCreateUpdateSummaryAction()
	{

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
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		
		if(userProfileForm.getAction().equalsIgnoreCase("create"))
		{
/* 05/08/2007 per ER 39600 -- remove badge and other ID number */			
/*			if(!userProfileForm.getBadgeNum().equals("") || !userProfileForm.getOtherIdNum().equals(""))
			{
				ValidateOfficerProfileEvent event =
					(ValidateOfficerProfileEvent) EventFactory.getInstance(
						OfficerProfileControllerServiceNames.VALIDATEOFFICERPROFILE);
				event.setDepartmentId(userProfileForm.getDepartmentId());
				event.setBadgeNum(userProfileForm.getBadgeNum());
				event.setOtherIdNum(userProfileForm.getOtherIdNum());
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(event);
				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				Map dataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(dataMap);
				Object obj = MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
				if(obj != null)
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
					return aMapping.findForward("validateDepartmentFailure");
				}
			} */
		
			DepartmentResponseEvent responseEvent = getValidDepartment(aRequest, userProfileForm);
			String userType = SecurityUIHelper.getUserTypeId();
			if (responseEvent == null && userType.equalsIgnoreCase("SA"))
			{
				// 04/02/07 LDeen Defect #38917
				//this.saveErrors("errors.invalid","SA type users can set only departments which they are allowed to manage. Department code ",userProfileForm,aRequest);
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid","You are not authorized to manage the selected department. Select an appropriate department or contact JIMS. Department"));			
				saveErrors(aRequest, errors);
				return aMapping.findForward("validateDepartmentFailure");
			}
			else if (responseEvent == null && userType.equalsIgnoreCase("ASA"))
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid","You are not authorized to manage the selected department. Select an appropriate department or contact JIMS. Department"));			
				saveErrors(aRequest, errors);
				return aMapping.findForward("validateDepartmentFailure");
			}
			else if (responseEvent == null && userType.equalsIgnoreCase("LA"))
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid","You are not authorized to manage the selected department. Select an appropriate department or contact JIMS. Department"));			
				saveErrors(aRequest, errors);
				return aMapping.findForward("validateDepartmentFailure");
			}
			else if (responseEvent == null)
			{
				this.saveErrors("error.user.department","Invalid Department Code.",userProfileForm,aRequest);
				return aMapping.findForward("validateDepartmentFailure");
			}
			userProfileForm.setDepartmentName(responseEvent.getDepartmentName());	
			userProfileForm.setDepartmentId(responseEvent.getDepartmentId());
			userProfileForm.setOrgCode(responseEvent.getOrgCode());
			userProfileForm.setGenericUserTypes(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GENERIC_USER_TYPE, true)); //U.S #79250
			if(!userProfileForm.getGenericUserTypeId().equals(""))
				userProfileForm.getGenericUserTypeCodeDescription();
			else
			{
				userProfileForm.setGenericUserType("Non-Generic");
				userProfileForm.setGenericUserTypeId("N");
			}			
		}	
		
		if(!userProfileForm.getUserStatusId().equals(""))
			userProfileForm.getAgencyStatusCodeDescription();	
		if(!userProfileForm.getInactivationTimeId().equals(""))
			userProfileForm.getWorkDaysCodeDescription();
		ValidateUserCreateUpdateRequirementsEvent validateEvent =
			(ValidateUserCreateUpdateRequirementsEvent) EventFactory.getInstance(
				UserControllerServiceNames.VALIDATEUSERCREATEUPDATEREQUIREMENTS);
		validateEvent.setLogonId(userProfileForm.getLogonId());
//		validateEvent.setPublicInd(userProfileForm.getPublicInd());
		//remove the '/' from the date string
		StringTokenizer stok=new StringTokenizer(userProfileForm.getDateOfBirth(),"/");
		StringBuffer dob=new StringBuffer();		
		while(stok.hasMoreTokens())
		{
			String str=stok.nextToken();
			dob.append(str);				
		}
		validateEvent.setDateOfBirth(DateUtil.convertMMDDYY(dob.toString()));
		validateEvent.setDepartmentId(userProfileForm.getDepartmentId());
		validateEvent.setFirstName(userProfileForm.getUserName().getFirstName());
		validateEvent.setLastName(userProfileForm.getUserName().getLastName());
		validateEvent.setMiddleName(userProfileForm.getUserName().getMiddleName());

		validateEvent.setGenericUserType(userProfileForm.getGenericUserTypeId());
		validateEvent.setPhoneNumber(userProfileForm.getPhoneNum().getPhoneNumber());
		validateEvent.setSsn(userProfileForm.getSsn().getSSN());
		validateEvent.setCustomCodeGenerationId(userProfileForm.getCustomCodeGeneration().toUpperCase());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(validateEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		DuplicateUserResponseEvent dResp = (DuplicateUserResponseEvent) MessageUtil.filterComposite(compositeResponse, DuplicateUserResponseEvent.class);
		if(dResp != null && dResp.getMessage() != null){
			this.sendToErrorPage(aRequest,dResp.getMessage(),dResp.getLogonId());
			return aMapping.findForward(UIConstants.VALIDATE_FAILURE);
		}
		
		if (userProfileForm.getAction().equals("create")|| userProfileForm.getAction().equals("createSummary") || userProfileForm.getAction().equals("createConfirm"))
		{
			userProfileForm.setAction("createSummary");
		}
		else
		{
			userProfileForm.setAction("updateSummary");
		}
		
		Collection users = MessageUtil.compositeToCollection(compositeResponse, UserResponseEvent.class);
		if (!users.isEmpty())
		{
				Collection coll = new ArrayList();
				Iterator iter = users.iterator();
				while(iter.hasNext())
				{
					UserResponseEvent resp = (UserResponseEvent) iter.next();
					UserProfileForm.UserProfile user = new UserProfileForm.UserProfile();
					if(!resp.getLogonId().equalsIgnoreCase(userProfileForm.getLogonId()))
					{	
						user.setLogonId(resp.getLogonId());			
						user.setDepartmentId(resp.getDepartmentId());
						user.setPhoneNum(new PhoneNumber(resp.getPhoneNum()));
						user.setSsn(new SocialSecurity(resp.getSsn()));
						user.setUserStatus(resp.getUserStatus());
						user.setDateOfBirth(resp.getDateOfBirth());
						user.setUserName(new Name(resp.getFirstName(),resp.getMiddleName(),resp.getLastName()));
						user.setTrainingInd(resp.getTrainingInd());
						coll.add(user);
					}
				}
				userProfileForm.setMatchingProfiles(coll);	
				if (coll != null && coll.size() > 0)
				{	
					return aMapping.findForward(UIConstants.LIST_SUCCESS);
				}	
		}
	
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}

	/**
	 * @param errorkey
	 * @param errorMsg
	 * @param userProfileForm
	 * @param request
	 */
	private void saveErrors(String errorkey, String errorMsg, UserProfileForm userProfileForm, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorkey,errorMsg));				
		userProfileForm.setDepartmentName("");
		userProfileForm.setOrgCode("");		
		saveErrors(request, errors);
		userProfileForm.setAction("create");
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String errorKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));
		saveErrors(aRequest, errors);
	}
	
	private void sendToErrorPage(HttpServletRequest aRequest, String errorKey, String customGenerationCodeId)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey,customGenerationCodeId));
		saveErrors(aRequest, errors);
	}
	
	public ActionForward back(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			UserProfileForm userProfileForm = (UserProfileForm) aForm;
			String action = userProfileForm.getAction();
			userProfileForm.clear();
			setAction(userProfileForm, action);		
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
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		String action = userProfileForm.getAction();
		userProfileForm.clear();
		setAction(userProfileForm, action);		
		return aMapping.findForward("reset");
	}
	
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		userProfileForm.clear();
		userProfileForm.clearUserProfiles();
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	public ActionForward validateDepartmentCode(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		UserProfileForm upf = (UserProfileForm) aForm;		
		DepartmentResponseEvent responseEvent = getValidDepartment(aRequest,upf);
		ISecurityManager securityManager = (ISecurityManager)ContextManager.getSession().get("ISecurityManager");		
		IUserInfo userInfo = securityManager.getIUserInfo();
		String userType = userInfo.getUserTypeId();
		if (responseEvent == null && userType.equalsIgnoreCase("SA"))
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid","You are not authorized to manage the selected department. Select an appropriate department or contact JIMS. Department"));			
			saveErrors(aRequest, errors);
			upf.setDepartmentName("");
			upf.setOrgCode("");		
			setAction(upf, upf.getAction());	
			return aMapping.findForward("validateDepartmentFailure");
		}
		else if (responseEvent == null && userType.equalsIgnoreCase("ASA"))
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid","You are not authorized to manage the selected department. Select an appropriate department or contact JIMS. Department"));			
			saveErrors(aRequest, errors);			
			upf.setDepartmentName("");
			upf.setOrgCode("");	
			setAction(upf, upf.getAction());
			return aMapping.findForward("validateDepartmentFailure");
		}
		else if (responseEvent == null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.user.department","Invalid Department Code."));			
			saveErrors(aRequest, errors);			
			upf.setDepartmentName("");
			upf.setOrgCode("");
			setAction(upf, upf.getAction());	
			return aMapping.findForward("validateDepartmentFailure");
		}
		upf.setAction("create");
		upf.setDepartmentName(responseEvent.getDepartmentName());		
		upf.setDepartmentId(responseEvent.getDepartmentId());
		upf.setOrgCode(responseEvent.getOrgCode());
		upf.setGenericUserTypes(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GENERIC_USER_TYPE, true));
		return aMapping.findForward(UIConstants.VALIDATE_DEPT_SUCCESS);
	}

	public ActionForward continueUpdate(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		UserProfileForm upf = (UserProfileForm) aForm;
		upf.setAction("updateSummary");
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}

	public ActionForward continueCreate(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		UserProfileForm upf = (UserProfileForm) aForm;
		upf.setAction("createSummary");
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}
	
	private DepartmentResponseEvent getValidDepartment(HttpServletRequest aRequest, UserProfileForm userProfileForm)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		CompositeResponse compositeResponse = null;
		Map dataMap = null;
		String userType = SecurityUIHelper.getUserTypeId();
		//an MA type of user can set any department
		if(userType.equalsIgnoreCase("MA"))
		{
			GetDepartmentEvent validateDeptEvent =
				(GetDepartmentEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENT);		
			validateDeptEvent.setDepartmentId(userProfileForm.getDepartmentId());
			validateDeptEvent.setGetAddressAndContact(false);

			dispatch.postEvent(validateDeptEvent);
			compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);

			DepartmentResponseEvent responseEvent =
				(DepartmentResponseEvent) MessageUtil.filterComposite(compositeResponse, DepartmentResponseEvent.class);
			return responseEvent; 	
		}
		//an SA type of user can set department to a department within their own agency
		else if(userType.equalsIgnoreCase("SA"))
		{
			GetAgenciesDepartmentsEvent agenciesDeptsEvent = (GetAgenciesDepartmentsEvent)EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCIESDEPARTMENTS);
			agenciesDeptsEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
			dispatch.postEvent(agenciesDeptsEvent);
			compositeResponse = (CompositeResponse) dispatch.getReply();
			dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
			Collection agencies = null;
			if (dataMap.containsKey("Agency"))
			{
				agencies = (Collection) dataMap.get("Agency");
				if (agencies != null && agencies.size() > 0)
				{
					Iterator iter = agencies.iterator();
					while(iter.hasNext())
					{
						AgencyResponseEvent agencyEvent = (AgencyResponseEvent)iter.next();
						Collection coll = agencyEvent.getDepartments();
						Iterator depts = coll.iterator();
						while(depts.hasNext())
						{
							DepartmentResponseEvent responseEvent =	(DepartmentResponseEvent)depts.next();
							if(responseEvent.getDepartmentId().equalsIgnoreCase(userProfileForm.getDepartmentId()))
								return responseEvent;
						}
					}
				}
				else
					if (agencies == null || agencies.size() == 0)
					{
						return null;
					}
			}
			else
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.agency.found"));
				saveErrors(aRequest, errors);
			}
		}
		//an ASA can set set department to one of the departments they are allowed to manage
		else if (userType.equalsIgnoreCase("ASA"))
		{
			GetDepartmentsForASAEvent asaDeptsEvent = (GetDepartmentsForASAEvent)EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTSFORASA);
			asaDeptsEvent.setLogonId(SecurityUIHelper.getJIMSLogonId());
			dispatch.postEvent(asaDeptsEvent);
			compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			Collection depts = MessageUtil.compositeToCollection(compositeResponse, DepartmentResponseEvent.class);
			Iterator iter = depts.iterator();
			while(iter.hasNext())
			{
				DepartmentResponseEvent responseEvent =	(DepartmentResponseEvent)iter.next();
				if(responseEvent.getDepartmentId().equalsIgnoreCase(userProfileForm.getDepartmentId()))
					return responseEvent;
			}
		}
		else if (userType.equalsIgnoreCase("LA"))
		{
			GetDepartmentConstraintsForUserAdministrationEvent cEvent = new GetDepartmentConstraintsForUserAdministrationEvent();
			cEvent.setLogonId(SecurityUIHelper.getJIMSLogonId());
			dispatch.postEvent(cEvent);
			compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			Collection depts = MessageUtil.compositeToCollection(compositeResponse, DepartmentConstraintsForUserAdministrationResponseEvent.class);
			Iterator iter = depts.iterator();
			while(iter.hasNext())
			{
				DepartmentConstraintsForUserAdministrationResponseEvent responseEvent =	(DepartmentConstraintsForUserAdministrationResponseEvent)iter.next();
				if(responseEvent.getDepartmentId().equalsIgnoreCase(userProfileForm.getDepartmentId())){
					DepartmentResponseEvent dResp = new DepartmentResponseEvent();
				    dResp.setDepartmentId(responseEvent.getDepartmentId());
				    dResp.setDepartmentName(responseEvent.getDepartmentName());
					return dResp;
				}
			}
		}
		//others are not supposed to be doing this at all
		else
		{
			return null;
		}
		return null;
	}
	
	private void setAction(UserProfileForm userProfileForm, String action)
	{
		
		if (action.equals("create") || action.equals("update"))
			userProfileForm.setAction(action);
		if(action.equals("createSummary"))
			userProfileForm.setAction("create");
		if(action.equals("updateSummary"))
					userProfileForm.setAction("update");
	}
	

}
