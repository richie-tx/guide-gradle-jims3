//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\HandleViolationReportSelectionAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercompliance.administercasehistory.UICaseHistoryHelper;
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;
import ui.supervision.posttrial.form.CSCDTaskForm;

public class HandleViolationReportSelectionAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D3E03CF
	 */
	public HandleViolationReportSelectionAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.view", "view");
		keyMap.put("button.createViolationReport", "createViolationReport");
		keyMap.put("button.delete", "delete");
		keyMap.put("button.update", "update");		
		keyMap.put("button.maintain", "maintain");	
		keyMap.put("button.createTask", "createTask");				
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward createViolationReport(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ActionForward forward = aMapping.findForward( UIConstants.VIOLATION_REPORTS_SUCCESS );
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		vrForm.clearCurrents();
		vrForm.setStatusId("");
		vrForm.setAllowUpdate(UIConstants.YES);
		vrForm.setSecondaryAction(UIConstants.CREATE);
		vrForm.setViolationReportId("");
		vrForm.setConfirmationMessage("");
		
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,	"caseAssignmentForm", true);
		
		UICaseHistoryHelper helper = new UICaseHistoryHelper();
		
		if (!helper.isCaseAssigned( caForm )){
			
			// Send back to results list if an error
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Cannot create a Violation Report. Case is not assigned to an Officer");
			forward =  aMapping.findForward( UIConstants.EXCEPTIONS );
		}
		
		return forward;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		vrForm.clearCurrents();
		vrForm.setAllowUpdate("");	
		vrForm.setSecondaryAction(UIConstants.VIEW);
		vrForm.setAllowMaintain("");
		if (SecurityUIHelper.isUserSA()){
			vrForm.setAllowMaintain(UIConstants.YES);
		}
	 	UIViolationReportHelper.LoadCurrentVRInfo(vrForm);
		vrForm.setConfirmationMessage("");
        return aMapping.findForward(UIConstants.VIEW_SUCCESS);
	}	

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward delete(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		vrForm.setAllowUpdate("");	
		vrForm.setSecondaryAction(UIConstants.DELETE);
		UIViolationReportHelper.LoadCurrentVRInfo(vrForm);
		vrForm.setConfirmationMessage("");
		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ActionForward forward = aMapping.findForward( UIConstants.UPDATE_SUCCESS );
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		vrForm.clearCurrents();
		vrForm.clearSelecteds();
		vrForm.setAllowUpdate(UIConstants.YES);	
		vrForm.setSecondaryAction(UIConstants.UPDATE);
		UIViolationReportHelper.LoadCurrentVRInfo(vrForm);	
		vrForm.setConfirmationMessage("");
		
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,	"caseAssignmentForm", true);
		
		UICaseHistoryHelper histHelper = new UICaseHistoryHelper();
		
		if (!histHelper.isCaseAssigned( caForm )){
			
			// Send back to results list if an error
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Cannot create a Violation Report. Case is not assigned to an Officer");
			forward =  aMapping.findForward( UIConstants.EXCEPTIONS );
		}
		
		return forward;
	}	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward maintain(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		vrForm.clearCurrents();		
		vrForm.setAllowUpdate(UIConstants.YES);	
		vrForm.setSecondaryAction("maintain");
		UIViolationReportHelper.LoadCurrentVRInfo(vrForm);	
		vrForm.setConfirmationMessage("");
		return aMapping.findForward(UIConstants.MAINTAIN_SUCCESS);
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward createTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CSCDTaskForm ctForm = (CSCDTaskForm) getSessionForm(aMapping, aRequest, "cscdTaskForm", true);
		ctForm.setSearchById("");
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		ctForm.setTaskCdi(vrForm.getCdi());
		ctForm.setTaskCaseNumber(vrForm.getCaseNum());
		ctForm.setSpn(vrForm.getSuperviseeId());
		return aMapping.findForward(UIConstants.CREATE_TASK_SUCCESS);
	}	
}
