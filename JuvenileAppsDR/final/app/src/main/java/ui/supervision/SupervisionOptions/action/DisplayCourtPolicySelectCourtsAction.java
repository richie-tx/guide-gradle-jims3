//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayCourtPolicySelectCourtsAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.ValidateCourtPolicyEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.CSTaskItem;
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;

public class DisplayCourtPolicySelectCourtsAction extends LookupDispatchAction
{
   
	protected Map getKeyMethodMap() {
		  Map buttonMap = new HashMap();
		buttonMap.put("button.addMore", "addMore");
					  buttonMap.put("button.remove", "remove");
	   buttonMap.put("button.back", "back");
		  buttonMap.put("button.next", "next");
		  return buttonMap;
	  }
   
	public ActionForward remove(
						ActionMapping aMapping,
						ActionForm aForm,
						HttpServletRequest aRequest,
						HttpServletResponse aResponse)
				 {
					CourtPolicyForm form = (CourtPolicyForm) aForm;
					 if(form.getSelectedValue()==null || form.getSelectedValue().equals(""))
						return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("successUpdateTasks",form.getAgencyId()));
					// form.getTasks().removeTaskItem(Integer.parseInt(form.getSelectedValue()));
					ArrayList myList=(ArrayList)(form.getTasks().getTaskItems());
					CSTaskItem myTaskItem=(CSTaskItem)(myList.get(Integer.parseInt(form.getSelectedValue())));
					myTaskItem.setDeleted(true);
					 return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("successUpdateTasks",form.getAgencyId()));
				 }
		  
		public ActionForward addMore(
					ActionMapping aMapping,
					ActionForm aForm,
					HttpServletRequest aRequest,
					HttpServletResponse aResponse)
			 {
				CourtPolicyForm form = (CourtPolicyForm) aForm;
				 form.getTasks().addTaskItem();
				 return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("successUpdateTasks",form.getAgencyId()));
			 }

   
	public ActionForward back(
				   ActionMapping aMapping,
				   ActionForm aForm,
				   HttpServletRequest aRequest,
				   HttpServletResponse aResponse)
			{
				CourtPolicyForm form = (CourtPolicyForm)aForm;
				form.populateGroupsForDisplay();
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK,form.getAgencyId()));
			}
   /**
    * @roseuid 42F7C4830280
    */
   public DisplayCourtPolicySelectCourtsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F7997C0265
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		CourtPolicyForm policyForm = (CourtPolicyForm)aForm;
   		
		//	all courts is selected by default
		if(policyForm.getSelectedCourts() != null && policyForm.getSelectedCourts().size() > 0)
		{	
			policyForm.setAllCourtsSelected(false);
		}
		else
		{
			policyForm.setAllCourtsSelected(true);
		}
		  
		if(policyForm.getCourts() == null || policyForm.getCourts().size() == 0)
		{	
			Collection courtBeans =	UISupervisionOptionHelper.getFilteredCourtBeans();
			if(policyForm.getAgencyId().equals(UIConstants.JUV)){
				courtBeans=UISupervisionOptionHelper.filterCourtBeansForCrtCategory(UIConstants.JUVENILE_COURT_CATEGORY,courtBeans);
			}
			policyForm.setCourts(courtBeans);
		}
		
		ValidateCourtPolicyEvent reqEvent = new ValidateCourtPolicyEvent();
		reqEvent.setAgencyId(policyForm.getAgencyId());
		reqEvent.setName(policyForm.getCourtPolicyName());
		reqEvent.setPolicyId(policyForm.getPolicyId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(reqEvent);
	
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
		if (returnException != null)
		{
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE,policyForm.getAgencyId()));
		}
		DuplicationNameErrorEvent duplicateException = (DuplicationNameErrorEvent) MessageUtil.filterComposite(response, DuplicationNameErrorEvent.class);
		if (duplicateException  != null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.name.exist", "Duplicate Name"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_FAILURE,policyForm.getAgencyId()));
	//		return aMapping.findForward(UIConstants.FAILURE);
		}
	   	
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,policyForm.getAgencyId()));
   }
   
   
}
