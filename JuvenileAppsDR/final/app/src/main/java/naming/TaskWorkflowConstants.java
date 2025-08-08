/*
 * Created on May 11, 2006
 * 
 */
package naming;

public class TaskWorkflowConstants
{
    // Casenote Constants
    public static final String CASENOTE_DATE_FMT = "MM/dd/yyyy, hh:mm";
    
    //	Scenario and Previous Scenario Constants
    public static final String SC_ASSIGN_NEW_CASE = "assignNewCase";
    
    public static final String SC_REASSIGN_SUPERVISEE = "reassignSupervisee";
    
    public static final String SC_REASSIGN_CASE = "reassignCase";

    // Task State Constants
    public static final String ACTIVITY_IND = "activityInd";

    public static final String TASK_STATE_SPN = "SPN" ; 
    
    public static final String TASK_STATE_CASE_ASSIGNMENT_ID = "caseAssignmentId" ; 
    
    
    public static final String WORKFLOW_IND = "workflowInd";

    public static final String SCENARIO = "scenario";       

    public static final String DEFENDANT_ID = "defendantId";
    
    public static final String SUPERVISION_ORDER_ID = "supervisionOrderId";

    public static final String CASE_ASSIGNMENT_ID = "caseAssignmentId";    
    
    public static final String SUPERVISOR_POSITION_ID = "supervisorPositionId";
    public static final String SUPERVISOR_FIRST_NAME = "supervisorFirstName";
    public static final String SUPERVISOR_MIDDLE_NAME = "supervisorMiddleName";
    public static final String SUPERVISOR_LAST_NAME = "supervisorLastName";
  
    // Workflow constants for PASO
    public static final String WF_CREATE_NEW_CONDITION = "createNewCondition";
    public static final String CONDITION_CODE = "conditionCode";
    
    // Workflow Constants for Casenotes
    public static final String WF_CREATE_CASENOTE = "createCasenote";
    public static final String WF_CREATE_CASENOTE_SUMMARY = "createCasenoteSummary";

   
    
    public static final String WF_ASSIGN_SUPERVISEE_TO_PROGRAMUNIT = "assignSuperviseeToPU";
    
    public static final String WF_ASSIGN_SUPERVISEE_TO_PROGRAMUNIT_SELECT_PU = "assignSuperviseeToPU_SelectPU";
    public static final String WF_ASSIGN_SUPERVISEE_TO_PROGRAMUNIT_SELECT_WG = "assignSuperviseeToPU_SelectWG";
    public static final String WF_ASSIGN_SUPERVISEE_TO_PROGRAMUNIT_CREATE_CN = "assignSuperviseeToPU_CreateCN";    
    
    public static final String WF_ASSIGN_SUPERVISEE_TO_PROGRAMUNIT_SUMMARY = "assignSuperviseeToPU_Summary";
    public static final String WF_ASSIGN_SUPERVISEE_TO_PROGRAMUNIT_CONFIRM = "assignSuperviseeToPU_Confirm";    

    public static final String WF_REASSIGN_CASE_TO_CLO = "reassignCaseToCLO";
    public static final String WF_REASSIGN_CASE_CREATE_APPROVE_TASK = "reassignCaseCreateApproveTask";
    public static final String WF_REASSIGN_CASE_DIRECT_CREATE_TASK = "reassignCaseDirectCreateTask";
    public static final String WF_REASSIGN_CASE_INBOUND_CREATE_TASK = "reassignCaseInboundCreateTask";
    public static final String WF_REASSIGN_CASE_OUTBOUND_CREATE_TASK = "reassignCaseOutboundCreateTask";
    public static final String WF_REASSIGN_CASE_SELECT_WORKGROUP = "reassignCaseSelectWorkgroup";
    public static final String WF_REASSIGN_CASE_CREATE_TASK_SUMMARY = "reassignCaseCreateTaskSummary";    
    public static final String WF_REASSIGN_CASE_CREATE_TASK_CONFIRM = "reassignCaseCreateTaskConfirm";
    public static final String WF_REASSIGN_CASE_TO_CLO_CONFIRM = "reassignCaseToCLOConfirm";
    
