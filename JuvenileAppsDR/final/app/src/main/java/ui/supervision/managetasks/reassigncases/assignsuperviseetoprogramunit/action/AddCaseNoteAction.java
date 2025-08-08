package ui.supervision.managetasks.reassigncases.assignsuperviseetoprogramunit.action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.domintf.ICaseAssignment;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class AddCaseNoteAction extends JIMSBaseAction
{
    /**
     * @roseuid 464368F103D5
     */
    public AddCaseNoteAction()
    {
    }

    protected void addButtonMapping(Map keyMap)
    {
		keyMap.put("assign.supervisee.setup", "displayCaseNote");
    	keyMap.put("button.assignsuperviseetoprogramunit.addcasenote.summary" , "summarize") ;
    }
        
    public ActionForward displayCaseNote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;
		caseAssignmentForm.setCasenoteDate(caseAssignmentForm.getProgramUnitAllocationDate());
        Date caseNoteCreateDate = new Date() ;
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a");
        String casenoteTime= timeFormat.format(caseNoteCreateDate) ; 
        caseAssignmentForm.setCasenoteTime(casenoteTime) ; 
			
		StringBuffer sb = new StringBuffer();
		String caseNum = "";
        Iterator iterator = caseAssignmentForm.getActiveCases().iterator();
		while (iterator.hasNext()) {
			ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
			caseNum = activeCase.getCriminalCaseId();
			if (caseNum != null & caseNum.length() > 3){
				sb.append(caseNum.substring(0,3));
				sb.append(" ");
				sb.append(caseNum.substring(3,caseNum.length()));
			}
			sb.append(" CRT ");
			sb.append(activeCase.getCourtId());
			sb.append(", ");
		}
		String caseAssignmentIds  = sb.deleteCharAt(sb.lastIndexOf(",")).toString();
		sb.setLength(0);
		sb.append(caseAssignmentIds.trim());
		sb.append(", ");
		sb.append(caseAssignmentForm.getSuperviseeNameStr());
		sb.append(" SPN ");
		sb.append(caseAssignmentForm.getDefendantId());
		sb.append(" assigned to " );
		sb.append(caseAssignmentForm.getWorkGroupName());
		sb.append(" on ");
		sb.append(caseAssignmentForm.getCasenoteDate());
		
        caseAssignmentForm.setCasenoteText(sb.toString()) ; 
      return aMapping.findForward("displayCaseNote");
    }
    
    public ActionForward summarize(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      return aMapping.findForward("summarize");
    }
}
