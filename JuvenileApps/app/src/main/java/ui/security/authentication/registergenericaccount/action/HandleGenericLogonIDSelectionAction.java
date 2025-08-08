//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\registergenericaccount\\HandleGenericLogonIDSelectionAction.java

package ui.security.authentication.registergenericaccount.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.registergenericaccount.reply.JIMSGenericAccountResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.authentication.registergenericaccount.form.GenericLogonIdForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleGenericLogonIDSelectionAction extends Action
{
   
   /**
    * @roseuid 456220750072
    */
   public HandleGenericLogonIDSelectionAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45621366034C
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		GenericLogonIdForm genericLogonIdForm = (GenericLogonIdForm) aForm;
	
		Iterator usersIter = genericLogonIdForm.getUsers().iterator();
		while (usersIter.hasNext())
		{
			JIMSGenericAccountResponseEvent resp = (JIMSGenericAccountResponseEvent) usersIter.next();
			if (resp != null && resp.getGenericAccountId().equalsIgnoreCase(genericLogonIdForm.getGenericLogonId()))
			{
				genericLogonIdForm.setLogonId(resp.getLogonId());
				genericLogonIdForm.setCurrentPassword(resp.getPassword());
				genericLogonIdForm.setStatusId(resp.getStatusId());
				break;
			}
		}
		String action = genericLogonIdForm.getAction();
		if(action != null && action.equalsIgnoreCase(UIConstants.EDIT)){
			genericLogonIdForm.setNewPassword("");
			genericLogonIdForm.setReenterPassword("");
			return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
		}else{
			genericLogonIdForm.setAction(UIConstants.INACTIVATE_SUMMARY);
			return aMapping.findForward(UIConstants.INACTIVATE_SUCCESS);
		}
	}
}
