//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderSummaryActionAction.java

package ui.supervision.supervisionorder.action;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionorder.UpdateSupervisionOrderEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.supervisionorder.reply.OrderCreateErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SupervisionConstants;
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

public class DisplaySupervisionOrderSummaryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 438F24090083
	 */
	public DisplaySupervisionOrderSummaryAction()
	{

	}
	/**
	* @see LookupDispatchAction#getKeyMethodMap()
	* @return Map
	*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.saveContinue", "saveContinue");
		keyMap.put("button.addRemoveConditions", "addRemoveConditions");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward saveContinue(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		//long timeStart = System.currentTimeMillis();

		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		sof.setSecondaryAction(""); // used to control back button on preceding pages
		String action = sof.getAction();

		UpdateSupervisionOrderEvent supervisionOrder = UISupervisionOrderHelper.updateSupervisionOrder(sof);

		//ER 53327 - PASO change from UAT - remove details validation
		// validate if value has been entered for all the variable variables
		/*String varElemMissingName=UISupervisionOrderHelper.validateConditionValues(sof.getConditionSelectedList(),false);
		 if (varElemMissingName!=null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.conditionValueMissing",varElemMissingName));
			saveErrors(aRequest, errors);

			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.VALIDATE_ORDER_DETAILS_FAILURE, UIUtil.getCurrentUserAgencyID()));
		}*/

		if (action.equals(UIConstants.CREATE))
		{
			CaseOrderResponseEvent orderRespEvent = postUpdateEvent(supervisionOrder, aRequest);
			if(orderRespEvent != null){
				sof.setOrderStatusId(orderRespEvent.getOrderStatusId());
				sof.setStatusChangeDate(orderRespEvent.getStatusChangeDate()); 
				sof.setOrderId(orderRespEvent.getOrderId());
				sof.setOrderTitleId(orderRespEvent.getOrderTitleId()); //Added by Kiran for printing.
				updateLikeConditions(sof, orderRespEvent);
				sof.setCompareToPrevousVersion("added");
				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
			}else{
			    return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
			}
		}
		else
			if (action.equals(UIConstants.UPDATE))
			{
				// check if update is being done to create a new version
				if (sof.isVersionTypeChangeAllowed())
				{
					supervisionOrder.setVersionType(sof.getVersionTypeId());
					supervisionOrder.setNewVersion(true);
				} else {
				    //DAG - Added because versionType was disappearing on updates.
					supervisionOrder.setVersionType(sof.getVersionTypeId());
					supervisionOrder.setNewVersion(false);
				}
				CaseOrderResponseEvent orderRespEvent = postUpdateEvent(supervisionOrder, aRequest);
				if(orderRespEvent != null){
					//set orderId in the form as we might have created a new version as a result of save/Continue
					sof.setOrderId(orderRespEvent.getOrderId());
					sof.setOrderTitleId(orderRespEvent.getOrderTitleId()); //Added by Kiran for printing.
	
				//	sof.setFineAmountTotal(Double.toString(orderRespEvent.getFineAmountTotal()));
					sof.setOrderStatusId(orderRespEvent.getOrderStatusId());
					sof.setStatusChangeDate(orderRespEvent.getStatusChangeDate()); 
					sof.setLikeConditionInd(false);
					updateLikeConditions(sof, orderRespEvent);
					sof.setVersionNum(orderRespEvent.getVersionNum());
					sof.setOrderFileDate(orderRespEvent.getOrderFileDate());
					sof.updateReferenceVariableMap();
					forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.UPDATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
				}else{
				    return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
				}
			}
			else
			{
				sof.clear();
				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
			}
		//long timeEnd = System.currentTimeMillis();
		//System.out.println("Total Time(milli seconds) for Supervision Order update : " + (timeEnd - timeStart));
		//sof.updateReferenceVariableMap();
		return forward;
	}

	/**
	 * @param sof
	 * @param orderRespEvent
	 */
	private void updateLikeConditions(SupervisionOrderForm sof, CaseOrderResponseEvent orderRespEvent)
	{
		Collection likeCondIds = orderRespEvent.getLikeConditionIds();
		for (Iterator iter = sof.getConditionSelectedList().iterator(); iter.hasNext();)
		{
			ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent) iter.next();			
			// only like condition ids are returned from pd
			if (likeCondIds.contains(cdre.getConditionId()))
			{
				cdre.setLikeConditionInd(true);
				// set indicator in the form to display "process Impacted Orders" button.
				sof.setLikeConditionInd(true);
			}
			else
			{
				cdre.setLikeConditionInd(false);
				sof.setLikeConditionInd(false);
			}
		}
	}

	/**
	 * @param supervisionOrder
	 * @return
	 */
	private CaseOrderResponseEvent postUpdateEvent(UpdateSupervisionOrderEvent supervisionOrder, HttpServletRequest aRequest)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(supervisionOrder);

		CompositeResponse compositeResponse = UISupervisionOrderHelper.getCompositeResponse(dispatch);
		MessageUtil.processReturnException(compositeResponse);

		Collection orderCreatedAlreadyErrs = MessageUtil.compositeToCollection(compositeResponse, OrderCreateErrorResponseEvent.class);
		if(orderCreatedAlreadyErrs!=null && orderCreatedAlreadyErrs.size()>0){
			sendToErrorPage(aRequest,"error.supOrderAlreadyExists");
			return null;
		}
		Collection searchResults =
			MessageUtil.compositeToCollection(compositeResponse, CaseOrderResponseEvent.class);
		MessageUtil.processEmptyCollection(searchResults);
		Iterator iter = searchResults.iterator(); // only ome record
		CaseOrderResponseEvent orderRespEvent = null;
		if (iter.hasNext())
		{
			orderRespEvent = (CaseOrderResponseEvent) iter.next();
		}

		return orderRespEvent;

	}

	public ActionForward addRemoveConditions(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		sof.setWorkflowFrom(null);
		sof.setConditionName("");
		sof.setConditionLiteral("");
		sof.setGroup1Id("");
		sof.setGroup2Id("");
		sof.setGroup3Id("");
		//sof.setStandardSearchCriteria("false");  //false=Non-Standard  true=Standard ""=All
		sof.setStandardSearchCriteria("ALL");  //false=Non-Standard  true=Standard "ALL"=All
		UpdateSupervisionOrderEvent supervisionOrder = UISupervisionOrderHelper.updateSupervisionOrder(sof);

		String action = sof.getAction();
		
		if (action.equals(UIConstants.CREATE))
		{
			CaseOrderResponseEvent orderRespEvent = postUpdateEvent(supervisionOrder, aRequest);
			if(orderRespEvent != null){
				sof.setOrderStatusId(orderRespEvent.getOrderStatusId());
				sof.setStatusChangeDate(orderRespEvent.getStatusChangeDate()); 
				updateLikeConditions(sof, orderRespEvent);
				sof.setCompareToPrevousVersion("added");
				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONDITION_SUCCESS, UIUtil.getCurrentUserAgencyID()));
			}else{
			    return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
			}
		}
		else
			if (action.equals(UIConstants.UPDATE))
			{
				// check if update is being done to create a new version
				if (sof.isVersionTypeChangeAllowed())
				{
					supervisionOrder.setVersionType(sof.getVersionTypeId());
					supervisionOrder.setNewVersion(true);
				} else {
				    //DAG - Added because versionType was disappearing on updates.
				    supervisionOrder.setVersionType(sof.getVersionTypeId());
				    supervisionOrder.setNewVersion(false);
				}
				supervisionOrder.setAddRemoveCondition(true);
				CaseOrderResponseEvent orderRespEvent = postUpdateEvent(supervisionOrder, aRequest);
				sof.updateReferenceVariableMap();
				//set orderId in the form as we might have created a new version as a result of save/Continue
				if(orderRespEvent != null){
					sof.setOrderId(orderRespEvent.getOrderId());
					sof.setOrderStatusId(orderRespEvent.getOrderStatusId());
					sof.setStatusChangeDate(orderRespEvent.getStatusChangeDate()); 
					sof.setOrderTitleId(orderRespEvent.getOrderTitleId()); //01/29/07 dag					
					updateLikeConditions(sof, orderRespEvent);
					sof.setVersionNum(orderRespEvent.getVersionNum());
					sof.setOrderFileDate(orderRespEvent.getOrderFileDate());
					forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONDITION_SUCCESS, UIUtil.getCurrentUserAgencyID()));
				}else{
				    return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
				}
			}
			else
			{
				sof.clear();
				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
			}

		//forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONDITION_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		sof.updateReferenceVariableMap();
		return forward;
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm; 
		sof.setWorkflowFrom(SupervisionConstants.PASO_WORKFLOWFROM);
		if(sof.getSecondaryAction().equalsIgnoreCase(UIConstants.RESEQUENCE_SUCCESS)){ // used to control back button on preceding pages)
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.RESEQUENCE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		}
		else
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