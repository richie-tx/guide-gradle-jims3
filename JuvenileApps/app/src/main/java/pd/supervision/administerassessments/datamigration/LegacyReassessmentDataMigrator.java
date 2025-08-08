package pd.supervision.administerassessments.datamigration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import java.util.Calendar;

import naming.CSCAssessmentConstants;
import naming.PDConstants;

import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.AssessmentQuestionAnswer;
import pd.supervision.administerassessments.LSIR;
import pd.supervision.administerassessments.Wisconsin;
import pd.supervision.legacyupdates.LegacyUpdateLog;

import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.utilities.DateUtil;

public class LegacyReassessmentDataMigrator {
	public static void main(String[] args) {
         if (!(args != null && args.length == 1)){
		    System.out.println("!!!!! INVALID PARMS !!!!!");
		} else {
			LegacyReassessmentDataMigrator ladm = new LegacyReassessmentDataMigrator(args[0]);
		    ladm.execute();
		}
    }
	Connection readConnection = null;
	
	Map needsRisksSchemaMap = null;

    private int nextAssessmentOID = 0;
    private int nextWisconsinOID = 0;
    private int nextLSIROID = 0;
    private int nextAssessmentQASOID = 0;
    private int nextLegacyUpdateOID = 0;
    private int totLegacyAssessmentsRead = 0;
    private int totAssessmentsWritten = 0;
    private int totWisconsinsWritten = 0;
    private int totLISRsWritten = 0;
    private int totQAsWritten = 0;
    private int totErrors = 0;
    private BufferedWriter assessmentFlatFile = null;
    private BufferedWriter wisconsinFlatFile = null; 
    private BufferedWriter lsirFlatFile = null; 
    private BufferedWriter qasFlatFile = null; 
    private BufferedWriter legacyUpdateFlatFile = null; 
    private BufferedWriter errorFile = null;
    BufferedWriter migrationResults = null;	     

	private static final int ZERO = 0;
	private Map wisconsinAnswerMap;
	private static final int EIGHT = 8;
	private static boolean TRUE = true;
	private static boolean FALSE = false;
	private static final String MIGRATED = "MIGR-";
	private static final String ZERO_STRING = "0";
	private int incrementalCounter = 0;
	
	public LegacyReassessmentDataMigrator(String configFileName) {
	    super();
	    System.setProperty("mojo.config", configFileName);
	    System.out.println("Configuration file: "+configFileName);
		mojo.km.context.ContextManager.currentContext();
	}
	private void buildLSIRAnswers(Assessment assessment,
			String needsRisksString) {
		
		AssessmentQuestionAnswer aqa = this.getBaseAnswer(assessment);
		aqa.setAnswerId(null);
		aqa.setAnswerText(AssessmentDataMigrationHelper.getDataValue(needsRisksString, AssessmentDataMigrationConstants.RISK_SCORE_TOT, needsRisksSchemaMap));
		aqa.setQuestionGroupId(AssessmentDataMigrationConstants.LSIR1);
		aqa.setQuestionId(AssessmentDataMigrationConstants.LSIR_RISK);
		
		this.writeAnswer(aqa);
		
		aqa = this.getBaseAnswer(assessment);
		aqa.setAnswerId(null);
		aqa.setAnswerText(AssessmentDataMigrationHelper.getDataValue(needsRisksString, AssessmentDataMigrationConstants.NEED_SCORE_TOT, needsRisksSchemaMap));
		aqa.setQuestionGroupId(AssessmentDataMigrationConstants.LSIR1);
		aqa.setQuestionId(AssessmentDataMigrationConstants.LSIR_NEEDS);
		this.writeAnswer(aqa);
	}
	private static final String TWO = "2";
	private static final String ONE_STR = "1";
	private static final String THREE = "3";
	
