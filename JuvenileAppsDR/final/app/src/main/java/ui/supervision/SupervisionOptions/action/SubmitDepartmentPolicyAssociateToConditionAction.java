//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\SubmitDepartmentPolicyAssociateToConditionAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.SaveDepartmentPolicyAssociateToConditionsEvent;
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
import ui.supervision.SupervisionOptions.form.DepartmentPolicyForm;

public class SubmitDepartmentPolicyAssociateToConditionAction extends Action
{
   
   /**
    * @roseuid 42F7C4A0031C
    */
   public SubmitDepartmentPolicyAssociateToConditionAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A750011
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		DepartmentPolicyForm form = (DepartmentPolicyForm)aForm;
			
		// create event to post
		SaveDepartmentPolicyAssociateToConditionsEvent reqEvent = new SaveDepartmentPolicyAssociateToConditionsEvent();
		reqEvent.setPolicyId(form.getPolicyId());
				
		// add courtPolicyIds into the event            
		Collection associatedConditions = form.getAssociatedConditions();
				
		if(associatedConditions != null){
			  Iterator it = associatedConditions.iterator();
			  while(it.hasNext()){
				AssociateBean asscBean = (AssociateBean)it.next();
				reqEvent.addConditionId(asscBean.getObjId());
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
