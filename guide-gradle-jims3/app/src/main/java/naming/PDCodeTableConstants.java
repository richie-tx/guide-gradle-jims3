/*
 * Created on Aug 5, 2004
 *
 */
package naming;

/**
 * @author mlawles
 * 
 */
public final class PDCodeTableConstants
{
    public static final String CODE_ID_SEPARATOR = "##";
    public static final String CODE_ID_URL_SEPERATOR = "##";
    public static final String CODE_ID_NEW_INDICATOR = "~";

    // ADDRESS Type constants
    public static final String ADDRESS_TYPE_MAILING = "MAL";
    public static final String ADDRESS_TYPE_BILLING = "BUS";

    // Status constants
    public static final String INACTIVE = "Inactive";
    public static final String ACTIVE = "Active";
    public static final String UNUSED = "Unused";
    public static final String PENDINGACTIVE = "Pending Active";
    public static final String PENDINGINACTIVE = "Pending Inactive";
    public static final String INACT = "INACTIVE";

    // Topic constants
    static public final String CODE_TABLE = "CodeTable";
    static public final String CODE_TABLE_NAME = "codetableName";
    static public final String CODE_TABLE_TYPE = "CodeTableType";
    static public final String CODE_TABLE_SUBTYPE = "CodeTableSubtype";
    static public final String CODE = "Code";
    static public final String SOCIALELEMENTCODE = "SocialelementCode";
    static public final String CODE_HISTORY = "CodeHistory";
    public static final String OFFICER_SUBTYPE = "OFFICER_SUBTYPE";
    public static final String OFFICER_STATUS = "OFFICER_STATUS";
    public static final String RESPONSE_CONTEXT_LOCATOR = "RESPONSE_CONTEXT_LOCATOR";
    public static final String SECURITY_INQUIRE_BY = "SECURITY_INQUIRE_BY";
    public static final String JMCREP = "MANAGEMENT_COMMITTEE_REP";

    // Transaction constants
    public static final String TRANS_TYPE_CHANGE = "C";
    public static final String TRANS_TYPE_ADD = "A";
    public static final String TRANS_TYPE_DELETE = "D";

    // JUVENILE DISTRICT COURT CONSTANTS
    public static final String PETITION_STATUS = "PETITION_STATUS";
    public static final String SUBPOENAS_TO_BE_PRINTED = "SUBPOENAS_TO_BE_PRINTED";
    public static final String AMENDMENT_NUMBER = "AMENDMENT_NUMBER";
    public static final String PETITION_TYPE = "PETITION_TYPE";
    public static final String TYPE_CASE = "TYPE_CASE";
    
    
    /**
     * Generates a Topic for a particular code table with the correct naming
     * convention. Used by both UI and PD for consistancy.
     * 
     * @param codeTableName
     * @return codeTableName
     */
    public static String getCodeTableTopic(String codeTableName)
    {
	return codeTableName + "." + PDCodeTableConstants.CODE + "." + PDConstants.LIST_ITEM;
    }

    public static String getSocialElementCodeTableTopic(String codeTableName)
    {
	return codeTableName + "." + PDCodeTableConstants.SOCIALELEMENTCODE + "." + PDConstants.LIST_ITEM;
    }

