//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayDepartmentPolicyViewAction.java

package ui.supervision.SupervisionOptions.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.DepartmentPolicyForm;

public class DisplayDepartmentPolicyViewAction extends Action
{
   
   /**
    * @roseuid 42F7C48C03D8
    */
   public DisplayDepartmentPolicyViewAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A090158
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		DepartmentPolicyForm form = (DepartmentPolicyForm)aForm;
		
		form.setViewOnly(Boolean.valueOf(aRequest.getParameter("viewOnly")).booleanValue());
		form.setInUse(false);
		if (UISupervisionOptionHelper.isDeptPolicyInUse(form.getPolicyId()))
		{
						form.setInUse(true);
		}
		if ( UISupervisionOptionHelper.loadDepartmentPolicyForm( form, form.getPolicyId() ) )
		{
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
		}
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE,form.getAgencyId()));
   }
}
