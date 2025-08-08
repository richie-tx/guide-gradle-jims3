/*
 * Created on Nov 6, 2007
 */
package ui.supervision.administercaseload.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import messaging.administercaseload.GetCaseAssignmentEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administerprogramreferrals.ExitProgramReferralEvent;
import messaging.administerprogramreferrals.SaveProgRefCasenoteEvent;
import messaging.administerprogramreferrals.SaveProgramUnitReferralEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.csserviceprovider.GetProgramUnitProgramEvent;
import messaging.csserviceprovider.reply.CSProgramResponseEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSAdministerProgramReferralsConstants;
import naming.CSAdministerServiceProviderConstants;
import naming.CSProgramReferralControllerServiceNames;
import naming.CaseloadConstants;
import naming.PDCodeTableConstants;
import ui.supervision.AssignReassignSuperviseeHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

/**
 * @author cc_rbhat
 *  
 */
public class ReassignSuperviseeService {

	private static ReassignSuperviseeService service;

	private static boolean serviceInitialized = false;

	/**
	 *  
	 */
	private ReassignSuperviseeService() {
	}

	public static ReassignSuperviseeService getInstance() {
		if (service == null) {
			synchronized (ReassignSuperviseeService.class) {
				if (!serviceInitialized) {
					service = new ReassignSuperviseeService();
					serviceInitialized = true;
				}
			}
		}
		return service;
	}

	public List getCasesForReassignment( CaseAssignmentForm caseAssignmentForm ) {
		
		

		String supervisionOrderIdList = caseAssignmentForm.getSupervisionOrderId() ;
		List supervisionOrderList = new ArrayList();
		supervisionOrderList.add( caseAssignmentForm.getSupervisionOrderId() );
		if ( supervisionOrderIdList != null ){
			
			StringTokenizer supervisionOrderTokenizer = new StringTokenizer(supervisionOrderIdList, ",");
			String supervisionOrderId = null;
			while (supervisionOrderTokenizer.hasMoreTokens()) {
				supervisionOrderId = supervisionOrderTokenizer.nextToken();
				supervisionOrderList.add(supervisionOrderId.trim());
			}
		}		

		String caseAssignmentIdList = caseAssignmentForm.getCaseAssignmentId();
		List caseAssignmentList = new ArrayList();
		String caseAssignmentId = null;
		if ( caseAssignmentIdList != null ){
			
			StringTokenizer caseAssignmentTokenizer = new StringTokenizer(caseAssignmentIdList, ",");
			while (caseAssignmentTokenizer.hasMoreTokens()) {
				caseAssignmentId = caseAssignmentTokenizer.nextToken();
				caseAssignmentList.add(caseAssignmentId.trim());
			}
		}
		
		GetCaseAssignmentEvent getCaseAssignmentEvent = null;
		CaseAssignmentResponseEvent caseAssignmentResponseEvent = null;
		List caseAssignmentListForReassignment = new ArrayList();

		for (int counter = 0; counter < caseAssignmentList.size(); counter++) {
			getCaseAssignmentEvent = new GetCaseAssignmentEvent();
			getCaseAssignmentEvent.setCaseAssignmentId((String) caseAssignmentList.get(counter));
			getCaseAssignmentEvent.setSupervisionOrderId((String) supervisionOrderList.get(counter));

			caseAssignmentResponseEvent = (CaseAssignmentResponseEvent) MessageUtil.postRequest(getCaseAssignmentEvent,
					CaseAssignmentResponseEvent.class);
			List activeCaseAssignments = caseAssignmentResponseEvent.getCaseAssignments();
			ICaseAssignment caseToAcknowledge = (ICaseAssignment) activeCaseAssignments.get(0);
			caseAssignmentListForReassignment.add(caseToAcknowledge);
		}
		return caseAssignmentListForReassignment;
	}

	/**
	 * Supervisee reassigned by program unit office manager to another CSO with the same
	 * program unit.
	 * @param caseAssignmentForm
	 */
	public void reassignSuperviseeToOfficer(CaseAssignmentForm caseAssignmentForm) {
		String caseState = CaseloadConstants.OFFICER_ASSIGNED;
		String supervisionStyle = "";
		acknowledgePaperFileForActiveCases(caseAssignmentForm, caseState, supervisionStyle);
		updatePrefillCaseNote(caseAssignmentForm);		
		String oldTaskId = caseAssignmentForm.getTaskId();
		
		AssignReassignSuperviseeHelper superviseeHelper = AssignReassignSuperviseeHelper.getInstance();
		superviseeHelper.closePreviousTask( oldTaskId );
		
		String officerPositionId = caseAssignmentForm.getOfficerPositionId();
		String pageFlowScenario = CaseloadConstants.REASSIGN_CASES_PAGEFLOW;
		// Create a CStask only
		createTaskForStaffPosition( caseAssignmentForm, officerPositionId, pageFlowScenario );
		String superviseeUpdateType = CaseloadConstants.SUPERVISEE_ASSIGNED_TO_STAFF;
		// check if clo then last case
		if ("CLO".equalsIgnoreCase( superviseeHelper.findUserPosition( officerPositionId ))){
			
			if ( superviseeHelper.isLastCaseAssigned( caseAssignmentForm )){
				
				updateSupeviseeDetails( caseAssignmentForm, superviseeUpdateType );
				
			}else {
				
				superviseeHelper.recalculateWorkloadCredit( caseAssignmentForm );
				updateSupeviseeDetails( caseAssignmentForm, superviseeUpdateType );
			}
		}else {
			
			updateSupeviseeDetails(caseAssignmentForm, superviseeUpdateType);
				
		}
	}

