/*
 * Created on Apr 29, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package naming;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PDConstants
{

	// Agency Related Constants
	static public final String AGENCY_ID = "agencyId"; 
	static public final String AGENCY_NAME = "agencyName";
	// Transaction Constants
	static public final String CREATE = "CREATE";
	static public final String UPDATE = "UPDATE";
	static public final String DELETE = "DELETE";
 	// Miscellaneous 
	static public final String YES = "Y";
	static public final String NO = "N";
	
	// Restricted Social Security Numbers that have special meanings
	public static String SSN_111111111="111111111";
	public static String SSN_222222222="222222222";
	public static String SSN_333333333="333333333";
	public static String SSN_444444444="444444444";
	public static String SSN_555555555="555555555";
	
	public static String SSN_666666666="666666666";
	public static String SSN_777777777="777777777";
	public static String SSN_888888888="888888888";
	public static String SSN_999999999="999999999";
	
	// Topic Naming Attributes
	static public final String LIST_ITEM = "ListItem";
	
	// ERROR_EVENT Topic
	static public final String ERROR_EVENT_TOPIC = "ErrorEvent";

	//Number formats
	static public final String NUMBER_FORMAT_WITH_TWO_DECIMALS = "###,###.##";
	static public final String NUMBER_FORMAT_WITH_NO_DECIMALS = "##,###,###";
	
	//Date formats
	static public final String DATE_FORMAT_MMDDYY = "MMddyy";
	static public final String DATE_FORMAT_YYMMDD = "yyMMdd";
	static public final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	
	//Miscellaneous
	static public final String NONE = "none";
	static public final String BLANK = "";
	static public final String YEAR = "Y";
	static public final String YEARS = "YS";
	static public final String MONTH = "M";
	static public final String MONTHS = "MS";
	static public final String DAY = "D";
	static public final String DAYS = "DS";
	static public final String HOUR = "H";
	static public final String HOURS = "HS";
	static public final String WEEK = "W";
	static public final String WEEKS = "WS";
	static public final String EIGHT_ZEROES = "00000000";
	static public final String FOUR_ZEROES = "0000";
	static public final String SIX_ZEROES = "000000";
	static public final String TWELVE_ZEROES = "000000000000";
	static public final String OTHER = "other";
	static public final String SIMPLE = "SL";
	static public final String COMPLEX = "CX";
	static public final String COMPOUND = "CD";
	
	//Calendaring Detail Length Constants
	static public final String CAL_DETAIL_SHORT="short";
	static public final String CAL_DETAIL_MEDIUM="medium";
	
	// Logging
	static public final String PROCESS_SCHEDULER = "J2SCHJX";
	
	static public final int SEARCH_LIMIT = 2000;
	public static final String STATUS_NEW = "NEW";
	public static final String STATUS_PENDINGUPDATE = "PUP";
	public static final String STATUS_PENDINGDELETE = "PDE";
	
	//<KISHORE>JIMS200059078 : Calendar: Add new event type Job Visit (PD) - KK
	public static final String JOB_VISIT = "JOB";

	//<KISHORE>JIMS200060664 : Document tab in Casefile view (PD) - KK
	public static final String REPORT_TYPE_CASEPLAN = "CASEPLAN";
	public static final String REPORT_TYPE_CASEPLAN_JPO_REVIEW = "JPO CASEPLAN REVIEW";
	public static final String REPORT_TYPE_SOCIAL_HISTORY = "SOCIAL HISTORY REPORT";
	public static final String REPORT_TYPE_PARENTAL_WRITTEN_STMT = "PARENTAL WRITTEN STATEMENT";
	public static final String REPORT_TYPE_REQUEST_APPOINTED_ATTORNEY = "REQUEST APPOINTED ATTORNEY";
	public static final String REPORT_TYPE_RIGHTS_OF_PARENTS_WORKSHEET = "RIGHTS OF PARENTS WORKSHEET";
	public static final String REPORT_TYPE_SCHOOL_ADJUDICATION_NOTIFICATION = "SCHOOL ADJUDICATION NOTIFICATION";
	public static final String REPORT_TYPE_FACILITY_PARENT_ORIENTATION_LETTER = "FACILITY PARENT ORIENTATION LETTER";//User Story #12253
	public static final String REPORT_TYPE_COURT_APPOINTMENT_LETTER = "COURT_APPOINTMENT LETTER";//User Story #11109
	public static final String REPORT_TYPE_COMMUNITY_EXIT_PLAN = "COMMUNITY EXIT PLAN";
	// ER changes 11069  change court exit plan to common app report
	public static final String REPORT_TYPE_COMMON_APP_REPORT = "COMMON APP REPORT"; 
	public static final String REPORT_TYPE_RESIDENTAIL_EXIT_PLAN = "RESIDENTAIL EXIT PLAN";
	public static final String REPORT_TYPE_NON_COMPLIANCE_NOTICE = "NON COMPLIANCE NOTICE";
	public static final String REPORT_TYPE_COURT_REPORT_INFO_SUMMARY = "CRIS REPORT"; 
	public static final String REPORT_TYPE_REPORT_TO_REFEREE = "REPORT TO REFEREE"; 
	public static final String REPORT_TYPE_MAYSI_ASSESSMENT = "MAYSI ASSESSMENT"; 
	public static final String REPORT_TYPE_JOURNAL_CASE_REVIEW = "CASE REVIEW"; 
	public static final String REPORT_TYPE_FACILITY = "FACILITY ADMIT REPORT"; 
	public static final String REPORT_TYPE_PCS = "PRE-COURT STAFFING REPORT";
	public static final String REPORT_TYPE_EXH = "EXHIBIT REPORT"; 

}
