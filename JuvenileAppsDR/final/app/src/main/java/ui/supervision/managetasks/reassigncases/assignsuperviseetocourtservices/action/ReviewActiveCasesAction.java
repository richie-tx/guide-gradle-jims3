package ui.supervision.managetasks.reassigncases.assignsuperviseetocourtservices.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.CaseloadConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.helper.ReassignSuperviseeService;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class ReviewActiveCasesAction extends JIMSBaseAction {
	/**
	 * @roseuid 464368F103D5
	 */
	public ReviewActiveCasesAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "reviewActiveCases");
		keyMap.put("button.paperFileReceived", "paperFileReceived");
	}

	public ActionForward reviewActiveCases(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		
		String orderId = ((String) aRequest.getAttribute( CaseloadConstants.SUPERVISION_ORDER_ID ));
		String caseAssignmentId = ((String) aRequest.getAttribute(CaseloadConstants.CASE_ASSIGNMENT_ID));
		String criminalCaseId = ((String) aRequest.getAttribute(CaseloadConstants.CRIMINAL_CASE_ID));
		String taskId = (String)aRequest.getAttribute("taskId") ;
    try {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;
		caseAssignmentForm.setTaskId(taskId) ; 
		caseAssignmentForm.setCriminalCaseId( criminalCaseId );
		caseAssignmentForm.setCaseAssignmentId(caseAssignmentId);
		caseAssignmentForm.setSupervisionOrderId(orderId);

			List caseAssignmentListForReassignment = ReassignSuperviseeService.getInstance().getCasesForReassignment(
					caseAssignmentForm);
			caseAssignmentForm.setActiveCases(caseAssignmentListForReassignment);

			if (caseAssignmentListForReassignment != null && caseAssignmentListForReassignment.size() > 0) {
				ICaseAssignment activeCase = (ICaseAssignment) caseAssignmentListForReassignment.get(0);
				caseAssignmentForm.setDefendantId(activeCase.getDefendantId());
				caseAssignmentForm.setSuperviseeName(activeCase.getDefendantName());
				if(activeCase.getDefendantName() != null){
					caseAssignmentForm.setSuperviseeNameStr(activeCase.getDefendantName().getFormattedName());
				}
				caseAssignmentForm.setProgramUnitId(activeCase.getProgramUnitId());
				caseAssignmentForm.setProgramUnitName(activeCase.getProgramUnitName());
				caseAssignmentForm.setOfficerPositionId(activeCase.getAssignedStaffPositionId());
				caseAssignmentForm.setCourtNumber( activeCase.getCourtId() );
			}		

		} catch (Exception e) {
			e.printStackTrace();
			this.sendToErrorPage(aRequest, "error.assignSupervisee.casesNotFound");			
		}

		return aMapping.findForward("displayReviewActiveCases");
	}

	public ActionForward paperFileReceived(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		CSCDSupervisionStaffResponseEvent staffInfoEvent = AssignSuperviseeService.getInstance().getCSCDStaff();
		caseAssignmentForm.setProgramUnitId(staffInfoEvent.getOrganizationId());
		caseAssignmentForm.setProgramUnitName(staffInfoEvent.getProgramUnitName());
		String currentDate = DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1);
		caseAssignmentForm.setOfficerAssignmentDate(currentDate);
		//RRY created a new method... 
		ReassignSuperviseeService.getInstance().reassignSuperviseeToCLOPosition( caseAssignmentForm );
		return aMapping.findForward("confirm");
	}
}
