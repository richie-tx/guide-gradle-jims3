package ui.util;

/**
 * define all the reports as enum with a reportUrl (jsp path) and a reportName (name of the report)
 * @author rcarter
 *
 */
public enum PDFReport {
	
	// warrant reports
	GENERICWARRANT_REPORT_NAME("/jsp/pdfreports/genericWarrantNoticeReport.jsp","BFO_Generic_Warrant_Notice.pdf"),
	ARRESTWARRANT_REPORT_NAME("/jsp/pdfreports/arrestWarrantReport.jsp","BFO_Arrest_Warrant.pdf"),
	ARRESTWARRANTRETURN_REPORT_NAME("/jsp/pdfreports/arrestWarrantReturnReport.jsp","BFO_Arrest_Warrant_Return.pdf"),
	DTAWARRANT_REPORT_NAME("/jsp/pdfreports/DTAWarrantReport.jsp","BFO_Directive_To_Apprehend.pdf"),
	DTAAFFIDAVIT_REPORT_NAME("/jsp/pdfreports/DTAAffidavitReport.jsp","BFO_Directive_To_Apprehend_Affidavit.pdf"),
	DTAWARRANTRETURN_REPORT_NAME("/jsp/pdfreports/DTAWarrantReturnReport.jsp","BFO_Directive_To_Apprehend_Return.pdf"),
	OICWARRANT_REPORT_NAME("/jsp/pdfreports/OICWarrantReport.jsp","BFO_Order_Of_Immediate_Custody.pdf"),
	OICWARRANTRETURN_REPORT_NAME("/jsp/pdfreports/OICWarrantReturnReport.jsp","BFO_Order_Of_Immediate_Custody_Return.pdf"),
	
	// caseload reports
	ADJUDICATION_NOTIFICATION("/jsp/pdfreports/adjudication_Notification.jsp","BFO_Adjudication_Notification.pdf"),
	CHRONOLOGICAL_DICTATION_JOURNAL("/jsp/pdfreports/chronological_Dictation_Journal.jsp","BFO_file_Chronological_Dictation.pdf"),
	CASEFILE_REVIEW_SUMMARY("/jsp/pdfreports/casefile_Review_Journal_Summary.jsp","BFO_Casefile_Review_Journal_Summary.pdf"),
	MAYSI_REQUEST_INFO("/jsp/pdfreports/MAYSI_Request_Info.jsp","BFO_MAYSI_Request_Info.pdf"),
	NONCOMPLIANCE_NOTICE("/jsp/pdfreports/noncompliance_Notice.jsp","BFO_Noncompliance_Notice.pdf"),
	PROGRAM_REFERRAL_LIST_REPORT("/jsp/pdfreports/program_Referral_List_Report.jsp","BFO_Program_Referral_List_Report.pdf"),
	SOCIAL_HISTORY_REPORT("/jsp/pdfreports/social_History_Report.jsp","BFO_Social_History_Report.jsp.pdf"),
	SOCIAL_HISTORY_REPORT_GENERIC("/jsp/pdfreports/social_History_Report_Generic.jsp","BFO_Social_History_Report_Generic.pdf"),
	REPORT_TO_REFEREE_INITIATION("/jsp/pdfreports/report_To_Referee_Initiation.jsp","BFO_Report_To_Referee_Initiation.pdf"),
	HIRE_ATTORNEY_REPORT("/jsp/pdfreports/hire_Attorney_Report.jsp","BFO_Parental_Rights_Report.pdf"),
	REQUEST_ATTORNEY_REPORT("/jsp/pdfreports/request_Attorney_Report.jsp","BFO_Request_Attorney_Report.pdf"),
	PARENTAL_STATEMENT_REPORT_EN("/jsp/pdfreports/parental_Statement_Report_En.jsp","BFO_Parental_Statement_Report_En.pdf"),
	PARENTAL_STATEMENT_REPORT_ES("/jsp/pdfreports/parental_Statement_Report_Es.jsp","BFO_Parental_Statement_Report_Es.pdf"),	
	APPOINTMENT_LETTER("/jsp/pdfreports/appointment_Letter.jsp","BFO_Appointment_Letter.pdf"),
	MAYSI_MENTAL_HEALTH_REPORT("/jsp/pdfreports/MAYSI_Mental_Health_Report.jsp","BFO_MAYSI_Mental_Health_Report.pdf"),
	
