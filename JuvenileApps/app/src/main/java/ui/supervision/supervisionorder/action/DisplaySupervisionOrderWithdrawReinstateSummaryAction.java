/*
 * Created on Dec 27, 2005
 */
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
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author hrodriguez
 */
public class DisplaySupervisionOrderWithdrawReinstateSummaryAction extends LookupDispatchAction
{

	/**
	 * 
	 */
	public DisplaySupervisionOrderWithdrawReinstateSummaryAction()
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

		String action = sof.getAction();

		if (action.equals(UIConstants.WITHDRAW))
		{
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.WITHDRAW_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		}
		else
			if (action.equals(UIConstants.REINSTATE))
			{
				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.REINSTATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
			}
			else
			{
				sof.clear();
				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
			}
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
