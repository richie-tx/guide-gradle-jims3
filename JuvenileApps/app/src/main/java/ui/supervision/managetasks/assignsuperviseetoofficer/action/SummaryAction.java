package ui.supervision.managetasks.assignsuperviseetoofficer.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetSuperviseeHeaderInfoEvent;
import messaging.administercaseload.reply.SuperviseeInfoResponseEvent;
import messaging.administerprogramreferrals.GetInitOpenReferralsForRefTypesEvent;
import messaging.administersupervisee.GetSuperviseeDataEvent;
import messaging.administersupervisee.reply.SuperviseeDetailResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadControllerServiceNames;
import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

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
		// get supervisee header information for supervisee
		GetSuperviseeDataEvent anEvent = new GetSuperviseeDataEvent();
		anEvent.setSuperviseeId(caseAssignmentForm.getDefendantId());
		CompositeResponse response = MessageUtil.postRequest(anEvent);		
		SuperviseeDetailResponseEvent superviseeDetailResponseEvent = (SuperviseeDetailResponseEvent) MessageUtil.filterComposite(response,
				SuperviseeDetailResponseEvent.class);
		if (!caseAssignmentForm.isTaskProcessed()){
			
			AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
			assignmentService.assignSuperviseeToOfficer(caseAssignmentForm, superviseeDetailResponseEvent);
			
			// Initiate Program Unit referral
			assignmentService.initiateProgramUnitReferral( caseAssignmentForm );
		}else{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Case Assignment is already Processed");
			return aMapping.findForward("error");
		}
		
		return aMapping.findForward("confirm");
	}
}
