//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderSelectSugOrderAction.java

package ui.supervision.supervisionorder.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.suggestedorder.GetSuggestedOrdersForCourtEvent;
import messaging.suggestedorder.reply.SuggestedOrderResponseEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionorder.UpdateSupervisionOrderEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.supervisionorder.reply.OrderCreateErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.SuggestedOrderControllerServiceNames;
import naming.SupervisionConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.common.form.OffenseSearchForm;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.supervisionorder.SupervisionOrderButtonHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author dgibler
 *  
 */
public class DisplaySupervisionOrderSelectSugOrderAction extends
		JIMSBaseAction {

	/**
	 * @roseuid 438F23F9012F
	 */
	public DisplaySupervisionOrderSelectSugOrderAction() {

	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.saveContinue", "next");
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
//		keyMap.put("button.validate", "validate");
		keyMap.put("prompt.findOffenseCode", "findOffenseCode");
		keyMap.put("button.addRemoveConditions", "addRemoveConditions");
		keyMap.put("button.calculate", "calculateEndDate");
	}

	/**
	 * Determines type of case based on court category or in the cases of
	 * out-of-county cases, court number.
	 * 
	 * @param theForm
	 * @return
	 */
	/* private String getCaseType(String courtCtg, String courtDiv, String courtNum) {
		String caseType = SupervisionConstants.MISDEMEANOR;
		if (courtCtg.equals(SupervisionConstants.OUT_OF_COUNTY_COURT)) {
			if (courtNum.substring(2).equals(SupervisionConstants.FELONY)) {
				caseType = SupervisionConstants.BOTH;
			} else {
				caseType = SupervisionConstants.MISDEMEANOR;
			}
		} else if (courtDiv != null && !courtDiv.equals("")) {
			if (courtDiv.equals("003")) {
				caseType = SupervisionConstants.BOTH;
			}
		}
		return caseType;
	} */

	/**
	 * This opens a dialog to allow the user to search for an Offense by
	 * multiple criteria.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return the appropriate ActionForward
	 */
	public ActionForward findOffenseCode(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		//SupervisionOrderForm myForm = (SupervisionOrderForm) aForm;
		OffenseSearchForm searchOffenseForm = (OffenseSearchForm) UICommonSupervisionHelper
				.getOffenseSearchForm(aRequest, true);
		// make sure the offense search criteria is initialized
		searchOffenseForm.clearSearchFields();

		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FIND_OFFENSE_SUCCESS, UIUtil.getCurrentUserAgencyID()));

		return forward;
	}

	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
