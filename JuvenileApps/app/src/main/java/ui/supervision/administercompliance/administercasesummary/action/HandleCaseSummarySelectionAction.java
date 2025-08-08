//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\HandleCaseSummarySelectionAction.java

package ui.supervision.administercompliance.administercasesummary.action;

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
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.posttrial.form.CSCDTaskForm;

public class HandleCaseSummarySelectionAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D3E03CF
	 */
	public HandleCaseSummarySelectionAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.view", "view");
		keyMap.put("button.createCaseSummary", "createCaseSummary");
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
	public ActionForward createCaseSummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		csForm.clearCurrents();	
		csForm.setStatusId("");
		csForm.setViolationReportId("");
		csForm.setAllowUpdate(UIConstants.YES);
		csForm.setSecondaryAction(UIConstants.CREATE);
		csForm.setConfirmationMessage("");
		ActionForward forward = aMapping.findForward(UIConstants.CASE_SUMMARY_SUCCESS);
		
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,	"caseAssignmentForm", true);
		
		UICaseHistoryHelper helper = new UICaseHistoryHelper();
		
		if (!helper.isCaseAssigned( caForm )){
			
			// Send back to results list if an error
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Cannot create a Case Summary. Case is not assigned to an Officer");
			forward = aMapping.findForward( UIConstants.EXCEPTIONS );
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		csForm.clearCurrents();
		csForm.setAllowUpdate("");	
		csForm.setSecondaryAction(UIConstants.VIEW);
		csForm.setAllowMaintain("");
		if (SecurityUIHelper.isUserSA()){
			csForm.setAllowMaintain(UIConstants.YES);
		}
	 	UICaseSummaryHelper.LoadCurrentCSInfo(csForm);
	 	csForm.setConfirmationMessage("");
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		csForm.setAllowUpdate("");	
		csForm.setSecondaryAction(UIConstants.DELETE);
		UICaseSummaryHelper.LoadCurrentCSInfo(csForm);
		csForm.setConfirmationMessage("");
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		csForm.clearCurrents();
		csForm.clearSelecteds();
		csForm.setAllowUpdate(UIConstants.YES);	
		csForm.setSecondaryAction(UIConstants.UPDATE);
		UICaseSummaryHelper.LoadCurrentCSInfo(csForm);
		csForm.setConfirmationMessage("");
		
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,	"caseAssignmentForm", true);
		
		UICaseHistoryHelper helper = new UICaseHistoryHelper();
		
		if (!helper.isCaseAssigned( caForm )){
			
			// Send back to results list if an error
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Cannot create a Case Summary. Case is not assigned to an Officer");
			forward = aMapping.findForward( UIConstants.EXCEPTIONS );
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		csForm.clearCurrents();		
		csForm.setAllowUpdate(UIConstants.YES);	
		csForm.setSecondaryAction("maintain");
		UICaseSummaryHelper.LoadCurrentCSInfo(csForm);	
		csForm.setConfirmationMessage("");
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		ctForm.setTaskCdi(csForm.getCdi());
		ctForm.setTaskCaseNumber(csForm.getCaseNum());		
		return aMapping.findForward(UIConstants.CREATE_TASK_SUCCESS);
	}	
}
