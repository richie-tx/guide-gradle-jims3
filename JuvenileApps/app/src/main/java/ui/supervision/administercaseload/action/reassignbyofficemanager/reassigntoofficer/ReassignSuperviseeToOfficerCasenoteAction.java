/*
 * Created on Jun 27, 2007
 */
package ui.supervision.administercaseload.action.reassignbyofficemanager.reassigntoofficer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.domintf.ICaseloadSummary;
import messaging.contact.domintf.IName;
import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author cc_rbhat
 */
public class ReassignSuperviseeToOfficerCasenoteAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";

	private static String FWD_ASSIGN_TO_OFFICER_SUMMARY = "assignToOfficerSummary";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toOfficer.addCaseNote", "setup");
		keyMap.put("button.next", "createCasenote");
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

		String selectedOfficerPositionId = caseAssignmentForm.getOfficerPositionId();
		List caseLoads = caseAssignmentForm.getCaseloads();
		for (Iterator iterator = caseLoads.iterator(); iterator.hasNext();) {
			ICaseloadSummary caseLoad = (ICaseloadSummary) iterator.next();
			if (caseLoad.getOfficerPositionId().equalsIgnoreCase(selectedOfficerPositionId)) {
				IName officerName = caseLoad.getOfficerName();
				String poi = caseLoad.getProbationOfficerInd();
				String positionName = caseLoad.getPositionName();

				caseAssignmentForm.setOfficerName(officerName);
				caseAssignmentForm.setOfficerPositionName(positionName);
				caseAssignmentForm.setOfficerPOI(poi);
				break;
			}
		}
		setupCaseNote(caseAssignmentForm);
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
	public ActionForward createCasenote(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;

		return mapping.findForward(FWD_ASSIGN_TO_OFFICER_SUMMARY);
	}

	private void setupCaseNote(CaseAssignmentForm caseAssignmentForm) {
		StringBuffer sb = new StringBuffer();
		String caseNum = "";
		Iterator iterator = caseAssignmentForm.getActiveCases().iterator();
		while (iterator.hasNext()) {
			ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
			caseNum = activeCase.getCriminalCaseId();
			if (caseNum != null & caseNum.length() > 3){
				caseNum = caseNum.substring(0,3) + " " + caseNum.substring(3,caseNum.length());
			}
			sb.append(caseNum);
			sb.append(", ");
			sb.append(activeCase.getCourtId());
			sb.append(", ");
		}
		sb.append(" assigned to ");
		IName officerName = caseAssignmentForm.getOfficerName();
		if (officerName == null) {
			sb.append("No Officer Assigned");
		} else if (officerName.getFormattedName() == null || officerName.getFormattedName().length() == 0) {
			sb.append("No Officer Assigned");			
		} else {
			sb.append(officerName.getFormattedName());			
		}
		sb.append(" on ");
		sb.append(caseAssignmentForm.getOfficerAssignmentDate());
		sb.append(".");

		caseAssignmentForm.setCasenoteText(sb.toString());
		caseAssignmentForm.setCasenoteDate(caseAssignmentForm.getOfficerAssignmentDate());
		caseAssignmentForm.setCasenoteTime(DateUtil.dateToString(DateUtil.getCurrentDate(), "hh:mm a"));
	}
}