    public static final String WF_REASSIGN_CASE_INBOUND_ACK_CREATE_TASK = "reassignCaseInboundAcknowledgeCreateTask";
    public static final String WF_REASSIGN_CASE_APPROVE_TASK = "reassignCaseInboundApproveTask";
    public static final String WF_REASSIGN_CASE_APPROVE_TASK_CONFIRM = "reassignCaseInboundApproveTaskConfirm";
    
    public static final String WF_REASSIGN_SUPERVISEE_CSR_ACK = "reassignSuperviseeCSRAck";
    public static final String WF_REASSIGN_SUPERVISEE_CSR_ACK_CONFIRM = "reassignSuperviseeCSRAckConfirm";
    
    public static final String WF_REASSIGN_CASE_OUTBOUND = "reassignCaseOutbound";
    
    public static final String WF_ASSIGN_SUPERVISEE_TO_OFFICER = "assignSuperviseeToOff";

    public static final String WF_ALLOCATE_SUPERVISEE_TO_SUPERVISOR = "allocateSuperviseeToSup";
    
    public static final String WF_ALLOCATE_SUPERVISEE_TO_SUPERVISOR_SELECT_SUP = "allocateSuperviseeToSup_SelectSup";
    public static final String WF_ALLOCATE_SUPERVISEE_TO_SUPERVISOR_CREATE_CN = "allocateSuperviseeToSup_CreateCN";
    
    public static final String WF_ALLOCATE_SUPERVISEE_TO_SUPERVISOR_SUMMARY = "allocateSuperviseeToSup_Summary";
    public static final String WF_ALLOCATE_SUPERVISEE_TO_SUPERVISOR_CONFIRM = "allocateSuperviseeToSup_Confirm";
        
    public static final String WF_ASSIGN_SUPERVISEE_TO_OFFICER_CASELOADSUMMARY = "assignSuperviseeToOff_CaseloadSummary";
    public static final String WF_ASSIGN_SUPERVISEE_TO_OFFICER_CREATE_CN = "assignSuperviseeToOff_CreateCN";
    public static final String WF_ASSIGN_SUPERVISEE_TO_OFFICER_SUMMARY = "assignSuperviseeToOff_Summary";
    public static final String WF_ASSIGN_SUPERVISEE_TO_OFFICER_CONFIRM = "assignSuperviseeToOff_Confirm";

    public static final String WF_PROCESS_OFFICER_NEW_CASE_ASSIGNMENT = "processOffNewCaseAssign";
    
    public static final String WF_REASSIGN_SUPERVISEE_TO_CLO = "assignSuperviseeToCLO";
    
    public static final String WF_REASSIGN_SUPERVISEE_TO_PROGRAMUNIT = "assignSuperviseeToPU";

    public static final String WF_CASELOAD_SEARCH_RESULTS = "caseloadSearchResults";
    
    public static final String WF_REASSIGN_SUPERVISEE_TO_CSO = "assignSuperviseeToCSO";

    public static final String WF_REALLOCATE_SUPERVISEE_TO_SUPERVISOR = "reallocateSuperviseeToSupervisor";

    
    public static final String WF_TASKLIST = "WF_TASKLIST";

    public static final String WF_ASSIGN_SUPERVISEE_TO_SUPERVISOR = "assignSuperviseeToSup";
    
    public static final String WF_CASELOAD_SEARCH_BY_NAME = "searchByName";
    
    public static final String WF_CASELOAD_SEARCH_BY_DEFENDANT_ID = "searchByDefendantId";
    
    public static final String WF_CASELOAD_SEARCH_BY_OFFICER = "searchByOfficer";    
    
    // Transaction Operation Constants
    public static final String WF_PAPERFILE_RECEIVED = "paperFileRecieved";
    
