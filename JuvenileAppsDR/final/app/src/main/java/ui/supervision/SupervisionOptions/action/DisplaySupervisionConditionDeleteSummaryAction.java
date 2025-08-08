//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplaySupervisionConditionDeleteSummaryAction.java

package ui.supervision.SupervisionOptions.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class DisplaySupervisionConditionDeleteSummaryAction extends Action
{
   
   /**
    * @roseuid 42F7C492031C
    */
   public DisplaySupervisionConditionDeleteSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A3B01D6
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	SupervisionConditionForm form = (SupervisionConditionForm)aForm;
	return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
   }
}
