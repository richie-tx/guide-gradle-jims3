// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\managesupervisioncase\\action\\SubmitOutOfCountyCaseCreateUpdateAction.java

package ui.supervision.managesupervisioncase.action;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.managesupervisioncase.GetOOCCaseUpdateHistoryEvent;
import messaging.managesupervisioncase.ReactivateOutOfCountyCaseEvent;
import messaging.managesupervisioncase.UpdateOutOfCountyCaseEvent;
import messaging.managesupervisioncase.reply.OOCCaseUpdateHistoryEvent;
import messaging.managesupervisioncase.reply.OutOfCountyCaseEvent;
import messaging.managesupervisioncase.reply.ValidateResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OutOfCountyCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.managesupervisioncase.form.OutOfCountyCaseForm;
import ui.supervision.supervisionorder.SupervisionOrderListHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;
import ui.supervision.supervisionorder.form.SupervisionOrderSearchForm;

public class SubmitOutOfCountyCaseCreateUpdateAction extends JIMSBaseAction {

    /**
     * @roseuid 4443EFD4020E
     */
    public SubmitOutOfCountyCaseCreateUpdateAction() {

    }

    /**
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     * @return Map
     */
    public void addButtonMapping(Map buttonMap) {
    	buttonMap.put("button.finish", "finish");
    	buttonMap.put("button.updateCase", "updateCase");
    	buttonMap.put("button.newSearch", "newSearch");
    	buttonMap.put("button.createSupervisionOrder", "createSupervisionOrder");
    	buttonMap.put("button.viewReasonForUpdateHistory", "viewReasonForUpdateHistory");
    	buttonMap.put("button.back", "back");
    	buttonMap.put("button.cancel", "cancel");
    }

    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
        ActionForward forward = new ActionForward();
        OutOfCountyCaseForm ooc = (OutOfCountyCaseForm) aForm;

