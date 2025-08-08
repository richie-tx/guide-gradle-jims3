/*
 * Created on Jun 27, 2007
 */
package ui.supervision.administercaseload.action.reassignbyofficemanager.reassigntoclo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.helper.ReassignSuperviseeService;

/**
 * @author cc_rbhat
 */
public class ReassignSuperviseeToCLOSummaryAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";

	private static String FWD_SUPERVISEE_ASSIGNMENT_FINISHED = "superviseeAssignmentFinished";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toCLO.summary", "setup");
		keyMap.put("button.finish", "finishReassigment");
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

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward finishReassigment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		ReassignSuperviseeService.getInstance().reassignSuperviseeToClo(caseAssignmentForm);
		return mapping.findForward(FWD_SUPERVISEE_ASSIGNMENT_FINISHED);
	}

}
