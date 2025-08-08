//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplaySupervisionConditionSelectCourtsAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
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
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;
import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;

public class DisplaySupervisionConditionSelectCourtsAction extends LookupDispatchAction
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
				 SupervisionConditionForm form = (SupervisionConditionForm) aForm;
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
			 SupervisionConditionForm form = (SupervisionConditionForm) aForm;
			 form.getTasks().addTaskItem();
			 return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("successUpdateTasks",form.getAgencyId()));
		 }
		  
	public ActionForward back(
				   ActionMapping aMapping,
				   ActionForm aForm,
				   HttpServletRequest aRequest,
				   HttpServletResponse aResponse)
			{
				SupervisionConditionForm form = (SupervisionConditionForm) aForm;
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK,form.getAgencyId()));
			}
   /**
    * @roseuid 42F7C49500AB
    */
   public DisplaySupervisionConditionSelectCourtsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A3A01B7
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		// validate name for duplication
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;

		if(form.isSpecialCondition())
   		{
   			//create selected courts
   			
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("specialConditionSetDetailsSuccess",form.getAgencyId()));	
   		}
   
		//all courts is selected by default
		if(form.getSelectedCourts() != null && form.getSelectedCourts().size() > 0)
		{	
			form.setAllCourtsSelected(false);
		}
		else
		{
			form.setAllCourtsSelected(true);
		}
		
		//if(form.getCourts() == null || form.getCourts().size() == 0)
		//{	
			Collection courtBeans =	UISuggestedOrderHelper.getCourtBeans();
			if(form.getAgencyId().equals(UIConstants.JUV)){
				courtBeans=UISupervisionOptionHelper.filterCourtBeansForCrtCategory(UIConstants.JUVENILE_COURT_CATEGORY,courtBeans);

			}
			form.setCourts(courtBeans);
			
		//}
   		

		// validate here if it is not update
		if(!form.getAction().equals(UIConstants.UPDATE)){
			CompositeResponse response = UISupervisionOptionHelper.validateCondition(form.getAgencyId(), form.getConditionName(), form.getConditionId());
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
		}
		if(form.getAgencyId().equals(UIConstants.JUV)){
   			form.setAllCourtsSelected(true);
   		}
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
   }
}
