package ui.supervision.managetasks.assignsuperviseetoprogramunit;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class AddCaseNoteAction extends JIMSBaseAction {

	/**
	 * @roseuid 464368F103D5
	 */
	public AddCaseNoteAction() {
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("assign.supervisee.setup", "displayCaseNote");
		keyMap.put("button.assignsuperviseetoprogramunit.addcasenote.summary", "summarize");
	}

	public ActionForward displayCaseNote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		caseAssignmentForm.setCasenoteText( "" );
		caseAssignmentForm.setCasenoteDate(caseAssignmentForm.getProgramUnitAllocationDate());
		StringBuffer caseNote = new StringBuffer();
		String caseNum = caseAssignmentForm.getCaseNum();
		if (caseNum != null & caseNum.length() > 3){
			caseNote.append( "Case " );
			caseNote.append( caseNum.substring(0,3) );
			caseNote.append( " " );
			caseNote.append( caseNum.substring(3,caseNum.length()) );
			caseNote.append( "; " );
		}
		caseNote.append( "CRT " );
		caseNote.append( caseAssignmentForm.getCourtNumber() );
		caseNote.append( ", " );
		caseNote.append( caseAssignmentForm.getSuperviseeNameStr() );
		caseNote.append( " SPN " );
		caseNote.append( caseAssignmentForm.getDefendantId() );
		caseNote.append( " assigned to " ); 
		caseNote.append( caseAssignmentForm.getProgramUnitName() );
		caseNote.append( " on " );
		caseNote.append( caseAssignmentForm.getCasenoteDate() );
		caseNote.append( "." );
		String casenoteText = caseNote.toString();
		caseAssignmentForm.setCasenoteText(casenoteText);

		return aMapping.findForward("displayCaseNote");
	}

	public ActionForward summarize(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward("summarize");
	}

}
