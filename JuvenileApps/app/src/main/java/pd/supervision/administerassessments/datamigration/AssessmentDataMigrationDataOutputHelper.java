package pd.supervision.administerassessments.datamigration;

import java.sql.Timestamp;
import java.util.Date;

import naming.CSAdministerServiceProviderConstants;
import mojo.km.utilities.DateUtil;

import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.AssessmentQuestionAnswer;
import pd.supervision.administerassessments.LSIR;
import pd.supervision.administerassessments.StrategiesForCaseSupervision;
import pd.supervision.administerassessments.Wisconsin;
import pd.supervision.legacyupdates.LegacyUpdateLog;

public class AssessmentDataMigrationDataOutputHelper {

	private final static String QUOTE = "'";
	private final static char NEWLINE = '\n';
	private final static String COMMA = ",";
	private final static String BOOLEAN_FALSE_STRING = "0";
	private final static String BOOLEAN_TRUE_STRING = "1";
	private final static boolean TRUE = true;
	private final static boolean FALSE = false;

	private static String getDateStringFromTimestamp(Timestamp timestamp){
		String aStringDate = "";
		if (timestamp != null){
			Date aDate = new Date(timestamp.getTime());
			aStringDate = DateUtil.dateToString(aDate, DateUtil.DATETIME24_FMT_1);
		}
		return aStringDate;
	}

	private static final char CARRIAGE_RETURN = '\n';
	public static String getDatabaseColumns(Assessment assessment) {
		StringBuffer sb = new StringBuffer("ASSESSMENTDATE,");
		sb.append("ASSESSMENTTYPECD,");
		sb.append("ASSESSMENT_ID,");
		sb.append("CREATEDATE,");
		sb.append("CREATEJIMS2USER,");
		sb.append("CREATEUSER,");
		sb.append("DEFENDANT_ID,");
		sb.append("MASTERASSESS_ID,");
		sb.append("TRANSACTIONDATE,");
		sb.append("UPDATEDATE,");
		sb.append("UPDATEJIMS2USER,");
		sb.append("UPDATEUSER,");
		sb.append("STATUSCD,");
		sb.append("MIGRASSESSDATE,");
		sb.append("VERSIONNUM");
		sb.append(CARRIAGE_RETURN);
		return sb.toString();
	}
	private static final String STATUS_COMPLETE = "C";
	public static String getDataDump(Assessment assessment){
		StringBuffer sb = new StringBuffer(NEWLINE);
		sb.append(surroundWithQuotes(DateUtil.dateToString(assessment.getAssessmentDate(), DateUtil.DATE_FMT_1), TRUE));
		sb.append(surroundWithQuotes(assessment.getAssessmentTypeId(), TRUE));
		sb.append(surroundWithQuotes(assessment.getOID(), TRUE));
		sb.append(surroundWithQuotes(getDateStringFromTimestamp(assessment.getCreateTimestamp()), TRUE));
		sb.append(surroundWithQuotes(assessment.getCreateJIMS2UserID(), TRUE));
		sb.append(surroundWithQuotes(assessment.getCreateUserID(), TRUE));
		sb.append(surroundWithQuotes(assessment.getDefendantId(), TRUE));
		sb.append(surroundWithQuotes(assessment.getOID(), TRUE));
		sb.append(surroundWithQuotes(DateUtil.dateToString(assessment.getTransactionDate(),DateUtil.DATETIME_FMT_1), TRUE));
		sb.append(surroundWithQuotes(getDateStringFromTimestamp(assessment.getUpdateTimestamp()), TRUE));
		sb.append(surroundWithQuotes(assessment.getUpdateJIMS2UserID(), TRUE));
		sb.append(surroundWithQuotes(assessment.getUpdateUserID(), TRUE));
		sb.append(surroundWithQuotes(STATUS_COMPLETE, TRUE));
		sb.append(surroundWithQuotes(DateUtil.dateToString(assessment.getMigratedAssessmentDate(), DateUtil.DATE_FMT_1), TRUE));
		sb.append(surroundWithQuotes(new Integer(assessment.getVersionNum()).toString(), FALSE));
		sb.append(CARRIAGE_RETURN);
		return sb.toString();
	}
	public static String getDatabaseColumns(LegacyUpdateLog log) {
		StringBuffer sb = new StringBuffer("CREATEDATE,");
		sb.append("CREATEJIMS2USER,");
		sb.append("CREATEUSER,");
		sb.append("CSTSSEQNO,");
		sb.append("LEGACYUPDATLOG_ID,");
		sb.append("OPID,");
		sb.append("RECDATA,");
		sb.append("RECSTATUS,");
		sb.append("RECTYPE,");
		sb.append("SOURCEOID,");
		sb.append("SPN,");
		sb.append("UPDATEDATE,");
		sb.append("UPDATEJIMS2USER,");
		sb.append("UPDATEUSER");
		sb.append(CARRIAGE_RETURN);
		return sb.toString();
	}
	public static String getDataDump(LegacyUpdateLog log){
		StringBuffer sb = new StringBuffer(NEWLINE);
		sb.append(surroundWithQuotes(DateUtil.dateToString(log.getCreateTimestamp(),DateUtil.DATE_FMT_1), TRUE));
		sb.append(surroundWithQuotes(log.getCreateJIMS2UserID(), TRUE));
		sb.append(surroundWithQuotes(log.getCreateUserID(), TRUE));
		sb.append(surroundWithQuotes(log.getCstsSeqNo(), TRUE));
		sb.append(surroundWithQuotes(log.getOID(), TRUE));
		sb.append(surroundWithQuotes(log.getOpId(), TRUE));
		sb.append(surroundWithQuotes(log.getRecordData(), TRUE));
		sb.append(surroundWithQuotes(log.getStatusId(), TRUE));
		sb.append(surroundWithQuotes(log.getRecordTypeId(), TRUE));
		sb.append(surroundWithQuotes(log.getSourceId(), TRUE));
		sb.append(surroundWithQuotes(log.getSpn(), TRUE));
		sb.append(surroundWithQuotes(getDateStringFromTimestamp(log.getUpdateTimestamp()), TRUE));
		sb.append(surroundWithQuotes(log.getUpdateJIMS2UserID(), TRUE));
		sb.append(surroundWithQuotes(log.getUpdateUserID(), FALSE));
		sb.append(CARRIAGE_RETURN);
		return sb.toString();
	}

