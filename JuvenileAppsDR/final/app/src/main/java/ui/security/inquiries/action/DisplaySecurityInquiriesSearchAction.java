//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesSearchAction.java

package ui.security.inquiries.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.LoadSecurityCodeTables;
import ui.security.inquiries.form.SecurityInquiriesForm;

public class DisplaySecurityInquiriesSearchAction extends Action
{
   
   /**
    * @roseuid 44E9D1DE006D
    */
   public DisplaySecurityInquiriesSearchAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 44D2216702EB
    */
   public ActionForward execute(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
   	   SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm;
   	   securityInquiriesForm.clear();
   	   LoadSecurityCodeTables load = LoadSecurityCodeTables.getInstance();
   	   load.setSecurityInquiriesForm(securityInquiriesForm);
   	   return aMapping.findForward(UIConstants.SUCCESS);
   }
}
