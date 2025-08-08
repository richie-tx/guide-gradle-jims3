package ui.supervision.managetasks.processnewcaseassignment.action;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class ConfirmationAction extends JIMSBaseAction
{
    /**
     * @roseuid 464368F103D5
     */
    public ConfirmationAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
    	keyMap.put("button.officernewcaseassignment.reviewactivecases.paperfilereceived" , "displayConfirmation") ;
    	keyMap.put("button.backToTaskSearchResults" , "backToTasks") ;
    }
    
    
    public ActionForward displayConfirmation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;
        String taskId = caseAssignmentForm.getTaskId() ; 
        caseAssignmentForm.setIsDivisionReassignment(false) ; 
        return aMapping.findForward("displayConfirmation");
    }

    
    public ActionForward backToTasks(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward("taskList");
    }
}
