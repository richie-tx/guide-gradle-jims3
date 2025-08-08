package pd.supervision.administerassessments.datamigration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import naming.LegacyUpdateConstants;
import naming.PDConstants;

import pd.contact.user.UserProfile;
import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.AssessmentHelper;
import pd.supervision.administerassessments.Wisconsin;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.legacyupdates.handlers.RiskNeedsScoreHandler;
import pd.supervision.legacyupdates.handlers.StrategiesForCaseSupervisionHandler;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import pd.supervision.supervisionorder.SupervisionPeriod;

import messaging.administerassessments.reply.CSCPossibleResponseEvent;
import messaging.administerassessments.reply.CSCQuestionAnswerResponseEvent;
import messaging.administerassessments.reply.CSCQuestionGroupResponseEvent;
import messaging.administerassessments.reply.CSCQuestionResponseEvent;
import messaging.legacyupdates.UpdateRiskNeedScoreAssessmentDataEvent;
import messaging.legacyupdates.UpdateSCSAssessmentDataEvent;
import messaging.supervisionorder.GetActiveSupervisionPeriodEvent;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.MojoProperties;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;

public class AssessmentDataMigrationHelper {
	private static final String FIELDNAME = "// SA.";

	private static final String FIELDLOCSTART = "(";

	private static final String COMMA = ",";

	private static final String FIELDLENGTHEND = ")";
	
	public static final String COMMENT_ID = " s =";
	public static final String COMMENT_ID_CAPS = " S =";
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;

	
	public static Map getM204Schema(FileReader aFileReader, boolean printSchema) {
		Map aMap = getM204Schema(aFileReader);
		if (printSchema){
			printSchema(aMap);
		}
		return aMap;
		
	}
	public static Map getM204Schema(FileReader aFileReader) {
		Map aMap = new HashMap();
		BufferedReader bufferedReader = new BufferedReader(aFileReader);
		String inputString = null;
		String aFieldName = null;
		int anIndex = ZERO;
		List aList = null;
		int fieldPosition = ZERO;
		int fieldLength = ZERO;
		int fieldStartLoc = ZERO;
		int fieldLengthLoc = ZERO;
		int fieldLengthEnd = ZERO;
		String aString = null;

		try {
			
			while (bufferedReader.read() > -1) {
				inputString = bufferedReader.readLine();
				if (!inputString.substring(ZERO, FOUR).equals(COMMENT_ID)
						&& !inputString.substring(ZERO, FOUR).equals(COMMENT_ID_CAPS)){
					break;
				}
				anIndex = inputString.indexOf(FIELDNAME) + THREE;
				aFieldName = inputString.substring(anIndex);
				//fieldStartLoc = inputString.indexOf(FIELDLOCSTART) + ONE;
				fieldStartLoc = inputString.indexOf(FIELDLOCSTART) + ONE;
				fieldLengthLoc = inputString.indexOf(COMMA) + ONE;
				fieldLengthEnd = inputString.indexOf(FIELDLENGTHEND);
				aString = inputString.substring(fieldStartLoc, fieldLengthLoc - ONE);
				fieldPosition = new Integer(aString);
				aString = inputString.substring(fieldLengthLoc, fieldLengthEnd);
				fieldLength = new Integer(aString);
				aList = new ArrayList();
				//Jason changed indexing on his end to start in 0 instead of 1.
				//aList.add(fieldPosition - ONE);
				aList.add(fieldPosition);
				aList.add(fieldLength);
				aMap.put(aFieldName, aList);
			}
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return aMap;
	}
	public static void printSchema(Map aMap){
		Collection coll = aMap.values();
		List mapValues = CollectionUtil.iteratorToList(coll.iterator());
		Set keySet = aMap.keySet();
		List keyValues = CollectionUtil.iteratorToList(keySet.iterator());
		List aList = null;
		String aName = null;
		StringBuffer sb = new StringBuffer();
		for (int i = ZERO; i < mapValues.size(); i++) {
			aList = (List) mapValues.get(i);
			aName = (String) keyValues.get(i);
			sb.append('\n');
			sb.append(aName);
			sb.append(" ");
			sb.append(aList.get(ZERO));
			sb.append(",");
			sb.append(aList.get(ONE));
		}
		System.out.println(sb.toString());
	}
	
	public static Date addTimeToDate(Date aDate, String timeStrValue) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(aDate);
		String hours = timeStrValue.substring(ZERO, TWO);
		String mins = timeStrValue.substring(THREE,FIVE);
		String secs = timeStrValue.substring(SIX);
		cal.set(Calendar.HOUR_OF_DAY, new Integer(hours).intValue());
		cal.set(Calendar.MINUTE, new Integer(mins).intValue());
		cal.set(Calendar.SECOND, new Integer(secs).intValue());
		aDate = cal.getTime();
		
		return aDate;
	}

