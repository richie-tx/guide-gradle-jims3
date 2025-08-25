/*
 * Created on April 10, 2008
 *
 */
package naming;

/**
 * @author mchowdhury
 *
 */
public class ViolationReportConstants {
    public static final String PARAM_DEFENDANT_ID = "defendantId";
    public static final String PARAM_SUPERVISEE_NAME = "superviseeName";
    public static final String PARAM_OFFICER_NAME = "officerName";
    public static final String PARAM_OFFENSE = "offense";
    public static final String PARAM_CDI = "cdi";
    public static final String PARAM_SEVERITYLEVEL = "severitylevel";
    public static final String PARAM_LOS = "los";
    public static final String PARAM_PROGRAM_UNIT = "programUnit";
    public static final String PARAM_COURT_NUMBER = "courtNumber";
    public static final String CASE_ID = "caseId";
    public static final String PARAM_NCRESPONSE_ID = "ncResponseId";
    public static final String REQUEST_MENTAL_HEALTH = "MH";
    public static final String REQUEST_MENTAL_HEALTH_COMMENTS = "MHC";
    public static final String REQUEST_MENTAL_HEALTH_DIAGNOSIS = "MHD";
    public static final String REQUEST_LAW_VIOLATION = "LWV";
    public static final String REQUEST_REPORTING = "RPT";
    public static final String REQUEST_REASON_FOR_TRANSFER = "RFT";
    public static final String REQUEST_DELINQUENT_FEE = "DFE";
    public static final String REQUEST_PREVIOUS_COURT_ACTIVITY = "PCA";
    public static final String REQUEST_TREATMENT = "TRM";
    public static final String REQUEST_COMMUNITY_SERVICE = "CMS";
    public static final String REQUEST_POSITIVE_UA = "PUA";
    public static final String REQUEST_EMPLOYMENT = "EMP";
    public static final String REQUEST_RECOMMENDATION = "RCM";
	public static final String CREATE_MODE = "create";
	public static final String UPDATE_MODE = "update";
	public static final String GROUP_NAME = "groupName";
	public static final String GROUP_ALCOHOL_DRUGS = "ALCOHOL/DRUGS";
	public static final String GROUP_REPORTING = "REPORTING";
	public static final String DRAFT = "DRAFT";
	public static final String REPORTTYPE = "type";
	
	public static final String REPORTTYPE_VIOLATION = "VR";
	public static final String REPORTTYPE_CASESUMMARY = "CS";
	
	public static final String PREVIOUS_COURT_ACTIVITY_VIOLATION = "Violation";
	public static final String PREVIOUS_COURT_ACTIVITY_MOTION = "Motion to Adjudicate";
	public static final String PREVIOUS_COURT_ACTIVITY_EXTENSION = "Extension";	
	public static final String PREVIOUS_COURT_ACTIVITY_ADMONISHMENT = "Admonishment";	
	
	public static final String PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION = "Violation";
	public static final String PREVIOUS_COURT_ACTIVITY_TYPE_MOTION = "Motion";
	public static final String PREVIOUS_COURT_ACTIVITY_TYPE_OTHER = "Other";	
	
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";	
	public static final String VIOLATIONREPORT = "VIOLATIONREPORT";	
	public static final String CASESUMMARY = "CASESUMMARY";	
	public static final String ASSIGNMENT = "ASSIGNMENT";	
	public static final String REASSIGNMENT = "REASSIGNMENT";		
	
	public static final String LAWVIOLATION_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.LawViolationDAO";
	public static final String COMMUNITY_SERVICE_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.CommunityServiceDAO";
	public static final String REPORTING_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.ReportingDAO";	
	public static final String POSITIVEUA_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.PositiveUADAO";
	public static final String PREVIOUS_COURT_ACTIVITY_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.PreviousCourtActivityDAO";
	public static final String REASON_FOR_TRANSFER_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.ReasonForTransferDAO";
	public static final String DELINQUENT_FEE_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.DelinquentFeeDAO";
	public static final String EMPLOYMENT_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.EmploymentDAO";
	public static final String RECOMMENDATION_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.RecommendationDAO";
	public static final String TREATMENT_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.TreatmentDAO";
	public static final String WORKFLOW_ASSIGNMENT_REASSIGNMENT_DAO_LOCATOR = "pd.supervision.posttrial.transactions.daos.WFAssignmentReassignmentDAO";
	public static final String WORKFLOW_VIOLATIONREPORT_CASESUMMARY_DAO_LOCATOR = "pd.supervision.posttrial.transactions.daos.WFViolationReportCaseSummaryDAO";
	public static final String MENTAL_HEALTH_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.MentalHealthDAO";
		
