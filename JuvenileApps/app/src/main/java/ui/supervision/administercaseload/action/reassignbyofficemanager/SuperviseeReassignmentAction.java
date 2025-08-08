/*
 * Created on Jun 27, 2007
 *
 */
package ui.supervision.administercaseload.action.reassignbyofficemanager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author cc_rbhat
 *  
 */
public class SuperviseeReassignmentAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";

	private static String FWD_REASSIGN_TO_OFFICER = "reassignToOfficer";

	private static String FWD_REASSIGN_TO_PROGRAM_UNIT = "reassignToProgramUnit";

	private static String FWD_REASSIGN_TO_CLO = "reassignToCLO";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.selectReassignmentType", "setup");
		keyMap.put("button.next", "selectReassignmentType");
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

		return mapping.findForward(FWD_SETUP);
	}

	public ActionForward selectReassignmentType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		ActionForward fwd = null;
		String reassignmentType = caseAssignmentForm.getReassignmentType();
		if (reassignmentType.equalsIgnoreCase("off")) {
			fwd = mapping.findForward(FWD_REASSIGN_TO_OFFICER);
		} else if (reassignmentType.equalsIgnoreCase("pu")) {
			fwd = mapping.findForward(FWD_REASSIGN_TO_PROGRAM_UNIT);
		} else if (reassignmentType.equalsIgnoreCase("clo")) {
			fwd = mapping.findForward(FWD_REASSIGN_TO_CLO);
		} else {
			String message = "Invalid Reassignment Type.";
			throw new RuntimeException(message);
		}
		return fwd;
	}
}
