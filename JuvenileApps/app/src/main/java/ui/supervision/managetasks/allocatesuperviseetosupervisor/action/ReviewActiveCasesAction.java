package ui.supervision.managetasks.allocatesuperviseetosupervisor.action;

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
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import naming.ViolationReportConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class ReviewActiveCasesAction extends JIMSBaseAction
{
    /**
     * @roseuid 464368F103D5
     */
    public ReviewActiveCasesAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
    	keyMap.put("button.allocatesuperviseetosupervisor.reviewactivecases", "reviewActiveCases") ;
    	keyMap.put("button.allocatesuperviseetosupervisor.reviewactivecases.paperfilereceived", "paperFileReceived") ;
    }				
        
    public ActionForward reviewActiveCases(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) { 
    	
    	String orderId = ((String) aRequest.getAttribute( CaseloadConstants.SUPERVISION_ORDER_ID ));
		String caseAssignmentId = ((String) aRequest.getAttribute(CaseloadConstants.CASE_ASSIGNMENT_ID));
		String criminalCaseId = ((String) aRequest.getAttribute(CaseloadConstants.CRIMINAL_CASE_ID));
		String defendantId = ((String) aRequest.getAttribute(ViolationReportConstants.PARAM_DEFENDANT_ID));
		
		// Clear request parameters RRY
		aRequest.setAttribute(CaseloadConstants.SUPERVISION_ORDER_ID, "" );
        aRequest.setAttribute(CaseloadConstants.CASE_ASSIGNMENT_ID, "" );
        aRequest.setAttribute(CaseloadConstants.CRIMINAL_CASE_ID, "" );
        aRequest.setAttribute(ViolationReportConstants.PARAM_DEFENDANT_ID, "" );
		
		try {
			CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
			String taskId = (String) aRequest.getAttribute("taskId");
			caseAssignmentForm.setTaskId(taskId);

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
				
				StringBuffer padCourt = null;
				padCourt = new StringBuffer( caseToAcknowledge.getCourtId() );
			    if ( padCourt.length() < 3){
			    	while (padCourt.length() < 3){
			    		padCourt.insert(0, "0");
			    	}
			    	caseToAcknowledge.setCourtId( padCourt.toString() );
			    }
			}
			
			caseAssignmentForm.setCaseToAcknowledge(caseToAcknowledge);
			String courtNumber = caseToAcknowledge.getCourtId();
			caseAssignmentForm.setCourtNumber(courtNumber);
			String caseId = caseToAcknowledge.getCriminalCaseId();
			caseAssignmentForm.setCaseNum(caseId);
			String programUnitId = caseToAcknowledge.getProgramUnitId();
			caseAssignmentForm.setProgramUnitId(programUnitId);
			String programUnitName = caseToAcknowledge.getProgramUnitName();
			caseAssignmentForm.setProgramUnitName(programUnitName);
			String programUnitAssignDate = DateUtil.dateToString(caseToAcknowledge.getProgramUnitAssignDate(), DateUtil.DATE_FMT_1);
			caseAssignmentForm.setProgramUnitAllocationDate(programUnitAssignDate);

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
		return aMapping.findForward("selectSupervisor");
	}
    
}
