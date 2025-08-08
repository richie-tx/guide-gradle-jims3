//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\SubmitNonComplianceChangeRequestCreateAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.UpdateNCResponseStatusEvent;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;

public class SubmitNonComplianceChangeRequestCreateAction extends JIMSBaseAction 
{
   
   /**
    * @roseuid 47DA9D440045
    */
   public SubmitNonComplianceChangeRequestCreateAction() 
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
    * @roseuid 47D5AF7900FC
    */
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		
		String status = "";
		if(ViolationReportConstants.STATUS_MANAGER_APPROVED.equalsIgnoreCase(csForm.getStatusId())){
			status = ViolationReportConstants.STATUS_PENDING_MANAGER_APPROVAL;
		}else if(ViolationReportConstants.STATUS_PENDING_MANAGER_APPROVAL.equalsIgnoreCase(csForm.getStatusId())){
			status = ViolationReportConstants.STATUS_DRAFT;
		}else if(ViolationReportConstants.STATUS_PENDING_SUBMISSION_APPROVAL.equalsIgnoreCase(csForm.getStatusId())){
			status = ViolationReportConstants.STATUS_MANAGER_APPROVED;
		}else if(ViolationReportConstants.STATUS_SUBMISSION_APPROVED.equalsIgnoreCase(csForm.getStatusId())){
			status = ViolationReportConstants.STATUS_MANAGER_APPROVED;
		}
		String topic = ViolationReportConstants.CSTASK_TOPIC_CASESUMMARY_UPDATES_REQUIRED;		
		UpdateNCResponseStatusEvent uEvent = UICaseSummaryHelper.prepareRequestEvent(csForm, csForm.getTaskSubject(), csForm.getTaskText(), topic, status,false);
		uEvent.setLogonId(csForm.getTaskCreatorId());

		MessageUtil.postRequest(uEvent);	
		csForm.setConfirmationMessage("Noncompliance response change request successfully sent.");
	   return aMapping.findForward(UIConstants.FINISH);
   }

}
