//Source file: C:\\views\\CommonSupervision\\app\\src\\action\\HandleSuggestedOrderSelectionAction.java
package ui.supervision.suggestedorder.action;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.suggestedorder.DeleteSuggestedOrderEvent;
import messaging.suggestedorder.GetSuggestedOrderDataEvent;
import messaging.suggestedorder.ValidateSuggestedOrderDeleteEvent;
import messaging.suggestedorder.reply.SuggestedOrderInUseResponseEvent;
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

import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;
import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;

/**
 * @author dgibler
 *
 */
public class HandleSuggestedOrderSelectionAction extends LookupDispatchAction
{
	private static String COPY = "Copy of ";
	/**
	 * @roseuid 433AF47603E0
	 */
	public HandleSuggestedOrderSelectionAction()
	{

	}

	/**
		* @see LookupDispatchAction#getKeyMethodMap()
		* @return Map
		*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.update", "update");
		keyMap.put("button.copy", "copy");
		keyMap.put("button.delete", "delete");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.backToSearch", "backToSearch");
		return keyMap;
	}

	public ActionForward update(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		ActionForward forward;
		try{
			//This logic is faulty and doesn't take into consideration that the
			//previous action may have been view on a different suggested order!
//			if (!sugOrderForm.getPreviousAction().equals(UIConstants.VIEW))
//			{
				this.processSuggesterOrderData(sugOrderForm);
//			}
			sugOrderForm.setPreviousOrderName(sugOrderForm.getOrderName());
			sugOrderForm.setPreviousAction("");
			forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
		}
		catch(GeneralFeedbackMessageException e){
			sendToErrorPage(aRequest,"error.generic",e.getMessage());
			forward = aMapping.findForward("deleteFailure");
		}
		return forward;
	}

	public ActionForward copy(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		ActionForward forward;
		try{
			//This logic is faulty and doesn't take into consideration that the
			//previous action may have been view on a different suggested order!
//			if (!sugOrderForm.getPreviousAction().equals(UIConstants.VIEW))
//			{
				sugOrderForm = this.processSuggesterOrderData(sugOrderForm);
//			}
			sugOrderForm.setPreviousAction("");
			sugOrderForm.setOrderName(this.prependWithCopyLiteral(sugOrderForm.getOrderName()));
			sugOrderForm.setOrderDescription(this.prependWithCopyLiteral(sugOrderForm.getOrderDescription()));
	
			forward = aMapping.findForward(UIConstants.COPY_SUCCESS);
		}
		catch(GeneralFeedbackMessageException e){
			sendToErrorPage(aRequest,"error.generic",e.getMessage());
			forward = aMapping.findForward("deleteFailure");
		}
		return forward;
	}

	public ActionForward delete(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		/* Removing validation of orders in progress.  Once a suggested order is 
		 * chosen and conditions are attached to the order there is no reason to
		 * keep the suggested order Id.  We don't use it for update.
		 */
		/* IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		ValidateSuggestedOrderDeleteEvent requestEvent =
			(ValidateSuggestedOrderDeleteEvent) EventFactory.getInstance(
				SuggestedOrderControllerServiceNames.VALIDATESUGGESTEDORDERDELETE);
		requestEvent.setSuggestedOrderId(sugOrderForm.getOrderId());
		dispatch.postEvent(requestEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Object obj = MessageUtil.filterComposite(response, SuggestedOrderInUseResponseEvent.class);
		ActionForward forward = null;
		if (obj != null)
		{
			this.sendToErrorPage(aRequest, "error.suggestedorder.inuse");
			forward = aMapping.findForward(UIConstants.DELETE_FAILURE);
		}
		else
		{
			forward = aMapping.findForward(UIConstants.DELETE_SUCCESS);
		}
		//return forward;*/
		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
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

	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		String action = sugOrderForm.getAction();

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		if (action.equalsIgnoreCase(UIConstants.DELETE))
		{
			DeleteSuggestedOrderEvent deleteRequestEvent = this.getDeleteRequestEvent(sugOrderForm);
			dispatch.postEvent(deleteRequestEvent);
		}

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

		MessageUtil.processReturnException(compositeResponse);

