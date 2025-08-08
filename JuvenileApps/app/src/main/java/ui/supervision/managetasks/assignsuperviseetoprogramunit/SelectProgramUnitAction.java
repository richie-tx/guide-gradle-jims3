package ui.supervision.managetasks.assignsuperviseetoprogramunit;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.adminstaff.UIAdminStaffHelper;

public class SelectProgramUnitAction extends JIMSBaseAction {
	/**
	 * @roseuid 464368F103D5
	 */
	public SelectProgramUnitAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("assign.supervisee.setup", "displayProgramUnits");
		keyMap.put("button.reassignDivision", "displayProgramUnits");	 	
		keyMap.put("button.assignsuperviseetoprogramunit.selectprogramunit.programUnitSelection", "selectProgramUnit"); 
	}

	public ActionForward displayProgramUnits(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		try {
			CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
			List divisionList = (List) UIAdminStaffHelper.getActiveOrganizationalHeirarchy();

			if (divisionList != null && !divisionList.isEmpty()) {
				caseAssignmentForm.setDivisionList(divisionList);
			} else {
				this.sendToErrorPage(aRequest, "error.assignSupervisee.programUnitsNotFound");
			}
			caseAssignmentForm.setProgramUnitAllocationDate("");				
		} catch (Exception e) {
			e.printStackTrace();
			this.sendToErrorPage(aRequest, "error.assignSupervisee.programUnitsNotFound");
		}		
		return aMapping.findForward("displaySelectProgramUnit");
	}

	public ActionForward selectProgramUnit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		String forward = null; 
		if (validateProgramUnitAssignmentDate(aForm, aRequest)) {
			forward = "selectWorkgroup";
		} else {
			forward = "displaySelectProgramUnit";
		}
		return aMapping.findForward(forward);
	}
	
	private boolean validateProgramUnitAssignmentDate(ActionForm aForm, HttpServletRequest request) {
		boolean validDate = false;
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		String puAssignmentDate = caseAssignmentForm.getProgramUnitAllocationDate();
		if (puAssignmentDate == null || puAssignmentDate.length() == 0) {
			sendToErrorPage(request, "error.programunitassignmentdate.invalid");
		} else {
			Date assignmentDate;
			try {
				assignmentDate = DateUtil.stringToDate(puAssignmentDate, DateUtil.DATE_FMT_1);
				Date currentDate = DateUtil.getCurrentDate();
				if (DateUtil.compare(assignmentDate, currentDate, DateUtil.DATE_FMT_1) <= 0) {
					validDate = true;
				} else {
					sendToErrorPage(request, "error.programunitassignmentdate.invalid");
				}
			} catch (ParseRuntimeException e) {
				validDate = false;
				sendToErrorPage(request, "error.programunitassignmentdate.invalid");
				e.printStackTrace();
			}
		}
		return validDate;
	}

}
