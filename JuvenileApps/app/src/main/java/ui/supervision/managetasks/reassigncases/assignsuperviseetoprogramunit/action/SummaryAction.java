package ui.supervision.managetasks.reassigncases.assignsuperviseetoprogramunit.action;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.helper.ReassignSuperviseeService;

public class SummaryAction extends JIMSBaseAction
{
    /**
     * @roseuid 464368F103D5
     */
    public SummaryAction()
    {
    }

    protected void addButtonMapping(Map keyMap)
    {    	
		keyMap.put("assign.supervisee.setup", "displaySummary");
    	keyMap.put("button.assignsuperviseetoofficer.confirmation.confirm" , "confirmSuperviseeAssignmentToProgramUnit") ;
    }
    
    public ActionForward displaySummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
    {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		String taskId = caseAssignmentForm.getTaskId();
		caseAssignmentForm.setIsDivisionReassignment(false);
		// TODO : reset the division for the case assignment
		return aMapping.findForward("displaySummary");
	}

    public ActionForward confirmSuperviseeAssignmentToProgramUnit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		String reassignedWorkgroupId = caseAssignmentForm.getWorkGroupId();
		caseAssignmentForm.setReassignedWorkGroupId(reassignedWorkgroupId);
		ReassignSuperviseeService.getInstance().reassignSuperviseeToProgramUnit(caseAssignmentForm);
		return aMapping.findForward("confirm");
    }
}
