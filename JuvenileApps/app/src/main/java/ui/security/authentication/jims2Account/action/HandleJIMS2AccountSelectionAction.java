//Source file: C:\\views\\Security\\app\\src\\ui\\security\\authenication\\registergenericaccount\\HandleJIMS2AccountSelectionAction.java

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

import ui.security.authentication.jims2Account.UIJIMS2AccountHelper;
import ui.security.authentication.jims2Account.form.JIMS2AccountForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleJIMS2AccountSelectionAction extends Action
{
   
   /**
    * @roseuid 456220750072
    */
   public HandleJIMS2AccountSelectionAction() 
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
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		String action = jaForm.getAction();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJIMS2AccountEvent getEvent =
			(GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
		getEvent.setJIMS2LogonId(jaForm.getJims2LogonId());
		getEvent.setLogonId(jaForm.getJimsLogonId());
		dispatch.postEvent(getEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		JIMS2AccountResponseEvent resp = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		String userType = resp.getJIMS2AccountTypeId();
		if (userType.equalsIgnoreCase("N")){
			UIJIMS2AccountHelper.setNonGenericUser(jaForm, resp);
   		} else {	
   			UIJIMS2AccountHelper.setGenericUser(jaForm, resp);
   		}
 	
		if(action != null && action.equalsIgnoreCase(UIConstants.UPDATE)){
			jaForm.setNewJIMS2LogonId("");
			jaForm.setReenterNewJIMS2LogonId("");
			jaForm.setNewPassword("");
			jaForm.setReenterNewPassword("");
			jaForm.setPasswordQuestion("");
			return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
		}else if(action != null && action.equalsIgnoreCase(UIConstants.INACTIVATE)) {
			return aMapping.findForward(UIConstants.INACTIVATE_SUCCESS);
		} else {
			return aMapping.findForward(UIConstants.DETAIL_SUCCESS);
		}		
	}
}