	// Code Tables	
	public static final String ADDRESS_TYPE = "ADDRESS.TYPE";
	public static final String COMPLEXION = "COMPLEXION";
	public static final String COMPLIANCE_REASON = "COMPLIANCE_REASON";
	public static final String CAPACITY = "CAPACITY";
	public static final String CAUTIONS = "SPN.CAUTION.CODE";
	public static final String COURT_DIVISION="COURT_DIVISION";
	public static final String COUNTY = "COUNTY.CODE";
	public static final String JUVENILE_COUNTY = "COUNTY";
	public static final String CSCD_RELATIONSHIP = "CSCD_RELATIONSHIP";
	public static final String DESCRIPTION_SOURCE = "DESCRIPTION_SOURCE";
	public static final String DRIVERS_LICENSE_CLASS = "DRIVERS_LICENSE_CLASS";
	public static final String DRIVERS_LICENSE_TYPE = "DRIVERS_LICENSE_TYPE";
	public static final String EMAIL_TYPE = "EMAIL_TYPE";
	public static final String EMPLOYMENT_STATUS = "EMPLOYMENT.STATUS";
	public static final String ETHNICITY = "ETHNICITY";
	public static final String JUVENILE_ETHNICITY = "JUV_ETHNICITY";
	public static final String EXIT_TYPE = "JUVENILE_SCHOOL_EXIT_TYPE";
	public static final String EYE_COLOR = "EYE.COLOR";
	public static final String HAIR_COLOR = "HAIR.COLOR";
	public static final String JIMS2_SUBSYSTEMS = "JIMS2_SUBSYSTEMS";
	public static final String LANGUAGE = "LANGUAGES";
	public static final String MARITAL_STATUS = "MARITAL.STATUS";
	public static final String NAME_SOURCE = "NAME_SOURCE";
	public static final String OFFICER_RANK = "RANK";
	public static final String PLACE_OF_BIRTH = "PLACE.OF.BIRTH";
	public static final String SKIN_TONE = "SKIN.COLOR";
	public static final String STREET_TYPE = "STREET.TYPE";
	public static final String STREET_SUFFIX = "STREET_SUFFIX";
	public static final String REASON_FOR_UPDATE = "REASON_FOR_UPDATE";
	public static final String RELATIONSHIP_JUVENILE = "JUVENILE_RELATIONSHIP";
	public static final String RELATIONSHIP_JUVENILE_REFERRAL = "JUVENILE_REFERRAL_RELATIONSHIP";
	public static final String RELATIONSHIP_JUVENILE_AFDC = "JUVENILE_RELATIONSHIP_AFDC";
	public static final String RELATIONSHIP_TO_PARTY = "RELATIONSHIP_TO_PARTY";
	public static final String RELIGION = "RELIGION";
	public static final String ROLE_TYPE = "ROLE_TYPE";
	public static final String SEX = "SEX";
	public static final String JJS_SEX = "SEX";
	public static final String SERVICE_STATUS = "SERVICE_STATUS";
	public static final String LOCATION_STATUS = "LOCATION_STATUS";
	public static final String LOCATION_REGION = "LOCATION_REGION";
	public static final String WARRANT_STATUS = "WARRANT_STATUS";
	public static final String OFFICER_ID_TYPE = "OFFICER_ID_TYPE";
	public static final String OFFENSE_LISTITEM = "OFFENSE_LISTITEM";
	public static final String TRANSFER_LOCATION = "TRANSFER_LOCATION";
	public static final String JUVENILE_CASEFILE_SEARCH = "JUV_CASE_FILE_SEARCH";
	public static final String JUVENILE_CASEFILE_CASE_STATUS = "JUV_CASE_STATUS";
	public static final String JUVENILE_CASEFILE_SUPERVISION_TYPE = "SUPERVISION_TYPE";
	public static final String JUVENILE_CASEFILE_SUPERVISION_CATEGORY = "SUPERVISION_CATEGORY";
	public static final String MAYSI_PLACEMENT_TYPES = "PLACEMENT_TYPE";
	public static final String MAYSI_LOCATION = "LOCATION";
	public static final String MAYSI_LENGTH_OF_STAY = "LENGTH_OF_STAY";
	public static final String MAYSI_REASON_NOT_DONE = "REASON_NOT_DONE" ;
	public static final String MAYSI_PROVIDER_TYPE_REFERRED = "MAYSI_PROVIDER_TYPE_REFERRED";
	public static final String CITY = "CITY";
	public static final String JUVENILE_PROFILE_SEARCH = "JUV_PROFILE_SEARCH";
	public static final String JUVENILE_PROFILE_STATUS = "JUV_PROFILE_STATUS";
	public static final String WORK_DAY = "WORK_DAY";
	public static final String DISPOSITION = "DISPOSITION";
	static public final String CONNECTION_CODE = "CONNECTION.CODE";
	public static final String ISUSCITIZEN = "IS_US_CITIZEN";
	public static final String FACILITY_TYPE = "FACILITY_TYPE";
	public static final String SUPERVISION_DISPOSITION_SUBSET="SUPERVISION_DISPOSITION";
	public static final String SERVEVENT_STATUS="SERVICE_EVENT_STATUS";
	public static final String SERVEVENT_ATTENDANCE_STATUS="SERVEVENT_ATTENDANCE_STATUS";
	public static final String SESSION_LENGTH_INTERVAL="SESSION_LENGTH_INTERVAL";
	public static final String RISK_MANDATORY_DETENTION = "RISK_MANDATORY_DETENTION";
	public static final String OVERRIDDENREASON = "OVERRIDDEN_REASON";
	public static final String DESCRIPTORSOURCE = "DESCRIPTOR_SOURCE";
	public static final String PLEA = "PLEA";
	public static final String TJPC_DSMIV_DIAGNOSIS = "TJPC_DSMIV_DIAGNOSIS";
	public static final String JUV_RISK_UI_CONTROL_TYPE = "JUV_RISK_UI_CONTROL_TYPE";
	public static final String JUV_RISK_ASSESSMENT_TYPE = "JUV_RISK_ASSESSMENT_TYPE";
	public static final String REASON_FOR_REFERRAL = "REASON_FOR_REFERRAL";
	public static final String JUVENILE_BENEFIT_STATUS = "JUVENILE_BENEFIT_STATUS";
	public static final String JUVENILE_JIS_AGENCY = "JUVENILEJISAGENCY";
	public static final String INFORMATION_SOURCE = "INFORMATION_SOURCE";
	public static final String REFERRAL_PIA_CASE_TYPE = "PIA_CASE_TYPE";
	//ER GANG_JIMS200074578 STARTS
	public static final String GANG_NAME = "GANG_NAME";
	public static final String GANG_CLIQUE = "GANG_CLIQUE";
	public static final String GANG_STATUS = "GANG_STATUS";
	public static final String GANG_ASSOCIATION_TYPE = "GANG_ASSOCIATION_TYPE";
	//ER GANG_JIMS200074578 ENDS

    // GANG ASSESSMENT STARTS
    public static final String GANG_ASSMNT_REASONFORREFERRAL = "GANG_ASSMNT_REASONFORREFERRAL";
    public static final String GANG_ASSESSMENT_LEVELOFGANGINVOLVEMENT = "GANG_ASSMNT_LVLOFGNGINVMNT";
    public static final String RECOMMENDATIONS = "RECOMMENDATIONS";
    public static final String ASSESSMENT_REFERRAL_TYPE = "ASSESSMENT_REFERRAL_TYPE";
    public static final String JUVPROF_DOCUMENT_TYPE = "JUVPROF_DOCUMENT_TYPE";
    // GANG ASSESSMENT ENDS

    public static final String CLIQUE_SET_NAME = "CLIQUE_SET_NAME";
    public static final String FACILITY_POPULATION_REPORT = "FACILITY_POPULATION_REPORT";

    // FACILITY CODE TABLES
    public static final String SPECIAL_ATTENTION = "SPECIAL_ATTENTION";
    public static final String SPECIAL_ATTENTION_REASON = "SPECIAL_ATTENTION_REASON";
    public static final String FACILITY_STATUS = "FACILITY_STATUS";
    public static final String TEMP_RELEASE_REASON = "TEMPORARY_RELEASE_REASON";
    public static final String MULTIPLE_OCCUPANCY_UNIT = "MULTIPLE_OCCUPANCY_UNIT";
    public static final String RETURN_REASON = "RETURN_REASON";
    public static final String FACILITY_OUTCOME = "FACILITY_OUTCOME";
    public static final String RELEASE_REASON = "RELEASE_REASON";

    // Service
    public static final String SERVICES_DELIVERED = "SERVICES.DELIVERED";
    // M204 Code Tables
    public static final String AGENCY_ACCESS_TYPE = "AGENCY.ACCESS.TYPE";
    public static final String AGENCY_STATUS = "AGENCY.STATUS";
    public static final String AGENCY_TYPE = "AGENCY_TYPE";
    public static final String BUILD = "BUILD";
    public static final String JUVENILE_COURT = "JUVENILE_COURT";
    public static final String JUVENILE_FACILITY = "JUVENILE_FACILITY";
    public static final String RACE = "JJS.RACE.CODE";
    public static final String RACE_ALT = "JJS.RACE.CODE.ALT";
    public static final String JJSRACE = "JJS_RACE_CODE";
    public static final String RECALL_REASON = "RECALL_REASON";
    public static final String SCARS_MARKS = "SCAR_MARKS";
    public static final String SETCIC_ACCESS = "SETCIC_ACCESS";
    public static final String STATE_ABBR = "STATE.ABBR";
    public static final String TATTOOS = "TATTOO";
    public static final String PERSONNEL = "PERSONNEL";
    //public static final String RELEASED_FROM_DETENTION = "pd.codetable.criminal.JuvenileReleasedFromDetention";
    public static final String RELEASED_BY_AUTHORITY = "RELEASED.BY.AUTHORITY";
    //public static final String RELEASED_TO = "RELEASED.TO.DETENTION";
    public static final String COURT_DECISION = "COURT_DECISION"; // Added for U.S #11645
    public static final String JUVENILE_COUNTY_OFFICIALS="JUVENILE_COUNTY_OFFICIALS";
    public static final String DISTRICT_CLERK_NAME = "DISTRICT_CLERK_NAME";

