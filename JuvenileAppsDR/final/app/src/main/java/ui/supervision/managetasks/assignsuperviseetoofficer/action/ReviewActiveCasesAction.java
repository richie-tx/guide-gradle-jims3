package ui.supervision.managetasks.assignsuperviseetoofficer.action;

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
		keyMap.put("button.assignsuperviseetoofficer.reviewactivecases", "reviewActiveCases");
		keyMap.put("button.assignsuperviseetoofficer.reviewactivecases.paperfilereceived", "paperFileReceived");
	}

	public ActionForward reviewActiveCases(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		
		String orderId = ((String) aRequest.getAttribute( CaseloadConstants.SUPERVISION_ORDER_ID ));
		String caseAssignmentId = ((String) aRequest.getAttribute(CaseloadConstants.CASE_ASSIGNMENT_ID));
		String criminalCaseId = ((String) aRequest.getAttribute(CaseloadConstants.CRIMINAL_CASE_ID));
		String taskId = ((String) aRequest.getAttribute("taskId"));
		
		// Clear out the attributes RRY
		aRequest.setAttribute(CaseloadConstants.SUPERVISION_ORDER_ID, "");
		aRequest.setAttribute(CaseloadConstants.CASE_ASSIGNMENT_ID, "");
		aRequest.setAttribute(CaseloadConstants.CRIMINAL_CASE_ID, "");
		aRequest.setAttribute("taskId", "");
		
		try {
			CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
			// Clearing previous data from form
			caseAssignmentForm.clearAll();
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
			caseAssignmentForm.setProgramUnitId(caseToAcknowledge.getProgramUnitId());
			caseAssignmentForm.setProgramUnitName(caseToAcknowledge.getProgramUnitName());
			caseAssignmentForm.setSupervisorPositionId(caseToAcknowledge.getAllocatedStaffPositionId());

			caseAssignmentForm.setDefendantId(caseToAcknowledge.getDefendantId());
			IName defendantName = caseToAcknowledge.getDefendantName();
			caseAssignmentForm.setSuperviseeName(defendantName);
			if(defendantName != null){
				caseAssignmentForm.setSuperviseeNameStr(defendantName.getFormattedName());
			}		

			/**During case assignment, we need to display all previous cases of the supervisee irrespective
			of the program unit they are in. Earlier the understanding was to display only those
			case which were already present in the program unit to which the current case is being 
			assigned to.**/
			List filteredCaseList = assignmentService.getActiveCasesOfDefendant( caseToAcknowledge.getDefendantId() );
			List caseList = new ArrayList();
			for ( int i =0; i < filteredCaseList.size(); i++ ){
				
				CaseAssignmentTO assignment = (CaseAssignmentTO) filteredCaseList.get(i);
				
				if ( caseAssignmentId != null && !caseAssignmentId.equals( assignment.getCaseAssignmentId() )){
					
					caseList.add(assignment);				}
			}
			caseAssignmentForm.setActiveCases(caseList);
			//set local variables to null
			orderId = null;
			caseAssignmentId = null;
			criminalCaseId = null;
			taskId = null;
			assignmentService = null;
			getCaseAssignmentEvent = null;
			caseAssignmentResponse = null;
			activeCaseAssignments = null;
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
		return aMapping.findForward("selectOfficer");
	}
}
