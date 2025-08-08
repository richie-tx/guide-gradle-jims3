//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\authentication\\action\\DisplayLoginAction.java

package ui.security.authentication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.authentication.form.LoginForm;

import naming.UIConstants;

public class DisplayLoginAction extends Action
{
   
   /**
    * @roseuid 4399CE4002E3
    */
   public DisplayLoginAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 439711A70311
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		LoginForm loginForm = (LoginForm) aForm;
   		loginForm.clearJIMSMessages();
   		loginForm.setConfirmMessage("JIMS2 Account created successfully. Please login with your JIMS2 User ID");
    	return aMapping.findForward(UIConstants.SUCCESS);
   }
}
