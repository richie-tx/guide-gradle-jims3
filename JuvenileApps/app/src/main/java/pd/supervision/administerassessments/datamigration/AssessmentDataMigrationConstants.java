package pd.supervision.administerassessments.datamigration;

public class AssessmentDataMigrationConstants {
	public static final String DATE_FORMAT_YMD = "yyyyMMdd";
	// Common M204 Dataname Constants:
	public static final String ENTRY_CLERK = "SA.ENTRY.CLERK";
	public static final String ENTRY_DATE = "SA.ENTRY.DATE";
	public static final String ENTRY_TIME = "SA.ENTRY.TIME";
	public static final String LCDATE = "SA.LCDATE";
	public static final String LCTIME = "SA.LCTIME";
	public static final String LCUSER = "SA.LCUSER";
	public static final String RECTYPE = "SA.RECTYPE";
	public static final String SPN = "SA.SPN";
	public static final String ASSESSMENT_DATE = "SA.ASSESSMENT.DATE";
	public static final String REASSESSMENT_DATE = "SA.REASSESSMENT.DATE";
	public static final String CSTS_SNU = "SA.CSTS.SNU";
	public static final String CSTS_TST = "SA.CSTS.TST";
	public static final String DATE_COMPLETE = "SA.DATE.COMPLETE";
	public static final String DUE_DATE = "SA.DUE.DATE";
	public static final String RECORD_COUNT = "SA.RECORD.COUNT";

	// Needs Assessment M204 Dataname Constants
	public static final String CJAD_OFFICER_NUM = "SA.CJAD.OFFICER.NUM";
	public static final String NEED_ACADEMIC = "SA.NEED.ACADEMIC";
	public static final String NEED_ALCOHOL = "SA.NEED.ALCOHOL";
	public static final String NEED_COMPANIONS = "SA.NEED.COMPANIONS";
	public static final String NEED_DRUG = "SA.NEED.DRUG";
	public static final String NEED_EMPLOYMENT = "SA.NEED.EMPLOYMENT";
	public static final String NEED_FINANCIAL = "SA.NEED.FINANCIAL";
	public static final String NEED_HEALTH = "SA.NEED.HEALTH";
	public static final String NEED_MARITAL = "SA.NEED.MARITAL";
	public static final String NEED_MENTAL = "SA.NEED.MENTAL";
	public static final String NEED_PO_IMPRESSION = "SA.NEED.PO.IMPRESSION";
	public static final String NEED_SCORE_TOT = "SA.NEED.SCORE.TOT";
	public static final String NEED_SEX_BEHAVE = "SA.NEED.SEX.BEHAVE";
	public static final String NEED_STABILITY = "SA.NEED.STABILITY";
	public static final String PROB_OFFICER = "SA.PROB.OFFICER";
	
	//Risk Assessment M204 dataname constants
	public static final String RISK_ADDRESS_CHANGE = "SA.RISK.ADDRESS.CHANGE";
	public static final String RISK_ADULT_JUV_ADJ = "SA.RISK.ADULT.JUV.ADJ";
	public static final String RISK_ADULT_JUV_ASSAULT = "SA.RISK.ADULT.JUV.ASSULT";
	public static final String RISK_COMMUNITY_RESOUR = "SA.RISK.COMMUNITY.RESOUR";
	public static final String RISK_ALCOHOL= "SA.RISK.ALCOHOL";
	public static final String RISK_ATTITUDE = "SA.RISK.ATTITUDE";
	public static final String RISK_DRUG = "SA.RISK.DRUG";
	public static final String RISK_FIRST_ADJ_AGE = "SA.RISK.FIRST.ADJ.AGE";
	public static final String RISK_PRIOR_FELONY_ADJ = "SA.RISK.PRIOR.FELONY.ADJ";
	public static final String RISK_PRIOR_PAROLE_REVOC = "SA.RISK.PRIOR.PAROLE.REVOC";
	public static final String RISK_PRIOR_PAROLE_REV = "SA.RISK.PRIOR.PAROLE.REV";
	public static final String RISK_PROB_RELATIONSHIP = "SA.RISK.PROB.RELATIONSHI";
	public static final String RISK_RESPONSE_COURT_COND = "SA.RISK.RESPONSE.COURT.C";
	public static final String RISK_PRIOR_PAROLE_SUPER = "SA.RISK.PRIOR.PAROLE.SUPER";
	public static final String RISK_SCORE_TOT = "SA.RISK.SCORE.TOT";
	public static final String RISK_SOCIAL_ID = "SA.RISK.SOCIAL.ID";
	public static final String RISK_TIME_EMPLOYED = "SA.RISK.TIME.EMPLOYED";
	
	//SCS Assessment M204 dataname constants
	public static final String STRATEGY_CODE = "SA.STRATEGY.CODE";
	
	//LSIR Question Constants corresponding to XML
	public static final String LSIR1 = "LSIR1";
	public static final String LSIR_RISK = "LSIR_Q1";
	public static final String LSIR_NEEDS = "LSIR_Q2";
	
