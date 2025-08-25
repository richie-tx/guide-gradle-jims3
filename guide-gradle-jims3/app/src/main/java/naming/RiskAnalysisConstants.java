/*
 * Created on Jun 20, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package naming;

import org.apache.commons.lang.time.FastDateFormat;

/**
 * @author dapte
 *
 */
//public class RiskAnalysisConstants
public interface RiskAnalysisConstants
{
	
	//Date Formats
	public static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("MM/dd/yyyy");
	public static final FastDateFormat FULL_DATE_FORMAT= FastDateFormat.getInstance("MM/dd/yyyy h:mm a");
	
	//Voilent Felony Indicators
	public static final String SERVERITYSUBTYPEINDICATOR_V = "V";
	
	public static final String OFFENSECATEGORY_CF = "CF";
	public static final String OFFENSECATEGORY_F1 = "F1";
	public static final String OFFENSECATEGORY_F2 = "F2";
	public static final String OFFENSECATEGORY_F3 = "F3";
	
	public static final String SEVERITY_6 = "6";
	public static final String SEVERITY_7 = "7";
	public static final String SEVERITY_8 = "8";
	public static final String SEVERITY_9 = "9";
	
	
	//Control Codes for Certain Qustions
	//Application has to know abou these questions through
	//codes so it can set the answers for these questions
	public static final String REFERRAL_CONTROL_CODE_CFT = "CFT";
	public static final String REFERRAL_CONTROL_CODE_MAB = "MAB";
	public static final String REFERRAL_CONTROL_CODE_MCC = "MCC";
	public static final String REFERRAL_CONTROL_CODE_FT1 = "FT1";
	public static final String REFERRAL_CONTROL_CODE_FT2 = "FT2";
	public static final String REFERRAL_CONTROL_CODE_FT3 = "FT3";
	public static final String REFERRAL_CONTROL_CODE_LVT = "LVT";
	public static final String REFERRAL_CONTROL_CODE_RHT = "RHT";
	public static final String REFERRAL_CONTROL_CODE_TOF = "TOF";
	public static final String REFERRAL_CONTROL_CODE_COT = "COT";
	public static final String REFERRAL_CONTROL_CODE_SJF = "SJF";
	public static final String REFERRAL_CONTROL_CODE_YCP = "YCP";
	public static final String REFERRAL_CONTROL_CODE_COP = "COP";
	public static final String REFERRAL_CONTROL_CODE_CCS = "CCS";
	public static final String REFERRAL_CONTROL_CODE_NOC = "NOC";
	public static final String REFERRAL_CONTROL_CODE_TOA = "TOA";
	
	public static final String INTERVIEW_CONTROL_CODE_OSA = "OSA";
	public static final String INTERVIEW_CONTROL_CODE_SEX = "SEX";
	public static final String INTERVIEW_CONTROL_CODE_LATEST_REFERRAL_SCORE = "LRSC";
	
	public static final String COURT_REFERRAL_DOA = "CRDOA";
	public static final String COURT_REFERRAL_AGE_REFERRAL = "CRAGF";
	public static final String COURT_REFERRAL_CODE_SEX = "CRSX";
	public static final String COURT_REFERRAL_JUVENILE_NAME = "CRJN";
	public static final String COURT_REFERRAL_BIRTH_DATE = "CRDB";
	public static final String COURT_REFERRAL_PID_NUMBER = "CRPD";
	public static final String COURT_REFERRAL_HEADCOUNTY = "CRHC";
	public static final String COURT_REFERRAL_TOTAL_REFFERRALS = "CRTR";
	public static final String COURT_REFERRAL_VIOLENT_FELONY = "CRVF";
	public static final String COURT_REFERRAL_REFERRALS_INFORMATION = "CRRND";
	
	public static final String PROGRESS_SUPERVISION_MONTHS = "PCSM";
	
	// Type constants
	public static final String RISK_TYPE_NON_CUSTODY_REFERRAL = "PRE-COURT STAFFING";
	public static final String RISK_TYPE_CUSTODY_REFERRAL = "NEWREFERRAL";
	public static final String RISK_TYPE_DETENTION = "NEWREFERRAL";
	public static final String RISK_TYPE_INTERVIEW = "INTERVIEW";
	public static final String RISK_TYPE_TESTING = "TESTING";
	public static final String RISK_TYPE_OLD_COMMUNITY = "COMMUNITY";
	public static final String RISK_TYPE_COMMUNITY = "COMMUNITY PCI";
	public static final String RISK_TYPE_RESIDENTIAL = "RESIDENTIAL";
	public static final String RISK_TYPE_PROGRESS = "PROGRESS";
	public static final String RISK_TYPE_GANG = "GANG";
	public static final String RISK_TYPE_GANG_COPY="GANG COPY";
	public static final String RISK_TYPE_COURT_REFERRAL_MALE = "COURTREFERRALM";
	public static final String RISK_TYPE_COURT_REFERRAL_FEMALE = "COURTREFERRALF";
	public static final String RISK_TYPE_PROGRESS_COPY = "PROGRESS COPY";
	public static final String RISK_TYPE_RES_PROGRESS = "RESIDENTIAL PROGRESS";
	public static final String RISK_TYPE_MH_SCREEN = "MH SCREEN";
	public static final String RISK_TYPE_PREA_FOLLOW_UP = "PREA FOLLOW-UP";
	
