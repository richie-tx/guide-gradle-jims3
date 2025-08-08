package pd.supervision.administerassessments.datamigration;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mojo.km.utilities.DateUtil;
import naming.CSCAssessmentConstants;
import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.Wisconsin;

public class ScheduleNextWisconsin {

	private static final String MIGRASSESSDATE = "MIGRASSESSDATE";
	//private static final String ASSESSMENTDATE = "ASSESSMENTDATE";
	private static final String ASSESSWIS_ID = "ASSESSWIS_ID";
	private static final String CREATEJIMS2USER = "CREATEJIMS2USER";
    private static String DEFENDANT_ID = "DEFENDANT_ID";
    private static final int FIVE_HUNDRED = 500;
    private static String GET_ALL_SPNS = "select distinct defendant_id from jims2.csassessment order by defendant_id for read only";
	//private static final String GET_ASSESSMENTS_POST = " order by assessmentdate desc for read only";
	private static final String GET_ASSESSMENTS_POST = " order by MIGRASSESSDATE desc for read only";
	//private static final String GET_ASSESSMENTS_PRE = "select ASSESSMENTDATE, CREATEJIMS2USER from jims2.csassessment where defendant_id='";
	private static final String GET_ASSESSMENTS_PRE = "select MIGRASSESSDATE, CREATEJIMS2USER from jims2.csassessment where defendant_id='";
	private static final String GET_ISPENDING_WIS_POST = "' AND ISPENDING = 1";
    private static final String GET_ISPENDING_WIS_PRE = "select * from jims2.CSASSESSWISCS where DEFENDANT_ID ='";
    private static final String JIDAG = "JIDAG";
    private static final String MIGR = "MIGR-"; 
    private static int ONE_NINETYNINE = 199;	     
	private static String QUOTE = "'";
	private static final String SPACE = " ";
    private static final boolean TRUE = true;
    private static final String UPDATE_ISPENDING_POST = "' WHERE ASSESSWIS_ID=";
    private static final String UPDATE_ISPENDING_PRE = "UPDATE JIMS2.CSASSESSWIS SET ASSESSMENTDUEDATE='";
    private static final int ZERO = 0;
    /**
	 * @param args
	 */
	public static void main(String[] args) {
        if (!(args != null && args.length == 1)){
		    System.out.println("!!!!! INVALID PARMS !!!!!");
		} else {
			ScheduleNextWisconsin ladm = new ScheduleNextWisconsin(args[ZERO]);
		    ladm.execute();
		}

	}
    private BufferedWriter assessmentFlatFile = null;

	private BufferedWriter errorFile = null;
	private int incrementalCounter = ZERO;
	BufferedWriter migrationResults = null;
	private int nextAssessmentOID = ZERO;
	private int nextWisconsinOID = ZERO;
	private Connection readConnection = null;
	private int totAssessmentsWritten = ZERO;
    private int totErrors = ZERO;
    private int totWisconsinsWritten = ZERO;
    private Connection updateConnection = null;
    private BufferedWriter wisconsinFlatFile = null;
    
    public ScheduleNextWisconsin(String configFileName) {
		super();
	    System.setProperty("mojo.config", configFileName);
	    System.out.println("Configuration file: "+configFileName);
		mojo.km.context.ContextManager.currentContext();
	}
    
