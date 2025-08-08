package ui.supervision.supervisionorder.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetCaseAssignmentEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionorder.DeleteSupervisionOrderEvent;
import messaging.supervisionorder.GetSupervisionOrderVersionsEvent;
import messaging.supervisionorder.UpdateOrderStatusToPendingEvent;
import messaging.supervisionorder.UpdateSupervisionOrderStatusEvent;
import messaging.supervisionorder.reply.ActivateSupervisionOrderErrorEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.supervisionorder.reply.JudgeResponseEvent;
import messaging.supervisionorder.reply.OrderDeleteErrorResponseEvent;
import messaging.supervisionorder.reply.SupOrderConditionResponseEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.SupervisionOrderButtonHelper;
import ui.supervision.supervisionorder.SupervisionOrderListHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;
import ui.supervision.supervisionorder.form.SupervisionOrderSearchForm;
import ui.supervision.supplementalDocuments.form.SupplementalDocumentsForm;

/**
 * @author dgibler
 *  
 */
public class HandleSupervisionOrderSelectionAction extends JIMSBaseAction {

    /**
     * @roseuid 438F240E01BC
     */
    public HandleSupervisionOrderSelectionAction() {

    }

    /**
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     * @return Map
     */
    public void addButtonMapping(Map keyMap) {
   
        keyMap.put("button.activateOrder", "activateOrder");
        keyMap.put("button.continueOrder", "continueOrder");
        keyMap.put("button.createOrder", "createOrder");
 //     keyMap.put("button.createPretrialInterventionOrder", "createPretrialInterventionOrder");
        keyMap.put("button.defendantSignatureAcquired", "defendantSignatureAcquired");
        keyMap.put("button.delete", "deleteOrder");
        keyMap.put("button.prepareToFile", "prepareToFile");
        keyMap.put("button.print", "printOrder");
        keyMap.put("button.printDraft", "printDraftOrder");
        keyMap.put("button.printSpanish", "printSpanishOrder");
        keyMap.put("button.reinstateOrder", "reinstateOrder");
        keyMap.put("button.updateOrder", "updateOrder");
        keyMap.put("button.viewOrderVersions", "viewOrderVersions");
        keyMap.put("button.viewAllOrderVersions", "viewAllOrderVersions");
        keyMap.put("button.compareOrderVersions", "compareOrderVersions");
        keyMap.put("button.withdrawOrder", "withdrawOrder");
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.newCaseDocuments", "newCaseDocuments");
        keyMap.put("button.link", "details");
        keyMap.put("button.printSignature", "printSignature");
        keyMap.put("button.setToPending", "resetOrderStatus");
    }
    
    private static final String SUPERVISION_ORDER_ID_MISSING = "HttpRequest info missing after CANCEL.  Please reenter search criteria and try again.";
  //  private static final String PENDING_ORDER_FOUND = "Please process Pending order before trying to reset the Original!";
    private static final String ASSIGNMENT_RECORD_FOUND = "Case has already been Assigned.Cannot reset to Pending!";
    
    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward activateOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = null;
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        sof.clear();
        sof.setSuggestedOrderId("");
        sof.setIsPretrialInterventionOrder(false);
        UISupervisionOrderHelper.populateMiniSupervisionOrderForm(sof, aRequest);
        