	public static String padWithZeroes(String stringValue, int aLength) {
		StringBuffer sb = new StringBuffer(stringValue);
		while (sb.length() < aLength){
			sb.insert(ZERO,ZERO);
		}
		return sb.toString();
	}

	public static String getDataValue(String inputString, String fieldName, Map aSchemaMap){
		List aList = (List) aSchemaMap.get(fieldName);
		int startingPosition = ((Integer) aList.get(ZERO)).intValue();
		int fieldLength = ((Integer) aList.get(ONE)).intValue();	
		String aString = null;
		int res = startingPosition + fieldLength;
		if (res > inputString.length()){
			if (startingPosition <= inputString.length()){
				aString = inputString.substring(startingPosition);
			} else {
				aString = PDConstants.BLANK;
			}
		} else {
			aString = inputString.substring(startingPosition, startingPosition + fieldLength);
		}
		return aString.trim();
	}
	
	public static void setCommonAttributes(PersistentObject pObj, String inputString, Map schemaMap) throws ParseRuntimeException {
		String aStringValue = AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.ENTRY_DATE, schemaMap);
		Date aDate = null;
		try {
			aDate = DateUtil.stringToDate(aStringValue, AssessmentDataMigrationConstants.DATE_FORMAT_YMD);
		} catch (ParseRuntimeException e) {
			throw e;
		}
		
