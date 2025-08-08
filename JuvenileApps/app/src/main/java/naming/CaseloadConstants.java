/*
 * Created on May 11, 2006
 * 
 */
package naming;

public class CaseloadConstants
{
    public static final int DEFENDANT_ID_LEN = 8;
    public static final String DEFENDANT_ID_PAD_CHAR = "0";
    
    public static final String NEW_ORDER_FOR_SUPERVISION = " New Order for Supervision" ;
    public static final String ASSIGNMENT_RETURNED = "Assignment Returned";
    public static final String REASSIGNMENT = "Reassignment";
    public static final String REASSIGNMENT_RETURNED = "Reassignment Returned";
    public static final String REASSIGN_CASES = "Reassign Case(s)";
    public static final String CASENUMBER="caseNumber";
    public static final String CDI="cdi";
    
    public static final String ASSIGN_PROGRAM_UNIT_PAGEFLOW = "assignprogramunitpageflow" ; 
    public static final String ALLOCATE_SUPERVISOR_PAGEFLOW = "allocatesupervisorpageflow" ; 
    public static final String ASSIGN_OFFICER_PAGEFLOW = "assignofficerpageflow" ; 
    public static final String OFFICER_ACCEPT_PAGEFLOW = "officeracceptpageflow" ; 
    
    public static final String CLO_PROGRESS_ORDER_PAGEFLOW="cloProgressOrderPageFlow";

    // Reassign 
    public static final String REASSIGN_PROGRAM_UNIT_PAGEFLOW = "reassignprogramunitpageflow" ; 
    public static final String REALLOCATE_SUPERVISOR_PAGEFLOW = "reallocatesupervisorpageflow" ; 
    public static final String REASSIGN_OFFICER_PAGEFLOW = "reassignofficerpageflow" ; 
    public static final String REASSIGN_CASES_PAGEFLOW = "reassigncasespageflow" ; 
    public static final String REASSIGN_COURT_SERVICES_RECEIVING_INBOUND_PAGEFLOW = "reassigncourtservicesreceivinginboundpageflow";
    public static final String REQUEST_SUPERVISEE_TRANSFER = "requestSuperviseeTransfer";
    public static final String REASSIGN_CASES_FROM_CSO_TO_CLO_PAGEFLOW = "reassignCasesFromCsoToCloPageflow";
    
    public static final String USER_JIMS_LOGON_ID_PROPERTY="JIMSLogonId" ;
    
    // Casenote Constants
    public static final String CASENOTE_DATE_FMT = "MM/dd/yyyy, hh:mm";
   
    public static final String CASE_ASSIGNMENT_ID_KEY = "caseAssignmentId" ;

    public static final String CASE_ASSIGNMENT_ID_LIST= "caseAssignmentIdList" ;
    
    public static final String CSCD_AGENCY_ID = "CSC" ; 

    //	Scenario and Previous Scenario Constants
    public static final String SC_ASSIGN_NEW_CASE = "assignNewCase";
    
    public static final String SC_REASSIGN_SUPERVISEE = "reassignSupervisee";
    
    public static final String SC_REASSIGN_CASE = "reassignCase";

    public static final String WORKGROUP_OID_PREPEND_STRING = "CSWORKGROUP_" ; 

    public static final String POSITION_OID_PREPEND_STRING = "CSPOSITION_" ; 
    
    // Task State Constants
    public static final String ACTIVITY_IND = "activityInd";

    public static final String TASK_STATE_SPN = "SPN" ; 
    
    public static final String TASK_STATE_CASE_ASSIGNMENT_ID = "caseAssignmentId" ; 
    
    
    public static final String WORKFLOW_IND = "workflowInd";

    public static final String SCENARIO = "scenario";       
    
    public static final String CRIMINAL_CASE_ID="criminalCaseId";

    public static final String DEFENDANT_ID = "defendantId";
    
    public static final String SUPERVISION_ORDER_ID = "supervisionOrderId";
    
    public static final String SUPERVISION_ORDER_ID_LIST = "supervisionOrderIdList";

    public static final String CASE_ASSIGNMENT_ID = "caseAssignmentId";    
    
    public static final String SUPERVISOR_POSITION_ID = "supervisorPositionId";
    public static final String SUPERVISOR_FIRST_NAME = "supervisorFirstName";
    public static final String SUPERVISOR_MIDDLE_NAME = "supervisorMiddleName";
    public static final String SUPERVISOR_LAST_NAME = "supervisorLastName";
    
    public static final String OFFICER_POSITION_ID = "officerPositionId";
    public static final String COURT_ID = "courtId"; 
    public static final String PROGRAM_UNIT_ID = "programUnitId";
    
    // Workflow Constants
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
    
//    public static final String WF_CASELOAD_SEARCH_BY_NAME = "searchByName";
//    
//    public static final String WF_CASELOAD_SEARCH_BY_DEFENDANT_ID = "searchByDefendantId";
//    
//    public static final String WF_CASELOAD_SEARCH_BY_OFFICER = "searchByOfficer";    
    
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
    
    public static final String ACKNOWLEDGMENT_STATUS_VERIFIED = "V";
    
    public static final String ACKNOWLEDGMENT_STATUS_ASSUMED = "A";

    // Level of Supervision Constants
    public static final String LOS0 = "LOS0";

    public static final String LOS1 = "LOS1";

    public static final String LOS2 = "LOS2";

    public static final String LOS3 = "LOS3";

    public static final String LOS4 = "LOS4";

    public static final String IND = "IND";
    
