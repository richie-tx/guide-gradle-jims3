package ui.supervision.managetasks.reassigncases.allocatesuperviseetosupervisor.action;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.transferobjects.OrganizationTO;
import mojo.km.utilities.DateUtil;
import naming.CaseloadConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class AddCasenoteForSuperviseeAllocationAction extends JIMSBaseAction {

	/**
	 * @roseuid 464368F103D5
	 */
	public AddCasenoteForSuperviseeAllocationAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("assign.supervisee.setup", "displayCaseNote");
		keyMap.put("button.allocatesuperviseetosupervisor.selectsupervisor.addCasenote", "addCaseNote");
	}

	public ActionForward displayCaseNote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		//program unit setup
		String programUnitId = caseAssignmentForm.getProgramUnitId();
		OrganizationTO programUnit = AssignSuperviseeService.getInstance().getProgramUnitDetails(programUnitId);
		String programUnitName = programUnit.getDescription();
		caseAssignmentForm.setProgramUnitName(programUnitName);
		
		//case note setup
		Date today = DateUtil.getCurrentDate();
		String caseNum = "";
		StringBuffer sb = new StringBuffer();
		sb.append("Paper file for ");
		Iterator iterator = caseAssignmentForm.getCaseAssignments().iterator();
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
		sb.append(caseAssignmentForm.getSuperviseeNameStr());
		sb.append(" SPN ");
		sb.append(caseAssignmentForm.getDefendantId());
		sb.append(" allocated to Supervisor ");
		sb.append(caseAssignmentForm.getSupervisorName().getLastName());
		sb.append(", ");
		sb.append(caseAssignmentForm.getSupervisorName().getFirstName());
		sb.append(" on ");
		sb.append(DateUtil.dateToString(today, CaseloadConstants.CASENOTE_DATE_FMT));
		sb.append(".");

		caseAssignmentForm.setCasenoteText(sb.toString());
		caseAssignmentForm.setCasenoteDate(DateUtil.dateToString(today, DateUtil.DATE_FMT_1));
		caseAssignmentForm.setCasenoteTime(DateUtil.dateToString(today, DateUtil.TIME24_FMT_1));

		return aMapping.findForward("displayCaseNote");
	}

	public ActionForward addCaseNote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward("summarize");
	}

}
