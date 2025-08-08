//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderCompareImpactedOrderAction.java

package ui.supervision.supervisionorder.action;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author dgibler
 *
 */
public class DisplaySupervisionOrderCompareImpactedOrderAction extends LookupDispatchAction
{

	/**
	 * @roseuid 438F23E703C0
	 */
	public DisplaySupervisionOrderCompareImpactedOrderAction()
	{

	}

	/**
		* @see LookupDispatchAction#getKeyMethodMap()
		* @return Map
		*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.updateCurrentOrder", "updateCurrentOrder");
		keyMap.put("button.printComparisonOfImpactedOrder", "printComparisonOfImpactedOrder");
		keyMap.put("button.nextImpactedOrder", "nextImpactedOrder");
		keyMap.put("button.previousImpactedOrder", "previousImpactedOrder");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		// set flag to show Finish button on summary page
		sof.setCompared(true);
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	public ActionForward updateCurrentOrder(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		// set flag to show compareImpactedOrder button on summary page
		sof.setCompared(false);
		ActionForward forward = new ActionForward();
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CURRENT_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	public ActionForward printComparisonOfImpactedOrder(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
        ActionForward forward = new ActionForward();
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.PREVIEW_IMPACT_SUCCESS, UIUtil.getCurrentUserAgencyID()));
        return forward;
	}

	public ActionForward nextImpactedOrder(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		// get the current order's index
		int curOrderIndex = sof.getCurrImpactedOrderIndex();
		// set impacted order for the next page
		UISupervisionOrderHelper.setImpactedOrder(sof, curOrderIndex + 1);
		
		ActionForward forward = new ActionForward();
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.NEXT_IMPACT_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
		
	}

	public ActionForward previousImpactedOrder(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		// get the current order's index
		int curOrderIndex = sof.getCurrImpactedOrderIndex();
		// set impacted order for the next page
		UISupervisionOrderHelper.setImpactedOrder(sof, curOrderIndex - 1);

		ActionForward forward = new ActionForward();
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.NEXT_IMPACT_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
}