    public static final String LEGACY_LOS_NEW_COMPACT_CASES = "E";
    public static final String LEGACY_LOS_CSR = "F";
    public static final String LEGACY_LOS_DIRECT_MEDIUM = "3";
    public static final String LEGACY_LOS_DIRECT_MINIMUM = "4";
    
    public static final String LEGACY_LOS_PRETRIAL_INDIRECT = "H8";
    public static final String LEGACY_LOS_PRETRIAL_DIRECT = "H7";
    public static final String LEGACY_LOS_PRETRIAL_BOND = "HB";
    
    public static final int LOS0_INDEX = 0;

    public static final int LOS1_INDEX = 1;    

    public static final int LOS2_INDEX = 2;

    public static final int LOS3_INDEX = 3;

    public static final int LOS4_INDEX = 4;

    public static final int IND_INDEX = 5;

    public static final int MULTIPLIER_COUNT = 6;

    public static final String TASK_TEXT = "taskText";

 //   public static final String SUPERVISEE_CONTEXT = "supervisee";

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
    public static final String CSTASK_TOPIC_NOTIFY_SUPERVISEE_TO_OFF = "CS.NOTIFY.SUPERVISEE.TO.OFF";
    public static final String CSTASK_TOPIC_NOTIFY_CLO_PROGRESS_ORDER = "CS.NOTIFY.CLO.PROGRESS.ORDER";
    public static final String CSTASK_TOPIC_PROCESS_OFF_NEW_CASE_ASSIGN = "CS.PROCESS.OFF.NEW.CASE.ASSIGN";
    public static final String CSTASK_TOPIC_REASSIGN_CASES = "CS.REASSIGN.CASES";
    
    public static final String SEARCH_CASELOAD_BY_SELECTED = "searchCaseloadBySelected";
    public static final String SEARCH_CASELOAD_BY_SPN = "searchCaseloadBySPN";
    public static final String SEARCH_CASELOAD_BY_NAME = "searchCaseloadByName";
    public static final String SEARCH_CASELOAD_BY_CASELOAD = "searchCaseloadByCaseload";
    public static final String SEARCH_CASELOAD_BY = "searchCaseloadBy";
    public static final String SEARCH_CASELOAD_BY_SUPERVISOR = "searchCaseloadBySupervisor";
    public static final String SEARCH_CASELOAD_BY_OFFICER = "searchCaseloadByOfficer";
    public static final String SEARCH_CASELOAD_BY_DIVISION = "searchCaseloadByDivision";
    public static final String SEARCH_CASELOAD_BY_QUADRANT = "searchCaseloadByQuadrant";
    public static final String SEARCH_CASELOAD_BY_ZIPCODE = "searchCaseloadByZipCode";
    public static final String SHOW_RESULTS = "showResults";

    //Supervisee and Supervisee history
    public static final String SUPERVISEE_ASSIGNED_TO_PU = "ASSIGN_TO_PU";
    public static final String SUPERVISEE_ASSIGNED_TO_STAFF = "ASSIGN_TO_STAFF";
    public static final String SUPERVISEE_ALLOCATE_TO_SUPERVISOR = "ALLOCATE_TO_SUPERVISOR";
    public static final String SUPERVISEE_REASSIGNED_TO_PU = "REASSIGN_TO_PU";
    public static final String SUPERVISEE_REASSIGNED_TO_STAFF = "REASSIGN_TO_STAFF";
    public static final String SUPERVISEE_UPDATE_CREDIT = "SUPERVISEE_UPDATE_CREDIT";
    
    public static final String SUPERVISEE_HISTORY_CREATE = "create";
    public static final String SUPERVISEE_HISTORY_UPDATE_DNA = "upd_dna";
    public static final String SUPERVISEE_HISTORY_UPDATE_LOS = "upd_los";
    public static final String SUPERVISEE_HISTORY_UPDATE_PROGRAM_TRACKER = "upd_pgtrkr";
    public static final String SUPERVISEE_HISTORY_UPDATE_PU = "upd_pu";
    public static final String SUPERVISEE_HISTORY_UPDATE_STAFF = "upd_staff";
    public static final String SUPERVISEE_HISTORY_UPDATE_ZIP = "upd_zip";
    public static final String SUPERVISEE_HISTORY_CLOSE_SUPERVISION = "CLOSE_SUP";
    public static final String SUPERVISEE_HISTORY_UPDATE_CREDIT = "UPD_CRED";
    public static final String SUPERVISEE_HISTORY_CORRECT_CREDIT = "CRCT_CRED";
    
    //Case Assignment States
    public static final String PROGRAM_UNIT_ASSIGNED = "PROGRAM_UNIT_ASSIGNED";
    public static final String SUPERVISOR_ALLOCATED = "SUPERVISOR_ALLOCATED";
    public static final String OFFICER_ASSIGNED = "OFFICER_ASSIGNED"; 
    public static final String OFFICER_ACKNOWLEDGED = "OFFICER_ACKNOWLEDGED";
    public static final String CASE_CLOSED = "CASE_CLOSED";
    
    //View case assignment search
    public static final String FILTER_BY_PROGRAMUNIT = "FILTER_BY_PROGRAMUNIT";
    public static final String FILTER_BY_WORKGROUP = "FILTER_BY_WORKGROUP";
    public static final String FILTER_BY_USER = "FILTER_BY_USER";
    public static final String FILTER_BY_DEFENDANT = "FILTER_BY_DEFENDANT";
    public static final String FILTER_BY_CASE = "FILTER_BY_CASE";
    public static final String FILTER_BY_PU_TRANSACTION_DATE = "FILTER_BY_PU_TRANSACTION_DATE";
	public static final String TYPE = "type";  
}
