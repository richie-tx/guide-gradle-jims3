package ui.supervision.administercaseload.action.transfercase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class TransferCaseConfirmationAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.search.setup", "setUp");
		keyMap.put("button.caseloadSearch", "transferComplete");
	}

	public ActionForward setUp(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;

		return aMapping.findForward("initialSetup");
	}

	public ActionForward transferComplete(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;

		return aMapping.findForward("caseloadSearch");
	}	
}