	//Wisconsin Initial Question Constants corresponding to XML
	public static final String WISCONSIN_INITIAL_G1 = "WISCONSIN_INITIAL_G1";
	public static final String WISCINITRISK_Q1 = "WISCINITRISK_Q1";
	public static final String WISCINITRISK_Q2 = "WISCINITRISK_Q2";
	public static final String WISCINITRISK_Q3 = "WISCINITRISK_Q3";
	public static final String WISCINITRISK_Q4 = "WISCINITRISK_Q4";
	public static final String WISCINITRISK_Q5 = "WISCINITRISK_Q5";
	public static final String WISCINITRISK_Q6 = "WISCINITRISK_Q6";
	public static final String WISCINITRISK_Q7 = "WISCINITRISK_Q7";
	public static final String WISCINITRISK_Q8 = "WISCINITRISK_Q8";
	public static final String WISCINITRISK_Q9 = "WISCINITRISK_Q9";
	public static final String WISCINITRISK_Q10A = "WISCINITRISK_Q10A";
	public static final String WISCINITRISK_Q10B = "WISCINITRISK_Q10B";
	public static final String WISCINITRISK_Q11 = "WISCINITRISK_Q11";
	public static final String WISCONSIN_INITIAL_G2 = "WISCONSIN_INITIAL_G2";
	public static final String WISCINITNEEDS_Q1 = "WISCINITNEEDS_Q1";
	public static final String WISCINITNEEDS_Q2 = "WISCINITNEEDS_Q2";
	public static final String WISCINITNEEDS_Q3 = "WISCINITNEEDS_Q3";
	public static final String WISCINITNEEDS_Q4 = "WISCINITNEEDS_Q4";
	public static final String WISCINITNEEDS_Q5 = "WISCINITNEEDS_Q5";
	public static final String WISCINITNEEDS_Q6 = "WISCINITNEEDS_Q6";
	public static final String WISCINITNEEDS_Q7 = "WISCINITNEEDS_Q7";
	public static final String WISCINITNEEDS_Q8 = "WISCINITNEEDS_Q8";
	public static final String WISCINITNEEDS_Q9 = "WISCINITNEEDS_Q9";
	public static final String WISCINITNEEDS_Q10 = "WISCINITNEEDS_Q10";
	public static final String WISCINITNEEDS_Q11 = "WISCINITNEEDS_Q11";
	public static final String WISCINITNEEDS_Q12 = "WISCINITNEEDS_Q12";
	
	//Wisconsin Reassessment Question Constants corresponding to XML
	public static final String WISCREAS_G1 = "WISCREAS_G1";
	public static final String WISCREAS_RISK_Q1 = "WISCREAS_RISK_Q1";
	public static final String WISCREAS_RISK_Q2 = "WISCREAS_RISK_Q2";
	public static final String WISCREAS_RISK_Q3 = "WISCREAS_RISK_Q3";
	public static final String WISCREAS_RISK_Q4 = "WISCREAS_RISK_Q4";
	public static final String WISCREAS_RISK_Q5A = "WISCREAS_RISK_Q5A";
	public static final String WISCREAS_RISK_Q5B = "WISCREAS_RISK_Q5B";
	public static final String WISCREAS_RISK_Q6A = "WISCREAS_RISK_Q6A";
	public static final String WISCREAS_RISK_Q6 = "WISCREAS_RISK_Q6";
	public static final String WISCREAS_RISK_Q7 = "WISCREAS_RISK_Q7";
	public static final String WISCREAS_RISK_Q8 = "WISCREAS_RISK_Q8";
	public static final String WISCREAS_RISK_Q9 = "WISCREAS_RISK_Q9";
	public static final String WISCREAS_RISK_Q10 = "WISCREAS_RISK_Q10";
	public static final String WISCREAS_RISK_Q11 = "WISCREAS_RISK_Q11";
	public static final String WISCREAS_RISK_Q12 = "WISCREAS_RISK_Q12";
	public static final String WISCREAS_RISK_Q13 = "WISCREAS_RISK_Q13";
	public static final String WISCREAS_G2 = "WISCREAS_G2";
	public static final String WISCREAS_NEED_Q1 = "WISCREAS_NEED_Q1";
	public static final String WISCREAS_NEED_Q2 = "WISCREAS_NEED_Q2";
	public static final String WISCREAS_NEED_Q3 = "WISCREAS_NEED_Q3";
	public static final String WISCREAS_NEED_Q4 = "WISCREAS_NEED_Q4";
	public static final String WISCREAS_NEED_Q5 = "WISCREAS_NEED_Q5";
	public static final String WISCREAS_NEED_Q6 = "WISCREAS_NEED_Q6";
	public static final String WISCREAS_NEED_Q7 = "WISCREAS_NEED_Q7";
	public static final String WISCREAS_NEED_Q8 = "WISCREAS_NEED_Q8";
	public static final String WISCREAS_NEED_Q9 = "WISCREAS_NEED_Q9";
	public static final String WISCREAS_NEED_Q10 = "WISCREAS_NEED_Q10";
	public static final String WISCREAS_NEED_Q11 = "WISCREAS_NEED_Q11";
	public static final String WISCREAS_NEED_Q12 = "WISCREAS_NEED_Q12";
	
	//SCS Assessments
	public static final String SCS_G1 = "SCS_G1"; 
	public static final String SCS_G2 = "SCS_G2"; 
	public static final String SCS_G3 = "SCS_G3"; 
	public static final String SCS_G4 = "SCS_G4";
	public static final String SCS_ANSWER_PREFIX = "SCS_Q";
	public static final String SCS_ANSWER_SUFFIX = "_R0";
}
