package ui.supervision.managetasks.processnewcaseassignment.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetCaseAssignmentEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.contact.domintf.IName;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class ReviewActiveCasesAction extends JIMSBaseAction {
	/**
	 * @roseuid 464368F103D5
	 */
	public ReviewActiveCasesAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "reviewActiveCases");
		keyMap.put("button.officernewcaseassignment.reviewactivecases", "reviewActiveCases");
		keyMap.put("button.officernewcaseassignment.reviewactivecases.paperfilereceived", "paperFileReceived");
	}

	public ActionForward reviewActiveCases(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		
		String orderId = ((String) aRequest.getAttribute( CaseloadConstants.SUPERVISION_ORDER_ID ));
		String caseAssignmentId = ((String) aRequest.getAttribute(CaseloadConstants.CASE_ASSIGNMENT_ID));
		String criminalCaseId = ((String) aRequest.getAttribute(CaseloadConstants.CRIMINAL_CASE_ID));
		String taskId = ((String) aRequest.getAttribute("taskId"));
		
		// Clear out the attributes
		aRequest.setAttribute(CaseloadConstants.SUPERVISION_ORDER_ID, "");
		aRequest.setAttribute(CaseloadConstants.CASE_ASSIGNMENT_ID, "");
		aRequest.setAttribute(CaseloadConstants.CRIMINAL_CASE_ID, "");
		aRequest.setAttribute("taskId", "");
		
		try {
			
			CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm; 
			AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
			
			GetCaseAssignmentEvent getCaseAssignmentEvent = new GetCaseAssignmentEvent();
			getCaseAssignmentEvent.setCaseAssignmentId(caseAssignmentId);
			getCaseAssignmentEvent.setSupervisionOrderId( orderId );
			getCaseAssignmentEvent.setCriminalCaseId( criminalCaseId );
			
			CaseAssignmentResponseEvent caseAssignmentResponse = (CaseAssignmentResponseEvent) MessageUtil.postRequest(
					getCaseAssignmentEvent, CaseAssignmentResponseEvent.class);
			
			List activeCaseAssignments = caseAssignmentResponse.getCaseAssignments();
			ICaseAssignment caseToAcknowledge = null;
			
			if (activeCaseAssignments != null && activeCaseAssignments.size() > 0) {
				caseToAcknowledge = (ICaseAssignment) activeCaseAssignments.get(0);	
				if( caseToAcknowledge.getCourtId().length() < 3 ) {
					StringBuffer padCrt = new StringBuffer( caseToAcknowledge.getCourtId() );
					while ( padCrt.length() < 3 ){
			    		padCrt.insert( 0, "0" );
			    	}
					caseToAcknowledge.setCourtId(padCrt.toString());
					padCrt = null;
				}
			}

			caseAssignmentForm.setTaskId( taskId );
			caseAssignmentForm.setCaseToAcknowledge(caseToAcknowledge);
			caseAssignmentForm.setCourtNumber(caseToAcknowledge.getCourtId());
			caseAssignmentForm.setCaseNum(caseToAcknowledge.getCriminalCaseId());
			String programUnitId = caseToAcknowledge.getProgramUnitId();
			String programUnitName = caseToAcknowledge.getProgramUnitName();
			caseAssignmentForm.setProgramUnitId(programUnitId);
			caseAssignmentForm.setProgramUnitName(programUnitName);

			String defendantId = caseToAcknowledge.getDefendantId();
			IName defendantName = caseToAcknowledge.getDefendantName();
			caseAssignmentForm.setDefendantId(defendantId);
			caseAssignmentForm.setSuperviseeName(defendantName);
			if(defendantName != null){
				caseAssignmentForm.setSuperviseeNameStr(defendantName.getFormattedName());
			}

			/**During case assignment, we need to display all previous cases of the supervisee irrespective
			   of the program unit they are in. Earlier the understanding was to display only those
			   case which were already present in the program unit to which the current case is being 
			   assigned to.**/
			List filteredCaseList = assignmentService.getActiveCasesOfDefendant( defendantId );
			List caseList = new ArrayList();
			for ( int i =0; i < filteredCaseList.size(); i++ ){
				
				CaseAssignmentTO assignment = (CaseAssignmentTO) filteredCaseList.get(i);
				
				if ( caseAssignmentId != null && !caseAssignmentId.equals( assignment.getCaseAssignmentId() )){
					
					caseList.add(assignment);				}
			}
			caseAssignmentForm.setActiveCases(caseList);
			orderId = null;
			caseAssignmentId = null;
			criminalCaseId = null;
			taskId = null; 
			assignmentService = null;
			getCaseAssignmentEvent = null;
			caseAssignmentResponse = null;
			caseToAcknowledge = null;
			defendantId = null;
			defendantName = null;
			filteredCaseList = null;
			caseList = null;
		} catch (Exception e) {
			e.printStackTrace();
			this.sendToErrorPage(aRequest, "error.assignSupervisee.casesNotFound");			
		}
		return aMapping.findForward("displayReviewActiveCases");
	}

	public ActionForward paperFileReceived(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		setCaseNote(caseAssignmentForm);
		AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
		assignmentService.superviseeAcceptedByOfficer(caseAssignmentForm);
		return aMapping.findForward("confirm");

	}
	
	private void setCaseNote(CaseAssignmentForm caseAssignmentForm) {
		String caseNum = caseAssignmentForm.getCaseNum();
		StringBuffer sb = new StringBuffer();
		sb.append("Paper file received for ");
		if (caseNum != null & caseNum.length() > 3){
			sb.append(caseNum.substring(0,3));
			sb.append(" ");
			sb.append(caseNum.substring(3,caseNum.length()));
		}
		sb.append(", CRT ");
		sb.append(caseAssignmentForm.getCourtNumber());
		sb.append(", ");
		sb.append(caseAssignmentForm.getSuperviseeNameStr());
		sb.append(" SPN ");
		sb.append(caseAssignmentForm.getDefendantId());
		caseAssignmentForm.setCasenoteText(sb.toString()); 
		caseNum = null;
		sb = null;
	}
}
