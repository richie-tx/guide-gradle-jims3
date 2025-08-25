/*
 * Created on Apr 22, 2004
 *
 */
package naming;

/**
 * @author Jim Fisher
 */
public class UIConstants
{

    //Risk Analysis Constants
    public static final String NOANSNEEDED = "NOANSNEEDED";
    public static final String DATE = "DATE";
    public static final String TEXTBOX = "TEXTBOX";
    public static final String TEXTAREA = "TEXTAREA";
    public static final String LISTBOX = "LISTBOX";
    public static final String CHECKBOX = "CHECKBOX";
    public static final String CHECKBOXWITHCHRONIC = "CHECKBOXWITHCHRONIC";
    public static final String RADIO = "RADIO";
    public static final String DROPDOWN = "DROPDOWN";
    public static final String QUESTIONHEADER = "QUESTIONHEADER";
    public static final String ISCHRONIC = "1";

    public static final String NO = "N";
    public static final String YES = "Y";
    public static final String NO_FULL_TEXT = "NO";
    public static final String YES_FULL_TEXT = "YES";
    public static final String EMPTY_STRING = "";

    public static final int MAX_SEARCH_RESULTS = 2000;

    public static final String INPUT = "input";

    // Printing constants

    public static final int PRINT_AS_WORD_DOC = 1;
    public static final int PRINT_AS_PDF_DOC = 2;

    //	SEARCH NOTIFICATION CONSTANTS
    public static final String SEARCH_NOTIFICATON_TYPE_TASK = "TASK";
    public static final String SEARCH_NOTIFICATON_TYPE_NOTIF = "NOTIF";
    public static final String SEARCH_NOTIFICATON_CATEGORY_ADMIN = "ADMIN";

    // NOTIFICATION CONSTANTS
    public static final String NOTIFICATON_RESPONSE_EVENT_CONTEXT = "respEvent";
    public static final String DUPLICATE_FAMILYMEMBER_FOUND_NOTIF = "DUPLICATE.FAMILYMEMBER.FOUND";
    public static final String FAMILYMEMBER_PRIMARYINFO_UPDATED_NOTIF = "FAMILYMEMBER.PRIMARYINFO.UPDATED";
    public static final String LOCATION_INACTIVATE_NOTIFICATION = "CS.LOCATION.INACTIVATED";
    public static final String SERVICE_INACTIVATE_NOTIFICATION = "CS.SERVICE.INACTIVATED";
    public static final String EVENT_CANCELLATION_OFFICER_NOTIFICATION = "JC.EVENT.OFFICER.CANCELLATION";
    public static final String EVENT_CANCELLATION_PROVIDER_NOTIFICATION = "JC.EVENT.PROVIDER.CANCELLATION";
    public static final String EVENT_CANCELLATION_GUARDIAN_NOTIFICATION = "JC.EVENT.GUARDIAN.CANCELLATION";
    public static final String EVENT_ATTENDANCE_OFFICER_NOTIFICATION = "JC.EVENT.OFFICER.ATTENDANCE";
    public static final String SERVICE_LOCATION_REMOVAL_NOTIFICATION = "JC.SERVICE.LOCATION.REMOVAL";
    public static final String JPO_SERVICE_LOCATION_REMOVAL_NOTIFICATION = "JC.JPO.SERVICE.LOCATION.REMOVAL";
    public static final String JPO_SERVICE_LOCATION_UNIT_REMOVAL_NOTIFICATION = "JC.JPO.SERVICE.LOCATION.UNIT.REMOVAL";
    public static final String SERVICE_INACTIVATION_NOTIFICATION = "JC.SERVICE.INACTIVATION";
    public static final String PROGRAM_REFERRAL_NOTIFICATION = "JC.PROGRAM.REFERRAL.ACTION";
    public static final String PROCESS_CASEPLAN_GOALS_NEEDED = "JC.PROCESS.CASEPLAN.GOALS.NEEDED";
    public static final String COPY_CASEPLAN_REVIEW_NEEDED = "JC.COPY.CASEPLAN.REVIEW.NEEDED";
    public static final String SP_CLM_NOTIFICATION = "JC.SP.CLM.NOTIFICATION";

    //added for facility
    public static final String JUVENILE_FACILITY_ADMIT_NOTIFICATION = "JC.JPO.FACILITY.ADMIT";

    //added for Detention Court Action
    public static final String JUVENILE_DETENTION_DETAINED_NOTIFICATION = "JC.DETENTION.COURT.DETAINED";
    public static final String JUVENILE_DETENTION_RELEASE_NOTIFICATION = "JC.DETENTION.COURT.RELEASE";

    public static final String CASEFILE_FAILURE_NOTIFICATON_CONTEXT = "CASEFILEFAILURECONTEXT";
    public static final String FAILURE_CASEFILE_NOTIFICATION = "MJCW.CASEFILE.GENERATIONFAILURE";

    // General error key constants
    public static final String REQUIRED_NONEDITABLE_ERROR = "error.requiredNonEditable";
    public static final String REQUIRED_NONEDITABLE_MISSING_ERROR = "error.requiredNonEditableMissing";
    public static final String REQUIRED_EDITABLE_ERROR = "error.requiredEditable";
    public static final String EXCEEDS_MAX_LENGTH_ERROR = "error.fieldExceedMaxLength";
    public static final String NO_RECORDS_ERROR = "error.noRecords";
    public static final String ERROR = "error";

    // Juvenile Warrants error key constants
    public static final String JW_NO_CHARGES_ERROR = "error.juvenilewarrant.nocharges";
    public static final String JW_ACTIVEORPENDINGWARRANT_ERROR = "error.juvenilewarrant.activeorpending.warrant.found";
    public static final int NO_ERRORS = 0;
    public static final int NO_CHARGES = 1;
    public static final int NO_WARRANT_FOUND = 2;
    public static final int IS_SIGNED = 4;
    public static final int PRE_CONTITIONS_NOT_MATCHED = 5;

    // Common constants for common ActionForwards

