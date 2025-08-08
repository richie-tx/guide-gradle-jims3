package naming;

public class ActivityConstants {
	
	// Constants for Conduct Interview use case
	
	public static final String DISPOSITIONAL_ALTERNATIVE_FIRST = "DA1";
	public static final String DISPOSITIONAL_ALTERNATIVE_SECOND = "DA2";
	public static final String DISPOSITIONAL_ALTERNATIVE_THIRD = "DA3";
	public static final String GENERATE_ATTORNEY_REQUEST = "GAT";
	public static final String FINANCIAL_STATEMENT_OBTAINED = "FSO";
	public static final String PARENTAL_RIGHTS_WORKSHEET_COMPLETED = "PRW";
	public static final String PARENTAL_WRITTEN_STATEMENT_REVIEWED = "PWS";
	public static final String SOCIAL_HISTORY_REPORT_GENERATED = "SHG";
	
	public static final String TITLE_IV_E_ASSESSMENT_COMPLETED = "T4C";
	public static final String TITLE_IV_E_ASSESSMENT_REVIEWED = "T4R";
	public static final String BENEFITS_ASSESSMENT_NOT_REQUESTED = "T4X";
	public static final String BENEFITS_ASSESSMENT_REQUESTED = "BRQ";
	
	public static final String ACTIVATED_A_CASEFILE = "ACF";
	
	public static final String EVALUATIVE_SUMMARY = "EVS";
	
	// Caseplan constants
	public static final String CASE_PLAN_CREATED = "CCP";
	public static final String CASE_PLAN_COPIED = "CPC";
	public static final String CASE_PLAN_MODIFIED = "CCM";
	public static final String CASE_PLAN_REVIEWED = "REV";
	public static final String CASE_PLAN_GENERATED = "CCG";	
	public static final String DRAFT_CASE_PLAN_GENERATED = "DFT";
	public static final String CASE_PLAN_FORWARDED_FOR_CLM_REVIEW = "REQ";
	public static final String CASE_PLAN_ACCEPTED_BY_CLM = "CLM";
	public static final String CASE_PLAN_REJECTED_BY_CLM = "REJ";
	
	//Casefile closing constants
	
	public static final String CLOSING_LETTER_GENERATED = "CLG";
	public static final String EXIT_PLAN_GENERATED = "EXG";
	public static final String CLOSING_PACKET_GENERATED = "CPG";
	public static final String CASE_CLOSING_PENDING = "PCC";
	public static final String CASEFILE_SUBMITTED_TO_SUPERVISOR_FOR_APPROVAL = "CSA";
	public static final String CASEFILE_CLOSING_REJECTED = "CSR";
	public static final String CASE_REVIEW_CLOSING_APPROVED = "CRA";
	public static final String CASE_REVIEWED_FOR_CLOSING = "CRC";
	public static final String CASEFILE_CLOSING_OVERRIDE_EXCEPTIONS = "CLE";
	
	
	//Interview constants
	public static final String SOCIAL_HISTORY_EMAILED_TO_DEFENSE_ATTORNEY = "SDX";
	
	//Mental Health Constants constants
	public static final String SUBSEQUENT_ASSESSMENT_FOR_MENTAL_HEALTH = "SUB";	
	
	// Rule Activity constants
	public static final String MODIFY_SUPERVISION_RULES = "MSR";
	public static final String SUPERVISION_RULES_TRANSFER = "SRT"; //added for US 22839
	
	// ACTIVITY_TYPE constants
	public static final String ACTIVITY_TYPE_CASEPLAN = "CSP";
	public static final String ACTIVITY_TYPE_CASE_MANAGEMENT = "CSM";
	public static final String ACTIVITY_TYPE_COMMON_APP_REPORT = "CMA"; //added for #34115
	public static final String ACTIVITY_TYPE_INFO_TRANSFER = "ITR"; //added for US 22839
	public static final String ACTIVITY_TYPE_REF_REC_CREATED = "REF"; //added for US 71173
	public static final String ACTIVITY_TYPE_REF_REC_OVERRIDE = "ARO"; //added for US 71181
	