    // added for user story 11026
    public static final String DETENTION_OUTCOME = "DETENTION.OUTCOME";

    // Party Code Tables
    public static final String CITIZENSHIP = "CITIZENSHIP";
    public static final String MISCELLANEOUS_NUMBER_TYPE = "MISCELLANEOUS_ID_NUMBER_TYPE";
    public static final String ALIAS_NAME_TYPE = "pd.codetable.person.AliasNameTypeCode";
    //public static final String JUVENILE_COUNTRY = "PLACE_OF_BIRTH_COUNTRY";
    public static final String STATE_AGENCY = "AGENCY";

    // Juvenile Warrant
    public static final String CHARGE = "CHARGE";
    public static final String COURT = "COURT";
    public static final String SCHOOL_DISTRICT = "SCHOOL_DISTRICT";
    public static final String WARRANT_ACKNOWLEDGE_STATUS = "WARRANT_ACKNOWLEDGE_STATUS";
    public static final String WARRANT_ACTIVATION_STATUS = "WARRANT_ACTIVATION_STATUS";
    public static final String WARRANT_SIGNED_STATUS = "WARRANT_SIGNED_STATUS";
    public static final String WARRANT_TYPE = "WARRANT_TYPE";
    public static final String WEAPON_TYPE  = "WEAPONS";
    public static final String SANCTION_DEVIATION  = "SANCTION.DEVIATION";

    public static final String CAUTION_CODE_OTHER = "OT";

    public static final String MARITAL_STATUS_SINGLE = "SI";

    // Juvenile Offender Tracking
    public static final String SCHOOL_CODE = "SCHOOL_CODE";

    // Juvenile Associate
    public static final String RELEASE_DECISION = "RELEASE_DECISION";
    public static final String ID_VERIFICATION = "ID_VERIFICATION";

    // MSA
    public static final String USER_TYPE = "USER_TYPE";
    public static final String USER_GROUP_STATUS = "USER_GROUP_STATUS";
    public static final String GENERIC_USER_TYPE = "JIMS2_ACCOUNT_TYPE";

    // Officer
    public static final String RANK = "RANK";

    // Juvenile Case Work
    static public final String SOCIAL_ELEMENT_SCHOOL_PROGRAM = "C";
    static public final String SOCIAL_ELEMENT_RELATION = "F";
    static public final String SOCIAL_ELEMENT_RELIGION = "D";

    // Juvenile Case Work
    static public final String NCACTION_TAKEN = "NCACTION_TAKEN";
    static public final String NCSANCTION_LEVEL = "NCSANCTION_LEVEL";
    static public final String NCVIOLATION_LEVEL = "NCVIOLATION_LEVEL";
    static public final String NCSIGNATURE_STATUS = "NCSIGNATURE_STATUS";
    static public final String NCCOMPLETION_STATUS = "NCCOMPLETION_STATUS";

    // Juvenile
    static public final String PREFIX = "PREFIX";
    static public final String CONTACT_RELATIONSHIP = "CONTACT_RELATIONSHIP";
    static public final String JUVENILE_AGENCY = "JUVENILE_AGENCY";
    static public final String JUVENILE_RELIGION = "JUVENILE'S RELIGION";
   // public static final String NATIONALITY = "PLACE_OF_BIRTH_COUNTRY";
    public static final String GRADE_LEVEL = "GRADE_LEVEL";
    public static final String APPROPRIRATE_GRADE_LEVEL = "APPROPRIATE_GRADE_LEVEL";
    public static final String SCHOOL_EXIT_CODES = "JUVENILE_SCHOOL_EXIT_TYPE";
    public static final String ABUSE_LEVEL = "JUV_ABUSE_LEVEL";
    public static final String ABUSE_TYPE = "JUV_ABUSE_TYPE";
    public static final String DRUG_TYPE = "DRUG_TYPE";
    public static final String DRUG_TYPE_TEST = "DRUG_TYPE_TEST";
    public static final String DRUG_FREQUENCY = "DRUG_FREQUENCY";
    public static final String DRUG_DEGREE = "DRUG_DEGREE";
    public static final String DRUG_LOCATION_OF_USE = "LOCATION_OF_USE";
    public static final String DRUG_TEST_RESULTS = "DRUG_TEST_RESULTS";
    public static final String DRUG_TEST_ADMINISTERED = "DRUG_TEST_ADMINISTERED";
    public static final String SCHOOL_PARTICIPATION = "SCHOOL_PARTICIPATION";
    public static final String SCHOOL_PROGRAM = "SCHOOL_PROGRAM";
    public static final String SCHOOL_RULEINFRACTION = "SCHOOL_RULE_INFRACTION";
    public static final String SCHOOL_ATTENDANCESTATUS = "SCHOOL_ATTENDANCE_STATUS";
    public static final String GED_ENROLLMENT_STATUS = "JUVENILE_GED_ENROLL_STATUS";
    public static final String INVOVLED_WEAPON_TYPE = "INVOLVED_WEAPON_TYPE";
    public static final String HOSPITAL_LENGTH_OF_STAY = "HOSPITAL_LENGTH_OF_STAY";
    public static final String SCHOOL_INFO_VERIFICATION = "SCHOOL_INFO_VERIFICATION";
    public static final String ACADEMIC_PERFORMANCE = "ACADEMIC_PERFORMANCE";    

    // Juvenile Education Charter Tab
    public static final String GEDPROGRAM = "GED_PROGRAM";
    public static final String VEPPROGRAM = "VEP_PROGRAM";
    public static final String POSTRELEASEEMPLOYED = "POST_RELEASE_EMPLOYED";
    public static final String POSTRELEASECONTINUINGED = "POST_RELEASE_CONTINUING_ED";

    // Grade Level Codes
    public static final String KINDERGARTEN = "K";