        String action = ooc.getAction();
        if (action.equals(UIConstants.CREATE)) {
            String message = doCreateUpdate(ooc, true);
            if (message == null) {
                ooc.setAction(UIConstants.CONFIRM_CREATE);
                forward = aMapping.findForward(UIConstants.CONFIRM_CREATE_SUCCESS);
            } else {
                sendToErrorPage(aRequest, message);
                forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
            }
        } else if (action.equals(UIConstants.UPDATE)) {
            String message = doCreateUpdate(ooc, false);
            if (message == null) {    
                ooc.setAction(UIConstants.CONFIRM_UPDATE);
                forward = aMapping.findForward(UIConstants.CONFIRM_UPDATE_SUCCESS);
            } else {
                sendToErrorPage(aRequest, message);
                forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
            }
        } else if (action.equals(UIConstants.REACTIVATE)) {
            //	Request-Response to PD
            doReactivate(ooc);
            ooc.setAction(UIConstants.CONFIRM_REACTIVATE);
            forward = aMapping.findForward(UIConstants.CONFIRM_REACTIVATE_SUCCESS);
        } else if (action.equals(UIConstants.PRETRIAL_CREATE)) {
            // Request-Response to PD
            String message = doCreateUpdate(ooc, true);
            if (message == null) {
                ooc.setAction(UIConstants.CONFIRM_PRETRIAL_CREATE);
                forward = aMapping.findForward(UIConstants.CONFIRM_CREATE_PRETRIAL_SUCCESS);
            } else {
                sendToErrorPage(aRequest, message);
                forward = aMapping.findForward(UIConstants.UPDATE_PRETRIAL_SUCCESS);
            }
        } else if (action.equals(UIConstants.PRETRIAL_UPDATE)) {
            // Request-Response to PD
            String message = doCreateUpdate(ooc, false);
            if (message == null) {
                ooc.setAction(UIConstants.CONFIRM_PRETRIAL_UPDATE);
                forward = aMapping.findForward(UIConstants.CONFIRM_UPDATE_PRETRIAL_SUCCESS);
            } else {
                sendToErrorPage(aRequest, message);
                forward = aMapping.findForward(UIConstants.UPDATE_PRETRIAL_SUCCESS);
            }
        } else if (action.equals(UIConstants.PRETRIAL_REACTIVATE)) {
            // Request-Response to PD
            doReactivate(ooc);

            ooc.setAction(UIConstants.CONFIRM_PRETRIAL_REACTIVATE);
            forward = aMapping.findForward(UIConstants.CONFIRM_REACTIVATE_PRETRIAL_SUCCESS);
        } else {
            sendToErrorPage(aRequest, "Reactivation of case failed.");
            forward = aMapping.findForward(UIConstants.REACTIVATE_PRETRIAL_SUCCESS);
        }
        return forward;

    }

    public ActionForward updateCase(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        OutOfCountyCaseForm ooc = (OutOfCountyCaseForm) aForm;

        String action = ooc.getAction();
        // load the code tables for the form

        // determine which view to display
        if (action.equals(UIConstants.VIEW)) {
            ooc.setAction(UIConstants.UPDATE);
            forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
        } else if (action.equals(UIConstants.PRETRIAL_VIEW)) {
            ooc.setAction(UIConstants.PRETRIAL_UPDATE);
            forward = aMapping.findForward(UIConstants.UPDATE_PRETRIAL_SUCCESS);
        } else {
            forward = aMapping.findForward(UIConstants.FAILURE);
        }
        return forward;
    }

    public ActionForward newSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
    }

    public ActionForward createSupervisionOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {

        OutOfCountyCaseForm oocForm = (OutOfCountyCaseForm) aForm;

        SupervisionOrderForm sof = new SupervisionOrderForm();
        // populate form
        sof.setName(oocForm.getName().toString());
// printed name needs to be in FML format   08/14/09  CWS      
//        sof.setPrintedName(oocForm.getName().toString());
        StringBuffer printedName = new StringBuffer();
        printedName.append(oocForm.getFirstName());
        printedName.append(" ");
        if (oocForm.getMiddleName() != null && !oocForm.getMiddleName().equals("")){
        	printedName.append(oocForm.getMiddleName());
        	printedName.append(" ");
        }
        if (oocForm.getLastName() != null){
        	printedName.append(oocForm.getLastName());
        }
        sof.setPrintedName(printedName.toString().trim());
        sof.setDefendantId(oocForm.getSpn());
        sof.setConnectionId("DEF");
        sof.setCdi(oocForm.getCdi());
        sof.setCaseNum(oocForm.getCaseNum());
        //sof.setPrimaryCaseOrderKey(oocForm.getPrimaryKey());
        sof.setPrimaryCaseOrderKey(oocForm.getCdi() + oocForm.getCaseNum());
        sof.setSpn(oocForm.getSpn());
        sof.setCourtId(oocForm.getCourtId());
        sof.setCourtNum(oocForm.getCaseTypeId());
        sof.setCourtCategory(oocForm.getCourtCategory());
        sof.setCurrentCourtId(oocForm.getCourtId());
        sof.setCurrentCourtNum(oocForm.getCaseTypeId());
        sof.setCurrentCourtCategory(oocForm.getCourtCategory());
        sof.setOffenseId(oocForm.getOffenseId());
        sof.setPleas(SimpleCodeTableHelper.getCodesSortedByCode(PDCodeTableConstants.PLEA));
        sof.setJuvCourts(SupervisionOrderListHelper.getJuvCourts());
        sof.setSpecialCourtCds(SimpleCodeTableHelper.getCodesSortedByCode(PDCodeTableConstants.SPECIAL_COURT));
        sof.setPrintedOffenseDesc(oocForm.getOffenseDesc());
        sof.setCaseFileDate(oocForm.getFilingDate());
        sof.setCaseSupervisionBeginDateAsString(oocForm.getSupervisionBeginDateAsString());

        sof.setVersionTypeId(PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL);
//        sof.setOrderTitleList(SupervisionOrderListHelper.groupOrderTitleList(sof.getAllOrderTitleList(), sof
//                .getCourtCategory(), sof.getVersionType(), sof.getCourtId())); 
        sof.setOrderTitleList(SupervisionOrderListHelper.groupOrderTitleList(
        		sof.getAllOrderTitleList(), 
        		sof.getCourtCategory(), 
        		sof.getCourtNum(),
        		sof.getVersionType())); 
        
//        set Confinement Length 
        sof.setConfinementLengthYears(oocForm.getConfinementLengthYear());
        sof.setConfinementLengthMonths(oocForm.getConfinementLengthMonth());
        sof.setConfinementLengthDays(oocForm.getConfinementLengthDay());
        
//        set Supervision Length
        sof.setSupervisionLengthYears(oocForm.getSupervisionLengthYear());
        sof.setSupervisionLengthMonths(oocForm.getSupervisionLengthMonth());
        sof.setSupervisionLengthDays(oocForm.getSupervisionLengthDay());
        
//        set supervision begin/end dates(for deferred and probation diposition type)
//        or pretrial intervention begin/end dates(for pretrial intervention disposition type)
        Date supervisionBeginDate = oocForm.getSupervisionBeginDate();
        Date supervisionEndDate = oocForm.getSupervisionEndDate();
        
        if((supervisionBeginDate==null) && (supervisionEndDate==null))
        {
        	supervisionBeginDate = oocForm.getPretrialInterventionBegin();
        	supervisionEndDate = oocForm.getPretrialInterventionEnd();
        }
        
        sof.setCaseSupervisionBeginDate(supervisionBeginDate);
        sof.setCaseSupervisionEndDate(supervisionEndDate);
        
        sof.setAction(UIConstants.CREATE);

        HttpSession session = aRequest.getSession();
        session.setAttribute("supervisionOrderForm", sof);

        SupervisionOrderSearchForm sosf = new SupervisionOrderSearchForm();
        // populate search form
        sosf.setName(oocForm.getName());
        sosf.setSpn(oocForm.getSpn());
        sosf.setCjis(oocForm.getCjis());
        sosf.setDateOfBirth(oocForm.getDateOfBirth());
        sosf.setRaceId(oocForm.getRaceId());
        sosf.setSexId(oocForm.getSexId());
        sosf.setSsn(oocForm.getSsn());
        sosf.setConnectionId("DEF");
        sosf.setCdi(oocForm.getCdi());
        sosf.setCaseNum(oocForm.getCaseNum());
        sosf.setOffenseId(oocForm.getOffenseId());
        sosf.setStateId(oocForm.getStateId());

        sosf.setVersionTypeId(PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL);
        sosf.setAction(UIConstants.CREATE);

        session.setAttribute("supervisionOrderSearchForm", sosf);

        return aMapping.findForward(UIConstants.CREATE_SUCCESS);
    }

    public ActionForward viewReasonForUpdateHistory(ActionMapping aMapping, ActionForm aForm,
            HttpServletRequest aRequest, HttpServletResponse aResponse) {
        OutOfCountyCaseForm oocForm = (OutOfCountyCaseForm) aForm;

        GetOOCCaseUpdateHistoryEvent request = (GetOOCCaseUpdateHistoryEvent) EventFactory
                .getInstance(OutOfCountyCaseControllerServiceNames.GETOOCCASEUPDATEHISTORY);

        request.setCaseNum(oocForm.getCaseNum());
        request.setCourtDivisionId(oocForm.getCdi());

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        dispatch.postEvent(request);

        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

        // check for system errors during create / update
        MessageUtil.processReturnException(compositeResponse);

        // check for validation errors during create / update
        Iterator historyRecords = MessageUtil.compositeToCollection(compositeResponse, OOCCaseUpdateHistoryEvent.class)
                .iterator();
        if (historyRecords.hasNext()) {
            OOCCaseUpdateHistoryEvent historyEvent = (OOCCaseUpdateHistoryEvent) historyRecords.next();
            // add history data to the form
            oocForm.setOOCCaseUpdateHistoryValues(historyEvent);
        }

        return aMapping.findForward(UIConstants.VIEW_HISTORY_SUCCESS);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
    	
    	    OutOfCountyCaseForm ooc = (OutOfCountyCaseForm) aForm;
    	    sendToErrorPage(aRequest, "");
    	    
    	    String action = ooc.getAction();
    	    if (action.equals(UIConstants.CREATE))
    	    	return aMapping.findForward(UIConstants.BACK_CREATE);
    	    else if (action.equals(UIConstants.UPDATE))
    	    	return aMapping.findForward(UIConstants.BACK_UPDATE);
    	    else if (action.equals(UIConstants.REACTIVATE))
    	    	return aMapping.findForward(UIConstants.BACK_REACTIVATE);
    	    else    	    	
                return aMapping.findForward(UIConstants.BACK);
    }
    
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        return aMapping.findForward(UIConstants.CANCEL);
    }

    private String doCreateUpdate(OutOfCountyCaseForm ooc, boolean createInd) {
        UpdateOutOfCountyCaseEvent request = (UpdateOutOfCountyCaseEvent) EventFactory
                .getInstance(OutOfCountyCaseControllerServiceNames.UPDATEOUTOFCOUNTYCASE);
       
        if(ooc.getAction().equalsIgnoreCase(UIConstants.CREATE))
        {
        	request.setCscCreate(true);
        }
        else if(ooc.getAction().equalsIgnoreCase(UIConstants.UPDATE))
        {
        	request.setCscUpdate(true);
        }
        
        ooc.fillOutOfCountyCase(request);
        
        // indicate new case to create
        request.setCreateInd(createInd);
        request.setNamePtr(ooc.getNamePtr());
        request.setNameSeqNum(ooc.getNameSeqNum());

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        dispatch.postEvent(request);

        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

        // check for system errors during create / update
        MessageUtil.processReturnException(compositeResponse);

        // check for validation errors during create / update
        Iterator validationErrors = MessageUtil.compositeToCollection(compositeResponse, ValidateResponseEvent.class)
                .iterator();
        StringBuffer message = new StringBuffer();
        boolean first = true;
        if (validationErrors.hasNext()) {
            while (validationErrors.hasNext()) {
                if (!first) {
                    message.append("\n");
                    first = false;
                }
                ValidateResponseEvent errorEvent = (ValidateResponseEvent) validationErrors.next();
                message.append(errorEvent.getMessage());
            }
            return message.toString();
        }
        // need to get the caseNum assigned to the newly created case
        if (createInd) {
            OutOfCountyCaseEvent newCase = (OutOfCountyCaseEvent) MessageUtil.compositeToCollection(compositeResponse,
                    OutOfCountyCaseEvent.class).iterator().next();
            if (newCase != null) {
                ooc.setCaseNum(newCase.getCaseNum());
                ooc.setFilingDate(newCase.getFilingDate());
            }
        }
        return null;
    }

    private void doReactivate(OutOfCountyCaseForm ooc) {
        ReactivateOutOfCountyCaseEvent request = (ReactivateOutOfCountyCaseEvent) EventFactory
                .getInstance(OutOfCountyCaseControllerServiceNames.REACTIVATEOUTOFCOUNTYCASE);
        
        ooc.fillOutOfCountyCase(request);

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(request);

        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
        MessageUtil.processReturnException(compositeResponse);

    }

/*    private String getSupervisionLength(OutOfCountyCaseForm oocForm) {
        String temp = "" + oocForm.getSupervisionLengthYear();
        if (temp.length() < 2) {
            temp = "0" + temp;
        }
        StringBuffer supervisionLength = new StringBuffer(temp);
        temp = "" + oocForm.getSupervisionLengthMonth();
        if (temp.length() < 2) {
            temp = "0" + temp;
        }
        supervisionLength.append(temp);
        temp = "" + oocForm.getSupervisionLengthDay();
        if (temp.length() < 2) {
            temp = "0" + temp;
        }
        supervisionLength.append(temp);
        return supervisionLength.toString();
    }
*/
}
