package naming;

public class CloseCaseConstants
{
//	OOC Case - actions
	public static final String OOC_CASE_CLOSE = "close";	
	public static final String OOC_CASE_UPDATE_CLOSURE = "updateClosure";
	
//	OOC Case - Action Forwards
	public static final String REVIEW_ACTIVE_CASE_SUCCESS = "reviewActiveCaseSuccess";
	public static final String CLOSE_CASE_SUCCESS = "closeCaseSuccess";
	public static final String CLOSE_CASE_SUMMARY_SUCCESS = "closeCaseSummarySuccess";
	public static final String CLOSE_CASE_CONFIRM_SUCCESS = "closeCaseConfirmSuccess";
	public static final String CASELOAD_SEARCH = "caseloadSearch";
	
	public static final String CS_CALENDAR_FIELD_VISIT_EVENTTYPE = "FV"; 
	
//	program referral casenote subject code
	public static final String PROGRAM_REFERRAL_SUBJECT_CD = "PR";
	
	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failure";
	
//	Failure Reasons
	public static final String OOC_CASE_CLOSE_FAILURE = "Failed to close Out Of County Case";
	public static final String CASEASSIGNMENT_FAILURE = "Failed to terminate Case Assignment";
	public static final String CASE_CLOSURE_CASENOTES_FAILURE = "Failed to record Casenotes on Case Closure";
	public static final String SUPERVISION_ORDER_FAILURE = "Failed to inactivate Supervision Order";
	
	public static final String PROGRAM_REFERRAL_FAILURE = "Failed to Exit Program Referrals";
	public static final String CALENDAR_FAILURE = "Failed to delete Calendar Events";
	public static final String SUPERVISION_PERIOD_FAILURE = "Failed to end Supervision Period";
	public static final String SUPERVISEE_FAILURE = "Failed to update Supervisee details";
		
	public static final String OUT_OF_COUNTY = "010";
	public static final String YYYYMMDD = "yyyyMMdd";
}