    // Manage Officer
    public static final String JUVLOCATION = "JUV_LOCATION";
    public static final String JUVUNIT = "JUV_UNIT";
    public static final String WORKDAY = "WORK_DAY";
    public static final String OFFICERTYPE = "OFFICER_TYPE";

    // Common Supervision
    static public final String EVENT_TYPE = "EVENT_TYPE";
    static public final String JURISDICTION = "JURISDICTION";
    static public final String PERIOD = "PERIOD";
    static public final String SEVERITY_LEVEL = "SEVERITY_LEVEL";
    static public final String DOCUMENTS = "DOCUMENTS";
    static public final String CONDITION_STATUS = "CONDITION_STATUS";
    static public final String POLICY_STATUS = "POLICY_STATUS";
    static public final String STAFF_STATUS = "STAFF_STATUS";
    static public final String STAFF_POSITION_TYPE = "STAFF_POSITION_TYPE";
    static public final String STAFF_JOB_TITLE = "STAFF_JOB_TITLE";
    static public final String CSTS_OFFICER_TYPE = "CSTS_OFFICER_TYPE";
    static public final String SPECIAL_COURT = "SPECIAL_COURT";

    // CSCD Calendar
    static public final String QUADRANT = "QUADRANT";
    static public final String OV_OUTCOME = "OV_OUTCOME";
    static public final String FV_OUTCOME = "FV_OUTCOME";
    static public final String FV_PURPOSE = "FV_PURPOSE";
    static public final String CAL_CONTACT_METHOD = "CONTACT_METHOD";
    static public final String FV_TYPES = "FV_TYPES";
    static public final String SEX_OFFENDER_TYPES = "SEX_OFFENDER_TYPES";
    static public final String FV_MEASUREMENT_TYPES = "FV_MEASUREMENT_TYPES";
    static public final String CAR_TYPE = "CAR_TYPE";
    static public final String CAL_EVENT_CATEGORY = "CAL_EVENT_CATEGORY";
    static public final String CAL_EVENT_STATUS = "CAL_EVENT_STATUS";
    static public final String CS_EVENT_STATUS_OPEN = "O";
    static public final String CS_EVENT_STATUS_CLOSE = "C";
    static public final String CS_OFFICE_VISIT_CATEGORY = "OV";
    static public final String CS_FIELD_VISIT_CATEGORY = "FV";
    static public final String CS_OTHER_EVENT_CATEGORY = "OE";
    static public final String CS_OFFICE_VISIT_TYPE = "OV";
    static public final String CS_GROUP_OFFICE_VISIT_TYPE = "GV";
    static public final String CS_EVENT_CONTEXT_POSITION = "P";
    static public final String CS_EVENT_CONTEXT_SUPERVISEE = "S";
    static public final String CS_EVENT_CONTEXT_POSITION_AND_SUPERVISEE = "A";
    static public final String OTHER_AND_FV_OUTCOME_COMPLETE = "CO";
    static public final String OTHER_AND_FV_OUTCOME_INCOMPLETE = "IC";
    static public final String OTHER_AND_FV_OUTCOME_RESCHEDULED = "RE";
    static public final String OTHER_AND_FV_OUTCOME_SCHEDULED = "SC";
    static public final String OV_OUTCOME_ATTENDED = "AT";
    static public final String OV_OUTCOME_ABSENT = "AB";
    static public final String OV_OUTCOME_EXCUSED = "EX";
    static public final String OV_OUTCOME_RESCHEDULED = "RE";
    static public final String CASENOTE_FVCONTACTID_COLLATERAL = "FC";
    static public final String CASENOTE_FVCONTACTID_FACETOFACE = "FF";
    static public final String CASENOTE_FVCONTACTID_NOCONTACT = "FV";
    static public final String CS_FV_CONTACTMETHOD_DIRECTCONTACT = "DI";
    static public final String OTHER_EVENT_TYPE = "OT";
    static public final String DEFAULT_CS_EVENT_OUTCOME = "SC";

    // Common Supervision Adminsiter Staff Code Table Values
    static public final String STAFF_POSITION_TYPE_ASSISTANTSUP = "AS";
    static public final String STAFF_POSITION_TYPE_SUPERVISOR = "SU";
    static public final String STAFF_POSITION_TYPE_DIVISIONMGR = "DM";
    static public final String STAFF_POSITION_TYPE_OFFICER = "OF";
    static public final String STAFF_JOB_TITLE_CCO = "CCO";
    static public final String STAFF_JOB_TITLE_CLO = "CLO";
    static public final String STAFF_JOB_TITLE_CLOFLOATER = "CLF";
    static public final String STAFF_JOB_TITLE_CSO = "CSO";
    static public final String STAFF_JOB_TITLE_OFFICEMGR = "OMG";
    static public final String STAFF_JOB_TITLE_OFFICEMGR_SUPPORT_STAFF = "OAS";
    static public final String STAFF_STATUS_ACTIVE = "A";
    static public final String STAFF_STATUS_RETIRED = "R";
    static public final String STAFF_STATUS_INACTIVE = "I";

    // Common Supervision Task Code Tables
    static public final String TASK_LIST_TYPES = "TASK_LIST_TYPES";
    static public final String TASK_RECIPIENTS = "TASK_RECIPIENTS"; // CSC
    static public final String EMAIL_TASK_RECIPIENTS = "EMAIL_TASK_RECIPIENTS"; // CSC
    static public final String TASK_RECIPIENTS_PTS = "TASK_RECIPIENTS_PTS";
    static public final String EMAIL_TASK_RECIPIENTS_PTS = "EMAIL_TASK_RECIPIENTS_PTS";
    static public final String TASK_NOTIFICATION_TYPES = "TASK_NOTIFICATION_TYPES";
    static public final String TASK_SEVERITY_LEVEL = "TASK_SEVERITY_LEVEL"; 
    static public final String TASK_NOTIFICATION_TYPE_EMAIL = "E"; // this matches the database code entry do not change

    static public final String TASK_NOTIFICATION_TYPE_TASK = "T"; // this matches the  database  do not change

    static public final String TASK_NOTIFICATION_TYPE_NOTICE = "N"; // this matches the database code entry do not change


    // Task Status values
    static public final String TASK_NOTIFICATION_STATUS = "NOTIFICATION_STATUS"; // status for notification

    static public final String TASK_STATUS = "TASKSTATUS";