    public static final String ACCEPT_SUCCESS = "acceptSuccess";
    public static final String ACTION = "action";
    public static final String ACTIVATE = "activate";
    public static final String ACTIVATE_SUCCESS = "activateSuccess";
    public static final String ACTIVATE_FAILURE = "activateFailure";
    public static final String ACTIVE = "active";
    public static final String ADD = "add";
    public static final String ADD_USER_SUCCESS = "addUserSuccess";
    public static final String ADDSELECTED_SUCCESS = "addSelected";
    public static final String ADD_SELECTED_SUCCESS = "addSelectedSuccess";
    public static final String ADD_SUCCESS = "addSuccess";
    public static final String ADD_TO_LIST_SUCCESS = "addToListSuccess";
    public static final String APPROVAL_FAILURE = "approvalFailure";
    public static final String APPROVE = "approve";
    public static final String ADVANCED = "advanced";
    public static final String ADVANCED_SEARCH_SUCCESS = "advancedSearchSuccess";
    public static final String ADVANCED_SEARCH_FAILURE = "advancedSearchFailure";
    public static final String APPLICATION_FAILURE = "applicationFailure";
    public static final String ARCHIVE_SUCCESS = "archiveSuccess";
    public static final String BACK = "back";
    public static final String BACK_REFERRAL_TRANSFER = "backToReferralTransfer";
    public static final String BACK_CREATE = "backToCreate";
    public static final String BACK_MODIFY = "backModify";
    public static final String BACK_REACTIVATE = "backToReactivate";
    public static final String BACK_TO_JOT = "backToJot";
    public static final String BACK_TO_WARRANT = "backToWarrant";
    public static final String BACK_TO_SEARCH = "backToSearch";
    public static final String BACK_TO_SERVICE = "backToService";
    public static final String BACK_TO_SUMMARY = "backToSummary";
    public static final String BACK_UPDATE = "backToUpdate";
    public static final String BACK_VIEW = "backView";
    public static final String BASIC = "basic";
    public static final String BASIC_SEARCH_SUCCESS = "basicSearchSuccess";
    public static final String BRACKET_LEFT = "[";
    public static final String BRACKET_RIGHT = "]";
    public static final String CANCEL = "cancel";
    public static final String NOTIFICATION = "notification";
    public static final String CANCEL_CREATE = "cancelCreate";
    public static final String CANCEL_DELETE = "cancelDelete";
    public static final String CANCEL_MAIN_PAGE_HOME = "cancelMainPageHome";
    public static final String CANCEL_JOT = "cancelJot";
    public static final String CANCEL_SEARCH = "cancelSearch";
    public static final String CANCEL_SERVICE = "cancelService";
    public static final String CANCEL_UPDATE = "cancelUpdate";
    public static final String CANCEL_VIEW = "cancelView";
    public static final String CASE = "CASE";
    public static final String CASEFILE = "Casefile";
    public static final String CASE_SEARCH_SUCCESS = "caseSearchSuccess";
    public static final String CLOSE = "close";
    public static final String CLOSE_SUCCESS = "closeSuccess";
    public static final String CONFIRM = "confirm";
    public static final String CONFIRM_DUAL = "confirmdual";
    public static final String CONFIRM_COPY = "confirmCopy";
    public static final String CONFIRM_COPY_SUCCESS = "confirmCopySuccess";
    public static final String CONFIRM_CREATE = "confirmCreate";
    public static final String CONFIRM_CREATE_SUCCESS = "confirmCreateSuccess";
    public static final String CONFIRM_CREATE_PRETRIAL_SUCCESS = "confirmCreatePretrialSuccess";
    public static final String CONFIRM_DELETE = "confirmDelete";
    public static final String CONFIRM_DELETE_SUCCESS = "confirmDeleteSuccess";
    public static final String CONFIRM_PRETRIAL_CREATE = "confirmPretrialCreate";
    public static final String CONFIRM_PRETRIAL_REACTIVATE = "confirmPretrialReactivate";
    public static final String CONFIRM_PRETRIAL_UPDATE = "confirmPretrialUpdate";
    public static final String CONFIRM_PRETRIAL_VIEW = "confirmPretrialView";
    public static final String CONFIRM_PREPARE_TO_FILE = "confirmPrepareToFile";
    public static final String CONFIRM_REACTIVATE = "confirmReactivate";
    public static final String CONFIRM_REACTIVATE_PRETRIAL_SUCCESS = "confirmReactivatePretrialSuccess";
    public static final String CONFIRM_REACTIVATE_SUCCESS = "confirmReactivateSuccess";
    public static final String CONFIRM_REINSTATE = "confirmReinstate";
    public static final String CONFIRM_REINSTATE_SUCCESS = "confirmReinstateSuccess";
    public static final String CONFIRM_SUCCESS = "confirmSuccess";
    public static final String CONFIRM_TO_FILE_SUCCESS = "confirmToFileSuccess";
    public static final String CONFIRM_UPDATE = "confirmUpdate";
    public static final String CONFIRM_UPDATE_PRETRIAL_SUCCESS = "confirmUpdatePretrialSuccess";
    public static final String CONFIRM_UPDATE_SUCCESS = "confirmUpdateSuccess";
    public static final String CONFIRM_WITHDRAW = "confirmWithdraw";
    public static final String CONFIRM_WITHDRAW_SUCCESS = "confirmWithdrawSuccess";
    public static final String COPY = "copy";
    public static final String COPY_FAILURE = "copyFailure";
    public static final String COPY_SUCCESS = "copySuccess";
    public static final String CONDITION_SUCCESS = "conditionSuccess";
    public static final String CONTINUE_PROCESS = "continueProcess";
    public static final String CONTINUE_SUCCESS = "continueSuccess";
    public static final String CONTINUE_CREATE_SUCCESS = "continueCreateSuccess";
    public static final String CORRECT_SUCCESS = "correctSuccess";
    public static final String CORRECT = "correct";
    public static final String CREATE = "create";
    public static final String CREATEDUALSTATUS = "createDualStatus";
    public static final String CREATE_FAILURE = "createFailure";
    public static final String CREATE_NEW_REFERRAL = "createNewReferral";
    public static final String CREATE_PRETRIAL_SUCCESS = "createPretrialSuccess";
    public static final String CREATE_RESET = "createReset";
    public static final String CREATE_SUCCESS = "createSuccess";
    public static final String CREATE_SUMMARY = "createSummary";
    public static final String CREATE_CONFIRM = "createConfirm";
    public static final String CS_WORKGRP_UPDATE_ALL = "CS-WORKGRP-UPDATE-ALL";
    public static final String CURRENT_SUCCESS = "currentSuccess";
    public static final String CUSTOMIZE_SUCCESS = "customizeSuccess";
    public static final String DEFENDENT_SIG_AQUIRED_SUCCESS = "defendentSigAquiredSuccess";
    public static final String DEFENDENT_SIG_AQUIRED = "defendentSigAquired";
    public static final String DEFENDENT_SIG_AQUIRED_CONFIRMATION = "defendentSigAquiredConfirmation";
    public static final String DELETE = "delete";
    public static final String DELETE_FAILURE = "deleteFailure";
    public static final String DELETE_SUCCESS = "deleteSuccess";
    public static final String DELETE_SUMMARY = "deleteSummary";
    public static final String EDIT = "edit";
    public static final String EXCEPTIONS = "exceptions";
    public static final String EXCEPTIONSAPPROVE = "exceptionsApprove";
    public static final String FAILURE = "failure";
    public static final String REDIRECT = "redirect";
    public static final String FILTER_SUCCESS = "filterSuccess";
    public static final String FIND = "find";
    public static final String FIND_OFFENSE_SUCCESS = "findOffenseSuccess";
    public static final String FIND_OFFENSE_PRETRIAL_SUCCESS = "findOffensePretrialSuccess";
    public static final String FIND_SUCCESS = "findSuccess";
    public static final String FINISH = "finish";
    public static final String GO = "go";
    public static final String HANDLE_EXCEPTION = "handleException";
    public static final String HIDE_SAMPLE_SUCCESS = "hideSampleSuccess";
    public static final String IMPACT_SUCCESS = "impactSuccess";
    public static final String INACTIVE = "inactive";
    public static final String INACTIVE_SUCCESS = "inactiveSuccess";
    public static final String INVESTIGATE = "investigate";
    public static final String JUVENILE = "Juvenile";
    public static final String LIGHT = "Light";
    public static final String LISTSUCCESS = "listSuccess";
    public static final String LOGOUT_SUCCESS = "logoutSuccess";
    public static final String MAIN_PAGE = "mainPage";
    public static final String MAIN_MENU = "mainMenu";
    public static final String MAINTAIN = "maintain";
    public static final String MANAGE_LOCATION_UNITS = "manageLocationUnits";
    public static final String MODIFY = "modify";
    public static final String MODIFY_ONE_SUCCESS = "modifyOneSuccess";
    public static final String MODIFY_SUCCESS = "modifySuccess";
    public static final String NEW_CONSOLIDATION = "newConsolidation";
    public static final String NEW_CASE_DOCS_SUCCESS = "newCaseDocsSuccess";
    public static final String NEXT = "next";
    public static final String NEXTTOPASOREVIEWNEWCONDITION = "nextToPasoReviewNewCondition";
    public static final String NEXT_IMPACT_SUCCESS = "nextImpactSuccess";
    public static final String NEXT_SUCCESS = "nextSuccess";
    public static final String PREPARE_TO_FILE = "prepareToFile";
    public static final String PREPARE_TO_FILE_SUCCESS = "prepareToFileSuccess";
    public static final String PRETRIAL_CREATE = "pretrialCreate";
    public static final String PRETRIAL_REACTIVATE = "pretrialReactivate";
    public static final String PRETRIAL_UPDATE = "pretrialUpdate";
    public static final String PRETRIAL_VIEW = "pretrialView";
    public static final String PREVIEW_SUCCESS = "previewSuccess";
    public static final String PREVIEW_IMPACT_SUCCESS = "previewImpactSuccess";
    public static final String PRINT = "print";
    public static final String PRINT_REPORT = "printReport";
    public static final String PRINT_SUCCESS = "printSuccess";
    public static final String PRINT_PREA_SUCCESS = "printPREASuccess";
    public static final String PRINT_SIGNATURE = "printSignature";
    public static final String PRINT_SIGNATURE_SUCCESS = "printSignatureSuccess";
    public static final String PRINT_DRAFT_SUCCESS = "printDraftSuccess";
    public static final String PRINT_SPANISH_SUCCESS = "printSpanishSuccess";
    public static final String REACTIVATE = "reactivate";
    public static final String REACTIVATE_PRETRIAL_SUCCESS = "reactivatePretrialSuccess";
    public static final String REACTIVATE_SUCCESS = "reactivateSuccess";
    public static final String REFERRAL_INQUIRY = "referralInquiry";
    public static final String REFRESH = "refresh";
    public static final String DETENTION = "detention";
    public static final String REFRESH_SUCCESS = "refreshSuccess";
    public static final String REINSTATE = "reinstate";
    public static final String REINSTATE_SUCCESS = "reinstateSuccess";
    public static final String REJECT = "reject";
    public static final String REMOVE = "remove";
    public static final String REMOVE_FROM_LIST_SUCCESS = "removeFromListSuccess";
    public static final String RESEQUENCE_SUCCESS = "resequenceSuccess";
    public static final String RESET = "Reset";
    public static final String RESET_SUCCESS = "resetSuccess";
    public static final String RETURN_SUCCESS = "returnSuccess";
    public static final String SEARCH = "search";
    public static final String SEARCH_FAILURE = "searchFailure";
    public static final String SEARCH_SUCCESS = "searchSuccess";
    public static final String SEARCH_NORESULTS = "searchNoResults";
    public static final String SELECT_SUCCESS = "selectSuccess";
    public static final String SKIP_TO_ADD_USER = "skipToDisplayworkgroupAddUser";
    public static final String SUCCESS_HOME = "successHome";
    public static final String SORT_SUCCESS = "sortSuccess";
    public static final String SPECIAL_CONDITION_SUCCESS = "specialConditionSuccess";
    public static final String SPN = "SPN";
    public static final String SUCCESS = "success";
    public static final String ADD_JUVENILE_SUCCESS = "addJuvenileSuccess";
    public static final String PROBATIONDATESSUCCESS = "probationdatesuccess";
    public static final String DUALSUCCESS = "dualsuccess";
    public static final String SUBMIT = "submit";
    public static final String SUBMIT_SUCCESS = "submitSuccess";
    public static final String SUMMARY = "summary";
    public static final String SUMMARY_DUAL = "summarydual";
    public static final String SUMMARY_SUCCESS = "summarySuccess";
    public static final String SUMMARY_FAILURE = "summaryFailure";
    public static final String SUSPEND = "suspend";
    public static final String UPDATE = "update";
    public static final String UPDATE_CONITNUE = "updateContinue";
    public static final String UPDATE_CONFIRM = "updateConfirm";
    public static final String UPDATE_CONFIRM_SUCCESS = "updateConfirmSuccess";
    public static final String UPDATE_LIST = "updateList";
    public static final String UPDATE_FAILURE = "updateFailure";
    public static final String UPDATE_PRETRIAL_SUCCESS = "updatePretrialSuccess";
    public static final String UPDATE_RESET = "updateReset";
    public static final String UPDATE_SUCCESS = "updateSuccess";
    public static final String UPDATE_SUMMARY = "updateSummary";
    public static final String UPDATE_SSN = "updateSSN";
    public static final String UPDATE_COMMENT = "updateComment";

    public static final String VALIDATE_SUCCESS = "validateSuccess";
    public static final String VALIDATE_FAILURE = "validateFailure";
    public static final String VALIDATE_DEPARTMENT_SUCCESS = "validateDepartmentSuccess";
    public static final String VALIDATE_DEPARTMENT_FAILURE = "validateDepartmentFailure";
    public static final String VALIDATE_JOT_DEPARTMENT_SUCCESS = "validateJotDepartmentSuccess";
    public static final String VALIDATE_SERVICE_DEPARTMENT_SUCCESS = "validateServiceDepartmentSuccess";
    public static final String VALIDATE_MANAGER_SUCCESS = "validateManagerSuccess";
    public static final String VALIDATE_MANAGER_FAILURE = "validateManagerFailure";
    public static final String VALIDATE_ORDER_DETAILS_FAILURE = "validateOrderDetailsFailure";
    public static final String VALIDATE_PRETRIAL_SUCCESS = "validatePretrialSuccess";
    public static final String VIEW = "view";
    public static final String VIEW_FAILURE = "viewFailure";
    public static final String VIEW_DELETE = "viewDelete";
    public static final String VIEW_DELETE_SUCCESS = "viewDeleteSuccess";
    public static final String VIEW_DETAIL = "viewDetail";
    public static final String VIEW_ONE_SUCCESS = "viewOneSuccess";
    public static final String VIEW_ORDER_SUCCESS = "viewOrderSuccess";
    public static final String VIEW_PRETRIAL_SUCCESS = "viewPretrialSuccess";
    public static final String VIEW_SAMPLE_SUCCESS = "viewSampleSuccess";
    public static final String VIEW_SUCCESS = "viewSuccess";
    public static final String VIEW_UPDATE = "viewUpdate";
    public static final String VIEW_UPDATE_SUCCESS = "viewUpdateSuccess";
    public static final String VIEW_HISTORY_SUCCESS = "viewHistorySuccess";
    public static final String RENEW_SUCCESS = "renewSuccess";
    public static final String RENEW = "renew";

    public static final String WITHDRAW = "withdraw";
    public static final String WITHDRAW_SUCCESS = "withdrawSuccess";
    public static final String DOCKET_SUCCESS = "docketSuccess";

    public static final String REASSIGN_DIVISION = "Reassign Division";
    //added for ER-GANG
    public static final String FINISH_SUCCESS = "finishSuccess";

    //added for ER_GANG TATTOO - 11051
    public static final String TATTOO_SUCCESS = "tattooSuccess";

    /* login/logout */
    public static final String UPDATEPASSWORD = "updatePassword";

