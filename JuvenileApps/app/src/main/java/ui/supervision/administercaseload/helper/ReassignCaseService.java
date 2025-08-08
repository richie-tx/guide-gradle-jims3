/*
 * Created on Nov 16, 2007
 */
package ui.supervision.administercaseload.helper;

import java.util.Calendar;
import java.util.Iterator;

import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.managetask.CreateCSTaskEvent;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author cc_rbhat
 */
public class ReassignCaseService {
	private static ReassignCaseService service;

	private static boolean serviceInitialized = false;

	/**
	 *  
	 */
	private ReassignCaseService() {
	}

	/**
	 * 
	 * @return
	 */
	public static ReassignCaseService getInstance() {
		if (service == null) {
			synchronized (ReassignCaseService.class) {
				if (!serviceInitialized) {
					service = new ReassignCaseService();
					serviceInitialized = true;
				}
			}
		}
		return service;
	}

	/**
	 * Supervisee reassignment request by CSO.
	 * @param caseAssignmentForm
	 */
	public void caseReassignmentByCSO(CaseAssignmentForm caseAssignmentForm) {
		String supervisorPositionId = caseAssignmentForm.getSupervisorPositionId();
		String pageFlowScenario = CaseloadConstants.REQUEST_SUPERVISEE_TRANSFER;
		createCSOCaseReassignmentCSTask( caseAssignmentForm, supervisorPositionId, pageFlowScenario );
	}

	/**
	 * Supervisee reassigned to court services receiving by CLO.
	 * @param caseAssignmentForm
	 */
	public void caseReassignmentByCLO(CaseAssignmentForm caseAssignmentForm) {
		String workgroupId = caseAssignmentForm.getWorkGroupId();
		String pageFlowScenario = CaseloadConstants.REASSIGN_PROGRAM_UNIT_PAGEFLOW;
		createCLOCaseReassignmentCSTask( caseAssignmentForm, workgroupId ,pageFlowScenario );
	}

	
	/**
	 * 
	 * @param caseAssignmentForm
	 * @param ntTaskId
	 */
	public void createCSOCaseReassignmentCSTask( CaseAssignmentForm caseAssignmentForm, String positionId, String scenario ) {
		
		StringBuffer caseAssignmentIdsBuffer = new StringBuffer();
		StringBuffer supervisionOrderIdBuffer = new StringBuffer();
		String caseId = "";
		String defendantId = caseAssignmentForm.getDefendantId();
		
		
		//due date
		Calendar calendar = Calendar.getInstance();
		// Get the weekday and print it 
		int weekday = calendar.get(Calendar.DAY_OF_WEEK); 
		
			switch ( weekday) {
				
			case 5:
				calendar.add(Calendar.DATE, 4);
				break;
				
			case 6:
				calendar.add(Calendar.DATE, 4);
				break;
				
			default:
				calendar.add(Calendar.DATE, 2);
				break;
				
			}

		
		StringBuffer subject2 = new StringBuffer();
		subject2.append("Reassign case to CLO - forward casefile to Program Unit office assistant");
		
		CreateCSTaskEvent createCSTaskEvent = new CreateCSTaskEvent();
		createCSTaskEvent.setDefendantId(defendantId);
		createCSTaskEvent.setStaffPositionId( positionId );
		createCSTaskEvent.setDueDate( calendar.getTime());
		
		Iterator iterator = caseAssignmentForm.getActiveCasesSelectedForReassignment().iterator();
		for (; iterator.hasNext();) {
			CaseAssignmentTO activeCase = (CaseAssignmentTO) iterator.next();
			
			caseAssignmentIdsBuffer.append( activeCase.getCaseAssignmentId() + "," );
			supervisionOrderIdBuffer.append( activeCase.getSupervisionOrderId() + "," );
			caseId = activeCase.getCriminalCaseId();
			createCSTaskEvent.setCaseAssignId( caseAssignmentIdsBuffer.toString() );
			createCSTaskEvent.setSupervisionOrderId( supervisionOrderIdBuffer.toString() );
			createCSTaskEvent.setCourtId( activeCase.getCourtId() );
			
		}
		
		createCSTaskEvent.setCriminalCaseId( caseId );
		createCSTaskEvent.setScenario( scenario );
		createCSTaskEvent.setSuperviseeName( caseAssignmentForm.getSuperviseeNameStr() );
		createCSTaskEvent.setSubject2( subject2.toString() );
		createCSTaskEvent.setTaskSubject( caseAssignmentForm.getRequestReassignmentTaskSubject() );
		createCSTaskEvent.setTopic( CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP );

		createCSTaskEvent.setTastText( caseAssignmentForm.getRequestReassignmentTaskNote() );

		MessageUtil.postRequest(createCSTaskEvent);
	}
	
	/**
	 * 
	 * @param caseAssignmentForm
	 * @param ntTaskId
	 */
	public void createCLOCaseReassignmentCSTask( CaseAssignmentForm caseAssignmentForm, String workgroupId, String scenario ) {
		
		StringBuffer caseAssignmentIdsBuffer = new StringBuffer();
		StringBuffer supervisionOrderIdBuffer = new StringBuffer();
		String caseId = "";
		String defendantId = caseAssignmentForm.getDefendantId();
		
		//due date
		Calendar calendar = Calendar.getInstance();
		// Get the weekday and print it 
		int weekday = calendar.get(Calendar.DAY_OF_WEEK); 
		
			switch ( weekday) {
				
			case 5:
				calendar.add(Calendar.DATE, 4);
				break;
				
			case 6:
				calendar.add(Calendar.DATE, 4);
				break;
				
			default:
				calendar.add(Calendar.DATE, 2);
				break;
				
			}
		
		CreateCSTaskEvent createCSTaskEvent = new CreateCSTaskEvent();
		createCSTaskEvent.setCaseAssignId( caseAssignmentForm.getCaseAssignmentId() );
		createCSTaskEvent.setDefendantId(defendantId);
		createCSTaskEvent.setWorkGroupId( workgroupId );
		createCSTaskEvent.setDueDate( calendar.getTime());
		
		Iterator iterator = caseAssignmentForm.getActiveCasesSelectedForReassignment().iterator();
		for (; iterator.hasNext();) {
			CaseAssignmentTO activeCase = (CaseAssignmentTO) iterator.next();
			
			caseAssignmentIdsBuffer.append( activeCase.getCaseAssignmentId() + "," );
			supervisionOrderIdBuffer.append( activeCase.getSupervisionOrderId() + "," );
			caseId = activeCase.getCriminalCaseId();
			createCSTaskEvent.setCaseAssignId( caseAssignmentIdsBuffer.toString() );
			createCSTaskEvent.setSupervisionOrderId( supervisionOrderIdBuffer.toString() );
			createCSTaskEvent.setCourtId( activeCase.getCourtId() );
		}
		
		createCSTaskEvent.setCriminalCaseId( caseId );
		createCSTaskEvent.setScenario( scenario );
		createCSTaskEvent.setSuperviseeName( caseAssignmentForm.getSuperviseeNameStr() );
		createCSTaskEvent.setTaskSubject( caseAssignmentForm.getRequestReassignmentTaskSubject() );
		createCSTaskEvent.setTopic( CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP );
		
		createCSTaskEvent.setTastText( caseAssignmentForm.getRequestReassignmentTaskNote() );

		MessageUtil.postRequest(createCSTaskEvent);
	}

}