    // Common Supervision - Administer Service Provider
    static public final String SERVICE_LOCATION = "LOCATION";
    static public final String PROGRAM_TYPE = "PROGRAM_TYPE";
    static public final String PROGRAM_SUB_TYPE = "PROGRAM_SUB_TYPE";
    static public final String JUV_PROGRAM_GROUP = "PROGRAM_GROUP";
    static public final String COST_BASIS = "COST_BASIS";
    static public final String STATE_PROGRAM_CODE = "STATE_PROGRAM_CODE";
    static public final String TYPE_PROGRAM_CODE = "TJJD_PROGRAM_SUBTYPE";
    static public final String NAME_PREFIX = "NAME_PREFIX";
    static public final String NAME_SUFFIX = "NAME_SUFFIX";
    static public final String SERVICE_PROVIDER_STATUS = "SERVICE_PROVIDER_STATUS";

    static public final String ASP_CS_SEX_SPECIFIC = "PROG_SEX_SPECIFIC";
    static public final String ASP_CS_LANGUAGES_OFFERRED = "LANGUAGE";
    static public final String ASP_CS_PROGRAM_TYPE = "CS_PROGRAM_TYPE";
    static public final String ASP_CS_PROGRAM_SUB_TYPE = "CS_PROGRAM_SUBTYPE";
    static public final String ASP_CS_PROGRAM_GROUP = "CS_PROGRAM_GROUP";
    static public final String ASP_CS_PROGRAM_STATUS = "CS_PROGRAM_STATUS";
    static public final String ASP_CS_CONTACT_STATUS = "CS_SP_CONTACT_STATUS";
    static public final String ASP_CS_SERVICE_PROVIDER_STATUS = "CS_SERVICE_PROVIDER_STATUS";

    static public final String ASP_PROGRAM_LANGUAGE = "ASP_PROGRAM_LANGUAGE";

    static public final String ASP_CS_SERVPROV_INHOUSE_YES = "YES";
    static public final String ASP_CS_SERVPROV_INHOUSE_NO = "NO";

    static public final String ASP_CS_SERVPROV_SEARCHBY_PROGRAM = "PROG";
    static public final String ASP_CS_SERVPROV_SEARCHBY_SP = "SP";
    static public final String ASP_CS_SERVPROV_SEARCHBY_CONSOLIDATED = "CONS";

    static public final String ASP_CS_SERVPROV_ACTIVE = "A";
    static public final String ASP_CS_SERVPROV_PENDING = "P";
    static public final String ASP_CS_SERVPROV_INACTIVE = "I";

    static public final String ASP_CS_PROGRAM_ACTIVE = "A";
    static public final String ASP_CS_PROGRAM_PENDING = "P";
    static public final String ASP_CS_PROGRAM_INACTIVE = "I";
    static public final String ASP_CS_PROGRAM_SUSPENDED = "S";
    static public final String ASP_CS_PROGRAM_UNDERINVESTIGATION = "UI";

    static public final String ASP_CS_CONTACT_ACTIVE = "A";
    static public final String ASP_CS_CONTACT_PENDING = "P";
    static public final String ASP_CS_CONTACT_INACTIVE = "I";

    static public final String ASP_CS_CONTRACTPROGRAM_YES = "YES";
    static public final String ASP_CS_CONTRACTPROGRAM_NO = "NO";

    static public final String ASP_CS_CONTACT_ADMINCONTACT_YES = "YES";
    static public final String ASP_CS_CONTACT_ADMINCONTACT_NO = "NO";

    // Condition/CourtPolicy/DeptPolicy status
    final static public String STATUS_CREATED = "C";
    final static public String STATUS_INACTIVE = "I";
    final static public String STATUS_ACTIVE = "A";
    final static public String STATUS_CLOSED = "C";

    // Common Supervision - Suggested Order
    static public final String CONDITION_TYPE = "SUGGESTED_ORDER_COND";
    // Common Supervision - Process Supervision Order
    public static final String JURISDICTION_HC = "HC";
    public static final String JURISDICTION_OC = "OC";
    public static final String JURISDICTION_OS = "OS";
    public static final String ORDER_STATUS = "ORDER_STATUS";
    public static final String ORDER_TITLE = "ORDER_TITLE";
    public static final String REINSTATEMENT_REASON = "REINSTATEMENT_REASON";
    public static final String VERSION_TYPE = "VERSION_TYPE";
    public static final String WITHDRAW_REASON = "WITHDRAW_REASON";

    // Common Supervision - Administer Casenotes
    static public final String CONTACT_WITH = "CONTACT_WITH";
    static public final String CONTACT_METHOD = "CONTACT_METHOD";
    static public final String CASENOTE_SEARCHBY = "CASENOTE_SEARCHBY";
    static public final String CASENOTE_SUBJECT = "CASENOTE_SUBJECT";
    static public final String HOW_GENERATED = "HOW_GENERATED";
    static public final String CONTACT_WITH_ASSOCIATES = "AS";
    public static final String CASENOTE_TYPE = "CASENOTE_TYPE";
    public static final String CASENOTE_TYPE_ID_ORDER_MODIFICATION = "OM";
    public static final String CASENOTE_TYPE_ID_GENERAL = "GN";
    public static final String CASENOTE_TYPE_ID_SUPERVISION = "SU";
    public static final String CASENOTE_TYPE_ID_COMPLIANCE = "CM";
    public static final String CASENOTE_CONTACT_METHOD_COURT = "CT";
    public static final String CASENOTE_CONTACT_METHOD_NONE = "NO";
    public static final String CASENOTE_CONTACT_METHOD_INPERSON_OV = "IP";
    public static final String CASENOTE_SYSTEM_GENERATED_ID = "SG";
    public static final String CASENOTE_CREATED_BY_ID = "CB";
    public static final String CASENOTE_SUBJ_ID_ASSIGNMENT = "115";
    public static final String CASENOTE_SUBJECT_UNSPECIFIED = "AU";
    public static final String CASENOTE_SUBJECT_OTHER_EVENT = "OE";
    public static final String PROGRAM_REFERRAL_SUBJECT_CD = "PR";
    public static final String CASENOTE_SUBJECT_COURT_REVIEW = "CW";
    public static final String CASENOTE_SUBJECT_OFFICE_VISIT = "OV";
    public static final String CASENOTE_SUBJECT_GROUP_OFFICE_VISIT = "GV";
    public static final String CASENOTE_SUBJECT_FIELD_VISIT = "FV";
    public static final String CASENOTE_SUBJECT_VIOLATION = "VIO";
    public static final String CASENOTE_SUBJECT_CASESUMMARY = "SUM";
    public static final String CASENOTE_SUBJ_ID_FV_ASSIGNMENT = "379";

