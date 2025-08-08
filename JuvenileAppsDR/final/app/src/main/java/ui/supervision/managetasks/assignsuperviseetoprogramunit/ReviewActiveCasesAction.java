package ui.supervision.managetasks.assignsuperviseetoprogramunit;

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
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class ReviewActiveCasesAction extends JIMSBaseAction {
	

	/**
	 * @roseuid 464368F103D5
	 */
	public ReviewActiveCasesAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.allocatesuperviseetosupervisor.reviewactivecases", "reviewActiveCases");
		keyMap.put("button.paperFileReceived", "paperFileReceived"); 
	}

	public ActionForward reviewActiveCases(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {	
		
		String orderId = ((String) aRequest.getAttribute( CaseloadConstants.SUPERVISION_ORDER_ID ));
		String criminalCaseId = ((String) aRequest.getAttribute(CaseloadConstants.CRIMINAL_CASE_ID));
		String taskId = (String) aRequest.getAttribute("taskId");
		
		// Clear out the attributes
		aRequest.setAttribute(CaseloadConstants.SUPERVISION_ORDER_ID, "");
		aRequest.setAttribute(CaseloadConstants.CRIMINAL_CASE_ID, "");
		aRequest.setAttribute("taskId", "");
		
		try {
			CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
			// Clearing previous data from form
			caseAssignmentForm.clearAll();
			AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
			
			caseAssignmentForm.setTaskId(taskId);		
			GetCaseAssignmentEvent getCaseAssignmentEvent = new GetCaseAssignmentEvent();
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
			
			if (caseToAcknowledge != null) {
			    String agencyId = SecurityUIHelper.getUserAgencyId();
				if (agencyId == null) {
					agencyId = "";
				}
				caseAssignmentForm.setAgencyId(agencyId);
				caseAssignmentForm.setCaseToAcknowledge( caseToAcknowledge );
				
				caseAssignmentForm.setCourtNumber( caseToAcknowledge.getCourtId() );
				caseAssignmentForm.setCaseNum( caseToAcknowledge.getCriminalCaseId() );
				String defendantId = caseToAcknowledge.getDefendantId();
				IName defendantName = caseToAcknowledge.getDefendantName();
				caseAssignmentForm.setDefendantId(defendantId);
				caseAssignmentForm.setSuperviseeName(defendantName);
				if(defendantName != null){
					caseAssignmentForm.setSuperviseeNameStr(defendantName.getFormattedName());
				}					
				
				List caseAssignments = assignmentService.getActiveCasesOfDefendant(defendantId);
				List caseAssignmentsNew = new ArrayList();
				
				for ( int i =0; i < caseAssignments.size(); i++ ){
					
					CaseAssignmentTO assignment = (CaseAssignmentTO) caseAssignments.get(i);
					
					if ( !criminalCaseId.equals(assignment.getCriminalCaseId())){
						
						caseAssignmentsNew.add( assignment );
					}
	
				}
				caseAssignmentForm.setActiveCases( caseAssignmentsNew );
				orderId = null;
				criminalCaseId = null;
				taskId = null;	
				caseAssignmentResponse = null;
				activeCaseAssignments = null;
				caseToAcknowledge = null;
				caseAssignments = null;
				caseAssignmentsNew = null;
			
			} else {
				this.sendToErrorPage(aRequest, "error.assignSupervisee.casesNotFound");			
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.sendToErrorPage(aRequest, "error.assignSupervisee.casesNotFound");			
		}
		return aMapping.findForward("displayReviewActiveCases");
	}

	public ActionForward paperFileReceived(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward("selectProgramUnit");
	}

}
