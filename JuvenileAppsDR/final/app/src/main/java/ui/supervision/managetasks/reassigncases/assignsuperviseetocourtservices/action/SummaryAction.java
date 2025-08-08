package ui.supervision.managetasks.reassigncases.assignsuperviseetocourtservices.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class SummaryAction extends JIMSBaseAction {
	

	/**
	 * @roseuid 464368F103D5
	 */
	public SummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.paperFileReceived", "displaySummary");
		keyMap.put("button.backToTaskSearchResults", "confirmSuperviseeAssignmentToCSR");
	}

	public ActionForward displaySummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		try {
			CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
			String taskId = caseAssignmentForm.getTaskId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aMapping.findForward("displayConfirmation");
	}

	public ActionForward confirmSuperviseeAssignmentToCSR(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward("taskList");
	}
}
