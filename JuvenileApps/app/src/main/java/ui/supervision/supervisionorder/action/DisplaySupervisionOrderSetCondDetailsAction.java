//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderSetCondDetailsAction.java

package ui.supervision.supervisionorder.action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervision.reply.SupervisionStaffResponseEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionorder.CreateSupervisionOrderEvent;
import messaging.supervisionorder.SupervisionOrderConditionEvent;
import messaging.supervisionorder.UpdateSupervisionOrderEvent;
import messaging.supervisionorder.UpdateSupervisionOrderWithSuggestedOrderConditionsEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.supervisionorder.reply.JudgeResponseEvent;
import messaging.supervisionorder.reply.OrderCreateErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.SupervisionOrderControllerServiceNames;
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

/**
 * @author  dgibler
 *
*/
public class DisplaySupervisionOrderSetCondDetailsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 438F23FF0035
	 */
	public DisplaySupervisionOrderSetCondDetailsAction()
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
		keyMap.put("button.customizeSuggestedOrder", "customizeSuggestedOrder");
		keyMap.put("button.printSignature", "printSignature");
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
	public ActionForward saveContinue(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		//long timeStart = System.currentTimeMillis();

		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		//if (this.isUpdateAfterPrintSignature(sof)){
		if (this.isUpdate(sof)){
			//DAG 11/02/09 - Clear out condition lists before applying suggested order conditions.
			sof.setConditionResultList(new ArrayList());
			sof.setConditionSelectedList(new ArrayList());
			UpdateSupervisionOrderEvent se = UISupervisionOrderHelper.updateSupervisionOrder(sof);
			UpdateSupervisionOrderWithSuggestedOrderConditionsEvent updateEvent = this.getUpdateWithSugOrderEvent(se);
			updateEvent.setAgencyId(sof.getAgencyId());
			updateEvent.setOrderCourtId(sof.getCourtId());
			updateEvent.setSuggestedOrderId(sof.getSuggestedOrderId());
			updateEvent.setVariableElementReferenceMap(sof.getReferenceVariableMap());
			this.updateSupervisionOrder(updateEvent, sof);

		} else {
			if(!this.createSupervisionOrder(sof,aRequest)){
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
			}
		}

		//RRY added Summary of changes to form for Amended of modified migrated orders
		String summaryNotes = sof.getSummaryOfChanges();
		String versionType = sof.getVersionTypeId();
		if(PDCodeTableConstants.VERSION_TYPE_ID_AMMENDED.equals(versionType) ||
					 (PDCodeTableConstants.VERSION_TYPE_ID_MODIFIED.equals(versionType))){

			StringBuffer sb = new StringBuffer();
			sb.append(sof.getCdi()).append(", ").append(sof.getCaseNum()).append(", ").append(summaryNotes);
			sof.setCasenotes(sb.toString());

		}

		//Order status will be blank if creating an order on a new case.
		//Order status will be active if creating a new order version which would have been created in
		//DisplaySupervisionOrderSetCondDetailsAction.
		if (sof.getOrderStatusId() == null
				|| sof.getOrderStatusId().equals(PDConstants.BLANK)
				|| sof.getOrderStatusId().equals(PDCodeTableConstants.STATUS_ACTIVE_ID)){
			sof.setOrderStatusId(PDCodeTableConstants.STATUS_INCOMPLETE_ID);
			sof.setStatusChangeDate(null); 
		}//else leave the status to whatever it was set to previously.

		sof.updateReferenceVariableMap();

		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		//long timeEnd = System.currentTimeMillis();
		//System.out.println("Total Time(milli seconds) to display Condition detail page : " + (timeEnd - timeStart));
		return forward;
	}

	private UpdateSupervisionOrderWithSuggestedOrderConditionsEvent getUpdateWithSugOrderEvent(UpdateSupervisionOrderEvent superEvent) {
		UpdateSupervisionOrderWithSuggestedOrderConditionsEvent updateEvent = (UpdateSupervisionOrderWithSuggestedOrderConditionsEvent) EventFactory
        .getInstance(SupervisionOrderControllerServiceNames.UPDATESUPERVISIONORDERWITHSUGGESTEDORDERCONDITIONS);

		updateEvent.setComments(superEvent.getComments());
		updateEvent.setIsHistoricalOrder(superEvent.getIsHistoricalOrder());
		updateEvent.setVersionNum(superEvent.getVersionNum());
		updateEvent.setMigratedOrder(superEvent.isMigratedOrder());
		updateEvent.setOrderId(superEvent.getOrderId());
		updateEvent.setModificationReason(superEvent.getModificationReason());
		updateEvent.setSummaryChanges(superEvent.getSummaryChanges());
		updateEvent.setOrderTitle(superEvent.getOrderTitle());
		updateEvent.setPlea(superEvent.getPlea());
		updateEvent.setPrintedName(superEvent.getPrintedName());
		updateEvent.setPrintedOffenseDesc(superEvent.getPrintedOffenseDesc());
		updateEvent.setFineAmount(superEvent.getFineAmount());
		updateEvent.setLimitedSupervisionPeriod(superEvent.isLimitedSupervisionPeriod());
		updateEvent.setLimitedSupervisionBeginDate(superEvent.getLimitedSupervisionBeginDate());
		updateEvent.setLimitedSupervisionEndDate(updateEvent.getLimitedSupervisionEndDate());
		//updateEvent.setOffenseId(superEvent.getOffenseId());
		updateEvent.setCaseSupervisionBeginDate(superEvent.getCaseSupervisionBeginDate());
		updateEvent.setCaseSupervisionEndDate(superEvent.getCaseSupervisionEndDate());
		updateEvent.setConfinementLengthDays(superEvent.getConfinementLengthDays());
		updateEvent.setConfinementLengthMonths(superEvent.getConfinementLengthMonths());
		updateEvent.setConfinementLengthYears(superEvent.getConfinementLengthYears());
		updateEvent.setDispositionTypeId(superEvent.getDispositionTypeId());
		updateEvent.setSupervisionLengthDays(superEvent.getSupervisionLengthDays());
		updateEvent.setSupervisionLengthMonths(superEvent.getSupervisionLengthMonths());
		updateEvent.setSupervisionLengthYears(superEvent.getSupervisionLengthYears());
		updateEvent.setJuvCourtId(superEvent.getJuvCourtId());
		updateEvent.setJuvSupervisionLengthYears(superEvent.getJuvSupervisionLengthYears());
		updateEvent.setJuvSupervisionLengthMonths(superEvent.getJuvSupervisionLengthMonths());
		updateEvent.setJuvSupervisionLengthDays(superEvent.getJuvSupervisionLengthDays());
		updateEvent.setJuvSupervisionBeginDate(superEvent.getJuvSupervisionBeginDate());
		updateEvent.setSpecialCourtCd(superEvent.getSpecialCourtCd());

		return updateEvent;
	}

	private void updateSupervisionOrder(UpdateSupervisionOrderWithSuggestedOrderConditionsEvent updateEvent,
			SupervisionOrderForm sof) {
		CompositeResponse compositeResponse = MessageUtil.postRequest(updateEvent);
		// get Order
//		Collection searchResults = MessageUtil.compositeToCollection(compositeResponse, CaseOrderResponseEvent.class);
//		MessageUtil.processEmptyCollection(searchResults);
//		Iterator it = searchResults.iterator();
//		if (it.hasNext())
//		{
//			CaseOrderResponseEvent orderRespEvent = (CaseOrderResponseEvent) it.next();
//			sof.setOrderId(orderRespEvent.getOrderId());
//		}

		// get conditions
		Collection conditions = MessageUtil.compositeToCollection(compositeResponse, ConditionDetailResponseEvent.class);
		conditions = MessageUtil.processEmptyCollection(conditions);
		// sort it according to sequenceNum here
		Collections.sort((List)conditions, ConditionDetailResponseEvent.SeqNumComparator);
		// reset the number if there was any inactive condition in ASO
		int count = 1;

		for(Iterator iter = conditions.iterator(); iter.hasNext(); ){
			ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent)iter.next();
			UISupervisionOrderHelper.validateConditionValues(cdre, false);
			cdre.setSequenceNum(count++);
		}
		sof.setConditionSelectedList(conditions);
		if(conditions!=null && conditions.size()>0)
			UISupervisionOrderHelper.setPreviewSample(sof,conditions,true);
	}

	/* private boolean isUpdateAfterPrintSignature(SupervisionOrderForm sof) {
		boolean isUpdate = false;
		if (sof.getOrderId() != null && !sof.getOrderId().equals(PDConstants.BLANK)
				&& sof.getPresentedByLastName() != null && !sof.getPresentedByLastName().equals(PDConstants.BLANK)
				&& (sof.getOrderStatusId().equals(PDCodeTableConstants.STATUS_INCOMPLETE_ID))){
			isUpdate = true;
		}
		return isUpdate;
	}*/
	private boolean isUpdate(SupervisionOrderForm sof) {
		boolean isUpdate = false;
		if (sof.getOrderId() != null && !sof.getOrderId().equals(PDConstants.BLANK)
				&& (sof.getOrderStatusId().equals(PDCodeTableConstants.STATUS_INCOMPLETE_ID))){
			isUpdate = true;
		}
		return isUpdate;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward printSignature(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		sof.setSecondaryAction(sof.getAction());
		//in case the user selects a suggested order, then hits back and selects to print signature
		sof.setSuggestedOrderId("");
		
		//sof.getConditionSelectedList().clear();

		sof.setAction(UIConstants.PRINT_SIGNATURE);

		if (sof.getPresentedByLastName() != null && !sof.getPresentedByLastName().equals(PDConstants.BLANK)){
			UISupervisionOrderHelper.setPresentedByInfo(sof);
		} else {
//			sof.setDefendantSignature("Signed"); // This default is being set with accordance to defect 32000050179 though I disagree with it
			SupervisionStaffResponseEvent ssre = UISupervisionOrderHelper.getSupervisionStaff(sof.getPresentedByList());
			if (ssre != null) {
				sof.setPresentedById(ssre.getSupervisionStaffId());
			}
			JudgeResponseEvent jre = UISupervisionOrderHelper.getJudge(sof.getJudgeSelectList(), sof.getCourtId());
			if (jre != null) {
				sof.setJudgeSelectId(jre.getCourtId());
			}
		}
		sof.setSignedDateAsString("");
        sof.setDefendantSignature("");
        sof.setDefendantSignedDateAsString("");
		return aMapping.findForward(UIConstants.PRINT_SIGNATURE);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward customizeSuggestedOrder(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		//long timeStart = System.currentTimeMillis();

		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		
		//DAG 11/02/09 - clear out condition lists to avoid problems with duplicate conditions
		//if back button is pressed after a suggested order is selected.
		sof.setConditionResultList(new ArrayList());
		sof.setConditionSelectedList(new ArrayList());
		
		//if (this.isUpdateAfterPrintSignature(sof)){
		if (this.isUpdate(sof)){
			UpdateSupervisionOrderEvent se = UISupervisionOrderHelper.updateSupervisionOrder(sof);
			
			UpdateSupervisionOrderWithSuggestedOrderConditionsEvent updateEvent = this.getUpdateWithSugOrderEvent(se);
			updateEvent.setAgencyId(sof.getAgencyId());
			updateEvent.setOrderCourtId(sof.getCourtId());
			updateEvent.setSuggestedOrderId(sof.getSuggestedOrderId());
			updateEvent.setVariableElementReferenceMap(sof.getReferenceVariableMap());
			this.updateSupervisionOrder(updateEvent, sof);

		} else {
			if(!this.createSupervisionOrder(sof,aRequest)){
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
			}
		}
		sof.setOrderStatusId(PDCodeTableConstants.STATUS_INCOMPLETE_ID);
		sof.setStatusChangeDate(null); 
		sof.updateReferenceVariableMap();

		// set default search condition field
		//sof.setStandardSearchCriteria("true");
		sof.setStandardSearchCriteria("ALL");

		sof.setSearchSuperCondPerformed(false);
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CUSTOMIZE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		//long timeEnd = System.currentTimeMillis();
		//System.out.println("Total Time(milli seconds) to display Condition detail page : " + (timeEnd - timeStart));
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
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		String curAction = sof.getAction();
		if (sof.getSecondaryAction() != null && !sof.getSecondaryAction().equals("") ){
			sof.setAction(sof.getSecondaryAction());
			sof.setSecondaryAction("");
		}

		ActionForward forward = new ActionForward();

		if (sof.getIsPretrialInterventionOrder() || sof.getIsMigratedOrder()){
			sof.setShowOrigJudgeInput(false);
			if (sof.getIsMigratedOrder() && curAction.equalsIgnoreCase("create")){
				sof.setShowOrigJudgeInput(true);
				if (!sof.getCdi().equals("003")){
					sof.setShowOrigJudgeInput(false);
				}
			}
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
		}else {
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
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
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	/**
	 * Creates supervision order.
	 * @param sof
	 * returns true if succcessful else returns false
	 */
	private boolean createSupervisionOrder(SupervisionOrderForm sof, HttpServletRequest aRequest)
	{
		// post event to create Order
		CreateSupervisionOrderEvent supervisionOrder = UISupervisionOrderHelper.createSupervisionOrder(sof);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(supervisionOrder);

		CompositeResponse compositeResponse = UISupervisionOrderHelper.getCompositeResponse(dispatch);
		Collection orderCreatedAlreadyErrs = MessageUtil.compositeToCollection(compositeResponse, OrderCreateErrorResponseEvent.class);
		if(orderCreatedAlreadyErrs!=null && orderCreatedAlreadyErrs.size()>0){
			sendToErrorPage(aRequest,"error.supOrderAlreadyExists");
			return false;
		}
		// get Order
		Collection searchResults = MessageUtil.compositeToCollection(compositeResponse, CaseOrderResponseEvent.class);
		MessageUtil.processEmptyCollection(searchResults);
		Iterator it = searchResults.iterator();
		if (it.hasNext())
		{
			CaseOrderResponseEvent orderRespEvent = (CaseOrderResponseEvent) it.next();
			sof.setOrderId(orderRespEvent.getOrderId());
			//sof.setOrderStatusId(PDCodeTableConstants.STATUS_INCOMPLETE_ID);
		}
		//sof.updateReferenceVariableMap();
		// get conditions
		Collection conditions = MessageUtil.compositeToCollection(compositeResponse, ConditionDetailResponseEvent.class);
		conditions = MessageUtil.processEmptyCollection(conditions);
		// sort it according to sequenceNum here
		Collections.sort((List)conditions, ConditionDetailResponseEvent.SeqNumComparator);
		// reset the number if there was any inactive condition in ASO
		int count = 1;

		for(Iterator iter = conditions.iterator(); iter.hasNext(); ){
			ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent)iter.next();
			UISupervisionOrderHelper.validateConditionValues(cdre, false);
			cdre.setSequenceNum(count++);
			SupervisionOrderConditionEvent conditionOrderEvent = 
				UISupervisionOrderHelper.createOrderConditionEvent(cdre, sof.getReferenceVariableMap());
			cdre.setResolvedDescription(conditionOrderEvent.getResolvedDescription());
		}
		sof.setConditionSelectedList(conditions);
		if(conditions!=null && conditions.size()>0)
			UISupervisionOrderHelper.setPreviewSample(sof,conditions,true);
		return true;

	}

	/**
	 * Forward to error page.
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