	public static final String RISK_TYPE_NON_COURT_REFERRAL_USE_NAME = "TJJD RISK (MANDATORY)";
	public static final String RISK_TYPE_PRE_COURT_STAFFING_REFERRAL_USE_NAME = "PRE-COURT-STAFFING(PCS)";
	public static final String RISK_TYPE_CUSTODY_REFERRAL_USE_NAME = "DETENTION RISK ASSESSMENT";
	public static final String RISK_TYPE_NON_CUSTODY_AND_CUSTODY_REFERRAL_USE_NAME = "CUSTODY OR NON CUSTODY REFERRAL ASSESSMENT";
	
	public static final String RISK_ASSESSMENT_TYPE = "assessmentType";
	public static final String RISK_FORMULA_ID = "riskFormulaId";
	public static final String RISK_ANALYSIS_ID = "riskAnalysisId";
	public static final String RESIDENTIAL_SCHOOLATTENDANCE_TRAIT = "School Attendance";
	public static final String RESIDENTIAL_EDUCATIONALPERFORMANCE_TRAIT = "Educational Performance";
	public static final String RESIDENTIAL_SCHOOLBEHAVIOR_TRAIT = "School Behavior";
	public static final String NON_CUSTODY = "Non-Custody";
	public static final String CUSTODY = "Custody";
	public static final String MALE = "MALE";
	public static final String FEMALE = "FEMALE";
	public static final String LOWERCASEMALE = "Male";
	public static final String LOWERCASEFEMALE = "Female";
	
	//Used for forced recommendations
	public static final String REFFERAL_NON_CUSTODY_RECOMMENDATION = "REFERRALCOURT";
	public static final String REFERRAL_MANDATORY_DETENTION_RECOMMENDATION = "Detain -- Secure Detention";

	
	public static final String YES = "Yes";
	public static final String NO = "No";
	
	public static final String AGELESSTHEN13 = "Less Then 13";
	public static final String AGE10 = "10";
	public static final String AGE11 = "11";
	public static final String AGE12 = "12";
	public static final String AGE13 = "13";
	public static final String AGE14 = "14";
	public static final String AGE15 = "15";
	public static final String AGE16 = "16";
	public static final String AGE17 = "17";
	public static final String AGE18 = "18";
	public static final String AGE19 = "19";
	public static final String AGE20 = "20";
	public static final String AGE21 = "21";
	
	//public static final String 
	
	public static final String RESIDENTIAL_SCHOOLPROGRAMTYPE_TRAIT = "School Program Type";
	public static final int RISK_INTERVIEW_PROBABLE_CAUSE_HEARING_ANS=50;
	public static final String RISK_INTERVIEW_PROBABLE_CAUSE_HEARING_QUESTION_ID="21";
	public static final String LEVEL_OF_SUPERVISION = "Level of Supervision";
	public static final String CASEFILE_ID = "casefileId";
	public static final String PROBABLE_CAUSE_HEARING = "Probable Cause Hearing Assessment";
	
	public static final String OVERRIDE_TYPE_RELEASE = "Release Override" ;
	public static final String OVERRIDE_TYPE_DETENTION = "Detention Override" ;
	public static final String OVERRIDE_TYPE_OTHER = "Override - Other (Explain)" ; // free-form text by user
	
	public static final char OVERRIDE_TYPE_RELEASE_CODE_FIRST_CHAR = 'R' ;
	public static final char OVERRIDE_TYPE_DETENTION_CODE_FIRST_CHAR = 'D' ;
	public static final char OVERRIDE_TYPE_OTHER_CODE_FIRST_CHAR = 'O' ; 
	
	public static final char OVERRIDE_TYPE_SECOND_CHAR = 'O' ; 
	
	public static final String OVERRIDE_TYPE_OTHER_FIRST_TWO_RO = "RO" ;
	
	public static final String OVERRIDE_TYPE_OTHER_FIRST_TWO_DO = "DO" ;
	
	public static final String OVERRIDE_TYPE_OTHER_CODE = "O01" ;
	
	public static final String SAME_HOUR_SAME_CASEFILE = "SHSC" ;
	
	public static final String NEW_REFERAL_NEEDED = "NFN" ;
	
	public static final String NO_ACTIVE_FORMULA = "NAF";
	
	public static final String JUV_RISK_UI_CONTROL_TYPE="JUV_RISK_UI_CONTROL_TYPE";
	public static final String JUV_RISK_CONTROL_CODE="JUV_RISK_CONTROL_CODE";
	public static final String JUV_RISK_ASSESSMENT_TYPE="JUV_RISK_ASSESSMENT_TYPE";
	public static final String JUV_RISK_FORMULA_STATUS_ACTIVE = "A";
	public static final String JUV_RISK_FORMULA_STATUS_INACTIVE = "I";
	public static final String JUV_RISK_FORMULA_STATUS_PENDING = "P";
	public static final String JUV_RISK_FORMULA_STATUS = "JUV_RISK_FORMULA_STATUS";
	
	public static final String RISK_QUESTION_ID = "riskQuestionId";
	public static final String ETHNICITY = "ETH";
	public static final String SCHOOL = "SCHL";
	public static final String GRADE_LEVEL = "GL";
	public static final String COURT = "CRT";
	public static final String USER_ID = "UID";
	public static final String USER_NAME = "UNAME";
}