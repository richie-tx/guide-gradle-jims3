//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\DisplayUserProfileSearchResultsAction.java

package ui.contact.user.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.domintf.contact.user.IUserProfile;
import messaging.info.reply.CountInfoMessage;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import messaging.user.GetUserProfilesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.AbstractLookupResultsTemplateAction;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import ui.contact.user.form.UserProfileForm;
import ui.contact.user.form.UserProfileSearchForm;
import ui.contact.user.helper.UIUserFormHelper;
import ui.security.SecurityUIHelper;

public class DisplayUserProfileSearchResultsAction extends AbstractLookupResultsTemplateAction
{
	/**
	 * @roseuid 43F4FC400384
	 */
	public DisplayUserProfileSearchResultsAction()
	{
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.refresh", "refresh");
		buttonMap.put("button.findUserProfiles", "findUserProfiles");	
		return buttonMap;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 43F4EE3902CC
	 */
	public ActionForward findUserProfiles(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileSearchForm searchForm = (UserProfileSearchForm) aForm;
		UserProfileForm userProfileForm = getUserProfileForm(aRequest);
		userProfileForm.clear();
		GetUserProfilesEvent userProfiles =
			(GetUserProfilesEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILES);
		userProfiles.setLogonId(searchForm.getLogonId());
		userProfiles.setJims2LogonId(searchForm.getJims2LogonId());
		userProfiles.setLastName(searchForm.getUserName().getLastName());
		userProfiles.setFirstName(searchForm.getUserName().getFirstName());
		userProfiles.setMiddleName(searchForm.getUserName().getMiddleName());
		userProfiles.setDateOfBirthFrom(DateUtil.stringToDate(searchForm.getFromDateOfBirth(), "MM/dd/yyyy"));
		userProfiles.setDateOfBirthTo(DateUtil.stringToDate(searchForm.getToDateOfBirth(), "MM/dd/yyyy"));
		StringBuffer buff = new StringBuffer();
		buff.append(searchForm.getSSN().getSSN1());
		buff.append(searchForm.getSSN().getSSN2());
		buff.append(searchForm.getSSN().getSSN3());
		userProfiles.setSsn(buff.toString());
		userProfiles.setUserStatus(searchForm.getUserStatusId());
		userProfiles.setAgencyId(searchForm.getAgencyId());
		userProfiles.setAgencyName(searchForm.getAgencyName());
		userProfiles.setDepartmentId(searchForm.getDepartmentId());
		userProfiles.setDepartmentName(searchForm.getDepartmentName());
		userProfiles.setGenericUserType(searchForm.getGenericUserTypeId());
//		userProfiles.setPublicInd(searchForm.getPublicInd());
		userProfiles.setUserType(searchForm.getUserTypeId());
		userProfiles.setRequestorLastName(searchForm.getRequestorName().getLastName());
		userProfiles.setRequestorFirstName(searchForm.getRequestorName().getFirstName());
		userProfiles.setActivationDateFrom(DateUtil.stringToDate(searchForm.getFromActivationDate(), "MM/dd/yyyy"));
		userProfiles.setInactivationDateFrom(
		DateUtil.stringToDate(searchForm.getFromInactivationDate(), "MM/dd/yyyy"));
		userProfiles.setChangeDateFrom(DateUtil.stringToDate(searchForm.getFromChangeDate(), "MM/dd/yyyy"));
		userProfiles.setActivationDateTo(DateUtil.stringToDate(searchForm.getToActivationDate(), "MM/dd/yyyy"));
		userProfiles.setInactivationDateTo(DateUtil.stringToDate(searchForm.getToInactivationDate(), "MM/dd/yyyy"));
		userProfiles.setChangeDateTo(DateUtil.stringToDate(searchForm.getToChangeDate(), "MM/dd/yyyy"));
		userProfiles.setOperatorId(searchForm.getOPID());
		// submit the request to the PD
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(userProfiles);
		
		// get reply from PD
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		// check for max. limit exceeded 
       	CountInfoMessage infoEvent = new CountInfoMessage();
       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
       	if (iMessage != null ){
       		ActionErrors errors = new ActionErrors();
       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
       		saveErrors(aRequest, errors);
       		return aMapping.findForward(UIConstants.FAILURE);
       	} 
       	else
       	{
       		return handleResponse(aMapping, aForm, aRequest, aResponse, UserResponseEvent.class, compositeResponse);
       	}
		/*
		  -- OLD CODE; May not be necessary
		Map profileMap = MessageUtil.groupByTopic(compositeResponse);
		*/
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
		UserProfileSearchForm searchForm = (UserProfileSearchForm) aForm;
 		String deptId = searchForm.getDepartmentId();
 		String isMA = searchForm.getUserIsMA();
 		String agencyId = "";
 		String agencyName = "";
		if (isMA.equalsIgnoreCase("N")){
	 		agencyId = searchForm.getAgencyId();
	 		agencyName = searchForm.getAgencyName();			
		}
 		Collection coll = new ArrayList();
		searchForm.clear();
		if (isMA.equalsIgnoreCase("N")){
	 		searchForm.setDepartmentId(deptId);
	 		searchForm.setUserIsMA(isMA);	
	 		searchForm.setAgencyId(agencyId);
	 		searchForm.setAgencyName(agencyName);
		}
		UserProfileForm userProfileForm = getUserProfileForm(aRequest);
		userProfileForm.setUserProfiles(coll);
		userProfileForm.setNumUsers("0");
		return aMapping.findForward("reset");
	}
	
	
	/* (non-Javadoc)
	 * @see ui.action.IErrorResultsActionHandler#processBusinessExceptions(javax.servlet.http.HttpServletRequest, mojo.km.messaging.Composite.CompositeResponse, org.apache.struts.action.ActionErrors)
	 */
	public void processBusinessExceptions(HttpServletRequest aRequest, CompositeResponse response, ActionErrors errors)
	{
		// TODO: Anything to do here ??

	}

	/* (non-Javadoc)
	 * @see ui.action.IResultsActionTemplate#handleMultipleResults(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, mojo.km.messaging.Composite.CompositeResponse, java.util.Collection)
	 */
	public ActionForward handleMultipleResults(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		CompositeResponse event,
		Collection data)
	{
		UserProfileSearchForm searchForm = (UserProfileSearchForm) aForm;
		UserProfileForm userProfileForm = getUserProfileForm(aRequest);
		UserProfileForm.UserProfile user = new UserProfileForm.UserProfile();
		data = SecurityUIHelper.sortUserProfileNames(data);
		Collection coll = new ArrayList();
		Iterator iter = data.iterator();
		while(iter.hasNext())
		{
			UserResponseEvent resp = (UserResponseEvent) iter.next();
			user.setLogonId(resp.getLogonId());			
			user.setDepartmentId(resp.getDepartmentId());
			user.setPhoneNum(new PhoneNumber(resp.getPhoneNum()));
			user.getPhoneNum().setExt(resp.getPhoneExt());
			user.setSsn(new SocialSecurity(resp.getSsn()));
			user.setGenericUserType(resp.getGenericUserType());
			if(resp.getUserStatus().equals("PC"))
				user.setUserStatus("PENDING CHANGE");
			else if(resp.getUserStatus().equals("PA"))
				user.setUserStatus("PENDING ADD");
			else if(resp.getUserStatus().equals("PD"))
				user.setUserStatus("PENDING DELETE");
			else
				user.setUserStatus(CodeHelper.getCodeDescription(searchForm.getUserStatuses(),resp.getUserStatus()));
			
			user.setDateOfBirth(resp.getDateOfBirth());
			user.setUserName(new Name(resp.getFirstName(),resp.getMiddleName(),resp.getLastName()));
			user.setTrainingInd(resp.getTrainingInd());
			coll.add(user);	
			user = new UserProfileForm.UserProfile();	
		}
		userProfileForm.setUserProfiles(coll);		
		userProfileForm.setUserProfilesSize(coll.size());
		userProfileForm.setNumUsers(""+UIConstants.MULTIPLE_RESULTS);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see ui.action.IResultsActionTemplate#handleSingleResult(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, mojo.km.messaging.Composite.CompositeResponse, mojo.km.messaging.IEvent)
	 */
	public ActionForward handleSingleResult(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		CompositeResponse event,
		IEvent data)
	{
		UserProfileForm userProfileForm = getUserProfileForm(aRequest);
		UserProfileForm.UserProfile user = new UserProfileForm.UserProfile();
		IUserProfile userResponse = (UserResponseEvent)data;
		
		UserProfileForm.UserProfileDetail profileDetail = new UserProfileForm.UserProfileDetail();
		setCodeDescriptions(profileDetail,(UserResponseEvent)data);
		OfficerProfileResponseEvent officerResponse = null;
/* 05/08/2007 per ER 39600 -- remove badge and other ID number */		
/*		if(userResponse.getGenericUserType().equalsIgnoreCase("N"))
		{
			officerResponse = UIUserFormHelper.getUserOfficerProfile(userResponse.getLogonId());		
			if(officerResponse!=null)
			{
				profileDetail.setBadgeNum(officerResponse.getBadgeNum());
				profileDetail.setOtherIdNum(officerResponse.getOtherIdNum());
			}
		} */
		//get the JIMS2 account only for nonGenerics
		if(userResponse.getGenericUserType().equalsIgnoreCase("N"))
		{
			JIMS2AccountResponseEvent jims2AccountResponse=UIUserFormHelper.getJIMS2Account(userResponse.getLogonId());
			if(jims2AccountResponse != null)
			{
				profileDetail.setAnswer(jims2AccountResponse.getPasswordAnswer());
				profileDetail.setJims2UserId(jims2AccountResponse.getJIMS2LogonId());
				profileDetail.setJims2UserName(new Name(jims2AccountResponse.getFirstName(),jims2AccountResponse.getMiddleName(),jims2AccountResponse.getLastName()));
				profileDetail.setJims2DepartmentName(jims2AccountResponse.getDepartmentName());
				profileDetail.setJims2Password(jims2AccountResponse.getJIMS2Password());
				String passwdPhrase=jims2AccountResponse.getForgottenPasswdPhrase();
				String description="";
				if(passwdPhrase!=null)
					description=UIUserFormHelper.getPasswordQuestionDescription(passwdPhrase);
				profileDetail.setForgottenPasswdPhrase(description);
			}
		}
		UIUserFormHelper.setUserProfileDetailValues(profileDetail, (UserResponseEvent)data);		
/* this UserIsLA value needs to be set when this method is accessed from outside UserProfile */		
		userProfileForm.setUserIsLA("N");
		if (SecurityUIHelper.getUserTypeId().equalsIgnoreCase("LA")){
			userProfileForm.setUserIsLA("Y");
		}
		userProfileForm.setAllowUserUpdate("Y");
		if (!SecurityUIHelper.getUserTypeId().equalsIgnoreCase("MA")){
			if (!SecurityUIHelper.getUserAgencyId().equalsIgnoreCase(userResponse.getAgencyId())){
				userProfileForm.setAllowUserUpdate("N");
			}	
		}
		userProfileForm.setNumUsers("single");
		//userProfileForm.setAction("details");
		Collection coll = new ArrayList();
		coll.add(profileDetail);
		userProfileForm.setUserProfileDetails(coll);
		UserProfileSearchForm searchForm = (UserProfileSearchForm) aForm;
		return aMapping.findForward("searchUserProfileSuccess");
	}

	/* (non-Javadoc)
	 * @see ui.action.IResultsActionTemplate#handleZeroResults(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, mojo.km.messaging.Composite.CompositeResponse)
	 */
	public ActionForward handleZeroResults(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse,
		CompositeResponse event)
	{
		UserProfileForm userProfileForm = getUserProfileForm(aRequest);
		//userProfileForm.setUserProfiles(new ArrayList());
		userProfileForm.setNumUsers("zero");
		userProfileForm.setUserProfiles(new ArrayList());
		ActionErrors errors = new ActionErrors();  
 		boolean isMA = SecurityUIHelper.isUserMA();
		if (isMA)
		{	
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.userProfiles.found"));
		} else {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.userProfileinDept.found"));			
		}
		saveErrors(aRequest, errors);
		UserProfileSearchForm searchForm = (UserProfileSearchForm) aForm;
//		searchForm.clear();		
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * put the user profile form in session
	 * @return HttpServletRequest
	 */
	private UserProfileForm getUserProfileForm(HttpServletRequest aRequest)
	{

		UserProfileForm userProfileForm = null;
		HttpSession session = aRequest.getSession();
		Object obj = session.getAttribute(UIConstants.USERPROFILE_FORM);
		if (obj != null || obj instanceof UserProfileForm)
		{
			userProfileForm = (UserProfileForm) obj;
		}
		else
		{
			userProfileForm = new UserProfileForm();
			session.setAttribute(UIConstants.USERPROFILE_FORM, userProfileForm);
		}
		return userProfileForm;
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
			
			if(userResponse.getUserStatus().equals("PC"))
				profileDetail.setUserStatus("PENDING CHANGE");
			if(userResponse.getUserStatus().equals("PA"))
				profileDetail.setUserStatus("PENDING ADD");
			if(userResponse.getUserStatus().equals("PD"))
				profileDetail.setUserStatus("PENDING DELETE");
			else
			{
				iter = CodeHelper.getCodes(PDCodeTableConstants.AGENCY_STATUS).iterator();
				while(iter.hasNext())
				{
					CodeResponseEvent codeResp = (CodeResponseEvent) iter.next();
					if(codeResp.getCode().equals(userResponse.getUserStatus()))
					{
						profileDetail.setUserStatus(codeResp.getDescription());
						
					}
				}
			}
//			if(userResponse.getPublicInd().equals("Y"))
//				profileDetail.setPublicIndString("YES");
//			else
//				profileDetail.setPublicIndString("NO");	
		 }

}