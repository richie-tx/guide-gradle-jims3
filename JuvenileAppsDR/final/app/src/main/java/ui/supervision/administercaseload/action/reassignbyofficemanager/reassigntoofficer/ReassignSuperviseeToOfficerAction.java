/*
 * Created on Jun 27, 2007
 */
package ui.supervision.administercaseload.action.reassignbyofficemanager.reassigntoofficer;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.to.CaseloadSummaryTO;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

/**
 * @author cc_rbhat
 */
public class ReassignSuperviseeToOfficerAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";

	private static String FWD_REASSIGN_TO_OFFICER_ADD_CASENOTE = "reassignToOfficerCaseNote";

	private static String FWD_GET_PROGRAM_UNIT_SUPERVISORS = "getProgramUnitSupervisors";

	private static String FWD_GET_SUPERVISOR_CASELOAD = "getSupervisiorCaseload";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toOfficer", "setup");
		keyMap.put("button.next", "reassignToSelectedOfficer"); 
		keyMap.put("button.getSupervisors", "getProgramUnitSupervisors");
		keyMap.put("button.viewCaseloadSummary", "getSupervisorCaseload");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward setup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		String officerPositionId = caseAssignmentForm.getOfficerPositionId();
		caseAssignmentForm.setStaffPositionIdBeforeReassignment(officerPositionId);

		getSupervisors(caseAssignmentForm);
		getCaseloads(caseAssignmentForm); 
		
		//Business Rule: When reassigning multiple cases of a supervisee, the case with the
		//greatest officer assignment date will be chosen as default value.
		Date largestOfficerAssignDate = null;
		boolean firstTime = true;
		List caseAssignmentListForReassignment = caseAssignmentForm.getActiveCases();
		for (Iterator iterator = caseAssignmentListForReassignment.iterator(); iterator.hasNext();) {
			ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
			Date officerAssignDate = activeCase.getOfficerAssignDate();
			if (officerAssignDate == null)
				continue;
			if (firstTime) {
				largestOfficerAssignDate = officerAssignDate;
				firstTime = false;
			} else {
				if (officerAssignDate.after(largestOfficerAssignDate)) {
					largestOfficerAssignDate = officerAssignDate;
				}
			}
		}
		String officerAssignmentDate = null;
		if (largestOfficerAssignDate != null) {
			officerAssignmentDate = DateUtil.dateToString(largestOfficerAssignDate, DateUtil.DATE_FMT_1);			
		} else {
			officerAssignmentDate = DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1);
		}
		caseAssignmentForm.setOfficerAssignmentDate(officerAssignmentDate);

		return mapping.findForward(FWD_SETUP);
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward reassignToSelectedOfficer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String forward = "";
		if (validateOfficerAssignmentDate(form, request)) {
			forward = FWD_REASSIGN_TO_OFFICER_ADD_CASENOTE;
		} else {
			forward = FWD_SETUP;
		}
		return mapping.findForward(forward);
	}

	public ActionForward getProgramUnitSupervisors(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;

		getSupervisors(caseAssignmentForm);

		return mapping.findForward(FWD_GET_PROGRAM_UNIT_SUPERVISORS);
	}

	public ActionForward getSupervisorCaseload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		String supervisorPositionId = caseAssignmentForm.getSupervisorPositionId();
		IName supervisorName = null;
		for (Iterator iterator = caseAssignmentForm.getSupervisionStaff().iterator(); iterator.hasNext();) {
            CSCDSupervisionStaffResponseEvent supervisor = (CSCDSupervisionStaffResponseEvent) iterator.next();
            if (supervisorPositionId.equals(supervisor.getStaffPositionId())) {
                supervisorName = new NameBean(supervisor.getAssignedName().getFirstName(), 
                		supervisor.getAssignedName().getMiddleName(), supervisor.getAssignedName().getLastName());
                break;
            }
		}
		caseAssignmentForm.setSupervisorName(supervisorName);
		getCaseloads(caseAssignmentForm);

		return mapping.findForward(FWD_GET_SUPERVISOR_CASELOAD);
	}

	private void getCaseloads(CaseAssignmentForm caseAssignmentForm) {
		String supervisorPositionId = caseAssignmentForm.getSupervisorPositionId();
		AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
		List caseload = assignmentService.getOfficerCaseload(supervisorPositionId);
		caseAssignmentForm.setCaseloads(caseload);
	}

	private void getSupervisors(CaseAssignmentForm caseAssignmentForm) throws Exception {
		String programUnitId = caseAssignmentForm.getProgramUnitId();
		AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
		List supervisors = assignmentService.getSupervisorsForProgramUnits(programUnitId);
		caseAssignmentForm.setSupervisionStaff(supervisors);
	}

	/**
	 * Business Rule: The current officer assignment date cannot be a future date
	 * and should be greater than last officer assignment date.
	 * Previous Officer Assign Date <= Officer Assign Date <= Current Date
	 */
	private boolean validateOfficerAssignmentDate(ActionForm aForm, HttpServletRequest request) {
		boolean validDate = false;
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		String officerAssignmentDate = caseAssignmentForm.getOfficerAssignmentDate();
		if (officerAssignmentDate == null || officerAssignmentDate.trim().length() == 0) {
			sendToErrorPage(request, "error.officerassignmentdate.invalid");
		} else {
			try {
				Date currentOfficerAssignmentDate = DateUtil.stringToDate(officerAssignmentDate, DateUtil.DATE_FMT_1);				
				Date today = new Date();
				
				Date largestOfficerAssignDate = null;
				boolean firstTime = true;
				List caseAssignmentListForReassignment = caseAssignmentForm.getActiveCases();
				for (Iterator iterator = caseAssignmentListForReassignment.iterator(); iterator.hasNext();) {
					ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
					Date officerAssignDate = activeCase.getOfficerAssignDate();
					if (officerAssignDate == null)
						continue;
					if (firstTime) {
						largestOfficerAssignDate = officerAssignDate;
						firstTime = false;
					} else {
						if (officerAssignDate.after(largestOfficerAssignDate)) {
							largestOfficerAssignDate = officerAssignDate;
						}
					}
				}
				if (largestOfficerAssignDate != null &&
						currentOfficerAssignmentDate.getTime() <= today.getTime() &&
						currentOfficerAssignmentDate.getTime() >= largestOfficerAssignDate.getTime()) {
					validDate = true;
				} else if (largestOfficerAssignDate == null &&
						currentOfficerAssignmentDate.getTime() <= today.getTime()) {
					validDate = true;
				} else {
					validDate = false;
					sendToErrorPage(request, "error.officerassignmentdate.invalid");								
				}
			} catch (ParseRuntimeException ex) {
				sendToErrorPage(request, "error.officerassignmentdate.invalid");
				ex.printStackTrace();
			}
		}
		return validDate;
	}
	
}
