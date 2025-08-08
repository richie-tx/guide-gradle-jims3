/*
 * Created on Oct 11, 2005
 *
 * 01/09/2006 - Hien Rodriguez  JIMS200027446 - Set Action to "view" when search result found is 1.
 */
package ui.supervision.suggestedorder.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.suggestedorder.GetSuggestedOrdersEvent;
import messaging.suggestedorder.reply.SuggestedOrderResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SuggestedOrderControllerServiceNames;
import naming.SupervisionConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.StringUtil;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;
import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;

/**
 * @author dgibler
 *
 */
public class DisplaySuggestedOrderSearchResultsAction extends LookupDispatchAction
{

	/**
		 * @roseuid 433AF4B0020B
		 */
	public DisplaySuggestedOrderSearchResultsAction()
	{

	}

	/**
		* @see LookupDispatchAction#getKeyMethodMap()
		* @return Map
		*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.submit", "submit");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward submit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetSuggestedOrdersEvent requestEvent =
			(GetSuggestedOrdersEvent) EventFactory.getInstance(SuggestedOrderControllerServiceNames.GETSUGGESTEDORDERS);

		requestEvent.setSuggestedOrderDescription(StringUtil.trimToEmpty(sugOrderForm.getOrderDescription()));
		requestEvent.setSuggestedOrderName(StringUtil.trimToEmpty(sugOrderForm.getOrderName()));
		requestEvent.setCourtDivision(sugOrderForm.getCourtDivisionId());
		requestEvent.setConditionName(StringUtil.trimToEmpty(sugOrderForm.getConditionName()));
		requestEvent.setOffenseCode(StringUtil.trimToEmpty(sugOrderForm.getOffenseId()));
		requestEvent.setOffenseLiteral(StringUtil.trimToEmpty(sugOrderForm.getOffenseLiteral()));
		requestEvent.setPenalCode(StringUtil.trimToEmpty(sugOrderForm.getPenalCodeId()));

		Collection selectedCourts = UISuggestedOrderHelper.retrieveSelectedCourtsFromRequest(sugOrderForm, aRequest);
		selectedCourts = MessageUtil.processEmptyCollection(selectedCourts);
		selectedCourts = UISuggestedOrderHelper.buildCourtList(selectedCourts);
		requestEvent.setCourts(selectedCourts);

		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map responseMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(responseMap);

		Collection sos = (Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_EVENT_TOPIC);
		if (sos != null && sos.size() == 1)
		{
			this.processOneSuggestedOrder(responseMap, sugOrderForm);
			sugOrderForm.setAction(UIConstants.VIEW);
			forward = aMapping.findForward(UIConstants.VIEW_SUCCESS);
		}
		else
		{
			Collection soConditions =
				(Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_CONDITION_EVENT_TOPIC);

			if (soConditions == null || soConditions.size() == 0)
			{
				this.sendToErrorPage(aRequest, "error.noRecords");
				sugOrderForm.setSuggestedOrderList(new ArrayList());
				sugOrderForm.setSuggestedOrderSize(new Integer(0).toString());
				forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
				return forward;
			}
			else
			{
				sugOrderForm.setSuggestedOrderList(soConditions);
				sugOrderForm.setSuggestedOrderSize(new Integer(soConditions.size()).toString());
			}

			forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		}
		return forward;
	}

	/**
	 * @param responseMap
	 * @param sugOrderForm
	 */
	private void processOneSuggestedOrder(Map responseMap, SuggestedOrderForm sugOrderForm)
	{
		Collection sos = (Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_EVENT_TOPIC);
		MessageUtil.processEmptyCollection(sos);
		Iterator iter = sos.iterator();

		if (iter != null)
		{
			SuggestedOrderResponseEvent so = (SuggestedOrderResponseEvent) iter.next();
			sugOrderForm.setOrderName(so.getOrderName());
			sugOrderForm.setOrderDescription(so.getOrderDescription());
			sugOrderForm.setOrderId(so.getSuggestedOrderId());
		}

		Collection soOffenses = (Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_OFFENSE_EVENT_TOPIC);
		MessageUtil.processEmptyCollection(soOffenses);
		sugOrderForm.setOffenseSelectedList(soOffenses);

		Collection soConditions =
			(Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_CONDITION_EVENT_TOPIC);
		MessageUtil.processEmptyCollection(soConditions);
		sugOrderForm.setConditionSelectedList(soConditions);

	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}

}