	//added for US 71173
	public static final String REFERRAL_RECORD_CREATED = "RCR";
	public static final String REFERRAL_RECORD_MODIFIED = "RCM";
	public static final String REFERRAL_RECORD_CLOSED = "RCC";
	public static final String REFERRAL_RECORD_REOPENED = "RRE";
	
	//added for US 71181
	public static final String ASSIGNMENT_RECORD_OVERRIDE = "AOR";
	
	//added for US 85483
	public static final String ASSIGNMENT_GENERATED = "MAR";
	
	//ER GANG ASSESSMENT REFERRAL CHANGES.
	public static final String ACTIVITY_TYPE_GANG_ASSESSMENT_REFERRAL = "GAR";
	//District Court
	public static final String ACTIVITY_TYPE_JUDICIAL = "JUD";
	
	
	// ACTIVITY_CATEGORY constants
	public static final String ACTIVITY_CATEGORY_REPORTING = "REP";
	public static final String ACTIVITY_CATEGORY_ADMINISTRATIVE = "ADM";
	public static final String ACTIVITY_SYSTEM_GENERATED = "SYS"; //added for US 22839
	
	//Facility activity Category
	public static final String ACTIVITY_CATEGORY_RESIDENTIAL ="RES";	
	public static final String INTERVIEW_APPOINTMENT_LETTER_GENERATED = "IAL";
	
	public static final String SCHOOL_ADJUDICATION_NOTIFICATION = "NFW";
	public static final String SCHOOL_ADJUDICATION_NOTIFICATION2 = "NFT";
	public static final String COURT_APPOINTMENT_LETTER_GENERATED = "CAL";
	
	//GANG ASSESSMENT
	public static final String ACTIVITY_CATEGORY_ASSESSMENT_REFERRAL = "AR";
	public static final String ACTIVITY_CATEGORY_COMMONAPP_REPORT = "CAR"; //added for #34115
	public static final String ACTIVITY_CODE_COMMONAPP_REPORT = "CMA"; //added for #34115
	
	//FACILITY constants
	public static final String ADMIT_TO_FACILITY = "ATF";
	public static final String FACILITY_SECURE_STATUS="FSS";
	public static final String FACILITY_ADMIT_LOCATION="FAL";
	public static final String FACILITY_ADMIT_REASON="FAR";
	public static final String FACILITY_ADMISSION_COMMENTS="FAC";
	public static final String FACILITY_ADMIT_BY="FAB";
	public static final String FACILITY_ADMISSION_DETAIL="FAD";
	public static final String FACILITY_SPECIAL_ATTENTION="FSA";
	public static final String FACILITY_DOCUMENT_ESCAPE="FDE";
	public static final String FACILITY_DOCUMENT_RETURN="FDR";
	public static final String FACILITY_FINAL_RELEASE="FRF";
	public static final String TEMP_RELEASE_FACILITY="TRF"; //Temp Release Facility
	public static final String TEMPORARY_RELEASE_OUTCOME="TRO"; //Temp Release Decision
	public static final String RETURN_TEMP_RELEASE_FACILITY="RTF"; //Return Temp Release Facility
	public static final String FACILITY_TRANSFER_RELEASE="FNR";
	public static final String FACILITY_REFERRAL_TRANSFER="FRT";
	
	
	//Non-compliance constants
	public static final String VOP_SANCTIONS_NOTICE_GENERATED = "VON";
	public static final String VOP_SANCTIONS_DOCUMENTED = "VOP";
	public static final String VOP_SANCTIONS_NOTICE_SIGNED = "VPX";
	public static final String VOP_SANCTIONS_NOTICE_SIGNATURE_REFUSAL  = "VPR";
	public static final String VOP_SANCTIONS_COMPLETION_STATUS_UPDATE = "PCX";
	
	public static final String ASSESSMENT_REFERRAL_CREATED="ARC";
	public static final String ASSESSMENT_REFERRAL_SUBMITTED="ARS";
	
	//District court
	public static final String PETITION_DELETED="PTD";
	public static final String ACTIVITY_CATEGORY_PETITION = "PET";
	
	public static final String TEMPORARY_RELEASE_REQUEST="TRR";
	
	public static final String MAYSI_IDENTIFICATION_SHEET_PRINTED="MIP";
}