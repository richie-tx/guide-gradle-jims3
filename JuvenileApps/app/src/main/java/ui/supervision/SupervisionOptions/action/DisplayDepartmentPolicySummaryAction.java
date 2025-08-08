//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayDepartmentPolicySummaryAction.java

package ui.supervision.SupervisionOptions.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.ValidateDepartmentPolicyEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
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

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.DepartmentPolicyForm;

public class DisplayDepartmentPolicySummaryAction extends Action
{
   
   /**
    * @roseuid 42F7C48C00CB
    */
   public DisplayDepartmentPolicySummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A080263
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		DepartmentPolicyForm form = (DepartmentPolicyForm)aForm ;

		if(form.getAction() != null && form.getAction().equalsIgnoreCase(UIConstants.DELETE))
		{
			form.setPageType(UIConstants.SUMMARY);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
		}
		else
		{
			ValidateDepartmentPolicyEvent reqEvent = new ValidateDepartmentPolicyEvent();
			reqEvent.setAgencyId(form.getAgencyId());
			reqEvent.setName(form.getName());
			reqEvent.setPolicyId(form.getPolicyId());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(reqEvent);
			
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
			if (returnException != null)
			{
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE,form.getAgencyId()));
			}
			DuplicationNameErrorEvent duplicateException = (DuplicationNameErrorEvent) MessageUtil.filterComposite(response, DuplicationNameErrorEvent.class);
			if (duplicateException  != null)
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.name.exist", "Duplicate Name"));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_FAILURE,form.getAgencyId()));
			}
		   	
	   		
	   		form.setPageType( UIConstants.SUMMARY );
	   		
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
		}
   }
}
