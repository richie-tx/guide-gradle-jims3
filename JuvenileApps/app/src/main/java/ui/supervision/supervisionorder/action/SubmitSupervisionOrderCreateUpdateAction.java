//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\SubmitSupervisionOrderCreateUpdateAction.java

package ui.supervision.supervisionorder.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervision.reply.SupervisionStaffResponseEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionorder.GetImpactedOrdersEvent;
import messaging.supervisionorder.UpdateSupervisionOrderStatusEvent;
import messaging.supervisionorder.reply.ActivateSupervisionOrderErrorEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.SupervisionOrderButtonHelper;
import ui.supervision.supervisionorder.SupervisionOrderListHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author dgibler
 *  
 */
public class SubmitSupervisionOrderCreateUpdateAction extends JIMSBaseAction {


    
	/**
	 * @roseuid 438F241400E1
	 */
	public SubmitSupervisionOrderCreateUpdateAction() {

	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.activateOrder", "activateOrder");
		keyMap.put("button.backToCaseOrderSearchResults", "backToCaseOrderSearchResults");
		keyMap.put("button.compareImpactedOrders", "compareImpactedOrders");
		keyMap.put("button.defendantSignatureAcquired", "defendantSignatureAcquired");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.prepareToFile", "prepareToFile");
		keyMap.put("button.updateOrder", "updateOrder");
		keyMap.put("button.backToSearch", "backToSearch");
		keyMap.put("button.mainPage", "mainPage");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward activateOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;

		Collection conditions = sof.getConditionSelectedList();
		List conditionIdsList = getOrderConditionIds(conditions);
        
		ActionForward forward = null;
		if (conditions == null || conditions.size() == 0) {
			this.sendToErrorPage(aRequest, "error.supervisionorder.noconditions");
			//forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ACTIVATE_FAILURE);
			return SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
		} else {
    		// validate if value has been entered for all the variable variables
			//If condition values were updated previously, this will not be reflected in ConditionResponseEvent.f
    		String varElemMissingName=UISupervisionOrderHelper.validateConditionValues(sof.getConditionSelectedList(),true);
    		if (varElemMissingName!=null)
    		{
    			this.sendToErrorPage(aRequest, "error.supervisionorder.noconditiondetails");
    			sof.setAction(UIConstants.UPDATE);    			
    			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ACTIVATE_FAILURE, UIUtil.getCurrentUserAgencyID()));    			
    		}
			UpdateSupervisionOrderStatusEvent requestEvent = (UpdateSupervisionOrderStatusEvent) EventFactory
					.getInstance(SupervisionOrderControllerServiceNames.UPDATESUPERVISIONORDERSTATUS);

			requestEvent.setSupervisionOrderId(sof.getOrderId());
			requestEvent.setStatus(PDCodeTableConstants.STATUS_ACTIVE_ID);
			requestEvent.setStatusChangeDate(new Date()); 
			requestEvent.setCourtId(sof.getCourtId());
			requestEvent.setDefendantId(sof.getDefendantId());
			requestEvent.setName(sof.getName());
			requestEvent.setOutOfCountyCase(sof.isOutOfCountyCase());
			requestEvent.setOrderConditionIdsList(conditionIdsList);
			