	private void buildWisconsinReassessmentAnswers(Assessment
			assessment, String needsRisksString) {

		AssessmentQuestionAnswer aqa = this.getBaseAnswer(assessment);
		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
				AssessmentDataMigrationConstants.WISCREAS_RISK_Q1, 
				AssessmentDataMigrationConstants.RISK_ADDRESS_CHANGE,
				needsRisksString);
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
				AssessmentDataMigrationConstants.WISCREAS_RISK_Q2, 
				AssessmentDataMigrationConstants.RISK_FIRST_ADJ_AGE,
				needsRisksString);
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
				AssessmentDataMigrationConstants.WISCREAS_RISK_Q3, 
				AssessmentDataMigrationConstants.RISK_PRIOR_PAROLE_REV,
				needsRisksString);
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
				AssessmentDataMigrationConstants.WISCREAS_RISK_Q4, 
				AssessmentDataMigrationConstants.RISK_PRIOR_FELONY_ADJ,
				needsRisksString);
		String m204Response = AssessmentDataMigrationHelper.getDataValue(needsRisksString, 	
				AssessmentDataMigrationConstants.RISK_ADULT_JUV_ADJ, needsRisksSchemaMap);
		if (m204Response.equals(ZERO_STRING)){//Burglary/Theft/Auto theft/Robbery
			this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
					AssessmentDataMigrationConstants.WISCREAS_RISK_Q5A, ZERO_STRING);			
			this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
					AssessmentDataMigrationConstants.WISCREAS_RISK_Q5B, ZERO_STRING);
		} else if (m204Response.equals(ONE_STR)){//Burglary/Theft/Auto theft/Robbery
			this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
					AssessmentDataMigrationConstants.WISCREAS_RISK_Q5A, TWO);			
			this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
					AssessmentDataMigrationConstants.WISCREAS_RISK_Q5B, ZERO_STRING);
			
		} else if (m204Response.equals(TWO)){//Burglary/Theft/Auto theft/Robbery
			this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
					AssessmentDataMigrationConstants.WISCREAS_RISK_Q5A, ZERO_STRING);			
			this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
					AssessmentDataMigrationConstants.WISCREAS_RISK_Q5B, TWO);

		} else if (m204Response.equals(THREE)){//Worthless Checks/Forgery
			this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
					AssessmentDataMigrationConstants.WISCREAS_RISK_Q5A, TWO);			
			this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
					AssessmentDataMigrationConstants.WISCREAS_RISK_Q5B, THREE);
		} else {
			this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
					AssessmentDataMigrationConstants.WISCREAS_RISK_Q5A, ZERO_STRING);			
			this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
					AssessmentDataMigrationConstants.WISCREAS_RISK_Q5B, ZERO_STRING);
		}		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
				AssessmentDataMigrationConstants.WISCREAS_RISK_Q6, 
				AssessmentDataMigrationConstants.RISK_TIME_EMPLOYED,
				needsRisksString);
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
				AssessmentDataMigrationConstants.WISCREAS_RISK_Q7, 
				AssessmentDataMigrationConstants.RISK_ALCOHOL,
				needsRisksString);
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
				AssessmentDataMigrationConstants.WISCREAS_RISK_Q8, 
				AssessmentDataMigrationConstants.RISK_DRUG,
				needsRisksString);
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
				AssessmentDataMigrationConstants.WISCREAS_RISK_Q9, 
				AssessmentDataMigrationConstants.RISK_PROB_RELATIONSHIP,
				needsRisksString);		

		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
				AssessmentDataMigrationConstants.WISCREAS_RISK_Q10, 
				AssessmentDataMigrationConstants.RISK_SOCIAL_ID,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
				AssessmentDataMigrationConstants.WISCREAS_RISK_Q11, 
				AssessmentDataMigrationConstants.RISK_RESPONSE_COURT_COND,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
				AssessmentDataMigrationConstants.WISCREAS_RISK_Q12, 
				AssessmentDataMigrationConstants.RISK_COMMUNITY_RESOUR,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G1,
				AssessmentDataMigrationConstants.WISCREAS_RISK_Q13, 
				AssessmentDataMigrationConstants.RISK_ADULT_JUV_ASSAULT,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G2,
				AssessmentDataMigrationConstants.WISCREAS_NEED_Q1, 
				AssessmentDataMigrationConstants.NEED_ACADEMIC,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G2,
				AssessmentDataMigrationConstants.WISCREAS_NEED_Q2, 
				AssessmentDataMigrationConstants.NEED_EMPLOYMENT,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G2,
				AssessmentDataMigrationConstants.WISCREAS_NEED_Q3, 
				AssessmentDataMigrationConstants.NEED_FINANCIAL,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G2,
				AssessmentDataMigrationConstants.WISCREAS_NEED_Q4, 
				AssessmentDataMigrationConstants.NEED_MARITAL,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G2,
				AssessmentDataMigrationConstants.WISCREAS_NEED_Q5, 
				AssessmentDataMigrationConstants.NEED_COMPANIONS,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G2,
				AssessmentDataMigrationConstants.WISCREAS_NEED_Q6, 
				AssessmentDataMigrationConstants.NEED_STABILITY,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G2,
				AssessmentDataMigrationConstants.WISCREAS_NEED_Q7, 
				AssessmentDataMigrationConstants.NEED_ALCOHOL,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G2,
				AssessmentDataMigrationConstants.WISCREAS_NEED_Q8, 
				AssessmentDataMigrationConstants.NEED_DRUG,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G2,
				AssessmentDataMigrationConstants.WISCREAS_NEED_Q9, 
				AssessmentDataMigrationConstants.NEED_MENTAL,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G2,
				AssessmentDataMigrationConstants.WISCREAS_NEED_Q10, 
				AssessmentDataMigrationConstants.NEED_HEALTH,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G2,
				AssessmentDataMigrationConstants.WISCREAS_NEED_Q11, 
				AssessmentDataMigrationConstants.NEED_SEX_BEHAVE,
				needsRisksString);		
		this.writeWisconsinAnswer(aqa, AssessmentDataMigrationConstants.WISCREAS_G2,
				AssessmentDataMigrationConstants.WISCREAS_NEED_Q12, 
				AssessmentDataMigrationConstants.NEED_PO_IMPRESSION,
				needsRisksString);		

	}
	public void execute() {
        try {
	       File needsRiskExtractFile = new File("C:\\JDSACSA\\JDSACSA0.REASSESS.NEED.TXT");
	       errorFile = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\REASSESSMENT.ERRORS", errorFile);
	       assessmentFlatFile = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\REASSESSMENT.ASSESSMENT", assessmentFlatFile);
	       assessmentFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDatabaseColumns(new Assessment()));
	       wisconsinFlatFile = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\REASSESSMENT.WISCONSIN", wisconsinFlatFile);
	       wisconsinFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDatabaseColumns(new Wisconsin()));
	       lsirFlatFile = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\REASSESSMENT.LSIR", lsirFlatFile);
	       lsirFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDatabaseColumns(new LSIR()));
	       qasFlatFile = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\REASSESSMENT.ASSESSMENTQAS", qasFlatFile);
	       qasFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDatabaseColumns(new AssessmentQuestionAnswer()));
	       legacyUpdateFlatFile = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\REASSESSMENT.CSLEGACYUPDATLOG", legacyUpdateFlatFile);
	       legacyUpdateFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDatabaseColumns(new LegacyUpdateLog()));
	       migrationResults = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\REASSESSMENT.MIGRATION.RESULTS", migrationResults);

	       FileReader needsRiskExtractFileReader = null;

	       needsRiskExtractFileReader = new FileReader(needsRiskExtractFile);
	       needsRisksSchemaMap = AssessmentDataMigrationHelper.getM204Schema(needsRiskExtractFileReader, true);
	       needsRiskExtractFileReader.close();
	       needsRiskExtractFileReader = new FileReader(needsRiskExtractFile);
     
	       readConnection = AssessmentDataMigrationHelper.getConnection();

	       migrationResults.write("STARTING REASSESSMENT MIGRATION @ "+ new Date() + '\n');
	       this.processMigration(needsRiskExtractFileReader);
	       needsRiskExtractFileReader.close();

	       StringBuffer msg = new StringBuffer('\n' + "TOTAL LEGACY REASSESSMENTS READ=");
	       msg.append(totLegacyAssessmentsRead);
	       msg.append('\n');
	       msg.append("TOTAL ASSESSMENT RECORDS WRITTEN=");
	       msg.append(totAssessmentsWritten);
	       msg.append('\n');
	       msg.append("TOTAL WISCONSIN RECORDS WRITTEN=");
	       msg.append(totWisconsinsWritten);
	       msg.append('\n');
	       msg.append("TOTAL LSIR RECORDS WRITTEN=");
	       msg.append(totLISRsWritten);
	       msg.append('\n');
	       msg.append("TOTAL ANSWER RECORDS WRITTEN=");
	       msg.append(totQAsWritten);
	       msg.append('\n');
	       msg.append("TOTAL ERRORS=");
	       msg.append(totErrors);
	       migrationResults.write(msg.toString());
	       migrationResults.write('\n' + "FINISHED REASSESSMENT MIGRATION @ "+ new Date());
	       migrationResults.close();
	       
	   } catch (Exception e) {
		   this.writeError(SPACE, e.getMessage());
	     	e.printStackTrace();
	    } 
	}
	
	private AssessmentQuestionAnswer getBaseAnswer(Assessment assessment) {
		AssessmentQuestionAnswer aqa = new AssessmentQuestionAnswer();
		aqa.setAssessmentId(assessment.getOID());
		aqa.setCreateJIMS2UserID(assessment.getCreateJIMS2UserID());
		aqa.setCreateTimestamp(assessment.getCreateTimestamp());
		aqa.setCreateUserID(assessment.getCreateUserID());
		aqa.setUpdateTimestamp(assessment.getUpdateTimestamp());
		aqa.setUpdateUserID(assessment.getUpdateUserID());
		aqa.setUpdateJIMS2UserID(assessment.getCreateJIMS2UserID());
		return aqa;
	}
	
	private String getJIMS2User() {
		StringBuffer sb = new StringBuffer(MIGRATED);
		sb.append(DateUtil.getCurrentDateString(DateUtil.LOG_DATE_FMT));
		return sb.toString();
	}
	private static final String ACTIVE_SPVPER_MSG = "ACTIVE SUPERVISION PERIOD NOT FOUND";
	private static final String PRIOR_SUP_PERIOD_MSG = "ASSESSMENT BYPASSED - IN PRIOR SUPERVISION PERIOD-SPBEGDATE=";
	//private static final String ASSESSMENTDATE_RECALCULATED_MSG = "ASSESSMENT DATE WITHIN ONE WEEK OF SPBEGDATE - CHANGED TO SPBEGDATE=";
	private static final String REASSESSMENT_PRIOR_TO_LATEST_INITIAL = "ASSESSMENT DATE PRIOR TO MOST RECENT INITIAL ASSESSMENT DATE - ";
	private static final String INITIAL_ASSESSMENT_NOT_FOUND = "INITIAL ASSESSMENT NOT FOUND";
	private static final String NO_CSTS_SNU = "NO CSTS SNU ON M204 ASSESSMENT RECORD";
	private static final String ASTERISKS = "***";
	private String prevSpn;
	private Date supervisionBeginDate;
	private boolean errorFound = false;
	
	private void processAssessment(String needsRisksString) {
		incrementalCounter++;
		errorFound = false;
		Assessment assessment = new Assessment();
		LegacyReassessmentNeedRisk lanr = null;
		try {
			lanr = LegacyReassessmentNeedRisk.getData(needsRisksString, needsRisksSchemaMap);
		} catch (ParseRuntimeException e) {
			this.writeError(needsRisksString, e.getMessage());
			return;
		}

		if (lanr.isLsir()){
			assessment.setAssessmentTypeId(CSCAssessmentConstants.ASSESSMENTTYPE_LSIR);
		} else {
			assessment.setAssessmentTypeId(CSCAssessmentConstants.ASSESSMENTTYPE_WISCONSIN);
		}
		try {
			AssessmentDataMigrationHelper.setCommonAttributes(assessment, needsRisksString, needsRisksSchemaMap);
		} catch (ParseRuntimeException e) {
			this.writeError(needsRisksString, e.getMessage());
			return;
		}
		
		Date aDate = null;
		String aStringValue = AssessmentDataMigrationHelper.getDataValue(needsRisksString, AssessmentDataMigrationConstants.REASSESSMENT_DATE, needsRisksSchemaMap);
		if (!aStringValue.equals(PDConstants.BLANK)){
			try {
				aDate = DateUtil.stringToDate(aStringValue,
						AssessmentDataMigrationConstants.DATE_FORMAT_YMD);
			} catch (ParseRuntimeException e) {
				this.writeError(needsRisksString, e.getMessage());
				return;
			}
		} else {
			this.writeError(needsRisksString, "NO ASSESSMENT DATE");
			return;

		}
//		if (lanr.getCstsSnu() != null 
//				&& !lanr.getCstsSnu().equals(PDConstants.BLANK)
//				&& !lanr.getCstsSnu().equals(ASTERISKS)){
//		} else {
//			this.writeError(needsRisksString, NO_CSTS_SNU);
//		}
		if (prevSpn == null || !lanr.getSpn().equals(prevSpn)){
			//SupervisionPeriod supervisionPeriod = SupervisionPeriod.findActiveSupervisionPeriod(lanr.getSpn(), CSC_AGENCY);
			supervisionBeginDate = AssessmentDataMigrationHelper.getSupervisionPeriodBeginDate(readConnection, lanr.getSpn());

			if (supervisionBeginDate != null){
 	        	Calendar cal = Calendar.getInstance();
 	        	cal.setTime(supervisionBeginDate);
 	            cal.set(Calendar.HOUR_OF_DAY,ZERO);
 	            cal.clear(Calendar.MINUTE);
 	            cal.clear(Calendar.SECOND);
 	            cal.clear(Calendar.MILLISECOND);
 	        	supervisionBeginDate = cal.getTime();
 	        } else {
 	        	this.writeError(needsRisksString,ACTIVE_SPVPER_MSG);
 	        	return;
 	        }
 	        prevSpn = lanr.getSpn();
		} 
		assessment.setMigratedAssessmentDate(aDate);
 /*    	if (aDate.compareTo(supervisionBeginDate) < ZERO){
     		//Assessment date is prior to active supervision period begin date.
     		//Check to see if assessment date falls within one week of supervision begin date.
     		boolean isWithinOneWeek = AssessmentDataMigrationHelper.isAssessmentDateWithinOneWeekofSupervisionDate(aDate, supervisionBeginDate);
     		if (!isWithinOneWeek){
     			this.writeError(needsRisksString,PRIOR_SUP_PERIOD_MSG + DateUtil.dateToString(supervisionBeginDate, DateUtil.DATE_FMT_1));
     			return;
     		} else {
     			this.writeError(needsRisksString,ASSESSMENTDATE_RECALCULATED_MSG + DateUtil.dateToString(supervisionBeginDate, DateUtil.DATE_FMT_1));
     			errorFound = false;
     			aDate = supervisionBeginDate;
     		}
     	}*/
     	//Check for initial assessment
     	Date initialAssessmentDate = AssessmentDataMigrationHelper.isInitialAssessmentComplete(readConnection, lanr.getSpn());
     	if (initialAssessmentDate == null){
     		this.writeError(needsRisksString, INITIAL_ASSESSMENT_NOT_FOUND);
     	} else if (aDate.compareTo(initialAssessmentDate) < 0){
 			this.writeError(needsRisksString,REASSESSMENT_PRIOR_TO_LATEST_INITIAL + DateUtil.dateToString(initialAssessmentDate, DateUtil.DATE_FMT_1));
 			return;
    	}
     	
		if (errorFound){
			return;
		}
		assessment.setAssessmentDate(aDate);
		
		nextAssessmentOID++;
		assessment.setOID(new Integer(nextAssessmentOID).toString());
		assessment.setMasterAssessmentId(assessment.getOID());
		
		aStringValue = AssessmentDataMigrationHelper.getDataValue(needsRisksString, AssessmentDataMigrationConstants.SPN, needsRisksSchemaMap);
		assessment.setDefendantId(AssessmentDataMigrationHelper.padWithZeroes(aStringValue, EIGHT));
		
		aStringValue = AssessmentDataMigrationHelper.getDataValue(needsRisksString, AssessmentDataMigrationConstants.ENTRY_DATE, needsRisksSchemaMap);
		if (!aStringValue.equals(PDConstants.BLANK)){
			try {
				aDate = DateUtil.stringToDate(aStringValue,
						AssessmentDataMigrationConstants.DATE_FORMAT_YMD);
			} catch (ParseRuntimeException e) {
				this.writeError(needsRisksString, e.getMessage());
				return;
			}
			aStringValue = AssessmentDataMigrationHelper.getDataValue(needsRisksString, AssessmentDataMigrationConstants.ENTRY_TIME, needsRisksSchemaMap);
			aDate = AssessmentDataMigrationHelper.addTimeToDate(aDate, aStringValue); 
		} else {
			aDate = null;
		}
		assessment.setTransactionDate(aDate);
		
		assessment.setVersionNum(AssessmentDataMigrationHelper.ONE);
		assessment.setCreateJIMS2UserID(this.getJIMS2User());
		assessment.setUpdateJIMS2UserID(assessment.getCreateJIMS2UserID());
		try {
			assessmentFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDataDump(assessment));
			totAssessmentsWritten++;
		} catch (IOException e) {
			this.writeError(needsRisksString, e.getMessage());
			e.printStackTrace();
		}
		if (lanr.isLsir()){
			LSIR lsir = new LSIR();
			lsir.setAssessmentId(assessment.getOID());
			lsir.setCreateJIMS2UserID(assessment.getCreateJIMS2UserID());
			lsir.setCreateTimestamp(assessment.getCreateTimestamp());
			lsir.setCreateUserID(assessment.getCreateUserID());
			lsir.setUpdateTimestamp(assessment.getUpdateTimestamp());
			lsir.setUpdateUserID(assessment.getUpdateUserID());
			lsir.setUpdateJIMS2UserID(assessment.getCreateJIMS2UserID());
			if (lanr.getDueDate() != null){
				lsir.setDueDate(lanr.getDueDate());
			} else {
		       Calendar calendar = Calendar.getInstance();
		       calendar.setTime(assessment.getAssessmentDate());
		       calendar.roll(Calendar.YEAR, true);
		       lsir.setDueDate(calendar.getTime());
			}
			lsir.setIsReassessment(TRUE);
			nextLSIROID++;
			lsir.setOID(new Integer(nextLSIROID).toString());
			try {
				lsirFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDataDump(lsir));
				totLISRsWritten++;
			} catch (IOException e) {
				this.writeError(needsRisksString, e.getMessage());
				e.printStackTrace();
			}
			this.buildLSIRAnswers(assessment, needsRisksString);
		} else {
			Wisconsin wisconsin = new Wisconsin();
			if (lanr.getDueDate() != null){
				wisconsin.setAssessmentDueDate(lanr.getDueDate());
			} else {
		       Calendar calendar = Calendar.getInstance();
		       calendar.setTime(assessment.getAssessmentDate());
		       calendar.roll(Calendar.YEAR, true);
		       wisconsin.setAssessmentDueDate(calendar.getTime());
			}
			wisconsin.setAssessmentId(assessment.getOID());
			wisconsin.setCreateJIMS2UserID(assessment.getCreateJIMS2UserID());
			wisconsin.setCreateTimestamp(assessment.getCreateTimestamp());
			wisconsin.setCreateUserID(assessment.getCreateUserID());
			wisconsin.setUpdateTimestamp(assessment.getUpdateTimestamp());
			wisconsin.setUpdateJIMS2UserID(assessment.getCreateJIMS2UserID());
			wisconsin.setUpdateUserID(assessment.getUpdateUserID());
			wisconsin.setIsPending(FALSE);
			wisconsin.setIsReassessment(TRUE);
			wisconsin.setNeedsLevel(lanr.getNeedsLevel());
			aStringValue = AssessmentDataMigrationHelper.getDataValue(needsRisksString, AssessmentDataMigrationConstants.NEED_SCORE_TOT, needsRisksSchemaMap);
			wisconsin.setTotalNeedsScore(AssessmentDataMigrationHelper.getIntegerFromString(aStringValue));
			wisconsin.setRiskLevel(lanr.getRiskLevel());

			int levelOfSupervisionCd = wisconsin.getRiskLevel();
			if(wisconsin.getNeedsLevel() < wisconsin.getRiskLevel())
			{
				levelOfSupervisionCd = wisconsin.getNeedsLevel();
			}
			wisconsin.setSuggestedLosCdId(new Integer(levelOfSupervisionCd).toString());
			aStringValue = AssessmentDataMigrationHelper.getDataValue(needsRisksString, AssessmentDataMigrationConstants.RISK_SCORE_TOT, needsRisksSchemaMap);
			wisconsin.setTotalRiskScore(AssessmentDataMigrationHelper.getIntegerFromString(aStringValue));
			nextWisconsinOID++;
			wisconsin.setOID(new Integer(nextWisconsinOID).toString());
			try {
				wisconsinFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDataDump(wisconsin));
				totWisconsinsWritten++;
			} catch (IOException e) {
				this.writeError(needsRisksString, e.getMessage());
				e.printStackTrace();
			}
			this.buildWisconsinReassessmentAnswers(assessment, needsRisksString);
		}
		if (lanr.getCstsSnu() != null 
				&& !lanr.getCstsSnu().equals(PDConstants.BLANK)
				&& !lanr.getCstsSnu().equals(ASTERISKS)){
			nextLegacyUpdateOID++;
			LegacyUpdateLog uLog = AssessmentDataMigrationHelper.createLegacyUpdateLog(assessment, lanr, nextLegacyUpdateOID);
			try {
				legacyUpdateFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDataDump(uLog));
			} catch (IOException e) {
				this.writeError(needsRisksString, e.getMessage());
				e.printStackTrace();
			}
		} else {
			this.writeError(needsRisksString, NO_CSTS_SNU);
		}

		if (incrementalCounter > 199){
			try {
				StringBuffer msg = new StringBuffer("PROCESSED RECORD # ");
				msg.append(lanr.getRecordCount());
				msg.append(" SPN=");
				msg.append(lanr.getSpn());
				msg.append('\n');
				migrationResults.write(msg.toString());
				System.out.println(msg.toString());
			} catch (Exception e) {
				this.writeError(needsRisksString, e.getMessage());
			}
			incrementalCounter = 0;
		}

	}
	private static final String SPACE = " ";
	private void writeError(String needsRisksString, String message) {
		totErrors++;
		StringBuffer msg = new StringBuffer(needsRisksString);
		msg.append(SPACE);
		msg.append(message);
		errorFound = true;
		try {
			errorFile.write(msg.toString());
			errorFile.write('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private static final int FOUR = 4;
	private void processMigration(FileReader needsRisksExtractFileReader) {

		wisconsinAnswerMap = AssessmentDataMigrationHelper.getAnswerMap(CSCAssessmentConstants.ASSESSMENTTYPE_WISCONSIN, false);
		BufferedReader needsRisksBufferedReader = new BufferedReader(needsRisksExtractFileReader);
		String needsRisksString = null;
		
		try {
			nextAssessmentOID = AssessmentDataMigrationHelper.getNextOID(AssessmentDataMigrationHelper.TABLE_NAME_ASSESSMENT, readConnection);
			nextAssessmentQASOID= AssessmentDataMigrationHelper.getNextOID(AssessmentDataMigrationHelper.TABLE_NAME_QAS, readConnection);
			nextLSIROID = AssessmentDataMigrationHelper.getNextOID(AssessmentDataMigrationHelper.TABLE_NAME_LSIR, readConnection);
			nextWisconsinOID = AssessmentDataMigrationHelper.getNextOID(AssessmentDataMigrationHelper.TABLE_NAME_WISCONSIN, readConnection);
			nextLegacyUpdateOID = AssessmentDataMigrationHelper.getNextOID(AssessmentDataMigrationHelper.TABLE_NAME_LEGACYUPDATE, readConnection);

			readConnection.commit();
			
			while (needsRisksBufferedReader.read() > -1) {
				needsRisksString = needsRisksBufferedReader.readLine();
				if (needsRisksString.substring(ZERO, FOUR).equals(AssessmentDataMigrationHelper.COMMENT_ID)
						|| needsRisksString.substring(ZERO, FOUR).equals(AssessmentDataMigrationHelper.COMMENT_ID_CAPS)){
					continue;
				} else {
					totLegacyAssessmentsRead++;
					this.processAssessment(needsRisksString);
				}
			}
		} catch (Exception e) {
			this.writeError(SPACE, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				needsRisksBufferedReader.close();
				readConnection.close();
				assessmentFlatFile.close();
				wisconsinFlatFile.close();
				lsirFlatFile.close();
				qasFlatFile.close();
				legacyUpdateFlatFile.close();
				errorFile.close();
			} catch (Exception e) {
				this.writeError(SPACE, e.getMessage());
				e.printStackTrace();
			}
		}
		
	}

	private void writeAnswer(AssessmentQuestionAnswer aqa) {
		try {
			nextAssessmentQASOID++;
			aqa.setOID(new Integer(nextAssessmentQASOID).toString());
			qasFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDataDump(aqa));
			totQAsWritten++;
		} catch (IOException e) {
			this.writeError(aqa.getOID(), e.getMessage());
			e.printStackTrace();
		}
	}
	private static final String PLUS = "+";
	private static final int ONE = 1;
	private void writeWisconsinAnswer(AssessmentQuestionAnswer aqa, String groupId, String questionId, 
			String m204DataName, String needsRisksString) {
		aqa.setQuestionGroupId(groupId);
		aqa.setQuestionId(questionId);
		String m204Response = AssessmentDataMigrationHelper.getDataValue(needsRisksString, 	m204DataName, needsRisksSchemaMap);
		if (m204Response.startsWith(PLUS)){
			m204Response = m204Response.substring(ONE);
		}
		Object answerId = wisconsinAnswerMap.get(AssessmentDataMigrationHelper.getResponseKey(aqa.getQuestionGroupId(), aqa.getQuestionId(), m204Response));

		if (answerId != null){
			aqa.setAnswerId((String) answerId);
			this.writeAnswer(aqa);
		} 
	}

	private void writeWisconsinAnswer(AssessmentQuestionAnswer aqa, String groupId, String questionId, String answer) {
		
		aqa.setQuestionGroupId(groupId);
		aqa.setQuestionId(questionId);

		Object answerId = wisconsinAnswerMap.get(AssessmentDataMigrationHelper.getResponseKey(aqa.getQuestionGroupId(), aqa.getQuestionId(), answer));

		if (answerId != null){
			aqa.setAnswerId((String) answerId);
			this.writeAnswer(aqa);
		}
	}
		
}