    /*
     * Common Supervision
     */
    /* General CS Constants do not change */
    public static final boolean CS_ENABLE_MULTI_DEPT_PAGES = true; // this constant controls that all JUV/CSC/PTR pages in CS are split out true is they are split out.
    /* END General CS Constants */
    public static final String JUVENILE_COURT_CATEGORY = "JUV";
    public static final String COUNTY_CRIMINAL_COURT_CATEGORY = "CC";
    public static final String DISTRICT_COURT_CATEGORY = "CR";
    public static final String COND_ASSOC_COURT_POLICY = "asscCourtPolicy";
    public static final String COND_ASSOC_DEPT_POLICY = "asscDeptPolicy";
    public static final String COPY_SELECT_CONDITION_SUCCESS = "copySelectConditionSuccess";
    public static final String COPY_SELECT_COURT_SUCCESS = "copySelectCourtSuccess";
    public static final String COPY_SELECT_OFFENSE_SUCCESS = "copySelectOffenseSuccess";
    public static final String COPY_SEQUENCE_LIST_SUCCESS = "copySequenceListSuccess";
    public static final String CREATE_SELECT_CONDITION_SUCCESS = "createSelectConditionSuccess";
    public static final String CREATE_SELECT_OFFENSE_SUCCESS = "createSelectOffenseSuccess";
    public static final String CREATE_SELECT_COURT_SUCCESS = "createSelectCourtSuccess";
    public static final String CREATE_SEQUENCE_LIST_SUCCESS = "createSequenceListSuccess";
    public static final String SIGNATURE_ACQUIRED = "signatureAcquired";
    public static final String SIGNATURE_ACQUIRED_SUCCESS = "signatureAcquiredSuccess";
    public static final String NO_OFFENSE_SUCCESS = "noOffenseSuccess";
    public static final String OFFENSE_SUCCESS = "offenseSuccess";
    public static final String UPDATE_SELECT_CONDITION_SUCCESS = "updateSelectConditionSuccess";
    public static final String UPDATE_SELECT_OFFENSE_SUCCESS = "updateSelectOffenseSuccess";
    public static final String UPDATE_SELECT_COURT_SUCCESS = "updateSelectCourtSuccess";
    public static final String UPDATE_SEQUENCE_LIST_SUCCESS = "updateSequenceListSuccess";
    // DO NOT CHANGE THE RIGHT SIDE OF THE CONSTANTS IN THE SECTION BELOW AS THEY ARE TIED TO DATABASE ENTRIES for Variable Variable
    // validation and presentation
    //*************BEGIN*****************
    public static final String DISPLAY_PRESENTATION_TYPE_DATE_FORMAL = "FORMAL"; //MMMM DD, YYYY
    public static final String DISPLAY_PRESENTATION_TYPE_DATE_DEFAULT = "DEFAULT"; //MM/DD/YYYY
    public static final String DISPLAY_PRESENTATION_TYPE_DATE_REVERSE_FORMAL = "REVERSEFORMAL"; //DDxx day of MMMM, YYYY
    public static final String DISPLAY_PRESENTATION_TYPE_DATE_DAY_ONLY = "DAYONLY"; //DD
    public static final String DISPLAY_PRESENTATION_TYPE_DATE_DAY_N_SUFFIX = "DAYNSUFFIX"; //DDxx
    public static final String DISPLAY_PRESENTATION_TYPE_DATE_FULLMONTHNAME = "FULLMONTHNAME"; //MMMM
    public static final String DISPLAY_PRESENTATION_TYPE_DATE_YEAR_ONLY = "YEARONLY"; //YYYY
    public static final String DISPLAY_PRESENTATION_TYPE_CURRENCY_DEFAULT = "DEFAULT"; //$XXX.XX
    public static final String DISPLAY_PRESENTATION_TYPE_CURRENCY_COMMAS = "COMMAS"; //$X,XXX.XX
    public static final String DISPLAY_PRESENTATION_TYPE_CURRENCY_NOCOMMAS = "NOCOMMAS"; //$XXXX.XX
    public static final String DISPLAY_PRESENTATION_TYPE_ALPHA_DEFAULT = "DEFAULT"; //AUIiop
    public static final String DISPLAY_PRESENTATION_TYPE_ALPHANUMERIC_SYMBOLS = "SYMBOLS"; //A5.8U
    public static final String DISPLAY_PRESENTATION_TYPE_ALPHANUMERIC_NOSYMBOLS = "NOSYMBOLS"; //A58U
    public static final String DISPLAY_PRESENTATION_TYPE_FREETEXT_DEFAULT = "DEFAULT"; //A58U&4$!
    public static final String DISPLAY_PRESENTATION_TYPE_NUMERIC_COMMAS = "COMMAS"; //X,XXX.XX
    public static final String DISPLAY_PRESENTATION_TYPE_NUMERIC_NOCOMMAS = "NOCOMMAS"; //XXXX.XX
    public static final String DISPLAY_PRESENTATION_TYPE_NUMERIC_DECIMAL_DEFAULT = "DECIMALDEFAULT"; //XXX,XXX.XXX
    public static final String DISPLAY_PRESENTATION_TYPE_TIME_12HOUR = "TIME_12HOUR"; //XX:XX
    public static final String DISPLAY_PRESENTATION_TYPE_TIME_24HOUR = "TIME_24HOUR"; //XX:XX
    public static final String DISPLAY_PRESENTATION_TYPE_TIME_12HOUR_AM_PM = "hh:mm a"; //XX:XX AM
    public static final String DISPLAY_PRESENTATION_TYPE_EnumeratedList = "Enumerated List"; //XX:XX
    public static final String DISPLAY_PRESENTATION_TYPE_PHONE_DEFAULT = "DEFAULT"; //XX:XX

    public static final String FORMAT_PRESENTATION_TYPE_DATE = "D"; //MMMM DD, YYYY
    public static final String FORMAT_PRESENTATION_TYPE_CURRENCY = "C"; //CURRENCY
    public static final String FORMAT_PRESENTATION_TYPE_ALPHANUMERIC = "Q"; //Alphanumeric
    public static final String FORMAT_PRESENTATION_TYPE_ALPHA = "A"; //Alpha
    public static final String FORMAT_PRESENTATION_TYPE_FREETEXT = "F"; //Free text
    public static final String FORMAT_PRESENTATION_TYPE_NUMERIC = "N"; //Numeric
    public static final String FORMAT_PRESENTATION_TYPE_INTEGER = "I"; //Integer
    public static final String FORMAT_PRESENTATION_TYPE_TIME = "T"; //Time
    public static final String FORMAT_PRESENTATION_TYPE_ENUMERATED_LIST = "L"; //Enumerated List
    public static final String FORMAT_PRESENTATION_TYPE_PHONE = "P"; //Enumerated List

    //*************END******************

    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String VARIABLE_ELEMENT_NAME_COMMENTS = "Comments";
    public static final String VARIABLE_ELEMENT_NAME_CLAIMAINT_ADDRESS = "NoContactComplAddress";

    /*
    * Administer Service Provider
    */
    public static final String SERVICEPROVIDER_BILLINGADDRESS_TYPE = "ALT";
    public static final String SERVICEPROVIDER_MAILINGADDRESS_TYPE = "MAL";
    /*
     * Manage Agency Constants
     */
    public static final String ADD_CONTACT = "addContact";
    public static final String ADD_DIVISION = "addDivision";
    public static final String MANAGE_CONTACT_SUCCESS = "manageContactSuccess";
    public static final String MANAGE_CONTACTS = "manageContacts";
    public static final String MANAGE_DIVISION_SUCCESS = "manageDivisionSuccess";
    public static final String MANAGE_DIVISIONS = "manageDivisions";
    public static final String REMOVE_CONTACT = "removeContact";
    public static final String REMOVE_DIVISION = "removeDivision";
    public static final String REMOVE_CONTACT_SUCCESS = "removeContactSuccess";
    public static final String REMOVE_DIVISION_SUCCESS = "removeDivisionSuccess";
    public static final String CONFIRM_ADD_YOUTH = "confirmAddYouth";

    /*
     * Manage Code Table Records Constants
     */
    public static final String CANCEL_CONFIRM = "cancelConfirm";
    public static final String CANCEL_MODIFY = "cancelModify";
    public static final String REMOVE_SELECTED = "removeSelected";

    /*
     * Manage Role Constants
     */
    public static final String AGENCIES = "agencies";
    public static final String AVAILABLE_ROLES = "availableRoles";
    public static final String ASSOCIATED_ROLES = "associatedRoles";
    public static final String ASSOCIATE_SYSTEM_ACTIVITIES = "associateSystemActivities";
    public static final String ASSOCIATE_SYSTEM_ACTIVITIES_SUCCESS = "associateSystemActivitiesSuccess";
    public static final String CREATE_REMOVE_AGENCY_SUCCESS = "createRemoveAgencySuccess";
    public static final String UPDATE_REMOVE_AGENCY_SUCCESS = "updateRemoveAgencySuccess";
    public static final String DISASSOCIATE_SYSTEM_ACTIVITIES = "disassociateSystemActivities";
    public static final String DISASSOCIATE_SYSTEM_ACTIVITIES_SUCCESS = "disassociateSystemActivitesSuccess";
    public static final String MA_COPY_SUCCESS = "maCopySuccess";
    public static final String MA_CREATE_SUCCESS = "maCreateSuccess";
    public static final String ROLETYPE_PRIVATE = "PRIVATE";
    public static final String ROLETYPE_PUBLIC = "PUBLIC";
    public static final String ROLETYPE_SHARED = "SHARED";
    public static final String ROLE_GROUP_NAME = "roleGroupName";
    public static final String ROLE_TYPES = "roleTypes";
    public static final String SA_COPY_SUCCESS = "saCopySuccess";
    public static final String SA_CREATE_SUCCESS = "saCreateSuccess";
    public static final String VIEW_ASSOCIATED_ACTIVITIES = "viewAssociatedActivities";
    public static final String SA_ROLETYPE = "SA";
    public static final String MA_ROLETYPE = "MA";
    public static final String ASA_ROLETYPE = "ASA";
    public static final String ROLETYPE_CREATEDBY_SA = "CREATE_BY_SA";
    public static final String ROLETYPE_CREATEDBY_MA = "CREATE_BY_MA";

    //	Manage Officer Constants
    public static final String SEARCH_MANAGE_PROFILE_SUCCESS = "searchManageProfileSuccess";
    public static final String FIND_MANAGE_PROFILE_DEPARTMENT_SUCCESS = "findManageProfileDepartmentSuccess";
    public static final String SELECT_DEPARTMENT_SUCCESS = "selectDepartmentSuccess";
    public static final String SELECT_MANAGER_SUCCESS = "selectManagerSuccess";

