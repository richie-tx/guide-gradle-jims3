//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\HandleDepartmentSelectionAction.java

package ui.contact.user.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import messaging.user.GetUserProfileEvent;
import messaging.user.GetUserProfileHistoryEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.UIUtil;
import ui.contact.user.form.UserProfileForm;
import ui.contact.user.helper.UIUserFormHelper;
import ui.security.SecurityUIHelper;
//import messaging.contact.officer.reply.OfficerProfileResponseEvent;


public class HandleUserProfileSelectionAction extends LookupDispatchAction 
{
   
 	public Map getKeyMethodMap()
 	{
 		Map buttonMap = new HashMap();
 		buttonMap.put("button.update", "update");
		buttonMap.put("button.inactivate", "inactivate");
		buttonMap.put("button.transfer", "transfer");
		buttonMap.put("button.transferUserProfile", "transfer");
		buttonMap.put("button.view", "view");	
		buttonMap.put("button.history", "history");
		buttonMap.put("button.viewHistory", "history");
		buttonMap.put("button.assignRoles","assignRoles");
		buttonMap.put("button.manageUserGroup","manageUserGroup");
		buttonMap.put("button.cancel","cancel");
		buttonMap.put("button.back","back");
 		return buttonMap;
 	}
   public HandleUserProfileSelectionAction() 
   {
    
   }
 
