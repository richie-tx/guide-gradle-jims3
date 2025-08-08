package ui.supervision.managetasks.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.CaseloadConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class ConfirmCaseAssignmentAction extends JIMSBaseAction
{
    
    
    /**
     * @roseuid 464368F103D5
     */
    public ConfirmCaseAssignmentAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
    }
    
    

    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
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
      return aMapping.findForward("confirmCaseAssignment");

    }

}
