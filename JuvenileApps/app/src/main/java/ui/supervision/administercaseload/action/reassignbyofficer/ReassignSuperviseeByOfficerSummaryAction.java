/*
 * Created on Jul 26, 2007
 */
package ui.supervision.administercaseload.action.reassignbyofficer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.utilities.Name;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.helper.ReassignCaseService;

/**
 * @author cc_rbhat
 */
public class ReassignSuperviseeByOfficerSummaryAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";

	private static String FWD_SUPERVISEE_ASSIGNMENT_FINISHED = "superviseeAssignmentFinished";

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toOfficer.summary", "setup");
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
		getSelectedSuperviorsName(caseAssignmentForm);
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
			ReassignCaseService.getInstance().caseReassignmentByCSO(caseAssignmentForm);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			this.sendToErrorPage(request, "error.generic", "Missing required data. Cannot proceed with this workflow");
			forward = FWD_SETUP;
		}		
		return mapping.findForward(forward);
	}

	private void getSelectedSuperviorsName(CaseAssignmentForm caseAssignmentForm) {		
		String supervisorPositionId = caseAssignmentForm.getSupervisorPositionId();
		List supervisionStaffList = caseAssignmentForm.getSupervisionStaff();
		
        Iterator staffIterator = supervisionStaffList.iterator() ; 
        CSCDSupervisionStaffResponseEvent staffDetails = null ;
        Name supervisorName = null ; 
        
        while (staffIterator.hasNext()) {
        	staffDetails = (CSCDSupervisionStaffResponseEvent) staffIterator.next() ;
        	if (staffDetails.getStaffPositionId().equals(supervisorPositionId) ) {
        		supervisorName = staffDetails.getAssignedName();     
        		if (supervisorName != null) {
        			IName name = new NameBean(supervisorName.getFirstName(), supervisorName.getMiddleName(), supervisorName.getLastName());
        			caseAssignmentForm.setSupervisorName(name);
	        		caseAssignmentForm.setSupervisorFirstName(supervisorName.getFirstName());
	        		caseAssignmentForm.setSupervisorLastName(supervisorName.getLastName());
        		} else {
        			IName name = new NameBean("", "", "");
        			caseAssignmentForm.setSupervisorName(name);
	        		caseAssignmentForm.setSupervisorFirstName("");
	        		caseAssignmentForm.setSupervisorLastName("");
        		}        		
        		break ; 
        	}
        }
	}  
}
