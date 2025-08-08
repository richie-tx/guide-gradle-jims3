//Source file: C:\\views\\Security\\app\\src\\ui\\security\\authenication\\jims2Account\\DisplayJIMS2AccountSearchAction.java

package ui.security.authentication.jims2Account.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.GetJIMS2AccountEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.LogonControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.LoadSecurityCodeTables;
import ui.security.SecurityUIHelper;
import ui.security.authentication.jims2Account.UIJIMS2AccountHelper;
import ui.security.authentication.jims2Account.form.JIMS2AccountForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DisplayJIMS2AccountSearchAction extends Action
{
   
   /**
    * @roseuid 4562205E0208
    */
   public DisplayJIMS2AccountSearchAction() 
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
   public ActionForward execute(
	ActionMapping aMapping,
	ActionForm aForm,
	HttpServletRequest aRequest,
	HttpServletResponse aResponse) 
   {
   		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
   		jaForm.clear();
   		String forward = UIConstants.FAILURE;
   		String userType = SecurityUIHelper.getUserTypeId();
   		jaForm.setUserType(userType);
		jaForm.setBasicUser("N"); 
		jaForm.setShowInactive("N");
		jaForm.setCurrentUserLogonId(SecurityUIHelper.getJIMSLogonId());
		LoadSecurityCodeTables load = LoadSecurityCodeTables.getInstance();
	   	load.setJIMS2AccountForm(jaForm);
   	// Only security admins (MA, SA, ASA and LA) -
	//	1. go to search page all others directly to update page
	//	2. allowed to inactivate
   		if (userType.equalsIgnoreCase("MA") || userType.equalsIgnoreCase("SA") || userType.equalsIgnoreCase("ASA") || userType.equalsIgnoreCase("LA")  )
   		{
  			jaForm.setShowInactive("Y");
   			forward = UIConstants.SUCCESS;
   		}else{
   			jaForm.setBasicUser("Y");
 			jaForm.setAction(UIConstants.UPDATE);  		
   			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   			GetJIMS2AccountEvent getEvent =
   				(GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
   			getEvent.setJIMS2LogonId(SecurityUIHelper.getJIMS2LogonId());
   			dispatch.postEvent(getEvent);

   			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
   			JIMS2AccountResponseEvent resp = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
   			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
   			MessageUtil.processReturnException(dataMap);
   			userType = resp.getJIMS2AccountTypeId();
   			
   			if (userType.equalsIgnoreCase("N")){
   				UIJIMS2AccountHelper.setNonGenericUser(jaForm, resp);
   	   			forward = UIConstants.NONGENERIC_SUCCESS;
   	   		} else {	
   	   			UIJIMS2AccountHelper.setGenericUser(jaForm, resp);
	   			forward = UIConstants.GENERIC_SUCCESS;
   	   		}
   		}
   		return aMapping.findForward(forward);
   	}
}
