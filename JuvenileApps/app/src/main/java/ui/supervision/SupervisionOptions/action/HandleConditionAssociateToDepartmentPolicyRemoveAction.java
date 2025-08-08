//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\HandleConditionAssociateToCourtPolicyAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.AssociateBean;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class HandleConditionAssociateToDepartmentPolicyRemoveAction extends Action
{

	/**
	 * @roseuid 42F7C499009C
	 */
	public HandleConditionAssociateToDepartmentPolicyRemoveAction() {}

	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		* @roseuid 42F79A75038C
		*/
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm)aForm;
		
		String removeId = aRequest.getParameter("removeId");
		Iterator iter = form.getAssociatedDeptPolicies().iterator();
		while ( iter.hasNext() )
		{
			AssociateBean bean = (AssociateBean)iter.next();
			if ( bean.getObjId().equals(removeId)  )
			{
				iter.remove();
				// remove from the resultant list
				form.removeAssociatedDeptPolicy(bean);
				break;
			}
		}
		
		AssociationHelper.filterConditionDepartmentPolicies( (SupervisionConditionForm)aForm );
		
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("success",form.getAgencyId()));
	}
	
}
