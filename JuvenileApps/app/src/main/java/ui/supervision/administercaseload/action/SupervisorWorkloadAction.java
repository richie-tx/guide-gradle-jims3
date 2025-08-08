package ui.supervision.administercaseload.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.form.OfficerCaseload;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class SupervisorWorkloadAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.search.setup", "setup");
	}

	public ActionForward setup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm)form;
		getSupervisorWorkload(caseAssignmentForm, request);
		return mapping.findForward("initialSetup");
	}

	private void getSupervisorWorkload(CaseAssignmentForm caseAssignmentForm, HttpServletRequest request) {
		String supervisorPositionId = (String) request.getParameter("sId");
		AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
		List caseload = assignmentService.getOfficerCaseload(supervisorPositionId);
		caseAssignmentForm.setCaseloads(caseload);
		
        OfficerCaseload caseloadDetail = caseAssignmentForm.getOfficerCaseload();
		String selectedSupervisorName = "";		
		List supervisorsInDivision = caseloadDetail.getSupervisorsInDivision();
		for (int i = 0; i < supervisorsInDivision.size(); i++) {
			CSCDSupervisionStaffResponseEvent supervisor = (CSCDSupervisionStaffResponseEvent) supervisorsInDivision.get(i);
			if (supervisor.getStaffPositionId().equals(supervisorPositionId)) {
				selectedSupervisorName = supervisor.getAssignedNameQualifiedByPositionName();	
				break;
			}			
		}
		caseloadDetail.setSelectedSupervisorName(selectedSupervisorName);
	}
	
}
