//file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\authentication\\action\\HandleValidateOfficerAction.java

package ui.security.authentication.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetDepartmentContactsEvent;
import messaging.authentication.ValidateJIMS2AccountEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.agency.reply.DepartmentContactResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfileByIdEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.LogonControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.security.authentication.form.LoginForm;

public class HandleValidateOfficerAction extends LookupDispatchAction
{
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.createOfficerProfile", "createOfficerProfile");
		keyMap.put("button.submit", "submit");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.reset", "reset");
		return keyMap;
	}
   
   /**
    * @roseuid 4399CE4201E9
    */
   public HandleValidateOfficerAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 439711A701D9
    */
   public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		LoginForm loginForm = (LoginForm)aForm;
		String deptId = loginForm.getDepartmentId();
		
		loginForm.clearJIMSMessages();	
		loginForm.clearOfficerDetails();	
		Collection codes = CodeHelper.getCodes(PDCodeTableConstants.PASSWORD_QUESTION);
		Iterator i = codes.iterator();
		Collection passwordQuestions = new ArrayList();
		while (i.hasNext())
		{
			CodeResponseEvent r = (CodeResponseEvent) i.next();
			passwordQuestions.add(r);			
		}
	//	loginForm.setForgottenPasswdPhraseList(passwordQuestions); //86322
		GetOfficerProfileByIdEvent officerEvent =
			(GetOfficerProfileByIdEvent) EventFactory.getInstance(
			OfficerProfileControllerServiceNames.GETOFFICERPROFILEBYID);	
			
		officerEvent.setDepartmentId(loginForm.getDepartmentId());
		if(loginForm.getBadgeNumber().equals(""))
		{
			officerEvent.setOtherIdNum(loginForm.getOtherIdNumber());			
			
		}
		else
		{
			officerEvent.setBadgeNum(loginForm.getBadgeNumber());			
			
		}
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(officerEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		OfficerProfileResponseEvent officerResponse = (OfficerProfileResponseEvent) MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);

		if(officerResponse!=null)
		{
			
			ValidateJIMS2AccountEvent validateJIMS2 = (ValidateJIMS2AccountEvent)EventFactory.getInstance(
			LogonControllerServiceNames.VALIDATEJIMS2ACCOUNT);
			validateJIMS2.setJIMS2AccountTypeId("L");			
			validateJIMS2.setJIMS2AccountTypeOID(officerResponse.getOfficerId());			
			dispatch.postEvent(validateJIMS2);
			compositeResponse = (CompositeResponse) dispatch.getReply();
			Map validateDataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(validateDataMap);
		
			LoginErrorResponseEvent loginError = (LoginErrorResponseEvent)MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
			
			if(loginError!=null)
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.existing.jims2UserId","Originating Agency Error."));			
				saveErrors(aRequest, errors);	
				loginForm.setAction("createOfficer");							
				return aMapping.findForward(UIConstants.FAILURE);
			}
			else
			{
				
				loginForm.setBadgeNumber(officerResponse.getBadgeNum());
				loginForm.setOtherIdNumber(officerResponse.getOtherIdNum());
				loginForm.setAgencyName(officerResponse.getAgencyName());
				loginForm.setDepartmentName(officerResponse.getDepartmentName());
				loginForm.setUserName(new Name(officerResponse.getFirstName(), officerResponse.getMiddleName(), officerResponse.getLastName()));
				loginForm.setUserWorkPhoneNumber(new PhoneNumber(officerResponse.getWorkPhone()));
				// no longer in use. Migrated to SM. Refer US #87188.
				//loginForm.setUserCellPhoneNumber(new PhoneNumber(officerResponse.getCellPhone()));
				//loginForm.setUserPagerNumber(new PhoneNumber(officerResponse.getCellPhone()));
				loginForm.setUserEmail(officerResponse.getEmail());	
				loginForm.setAccountTypeId("L");
				loginForm.setAccountTypeOID(officerResponse.getOfficerId());
				loginForm.setAction("createGenericOfficer");
				loginForm.clearJIMSMessages();			
				return aMapping.findForward("validSuccess");
				
			}
			
			
		}
		else
		{
			loginForm.clearJIMSMessages();
			if(loginForm.getCreateOfficerProfileInd().equalsIgnoreCase("y"))
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.badgeNumber.notFound","Badge/Other ID Number Error."));			
				saveErrors(aRequest, errors);	
				loginForm.setAction("createOfficerProfile");
			}
			else
			{

				GetDepartmentContactsEvent departmentContactsEvent = (GetDepartmentContactsEvent)EventFactory.getInstance(
				AgencyControllerServiceNames.GETDEPARTMENTCONTACTS);
				departmentContactsEvent.setDepartmentId(deptId);
				IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch1.postEvent(departmentContactsEvent);
				CompositeResponse compositeResponse1 = (CompositeResponse) dispatch1.getReply();				
				Map validateDataMap1 = MessageUtil.groupByTopic(compositeResponse1);
				MessageUtil.processReturnException(validateDataMap1);
				
				Collection deptContacts=MessageUtil.compositeToCollection(compositeResponse1, DepartmentContactResponseEvent.class);
				Iterator iter = deptContacts.iterator();
				while(iter.hasNext())
				{
					DepartmentContactResponseEvent deptResp = (DepartmentContactResponseEvent)iter.next();
					String primary = deptResp.getPrimaryContact();
					String phone = deptResp.getPhone();
					String ext = deptResp.getPhoneExt();
					String name =  deptResp.getLastName() + ", " + deptResp.getMiddleName()+ " " + deptResp.getFirstName();
					if(primary != null)
					{
						if(primary.equalsIgnoreCase("Y"))
						{
							ActionErrors errors = new ActionErrors();
							PhoneNumber phoneNumber = new PhoneNumber(phone);
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.create.officer", name, phoneNumber.formatPhoneNumberWithDashes(phone), ext));			
							saveErrors(aRequest, errors);	
							loginForm.setAction("createOfficer");							
						}
					}					
				}
				
				
			}
			return aMapping.findForward(UIConstants.FAILURE);
			
		}
		
   }
   
   public ActionForward createOfficerProfile(
	  ActionMapping aMapping,
	  ActionForm aForm,
	  HttpServletRequest aRequest,
	  HttpServletResponse aResponse)
	{
		LoginForm loginForm = (LoginForm)aForm;
		String deptId = loginForm.getDepartmentId();
		Collection codes = CodeHelper.getCodes(PDCodeTableConstants.PASSWORD_QUESTION);
		Iterator i = codes.iterator();
		Collection passwordQuestions = new ArrayList();
		while (i.hasNext())
		{
			CodeResponseEvent r = (CodeResponseEvent) i.next();
			passwordQuestions.add(r);
		}
	//  loginForm.setForgottenPasswdPhraseList(passwordQuestions);
	  loginForm.clearJIMSMessages();
	  ActionForward forward = aMapping.findForward("createOfficerSuccess");
	  return forward;
	}
   
   
   public ActionForward cancel(
	ActionMapping aMapping,
	ActionForm aForm,
	HttpServletRequest aRequest,
	HttpServletResponse aResponse)
	{
	  ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
	  LoginForm loginForm = (LoginForm)aForm;
	  loginForm.clearJIMSMessages();
	  return forward;
	}
	
	public ActionForward reset(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		LoginForm loginForm = (LoginForm)aForm;
		loginForm.clearOfficerLogin();
		loginForm.clearJIMSMessages();
		ActionForward forward = aMapping.findForward("reset");
		return forward;
	}
}
