/*
 * Created on Sep 28, 2007
 *
 */
package ui.supervision.managetasks.reassigncases.supervisor.action;

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

/**
 * @author cc_rbhat
 */
public class ReassignToWorkgroupAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toProgramUnit.workgroup", "setup");
		keyMap.put("button.next", "assignWorkgroup");
	}

	public ActionForward setup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
		List workgroups = assignmentService.getWorkgroupsForAgency(SecurityUIHelper.getUserAgencyId(), "IN");
		caseAssignmentForm.setWorkGroupsList(workgroups);
		
		return mapping.findForward("initialSetup");
	}

	public ActionForward assignWorkgroup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		String reassignedWorkGroupId = caseAssignmentForm.getWorkGroupId();
		caseAssignmentForm.setReassignedWorkGroupId(reassignedWorkGroupId);

		return mapping.findForward("approveReassignmentSummary");
	}

}
