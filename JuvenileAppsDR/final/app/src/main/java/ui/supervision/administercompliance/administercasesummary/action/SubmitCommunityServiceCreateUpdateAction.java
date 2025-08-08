//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\SubmitCommunityServiceCreateUpdateAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.UpdateNCCommunityServiceEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCCommunityServiceResponseEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
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
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;

public class SubmitCommunityServiceCreateUpdateAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D4402B6
	 */
	public SubmitCommunityServiceCreateUpdateAction() {

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
    	event.setRequestType(ViolationReportConstants.REQUEST_COMMUNITY_SERVICE);
    	event.setComments(csForm.getCreate1Comments());
    	event.setNcResponseId(csForm.getViolationReportId());
    	if (csForm.getViolationReportId() == null || csForm.getViolationReportId().equals("")){
    		event.setUserID(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
			event.setStatusId(ViolationReportConstants.STATUS_DRAFT);
			event.setOrderId(Integer.parseInt(csForm.getOrderId()));
    	}
		event.setReportType(ViolationReportConstants.REPORTTYPE_CASESUMMARY);
		event.setCaseId(new StringBuffer().append(csForm.getCdi()).append(csForm.getCaseNum()).toString());
    	
        UpdateNCCommunityServiceEvent ncReqEvent =  new UpdateNCCommunityServiceEvent();
        ncReqEvent.setCaseId(csForm.getCaseNum());
        ncReqEvent.setHoursCompleted(csForm.getHoursCompleted());
        ncReqEvent.setHoursOrdered(csForm.getHoursOrdered());
        event.addRequest(ncReqEvent);
        
        
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = (ReturnException) MessageUtil
				.filterComposite(response, ReturnException.class);

		if (returnException == null) {
			csForm.setConfirmationMessage("Community Service successfully processed.");
			csForm.setCurrentCommunityServiceComments(csForm.getCreate1Comments());
//	this should only be true on initial create of violation report	
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
							"Error occured saving Community Service information."));
			saveErrors(aRequest, errors);
		}
		csForm.setCreate1Comments("");
		NCCommunityServiceResponseEvent resp = (NCCommunityServiceResponseEvent) MessageUtil.filterComposite(response, NCCommunityServiceResponseEvent.class);
        if(resp != null){
        	csForm.setCurrentHoursCompleted(resp.getHoursCompleted());
        	csForm.setCurrentHoursOrdered(resp.getHoursOrdered());
        }

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
