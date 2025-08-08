/*
 * Created on May 6, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package naming;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PDJuvenileCaseConstants
{
	//Actions
	public static final String OVERRIDECLOSINGEXCEPTIONS = "overrideClosingExeceptions";
	public static final String EXCEPTIONSSUMMARY = "exceptionsSummary";
	public static final String CONTINUECLOSING = "continueClosing";

	// Case status
	public static final String CASESTATUS_PENDING = "P";
	public static final String CASESTATUS_ACTIVE = "A";
	public static final String CASESTATUS_PENDINGCLOSE = "CP";
	public static final String CASESTATUS_CLOSINGAPPROVED = "CA";
	public static final String CASESTATUS_CLOSINGSUBMITTED = "CS";
	public static final String CASE_STATUS_CLOSE = "C";
	public static final String CASESTATUS_ACTIVE_DESCRIPTION = "ACTIVE";
	public static final String CASESTATUS_PENDING_DESCRIPTION = "PENDING";
	public static final String CASESTATUS_CLOSED_DESCRIPTION = "CLOSED";
	public static final String CASESTATUS_CLOSED_APPROVED_DESCRIPTION = "CLOSING APPROVED";
	public static final String NO_CASEFILE = "NO CASEFILE";
	public static final String FAMILY_MEMBER_INVOLVEMENT_LVL_LOW="L";


	// Topics
	public static final String ACTIVE_TASK_EVENT_TOPIC = "ACTIVETASK";
	public static final String SEARCH_RESULTS_COUNT_TOPIC = "SEARCHRESULTSCOUNT";
	public static final String SEARCH_CASEFILE_RESULTS_TOPIC = "SEARCHCASEFILERESULTS";

	public static final String JUVENILE_CASEFILE_TOPIC = "JUVENILECASEFILE";
	public static final String JUVENILE_CASEFILE_REFERRAL_TOPIC = "JUVENILECASEFILE";
	public static final String JUVENILE_OFFENSES_TOPIC = "JUVENILEOFFENSES";
	public static final String MAYSI_RESULTS_LIST_TOPIC = "MAYSIRESULTSLIST";
	public static final String HOSPITALIZATION_RESULTS_LIST_TOPIC = "HOSPITALIZATIONRESULTSLIST";
	public static final String MAYSI_DETAILS_RESULT_TOPIC = "MAYSIDETAILRESULT";
	public static final String NO_MAYSI_RECORD_TOPIC = "NOMAYSIRECORD";
	public static final String JUVENILE_CONTACT_TOPIC = "JUVENILECONTACTTOPIC";
	public static final String JUVENILE_DRUG_TOPIC = "JUVENILEDRUGTOPIC";
	//ER:GANG-JIMS200074578 STARTS
	public static final String JUVENILE_GANG_TOPIC = "JUVENILEGANGTOPIC";
	//ER:GANG-JIMS200074578 ENDS
	public static final String JUVENILE_PHYSICAL_CHARACTERISTICS_TOPIC = "JUVENILEPHYSICALCHARACTERISTICTOPIC";
	public static final String JUVENILE_DISPOSITION_TOPIC = "JUVENILEDISPOSITIONTOPIC";
	public static final String JUVENILE_PETITION_DISPOSITION_TOPIC = "JUVENILEPETITIONDISPOSITIONTOPIC";
	public static final String JUVENILE_JOB_TOPIC = "JUVENILEJOBTOPIC";
	public static final String DUPLICATE_SUPERVISION_TOPIC = "duplicateSupervision";
	public static String NO_CASEFILE_EXCEPTION_TOPIC = "NOCASEFILEEXCEPTIONTOPIC";
	public static String JUVENILE_COURT_DISPOSITION_TOPIC = "JUVENILECOURTDISPOSITIONTOPIC";
	//US 161877
	public static final String VOP_DETAILS_REQUIRED ="VOPREQUIRED";
	
	public static final String NEW_MAYSI_ASSESSMENT_OPTION = "A";
	public static final String SUBSEQUENT_NEEDED_ASSESSMENT_OPTION = "B";
	public static final String SUBSEQUENT_NOT_NEEDED_ASSESSMENT_OPTION = "C";
	public static final String SUBSEQUENT_DONE_ASSESSMENT_OPTION = "D";
	public static final String TEST_NOT_ADMINISTERED_OPTION = "T";

	// Juvenile Casefile Search Types
	public static final String SEARCH_JUVENILE_NUMBER = "JNUM";
	public static final String SEARCH_JUVENILE_NAME = "JNAM";
	public static final String SEARCH_PROBATION_OFFICER = "PBON";
	public static final String SEARCH_SUPERVISION_TYPE = "SPVT";
	public static final String SEARCH_CASE_STATUS = "CSST";
	public static final String SEARCH_SUPERVISION_NUMBER = "SNUM";
	public static final String SEARCH_CASE_LOAD = "CSLD";
	public static final String SEARCH_DATE_OF_BIRTH = "JDOB";
	
	// Juvenile Profile Search Types
	public static final String SEARCH_JUVENILE_DOB = "JDOB";
	//task 171828
	public static final String SEARCH_JUVENILE_DALOG = "JDALG";
	
	//Juvenile profile/facility search types
	public static final String SEARCH_JUVENILE_SSN = "JSSN";
	
	// Constant for the parameter name on the casefile.
	public static final String CASEFILEID_PARAM = "casefileId";
	public static final String SUPERVISIONNUM_PARAM = "supervisionNum";
	public static final String PROGREFERRALSTATUS_PARAM = "programrefStatus";
	public static final String JUVENILENUM_PARAM = "juvnum";
	public static final String REFERRALNUM_PARAM = "refnum";
	public static final String PETITIONNUM_PARAM = "petnum";
	
	public static final String CPS_INVOLVEMENT_NO = "0";
	public static final String CPS_INVOLVEMENT_YES = "1";
	public static final String PARENTAL_RIGHTS_NO = "0";
	public static final String PARENTAL_RIGHTS_YES = "1";
	public static final String CPS_CUSTODY_NO = "0";
	public static final String CPS_CUSTODY_YES = "1";

	// HARD CODED - DB RELATED - Constants for the trait types and category trait tables
	//****************************
	public static final String PARENT_TRAIT_IDENTIFIER_VALUE = "0";  // hard coded value corresponding to JCTRAITTYPE that matches all TRAIT PARENTS PARENT_ID
	//public static final String GANG = "44"; NO LONGER USED as of 03/31/2006
	public static final String TRAIT_CATEGORY_NAME_GANGS = "GANGS";
	//	public static final String DRUGS = "16"; NO LONGER USED as of 03/31/2006
	public static final String TRAIT_CATEGORY_NAME_DRUGS = "DRUGS";
	public static final String TRAIT_CATEGORY_NAME_MEDICAL_ISSUES = "MEDICAL_ISSUES";
	//	public static final String SPECIAL_INTERESTS = "17"; NO LONGER USED as of 03/31/2006
	public static final String TRAIT_CATEGORY_NAME_SPECIAL_INTERESTS = "SPECIAL_INTERESTS";
	//	public static final String SCHOOL = "62"; -- NO LONGER USED as of 03/31/2006
	public static final String TRAIT_CATEGORY_NAME_SCHOOL = "SCHOOL";
	public static final String TRAIT_CATEGORY_NAME_ABUSE = "ABUSE";
	public static final String TRAIT_CATEGORY_NAME_DUALSTATUS = "DUALSTATUS";
	public static final String TRAIT_CATEGORY_STRENGTHS = "STRENGTHS";
	public static final String TRAIT_CATEGORY_SUBSTANCE_ABUSE_ISSUES = "SUBSTANCE ABUSE ISSUES";
	public static final String TRAIT_CATEGORY_NAME_SCHOOL_ATTENDANCE = "SCHOOL_ATTENDANCE";
	public static final String TRAIT_CATEGORY_NAME_SCHOOL_BEHAVIOR = "SCHOOL_BEHAVIOR";
	public static final String TRAIT_CATEGORY_NAME_EDUCATIONAL_PERFORMANCE = "EDUCATIONAL_PERFORMANCE";
	public static final String TRAIT_CATEGORY_NAME_SUPERVISION_LEVEL="SUPERVISION_LEVEL";
	public static final String TRAIT_CATEGORY_NAME_ADMINISTRATIVE="ADMINISTRATIVE";
	//For facility
	public static final String TRAIT_CATEGORY_NAME_FACILITY_TRAITS = "FACILITY";
	
	//	****************************
	
	public static final String MEDICAL_TAB = "medical";
	public static final String GANG_TAB = "gang";
	public static final String DRUG_TAB = "drug";
	public static final String FAMILY_TAB = "family";
	public static final String SCHOOL_TAB = "school";
	public static final String SPECIAL_INTEREST_TAB = "specialInterest";
	public static final String ABUSE_TAB = "abuse";
	public static final String MENTAL_HEALTH_TAB = "mentalhealth";
	public static final String JOB_TAB = "job";
	public static final String BENEFIT_TAB = "benefits";
	
	public static final String GANG_KEY = "prompt.gang";
	public static final String ABUSE_KEY = "prompt.abuse";
	public static final String SCHOOL_KEY = "prompt.school";
	public static final String SPECIAL_INTEREST_KEY = "prompt.specialInterest";
	
	// casefile closing details
	public static final String CASEFILE_CLOSING_EVENT_TOPIC = "casefileClosing";
	public static final String CASEFILE_CLOSING_ANSWER_LIST_EVENT_TOPIC = "casefileClosingAnswerList";
	public static final String QUESTION_ANSWER_EVENT_TOPIC = "questionAnswers";
	public static final String DB2ADMIN = "DB2ADMIN";
	public static String SUPERVISION_NUMBER = "supervisionNumber";
	public static String CASE_STATUS_ID = "caseStatusId";
	
	//supervision rules
	public static final String CONDITION_DETAIL_TOPIC = "CONDITIONDETAIL";
	public static final String RULE_LIST_TOPIC = "RULELIST";	
	public static final String RULE_DETAIL_TOPIC = "RULEDETAIL";
	public final static String RULES_DEFAULT_COURT = "JUV0313";	// was "CR 0176"
	
	// PDCasefile Question Answer constants
	public static final String SELECTED_RESPONSE_ID = "selectedResponseId";
	public static final String QUESTION_ID = "questionId";
	public static final String IS_DEFAULT = "isDefault";
	public static final String DISPLAY_TEXT = "displayText";
	public static final String ID = "id";
	public static final String RESPONSE_VALUE = "responseValue";
	public static final String TEXT = "text";
	public static final String RESPONSE_UICONTROL_TYPE = "responseUIControlType";
	public static final String IS_REQUIRED = "isRequired";
	public static final String IS_RESPONSE_NEW_LINE = "isResponseNewLine";
	public static final String IS_RENDER_QUES_NUM = "isRenderQuesNum";
	public static final String UICONTROL_SIZE = "uiControlSize";
	public static final String ROW_SEQUENCE = "rowSequence";
	public static final String COLUMN_SEQUENCE = "columnSequence";
	public static final String RESPONSE_DATA_TYPE = "responseDataType";
	public static final String FORMAT_KEY = "formatKey";
	public static final String VALIDATION_DESCRIPTION = "validationDescription";
	public static final String SEQUENCE = "sequence";
	public static final String RESPONSE_DEPENDENCY = "responseDependancy";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String TEXT_LENGTH="textLength";
	
//	 CAsework RULE STATUS CODE TABLE CONSTANTS
	public static final String RULE_STATUS_INACTIVE="I";
	public static final String RULE_STATUS_COMPLETE="S";
	public static final String RULE_STATUS_NON_COMPLIANT="N";
	public static final String RULE_STATUS_ACTIVE="A";
	public static final String RULE_STATUS_COMPLIANT="C";
	
	//PDCasefile closing exception types
	public static final String MAYSI_NOT_DONE = "MAYSI";
	public static final String MAYSI_DATE_PRIOR_ACTIVATION_DATE ="MAYSIPRIORACTIVATION";
	public static final String BENEFITS_NOT_DONE = "BENEFITS";
	public static final String RISK_NOT_DONE = "RISK";
	public static final String TJJD_NOT_COMPLETED = "TJJDRISK";
	public static final String RULES_NOT_DONE = "RULES";		
	public static final String GOALS_NOT_DONE = "GOALS";
	public static final String PROG_REFERRALS_NOT_DONE = "PROGRAMREFERRALS";
	public static final String GUARDIAN_IN_HOME_STATUS = "GUARDIANINHOMESTATUS";
	public static final String GUARDIAN_IN_HOME_ADDRESS_NOT_FOUND = "GUARDIANADDRESS";
	public static final String GUARDIANS_IN_HOME_ADDRESS_NOT_FOUND = "GUARDIANSADDRESS";
	public static final String NON_COMPLIANCE_COMPLETION_NO_STATUS_FOUND = "NONCOMPLIANCECOMPLETIONNOSTATUS";
	public static final String CASEPLAN_STATUS_NOT_ACKNOWLEDGED = "CASEPLANSTATUSNOTACKNOWLEDGED";
	public static final String PACT_NOT_DONE="PACT";
	public static final String JUVENILE_IN_FACILITY="JUVENILEINFACILITY";
	public static final String JUVENILE_REFFERAL_NOT_ASSOCIATED="JUVENILEREFFERALNOTASSOCIATED";
	public static final String JUVENILE_REFFERAL_ASSOCIATED="JUVENILEREFFERALASSOCIATED";
	public static final String PROGRAM_REFERRAL_END_DATE_AFTER = "PROGRAMREFERRALENDDATE";
	
	
	public static final String CASEFILE_EXTRACTION_SUCCESS_STATUS = "Y";
	public static final String CASEFILE_EXTRACTION_SKIPPED_STATUS = "S";
	public static final String CASEFILE_EXTRACTION_ERROR_STATUS = "E";
	/*public static final String CASEFILE_CREATOR = "CFEXTR";*/ // bug #89637
	public static final String JIMS2_CASEFILE_CREATOR = "JUVCFASMNTPROCESS@HCTX.NET";
	public static final String FAILURE_CASEFILE_NOTIFICATION = "MJCW.CASEFILE.GENERATIONFAILURE";
	public static final String MJCW_JPO_CONDUCTMAYSI = "MJCW.JPO.CONDUCTMAYSI";
	public static final String MJCW_JPO_NEWCASEFILEGENERATION = "MJCW.JPO.NEWCASEFILEGENERATION";
	public static final String MJCW_JPO_UPDATECASEFILE = "MJCW.JPO.UPDATECASEFILE";
	public static final String MJCW_JPO_MISSINGCONSTELLATION = "MJCW.JPO.MISSINGCONSTELLATION";
	public static final String MJCW_JPO_SUBSEQUENT_REFERRAL = "MJCW.JPO.SUBSEQUENT.REFERRAL";
	
	//Assigened Referrals constants
	
	public static final String JUVENILE_FEE_PAYOR_TYPE_A = "Attorney";
	public static final String JUVENILE_FEE_PAYOR_TYPE_O = "Other";
	public static final String JUVENILE_FEE_STATUS_P = "Paid in Full";
	public static final String JUVENILE_FEE_STATUS_D = "Delinquent";
	public static final String JUVENILE_FEE_STATUS_S = "Subsequent";
	public static final String JUVENILE_FEE_STATUS_W = "Waived";
	public static final String JUVENILE_LEVEL_CARE_B = "B";
	public static final String JUVENILE_LEVEL_CARE_M = "M";
	public static final String JUVENILE_LEVEL_CARE_S = "S";
	public static final String JUVENILE_LEVEL_CARE_I = "I";
	public static final String JUVENILE_REFERRAL = "REFERRAL";
	public static final String JUVENILE_FACILITY = "FACILITY";
	public static final String JUVENILE_CASEFILE = "CASEFILE";
	public static final String JUVENILE_PROFILE = "PROFILE";
	public static final String JUVENILE_JOT = "JOT";
	public static final String JUVENILE_TRANFERRED = "TRANSFERRED";
	public static final String VIOLATION_OF_PROBATION = "VOP";
	
	//Casefile Journal constants
	public static final String JOURNAL_CATEGORY_ACTIVITIES = "AC";
	public static final String JOURNAL_CATEGORY_CALENDAREVENTS = "CE";
	public static final String JOURNAL_CATEGORY_CLOSINGINFO = "CI";
	public static final String JOURNAL_CATEGORY_GOALS = "GL";
	public static final String JOURNAL_CATEGORY_PROGREF = "PR";
	public static final String JOURNAL_CATEGORY_RISKANAL = "RA";
	public static final String JOURNAL_CATEGORY_TRAITS = "TR";
	public static final String JOURNAL_CASE_REVIEW_SUMMARY = "CR";
	
	//jsp
	public static final String JUVENILE_HISTORY = "JUVENILE_HISTORY";
	public static final String JUVENILE_HISTORY_PAGE = "PAGE";
	public static final String FACILLITY_HISTORY_REFERRAL_ID_LIST = "FACILLITYHISTORYREFERRALIDLIST";
	
	//<KISHORE>JIMS200060775 : Add Social Hist. link to Program Ref Detail(PD)-KK
	public static final String SOCIAL_HISTORY_REPORT_TYPE = "SHR";
		
}	
