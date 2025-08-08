package ui.supervision.managetasks.reassigncases.assignsuperviseetoofficer.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.domintf.ICaseAssignment;
import mojo.km.utilities.DateUtil;
import naming.CaseloadConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.helper.ReassignSuperviseeService;

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
    	keyMap.put("button.next", "reviewActiveCases") ;
    	keyMap.put("button.assignsuperviseetoofficer.reviewactivecases", "reviewActiveCases") ;
    	keyMap.put("button.assignsuperviseetoofficer.reviewactivecases.paperfilereceived", "paperFileReceived") ;
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
			caseAssignmentForm.setCaseAssignments(caseAssignmentListForReassignment);
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
				caseAssignmentForm.setProgramUnitAllocationDate(DateUtil.dateToString(activeCase.getProgramUnitAssignDate(), DateUtil.DATE_FMT_1));
				caseAssignmentForm.setSupervisorPositionId(activeCase.getAllocatedStaffPositionId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.sendToErrorPage(aRequest, "error.assignSupervisee.casesNotFound");			
		}
		return aMapping.findForward("displayReviewActiveCases");
    }
    
    public ActionForward paperFileReceived(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      return aMapping.findForward("selectOfficer");
    }
}