	/**
	 * Supervisee reassigned to a new program unit by program unit office manager.
	 * @param caseAssignmentForm
	 */
	public void reassignSuperviseeToProgramUnit(CaseAssignmentForm caseAssignmentForm) {
		
		AssignReassignSuperviseeHelper supHelper = AssignReassignSuperviseeHelper.getInstance();
		String caseState = CaseloadConstants.PROGRAM_UNIT_ASSIGNED;
		String supervisionStyle = CaseloadConstants.SUPERVISION_STYLE_DIRECT;
		// Only applicable on new case Assignments not reassignment(RRY)
		//supHelper.checkProgramUnitChange( caseAssignmentForm );
		acknowledgePaperFileForActiveCases(caseAssignmentForm, caseState, supervisionStyle);
		updatePrefillCaseNote(caseAssignmentForm);
		String oldTaskId = caseAssignmentForm.getTaskId();
		
		
		supHelper.closePreviousTask( oldTaskId );
		
	    String workgroupId = caseAssignmentForm.getReassignedWorkGroupId();
		String pageFlowScenario = CaseloadConstants.REALLOCATE_SUPERVISOR_PAGEFLOW;
		createPUNtTaskForWorkgroup(caseAssignmentForm, workgroupId, pageFlowScenario); 
		String superviseeUpdateType = CaseloadConstants.SUPERVISEE_REASSIGNED_TO_PU;
		updateSupeviseeDetails(caseAssignmentForm, superviseeUpdateType);
	}
	
	
	/**
	 * Supervisee reassigned to CLO (i.e. Court Services Receiving workgroup) by program unit 
	 * office manager.
	 * When a supervisee gets reassigned to CLO, two things happen.
	 * First, the program unit of supervisee changes and the assigned staff position
	 * changes. The program unit change happens implicitly. In this scenario, there is
	 * no allocated staff position because the cases do not get assigned to officer by
	 * his supervisor.
	 * @param caseAssignmentForm
	 */
	public void reassignSuperviseeToClo(CaseAssignmentForm caseAssignmentForm) {
		String assignedStaffPositionId = caseAssignmentForm.getOfficerPositionId();
		AssignSuperviseeService assignSuperviseeService = AssignSuperviseeService.getInstance();
		CSCDSupervisionStaffResponseEvent staffResponseEvent = assignSuperviseeService.getCSCDStaff(assignedStaffPositionId);
		String programUnitId = staffResponseEvent.getOrganizationId();
		caseAssignmentForm.setProgramUnitId(programUnitId);
		String currentDate = DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1);
		caseAssignmentForm.setProgramUnitAllocationDate(currentDate);
		caseAssignmentForm.setOfficerAssignmentDate(currentDate);
		String caseState = CaseloadConstants.PROGRAM_UNIT_ASSIGNED;
		String supervisionStyle = CaseloadConstants.SUPERVISION_STYLE_INDIRECT;
		String officerPositionId = caseAssignmentForm.getOfficerPositionId();
		
		acknowledgePaperFileForActiveCases(caseAssignmentForm, caseState, supervisionStyle);
		caseState = CaseloadConstants.OFFICER_ASSIGNED;
		supervisionStyle = "";
		acknowledgePaperFileForActiveCases(caseAssignmentForm, caseState, supervisionStyle);
		String superviseeUpdateType = CaseloadConstants.SUPERVISEE_REASSIGNED_TO_STAFF;
		
		AssignReassignSuperviseeHelper superviseeHelper = AssignReassignSuperviseeHelper.getInstance();
		
		// check if clo then last case
		if ("CLO".equalsIgnoreCase( superviseeHelper.findUserPosition( officerPositionId ))){
			
			if ( superviseeHelper.isLastCaseAssigned( caseAssignmentForm )){
				
				updateSupeviseeDetails( caseAssignmentForm, superviseeUpdateType );
				
			}
		}
		updatePrefillCaseNote(caseAssignmentForm);
		//String positionId = caseAssignmentForm.getStaffPositionIdBeforeReassignment();
		//closeTasksForStaffPosition(caseAssignmentForm, positionId);
		//Following is needed when a supervisor approves a CSO's request to
		//reassign a supervisee to CLO.
		AssignReassignSuperviseeHelper.getInstance().closePreviousTask( caseAssignmentForm.getTaskId() );
		
