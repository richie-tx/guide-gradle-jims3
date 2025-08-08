//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\SubmitSupervisionConditionDeleteAction.java

package ui.supervision.SupervisionOptions.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.DeleteSupervisionConditionEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class SubmitSupervisionConditionDeleteAction extends Action 
{
   
   /**
    * @roseuid 42F7C4A203A9
    */
   public SubmitSupervisionConditionDeleteAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A3B0218
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		
//		DeleteSupervisionConditionEvent event = new DeleteSupervisionConditionEvent();
//		event.setConditionId(form.getConditionId());
		SupervisionOptionsHelper.deleteCSTaskConfiguration(form.getTasks());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		DeleteSupervisionConditionEvent event = form.getDeleteSupervisionConditionEvent();
		event.setReasonToInactivate(form.getDeleteNote());
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
		if (returnException != null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"An unknown error occurred: " + returnException.getMessage());
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
		}
	
		form.setPageType(UIConstants.CONFIRM);
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
   }
   
   protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param) {
    ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey,param));
    saveErrors(aRequest, errors);
}
}
