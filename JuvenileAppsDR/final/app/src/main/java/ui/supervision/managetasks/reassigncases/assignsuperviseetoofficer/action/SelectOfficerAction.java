package ui.supervision.managetasks.reassigncases.assignsuperviseetoofficer.action;

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
import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import messaging.supervisionstaff.GetLightParentOrganizationEvent;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
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
		
		//Business Rule: When reassigning multiple cases of a supervisee, the case with the
		//greatest officer assignment date will be chosen as default value.
		Date largestOfficerAssignDate = null;
		boolean firstTime = true;
		List caseAssignmentListForReassignment = caseAssignmentForm.getActiveCases();
		for (Iterator iterator = caseAssignmentListForReassignment.iterator(); iterator.hasNext();) {
			ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
			Date officerAssignDate = activeCase.getOfficerAssignDate();
			caseAssignmentForm.setCriminalCaseId( activeCase.getCriminalCaseId());
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
		String forward = "";
		if (validateOfficerAssignmentDate(aForm, aRequest)) {
			CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
			if (caseAssignmentForm.getProgramUnitAllocationDate() != null){
				caseAssignmentForm.setProgramBeginDateAsStr(caseAssignmentForm.getProgramUnitAllocationDate());
				caseAssignmentForm.setProgramEndDateAsStr(caseAssignmentForm.getProgramUnitAllocationDate());
				caseAssignmentForm.setReferralDateAsStr(caseAssignmentForm.getProgramUnitAllocationDate());
			}
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
			String positionNameFromCaseloadSummary = caseloadSummaryTO.getPositionName();
			IName officerNameAsIName = caseloadSummaryTO.getOfficerName();
			caseAssignmentForm.setOfficerPOI(probationOfficerInd);
			caseAssignmentForm.setOfficerPositionName(positionNameFromCaseloadSummary);
			caseAssignmentForm.setOfficerName(officerNameAsIName);
			caseAssignmentForm.setAgencyId(SecurityUIHelper.getUserAgencyId());
			forward = "addCaseNote";
		} else {
			sendToErrorPage(aRequest, "error.officerassignmentdate.invalid");
			forward = "displayOfficerList";
		}
		return aMapping.findForward(forward);
	}

	public ActionForward returnAssignment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward("returnReassignedCases");
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