	    String workgroupId = caseAssignmentForm.getReassignedWorkGroupId();
		String pageFlowScenario = CaseloadConstants.REASSIGN_COURT_SERVICES_RECEIVING_INBOUND_PAGEFLOW;
		createCLOTaskForWorkgroup( caseAssignmentForm, workgroupId, pageFlowScenario ); 
		
	}
		
	/**
	 * Officer acknowledges receipt of case file and takes over the supervision of a defendant.
	 * @param caseAssignmentForm
	 */
	public void superviseeAcceptedByOfficer(CaseAssignmentForm caseAssignmentForm) {		
		String caseState = CaseloadConstants.OFFICER_ACKNOWLEDGED;
		String supervisionStyle = "";
		acknowledgePaperFileForActiveCases(caseAssignmentForm, caseState, supervisionStyle);
		updatePrefillCaseNote(caseAssignmentForm);
		AssignReassignSuperviseeHelper.getInstance().closePreviousTask( caseAssignmentForm.getTaskId() );
	}

	/**
	 * Supervisee allocated to a supervisor by program unit office manager via task framework
	 * @param caseAssignmentForm
	 */
	public void allocateSuperviseeToSupervisor(CaseAssignmentForm caseAssignmentForm) {
		String caseState = CaseloadConstants.SUPERVISOR_ALLOCATED;
		String supervisionStyle = "";
		acknowledgePaperFileForActiveCases(caseAssignmentForm, caseState, supervisionStyle);
		updatePrefillCaseNote(caseAssignmentForm);
		AssignReassignSuperviseeHelper.getInstance().closePreviousTask( caseAssignmentForm.getTaskId() );
		
		// Switch to stop creating duplicate tasks.
		caseAssignmentForm.setTaskProcessed( true );
	    String supervisorPositionId = caseAssignmentForm.getSupervisorPositionId();
		String pageFlowScenario = CaseloadConstants.REASSIGN_OFFICER_PAGEFLOW;
		createTaskForStaffPosition( caseAssignmentForm, supervisorPositionId, pageFlowScenario );
		/**
		 * String superviseeUpdateType = CaseloadConstants.SUPERVISEE_ALLOCATE_TO_SUPERVISOR;
		 * updateSupeviseeDetails(caseAssignmentForm, superviseeUpdateType);
		 */

	}
	
	/**
	 * Supervisee assigned to an officer by supervisor via task framework
	 * @param caseAssignmentForm
	 */
	public void assignSuperviseeToOfficer(CaseAssignmentForm caseAssignmentForm) {
		String caseState = CaseloadConstants.OFFICER_ASSIGNED;
		String supervisionStyle = "";
		acknowledgePaperFileForActiveCases(caseAssignmentForm, caseState, supervisionStyle);
		updatePrefillCaseNote(caseAssignmentForm);
		AssignReassignSuperviseeHelper assignSuperviseeService = AssignReassignSuperviseeHelper.getInstance();
		assignSuperviseeService.closePreviousTask( caseAssignmentForm.getTaskId() );
		
		// Switch to stop creating duplicate tasks.
		caseAssignmentForm.setTaskProcessed( true );
		
        String officerPositionId = caseAssignmentForm.getOfficerPositionId();
		String pageFlowScenario = CaseloadConstants.REASSIGN_CASES_PAGEFLOW;
		createTaskForStaffPosition( caseAssignmentForm, officerPositionId, pageFlowScenario );
		String superviseeUpdateType = CaseloadConstants.SUPERVISEE_ASSIGNED_TO_STAFF;
		// check if clo then last case
		if ("CLO".equalsIgnoreCase( assignSuperviseeService.findUserPosition( officerPositionId ))){
			
			if ( assignSuperviseeService.isLastCaseAssigned( caseAssignmentForm )){
				
				updateSupeviseeDetails( caseAssignmentForm, superviseeUpdateType );
				
			}
			else {
				
				assignSuperviseeService.recalculateWorkloadCredit( caseAssignmentForm );
				updateSupeviseeDetails( caseAssignmentForm, superviseeUpdateType );
			}
		}else {
			
				updateSupeviseeDetails(caseAssignmentForm, superviseeUpdateType);

		}
	}
	
	/**
	 * Supervisee assigned to an officer by supervisor via task framework
	 * @param caseAssignmentForm
	 */
	public void reassignSuperviseeToCLOPosition( CaseAssignmentForm caseAssignmentForm ) {
		String caseState = CaseloadConstants.OFFICER_ASSIGNED;
		String supervisionStyle = "";
		acknowledgePaperFileForActiveCases(caseAssignmentForm, caseState, supervisionStyle);
		updatePrefillCaseNote(caseAssignmentForm);
		AssignReassignSuperviseeHelper assignSuperviseeService = AssignReassignSuperviseeHelper.getInstance();
		assignSuperviseeService.closePreviousTask( caseAssignmentForm.getTaskId() );
		
        String officerPositionId = caseAssignmentForm.getOfficerPositionId();
		// Stop creating the CLO Acknowledge tasks ER#JIMS200068091
		//createTaskForCLOPosition( caseAssignmentForm, officerPositionId, pageFlowScenario );
		String superviseeUpdateType = CaseloadConstants.SUPERVISEE_ASSIGNED_TO_STAFF;
		
		// check if clo then last case
		if ("CLO".equalsIgnoreCase( assignSuperviseeService.findUserPosition( officerPositionId ))){
			
			if ( assignSuperviseeService.isLastCaseAssigned( caseAssignmentForm )){
				
				updateSupeviseeDetails( caseAssignmentForm, superviseeUpdateType );
				
			}
		}
	}
	
	
	public void transferSuperviseeToOfficeManager(CaseAssignmentForm caseAssignmentForm) {
		acknowledgePaperFileForActiveCases(caseAssignmentForm, "", "");
		AssignReassignSuperviseeHelper.getInstance().closePreviousTask( caseAssignmentForm.getTaskId() );
	    
		String workgroupId = caseAssignmentForm.getReassignedWorkGroupId();
		String pageFlowScenario = CaseloadConstants.REASSIGN_CASES_FROM_CSO_TO_CLO_PAGEFLOW;
		createForwardToCasefileTask( caseAssignmentForm, workgroupId, pageFlowScenario ); 

	}

	public void returnAssignmentToWorkgroup(CaseAssignmentForm caseAssignmentForm) {
		String caseState = CaseloadConstants.PROGRAM_UNIT_ASSIGNED;
		String supervisionStyle = CaseloadConstants.SUPERVISION_STYLE_DIRECT;
		acknowledgePaperFileForActiveCases(caseAssignmentForm, caseState, supervisionStyle);
		String oldTaskId = caseAssignmentForm.getTaskId();
		AssignReassignSuperviseeHelper.getInstance().closePreviousTask( oldTaskId );

	    String workgroupId = caseAssignmentForm.getWorkGroupId();
		String pageFlowScenario = CaseloadConstants.REALLOCATE_SUPERVISOR_PAGEFLOW;
		createTaskForWorkgroup( caseAssignmentForm, workgroupId, pageFlowScenario ); 
	}

	private void acknowledgePaperFileForActiveCases(CaseAssignmentForm caseAssignmentForm, String caseState, 
			String supervisionStyle) {
		AssignSuperviseeService assignSuperviseeService = AssignSuperviseeService.getInstance();
		List casesToReassign = caseAssignmentForm.getActiveCases();
		if (casesToReassign.size() < 1){
			casesToReassign = caseAssignmentForm.getActiveCasesSelectedForReassignment();
    	}
		for (Iterator iterator = casesToReassign.iterator(); iterator.hasNext();) {
			ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
			caseAssignmentForm.setCaseToAcknowledge(activeCase);
			caseAssignmentForm.setCourtNumber(activeCase.getCourtId());
			//caseAssignmentForm.setProgramUnitAllocationDate( DateUtil.dateToString( activeCase.getProgramUnitAssignDate(), DateUtil.DATE_FMT_1 ) );
			assignSuperviseeService.acknowledgePaperFile(caseAssignmentForm, caseState, supervisionStyle); 
		}
	}

	private void updatePrefillCaseNote(CaseAssignmentForm caseAssignmentForm) {
		AssignSuperviseeService assignSuperviseeService = AssignSuperviseeService.getInstance();
		List tempList = caseAssignmentForm.getActiveCases();
		String supervisionOrderId = "";
		if (tempList.size()> 0){
			ICaseAssignment activeCase = (ICaseAssignment) caseAssignmentForm.getActiveCases().get(0);
			supervisionOrderId = activeCase.getSupervisionOrderId();
		} else {
			ICaseAssignment activeCase = (ICaseAssignment) caseAssignmentForm.getActiveCasesSelectedForReassignment().get(0);
			supervisionOrderId = activeCase.getSupervisionOrderId();
		}
		
		SupervisionOrderDetailResponseEvent supervisionOrderDetailResponse = assignSuperviseeService
				.getSupervisionOrderDetails(supervisionOrderId);
		String supervisionPeriodId = supervisionOrderDetailResponse.getSupervisionPeriodId();
		assignSuperviseeService.updatePrefillCaseNote(caseAssignmentForm, "", supervisionPeriodId);
	}

	/**
	 * 
	 * @param caseAssignmentForm
	 * @param workgroupId
	 * @param pageFlowScenario
	 */
	private void createTaskForWorkgroup(CaseAssignmentForm caseAssignmentForm, String workgroupId,
			String pageFlowScenario) {
		
        StringBuffer caseAssignmentIdsBuffer = new StringBuffer();
		StringBuffer supervisionOrderIdBuffer = new StringBuffer();
        StringBuffer taskTextBuffer = new StringBuffer();
        
        StringBuffer subject = new StringBuffer();
		subject.append("Reassignment to ")
		.append(caseAssignmentForm.getWorkGroupName());
        
        taskTextBuffer.append( caseAssignmentForm.getSuperviseeName().getFormattedName() );
        taskTextBuffer.append( ", ");
        taskTextBuffer.append( caseAssignmentForm.getDefendantId() );
        taskTextBuffer.append( ", ");    	
    	List activeCases = caseAssignmentForm.getActiveCases();
    	if (activeCases.size() < 1){
    		activeCases = caseAssignmentForm.getActiveCasesSelectedForReassignment();
    	}
    	String criminalCaseId = "";
    	StringBuffer padCrt = new StringBuffer();

    	for (Iterator iterator = activeCases.iterator(); iterator.hasNext();) {
    		ICaseAssignment caseAssignment = (ICaseAssignment) iterator.next();
    		
			caseAssignmentIdsBuffer.append( caseAssignment.getCaseAssignmentId() );
			caseAssignmentIdsBuffer.append( ",");
			supervisionOrderIdBuffer.append( caseAssignment.getSupervisionOrderId() );
			supervisionOrderIdBuffer.append( ",");

			// Reassign Case(s) of 002/130748201010, CRT: 015, to CLO on LOYA, VINCENT, SPN: 
			criminalCaseId = caseAssignment.getCriminalCaseId();
			String crimCaseId = caseAssignment.getCriminalCaseId();
            if ( crimCaseId != null & crimCaseId.length() > 3 ){
            	taskTextBuffer.append( crimCaseId.substring( 0,3 ) );
            	taskTextBuffer.append( " /  " );
            	taskTextBuffer.append( crimCaseId.substring( 3,crimCaseId.length() ) );
            	taskTextBuffer.append( ", " );
            }
            padCrt = new StringBuffer();
            padCrt.append( caseAssignment.getCourtId() );
	    	
			while ( padCrt.length() < 3 ){
	    		padCrt.insert( 0, "0" );
	    	}
		}
		String caseAssignmentIds = caseAssignmentIdsBuffer.deleteCharAt(caseAssignmentIdsBuffer.lastIndexOf(",")).toString();
		String supervisionOrderIds = supervisionOrderIdBuffer.deleteCharAt(supervisionOrderIdBuffer.lastIndexOf(",")).toString();
		String taskText = taskTextBuffer.deleteCharAt(taskTextBuffer.lastIndexOf(",")).toString();
		
		//create nt task - general task
        CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent(); 
        createTaskEvent.setTopic(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU);  
        createTaskEvent.setWorkGroupId( workgroupId );
    	Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 2);//due date
        createTaskEvent.setDueDate(calendar.getTime());
        createTaskEvent.setSubject2( subject.toString() );
        createTaskEvent.setScenario( pageFlowScenario );
        createTaskEvent.setCaseAssignId( caseAssignmentIds );
        createTaskEvent.setSupervisionOrderId( supervisionOrderIds );
        createTaskEvent.setCriminalCaseId( criminalCaseId ); 
        createTaskEvent.setDefendantId( caseAssignmentForm.getDefendantId() );
        createTaskEvent.setSuperviseeName( caseAssignmentForm.getSuperviseeNameStr() );
        
		String assignmentReturnReason = caseAssignmentForm.getAssignmentReturnReason();
		if(assignmentReturnReason != null && assignmentReturnReason.trim().length() > 0) {
			createTaskEvent.setTaskSubject(CaseloadConstants.REASSIGNMENT_RETURNED);
			taskText = CaseloadConstants.REASSIGNMENT_RETURNED + " for " + taskText + assignmentReturnReason;
		} else {
			createTaskEvent.setTaskSubject( CaseloadConstants.REASSIGNMENT + " to CLO");			
			taskText = CaseloadConstants.REASSIGN_CASES + " for " + taskText; 
		}
		createTaskEvent.setTastText( taskText );
        createTaskEvent.setCourtId( padCrt.toString() );
        
        MessageUtil.postRequest(createTaskEvent);           
	}
	
	private void createForwardToCasefileTask(CaseAssignmentForm caseAssignmentForm, String workgroupId,
			String pageFlowScenario) {
		
        StringBuffer caseAssignmentIdsBuffer = new StringBuffer();
		StringBuffer supervisionOrderIdBuffer = new StringBuffer();
        StringBuffer taskTextBuffer = new StringBuffer();
        
        StringBuffer subject = new StringBuffer();
		subject.append("Forward casefile to Court Services");
        
        taskTextBuffer.append( caseAssignmentForm.getSuperviseeName().getFormattedName() );
        taskTextBuffer.append( ", " );
        taskTextBuffer.append( caseAssignmentForm.getDefendantId() );
        taskTextBuffer.append( ", " );    	
    	List activeCases = caseAssignmentForm.getActiveCases();
    	if (activeCases.size() < 1){
    		activeCases = caseAssignmentForm.getActiveCasesSelectedForReassignment();
    	}
    	String criminalCaseId = "";
    	StringBuffer padCrt = new StringBuffer();
    	for (Iterator iterator = activeCases.iterator(); iterator.hasNext();) {
    		ICaseAssignment caseAssignment = (ICaseAssignment) iterator.next();
    		
			caseAssignmentIdsBuffer.append( caseAssignment.getCaseAssignmentId() );
			caseAssignmentIdsBuffer.append( "," );
			supervisionOrderIdBuffer.append( caseAssignment.getSupervisionOrderId() );
			supervisionOrderIdBuffer.append( "," );

			// Reassign Case(s) of 002/130748201010, CRT: 015, to CLO on LOYA, VINCENT, SPN: 
			criminalCaseId = caseAssignment.getCriminalCaseId();
			String crimCaseId = caseAssignment.getCriminalCaseId();
			padCrt = new StringBuffer();
			padCrt.append( caseAssignment.getCourtId() );
	    	
			while ( padCrt.length() < 3 ){
	    		padCrt.insert( 0, "0" );
	    	}
			
            if ( crimCaseId != null & crimCaseId.length() > 3 ){
            	taskTextBuffer.append( crimCaseId.substring( 0,3 ) );
            	taskTextBuffer.append( " /  " );
            	taskTextBuffer.append( crimCaseId.substring( 3,crimCaseId.length() ) );
            	taskTextBuffer.append( ", " );
            }
            
		}
		String caseAssignmentIds = caseAssignmentIdsBuffer.deleteCharAt(caseAssignmentIdsBuffer.lastIndexOf(",")).toString();
		String supervisionOrderIds = supervisionOrderIdBuffer.deleteCharAt(supervisionOrderIdBuffer.lastIndexOf(",")).toString();
		String taskText = taskTextBuffer.deleteCharAt(taskTextBuffer.lastIndexOf(",")).toString();
		
		//create CStask to Program Unit Intake when Supervisor Approves
        CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent();
        
        createTaskEvent.setTopic(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU);  
        createTaskEvent.setWorkGroupId( workgroupId );
    	Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 2);//due date
        createTaskEvent.setDueDate(calendar.getTime());
        createTaskEvent.setSubject2( subject.toString() );
        createTaskEvent.setScenario( pageFlowScenario );
        createTaskEvent.setCaseAssignId( caseAssignmentIds );
        createTaskEvent.setSupervisionOrderId( supervisionOrderIds );
        createTaskEvent.setCriminalCaseId( criminalCaseId ); 
        createTaskEvent.setDefendantId( caseAssignmentForm.getDefendantId() );
        createTaskEvent.setSuperviseeName( caseAssignmentForm.getSuperviseeNameStr() );
        
		String assignmentReturnReason = caseAssignmentForm.getAssignmentReturnReason();
		if(assignmentReturnReason != null && assignmentReturnReason.trim().length() > 0) {
			createTaskEvent.setTaskSubject(CaseloadConstants.REASSIGNMENT_RETURNED);
			taskText = CaseloadConstants.REASSIGNMENT_RETURNED + " for " + taskText + assignmentReturnReason;
		} else {
			createTaskEvent.setTaskSubject( CaseloadConstants.REASSIGNMENT + " to CLO");			
			taskText = CaseloadConstants.REASSIGN_CASES + " for " + taskText + " to CLO"; 
		}
		createTaskEvent.setTastText( taskText );

        createTaskEvent.setCourtId( padCrt.toString() );
        MessageUtil.postRequest(createTaskEvent);           
	}
	
	private void createPUNtTaskForWorkgroup(CaseAssignmentForm caseAssignmentForm, String workgroupId,
			String pageFlowScenario) {
        StringBuffer caseAssignmentIdsBuffer = new StringBuffer();
		StringBuffer supervisionOrderIdBuffer = new StringBuffer();
        StringBuffer taskTextBuffer = new StringBuffer();
        
        taskTextBuffer.append( caseAssignmentForm.getSuperviseeNameStr().trim() );
        taskTextBuffer.append( ",  SPN: ");
        taskTextBuffer.append( caseAssignmentForm.getDefendantId() );
        taskTextBuffer.append( ", ");    	
    	List activeCases = caseAssignmentForm.getActiveCases();
    	if (activeCases.size() < 1){
    		activeCases = caseAssignmentForm.getActiveCasesSelectedForReassignment();
    	}
    	StringBuffer taskCrt = new StringBuffer();
    	String tempCrimCaseId = "";
    	for (Iterator iterator = activeCases.iterator(); iterator.hasNext();) {
    		ICaseAssignment caseAssignment = (ICaseAssignment) iterator.next();
    		
			caseAssignmentIdsBuffer.append( caseAssignment.getCaseAssignmentId() );
			caseAssignmentIdsBuffer.append( "," );
			supervisionOrderIdBuffer.append( caseAssignment.getSupervisionOrderId() );
			supervisionOrderIdBuffer.append( ",");
			tempCrimCaseId = caseAssignment.getCriminalCaseId();
			
			if ( tempCrimCaseId.length() > 4 ){
				
				String cdi =  tempCrimCaseId.substring( 0,3 );
				String CaseNum = tempCrimCaseId.substring( 3 );
				taskTextBuffer.append( cdi );
				taskTextBuffer.append( " /  " );
				taskTextBuffer.append( CaseNum );
				taskTextBuffer.append( ", " );
			}
			

			taskTextBuffer.append( "CRT: " );
			taskTextBuffer.append( caseAssignment.getCourtId() );
			taskTextBuffer.append( "  " );
			taskCrt = new StringBuffer();
			taskCrt.append( caseAssignment.getCourtId() );
		}
		String caseAssignmentIds = caseAssignmentIdsBuffer.deleteCharAt(caseAssignmentIdsBuffer.lastIndexOf(",")).toString();
		String supervisionOrderIds = supervisionOrderIdBuffer.deleteCharAt(supervisionOrderIdBuffer.lastIndexOf(",")).toString();
		String taskText = taskTextBuffer.deleteCharAt(taskTextBuffer.lastIndexOf(",")).toString();
		
		//create CSTask - general task
        CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent(); 
        createTaskEvent.setTopic( CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU );  
        createTaskEvent.setWorkGroupId( workgroupId );
        //due date
		Calendar calendar = Calendar.getInstance();
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

        createTaskEvent.setDueDate( calendar.getTime() );
        
        StringBuffer subject2 = new StringBuffer();
		subject2.append("Reassignment to ")
				.append( caseAssignmentForm.getProgramUnitName() );

		while ( taskCrt.length() < 3 ){
    		taskCrt.insert( 0, "0" );
    	}
		
		createTaskEvent.setCourtId( taskCrt.toString() );
		createTaskEvent.setScenario( pageFlowScenario );
        createTaskEvent.setCaseAssignId( caseAssignmentIds );
        createTaskEvent.setCriminalCaseId( tempCrimCaseId );
        createTaskEvent.setSupervisionOrderId( supervisionOrderIds );
        createTaskEvent.setDefendantId( caseAssignmentForm.getDefendantId() );
        createTaskEvent.setSuperviseeName( caseAssignmentForm.getSuperviseeNameStr() );
		createTaskEvent.setSubject2( subject2.toString() );
		
		String assignmentReturnReason = caseAssignmentForm.getAssignmentReturnReason();
		if( assignmentReturnReason != null && assignmentReturnReason.trim().length() > 0 ) {
			createTaskEvent.setTaskSubject(CaseloadConstants.REASSIGNMENT_RETURNED);
			taskText = CaseloadConstants.REASSIGNMENT_RETURNED + " for " + taskText + assignmentReturnReason;
		} else {
			createTaskEvent.setTaskSubject( "Reassign Cases(s) to Program Unit" );			
			taskText = " Reassignment of  " + taskText; 
		}
		createTaskEvent.setTastText( taskText );
        
        MessageUtil.postRequest(createTaskEvent);           
	}

	// HERE RRY
	private void createTaskForStaffPosition(CaseAssignmentForm caseAssignmentForm, String staffPositionId,
			String pageFlowScenario) {
		
		//due date
		Calendar calendar = Calendar.getInstance();
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
		String superviseeName = caseAssignmentForm.getSuperviseeNameStr().trim();
		String defendantId = caseAssignmentForm.getDefendantId();
    	String tempCrt = "";

		StringBuffer taskTextBuffer = new StringBuffer();
		taskTextBuffer.append( " Reassignment of " );
		taskTextBuffer.append( superviseeName );
		taskTextBuffer.append( ", SPN: ");
		taskTextBuffer.append( defendantId );
		taskTextBuffer.append( ", " );

		StringBuffer caseAssignmentIdsBuffer = new StringBuffer();
		StringBuffer courtIdsBuffer = new StringBuffer();
		StringBuffer supervisionOrderIdBuffer = new StringBuffer();
		String criminalCaseId = "";
		StringBuffer taskCrt = new StringBuffer();
		List activeCases = caseAssignmentForm.getActiveCases();
		if (activeCases.size() < 1){
    		activeCases = caseAssignmentForm.getActiveCasesSelectedForReassignment();
    	}
		for (Iterator iterator = activeCases.iterator(); iterator.hasNext();) {
			ICaseAssignment caseAssignment = (ICaseAssignment) iterator.next();
			caseAssignmentIdsBuffer.append( caseAssignment.getCaseAssignmentId() );
			caseAssignmentIdsBuffer.append( ",");
			supervisionOrderIdBuffer.append( caseAssignment.getSupervisionOrderId() );
			supervisionOrderIdBuffer.append( ",");
			courtIdsBuffer.append( caseAssignment.getCourtId() );
			courtIdsBuffer.append( ", ");
			if ( "".equals( superviseeName ) ){
				
				superviseeName = caseAssignment.getSuperviseeName().trim();
			}
			String crimCaseId = caseAssignment.getCriminalCaseId();
			criminalCaseId = caseAssignment.getCriminalCaseId();
            if ( crimCaseId != null & crimCaseId.length() > 3 ){
            	taskTextBuffer.append( crimCaseId.substring( 0,3 ) );
            	taskTextBuffer.append(  " /  " );
            	taskTextBuffer.append( crimCaseId.substring( 3,crimCaseId.length() ) );
            	taskTextBuffer.append( ", " );
            }

			taskTextBuffer.append( "CRT: " );
			taskTextBuffer.append( caseAssignment.getCourtId() );
			taskTextBuffer.append( " " );
			tempCrt = caseAssignment.getCourtId();
		}
		String caseAssignmentIds = caseAssignmentIdsBuffer.deleteCharAt(caseAssignmentIdsBuffer.lastIndexOf(","))
				.toString();
		String supervisionOrderIds = supervisionOrderIdBuffer.deleteCharAt(supervisionOrderIdBuffer.lastIndexOf(","))
				.toString();
		String taskText = taskTextBuffer.deleteCharAt(taskTextBuffer.lastIndexOf(",")).toString();

		taskCrt.append( tempCrt );
		while ( taskCrt.length() < 3 ){
    		taskCrt.insert( 0, "0" );
    	}
		
		//create CStask - general task
		CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent();
		createTaskEvent.setTopic( CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_OFF );
		createTaskEvent.setStaffPositionId( staffPositionId );
		createTaskEvent.setDueDate(calendar.getTime());
		createTaskEvent.setCourtId( taskCrt.toString() );
		StringBuffer subject2 = new StringBuffer();
		if (pageFlowScenario.equals(CaseloadConstants.REASSIGN_OFFICER_PAGEFLOW)) {
			subject2.append("Reassignment to officer ");
			createTaskEvent.setTaskSubject( "Reassignment to Officer");
			pageFlowScenario = CaseloadConstants.REASSIGN_OFFICER_PAGEFLOW;
		}else if(pageFlowScenario.equals(CaseloadConstants.REASSIGN_CASES_PAGEFLOW)) {
			subject2.append("Reassignment - Officer Acknowledgment ");
			createTaskEvent.setTaskSubject( "Reassignment - Officer Acknowledgment");
		}
		subject2.append( caseAssignmentForm.getOfficerPositionName() );
		createTaskEvent.setSubject2( subject2.toString() );
		createTaskEvent.setSuperviseeName( superviseeName );
		createTaskEvent.setScenario( pageFlowScenario );
		createTaskEvent.setCaseAssignId( caseAssignmentIds );
		createTaskEvent.setSupervisionOrderId( supervisionOrderIds );
		createTaskEvent.setDefendantId( defendantId );
		createTaskEvent.setCriminalCaseId( criminalCaseId );
		createTaskEvent.setTastText( taskText );

        MessageUtil.postRequest(createTaskEvent);

	}
	
	/**
	 * 
	 * @param caseAssignmentForm
	 * @param workgroupId
	 * @param pageFlowScenario
	 */
	private void createCLOTaskForWorkgroup( CaseAssignmentForm caseAssignmentForm, String workgroupId,
			String pageFlowScenario ) {
        
		StringBuffer caseAssignmentIdsBuffer = new StringBuffer();
		StringBuffer supervisionOrderIdBuffer = new StringBuffer();
        StringBuffer taskTextBuffer = new StringBuffer();
        StringBuffer taskCrt = new StringBuffer();
        
    	List activeCases = caseAssignmentForm.getActiveCases();
    	if (activeCases.size() < 1){
    		activeCases = caseAssignmentForm.getActiveCasesSelectedForReassignment();
    	}
    	String crimCaseId = "";
    	String caseNum = "";
    	String court = "";
    	for (Iterator iterator = activeCases.iterator(); iterator.hasNext();) {
    		ICaseAssignment caseAssignment = (ICaseAssignment) iterator.next();
    		
			caseAssignmentIdsBuffer.append( caseAssignment.getCaseAssignmentId() );
			caseAssignmentIdsBuffer.append( "," );
			supervisionOrderIdBuffer.append( caseAssignment.getSupervisionOrderId() );
			supervisionOrderIdBuffer.append( "," );
			court = caseAssignment.getCourtId();

			// Reassign Case(s) of 002/130748201010, CRT: 015, to CLO on LOYA, VINCENT, SPN: 
			 crimCaseId = caseAssignment.getCriminalCaseId();
			 caseNum = caseAssignment.getCriminalCaseId();
            if ( crimCaseId != null & crimCaseId.length() > 3 ){
            	taskTextBuffer.append( crimCaseId.substring( 0,3 ) );
            	taskTextBuffer.append( " /  " );
            	taskTextBuffer.append( crimCaseId.substring( 3,crimCaseId.length() ) );
            	taskTextBuffer.append( ", " );
            }
            
			StringBuffer padCrt = new StringBuffer( caseAssignment.getCourtId() );
	    	
			while ( padCrt.length() < 3 ){
	    		padCrt.insert( 0, "0" );
	    	}
			taskTextBuffer.append( "CRT: " );
			taskTextBuffer.append( padCrt.toString() );
			taskTextBuffer.append( ", " );
		}
    	
    	taskCrt.append( court );
		while ( taskCrt.length() < 3 ){
    		taskCrt.insert( 0, "0" );
    	}
		
		String caseAssignmentIds = caseAssignmentIdsBuffer.deleteCharAt(caseAssignmentIdsBuffer.lastIndexOf(",")).toString();
		String supervisionOrderIds = supervisionOrderIdBuffer.deleteCharAt(supervisionOrderIdBuffer.lastIndexOf(",")).toString();
		
		taskTextBuffer.append( " to CLO on " );
		taskTextBuffer.append( caseAssignmentForm.getSuperviseeNameStr() );
		taskTextBuffer.append(  ", SPN: " );
        taskTextBuffer.append( caseAssignmentForm.getDefendantId() );  
		
		String taskText = taskTextBuffer.deleteCharAt(taskTextBuffer.lastIndexOf(",")).toString();
		//create CS task - general task
        CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent(); 
        createTaskEvent.setTopic(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU);  
        createTaskEvent.setWorkGroupId( workgroupId );
        
		//due date
		Calendar calendar = Calendar.getInstance();
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
		subject2.append("Reassignment to ")
		.append( caseAssignmentForm.getReassignedWorkGroupDescription());
		
		createTaskEvent.setCourtId( taskCrt.toString() );
		createTaskEvent.setDueDate(calendar.getTime());
        createTaskEvent.setScenario( pageFlowScenario );
        createTaskEvent.setCaseAssignId( caseAssignmentIds );
        createTaskEvent.setSupervisionOrderId( supervisionOrderIds );
        createTaskEvent.setCriminalCaseId( caseNum );
        createTaskEvent.setDefendantId( caseAssignmentForm.getDefendantId() );
        createTaskEvent.setSuperviseeName( caseAssignmentForm.getSuperviseeNameStr() );
        createTaskEvent.setSubject2( subject2.toString() );
        
		String assignmentReturnReason = caseAssignmentForm.getAssignmentReturnReason();
		if(assignmentReturnReason != null && assignmentReturnReason.trim().length() > 0) {
			// Special Subject for Reassign CLO
			createTaskEvent.setTaskSubject( CaseloadConstants.REASSIGNMENT_RETURNED );
			taskText = CaseloadConstants.REASSIGNMENT_RETURNED + " for " + taskText + assignmentReturnReason;
		} else {
			createTaskEvent.setTaskSubject( "Reassign Cases(s) to CLO" );			
			taskText = CaseloadConstants.REASSIGN_CASES + " of " + taskText; 
		}
		
		createTaskEvent.setTastText( taskText );
        
        MessageUtil.postRequest(createTaskEvent);           
	}
	
