//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\SubmitVREmploymentHistoryCreateUpdateAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

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
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;

public class SubmitVREmploymentHistoryCreateUpdateAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D45014F
	 */
	public SubmitVREmploymentHistoryCreateUpdateAction() {

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
       	event.setRequestType(ViolationReportConstants.REQUEST_EMPLOYMENT);
    	event.setComments(vrForm.getCreate1Comments());
    	event.setNcResponseId(vrForm.getViolationReportId());
    	if (vrForm.getViolationReportId() == null || vrForm.getViolationReportId().equals("")){
    		event.setUserID(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
			event.setStatusId("DR");
			event.setOrderId(Integer.parseInt(vrForm.getOrderId()));
		}      	
    	event.setReportType(ViolationReportConstants.REPORTTYPE_VIOLATION);
		event.setCaseId(new StringBuffer().append(vrForm.getCdi()).append(vrForm.getCaseNum()).toString());
				
    	UpdateNCEmploymentEvent ncReqEvent = null;
		int listSize = vrForm.getCreate1ElementsList().size();
		for (int g = 0; g < listSize; g++){
			NCEmploymentResponseEvent ncere = (NCEmploymentResponseEvent) vrForm.getCreate1ElementsList().get(g);
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
			vrForm.setConfirmationMessage("Employment History successfully processed.");
			// remove sort once sortResult tag is fixed
			List xlist = MessageUtil.compositeToList(response, NCEmploymentResponseEvent.class);
			if (!xlist.isEmpty()){
				for (int t=0; t< xlist.size(); t++){
					NCEmploymentResponseEvent ncre2 = (NCEmploymentResponseEvent) xlist.get(t);
					if ((ncre2.getStatusDesc() == null || ncre2.getStatusDesc().equals("") && ncre2.getStatusId() != null)){
						for (int x=0; x<vrForm.getEmploymentStatusList().size(); x++){
							CodeResponseEvent cre = (CodeResponseEvent) vrForm.getEmploymentStatusList().get(x);
							if (cre.getCodeId().equals(ncre2.getStatusId())){
								ncre2.setStatusDesc(cre.getDescription());
								break;	
							}
						}
					}
				}
			}
			vrForm.setCreate1ElementsList(UIViolationReportHelper.sortEmploymentHistoryList(xlist));
//	this should only be true on initial create of violation report	
			if (vrForm.getViolationReportId() == null || vrForm.getViolationReportId().equals("")){				
				NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
				.filterComposite(response, NCResponseResponseEvent.class);
				
				if(resp != null){
					vrForm.setStatusId(resp.getStatus()); 
					vrForm.setStatusDesc(ViolationReportConstants.DRAFT); 
					vrForm.setStatusChangedDate(DateUtil.getCurrentDate());
					vrForm.setCreatedByName(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
					vrForm.setCreateDate(DateUtil.getCurrentDate());	
					vrForm.setViolationReportId(resp.getNcResponseId());
				}
			}	
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"Error occurred saving Employment History information."));
			saveErrors(aRequest, errors);
			vrForm.setCreate1ElementsList(new ArrayList());
			vrForm.setCreate1Comments("");
		}
		
		vrForm.setCurrentEmploymentHistoryList(UIViolationReportHelper.sortEmploymentHistoryList(vrForm.getCreate1ElementsList()));
		vrForm.setCurrentEmploymentHistoryComments(vrForm.getCreate1Comments());
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