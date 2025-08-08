//Source file: C:\\views\\CommonSupervision\\app\\src\\action\\DisplaySuggestedOrderDetailsAction.java

package ui.supervision.suggestedorder.action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.suggestedorder.reply.SuggestedOrderResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SupervisionConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;
import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;

/**
 * @author dgibler
 *
 */
public class DisplaySuggestedOrderDetailsAction extends Action
{

	/**
	 * @roseuid 433AF49202F6
	 */
	public DisplaySuggestedOrderDetailsAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 433AF05103D2
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;


		String suggestedOrderId = sugOrderForm.getOrderId();
		if (suggestedOrderId == null || suggestedOrderId.equals(""))
		{
			suggestedOrderId = sugOrderForm.getSelectedInd();
		}
		sugOrderForm.setOrderId(suggestedOrderId);

		CompositeResponse compositeResponse = UISuggestedOrderHelper.fetchSuggestedOrder(suggestedOrderId);
		Map responseMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(responseMap);

		Collection suggestedOrders = (Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_EVENT_TOPIC);
		suggestedOrders = MessageUtil.processEmptyCollection(suggestedOrders);

		Iterator iter = suggestedOrders.iterator();

		if (iter != null && iter.hasNext())
		{
			SuggestedOrderResponseEvent so = (SuggestedOrderResponseEvent) iter.next();
			sugOrderForm.setOrderName(so.getOrderName());
			sugOrderForm.setOrderDescription(so.getOrderDescription());
			sugOrderForm.setStandardId(so.getIncludedConditionsId());
			sugOrderForm.setStandardLiteral(so.getIncludedConditionsDescription());
		}

		Collection soOffenses = (Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_OFFENSE_EVENT_TOPIC);
		soOffenses = MessageUtil.processEmptyCollection(soOffenses);
		Collections.sort((List) soOffenses);
		sugOrderForm.setOffenseSelectedList(soOffenses);

		Collection soConditions =
			(Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_CONDITION_EVENT_TOPIC);
		soConditions = MessageUtil.processEmptyCollection(soConditions);
		sugOrderForm.setConditionSelectedList(soConditions);

		Collection allCourtBeans = new ArrayList();
		if (sugOrderForm.getAllCourts() == null || sugOrderForm.getAllCourts().size() == 0)
		{
			Collection courtBeans = UISupervisionOptionHelper.getFilteredCourtBeans();
			sugOrderForm.setAllCourts(courtBeans);
			allCourtBeans = courtBeans;
		}
		else
		{
			allCourtBeans = sugOrderForm.getAllCourts();
		}
		if (sugOrderForm.getCourts() == null || sugOrderForm.getCourts().size() == 0)
		{
			sugOrderForm.setCourts(allCourtBeans);
		}

		Collection soCourts = (Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_COURT_EVENT_TOPIC);
		soCourts = MessageUtil.processEmptyCollection(soCourts);
		try{
			Collection selectedCourtBeans = UISuggestedOrderHelper.createCourtBeans(allCourtBeans, soCourts);
		
		
		selectedCourtBeans = UISuggestedOrderHelper.buildNewCollection(selectedCourtBeans);
		sugOrderForm.setSelectedCourts(selectedCourtBeans);

		String action = sugOrderForm.getAction();
		sugOrderForm.setPreviousAction(action);
		
		if (action.equals(UIConstants.VIEW))
		{
			forward = aMapping.findForward(UIConstants.VIEW_SUCCESS);
		}
		else
			if (action.equals(UIConstants.DELETE))
			{
				forward = aMapping.findForward(UIConstants.DELETE_SUCCESS);
			}
			else
			{
				forward = aMapping.findForward(UIConstants.FAILURE);
			}
		}
		catch(GeneralFeedbackMessageException e){
			sendToErrorPage(aRequest,"error.generic",e.getMessage());
			forward = aMapping.findForward("backToSearchResultsFailure");
		}
		return forward;
	}
	
	private void sendToErrorPage(HttpServletRequest aRequest, String msg, String param) {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg,param));
	    saveErrors(aRequest, errors);
	}
}