//	private void createTaskForCLOPosition( CaseAssignmentForm caseAssignmentForm, String staffPositionId,
//			String pageFlowScenario ) {
//		
//		//due date
//		Calendar calendar = Calendar.getInstance();
//		int weekday = calendar.get(Calendar.DAY_OF_WEEK); 
//		
//			switch ( weekday) {
//				
//			case 5:
//				calendar.add(Calendar.DATE, 4);
//				break;
//				
//			case 6:
//				calendar.add(Calendar.DATE, 4);
//				break;
//				
//			default:
//				calendar.add(Calendar.DATE, 2);
//				break;
//				
//			}
//
//		String defendantId = caseAssignmentForm.getDefendantId();
//
//		GetCSCDSupervisionStaffEvent staffEvent = (GetCSCDSupervisionStaffEvent) EventFactory
//		.getInstance(CSCDStaffPositionControllerServiceNames.GETCSCDSUPERVISIONSTAFF);
//
//		staffEvent.setStaffPositionId( caseAssignmentForm.getOfficerPositionId() );
//		
//		CSCDSupervisionStaffResponseEvent responseEvent = (CSCDSupervisionStaffResponseEvent) MessageUtil.postRequest(
//				staffEvent, CSCDSupervisionStaffResponseEvent.class);
//
//		String cloPosition = "";
//		if ( responseEvent != null ){
//			
//			cloPosition = responseEvent.getPositionName();
//		}
//		
//		StringBuffer subject2 = new StringBuffer();
//		subject2.append("Reassignment to ")
//		.append( cloPosition );
//		
//		
//		StringBuffer taskTextBuffer = new StringBuffer();
//		taskTextBuffer.append( " Reassignment of ");
//		taskTextBuffer.append(caseAssignmentForm.getSuperviseeName().getFormattedName() + ", SPN: ");
//		taskTextBuffer.append(defendantId + ", ");
//
//		StringBuffer caseAssignmentIdsBuffer = new StringBuffer();
//		StringBuffer courtIdsBuffer = new StringBuffer();
//		StringBuffer supervisionOrderIdBuffer = new StringBuffer();
//
//		String criminalCaseId = "";
//		StringBuffer padCrt = new StringBuffer();
//		List activeCases = caseAssignmentForm.getActiveCases();
//		for (Iterator iterator = activeCases.iterator(); iterator.hasNext();) {
//			ICaseAssignment caseAssignment = (ICaseAssignment) iterator.next();
//			caseAssignmentIdsBuffer.append(caseAssignment.getCaseAssignmentId() + ",");
//			supervisionOrderIdBuffer.append(caseAssignment.getSupervisionOrderId() + ",");
//			courtIdsBuffer.append(caseAssignment.getCourtId() + ",");
//
//			String crimCaseId = caseAssignment.getCriminalCaseId();
//			criminalCaseId = caseAssignment.getCriminalCaseId();
//            if ( crimCaseId != null & crimCaseId.length() > 3 ){
//            	crimCaseId = crimCaseId.substring( 0,3 ) + " /  " + crimCaseId.substring( 3,crimCaseId.length() );
//            	taskTextBuffer.append( crimCaseId +  ", " );
//            }
//
//			padCrt.append( caseAssignment.getCourtId() );
//	    	
//			while ( padCrt.length() < 3 ){
//	    		padCrt.insert( 0, "0" );
//	    	}
//			taskTextBuffer.append( "CRT: " + padCrt.toString() );
//		}
//		String caseAssignmentIds = caseAssignmentIdsBuffer.deleteCharAt(caseAssignmentIdsBuffer.lastIndexOf(","))
//				.toString();
//		String courtIds = courtIdsBuffer.deleteCharAt(courtIdsBuffer.lastIndexOf(",")).toString();
//		String supervisionOrderIds = supervisionOrderIdBuffer.deleteCharAt(supervisionOrderIdBuffer.lastIndexOf(","))
//				.toString();
//		String taskText = taskTextBuffer.deleteCharAt(taskTextBuffer.lastIndexOf(",")).toString();
//
//		//create nt task - general task
//		CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent();
//		createTaskEvent.setTopic(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_OFF);
//		createTaskEvent.setStaffPositionId( staffPositionId );
//		createTaskEvent.setDueDate(calendar.getTime());
//		createTaskEvent.setTaskSubject( "Reassignment to CLO");
//		createTaskEvent.setScenario( pageFlowScenario );
//		createTaskEvent.setCaseAssignId( caseAssignmentIds );
//		createTaskEvent.setSupervisionOrderId( supervisionOrderIds );
//		createTaskEvent.setCriminalCaseId( criminalCaseId );
//		createTaskEvent.setDefendantId( defendantId );
//		createTaskEvent.setSuperviseeName( caseAssignmentForm.getSuperviseeName().getFormattedName() );
//		createTaskEvent.setTastText( taskText );
//		createTaskEvent.setCourtId( padCrt.toString() );
//
//		MessageUtil.postRequest(createTaskEvent);
//
//	}

    public void updateSupeviseeDetails(CaseAssignmentForm caseAssignmentForm, String superviseeUpdateType) {
		
		AssignSuperviseeService assignSuperviseeService = AssignSuperviseeService.getInstance();
		assignSuperviseeService.updateSuperviseeDetails(caseAssignmentForm, superviseeUpdateType);
	}
	
	/**
	 * 
	 * @param caseAssignmentForm
	 */
	public void initiateProgramUnitReferral ( CaseAssignmentForm caseAssignmentForm ){
		
		       char stateRptCd = 'X'; // Default outside of range
		       String stateRptCdString = caseAssignmentForm.getStateReportingCode();
		       if ( stateRptCdString != "" && stateRptCdString != null ){
		    	   
		    	   stateRptCd = stateRptCdString.charAt( 0 );
		       }
		       
				switch ( stateRptCd ){
				
				case 'Y':
					// Initiate and submit referral
					initiateProgramReferral( caseAssignmentForm, true );
					break;
				
				case 'X': //Do nothing
					// If a org does not have state reporting cd
					closeExistingProgramUnitReferral( caseAssignmentForm );
					break;
					
				default: // Handles N and W
					// just initiate referral
					initiateProgramReferral( caseAssignmentForm, false );
					break;
				}
		}
		
	/**
	 * 
	 * @param caseAssignmentForm
	 */
	private void initiateProgramReferral( CaseAssignmentForm caseAssignmentForm, boolean subRef ){
		
		
		if ( caseAssignmentForm.isProgramUnitRef() ){
			
			GetProgramUnitProgramEvent getEvent = new GetProgramUnitProgramEvent();
			
			// Get the associated program name for PU
			getEvent.setProgramUnitId( caseAssignmentForm.getProgramUnitId() );
			getEvent.setProgramStatus( CSAdministerServiceProviderConstants.ACTIVE_PROG_STATUS );
			
			CSProgramResponseEvent 	pup_program = ( CSProgramResponseEvent )
									MessageUtil.postRequest( getEvent, 	CSProgramResponseEvent.class);
			
		
			SaveProgramUnitReferralEvent save_event = ( SaveProgramUnitReferralEvent )EventFactory.
										getInstance(CSProgramReferralControllerServiceNames.SAVEPROGRAMUNITREFERRAL );
			
			if ( pup_program.getLocationIds() != null ){
				
				save_event.setReferralTypeCode( pup_program.getReferralTypeCode() );
				List programLocIds = (List) pup_program.getProgramLocationIds();
				
				if ( programLocIds.size() > 0 ){
					
					save_event.setLocationId( String.valueOf( programLocIds.get( 0 )));
				}
	
				save_event.setCriminalCaseId( caseAssignmentForm.getCaseNum() );
				save_event.setDefendantId( caseAssignmentForm.getDefendantId() );
				save_event.setSubmitReferral( subRef );
				save_event.setProgramUnitReferral( true );
				save_event.setPlacementReasonCd( caseAssignmentForm.getReasonForPlacementId());
				save_event.setProgramBeginDate( DateUtil.stringToDate( caseAssignmentForm.getProgramBeginDateAsStr(), DateUtil.DATE_FMT_1 ));
				save_event.setReferralDate( DateUtil.stringToDate(caseAssignmentForm.getReferralDateAsStr(), DateUtil.DATE_FMT_1));
				
				MessageUtil.postRequest( save_event );
			}
		}
		
		if ( caseAssignmentForm.isCloseProgramUnitRef() ){
			
			this.closeExistingProgramUnitReferral( caseAssignmentForm );
		}
		
	}
	
	/**
	 * 
	 * @param caseAssignmentForm
	 */
	private static void saveCasenoteForExitReferral( CaseAssignmentForm caseAssignmentForm )
 	{
 		SaveProgRefCasenoteEvent saveCasenoteEvent = new SaveProgRefCasenoteEvent();
 		
 		saveCasenoteEvent.setProgramReferralId( caseAssignmentForm.getCsProgReferralToCloseId() );
 		
 		saveCasenoteEvent.setCasenoteSubjectCd(PDCodeTableConstants.PROGRAM_REFERRAL_SUBJECT_CD);
 		
 		saveCasenoteEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
 		saveCasenoteEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
 		saveCasenoteEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
 		
 		saveCasenoteEvent.setCasenoteContext(CSAdministerProgramReferralsConstants.CASENOTE_CONTEXT_PROGRAM_REFERAL);
 		
 		StringBuffer notes = new StringBuffer();
 		notes.append("Program Referral Exited for Case#: ");
 		notes.append( caseAssignmentForm.getCriminalCaseId() );
 		
  		notes.append(", Begin Date: ");
 		notes.append(caseAssignmentForm.getProgramBeginDateAsStr());
 		
 		notes.append(", Reason for Discharge: ");
 		notes.append(caseAssignmentForm.getReasonForDischargeDesc());
 		
 		notes.append(", End Date: ");
 		notes.append(caseAssignmentForm.getProgramEndDateAsStr());
 
 		
 		saveCasenoteEvent.setCasenoteComments(notes.toString());
 		
 		MessageUtil.postRequest( saveCasenoteEvent );
 	}
	
	/**
	 * 
	 * @param caseAssignmentForm
	 */
	private void closeExistingProgramUnitReferral( CaseAssignmentForm caseAssignmentForm ){
		
		// Process the exit referral
		if ( caseAssignmentForm.isCloseProgramUnitRef() ){
			
			ExitProgramReferralEvent reqEvent = new ExitProgramReferralEvent();
			
			reqEvent.setProgramReferralId( caseAssignmentForm.getCsProgReferralToCloseId() );			
			reqEvent.setProgramEndDate( DateUtil.stringToDate( caseAssignmentForm.getProgramEndDateAsStr(), DateUtil.DATE_FMT_1 ));
			reqEvent.setDischargeReasonCd( caseAssignmentForm.getReasonForDischargeId());

			MessageUtil.postRequest( reqEvent );
			saveCasenoteForExitReferral( caseAssignmentForm );			
		}
	}
	
}// End of class
