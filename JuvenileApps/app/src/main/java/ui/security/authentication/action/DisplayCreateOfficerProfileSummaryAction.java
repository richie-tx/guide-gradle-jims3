//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\authentication\\action\\DisplayCreateOfficerProfileSummaryAction.java

package ui.security.authentication.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import ui.security.authentication.form.LoginForm;
import naming.OfficerProfileControllerServiceNames;
import naming.LogonControllerServiceNames;
import naming.UIConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import messaging.authentication.ValidateJIMS2AccountEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import messaging.officer.ValidateOfficerProfileEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

public class DisplayCreateOfficerProfileSummaryAction extends LookupDispatchAction
{
	
   
   /**
    * @roseuid 4399BE8800FB
    */
   
   protected Map getKeyMethodMap()
   {
   	Map keyMap = new HashMap();
   	keyMap.put("button.next", "next");
	keyMap.put("button.back", "back");	
	keyMap.put("button.reset", "reset");		
	return keyMap;
   }
   
   public DisplayCreateOfficerProfileSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 439711A70272
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
			LoginForm loginForm = (LoginForm)aForm;
			loginForm.clearJIMSMessages();
			String password = loginForm.getJims2Password();		
			StringBuffer displayPassword= new StringBuffer(password.length());	
			for(int i=0;i<password.length();i++)
			{			
				displayPassword.append("x");			
			}	
			loginForm.setDisplayPassword(displayPassword.toString());
			//86322
		//	Collection passwordPhrases = loginForm.getForgottenPasswdPhraseList();
			/*Iterator iter = passwordPhrases.iterator();
			
			while(iter.hasNext())
			{
				CodeResponseEvent r = (CodeResponseEvent) iter.next();
				if(r.getCode().equals(loginForm.getForgottenPasswdPhraseId()))
					loginForm.setForgottenPasswdPhrase(r.getDescription());
			}*/
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			ValidateOfficerProfileEvent officerProfile = (ValidateOfficerProfileEvent)EventFactory.getInstance(OfficerProfileControllerServiceNames.VALIDATEOFFICERPROFILE);
			officerProfile.setBadgeNum(loginForm.getBadgeNumber());
			officerProfile.setOtherIdNum(loginForm.getOtherIdNumber());
			officerProfile.setDepartmentId(loginForm.getDepartmentId());
			
			dispatch.postEvent(officerProfile);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map validateDataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(validateDataMap);
			
			DuplicateRecordErrorResponseEvent errorResponse=(DuplicateRecordErrorResponseEvent)MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
			if(errorResponse==null)
			{
					ValidateJIMS2AccountEvent validateJIMS2 = (ValidateJIMS2AccountEvent)EventFactory.getInstance(
					LogonControllerServiceNames.VALIDATEJIMS2ACCOUNT);
				validateJIMS2.setUserID(loginForm.getLogonId());
				validateJIMS2.setJIMS2LogonId(loginForm.getJims2UserId());
				validateJIMS2.setJIMS2AccountTypeId("");
				validateJIMS2.setJIMS2AccountTypeOID("");
				dispatch.postEvent(validateJIMS2);
				compositeResponse = (CompositeResponse) dispatch.getReply();
				validateDataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(validateDataMap);
			
				LoginErrorResponseEvent loginError = (LoginErrorResponseEvent)MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
				if(loginError!=null)
				{
					loginForm.setErrorMessage(loginError.message);
					loginForm.setAction("createOfficer");
					return aMapping.findForward(UIConstants.FAILURE);
				}
				else
				{
					loginForm.clearJIMSMessages();
					loginForm.setAction("summary");
					return aMapping.findForward(UIConstants.SUCCESS);
				}
				
			
			}
			else
			{
				loginForm.setErrorMessage(errorResponse.getMessage());
				loginForm.setAction("createOfficer");
				return aMapping.findForward(UIConstants.FAILURE);
			}
   }
   
   public ActionForward back(
   ActionMapping aMapping,
   ActionForm aForm,
   HttpServletRequest aRequest,
   HttpServletResponse aResponse)
   {
	 ActionForward forward = aMapping.findForward(UIConstants.BACK);
	 LoginForm loginForm = (LoginForm)aForm;
	 loginForm.clearOfficerDetails();
	 loginForm.setJims2UserId("");
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
	   loginForm.clearOfficerDetails();
	   loginForm.setJims2UserId("");	   
	   loginForm.clearJIMSMessages();	 
	   ActionForward forward = aMapping.findForward(UIConstants.RESET);
	   return forward;
   }
			
	
}
