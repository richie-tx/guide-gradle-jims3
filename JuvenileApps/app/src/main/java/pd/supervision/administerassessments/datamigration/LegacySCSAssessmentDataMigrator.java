package pd.supervision.administerassessments.datamigration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import java.util.Calendar;

import naming.CSCAssessmentConstants;
import naming.PDConstants;

import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.AssessmentQuestionAnswer;
import pd.supervision.administerassessments.StrategiesForCaseSupervision;
import pd.supervision.legacyupdates.LegacyUpdateLog;

import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.utilities.DateUtil;

public class LegacySCSAssessmentDataMigrator {
	public static void main(String[] args) {
         if (!(args != null && args.length == 1)){
		    System.out.println("!!!!! INVALID PARMS !!!!!");
		} else {
		    LegacySCSAssessmentDataMigrator ladm = new LegacySCSAssessmentDataMigrator(args[0]);
		    ladm.execute();
		}
    }
	Connection readConnection = null;
	
	Map scsSchemaMap = null;

    private int nextAssessmentOID = 0;
    private int nextSCSOID = 0;
    private int nextAssessmentQASOID = 0;
    private int nextLegacyUpdateOID = 0;
    private int totLegacyAssessmentsRead = 0;
    private int totAssessmentsWritten = 0;
    private int totSCSWritten = 0;
    private int totQAsWritten = 0;
    private int totErrors = 0;
    private BufferedWriter assessmentFlatFile = null;
    private BufferedWriter scsFlatFile = null; 
    private BufferedWriter qasFlatFile = null; 
    private BufferedWriter legacyUpdateFlatFile = null;
    private BufferedWriter errorFile = null;
    BufferedWriter migrationResults = null;	     

	private static final int ZERO = 0;
	private static final int EIGHT = 8;
	private static final String MIGRATED = "MIGR-";
	private int incrementalCounter = 0;
	
	public LegacySCSAssessmentDataMigrator(String configFileName) {
	    super();
	    System.setProperty("mojo.config", configFileName);
	    System.out.println("Configuration file: "+configFileName);
		mojo.km.context.ContextManager.currentContext();
	}
//	private static final String A = "A";
//	private static final String B = "B";
//	private static final String C = "C";
//	private static final String D = "D";
//	private static final String E = "E";
//	private static final String F = "F";
//	private static final String R1 = "_R1";
	
