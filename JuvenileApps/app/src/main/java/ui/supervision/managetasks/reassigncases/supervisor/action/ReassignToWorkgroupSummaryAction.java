/*
 * Created on Sep 28, 2007
 */
package ui.supervision.managetasks.reassigncases.supervisor.action;

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
 *
 */
public class ReassignToWorkgroupSummaryAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toProgramUnit.summary", "setup");
		keyMap.put("button.finish", "finishReassignment"); 
	}

	public ActionForward setup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;		
		return mapping.findForward("initialSetup");
	}

	public ActionForward finishReassignment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		ReassignSuperviseeService.getInstance().transferSuperviseeToOfficeManager(caseAssignmentForm);
		return mapping.findForward("superviseeAssignmentFinished");
	}
}