        //Check to see if key form info is missing.  This is caused by hitting back button.
        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        } 

        UISupervisionOrderHelper.getSupervisionOrderDetails(sof);
        Collection conditions = sof.getConditionSelectedList();
        List conditionIdsList = getOrderConditionIds(conditions);
        
        if (conditions == null || conditions.size() == 0) {
            this.sendToErrorPage(aRequest, "error.supervisionorder.noconditions");
            forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ACTIVATE_FAILURE, UIUtil.getCurrentUserAgencyID()));
        } else {
    		// validate if value has been entered for all the variable variables
    		String varElemMissingName=UISupervisionOrderHelper.validateConditionValues(sof.getConditionSelectedList(),true);
    		 if (varElemMissingName!=null)
    		{
    			 sof.setAction(UIConstants.UPDATE);
    			 this.sendToErrorPage(aRequest, "error.supervisionorder.noconditiondetails");
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
            	 sof.setAction(UIConstants.UPDATE);
            	 String msg = null;
            	 if (errorEvent.isOocTransferInDateMissing()){
	     			msg = "TransferInDate missing from Out of County Case - Order cannot be activated";
            	 } else {
	     			msg = "Condition(s) exist which do not have all variable details set; please fill in all required fields and click the Validate Fields button to confirm entry";
            	 }
    			 this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, msg);
    	     	 forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ACTIVATE_FAILURE, UIUtil.getCurrentUserAgencyID()));
            } else {
                sof.setOrderStatusId(PDCodeTableConstants.STATUS_ACTIVE_ID);
                sof.setStatusChangeDate(new Date()); 
                sof.setAction(UIConstants.ACTIVATE);

                forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ACTIVATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
            }
        }
        return forward;
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
       
    public static boolean shouldKillTask(SupervisionOrderForm myForm){
    	boolean retVal=false;
    	// if current user is CLO and taskId is set
    	if(myForm!=null && myForm.getTaskId()!=null && !myForm.getTaskId().trim().equals("")){  // form can't be null task must be there
    		if(UIUtil.getCurrentUserAgencyID()!=null && UIUtil.getCurrentUserAgencyID().trim().equals(UIConstants.CSC)){  // and agency must be CSCD accordxing to ER 46979
    			return true;
    			
    		}
    	}	
    	return retVal;
    }
    
    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward continueOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        sof.clear();
        sof.setSuggestedOrderId("");
        sof.setIsPretrialInterventionOrder(false);
        UISupervisionOrderHelper.populateMiniSupervisionOrderForm(sof, aRequest);
        //Check to see if key form info is missing.  This is caused by hitting back button.
        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        } 
        
        UISupervisionOrderHelper.getSupervisionOrderDetails(sof);
        if (sof.getPrintedName() == null || sof.getPrintedName().equals("")){        
        	sof.setPrintedName(UISupervisionOrderHelper.parsePrintedName(sof.getName()));
        }

        //Not sure how to handle this yet
        //TODO:Remember to implement. Handling as plain update for now.
        //		sof.setAction(UIConstants.CONTINUE_PROCESS);
        //		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONTINUE_SUCCESS);
        sof.setAction(UIConstants.UPDATE);
        if (sof.getIsPretrialInterventionOrder()){
            forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.UPDATE_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
        } else {
            forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.UPDATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
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
    public ActionForward createOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        sof.clear();
        sof.setSuggestedOrderId("");
        sof.setAction(UIConstants.CREATE);

        // populate form
        UISupervisionOrderHelper.populateSupervisionOrderForm(sof, aRequest);
        sof.setCourtId(sof.getCurrentCourtId());
        sof.setCourtCategory(sof.getCurrentCourtCategory());
        sof.setCourtNum(sof.getCourtId());
        
        if (sof.getCaseNum() == null || sof.getCaseNum().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
       } 
        
        if (sof.getPrintedName() == null || sof.getPrintedName().equals("")){        
        	sof.setPrintedName(UISupervisionOrderHelper.parsePrintedName(sof.getName()));
        }
        
        sof.setPleaId("");
        sof.setIsPretrialInterventionOrder(false);
        sof.setVersionTypeId("");
        sof.setVersionType("");
        sof.setOrderId("");
		sof.setOrderFileDate(null);
		sof.setIsPretrialInterventionOrder(false);
		sof.setOrderStatusId("");
		sof.setStatusChangeDate(null); 
		sof.setOrderVersion("");
		sof.setVersionNum(0);
		sof.setLikeConditionInd(false);
		sof.setSupOrderRevisionDate(null);
		sof.setVersionTypeId("");
		sof.setSuggestedOrderId("");
		sof.setCasenotes("");
		sof.setSummaryOfChanges("");
		sof.setSignedByDefendantDate(null);
		sof.setOrderTitleId("");
		sof.setDispositionTypeId("");
		sof.setJudgeSelectId("");
		sof.setShowOrigJudgeInput(false); 
		sof.setJuvSupervisionLengthDays("000");
		sof.setJuvSupervisionLengthMonths("00");
		sof.setJuvSupervisionLengthYears("00");
		sof.setJuvSupervisionBeginDate(null);
		sof.setJuvSupervisionBeginDateAsString("");
		sof.setSignedDate(null);
        sof.setVersionTypeId(PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL);
        sof.setAction(UIConstants.CREATE);
		if (sof.getIsPretrialInterventionOrder() || sof.getIsMigratedOrder()){
			sof.setShowOrigJudgeInput(true); 
			if (!sof.getCdi().equals("003")){
				sof.setShowOrigJudgeInput(false);
			}
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
		}else {
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		}	
        return forward;
        
    }
    
