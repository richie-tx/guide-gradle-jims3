//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayCourtPolicyViewAction.java

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
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;


public class DisplayCourtPolicyViewAction extends Action
{
   
   /**
    * @roseuid 42F7C48500AB
    */
   public DisplayCourtPolicyViewAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F7997E00DF
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		CourtPolicyForm form = (CourtPolicyForm)aForm;
		
		form.setViewOnly(Boolean.valueOf(aRequest.getParameter("viewOnly")).booleanValue());
		  
		form.setPolicyId(aRequest.getParameter("policyId"));
		
		if ( UISupervisionOptionHelper.loadCourtPolicyForm( form, form.getPolicyId() ) )
		{
			form.setInUse(false);
			if (UISupervisionOptionHelper.isCourtPolicyInUse(form.getPolicyId()))
			{
				form.setInUse(true);
			}
			SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,false);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
		}
		
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE,form.getAgencyId()));
   }
}