	JOT_CHARGE("/jsp/pdfreports/JOT_Charge.jsp","BFO_JOT_Charge.pdf"),
	TEA_REPORT("/jsp/pdfreports/TEA_StudentDataReport.jsp","BFO_TEA_StudentDataReport.pdf"),
	
	SUSPICIOUS_MEMBER_MERGE_FORM("/jsp/pdfreports/suspiciousMemberMergeMemberFromForm.jsp","BFO_SUSPICIOUS_MEMBER_MERGE_FORM"),
	
	ID_INFO("/jsp/pdfreports/idInfo.jsp","BFO_ID_Info.pdf"),
	INTERVIEW_APPOINTMENT_LETTER_EN("/jsp/pdfreports/interviewAppointmentLetter_English.jsp","BFO_Interview_Appointment_Letter_English.pdf"),
	INTERVIEW_APPOINTMENT_LETTER_ES("/jsp/pdfreports/interviewAppointmentLetter_Spanish.jsp","BFO_Interview_Appointment_Letter_Spanish.pdf"),
	
	MJC_CLOSING_LETTER_EN("/jsp/pdfreports/MJC_Closing_Letter_English.jsp","BFO_MJC_CLOSING_Letter_English.pdf"),
	MJC_CLOSING_LETTER_ES("/jsp/pdfreports/MJC_Closing_Letter_Spanish.jsp","BFO_MJC_CLOSING_Letter_Spanish.pdf"),
	MJC_RESTRCITED_ACCESS_EN("/jsp/pdfreports/MJC_Restricted_Access_en_v2023.jsp","BFO_MJC_Restricted_Access_English.pdf"), //Task 36775
	MJC_RESTRICTED_ACCESS_ES("/jsp/pdfreports/MJC_Restricted_Access_es_v2023.jsp","BFO_MJC_Restricted_Access_Spanish.pdf"),
	CLIENT_SATISFACTION_SURVEY_EN("/jsp/pdfreports/MJC_ClientSatisfactionSurvey_en.jsp","BFO_MJC_Client_Satisfaction_Survey_en.pdf"),
	CLIENT_SATISFACTION_SURVEY_ES("/jsp/pdfreports/MJC_ClientSatisfactionSurvey_es.jsp","BFO_MJC_Client_Satisfaction_Survey_es.pdf"),
	VENDOR_SATISFACTION_SURVEY("/jsp/pdfreports/MJC_VendorSatisfactionSurvey.jsp","BFO_MJC_Vendor_Satisfaction_Survey.pdf"),

	EVENT_CANCELLATION_CALL_LIST("/jsp/pdfreports/MJC_EventCancellationCallList.jsp","BFO_EVENT_CANCELLATION_CALL_LIST.pdf"),
	EVENT_SIGN_IN_SHEET("/jsp/pdfreports/eventSignInSheet.jsp","BFO_EVENT_SIGN_IN_SHEET.pdf"),
	SCHEDULE_EVENTS_LIST("/jsp/pdfreports/MJC_ScheduleEventsList.jsp","BFO_MJC_SCHEDULE_EVENTS_LIST.pdf"),
	EXHIBITB_REPORT("/jsp/pdfreports/exhibitBReport.jsp","BFO_Exhibit_B_Report.pdf"),
	
	// facility reports
	POPULATION_TOTALS_REPORT_NAME("/jsp/pdfreports/facilityPopulationTotalsPrintReport.jsp","BFO_Facility_PopulationTotalsReport.pdf"),
	RESIDENT_STATUS_REPORT_NAME("/jsp/pdfreports/facilityResidentStatusPrintReport.jsp", "BFO_Facility_ResidentStatusReport.pdf"),
	
