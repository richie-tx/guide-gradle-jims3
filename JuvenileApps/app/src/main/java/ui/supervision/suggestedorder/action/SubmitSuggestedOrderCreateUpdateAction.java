//Source file: C:\\views\\CommonSupervision\\app\\src\\action\\SubmitSuggestedOrderCreateUpdateAction.java

package ui.supervision.suggestedorder.action;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.suggestedorder.SuggestedOrderConditionRequestEvent;
import messaging.suggestedorder.SuggestedOrderCourtRequestEvent;
import messaging.suggestedorder.SuggestedOrderOffenseRequestEvent;
import messaging.suggestedorder.UpdateSuggestedOrderEvent;
import messaging.suggestedorder.reply.SuggestedOrderConditionResponseEvent;
import messaging.suggestedorder.reply.SuggestedOrderResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SuggestedOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;

/**
 * @author dgibler
 *
 */
public class SubmitSuggestedOrderCreateUpdateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 433AF51D024A
	 */
	public SubmitSuggestedOrderCreateUpdateAction()
	{

	}

	/**
		* @see LookupDispatchAction#getKeyMethodMap()
		* @return Map
		*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.finish", "finish");
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
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		//long startTime = System.currentTimeMillis();		
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		String action = sugOrderForm.getAction();

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		if (action.equalsIgnoreCase(UIConstants.CREATE)
			|| action.equalsIgnoreCase(UIConstants.UPDATE)
			|| action.equalsIgnoreCase(UIConstants.COPY))
		{
			UpdateSuggestedOrderEvent updateRequestEvent = this.getCreateUpdateRequestEvent(sugOrderForm);
			dispatch.postEvent(updateRequestEvent);
		}

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

		MessageUtil.processReturnException(compositeResponse);

		SuggestedOrderResponseEvent sore =
			(SuggestedOrderResponseEvent) MessageUtil.filterComposite(compositeResponse, SuggestedOrderResponseEvent.class);

		if (sore != null)
		{
			sugOrderForm.setOrderId(sore.getSuggestedOrderId());
		}

		if (action.equals(UIConstants.CREATE))
		{
			sugOrderForm.setAction(UIConstants.CONFIRM_CREATE);
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
		//System.out.println("Elapsed Time= " + (System.currentTimeMillis()-startTime)/1000F);		
		return forward;
	}

	/**
	 * @param sugOrderForm
	 * @return
	 */
	private UpdateSuggestedOrderEvent getCreateUpdateRequestEvent(SuggestedOrderForm sugOrderForm)
	{
		UpdateSuggestedOrderEvent updateRequestEvent =
			(UpdateSuggestedOrderEvent) EventFactory.getInstance(
				SuggestedOrderControllerServiceNames.UPDATESUGGESTEDORDER);

		updateRequestEvent.setDescription(sugOrderForm.getOrderDescription());
		updateRequestEvent.setIncludedConditionsInd(sugOrderForm.getStandardId());

		if (!sugOrderForm.getAction().equals(UIConstants.COPY))
		{
			updateRequestEvent.setSuggestedOrderId(sugOrderForm.getOrderId());
		}

		updateRequestEvent.setSuggestedOrderName(sugOrderForm.getOrderName());

		updateRequestEvent = this.addChildRequests(updateRequestEvent, sugOrderForm);

		return updateRequestEvent;
	}

	/**
	 * @param updateRequestEvent
	 * @param sugOrderForm
	 * @return
	 */
	private UpdateSuggestedOrderEvent addChildRequests(
		UpdateSuggestedOrderEvent parentEvent,
		SuggestedOrderForm sugOrderForm)
	{
		Collection offenses = MessageUtil.processEmptyCollection(sugOrderForm.getOffenseSelectedList());
		parentEvent = this.processOffenses(parentEvent, offenses);
		sugOrderForm.setOffenseSelectedList(offenses);

		Collection courts = MessageUtil.processEmptyCollection(sugOrderForm.getSelectedCourts());
		parentEvent = this.processCourts(parentEvent, courts);

		Collection conditions = MessageUtil.processEmptyCollection(sugOrderForm.getConditionSelectedList());
		parentEvent = this.processConditions(parentEvent, conditions);

		return parentEvent;
	}

	/**
	 * @param parentEvent
	 * @param collection
	 * @return
	 */
	private UpdateSuggestedOrderEvent processConditions(UpdateSuggestedOrderEvent parentEvent, Collection conditions)
	{
		SuggestedOrderConditionResponseEvent ocre = null;
		SuggestedOrderConditionRequestEvent conditionRequestEvent = null;
		Iterator iter = conditions.iterator();
		while (iter.hasNext())
		{
			ocre = (SuggestedOrderConditionResponseEvent) iter.next();
			conditionRequestEvent = new SuggestedOrderConditionRequestEvent();
			conditionRequestEvent.setConditionId(ocre.getConditionId());
			conditionRequestEvent.setSeqNum(ocre.getSeqNum());
			parentEvent.addRequest(conditionRequestEvent);
		}
		return parentEvent;
	}

	/**
	 * @param parentEvent
	 * @param courtBeans
	 * @return
	 */
	private UpdateSuggestedOrderEvent processCourts(UpdateSuggestedOrderEvent parentEvent, Collection courtBeans)
	{
		CourtBean courtBean = null;
		Collection courts = null;
		CourtResponseEvent cre = null;
		SuggestedOrderCourtRequestEvent courtRequestEvent = null;

		Iterator iter = courtBeans.iterator();
		while (iter.hasNext())
		{
			courtBean = (CourtBean) iter.next();
			courts = courtBean.getCourts();
			if (courts != null)
			{
				Iterator iterator = courts.iterator();
				while (iterator.hasNext())
				{
					cre = (CourtResponseEvent) iterator.next();
					courtRequestEvent = new SuggestedOrderCourtRequestEvent();
					courtRequestEvent.setCourtId(cre.getCourtId());
					parentEvent.addRequest(courtRequestEvent);
				}

			}

		}
		return parentEvent;
	}

	/**
	 * @param parentEvent
	 * @param offenses
	 * @return
	 */
	private UpdateSuggestedOrderEvent processOffenses(UpdateSuggestedOrderEvent parentEvent, Collection offenses)
	{
		OffenseCodeResponseEvent ocre = null;
		SuggestedOrderOffenseRequestEvent offenseRequestEvent = null;
		Iterator iter = offenses.iterator();
		while (iter.hasNext())
		{
			ocre = (OffenseCodeResponseEvent) iter.next();
			offenseRequestEvent = new SuggestedOrderOffenseRequestEvent();
			offenseRequestEvent.setOffenseId(ocre.getOffenseCodeId());
			parentEvent.addRequest(offenseRequestEvent);
		}
		return parentEvent;
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
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
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
