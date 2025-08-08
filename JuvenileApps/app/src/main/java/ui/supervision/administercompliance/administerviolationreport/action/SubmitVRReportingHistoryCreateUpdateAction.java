//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\SubmitVRReportingHistoryCreateUpdateAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

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
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;

public class SubmitVRReportingHistoryCreateUpdateAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D490093
	 */
	public SubmitVRReportingHistoryCreateUpdateAction() {

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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		vrForm.setConfirmationMessage("");
		String forwardStr = UIConstants.FINISH;
    	UpdateNCResponseEvent event = (UpdateNCResponseEvent) EventFactory.getInstance(ComplianceControllerServiceNames.UPDATENCRESPONSE);
    	event.setRequestType(ViolationReportConstants.REQUEST_REPORTING);
    	event.setComments(vrForm.getCreate1Comments());
    	event.setNcResponseId(vrForm.getViolationReportId());
    	if (vrForm.getViolationReportId() == null || vrForm.getViolationReportId().equals("")){
    		event.setUserID(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
			event.setStatusId("DR");
			event.setOrderId(Integer.parseInt(vrForm.getOrderId()));
    	}
		event.setReportType(ViolationReportConstants.REPORTTYPE_VIOLATION);
		event.setCaseId(new StringBuffer().append(vrForm.getCdi()).append(vrForm.getCaseNum()).toString());
    	event.setAddressTypeId(vrForm.getAddressTypeId());
    	if (vrForm.getLastContactDate() != null){
    		event.setLastContactDate(new Timestamp(vrForm.getLastContactDate().getTime()));
    	}	
    	event.setCity(vrForm.getAddressCity());
    	event.setStreetNumber(vrForm.getAddressNumber());
    	event.setStreetName(vrForm.getAddressName());
    	event.setState(vrForm.getAddressStateId());
    	event.setZipcode(vrForm.getAddressZipCode());	
    	UpdateNCReportingEvent ncReqEvent = null;    	
//    	 REMOVE 'M' and 'L' VALUES IN LAWVIOLATIONID FIELD BEFORE PRESISTING.  THESE VALUES ONLY NEEDED
//    	 TO UNIQUELY IDENTIFY MANUALLY ADDED VALUES FOR REMOVAL UPDATE PAGE AND LEGACY DATA FOR PROCESSING
		int listSize = vrForm.getCreate1ElementsList().size();
		for (int g = 0; g < listSize; g++){
			NonComplianceEventResponseEvent ncre = (NonComplianceEventResponseEvent) vrForm.getCreate1ElementsList().get(g);
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
			vrForm.setConfirmationMessage("Reporting History successfully processed.");
			
			NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil.filterComposite(response, NCResponseResponseEvent.class);
			if(resp != null){
				vrForm.setViolationReportId(resp.getNcResponseId());
				vrForm.setAddressType(resp.getAddressType());
				vrForm.setAddressCity(resp.getCity());
				vrForm.setLastContactDate(resp.getLastContactDate());
				vrForm.setAddressState(resp.getState());
				vrForm.setAddressName(resp.getStreetName());
				vrForm.setAddressNumber(resp.getStreetNumber());
				vrForm.setAddressZipCode(resp.getZipcode());
			}
			// remove sort once sortResult tag is fixed
			vrForm.setCreate1ElementsList(UIViolationReportHelper.sortReportingHistoryList(MessageUtil.compositeToList(response, NonComplianceEventResponseEvent.class)));
//	this should only be true on initial create of violation report	
			if (vrForm.getStatusId() == null || vrForm.getStatusId().equals("")){
				vrForm.setStatusDesc(ViolationReportConstants.DRAFT); 
				vrForm.setStatusId(ViolationReportConstants.STATUS_DRAFT);
				vrForm.setStatusChangedDate(DateUtil.getCurrentDate());
				vrForm.setCreatedByName(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
				vrForm.setCreateDate(DateUtil.getCurrentDate());				
			}	
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"Error occured saving Reporting History information."));
			saveErrors(aRequest, errors);
			vrForm.setCreate1ElementsList(new ArrayList());
			vrForm.setCreate1Comments("");
		}
// at this point, eventTypes contains a list of ids, these ids need to be replaced with descriptions for display 
		vrForm.setCreate1ElementsList(UIViolationReportHelper.reloadEventTypeDescriptions(vrForm.getCreate1ElementsList(), vrForm.getEventTypes()));
// end reload of event types		
		vrForm.setCurrentReportingHistoryList(UIViolationReportHelper.sortReportingHistoryList(vrForm.getCreate1ElementsList()));
		vrForm.setCurrentReportingHistoryComments(vrForm.getCreate1Comments());
		vrForm.setCreate1ElementsList(new ArrayList());
		vrForm.setCreate1Comments("");
		
		if (vrForm.isTaskflowInd()){
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		String forwardStr = UIConstants.CANCEL;
		if (vrForm.getTaskId() != null && !vrForm.getTaskId().equals("")){
			forwardStr = UIConstants.TASKFLOW_CANCEL;
		}
		return aMapping.findForward(forwardStr);
	}
}