/*
 * Created on Sep 28, 2007
 *
 */
package ui.supervision.managetasks.reassigncases.supervisor.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;

/**
 * @author cc_rbhat
 *
 */
public class ReassignToWorkgroupCompletedAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toProgramUnit.completed", "displaySummary");
		keyMap.put("button.backToTasks", "confirmSuperviseeAssignment");
	}
	
	public ActionForward displaySummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward("displayConfirmation");
	}

	public ActionForward confirmSuperviseeAssignment(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward("taskList");
	}
}
