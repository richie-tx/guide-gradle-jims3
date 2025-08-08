/*
 * Created on Apr 2, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package naming;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSAdministerProgramReferralsConstants 
{
    /************* Program Referral Statuses ********************/
    public static final String INITIATED_REFERRAL_STATUS = "I";
    public static final String OPEN_REFERRAL_STATUS = "O";
    public static final String EXITED_REFERRAL_STATUS = "E";
    public static final String PROGRAM_GROUP_CODE_TABLE = "CS_PROGRAM_GROUP";
    public static final String PROGRAM_TYPE_CODE_TABLE = "CS_PROGRAM_TYPE";
    
    
    public static final String PROGRAM_ISSUES = "Program Issues" ;
    
    public static final String WORKGROUP_OID_PREPEND_STRING = "CSWORKGROUP_" ; 
    
    public static final String SERVICE_PROVIDER_COMMITTE_WORKGROUP_NAME = "SERVICE PROVIDER COMMITTE";
    
    public static final String CSTASK_TOPIC_PROGRAM_REPORT_ISSUES = "CS.PROGRAMREFERRAL.PROGRAM.REPORT.ISSUES";
    
    public static final String TASK_TEXT = "taskText";
    
    public static final String USER_ENTERED_SP = "USER_ENTERED";
    
    public static final String CASENOTE_CONTEXT_SUPERVISEE = "SUPERVISEE";
    public static final String CASENOTE_CONTEXT_PROGRAM_REFERAL = "REFERRAL";
    
    public final static String ACTION_CREATE="create";
    public final static String ACTION_VIEW="view";
    public final static String ACTION_DELETE="delete";
    
    public final static String ACTION_UPDATE_INIT="updateInit";
    public final static String ACTION_UPDATE_EXIT="updateExit";
	public final static String ACTION_UPDATE_SUBMIT="updateSubmit";
	
	public final static String ACTION_SUBMITREFERRAL="submitReferral";
	public final static String ACTION_EXITREFERRAL="exitReferral";
	public final static String ACTION_REREFERRAL="reReferral";
	
	public final static String ACTION_REMOVEENTRY="removeEntry";
	public final static String ACTION_REMOVEEXIT="removeExit";
	
	public final static String ACTION_GENERATE_FORM="generateForm";
	
	public final static String ACTION_CREATE_CASENOTE="createCasenote";
	public final static String ACTION_VIEW_CASENOTE="viewCasenote";
	
	public final static String STATUS_EXITED="exited";
	public final static String STATUS_INITIATED="initiated";
	public final static String STATUS_OPEN="open";
	
	public final static String SUBMIT_REFERRAL_SUCCESS = "submitRefSuccess";
	public final static String EXIT_REFERRAL_SUCCESS = "exitRefSuccess";
	public final static String REMOVE_ENTRY_SUCCESS = "removeEntrySuccess";
	public final static String REMOVE_EXIT_SUCCESS = "removeExitSuccess";
	public static final String SCHEDULE_SUCCESS = "scheduleSuccess";
	public static final String UPDATE_INIT_SUCCESS = "updateInitSuccess";
	public static final String UPDATE_SUBMIT_SUCCESS = "updateSubmitSuccess";
	public static final String UPDATE_EXIT_SUCCESS = "updateExitSuccess";
	public static final String RE_REFERRAL_SUCCESS = "reRefSuccess";
	public static final String PRINT_APPTMNT_SUCCESS = "printApptSuccess";
	public static final String GENERATE_FORM_SUCCESS = "generateFormSuccess";
	public static final String REFERRAL_SUCCESS = "referralSuccess";
	public static final String SELECT_SP_SUCCESS = "selectSPSuccess";
	public static final String CREATE_CASENOTE_SUCCESS = "createCasenoteSuccess";
	
	public static final String UI_CNTRL_TYPE_MCE_EDIT_TEXTBOX="MCETEXTBOX";
	public static final String UI_CNTRL_TYPE_TEXTBOX="TEXTBOX";
	public static final String UI_CNTRL_TYPE_TEXT="TEXT";
	public static final String UI_CNTRL_TYPE_RADIO="RADIO";
	public static final String UI_CNTRL_TYPE_SELECT="SELECT";
	public static final String UI_CNTRL_TYPE_HIDDEN="HIDDEN";
	public static final String UI_CNTRL_TYPE_SINGLE_CHECKBOX="SINGLECHECKBOX";
	public static final String UI_CNTRL_TYPE_CHECKBOX="CHECKBOX";
	
	public static final String UI_SINGLE_CHECKBOX_CHK_VAL = "1";
	
	public static final String REFERRAL_FORM_COMMENT_LABEL = "Reason for Referral"; 
	public static final String SEX_OFFENDER_REF_FORM_COMMENT_LABEL = "Sex Offender Referral Reason Comments";
	
	public static final String NOT_STATE_REPORTING_CODE = "LOC";
	
	public static final String GROUPNAME_SHOCK = "SHOCK";
	
	public static final String VARELEMTYPE_NAME_HCJ_NUMBER = "HCJNumber";
	public static final String VARELEMTYPE_NAME_HCJ_TIMEPERIODCS = "HCJTimePeriodCS";
	public static final String VARELEMTYPE_NAME_HCJ_WEEKENDNUMBER = "HCJWeekendNumber";
	public static final String VARELEMTYPE_NAME_HCJ_WEEKEND_TIMEPERIOD = "HCJWeekendTimePeriod";
	
	public static final String HCJTIMEPERIOD_DAY = "D";
	public static final String HCJTIMEPERIOD_DAYS = "DS";
	
	public static final String HCJTIMEPERIOD_MONTH = "M";
	public static final String HCJTIMEPERIOD_MONTHS = "MS";
	
	public static final String HCJTIMEPERIOD_YEAR = "Y";
	public static final String HCJTIMEPERIOD_YEARS = "YS";
	
	public static final String SERVICE_PROVIDER_SEARCH="SP";
	public static final String PROGRAM_SEARCH="PRGM";
	public static final String PROGRAM_LOCATION_SEARCH="LOC";
	
	public static final String REFRRAL_CASELOAD_SUCCESS = "referralCaseloadSuccess";
	public static final String SP_SEARCH_SUCCESS = "spSearchSuccess";
	public static final String PGM_SEARCH_SUCCESS = "pgmSearchSuccess";
	public static final String SP_VIEW_PROGRAM_SUCCESS = "spPgmViewSuccess";
	public static final String SP_VIEW_LOCATION_SUCCESS = "spPgmLocViewSuccess";
	public static final String VIEW_PROGRAM_REFERRAL_SUCCESS = "prgRefViewSuccess";
	public static final String VIEW_PRG_REF_DETAILS_SUCCESS = "prgRefDetailsViewSuccess";
	public static final String VIEW_ORDER_VERSIONS_SUCCESS = "orderVersionsViewSuccess";
	
	public static final String ACTION_VIEW_SP_PRGREF_CASELD = "spProgRefCaseload";
	public static final String ACTION_VIEW_PGM_PRGREF_CASELD = "pgmProgRefCaseload";
	public static final String ACTION_VIEW_PGM_NAME_PRGREF_CASELD = "pgmNameProgRefCaseload";
	public static final String ACTION_VIEW_LOC_PRGREF_CASELD = "locProgRefCaseload";
	
}