    // Acknowledge Role Codes
    
    public static final String OP_ACKNOWLEDGE_WORKGROUP_ASSIGNMENT = "ACKWGAS";
    public static final String OP_ACKNOWLEDGE_SUPERVISOR_ASSIGNMENT = "ACKSUAS";    
    public static final String OP_ACKNOWLEDGE_OFFICER_ASSIGNMENT = "ACKOFAS";
    public static final String OP_ACKNOWLEDGE_PROGRAMUNIT_REASSIGNMENT = "ACKPUREAS";
    public static final String OP_ACKNOWLEDGE_WORKGROUP_REASSIGNMENT = "ACKWGREAS";
    public static final String OP_ACKNOWLEDGE_SUPERVISOR_REASSIGNMENT = "ACKSUREAS";    
    public static final String OP_ACKNOWLEDGE_OFFICER_REASSIGNMENT = "ACKOFREAS";

    public static final String OP_SUPERVISIONSTYLE_CHANGE = "SVST";

    // Business Constants
    public static final String SUPERVISION_STYLE_DIRECT = "D";

    public static final String SUPERVISION_STYLE_INDIRECT = "I";    

    // Level of Supervision Constants
    public static final String LOS0 = "LOS0";

    public static final String LOS1 = "LOS1";

    public static final String LOS2 = "LOS2";

    public static final String LOS3 = "LOS3";

    public static final String LOS4 = "LOS4";

    public static final String IND = "IND";
    
    public static final int LOS0_INDEX = 0;

    public static final int LOS1_INDEX = 1;    

    public static final int LOS2_INDEX = 2;

    public static final int LOS3_INDEX = 3;

    public static final int LOS4_INDEX = 4;

    public static final int IND_INDEX = 5;

    public static final int MULTIPLIER_COUNT = 6;

    public static final String TASK_TEXT = "taskText";

    public static final String NONE_OTHER_CONTACTMETHOD = "NO";
    
    public static final String ASSIGNMENT_SUBJECT_CD = "AN";

    public static final String CASENOTE_SUBJECT_CODE_TABLE_NAME = "CASENOTE_SUBJECT";
    
    public static final String ASSIGNMENT_SUBJECT = "Assignment";
    
    public static final String ASSIGN_CSO = "ASCSO";
    
    public static final String ASSIGN_CLO = "ASCLO";
    
    public static final String ASSIGN_SUPERVISOR = "ASSP";
    
    public static final String ASSIGN_PROGRAMUNIT = "ASPU";    
    
    public static final String CHANGE_SUPERVISIONSTYLE = "CHSS";
    
    public static final String CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU = "CS.ASSIGN.SUPERVISEE.TO.PU";
    public static final String CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP = "CS.ALLOCATE.SUPERVISEE.TO.SUP";
    public static final String CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_OFF = "CS.ASSIGN.SUPERVISEE.TO.OFF";
    public static final String CSTASK_TOPIC_PROCESS_OFF_NEW_CASE_ASSIGN = "CS.PROCESS.OFF.NEW.CASE.ASSIGN";
    
    public static final String SEARCH_CASELOAD_BY_SELECTED = "searchCaseloadBySelected";
    public static final String SEARCH_CASELOAD_BY_SPN = "searchCaseloadBySPN";
    public static final String SEARCH_CASELOAD_BY_NAME = "searchCaseloadByName";
    public static final String SEARCH_CASELOAD_BY_CASELOAD = "searchCaseloadByCaseload";
    public static final String SEARCH_CASELOAD_BY = "searchCaseloadBy";
    public static final String SEARCH_CASELOAD_BY_SUPERVISOR = "searchCaseloadBySupervisor";
    public static final String SEARCH_CASELOAD_BY_OFFICER = "searchCaseloadByOfficer";
    public static final String SEARCH_CASELOAD_BY_DIVISION = "searchCaseloadByDivision";
}
