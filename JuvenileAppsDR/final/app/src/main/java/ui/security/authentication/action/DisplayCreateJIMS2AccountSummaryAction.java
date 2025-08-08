//Source file: C:\\VIEWS\\SECURITY\\APP\\SRC\\ui\\security\\authentication\\action\\DisplayCreateJIMS2AccountSummaryAction.java

package ui.security.authentication.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.ValidateJIMS2AccountEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.LogonControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.authentication.form.LoginForm;

public class DisplayCreateJIMS2AccountSummaryAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 4399BB120155
    */
   protected Map getKeyMethodMap()
	{
	 Map keyMap = new HashMap();
	 keyMap.put("button.back", "back");
	 keyMap.put("button.submit", "submit");
	 keyMap.put("button.cancel", "cancel");	
	 keyMap.put("button.reset", "reset");		
	 return keyMap;
	}
   public DisplayCreateJIMS2AccountSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 439711A90022
    */
   public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		LoginForm loginForm = (LoginForm)aForm;
		String action=loginForm.getAction();		
		loginForm.clearJIMSMessages();
		String password = loginForm.getJims2Password();		
		StringBuffer displayPassword= new StringBuffer(password.length());	
		for(int i=0;i<password.length();i++)
		{			
			displayPassword.append("x");			
		}	
		loginForm.setDisplayPassword(displayPassword.toString());
		//86322
		/*String passwdId = loginForm.getForgottenPasswdPhraseId();
		Collection phraseList = loginForm.getForgottenPasswdPhraseList();
		Iterator i = phraseList.iterator();
		while(i.hasNext())
		{
			CodeResponseEvent r = (CodeResponseEvent) i.next();
			if(r.getCode().equals(passwdId))
			{
				loginForm.setForgottenPasswdPhrase(r.getDescription());
			}
		}*/
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		ValidateJIMS2AccountEvent validateJIMS2 = (ValidateJIMS2AccountEvent)EventFactory.getInstance(
		LogonControllerServiceNames.VALIDATEJIMS2ACCOUNT);
		validateJIMS2.setJIMS2LogonId(loginForm.getJims2UserId());
		//validateJIMS2.setJIMS2AccountTypeId(loginForm.getAccountTypeId());
		//validateJIMS2.setJIMS2AccountTypeOID(loginForm.getAccountTypeOID());
		dispatch.postEvent(validateJIMS2);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map validateDataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(validateDataMap);
	
		LoginErrorResponseEvent loginError = (LoginErrorResponseEvent)MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
		if(loginError!=null)
		{
			loginForm.setErrorMessage(loginError.message);
			//if(loginForm.getAction().equals("summaryGenericOfficer") || loginForm.getAction().equals("confirmGenericOfficer") )
				loginForm.setAction("createJIMS2");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		else
		{
			loginForm.clearJIMSMessages();
			loginForm.setAction("summary");		
			return aMapping.findForward("success");
		}
   }
   
   public ActionForward back(
   	   ActionMapping aMapping,
   	   ActionForm aForm,
   	   HttpServletRequest aRequest,
   	   HttpServletResponse aResponse)
   	   {
   		 String forward = UIConstants.BACK;
   		 LoginForm loginForm = (LoginForm)aForm;
   		 if (loginForm.getUserType().equalsIgnoreCase("genericSP"))
   		 {	
   		 	forward = "backToSPValidation";
   		 }	
   		 if (loginForm.getUserType().equalsIgnoreCase("nonGenericUser"))
   		 {	
   		 	forward = "backToLogin";
   		 }	   		 
   		 loginForm.clearJIMSMessages();	 
   		 return aMapping.findForward(forward);
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
		 loginForm.clearJIMSMessages();	 
		 loginForm.setJims2UserId(loginForm.getUserEmail());
		 ActionForward forward = aMapping.findForward("reset");
		 return forward;
	 }
			
}