	// Task releted constants
	public static final String NEW_VIOLATION_FOR_APPROVAL = "New Violation Report for Appro";
	public static final String CSTASK_TOPIC_NEW_VIOLATION_FOR_APPROVAL = "CS.TASK.VIOLATIONREPORT.NEW.APPROVAL";
	public static final String CSTASK_TOPIC_VIOLATION_SUBMISSION_REVIEW = "CS.TASK.VIOLATIONREPORT.SUBMISSION.REVIEW";
	public static final String CSTASK_TOPIC_VIOLATION_SUBMISSION_APPROVAL = "CS.TASK.VIOLATIONREPORT.SUBMISSION.APPROVAL";
	public static final String CSTASK_TOPIC_VIOLATION_SUBMISSION_REQUIRED = "CS.TASK.VIOLATIONREPORT.SUBMISSION.REQUIRED";
	public static final String CSTASK_TOPIC_VIOLATION_UPDATES_REQUIRED = "CS.TASK.VIOLATIONREPORT.UPDATES.REQUIRED";
	public static final String CSTASK_SUBJECT_NEW_VIOLATION_FOR_APPROVAL = "New Violation Report for Approval";
	public static final String CSTASK_SUBJECT_VIOLATION_SUBMISSION_REVIEW = "Violation Report for submission review";
	public static final String CSTASK_SUBJECT_VIOLATION_SUBMISSION_APPROVAL = "Violation Report Submission Approval request";
	public static final String CSTASK_SUBJECT_VIOLATION_SUBMISSION_REQUIRED = "Violation Report Submission required";
	public static final String CSTASK_SUBJECT_VIOLATION_UPDATES_REQUIRED = "Violation Report updates required";
	public static final String CSTASK_TOPIC_CASESUMMARY_FILING_REQUIRED = "CS.TASK.CASESUMMARY.FILING.REQUIRED";
	public static final String CSTASK_TOPIC_CASESUMMARY_UPDATES_REQUIRED = "CS.TASK.CASESUMMARY.UPDATES.REQUIRED";
	public static final String CSTASK_TOPIC_CASESUMMARY_APPROVAL = "CS.TASK.CASESUMMARY.APPROVAL";
	public static final String CSTASK_SUBJECT_NEW_CASESUMMARY_FOR_APPROVAL  = "New Case Summary for Approval";
	public static final String CSTASK_SUBJECT_CASESUMMARY_FILING_REQUIRED = "Case Summary filing required";
	public static final String CSTASK_SUBJECT_CASESUMMARY_UPDATES_REQUIRED = "Case Summary updates required";
	public static final String CSTASK_SUBJECT_CASESUMMARY_SUBMISSION_FILING = "Case Summary for submission and filing";
	public static final String PAGEFLOW_SCENERIO = "violationReportPageFlow";
	public static final String PAGEFLOW_SCENERIO2 = "caseSummaryPageFlow";
	public static final String TASKTYPE = "taskType";
	public static final String TASKTYPE_APPAROVAL = "Approval";
	public static final String TASKTYPE_SUBMISSION_REVIEW = "SubmissionReview";
	public static final String TASKTYPE_SUBMISSION_APPROVAL = "SubmissionApproval";
	public static final String TASKTYPE_SELF_APPROVAL = "SelfApproval";
	public static final String TASKTYPE_COURT_SUBMISSION = "CourtSubmission";
	public static final String STATUS_COMPLETED = "CD";
	public static final String STATUS_DRAFT = "DR";
	public static final String STATUS_PENDING_SUBMISSION_APPROVAL = "PS";
	public static final String STATUS_SUBMISSION_APPROVED = "SA";
	public static final String STATUS_PENDING_MANAGER_APPROVAL = "PM";
	public static final String STATUS_MANAGER_APPROVED = "MA";
	public static final String STATUS_FILED = "FL";
	public static final String SCENARIO = "scenario";
	public static final String TASK_TEXT = "taskText";
	public static final String TASK_ID = "taskId";
	public static final String SUGGESTED_COURT_ACTION = "SCA";
	public static final String CREATOR_ID = "creatorId";
	public static final String STATUS_PENDING_SUBMISSION_CREATOR = "PSCREATOR";
	public static final String CS_TASK_STANDALONETASK = "CS.TASK.STANDALONETASK";
	
	public static final String VIOLATION_REPORT = "Violation Report";
	public static final String CASE_SUMMARY = "Case Summary";
	public static final String WORKFLOWVALIDATION = "workFlowValidation";
	public static final String VALIDATION_STATUS_ID = "validationStatusId";
}
