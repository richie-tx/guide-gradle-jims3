//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\SubmitVRReasonForTransferCreateUpdateAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.UpdateNCReasonForTransferEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
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
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;

public class SubmitVRReasonForTransferCreateUpdateAction extends JIMSBaseAction {

	/**
	 * 47DA9D470391
	 */
	public SubmitVRReasonForTransferCreateUpdateAction() {

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
		event.setRequestType(ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER);
    	event.setComments( vrForm.getIsExtended() );
    	event.setNcResponseId( vrForm.getViolationReportId() );
    	event.setCommentType( "EXTENDED" );
    	if (vrForm.getViolationReportId() == null || vrForm.getViolationReportId().equals("")){
    		event.setUserID(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
			event.setStatusId("DR");
			event.setOrderId(Integer.parseInt(vrForm.getOrderId()));
    	}
		event.setReportType(ViolationReportConstants.REPORTTYPE_VIOLATION);
		event.setCaseId(new StringBuffer().append(vrForm.getCdi()).append(vrForm.getCaseNum()).toString());
    	
    	UpdateNCReasonForTransferEvent reqEvent = new UpdateNCReasonForTransferEvent();    	
		List update2Elements = vrForm.getCreate2ElementsList();
		for (int g = 0; g < update2Elements.size(); g++){
			CodeResponseEvent cre = (CodeResponseEvent) update2Elements.get( g );
			reqEvent = new UpdateNCReasonForTransferEvent();
			reqEvent.setReasonForTransferCodeId(cre.getCode());
			reqEvent.setReasonForTransferCodeDesc(cre.getDescription());
	    	event.addRequest(reqEvent);
		}

//	add logic to persist data
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = (ReturnException) MessageUtil
				.filterComposite(response, ReturnException.class);

		if (returnException == null) {
			vrForm.setConfirmationMessage("Reason For Transfer successfully processed.");
//			this should only be true on initial create of violation report	
			if (vrForm.getStatusId() == null || vrForm.getStatusId().equals("")){
				vrForm.setStatusDesc(ViolationReportConstants.DRAFT); 
				vrForm.setStatusId(ViolationReportConstants.STATUS_DRAFT);
				vrForm.setStatusChangedDate(DateUtil.getCurrentDate());
				vrForm.setCreatedByName(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
				vrForm.setCreateDate(DateUtil.getCurrentDate());	
				
				NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil.filterComposite(response, NCResponseResponseEvent.class);
				if(resp != null){
					vrForm.setViolationReportId(resp.getNcResponseId());
				}
			}			
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"Error occured saving Reason For Transfer information"));
			saveErrors(aRequest, errors);
			vrForm.setCreate2ElementsList(new ArrayList());
			vrForm.setCreate1Comments("");
		}

		vrForm.setCurrentReasonForTransferList(vrForm.getCreate2ElementsList());
		vrForm.setCurrentReasonForTransferComments(vrForm.getIsExtended());
		vrForm.setCreate2ElementsList(new ArrayList());
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
		if(vrForm.getCurrentReasonForTransferComments() != null && !vrForm.getCurrentReasonForTransferComments().equals(vrForm.getIsExtended())){
			vrForm.setIsExtended(vrForm.getCurrentReasonForTransferComments());
		}
		String forwardStr = UIConstants.CANCEL;
		if (vrForm.getTaskId() != null && !vrForm.getTaskId().equals("")){
			forwardStr = UIConstants.TASKFLOW_CANCEL;
		}
		return aMapping.findForward(forwardStr);
	}
}
