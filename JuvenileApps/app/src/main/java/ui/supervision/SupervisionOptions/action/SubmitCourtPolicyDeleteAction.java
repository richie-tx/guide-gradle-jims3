//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\SubmitCourtPolicyDeleteAction.java

package ui.supervision.SupervisionOptions.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.DeleteCourtPolicyEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;

public class SubmitCourtPolicyDeleteAction extends Action
{
   
   /**
    * @roseuid 42F7C49E03B9
    */
   public SubmitCourtPolicyDeleteAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F7997B0263
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		CourtPolicyForm form = (CourtPolicyForm) aForm;
			
		DeleteCourtPolicyEvent event = new DeleteCourtPolicyEvent();
		event.setPolicyId(form.getPolicyId());
		event.setInUse(form.isInUse());
		if(!form.isInUse()){
			SupervisionOptionsHelper.deleteCSTaskConfiguration(form.getTasks());
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
		if (returnException != null)
		{
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE,form.getAgencyId()));
		}
		
		form.setPageType(UIConstants.CONFIRM);
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
   }
}
