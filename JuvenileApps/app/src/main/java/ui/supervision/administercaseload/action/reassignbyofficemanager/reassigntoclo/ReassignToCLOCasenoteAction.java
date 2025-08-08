/*
 * Created on Jun 27, 2007
 */
package ui.supervision.administercaseload.action.reassignbyofficemanager.reassigntoclo;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.contact.domintf.IName;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.CaseloadConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author cc_rbhat
 */
public class ReassignToCLOCasenoteAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";

	private static String FWD_ASSIGN_TO_CLO_SUMMARY = "assignToCLOSummary";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toCLO.casenote", "setup");
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

		setupWorkGroupDetails(caseAssignmentForm);
		setupCaseNote(caseAssignmentForm);

		return mapping.findForward(FWD_SETUP);
	}

	private void setupWorkGroupDetails(CaseAssignmentForm caseAssignmentForm) {
		String reassignedWorkGroupId = caseAssignmentForm.getReassignedWorkGroupId();
		String reassignedWorkGroupName = "";
		String reassignedWorkGroupDescription = "";

		Iterator iterator = caseAssignmentForm.getCourtServicesWorkGroups().iterator();
		while (iterator.hasNext()) {
			WorkGroupResponseEvent event = (WorkGroupResponseEvent) iterator.next();
			if (event.getWorkgroupId().equalsIgnoreCase(reassignedWorkGroupId)) {
				reassignedWorkGroupName = event.getWorkgroupName();
				reassignedWorkGroupDescription = event.getWorkgroupDescription();
				if (reassignedWorkGroupDescription == null || reassignedWorkGroupDescription.length() == 0) {
					reassignedWorkGroupDescription = reassignedWorkGroupName;
					break;
				}
			}
		}
		caseAssignmentForm.setReassignedWorkGroupName(reassignedWorkGroupName);
		caseAssignmentForm.setReassignedWorkGroupDescription(reassignedWorkGroupDescription);
	}


	/**
	 * Sample case note: "Supervisee Young, Selvin M, 32156487, 980124545 01010,
	 * 212 has been reassigned to CLO Smith, Rob Jones on 12/12/2005, 12:20."
	 * 
	 * @param caseAssignmentForm
	 */
	private void setupCaseNote(CaseAssignmentForm caseAssignmentForm) {
		Date today = DateUtil.getCurrentDate();
		StringBuffer sb = new StringBuffer();

		sb.append("Supervisee ");
		sb.append( caseAssignmentForm.getSuperviseeNameStr() );
		sb.append(", SPN ");
		sb.append(caseAssignmentForm.getDefendantId());
		sb.append(", Case ");
		
		Iterator iterator = caseAssignmentForm.getActiveCases().iterator();
		String caseNum = "";
		while (iterator.hasNext()) {
			ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
			caseNum = activeCase.getCriminalCaseId();
			if (caseNum != null & caseNum.length() > 3){
				caseNum = caseNum.substring(0,3) + " " + caseNum.substring(3,caseNum.length());
			}
			sb.append(caseNum);
			sb.append(", CRT ");
			sb.append(activeCase.getCourtId());
			sb.append(", ");
		}

		sb.append(" has been reassigned to CLO ");
		IName officerName = caseAssignmentForm.getOfficerName();
		if (officerName == null) {
			sb.append("No Officer Assigned");
		} else if (officerName.getFormattedName() == null || officerName.getFormattedName().length() == 0) {
			sb.append("No Officer Assigned");			
		} else {
			sb.append(officerName.getFormattedName());			
		}
		sb.append(" on ");
		sb.append(DateUtil.dateToString(today, CaseloadConstants.CASENOTE_DATE_FMT));
		sb.append(".");
		
		caseAssignmentForm.setCasenoteText(sb.toString());
		caseAssignmentForm.setCasenoteDate(DateUtil.dateToString(today, DateUtil.DATE_FMT_1));
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

		return mapping.findForward(FWD_ASSIGN_TO_CLO_SUMMARY);
	}

}