			if(HandleSupervisionOrderSelectionAction.shouldKillTask(sof)){
				requestEvent.setKillTaskId(sof.getTaskId());
				try{
					requestEvent.setCurrentUserId(UIUtil.getCurrentUserID());
				}
				catch(Exception e){
					
				}
			}
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(requestEvent);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			ActivateSupervisionOrderErrorEvent errorEvent = (ActivateSupervisionOrderErrorEvent) MessageUtil.filterComposite(compositeResponse, ActivateSupervisionOrderErrorEvent.class);
			if (errorEvent != null) {
				if (errorEvent.isOocTransferInDateMissing()){
	    			this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "TransferInDate missing from Out of County Case - Order cannot be activated");
				} else {
					this.sendToErrorPage(aRequest, "error.supervisionorder.noconditiondetails");
				}
				//forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ACTIVATE_FAILURE);
				forward = SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
			} else {
				//SubmitSupervisionOrderCreateUpdateAction.createTask(sof);
				sof.setOrderStatusId(PDCodeTableConstants.STATUS_ACTIVE_ID);
				sof.setStatusChangeDate(new Date()); 
				sof.setOrderFileDate(new Date());
				sof.setAction(UIConstants.ACTIVATE);

				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ACTIVATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
			}
		}
		return forward;
	}
	
	

	public ActionForward backToCaseOrderSearchResults(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {

        //setting this for print
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        sof.setPrintAction("");
        //End 
        
		ActionForward forward = SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
		return forward;
	}

	public ActionForward compareImpactedOrders(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;

		GetImpactedOrdersEvent reqEvent = new GetImpactedOrdersEvent();
		reqEvent.setOrderId(sof.getOrderId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(reqEvent);
		// process response
		CompositeResponse compositeResponse = UISupervisionOrderHelper.getCompositeResponse(dispatch);

		Collection coll = MessageUtil.compositeToCollection(compositeResponse,
				SupervisionOrderDetailResponseEvent.class);
		coll = MessageUtil.processEmptyCollection(coll);
		// set order title names
		SupervisionOrderListHelper.setOrderTitleNames(coll, sof.getOrderTitleList());
		// sort it by Activation Date
		Collections.sort((List) coll);
		// set it in the form
		sof.setImpactedOrderList(coll);
		// set the most current one to show on first page
		UISupervisionOrderHelper.setImpactedOrder(sof, 0);

		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.IMPACT_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	public ActionForward defendantSignatureAcquired(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		//	sof.clear();
		//	this.populateSupervisionOrderForm(sof, aRequest);

		sof.setAction(UIConstants.SIGNATURE_ACQUIRED);
		sof.setDefendantSignature("Signed"); // This default is being set with accordance to defect 32000050179 though I disagree with it
	      
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SIGNATURE_ACQUIRED_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		//forward =
		// SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping,
		// aRequest);
		return forward;
	}

	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {

		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		String action = sof.getAction();

		UISupervisionOrderHelper.postUpdateOrderStatusEvent(sof.getOrderId(), sof.getOrderStatusId(), sof.getStatusChangeDate(), PDCodeTableConstants.STATUS_DRAFT_ID); 
		sof.setOrderStatusId(PDCodeTableConstants.STATUS_DRAFT_ID);
		
       //getting original status id from the session and compare to the current status id --start
		String origStatusId = (String)aRequest.getSession().getAttribute("originalOrderStatusId");
		if(origStatusId != null && !origStatusId.equalsIgnoreCase(PDCodeTableConstants.STATUS_DRAFT_ID)){
			sof.setStatusChangeDate(new Date()); 
		}
		//end
		if(UISupervisionOrderHelper.isOOCcase(UIUtil.getCurrentUserAgencyID(),sof.getCdi())){
			return activateOrder(aMapping,aForm,aRequest,aResponse);
		}
		if (action.equals(UIConstants.CREATE)) {
			sof.setAction(UIConstants.CONFIRM_CREATE);
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONFIRM_CREATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		} else if (action.equals(UIConstants.UPDATE)) {
			sof.setAction(UIConstants.CONFIRM_UPDATE);
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONFIRM_UPDATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		} else {
			sof.clear();
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE, UIUtil.getCurrentUserAgencyID()));
		}
		return forward;
	}

	public ActionForward prepareToFile(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		
		String varElemMissingName=UISupervisionOrderHelper.validateConditionValues(sof.getConditionSelectedList(),true);
		if (varElemMissingName!=null)
		{
			this.sendToErrorPage(aRequest, "error.supervisionorder.noconditiondetails");
			sof.setAction(UIConstants.UPDATE);
			//this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Condition(s) exist which do not have all variable details set; please fill in all required fields and click the Validate Fields button to confirm entry");
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ACTIVATE_FAILURE, UIUtil.getCurrentUserAgencyID()));
			//return SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
		}
		
		
		/*if(UISupervisionOrderHelper.hasMissingVariableElements(sof.getConditionSelectedList())){
			sof.setAction(UIConstants.UPDATE);
	       	this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Condition(s) exist which do not have all variable details set; please fill in all required fields and click the Validate Fields button to confirm entry");
	       	forward= aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("activateFailure", UIUtil.getCurrentUserAgencyID()));
	       	//forward = SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
        }*/ else {
        	UISupervisionOrderHelper.setPresentedByInfo(sof); 
        	
        	sof.setDefendantSignature("");
        	sof.setDefendantSignedDateAsString("");
        	sof.setAction(UIConstants.PREPARE_TO_FILE);
        	forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.PREPARE_TO_FILE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
        }
        return forward;
	}

	public ActionForward updateOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		sof.setAction(UIConstants.UPDATE);
		if(sof.getIsPretrialInterventionOrder()){
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.UPDATE_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
		}
		else{
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.UPDATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		}
		return forward;
	}

	public ActionForward mainPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();

		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.MAIN_MENU, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	public ActionForward backToSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();

		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK_TO_SEARCH, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	/**
	 * @param staff
	 * @param supervisionStaffId
	 * @return
	 */
	public SupervisionStaffResponseEvent getSupervisionStaff(Collection staff) {
		SupervisionStaffResponseEvent ssre = null;
		IUserInfo userInfo = SecurityUIHelper.getUser();
		String userFirstName = userInfo.getFirstName();
		String userLastName = userInfo.getLastName();

		if (staff != null && userFirstName != null && userLastName != null) {
			Iterator iter = staff.iterator();

			while (iter.hasNext()) {
				ssre = (SupervisionStaffResponseEvent) iter.next();
				if (ssre.getLogonId().equals(userInfo.getJIMSLogonId())) {
					break;
				} else {
					ssre = null;
				}
			}
		}
		return ssre;
	}
	
	
	private List getOrderConditionIds(Collection conditions)
    {
    	ArrayList conditionIdsList = new ArrayList();
    	
    	if(conditions != null)
    	{
    		Iterator condIter = conditions.iterator();
    		while(condIter.hasNext())
    		{
    			ConditionDetailResponseEvent condRespEvent = (ConditionDetailResponseEvent)condIter.next();
    			conditionIdsList.add(condRespEvent.getConditionId());
    		}
    	}
    	return conditionIdsList;
    }
	
	public void addButtonMapping(Map keyMap) {
		keyMap.put("button.activateOrder", "activateOrder");
		keyMap.put("button.backToCaseOrderSearchResults", "backToCaseOrderSearchResults");
		keyMap.put("button.compareImpactedOrders", "compareImpactedOrders");
		keyMap.put("button.defendantSignatureAcquired", "defendantSignatureAcquired");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.prepareToFile", "prepareToFile");
		keyMap.put("button.updateOrder", "updateOrder");
		keyMap.put("button.backToSearch", "backToSearch");
		keyMap.put("button.mainPage", "mainPage");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}
	
}