	private void buildSCSAssessmentAnswers(Assessment
			assessment, String needsRisksString) {

		AssessmentQuestionAnswer aqa = this.getBaseAnswer(assessment);

		for (int i = 1; i < 21; i++) {
			this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G1,i, null, null);
		}
		for (int i = 21; i < 41; i++) {
			this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G2,i, null, null);
		}
		for (int i = 41; i < 50; i++) {
			this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,i, null, null);
		}
		/* this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,50, A, R1);
		this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,50, B, R1);
		this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,50, C, R1);
		this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,50, D, R1);
		this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,50, E, R1); */
		for (int i = 51; i < 54; i++) {
			this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,i, null, null);
		}
		/*this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,54, A, R1);
		this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,54, B, R1);
		this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,54, C, R1);
		this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,54, D, R1);
		this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,54, E, R1);
		this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,54, F, R1);*/
		for (int i = 55; i < 61; i++) {
			this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G3,i, null, null);
		}
		for (int i = 61; i < 72; i++) {
			this.writeSCSAnswer(aqa, AssessmentDataMigrationConstants.SCS_G4,i, null, null);
		}
		
	}
	public void execute() {
        try {
 	       File scsExtractFile = new File("C:\\JDSACSA\\JDSACSA0.ASSESSME.SCS.TXT");
	       errorFile = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\SCS.ASSESSMENT.ERRORS", errorFile);
	       assessmentFlatFile = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\SCS.ASSESSMENT", assessmentFlatFile);
	       assessmentFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDatabaseColumns(new Assessment()));
	       scsFlatFile = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\SCS.SCSASSESSMENT", scsFlatFile);
	       scsFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDatabaseColumns(new StrategiesForCaseSupervision()));
	       qasFlatFile = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\SCS.ASSESSMENTQAS", qasFlatFile);
	       qasFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDatabaseColumns(new AssessmentQuestionAnswer()));
	       legacyUpdateFlatFile = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\SCS.CSLEGACYUPDTLOG", legacyUpdateFlatFile);
	       legacyUpdateFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDatabaseColumns(new LegacyUpdateLog()));

	       migrationResults = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\SCS.ASSESSMENT.MIGRATION.RESULTS", migrationResults);

	       FileReader needsRiskExtractFileReader = null;

	       needsRiskExtractFileReader = new FileReader(scsExtractFile);
	       scsSchemaMap = AssessmentDataMigrationHelper.getM204Schema(needsRiskExtractFileReader, true);
	       needsRiskExtractFileReader.close();
	       needsRiskExtractFileReader = new FileReader(scsExtractFile);
     
	       readConnection = AssessmentDataMigrationHelper.getConnection();

	       migrationResults.write("STARTING SCS ASSESSMENT MIGRATION @ "+ new Date() + '\n');
	       this.processMigration(needsRiskExtractFileReader);
	       needsRiskExtractFileReader.close();

	       StringBuffer msg = new StringBuffer('\n' + "TOTAL LEGACY ASSESSMENTS READ=");
	       msg.append(totLegacyAssessmentsRead);
	       msg.append('\n');
	       msg.append("TOTAL ASSESSMENT RECORDS WRITTEN=");
	       msg.append(totAssessmentsWritten);
	       msg.append('\n');
	       msg.append("TOTAL SCS RECORDS WRITTEN=");
	       msg.append(totSCSWritten);
	       msg.append('\n');
	       msg.append("TOTAL ANSWER RECORDS WRITTEN=");
	       msg.append(totQAsWritten);
	       msg.append('\n');
	       msg.append("TOTAL ERRORS=");
	       msg.append(totErrors);
	       migrationResults.write(msg.toString());
	       migrationResults.write('\n' + "FINISHED SCS ASSESSMENT MIGRATION @ "+ new Date());
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
	private static final String PRIOR_SUP_PERIOD_MSG = "ASSESSMENT BYPASSED-IN PRIOR SUPERVISION PERIOD-SPBEGDATE=";
	private static final String NO_CSTS_SNU = "NO CSTS SNU ON M204 ASSESSMENT RECORD";
	private static final String ASTERISKS = "***";
	private static final String REASSESSMENT_PRIOR_TO_LATEST_INITIAL = "ASSESSMENT DATE PRIOR TO MOST RECENT INITIAL ASSESSMENT DATE - ";
	private static final String ASSESSMENTDATE_RECALCULATED_MSG = "ASSESSMENT DATE WITHIN ONE WEEK OF SPBEGDATE - CHANGED TO SPBEGDATE=";
	private static final String INITIAL_ASSESSMENT_NOT_FOUND = "INITIAL ASSESSMENT NOT FOUND";
	private static final String STRATEGY_CODE_MISSING = "STRATEGY CODE MISSING FROM M204 ASSESSMENT RECORD";
	private static final String SIS = "SIS";
	private static final String SIT = "SIT";
	private static final String SI = "SI";
	private static final String SS = "SS";
	private String prevSpn;
	Date supervisionBeginDate;
	boolean errorFound = false;
	
	private void processAssessment(String scsString) {
		incrementalCounter++;
		Assessment assessment = new Assessment();
		LegacyAssessmentSCS legacySCS = null;
		errorFound = false;
		try {
			legacySCS = LegacyAssessmentSCS.getData(scsString, scsSchemaMap);
		} catch (ParseRuntimeException e) {
			this.writeError(scsString, e.getMessage());
			return;
		}

		assessment.setAssessmentTypeId(CSCAssessmentConstants.ASSESSMENTTYPE_SCS);
//		try {
//			AssessmentDataMigrationHelper.setCommonAttributes(assessment, scsString, scsSchemaMap);
//		} catch (ParseRuntimeException e) {
//			this.writeError(scsString, e.getMessage());
//			return;
//		}
		
		Date aDate = null;
		String aStringValue = AssessmentDataMigrationHelper.getDataValue(scsString, AssessmentDataMigrationConstants.LCDATE, scsSchemaMap);

		try {
			aDate = DateUtil.stringToDate(aStringValue, AssessmentDataMigrationConstants.DATE_FORMAT_YMD);
		} catch (ParseRuntimeException e) {
			this.writeError(scsString, e.getMessage());
			return;
		}
		
		aStringValue = AssessmentDataMigrationHelper.getDataValue(scsString, AssessmentDataMigrationConstants.LCTIME, scsSchemaMap);
		aDate = AssessmentDataMigrationHelper.addTimeToDate(aDate, aStringValue); 
		Timestamp timestamp = new Timestamp(aDate.getTime());
		assessment.setUpdateTimestamp(timestamp);
		assessment.setCreateTimestamp(timestamp);
		
		aStringValue = AssessmentDataMigrationHelper.getDataValue(scsString, AssessmentDataMigrationConstants.LCUSER, scsSchemaMap);
		assessment.setUpdateUserID(aStringValue);
		assessment.setCreateUserID(aStringValue);

		aStringValue = AssessmentDataMigrationHelper
			.getDataValue(scsString, AssessmentDataMigrationConstants.ASSESSMENT_DATE, scsSchemaMap);
		if (!aStringValue.equals(PDConstants.BLANK)){
			try {
				aDate = DateUtil.stringToDate(aStringValue,
						AssessmentDataMigrationConstants.DATE_FORMAT_YMD);
			} catch (ParseRuntimeException e) {
				this.writeError(scsString, e.getMessage());
				return;
			}
		} else {
			this.writeError(scsString, "NO ASSESSMENT DATE");
			return;

		}
		if (prevSpn == null || !legacySCS.getSpn().equals(prevSpn)){
			//SupervisionPeriod supervisionPeriod = SupervisionPeriod.findActiveSupervisionPeriod(legacySCS.getSpn(), CSC_AGENCY);
			supervisionBeginDate = AssessmentDataMigrationHelper.getSupervisionPeriodBeginDate(readConnection, legacySCS.getSpn());

			if (supervisionBeginDate != null){
 	        	Calendar cal = Calendar.getInstance();
 	        	cal.setTime(supervisionBeginDate);
 	            cal.set(Calendar.HOUR_OF_DAY,ZERO);
 	            cal.clear(Calendar.MINUTE);
 	            cal.clear(Calendar.SECOND);
 	            cal.clear(Calendar.MILLISECOND);
 	        	supervisionBeginDate = cal.getTime();
 	        } else {
 	        	this.writeError(scsString,ACTIVE_SPVPER_MSG);
 	        	return;
 	        }
 	        prevSpn = legacySCS.getSpn();
		} 
		assessment.setMigratedAssessmentDate(aDate);
//     	if (aDate.compareTo(supervisionBeginDate) < ZERO){
//     		//Assessment date is prior to active supervision period begin date.
//     		//Check to see if assessment date falls within one week of supervision begin date.
//     		boolean isWithinOneWeek = AssessmentDataMigrationHelper.isAssessmentDateWithinOneWeekofSupervisionDate(aDate, supervisionBeginDate);
//     		if (!isWithinOneWeek){
//     			this.writeError(scsString,PRIOR_SUP_PERIOD_MSG + DateUtil.dateToString(supervisionBeginDate, DateUtil.DATE_FMT_1));
//     			return;
//     		} else {
//     			this.writeError(scsString,ASSESSMENTDATE_RECALCULATED_MSG + DateUtil.dateToString(supervisionBeginDate, DateUtil.DATE_FMT_1));
//     			errorFound = false;
//     			aDate = supervisionBeginDate;
//     		}
//     	}
     	String strategyCode = AssessmentDataMigrationHelper.getDataValue(scsString, 
				AssessmentDataMigrationConstants.STRATEGY_CODE, scsSchemaMap);
     	if (strategyCode == null || strategyCode.equals(PDConstants.BLANK)){
     		this.writeError(scsString, STRATEGY_CODE_MISSING);
     	}
     	//Check for initial assessment
     	Date initialAssessmentDate = AssessmentDataMigrationHelper.isInitialAssessmentComplete(readConnection, legacySCS.getSpn());
     	if (initialAssessmentDate == null){
     		this.writeError(scsString, INITIAL_ASSESSMENT_NOT_FOUND);
     		return;
     	} else if (aDate.compareTo(initialAssessmentDate) < 0){
 			this.writeError(scsString,REASSESSMENT_PRIOR_TO_LATEST_INITIAL + DateUtil.dateToString(initialAssessmentDate, DateUtil.DATE_FMT_1));
 			return;
    	}

     	if (errorFound){
     		return;
     	}

     	assessment.setAssessmentDate(aDate);
		assessment.setMasterAssessmentId(assessment.getOID());
		nextAssessmentOID++;
		assessment.setOID(new Integer(nextAssessmentOID).toString());
		
		aStringValue = AssessmentDataMigrationHelper.getDataValue(scsString, AssessmentDataMigrationConstants.SPN, scsSchemaMap);
		assessment.setDefendantId(AssessmentDataMigrationHelper.padWithZeroes(aStringValue, EIGHT));
		
		aStringValue = AssessmentDataMigrationHelper.getDataValue(scsString, AssessmentDataMigrationConstants.ASSESSMENT_DATE, scsSchemaMap);
		if (!aStringValue.equals(PDConstants.BLANK)){
			try {
				aDate = DateUtil.stringToDate(aStringValue,
						AssessmentDataMigrationConstants.DATE_FORMAT_YMD);
			} catch (ParseRuntimeException e) {
				this.writeError(scsString, e.getMessage());
				return;
			}
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
			this.writeError(scsString, e.getMessage());
			e.printStackTrace();
		}

		StrategiesForCaseSupervision scs = new StrategiesForCaseSupervision();
		scs.setAssessmentId(assessment.getOID());
		scs.setCreateJIMS2UserID(assessment.getCreateJIMS2UserID());
		scs.setCreateTimestamp(assessment.getCreateTimestamp());
		scs.setCreateUserID(assessment.getCreateUserID());
		scs.setUpdateTimestamp(assessment.getUpdateTimestamp());
		scs.setUpdateJIMS2UserID(assessment.getCreateJIMS2UserID());
		scs.setUpdateUserID(assessment.getUpdateUserID());
		scs.setCcTotal(ZERO);
		scs.setComments(null);
		scs.setEsTotal(ZERO);
		scs.setLsTotal(ZERO);
//		scs.setPrimaryClassificationId(AssessmentDataMigrationHelper
//				.getDataValue(scsString, 
//						AssessmentDataMigrationConstants.STRATEGY_CODE, scsSchemaMap));
		if (strategyCode.equals(SIT)){
			scs.setPrimaryClassificationId(SI);
		} else if (strategyCode.equals(SIS)){
			scs.setPrimaryClassificationId(SS);
		} else {
			scs.setPrimaryClassificationId(strategyCode);
		}
		scs.setSecondaryClassificationId(null);
		scs.setSiTotal(ZERO);

		nextSCSOID++;
		scs.setOID(new Integer(nextSCSOID).toString());
		try {
			scsFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDataDump(scs));
			totSCSWritten++;
		} catch (IOException e) {
			this.writeError(scsString, e.getMessage());
			e.printStackTrace();
		}
		//Only migrating primary classification code. do i need to build blank answers?
		this.buildSCSAssessmentAnswers(assessment, scsString);
		if (legacySCS.getCstsSnu() != null 
				&& !legacySCS.getCstsSnu().equals(PDConstants.BLANK)
				&& !legacySCS.getCstsSnu().equals(ASTERISKS)){
			nextLegacyUpdateOID++;
			LegacyUpdateLog uLog = AssessmentDataMigrationHelper.createLegacyUpdateLog(assessment, legacySCS, nextLegacyUpdateOID);
			try {
				legacyUpdateFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDataDump(uLog));
			} catch (IOException e) {
				this.writeError(scsString, e.getMessage());
				e.printStackTrace();
			}
		} else {
			this.writeError(scsString, NO_CSTS_SNU);
		}
			
		if (incrementalCounter > 199){
			try {
				StringBuffer msg = new StringBuffer("PROCESSED RECORD # ");
				msg.append(legacySCS.getRecordCount());
				msg.append(" SPN=");
				msg.append(legacySCS.getSpn());
				msg.append('\n');
				migrationResults.write(msg.toString());
				System.out.println(msg.toString());
			} catch (Exception e) {
				this.writeError(scsString, e.getMessage());
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
	private void processMigration(FileReader scsExtractFileReader) {

		BufferedReader needsRisksBufferedReader = new BufferedReader(scsExtractFileReader);
		String needsRisksString = null;
		
		try {
			nextAssessmentOID = AssessmentDataMigrationHelper.getNextOID(AssessmentDataMigrationHelper.TABLE_NAME_ASSESSMENT, readConnection);
			nextAssessmentQASOID= AssessmentDataMigrationHelper.getNextOID(AssessmentDataMigrationHelper.TABLE_NAME_QAS, readConnection);
			nextSCSOID = AssessmentDataMigrationHelper.getNextOID(AssessmentDataMigrationHelper.TABLE_NAME_SCS, readConnection);
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
				scsFlatFile.close();
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
	
	private void writeSCSAnswer(AssessmentQuestionAnswer aqa, 
			String groupId, int questionNum, String questionIdSuffix, String answerSuffixOverride) {
		aqa.setQuestionGroupId(groupId);
		aqa.setQuestionId(new Integer(questionNum).toString());
		StringBuffer answerId = new StringBuffer(AssessmentDataMigrationConstants.SCS_ANSWER_PREFIX);
		answerId.append(questionNum);
		if (questionIdSuffix !=  null){
			answerId.append(questionIdSuffix);
		}
		if (answerSuffixOverride != null){
			answerId.append(answerSuffixOverride);
		} else {
			answerId.append(AssessmentDataMigrationConstants.SCS_ANSWER_SUFFIX);
		}
		aqa.setAnswerId(answerId.toString());
		this.writeAnswer(aqa);
	}

}
