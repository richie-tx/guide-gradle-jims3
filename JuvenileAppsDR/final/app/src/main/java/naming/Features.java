/*
 * Created on Nov 22, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package naming;

/**
 * @author jjose TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public interface Features
{
    //UPDATE PASSWORD - Left Navigation feature
    public static String JMS_PASSWORD_U = "JMS-PASSWORD-U";
    // JUVENILE CASEWORK - Left Navigation features
    public static String JCW_BENEFITSASSESS = "JCW-BENEFITSASSESS"; // Process Benefits Assessment
    public static String JCW_SEARCHCASE = "JCW-SEARCHCASE"; // Search Juvenile Casefile
    public static String JCW_SEARCHPROF = "JCW-SEARCHPROF"; // Search Juvenile Profile
    public static String JCW_SEARCHFACPROF = "JCW-SEARCHFACPROF"; //Search Facility profile
    public static String JCW_WKSHOPCALENDAR = "JCW-WKSHOPCALENDAR"; // Workshop Calendar for Juvenile Casework
    public static String JCW_WC_JUV_ADD = "JCW-WC-JUV-ADD"; // Juvenile Casework Workshop Calendar Juvenile Add
    public static String JCW_SEARCHFAMILYMEMBER = "JCW-SEARCHFAMILYMEMBER"; // Search Family Members
    public static String JCW_SEARCHJUVREFPROF = "JCW-SEARCHJUVREFPROF"; // Search Juvenile Referral Profile
    public static String JCW_CASEWORKBRIEFINGDETAILSSCREEN = "JCW-CASEWORKBRIEFINGDETAILSSCREEN";//Access Casework Briefing 
    public static String JCW_JUVENILEINFORMATIONSHARING_JIS = "JCW-JUVENILEINFORMATIONSHARING-JIS";
    //Juvenile Casework Search Notifications Features
    public static String JCW_NS_SEARCHALL = "JCW-NS-SEARCHALL";
    public static String JCW_NS_SEARCHIND = "JCW-NS-SEARCHIND";
    public static String JCW_ACTIVITIES_MULTIPLE_YOUTH = "JCW-ACTIVITIES-MULTIPLE-YOUTH"; // Process Activity for Multiple Youth

    // JUVENILE CASEWORK - Task & Notices
    public static String JCW_PROCESS_NOTICES = "JCW-PROCESS-NOTICES"; // Process Notices
    public static String JCW_PROCESS_TASKS = "JCW-PROCESS-TASKS"; // Process Task

    // JUVENILE CASEWORK - Casefile Buttons features 
    public static String JCW_CF_ACT_U = "JCW-CF-ACT-U"; // Casefile-Activities Update
    public static String JCW_CF_ASSREF_U = "JCW-CF-ASSREF-U"; // Casefile-Assigned Referrals Update
    public static String JCW_CF_ACT_PRV_V = "JCW-CF-ACT-PRV-V"; // Casefile Activity view for vendor // Task 39013
    public static String JCW_CF_BENASSESS_U = "JCW-CF-BENASSESS-U"; // Casefile-Benefits Assessment Update
    public static String JCW_CF_BENASSESS_V = "JCW-CF-BENASSESS-V"; // Casefile-Benefits Assessment View
    public static String JCW_CF_CALENDAR_U = "JCW-CF-CALENDAR-U"; // Casefile-Calendar Update
    public static String JCW_CF_CASEFILE_U = "JCW-CF-CASEFILE-U"; // Casefile-Casefiles Update
    public static String JCW_CF_CLOSING_U = "JCW-CF-CLOSING-U"; // Casefile-Closing Update
    public static String JCW_CF_CLOSINGSUB_U = "JCW-CF-CLOSINGSUB-U";
    public static String JCW_CF_CLOSING_GENRP = "JCW-CF-CLOSING-GENRP"; // Casefile-Closing Generate, View and Print Closing Packets, Surveys and Letters 
    public static String JCW_CF_CLOSING_PRNRP = "JCW-CF-CLOSING-PRNRP"; // Casefile-Closing View and Print Reports and Common App versions
    public static String JCW_CF_CLOSING_REQRV = "JCW-CF-CLOSING-REQRV"; // Casefile-Closing Send CLM Request to Review Closing
    public static String JCW_CF_CLOSING_REV = "JCW-CF-CLOSING-REV"; // Casefile-Closing Approve or Reject Closing
    public static String JCW_CF_GOALS_U = "JCW-CF-GOALS-U"; // Casefile-Goals Update
    public static String JCW_CF_INTERVIEW_U = "JCW-CF-INTERVIEW-U"; // Casefile-Interview Update
    public static String JCW_CF_MAYSI_U = "JCW-CF-MAYSI-U"; // Casefile-MAYSI Update
    public static String JCW_CF_MAYSI_V = "JCW-CF-MAYSI-V"; // Casefile-MAYSI View
    public static String JCW_CF_MAYSI_TEXT_V = "JCW-CF-MAYSI-TEXT-V"; // Casefile-MAYSI Text Button View
    public static String JCW_CF_PGMREF_U = "JCW-CF-PGMREF-U"; // Casefile-Program Referral Update
    public static String JCW_CF_PGMREF_C = "JCW-CF-PGMREF-C"; // Casefile-Program Referral Create
    public static String JCW_CF_PGMREF_B = "JCW-CF-PGMREF-B"; // Casefile-Program Referral Create Update Begin date
    public static String JCW_CF_PGMREF_TR = "JCW-CF-PGMREF-TR"; // Casefile-Program Referral Transfer
    public static String JCW_CF_CASEPLAN_CP = "JCW-CF-CASEPLAN-CP"; // Casefile-Caseplan Copy
    public static String JCW_CF_CASEPLAN_ACK = "JCW-CF-CASEPLAN-ACK"; // Casefile-Caseplan Acknowledgement
    public static String JCW_CF_CASEPLAN_GEN = "JCW-CF-CASEPLAN-GEN";
    public static String JCW_CF_RISKANA_U = "JCW-CF-RISKANA-U"; // Casefile-Risk Analysis Update
    public static String JCW_CF_RISKANA_V = "JCW-CF-RISKANA-V"; // Casefile-Risk Analysis View
    public static String JCW_CF_CASE_REV_DRAFT = "JCW_CF_CASE_REV_DRAFT"; // Casefile-General Journal Case Review/Journal Summary Report - DRAFT BUTTON
    public static String JCW_CF_CASE_REV_FINAL = "JCW_CF_CASE_REV_FINAL"; // Casefile-General Journal Case Review/Journal Summary Report - FINAL BUTTON
    public static String JCW_CF_PGMREF_HLTHSVCPGMASS = "JCW-CF-PGMREF-HLTHSVCPGMASS";

    //PACT FEATURES
    public static String JCW_BRF_PACT_GEN = "JCW-BRF-PACT-GEN"; //briefing pact generation.
    public static String JCW_CF_PACT_GEN = "JCW-CF-PACT-GEN"; //casefile pact generation.
    public static String JCW_CF_PACT_RNLEVELS = "JCW-CF-PACT-RNLEVELS"; //adding risk and need levels

    //Gang Assessment Changes
    public static String JCW_CF_GANGASSESS_V = "JCW-CF-GANGASSESS-V"; // Casefile Gang Assessment Referral View
    public static String JCW_CF_GANGASSESS_U = "JCW-CF-GANGASSESS-U"; //Casefile Gang Assessment Referral Update
    public static String JCW_CF_GANGASSESS_C = "JCW-CF-GANGASSESS-C"; //Casefile Gang Assessment Referral Create

    // JUVENILE CASEWORK - Risk Button features 
    public static String JCW_CF_RISK_GANG = "JCW-CF-RISK-GANG"; // Casefile-Risk Analysis Gang Risk Button
    public static String JCW_CF_RISK_DETENTION = "JCW-CF-RISK-DETENTION"; // Casefile-Risk Analysis Detention Button
    public static String JCW_CF_RISK_COURT_REF = "JCW-CF-RISK-COURT-REF"; // Casefile-Risk Analysis Court Referral View
    public static String JCW_CF_RISK_TJPC_COURT_REF = "JCW-CF-RISK-TJPC-COURT-REF"; // Casefile-Risk Analysis TJPC Court Referral View
    public static String JCW_CF_RISK_TJPC_COURT_REF_UPDATE = "JCW-CF-RISK-TJPC-COURT-REF-UPDATE"; // Casefile-Risk Analysis TJPC Court Completion and Update buttons
    public static String JCW_DC_RISK_PROGRESS_COPY = "JCW-DC-RISK-PROGRESS-COPY";
    public static String JCW_DC_RISK_GANG_COPY = "JCW-DC-RISK-GANG-COPY";

    // Casefile-Risk (new) Referral Detail View - Override Recommendation
    public static String JCW_CF_OVERRIDE_RECOMMENDATION = "JCW-CFC-JUV-OVERRIDE-RECOMMENDATION";

    public static String JCW_CF_RISK_CLM_U = "JCW-CF-RISK-CLM-U";

    public static String JCW_CF_RULES_U = "JCW-CF-RULES-U"; // Casefile-Rules Update
    public static String JCW_CF_RULES_TR = "JCW-CF-RULES-TR"; // Casefile-Rules Transfer
    public static String JCW_CF_RULES_SR = "JCW-CF-RULES-SR"; // Casefile-Rules Update
    public static String JCW_CF_RULES_CR = "JCW-CF-RULES-CR"; // Casefile-Rules Update
    public static String JCW_CF_TRAITS_U = "JCW-CF-TRAITS-U"; // Casefile-Traits Update
    public static String JCW_CF_CORR_U = "JCW-CF-CORR-U"; // Casefile Correction Update

    // JUVENILE CASEWORK - Casefile Tabs features
    public static String JCW_CFV_ACT = "JCW-CFV-ACT"; // Casefile-View Activities Tab
    public static String JCW_CFU_ACT = "JCW-CFU-ACT"; // Casefile-Update Activities Tab
    public static String JCW_PRFV_CF_ACT = "JCW-PRFV-CF-ACT"; // Profile-View Casefiles Tab-Activities
    public static String JCW_PRFU_CF_ACT = "JCW-PRFU-CF-ACT"; // Profile-Update Casefiles Tab-Activities	

    public static String JCW_CFV_ASSREF = "JCW-CFV-ASSREF"; // Casefile-View Assigned Referrals Tab
    public static String JCW_CFV_CALENDAR = "JCW-CFV-CALENDAR"; // Casefile-View Calendar Tab
    public static String JCW_CFV_CALENDAR_BTNS = "JCW-CFV-CALENDAR-BTNS"; // Casefile-View Calendar Tab Buttons
    public static String JCW_CFV_CALATTENDPRT = "JCW-CFV-CALATTENDPRT"; // Casefile-View Calendar Tab Buttons
    public static String JCW_CFV_CASEFILE = "JCW-CFV-CASEFILE"; // Casefile-View Casefiles Tab
    public static String JCW_CFV_CLOSING = "JCW-CFV-CLOSING"; // Casefile-View Closing Tab
    public static String JCW_CFV_COMMONAPP = "JCW-CFV-COMMONAPP"; // Casefile-View Closing Tab
    public static String JCW_CFV_GOALS = "JCW-CFV-GOALS"; // Casefile-View Goals Tab
    public static String JCW_CFV_INTERVIEW = "JCW-CFV-INTERVIEW"; // Casefile-View Interview Tab
    public static String JCW_CFV_PGMREF = "JCW-CFV-PGMREF"; // Casefile-View Program Referral Tab
    public static String JCW_CFV_RULES = "JCW-CFV-RULES"; // Casefile-View Rules Tab
    public static String JCW_CFV_TRAITS = "JCW-CFV-TRAITS"; // Casefile-View Traits Tab
    public static String JCW_CFV_JOU = "JCW-CFV-JOU"; // Casefile-View Journal Tab
    public static String JCW_CFV_DOCUMENT = "JCW-CFV-DOCUMENT"; // Casefile-View Documents Tab
    public static String JCW_CF_INTERVIEW_EXHIBITB = "JCW-CF-INTERVIEW-EXHIBITB"; //Casefile-View Exhibit B
    ;
    // JUVENILE CASEWORK - Profile Buttons features
    public static String JCW_PRF_ASSREF_U = "JCW-PRF-ASSREF-U"; // Profile-Assigned Referral Update
    public static String JCW_PRF_CASEFILE_U = "JCW-PRF-CASEFILE-U"; // Profile-Casefiles Update
    public static String JCW_PRF_CASEPLAN_U = "JCW-PRF-CASEPLAN-U"; // Profile-Caseplan Update
    public static String JCW_PRF_CF_ACT_U = "JCW-PRF-CF-ACT-U"; // Profile-Casefiles Activities Update
    public static String JCW_PRF_CF_PI_U = "JCW-PRF-CF-PI-U"; // Profile-Casefiles Profile Info Update
    public static String JCW_PRF_CONTACTS_U = "JCW-PRF-CONTACTS-U"; // Profile-Contacts Update
    public static String JCW_PRF_FAM_BEN_U = "JCW-PRF-FAM-BEN-U"; // Profile-Update Active Family Member Benefits
    public static String JCW_PRF_FAM_CI_U = "JCW-PRF-FAM-CI-U"; // Profile-Update Active Family Member Contact Information\
    public static String JCW_PRF_FAM_AI_U = "JCW-PRF-FAM-AI-U"; // Profile-Update Active Family Member Address Information\
    public static String JCW_PRF_FAM_CMC_U = "JCW-PRF-FAM-CMC-U"; // Profile-Create New Family Constellation Members
    public static String JCW_PRF_FAM_CM_U = "JCW-PRF-FAM-CM-U"; // Profile-Update Active Family Constellation Members
    public static String JCW_PRF_FAM_DYN_U = "JCW-PRF-FAM-DYN-U"; // Profile-Update Active Family Constellation Member Dynamics
    public static String JCW_PRF_FAM_EMP_U = "JCW-PRF-FAM-EMP-U"; // Profile-Update Active Family Member Employment
    public static String JCW_PRF_FAM_FFC_U = "JCW-PRF-FAM-FFC-U"; // Profile-Update Active Family Constellation Member Family Financial
    public static String JCW_PRF_FAM_FF_U = "JCW-PRF-FAM-FF-U"; // Profile-Update Active Family Member Family Financial
    public static String JCW_PRF_FAM_GI_U = "JCW-PRF-FAM-GI-U"; // Profile-Create New Family Constellation Members Guardian Information
    public static String JCW_PRF_FAM_INS_U = "JCW-PRF-FAM-INS-U"; // Profile-Update Active Family Member Insurance
    public static String JCW_PRF_FAM_MI_U = "JCW-PRF-FAM-MI-U"; // Profile-Update Active Family Member Information
    public static String JCW_PRF_FAM_SUM_U = "JCW-PRF-FAM-SUM-U"; // Profile-Create New Family Constellation Members Summary
    public static String JCW_PRF_FAM_TR_U = "JCW-PRF-FAM-TR-U"; // Profile-Update Active Family Member Traits
    public static String JCW_PRF_FAMILY_U = "JCW-PRF-FAMILY-U"; // Profile-Family Update
    public static String JCW_PRF_FAM_SSN_U = "JCW-PRF-FAM-SSN-U"; //Profile-Family Member-SSN Update - US 39892

    public static String JCW_PRF_MAS_ABUSE_U = "JCW-PRF-MAS-ABUSE-U"; // Profile-Master Abuse Update
    public static String JCW_PRF_MAS_DUALSTATUS_U = "JCW-PRF-MAS-DUALSTATUS-U"; // Profile-Master DUALSTATUS Update
    public static String JCW_PRF_MAS_BEN_U = "JCW-PRF-MAS-BEN-U"; // Profile-Master Benefits Update
    public static String JCW_PRF_MAS_DRUG_U = "JCW-PRF-MAS-DRUG-U"; // Profile-Master Drugs Update
    public static String JCW_PRF_MAS_GANG_U = "JCW-PRF-MAS-GANG-U"; // Profile-Master Gangs Update
    public static String JCW_PRF_MAS_JOBS_U = "JCW-PRF-MAS-JOBS-U"; // Profile-Master Jobs Update
    public static String JCW_PRF_MAS_MED_U = "JCW-PRF-MAS-MED-U"; // Profile-Master Medical Update
    public static String JCW_PRF_MAS_MH_U = "JCW-PRF-MAS-MH-U"; // Profile-Master Mental Health Update
    public static String JCW_PRF_MAS_PI_U = "JCW-PRF-MAS-PI-U"; // Profile-Master Profile Info Update
    public static String JCW_PRF_MAS_PI_ID_V = "JCW-PRF-MAS-PI-ID-V"; // Profile-Master Profile Info View to restrict ID details- US 163083
    public static String JCW_PRF_MAS_SSN_U = "JCW-PRF-MAS-SSN-U"; //Profile-Master Profile-SSN Update - US 39892
    //Changes for JIMS200077276 Starts
    public static String JCW_PRF_MAS_SCL_U = "JCW-PRF-MAS-SCL-U"; // Profile-Master School Update
    //Changes for JIMS200077276 Ends
    public static String JCW_PRF_MAS_SCL_STU_U = "JCW-PRF-MAS-SCL-STU-U"; // Profile-View Master Tab-School STUDENT ID
    public static String JCW_JP_ENABLEMASTERTAB_CLOSEDJUV = "JCW-JP-ENABLEMASTERTAB-CLOSEDJUV"; //US 101337 Update profile of CLOSED Juv
    public static String JCW_PRF_MAS_SI_U = "JCW-PRF-MAS-SI-U"; // Profile-Master Special Interest Update
    public static String JCW_PRF_MH_DSM_U = "JCW-PRF-MH-DSM-U"; // Profile-Mental Health DSM IV Test Update
    public static String JCW_PRF_MH_IQ_U = "JCW-PRF-MH-IQ-U"; // Profile-Mental Health IQ Test Update
    public static String JCW_PRF_MH_AT_U = "JCW-PRF-MH-AT-U"; // Profile-Mental Health Achievement Test Update
    public static String JCW_PRF_MH_AF_U = "JCW-PRF-MH-AF-U"; // Profile-Mental Health Adaptive Functioning Test Update
    public static String JCW_PRF_MH_AB_U = "JCW-PRF-MH-AB-U"; // Profile-Mental Health Adaptive Behavior Test Update
    public static String JCW_PRF_MH_TEST_U = "JCW-PRF-MH-TEST-U"; // Profile-Mental Health Testing Session Update
    public static String JCW_PRF_PGMREF_U = "JCW-PRF-PGMREF-U"; // Profile-Program Referral Update
    public static String JCW_PRF_RULE_U = "JCW-PRF-RULE-U"; // Profile-Rules Update
    public static String JCW_PRF_MAS_VEP_U = "JCW-PRF-MAS-VEP-U"; // Profile-Education-VEP Update
    public static String JCW_PRF_TRAITS_U = "JCW-PRF-TRAITS-U"; // Profile-Traits Update
    public static String JCW_PRF_MASTER_U = "JCW-PRF-MASTER-U"; // Profile-Update Juvenile Master
    public static String JCW_ALIAS_DELETE = "JCW-ALIAS-DELETE"; // Profile-Remove Juvenile Alias
    public static String JCW_PRF_MED_HOSP_U = "JCW-PRF-MED-HOSP-U"; // Profile-Update Medical Hospitalization
    public static String JCW_PRF_MED_ISS_U = "JCW-PRF-MED-ISS-U"; // Profile-Update Medical Issues
    public static String JCW_PRF_MED_MEDS_U = "JCW-PRF-MED-MEDS-U"; // Profile-Update Medication
    public static String JCW_PRF_MED_TRA_U = "JCW-PRF-MED-TRA-U"; // Profile-Update Medical Traits
    public static String JCW_PRF_MH_HOSP_U = "JCW-PRF-MH-HOSP-U"; // Profile-Update Mental Health Hospitalization
    public static String JCW_PRF_MH_MAYSIN_U = "JCW-PRF-MH-MAYSIN-U"; // Profile-Update MAYSI New Assessment
    public static String JCW_PRF_MH_MAYSIS_U = "JCW-PRF-MH-MAYSIS-U"; // Profile-Update MAYSI Subsequent Assessment
    public static String JCW_PRF_MH_MAYSI_U = "JCW-PRF-MH-MAYSI-U"; // Profile-Update Mental Health MAYSI

    // JUVENILE CASEWORK - Profile Tabs features
    public static String JCW_PRFV_ASSREF = "JCW-PRFV-ASSREF"; // Profile-View Assigned Referral Tab
    public static String JCW_PRFV_CASEFILE = "JCW-PRFV-CASEFILE"; // Profile-View Casefiles Tab
    public static String JCW_PRFV_CASEPLAN = "JCW-PRFV-CASEPLAN"; // Profile-View Caseplan Tab
    public static String JCW_PRFV_CONTACTS = "JCW-PRFV-CONTACTS"; // Profile-View Contacts Tab
    public static String JCW_PRFV_FAMILY = "JCW-PRFV-FAMILY"; // Profile-View Family Tab
    public static String JCW_PRFV_MAS_ABUSE = "JCW-PRFV-MAS-ABUSE"; // Profile-View Master Tab-Abuse
    public static String JCW_PRFV_MAS_DUALSTATUS = "JCW-PRFV-MAS-DUALSTATUS";// Profile-View Master Tab-Dual Status
    public static String JCW_PRFV_MAS_BEN = "JCW-PRFV-MAS-BEN"; // Profile-View Master Tab-Benefits
    public static String JCW_PRFV_MAS_BEN_INS_FAMFIN = "JCW-PRFV-MAS-BEN-INS-FAMFIN"; // Profile-View Master Tab - Benefits, Insurance, Family Financial
    public static String JCW_PRFV_MAS_DRUG = "JCW-PRFV-MAS-DRUG"; // Profile-View Master Tab-Drugs
    public static String JCW_PRFV_MAS_ADD_DRUG = "JCW-PRFV-MAS-ADD-DRUG"; // Profile-View Master Tab-Add Drugs
    public static String JCW_PRFV_MAS_GANG = "JCW-PRFV-MAS-GANG"; // Profile-View Master Tab-Gangs
    public static String JCW_PRFV_MAS_JOBS = "JCW-PRFV-MAS-JOBS"; // Profile-View Master Tab-Jobs
    public static String JCW_PRFV_MAS_MED = "JCW-PRFV-MAS-MED"; // Profile-View Master Tab-Medical
    public static String JCW_PRFV_MAS_MH = "JCW-PRFV-MAS-MH"; // Profile-View Master Tab-Mental Health
    public static String JCW_PRFV_MAS_PI = "JCW-PRFV-MAS-PI"; // Profile-View Master Tab-Profile Info
    public static String JCW_PRFV_MAS_SCL = "JCW-PRFV-MAS-SCL"; // Profile-View Master Tab-School
    public static String JCW_PRFV_MAS_VEP = "JCW-PRFV-MAS-VEP"; // Profile-View Master Tab-VEP
    public static String JCW_PRFV_MAS_TEST = "JCW-PRFV-MAS-TEST"; // Profile-View Master Tab-Testing
    public static String JCW_PRFV_MAS_SI = "JCW-PRFV-MAS-SI"; // Profile-View Master Tab-Special Interest
    public static String JCW_PRFV_MASTER = "JCW-PRFV-MASTER"; // Profile-View Master Tab
    public static String JCW_PRFV_MH_AF = "JCW-PRFV-MH-AF"; // Profile-View Mental Health Adaptive Functioning Tab
    public static String JCW_PRFV_MH_AB = "JCW-PRFV-MH-AB"; // Profile-View Mental Health Adaptive Behavior Tab
    public static String JCW_PRFV_MH_AT = "JCW-PRFV-MH-AT"; // Profile-View Mental Health Achievement Tab
    public static String JCW_PRFV_MH_DSM = "JCW-PRFV-MH-DSM"; // Profile-View Mental Health DSM IV Tab
    public static String JCW_PRFV_MH_IQ = "JCW-PRFV-MH-IQ"; // Profile-View Mental Health IQ Tab
    public static String JCW_PRFV_MH_TEST = "JCW-PRFV-MH-TEST"; // Profile-View Mental Health Testing Session Tab
    public static String JCW_PRFV_PGMREF = "JCW-PRFV-PGMREF"; // Profile-View Program Referral Tab
    public static String JCW_PRFV_RULE = "JCW-PRFV-RULE"; // Profile-View Rules Tab
    public static String JCW_PRFV_TRAITS = "JCW-PRFV-TRAITS"; // Profile-View Traits Tab
    public static String JCW_PRFV_MED_HOSP = "JCW-PRFV-MED-HOSP"; // Profile-View Medical Hospitalization
    public static String JCW_PRFV_MED_ISS = "JCW-PRFV-MED-ISS"; // Profile-View Medical Issues
    public static String JCW_PRFV_MED_MEDS = "JCW-PRFV-MED-MEDS"; // Profile-View Medication
    public static String JCW_PRFV_MED_TRA = "JCW-PRFV-MED-TRA"; // Profile-View Medical Traits
    public static String JCW_PRFV_MH_HOSP = "JCW-PRFV-MH-HOSP"; // Profile-View Mental Health Hospitalization Tab
    public static String JCW_PRFV_MH_MAYSI_N = "JCW-PRFV-MH-MAYSI-N"; // Profile-View MAYSI New Assessment
    public static String JCW_PRFV_MH_MAYSI = "JCW-PRFV-MH-MAYSI"; // Profile-View Mental Health MAYSI Tab
    public static String JCW_PRFV_MH_MAYSI_S = "JCW-PRFV-MH-MAYSI-S"; // Profile-View MAYSI Subsequent Assessment
    public static String JCW_PRFV_JOU = "JCW-PRFV-JOU"; // Profile-View Journals 
    public static String JCW_PRFV_DOC = "JCW-PRFV-DOC"; // Profile-View Documents 
    public static String JCW_PRFV_ASMTREQ = "JCW-PRFV-ASMTREQ"; // Profile-View Assessment Request 
    public static String JCW_PRFV_ARL = "JCW-PRFV-ARL"; // Profile-View Assessment Referral Link 
    public static String JCW_PRFV_FACILITY_HEADER = "JCW-PRFV-FACILITY-HEADER"; // Profile-View Facility Header Section

    public static String JCW_CFC_JUV_FUTURE_EVENTS = "JCW-CFC-JUV-FUTURE-EVENTS"; //Casefile-Calendar-Cancel Future Juvenile Events
    public static String JCW_CFC_SP_FUTURE_EVENTS = "JCW-CFC-SP-FUTURE-EVENTS"; //Casefile-Calendar-Cancel Future Service Provider Events
    public static String JCW_CFC_JUV_SERV_PROVIDER_EVENTS_ONLY = "JCW-CFC-JUV-SERV-PROVIDER-EVENTS-ONLY"; //Casefile-Calendar-Cancel Future Service Provider Events
    public static String JCW_CFC_SP_EVENTS_MORETHAN7DAYSOLD = "JCW-CFC-SP-EVENTS-MORETHAN7DAYSOLD"; //Casefile-Calendar-Schedule Service Provider Events more than 7 days old
    public static String JCW_BRF_DET_VISITATION = "JCW-BRF-DET-VISITATION";//Casework Briefing screen and Process Juvenile Facility Admission screen

    // JUVENILE CASEWORK - NonCompliance features
    public static String JCW_CFV_NONCOMPLIANCE = "JCW-CFV-NONCOMPLIANCE"; // Non compliance Tab display
    public static String JCW_CF_NONCOMPLIANCE_U = "JCW-CF-NONCOMPLIANCE-U"; // Non compliance update

    // JUVENILE CASEWORK - Transferred offenses features
    public static String JCW_CF_TRANSOFF_VIEW = "JCW-CF-TRANSOFF-V"; // Transferred Offenses Tab display
    public static String JCW_CF_TRANSOFF_ADD = "JCW-CF-TRANSOFF-ADD"; // Transferred Offenses Add

    // JUVENILE CASEWORK - Violation of Probation features
    public static String JCW_CF_VOP_CREATE = "JCW-CF-VOP-CREATE";
    public static String JCW_CF_VOP_DETAILS = "JCW-CF-VOP-DETAILS";

    public static String JCW_CFV_VIEW_MAP = "JCW-CFV-VIEW-MAP";

    //JUVENILE FACILITY
    public static String JFA_BOOKING_TRANSFER = "JFA-BT-V"; // BOOKING TRANSFER VIEW
    public static String JFA_TEMPORARY_RELEASE_DECISION = "JFA-TEMPORARYRELEASEDECISION"; // Temporary Release Decision
    public static String JFA_DETAILS_UPDATE = "JFA-DETAILS-U"; // FACILITY BRIEFING DETAILS PAGE VIEW only
    public static String JFA_MODIFY_ADMIT_REASON = "JFA-MODIFY-ADMIT-REASON"; // JFA-MODIFY-ADMIT-REASON

    // COMMON SUPERVISION
    public static String CS_POSV_POSITIONS = "CS-POSV-POSITIONS"; // Common Supervision Position TAB
    public static String CS_POS_VIEW_CSC = "CS-POS-VIEW-CSC"; // Common Supervision Position View
    public static String CS_POS_CREATE_CSC = "CS-POS-CREATE-CSC"; // Common Supervision Position create
    public static String CS_POS_UPDATE_CSC = "CS-POS-UPDATE-CSC"; // Common Supervision Position update
    public static String CS_POS_ASSIGN_CSC = "CS-POS-ASSIGN-CSC"; // Common Supervision Position assign
    public static String CS_POS_REASSIGN_CSC = "CS-POS-REASSIGN-CSC"; // Common Supervision Position reassing
    public static String CS_POS_VACATE_CSC = "CS-POS-VACATE-CSC"; // Common Supervision Position vacate
    public static String CS_POS_RETIRE_CSC = "CS-POS-RETIRE-CSC"; // Common Supervision Position Retire
    public static String CS_POS_REPORTS_CSC = "CS-POS-REPORTS-CSC"; // Common Supervision Generate Reports
    public static String CS_CASENOTE_CREATE = "CS-CASENOTE-CREATE"; // Common Supervision Casenote create

    // COMMUNITY SUPERVISION  CSCD	
    public static String CS_COMPLIANCE_ACCESS = "CSCD-COMP-ACCESS"; // Community Supervision Allow Access To Compliance
    public static String CS_COMPLIANCE_DECREMENT = "CSCD-COMP-DECREMENT"; // Community Supervision Condition Decrement	
    public static String CSCD_VIOLATION_REPORT_ACCESS = "CSCD-VIOL-RPT-ACCESS"; // Community Supervision Allow Violation Report Access
    public static String CSCD_VIOLATION_REPORT_CREATE = "CSCD-VIOL-RPT-CREATE"; // Community Supervision Allow Violation Report Create
    public static String CSCD_VIOLATION_REPORT_DELETE = "CSCD-VIOL-RPT-DELETE"; // Community Supervision Allow Violation Report Delete
    public static String CSCD_CASE_SUMMARY_ACCESS = "CSCD-CASE-SUM-ACCESS"; // Community Supervision Allow Case Summary Access
    public static String CSCD_CASE_SUMMARY_CREATE = "CSCD-CASE-SUM-CREATE"; // Community Supervision Allow Case Summary Report Create
    public static String CSCD_CASE_SUMMARY_DELETE = "CSCD-CASE-SUM-DELETE"; // Community Supervision Allow Case Summary Delete
    public static String CSCD_MOTIONS_ACCESS = "CSCD-MOTIONS-ACCESS"; // Community Supervision Allow Motions Access
    public static String CSCD_NONCOMPLIANCE_MAINT_ACCESS = "CSCD-NONCOMP-MAINT"; // Community Supervision Allow Maintenance Access in VRpt and Case Summary
    public static String CSCD_CAL_COT_DELETE = "CSCD-CAL-COT-DELETE"; //Community Supervision  Delete Closed Other Visit
    public static String CSCD_CAL_OOT_DELETE = "CSCD-CAL-OOT-DELETE"; //Community Supervision Delete Open Other Visit
    public static String CSCD_CAL_OT_CREATE = "CSCD-CAL-OT-CREATE"; //Community Supervision Administer Other Visit
    public static String CSCD_CAL_OT_RESCHEDULE = "CSCD-CAL-OT-RESCHEDULE"; //Community Supervision Reschedule Other Visit 
    public static String CSCD_CAL_OT_UPDATE = "CSCD-CAL-OT-UPDATE"; //Community Supervision Administer Other Visit Update
    public static String CSCD_CAL_OT_VIEW = "CSCD-CAL-OT-VIEW"; //Community Supervision View Other DIV Calendar
    public static String CSCD_CAL_OTR_CREATE = "CSCD-CAL-OTR-CREATE"; //Community Supervision Administer Other Visit Result
    public static String CSCD_CAL_VIEW = "CSCD-CAL-VIEW"; //Community Supervision View CSCD Calendar
    public static String CSCD_CALCFV_DELETE = "CSCD-CALCFV-DELETE"; //Community Supervision Delete Closed Field Visit
    public static String CSCD_CALCOV_DELETE = "CSCD-CALCOV-DELETE"; //Community Supervision Delete Closed Office Visit
    public static String CSCD_CALFV_CREATE = "CSCD-CALFV-CREATE"; //Community Supervision Administer Field Visit 
    public static String CSCD_CALFV_RESCHEDULE = "CSCD-CALFV-RESCHEDULE"; //Community Supervision Reschedule Field Visit
    public static String CSCD_CALFV_UPDATE = "CSCD-CALFV-UPDATE"; //Community Supervision  Administer Field Visit Update
    public static String CSCD_CALFVR_CREATE = "CSCD-CALFVR-CREATE"; //Community Supervision Administer Field Visit Result 
    public static String CSCD_CALGOV_CREATE = "CSCD-CALGOV-CREATE"; //Community Supervision  Administer GROUP Office Visit
    public static String CSCD_CALOFV_DELETE = "CSCD-CALOFV-DELETE"; //Community Supervision Delete Open Field Visit
    public static String CSCD_CALOOV_DELETE = "CSCD-CALOOV-DELETE"; //Community Supervision Delete Open Office Visit
    public static String CSCD_CALOV_CREATE = "CSCD-CALOV-CREATE"; //Community Supervision Administer Office Visit
    public static String CSCD_CALOV_RESCHEDULE = "CSCD-CALOV-RESCHEDULE"; //Community Supervision Reschedule Office Visit
    public static String CSCD_CALOV_UPDATE = "CSCD-CALOV-UPDATE"; //Community Supervision Administer Office Visit Update
    public static String CSCD_CALOVR_CREATE = "CSCD-CALOVR-CREATE"; //Community Supervision Administer Office Visit Result
    public static String CSCD_CALRP_CREATE = "CSCD-CALRP-CREATE"; //Community Supervision View Calendar Report
    public static String CSCD_CALFV_QUAD_SEARCH = "CSCD-CALFV-QUAD-SEARCH"; //Community Supervision  Administer Field Visit Quardrant Search
    public static String CSCD_CALFV_ZIP_SEARCH = "CSCD-CALFV-ZIP-SEARCH"; //Community Supervision  Administer Field Visit ZIP Search

    public static String CSCD_CASELOAD_SEARCH = "CSCD-CASELOAD-SEARCH"; //Community Supervision Administer Caseload Search
    public static String CSCD_SETUP_SEARCH = "CSCD-SETUP-SEARCH"; //Community Supervision SETUP Search
    public static String CSCD_CASENOTES_SEARCH = "CSCD-CASENOTES-SEARCH"; //Community Supervision Administer Casenotes Search

    public static String CSCD_CASELOAD_ADMIN = "CSCD-CASELOAD-ADMIN"; //Community Supervision Administer Caseload

    // COMMON FUNCTIONALITY
    public static String CF_UPDATE_CODETABLE = "CF-CODE-U"; // Common Functionality Update Code Table

    public static String CS_ASP_VIEW_CSC = "CS-ASP-VIEW-CSC"; // Common Supervision ASP  View
    public static String CS_ASP_CREATE_CSC = "CS-ASP-CREATE-CSC"; // Common Supervision ASP create
    public static String CS_ASP_UPDATE_CSC = "CS-ASP-UPDATE-CSC"; // Common Supervision ASP update
    public static String CS_ASP_INACTIVATE_CSC = "CS-ASP-INACTIVATE-CSC"; // Common Supervision ASP inactivate
    public static String CS_ASP_SEARCH_CSC = "CS-ASP-SEARCH-CSC"; // Common Supervision ASP inactivate
    //	COMMUNITY SUPERVISION (CSCD)
    public static String CSCD_LOC_VIEW = "CSCD-LOC-VIEW"; // CSCD Location View
    public static String CSCD_LOC_SEARCH = "CSCD-LOC-SEARCH"; //CSCD Location Search
    public static String CSCD_LOC_CREATE = "CSCD-LOC-CREATE"; // CSCD Location Create
    public static String CSCD_LOC_UPDATE = "CSCD-LOC-UPDATE"; // CSCD Location Update
    public static String CSCD_LOC_ACTIVATE = "CSCD-LOC-ACTIVATE"; // CSCD Location Activate
    public static String CSCD_LOC_INACTIVATE = "CSCD-LOC-INACTIVATE"; // CSCD Location Inactivate

    //	COMMUNITY SUPERVISION (CSCD) - ASSESSMENTS
    public static String CSCD_ASSESS_ACCESS = "CSCD-ASSESS-ACCESS"; // CSCD Assessments - Access to assessments Tab/Button
    public static String CSCD_ASSESS_CREATE_INITIAL = "CSCD-ASSESS-CREATE-INITIAL"; // CSCD Assessments - create Initial Assessment
    public static String CSCD_ASSESS_CREATE_REASSESS = "CSCD-ASSESS-CREATE-REASSESS"; // CSCD Assessments - Create Reassessment
    public static String CSCD_ASSESS_UPDATE_WITHOUT_RESTRC = "CSCD-ASSESS-UPDATE-WITHOUT-RESTRC"; // CSCD Assessments - Update Complete/Incomplete assessments
    public static String CSCD_ASSESS_UPDATE_WITH_RESTRC = "CSCD-ASSESS-UPDATE-WITH-RESTRC"; // CSCD Assessments - Update only Incomplete assessments
    public static String CSCD_ASSESS_VIEW = "CSCD-ASSESS-VIEW"; // CSCD Assessments - View Details
    public static String CSCD_ASSESS_VIEW_HISTORY = "CSCD-ASSESS-VIEW-HISTORY"; // CSCD Assessments - View Assessments that have been versioned
    public static String CSCD_ASSESS_VIEW_PRV_SUP_PRD = "CSCD-ASSESS-VIEW-PRV-SUP-PRD"; // CSCD Assessments - View Assessments from previous supervision period
    public static String CSCD_ASSESS_DELETE_WITHOUT_RESTRC = "CSCD-ASSESS-DELETE-WITHOUT-RESTRC"; // CSCD Assessments - Delete any assessment i.e delete an assessment (sent to/not sent to) state 
    public static String CSCD_ASSESS_DELETE_WITH_RESTRC = "CSCD-ASSESS-DELETE-WITH-RESTRC"; // CSCD Assessments - Delete an assessment that has not been sent to state

    //	COMMUNITY SUPERVISION (CSCD) - ADMINISTER SUPERVISION PLAN
    public static String CSCD_SUPPLAN_VIEW = "CSCD-SUPPLAN-VIEW";
    public static String CSCD_SUPPLAN_CREATE = "CSCD-SUPPLAN-CREATE";
    public static String CSCD_SUPPLAN_UPDATE = "CSCD-SUPPLAN-UPDATE";
    public static String CSCD_SUPPLAN_COPY = "CSCD-SUPPLAN-COPY";
    public static String CSCD_SUPPLAN_DELETE_WTHOUT_RESTRC = "CSCD-SUPPLAN-DELETE-WTHOUT-RESTRC";
    public static String CSCD_SUPPLAN_DELETE_WITH_RESTRC = "CSCD-SUPPLAN-DELETE-WITH-RESTRC";

    //  COMMUNITY SUPERVISION (CSCD) - ADMINISTER SUPERVISEE
    public static String CSCD_SUPERVISEE_DNA_ADD = "CSCD-SUPERVISEE-DNA-ADD";
    public static String CSCD_SUPERVISEE_DNA_CORRECT = "CSCD-SUPERVISEE-DNA-CORRECT";
    public static String CSCD_SUPERVISEE_DNA_VIEW = "CSCD-SUPERVISEE-DNA-VIEW";
    public static String CSCD_SUPERVISEE_DNA_DELETE = "CSCD-SUPERVISEE-DNA-DELETE";
    public static String CSCD_SUPERVISEE_LOS_VIEW_HISTORY = "CSCD-SUPERVISEE-LOS-VIEW-HISTORY";
    public static String CSCD_SUPERVISEE_LOS_ADD = "CSCD-SUPERVISEE-LOS-ADD";
    public static String CSCD_SUPERVISEE_LOS_CORRECT = "CSCD-SUPERVISEE-LOS-CORRECT";
    public static String CSCD_SUPERVISEE_LOS_DELETE_WITHOUT_RESTRC = "CSCD-SUPERVISEE-LOS-DELETE-WITHOUT-RESTRC";//Delete an LOS that has been sent to state.
    public static String CSCD_SUPERVISEE_LOS_DELETE_WITH_RESTRC = "CSCD-SUPERVISEE-LOS-DELETE-WITH-RESTRC";//Delete an LOS that has not been sent to state.
    public static String CSCD_SUPERVISEE_PROGRAMTRACKER_VIEW_HISTORY = "CSCD-SUPERVISEE-PROGRAMTRACKER-VIEW-HISTORY";
    public static String CSCD_SUPERVISEE_PROGRAMTRACKER_ADD = "CSCD-SUPERVISEE-PROGRAMTRACKER-ADD";
    public static String CSCD_SUPERVISEE_PROGRAMTRACKER_CORRECT = "CSCD-SUPERVISEE-PROGRAMTRACKER-CORRECT";
    public static String CSCD_SUPERVISEE_PROGRAMTRACKER_DELETE = "CSCD-SUPERVISEE-PROGRAMTRACKER-DELETE";
    public static String CSCD_SUPERVISEE_PROGRAMTRACKER_REMOVE = "CSCD-SUPERVISEE-PROGRAMTRACKER-REMOVE";

    //	COMMUNITY SUPERVISION (CSCD) - MANAGE TASKS
    public static String CSCD_TASKS_CLOSE_ADV = "CSCD-TASKS-CLOSE-ADV";
    public static String CSCD_TASKS_CREATE = "CSCD-TASKS-CREATE";
    public static String CSCD_TASKS_VIEW_ADV = "CSCD-TASKS-VIEWALL";

    //	COMMUNITY SUPERVISION (CSCD) - CLOSE SUPERVISION (HARRIS COUNTY CASE)
    public static final String CSCD_CLOSE_SUPERVISION = "CSCD-CLOSE-SUPERVISION";

    //	COMMON SUPERVISION - CLOSE SUPERVISION (OOC CASE)
    public static final String CSCD_OOC_CLOSE_CASE = "CSCD-OOC-CLOSE-CASE";
    public static final String CSCD_OOC_UPDATE_CLOSURE = "CSCD-OOC-UPDATE-CLOSURE";

    //  COMMON SUPERVISION - LOCATION
    public static final String CS_LOC_ACTIVATE = "CS-LOC-ACTIVATE";
    public static final String CS_LOC_CREATE = "CS-LOC-CREATE";
    public static final String CS_LOC_INACTIVATE = "CS-LOC-INACTIVATE";

    //  JUVENILE WARRANTS (ALL JUVENILE WARRANTS)
    public static final String JW_INACTIVATE = "JW-INACTIVATE"; // Inactivate Warrant
    public static final String JW_RECALL = "JW-RECALL"; // Recall Warrant
    public static final String JW_VIEW = "JW-VIEW"; // View Warrant

    //  JUVENILE WARRANTS (ARREST WARRANT)
    public static final String JW_ARR_INIT = "JW-ARR-INIT"; // Initiate Arrest Warrant
    public static final String JW_ARR_REQACT = "JW-ARR-REQACT"; //Activate Arrest Warrant

    //  JUVENILE WARRANTS (DIRECTIVE TO APPREHEND WARRANT)
    public static final String JW_DTA_INIT = "JW-DTA-INIT"; // Initiate Directive to Apprehend Warrant
    public static final String JW_DTA_REQACK = "JW-DTA-REQACK"; // Acknowledge Directive to Apprehend Warrant
    public static final String JW_DTA_REQACT = "JW-DTA-REQACT"; // Activate Directive to Apprehend Warrant

    //  JUVENILE WARRANTS (ORDER OF IMMEDIATE CUSTODY WARRANT)
    public static final String JW_OIC_INIT = "JW-OIC-INIT"; // Initiate Order of Immediate Custody Warrant
    public static final String JW_OIC_REQSIG = "JW-OIC-REQSIG"; // Update Signature Order of Immediate Custody Warrant
    public static final String JW_OIC_UPDATE = "JW-OIC-UPDATE"; // Update Order of Immediate Custody Warrant

    //  JUVENILE WARRANTS (PROBABLE CAUSE WARRANT)
    public static final String JW_PC_INIT = "JW-PC-INIT"; // Initiate Probable Cause Warrant

    //  JUVENILE WARRANTS (VIOLATION OF PROBATION WARRANT)
    public static final String JW_VOP_INIT = "JW-VOP-INIT"; // Initiate Violation of Probation Warrant
    public static final String JW_VOP_REQACT = "JW-VOP-REQACT"; // Activate Violation of Probation Warrant
    public static final String JW_VOP_UPDATE = "JW-VOP-UPDATE"; // Update Violation of Probation Warrant

    //  JUVENILE WARRANTS (RELEASE DECISION)
    public static final String JW_REL_DECISION = "JW-REL-DECISION"; // Enter Release Decision
    public static final String JW_REL_TO_JP = "JW-REL-TO-JP"; // Enter Release to JP Information
    public static final String JW_REL_TO_PER = "JW-REL-TO-PER"; // Enter Release to Person Information

    //  JUVENILE WARRANTS (WARRANT SERVICE)
    public static final String JW_WARRANT_SRVC = "JW-WARRANT-SRVC"; // Update Warrant Service
    public static final String JW_PROCESS_RTRN = "JW-PROCESS-RTRN"; // Process Return of Service
    public static final String JW_REQ_RTRN = "JW-REQ-RTRN"; // File Return of Service Signature

    //	COMMUNITY SUPERVISION (CSCD) - PROGRAM REFERRALS
    public static String CSCD_PRG_REF_INITIATE = "CSCD-PROG-REF-INITIATE"; // CSCD Program Referral - Initiate Referral
    public static String CSCD_PRG_REF_REREFER = "CSCD-PROG-REF-REREFER"; // CSCD Program Referral - Re-referral
    public static String CSCD_PRG_REF_VIEW = "CSCD-PROG-REF-VIEW"; // CSCD Program Referral - View Referral
    public static String CSCD_PRG_TEN_REF_ACCEPT_CLOSE = "CSCD-PROG-TEN-REF-ACCEPT-CLOSE"; // CSCD Program Referral - Accept n Close Tentative Referral //Task 52635
    public static String CSCD_PRG_REF_UPDATE = "CSCD-PROG-REF-UPDATE"; // CSCD Program Referral - Update Referral
    public static String CSCD_PRG_REF_SUBMIT = "CSCD-PROG-REF-SUBMIT"; // CSCD Program Referral - Submit Referral
    public static String CSCD_PRG_REF_EXIT = "CSCD-PROG-REF-EXIT"; // CSCD Program Referral - Exit Referral
    public static String CSCD_PRG_REF_DELETE = "CSCD-PROG-REF-DELETE"; // CSCD Program Referral - Delete Referral
    public static String CSCD_PRG_REF_REM_EXT_ENT_WITHOUT_RESTRC = "CSCD-PROG-REF-REM-ENT-EXT-WITOUT-RESTRC"; // CSCD Program Referral - remove entry/exit on Referral sent to/not sent to state 
    public static String CSCD_PRG_REF_REM_EXT_ENT_WITH_RESTRC = "CSCD-PROG-REF-REM-ENT-EXT-WTH-RESTRC"; // CSCD Program Referral - remove entry/exit on Referral not sent to state 

    //	COMMUNITY SUPERVISION (CSCD) - DATA ADMINISTRATION
    public static String CSCD_CAS_ASSIGN_DATA_CNTL = "CSCD-CAS-ASSIGN-DATA-CNTL"; // CSCD Case Assignment Data Control
    public static String CSCD_SUP_CREDIT_DATA_CNTL = "CSCD-SUP-CREDIT-DATA-CNTL"; // Supervisee Credit Data Control	

    //	COMMUNITY SUPERVISION (CMSP) - PASO
    public static String CS_ORDER_ALLOW_WITHDRAWAL = "CS-ORDER-ALLOW-WITHDRAWAL"; // CMSP PASO Supervision Order - Allow Pending Order Withdrawel

    // PRODUCTION SUPPORT
    public static String JCW_SEALJUVRECORD = "JCW-SEALJUVRECORD";
    public static String JCW_PS_UPDATEREFERRAL = "JCW-PS-UPDATEREFERRAL";
    public static String JCW_PS_UPDATEHEADER = "JCW-PS-UPDATEHEADER";
    public static String JCW_PS_UPDATEDISTRICTCOURTCALENDARRECORD = "JCW-PS-UPDATEDISTRICTCOURTCALENDARRECORD";
    public static String JCW_PS_UPDATEJUVENILEMASTER = "JCW-PS-UPDATEJUVENILEMASTER";
    public static String JCW_PS_UPDATEJUVENILENUM = "JCW-PS-UPDATEJUVENILENUM";
    public static String JCW_PS_REFERRALOFFENSEPROCESSING = "JCW-PS-REFERRALOFFENSEPROCESSING";
    public static String JCW_PS_REFERRALOFFENSEPROCESSING_MASTER = "JCW-PS-REFERRALOFFENSEPROCESSING-MASTER";
    public static String JCW_PS_UPDATEPETITIONDETAILS = "JCW-PS-UPDATEPETITIONDETAILS";
    public static String JCW_PS_UPDATEDETENTIONCOURTRECORD = "JCW-PS-UPDATEDETENTIONCOURTRECORD";
    public static String JCW_PS_TERMINATIONDATE = "JCW-PS-TERMINATIONDATE";
    //task 101380
    public static String JCW_PS_REFERRALUPDATE_MASTER = "JCW-PS-REFERRALUPDATE-MASTER";
    //104062 - 104063
    public static String JCW_PS_INTAKEHISTORY = "JCW-PS-INTAKEHISTORY";
    public static String JCW_PS_INTAKEHISTORYFULLACCESS = "JCW-PS-INTAKEHISTORYFULLACCESS";
    //106728
    public static String JCW_PS_SCANNED_DOCUMENTS = "JCW-PS-SCANNED-DOCUMENTS";
    //106275
    public static String JCW_PS_UPDATELASTATTORNEY = "JCW-PS-UPDATELASTATTORNEY";
    public static String JCW_PS_ANCILLARYCOURT = "JCW-PS-ANCILLARYCOURT";
    public static String JCW_PS_DELETECOURT = "JCW-PS-DELETECOURT";
    public static String JCW_PS_DELETEDETENTIONRECORD = "JCW-PS-DELETEDETENTIONRECORD";
    public static String JCW_PS_REFERRALDELETION = "JCW–PS–REFERRALDELETION";
    public static String JCW_PS_UPDATEACTIVITY = "JCW-PS-UPDATEACTIVITY";
    public static String JCW_PS_UPDATEFAMILYMEMBERDETAILS = "JCW-PS-UPDATEFAMILYMEMBERDETAILS";
    public static String JCW_PS_EVENTMOVEPROGRAM = "JCW-PS-EVENTMOVEPROGRAM"; //178787

    //JUVENILE COURT
    public static String JCW_DELETE_PETITION = "JCW-DELETE-PETITION";
    public static String JCW_CRTACTN_DELETE = "JCW-CRTACTN-D";
    public static String JCW_CRTACTN_UPDATE = "JCW-CRTACTN-U";
    public static String JCW_CRTACTN = "JCW-CRTACTN";
    public static String JCW_CRTACTVTYYOUTH = "JCW-CRTACTVTYYOUTH";
    public static String JCW_CRTPETCJIS = "JCW-CRTPETCJIS";
    public static String JCW_CRTDALOGNUM = "JCW-CRTDALOGNUM";
    public static String JCW_CRTANCIADD = "JCW-CRTANCIADD";
    public static String JCW_CRTANCIDISP = "JCW-CRTANCIDISP";
    public static String JCW_CRTANCILACTVTY = "JCW-CRTANCILACTVTY";
    public static String JCW_CRTDCKTDISP = "JCW-CRTDCKTDISP";
    public static String JCW_CRTINISETTNG = "JCW-CRTINISETTNG";
    public static String JCW_CRTMASBRIEFG = "JCW-CRTMASBRIEFG";
    public static String JCW_CRTNAMESEAR = "JCW-CRTNAMESEAR";
    public static String JCW_CRTNUMINQ = "JCW-CRTNUMINQ";
    public static String JCW_CRTPETUPDATE = "JCW-CRTPETUPDATE";
    public static String JCW_CRTREFINQ = "JCW-CRTREFINQ";
    public static String JCW_DISTRICT_ATTORNEY_CONFIRMATION_BYPASS = "JCW-DISTRICT-ATTORNEY-CONFIRMATION-BYPASS";
    public static String JCW_VIEW_COURTORDER = "JCW-VIEW-COURTORDER";

    //DETENTION COURT
    public static String JCW_DETACTN_UPDATE = "JCW-DETACTN-U";
    public static String JCW_DET_ATTORNEY_CONFIRMATION_BYPASS = "JCW-DET-ATTORNEY-CONFIRMATION-BYPASS";
    public static String JCW_SECSERVPROV = "JCW-SECSERVPROV";

    //REFERRALS CONVERSION
    public static String JCW_UPDATEJUVRECORD = "JCW-UPDATEJUVRECORD";
    public static String JCW_CREATEREFERRAL = "JCW-CREATEREFERRAL";
    public static String JCW_UPDATEREFERRAL = "JCW-UPDATEREFERRAL";
    public static String JCW_MANAGEASSIGNMENT = "JCW-MANAGEASSIGNMENT";
    public static String JCW_OVERRIDEASSIGNMENT = "JCW-OVERRIDEASSIGNMENT";
    public static String JCW_UPDATEREFSTAT = "JCW-UPDATEREFSTAT";
    public static String JCW_UPDATEJUVRECWITHCASEFILES = "JCW-UPDATEJUVRECWITHCASEFILES";
    public static String JCW_CREATEJUVENILE = "JCW-CREATEJUVENILE";
    public static String JCW_CREATERESEAL = "JCW-CREATERESEAL";
    public static String JCW_PRINTMASTERDISPLAY = "JCW-PRINTMASTERDISPLAY";
    public static String JCW_SEALPURGE_VIEW = "JCW-SEALPURGE-VIEW";
    public static String JCW_JCW_REF_REALDOB_UPDATE = "JCW-REF-REALDOB-UPDATE";
    public static String JCW_REFERRALVIEW_DALOG_PETITION = "JCW-REFERRALVIEW-DALOG-PETITION";

    //PROFILE UPDATE
    public static String JCW_OFFICERPROFILEJPOUPDATE = "JCW-OFFICERPROFILEJPOUPDATE";
    public static String JCW_OFFICERPROFILEFULLACCESS = "JCW-OFFICERPROFILEFULLACCESS";
    public static String JRP_JUVSSN_U = "JRP-JUVSSN-U";
    public static String JRP_CREATEJUV_EXCJUVRPT_CHK = "JRP-CREATEJUV-EXCJUVRPT-CHK";
    public static String JRP_REFASSIGNMENT_EXCREFRPT_CHK = "JRP-REFASSIGNMENT-EXCREFRPT-CHK";
    public static String MOP_OFFICERTRAINING = "MOP-OFFICERTRAINING";

    //HCJPD
    public static String JCW_SP_SEARCH_FUNDSOURCE = "SP-SEARCH-FUNDSOURCE";
}
