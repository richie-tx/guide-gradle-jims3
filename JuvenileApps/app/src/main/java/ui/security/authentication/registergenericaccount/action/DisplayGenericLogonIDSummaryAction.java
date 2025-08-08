//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\registergenericaccount\\DisplayGenericLogonIDSummaryAction.java

package ui.security.authentication.registergenericaccount.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.registergenericaccount.reply.VerificationGenericAccountResponseEvent;
import messaging.registergenericaccount.VerifyJIMSGenericAccountEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.RegisterGenericControllerServiceNames;
import naming.UIConstants;

//import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.authentication.registergenericaccount.form.GenericLogonIdForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayGenericLogonIDSummaryAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 4562206803AE
    */
   public DisplayGenericLogonIDSummaryAction() 
   {
    
   }

   protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
	}
     
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45621366008F
    */
   public ActionForward next(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
   		GenericLogonIdForm gForm = (GenericLogonIdForm) aForm;
   		String logonId = gForm.getLogonId();
   		String forward = UIConstants.SUCCESS;
   		
   		String action = gForm.getAction();
   		if(action != null && action.equalsIgnoreCase(UIConstants.INACTIVATE)){
   			gForm.setAction(UIConstants.INACTIVATE_SUMMARY);
   			return aMapping.findForward(forward);
   		}
   		else if(action != null && action.equalsIgnoreCase(UIConstants.EDIT)){
   			gForm.setAction(UIConstants.UPDATE_SUMMARY);
   		}else if(action != null && (action.startsWith(UIConstants.CREATE))){
   			VerificationGenericAccountResponseEvent vResp = this.verifyLogonId(logonId);
   			if (vResp != null && vResp.getMessage() != null)
   			{
   				ActionErrors errors = new ActionErrors();
   				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(vResp.getMessage(),gForm.getLogonId()));
   				saveErrors(aRequest, errors);
   				return aMapping.findForward(UIConstants.CREATE_FAILURE);
   			}
   		}
   		return aMapping.findForward(forward);
   }

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		GenericLogonIdForm genericLogonIdForm = (GenericLogonIdForm) aForm;
		genericLogonIdForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}   
	/**
	 * @param logonId
	 * @return
	 */
	private VerificationGenericAccountResponseEvent verifyLogonId(String logonId) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		VerifyJIMSGenericAccountEvent request =
			(VerifyJIMSGenericAccountEvent) EventFactory.getInstance(RegisterGenericControllerServiceNames.VERIFYJIMSGENERICACCOUNT);
		request.setLogonId(logonId);
		dispatch.postEvent(request);
	
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
	
		return (VerificationGenericAccountResponseEvent) MessageUtil.filterComposite(compositeResponse, VerificationGenericAccountResponseEvent.class);
	}
}
