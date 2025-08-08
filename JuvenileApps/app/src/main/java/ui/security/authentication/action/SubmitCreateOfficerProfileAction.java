//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\authentication\\action\\SubmitCreateOfficerProfileAction.java

package ui.security.authentication.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.CreateJIMS2AccountEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.UpdateOfficerProfileEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.LogonControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.authentication.form.LoginForm;

public class SubmitCreateOfficerProfileAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 4399CE44013D
    */
   public SubmitCreateOfficerProfileAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 439711A70295
    */
   
   protected Map getKeyMethodMap()
	 {
	  Map keyMap = new HashMap();
	  keyMap.put("button.finish", "finish");
	  keyMap.put("button.backToLogin","backToLogin");
	  keyMap.put("button.back", "back");
	  keyMap.put("button.cancel", "cancel");	
	  keyMap.put("button.reset", "reset");		
	  return keyMap;
	 }
  
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		LoginForm loginForm = (LoginForm)aForm;
		loginForm.clearJIMSMessages();
		
		
		UpdateOfficerProfileEvent  updateOfficerProfile = (UpdateOfficerProfileEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.UPDATEOFFICERPROFILE);
		updateOfficerProfile.setDepartmentId(loginForm.getDepartmentId());
		updateOfficerProfile.setBadgeNum(loginForm.getBadgeNumber());
		updateOfficerProfile.setOtherIdNum(loginForm.getOtherIdNumber());
		updateOfficerProfile.setFirstName(loginForm.getUserName().getFirstName());
		updateOfficerProfile.setLastName(loginForm.getUserName().getLastName());
		updateOfficerProfile.setMiddleName(loginForm.getUserName().getMiddleName());
		// no longer in use. Migrated to SM. Refer US #87188.
		//updateOfficerProfile.setCellPhone(loginForm.getUserCellPhoneNumber().getPhoneNumber());
		updateOfficerProfile.setWorkPhone(loginForm.getUserWorkPhoneNumber().getPhoneNumber());
		updateOfficerProfile.setExtn(loginForm.getUserWorkPhoneNumber().getExt());
		// no longer in use. Migrated to SM. Refer US #87188.
		//updateOfficerProfile.setPager(loginForm.getUserPagerNumber().getPhoneNumber());
		updateOfficerProfile.setOfficerTypeId("L");
		updateOfficerProfile.setEmail(loginForm.getUserEmail());	
		updateOfficerProfile.setStatusId("A");
		//updateOfficerProfile.setLogonId(loginForm.getUserId());
		IDispatch dispatch=EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(updateOfficerProfile);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		//Map validateDataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(compositeResponse);
		
		OfficerProfileResponseEvent officerResponse =  (OfficerProfileResponseEvent)MessageUtil.filterComposite(compositeResponse,OfficerProfileResponseEvent.class);
		if(officerResponse==null)
		{
			
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.officer.create","Officer Create Error."));			
			saveErrors(aRequest, errors);	
			loginForm.clearOfficerDetails();
			loginForm.setAction("createOfficer");
			return aMapping.findForward(UIConstants.FAILURE);
			
		}
		
		loginForm.setOfficerId(officerResponse.getOfficerId());
		CreateJIMS2AccountEvent createJIMS2 = (CreateJIMS2AccountEvent)EventFactory.getInstance(
							LogonControllerServiceNames.CREATEJIMS2ACCOUNT);
	
		createJIMS2.setLogonId(loginForm.getLogonId());
		createJIMS2.setPassword(loginForm.getPassword());
		createJIMS2.setJIMS2AccountTypeId("L");
		createJIMS2.setJIMS2AccountTypeOID(officerResponse.getOfficerId());
		createJIMS2.setJIMS2LogonId(loginForm.getJims2UserId());
		createJIMS2.setJIMS2Password(loginForm.getJims2Password());
		//createJIMS2.setPasswordQuestionId(loginForm.getForgottenPasswdPhraseId());
		//createJIMS2.setAnswer(loginForm.getAnswer()); //86322
		createJIMS2.setFirstName(loginForm.getUserName().getFirstName());
		createJIMS2.setLastName(loginForm.getUserName().getLastName());
		createJIMS2.setMiddleName(loginForm.getUserName().getMiddleName());
		createJIMS2.setDepartmentId(loginForm.getDepartmentId());
		dispatch.postEvent(createJIMS2);
		compositeResponse = (CompositeResponse) dispatch.getReply();
		//validateDataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(compositeResponse);
	
		loginForm.setAction("confirm");
		return  aMapping.findForward(UIConstants.SUCCESS);
   }
   
   public ActionForward cancel(
	  ActionMapping aMapping,
	  ActionForm aForm,
	  HttpServletRequest aRequest,
	  HttpServletResponse aResponse)
	  {
	  	LoginForm loginForm = (LoginForm) aForm;
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		loginForm.clearOfficerDetails();
		loginForm.clearJIMSMessages();
		return forward;
	  }

	  public ActionForward back(
			  ActionMapping aMapping,
			  ActionForm aForm,
			  HttpServletRequest aRequest,
			  HttpServletResponse aResponse)
	  {
		  LoginForm loginForm = (LoginForm)aForm;
		  //loginForm.clearOfficerDetails();
		  ActionForward forward = aMapping.findForward(UIConstants.BACK);
		  return forward;
	  }
	public ActionForward backToLogin(
		 ActionMapping aMapping,
		 ActionForm aForm,
		 HttpServletRequest aRequest,
		 HttpServletResponse aResponse)
		 {
		 
		   ActionForward forward = aMapping.findForward("backtologin");
		   LoginForm loginForm = (LoginForm) aForm;		
		   loginForm.setLogonId(loginForm.getJims2UserId());		 
		   loginForm.clearJIMSMessages();		   
		   return forward;
		 }
}
