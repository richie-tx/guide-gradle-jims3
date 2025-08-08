package ui.supervision.managetasks.assignsuperviseetoofficer.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.to.CaseloadSummaryTO;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import messaging.supervisionstaff.GetLightParentOrganizationEvent;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class SelectOfficerAction extends JIMSBaseAction {
	/**
	 * @roseuid 464368F103D5
	 */
	public SelectOfficerAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("assign.supervisee.setup", "displayOfficer");
		keyMap.put("button.assignsuperviseetoofficer.selectofficer.selectofficer", "selectOfficer");
		keyMap.put("button.allocatesuperviseetosupervisor.selectsupervisor.viewsupervisors", "viewSupervisors");
		keyMap.put("button.assignsuperviseetoofficer.selectofficer.viewcaseloadsummary", "viewCaseloadSummary");	
		keyMap.put("button.link", "returnAssignment");
	}

	public ActionForward viewSupervisors(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		String programUnitId = caseAssignmentForm.getProgramUnitId();

		AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
		List supervisors = assignmentService.getSupervisorsForProgramUnits(programUnitId);
		caseAssignmentForm.setSupervisionStaff(supervisors);
		return aMapping.findForward("displayOfficerList");
	}

	public ActionForward displayOfficer(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		String programUnitId = caseAssignmentForm.getProgramUnitId();
		AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
		
		GetLightParentOrganizationEvent event = new GetLightParentOrganizationEvent();
		
		event.setProgramUnitId( programUnitId );
		OrganizationResponseEvent division = (OrganizationResponseEvent) 
									MessageUtil.postRequest( event, OrganizationResponseEvent.class );
		String divisionId = division.getOrganizationId();
		
		List supervisors = assignmentService.getLightSupervisorsForOrg( divisionId );

		String supervisorPositionId = caseAssignmentForm.getSupervisorPositionId();
		List caseload = assignmentService.getOfficerCaseload(supervisorPositionId);

		String divisionName = division.getDescription();
		caseAssignmentForm.setDivisionName(divisionName);
		caseAssignmentForm.setSupervisionStaff(supervisors);
		caseAssignmentForm.setCaseloads(caseload);
		
		//Business Rule: For a new case default value for officer assignment date is current system date.
		String officerAssignmentDate = DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1);
		caseAssignmentForm.setOfficerAssignmentDate(officerAssignmentDate);
		return aMapping.findForward("displayOfficerList");
	}

	public ActionForward viewCaseloadSummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
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

		List caseload = assignmentService.getOfficerCaseload(supervisorPositionId);
		caseAssignmentForm.setCaseloads(caseload);
		return aMapping.findForward("displayOfficerList");
	}

	public ActionForward selectOfficer(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		String forward = null;
		if (validateOfficerAssignmentDate(aForm, aRequest)) {
			CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
			String officerPositionId = caseAssignmentForm.getOfficerPositionId();
			List caseloadList = caseAssignmentForm.getCaseloads();
			Iterator caseloadIterator = caseloadList.iterator();
			CaseloadSummaryTO caseloadSummaryTO = null;
			while (caseloadIterator.hasNext()) {
				caseloadSummaryTO = (CaseloadSummaryTO) caseloadIterator.next();
				if (caseloadSummaryTO.getOfficerPositionId().equals(officerPositionId)) {
					break;
				}
			}
			String probationOfficerInd = caseloadSummaryTO.getProbationOfficerInd();
			caseAssignmentForm.setOfficerPOI(probationOfficerInd);

			String positionNameFromCaseloadSummary = caseloadSummaryTO.getPositionName();
			caseAssignmentForm.setOfficerPositionName(positionNameFromCaseloadSummary);
			caseAssignmentForm.setProgramBeginDateAsStr(caseAssignmentForm.getProgramUnitAllocationDate());

			IName officerNameAsIName = caseloadSummaryTO.getOfficerName();
			caseAssignmentForm.setOfficerName(officerNameAsIName);
			forward = "addCaseNote";
		} else {
			forward = "displayOfficerList";
		}
		return aMapping.findForward(forward);
	}
	
	public ActionForward returnAssignment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward("returnAssignment");
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
				if (currentOfficerAssignmentDate.getTime() <= today.getTime()) {
					validDate = true;
				} else {
					validDate = false;
					sendToErrorPage(request, "error.positionAssignDateisFutureDate");
				}
			} catch (ParseRuntimeException ex) {
				sendToErrorPage(request, "error.officerassignmentdate.invalid");
				ex.printStackTrace();
			}
		}
		return validDate;
	}
	
}