    //	Manage Department Constants
    public static final String COPY_DEPT_SUCCESS = "copyDeptSuccess";
    public static final String CONTACT_CREATE = "contactCreate";
    public static final String CONTACT_DELETE = "contactDelete";
    public static final String CONTACT_LIST_SUCCESS = "contactListSuccess";
    public static final String CONTACT_MODIFY = "contactModify";
    public static final String CREATE_CONTACT = "createContact";
    public static final String CREATE_CONTACT_FAILURE = "createContactFailure";
    public static final String CREATE_CONTACT_SUCCESS = "createContactSuccess";
    public static final String CREATE_DEPT_SUCCESS = "createDeptSuccess";
    public static final String DEPT_CREATE = "deptCreate";
    public static final String DEPT_COPY = "deptCopy";
    public static final String DEPT_DELETE = "deptDelete";
    public static final String DEPT_UPDATE = "deptUpdate";
    public static final String DEPT_VIEW = "deptView";
    public static final String DELETE_CONTACT_SUCCESS = "deleteContactSuccess";
    public static final String DELETE_DEPT_SUCCESS = "deleteDeptSuccess";
    public static final String FIND_DEPT_SUCCESS = "findDeptSuccess";
    public static final String MODIFY_CONTACT_SUCCESS = "modifyContactSuccess";
    public static final String UPDATE_CONTACT_SUCCESS = "updateContactSuccess";
    public static final String UPDATE_CONTACT_FAILURE = "updateContactFailure";
    public static final String UPDATE_DEPT_SUCCESS = "updateDeptSuccess";
    public static final String VIEW_CONTACT_SUCCESS = "viewContactSuccess";
    public static final String VIEW_DEPARTMENT_SUCCESS = "viewDepartmentSuccess";

    // Manage User Profile Constants
    //public static final String INFORMATION = "information";
    public static final String ASPCSC_PROGRAM_FORM = "cscServiceProviderProgramForm";
    public static final String ASPCSC_SP_FORM = "cscServiceProviderForm";
    public static final String ASPCSC_SEARCHSP_FORM = "cscServiceProviderSearchForm";
    public static final String ASPCSC_CONTACT_FORM = "cscServiceProviderContactForm";
    public static final String USERPROFILE_FORM = "userProfileForm";
    public static final String DUPLICATES = "duplicateSuccess";
    public static final String MAINTAIN_ROLES = "maintainRolesSuccess";
    public static final String TRANSFER_AGENCY = "transferAgencySuccess";
    public static final String SEARCH_DEPT_SUCCESS = "searchDepartmentSuccess";
    public static final String TRANSFER_SUCCESS = "transferSuccess";
    public static final String VALIDATE_DEPT_SUCCESS = "validateDepartmentSuccess";
    public static final String VALIDATE_DEPT_FAILURE = "validateDepartmentFailure";
    public static final String SELECT_DEPT_SUCCESS = "selectDepartmentSuccess";
    public static final String LIST_SUCCESS = "listSuccess";
    public static final String VOP = "vop";
    public static final String VOP_CREATE = "vopCreate";
    public static final String VOP_ADD_DETAILS = "vopAddDetails";

    // Manage Juvenile Warrant Constants
    public static final String ARR_ASSOCIATE = "arr";
    public static final String ACTARR = "actARR";
    public static final String ACTARR_SUCCESS = "actARRSuccess";
    public static final String ACTDTA = "actDTA";
    public static final String ACTDTA_SUCCESS = "actDTASuccess";
    public static final String ACTVOP = "actVOP";
    public static final String ACTVOP_LISTSUCCESS = "actVOPListSuccess";
    public static final String ACTVOP_SUMMARY = "actVOPSummary";
    public static final String ACTVOP_SUCCESS = "actVOPSuccess";
    public static final String ACTVOP_CONFIRM = "actVOPConfirm";
    public static final String ARR_SUCCESS = "arrSuccess";
    public static final String DTA_SUCCESS = "dtaSuccess";
    public static final String INACTIVATE = "inactivate";
    public static final String INACTIVATE_SUCCESS = "inactivateSuccess";
    public static final String INACTIVATE_SUMMARY = "inactivateSummary";
    public static final String INACTIVATE_CONFIRM = "inactivateConfirm";
    public static final String INVALID_LAW_ENFORCEMENT = "invalidLawEnforcement";
    public static final String JJS = "JJS";
    public static final String JOT = "JOT";
    public static final String GENERATE = "generate";
    public static final String LAW_ENFORCEMENT = "lawEnforcementInfo";
    public static final String MANAGE_ADDRESS_SUCCESS = "manageAddressSuccess";
    public static final String MANAGE_ADDRESSES = "manageAddresses";
    public static final String OIC_SUCCESS = "oicSuccess";
    public static final String PC_SUCCESS = "pcSuccess";
    public static final String PROCESS_RETURN = "processReturn";
    public static final String PROCESS_RETURN_SUCCESS = "processReturnSuccess";
    public static final String RECALL = "recall";
    public static final String RECALL_SUCCESS = "recallSuccess";
    public static final String RELEASE_DECISION = "releaseDecision";
    public static final String RELEASE_DECISION_SUCCESS = "releaseDecisionSuccess";
    public static final String RELEASE_TOJP = "releaseToJP";
    public static final String RELEASE_TOJP_SUCCESS = "releaseToJPSuccess";
    public static final String RELEASE_TOPERSON = "releaseToPerson";
    public static final String RELEASE_TOPERSON_SUCCESS = "releaseToPersonSuccess";
    public static final String REQACKDTA = "reqAckDTA";
    public static final String REQACKDTA_LISTSUCCESS = "reqAckDTAListSuccess";
    public static final String REQACKDTA_SUCCESS = "reqAckDTASuccess";
    public static final String REQSIGOIC = "reqSigOIC";
    public static final String REQSIGOIC_LISTSUCCESS = "reqSigOICListSuccess";
    public static final String REQSIGOIC_SUCCESS = "reqSigOICSuccess";
    public static final String REQSIGOICCONFIRM_SUCCESS = "OICSignatureConfirm";
    public static final String RETURN_SIGNATURE_STATUS = "retSigStatus";
    public static final String RETURN_SIGNATURE_STATUS_SUCCESS = "retSigStatusSuccess";

    public static final String UPDATE_OIC = "updateOIC";
    public static final String UPDATE_OIC_SUCCESS = "updateOICSuccess";
    public static final String UPDATE_OIC_CONFIRM = "updateOICConfirm";
    public static final String VOP_SUCCESS = "vopSuccess";
    public static final String WARRANT_NUM = "warrantNum";
    public static final String WARRANT_SERVICE = "warrantService";
    public static final String WARRANT_SERVICE_SUCCESS = "warrantServiceSuccess";
    public static final String WARRANT_TYPE_UI = "warrantTypeUI";
    public static final String UPDATE_VOP = "updateVOP";
    public static final String UPDATE_VOP_SUCCESS = "updateVOPSuccess";
    public static final String UPDATE_VOP_CONFIRM = "updateVOPConfirm";

    // Manage Security Administrator Constants	 
    public static final String ADD_FEATURES = "addFeatures";
    public static final String ALL_SECURITIES_ADMINISTRATORS = "All Security Admin";
    public static final String EVERYONE = "Everyone";
    public static final String CREATE_FEATURE_FAILURE = "createFeatureFailure";
    public static final String CREATE_FEATURE_SUCCESS = "createFeatureSuccess";
    public static final String CREATE_REFRESH_SUCCESS = "createRefreshSuccess";
    public static final String CREATE_REMOVE_FEATURE_SUCCESS = "createRemoveFeatureSuccess";
    public static final String FEATURE_SUCCESS = "featureSuccess";
    public static final String REMOVE_FEATURES = "removeFeatures";
    public static final String UPDATE_AGENCY_SUCCESS = "updateAgencySuccess";
    public static final String CREATE_AGENCY_SUCCESS = "createAgencySuccess";
    public static final String UPDATE_FEATURE_FAILURE = "updateFeatureFailure";
    public static final String UPDATE_FEATURE_SUCCESS = "updateFeatureSuccess";
    public static final String UPDATE_REFRESH_SUCCESS = "updateRefreshSuccess";
    public static final String UPDATE_REMOVE_FEATURE_SUCCESS = "updateRemoveFeatureSuccess";

    // Manage SA users	 
    public static final String SUCCESS_SUMMARY = "successSummary";

    // Manage User Groups Constants
    public static final String ASSIGN_ROLES_SUCCESS = "assignRolesSuccess";
    public static final String FIND_AGENCIES_SUCCESS = "findAgencySuccess";
    public static final String FIND_USERS_SUCCESS = "findUserSuccess";
    public static final String MA_SUCCESS = "maSuccess";
    public static final String SA_SUCCESS = "saSuccess";
    public static final String UPDATE_USERS_SUCCESS = "updateUsersSuccess";
    public static final String UPDATE_REMOVE_USER_SUCCESS = "updateRemoveUserSuccess";
    public static final String UPDATE_USER_REMOVE_SUCCESS = "updateUserRemoveUserSuccess";
    public static final String CREATE_REMOVE_USER_SUCCESS = "createRemoveUserSuccess";

    // Assign Roles Constants 	 
    public static final String ASSIGN_USER_GROUP_ROLES_FIND_SUCCESS = "findSuccess";
    public static final String ASSIGN_USER_GROUP_ROLES_ADD_SUCCESS = "addSuccess";
    public static final String ASSIGN_ROLE_REMOVE_SUCCESS = "removeSuccess";
    public static final String ASSIGN_USER_GROUP_ROLES_CONFIRM_SUCCESS = "confirmSuccess";
    public static final String ASSIGN_USER_ROLES_FIND_SUCCESS = "findSuccess";
    public static final String ASSIGN_USER_ROLES_ADD_SUCCESS = "addSuccess";
    public static final String ASSIGN_USER_ROLES_CONFIRM_SUCCESS = "confirmSuccess";
    public static final String SA_USER_TYPE = "SA";
    public static final String MA_USER_TYPE = "MA";

    // JMC REP Codes
    public static final String JMCREP = "JMC Rep";
    public static final String JMCREP_CODE = "Y";
    public static final String NON_JMCREP = "Non JMC Rep";
    public static final String NON_JMCREP_CODE = "N";

    // Manage Juvenile Casefile Constants	 
    public static final String NEW_ASSESSMENT = "newAssessment";
    public static final String MAYSI_TEXT_FILE = "maysiTextFile";
    public static final String UPDATE_CLOSING = "updateClosing";
    public static final String NO_CASEFILE = "NO CASEFILE";
    public static final String MAYSI_MENTAL_HEALTH_REPORT_CODE = "MMH";

    //MAYSI ASSESSMENT OPTION CONSTANTS
    public static final String NEW_MAYSI_ASSESSMENT_OPTION = "NEW";
    public static final String SUBSEQUENT_NEEDED_ASSESSMENT_OPTION = "SUBSEQUENT NEEDED";
    public static final String SUBSEQUENT_NOT_NEEDED_ASSESSMENT_OPTION = "SUBSEQUENT NOT NEEDED";
    public static final String SUBSEQUENT_DONE_ASSESSMENT_OPTION = "SUBSEQUENT DONE";

    //CASEFILE ACTIVITY CONSTANTS
    public static final String PROFILE_ACTIVITY_LIST_SUCCESS = "profileActivityListSuccess";
    public static final String CASEFILE_ACTIVITY_LIST_SUCCESS = "casefileActivityListSuccess";
    public static final String ACTIVITY_CODE_POF = "POF";
    public static final String ACTIVITY_CODE_EVS = "EVS";
    public static final String ACTIVITY_CODE_DOR = "DOR";
    public static final String ACTIVITY_TYPE_CASE_MANAGEMENT = "CSM";
    public static final String ACTIVITY_CATEGORY_REPORTING = "REP";

