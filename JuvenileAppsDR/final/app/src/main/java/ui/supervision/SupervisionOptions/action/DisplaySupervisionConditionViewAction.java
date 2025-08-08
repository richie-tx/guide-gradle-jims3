//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplaySupervisionConditionViewAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.reply.ConditionInUseEvent;
import messaging.supervisionorder.ValidateConditionInUseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class DisplaySupervisionConditionViewAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 42F7C4980167
    */
   public DisplaySupervisionConditionViewAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A3902FF
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
       SupervisionConditionForm form = (SupervisionConditionForm)aForm;
       
      		
		form.setViewOnly(Boolean.valueOf(aRequest.getParameter("viewOnly")).booleanValue());
		
		ValidateConditionInUseEvent requestEvent =
			(ValidateConditionInUseEvent) EventFactory.getInstance(
					SupervisionOrderControllerServiceNames.VALIDATECONDITIONINUSE);

		requestEvent.setConditionId(form.getConditionId());
		if(aRequest.getAttribute("Flag")!=null){
		 if(aRequest.getAttribute("Flag").equals("2")){
	           
		 	if(aRequest.getAttribute("conditionId")!=null){
		     String condId = (String) (aRequest.getAttribute("conditionId"));
		     requestEvent.setConditionId(condId);
		     form.setConditionId(condId);
		 	}
	       }
		}
		
		requestEvent.setAction(UIConstants.UPDATE);
				
		ConditionInUseEvent responseEvent = (ConditionInUseEvent) 
			MessageUtil.postRequest(requestEvent, ConditionInUseEvent.class);
		
		form.setInUse(false);
		if (responseEvent != null)
		{
			form.setInUse(true);
		}
   		if ( UISupervisionOptionHelper.loadSupervisionConditionForm( form, form.getConditionId() ) )
   		{
			//return aMapping.findForward(UIConstants.SUCCESS);
			SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,false);
//			check if Special Condition
			if (form.isSpecialCondition())
				{
					return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SPECIAL_CONDITION_SUCCESS,form.getAgencyId()));
				}
				else
				{
					return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
				}
   		}
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE,form.getAgencyId()));
		
   }

@Override
protected void addButtonMapping(Map keyMap) {
	// TODO Auto-generated method stub
	
}
}