//		long timeStart = System.currentTimeMillis();

		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		//10/29/09 No longer entering offense on order presentation. Using offense on case.
		/* if(UIUtil.getCurrentUserAgencyID().equalsIgnoreCase(UIConstants.CSC)){
			String message = UICommonSupervisionHelper.validateOffense(sof.getOffenseId(), sof.getCourtId(),true);
			if (message != null) 
			{
				sendToErrorPage(aRequest, message);
				if(sof.getIsHistoricalOrder() || sof.getIsMigratedOrder()){
					return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
				}
				else
					return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
			}
		}*/
		String action = sof.getAction();
		if (sof.getGroups() == null || sof.getGroups().size() == 0)
		{
			Collection groups = UISupervisionOptionHelper.fetchGroups(UIUtil.getCurrentUserAgencyID());
			sof.setGroups(groups);
		}

		if (action.equals(UIConstants.CREATE)) 
		{
			// get suggested order to display
			sof.setSuggestedOrderList(getSuggestedOrders( sof.getCurrentCourtId(), sof.getOffenseId() ));
			sof.setPlea("");
			if (sof.getPleaId() != null && !sof.getPleaId().equals("")){
			   sof.setPlea(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLEA, sof.getPleaId()));
			} 
			if (!"".equals(sof.getSpecialCourtCd()) ){
				sof.setSpecialCourt(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SPECIAL_COURT, sof.getSpecialCourtCd()));
			} else {
				sof.setSpecialCourt("");
			}
			if(sof.getIsPretrialInterventionOrder())
				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
			else
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		} 
		else if (action.equals(UIConstants.UPDATE)) 
		{
			//if the suggested order id is null or "" this is an incomplete order that only has 
			//prepare to file and defendant signature information (no conditions have been selected).
			//SuggestedOrderId may also be blank if the suggested order has been deleted via ASO.
//			if((sof.getSuggestedOrderId()!=null && !sof.getSuggestedOrderId().equals(""))
//					&& (sof.getOrderStatusId().equals(PDCodeTableConstants.STATUS_INCOMPLETE_ID)
//							|| sof.getOrderStatusId().equals(PDCodeTableConstants.STATUS_ACTIVE_ID))) 
			if (!"".equals(sof.getSpecialCourtCd()) ){
				sof.setSpecialCourt(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SPECIAL_COURT, sof.getSpecialCourtCd()));
			}else {
				sof.setSpecialCourt("");
			}
			if(  (sof.getSuggestedOrderId() != null && !sof.getSuggestedOrderId().equals("")) ||		
				((sof.getSuggestedOrderId() == null || sof.getSuggestedOrderId().equals("")) &&
				  sof.getConditionSelectedList() != null && sof.getConditionSelectedList().size() > 0) )
			{						
				if (sof.isRefreshRefVarsAllowed()){
					if (!sof.getIsMigratedOrder() 
							&& (sof.getConditionSelectedList() == null || sof.getConditionSelectedList().size()==0)){
						SupervisionOrderForm priorVersion = new SupervisionOrderForm();
						priorVersion.setOrderId(sof.getOrderId());
						UISupervisionOrderHelper.getSupervisionOrderDetails(priorVersion);
						sof.setConditionSelectedList(priorVersion.getConditionSelectedList());
						priorVersion = null;
					}
					//Update supervision order with new reference variable values.
					UpdateSupervisionOrderEvent supervisionOrderEvt = UISupervisionOrderHelper.updateSupervisionOrder(sof); 
					if (sof.isVersionTypeChangeAllowed())
					{
						supervisionOrderEvt.setVersionType(sof.getVersionTypeId());
						supervisionOrderEvt.setNewVersion(true);
						if (sof.getIsMigratedOrder()){
							sof.setSuggestedOrderList(getSuggestedOrders(sof.getCourtId(), sof.getOffenseId()));
							if (sof.getPleaId() != null && !sof.getPleaId().equals(""))
							{
							   sof.setPlea(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLEA, sof.getPleaId()));
							} 
							forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
							return forward;
						} else {
							// do nothing
						}
					} else {
						supervisionOrderEvt.setVersionType(sof.getVersionTypeId());
						supervisionOrderEvt.setNewVersion(false);
					}
					
					
					//Need to be able to determine if this is a new version being created and if so, create a cloned order with
					//conditions from the active order.
		            CaseOrderResponseEvent orderRespEvent = this.postUpdateEvent(supervisionOrderEvt, aRequest);
		            if (orderRespEvent == null){
		            	sendToErrorPage(aRequest, "error.supOrderAlreadyExists");
		            } else {	
						//set orderId in the form as we might have created a new version as a result of save/Continue
						sof.setOrderId(orderRespEvent.getOrderId());
						sof.setOrderTitleId(orderRespEvent.getOrderTitleId()); //Added by Kiran for printing.
						sof.setOrderStatusId(orderRespEvent.getOrderStatusId());
						sof.setStatusChangeDate(orderRespEvent.getStatusChangeDate()); 
						sof.setLikeConditionInd(false);
						updateLikeConditions(sof, orderRespEvent);
						sof.setVersionNum(orderRespEvent.getVersionNum());
						sof.setOrderFileDate(orderRespEvent.getOrderFileDate());
						sof.updateReferenceVariableMap();
						UISupervisionOrderHelper.getSupervisionOrderDetails(sof);
						
						//String casenotes = sof.getCasenotes();
						String summaryNotes = sof.getSummaryOfChanges();
						String versionType = sof.getVersionTypeId();
						if(PDCodeTableConstants.VERSION_TYPE_ID_AMMENDED.equals(versionType) ||
									 (PDCodeTableConstants.VERSION_TYPE_ID_MODIFIED.equals(versionType))){
							
							StringBuffer sb = new StringBuffer();
							sb.append(sof.getCdi()).append(", ").append(sof.getCaseNum()).append(", ").append(summaryNotes);
							sof.setCasenotes(sb.toString());

						}	
					}
				}  // end isRefreshRefVarsAllowed()
				
				
				if(!sof.getIsPretrialInterventionOrder()){
// UI fix for defect #62244 on 10/16/09	
//				    if(PDCodeTableConstants.VERSION_TYPE_ID_AMMENDED.equals(sof.getVersionTypeId()))
//				    {
//				        sof.revertOrderTitleId();
//				    }
				    
					forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.UPDATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
				}
				else{
					forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.UPDATE_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
				}
			}else
			{
				sof.setSuggestedOrderList(getSuggestedOrders(sof.getCourtId(), sof.getOffenseId()));
				sof.setPlea("");
				if (sof.getPleaId() != null && !sof.getPleaId().equals("")){
				   sof.setPlea(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLEA, sof.getPleaId()));
				} 	
				if(sof.getIsPretrialInterventionOrder()) {
					forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.UPDATE_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
				} else {
					forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
				}
			}
		} // end Action Update
		else // action not Create or Update
		{
			sof.clear();
			if(sof.getIsPretrialInterventionOrder())
				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
			else
				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
		}
		