	public static String getDatabaseColumns(AssessmentQuestionAnswer aqa){
		StringBuffer sb = new StringBuffer("ANSWERTEXT,");
		sb.append("ANSWER_ID,");
		sb.append("ASSESSMENT_ID,");
		sb.append("ASSESSQAS_ID,");
		sb.append("CREATEDATE,");
		sb.append("CREATEJIMS2USER,");
		sb.append("CREATEUSER,");
		sb.append("QUESTIONGROUP,");
		sb.append("QUESTIONID,");
		sb.append("UPDATEDATE,");
		sb.append("UPDATEJIMS2USER,");
		sb.append("UPDATEUSER");
		sb.append(CARRIAGE_RETURN);
		return sb.toString();
	}
	public static String getDataDump(AssessmentQuestionAnswer aqa){
		StringBuffer sb = new StringBuffer(NEWLINE);
		sb.append(surroundWithQuotes(aqa.getAnswerText(), TRUE));
		sb.append(surroundWithQuotes(aqa.getAnswerId(), TRUE));
		sb.append(surroundWithQuotes(aqa.getAssessmentId(), TRUE));
		sb.append(surroundWithQuotes(aqa.getOID(), TRUE));
		sb.append(surroundWithQuotes(getDateStringFromTimestamp(aqa.getCreateTimestamp()), TRUE));
		sb.append(surroundWithQuotes(aqa.getCreateJIMS2UserID(), TRUE));
		sb.append(surroundWithQuotes(aqa.getCreateUserID(), TRUE));
		sb.append(surroundWithQuotes(aqa.getQuestionGroupId(), TRUE));
		sb.append(surroundWithQuotes(aqa.getQuestionId(), TRUE));
		sb.append(surroundWithQuotes(getDateStringFromTimestamp(aqa.getUpdateTimestamp()), TRUE));
		sb.append(surroundWithQuotes(aqa.getUpdateJIMS2UserID(), TRUE));
		sb.append(surroundWithQuotes(aqa.getUpdateUserID(), FALSE));
		sb.append(CARRIAGE_RETURN);
		return sb.toString();
	}
	public static String getDatabaseColumns(Wisconsin wisc){
		StringBuffer sb = new StringBuffer("ASSESSMENTDUEDATE,");
		sb.append("ASSESSMENT_ID,");
		sb.append("ASSESSWIS_ID,");
		sb.append("CREATEDATE,");
		sb.append("CREATEJIMS2USER,");
		sb.append("CREATEUSER,");
		sb.append("ISPENDING,");
		sb.append("ISREASSESSMENT,");
		sb.append("NEEDSLEVEL,");
		sb.append("RISKLEVEL,");
		sb.append("SUGGESTEDLOSCD,");
		sb.append("TOTALNEEDSSCORE,");
		sb.append("TOTALRISKSCORE,");
		sb.append("UPDATEDATE,");
		sb.append("UPDATEJIMS2USER,");
		sb.append("UPDATEUSER");
		sb.append(CARRIAGE_RETURN);
		return sb.toString();
	}
	public static String getDataDump(Wisconsin wisconsin){
		StringBuffer sb = new StringBuffer(NEWLINE);
		sb.append(surroundWithQuotes(DateUtil.dateToString(wisconsin.getAssessmentDueDate(), DateUtil.DATE_FMT_1), TRUE));
		sb.append(surroundWithQuotes(wisconsin.getAssessmentId(), TRUE));
		sb.append(surroundWithQuotes(wisconsin.getOID(), TRUE));
		sb.append(surroundWithQuotes(getDateStringFromTimestamp(wisconsin.getCreateTimestamp()), TRUE));
		sb.append(surroundWithQuotes(wisconsin.getCreateJIMS2UserID(), TRUE));
		sb.append(surroundWithQuotes(wisconsin.getCreateUserID(), TRUE));
		sb.append(surroundWithQuotes(getBooleanString(wisconsin.getIsPending()), TRUE));
		sb.append(surroundWithQuotes(getBooleanString(wisconsin.getIsReassessment()), TRUE));
		sb.append(surroundWithQuotes(new Integer(wisconsin.getNeedsLevel()).toString(), TRUE));
		sb.append(surroundWithQuotes(new Integer(wisconsin.getRiskLevel()).toString(), TRUE));
		sb.append(surroundWithQuotes(wisconsin.getSuggestedLosCdId(), TRUE));
		sb.append(surroundWithQuotes(new Integer(wisconsin.getTotalNeedsScore()).toString(), TRUE));
		sb.append(surroundWithQuotes(new Integer(wisconsin.getTotalRiskScore()).toString(), TRUE));
		sb.append(surroundWithQuotes(getDateStringFromTimestamp(wisconsin.getUpdateTimestamp()), TRUE));
		sb.append(surroundWithQuotes(wisconsin.getUpdateJIMS2UserID(), TRUE));
		sb.append(surroundWithQuotes(wisconsin.getUpdateUserID(), FALSE));
		sb.append(CARRIAGE_RETURN);
		return sb.toString();
	}
	public static String getDatabaseColumns(LSIR lsir){
		StringBuffer sb = new StringBuffer("ASSESSLSIR_ID,");
		sb.append("ASSESSMENT_ID,");
		sb.append("COMMENTS,");
		sb.append("CREATEDATE,");
		sb.append("CREATEJIMS2USER,");
		sb.append("CREATEUSER,");
		sb.append("DUEDATE,");
		sb.append("ISREASSESSMENT,");
		sb.append("UPDATEDATE,");
		sb.append("UPDATEJIMS2USER,");
		sb.append("UPDATEUSER");
		sb.append(CARRIAGE_RETURN);
		return sb.toString();
	}
	public static String getDataDump(LSIR lsir){
		StringBuffer sb = new StringBuffer(NEWLINE);
		sb.append(surroundWithQuotes(lsir.getOID(), TRUE));
		sb.append(surroundWithQuotes(lsir.getAssessmentId(), TRUE));
		sb.append(surroundWithQuotes(lsir.getComments(), TRUE));
		sb.append(surroundWithQuotes(getDateStringFromTimestamp(lsir.getCreateTimestamp()), TRUE));
		sb.append(surroundWithQuotes(lsir.getCreateJIMS2UserID(), TRUE));
		sb.append(surroundWithQuotes(lsir.getCreateUserID(), TRUE));
		sb.append(surroundWithQuotes(DateUtil.dateToString(lsir.getDueDate(), DateUtil.DATE_FMT_1), TRUE));
		sb.append(surroundWithQuotes(getBooleanString(lsir.getIsReassessment()), TRUE));
		sb.append(surroundWithQuotes(getDateStringFromTimestamp(lsir.getUpdateTimestamp()), TRUE));
		sb.append(surroundWithQuotes(lsir.getUpdateJIMS2UserID(), TRUE));
		sb.append(surroundWithQuotes(lsir.getUpdateUserID(), FALSE));
		sb.append(CARRIAGE_RETURN);
		return sb.toString();
	}
	public static String getDatabaseColumns(StrategiesForCaseSupervision scs){
		StringBuffer sb = new StringBuffer("ASSESSFORCE_ID,");
		sb.append("ASSESSMENT_ID,");
		sb.append("ASSESSSCS_ID,");
		sb.append("CCTOTAL,");
		sb.append("COMMENTS,");
		sb.append("CREATEDATE,");
		sb.append("CREATEJIMS2USER,");
		sb.append("CREATEUSER,");
		sb.append("ESTOTAL,");
		sb.append("ISTOTAL,");
		sb.append("PRIMARYCLASSCD,");
		sb.append("SECONDCLASSCD,");
		sb.append("SITOTAL,");
		sb.append("UPDATEDATE,");
		sb.append("UPDATEJIMS2USER,");
		sb.append("UPDATEUSER");
		sb.append(CARRIAGE_RETURN);
		return sb.toString();
	}
	public static String getDataDump(StrategiesForCaseSupervision scs){
		StringBuffer sb = new StringBuffer(NEWLINE);
		sb.append(surroundWithQuotes(scs.getForceFieldAnalysisId(), TRUE));
		sb.append(surroundWithQuotes(scs.getAssessmentId(), TRUE));
		sb.append(surroundWithQuotes(scs.getOID(), TRUE));
		sb.append(surroundWithQuotes(new Integer(scs.getCcTotal()).toString(), TRUE));
		sb.append(surroundWithQuotes(scs.getComments(), TRUE));
		sb.append(surroundWithQuotes(getDateStringFromTimestamp(scs.getCreateTimestamp()), TRUE));
		sb.append(surroundWithQuotes(scs.getCreateJIMS2UserID(), TRUE));
		sb.append(surroundWithQuotes(scs.getCreateUserID(), TRUE));
		sb.append(surroundWithQuotes(new Integer(scs.getEsTotal()).toString(), TRUE));
		sb.append(surroundWithQuotes(new Integer(scs.getLsTotal()).toString(), TRUE));
		sb.append(surroundWithQuotes(scs.getPrimaryClassificationId(), TRUE));
		sb.append(surroundWithQuotes(scs.getSecondaryClassificationId(), TRUE));
		sb.append(surroundWithQuotes(new Integer(scs.getSiTotal()).toString(), TRUE));
		sb.append(surroundWithQuotes(getDateStringFromTimestamp(scs.getUpdateTimestamp()), TRUE));
		sb.append(surroundWithQuotes(scs.getUpdateJIMS2UserID(), TRUE));
		sb.append(surroundWithQuotes(scs.getUpdateUserID(), FALSE));
		sb.append(CARRIAGE_RETURN);
		return sb.toString();
	}
	private static String surroundWithQuotes(String aValue, boolean insertComma) {
		StringBuffer sb = new StringBuffer(QUOTE);
		if (aValue != null){
			sb.append(aValue.trim());
		}
		sb.append(QUOTE);
		if (insertComma){
			sb.append(COMMA);
		}
		return sb.toString();
	}
	private static String getBooleanString(boolean b){
		String aString = BOOLEAN_FALSE_STRING;
		if (b){
			aString = BOOLEAN_TRUE_STRING;
		}
		return aString;
	}

}