		SuggestedOrderResponseEvent sore =
			(SuggestedOrderResponseEvent) MessageUtil.filterComposite(
				compositeResponse,
				SuggestedOrderResponseEvent.class);

		if (sore != null)
		{
			sugOrderForm.setOrderId(sore.getSuggestedOrderId());
		}

		if (action.equals(UIConstants.DELETE))
		{
			sugOrderForm.setAction(UIConstants.CONFIRM_DELETE);
			forward = aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
		}
		else
			if (action.equals(UIConstants.UPDATE))
			{
				sugOrderForm.setAction(UIConstants.CONFIRM_UPDATE);
				forward = aMapping.findForward(UIConstants.CONFIRM_SUCCESS);

			}
			else
				if (action.equals(UIConstants.COPY))
				{
					sugOrderForm.setAction(UIConstants.CONFIRM_COPY);
					forward = aMapping.findForward(UIConstants.CONFIRM_SUCCESS);

				}
				else
				{
					forward = aMapping.findForward(UIConstants.FAILURE);
				}

		return forward;
	}

	/**
	 * @param sugOrderForm
	 * @return
	 */
	private DeleteSuggestedOrderEvent getDeleteRequestEvent(SuggestedOrderForm sugOrderForm)
	{
		DeleteSuggestedOrderEvent deleteRequestEvent =
			(DeleteSuggestedOrderEvent) EventFactory.getInstance(
				SuggestedOrderControllerServiceNames.DELETESUGGESTEDORDER);

		deleteRequestEvent.setSuggestedOrderId(sugOrderForm.getOrderId());

		return deleteRequestEvent;
	}

	public ActionForward backToSearch(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK_TO_SEARCH);
		return forward;
	}

	/**
	 * @param aString
	 * @return
	 */
	private String prependWithCopyLiteral(String aString)
	{
		StringBuffer sb = new StringBuffer(COPY);
		if (aString != null)
		{
			sb.append(aString);
		}
		return sb.toString();
	}
	/**
	 * @param sugOrderform
	 * @return
	 */
	private SuggestedOrderForm processSuggesterOrderData(SuggestedOrderForm sugOrderForm) throws GeneralFeedbackMessageException
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetSuggestedOrderDataEvent requestEvent =
			(GetSuggestedOrderDataEvent) EventFactory.getInstance(
				SuggestedOrderControllerServiceNames.GETSUGGESTEDORDERDATA);

		requestEvent.setSuggestedOrderId(sugOrderForm.getOrderId());
		dispatch.postEvent(requestEvent);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		Map responseMap = MessageUtil.groupByTopic(response);
		MessageUtil.processReturnException(responseMap);

		Collection suggestedOrders = (Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_EVENT_TOPIC);
		suggestedOrders = MessageUtil.processEmptyCollection(suggestedOrders);

		Iterator iter = suggestedOrders.iterator();
		SuggestedOrderResponseEvent sore = (SuggestedOrderResponseEvent) iter.next();
		sugOrderForm.setOrderName(sore.getOrderName());
		sugOrderForm.setOrderDescription(sore.getOrderDescription());
		sugOrderForm.setStandardId(sore.getIncludedConditionsId());

		Collection offenses = (Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_OFFENSE_EVENT_TOPIC);
		offenses = MessageUtil.processEmptyCollection(offenses);
		Collections.sort((List) offenses);
		sugOrderForm.setOffenseSelectedList(offenses);

		Collection soCourts = (Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_COURT_EVENT_TOPIC);
		soCourts = MessageUtil.processEmptyCollection(soCourts);
		Collection courtBeans = UISuggestedOrderHelper.createCourtBeans(sugOrderForm.getAllCourts(), soCourts);
		courtBeans = UISuggestedOrderHelper.buildNewCollection(courtBeans);
		sugOrderForm.setSelectedCourts(courtBeans);

		Collection soConditions =
			(Collection) responseMap.get(SupervisionConstants.SUGGESTED_ORDER_CONDITION_EVENT_TOPIC);
		soConditions = MessageUtil.processEmptyCollection(soConditions);
		sugOrderForm.setConditionSelectedList(soConditions);

		return sugOrderForm;
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
	private void sendToErrorPage(HttpServletRequest aRequest, String msg, String param) {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg,param));
	    saveErrors(aRequest, errors);
	}
}
