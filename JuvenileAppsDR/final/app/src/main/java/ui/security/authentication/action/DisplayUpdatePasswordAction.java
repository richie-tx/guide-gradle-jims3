//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\authentication\\action\\DisplayUpdatePasswordAction.java

package ui.security.authentication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.security.authentication.reply.LoginErrorResponseEvent;
import naming.PDSecurityConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.authentication.form.LoginForm;

public class DisplayUpdatePasswordAction extends Action
{
   
   /**
    * @roseuid 4399CE41013D
    */
   public DisplayUpdatePasswordAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 43984268019A
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		LoginForm loginForm = (LoginForm) aForm;
		String fromPage = aRequest.getParameter("fromPage");
		if (fromPage == null){
			fromPage = "";
		}
		loginForm.setFromPage(fromPage);
   		loginForm.clearJIMSMessages();
   		loginForm.setCurrentPassword("");
   		loginForm.setNewPassword("");
   		loginForm.setConfirmPassword("");
   		//86322
   		//loginForm.setForgottenPasswdPhraseId("");
   		//loginForm.setAnswer("");
		/*Collection codes = CodeHelper.getCodes(PDCodeTableConstants.PASSWORD_QUESTION);
		Iterator i = codes.iterator();
		Collection passwordQuestions = new ArrayList();
		while (i.hasNext())
		{
			CodeResponseEvent r = (CodeResponseEvent) i.next();
			passwordQuestions.add(r);			
		}*/
   		if ((loginForm.getUserType().equals("nonGenericUser")) || (loginForm.getUserType().equals(PDSecurityConstants.NON_GENERIC_USER)))
   		{
   		    ActionErrors errors = new ActionErrors();
   		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Non-Generic user cannot update the password from JIMS2 Application"));
   		    saveErrors(aRequest, errors);
   		    loginForm.setAction("updatePassword");
   		}
		
		//loginForm.setForgottenPasswdPhraseList(passwordQuestions);
		return  aMapping.findForward(UIConstants.SUCCESS);
   }
}
	