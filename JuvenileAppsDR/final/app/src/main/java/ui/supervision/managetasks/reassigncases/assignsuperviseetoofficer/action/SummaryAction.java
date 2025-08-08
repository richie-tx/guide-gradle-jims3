package ui.supervision.managetasks.reassigncases.assignsuperviseetoofficer.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.GetInitOpenReferralsForRefTypesEvent;
import messaging.administerprogramreferrals.reply.ReferralWithRefTypeResponseEvent;
import messaging.csserviceprovider.GetProgramUnitProgramEvent;
import messaging.csserviceprovider.reply.CSProgramResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.CSAdministerServiceProviderConstants;
import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.helper.ReassignSuperviseeService;

public class SummaryAction extends JIMSBaseAction {
	/**
	 * @roseuid 464368F103D5
	 */
	public SummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("assign.supervisee.setup", "displaySummary");				
		keyMap.put("button.assignsuperviseetoofficer.confirmation.confirm", "confirmSuperviseeAssignmentToOfficer");
	}

	public ActionForward displaySummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		String taskId = caseAssignmentForm.getTaskId();
		caseAssignmentForm.setIsDivisionReassignment(false);
		// TODO : reset the division for the case assignment
		
//		query all the initiate and open referrals for selectedRefTypes
		GetInitOpenReferralsForRefTypesEvent requestEvent = new GetInitOpenReferralsForRefTypesEvent();
		requestEvent.setDefendantId( caseAssignmentForm.getDefendantId() );
		
		GetProgramUnitProgramEvent getEvent = new GetProgramUnitProgramEvent();
		
		List referralTypesCodeList = new ArrayList();
		getEvent.setProgramUnitId( caseAssignmentForm.getProgramUnitId() );
		getEvent.setProgramStatus( CSAdministerServiceProviderConstants.ACTIVE_PROG_STATUS );

		// Get the associated program name for PU
		CSProgramResponseEvent 	pup_program = ( CSProgramResponseEvent )
								MessageUtil.postRequest( getEvent, 	CSProgramResponseEvent.class);
		
		referralTypesCodeList.add (pup_program.getReferralTypeCode() );
		requestEvent.setRefTypesCdList( referralTypesCodeList );
		
		List responseList = MessageUtil.postRequestListFilter( requestEvent, ReferralWithRefTypeResponseEvent.class );
		
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm a" );

	    String now = sdf.format( cal.getTime());
	    caseAssignmentForm.setCasenoteTime( now );
	    
	    String dischargeDesc = SimpleCodeTableHelper.getDescrByCode("JIMS2_DISCHARGE_REASON", 
				caseAssignmentForm.getReasonForDischargeId());
	    caseAssignmentForm.setReasonForDischargeDesc( dischargeDesc ); 
	    
		String placementDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.REASON_FOR_PLACEMENT, 
											caseAssignmentForm.getReasonForPlacementId());
		caseAssignmentForm.setReasonForPlacementDesc( placementDesc );
		return aMapping.findForward("displaySummary");
	}

	public ActionForward confirmSuperviseeAssignmentToOfficer(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		
		if (!caseAssignmentForm.isTaskProcessed()){
			
			ReassignSuperviseeService.getInstance().assignSuperviseeToOfficer( caseAssignmentForm );
			
			// Initiate Program Unit referral
			/**
			 * Temporary turned off RRY
			 * ReassignSuperviseeService.getInstance().initiateProgramUnitReferral( caseAssignmentForm );
			 */
			
			
		}else{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Case Assignment is already Processed");
			return aMapping.findForward("error");
		}
		return aMapping.findForward("confirm");
	}
}