	ADMIT_REASON_REPORT_NAME("/jsp/pdfreports/facilityAdmitReasonPrintReport.jsp","BFO_Facility_AdmitReasonReport.pdf"),
	CURRENT_POPULATION_REPORT_NAME("/jsp/pdfreports/facilityCurrentPopulationPrintReport.jsp","BFO_Facility_CurrentPopulationReport.pdf"),
	CURRENT_POPULATION_GUARDIANREPORT_NAME("/jsp/pdfreports/facilityCurrentPopulationGuardianPrintReport.jsp","BFO_Facility_CurrentPopulationGuardianReport.pdf"),
	CURRENT_OBSERVATION_REPORT_NAME("/jsp/pdfreports/facilityCurrentObservationPrintReport.jsp","BFO_Facility_CurrentObservationReport.pdf"),
	
	FACILITY_HISTORICAL_RECEIPT_REPORT_NAME("/jsp/pdfreports/facilityHistoricalReceiptPrintReport.jsp","BFO_Facility_Historical_Receipt_Report.pdf"),
	
	FACILITY_ADMISSION_FORM("/jsp/pdfreports/facility_Admission_Form.jsp","BFO_Facility_Admission_Form.pdf"),
	
	//gang assessment reports
	GANG_ASSESSMENT_REFERRAL_REPORT("/jsp/pdfreports/gangAssessmentReferralPrintReport.jsp","BFO_Gang_AssessmentReferral_Report.pdf"),
	//Gang Risk Assessment
	GANG_RISK_ASSESSMENT_REPORT("/jsp/pdfreports/gangRiskAssessPrintReport.jsp","BFO_Gang_Risk_Assessment_Report.pdf"),
	//MH Screen Risk Assessment
	MHSCREEN_RISK_ASSESSMENT_REPORT("/jsp/pdfreports/gangRiskAssessPrintReport.jsp","BFO_MHScreen_Risk_Assessment_Report.pdf"),
	//MH Screen Risk Assessment
	PREA_FOLLOW_UP_RISK_ASSESSMENT_REPORT("/jsp/pdfreports/PREAfollowUpRiskAssessPrintReport.jsp","BFO_PREAFollowUp_Risk_Assessment_Report.pdf"),
	//FacilityOrientation PDFReport.12253 changes
	FACILITY_ORIENTATION_LETTER_EN("/jsp/pdfreports/facilityOrientationLetter_En.jsp","BFO_Facility_Orientation_Letter_En.pdf"),
	FACILITY_ORIENTATION_LETTER_ES("/jsp/pdfreports/facilityOrientationLetter_Es.jsp","BFO_Facility_Orientation_Letter_Es.pdf"),
		
	// CommonApp BFO Report #34452
	COMMON_APPLICATION_FOR_PLACEMENT_OF_CHILDREN("/jsp/pdfreports/MJC_CommonApplicationForPlacementOfChildren.jsp","BFO_CommonApp_For_PlacementOfChildren.pdf"),
	
	//Casefile Search Results Report #34663
	CASEFILE_SEARCH_RESULTS_REPORT("/jsp/pdfreports/casefile_SearchResults_Report.jsp", "BFO_Casefile_Search_Results.pdf"),
	CASELOAD_SEARCH_RESULTS_REPORT("/jsp/pdfreports/caseload_SearchResults_Report.jsp", "BFO_Caseload_Search_Results.pdf"),
	//Casefile Search Results => Add button to Print Youth Addresses US# 153691
	CASEFILE_SEARCH_RESULTS_WITH_ADDRESS_REPORT("/jsp/pdfreports/casefile_SearchResults_with_youthAddress_Report.jsp", "BFO_Casefile_Search_Results_WithYouthAddress.pdf"),
	JUVENILE_DEMOGRAPHICS_REPORT("/jsp/pdfreports/JuvenileDemographicsReport.jsp","BFO_JuvenileDemographicsReport.pdf"),
	
	DETENTION_HEARING_DOCKET("/jsp/pdfreports/detentionHearingDocketReport.jsp","BFO_Detention_Hearing_Docket.pdf"),
	
	//Court Docket Display PDFs
	STANDARD_COURT_DOCKET_REPORT("/jsp/pdfreports/standardCourtDocketReport.jsp","BFO_Standard_Court_Docket.pdf"),
	ALPHABETIZED_COURT_DOCKET_REPORT("/jsp/pdfreports/standardCourtDocketReport.jsp","BFO_Alphabetized_Court_Docket.pdf"),
	
