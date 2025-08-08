package ui.supervision.managetasks.allocatesuperviseetosupervisor.action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.reply.LightProgramUnitResponseEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class AddCasenoteForSuperviseeAllocationAction extends JIMSBaseAction
{
    /**
     * @roseuid 464368F103D5
     */
    public AddCasenoteForSuperviseeAllocationAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
    	keyMap.put("assign.supervisee.setup", "displayCaseNote");
    	keyMap.put("button.allocatesuperviseetosupervisor.selectsupervisor.addCasenote" , "addCaseNote") ;    	
    }
        
    public ActionForward displayCaseNote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		//setup program unit name.
		List programUnits = caseAssignmentForm.getProgramUnitList();
		String selectedProgramUnitId = caseAssignmentForm.getProgramUnitId();
		for (Iterator iterator = programUnits.iterator(); iterator.hasNext();) {
			LightProgramUnitResponseEvent programUnit = (LightProgramUnitResponseEvent) iterator.next();
			if (programUnit.getProgramUnitId().equals( selectedProgramUnitId )) {
				caseAssignmentForm.setProgramUnitName(programUnit.getProgramUnitName() );
				break;
			}
		}
		//setup case note.
		Date caseNoteDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String caseNoteDateAsString = simpleDateFormat.format(caseNoteDate);
		caseAssignmentForm.setCasenoteDate(caseNoteDateAsString);
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("H:mm a");
		String caseNoteTimeAsString = simpleTimeFormat.format(caseNoteDate);
		caseAssignmentForm.setCasenoteTime(caseNoteTimeAsString);
		StringBuffer caseNote = new StringBuffer();
		caseNote.append( "Paper file received for " );
		String caseNum = caseAssignmentForm.getCaseNum();
		if (caseNum != null & caseNum.length() > 3){
			caseNote.append( caseNum.substring(0,3) );
			caseNote.append( " " );
			caseNote.append( caseNum.substring(3,caseNum.length()) );
		}
		caseNote.append( ", CRT " );
		caseNote.append( caseAssignmentForm.getCourtNumber() );
		caseNote.append( ", " );
		caseNote.append( caseAssignmentForm.getSuperviseeNameStr() );
		caseNote.append( " SPN " );
		caseNote.append( caseAssignmentForm.getDefendantId() );
		caseNote.append( " on " );
		caseNote.append( caseNoteDateAsString );
		caseNote.append( " and allocated to Supervisor " );
		caseNote.append( caseAssignmentForm.getSupervisorName().getLastName() );
		if ( StringUtils.isNotEmpty(caseAssignmentForm.getSupervisorName().getFirstName()) )  {
			caseNote.append( ", " );
			caseNote.append( caseAssignmentForm.getSupervisorName().getFirstName() );
			if ( StringUtils.isNotEmpty(caseAssignmentForm.getSupervisorName().getMiddleName()) )  {
			caseNote.append( " " );
			caseNote.append( caseAssignmentForm.getSupervisorName().getMiddleName() );
			}
		}
		caseNote.append( "." );
		String caseNoteText = caseNote.toString(); 
		caseAssignmentForm.setCasenoteText(caseNoteText);
		return aMapping.findForward("displayCaseNote");
	}

	public ActionForward addCaseNote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward("summarize");
	}
}