    // CASEFILE STATUS
    public static final String CASEFILE_CASE_STATUS_PENDING = "P";
    public static final String CASEFILE_CASE_STATUS_ACTIVE = "A";
    public static final String CASEFILE_CASE_STATUS_CLOSING_PENDING = "CP";
    public static final String CASEFILE_CASE_STATUS_CLOSING_SUBMITTED = "CS";
    public static final String CASEFILE_CASE_STATUS_CLOSING_APPROVED = "CA";
    public static final String CASEFILE_CASE_STATUS_CLOSED = "C";
    public static final String CASEFILE_CASE_STATUS_ACTIVE_DESCRIPTION = "ACTIVE";
    public static final String CASEFILE_CASE_STATUS_PENDING_DESCRIPTION = "PENDING";
    public static final String CASEFILE_CASE_STATUS_CLOSED_DESCRIPTION = "CLOSED";

    public static final String DISPOSITION_TYPE_CODE_DEFERRED = "DADJ";
    public static final String DISPOSITION_TYPE_CODE_PROBATION = "PROB";
    public static final String DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION = "PTIN";

    //CASEFILE CATEGORIES BUG FIX 13556
    public static final String POST_ADJUDICATION_COMMUNITY = "AC";
    public static final String PRE_ADJUDICATION = "AD";
    public static final String POST_ADJUDICATION_RESIDENTIAL = "AR";
    public static final String DEFERRED_ADJUDICATION = "DA";
    public static final String PRE_PETITION = "PP";

    // Casework View Calendar
    public static final String NAME_LIST_SUCCESS = "nameListSuccess";
    public static final String USER_ENTERED_CUSTOM_ADDRESS = "-1";

    // CAsework RULE STATUS CODE TABLE CONSTANTS
    public static final String RULE_STATUS_INACTIVE = "I";
    public static final String RULE_STATUS_COMPLETE = "C";
    public static final String RULE_STATUS_NON_COMPLIANT = "N";
    public static final String RULE_STATUS_ACTIVE = "A";

    // SUPERVISION CATEGORIES
    public static final String CASEFILE_SUPERVISION_CAT_PRE_PETITION = "PP";
    public static final String CASEFILE_SUPERVISION_CAT_PRE_ADJ = "AD";
    public static final String CASEFILE_SUPERVISION_CAT_POST_ADJ_COM = "AC";
    public static final String CASEFILE_SUPERVISION_CAT_POST_ADJ_RES = "AR";
    public static final String CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ = "DA";
    public static final String CASEFILE_SUPERVISION_CAT_INDIRECT = "ID";

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
    public static final String CASEFILE_SUPERVISION_TYPE_MENTAL_HEALTH_SUPERVISION = "MHS";
    public static final String CASEFILE_SUPERVISION_TYPE_GANG_CASELOAD_SUPERVISION = "GCS";
    public static final String CASEFILE_SUPERVISION_TYPE_SEX_OFFENDER_SUPERVISION = "SXS";
    public static final String CASEFILE_SUPERVISION_TYPE_DRUG_OFFENDER_SUPERVISION = "DOS";
    public static final String CASEFILE_SUPERVISION_TYPE_INTERIM_SUPERVISION = "CSS";

    // Security Inquiries Constants
    public static final String AGENCY_SUCCESS = "agencySuccess";
    public static final String AGENCY_LIST_SUCCESS = "agencyListSuccess";
    public static final String EMPLOYEE_ID_SUCCESS = "employeeIdSuccess";
    public static final String ROLE_SUCCESS = "roleSuccess";
    public static final String ROLE_LIST_SUCCESS = "roleListSuccess";
    public static final String SUBSYSTEM_SUCCESS = "subsystemSuccess";
    public static final String SUBSYSTEM_LIST_SUCCESS = "subsystemListSuccess";
    public static final String USERGROUP_SUCCESS = "userGroupSuccess";
    public static final String USERGROUP_LIST_SUCCESS = "userGroupListSuccess";
    public static final String USERPROFILE_SUCCESS = "userProfileSuccess";
    public static final String USERPROFILE_LIST_SUCCESS = "userProfileListSuccess";

    // JIMS2 Account Constants
    public static final String BASIC_USER_CANCEL = "basicUserCancel";
    public static final String BASIC_USER_FAILURE = "basicUserFailure";
    public static final String BASIC_USER_UPDATE_SUCCESS = "basicUserUpdateSuccess";
    public static final String DETAIL_SUCCESS = "detailSuccess";
    public static final String GENERIC_SUCCESS = "genericSuccess";
    public static final String INACTIVATE_SELECT_SUCCESS = "inactiveSelectSuccess";
    public static final String OFFICER_PROFILE_SUCCESS = "officerProfileSuccess";
    public static final String NONGENERIC_SUCCESS = "nonGenericSuccess";
    public static final String SERVICE_PROVIDER_SUCCESS = "serviceProviderSuccess";
    public static final String UPDATE_SELECT_SUCCESS = "updateSelectSuccess";

    // Constants for Shopping Cart
    public static final String ROLE_RESPONSE_EVENT = "messaging.security.reply.RoleResponseEvent";
    public static final String ROLE_RESPONSE_EVENT_ID = "getRoleId";
    public static final String AGENCY_RESPONSE_EVENT = "messaging.contact.agency.reply.AgencyResponseEvent";
    public static final String AGENCY_RESPONSE_EVENT_ID = "getAgencyId";
    public static final String FEATURE_RESPONSE_EVENT = "messaging.security.reply.FeaturesResponseEvent";
    public static final String FEATURE_RESPONSE_EVENT_ID = "getFeatureId";
    public static final String USER_RESPONSE_EVENT = "messaging.contact.user.reply.UserResponseEvent";
    public static final String USER_RESPONSE_EVENT_ID = "getLogonId";
    public static final String USERGROUP_RESPONSE_EVENT = "messaging.security.reply.UserGroupResponseEvent";
    public static final String USERGROUP_RESPONSE_EVENT_ID = "getUserGroupId";
    public static final String CONTRACT_RESPONSE_EVENT = "messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent";
    public static final String CONTRACT_RESPONSE_EVENT_ID = "getContractId";
    public static final String QUESTION_RESPONSE_EVENT = "messaging.riskanalysis.reply.GetQuestionResponseEvent";
    public static final String QUESTION_RESPONSE_EVENT_ID = "getQuestionId";

    // Response status codes
    public static final int SYSTEM_EXEPTION_RESULT = -1;
    public static final int APPLICATION_EXCEPTION_RESULT = 0;
    public static final int SUCCESS_RESULT = 1;

    public static final int ZERO_RESULTS = 0;
    public static final int SINGLE_RESULT = 1;
    public static final int MULTIPLE_RESULTS = 2;

    public static final String ERROR_EVENT = "errorEvent";
    public static final String PAGE_NAME = "pageName";

    // DAY NAME
    public static final String SUNDAY = "SUN";
    public static final String MONDAY = "MON";
    public static final String TUESDAY = "TUE";
    public static final String WEDNESDAY = "WED";
    public static final String THURSDAY = "THU";
    public static final String FRIDAY = "FRI";
    public static final String SATURDAY = "SAT";

    //	 Currency Format Constants
    public static final String CURRENCY_US_DEFAULT_POSITIVE_FORMAT = "###,###,###,##0.00";
    public static final String CURRENCY_US_DEFAULT_NEGATIVE_FORMAT = "-###,###,###,##0.00";
    public static final String CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT = "############0.00";
    public static final String CURRENCY_US_NOCOMMAS_NEGATIVE_FORMAT = "-############0.00";

    //	 Decimal Number Format Constants
    public static final String DECIMAL_DEFAULT_DECIMAL_FORMAT = "###,###,###,###.############";

    // Whole Number Format Constants
    public static final String NUMBER_DEFAULT_POSITIVE_FORMAT = "###,###,###,###";
    public static final String NUMBER_NOCOMMAS_POSITIVE_FORMAT = "#########";
    public static final String NUMBER_DEFAULT_NEGATIVE_FORMAT = "-###,###,###,###";
    public static final String NUMBER_NOCOMMAS_NEGATIVE_FORMAT = "-############";

    // Date Format Constants -- 
    public static final String DATE_FMT_1 = "MM/dd/yyyy";
    public static final String DATE_FMT_FORMAL = "MMMM dd, yyyy";
    public static final String DATE_FMT_FORMALWSUFFIX = "MMMM ddxx, yyyy";
    public static final String DATE_FMT_REVERESED_FORMAL = "dd'xx day of 'MMMM,yyyy";
    public static final String DATE_FMT_FULLMONTH_YEAR = "MMMM, yyyy";
    public static final String DATE_FMT_DAY_ONLY = "dd";
    public static final String DATE_FTM_DAY_PLUS_SUFFIX = "dd'xx'"; // this is the date format but is used and should be appended to the day suffix i.e. 1st, 2nd , 3rd, 11th
    public static final String DATE_FMT_YEAR_ONLY = "yyyy";
    public static final String DATE_FMT_MONTH_ONLY_FULL_NAME = "MMMM";
    public static final String DATETIME_FMT_1 = "MM/dd/yyyy hh:mm";
    public static final String DATETIME_FMT_1AMPM = "MM/dd/yyyy hh:mm a";
    public static final String DATETIME24_FMT_1 = "MM/dd/yyyy HH:mm";
    public static final String DATETIME24_FMT_2 = "MM/dd/yyyy HH:mm:ss";
    public static final String DATETIME24LOOSE_FMT_1 = "M/d/yyyy HH:mm";
    public static final String TIME24_FMT_1 = "HH:mm";
    public static final String TIME_FMT_1 = "hh:mm";
    public static final String TIME_FMT_1AMPM = "hh:mm a";

    //Agency Names - Used by Common Supervision use-case
    public static final String PTR = "PTR"; //PreTrial
    public static final String JUV = "JUV"; //HCJPD
    public static final String CSC = "CSC"; //HCJPD
    public static final String CODE_DELIM = "##";
    public static final String HCOUNTY = "HC";//HCOUNTY default jurisdiction during create

    // Manage Contract 
    public static final String MANAGE_CONTRACT_SUCCESS = "manageContractSuccess";
    public static final String CREATE_SERVICE = "createService";
    public static final String UPDATE_SERVICE = "updateService";
    public static final String VERIFICATION_FAILURE = "verificationFailure";
    public static final String NONEW_ASSIGNMENT_SUCCESS = "noNewAssignmentSuccess";
    public static final String DROP_ASSIGNMENT_SUCCESS = "dropAssignmentSuccess";

    public static final String ACTIVE_STATUS_ID = "A";
    public static final String CLOSED_STATUS_ID = "C";
    public static final String SUBMITTED_STATUS_ID = "S";
    public static final String ACCEPTED_STATUS_ID = "A";
    public static final String INACTIVE_STATUS_ID = "I";
    public static final String OPEN_STATUS_ID = "O";

    //Service event
    public static final String PRESCHEDULED_SERVICE_TYPE = "P";
    public static final String NONINTERVIEW_SERVICE_TYPE = "N";
    public static final String INTERVIEW_SERVICE_TYPE = "I";
    public static final String DOCKET_SERVICE_TYPE = "D";