    // Common Supervision - Manage Workgroup
    public static final String WORKGROUP_TYPE = "WORKGROUP_TYPE";

    // order status
    public static final String STATUS_ACTIVE_ID = "A";
    public static final String STATUS_DRAFT_ID = "D";
    public static final String STATUS_INACTIVE_ID = "I";
    public static final String STATUS_INCOMPLETE_ID = "N";
    public static final String STATUS_PENDING_ID = "P";
    public static final String STATUS_WITHDRAWN_ID = "W";
    public static final String STATUS_REINSTATED_ID = "R";
    // Order version types
    public static final String VERSION_TYPE_ID_AMMENDED = "A";
    public static final String VERSION_TYPE_ID_MODIFIED = "M";
    public static final String VERSION_TYPE_ID_ORIGINAL = "O";

    public static final String SIGNOR_JUDGE_ID = "J";
    public static final String SIGNOR_MAGISTRATE_ID = "M";
    public static final String ORDER_TITLE_SOPB = "SOPB";

    // Criminal Code Categories
    static public final String SCAR_CATEGORY = "13";
    static public final String TATTOO_CATEGORY = "15";

    // Casefile Closing
    static public final String FACILITY = "JUVENILE_DETENTION_FACILITY";
    static public final String PERMANENCYPLAN = "PERMANENCY_PLAN";
    static public final String FACILITY_RELEASON_REASON = "FACILITY_RELEASE_REASON";
    static public final String LEVELOFCARE = "LEVEL_OF_CARE";
    static public final String SUPERVISIONOUTCOME = "SUPERVISION_OUTCOME";

    // Salary Rate constants of salary rate code table
    static public final String SALARY_RATE_HOURLY = "HR";
    static public final String SALARY_RATE_WEEKLY = "WK";
    static public final String SALARY_RATE_BIWEEKLY = "BW";
    static public final String SALARY_RATE_MONTHLY = "MO";
    static public final String SALARY_RATE_YEARLY = "YR";

    // Family Traits
    static public final String FAMILY_TRAIT_STATUS = "FAMILY_TRAIT_STATUS";
    static public final String FAMILY_TRAIT_LEVEL = "FAMILY_TRAIT_LEVEL";
    static public final String FAMILY_TRAIT = "FAMILY_TRAIT";
    static public final String MEMBER_TRAIT_TYPE = "MEMBER_TRAIT_TYPE";
    static public final String CAUSE_OF_DEATH = "CAUSE_OF_DEATH";
    static public final String DEATH_VERIFICATION = "DEATH_VERIFICATION";
    static public final String SALARY_RATE = "SALARY_RATE";
    static public final String MEMBER_BENEFIT_ELIGIBILITY_TYPE = "MEMBER_BENEFIT_ELIGIBILITY";
    static public final String INSURANCE_TYPE = "INSURANCE_TYPE";
    static public final String INVOLVEMENT_LEVEL = "INVOLVEMENT_LEVEL";
    static public final String PHONE_TYPE = "PHONE_TYPE";

    // Process Benefits Assessment
    static public final String BENEFITS_ASSESSMENT_SOURCE = "BENEFITS_ASSESSMENT_SOURCE";
    static public final String INCOME_SOURCE = "INCOME_SOURCE";

    // caseplan
    static public final String CASEPLAN_STATUS = "CASEPLAN_STATUS";
    static public final String GOAL_STATUS = "GOAL_STATUS";
    static public final String GOAL_STATUS_PENDING = "P";
    static public final String GOAL_STATUS_LOCKED = "L";
    static public final String GOAL_STATUS_APPROVED = "A";
    static public final String GOAL_STATUS_ACCEPTED = "C";
    static public final String GOAL_STATUS_ENDED = "E";

    static public final String CASEPLAN_STATUS_PENDING = "P";
    static public final String CASEPLAN_STATUS_INREVIEW = "I";
    static public final String CASEPLAN_STATUS_REVIEWED = "R";
    static public final String CASEPLAN_STATUS_SIGNED = "S";

    // supervision rules
    static public final String MONITOR_FREQUENCY = "MONITOR_FREQUENCY";
    static public final String COMPLETION_STATUS = "COMPLETION_STATUS";
    static public final String SUPERVISION_RULES_TYPE = "SUPERVISION_RULES_TYPE";
    static public final String JUV_SUPERVISION_LEVEL = "JUV_SUPERVISION_LEVEL";
    static public final String JUV_SPL_EDU_HANDICAPPING_COND = "JUV_SPL_EDU_HANDICAPPING_COND";
    static public final String CHARACTER_DESCRIPTION = "CHARACTER_DESCRIPTION";

    // Login-Logout
    static public final String PASSWORD_QUESTION = "PASSWORD_QUESTION";

    // Out Of County Case
    static public final String CSCD_AGENCY = "CSC";
    static public final String PRETRIAL_AGENCY = "PTR";
    static public final String OFFENSE_CODE = "pd.codetable.criminal.OffenseCode";
    static public final String DISP_TYPE = "DISPOSITION";
    // Court Divisions (CDI)
    static public final String CSCD = "010";
    static public final String PTS = "020";
    // Disposition Codes
    static public final String PRETRIAL_INTERVENTION = "PTIN";
    static public final String DEFERRED_ADJUDICATION = "DADJ";
    static public final String STRAIGHT_PROBATION = "PROB";
    static public final String CLOSED_ABSCONDED = "CABS";
    static public final String CLOSED_DEATH_OF_PROBATIONER = "CDTH";
    static public final String CLOSED_EARLY_TERMINATION = "CETR";
    static public final String CLOSED_PROBATION_EXPIRED = "CEXP";
    static public final String CLOSED_LAW_VIOLATION = "CLAW";
    static public final String CLOSED_MOVED_FROM_HARRIS_COUNTY = "CMOV";
    static public final String CLOSED_OTHER_REASON = "COTH";
    static public final String CLOSED_SUPERVISION_REJECTED = "CREJ";
    static public final String CLOSED_REQUEST_OF_ORIGINAL_JURISDICTION = "CREQ";
    static public final String CLOSED_PROBATION_REVOKED = "CRVK";
    static public final String CLOSED_TECHNICAL_VIOLATION = "CTEC";
    // Defendant status
    static public final String OCS_OOC_PRETRIAL_INTERVENTION = "W";
    // Case status
    static public final String OCS_PRETRIAL_INTERVENTION = "K";
    // Instrument type
    static public final String PRETRIAL_INTERVENTION_INSTR = "PTI";
    static public final String CSCD_PTIN_INSTR_TYPE = "PIN";
    static public final String CSCD_MISD_INSTR_TYPE = "MIN";
    static public final String CSCD_FELONY_INSTR_TYPE = "FIN";
    // Courts
    static public final String OUT_OF_STATE_FELONY = "OTF";
    static public final String IN_STATE_FELONY = "INF";
    static public final String OUT_OF_STATE_MISD = "OTM";
    static public final String IN_STATE_MISD = "INM";
    static public final String HARRIS_COUNTY_TREASURER_OFFICE_MISD = "HCT";
    static public final String CSCD_CSR_JP_CRTS_IN_LIEU_OF_MISD = "CSR";

