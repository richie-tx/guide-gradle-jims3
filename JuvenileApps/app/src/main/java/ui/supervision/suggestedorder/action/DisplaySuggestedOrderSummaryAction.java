//Source file: C:\\views\\CommonSupervision\\app\\src\\action\\DisplaySuggestedOrderSummaryAction.java
package ui.supervision.suggestedorder.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.SupervisionConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;

/**
 * @author dgibler
 *
 */
public class DisplaySuggestedOrderSummaryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 433AF4DA02D6
	 */
	public DisplaySuggestedOrderSummaryAction()
	{

	}

	/**
		* @see LookupDispatchAction#getKeyMethodMap()
		* @return Map
		*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.previewOrder", "previewOrder");
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward previewOrder(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
	
		SuggestedOrderForm sof = (SuggestedOrderForm) aForm;
		Collection originalList=sof.getConditionSelectedList();	
		Collection newList=UISuggestedOrderHelper.setResequenceCondition(sof.getConditionSelectedList(),sof.getResequencedOrderValue(),true);
		sof.setPreviewConditionSelectedList(newList);
		sof.setResequencedOrderValue("");
		return aMapping.findForward(UIConstants.PREVIEW_SUCCESS);

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sof = (SuggestedOrderForm) aForm;
		sof.setPreviousAction("");
		Collection originalList=sof.getConditionSelectedList();
		Collection newList=UISuggestedOrderHelper.setResequenceCondition(sof.getConditionSelectedList(),sof.getResequencedOrderValue(),false);
		sof.setConditionSelectedList(newList);
		sof.setResequencedOrderValue("");
		
		String action = sof.getAction();
		if (action.equals(UIConstants.CREATE) || action.equals(UIConstants.UPDATE) || action.equals(UIConstants.COPY))
		{
			forward = aMapping.findForward(UIConstants.SELECT_SUCCESS);
		}
		else
		{
			forward = aMapping.findForward(UIConstants.FAILURE);
		}
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sof = (SuggestedOrderForm) aForm;
		if (sof.getPreviousAction() !=null){
			if (sof.getPreviousAction().equals(SupervisionConstants.STANDARD_ONLY_CONDITION)){
				forward = aMapping.findForward("backToSelectCourts");
			}else {
				forward = aMapping.findForward("backToSelectConditions");
			}
		}else {
			forward = aMapping.findForward("backToSelectConditions");
		}
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		String action = sugOrderForm.getAction();

		if (action.equals(UIConstants.CREATE))
		{
			forward = aMapping.findForward(UIConstants.CANCEL_CREATE);
		}
		else
		{
			forward = aMapping.findForward(UIConstants.CANCEL);
		}
		return forward;
	}

	/**
	 * @param aRequest
	 * @param msg
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
}
