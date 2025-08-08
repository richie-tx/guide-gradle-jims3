package ui.supervision.managetasks.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class ReassignWorkgroupAction extends JIMSBaseAction
{
   
    
    /**
     * @roseuid 464368F103D5
     */
    public ReassignWorkgroupAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
    	keyMap.put("button.next" , "reassignWorkgroup") ;
    	keyMap.put("button.addCaseNote" , "addCaseNote") ;
    	
    }
    
    
    public ActionForward reassignWorkgroup(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
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
      return aMapping.findForward("workgroupList");

    }

    
    public ActionForward addCaseNote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
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
      return aMapping.findForward("displayCaseNote");

    }
    

}