// 12-07-09 Commented out instead of deleting in case Glenda/Shueyun change their mind  
/**    public ActionForward createPretrialInterventionOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        sof.clear();
        sof.setSuggestedOrderId("");
        // populate form
        sof.setAction(UIConstants.CREATE);

        UISupervisionOrderHelper.populateSupervisionOrderForm(sof, aRequest);
        
        if (sof.getCaseNum() == null || sof.getCaseNum().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        }
        
        if (sof.getPrintedName() == null || sof.getPrintedName().equals("")){        
        	sof.setPrintedName(UISupervisionOrderHelper.parsePrintedName(sof.getName()));
        }
        
        sof.setIsPretrialInterventionOrder(true);
        sof.setVersionTypeChangeAllowed(true);
        sof.setDispositionTypeId(UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION);
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
        return forward;
    } */

    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward updateOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        //long timeStart = System.currentTimeMillis();

        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;        
        sof.clear();
        sof.setSuggestedOrderId("");
        sof.setIsPretrialInterventionOrder(false);
        sof.setAction(UIConstants.UPDATE);
        UISupervisionOrderHelper.populateMiniSupervisionOrderForm(sof, aRequest);
        
        //storing the statusId in the session--start 
        String origOrderStatusId = sof.getOrderStatusId();
		aRequest.getSession().setAttribute("originalOrderStatusId",origOrderStatusId);
		//end
        
        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
       } 
        
        if (sof.getPrintedName() == null || sof.getPrintedName().equals("")){        
        	sof.setPrintedName(UISupervisionOrderHelper.parsePrintedName(sof.getName()));
        }
        
        if(sof.getIsPretrialInterventionOrder() || sof.getIsMigratedOrder()){	
       		sof.setOrderTitleList(SupervisionOrderListHelper.groupOrderTitleList(
       				sof.getAllOrderTitleList(), 
       				sof.getCourtCategory(), 
       				sof.getCourtNum(),
       				PDConstants.BLANK));
       		//Need to retrieve conditions on update
            UISupervisionOrderHelper.getSupervisionOrderDetails(sof);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
        }
        // Defect #JIMS200040159 - Modified Order Title Missing
        if(sof.getOrderTitleId()==null || sof.getOrderTitleId().equals("")){
	        SupervisionOrderSearchForm searchForm = (SupervisionOrderSearchForm) aRequest.getSession().getAttribute("supervisionOrderSearchForm");
	        sof.setOriginalOrderTitleId(searchForm.getOriginalOrderTitleId());     
        }
        else{
        	sof.setOriginalOrderTitleId(sof.getOrderTitleId());
        }
        //11/21/08 - need to retrieve conditions for next page.
        UISupervisionOrderHelper.getSupervisionOrderDetails(sof);
        
        //If creating a new order version from a non-migrated original, original judge
        //name should not be opened for update.
        if (sof.isVersionTypeChangeAllowed()
        		&& PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL.equals(sof.getVersionTypeId())
        		&& !sof.getIsMigratedOrder()){
        	sof.setOriginalJudgeFirstName(PDConstants.BLANK);
        	sof.setOriginalJudgeLastName(PDConstants.BLANK);
        }
        
        //long timeEnd = System.currentTimeMillis();
        //System.out.println("Total Time(milli seconds) for detailed Supervision Order display" + (timeEnd - timeStart));
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.UPDATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward defendantSignatureAcquired(ActionMapping aMapping, ActionForm aForm,
            HttpServletRequest aRequest, HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        sof.clear();
        sof.setSuggestedOrderId("");
        sof.setIsPretrialInterventionOrder(false);
        
        UISupervisionOrderHelper.populateSupervisionOrderForm(sof, aRequest);
        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ACTIVATE_FAILURE, UIUtil.getCurrentUserAgencyID()));
        } 

        /*
         * // dp ----- SupervisionOrderSearchForm searchForm =
         * (SupervisionOrderSearchForm)
         * aRequest.getSession().getAttribute("supervisionOrderSearchForm");
         * Iterator iter = searchForm.getCaseOrderList().iterator(); String
         * orderId = ""; while(iter.hasNext()) { CaseOrderResponseEvent
         * caseOrder = (CaseOrderResponseEvent) iter.next(); if(caseOrder !=
         * null &&
         * caseOrder.getPrimaryKey().equals(sof.getPrimaryCaseOrderKey())) {
         * orderId = caseOrder.getOrderId(); sof.setSupervisionOrderId(orderId);
         * break; } }
         */

        //----------dp
        if (sof.getSignedByDefendantDate() == null){ //Didn't set info on print signature.
        	sof.setDefendantSignature("Signed"); // This default is being set with accordance to defect 32000050179 though I disagree with it
        } 
        sof.setSignedByDefendantDate(null); // change for defect 62623 so current date displays in jsp - cws 10/27/09
        sof.setAction(UIConstants.SIGNATURE_ACQUIRED);
        //sof.setAction(UIConstants.DEFENDENT_SIG_AQUIRED);
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SIGNATURE_ACQUIRED_SUCCESS, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward deleteOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        ActionForward forward = null;
        sof.setIsPretrialInterventionOrder(false);
        sof.setAction(UIConstants.DELETE);
        UISupervisionOrderHelper.populateMiniSupervisionOrderForm(sof, aRequest);
        
        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        } 

        UISupervisionOrderHelper.getSupervisionOrderDetails(sof);

        DeleteSupervisionOrderEvent event = new DeleteSupervisionOrderEvent();
        event.setSupervisionOrderId(sof.getOrderId());

        CompositeResponse compositeResponse = MessageUtil.postRequest(event);
        IEvent errorEvent = (IEvent) MessageUtil.filterComposite(compositeResponse, OrderDeleteErrorResponseEvent.class);
        
        if (errorEvent != null){
          	this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Delete not allowed. Order Status is no longer Incomplete.");
          	forward = SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
        } else {
        	forward =  aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.DELETE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
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
    public ActionForward prepareToFile(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        sof.clear();
        sof.setSuggestedOrderId("");
        sof.setIsPretrialInterventionOrder(false);
        UISupervisionOrderHelper.populateMiniSupervisionOrderForm(sof, aRequest);
        
        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        } 

        UISupervisionOrderHelper.getSupervisionOrderDetails(sof);
        sof.setSignedDateAsString("");
       
        String varElemMissingName=UISupervisionOrderHelper.validateConditionValues(sof.getConditionSelectedList(),true);
        if (varElemMissingName!=null)
		{
			this.sendToErrorPage(aRequest, "error.supervisionorder.noconditiondetails");
			sof.setAction(UIConstants.UPDATE);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ACTIVATE_FAILURE, UIUtil.getCurrentUserAgencyID()));
		}else {
        	UISupervisionOrderHelper.setPresentedByInfo(sof); 
	        sof.setDefendantSignature("");
	        sof.setDefendantSignedDateAsString("");
	        sof.setJudgeSignedDateAsString("");
	        sof.setAction(UIConstants.PREPARE_TO_FILE);
	        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.PREPARE_TO_FILE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
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
	public ActionForward printOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
        HttpServletResponse aResponse) {
	       SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
	        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
	            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
	            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
	        } 

        return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.PRINT_SUCCESS, UIUtil.getCurrentUserAgencyID()));
    }

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward printDraftOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        } 

        return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.PRINT_DRAFT_SUCCESS, UIUtil.getCurrentUserAgencyID()));
    }

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward printSpanishOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        } 

        return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.PRINT_SPANISH_SUCCESS, UIUtil.getCurrentUserAgencyID()));
    }

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward reinstateOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        sof.clear();
        sof.setSuggestedOrderId("");
        sof.setAction(UIConstants.REINSTATE);
        UISupervisionOrderHelper.populateSupervisionOrderForm(sof, aRequest);
        
        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        } 

        if(UIUtil.getCurrentUserAgencyID()!=null && UIUtil.getCurrentUserAgencyID().equals(UIConstants.CSC))
        	sof.setReasonId("DEE"); // default for CSC
        //sof.setAction(UIConstants.REINSTATE); setting above
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.REINSTATE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }
    
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward viewOrderVersions(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
    	return commonViewOrderVersions(aMapping,aForm,aRequest,aResponse,false);
    }
    
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward viewAllOrderVersions(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
    	
    	SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
    	
    	// RRY added so the form gets filled
        UISupervisionOrderHelper.populateSupervisionOrderForm(sof, aRequest);
        
    	sof.setSelectedOrderId(sof.getOrderId());
        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
       } 

    	return commonViewOrderVersions(aMapping,aForm,aRequest,aResponse,true);
    }

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward commonViewOrderVersions(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse, boolean aViewAll) {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        sof.clear();
        sof.setSuggestedOrderId("");
        sof.setIsPretrialInterventionOrder(false);
        sof.setAction(UIConstants.VIEW);
        UISupervisionOrderHelper.populateSupervisionOrderForm(sof, aRequest);
        //Check to see if key form info is missing.  This is caused by hitting back button.
        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        } 
        // post pd event
        GetSupervisionOrderVersionsEvent reqEvent = new GetSupervisionOrderVersionsEvent();
        reqEvent.setAgencyId(sof.getAgencyId());
        reqEvent.setCaseNum(sof.getCaseId());
        reqEvent.setOrderId(sof.getOrderId());
        reqEvent.setOrderChainNum(sof.getOrderChainNum());
        reqEvent.setAllOrderChains(aViewAll);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(reqEvent);
        CompositeResponse compositeResponse = UISupervisionOrderHelper.getCompositeResponse(dispatch);
        // get OrderResposneEvent
        Collection coll = MessageUtil.compositeToCollection(compositeResponse, SupervisionOrderDetailResponseEvent.class);
        coll = MessageUtil.processEmptyCollection(coll);
        // set order title names and version types
        UISupervisionOrderHelper.setOrderDescriptions(coll, sof);

        // set collection in form
        List orderVersions = (List) coll;
        String versionTypeId = "";
        if (orderVersions != null){
        	// change plea value from Id to description for display in jsp 
        	for (int p = 0; p < orderVersions.size(); p++){
        		SupervisionOrderDetailResponseEvent sodrEvent = (SupervisionOrderDetailResponseEvent) orderVersions.get(p);
        		if (sodrEvent.getPlea() != null){
        			sodrEvent.setPlea(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLEA,sodrEvent.getPlea()));
        		}
        		if (sodrEvent.getSpecialCourtCd() != null){
        			sodrEvent.setSpecialCourtDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SPECIAL_COURT,sodrEvent.getSpecialCourtCd()));
        		}
        	}
        	if (orderVersions.size() == 1){
        		SupervisionOrderDetailResponseEvent theEvent = (SupervisionOrderDetailResponseEvent) orderVersions.get(0);
        		versionTypeId = theEvent.getVersionTypeId();
        	}
        }
        // other versions should not display in jsp if only order is original
       	if (orderVersions.size() > 1 || (orderVersions.size() == 1 && !versionTypeId.equalsIgnoreCase("O"))){
       		sof.setOrderVersionList(orderVersions);
       		Collections.sort((List)orderVersions);
        } else {
        	sof.setOrderVersionList(new ArrayList());
        }
       	
        // set selectedOrderVersion and previousOrderVersion properties
       	if (orderVersions != null && orderVersions.size() > 0){
       		sof.setSelectedOrderVersion((SupervisionOrderDetailResponseEvent) orderVersions.get(0));
       	}
        // previous version
        if (orderVersions.size() > 1) {
            sof.setPreviousOrderVersion((SupervisionOrderDetailResponseEvent) orderVersions.get(1));
        }
        // set conditions
        // sort it by sequence num
        Collection conditions = getViewVersionConditions(sof.getSelectedOrderVersion(), sof.getPreviousOrderVersion());
        Collections.sort((List) conditions, ConditionDetailResponseEvent.SeqNumComparator);
        sof.setConditionSelectedList(conditions);

        //sof.setAction(UIConstants.VIEW); setting above
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.VIEW_SUCCESS, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward compareOrderVersions(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;

        List orderVersions = (List) sof.getOrderVersionList();

        String selectedOrderId = (String) aRequest.getParameter("orderId");
        //Check to see if key form info is missing.  This is caused by hitting back button.
        if (selectedOrderId == null || selectedOrderId.equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
       } 

        sof.setSelectedOrderId(selectedOrderId);
        SupervisionOrderDetailResponseEvent currentSelectedVersion = null;
        SupervisionOrderDetailResponseEvent previousVersion = null;
        for (Iterator iter = orderVersions.iterator(); iter.hasNext();) {
            currentSelectedVersion = (SupervisionOrderDetailResponseEvent) iter.next();
            if (currentSelectedVersion.getOrderId().equals(selectedOrderId)) {
                // get its predecessor
                if (iter.hasNext()) {
                    previousVersion = (SupervisionOrderDetailResponseEvent) iter.next();
                }
                break;
            }
        }

        sof.setSelectedOrderVersion(currentSelectedVersion);
        sof.setIsMigratedOrder(currentSelectedVersion.isMigrated());
        sof.setPreviousOrderVersion(previousVersion);

        // set conditions
        // sort it by sequence num
        Collection conditions = getViewVersionConditions(sof.getSelectedOrderVersion(), sof.getPreviousOrderVersion());
        sof.setConditionSelectedList(conditions);

        sof.setAction(UIConstants.VIEW);
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.VIEW_SUCCESS, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward resetOrderStatus(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;

        //Default forward
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("resetOrderSuccess",UIUtil.getCurrentUserAgencyID()));
        
        SupervisionOrderSearchForm searchForm = (SupervisionOrderSearchForm) 
        					aRequest.getSession().getAttribute("supervisionOrderSearchForm");
        
        String cdi = sof.getPrimaryCaseOrderKey().substring(0,3).trim();
        String caseNum = sof.getPrimaryCaseOrderKey().substring(3,15);
        String orderId = sof.getPrimaryCaseOrderKey().substring(15);
        String criminalCaseId = sof.getPrimaryCaseOrderKey().substring(0,15);
        List caseOrderList = (List) searchForm.getCaseOrderList();
        List caseAssignments = new ArrayList();
        // Check to see if there is other orders for the same case in open statuses.
        // Send error msg.
        
        for ( int z =0; z < caseOrderList.size(); z++ ){
        	
        	CaseOrderResponseEvent response = (CaseOrderResponseEvent) caseOrderList.get(z);
        	if ( cdi.equals( response.getCdi() ) && caseNum.equals(response.getCaseNum() )){

        		if ( "O".equals( response.getVersionTypeId() ) ){
        			
        			// Check is assigned
        			GetCaseAssignmentEvent getCaseAssignmentEvent = new GetCaseAssignmentEvent();
        			getCaseAssignmentEvent.setSupervisionOrderId( orderId );
        			getCaseAssignmentEvent.setCriminalCaseId( criminalCaseId );
        			Date puAssignDate = null;
        			
        			CaseAssignmentResponseEvent caseAssignmentResponse = (CaseAssignmentResponseEvent) MessageUtil.postRequest(
        					getCaseAssignmentEvent, CaseAssignmentResponseEvent.class);

        			caseAssignments = caseAssignmentResponse.getCaseAssignments();
        			for ( int x = 0; x< caseAssignments.size();x++ ){
        				
        				CaseAssignmentTO to = (CaseAssignmentTO) caseAssignments.get(x);
        				puAssignDate = to.getProgramUnitAssignDate();
        				break;
        			}

        			if ( puAssignDate != null ){
        				
        				this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, ASSIGNMENT_RECORD_FOUND);
                        return forward;
        			}
        		}
        	}
        }
        UpdateOrderStatusToPendingEvent updateEvent = new UpdateOrderStatusToPendingEvent();
    	updateEvent.setSupervisionOrderId( orderId );
    	MessageUtil.postRequest( updateEvent );
    	
    	sof.setAction(UIConstants.UPDATE);
        return forward;
    }

    private Collection getViewVersionConditions(SupervisionOrderDetailResponseEvent selectedOrder,
            SupervisionOrderDetailResponseEvent previousOrder) {
        Collection selectedConditions = new ArrayList();

        Collection selectedOrderConditions = selectedOrder.getConditions();

        // if there is no previous Order, conditions should be shown as if they
        // exist in both Orders
        if (previousOrder == null) {
            previousOrder = selectedOrder;
        }
     // orderIds are OIDs
        if (previousOrder != null){
        	if (Integer.parseInt(previousOrder.getOrderId()) > Integer.parseInt(selectedOrder.getOrderId() ) ){
        		previousOrder = selectedOrder;
        	}
        }
        Collection previousOrderConditions = previousOrder.getConditions();

        // create a set of condition ids and a map
        Set prevCondIds = new HashSet();
        // create a map of conditionId and responseEvent
        Map prevCondMap = new HashMap();
        for (Iterator prevIter = previousOrderConditions.iterator(); prevIter.hasNext();) {
            SupOrderConditionResponseEvent previousOrderCondition = (SupOrderConditionResponseEvent) prevIter.next();
            prevCondIds.add(previousOrderCondition.getConditionId());
            prevCondMap.put(previousOrderCondition.getConditionId(), previousOrderCondition);
        }

        // traverse through current Order
        for (Iterator selectedIter = selectedOrderConditions.iterator(); selectedIter.hasNext();) {
            SupOrderConditionResponseEvent selectedOrderCondition = (SupOrderConditionResponseEvent) selectedIter
                    .next();
            // convert into ConditionDetailResponseEvent to add into collection
            ConditionDetailResponseEvent selectedCondition = convert(selectedOrderCondition);

            SupOrderConditionResponseEvent previousOrderCondition = (SupOrderConditionResponseEvent) prevCondMap
                    .get(selectedOrderCondition.getConditionId());
            if (previousOrderCondition != null) { // condition exists in both
                // versions
                // get resolved descriptions
                String selectedResolveDesc = UIUtil.removeXMLtags(selectedOrderCondition.getResolvedDescription(), true);
                String previousResolveDesc = UIUtil.removeXMLtags(previousOrderCondition.getResolvedDescription(), true);
                selectedResolveDesc = selectedResolveDesc.trim();  // extra step for better compare
                previousResolveDesc = previousResolveDesc.trim();  // extra step for better compare
                // check if condition has been updated
                if (selectedResolveDesc == null || previousResolveDesc == null
                        || selectedResolveDesc.equals(previousResolveDesc)) { // unchanged
                    // condition
                    selectedCondition.setCompareToPreviousVersion("");
                } else { // updated condition
                    selectedCondition.setCompareToPreviousVersion("updated");
                }
                // remove it from the map
                prevCondMap.remove(selectedOrderCondition.getConditionId());
            } else { // added conditions
                selectedCondition.setCompareToPreviousVersion("added");
            }
            // add it into collection
            selectedConditions.add(selectedCondition);
        }

        // needs to be sorted by sequence number
        Collections.sort((List) selectedConditions, ConditionDetailResponseEvent.SeqNumComparator);

        // removed conditions
        Collection removedConditions = prevCondMap.values();
        if (removedConditions != null) {
            for (Iterator iter = removedConditions.iterator(); iter.hasNext();) {
                SupOrderConditionResponseEvent removedCondition = (SupOrderConditionResponseEvent) iter.next();
                // convert
                ConditionDetailResponseEvent selectedCondition = convert(removedCondition);
                selectedCondition.setCompareToPreviousVersion("removed");
                // add it into collection
                selectedConditions.add(selectedCondition);
            }
        }

        return selectedConditions;
    }

    private ConditionDetailResponseEvent convert(SupOrderConditionResponseEvent orderCondRespEvent) {
        ConditionDetailResponseEvent condDetailRespEvent = new ConditionDetailResponseEvent();
        condDetailRespEvent.setConditionId(orderCondRespEvent.getConditionId());
        condDetailRespEvent.setDescription(orderCondRespEvent.getDescription());
        condDetailRespEvent.setResolvedDescription(orderCondRespEvent.getResolvedDescription());
        condDetailRespEvent.setName(orderCondRespEvent.getName());
        condDetailRespEvent.setLikeConditionInd(orderCondRespEvent.isLikeConditionInd());
        condDetailRespEvent.setSequenceNum(orderCondRespEvent.getSequenceNum());
        return condDetailRespEvent;
    }

    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward withdrawOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        sof.clear();
        sof.setSuggestedOrderId("");
        sof.setIsPretrialInterventionOrder(false);
        sof.setAction(UIConstants.WITHDRAW);

        UISupervisionOrderHelper.populateSupervisionOrderForm(sof, aRequest);
        
        if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK)){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
            return forward;
        } 

        //sof.setAction(UIConstants.WITHDRAW); setting above
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.WITHDRAW_SUCCESS, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
    }

    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
     * @throws GeneralFeedbackMessageException 
	 */
	public ActionForward newCaseDocuments(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		
		SupplementalDocumentsForm suppDocForm =(SupplementalDocumentsForm)getSessionForm
												(aMapping, aRequest, "supplementalDocumentsForm", true);
		SupervisionOrderForm sForm = (SupervisionOrderForm) aForm;
		SupervisionOrderSearchForm searchForm = (SupervisionOrderSearchForm) aRequest.getSession().getAttribute("supervisionOrderSearchForm");
		
		//Prefill form from selected case or free fill at the end??
		if ( StringUtils.isNotEmpty( searchForm.getSpn() ) ) {
			suppDocForm.setSpn(searchForm.getSpn());
		}
		if ( StringUtils.isNotEmpty( sForm.getPrimaryCaseOrderKey() ) ) {
			String criminalCaseId = sForm.getPrimaryCaseOrderKey();
			suppDocForm.setCdi(criminalCaseId.substring(0, 3));
			suppDocForm.setCaseNumber( criminalCaseId.substring(3, 15) );
			if ( StringUtils.isNotEmpty( sForm.getMyStaffPos().getStaffPositionId() ) ) {
				String staffPositionId = sForm.getMyStaffPos().getStaffPositionId();
				GetLightCSCDStaffForUserEvent reqEvt = new GetLightCSCDStaffForUserEvent();
				reqEvt.setStaffPositionId(staffPositionId);
				reqEvt.setOfficerNameNeeded(true);
				LightCSCDStaffResponseEvent staffPosRespEvt = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(reqEvt, LightCSCDStaffResponseEvent.class);
				//add logic to find userprofile to get positions phone and email
				if(staffPosRespEvt!=null)
				{
					if ( StringUtils.isNotEmpty( staffPosRespEvt.getOfficerName() ) ) {
						//set assigned officer name.
						suppDocForm.setOfficer(staffPosRespEvt.getOfficerName());
					} else {
						suppDocForm.setOfficer("NO OFFICER ASSIGNED");						
					}
					if ( StringUtils.isNotEmpty( staffPosRespEvt.getStaffPositionName() ) ) {
						//set assigned officer POI
						suppDocForm.setPoi(staffPosRespEvt.getStaffPositionName());
					}
				}		        
			}
		}
// forward to DisplayNewCaseDocumentListAction		
        return aMapping.findForward(UIConstants.NEW_CASE_DOCS_SUCCESS);
    }

    
    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward details(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        String orderId = sof.getSelectedValue();
        sof.clear();
        sof.setSuggestedOrderId("");
        sof.setIsPretrialInterventionOrder(false);
        sof.setOrderId(orderId);
        sof.setAction(UIConstants.VIEW);
        if (orderId != null && !orderId.equals("")) {
            UISupervisionOrderHelper.getSupervisionOrderDetails(sof);
            sof.setOrderTitleList(sof.getAllOrderTitleList());
        }
        if (sof.getCaseNum() == null || sof.getSpn() == null || sof.getCaseNum().equals("") || sof.getSpn().equals("")) {
            // couldn't find one
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, SUPERVISION_ORDER_ID_MISSING);
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        }
        // post pd event
        GetSupervisionOrderVersionsEvent reqEvent = new GetSupervisionOrderVersionsEvent();
        reqEvent.setAgencyId(sof.getAgencyId());
        reqEvent.setCaseNum(sof.getCaseId());
        reqEvent.setOrderId(sof.getOrderId());
        reqEvent.setOrderChainNum(sof.getOrderChainNum());
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(reqEvent);
        CompositeResponse compositeResponse = UISupervisionOrderHelper.getCompositeResponse(dispatch);
        // get OrderResposneEvent
        Collection coll = MessageUtil.compositeToCollection(compositeResponse,
                SupervisionOrderDetailResponseEvent.class);
        coll = MessageUtil.processEmptyCollection(coll);
        // set order title names and version types
        UISupervisionOrderHelper.setOrderDescriptions(coll, sof);

        // set collection in form
        List orderVersions = (List) coll;
        Collections.sort(orderVersions);
        sof.setOrderVersionList(coll);
        // set selectedOrderVersion and previousOrderVersion properties
        if (orderVersions != null && orderVersions.size() > 0){
        	sof.setSelectedOrderVersion((SupervisionOrderDetailResponseEvent) orderVersions.get(0));
        }
        // previous version
        if (orderVersions.size() > 1) {
            sof.setPreviousOrderVersion((SupervisionOrderDetailResponseEvent) orderVersions.get(1));
        }
        // set conditions
        // sort it by sequence num
        Collection conditions = getViewVersionConditions(sof.getSelectedOrderVersion(), sof.getPreviousOrderVersion());
        Collections.sort((List) conditions, ConditionDetailResponseEvent.SeqNumComparator);
        sof.setConditionSelectedList(conditions);
        sof.setPleas(SimpleCodeTableHelper.getCodesSortedByCode(PDCodeTableConstants.PLEA));
        sof.setJuvCourts(SupervisionOrderListHelper.getJuvCourts());     
        sof.setSpecialCourtCds(SimpleCodeTableHelper.getCodesSortedByCode(PDCodeTableConstants.SPECIAL_COURT));
        sof.setIsMigratedOrder(sof.getSelectedOrderVersion().isMigrated());  // needed for print check in jsp
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward printSignature(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{		
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		sof.setSecondaryAction(sof.getAction());
		//in case the user selects a suggested order, then hits back and selects to print signature
		sof.setSuggestedOrderId("");		
		sof.getConditionSelectedList().clear();

		sof.setAction(UIConstants.PRINT_SIGNATURE);
		UISupervisionOrderHelper.populateMiniSupervisionOrderForm(sof, aRequest);
        UISupervisionOrderHelper.getSupervisionOrderDetails(sof);
		if (sof.getPresentedByLastName() != null && !sof.getPresentedByLastName().equals(PDConstants.BLANK)){
			UISupervisionOrderHelper.setPresentedByInfo(sof);
		} else {
			sof.setDefendantSignature("Signed"); // This default is being set with accordance to defect 32000050179 though I disagree with it
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
		sof.setDefendantSignedDateAsString("");
        sof.setDefendantSignature("");
        sof.setJudgeSignedDateAsString("");
		return aMapping.findForward(UIConstants.PRINT_SIGNATURE);
	}
	/**
	 * @param collection
	 * @return sorted collection
	 */	
	private Collection sortOrderVersions(Collection coll){
		 if (coll.size() > 1){
			SortedMap map = new TreeMap();
			Iterator itr = coll.iterator();
			while (itr.hasNext()){
				SupervisionOrderDetailResponseEvent sEvent = (SupervisionOrderDetailResponseEvent)itr.next();
				map.put(sEvent.getOrderId(), sEvent);
			}
			List temp1 = new ArrayList(map.values());
			List temp2 = new ArrayList();
//reverse the sort order
			int indx = temp1.size() - 1;
			for (int x = 0; x <= indx; x++){
				temp2.add(temp1.get(indx - x)); 
			}
			coll = temp2;
		 }
		 return coll;
	}	
}