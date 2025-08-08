package ui.supervision.managetasks.assignsuperviseetoofficer.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class ReturnAssignmentSummaryAction extends JIMSBaseAction {

	@Override
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("assign.supervisee.setup", "setup");
		keyMap.put("button.finish", "returnAssignmentComplete");
	}

	public ActionForward setup(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {		
		return aMapping.findForward("displayReturnSummary");		
	}

	public ActionForward returnAssignmentComplete(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
		assignmentService.returnAssignmentToWorkgroup(caseAssignmentForm);	
		return aMapping.findForward("confirm");		
	}

}
