//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\SubmitReportingHistoryCreateUpdateAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.UpdateNCReportingEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
import messaging.administercompliance.reply.NonComplianceEventResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ComplianceControllerServiceNames;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;

public class SubmitReportingHistoryCreateUpdateAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D490093
	 */
	public SubmitReportingHistoryCreateUpdateAction() {

	}
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		csForm.setConfirmationMessage("");
		String forwardStr = UIConstants.FINISH;
    	UpdateNCResponseEvent event = (UpdateNCResponseEvent) EventFactory.getInstance(ComplianceControllerServiceNames.UPDATENCRESPONSE);
    	event.setRequestType(ViolationReportConstants.REQUEST_REPORTING);
    	event.setComments(csForm.getCreate1Comments());
    	event.setNcResponseId(csForm.getViolationReportId());
    	if (csForm.getViolationReportId() == null || csForm.getViolationReportId().equals("")){
    		event.setUserID(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
			event.setStatusId("DR");
			event.setOrderId(Integer.parseInt(csForm.getOrderId()));
    	}
		event.setReportType(ViolationReportConstants.REPORTTYPE_CASESUMMARY);
		event.setCaseId(new StringBuffer().append(csForm.getCdi()).append(csForm.getCaseNum()).toString());
    	event.setAddressTypeId(csForm.getAddressTypeId());
    	if (csForm.getLastContactDate() != null){
    		event.setLastContactDate(new Timestamp(csForm.getLastContactDate().getTime()));
    	}	
    	event.setCity(csForm.getAddressCity());
    	event.setStreetNumber(csForm.getAddressNumber());
    	event.setStreetName(csForm.getAddressName());
    	event.setState(csForm.getAddressStateId());
    	event.setZipcode(csForm.getAddressZipCode());	
    	UpdateNCReportingEvent ncReqEvent = null;    	
//    	 REMOVE 'M' and 'L' VALUES IN LAWVIOLATIONID FIELD BEFORE PRESISTING.  THESE VALUES ONLY NEEDED
//    	 TO UNIQUELY IDENTIFY MANUALLY ADDED VALUES FOR REMOVAL UPDATE PAGE AND LEGACY DATA FOR PROCESSING
		int listSize = csForm.getCreate1ElementsList().size();
		for (int g = 0; g < listSize; g++){
			NonComplianceEventResponseEvent ncre = (NonComplianceEventResponseEvent) csForm.getCreate1ElementsList().get(g);
			ncReqEvent = new UpdateNCReportingEvent();

			if (ncre.getNonComplianceEventId().indexOf('M') > -1 || ncre.getNonComplianceEventId().indexOf('L') > -1){
				ncReqEvent.setReportingid("");
			}else{
				ncReqEvent.setReportingid(ncre.getNonComplianceEventId());
			}
	        ncReqEvent.setDetails(ncre.getDetails());
	        ncReqEvent.setEventTypes(ncre.getEventTypesId());
			if (ncre.getDateTime() != null && !ncre.getDateTime().equals("")){
				ncReqEvent.setOccurencedate(ncre.getDateTime());						
			}
			ncReqEvent.setManualAdded(ncre.isManualAdded());
	        event.addRequest(ncReqEvent);
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = (ReturnException) MessageUtil
				.filterComposite(response, ReturnException.class);

		if (returnException == null) {
			csForm.setConfirmationMessage("Reporting History successfully processed.");
			// remove sort once sortResult tag is fixed
			csForm.setCreate1ElementsList(UICaseSummaryHelper.sortReportingHistoryList(MessageUtil.compositeToList(response, NonComplianceEventResponseEvent.class)));
//	this should only be true on initial create of case summary report	
			if (csForm.getStatusId() == null || csForm.getStatusId().equals("")){
				csForm.setStatusDesc(ViolationReportConstants.DRAFT); 
				csForm.setStatusId(ViolationReportConstants.STATUS_DRAFT);
				csForm.setStatusChangedDate(DateUtil.getCurrentDate());
				csForm.setCreatedByName(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
				csForm.setCreateDate(DateUtil.getCurrentDate());	
				
				NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
				.filterComposite(response, NCResponseResponseEvent.class);
				if(resp != null){
					csForm.setViolationReportId(resp.getNcResponseId());
				}
			}	
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"Error occured saving Reporting History information."));
			saveErrors(aRequest, errors);
			csForm.setCreate1ElementsList(new ArrayList());
			csForm.setCreate1Comments("");
		}
		// at this point, eventTypes contains a list of ids, these ids need to be replaced with descriptions for display 
		csForm.setCreate1ElementsList(UICaseSummaryHelper.reloadEventTypeDescriptions(csForm.getCreate1ElementsList(), csForm.getEventTypes()));
// end reload of event types	
		
		csForm.setCurrentReportingHistoryList(UICaseSummaryHelper.sortReportingHistoryList(csForm.getCreate1ElementsList()));
		csForm.setCurrentReportingHistoryComments(csForm.getCreate1Comments());
		csForm.setCreate1ElementsList(new ArrayList());
		csForm.setCreate1Comments("");
		
		if (csForm.isTaskflowInd()){
			forwardStr = UIConstants.TASKFLOW_FINISH;
		}
		return aMapping.findForward(forwardStr);
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		String forwardStr = UIConstants.CANCEL;
		if (csForm.getTaskId() != null && !csForm.getTaskId().equals("")){
			forwardStr = UIConstants.TASKFLOW_CANCEL;
		}
		return aMapping.findForward(forwardStr);
	}

}
