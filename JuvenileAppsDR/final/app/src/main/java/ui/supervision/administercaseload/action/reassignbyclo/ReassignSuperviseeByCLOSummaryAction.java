/*
 * Created on Jul 27, 2007
 */
package ui.supervision.administercaseload.action.reassignbyclo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.manageworkgroup.reply.WorkGroupResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.helper.ReassignCaseService;

/**
 * @author cc_rbhat
 */
public class ReassignSuperviseeByCLOSummaryAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";

	private static String FWD_SUPERVISEE_ASSIGNMENT_FINISHED = "superviseeAssignmentFinished";

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toCLO.summary", "setup");
		keyMap.put("button.finish", "finishReassignment"); 
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
		getSelectedWorkgroupName(caseAssignmentForm);
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
	public ActionForward finishReassignment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String forward = FWD_SUPERVISEE_ASSIGNMENT_FINISHED;
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		try {
			ReassignCaseService.getInstance().caseReassignmentByCLO(caseAssignmentForm);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			this.sendToErrorPage(request, "error.generic", "Missing required data. Cannot proceed with this workflow");
			forward = FWD_SETUP;
		}		
		return mapping.findForward(forward);
	}

	private void getSelectedWorkgroupName(CaseAssignmentForm caseAssignmentForm) {		
		String selectWorkgroupId = caseAssignmentForm.getWorkGroupId();
		List courtServicesWorkGroups = caseAssignmentForm.getCourtServicesWorkGroups();
		for (Iterator iterator = courtServicesWorkGroups.iterator(); iterator.hasNext();) {
			WorkGroupResponseEvent event = (WorkGroupResponseEvent) iterator.next();
			if (event.getWorkgroupId().equalsIgnoreCase(selectWorkgroupId)) {
				String workgroupName = event.getWorkgroupName();
				caseAssignmentForm.setWorkGroupName(workgroupName);
				break;
			}
		}		
	}
}