    public static final String HOME_VISIT = "HDV";
    public static final String HOME_DIAGNOSTIC_VISIT = "HDS";
    public static final String JOB_VISIT = "JOB";
    public static final String CURFEW_CHECK = "CVI";
    public static final String FACE_TO_FACE_CURFEW_CHECK = "CVF";
    public static final String PHONE_CURFEW_CHECK = "CFP";
    public static final String INTERVIEW = "INT";
    public static final String SCHOOL_VISIT = "CVS";
    public static final String PLACEMENT_VISIT = "PVT";
    public static final String FACILITY_PARENT_ORIENTATION = "FPO"; //12253 changes
    public static final String COURT_DATE = "DOC";
    public static final String DETENTION_HEARING = "DHG";
    public static final String SERVICE_TYPE = "SERVICE_TYPE";
    public static final String INTERVIEW_UNSCHEDULED_CODE = "INX";
    public static final String INTERVIEW_SCHEDULED = "INT";
    public static final String REPORTING = "REP";
    public static final String SCHOOL_ADJUDICATION = "SAN";
    public static final String MENTAL_HEALTH_EVALUATION = "MHE";
    public static final String CLOSING_LETTER = "CLL";
    public static final String CLOSING_PACKET = "CLP";
    public static final String APPOINTMENT_LETTER = "APL";

    public static final String OTHER_COURT_ORDER = "OCO";
    public static final String OTHER_DOCUMENTS = "ODS";

    public static final String VIEW_DOCUMENT_ATTENDANCE = "documentAttendance";
    public static final String CANCEL_EVENT = "cancelEvent";

    // FORMS NEEDED IN THE UI 

    public static final String ACTIVITIES_FORM = "activitiesForm";
    public static final String JUVENILE_TRAITS_FORM = "juvenileTraitsForm";
    public static final String JUVENILE_DRUG_FORM = "juvenileDrugForm";
    //ER_GANG-JIMS200074578 STARTS
    public static final String JUVENILE_GANG_FORM = "juvenileGangsForm";
    //ER_GANG-JIMS200074578 ENDS
    public static final String CASEFILE_HEADER_FORM = "juvenileCasefileForm";
    public static final String SUPERVISEE_HEADER_FORM = "superviseeHeaderForm";
    public static final String SUPERVISEE_SEARCH_FORM = "superviseeSearchForm";
    public static final String SUPERVISEE_FORM = "superviseeForm";

    // Constants - Manage Associate Usecase ;
    public static final String ASSOCIATE_CREATE_SUCCESS = "associateCreateSuccess";
    public static final String ASSOCIATE_FORM = "associateForm";
    public static final String CANCEL_CASELOAD = "cancelCaseload";
    public static final String CANCEL_CASENOTES = "cancelCasenotes";
    public static final String CASELOAD_SUCCESS = "caseloadSuccess";
    public static final String CANCEL_FIELD_RESULTS = "cancelFieldResults";
    public static final String COPY_TO_PRIMARY_ADDRESS = "copyToPrimaryAddress";
    public static final String COPY_TO_OTHER_ADDRESS = "copyToOtherAddress";
    public static final String CREATE_CASENOTES_SUCCESS = "createCasenotesSuccess";
    public static final String FIELD_RESULTS_SUCCESS = "fieldResultsSuccess";
    public static final String FROM_CASENOTES = "fromCasenotes";
    public static final String FROM_CASELOAD = "fromCaseload";
    public static final String FROM_COMPLIANCE_RESOLVE_CASENOTE = "fromComplianceResolveCasenote";
    public static final String FROM_COMPLIANCE_SET_TO_NONCOMPLIANT_CASENOTE = "fromComplianceSetToNoncompliantCasenote";
    public static final String FROM_COMPLIANCE_CREATE_CASENOTE = "fromComplianceCreateCasenote";
    public static final String FROM_FIELD_RESULTS = "fromFieldResults";
    public static final String COMPLIANCE_RESOLVE_CASENOTE_SUCCESS = "complianceResolveCasenoteSuccess";
    public static final String COMPLIANCE_SET_NONCOMPLIANT_CASENOTE_SUCCESS = "complianceSetToNoncompliantCasenoteSuccess";
    public static final String COMPLIANCE_CREATE_CASENOTE_SUCCESS = "complianceCreateCasenoteSuccess";
    public static final String ADMIN_STAFF_SEARCH_FORM = "adminStaffSearchForm";
    public static final String ADMIN_STAFF_FORM = "adminStaffForm";
    public static final String ADMIN_STAFF_REPORT_FORM = "adminStaffReportForm";

    //Benefits assessment constants
    public static final String OVER_7_DAYS = "over7days";
    public static final String OVER_30_DAYS = "over30days";

    //Journal constants
    public static final String COMMUNITY_ANALYSIS_COMMENTS = "Community Supervision Comments";
    public static final String RESIDENTIAL_ANALYSIS_COMMENTS = "PO Impressions Needs";
    public static final String PROGRESS_ANALYSIS_COMMENTS = "Additional comments about the child's attitude, compliance, or behavior during this evaluation period";
    public static final String PROGRESS_ANALYSIS_SUPERVISION_MONTHS = "Supervision Month(s)";
    public static final String PROGRESS_ANALYSIS_SUPERVISION_LEVEL = "Supervision Level";
    public static final String GANG_RISK_ANALYSIS_COMMENTS = "Notes/Comments";

    // Manage CS Tasks constants
    public static final String TASK_LIST_TYPE_ACTIONLIST = "AL";
    public static final String TASK_LIST_TYPE_WORKGROUP = "WG";
    public static final String TASK_LIST_TYPE_OFFICEVISIT = "OV";
    public static final String CONFIRMATION_MESSAGE_REQUEST_ATTRIBUTE_NAME = "confirmationMessageString";
    public static final String TRANSFER_WORKGROUP = "transferWorkgroup";
    public static final String TRANSFER_POSITION = "transferPosition";

    public static final String REASSIGN_DIVISION_CONFIRMATION_MESSAGE = "Supervisee's division successfully changed.";

    public static final String JUV_CONTACT_REL_PLACEMENT = "PL";

    public static final String ALLOCATE_SUPERVISEE_TO_SUPERVISOR_TASK_SUBJECT = "New order for supervision.";

    public static final String PAPER_FILE_RECEIVED = "paperFileReceived";

    // CS Referral Level of Care full description constants - values must be all CAPS	
    public static final String BASIC_LEVEL_OF_CARE_DESCRIPTION = "BASIC (B) SERVICE LEVEL: CONSISTS OF A NORMAL FAMILY SETTING THAT IS DESIGNED TO MAINTAIN OR IMPROVE THE CHILDS FUNCTIONING.";
    public static final String INTENSE_LEVEL_OF_CARE_DESCRIPTION = "INTENSE (I) SERVICE LEVEL: CONSISTS OF A HIGH DEGREE OF STRUCTURE TO LIMIT THE CHILDS ACCESS TO ENVIROMENTS AS NECESSARY TO PROTECT THE CHILD.  THE CAREGIVERS HAVE SPECICIALEZED TRAINING TO PROVIDE INTENSE THERAPEUTIC AND REHABILITATIVE SUPPORTS AND INTERVENTIONS WITH LIMITED OUTSIDE ACCESS.";
    public static final String MODERATE_LEVEL_OF_CARE_DESCRIPTION = "MODERATE (M) SERVICE LEVEL: CONSISTS OF A STRUCTURED SUPPORTIVE AND PREFERABLY FAMILY LIKE SETTING IN WHICH MOST ACTIVITIES ARE DESIGNED TO IMPROVE THE CHILDS FUNCTIONING.";
    public static final String SPECIALIZED_LEVEL_OF_CARE_DESCRIPTION = "SPECIALIZED (S) SERVICE LEVEL: CONSISTS OF A TREATMENT SETTING IN WHICH CAREGIVERS HAVE SPECIALIZED TRAINING TO PROVIDE THERAPEUTIC, REHABILITATIVE AND MEDICAL SUPPORT AND INTERVENTIONS.";

    //Offense Level Degree constants
    /*
     * if any of these consts are added or removed, 
     * update the Sring[] below, pretty please
     */
    public static final String OFFENSE_LEVEL_DEGREE_CF = "CF";
    public static final String OFFENSE_LEVEL_DEGREE_F1 = "F1";
    public static final String OFFENSE_LEVEL_DEGREE_F2 = "F2";
    public static final String OFFENSE_LEVEL_DEGREE_F3 = "F3";
    public static final String OFFENSE_LEVEL_DEGREE_JF = "JF";
    public static final String OFFENSE_LEVEL_DEGREE_MA = "MA";
    public static final String OFFENSE_LEVEL_DEGREE_MB = "MB";
    public static final String OFFENSE_LEVEL_DEGREE_SO = "SO";
    public static final String OFFENSE_LEVEL_DEGREE_AC = "AC";
    public static final String OFFENSE_LEVEL_DEGREE_MC = "MC";
    public static final String OFFENSE_LEVEL_DEGREE_CO = "CO";

    public static final String[] OFFENSE_LEVEL_STR_ARRAY = { OFFENSE_LEVEL_DEGREE_CF, OFFENSE_LEVEL_DEGREE_F1, OFFENSE_LEVEL_DEGREE_F2, OFFENSE_LEVEL_DEGREE_F3, OFFENSE_LEVEL_DEGREE_JF, OFFENSE_LEVEL_DEGREE_MA, OFFENSE_LEVEL_DEGREE_MB, OFFENSE_LEVEL_DEGREE_SO, OFFENSE_LEVEL_DEGREE_AC, OFFENSE_LEVEL_DEGREE_MC, OFFENSE_LEVEL_DEGREE_CO };

    // CS compliance constants
    public static final String RESOLVE_NONCOMPLIANT_SUCCESS = "resolveNoncompliantSuccess";
    public static final String SET_NONCOMPLIANT_SUCCESS = "setNoncompliantSuccess";
    public static final String CASENOTES_SUCCESS = "casenotesSuccess";
    public static final String CONDITION_CASENOTE_SUCCESS = "conditionCasenoteSuccess";
    public static final String DECREMENT_SUCCESS = "decrementSuccess";

    /*
     * CSCD NONCOMPLIANCE Constants - including Violation Reports and Case Summary
     */
    public static final String APPROVAL_SUCCESS = "approvalSuccess";
    public static final String APPROVE_SUCCESS = "approveSuccess";
    public static final String CASE_HISTORY_SUCCESS = "caseHistorySuccess";
    public static final String CASE_SUMMARY_SUCCESS = "caseSummarySuccess";
    public static final String CHANGE_SUCCESS = "changeSuccess";
    public static final String CREATE_TASK_SUCCESS = "createTaskSuccess";
    public static final String DRAFT_SUCCESS = "draftSuccess";
    public static final String FILE_SUCCESS = "fileSuccess";
    public static final String MAINTAIN_SUCCESS = "maintainSuccess";
    public static final String MOTIONS_SUCCESS = "motionsSuccess";
    public static final String TASK_SUCCESS = "taskSuccess";
    public static final String TASKFLOW_BACK = "taskFlowBack";
    public static final String TASKFLOW_CANCEL = "taskFlowCancel";
    public static final String TASKFLOW_FINISH = "taskFlowFinish";
    public static final String VIOLATION_REPORTS_SUCCESS = "violationReportsSuccess";
    public static final String VIOLATION_REPORT_LIST_SUCCESS = "violationReportListSuccess";

