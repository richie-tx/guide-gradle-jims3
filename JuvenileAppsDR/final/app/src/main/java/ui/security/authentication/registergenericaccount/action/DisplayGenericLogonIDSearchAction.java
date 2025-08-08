//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\registergenericaccount\\DisplayGenericLogonIDSearchResultsAction.java

package ui.security.authentication.registergenericaccount.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.SecurityUIHelper;
import ui.security.authentication.registergenericaccount.form.GenericLogonIdForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DisplayGenericLogonIDSearchAction extends Action
{
   
   /**
    * @roseuid 4562205E0208
    */
   public DisplayGenericLogonIDSearchAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 4562136602B1
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	   GenericLogonIdForm genericLogonIdForm = (GenericLogonIdForm) aForm;
   	   genericLogonIdForm.clear();

	   if (!SecurityUIHelper.isUserMA())
	   {
		   //only MA is allowed this operation
		   ActionErrors errors = new ActionErrors();
		   errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.registergenericaccount.unauthorized"));
		   saveErrors(aRequest, errors);
		   return aMapping.findForward(UIConstants.FAILURE);
	   }
       return aMapping.findForward(UIConstants.SUCCESS);
   }
}
