//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\SubmitVRNonComplianceChangeRequestCreateAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.UpdateNCResponseStatusEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;

public class SubmitVRNonComplianceChangeRequestCreateAction extends JIMSBaseAction 
{
   
   /**
    * @roseuid 47DA9D440045
    */
   public SubmitVRNonComplianceChangeRequestCreateAction() 
   {
    
   }
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
	}  
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @throws GeneralFeedbackMessageException 
    * @roseuid 47D5AF7900FC
    */
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
   {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		
	String status = "";
		if(ViolationReportConstants.STATUS_MANAGER_APPROVED.equalsIgnoreCase(vrForm.getStatusId())){
			status = ViolationReportConstants.STATUS_PENDING_MANAGER_APPROVAL;
		}else if(ViolationReportConstants.STATUS_PENDING_MANAGER_APPROVAL.equalsIgnoreCase(vrForm.getStatusId())){
			status = ViolationReportConstants.STATUS_DRAFT;
		}else if(ViolationReportConstants.STATUS_PENDING_SUBMISSION_APPROVAL.equalsIgnoreCase(vrForm.getStatusId())){
			status = ViolationReportConstants.STATUS_MANAGER_APPROVED;
		}else if(ViolationReportConstants.STATUS_SUBMISSION_APPROVED.equalsIgnoreCase(vrForm.getStatusId())){
			status = ViolationReportConstants.STATUS_MANAGER_APPROVED;
		}
		// RRY added this in to make sure the court number stays after updating
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,"caseAssignmentForm", true);
		vrForm.setCourtNum( caForm.getCourtNumber());
		vrForm.setAction("changeRequest");
		// RRY end
		String topic = ViolationReportConstants.CSTASK_TOPIC_VIOLATION_UPDATES_REQUIRED;		
		UpdateNCResponseStatusEvent uEvent = UIViolationReportHelper.prepareRequestEvent(vrForm, vrForm.getTaskSubject(), vrForm.getTaskText(), topic, status ,false);
		uEvent.setLogonId(vrForm.getTaskCreatorId());

//		MessageUtil.postRequest(uEvent);
		CompositeResponse compResp = MessageUtil.postRequest(uEvent);
		ErrorResponseEvent er = (ErrorResponseEvent) MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
		if(er != null){
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(er.getMessage(), er.getUserId()));
			saveErrors(aRequest, errors);
			vrForm.setConfirmationMessage("");
		} else {
			vrForm.setConfirmationMessage("Noncompliance response change request successfully sent.");
		}
	   return aMapping.findForward(UIConstants.FINISH);
   }

}
