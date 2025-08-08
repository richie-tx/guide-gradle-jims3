/*
 * Created on Jun 27, 2007
 */
package ui.supervision.administercaseload.action.reassignbyofficemanager.reassigntoprogramunit;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.domintf.ICaseAssignment;
import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author cc_rbhat
 */
public class ReassignToPUWorkgroupCasenoteAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";

	private static String FWD_ASSIGN_TO_PROGARAM_UNIT_SUMMARY = "assignToProgramUnitSummary";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toProgramUnit.casenote", "setup");
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

		setupCaseNote(caseAssignmentForm);

		return mapping.findForward(FWD_SETUP);
	}

	/**
	 * Sample case note: "980124545 01010, 212 has been reassigned to Mental
	 * Health on 12/12/2005, 12:20."
	 * 
	 * @param caseAssignmentForm
	 */
	private void setupCaseNote(CaseAssignmentForm caseAssignmentForm) {
		StringBuffer sb = new StringBuffer();
		Iterator iterator = caseAssignmentForm.getActiveCases().iterator();
		String caseNum = "";
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

		sb.append(" has been reassigned to ");
		sb.append(caseAssignmentForm.getProgramUnitName());
		sb.append(" on ");
//		sb.append(DateUtil.dateToString(today, CaseloadConstants.CASENOTE_DATE_FMT));
		sb.append(caseAssignmentForm.getProgramUnitAllocationDate());		
		sb.append(".");

		caseAssignmentForm.setCasenoteText(sb.toString());
		caseAssignmentForm.setCasenoteDate(caseAssignmentForm.getProgramUnitAllocationDate());
		Date today = DateUtil.getCurrentDate();
		caseAssignmentForm.setCasenoteTime(DateUtil.dateToString(today, DateUtil.TIME24_FMT_1));
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

		return mapping.findForward(FWD_ASSIGN_TO_PROGARAM_UNIT_SUMMARY);
	}
}
