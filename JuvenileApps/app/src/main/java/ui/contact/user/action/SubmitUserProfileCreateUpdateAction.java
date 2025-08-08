//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\SubmitUserProfileCreateUpdateAction.java

package ui.contact.user.action;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.CreateJIMS2AccountEvent;
//import messaging.authentication.GetJIMS2AccountByOfficerIdEvent;
import messaging.authentication.GetJIMS2AccountEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.user.UpdateUserProfileEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
//import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.LogonControllerServiceNames;
import naming.UIConstants;
import naming.UserControllerServiceNames;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.LoadManageOfficerCodeTables;
import ui.contact.officer.form.OfficerForm;
import ui.contact.user.form.UserProfileForm;
import ui.security.SecurityUIHelper;
import messaging.user.reply.InvalidUserResponseEvent;

public class SubmitUserProfileCreateUpdateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 43F4FC4102F9
	 */
	public SubmitUserProfileCreateUpdateAction()
	{

	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.back", "back");
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.mainPage","mainPage");
		buttonMap.put("button.assignRoles","assignRoles");
		buttonMap.put("button.manageUserGroup","manageUserGroup");
		buttonMap.put("button.createOfficerProfile","createOfficer");
		buttonMap.put("button.searchUserProfile","searchUserProfile");
		buttonMap.put("button.userProfileSearchResults","userProfileSearchResults");
		buttonMap.put("button.searchOfficerProfile","searchOfficerProfile");
		return buttonMap;
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
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		String action = userProfileForm.getAction();
		if(action.equals("updateSummary"))
			userProfileForm.setAction("update");
		else if (action.equals("createSummary"))
			userProfileForm.setAction("create");
		return aMapping.findForward(UIConstants.BACK);
	}
	
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 43F4EE4200C6
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UpdateUserProfileEvent updateEvent =
			(UpdateUserProfileEvent) EventFactory.getInstance(
				UserControllerServiceNames.UPDATEUSERPROFILE);
				
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		if (userProfileForm.getAction().equals("createSummary"))
		{
			// mark as a create, not an update
			updateEvent.setCreateInd(true);
		}
		if(userProfileForm.getAction().equalsIgnoreCase("updateSummary"))
			updateEvent.setUserID(userProfileForm.getLogonId());

