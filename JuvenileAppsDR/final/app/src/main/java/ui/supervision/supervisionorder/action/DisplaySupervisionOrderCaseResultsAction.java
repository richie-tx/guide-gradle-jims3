//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderCaseResultsAction.java

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

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.SupervisionOrderButtonHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;
import ui.supervision.supervisionorder.form.SupervisionOrderSearchForm;

/**
 * @author dgibler
 *
 */
public class DisplaySupervisionOrderCaseResultsAction extends JIMSBaseAction
{

	/**
	 * @roseuid 438F23E100D2
	 */
	public DisplaySupervisionOrderCaseResultsAction()
	{

	}
	/**
	   * @see LookupDispatchAction#getKeyMethodMap()
	   * @return Map
	   */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.submit", "submit");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}

	public ActionForward submit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		SupervisionOrderSearchForm orderSearchForm = (SupervisionOrderSearchForm) aForm;
		SupervisionOrderForm mySof=(SupervisionOrderForm)getSessionForm(aMapping,aRequest,"supervisionOrderForm",true);
		mySof.setMyStaffPos(null);
		mySof.setTaskId(null);
		mySof.setMyStaffPos(UICommonSupervisionHelper.getUserStaffPosition(null,null));
		ActionForward forward = SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
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

	
}
