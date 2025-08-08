//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\system\\codetable\\action\\DisplayUpdateCodeTableConfirmationAction.java

package ui.codetable.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import ui.action.JIMSBaseAction;
import ui.codetable.form.CodetableForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayUpdateCodetableConfirmationAction extends LookupDispatchAction 
{
   
   /**
    * @roseuid 45B9594E02E4
    */
   public DisplayUpdateCodetableConfirmationAction() 
   {
    
   }
   public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
	   	return aMapping.findForward(UIConstants.BACK);
	}
   
   public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		CodetableForm cForm = (CodetableForm) aForm;
		return aMapping.findForward(UIConstants.CANCEL);
	}
   
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 4112564F016F
    */
   public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
       aRequest.setAttribute("opStatus","summary");  
       
       CodetableForm cForm = (CodetableForm)aForm;
       
       String codeTableName = cForm.getCodetableName();        
       boolean isValidCode = true; 
       
       if("DSM_V_CODE_WITH_TJJDCD".equalsIgnoreCase(codeTableName)){
	   String dsmCode = (String)cForm.getCurrentCode().getValueMap().get(1);
	   isValidCode = dsmCode.matches("^[a-zA-Z0-9.]+$"); //alphanumeric characters and period(.) 
       }        		
   
       if(!isValidCode){
	   sendToErrorPage(aRequest, "error.invalidDSMCode");
	   return aMapping.findForward("createFailure");
       } 
       
       return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
   }
   

	/**
	 * Method that adds a message to the request for sending back to the user takes no parameters, message key's message is diplayed as is
	 * to the user
	 * @param aRequest
	 * @param msgKey  -- error message key to add
	 */
	protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey));
		saveErrors(aRequest, errors);
	}
   
   /**
	 * @return Map
	 * @roseuid 4294862303BD
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.back", "back");
		buttonMap.put("button.submit", "submit");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
	}
}
