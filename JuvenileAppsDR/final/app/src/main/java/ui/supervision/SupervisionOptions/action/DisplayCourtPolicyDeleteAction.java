//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayCourtPolicyDeleteAction.java

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

public class DisplayCourtPolicyDeleteAction extends Action
{
   
   /**
    * @roseuid 42F7C47C02EE
    */
   public DisplayCourtPolicyDeleteAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F7997B0187
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		CourtPolicyForm form = (CourtPolicyForm)aForm;
		if ( UISupervisionOptionHelper.loadCourtPolicyForm( form, form.getCourtPolicyId() ) )
		{
			form.setAction(UIConstants.DELETE);
			SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,false);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
		}
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE,form.getAgencyId()));
   }
}
