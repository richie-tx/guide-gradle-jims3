//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\SubmitEmploymentHistoryCreateUpdateAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.UpdateNCEmploymentEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCEmploymentResponseEvent;
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
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;

public class SubmitEmploymentHistoryCreateUpdateAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D45014F
	 */
	public SubmitEmploymentHistoryCreateUpdateAction() {

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
       	event.setRequestType(ViolationReportConstants.REQUEST_EMPLOYMENT);
    	event.setComments(csForm.getCreate1Comments());
    	event.setNcResponseId(csForm.getViolationReportId());
    	if (csForm.getViolationReportId() == null || csForm.getViolationReportId().equals("")){
    		event.setUserID(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
			event.setStatusId("DR");
			event.setOrderId(Integer.parseInt(csForm.getOrderId()));
    	}      	
		event.setReportType(ViolationReportConstants.REPORTTYPE_CASESUMMARY);
		event.setCaseId(new StringBuffer().append(csForm.getCdi()).append(csForm.getCaseNum()).toString());
    			
    	UpdateNCEmploymentEvent ncReqEvent = null; 
//   	 REMOVE 'M' and 'L' VALUES IN LAWVIOLATIONID FIELD BEFORE PRESISTING.  THESE VALUES ONLY NEEDED
//   	 TO UNIQUELY IDENTIFY MANUALLY ADDED VALUES FOR REMOVAL UPDATE PAGE AND LEGACY DATA FOR PROCESSING
		int listSize = csForm.getCreate1ElementsList().size();
		for (int g = 0; g < listSize; g++){
			NCEmploymentResponseEvent ncere = (NCEmploymentResponseEvent) csForm.getCreate1ElementsList().get(g);
			ncReqEvent = new UpdateNCEmploymentEvent();
			
			if (ncere.getEmploymentId().indexOf('M') > -1 || ncere.getEmploymentId().indexOf('L') > -1){
				ncReqEvent.setEmploymentId("");
			}else{
				ncReqEvent.setEmploymentId(ncere.getEmploymentId());
			}
	        ncReqEvent.setEmployerName(ncere.getEmployerName());
	        ncReqEvent.setJobTitle(ncere.getJobTitle());
	        ncReqEvent.setStatusId(ncere.getStatusId());
	        ncReqEvent.setManualAdded(ncere.isManualAdded());
	        ncReqEvent.setSeqNum( ncere.getSeqNum() );
	        
	        event.addRequest(ncReqEvent);
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = (ReturnException) MessageUtil
				.filterComposite(response, ReturnException.class);

		if (returnException == null) {
			csForm.setConfirmationMessage("Employment History successfully processed.");
			// remove sort once sortResult tag is fixed
			List xlist = MessageUtil.compositeToList(response, NCEmploymentResponseEvent.class);
			if (!xlist.isEmpty()){
				for (int t=0; t< xlist.size(); t++){
					NCEmploymentResponseEvent ncre2 = (NCEmploymentResponseEvent) xlist.get(t);
					if ((ncre2.getStatusDesc() == null || ncre2.getStatusDesc().equals("") && ncre2.getStatusId() != null)){
						for (int x=0; x<csForm.getEmploymentStatusList().size(); x++){
							CodeResponseEvent cre = (CodeResponseEvent) csForm.getEmploymentStatusList().get(x);
							if (cre.getCodeId().equals(ncre2.getStatusId())){
								ncre2.setStatusDesc(cre.getDescription());
								break;	
							}
						}
					}
				}
			}
			csForm.setCreate1ElementsList(UICaseSummaryHelper.sortEmploymentHistoryList(xlist));
//	this should only be true on initial create of violation report	
			if (csForm.getViolationReportId() == null || csForm.getViolationReportId().equals("")){				
				NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
				.filterComposite(response, NCResponseResponseEvent.class);
				
				if(resp != null){
					csForm.setStatusId(resp.getStatus()); 
					csForm.setStatusDesc(ViolationReportConstants.DRAFT); 
					csForm.setStatusChangedDate(DateUtil.getCurrentDate());
					csForm.setCreatedByName(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
					csForm.setCreateDate(DateUtil.getCurrentDate());	
					csForm.setViolationReportId(resp.getNcResponseId());
				}
			}	
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"Error occured saving Employee History information."));
			saveErrors(aRequest, errors);
			csForm.setCreate1ElementsList(new ArrayList());
			csForm.setCreate1Comments("");
		}
		
		csForm.setCurrentEmploymentHistoryList(UICaseSummaryHelper.sortEmploymentHistoryList(csForm.getCreate1ElementsList()));
		csForm.setCurrentEmploymentHistoryComments(csForm.getCreate1Comments());
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