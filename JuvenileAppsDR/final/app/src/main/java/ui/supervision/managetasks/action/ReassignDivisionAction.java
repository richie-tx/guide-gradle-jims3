package ui.supervision.managetasks.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class ReassignDivisionAction extends JIMSBaseAction
{

    
    /**
     * @roseuid 464368F103D5
     */
    public ReassignDivisionAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
    	keyMap.put("button.reassignDivision" , "reassignDivision") ;
    	keyMap.put("button.next" , "reassignWorkgroup") ;
    	
    }
    
    public ActionForward reassignDivision(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      try
	  {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;
        String taskId = caseAssignmentForm.getTaskId() ; 
        caseAssignmentForm.setIsDivisionReassignment(true) ; 
        
	  }
      catch (Exception e)
	  {
        e.printStackTrace() ; 
	  }
      return aMapping.findForward("programUnitList");
    }
    
    public ActionForward reassignWorkgroup(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      try
	  {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;
        String taskId = caseAssignmentForm.getTaskId() ; 
        caseAssignmentForm.setIsDivisionReassignment(false) ; 
	  }
      catch (Exception e)
	  {
        e.printStackTrace() ; 
	  }
      return aMapping.findForward("workgroupAssignment");

    }


}
