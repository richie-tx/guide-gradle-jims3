//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\HandleConditionAssociateToCourtPolicyAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class HandleConditionAssociateToCourtPolicyAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42F7C499009C
	 */
	public HandleConditionAssociateToCourtPolicyAction() {}

	public Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.filter", "filter");
		buttonMap.put("button.addSelected", "addSelected");
		buttonMap.put("button.next", "next");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A75038C
	 */
	public ActionForward filter(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm)aForm;
		AssociationHelper.filterConditionCourtPolicies( (SupervisionConditionForm)aForm );
		
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("filter",form.getAgencyId()));
	}

	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		* @roseuid 42F79A75038C
		*/
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		SupervisionConditionForm form = (SupervisionConditionForm)aForm;
		
		form.setPageType( UIConstants.SUMMARY );
		
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("next",form.getAgencyId()));
	}

	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		* @roseuid 42F79A75038C
		*/
	public ActionForward addSelected(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm)aForm;
		AssociationHelper.addSelectedConditionCourtPolicies( (SupervisionConditionForm)aForm );
		
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("addSelected",form.getAgencyId()));
	}
	
}