//		userProfileForm.setPublicInd(userProfileForm.getPublicIndString());	
		
		userProfileForm.fillUserProfileData(updateEvent);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(updateEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		//check for JIMS2 account and if one exists, update it
		if(userProfileForm.getAction().equalsIgnoreCase("updateSummary") && userProfileForm.getGenericUserType().equalsIgnoreCase("Non-Generic"))
		{//U.S #79250 //TODO revisit later
		/*
			GetJIMS2AccountEvent accountEvent =
				(GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
			accountEvent.setJIMS2LogonId(userProfileForm.getJims2LogonId());
			dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(accountEvent);
			CompositeResponse compositeResponse1 = (CompositeResponse) dispatch.getReply();
			JIMS2AccountResponseEvent jims2User =
				(JIMS2AccountResponseEvent) MessageUtil.filterComposite(
					compositeResponse1,
					JIMS2AccountResponseEvent.class);
			if (jims2User != null)
			{
				CreateJIMS2AccountEvent createJIMS2 =
					(CreateJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.CREATEJIMS2ACCOUNT);
				createJIMS2.setCreate(false);
				createJIMS2.setFirstName(userProfileForm.getUserName().getFirstName());
				createJIMS2.setMiddleName(userProfileForm.getUserName().getMiddleName());
				createJIMS2.setLastName(userProfileForm.getUserName().getLastName());
				createJIMS2.setJIMS2LogonId(userProfileForm.getJims2LogonId());
				IDispatch disp =  EventManager.getSharedInstance(EventManager.REQUEST);
				disp.postEvent(createJIMS2);
				CompositeResponse cResp = (CompositeResponse) dispatch.getReply();
				MessageUtil.processReturnException(cResp);
			}*/
		}
		
		ReturnException lResponse =
			(ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
		if (lResponse != null)
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.user.invalid",lResponse.getMessage());
			errors.add("error", error);
			saveErrors( aRequest, errors );	
			return aMapping.findForward(UIConstants.FAILURE);
		}
		InvalidUserResponseEvent invalidEvent = (InvalidUserResponseEvent)MessageUtil.filterComposite(compositeResponse, InvalidUserResponseEvent.class);
		if(invalidEvent != null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid",invalidEvent.getMessage()+" Custom Code "));
			saveErrors(aRequest, errors);
			userProfileForm.setAction("create");
			return aMapping.findForward(UIConstants.FAILURE);
			
		}
/* 05/08/2007 per ER 39600 -- remove badge and other ID number */		
/*		DuplicateRecordErrorResponseEvent errorResponse=(DuplicateRecordErrorResponseEvent)MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
		if (errorResponse != null)
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage error=null;
			if(!userProfileForm.getBadgeNum().equals(""))
				error = new ActionMessage("error.badge.exist",errorResponse.getMessage());
			else if(!userProfileForm.getOtherIdNum().equals(""))
				error = new ActionMessage("error.otherId.exist",errorResponse.getMessage());
			errors.add("error", error);
			saveErrors( aRequest, errors );
			userProfileForm.setAction("create");
			return aMapping.findForward(UIConstants.FAILURE);
		}*/
		if (userProfileForm.getAction().equals("createSummary"))
		{
			UserResponseEvent responseEvent =
									(UserResponseEvent) MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);
			if(responseEvent!=null)
			{
				userProfileForm.setLogonId(responseEvent.getLogonId());	
				userProfileForm.setAction("createConfirm");
				return aMapping.findForward(UIConstants.SUCCESS);
			}
			else
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.user.create","user not found"));
				saveErrors(aRequest, errors);
				userProfileForm.setAction("create");
				return aMapping.findForward(UIConstants.FAILURE);
			}
		}
		else
		{
			userProfileForm.setAction("updateConfirm");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		
	
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
	
	public ActionForward mainPage(
				   ActionMapping aMapping,
				   ActionForm aForm,
				   HttpServletRequest aRequest,
				   HttpServletResponse aResponse)
	  {
		return aMapping.findForward(UIConstants.MAIN_PAGE);
	  }
	public ActionForward assignRoles(
				   ActionMapping aMapping,
				   ActionForm aForm,
				   HttpServletRequest aRequest,
			   HttpServletResponse aResponse)
	  {
		
		return aMapping.findForward(UIConstants.ASSIGN_ROLES_SUCCESS);
	  }
	
	public ActionForward manageUserGroup(
				   ActionMapping aMapping,
				   ActionForm aForm,
				   HttpServletRequest aRequest,
				   HttpServletResponse aResponse)
	  {
		return aMapping.findForward("manageUserGroupSuccess");
	  }
	
	public ActionForward createOfficer(
				   ActionMapping aMapping,
				   ActionForm aForm,
				   HttpServletRequest aRequest,
				   HttpServletResponse aResponse)
	  {                                   
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		OfficerForm officerForm = SecurityUIHelper.getOfficerForm(aRequest, true);
		officerForm.clear();
		officerForm.setUserId(userProfileForm.getLogonId());
		officerForm.setFirstName(userProfileForm.getUserName().getFirstName());
		officerForm.setMiddleName(userProfileForm.getUserName().getMiddleName());
		officerForm.setLastName(userProfileForm.getUserName().getLastName());
		officerForm.setDepartmentId(userProfileForm.getDepartmentId());
		officerForm.setDepartmentName(userProfileForm.getDepartmentName());
/* 05/08/2007 per ER 39600 -- remove badge and other ID number */		
//		officerForm.setBadgeNum(userProfileForm.getBadgeNum());
//		officerForm.setOtherIdNum(userProfileForm.getOtherIdNum());
		officerForm.setSsn1(userProfileForm.getSsn().getSsn1());
		officerForm.setSsn2(userProfileForm.getSsn().getSsn2());
		officerForm.setSsn3(userProfileForm.getSsn().getSsn3()); 
		officerForm.setWorkPhoneAreaCode(userProfileForm.getPhoneNum().getAreaCode());
		officerForm.setWorkPhonePrefix(userProfileForm.getPhoneNum().getPrefix());
		officerForm.setWorkPhoneMain(userProfileForm.getPhoneNum().get4Digit()); 
		officerForm.setExtn(userProfileForm.getPhoneNum().getExt());
		officerForm.setEmail(userProfileForm.getEmail());
		LoadManageOfficerCodeTables instance = LoadManageOfficerCodeTables.getInstance();
		instance.setOfficerForm(officerForm);
		officerForm.setStatusId("A");
		officerForm.setJuvUnits(new ArrayList());		
		return aMapping.findForward("createOfficerSuccess");
	  }
	  
	public ActionForward searchUserProfile(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileForm profileForm =(UserProfileForm)aForm;
		profileForm.clear();
		return aMapping.findForward("search");
	}

	public ActionForward searchOfficerProfile(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			UserProfileForm userProfileForm = (UserProfileForm) aForm;
			OfficerForm officerForm = SecurityUIHelper.getOfficerForm(aRequest, true);
			officerForm.clear();	
			if(officerForm.getJuvLocations() == null || officerForm.getJuvUnits() == null)
			{
				LoadManageOfficerCodeTables instance = LoadManageOfficerCodeTables.getInstance();
				instance.setOfficerForm(officerForm);
			}
			officerForm.setFirstName(userProfileForm.getUserName().getFirstName());
			officerForm.setFirstNamePrompt(userProfileForm.getUserName().getFirstName());
			officerForm.setMiddleName(userProfileForm.getUserName().getMiddleName());			
			officerForm.setLastName(userProfileForm.getUserName().getLastName());	
			officerForm.setLastNamePrompt(userProfileForm.getUserName().getLastName());	
			officerForm.setStatusId("");
			return aMapping.findForward("searchOfficer");
		}
public ActionForward userProfileSearchResults(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward("searchResults");
	}
}