	// 5 supoenas
	SUBPOENA_CERT_GUARD_FATHER("/jsp/pdfreports/subpoenaCertGuardianReport.jsp","BFO_Subpoena_Certify_Guardian_Father.pdf"),
	SUBPOENA_CERT_GUARD_MOTHER("/jsp/pdfreports/subpoenaCertGuardianReport.jsp","BFO_Subpoena_Certify_Guardian_Mother.pdf"),
	SUBPOENA_CERT_GUARD_OTHER("/jsp/pdfreports/subpoenaCertGuardianReport.jsp","BFO_Subpoena_Certify_Guardian_Other.pdf"),
	SUBPOENA_CERT_17_JUVENILE("/jsp/pdfreports/subpoenaCertJuvReport.jsp","BFO_Subpoena_Certify_17_Juvenile.pdf"),
	SUBPOENA_CERT_OVER17_JUVENILE("/jsp/pdfreports/subpoenaCertJuvReport.jsp","BFO_Subpoena_Certify_Over17_Juvenile.pdf"),
	SUBPOENA_STD_GUARD_FATHER("/jsp/pdfreports/subpoenaStdGuardianReport.jsp","BFO_Subpoena_Standard_Guardian_Father.pdf"),
	SUBPOENA_STD_GUARD_MOTHER("/jsp/pdfreports/subpoenaStdGuardianReport.jsp","BFO_Subpoena_Standard_Guardian_Mother.pdf"),
	SUBPOENA_STD_GUARD_OTHER("/jsp/pdfreports/subpoenaStdGuardianReport.jsp","BFO_Subpoena_Standard_Guardian_Other.pdf"),
	SUBPOENA_STD_JUVENILE("/jsp/pdfreports/subpoenaStdJuvReport.jsp","BFO_Subpoena_Standard_Juvenile.pdf"),
	
	//Process Referrals
	REFERRAL_RECEIPT("/jsp/pdfreports/Referral_Receipt.jsp","BFO_Referral_Receipt.pdf"),
	REFERRAL_BRIEFING_MASTER_DISPLAY_REPORT("/jsp/pdfreports/ReferralBriefingMasterDisplayReport.jsp","BFO_ReferralBriefingMasterDisplayReport.pdf"),
	
	ATTORNEYSETTINGS_SEARCH_RESULTS_REPORT("/jsp/pdfreports/Attorneysettings_SearchResults_Report.jsp", "BFO_Attorneysettings_Search_Results.pdf"),
	GALATTORNEYSETTINGS_SEARCH_RESULTS_REPORT("/jsp/pdfreports/GALAttorneysettings_SearchResults_Report.jsp", "BFO_GALAttorneysettings_Search_Results.pdf"),
	JUVENILEBYACTIVITYCATEGORY_SEARCH_RESULTS_REPORT("/jsp/pdfreports/JuvenilebyActivityCasefile_SearchResults_Report.jsp", "BFO_JuvenilebyActivityCasefile_Search_Results.pdf"),
	//Gang Risk Assessment
	PRE_COURT_STAFFING_REPORT("/jsp/pdfreports/preCourtStaffingPrintReport.jsp","BFO_Pre_Court_Staffing_Report.pdf"),
	PROGRAM_REFERRAL_STATISTICAL_SUMMARY("/jsp/pdfreports/programReferralStatisticalSummaryReport.jsp","BFO_Program_Ref_Statistical_Report.pdf"),
	GANG_ASSESSMENT_REPORT("/jsp/pdfreports/gangAssessmentReferrals_SearchResults_Report.jsp","BFO_Gang_Assessment_Report.pdf");
	
	private String reportUrl;
	private String reportName;
	
	private PDFReport(String reportUrl, String reportName){
		this.reportUrl = reportUrl;
		this.reportName = reportName;
	}
	
	public String getReportUrl(){
		return reportUrl;
	}
	
	public String getReportName(){
		return reportName;
	}
	
	
}