    //	CSC Administer Assessments Constants
    public static final String CSC_ASSESS_ASSESSMENT_FORM = "assessmentForm";
    public static final String CSC_ASSESS_WISC_ASSESSMENT_FORM = "wisconsinAssessmentForm";
    public static final String CSC_ASSESS_LSIR_ASSESSMENT_FORM = "lsirAssessmentForm";
    public static final String CSC_ASSESS_SCS_ASSESSMENT_FORM = "scsAssessmentForm";
    public static final String CSC_ASSESS_SCS_INTERVIEW_ASSESSMENT_FORM = "scsInterviewAssessmentForm";
    public static final String CSC_ASSESS_FORCE_FIELD_ASSESSMENT_FORM = "forceFieldAssessmentForm";

    public static final String ASSESSMENT_DETAILS = "assessmentDetails";
    public static final String ASSESSMENT_VERSION_DETAILS = "assessmentVersionDetails";

    public static final String CREATE_INITIAL_ASSESSMENT = "createInitialAssessment";
    public static final String CREATE_REASSESSMENT = "createReassessment";
    public static final String UPDATE_INITIAL_ASSESSMENT = "updateInitialAssessment";
    public static final String UPDATE_REASSESSMENT = "updateReassessment";
    public static final String DELETE_INITIAL_ASSESSMENT = "deleteInitialAssessment";
    public static final String DELETE_REASSESSMENT = "deleteReassessment";
    public static final String CREATE_UPDATE_SUCCESS = "createUpdateSuccess";

    public static final String CLASSIFICATION_TIE = "classificationTie";
    public static final String CLASSIFICATION_TIE_SUCCESS = "classificationTieSuccess";
    public static final String NO_CLASSIFICATION_TIE = "noClassificationTie";

    public static final String CREATE_UPDATE_CONFIRM_SUCCESS = "createUpdateConfirmSuccess";
    public static final String DELETE_CONFIRM_SUCCESS = "deleteConfirmSuccess";

    //	CSC Administer Supervision Plan Constants
    public static final String CSC_SUPERVISION_PLAN_FORM = "supervisionPlanForm";
    public static final String CREATE_UPDATE_COPY_SUCCESS = "createUpdateCopySuccess";
    public static final String SAVE_AS_DRAFT_SUCCESS = "saveAsDraftSuccess";

    //posttrial                                           
    public static final String VIEW_ACTIVE_CASES_SUCCESS = "viewActiveCasesSuccess";
    public static final String VIEW_ACTIVE_CASES_FAILURE = "viewActiveCasesFailure";
    public static final String VIEW_PROFILE_SUCCESS = "viewProfileSuccess";
    public static final String VIEW_PROFILE_FAILURE = "viewProfileFailure";

    //Caseload, reassign
    public static final String CS_REASSIGN_SUP = "CS-REASSIGN-SUP";
    public static final String CS_REASSIGN_CASE_CLO = "CS-REASSIGN-CASE-CLO";
    public static final String CS_REASSIGN_CASE_CSO = "CS-REASSIGN-CASE-CSO";
    public static final String CS_VIEW_CASELOAD = "CS-VIEW-CASELOAD";
    public static final String CS_REASSIGN_ADMIN = "CS-REASSIGN-ADMIN";
    public static final String CS_CASE_ASSIGNMENT_FORM = "caseAssignmentForm";

    //Administer Supervisee 
    public static final String VIEW_HISTORY_LOS = "viewHistoryLOSSuccess";
    public static final String ADD_LOS = "addLOSSuccess";
    public static final String LOS_CORRECT = "correctSuccess";
    public static final String LOS_DELETE = "viewSummarySuccess";
    public static final String VIEW_LOS_SUMMARY = "viewSummarySuccess";
    public static final String FINISH_LOS = "finishSuccess";
    public static final String SUPP_REPORT_SUCCESS = "suppReportsSuccess";
    public static final String VIEW_HISTORY_PROGRAM_TRACKER = "viewHistoryProgramTrackerSuccess";
    public static final String ADD_PROGRAM_TRACKER = "addProgramTrackerSuccess";
    public static final String REMOVE_PROGRAM_TRACKER = "removeProgramTrackerSuccess";
    public static final String PROGRAM_TRACKER_CORRECT = "correctProgramTrackerSuccess";
    public static final String PROGRAM_TRACKER_DELETE = "viewProgramTrackerSummarySuccess";
    public static final String VIEW_PROGRAM_TRACKER_SUMMARY = "viewSummarySuccess";
    public static final String FINISH_PROGRAM_TRACKER = "finishSuccess";
    public static final String ADD_DNA = "addDNASuccess";
    public static final String DNA_CORRECT = "correctDNASuccess";
    public static final String VIEW_DNA_SUMMARY = "viewSummarySuccess";
    public static final String FINISH_DNA = "finishSuccess";
    public static final String VIEW_DNA_HISTORY = "viewDnaHistory";
    public static final String VIEW_HISTORY_DNA = "viewHistoryDNASuccess";
    public static final String VIEW_DNA = "viewDNASuccess";

    //WorkFlow DashBoard
    public static final String POSITION_SUCCESS = "positionSuccess";
    public static final String WORKGROUP_SUCCESS = "workGroupSuccess";
    public static final String CANCELPOSITIONSUCESS = "cancelPositionSuccess";
    public static final String CANCELWORKGROUPSUCESS = "cancelWorkgroupSuccess";

    //Transfer Cases
    public static final String CS_TRANSFER_CASES = "CS-TRANSFER-CASES";
    public static final String CS_TRANSFER_IN_CASES = "CS-TRANSFER-IN-CASES";
    public static final String CS_TRANSFER_OUT_CASES = "CS-TRANSFER-OUT-CASES";
    public static final String CS_UPDATE_TRANSFER_CASES = "CS-UPDATE-TRANSFER-CASES";

    //	Generate Calendar Events Report 
    public static final String CS_CALENDAR_FIELD_VISIT_FORM = "csCalendarFVForm";
    public static final String CS_CALENDAR_OFFICE_VISIT_FORM = "csCalendarOVForm";
    public static final String CS_CALENDAR_OTHER_EVT_FORM = "csCalendarOtherForm";

    public static final String VIEW_FIELD_VST_DETAILS_SUCCESS = "viewFieldVisitSuccess";
    public static final String VIEW_OFFICE_VST_DETAILS_SUCCESS = "viewOfficeVisitSuccess";
    public static final String VIEW_GROUP_VST_DETAILS_SUCCESS = "viewGroupVisitSuccess";
    public static final String VIEW_OTHER_EVTS_DETAILS_SUCCESS = "viewOtherEventSuccess";

    //	Administer Program Referrals
    public static final String CS_PROGRAM_REFERRAL_SEARCH_FORM = "cscProgRefSearchForm";
    public static final String CS_PROGRAM_REFERRAL_FORM = "cscProgRefForm";
    public static final String CS_PROGRAM_REFERRAL_CASELOAD_FORM = "cscProgRefCaseloadForm";

    //	SPN Splits
    public static final String SUCCESS_CASE = "successCase";
    public static final String SUCCESS_SUPERVISEE = "successSupervisee";
    public static final String WISCONSIN_SUCCESS = "wisconsinSuccess";
    public static final String FORCEFIELD_SUCCESS = "forceFieldSuccess";
    public static final String LSIR_SUCCESS = "lsirSuccess";
    public static final String SCS_SUCCESS = "scsSuccess";
    public static final String ASSOCIATE_SUCCESS = "associateSuccess";
    public static final String SUPERVISION_SUCCESS = "supervisionSuccess";
    public static final String ASSESSMENT = "assessment";
    public static final String ASSOCIATE = "associate";
    public static final String LOS = "LOS";
    public static final String SUPERVISION_PLAN = "suspervisionPlan";

    //	Facility History
    public static final String FACILITY = "facilityHistory";
    public static final String FACILITY_POP_UP = "facilityPopUp";
    public static final String NO_FACILITY_RECEIPTS_FOUND_ERROR = "error.no.facilities.receipts.found";
    public static final String NO_JUVENILE_CASEFILE_ACTIVITY_FOUND_ERROR = "error.no.juvenile.records.found";
    public static final String NO_JUVENILE_RECORD_FOUND_ERROR = "error.no.juvenile.record.found";

    //  Referrals Attendance status
    public static final String ATTENDANCE_CANCELLED_STATUS = "CANCELLED";
    public static final String ATTENDANCE_UNCONFIRMED_STATUS = "UNCONFIRMED";
    public static final String ATTENDANCE_EXCUSED_STATUS = "EXCUSED";
    public static final String ATTENDANCE_ABSENT_STATUS = "ABSENT";

    // Juvenile
    public static final String SCARSANDTATTOOS = "scarsAndTattoos";
    public static final String SCHEDULE_PAST_PRESCHEDULED_STR = "schedulePastPrescheduled";

    //Event event status codes
    public static final String STATUS_CODE_AVAILABLE = "AV";
    public static final String STATUS_CODE_SCHEDULED = "SC";
    public static final String STATUS_CODE_PENDING = "PN";
    public static final String STATUS_CODE_CANCELLED = "CC";

    public static final String OFFICER_TYPE_JUVENILE = "J";
    public static final String OFFICER_SUBTYPE = "officerSubTypeId";
    public static final String OFFICER_SUBTYPE_CLM = "CLM";
    public static final String OFFICER_MANAGER_USERID = "managerId";

    // PASO	
    public static final String CALCULATE = "calculate";

    //	Supervision Order
    public static final String SUPERVISION_ORDER_FORM = "supervisionOrderForm";

    //  Education Tab - MJCW
    public static final String CREATE_GED = "createGED";
    public static final String CREATE_VEP = "createVEP";
    public static final String CREATE_PRT = "createPRT";

    //  MJCW Non Compliance VOP 
    public static final String MINOR = "MINOR";
    public static final String MODERATE_TO_SEVERE = "MODSEV";

    //  MJCW Suspicious Members 
    public static final String MULTIPLE_SUCCESS = "multiSuccess";
    public static final String MERGE_SUCCESS = "mergeSuccess";
    public static final String REMOVE_SUCCESS = "removeSuccess";
    public static final String REPLACE_SUCCESS = "replaceSuccess";
    public static final String CONNECT_SUCCESS = "connectSuccess";
    public static final String BACK_TO_SELECT = "backToSelect";
    public static final String NO_MATCHES_FOUND = "noMatchesFound";

    //  MJCW Outcome Subcategory 
    public static final String OUTCOME_COMPLETE = "S";
    public static final String OUTCOME_FAILURE_TO_COMPLY = "X";

