//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\SubmitConditionAssociateToCourtPolicyAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.SaveConditionAssociateToCourtPoliciesEvent;
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
import ui.supervision.SupervisionOptions.form.AssociateBean;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class SubmitConditionAssociateToCourtPolicyAction extends Action
{
   
   /**
    * @roseuid 42F7C49C0119
    */
   public SubmitConditionAssociateToCourtPolicyAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A7503CA
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		SupervisionConditionForm form = (SupervisionConditionForm)aForm;
	
		// create event to post
		SaveConditionAssociateToCourtPoliciesEvent reqEvent = new SaveConditionAssociateToCourtPoliciesEvent();
		reqEvent.setConditionId(form.getConditionId());
		
		// add courtPolicyIds into the event            
		Collection associatedPolicies = form.getAssociatedCourtPolicies();
		
		if(associatedPolicies != null){
			  Iterator it = associatedPolicies.iterator();
			  while(it.hasNext()){
				AssociateBean asscBean = (AssociateBean)it.next();
				reqEvent.addPolicyId(asscBean.getObjId());
			  }
		}
		// post the event
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(reqEvent);
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