    // Connection code
    static public final String DEFENDANT = "DEF";

    // Conduct Interview
    static public final String INTERVIEW_TYPE = "INTERVIEW_TYPE";
    static public final String INTERVIEW_QUESTION_CATEGORY = "INTERVIEW_QUESTION_CATEGORY";
    
    static public final String YOUTH_LIVES_WITH = "YOUTH_LIVES_WITH";
    static public final String INTERVIEW_RECORD = "INTERVIEW_RECORD";
    static public final String TASKSTATUS = "TASKSTATUS";
    static public final String DETENTION_NOTIFICATION_METHOD = "DETENTION_NOTIFICATION_METHOD";
    static public final String JUVENILE_DETENTION_FACILITY = "FACILITY";
    static public final String DETENTION_REASON = "DETENTION.REASON";

    static public final String CONTRACTTYPE = "CONTRACT_TYPE";
    static public final String GLACCOUNTKEY = "GLACCOUNTKEY";
    static public final String EXPLANATION_METHOD = "EXPLANATION_METHOD";

    // Search Casefile - Activitites
    static public final String ACTIVITY_CATEGORY = "ACTIVITY_CATEGORY";
    static public final String ACTIVITY_TYPE = "ACTIVITY_TYPE";
    static public final String ACTIVITY_CODE = "ACTIVITY_CODE";
    // ER changes 11054

    static public final String ACTIVITY_PERMISSION = "ACTIVITY_PERMISSION";
    static public final String REFERRAL_TYPE = "REFERRAL_TYPE";

    // MAYSI
    static public final String REASON_NOT_DONE = "REASON_NOT_DONE";

    // Hospitalization
    static public final String ADMISSION_TYPE = "ADMISSION_TYPE";

    // manage codetable record use case
    public static final String FINDALL = "findAll";
    public static final String SETCREATEDATE = "setCreateTimestamp";
    public static final String SETUPDATEDATE = "setUpdateTimestamp";
    public static final String SETCREATEUSERID = "setCreateUserID";
    public static final String SETUPDATEUSERID = "setUpdateUserID";
    public static final String GETOID = "getOID";
    public static final String FIND = "find";
    public static final String SET = "set";
    public static final String GET = "get";
    public static final String IS = "is";
    public static final String STRING = "String";
    public static final String EMPTY_QUOTE = "";
    public static final String SETSTATUS = "setStatus";
    public static final String SETCODETABLENAME = "setCodeTableName";
    public static final String SETINACTIVEEFFECTIVEDATE = "setInactiveEffectiveDate";
    public static final String SETACTIVEDATE = "setActiveDate";
    public static final String SIMPLE_CODE = "SL";
    public static final String COMPOUND_CODE = "CD";
    public static final String COMPLEX_CODE = "CX";
    public static final String ENUMERATION = "enum";
    public static final String DOUBLE = "DOUBLE";
    public static final String PRIMITIVEDOUBLE = "double";
    public static final String FLOAT = "FLOAT";
    public static final String PRIMITIVEFLOAT = "float";
    public static final String INTEGER = "INTEGER";
    public static final String INT = "INT";
    public static final String PRIMITIVEINTEGER = "integer";
    public static final String DATE = "DATE";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String BOOLEAN = "BOOLEAN";
    public static final String PRIMITIVEBOOLEAN = "boolean";
    public static final String LONG = "LONG";
    public static final String PRIMITIVELONG = "long";
    public static final String OBJECT = "Object";
    public static final String OID = "OID";
    public static final String SETMODIFIED = "setModified";
    public static final String SETCONTEXT = "setContext";
    public static final String ISSUE = "ISSUE";
    public static final String CATEGORY = "CATEGORY";
    public static final String OPTION = "OPTION";
    public static final String CODETABLE_CONTEXTS = "CONTEXT_KEYS";
    public static final String CODETABLE_ENTITIES = "ENTITY_NAMES";
    public static final String CODETABLE_CONTEXTS_AND_ENTITIES = "CONTEXT_KEYS_AND_ENTITIES";
    public static final String CONTEXTS_CLASS = "pd.codetable.Code";
    public static final String ENTITIES_PACKAGE = "pd.codetable.";
    public static final String CODE_TABLE_TYPE_NAME = "CODE_TABLE_TYPE";

    // Program Referral
    public static final String PROGRAM_REFERRAL_STATUS = "PROGRAM_REFERRAL_STATUS";
    public static final String PROGRAM_REFERRAL_SUBSTATUS = "PROGRAM_REFERRAL_SUBSTATUS";
    public static final String PROGRAM_REFERRAL_OUTCOME = "PROGRAM_REFERRAL_OUTCOME";
    public static final String REASON_FOR_PLACEMENT = "REASON_FOR_PLACEMENT";

    // SUPERVISION CATEGORIES
    public static final String CASEFILE_SUPERVISION_CAT_PRE_PETITION = "PP";
    public static final String CASEFILE_SUPERVISION_CAT_PRE_ADJ = "AD";
    public static final String CASEFILE_SUPERVISION_CAT_POST_ADJ_COM = "AC";
    public static final String CASEFILE_SUPERVISION_CAT_POST_ADJ_RES = "AR";
    public static final String CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ = "DA";