   public ActionForward update(
   		ActionMapping aMapping,
   		ActionForm aForm,
   		HttpServletRequest aRequest,
   		HttpServletResponse aResponse)
   {
   		UserProfileForm profileForm = (UserProfileForm) aForm;
		//profileForm.setAction("");
		//check if we have come from the details page
		//in which case the form values will not be already set
		if(checkIfFromDetails(profileForm))
		{
				profileForm.setLogonId(getUserId(profileForm).getLogonId());				
		}
   		else if(!profileForm.getNumUsers().equals("single"))
   		{
			if(!profileForm.getSelectedValue().equals("0") && !profileForm.getSelectedValue().equals(""))
			{
   			
		   		Collection temp=profileForm.getUserProfiles();
				Iterator iter=temp.iterator();
				while(iter.hasNext())
				{
					UserProfileForm.UserProfile resp = (UserProfileForm.UserProfile) iter.next();
					if(profileForm.getSelectedValue().equals(resp.getLogonId()))
					{
						profileForm.setLogonId(resp.getLogonId());
					}
				}
			}
   		}
   		
   		//profileForm.clear();
   		profileForm.setAction("update");
		setUserProfileCodes(profileForm);	
		profileForm.setJims2Ind("");
		JIMS2AccountResponseEvent jims2AccountResponse=UIUserFormHelper.getJIMS2Account(profileForm.getLogonId());
		if(jims2AccountResponse == null)
		{
			profileForm.setJims2Ind("y");
		}
		else
			profileForm.setJims2LogonId(jims2AccountResponse.getJIMS2LogonId());
		//get the badge num/payroll num associated with this profile
/* 05/08/2007 per ER 39600 -- remove badge and other ID number */		
//		OfficerProfileResponseEvent officerResponse=UIUserFormHelper.getUserOfficerProfile(profileForm.getLogonId());
		UserResponseEvent userResponse = getUserProfile(profileForm);		
		if(userResponse == null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.user.profile","User Profile Not Found."));			
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		if(checkIfFromDetails(profileForm))
		{
				setCodeDescriptions(getUserId(profileForm),userResponse);
		}
/* 05/08/2007 per ER 39600 -- remove badge and other ID number */			
/*		if(officerResponse!=null)
		{
			profileForm.setBadgeNum(officerResponse.getBadgeNum());
			profileForm.setOtherIdNum(officerResponse.getOtherIdNum());
		} */
		UIUserFormHelper.setUserProfileFormValues(profileForm, userResponse);
    	return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
   }
   public ActionForward view(
		 ActionMapping aMapping,
		 ActionForm aForm,
		 HttpServletRequest aRequest,
		 HttpServletResponse aResponse)
	{
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		String source = userProfileForm.getAction();
		userProfileForm.setAction("view");
		String logonId = aRequest.getParameter("logonId");
		if( logonId != null ){
			userProfileForm.setLogonId(logonId);  
			String selValue = userProfileForm.getSelectedValue();
			if( !selValue.equalsIgnoreCase(logonId)){
				userProfileForm.setSelectedValue(logonId);  
			}		
		}
		UserProfileForm.UserProfileDetail profileDetail= new UserProfileForm.UserProfileDetail();
		UserResponseEvent userResponse = setViewOrDeleteDetails(userProfileForm, profileDetail,source);
		if(userResponse == null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.user.profile","User Profile Not Found."));			
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		if(userResponse.getGenericUserType().equalsIgnoreCase("N"))
			getJIMS2Account(profileDetail, userResponse.getLogonId());
		
		setCodeDescriptions(profileDetail,userResponse);
		userProfileForm.setAllowUserUpdate("Y");
/* this UserIsLA value needs to be set when this method is accessed from outside UserProfile */		
		userProfileForm.setUserIsLA("N");
		if (SecurityUIHelper.getUserTypeId().equalsIgnoreCase("LA")){
			userProfileForm.setUserIsLA("Y");
			if (!SecurityUIHelper.getUserDepartmentId().equalsIgnoreCase(userResponse.getDepartmentId())){
				userProfileForm.setAllowUserUpdate("N");
			}
		}
		UIUserFormHelper.setUserProfileDetailValues(profileDetail, userResponse);
		if (!SecurityUIHelper.getUserTypeId().equalsIgnoreCase("MA")){
			if (!SecurityUIHelper.getUserAgencyId().equalsIgnoreCase(userResponse.getAgencyId())){
				userProfileForm.setAllowUserUpdate("N");
			}	
		}
		
		Collection coll = new ArrayList();
		coll.add(profileDetail);
		userProfileForm.setUserProfileDetails(coll);
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);	  
	}
	
// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
   
 /*  public ActionForward history(
		  ActionMapping aMapping,
		  ActionForm aForm,
		  HttpServletRequest aRequest,
		  HttpServletResponse aResponse)
	 {
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		UserProfileForm.UserProfileDetail profileDetail = new UserProfileForm.UserProfileDetail();
		if(checkIfFromDetails(userProfileForm))
		{
			profileDetail = getUserId(userProfileForm);
			userProfileForm.setLogonId(profileDetail.getLogonId());
			userProfileForm.setUserName(profileDetail.getUserName());
		}
		else if(!userProfileForm.getNumUsers().equals("single"))
		{
			if(!userProfileForm.getSelectedValue().equals("0") && !userProfileForm.getSelectedValue().equals(""))
			{
			
				Collection temp=userProfileForm.getUserProfiles();
				Iterator iter=temp.iterator();
				while(iter.hasNext())
				{
					UserProfileForm.UserProfile resp = (UserProfileForm.UserProfile) iter.next();
					if(userProfileForm.getSelectedValue().equals(resp.getLogonId()))
					{
						userProfileForm.setLogonId(resp.getLogonId());
						userProfileForm.setUserName(resp.getUserName());
					}
				}
			}
		}
		GetUserProfileHistoryEvent getHistoryEvent =
			(GetUserProfileHistoryEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILEHISTORY);
		getHistoryEvent.setLogonId(userProfileForm.getLogonId());

		// submit the request to the PD
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(getHistoryEvent);

		// get reply from PD
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
		Collection userHistory = MessageUtil.compositeToCollection(compositeResponse, UserHistoryResponseEvent.class);
		if (userHistory.size() == 0)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.user.history","User Profile History Not Found."));			
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		Collections.sort((ArrayList)userHistory);
		userProfileForm.setUserProfileHistory(userHistory);
		// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
		return aMapping.findForward(UIConstants.VIEW_HISTORY_SUCCESS);
	
		  
	 }*/
	public ActionForward inactivate(
		   ActionMapping aMapping,
		   ActionForm aForm,
		   HttpServletRequest aRequest,
		   HttpServletResponse aResponse)
	  {
		UserProfileForm userProfileForm = (UserProfileForm) aForm;	
		String source=userProfileForm.getAction();
		userProfileForm.setAction("inactivate");
		UserProfileForm.UserProfileDetail profileDetail= new UserProfileForm.UserProfileDetail();
		UserResponseEvent userResponse = setViewOrDeleteDetails(userProfileForm, profileDetail,source);
		if(userResponse == null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.user.profile","User Profile Not Found."));			
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		getJIMS2Account(profileDetail, userResponse.getLogonId());
		
		setCodeDescriptions(profileDetail,userResponse);
		UIUserFormHelper.setUserProfileDetailValues(profileDetail, userResponse);
		Collection coll = new ArrayList();
		coll.add(profileDetail);
		userProfileForm.setUserProfileDetails(coll);
		 return aMapping.findForward(UIConstants.DELETE_SUCCESS);
	  }
	public ActionForward transfer(
		   ActionMapping aMapping,
		   ActionForm aForm,
		   HttpServletRequest aRequest,
		   HttpServletResponse aResponse)
	  {
		UserProfileForm userProfileForm = (UserProfileForm) aForm;	
		if(checkIfFromDetails(userProfileForm))
							userProfileForm.setLogonId(getUserId(userProfileForm).getLogonId());
		else if(!userProfileForm.getNumUsers().equals("single"))
		{
			if(!userProfileForm.getSelectedValue().equals("0") && !userProfileForm.getSelectedValue().equals(""))
			{
				Collection temp=userProfileForm.getUserProfiles();
				Iterator iter=temp.iterator();
				while(iter.hasNext())
				{
					UserProfileForm.UserProfile resp = (UserProfileForm.UserProfile) iter.next();
					if(userProfileForm.getSelectedValue().equals(resp.getLogonId()))
					{
						userProfileForm.setLogonId(resp.getLogonId());
					}
				}
			}
		}
		UserResponseEvent userResponse = getUserProfile(userProfileForm);
		if(userResponse == null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("User Profile Not Found."));			
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		userProfileForm.clearDepartments();
		userProfileForm.setUserName(new Name(userResponse.getFirstName(),userResponse.getMiddleName(),userResponse.getLastName()));
		userProfileForm.setLogonId(userResponse.getLogonId());
		userProfileForm.setAgencyId(userResponse.getAgencyId());
		userProfileForm.setAgencyName(userResponse.getAgencyName());
		userProfileForm.setDepartmentId(userResponse.getDepartmentId());
		userProfileForm.setDepartmentName(userResponse.getDepartmentName());
		userProfileForm.setNewDepartmentId("");
		userProfileForm.setNewDepartmentName("");
		userProfileForm.setTransferDate("");
		userProfileForm.setTransferTime("");
		userProfileForm.setWorkDays(UIUtil.sortCodesByStringCodeId(CodeHelper.getCodes(PDCodeTableConstants.WORK_DAY)));
		
	   return aMapping.findForward(UIConstants.TRANSFER_SUCCESS);		
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
  
	public ActionForward cancel(
			   ActionMapping aMapping,
			   ActionForm aForm,
			   HttpServletRequest aRequest,
			   HttpServletResponse aResponse)
	  {
		return aMapping.findForward(UIConstants.CANCEL);
	  }
	public ActionForward back(
			   ActionMapping aMapping,
			   ActionForm aForm,
			   HttpServletRequest aRequest,
			   HttpServletResponse aResponse)
	  {
		UserProfileForm userProfileForm = (UserProfileForm) aForm;	
		if(checkIfFromDetails(userProfileForm))
			userProfileForm.setLogonId(getUserId(userProfileForm).getLogonId());
		userProfileForm.setAction("");
		return aMapping.findForward(UIConstants.BACK);
	  }
	  private UserResponseEvent getUserProfile(UserProfileForm userProfileForm)
	  {
	  	GetUserProfileEvent userProfile = (GetUserProfileEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILE);
	  	userProfile.setLogonId(userProfileForm.getLogonId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(userProfile);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		UserResponseEvent userResponse = (UserResponseEvent) MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);
		return userResponse;		
	  }
	  
	private UserResponseEvent getUserProfileDetail(String userId)
	{
	  GetUserProfileEvent userProfile = (GetUserProfileEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILE);
	  userProfile.setLogonId(userId);
	  IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	  dispatch.postEvent(userProfile);
	  CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	  UserResponseEvent userResponse = (UserResponseEvent) MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);
	  return userResponse;		
	}

	private boolean checkIfFromDetails(UserProfileForm upf)
	{
		if(upf.getAction().equals("details"))
			return true;
		else
			return false;
	}
	private UserProfileForm.UserProfileDetail getUserId(UserProfileForm upf)
	{
		Collection coll = upf.getUserProfileDetails();
		Iterator iter = coll.iterator();
		String userId="";
		while(iter.hasNext())
		{
			UserProfileForm.UserProfileDetail profileDetail = (UserProfileForm.UserProfileDetail)iter.next();
			return profileDetail;
		}
		
		return null;
	}
	 private void setUserProfileCodes(UserProfileForm profileForm)
	 {
		profileForm.setUserTypes(CodeHelper.getCodes(PDCodeTableConstants.USER_TYPE));
		profileForm.setUserStatuses(CodeHelper.getCodes(PDCodeTableConstants.AGENCY_STATUS));
		profileForm.setGenericUserTypes(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GENERIC_USER_TYPE, true)); //U.S #79250
		profileForm.setWorkDays(UIUtil.sortCodesByStringCodeId(CodeHelper.getCodes(PDCodeTableConstants.WORK_DAY)));
	 	
	 }
	 private void setCodeDescriptions(UserProfileForm.UserProfileDetail profileDetail,UserResponseEvent userResponse)
	 {
		Iterator iter;
		if(userResponse.getUserTypeId()==null || userResponse.getUserTypeId().equals("") ||  userResponse.getUserTypeId().equals("BA"))
			profileDetail.setUserType("BASIC");
		else
		{
			iter = CodeHelper.getCodes(PDCodeTableConstants.USER_TYPE).iterator();
			while(iter.hasNext())
			{
				CodeResponseEvent codeResp = (CodeResponseEvent) iter.next();
				if(codeResp.getCode().equals(userResponse.getUserTypeId()))
				{
					profileDetail.setUserType(codeResp.getDescription());
					
				}
			}
		}
		if(userResponse.getGenericUserType()==null || userResponse.getGenericUserType().equals(""))
			profileDetail.setGenericUserType("Non-Generic");
		else
		{
			iter = CodeHelper.getCodes(PDCodeTableConstants.GENERIC_USER_TYPE).iterator();
			while(iter.hasNext())
			{
				CodeResponseEvent codeResp = (CodeResponseEvent) iter.next();
				if(codeResp.getCode().equals(userResponse.getGenericUserType()))
				{
					profileDetail.setGenericUserType(codeResp.getDescription());
					
				}
			}
		}
		iter = CodeHelper.getCodes(PDCodeTableConstants.AGENCY_STATUS).iterator();
		while(iter.hasNext())
		{
			CodeResponseEvent codeResp = (CodeResponseEvent) iter.next();
			if(codeResp.getCode().equals(userResponse.getUserStatus()))
			{
				profileDetail.setUserStatus(codeResp.getDescription());
				
			}
		}
//		if(userResponse.getPublicInd().equals("Y"))
//			profileDetail.setPublicIndString("YES");
//		else
//			profileDetail.setPublicIndString("NO");	
		return;
	 }
	 private UserResponseEvent setViewOrDeleteDetails(UserProfileForm userProfileForm, UserProfileForm.UserProfileDetail profileDetail,String source )
	 {
	 	if(!source.equals("details"))
	 	{
			if(!userProfileForm.getNumUsers().equals("single") && !userProfileForm.getNumUsers().equals("zero"))
			{
				//if no value is selected e.g. when back button is pressed
				if(userProfileForm.getSelectedValue().equals("0") || userProfileForm.getSelectedValue().equals(""))
				{
					profileDetail.setLogonId(userProfileForm.getLogonId());
					profileDetail.setUserStatus(userProfileForm.getUserStatus());
				}
				else
				{
					Collection temp=userProfileForm.getUserProfiles();
					Iterator iter=temp.iterator();
					while(iter.hasNext())
					{
						UserProfileForm.UserProfile resp = (UserProfileForm.UserProfile) iter.next();
						if(userProfileForm.getSelectedValue().equals(resp.getLogonId()))
						{
							profileDetail.setLogonId(resp.getLogonId());
							profileDetail.setUserStatus(resp.getUserStatus());
							profileDetail.setGenericUserType(resp.getGenericUserType());
						}
					}
					if(profileDetail.getLogonId().equals(""))
					{
						profileDetail.setLogonId(userProfileForm.getSelectedValue());
					}
				}
			}
			else
			{
				if(userProfileForm.getSelectedValue().equals("0") || userProfileForm.getSelectedValue().equals(""))
					profileDetail.setLogonId(userProfileForm.getLogonId());
				else
					profileDetail.setLogonId(userProfileForm.getSelectedValue());
			}
	 	}
	 	else
	 	{
			Collection temp = userProfileForm.getUserProfileDetails();
			Iterator iter = temp.iterator();
			while(iter.hasNext())
			{
				UserProfileForm.UserProfileDetail det = (UserProfileForm.UserProfileDetail) iter.next();
				profileDetail.setLogonId(det.getLogonId());
				profileDetail.setUserStatus(det.getUserStatus());
			}
	 	}
	 	
		UserResponseEvent userResponse = getUserProfileDetail(profileDetail.getLogonId());
/* 05/08/2007 per ER 39600 -- remove badge and other ID number */
/*		if(userResponse.getGenericUserType().equalsIgnoreCase("N"))
	 	{
			OfficerProfileResponseEvent officerResponse=UIUserFormHelper.getUserOfficerProfile(profileDetail.getLogonId());
			if(officerResponse!=null)
			{
				profileDetail.setBadgeNum(officerResponse.getBadgeNum());
				profileDetail.setOtherIdNum(officerResponse.getOtherIdNum());
			}
	 	} */
		return userResponse;
			
	 }
	 
	 private void getJIMS2Account(UserProfileForm.UserProfileDetail profileDetail, String userId)
	 {
		JIMS2AccountResponseEvent jims2AccountResponse=UIUserFormHelper.getJIMS2Account(userId);
		if(jims2AccountResponse != null)
		{
			profileDetail.setAnswer(jims2AccountResponse.getPasswordAnswer());
			profileDetail.setJims2UserId(jims2AccountResponse.getJIMS2LogonId());
			profileDetail.setJims2UserName(new Name(jims2AccountResponse.getLastName(),jims2AccountResponse.getMiddleName(),jims2AccountResponse.getFirstName()));
			profileDetail.setJims2DepartmentName(jims2AccountResponse.getDepartmentName());
			profileDetail.setJims2Password(jims2AccountResponse.getJIMS2Password());
			String passwdPhrase=jims2AccountResponse.getForgottenPasswdPhrase();
			String description="";
			if(passwdPhrase!=null)
				description=UIUserFormHelper.getPasswordQuestionDescription(passwdPhrase);
			profileDetail.setForgottenPasswdPhrase(description);
		}
	 	
	 }
}