package ui.juvenilecase.prodsupport.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

public class Constants {

	public static String DRIVER = "com.ibm.db2.jcc.DB2Driver";
	public static String USERID = "db2admin";
	public static String PASSWORD = "db2admin";
	
	public static String URL_D2 = "jdbc:db2://10.5.22.104:3018/HCDB2D2";
	public static String URL_O2 = "jdbc:db2://10.5.22.104:3022/HCDB2O2";
	public static String URL_E2 = "jdbc:db2://10.5.22.100:3026/HCDB2E2";
	public static String URL_R2 = "jdbc:db2://10.5.22.100:3030/HCDB2R2";
	public static String URL_PT = "jdbc:db2://10.5.20.96:3032/HCDB2PT";
	
	public static String currentDatabase = URL_D2;
	
	
	public static void writeToLog(String message, String logonid){
		System.out.println("[" + new Date() + "] ProdSupport ("
				+ logonid.toUpperCase() + ") - " + message);
	}
		
	public static Connection openConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER).newInstance();
			conn = DriverManager.getConnection(currentDatabase, USERID, PASSWORD);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}	

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean runStatement(String statement) {

		Connection conn = null;

		conn = openConnection();
		if (conn == null) {
			System.err
					.println("ERROR: (runStatment() - Constants.java) Couldn't get connection.");
			return false;
		}

		try {
			Statement st = conn.createStatement();
			st.executeUpdate(statement);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			//closeConnection(conn);
		}
		return true;
	}
	
	public static ArrayList runQuery(String query) {

		ArrayList results = null;
		Connection conn = null;
		ResultSet rSet = null;
		ResultSetMetaData rsmd = null;

		conn = openConnection();
		if (conn == null) {
			System.err.println("ERROR: (runQuery) Couldn't get connection.");
			return null;
		}

		try {
			Statement st = conn.createStatement();
			rSet = st.executeQuery(query);

			rsmd = rSet.getMetaData();

			rSet.next();

			results = getNextRow(rSet, rsmd);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//closeConnection(conn);
		}
		
		return results;
	}
	
	public static ArrayList runQueryGeneric(String query) {

		ArrayList results = null;
		Connection conn = null;
		ResultSet rSet = null;
		ResultSetMetaData rsmd = null;

		conn = openConnection();
		if (conn == null) {
			System.err.println("ERROR: (runQuery) Couldn't get connection.");
			return null;
		}

		try {
			Statement st = conn.createStatement();
			rSet = st.executeQuery(query);

			rsmd = rSet.getMetaData();

			rSet.next();

			results = getNextRowGeneric(rSet, rsmd);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//closeConnection(conn);
		}
		
		return results;
	}
	
	public static ArrayList singleOIDQuery(String tablename, String oid){
		
		String oidname = tablename.substring(2)+"_ID";
		String query = "";
		
		//Add ticks around 'JUVENILE_ID' if applicable
		if (tablename.equalsIgnoreCase("JCJUVENILE"))
			query = "select * from jims2."+tablename+" where "+oidname +"='"+oid+"';";
		else
			query = "select * from jims2."+tablename+" where "+oidname +"="+oid;
		
		return runQuery(query);
	}
	
	public static ArrayList singleOIDQuery(String tablename, String oidName, String oidValue){		
		String query = "";
		
		//Add ticks around a couple of special varchar fields
		if (oidName.equalsIgnoreCase("JUVENILE_ID")||oidName.equalsIgnoreCase("SUPERVISIONNUM"))
			query = "select * from jims2."+tablename+" where "+oidName +"='"+oidValue+"';";
		else
			query = "select * from jims2."+tablename+" where "+oidName +"="+oidValue;
		
		return runQuery(query);
	}
	
	public static ArrayList singleOIDQueryGeneric(String tablename, String oidName, String oidValue){		
		String query = "";
		
		//Add ticks around a couple of special varchar fields
		if (oidName.equalsIgnoreCase("JUVENILE_ID")||oidName.equalsIgnoreCase("SUPERVISIONNUM"))
			query = "select * from jims2."+tablename+" where "+oidName +"='"+oidValue+"';";
		else
			query = "select * from jims2."+tablename+" where "+oidName +"="+oidValue;
		
		return runQueryGeneric(query);
	}
	
	public static ArrayList populateDropDown (String codeTableName){		
		
		ArrayList codes = null;
		String codeQuery = "";
		
		/**Special case for one odd-ball codetable**/
		if (codeTableName.equals("SUPERVISION_OUTCOME_DESC"))
			codeQuery = "select * from jims2.CODETABLECHILDRN where code_table_name='SUPERVISION_OUTCOME_DESC'";
		else
			codeQuery = "select * from jims2.codetables where code_table_name='"+codeTableName+"'";

		codes = runQuery(codeQuery);
		
		return codes;
	}
	
	public static boolean SingleColumnUpdate(String logonid, String tableName, String columnName, 
			String oidName, String oidValue, String newValue){
	
		String updateStatement1 = null;
		
		if (newValue!=null && newValue.equals("")==false 
			&& oidValue!=null && oidValue.equals("")==false 
			&& oidName!=null && oidName.equals("")==false)
		{
			updateStatement1 = "UPDATE JIMS2."+tableName+" SET "+columnName+" = '"+newValue+
				"' where "+ oidName +"="+oidValue + ";";
			
			writeToLog("BEGIN "+updateStatement1, logonid);
		}

		return runStatement(updateStatement1);
	}
	
	public static ArrayList getNextRowGeneric(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {

		ArrayList queryObjects = new ArrayList();

		int numColumns = rsmd.getColumnCount();

		do {
			QueryObject row = new QueryObject();
			
			for (int columnPosition = 1; columnPosition < numColumns + 1; columnPosition++) {

				QueryColumn column = new QueryColumn();

				// To catch empty result set
				try {
					rs.getString(columnPosition);
				} catch (Exception e) {
					if (e.getMessage().contains("result set closed"))
						return null;
				}

				switch (rsmd.getColumnType(columnPosition)) {
				case Types.CHAR:
				case Types.VARCHAR:
				case Types.LONGVARCHAR:

					column.setColumnName(rsmd.getColumnName(columnPosition));
					column.setColumnValue(rs.getString(column.getColumnName()));

					break;

				case Types.DATE:
				case Types.TIMESTAMP:

					String columnName2 = rsmd.getColumnName(columnPosition);
					Timestamp tp = rs.getTimestamp(columnPosition);

					column.setColumnName(columnName2);
					
					if (tp!=null)
						column.setColumnValue(tp.toString());
					else
						column.setColumnValue(" ");
					
					break;
				case Types.BLOB:
				case Types.CLOB:
				case Types.OTHER:
					//Ignore these. They're not human-readable anyway.
					break;	
				case Types.DECIMAL:
				case Types.INTEGER:
				case Types.SMALLINT:
				case Types.DOUBLE:
				case Types.NUMERIC:
				case Types.BIGINT:

					int value = rs.getInt(columnPosition);

					String columnName3 = rsmd.getColumnName(columnPosition);

					column.setColumnName(columnName3);
					column.setColumnValue(String.valueOf(value));
					
					break;
				default:

					System.err
							.println("ERROR: Unhandled type was encountered.");
					System.err.println("Type was: "
							+ rsmd.getColumnTypeName(columnPosition));
					System.err.println("\tType code: "
							+ rsmd.getColumnType(columnPosition));

				} // end of switch
				if (column.getColumnName()!=null)
					row.columns.add(column);
				
			} // end of for

			queryObjects.add(row);	

		} while (rs.next());

		return queryObjects;

	}
	
	public static ArrayList getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {

		ArrayList queryObject = new ArrayList();

		int numColumns = rsmd.getColumnCount();

		do {
			QueryObject cell = new QueryObject();

			for (int columnPosition = 1; columnPosition < numColumns + 1; columnPosition++) {

				// To catch empty result set
				try {
					rs.getString(columnPosition);
				} catch (Exception e) {
					if (e.getMessage().contains("result set closed"))
						return null;
				}

				switch (rsmd.getColumnType(columnPosition)) {
				case Types.CHAR:
				case Types.VARCHAR:
				case Types.LONGVARCHAR:
				case Types.CLOB:

					String columnName = rsmd.getColumnName(columnPosition);
					String s = rs.getString(columnName);

					// System.err.println("Column Name: " + columnName);
					// System.err.println("\tValue: " + s);

					if (columnName.equals("CODE"))
						cell.setCode(s);
					
					if (columnName.equals("DESCRIPTION"))
						cell.setDescription(s);
					
					if (columnName.equals("ACTIVITYCD"))
						cell.setActivitycd(s);
					
					if (columnName.equals("TITLE"))
						cell.setTitle(s);
					
					if (columnName.equals("JUVENILE_ID"))
						cell.setJuvenileid(s);

					if (columnName.equals("CASEFILE_ID"))
						cell.setCasefileid(s);

					if (columnName.equals("CASESTATUSCD"))
						cell.setCasestatuscd(s);

					if (columnName.equals("SPRVSIONTYPECD"))
						cell.setSprvsiontypecd(s);

					if (columnName.equals("CREATEUSER"))
						cell.setCreateuser(s);

					if (columnName.equals("UPDATEUSER"))
						cell.setUpdateuser(s);

					if (columnName.equals("CREATEJIMS2USER"))
						cell.setCreatejims2user(s);

					if (columnName.equals("UPDATEJIMS2USER"))
						cell.setUpdatejims2user(s);

					if (columnName.equals("REFERRALNUMBER"))
						cell.setReferralnumber(s);

					if (columnName.equals("SERVICEUNITCD"))
						cell.setServiceunitcd(s);

					if (columnName.equals("ASSMNTLEVELCD"))
						cell.setAssmntlevelcd(s);

					if (columnName.equals("PLANSTATUSCD"))
						cell.setPlanstatuscd(s);

					if (columnName.equals("SUPERVISIONOUTCOME"))
						cell.setSupervisionoutcome(s);

					if (columnName.equals("CASFILECLOSNGSTATS"))
						cell.setCasefileclosngstats(s);

					if (columnName.equals("CNTROLLINGREFERRAL"))
						cell.setCntrollingreferral(s);

					if (columnName.equals("IVIEWTYPECD"))
						cell.setIviewtypecd(s);

					if (columnName.equals("STATUSCD"))
						cell.setStatuscd(s);

					if (columnName.equals("OTHERRECSINTV"))
						cell.setOtherrecsintv(s);

					if (columnName.equals("DOCTYPECD"))
						cell.setDoctypecd(s);

					if (columnName.equals("ASSESSMENTTYPE"))
						cell.setAssessmenttype(s);

					if (columnName.equals("JPOUSERID"))
						cell.setJpouserid(s);

					if (columnName.equals("ASSIGNEDHOURS"))
						cell.setAssignedhours(s);

					if (columnName.equals("SUBSTATUSCD"))
						cell.setSubstatuscd(s);

					if (columnName.equals("TITLE4EOFFICER_ID"))
						cell.setTitle4eofficerid(s);

					if (columnName.equals("TITLE4EOFFICERNAME"))
						cell.setTitle4eofficername(s);

					if (columnName.equals("JUVRELATIONDESC"))
						cell.setJuvrelationdesc(s);

					if (columnName.equals("REQUESTORNAME"))
						cell.setRequestorname(s);

					if (columnName.equals("COMPLETIONSTATUSID"))
						cell.setCompletionstatusid(s);

					if (columnName.equals("MONITORFREQUENCYID"))
						cell.setMonitorfrequencyid(s);

					if (columnName.equals("RULETYPECD"))
						cell.setRuletypecd(s);

					if (columnName.equals("STATUSCD"))
						cell.setStatuscd(s);
					
					if (columnName.equals("SUBSTATUSCD"))
						cell.setSubstatuscd(s);
					
					if (columnName.equals("OUTCOMECD"))
						cell.setOutcomecd(s);

					if (columnName.equals("TASKSUBJECT"))
						cell.setTasksubject(s);

					if (columnName.equals("TOPIC"))
						cell.setTopic(s);

					if (columnName.equals("EVENT_NAME"))
						cell.setEventname(s);

					if (columnName.equals("SCHEDULE_NAME"))
						cell.setSchedulename(s);
					
					if (columnName.equals("TASK_NAME"))
						cell.setTaskname(s);
					
					if (columnName.equals("TASK_STATUS"))
						cell.setTaskstatus(s);
					
					if (columnName.equals("FACLTYRLESEREASON"))
						cell.setFacltyrlsesereason(s);
					
					if (columnName.equals("PERMANENCYPLAN"))
						cell.setPermanencyplan(s);
					
					if (columnName.equals("FACILITY"))
						cell.setFacility(s);
					
					if (columnName.equals("FACILITY"))
						cell.setFacility(s);
					
					if (columnName.equals("LEVELOFCARE"))
						cell.setLevelofcare(s);
					
					if (columnName.equals("PETITIONNUMBER"))
						cell.setPetitionnumber(s);
					
					if (columnName.equals("EXITPLANTEMPLTLCTN"))
						cell.setExitplantempltlctn(s);
					
					if (columnName.equals("SUPERVISIONOUTCOME"))
						cell.setOutcomecd(s);
										
					if (columnName.equals("ATTENDSTATUSCD"))
						cell.setAttendstatuscd(s);
					
					if (columnName.equals("PROGRESSNOTES"))
						cell.setProgressnotes(s);
					
					if (columnName.equals("REFERRALNUMBER"))
						cell.setReferralnumber(s);
					
					if (columnName.equals("ASSESSOPTION"))
						cell.setAssessoption(s);
					
					if (columnName.equals("ASSESSOFFICER_ID"))
						cell.setAssessofficerId(s);
					
					if (columnName.equals("LENGTHOFSTAY"))
						cell.setLengthofstay(s);
					
					if (columnName.equals("FACILITYTYPECD"))
						cell.setFacilitytypecd(s);
					
					if (columnName.equals("JUVENILE_ID"))
						cell.setJuvenileid(s);
					
					if (columnName.equals("REASONNOTDONE"))
						cell.setReasonnotdone(s);
					
					if (columnName.equals("GENDERCD"))
						cell.setGendercd(s);
					
					if (columnName.equals("RACECD"))
						cell.setRacecd(s);
					
					if (columnName.equals("ETHNICBACKGROUND"))
						cell.setEthnicbackground(s);
					
					if (columnName.equals("PRIORSTAY"))
						cell.setPriorstay(s);
					
					if (columnName.equals("FACILITYNAME"))
						cell.setFacilityname(s);
					
					if (columnName.equals("FORWARD"))
						cell.setForward(s);
					
					if (columnName.equals("FEATURE"))
						cell.setFeature(s);
					
					if (columnName.equals("CURRENTGRADECD"))
						cell.setCurrentgradecd(s);
					
					if (columnName.equals("EXITTYPECD"))
						cell.setExittypecd(s);
					
					if (columnName.equals("APPROPGRADECD"))
						cell.setAppropgradecd(s);
					
					if (columnName.equals("SCHOOLCD"))
						cell.setSchoolcd(s);
					
					if (columnName.equals("SCHOOLDISTCD"))
						cell.setSchooldistcd(s);
					
					if (columnName.equals("TEACODE"))
						cell.setTeacode(s);
					
					if (columnName.equals("GRADEAVG"))
						cell.setGradeavg(s);
					
					if (columnName.equals("GRADEREPEATNUM"))
						cell.setGraderepeatnum(s);
					
					if (columnName.equals("GRADESREPEATCD"))
						cell.setGradesrepeatcd(s);
					
					if (columnName.equals("PARTICIPATIONCD"))
						cell.setParticipationcd(s);
					
					if (columnName.equals("PGMATTENDINGCD"))
						cell.setPgmattendingcd(s);
					
					if (columnName.equals("RULEINFRACTIONCD"))
						cell.setRuleinfractioncd(s);
					
					if (columnName.equals("ATTSTATUSCD"))
						cell.setAttstatuscd(s);
					
					if (columnName.equals("TRUANCYHISTORY"))
						cell.setTruancyhistory(s);
					
					if (columnName.equals("HOMESCHOOLDISTCD"))
						cell.setHomeschooldistcd(s);
					
					if (columnName.equals("HOMESCHOOLCD"))
						cell.setHomeschoolcd(s);
					
					if (columnName.equals("EVENTSTATUSCD"))
						cell.setEventstatuscd(s);
					
					if (columnName.equals("EVENTTYPECD"))
						cell.setEventtypecd(s);
					
					if (columnName.equals("EVENTCOMMENTS"))
						cell.setEventcomments(s);				
					
					if (columnName.equals("SCHOOLDERIVEDCD"))
						cell.setSchoolderivedcd(s);		
					
					if (columnName.equals("FACILITYCD"))
						cell.setFacilitycd(s);						
					
					if (columnName.equals("CONTACTFIRSTNAME"))
						cell.setContactfirstname(s);	
					
					if (columnName.equals("CONTACTLASTNAME"))
						cell.setContactlastname(s);
					
					if (columnName.equals("SEXOFFENDER"))
						cell.setSexoffender(s);	
					
					if (columnName.equals("RESTRICTOTHER"))
						cell.setRestrictother(s);	
					
					break;

				case Types.DATE:
				case Types.TIMESTAMP:

					String columnName2 = rsmd.getColumnName(columnPosition);

					Timestamp tp = rs.getTimestamp(columnPosition);
					if (tp == null) {
						break;
					}

					// System.out.println("Column Name: " + columnName2);
					// System.out.println("\tValue: " + tp.toString());

					if (columnName2.equals("INACTIVEDATE"))
						cell.setInactivedate(tp.toString());
					
					if (columnName2.equals("ACTIVITYDATE"))
						cell.setActivitydate(tp.toString());
					
					if (columnName2.equals("ACTIVATIONDATE"))
						cell.setActivationdate(tp.toString());

					if (columnName2.equals("SUPRVSIONENDDATE")
							|| columnName2.equals("SUPERVISIONENDDATE"))
						cell.setSupervsionenddate(tp.toString());

					if (columnName2.equals("CREATEDATE"))
						cell.setCreatedate(tp.toString());

					if (columnName2.equals("UPDATEDATE"))
						cell.setUpdatedate(tp.toString());

					if (columnName2.equals("ASSMNTADDDATE"))
						cell.setAssmntadddate(tp.toString());

					if (columnName2.equals("REVIEWDATE"))
						cell.setReviewdate(tp.toString());

					if (columnName2.equals("EXPECTEDRELESEDATE"))
						cell.setExpectedrelesedate(tp.toString());

					if (columnName2.equals("IVIEWDATE"))
						cell.setIviewdate(tp.toString());

					if (columnName2.equals("DATEENTERED"))
						cell.setDateentered(tp.toString());

					if (columnName2.equals("ACKDATE"))
						cell.setAckdate(tp.toString());

					if (columnName2.equals("BEGINDATE"))
						cell.setBegindate(tp.toString());

					if (columnName2.equals("ENDDATE"))
						cell.setEnddate(tp.toString());

					if (columnName2.equals("SENTDATE"))
						cell.setSentdate(tp.toString());

					if (columnName2.equals("COMPLETIONDATE"))
						cell.setCompletiondate(tp.toString());

					if (columnName2.equals("ACCEPTEDDATE"))
						cell.setAccepteddate(tp.toString());

					if (columnName2.equals("CLOSEDDATE"))
						cell.setCloseddate(tp.toString());

					if (columnName2.equals("DUEDATE"))
						cell.setDuedate(tp.toString());

					if (columnName2.equals("SUBMITTEDDATE"))
						cell.setSubmitteddate(tp.toString());

					if (columnName2.equals("NEXT_NOTICE_DATE"))
						cell.setNextnoticedate(tp.toString());
					
					if (columnName2.equals("FIRST_NOTICE_DATE"))
						cell.setFirstnoticedate(tp.toString());
					
					if (columnName2.equals("JPOASSNMNTDT"))
						cell.setJpoassnmntdt(tp.toString());
					
					if (columnName2.equals("LASTACTIONDATE"))
						cell.setLastactiondate(tp.toString());
					
					if (columnName2.equals("PRGREFASGNDATE"))
						cell.setPrgrefasgndate(tp.toString());
					
					if (columnName2.equals("ASSESSDATE"))
						cell.setAssessdate(tp.toString());
					
					if (columnName2.equals("SCREENINGDATE"))
						cell.setScreeningdate(tp.toString());
					
					if (columnName2.equals("LASTATTENDEDDATE"))
						cell.setLastattendeddate(tp.toString());
					
					if (columnName2.equals("VERIFIEDDATE"))
						cell.setVerifieddate(tp.toString());
					
					break;
				case Types.BLOB:
				case Types.OTHER:
					//What do I want to do to handle these? Or can I just ignore them?
				break;	
				case Types.DECIMAL:
				case Types.INTEGER:
				case Types.SMALLINT:
				case Types.DOUBLE:
				case Types.NUMERIC:
				case Types.BIGINT:

					int value = rs.getInt(columnPosition);

					String columnName3 = rsmd.getColumnName(columnPosition);
					// System.out.println("Column Name: " + columnName3);
					// System.out.println("\tValue: " + value);

					if (columnName3.equals("ACTIVITY_ID"))
						cell.setActivityid(String.valueOf(value));
					
					if (columnName3.equals("CASEFILE_ID"))
						cell.setCasefileid(String.valueOf(value));

					if (columnName3.equals("OFFICER_ID"))
						cell.setOfficerid(String.valueOf(value));

					if (columnName3.equals("JUVSEQNUM"))
						cell.setJuvseqnum(String.valueOf(value));

					if (columnName3.equals("ASSIGNMENT_ID"))
						cell.setAssignmentid(String.valueOf(value));

					if (columnName3.equals("PLACEMENT_ID"))
						cell.setPlacementid(String.valueOf(value));

					if (columnName3.equals("CASEPLAN_ID"))
						cell.setCaseplanid(String.valueOf(value));

					if (columnName3.equals("CASFILECLOSNG_ID"))
						cell.setCasefileclosngid(String.valueOf(value));

					if (columnName3.equals("INTERVIEW_ID"))
						cell.setInterviewid(String.valueOf(value));

					if (columnName3.equals("CALEVENT_ID"))
						cell.setCaleventid(String.valueOf(value));

					if (columnName3.equals("LOCATION_ID"))
						cell.setLocationid(String.valueOf(value));

					if (columnName3.equals("ADDRESS_ID"))
						cell.setAddressid(String.valueOf(value));

					if (columnName3.equals("JUVLOCUNIT_ID"))
						cell.setJuvlocunitid(String.valueOf(value));

					if (columnName3.equals("ISCSTMADDRSVALID"))
						cell.setIscstmaddrsvalid(String.valueOf(value));

					if (columnName3.equals("IVIEWDOC_ID"))
						cell.setIviewdocid(String.valueOf(value));

					if (columnName3.equals("FINALSCORE"))
						cell.setFinalscore(String.valueOf(value));

					if (columnName3.equals("ISCUSTODY"))
						cell.setIscustody(String.valueOf(value));

					if (columnName3.equals("RISKANALYSIS_ID"))
						cell.setRiskanalysisid(String.valueOf(value));
					
					if (columnName3.equals("RISKRESPONSES_ID"))
						cell.setRiskresponsesid(String.valueOf(value));

					if (columnName3.equals("JUVPROGREF_ID"))
						cell.setJuvprogrefid(String.valueOf(value));

					if (columnName3.equals("BENEASMNT_ID"))
						cell.setBeneasmntid(String.valueOf(value));

					if (columnName3.equals("CONSRELATION_ID"))
						cell.setConsrelationid(String.valueOf(value));

					if (columnName3.equals("SUPRULE_ID"))
						cell.setSupruleid(String.valueOf(value));

					if (columnName3.equals("CONDITION_ID"))
						cell.setConditionid(String.valueOf(value));

					if (columnName3.equals("CALEVENTCONT_ID"))
						cell.setCaleventcontid(String.valueOf(value));

					if (columnName3.equals("SOURCE_ID"))
						cell.setSourceid(String.valueOf(value));

					if (columnName3.equals("TASK_ID"))
						cell.setTaskid(String.valueOf(value));

					if (columnName3.equals("SEVLEVEL_ID"))
						cell.setSevlevel(String.valueOf(value));

					if (columnName3.equals("OWNER_ID"))
						cell.setOwnerid(String.valueOf(value));

					if (columnName3.equals("EVENT_TASK_ID"))
						cell.setEventtaskid(String.valueOf(value));

					if (columnName3.equals("EXECUTED"))
						cell.setExecuted(String.valueOf(value));
					
					if (columnName3.equals("JPOASSNMNTHIST_ID"))
						cell.setAssnmnthistid(String.valueOf(value));
					
					if (columnName3.equals("PROVPROGRAM_ID"))
						cell.setProvprogramid(String.valueOf(value));

					if (columnName3.equals("PROGRFASGNHIST_ID"))
						cell.setProgrfasgnhistid(String.valueOf(value));
					
					if (columnName3.equals("ISCLOSNGPKTGEN"))
						cell.setIsclosngpktgen(String.valueOf(value));
					
					if (columnName3.equals("ISCLOSNGLTRGEN"))
						cell.setIsclosngltrgen(String.valueOf(value));

					if (columnName3.equals("SERVATTEND_ID"))
						cell.setServattendId(String.valueOf(value));
					
					if (columnName3.equals("SERVEVENT_ID"))
						cell.setServeventId(String.valueOf(value));	
					
					if (columnName3.equals("ADDLATTENDEES"))
						cell.setAddlattendees(String.valueOf(value));
					
					if (columnName3.equals("MAYSIASSESSMNT_ID"))
						cell.setMaysiassessmntId(String.valueOf(value));
					
					if (columnName3.equals("MAYSIDETAIL_ID"))
						cell.setMaysidetailId(String.valueOf(value));
					
					if (columnName3.equals("HASPREVIOUSMAYSI"))
						cell.setHaspreviousmaysi(String.valueOf(value));
					
					if (columnName3.equals("LENGTHOFSTAY"))
						cell.setLengthofstay(String.valueOf(value));
					
					if (columnName3.equals("ISADMINISTERED")||columnName3.equals("ISADMINISTER"))
						cell.setIsadministered(String.valueOf(value));
					
					if (columnName3.equals("TESTAGE"))
						cell.setTestage(String.valueOf(value));
					
					if (columnName3.equals("JUVENILEAGE"))
						cell.setJuvenileage(String.valueOf(value));
					
					if (columnName3.equals("GENDER"))
						cell.setGender(String.valueOf(value));
					
					if (columnName3.equals("FACILITYTYPE"))
						cell.setFacilitytype(String.valueOf(value));
					
					if (columnName3.equals("ANGRYIRRITABLE"))
						cell.setAngryirritable(String.valueOf(value));
					
					if (columnName3.equals("THOUGHTDISTURB"))
						cell.setThoughtdisturb(String.valueOf(value));
				
					if (columnName3.equals("SOMATICCOMPLAINTS"))
						cell.setSomaticcomplaints(String.valueOf(value));
					
					if (columnName3.equals("ALCOHOLDRUGUSE"))
						cell.setAlcoholdruguse(String.valueOf(value));
					
					if (columnName3.equals("ALCOHOLDRUGUSE"))
						cell.setAlcoholdruguse(String.valueOf(value));
					
					if (columnName3.equals("SUICIDEIDEATION"))
						cell.setSuicideideation(String.valueOf(value));
					
					if (columnName3.equals("TRAUMATICEXP"))
						cell.setTraumaticexp(String.valueOf(value));
					
					if (columnName3.equals("DEPRESSEDANXIOUS"))
						cell.setDepressedanxious(String.valueOf(value));										
					
					if (columnName3.equals("ISSUBASMNTREF"))
						cell.setIssubasmntref(String.valueOf(value));										
					
					if (columnName3.equals("ASSESSMENTFOUND"))
						cell.setAssessmentfound(String.valueOf(value));		
					
					if (columnName3.equals("ISBENASMNTNEEDED"))
						cell.setIsbenasmntneeded(String.valueOf(value));		
					
					if (columnName3.equals("SCHOOLHIST_ID"))
						cell.setSchoolhistId(String.valueOf(value));
					
					if (columnName3.equals("CASENONCOMPDOC_ID"))
						cell.setNoncompdocid(String.valueOf(value));
					
					if (columnName3.equals("COMMONAPPDOC_ID"))
						cell.setCommonappdocid(String.valueOf(value));
					
					if (columnName3.equals("CASENONCOMNOTE_ID"))
						cell.setCasenoncomnoteid(String.valueOf(value));
					
					if (columnName3.equals("ISMAYSINEEDED"))
						cell.setIsmaysineeded(String.valueOf(value));
					
					if (columnName3.equals("SERVEVENT_ID"))
						cell.setServeventid(String.valueOf(value));
					
					if (columnName3.equals("EVENTMAXIMUM"))
						cell.setEventmaximum(String.valueOf(value));
					
					if (columnName3.equals("EVENTMINIMUM"))
						cell.setEventminimum(String.valueOf(value));
					
					if (columnName3.equals("JVSRVPRVPROF_ID"))
						cell.setJvsrvprvprofid(String.valueOf(value));
					
					if (columnName3.equals("SERVICE_ID"))
						cell.setServiceid(String.valueOf(value));
					
					if (columnName3.equals("MEMADDRESS_ID"))
						cell.setMemaddressid(String.valueOf(value));
					
					if (columnName3.equals("CURRENTENROLL"))
						cell.setCurrentenroll(String.valueOf(value));
					
					if (columnName3.equals("MEMEMPLOY_ID"))
						cell.setMememployid(String.valueOf(value));

			
					break;
				default:

					System.err
							.println("ERROR: Unhandled type was encountered.");
					System.err.println("Type was: "
							+ rsmd.getColumnTypeName(columnPosition));
					System.err.println("\tType code: "
							+ rsmd.getColumnType(columnPosition));

				} // end of switch

			} // end of for

			queryObject.add(cell);
		} while (rs.next());

		return queryObject;

	}
	
	
	

	
}
