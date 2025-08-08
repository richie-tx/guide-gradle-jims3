package ui.supervision.managetasks.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.CaseloadConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class CommonConfirmAssignmentAction extends JIMSBaseAction
{
   
    
    /**
     * @roseuid 464368F103D5
     */
    public CommonConfirmAssignmentAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
    	keyMap.put("button.finish" , "finish") ;
    	keyMap.put("button.backToTasks" , "backToTasks") ;
    }
    
    
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      try
	  {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;
        String confirmationMessageString = (String) aRequest.getAttribute(UIConstants.CONFIRMATION_MESSAGE_REQUEST_ATTRIBUTE_NAME) ;
        caseAssignmentForm.setConfirmationMessageString( confirmationMessageString ) ; 
        aRequest.removeAttribute(UIConstants.CONFIRMATION_MESSAGE_REQUEST_ATTRIBUTE_NAME) ; 
	  }
      catch (Exception e)
	  {
        e.printStackTrace() ; 
	  }
      return aMapping.findForward("confirmCaseAssignment");

    }

    
    public ActionForward backToTasks(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      try
	  {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;
        caseAssignmentForm.setActivityInd( CaseloadConstants.WF_TASKLIST) ;
        caseAssignmentForm.setScenario(CaseloadConstants.SC_ASSIGN_NEW_CASE) ;
	  }
      catch (Exception e)
	  {
        e.printStackTrace() ; 
	  }
      return aMapping.findForward("backToTasks");
    }

}