    //  MJCW Assessment Request - Gang  
    public static final String LEVEL_OF_ENVOLVMENT_LOW = "Low - Showing a sign of interest in gangs, in the general area where gang activity exist";
    public static final String LEVEL_OF_ENVOLVMENT_MED = "Medium - Associating with gang members, getting involved in gang related activities";
    public static final String LEVEL_OF_ENVOLVMENT_HIGH = "HIgh - Frequently involved in gang related circumstantces, create conflict, problematic at times, has followers";
    //traits for officer alert	
    public static final String TRAIT_TYPE_ADMINISTRATIVE = "ADMINISTRATIVE";
    //traits for facility 
    public static final String TRAIT_TYPE_DETENTION = "FACILITY TRAITS";
    public static final String TRAIT_TYPE_DESCRIPTION_OFFICER_ALERT = "JUVENILE OFFICER SAFETY/SECURITY ALERT";
    public static final String TRAIT_TYPE_DESCRIPTION_RESTRICTED_ACCESS = "RESTRICTED ACCESS";
    public static final String TRAIT_TYPE_DESCRIPTION_BANNED_VISITATION = "INDIVIDUAL(S) BANNED FOR VISITATION";
    //traits for commonapp school attendence ER changes 11032 starts
    public static final String TRAIT_TYPE_ID_EXCESSIVELY_TRUANT = "ETT";
    public static final String TRAIT_TYPE_ID_MODERATELY_TRUANT = "MTT";
    public static final String TRAIT_TYPE_ID_OCCASIONALLY_TRUANT = "OTT";
    //ER Changes 11032 ends
    //added new for data correction team
    public static final String DATA_CORRECTION_TEAM = "Data.corrections@hcjpd.hctx.net";
    public static final String JIMS2_NOTIFICATION_TEAM = "JIMS2Notifications@hctx.net";
    //added for showing only currently taking medication
    public static final String CURRENTLY_TAKING_MEDICATION_YES = "Y";
    public static final String CURRENT_HEALTH_ISSUE_ACTIVE = "A";
    public static final String GOAL_DOMAIN_TYPE_RECREATIONAL = "RC";
    public static final String GOAL_DOMAIN_TYPE_CONTACT_FREQUENCY = "CF";
    public static final String GOAL_DOMAIN_TYPE_FAMILY_PARTICIPATION = "FP";
    public static final String GOAL_DOMAIN_TYPE_SOCIALIZATION = "SC";
    public static final String GOAL_DOMAIN_TYPE_SUPPORT_SERVICES = "SP";

    //ER Changes JIMS200074578 Starts
    public static final String FOSTER = "FOSTER";
    public static final String CHILD = "CHILD";
    public static final String MILITARY = "MILI";
    public static final String EDUCATIONAL_PERFORMANCE = "EDUCATIONAL PERFORMANCE";
    public static final String DYSLEXIA = "DYSLEXIA";
    public static final String DENIES = "DENIES";
    //ER Changes JIMS200074578 Ends

    //Changes for ER: JIMS200077481 starts
    public static final String GED_EFFECTIVE_DATE = "06/19/2014";
    //Changes for ER: JIMS200077481 ends

    //DSM Effective Date added for bug fix 23314
    public static final String DSM_EFFECTIVE_DATE = "08/01/2013";

    //Common App For bug fix: 11238 starts
    public static final String KINDERGARTEN = "KINDERGARTEN";
    public static final String COLLEGE = "COLLEGE";
    public static final String NA = "NOT APPLICABLE";
    //Common App For bug fix: 11238 ends

    //Gang Assessment Constants starts.
    public static final String DETAILS = "details";
    public static final String OTHER = "OTHR";
    public static final String ASSESSMENT_ID = "assessId";
    public static final String GANG_DOCTYPE_CODE = "GAR";

    //Facility changes starts
    public static final String FACILITY_SUCCESS = "facilitySuccess";
    public static final String FACILITY_LISTSUCCESSS = "facilityListSuccess";
    public static final String FACILITY_SEARCH_FAILURE = "facilitySearchFailure";
    public static final String FACILITY_DOCTYPE_CODE = "FAR";
    public static final String FACILITY_REFERRAL_FAILURE = "referralFailure";

    //referrals
    public static final String REFERRAL_SUCCESS = "referralSuccess";
    public static final String REFERRAL_SEARCH_FAILURE = "referralSearchFailure";
    public static final String REFERRAL_LIST_SUCCESS = "referralListSuccess";
    public static final String REFERRAL_CANCEL = "referralCancel";

    //Gang Risk Report:
    public static final String GANG_RISK_DOCTYPE_CODE = "GRA";
    public static final String MHSCREEN_RISK_DOCTYPE_CODE = "MHS";

    //Common App ER  11046 changes
    public static final String CASP = "CASP";
    public static final String CAFD = "CAFD";
    public static final String CASA = "CASA";
    public static final String CADH = "CADH";
    public static final String CASN = "CASN";
    public static final String CAAH = "CAAH";
    public static final String CAFH = "CAFH";
    public static final String CAER = "CAER";

    public static final String CA_SCREENINGPROFILE_ERQ_TAB = "screeningProfile";
    public static final String CA_FACILITYDETAILS_ERQ_TAB = "facilityDetails";
    public static final String CA_SOCIALASSESSMENT_ERQ_TAB = "socialAssessment";
    public static final String CA_DELINQUENCYHISTORY_ERQ_TAB = "delinquencyHistory";
    public static final String CA_SPECIALNEEDS_ERQ_TAB = "specialNeeds";
    public static final String CA_SUBSTANCEABUSEHISTORY_ERQ_TAB = "substanceAbuseHistory";
    public static final String CA_FAMILYHISTORY_ERQ_TAB = "familyHistory";

    public static final String CA_SCREENINGPROFILE_ERQ_SUMMARY_TAB = "screeningProfileSummary";
    public static final String CA_FACILITYDETAILS_ERQ_SUMMARY_TAB = "facilityDetailsSummary";
    public static final String CA_SOCIALASSESSMENT_ERQ_SUMMARY_TAB = "socialAssessmentSummary";
    public static final String CA_DELINQUENCYHISTORY_ERQ_SUMMARY_TAB = "delinquencyHistorySummary";
    public static final String CA_SPECIALNEEDS_ERQ_SUMMARY_TAB = "specialNeedsSummary";
    public static final String CA_SUBSTANCEABUSEHISTORY_SUMMARY_ERQ_TAB = "substanceAbuseHistorySummary";
    public static final String CA_FAMILYHISTORY_ERQ_SUMMARY_TAB = "familyHistorySummary";
    public static final String OLD_REPORT_YR = "2005";
    public static final String REPORT_TYPE = "reportType";
    public static final String COMMONAPP_REPORT = "CRP";
    //Common App ER  11046 ends

    //Common APP Existing Constants //34334 changes.
    public static final String JUVENILE_GANG_RELATED_TRAIT = "GAN";
    public static final String SUICIDE_IDEATION_TRAIT = "SID";
    public static final String SUICIDE_ATTEMPTS_TRAIT = "SIA";
    public static final String ASSAULTIVE_BEHAVIOR_TOWARD_FAMILY_MEMBERS_TRAIT = "ABF";
    public static final String ASSAULTIVE_BEHAVIOR_TOWARD_NON_FAMILY_MEMBERS_TRAIT = "ABO";
    public static final String ESCAPE_OR_RUNAWAY_TENDANCY_TRAIT = "ESC";
    public static final String FAMILY_MEMBER_INVOLVED_IN_GANG_TRAIT = "IGA";
    public static final String[] FAMILY_MEMBER_TRAITS_REQUIRED_FOR_COMMON_APP = { "VTF", "SUI", "SUA", "CRM", "ICC", "MRL", "MID", "PHD", "SXD" };
    public static final String FAMILY_MEMBER_PARENTAL_RIGHTS_TERMINATED = "PRT";
    public static final String STEP_FATHER = "SF";
    public static final String ADOPTIVE_FATHER = "AF";
    public static final String BIRTH_FATHER = "BF";
    public static final String ADOPTIVE_MOTHER = "AM";
    public static final String STEP_MOTHER = "SM";
    public static final String BIRTH_MOTHER = "BM";
    public static final String MEDICAL_ISSUES = "MEDICAL ISSUES"; //ER changes 11454
    public static final String DEVELOPMENTAL = "DEVELOPMENTAL"; // added for U.S 35663
    public static final String RESIDENTIAL_PROG_IBT_LEVEL = "IBT Level";
    public static final String RESIDENTIAL_PROG_IBT_PROGRESS = "Youth's progress with the IBT program?";
    public static final String RESIDENTIAL_PROG_ADDITIONAL = "Prior Rules, Special Rules/Instructions, and Statuses";
    public static final String RESIDENTIAL_PROGRESS_COMMENTS = "Plan of Action for JPO";
    public static final String RESIDENTIAL_PROG_JUVENILE = "Plan of Action for Juvenile";
    public static final String RESIDENTIAL_PROG_PARENT = "Plan of Action for Parent/Guardian";
    public static final String RESIDENTIAL_PROG_OTHER = "Plan of Action for Other (identify relationship)";

    //JJSCLDETENTION -detention hearing constants #11645
    public static final String COURT_ACTION = "courtAction";
    public static final String COURT_DOCKET = "courtDocket";

    //JJSCLCourt
    public static final String COURT_DOCKET_DISPLAY_PRINT = "courtDocketDisplay";
    public static final String INITIAL_SETTING_DISPLAY = "initialSettingDisplay";
    public static final String COURT_OFFENSE_SUCCESS = "courtOffenseSuccess";
    public static final String COURT_MAIN_MENU = "courtMainMenu";
    public static final String ANCILLARY_SETTING_ADD = "ancillarySettingAdd";//
    public static final String ANCILLARY_SETTING_ADD_SUCCESS = "ancillarySettingAddSuccess";
    public static final String ANCILLARY_SETTING_UPDATE_SUCCESS = "ancillarySettingUpdateSuccess";
    public static final String ANCILLARY_SETTING_DISPLAY = "ancillarySettingDisplay";
    public static final String ATTORNEY_SEARCH_SUCCESS = "attorneySearchSuccess";
    public static final String ANCILLARY_COURT_ACTIVITY = "ancillaryCourtActivity";
    public static final String COURT_NAME_SEARCH = "courtNameSearch";
    public static final String BRIEFING_DETAILS = "briefingDetails";
    public static final String PETITION_UPDATE_DISPLAY = "petitionUpdateDisplay";
    public static final String COURT_REFERRAL_SUMMARY = "courtReferralSummary";
    public static final String COURT_PETCJIS_SUMMARY = "courtPETCJISSummary";
    public static final String COURT_ACTION_DISPLAY = "courtActionDisplay";
    public static final String ATTORNEY_SETTING_SUCCESS = "attorneySettingSearchSuccess";
    public static final String GAL_ATTORNEY_SETTING_SUCCESS = "GALattorneySettingSearchSuccess";
    public static final String JUVENILE_SETTING_SUCCESS = "juvenileSettingSearchSuccess";
    public static final String OVERRIDE_ASSIGNMENT = "overrideAssignment";
    public static final String MANAGE_ASSIGNMENT = "manageAssignment";
    public static final String DISTRICTCOURT_UPDATE_DISPLAY = "districtCourtUpdateDisplay";
    public static final String ANCILLARYCALENDAR_UPDATE_DISPLAY = "ancillaryCalendarUpdateDisplay";
}
