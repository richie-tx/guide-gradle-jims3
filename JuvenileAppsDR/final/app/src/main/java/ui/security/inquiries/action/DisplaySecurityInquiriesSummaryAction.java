//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesSummaryAction.java

package ui.security.inquiries.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.inquiries.form.SecurityInquiriesForm;

public class DisplaySecurityInquiriesSummaryAction extends Action
{
   
   /**
    */
   public DisplaySecurityInquiriesSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward execute(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
	   	SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm;  
	   	securityInquiriesForm.clear();
	   	return aMapping.findForward(UIConstants.CANCEL);
	   }
}