		aStringValue = AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.ENTRY_TIME, schemaMap);
		aDate = AssessmentDataMigrationHelper.addTimeToDate(aDate, aStringValue); 
		Timestamp timestamp = new Timestamp(aDate.getTime());
		pObj.setUpdateTimestamp(timestamp);
		pObj.setCreateTimestamp(timestamp);
		
		aStringValue = AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.ENTRY_CLERK, schemaMap);
		pObj.setUpdateUserID(aStringValue);
		pObj.setCreateUserID(aStringValue);
	}
	
	public static Integer getIntegerFromString(String aString) {
		Integer retInt = new Integer(ZERO);
		try {
			Integer newInt = new Integer(aString);
			retInt = newInt;
		} catch (NumberFormatException e) {
		}
		return retInt;
	}	
	private static String JDBC = "JDBC";
    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties(JDBC);

        System.out.println("Load driver: "+cProps.getDriver());
        Class.forName(cProps.getDriver());
        String conUrl = cProps.getTestUrl();
        Connection connection = DriverManager.getConnection(conUrl, cProps.getUserID(), cProps.getPassword());
        connection.setReadOnly(true);
        return connection;
    }

    private static final String SELECT_NEXTOID = "select dname, coalesce (maxassignedval+1, restartwith, 1) as nextval from sysibm.syssequences a, sysibm.syssequencesdep b where a.sequenceid = b.bsequenceid and b.dname = ";
    private static final String QUOTE = "'";
    private static final String FOR_READ_ONLY = " FOR READ ONLY";
    private static final String NEXTVAL = "NEXTVAL";
    public static final String TABLE_NAME_ASSESSMENT = "T_CSASSESSMENT";
    public static final String TABLE_NAME_WISCONSIN = "T_CSASSESSWIS";
    public static final String TABLE_NAME_LSIR = "T_CSASSESSLSIR";
    public static final String TABLE_NAME_QAS = "T_CSASSESSQAS";
    public static final String TABLE_NAME_SCS = "T_CSASSESSSCS";
    public static final String TABLE_NAME_LEGACYUPDATE = "T_CSLEGACYUPDATLOG";
    
    public static int getNextOID(String tableName, Connection connection) throws SQLException {
    	int nextOID = 0;
        StringBuffer sql = new StringBuffer(SELECT_NEXTOID);
        sql.append(QUOTE);
        sql.append(tableName);
        sql.append(QUOTE);
        sql.append(FOR_READ_ONLY);
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql.toString());
            if (rs.next()){
            	String aString = rs.getString(NEXTVAL);
            	nextOID = new Integer(aString).intValue();
            }
        } catch (SQLException e) {
			throw e;  	
        } finally {
            try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				throw e;
			}
        }
    	return nextOID;
    }
    public static BufferedWriter createFile(String fileName, BufferedWriter bufferedWriter){
        StringBuffer sb = new StringBuffer(fileName);
        sb.append(".");
        sb.append(DateUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        sb.append(".txt");
        File file = new File(sb.toString());
        bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }    
        return bufferedWriter;
    }
    /**
     * Builds map of all possible answers for each questionGroupId, QuestionId, possibleResponseId combination.
     * @return
     */
    public static HashMap getAnswerMap(String assessmentType, boolean isInitial) {
        
        HashMap answerMap = new HashMap();

        CSCQuestionAnswerResponseEvent qare = AssessmentHelper.getQuestions(assessmentType, isInitial);
        
        if (qare != null){
            List qGroupList = CollectionUtil.iteratorToList(qare.getQuestionGroupResponseEvents().iterator());
            CSCQuestionGroupResponseEvent qGroup = null;
            CSCQuestionResponseEvent qRe = null;
            List qAnswerList = null;
            List pqAnswerList = null;
            CSCPossibleResponseEvent pqRe = null;
            String aKey = null;
            
            for (int i = ZERO; i < qGroupList.size(); i++) {
                qGroup = (CSCQuestionGroupResponseEvent) qGroupList.get(i);
                qAnswerList = CollectionUtil.iteratorToList(qGroup.getQuestionResponseEvents().iterator());
                
                for (int j = ZERO; j < qAnswerList.size(); j++) {
                    qRe = (CSCQuestionResponseEvent) qAnswerList.get(j);
                    pqAnswerList = CollectionUtil.iteratorToList(qRe.getPossibleResponseEvents().iterator());
                    
                    for (int k = ZERO; k < pqAnswerList.size(); k++) {
                        pqRe = (CSCPossibleResponseEvent) pqAnswerList.get(k);
                        aKey = getResponseKey(qGroup.getId(), qRe.getId(), pqRe.getResponseValue());
                        answerMap.put(aKey, pqRe.getId());
                    }
                }
            }
        }
        return answerMap;
    }
    private static final String PIPE = "|";
    public static String getResponseKey(String groupId, String questionId, String responseId) {
        
        StringBuffer aKey = new StringBuffer(groupId);
        aKey.append(PIPE);
        aKey.append(questionId);
        aKey.append(PIPE);
        aKey.append(responseId);

        return aKey.toString();
    }
    private static final String T21 = "T21";
    private static final String T22 = "T22";
    
    public static LegacyUpdateLog createLegacyUpdateLog(Assessment assessment, 
    		LegacyAssessmentNeedRisk legacyAssessment, int nextLegacyUpdateOID){
    	UpdateRiskNeedScoreAssessmentDataEvent updateEvent = new UpdateRiskNeedScoreAssessmentDataEvent();
    	updateEvent.setAction(PDConstants.BLANK);
    	updateEvent.setAssessmentDate(assessment.getAssessmentDate());
    	updateEvent.setAssessmentType(assessment.getAssessmentTypeId());
    	updateEvent.setJims2SourceId(assessment.getOID());
    	if (assessment.getUpdateUserID() != null){
    		updateEvent.setLogonId(assessment.getUpdateUserID());
    	} else {
    		updateEvent.setLogonId(assessment.getCreateUserID());
    	}
    	
    	updateEvent.setNeedsScore(new Integer(legacyAssessment.getNeedsLevel()).toString());
    	updateEvent.setRecType(T21);
    	updateEvent.setRiskScore(new Integer(legacyAssessment.getRiskLevel()).toString());
    	updateEvent.setSpn(assessment.getDefendantId());

    	LegacyUpdateLog uLog = new LegacyUpdateLog();
    	uLog.setCstsSeqNo(legacyAssessment.getCstsSnu());
		uLog.setSpn(assessment.getDefendantId());
		uLog.setRecordTypeId(T21);
		uLog.setSourceId(assessment.getOID());
		uLog.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
//		String logonId = null;
//    	if (assessment.getUpdateUserID() != null){
//    		logonId = assessment.getUpdateUserID();
//    	} else {
//    		logonId = assessment.getCreateUserID();
//    	}
//		UserProfile up = UserProfile.find(logonId);
//		if(up != null){
//			uLog.setOpId(up.getOperatorId());
//		}
		try {
			uLog.setRecordData(xmlWriter(updateEvent, LegacyUpdateConstants.TST21_UPDATE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		uLog.setOID(new Integer(nextLegacyUpdateOID).toString());
		uLog.setUpdateJIMS2UserID(assessment.getUpdateJIMS2UserID());
		uLog.setUpdateTimestamp(assessment.getUpdateTimestamp());
		uLog.setUpdateUserID(assessment.getUpdateUserID());
		uLog.setCreateJIMS2UserID(assessment.getCreateJIMS2UserID());
		uLog.setCreateTimestamp(assessment.getCreateTimestamp());
		uLog.setCreateUserID(assessment.getCreateUserID());
		
		return uLog;
    }
    public static LegacyUpdateLog createLegacyUpdateLog(Assessment assessment, 
    		LegacyReassessmentNeedRisk legacyAssessment, int nextLegacyUpdateOID){
    	UpdateRiskNeedScoreAssessmentDataEvent updateEvent = new UpdateRiskNeedScoreAssessmentDataEvent();
    	updateEvent.setAction(PDConstants.BLANK);
    	updateEvent.setAssessmentDate(assessment.getAssessmentDate());
    	updateEvent.setAssessmentType(assessment.getAssessmentTypeId());
    	updateEvent.setJims2SourceId(assessment.getOID());
    	if (assessment.getUpdateUserID() != null){
    		updateEvent.setLogonId(assessment.getUpdateUserID());
    	} else {
    		updateEvent.setLogonId(assessment.getCreateUserID());
    	}
    	
    	updateEvent.setNeedsScore(new Integer(legacyAssessment.getNeedsLevel()).toString());
    	updateEvent.setRecType(T21);
    	updateEvent.setRiskScore(new Integer(legacyAssessment.getRiskLevel()).toString());
    	updateEvent.setSpn(assessment.getDefendantId());

    	LegacyUpdateLog uLog = new LegacyUpdateLog();
    	uLog.setCstsSeqNo(legacyAssessment.getCstsSnu());
		uLog.setSpn(assessment.getDefendantId());
		uLog.setRecordTypeId(T21);
		uLog.setSourceId(assessment.getOID());
		uLog.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
//		String logonId = null;
//    	if (assessment.getUpdateUserID() != null){
//    		logonId = assessment.getUpdateUserID();
//    	} else {
//    		logonId = assessment.getCreateUserID();
//    	}
//		UserProfile up = UserProfile.find(logonId);
//		if(up != null){
//			uLog.setOpId(up.getOperatorId());
//		}
		try {
			uLog.setRecordData(xmlWriter(updateEvent, LegacyUpdateConstants.TST21_UPDATE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		uLog.setOID(new Integer(nextLegacyUpdateOID).toString());
		uLog.setUpdateJIMS2UserID(assessment.getUpdateJIMS2UserID());
		uLog.setUpdateTimestamp(assessment.getUpdateTimestamp());
		uLog.setUpdateUserID(assessment.getUpdateUserID());
		uLog.setCreateJIMS2UserID(assessment.getCreateJIMS2UserID());
		uLog.setCreateTimestamp(assessment.getCreateTimestamp());
		uLog.setCreateUserID(assessment.getCreateUserID());
		
		return uLog;
    }
    public static LegacyUpdateLog createLegacyUpdateLog(Assessment assessment, 
    		LegacyAssessmentSCS legacyAssessment, int nextLegacyUpdateOID){
    	UpdateSCSAssessmentDataEvent updateEvent = new UpdateSCSAssessmentDataEvent();
    	updateEvent.setAssessmentDate(assessment.getAssessmentDate());
    	updateEvent.setJims2SourceId(assessment.getOID());
    	if (assessment.getUpdateUserID() != null){
    		updateEvent.setLogonId(assessment.getUpdateUserID());
    	} else {
    		updateEvent.setLogonId(assessment.getCreateUserID());
    	}
    	updateEvent.setAssessmentCode(assessment.getAssessmentTypeId());
    	updateEvent.setScsClassification(legacyAssessment.getStrategyCode());
    	updateEvent.setRecType(T22);
    	updateEvent.setSpn(assessment.getDefendantId());
    	updateEvent.setCriminalCaseId(SupervisionOrderHelper.getActiveCaseId(assessment.getDefendantId()));

    	LegacyUpdateLog uLog = new LegacyUpdateLog();
    	uLog.setCstsSeqNo(legacyAssessment.getCstsSnu());
		uLog.setSpn(assessment.getDefendantId());
		uLog.setRecordTypeId(T22);
		uLog.setSourceId(assessment.getOID());
		uLog.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
//		String logonId = null;
//    	if (assessment.getUpdateUserID() != null){
//    		logonId = assessment.getUpdateUserID();
//    	} else {
//    		logonId = assessment.getCreateUserID();
//    	}
//		UserProfile up = UserProfile.find(logonId);
//		if(up != null){
//			uLog.setOpId(up.getOperatorId());
//		}
		try {
			uLog.setRecordData(xmlWriter(updateEvent, LegacyUpdateConstants.TST22_UPDATE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		uLog.setOID(new Integer(nextLegacyUpdateOID).toString());
		uLog.setUpdateJIMS2UserID(assessment.getUpdateJIMS2UserID());
		uLog.setUpdateTimestamp(assessment.getUpdateTimestamp());
		uLog.setUpdateUserID(assessment.getUpdateUserID());
		uLog.setCreateJIMS2UserID(assessment.getCreateJIMS2UserID());
		uLog.setCreateTimestamp(assessment.getCreateTimestamp());
		uLog.setCreateUserID(assessment.getCreateUserID());
		
		return uLog;
    }
	protected static final String DATE_FORMAT = "yyyyMMdd";
	public static String xmlWriter(UpdateRiskNeedScoreAssessmentDataEvent reqEvent, String recType) throws IOException{
		Element root = new Element(LegacyUpdateConstants.XML_ASSESSMENTDATA);
		Element child = new Element(LegacyUpdateConstants.XML_RISKNEEDSSCORE);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,reqEvent.getAction());
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTTYPE,reqEvent.getAssessmentType());
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE, (reqEvent.getAssessmentDate() == null)?"":DateUtil.dateToString(reqEvent.getAssessmentDate(), DATE_FORMAT));
		StringBuffer sb =  new StringBuffer(reqEvent.getNeedsScore());
		if (sb.length() < 2){
			sb.insert(0, "0");
		}
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_NEEDSSCORE, sb.toString());
		sb =  new StringBuffer(reqEvent.getRiskScore());
		if (sb.length() < 2){
			sb.insert(0, "0");
		}
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_RISKSCORE, sb.toString());
		String caseNum = reqEvent.getCriminalCaseId();
		if (caseNum != null && caseNum.length() == 15){
			caseNum = caseNum.substring(3);
		}
		child.setAttribute(
				LegacyUpdateConstants.ATTRIBUTE_CASENUM,
				(caseNum == null) ? "":caseNum);

		root.addContent(child);	
		return writeToXML(root);
	}
	public static String xmlWriter(UpdateSCSAssessmentDataEvent reqEvent,
			String recType) throws IOException {
		Element root = new Element(LegacyUpdateConstants.XML_ASSESSMENTDATA);
		Element child = new Element(LegacyUpdateConstants.XML_SCSDATA);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,
				(reqEvent.getAction() == null) ? "" : reqEvent
						.getAction());
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCODE,
				(reqEvent.getAssessmentCode() == null) ? "" : reqEvent
						.getAssessmentCode());
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE,
				(reqEvent.getAssessmentDate() == null) ? ""
						: DateUtil.dateToString(reqEvent.getAssessmentDate(),
								DATE_FORMAT));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCLASSIFICATION,(reqEvent.getScsClassification() == null) ? "":reqEvent.getScsClassification());
		String caseNum = reqEvent.getCriminalCaseId();
		if (caseNum != null && caseNum.length() == 15){
			caseNum = caseNum.substring(3);
		}
		child.setAttribute(
				LegacyUpdateConstants.ATTRIBUTE_CASENUM,
				(caseNum == null) ? "":caseNum);
		
		root.addContent(child);
		return writeToXML(root);
	}
	
	public static String writeToXML(Element root) throws IOException {		
		Document document = new Document(root);		
		XMLOutputter outputter = new XMLOutputter();
		outputter.setOmitDeclaration(true);
		OutputStream out = new ByteArrayOutputStream();
		outputter.output(document, out);
		return out.toString();	}	
	private static final String SELECT_SUPERVISION_PERIOD = "SELECT * FROM JIMS2.CSSPVNORDERPER WHERE DEFENDANT_ID='";
	private static final String SELECT_SUPERVISION_PERIOD2 = " AND AGENCY_ID = 'CSC' AND SPRVISIONENDDATE IS NULL";
	private static final String SPRVISIONBEGDATE = "SPRVISIONBEGDATE";
	static public Date getSupervisionPeriodBeginDate(Connection connection, String spn) 
	{
		// find active supervision period
		//FROM JIMS2.CSSPVNORDERPER WHERE DEFENDANT_ID = ? and AGENCY_ID = ? AND SPRVISIONENDDATE IS NULL
        StringBuffer sql = new StringBuffer(SELECT_SUPERVISION_PERIOD);
        sql.append(spn);
        sql.append(QUOTE);
        sql.append(SELECT_SUPERVISION_PERIOD2);
        sql.append(FOR_READ_ONLY);
        Statement statement = null;
        ResultSet rs = null;
        java.sql.Date aDate = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql.toString());
            if (rs.next()){
            	aDate = rs.getDate(SPRVISIONBEGDATE);
            }
        } catch (SQLException e) {
			System.out.println(sql.toString() + " " + e.getMessage());  	
        } finally {
            try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
			}
        }
       
    	return (Date) aDate;
    }
	private static final String SELECT_WISCONSIN_INITIAL_1 = "SELECT * FROM JIMS2.CSASSESSWISCS WHERE DEFENDANT_ID='";
	private static final String SELECT_INITIAL_2 = "' AND ISREASSESSMENT=0";
	private static final String SELECT_INITIAL_3 = " AND ISPENDING=0";
	private static final String SELECT_LSIR_INITIAL_1 = "SELECT * FROM JIMS2.CSASSESSLSIRS WHERE DEFENDANT_ID='";

	private static final String ASSESSMENTDATE = "ASSESSMENTDATE";
	static public Date isInitialAssessmentComplete(Connection connection, String spn) 
	{
		//SELECT * FROM JIMS2.CSASSESSWISCS WHERE DEFENDANT_ID='?' AND ISREASSESSMENT=0;
        StringBuffer sql = new StringBuffer(SELECT_WISCONSIN_INITIAL_1);
        sql.append(spn);
        sql.append(SELECT_INITIAL_2);
        sql.append(SELECT_INITIAL_3);
        sql.append(FOR_READ_ONLY);
        Statement statement = null;
        ResultSet rs = null;
        java.sql.Date aDate = null;
        boolean initialAssessmentFound = false;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql.toString());
            if (rs.next()){
            	aDate = rs.getDate(ASSESSMENTDATE);
            	initialAssessmentFound = true;
            } 
        } catch (SQLException e) {
			System.out.println(sql.toString() + " " + e.getMessage());  	
        } finally {
            try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
			}
        }
        if (!initialAssessmentFound){
        	//SELECT * FROM JIMS2.CSASSESSLSIRS WHERE DEFENDANT_ID='?' AND ASSESSMENTTYPECD = 'L' AND ISREASSESSMENT=0;
            sql = new StringBuffer(SELECT_LSIR_INITIAL_1);
            sql.append(spn);
            sql.append(SELECT_INITIAL_2);
            sql.append(FOR_READ_ONLY);
            statement = null;
            rs = null;
            aDate = null;

            try {
                statement = connection.createStatement();
                rs = statement.executeQuery(sql.toString());
                if (rs.next()){
                	aDate = rs.getDate(ASSESSMENTDATE);
                	initialAssessmentFound = true;
                } 
            } catch (SQLException e) {
    			System.out.println(sql.toString() + " " + e.getMessage());  	
            } finally {
                try {
    				statement.close();
    				rs.close();
    			} catch (SQLException e) {
    			}
            }
       	
        	
        }
       
    	return aDate;
    }
	
	private static final int SEVEN_DAYS = 7;
	
	//Check to see if assessment date falls within one week of supervision begin date.
	public static boolean isAssessmentDateWithinOneWeekofSupervisionDate(
			Date assessmentDate, Date supervisionBeginDate) {
		
		boolean assessmentDateWithinOneWeek = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(assessmentDate);
		cal.add(Calendar.DATE, SEVEN_DAYS);
		Date newAssessmentDate = cal.getTime();
		if (newAssessmentDate.compareTo(supervisionBeginDate) >= ZERO){
			assessmentDateWithinOneWeek = true;
		}
		
		return assessmentDateWithinOneWeek;
	}
}
