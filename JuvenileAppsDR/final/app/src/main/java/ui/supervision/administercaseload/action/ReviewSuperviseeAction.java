/*
 * Created on Jun 27, 2007
 */
package ui.supervision.administercaseload.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.managetask.GetCSTasksEvent;
import messaging.managetask.reply.CSTaskResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.EventFactory;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.MessageUtil;
import naming.CSTaskControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.form.SuperviseeTransferCasesInfo;

/**
 * @author cc_rbhat
 */
public class ReviewSuperviseeAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";
	private static String CLOSE_CASE_ACK_SUCCESS = "closeCaseAckSuccess";

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.reviewActiveCases", "setup");
		keyMap.put("button.paperFileReceived", "paperFileAcknowledgement");
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward setup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		filterSelectedCases(caseAssignmentForm);
		
		String forward = FWD_SETUP;;
		String userSecurityFeature = caseAssignmentForm.getUserSecurityFeature();
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		String userType = securityManager.getIUserInfo().getUserTypeId();
		if (userType.equals("MA") || userType.equals("SA")){
			if (caseAssignmentForm.isCloseCases()) {
				forward = "searchByOfficer";
				if (UIConstants.CS_TRANSFER_OUT_CASES.equals(caseAssignmentForm.getSecondaryAction())){
					forward = FWD_SETUP;
				}			
				if (caseAssignmentForm.isCloseCases()) {
					forward = FWD_SETUP;
				}			
			}			
		} else if (userSecurityFeature.equals(UIConstants.CS_REASSIGN_CASE_CSO)) {			
			//CSO already has the case file there is no reason for them to acknowledge it. 
			forward = "searchByOfficer";
			if (UIConstants.CS_TRANSFER_OUT_CASES.equals(caseAssignmentForm.getSecondaryAction())){
				forward = FWD_SETUP;
			}			
			if (caseAssignmentForm.isCloseCases()) {
				forward = FWD_SETUP;
			}			
		} else if (userSecurityFeature.equals(UIConstants.CS_REASSIGN_CASE_CLO)) {
			forward = "searchByCLO";
		}

		// We only want to find tasks for Assignment/Reassignments - Also used in Close case
		if (!caseAssignmentForm.isCloseCases()){
			
			List selectedCases = caseAssignmentForm.getActiveCasesSelectedForReassignment();
			
			for ( int z=0; z < selectedCases.size(); z++ ){
				
				ICaseAssignment ca = (ICaseAssignment) selectedCases.get(z);
				String criminalCaseId = ca.getCriminalCaseId();
				String defendantId = caseAssignmentForm.getDefendantId();
				
				if ( hasCurrentTasks(defendantId, criminalCaseId)){
					
					sendToErrorPage(request, "error.generic", "Pending tasks exist for a workgroup or staff position for this case.  Process existing tasks before reassigning case");	
					forward = "failure";
				}
			}
		}

		return mapping.findForward(forward);
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward paperFileAcknowledgement(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {		
		String forward = "";
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		SuperviseeTransferCasesInfo superviseeTransferCases = caseAssignmentForm.getSuperviseeTransferCases();
		
		if(caseAssignmentForm.isCloseCases()) {
			forward = CLOSE_CASE_ACK_SUCCESS;
		} else if (UIConstants.CS_TRANSFER_IN_CASES.equals(superviseeTransferCases.getTransferType()) ||
				UIConstants.CS_TRANSFER_OUT_CASES.equals(superviseeTransferCases.getTransferType()) ) { 
			forward = "transferCase";
		} else {
			String userSecurityFeature = caseAssignmentForm.getUserSecurityFeature();
			if (userSecurityFeature.equals(UIConstants.CS_REASSIGN_SUP)) {
				forward = "searchByOfficeManager";
			} else if (userSecurityFeature.equals(UIConstants.CS_REASSIGN_CASE_CLO)) {
				forward = "searchByCLO";			
			} else if (userSecurityFeature.equals(UIConstants.CS_REASSIGN_ADMIN)) {			
				forward = "searchByAdmin";
			} 	
		}
		return mapping.findForward(forward);
	}
	
	private void filterSelectedCases(CaseAssignmentForm caseAssignmentForm) {
		List activeCasesSelectedForReassignment = new ArrayList();
		List otherActiveCases = new ArrayList();
		String[] casesSelectedForReassignment = caseAssignmentForm.getCasesSelectedForReassignment();

		List activeCases = caseAssignmentForm.getCaseAssignments();
		String cdi = "";
		for (int index = 0; index < casesSelectedForReassignment.length; index++) {	
			if (activeCases != null) {
				for (Iterator iterator = activeCases.iterator(); iterator.hasNext();) {
					CaseAssignmentTO activeCase = (CaseAssignmentTO) iterator.next();
					if (activeCase.getCaseAssignmentId().equalsIgnoreCase(casesSelectedForReassignment[index])) {
						cdi = "";
						if (activeCase.getCriminalCaseId() != null){
							cdi = activeCase.getCriminalCaseId();
							if (cdi.length() > 3){
								cdi = cdi.substring(0,3);
							}
						}
						activeCase.setCdi(cdi);
						activeCasesSelectedForReassignment.add(activeCase);
					}else{
						cdi = "";
						if (activeCase.getCriminalCaseId() != null){
							cdi = activeCase.getCriminalCaseId();
							if (cdi.length() > 3){
								cdi = cdi.substring(0,3);
							}
						}
						activeCase.setCdi(cdi);
						otherActiveCases.add(activeCase);
					}
				}
			}	
		}
		//This is where the cases for reassignment are set
		caseAssignmentForm.setActiveCasesSelectedForReassignment(activeCasesSelectedForReassignment);
		caseAssignmentForm.setActiveCases(activeCasesSelectedForReassignment);
		activeCasesSelectedForReassignment = null;
		otherActiveCases = null;
		casesSelectedForReassignment = null;
		activeCases = null;
		cdi = null;
	}
	
	/**
	 * 
	 * @param spn
	 * @param crimCaseId
	 * @return
	 */
	private boolean hasCurrentTasks( String spn , String crimCaseId ){
		
		boolean hasTasks = false;
		
		GetCSTasksEvent request = (GetCSTasksEvent) EventFactory
        							.getInstance(CSTaskControllerServiceNames.GETCSTASKS );
		
		request.setDefendantId( spn );
		request.setCriminalCaseId( crimCaseId );
		
		CSTaskResponseEvent taskResponse = (CSTaskResponseEvent) 
									MessageUtil.postRequest( request, CSTaskResponseEvent.class );
		if ( taskResponse != null ){
			
			hasTasks = true;
		}
		return hasTasks;
	}
}