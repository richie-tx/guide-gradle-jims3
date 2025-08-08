/*
 * Created on Jun 27, 2007
 *
 */
package ui.supervision.administercaseload.action.reassignbyofficemanager.reassigntoprogramunit;


import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.to.CaseAssignmentTO;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author cc_rbhat
 * 
 */
public class ReassignSuperviseeToProgramUnitAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";

	private static String FWD_REASSIGN_TO_PROGRAM_UNIT_WORKGROUP = "reassignToProgramUnitWorkgroup";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toProgramUnit", "setup");
		keyMap.put("button.next", "reassignToProgramUnitWorkgroup");
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
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		caseAssignmentForm.setProgramUnitAllocationDate("");

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
	public ActionForward reassignToProgramUnitWorkgroup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String forward = null; 
		if (validateProgramUnitAssignmentDate(form, request)) {
			forward = FWD_REASSIGN_TO_PROGRAM_UNIT_WORKGROUP;
		} else {
			forward = FWD_SETUP;
		}
		return mapping.findForward(forward);

	}

	private boolean validateProgramUnitAssignmentDate(ActionForm aForm, HttpServletRequest request) {
		boolean validDate = true;
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		String puAssignmentDate = caseAssignmentForm.getProgramUnitAllocationDate();
		if (puAssignmentDate == null || puAssignmentDate.length() == 0) {
			validDate = false;
			sendToErrorPage(request, "error.programunitassignmentdate.invalid");
		} else {
			try {
				Date assignmentDate = DateUtil.stringToDate(puAssignmentDate, DateUtil.DATE_FMT_1);
				Date currentDate = DateUtil.getCurrentDate();
				
				if (assignmentDate.compareTo(currentDate) <= 0) {
					//compare the user selected program unit assignment date to the criminal-case level program unit assignment dates. 
					//the assignment date selected by user must be greater than the program unit assignment dates of all the cases
					//selected for reassignment.				
					for (Iterator iterator = caseAssignmentForm.getActiveCases().iterator(); iterator.hasNext();) {
						CaseAssignmentTO activeCase = (CaseAssignmentTO) iterator.next();
						if (assignmentDate.compareTo(activeCase.getProgramUnitAssignDate()) < 0) {
							sendToErrorPage(request, "error.programunitassignmentdate.invalid1");
							validDate = false;
							break;
						}
					}
				} else {
					validDate = false;
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
