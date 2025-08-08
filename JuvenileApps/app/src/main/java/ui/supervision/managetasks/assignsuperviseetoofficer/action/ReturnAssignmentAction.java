package ui.supervision.managetasks.assignsuperviseetoofficer.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class ReturnAssignmentAction extends JIMSBaseAction {

	@Override
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("assign.supervisee.setup", "setup");
		keyMap.put("button.next", "returnAssignment");
	}

	public ActionForward setup(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		caseAssignmentForm.setAssignmentReturnReason("");
		AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
		List workgroups = assignmentService.getWorkgroupsForAgency(SecurityUIHelper.getUserAgencyId(), "IN");
		caseAssignmentForm.setWorkGroupsList(workgroups);

		return aMapping.findForward("displayReturnReason");		
	}

	public ActionForward returnAssignment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {	
		String forward = "returnSummary";
//		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
//		String assingmentReturnReason = caseAssignmentForm.getAssignmentReturnReason();
//		if(assingmentReturnReason == null || assingmentReturnReason.trim().length() == 0) {
//			sendToErrorPage(aRequest, "error.assignment.returnReason.invalid");
//			forward = "displayReturnReason";
//		}
		return aMapping.findForward(forward);		
	}
}