//		long timeEnd = System.currentTimeMillis();
//		System.out.println("Total Time(milli seconds) to display Condition detail page : "	+ (timeEnd - timeStart));
		if(sof.getIsPretrialInterventionOrder()){
			sof.setConditionResultList(new ArrayList());
			UISupervisionOrderHelper.addNewPasoLightConditions(sof);
			sof.setWorkflowFrom(SupervisionConstants.PASO_WORKFLOWFROM);
			
			String summaryNotes = sof.getSummaryOfChanges();
			String versionType = sof.getVersionTypeId();
			if(PDCodeTableConstants.VERSION_TYPE_ID_AMMENDED.equals(versionType) ||
						 (PDCodeTableConstants.VERSION_TYPE_ID_MODIFIED.equals(versionType))){
				
				StringBuffer sb = new StringBuffer();
				sb.append(sof.getCdi()).append(", ").append(sof.getCaseNum()).append(", ").append(summaryNotes);
				sof.setCasenotes(sb.toString());
			}
		}
		return forward;
	}


	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		forward=SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
		return forward;
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
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
			}
		}
	}

	private Collection getSuggestedOrders(String courtId, String offenseId) {

		// get the list of suggested orders
		GetSuggestedOrdersForCourtEvent getSugOrdersEvent = (GetSuggestedOrdersForCourtEvent) EventFactory
				.getInstance(SuggestedOrderControllerServiceNames.GETSUGGESTEDORDERSFORCOURT);
		//		getSugOrdersEvent.setCourtNum(courtNum);
		//		getSugOrdersEvent.setCourtDiv(courtDiv);
		getSugOrdersEvent.setCourtId(courtId);
		getSugOrdersEvent.setOffenseId(offenseId);
		// post the event
		IDispatch dispatch = EventManager
				.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(getSugOrdersEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch
				.getReply();
		Map responseMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(responseMap);

		Collection suggOrders = MessageUtil.compositeToCollection(
				(CompositeResponse) dispatch.getReply(),
				SuggestedOrderResponseEvent.class);
		MessageUtil.processEmptyCollection(suggOrders);

		return suggOrders;
	}
	 private CaseOrderResponseEvent postUpdateEvent(UpdateSupervisionOrderEvent supervisionOrder,
	            HttpServletRequest aRequest)
	    {
     		CaseOrderResponseEvent orderRespEvent = null;   
     		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	        dispatch.postEvent(supervisionOrder);

	        CompositeResponse compositeResponse = UISupervisionOrderHelper.getCompositeResponse(dispatch);
	        MessageUtil.processReturnException(compositeResponse);

	        Collection orderCreatedAlreadyErrs = MessageUtil.compositeToCollection(compositeResponse,
	                OrderCreateErrorResponseEvent.class);
	        if (orderCreatedAlreadyErrs != null && orderCreatedAlreadyErrs.size() > 0)
	        {
	           // sendToErrorPage(aRequest, "error.supOrderAlreadyExists");
	            //return null;
	        } else {
	        	Collection searchResults = MessageUtil.compositeToCollection(compositeResponse, CaseOrderResponseEvent.class);
	        	MessageUtil.processEmptyCollection(searchResults);
	        	Iterator iter = searchResults.iterator(); // only ome record

	        	if (iter.hasNext())
	        	{
	        		orderRespEvent = (CaseOrderResponseEvent) iter.next();
	        	}
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
		sof.setWorkflowFrom(SupervisionConstants.PASO_WORKFLOWFROM);
		sof.setStandardSearchCriteria("ALL");  //false=Non-Standard  true=Standard "ALL"=All
		//10/29/09 No longer entering offense on order presentation. Using offense on case.
		/* if(UIUtil.getCurrentUserAgencyID().equalsIgnoreCase(UIConstants.CSC)){
			String message = UICommonSupervisionHelper.validateOffense(sof.getOffenseId(), sof.getCourtId(),true);
			if (message != null) 
			{
				sendToErrorPage(aRequest, message);
				if(sof.getIsHistoricalOrder()){
					return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
				}
				else
					return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
			}
		}*/
//		String action = sof.getAction();
		if (sof.getGroups() == null || sof.getGroups().size() == 0)
		{
			Collection groups = UISupervisionOptionHelper.fetchGroups(UIUtil.getCurrentUserAgencyID());
			sof.setGroups(groups);
		}

//		if (sof.isRefreshRefVarsAllowed()){
//			if (!sof.getIsMigratedOrder()){
				SupervisionOrderForm priorVersion = new SupervisionOrderForm();
				priorVersion.setOrderId(sof.getOrderId());
				UISupervisionOrderHelper.getSupervisionOrderDetails(priorVersion);
				sof.setConditionSelectedList(priorVersion.getConditionSelectedList());
				priorVersion = null;
//			}
				//Update supervision order with new reference variable values.
			UpdateSupervisionOrderEvent supervisionOrderEvt = UISupervisionOrderHelper.updateSupervisionOrder(sof); 
			if (sof.isVersionTypeChangeAllowed())
			{
				supervisionOrderEvt.setVersionType(sof.getVersionTypeId());
				supervisionOrderEvt.setNewVersion(true);
//				if (sof.getIsMigratedOrder()){
//					sof.setSuggestedOrderList(getSuggestedOrders(sof.getCourtId(), sof.getOffenseId()));
//					forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
//					return forward;
//				} 
			} else {
				if (sof.getOrderStatusId() == null || sof.getOrderStatusId().equals("")){
					supervisionOrderEvt.setOrderStatusId(PDCodeTableConstants.STATUS_INCOMPLETE_ID);
				} else {
					supervisionOrderEvt.setOrderStatusId(sof.getOrderStatusId());
				}
				supervisionOrderEvt.setAddRemoveCondition(true);
				supervisionOrderEvt.setVersionType(sof.getVersionTypeId());
				supervisionOrderEvt.setNewVersion(false);
			}
			if (sof.getVersionTypeId().equals(SupervisionConstants.AMMENDED)
					|| sof.getVersionTypeId().equals(SupervisionConstants.MODIFIED)){
				supervisionOrderEvt.setSummaryChanges(sof.getSummaryOfChanges());
			}
			//Need to be able to determine if this is a new version being created and if so, create a cloned order with
			//conditions from the active order.
			CaseOrderResponseEvent orderRespEvent = this.postUpdateEvent(supervisionOrderEvt, aRequest);
            if (orderRespEvent == null){
            	sendToErrorPage(aRequest, "error.supOrderAlreadyExists");
            } else {			
				//set orderId in the form as we might have created a new version as a result of save/Continue
				sof.setOrderId(orderRespEvent.getOrderId());
				sof.setOrderTitleId(orderRespEvent.getOrderTitleId()); //Added by Kiran for printing.
				sof.setOrderStatusId(orderRespEvent.getOrderStatusId());
				sof.setStatusChangeDate(orderRespEvent.getStatusChangeDate()); 
				sof.setLikeConditionInd(false);
				updateLikeConditions(sof, orderRespEvent);
				sof.setVersionNum(orderRespEvent.getVersionNum());
				sof.setOrderFileDate(orderRespEvent.getOrderFileDate());
				sof.updateReferenceVariableMap();
				UISupervisionOrderHelper.getSupervisionOrderDetails(sof);
				
				//String casenotes = sof.getCasenotes();
				String summaryNotes = sof.getSummaryOfChanges();
				String versionType = sof.getVersionTypeId();
				if(PDCodeTableConstants.VERSION_TYPE_ID_AMMENDED.equals(versionType) ||
							 (PDCodeTableConstants.VERSION_TYPE_ID_MODIFIED.equals(versionType))){
					
					StringBuffer sb = new StringBuffer();
					sb.append(sof.getCdi()).append(", ").append(sof.getCaseNum()).append(", ").append(summaryNotes);
					sof.setCasenotes(sb.toString());
					
				}	
			}	
	//			if(!sof.getIsHistoricalOrder()){
//
//			    if(PDCodeTableConstants.VERSION_TYPE_ID_AMMENDED.equals(sof.getVersionTypeId()))
//			    {
//			        sof.revertOrderTitleId();
//			    }
//			}
//		} 
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONDITION_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		if(sof.getIsPretrialInterventionOrder()){
			UISupervisionOrderHelper.addNewPasoLightConditions(sof);
			
			String summaryNotes = sof.getSummaryOfChanges();
			String versionType = sof.getVersionTypeId();
			if(PDCodeTableConstants.VERSION_TYPE_ID_AMMENDED.equals(versionType) ||
						 (PDCodeTableConstants.VERSION_TYPE_ID_MODIFIED.equals(versionType))){
				
				StringBuffer sb = new StringBuffer();
				sb.append(sof.getCdi()).append(", ").append(sof.getCaseNum()).append(", ").append(summaryNotes);
				sof.setCasenotes(sb.toString());
				
			}
		}
		return forward;
	}
	/**
	 * This validates the Offense Code that was entered by the user.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return the appropriate ActionForward
	 */
/**	public ActionForward calculateEndDate(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SupervisionOrderForm myForm = (SupervisionOrderForm) aForm;
		
		String forwareStr = UIConstants.CALCULATE;
		if(myForm.getIsHistoricalOrder()){
			forwareStr += UIConstants.LIGHT;
		}
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(forwareStr, UIUtil.getCurrentUserAgencyID()));
		String [] dateElem = null;
		dateElem = myForm.getCaseSupervisionBeginDateAsString().split("/");
		//GregorianCalendar begDate = new GregorianCalendar(Integer.parseInt(dateElem[2]), Integer.parseInt(dateElem[0]), Integer.parseInt(dateElem[1]) );
		
		Calendar begDate = new GregorianCalendar(Integer.parseInt(dateElem[2]), Integer.parseInt(dateElem[0]) -1, Integer.parseInt(dateElem[1]));

		begDate.add(Calendar.YEAR, Integer.parseInt(myForm.getSupervisionLengthYears()));
		begDate.add(Calendar.MONTH, Integer.parseInt(myForm.getSupervisionLengthMonths()));
		begDate.add(Calendar.DATE, Integer.parseInt(myForm.getSupervisionLengthDays())-1);  

		Date endDate = begDate.getTime();
		String endDateStr = DateUtil.dateToString(endDate,DateUtil.DATE_FMT_1);
		myForm.setCaseSupervisionEndDateAsString(endDateStr);
		return forward;
	}	 */
} 