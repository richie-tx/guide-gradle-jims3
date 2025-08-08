package ui.supervision.managetasks.reassigncases.action;



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
    	keyMap.put("button.assignsuperviseetoofficer.reviewactivecases.paperfilereceived" , "displayConfirmation") ;
    	keyMap.put("button.assignsuperviseetoofficer.confirmation.confirm" , "displayConfirmation") ;
    	keyMap.put("button.backToTaskSearchResults" , "backToTasks") ;
    }
    
    
    public ActionForward displayConfirmation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      try
	  {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;
        String taskId = caseAssignmentForm.getTaskId() ; 
        caseAssignmentForm.setIsDivisionReassignment(false) ; 
        // TODO : reset the division for the case assignment 
	  }
      catch (Exception e)
	  {
        e.printStackTrace() ; 
	  }
      return aMapping.findForward("displayConfirmation");
    }

    
    public ActionForward backToTasks(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      try
	  {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;
        String taskId = caseAssignmentForm.getTaskId() ; 
        
	  }
      catch (Exception e)
	  {
        e.printStackTrace() ; 
	  }
      return aMapping.findForward("taskList");

    }
    

}