    private void createScheduledWisconsin(String spn, Date newestAssessmentDate) {
		Assessment assessment = new Assessment();
		
		long aLong = newestAssessmentDate.getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(aLong);
		java.util.Date assessmentDate = calendar.getTime();
		assessment.setAssessmentDate(assessmentDate);
		assessment.setAssessmentTypeId(CSCAssessmentConstants.ASSESSMENTTYPE_WISCONSIN);
		assessment.setDefendantId(spn);
		assessment.setTransactionDate(new java.util.Date());
		assessment.setVersionNum(AssessmentDataMigrationHelper.ONE);
		assessment.setCreateJIMS2UserID(this.getJIMS2User());
		assessment.setCreateUserID(JIDAG);
		assessment.setCreateTimestamp(new Timestamp(new java.util.Date().getTime()));
		
		nextAssessmentOID++;
		assessment.setOID(new Integer(nextAssessmentOID).toString());
		assessment.setMasterAssessmentId(assessment.getOID());
		
		Wisconsin wisconsin = new Wisconsin();
		wisconsin.setAssessmentId(assessment.getOID());
		wisconsin.setIsPending(TRUE);

        calendar.setTime(assessmentDate);
        calendar.roll(Calendar.YEAR, TRUE);
        wisconsin.setAssessmentDueDate(calendar.getTime());
        wisconsin.setIsReassessment(false);
        wisconsin.setCreateJIMS2UserID(assessment.getCreateJIMS2UserID());
        wisconsin.setCreateUserID(JIDAG);
        wisconsin.setCreateTimestamp(assessment.getCreateTimestamp());

		nextWisconsinOID++;
		wisconsin.setOID(new Integer(nextWisconsinOID).toString());
		
		try {
			assessmentFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDataDump(assessment));
			totAssessmentsWritten++;
			wisconsinFlatFile.write(AssessmentDataMigrationDataOutputHelper.getDataDump(wisconsin));
			totWisconsinsWritten++;

		} catch (IOException e) {
			this.writeError(spn, e.getMessage());
			e.printStackTrace();
		}
		
	}
    public void execute() {
	       try {
			assessmentFlatFile = AssessmentDataMigrationHelper.createFile(
					"C:\\JDSACSA\\SCHEDWISC.ASSESSMENT", assessmentFlatFile);
			assessmentFlatFile.write(AssessmentDataMigrationDataOutputHelper
					.getDatabaseColumns(new Assessment()));
			wisconsinFlatFile = AssessmentDataMigrationHelper.createFile(
					"C:\\JDSACSA\\SCHEDWISC.WISCONSIN", wisconsinFlatFile);
			wisconsinFlatFile.write(AssessmentDataMigrationDataOutputHelper
					.getDatabaseColumns(new Wisconsin()));
			migrationResults = AssessmentDataMigrationHelper.createFile(
					"C:\\JDSACSA\\SCHEDWISC.ASSESSMENT.MIGRATION.RESULTS",
					migrationResults);
			errorFile = AssessmentDataMigrationHelper.createFile(
					"C:\\JDSACSA\\SCHEDWISC.ERRORS",
					errorFile);
			migrationResults.write("STARTING SCHEDULE NEXT WISCONSIN @ " + new java.util.Date());
			migrationResults.write(CARRIAGE_RETURN);
			readConnection = AssessmentDataMigrationHelper.getConnection();
			nextAssessmentOID = AssessmentDataMigrationHelper.getNextOID(AssessmentDataMigrationHelper.TABLE_NAME_ASSESSMENT, readConnection);
			nextWisconsinOID = AssessmentDataMigrationHelper.getNextOID(AssessmentDataMigrationHelper.TABLE_NAME_WISCONSIN, readConnection);
			readConnection.commit();
		} catch (Exception e) {
			   this.writeError(SPACE, e.getMessage());
		     	e.printStackTrace();
		     	return;
		}
		int readConnCtr = ZERO;
		try {
			updateConnection = AssessmentDataMigrationHelper.getConnection();
			List spnList = this.getSpnsWithAssessments();
			String aSpn = null;
			for (int i = ZERO; i < spnList.size(); i++) {
				aSpn = (String) spnList.get(i);
				this.processSpn(aSpn);
				readConnCtr++;
				if (readConnCtr > FIVE_HUNDRED){
					readConnection.commit();
					readConnCtr = ZERO;
				}
			}
		} catch (Exception e) {
			   this.writeError(SPACE, e.getMessage());
		     	e.printStackTrace();
		} finally {
			try {
				migrationResults.write("FINISHED SCHEDULE NEXT WISCONSIN @ " + new java.util.Date());
				readConnection.close();
				updateConnection.close();
				assessmentFlatFile.close();
				wisconsinFlatFile.close();
				migrationResults.close();
				errorFile.close();
			} catch (Exception e) {
				this.writeError(SPACE, e.getMessage());
				e.printStackTrace();
			}
		}
	}
    private String getJIMS2User() {
		StringBuffer sb = new StringBuffer(MIGR);
		sb.append(DateUtil.getCurrentDateString(DateUtil.LOG_DATE_FMT));
		return sb.toString();
	}
    private List getSpnsWithAssessments() throws SQLException {

    	StringBuffer sql = new StringBuffer(GET_ALL_SPNS);
        Statement statement = null;
        ResultSet rs = null;
        List spnList = new ArrayList();

        try {
            statement = readConnection.createStatement();
            rs = statement.executeQuery(sql.toString());
            String aSpn = null;
            while (rs.next()){
            	aSpn = rs.getString(DEFENDANT_ID);
            	spnList.add(aSpn);
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
        readConnection.commit();
    	return spnList;
    }
    private void processSpn(String spn) throws Exception {

        StringBuffer sql = new StringBuffer(GET_ASSESSMENTS_PRE);
        sql.append(spn);
        sql.append(QUOTE);
        sql.append(GET_ASSESSMENTS_POST);
        Statement statement = null;
        ResultSet rs = null;
        
        try {
            statement = readConnection.createStatement();
            rs = statement.executeQuery(sql.toString());
            Date newestAssessmentDate = null;
            boolean firstRec = true;
            boolean allAssessmentsAreMigrated = true;
            String createUser = null;
            
            while(rs.next()){
            	//Records are sorted by original assessment date descending.
            	if (firstRec){
            		//newestAssessmentDate = rs.getDate(ASSESSMENTDATE);
            		newestAssessmentDate = rs.getDate(MIGRASSESSDATE);
            		firstRec = false;
            	}
            	createUser = rs.getString(CREATEJIMS2USER);
            	if (createUser ==  null || !createUser.startsWith(MIGR)){
            		allAssessmentsAreMigrated = false;
            		break;
            	}
            }
            //If all assessments migrated, create next wisconsin based on newest assessment date.
            if (allAssessmentsAreMigrated){
            	this.createScheduledWisconsin(spn, newestAssessmentDate);
            } else {
                //If there are assessments that were created via the application, read for scheduled
                //Wisconsin and update with newest AssessmentDate.
            	this.updateScheduledWisconsin(spn, newestAssessmentDate);
            }
    		if (incrementalCounter > ONE_NINETYNINE){
    			StringBuffer msg = new StringBuffer("PROCESSED SPN=");
    			msg.append(spn);
    			msg.append(CARRIAGE_RETURN);
    			migrationResults.write(msg.toString());
    			incrementalCounter = ZERO;
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

	}
    private static final char CARRIAGE_RETURN = '\n';	
    
    private void updateScheduledWisconsin(String spn, Date newestAssessmentDate) {
        Statement statement = null;
        ResultSet rs = null;
        
        try {
            StringBuffer sql = new StringBuffer(GET_ISPENDING_WIS_PRE);
            sql.append(spn);
            sql.append(GET_ISPENDING_WIS_POST);
            
            statement = updateConnection.createStatement();
            rs = statement.executeQuery(sql.toString());
            if (rs.next()){
            	String theOid = rs.getString(ASSESSWIS_ID);
				sql = new StringBuffer(UPDATE_ISPENDING_PRE);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(newestAssessmentDate);
			    calendar.roll(Calendar.YEAR, true);
			    Timestamp ts = new Timestamp(calendar.getTimeInMillis());
			    sql.append(ts);
			    sql.append(UPDATE_ISPENDING_POST);
			    sql.append(theOid);
			    statement.executeUpdate(sql.toString());
			    updateConnection.commit();
			} else {
				this.writeError(spn, "ISPENDING WISCONSIN NOT FOUND FOR SPN");
				migrationResults.write(spn + " UPDATE OF SCHEDULED WISCONSIN FOR THIS SPN. OID=");
				migrationResults.write(CARRIAGE_RETURN);
			}
		} catch (Exception e) {
			this.writeError(spn, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
			} catch (SQLException e) {
				this.writeError(SPACE, e.getMessage());
				e.printStackTrace();
			}
		}
	}
    
    private void writeError(String aString, String message) {
		totErrors++;
		StringBuffer msg = new StringBuffer(aString);
		msg.append(SPACE);
		if (message != null){
			msg.append(message);
		}
		try {
			errorFile.write(msg.toString());
			errorFile.write(CARRIAGE_RETURN);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