    // SUPERVISION TYPES
    public static final String CASEFILE_SUPERVISION_TYPE_COURTESY_SUPERVISION = "CSS";
    public static final String CASEFILE_SUPERVISION_TYPE_INTAKE_SCREENING_SUPERVISION = "ISS";
    public static final String CASEFILE_SUPERVISION_TYPE_COMMUNITY_SUPERVISION = "CMS";
    public static final String CASEFILE_SUPERVISION_TYPE_PRE_ADJUDICATION_SUPERVISION = "PAS";
    public static final String CASEFILE_SUPERVISION_TYPE_RESIDENTIAL_SUPERVISION = "RPS";
    public static final String CASEFILE_SUPERVISION_TYPE_DEFERRED_PROSECUTION_SUPERVISION = "DPS";
    public static final String CASEFILE_SUPERVISION_TYPE_COURT_SUPERVISION = "CST";
    public static final String CASEFILE_SUPERVISION_TYPE_STATUS_OFFENDER_SUPERVISION = "SOS";
    public static final String CASEFILE_SUPERVISION_TYPE_ENHANCED_SUPERVISION = "ACS";
    public static final String CASEFILE_SUPERVISION_TYPE_INTENSIVE_SUPERVISION = "INT";
    public static final String CASEFILE_SUPERVISION_TYPE_DEFERRED_ADJUDICATION_SUPERVISION = "DAS";

    public static final String CS_TASK_LIST_TYPE_WORKGROUP = "WG";

    // County default drop down value
    public static final String HARRIS_COUNTY = "101";

    // ADMININSTER ASSESSMENTS
    public static final String CSC_ASSESSMENT_INCOMPLETE = "I";
    public static final String CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_TYPE = "ASSESSMENT_TYPE";
    public static final String CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_CLASSIFICATION = "ASSESSMENT_CLASSIFICATION";

    public static final String CSC_ASSESSMENTS_TYPE_ID_WISCONSIN = "W";
    public static final String CSC_ASSESSMENTS_TYPE_ID_LSIR = "L";
    public static final String CSC_ASSESSMENTS_TYPE_ID_SCS = "S";
    public static final String CSC_ASSESSMENTS_TYPE_ID_SCS_INTERVIEW = "I";
    public static final String CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD = "F";

    public static final String CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SI = "SI";
    public static final String CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_CC = "CC";
    public static final String CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_ES = "ES";
    public static final String CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_LS = "LS";
    public static final String CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SS = "SS";

    // ADMINISTER SUPERVISION PLAN
    public static final String CSC_SUPERVISION_PLAN_STATUS = "SUPERVISION_PLAN_STATUS";

    public static final String CSC_SUPERVISION_PLAN_STATUS_ACTIVE = "A";
    public static final String CSC_SUPERVISION_PLAN_STATUS_INACTIVE = "I";
    public static final String CSC_SUPERVISION_PLAN_STATUS_DRAFT = "D";

    // Common Supervision - Administer Compliance - Violation Report/Case Summary
    public static final String SUGGESTEDCOURTACTION = "SUGGESTED_COURT_ACTION";
    public static final String PAYTYPE = "PAY_TYPE";
    public static final String VIOLATION_REPORT_STATUS = "VIOLATION_REPORT_STATUS";
    public static final String MOTION_ACTIVITY = "MOTION_ACTIVITY";
    public static final String MOTION_DISPOSITION = "MOTION_DISPOSITION";
    public static final String OTHER_ACTIVITY = "OTHER_ACTIVITY";

    // CLOSE SUPERVISION
    public static final String OOC_DISPOSITION_TYPE = "OOC_DISPOSITION_TYPE";

    public static final String OOC_DISP_TYPE_ABSCONDED = "CABS";
    public static final String OOC_DISP_TYPE_DEATH = "CDTH";
    public static final String OOC_DISP_TYPE_EARLY_TERMINATION = "CETR";
    public static final String OOC_DISP_TYPE_PROBATION_EXP = "CEXP";
    public static final String OOC_DISP_TYPE_LAW_VIOLATION = "CLAW";
    public static final String OOC_DISP_TYPE_MOVED_FRM_HRRS_CNTY = "CMOV";
    public static final String OOC_DISP_TYPE_OTHER_REASON = "COTH";
    public static final String OOC_DISP_TYPE_SUPERVISION_REJECTED = "CREJ";
    public static final String OOC_DISP_TYPE_REQ_OF_ORIG_JURISDCTION = "CREQ";
    public static final String OOC_DISP_TYPE_PROBATION_REVOKED = "CRVK";
    public static final String OOC_DISP_TYPE_TECHNICAL_VIOLATION = "CTEC";

    public static final String JIMS2_DISCHARGE_REASON = "JIMS2_DISCHARGE_REASON";

    public static final String JIMS2_DISCHRG_REAS_ABSCONDED = "ABS";
    public static final String JIMS2_DISCHRG_REAS_DEATH = "DTH";
    public static final String JIMS2_DISCHRG_REAS_INAPPROPRIATE = "INA";
    public static final String JIMS2_DISCHRG_REAS_PRGM_VIOLATIONS = "PGV";
    public static final String JIMS2_DISCHRG_REAS_SUCCESSFUL = "SUC";
    public static final String JIMS2_DISCHRG_REAS_REVOKED = "REV";
    public static final String JIMS2_DISCHRG_REAS_TRANSFER = "TFC";
    public static final String JIMS2_DISCHRG_REAS_UNKNOWN = "UNK";

    public static final String CSTS_DISCHARGE_REASON = "CSTS_DISCHARGE_REASON";

    public static final String CSTS_DISCHRG_REAS_OTHER = "O";
    public static final String CSTS_DISCHRG_REAS_INAPPROPRIATE = "I";
    public static final String CSTS_DISCHRG_REAS_VIOLATION = "V";
    public static final String CSTS_DISCHRG_REAS_COMPLETION = "C";

    public static final String CONVERSION_TYPE_TST_JIMS2 = "TST_JIMS2";
    public static final String CONVERSION_TYPE_OOC_JIMS2_AUTO_EXIT = "OOC_REFERRAL_AUTO_EXIT";
    public static final String CONVERSION_TYPE_JIMS2_CSTS = "JIMS2_CSTS";
    public static final String CONVERSION_TYPE_HC_AUTO_EXIT = "HC_REFERRAL_AUTO_EXIT";

    static public final String NAME_TYPES = "NAME.TYPE";
    static public final String PROGRAM_TRACKER = "PROGRAM_TRACKER";
    static public final String PROGRAM_SCHEDULE_TYPE = "PROGRAM_SCHEDULE_TYPE"; // added for U.S #11099
    
    public static final String OVERRIDE_REASON = "OVERRIDE_REASON";
    
    public static final String TJJD_SUBSTANCE_ABUSE = "TJJD_SUBSTANCE_ABUSE";

    /**
     * Constructor
     */
    private PDCodeTableConstants()
    {
	super();
    }

}
