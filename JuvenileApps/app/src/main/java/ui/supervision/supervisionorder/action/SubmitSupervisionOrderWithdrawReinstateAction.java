/*
 * Created on Dec 27, 2005
 */
package ui.supervision.supervisionorder.action;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionorder.ReinstateSupervisionOrderEvent;
import messaging.supervisionorder.WithdrawSupervisionOrderEvent;
import messaging.supervisionorder.reply.ReinstateSupervisionOrderErrorEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.SupervisionOrderButtonHelper;
import ui.supervision.supervisionorder.UISupervisionOrderPrintHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author hrodriguez
 */
public class SubmitSupervisionOrderWithdrawReinstateAction extends LookupDispatchAction
{

	/**
	 * 
	 */
	public SubmitSupervisionOrderWithdrawReinstateAction()
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
		keyMap.put("button.backToCaseOrderSearchResults", "backToCaseOrderSearchResults");
		keyMap.put("button.backToSearch", "backToSearch");
		keyMap.put("button.mainPage", "mainPage");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.printOrder", "printOrder");
		return keyMap;
	}

	public ActionForward finish(
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
			sof.setAction(UIConstants.CONFIRM_WITHDRAW);
			this.withdrawOrder(sof);
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONFIRM_WITHDRAW_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		}
		else
			if (action.equals(UIConstants.REINSTATE))
			{
				sof.setAction(UIConstants.CONFIRM_REINSTATE);
				ReinstateSupervisionOrderErrorEvent errorEvent = this.reinstateOrder(sof);
				if(errorEvent == null){
					sof.setOrderStatusId(PDCodeTableConstants.STATUS_ACTIVE_ID);
					sof.setStatusChangeDate(new Date());  
					sof.setOrderFileDate(sof.getStatusChangeDate());//OrderFileDate is status change date in header.
					forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONFIRM_REINSTATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
				}else{ // very rare condition as we are doing this validation in ui before this operation starts
		            sof.setAction(UIConstants.REINSTATE);
					this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errorEvent.getErrorMessage());
					forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
				}
			}
			else
			{
				sof.clear();
				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
			}
		return forward;
	}
	
	

	/**
	 * @param sof
	 */
	private void withdrawOrder(SupervisionOrderForm sof)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		WithdrawSupervisionOrderEvent requestEvent =
			(WithdrawSupervisionOrderEvent) EventFactory.getInstance(
				SupervisionOrderControllerServiceNames.WITHDRAWSUPERVISIONORDER);

		requestEvent.setNotes(sof.getCasenotes());
		requestEvent.setWithdrawDate(sof.getStatusChangeDate());
		requestEvent.setWithdrawReasonId(sof.getReasonId());
		requestEvent.setSupervisionOrderId(sof.getOrderId());

		dispatch.postEvent(requestEvent);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(response);
		MessageUtil.processReturnException(dataMap);

		
	}
	/**
	 * @param sof
	 */
	private ReinstateSupervisionOrderErrorEvent reinstateOrder(SupervisionOrderForm sof)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		ReinstateSupervisionOrderEvent requestEvent =
			(ReinstateSupervisionOrderEvent) EventFactory.getInstance(
				SupervisionOrderControllerServiceNames.REINSTATESUPERVISIONORDER);

		requestEvent.setNotes(sof.getCasenotes());
		requestEvent.setReinstatementDate(sof.getStatusChangeDate());
		requestEvent.setReinstatementReason(sof.getReasonId());
		requestEvent.setSupervisionOrderId(sof.getOrderId());

		dispatch.postEvent(requestEvent);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(response);
		MessageUtil.processReturnException(dataMap);

		// check for validation
		ReinstateSupervisionOrderErrorEvent errorEvent = null;
		Collection searchResults = MessageUtil.compositeToCollection(response, ReinstateSupervisionOrderErrorEvent.class);
		searchResults = MessageUtil.processEmptyCollection(searchResults);
		Iterator iter = searchResults.iterator();
		if (iter.hasNext())
		{
			errorEvent = (ReinstateSupervisionOrderErrorEvent)iter.next();
		}
		return errorEvent;
	}
	public ActionForward backToCaseOrderSearchResults(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
        //setting this for print
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        sof.setPrintAction("");
        //End 
        
		ActionForward forward = SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
		return forward;
	}

	public ActionForward mainPage(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();

		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.MAIN_MENU, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	public ActionForward backToSearch(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
			{
				ActionForward forward = new ActionForward();

				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK_TO_SEARCH, UIUtil.getCurrentUserAgencyID()));
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

	public ActionForward printOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception {
		 UISupervisionOrderPrintHelper helper = new UISupervisionOrderPrintHelper();
		 helper.processReport(aForm, aRequest, aResponse);
		 ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONFIRM_REINSTATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		 return forward;	        
	}
	
	protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param) {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey,param));
	    saveErrors(aRequest, errors);
	}

}
