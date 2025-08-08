package ui.supervision.managetasks.allocatesuperviseetosupervisor.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class SummaryAction extends JIMSBaseAction
{
    /**
     * @roseuid 464368F103D5
     */
    public SummaryAction() {

	}

    protected void addButtonMapping(Map keyMap)
    {
    	keyMap.put("assign.supervisee.setup", "displaySummary");
    	keyMap.put("button.allocatesuperviseetosupervisor.confirmation.confirm" , "confirmSuperviseeAllocationToSupervisor") ;
    }
    
    public ActionForward displaySummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		String taskId = caseAssignmentForm.getTaskId();
		caseAssignmentForm.setIsDivisionReassignment(false);
		// TODO : reset the division for the case assignment 
		return aMapping.findForward("displaySummary");
	}

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward confirmSuperviseeAllocationToSupervisor(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;

		if (!caseAssignmentForm.isTaskProcessed()){
			
			AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
			assignmentService.allocateSuperviseeToSupervisor(caseAssignmentForm);
		}else{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Case Assignment is already Processed");
			return aMapping.findForward("error");
		}
		
		
		return aMapping.findForward("confirm");
	}
}
