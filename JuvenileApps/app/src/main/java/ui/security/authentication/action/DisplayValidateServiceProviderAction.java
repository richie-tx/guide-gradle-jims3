//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\authentication\\action\\DisplayValidateServiceProviderAction.java

package ui.security.authentication.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.authentication.ValidateServiceProviderSecurityEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.LogonControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Name;
import ui.common.PhoneNumber;
import ui.security.SecurityUIHelper;
import ui.security.authentication.form.LoginForm;

public class DisplayValidateServiceProviderAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 4399CE41037F
    */
   public DisplayValidateServiceProviderAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 439711A8012A
    */
   public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		LoginForm loginForm = (LoginForm) aForm;
   		// no longer in use. Migrated to SM. Refer US #87188.
   		//String employeeId=loginForm.getEmployeeId(); 
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		ValidateServiceProviderSecurityEvent validateSP = (ValidateServiceProviderSecurityEvent)EventFactory.getInstance(LogonControllerServiceNames.VALIDATESERVICEPROVIDERSECURITY);
		// no longer in use. Migrated to SM. Refer US #87188.
   		//validateSP.setEmployeeId(employeeId);
   		validateSP.setUserID(loginForm.getLogonId());
   		validateSP.setDepartmentId(loginForm.getDepartmentId());
   		validateSP.setServiceProviderId(loginForm.getServiceProviderId());
		dispatch.postEvent(validateSP);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Map validateDataMap = MessageUtil.groupByTopic(response);
			// Perform Error handling
		MessageUtil.processReturnException(validateDataMap); 
		ServiceProviderContactResponseEvent contactResp = (ServiceProviderContactResponseEvent)MessageUtil.filterComposite(response, ServiceProviderContactResponseEvent.class);
		if(contactResp!=null)
		{
			if(contactResp.getMessage().equals(""))
			{
				loginForm.setServiceProviderName(contactResp.getProviderName());
				loginForm.setUserName(new Name(contactResp.getFirstName(),contactResp.getMiddleName(),contactResp.getLastName()));
				loginForm.setUserWorkPhoneNumber(new PhoneNumber(contactResp.getWorkPhone()));
				loginForm.setUserEmail(contactResp.getEmail());
				
			}
			else
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(contactResp.getMessage(),contactResp.getDepartmentName()));		
				saveErrors(aRequest, errors);
				return aMapping.findForward("failure");
			}
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			if (loginForm.getDepartmentName() != null && !loginForm.getDepartmentName().equals("")){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.invalid.juvEmployeeId", loginForm.getDepartmentName()));
			}else {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Employee ID is not found, please verify value and try again"));
			}
			saveErrors(aRequest, errors);
			return aMapping.findForward("failure");
		}
		//loginForm.setForgottenPasswdPhraseList(SecurityUIHelper.getPasswordQues()); //86322
   		loginForm.setAction("createServiceProvider");
    	return aMapping.findForward("genericSPSuccess");
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
   
   /**
    * @roseuid 4399BB120155
    */
   protected Map getKeyMethodMap()
	{
	 Map keyMap = new HashMap();
	 keyMap.put("button.submit", "submit");
	 keyMap.put("button.cancel", "cancel");	
	 return keyMap;
	}
}
