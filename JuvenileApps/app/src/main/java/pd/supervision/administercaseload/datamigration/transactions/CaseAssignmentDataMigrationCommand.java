/*
 * Created on Oct 29, 2007
 *
 */
package pd.supervision.administercaseload.datamigration.transactions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

import naming.CaseloadConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.UIConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.user.UserProfile;
import pd.criminalcase.CriminalCase;
import pd.supervision.administercaseload.datamigration.CaseAssignmentMigr;
import pd.supervision.administercaseload.datamigration.SuperviseeHistoryMigr;
import pd.supervision.administercaseload.datamigration.SuperviseeMigr;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercaseload.datamigration.LegacyCaseAssignmentExtract;
import pd.supervision.managetask.CSTask;
import pd.supervision.managetask.PDTaskHelper;
import pd.supervision.manageworkgroup.WorkGroup;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import messaging.cscdstaffposition.GetOrganizationStaffPositionByPOIEvent;
import messaging.datamigration.CaseAssignmentDataMigrationEvent;
import messaging.managetask.CreateCSTaskEvent;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.MojoProperties;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;

/**
 * @author dgibler
 *  
 */
public class CaseAssignmentDataMigrationCommand {
    private static final String AND = " AND ";
    private static final String AND_CM_KEY_CAS = " AND CM_KEY_CAS = ";

    /**
     * @param msg
     * @param recCounter
     */
    private static final String BENCHMARK = "BENCHMARK ";
    private static final String BLANK = " ";
    
    private static final char CARRIAGE_RETURN = '\n';
    
    /**
     * @param criminalCaseId
     * @return
     */
    private static final String CASE_ASSIGN_SELECT = "SELECT * FROM JIMS2.CSCASEASSIGN WHERE CRIMINALCASE_ID = ";
    private static final String CASE_ASSIGNMENT_ALREADY_EXISTS = "CASE ASSIGNMENT ALREADY EXISTS IN JIMS2";
    private static final String CASE_NOT_FOUND_MSG = "CASE NOT FOUND";
    private static final String JP_COURT_NEEDED = "JP COURT NEEDED IN M204 ";
    /**
     * @param courtNum
     * @return
     */
    private static final String CDI_002 = "002";

    private static final String CDI_003 = "003";
    private static final String CDI_001 = "001";
    private static final String CDI_010 = "010";
    private static final String CDI_020 = "020";
    private static String CLO_JOB_TITLE_ID = null;
    
    private static String CLOFLOATER_JOB_TITLE_ID = null;
    private static final String CM_AA_CCR = "CM_AA_CCR";
    private static final String CM_AA_COF = "CM_AA_COF";
    
    /**
     * @param criminalCaseId
     * @return
     */
    private static final String CM_JJ_NAM = "CM_JJ_NAM";
    
    private static final String CM_KEY_CAS = "CM_KEY_CAS";
    /**
     * @param criminalCaseId
     * @return
     */
    private static final String CM_KEY_CDI = "CM_KEY_CDI";
    private static final String COL_CRIMINALCASE_ID = "CRIMINALCASE_ID";
    
    private static final String COL_DEFENDANT_ID = "DEFENDANT_ID";
    private static final String COMMA_SPACE = ", ";
    //private static final String CRIMINALCASE_SELECT = "SELECT * FROM VSAM.CMAA WHERE CM_KEY_CDI = ";
    private static final String CRIMINALCASE_SELECT = "SELECT * FROM JIMS2.CMAA WHERE CM_KEY_CDI = ";

    private static final String CSCD = "CSC";
    private static String CSO_JOB_TITLE_ID = null;

    /**
     * @param workGroupId
     * @param caseAssignment
     * @param order
     * @param criminalCase
     */
    private static final String CTG_CC = "CC ";

    private static final String CTG_CR = "CR ";

    private static final String CTG_JP = "JP ";
    private static final String CTG_OC = "OC ";
    private static final String CURRENTCOURT_ID = "CURRENTCOURT_ID";
    
    private static final String DEFENDANT_ID = "DEFENDANT_ID";
    //private static final String DEFNAME_SELECT = "SELECT * FROM VSAM.CMJJ WHERE CM_KEY_SNU = '999' AND CM_KEY_CDI = ";
    private static final String DEFNAME_SELECT = "SELECT * FROM JIMS2.CMJJ WHERE CM_KEY_SNU = '999' AND CM_KEY_CDI = ";
    private static final String EQUAL = "=";
    private static final String FIRST_RECORD_NOT_CURRENT_POI = "FIRST RECORD NOT CURRENT POI";
    private static final int FIVE_HUNDRED = 500;
    private static final String FOR_READ_ONLY = " FOR READ ONLY";
    /**
     * @param courtNum
     * @return
     */
    private static final int FOUR = 4;
    private static final String ISCURRENTPOI = "ISCURRENTPOI";
    private static final String ISCURRENT = "ISCURRENT";

    private static final String JOBTITLE_ID = "JOBTITLE_ID";
    private static final String STATUS_ID = "STATUS_ID";
    private static String STAFF_STATUS_RETIRED= null;
    
    private static final String JP_CRT = "J";
     private static final String JZP_SEQ_NUM = "JZP_SEQ_NUM";

    private static final String LEGACY_POI = "LEGACY_POI";
    
    /**
     * Creates CaseAssignmentHistory records.
     * @param caseAssignment
     * @param legacyCaseAssignments
     * @param courtId
     * @param userProfileMap
     * @param staffByPOIMap
     */
    private static final int NEGATIVE_ONE = -1;
    private static Map newCasesPOIMap = null;
    //private static String [] newCasesPOIs = {"RQ", "Z@", "R", "R6", "R@", "V$", "J1", "V@", "V#", "V%", "RP", "RN"};
    //private static String [] newCasesPOIs = {"7","FF","#9","?","A$","AX","BA","BB","BW","BX","BY","BZ","E","F1","G","H","I","J","J1","JL","K","K1","KA","L","N","P","P1","P8","PF","PN","PZ","QA","QQ","QS","R","R@","R4","R6","S","V#","V$","V%","V@","W","X","Y@","ZA","ZG","ZZ","H9","B9","FV"};
    //Removed BB, BW as per Glenda on 2/24/10
    //Added 7Z which became effective on 4/01.
    //Re-added BB
    //05/12/10 - removed BB
    //05/21/10 - REMOVEd BX,BY,PZ,P1,V$,V%
    //private static String [] newCasesPOIs = {"7","FF","#9","?","A$","AX","BA","BX","BY","BZ","E","F1","G","H","I","J","J1","JL","K","K1","KA","L","N","P","P1","P8","PF","PN","PZ","QA","QQ","QS","R","R@","R4","R6","S","V#","V$","V%","V@","W","X","Y@","ZA","ZG","ZZ","H9","B9","FV","7Z"};
    private static String [] newCasesPOIs = {"7","FF","#9","?","A$","AX","BA","BZ","E","F1","G","H","I","J","J1","JL","K","K1","KA","L","N","P","P8","PF","PN","QA","QQ","QS","R","R@","R4","R6","S","V#","V@","W","X","Y@","ZA","ZG","ZZ","H9","B9","FV","7Z"};

    private static final String NO_ACTIVE_SUPERVISION_ORDER = "NO ACTIVE SUPERVISION ORDER IN JIMS2";
    private static final String NO_PROBATION_OFFICER_ASSIGNMENT = "NO PROBATION OFFICER ASSIGNMENT FOR CASE";
    /**
     * @param poi
     * @return
     */
    private static final String OID = "OID";
    /**
     * Validate legacy case information.
     * @param legacyCaseAssignments
     * @param staffMap
     * @return
     */
    private static final int ONE = 1;
    private static final int ONE_HUNDRED = 100;
    
	private static final String ORDER_FILED = "New Order For Supervision";
 
    private static final String ORDER_FILED_TEXT = " Order filed";

    private static final String ORGANIZATION_ID = "ORGANIZATION_ID";
    
    private static final String ORGANIZATION_SELECT = "SELECT * FROM JIMS2.CSORGANIZATION WHERE AGENCY_ID = 'CSC' AND PROBOFFICERIND = ";
    private Map orgPOIMap = null;
    private static final String PIPE = "|";	
    private static final String POI_PROGRAM_UNIT_NOT_FOUND = "PROGRAM UNIT NOT FOUND FOR POI";
    private static final String STAFF_POSITION_WITHOUT_PROGRAM_UNIT = "STAFF POSITION HAS NO PROGRAM UNIT";
    
    private static final String PROBATION_OFFICER_NOT_FOUND = "PROBATION OFFICER STAFF POSITION NOT FOUND";
    private static final String PROBATION_OFFICER_NOT_FOUND_NOT_CURRENT = "PROBATION OFFICER STAFF POSITION NOT FOUND (NOT CURRENT OFFICER)";
    private static final String PROBATION_TERMINATED = "**";
    private static final String PROBATION_TERMINATED_CASE_NOT_MIGRATED = "PROBATION TERMINATED - CASE NOT MIGRATED";
    private static final String PROBOFFICERIND = "PROBOFFICERIND";
    private static final String QUOTE = "'";
    private static final int[] RECORD_FMT = { 8, 23, 25, 31, 33, 34 };
    /**
     * Retrieve staff position via POI.
     * @param legacyCaseAssignment
     * @param staffbyPOIMap
     */
    private static final String SELECT_STAFF_POS = "SELECT * FROM JIMS2.CSORGSTAFFPOSITION WHERE AGENCY_ID = 'CSC' AND PROBOFFICERIND = ";
    private static final String SPRVISIONORDER_ID= "SPRVISIONORDER_ID";
    
    private static final String STAFF_POSITION_UNASSIGNED = "STAFF POSITION NOT ASSIGNED";
    private static final String STAFFPOSITION_ID = "STAFFPOSITION_ID";
    private static final String STATUS = "STATUS";
    
    private static final String STATUS_CODE_OPEN = "O";

    private static final String SUP_ORDER_SELECT = "SELECT * FROM JIMS2.CSSPRVISIONORDER WHERE AGENCYCD = 'CSC' AND ORDERSTATUSCD = 'A' AND DEFENDANT_ID=";
    
    /**
     * @param defendantId
     * @return
     */
    private static final String SUPERVISEE_SELECT = "SELECT * FROM JIMS2.CSSUPERVISEE WHERE DEFENDANT_ID = '";

    private static final int THREE = 3;
    private static final String TRANS_DATE = "TRANS_DATE";

    private static final String UNIT_ID = "UNIT_ID";
    /**
     * @param caseAssignments
     */
    private static final String UPDATE_TEMPPMJZ_SQL = "UPDATE JIMS2.TEMPPMJZ SET STATUS='C' WHERE ";

    private static final String VERSION = "Version #";
    private static final String VERSIONNUM = "VERSIONNUM";
    private static final String VERSIONTYPECD = "VERSIONTYPECD";
    private static final String WORKGROUP_ID = "WORKGROUP_ID";
    //private static final String WORKGROUP_POI_SELECT = "SELECT * FROM JIMS2.TEMPDM WHERE POI = '";
    private static final String WORKGROUP_PROGRAMUNIT_SELECT = "SELECT * FROM JIMS2.TEMPDM WHERE PROGRAMUNIT_ID = '";
    //private static final String WORKGROUP_SELECT = "SELECT * FROM JIMS2.CSWORKGROUP WHERE AGENCY_ID='CSC' AND TYPECD = 'MI' AND NAME = '";
    private static final String WORKGROUP_SELECT = "SELECT * FROM JIMS2.CSWORKGROUP WHERE AGENCY_ID='CSC' AND NAME = '";
    
    /**
     * @param caseAssignment
     * @param staffPos
     */
    private static final String WORKGROUPNAME = "WORKGROUPNAME";
    private static final String YES = "Y";
    private static final int ZERO = 0;
    
    public static void main(String[] args) {
        Date startTime = new Date();
		CaseAssignmentDataMigrationEvent requestEvent = new CaseAssignmentDataMigrationEvent();
		if (!(args != null && args.length == 4)){
		    System.out.println("!!!!! INVALID PARMS !!!!!");
		} else {
			StringBuffer sb = new StringBuffer("restartKey args[0]=");
		    int restartKey = new Integer(args[0]).intValue();
		    sb.append(restartKey);
		    requestEvent.setRestartKey(restartKey);
		    sb.append(" isUpdate args[1]=);");
		    boolean isUpdate = new Boolean(args[1]).booleanValue();
		    sb.append(isUpdate);
		    requestEvent.setUpdate(isUpdate);
		    sb.append( " defendantId args[2]=");
		    sb.append(args[2]);
		    System.out.println(sb.toString());
		    requestEvent.setDefendantId(args[2]);
	        CaseAssignmentDataMigrationCommand comm = new CaseAssignmentDataMigrationCommand(args[3]);

		    comm.execute(requestEvent);
		    sb = new StringBuffer();
		    sb.append('\n');
		    sb.append('\n');
		    sb.append("PARMS: ");
		    for (int i = 0; i < args.length; i++) {
		        if (i > 0){
	                sb.append(" ");
		        }
                sb.append(args[i]);
            }
		    sb.append('\n');
		    sb.append("START TIME: ");
		    sb.append(startTime);
		    sb.append('\n');
		    sb.append("END TIME: ");
		    sb.append(new Date());
		    System.out.println(sb.toString());
		}
    }
    private BufferedWriter benchMarkFile = null;

    private Connection connection;

    private StringBuffer connURL = null;
    private BufferedWriter debugFile = null;
    private BufferedWriter exceptionFile = null;
    private BufferedWriter orderExceptionFile = null;
 
    private boolean isDebug = false;
    private boolean isUpdate = false;
    private String logonId = null;
    private String password = null;
    private Connection readConnection = null;
    private Map staffByOidMap = null;

    private Connection updateConnection = null;
    /**
     * 
     */
    public CaseAssignmentDataMigrationCommand(String configFileName) {
        super();
        System.setProperty("mojo.config", configFileName);
        System.out.println("Configuration file: "+configFileName);
		mojo.km.context.ContextManager.currentContext();
        debugFile = this.createFile("C:\\jzp.case.assignment.debug", debugFile);
    }

    /**
     * 
     */
    private void buildNewCasesPOIMap(){
        newCasesPOIMap = new HashMap();
        String poi = null;
        for (int i = ZERO; i < newCasesPOIs.length; i++) {
            poi = newCasesPOIs [i];
            newCasesPOIMap.put(poi, poi);
        }
    }
    /**
     * Creates CaseAssignment record from legacy info.
     * @param legacyCaseAssignment
     * @param criminalCase
     * @param staffByPOIMap
     * @return
     */

    private CaseAssignmentMigr createCaseAssignment(LegacyCaseAssignmentExtract legacyCaseAssignment, CriminalCase criminalCase, Map staffByPOIMap, Map orderMap) throws SQLException {
        //this.writeDebug("START createCaseAssignment");
        CaseAssignmentMigr caseAssignment = new CaseAssignmentMigr();
        if (legacyCaseAssignment.getPoi() != null && !legacyCaseAssignment.getPoi().equals(PROBATION_TERMINATED)) {
            CSCDOrganizationStaffPosition staffPosition = this.getStaffPosition(legacyCaseAssignment, staffByPOIMap);
            if (staffPosition != null) {
            	if (!staffPosition.getProgramUnitId().equals("0")){
            		caseAssignment.setAssignedStaffPositionId(staffPosition.getStaffPositionId());
            		caseAssignment.setAcknowledgePositionId(staffPosition.getStaffPositionId());
            		caseAssignment.setAcknowledgeUserId(staffPosition.getUserProfileId());
            		caseAssignment.setProgramUnitId(staffPosition.getProgramUnitId());
            		caseAssignment.setOfficerAssignDate(legacyCaseAssignment.getTransactionDate());
            		caseAssignment.setAcknowlegeDate(legacyCaseAssignment.getTransactionDate());
            		caseAssignment.setProgramUnitAssignDate(legacyCaseAssignment.getTransactionDate());
            		caseAssignment.setCaseState(CaseloadConstants.OFFICER_ACKNOWLEDGED);
            	} else {
            		caseAssignment = null;
            		StringBuffer sb = new StringBuffer(STAFF_POSITION_WITHOUT_PROGRAM_UNIT);
            		sb.append("-");
            		sb.append(staffPosition.getStaffPositionId());
                    this.writeError(sb.toString(), legacyCaseAssignment);
            	}
                //caseAssignment.setAcknowledgeRoleId(CaseloadConstants.OP_ACKNOWLEDGE_OFFICER_ASSIGNMENT);
            } else { //Check for Program Unit assignment.
                String programUnitId = this.getProgramUnitByPOI(legacyCaseAssignment.getPoi().trim());
            	//if (this.isNewCasesPOI(legacyCaseAssignment.getPoi())){
                    //String programUnitId = this.getProgramUnitByPOI(legacyCaseAssignment.getPoi());
                    if (programUnitId != null){
                        caseAssignment.setAssignedStaffPositionId(null);
                        caseAssignment.setProgramUnitId(programUnitId);
                        caseAssignment.setOfficerAssignDate(null);
                        caseAssignment.setProgramUnitAssignDate(legacyCaseAssignment.getTransactionDate());
                        caseAssignment.setCaseState(CaseloadConstants.PROGRAM_UNIT_ASSIGNED);
                        caseAssignment.setAcknowledgeRoleId(CaseloadConstants.OP_ACKNOWLEDGE_WORKGROUP_ASSIGNMENT);
                    } else {
                        caseAssignment = null;
                        this.writeError(POI_PROGRAM_UNIT_NOT_FOUND, legacyCaseAssignment);
                    }
                //} else {
                //    caseAssignment = null;
                //}
            }
        }
        if (caseAssignment != null) {
            caseAssignment.setAcknowledgeStatusId(CaseloadConstants.ACKNOWLEDGMENT_STATUS_ASSUMED);
            caseAssignment.setCriminalCaseId(legacyCaseAssignment.getCriminalCaseId());
            caseAssignment.setDefendantId(legacyCaseAssignment.getSpn());
            caseAssignment.setSupervisionStyleId(CaseloadConstants.SUPERVISION_STYLE_DIRECT);
            SupervisionOrder order = (SupervisionOrder) orderMap.get(caseAssignment.getCriminalCaseId());
            caseAssignment.setSupervisionOrderId(order.getOID());
            caseAssignment.bind(updateConnection, logonId,password);

        }
        //this.writeDebug("END createCaseAssignment");

        return caseAssignment;
    }
    /**
     * @param caseAssignment
     * @param legacyCaseAssignments
     * @param courtId
     * @param userProfileMap
     * @param staffByPOIMap
     * @throws SQLException 
     */
    private void createCaseAssignmentHistory(
            CaseAssignmentMigr caseAssignment, ArrayList legacyCaseAssignments, String courtId, Map userProfileMap, Map staffByPOIMap) throws SQLException {
        if (this.isDebug) {this.writeDebug("START createCaseAssignmentHistory");}
        if (caseAssignment == null){
        	return;
        }
        LegacyCaseAssignmentExtract theLegacyCaseAssignment = null;
        LegacyCaseAssignmentExtract prevLegacyCaseAssignment = null;
        pd.supervision.administercaseload.datamigration.CaseAssignmentHistoryMigr history = null;
        CSCDOrganizationStaffPosition thisStaffPos = null;
        CSCDOrganizationStaffPosition prevStaffPos = null;
        UserProfile userProfile = null;
        int rowCount = NEGATIVE_ONE;
        int nextRow = ZERO;
        String programUnitId = null;
        Iterator iter = legacyCaseAssignments.iterator();
        List reverseChronHistories = new ArrayList();
        if (iter != null && iter.hasNext()){
            while (iter.hasNext()){
                rowCount++;
                history = new pd.supervision.administercaseload.datamigration.CaseAssignmentHistoryMigr();
                theLegacyCaseAssignment = (LegacyCaseAssignmentExtract) iter.next();       
                if (PROBATION_TERMINATED.equals(theLegacyCaseAssignment.getPoi())){
                    if (rowCount == ZERO){
                        history.setTerminationDate(caseAssignment.getTerminationDate());
                        history.setAssignedStaffPositionId(caseAssignment.getAssignedStaffPositionId());
                        history.setProgramUnitId(caseAssignment.getProgramUnitId());
                        history.setOfficerAssignDate(caseAssignment.getOfficerAssignDate());
                        history.setProgramUnitAssignDate(caseAssignment.getProgramUnitAssignDate());
                        history.setHistoryType(CaseloadConstants.OFFICER_ASSIGNED);
                    } else {
                        history.setTerminationDate(theLegacyCaseAssignment.getTransactionDate());
                        nextRow = rowCount + ONE;
                        prevLegacyCaseAssignment = (LegacyCaseAssignmentExtract) legacyCaseAssignments.get(nextRow);
                        thisStaffPos = this.getStaffPosition(prevLegacyCaseAssignment, staffByPOIMap);
                        if (thisStaffPos != null){
                            history.setAssignedStaffPositionId(thisStaffPos.getStaffPositionId());
                            history.setProgramUnitId(thisStaffPos.getProgramUnitId());
                            history.setOfficerAssignDate(theLegacyCaseAssignment.getTransactionDate());
                        } else {
                        	programUnitId = this.getProgramUnitByPOI(prevLegacyCaseAssignment.getPoi());
                        	//if (this.isNewCasesPOI(prevLegacyCaseAssignment.getPoi())){
                        	if (programUnitId != null){
                                history.setHistoryType(CaseloadConstants.PROGRAM_UNIT_ASSIGNED);
                                //history.setProgramUnitId(this.getProgramUnitByPOI(prevLegacyCaseAssignment.getPoi()));
                                history.setProgramUnitId(programUnitId);
                                history.setProgramUnitAssignDate(prevLegacyCaseAssignment.getTransactionDate());
                            } else {
                                continue;//staff position no longer exists; do not create history record.
                            }
                        }
                     }
                } else { //Probation not terminated.
                    thisStaffPos = this.getStaffPosition(theLegacyCaseAssignment, staffByPOIMap);
                    if (thisStaffPos != null){
                        history.setProgramUnitId(thisStaffPos.getProgramUnitId());
                        history.setAssignedStaffPositionId(thisStaffPos.getStaffPositionId());
                        history.setAcknowledgePositionId(thisStaffPos.getStaffPositionId());
                       	history.setAcknowledgeUserId(thisStaffPos.getUserProfileId());
                        history.setAcknowledgeDate(theLegacyCaseAssignment.getTransactionDate());
                        history.setHistoryType(CaseloadConstants.OFFICER_ASSIGNED);
                        history.setOfficerAssignDate(theLegacyCaseAssignment.getTransactionDate());
                    } else {
                    	programUnitId = this.getProgramUnitByPOI(theLegacyCaseAssignment.getPoi());
                        //if (this.isNewCasesPOI(theLegacyCaseAssignment.getPoi())){
                    	if (programUnitId != null){
                            history.setHistoryType(CaseloadConstants.PROGRAM_UNIT_ASSIGNED);
                            //history.setProgramUnitId(this.getProgramUnitByPOI(theLegacyCaseAssignment.getPoi()));
                            history.setProgramUnitId(programUnitId);
                            history.setProgramUnitAssignDate(theLegacyCaseAssignment.getTransactionDate());
                        } else {
                            continue; //staff position no longer exists; do not create history record.
                        }
                    }
                } 
                history.setAcknowledgeStatusId(CaseloadConstants.ACKNOWLEDGMENT_STATUS_ASSUMED);
                history.setCourtNum(courtId);
        		history.setSupervisionStyleId(CaseloadConstants.SUPERVISION_STYLE_DIRECT);
        		history.setCaseAssignmentId(caseAssignment.getOID());
        		//history.bind(updateConnection,logonId, password);
        		reverseChronHistories.add(ZERO, history);
            }
        }
        
        if (reverseChronHistories.size() > 0){
        	Date progUnitAssignDate = null;
        	pd.supervision.administercaseload.datamigration.CaseAssignmentHistoryMigr prevHistory = null;
        	for (int i = 0; i < reverseChronHistories.size(); i++) {
				history = (pd.supervision.administercaseload.datamigration.CaseAssignmentHistoryMigr) reverseChronHistories.get(i);
				//Retain programUnitId and assignDate to be stored on more recent history records.
				if (history.getProgramUnitAssignDate() != null){	
					progUnitAssignDate = history.getProgramUnitAssignDate();
				} else {
					if (progUnitAssignDate != null){
						history.setProgramUnitAssignDate(progUnitAssignDate);
					} else {
						int prevIndex = i - 1;
						if (prevIndex < 0){
							history.setProgramUnitAssignDate(history.getOfficerAssignDate());
							history.setProgramUnitId(history.getProgramUnitId());
						} else {
							prevHistory = (pd.supervision.administercaseload.datamigration.CaseAssignmentHistoryMigr) reverseChronHistories.get(prevIndex);
							history.setProgramUnitAssignDate(prevHistory.getProgramUnitAssignDate());
							history.setProgramUnitId(prevHistory.getProgramUnitId());
						}
					}
				} 
				history.bind(updateConnection,logonId, password);
			}
			//history = (pd.supervision.administercaseload.datamigration.CaseAssignmentHistoryMigr) reverseChronHistories.get(reverseChronHistories.size()-1);
			//Set caseAssignment programUnitAssignDate to most recent history programUnitAssignDate.
			/* caseAssignment.setProgramUnitAssignDate(history.getProgramUnitAssignDate());
			caseAssignment.setProgramUnitId(history.getProgramUnitId());
			caseAssignment.update(updateConnection, logonId, password);
			*/
         }
        try {
            updateConnection.commit();  //commit history inserts
        } catch (SQLException e) {
        	System.out.println(e.getLocalizedMessage());
        	throw e;
        }
 
        if (this.isDebug) {this.writeDebug("END createCaseAssignmentHistory");}
        
    }
    private BufferedWriter createFile(String fileName, BufferedWriter bufferedWriter){
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
    private LegacyCaseAssignmentExtract createLegacyCaseAssignment(ResultSet rs) throws SQLException {
        //this.writeDebug("START createLegacyCaseAssignment");

        LegacyCaseAssignmentExtract lca = new LegacyCaseAssignmentExtract();
	    try {
            lca.setCriminalCaseId(rs.getString(COL_CRIMINALCASE_ID));
            lca.setSpn(rs.getString(DEFENDANT_ID));
            //lca.setCurrPoi(rs.getBoolean(ISCURRENTPOI));
            lca.setCurrPoi(rs.getBoolean(ISCURRENT));

            lca.setPmJzpSeqNum(rs.getString(JZP_SEQ_NUM));
            lca.setPoi(rs.getString(LEGACY_POI));
            lca.setRecordSeqNum(rs.getInt(OID));
            lca.setStatusCd(rs.getString(STATUS));
            lca.setTransactionDate(rs.getDate(TRANS_DATE));
            //this.writeFile(lca.toString(), logFile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //this.writeDebug("END createLegacyCaseAssignment");
        return lca;
	}
    /**
     * @param caseAssignments
     * @return
     * @throws SQLException 
     */
    private void createSupervisee(List caseAssignments, Map staffbyPOIMap) throws SQLException {
        if (this.isDebug) {this.writeDebug("START createSupervisee");}
        CaseAssignmentMigr caseAssignment = (CaseAssignmentMigr) caseAssignments.get(ZERO);
        Supervisee supervisee = this.getSupervisee(caseAssignment.getDefendantId());
        SuperviseeMigr supMigr = new SuperviseeMigr();
        /*if (supervisee == null){
            supMigr = new SuperviseeMigr();
            supMigr.setDefendantId(caseAssignment.getDefendantId());
        } */
        supMigr.setDefendantId(supervisee.getDefendantId());
        supMigr.setOID(supervisee.getOID());
        this.determineCaseloadCredit(supMigr, caseAssignments, staffbyPOIMap);
        //migrating active supervisees only.
        supMigr.setCurrentlySupervised(true);
        try {
			supMigr.bind(updateConnection, logonId, password);
		} catch (SQLException e) {
			throw e;
		}
        
        SuperviseeHistoryMigr sh = new SuperviseeHistoryMigr();
        sh.setSuperviseeId(supMigr.getOID());
        sh.setAssignedProgramUnitId(supMigr.getAssignedProgramUnitId());
        sh.setProgramUnitAssignDate(supMigr.getProgramUnitAssignDate());
        sh.setAssignedStaffPositionId(supMigr.getAssignedStaffPositionId());
        sh.setCaseloadCreditStaffPositionId(supMigr.getCaseloadCreditStaffPositionId());
        if (supMigr.getAssignedStaffPositionId() != null){
        	sh.setType(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_STAFF.toUpperCase());
        } else {
        	sh.setType(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_PU.toUpperCase());
        }
        
        sh.setCurrentlySupervised(supMigr.isCurrentlySupervised());
        try {
			sh.bind(updateConnection, logonId, password);
		} catch (SQLException e) {
			throw e;
		}
        if (this.isDebug) {this.writeDebug("END createSupervisee");}
     }
    private static final String CS_INTAKE = "CS INTAKE";
    private static final String OOC_INTAKE = "CS OOC INTAKE";
    
    private void createTask(String workGroupName, String workGroupId, CaseAssignmentMigr caseAssignment, SupervisionOrder order, CriminalCase criminalCase) throws Exception {
        if (this.isDebug) {this.writeDebug("START createTask");}
        CreateCSTaskEvent createTask = new CreateCSTaskEvent();
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, ONE);
	    createTask.setDueDate(cal.getTime());
	    createTask.setStatusId(STATUS_CODE_OPEN);
	
	    StringBuffer subject = new StringBuffer();
	    StringBuffer text= new StringBuffer();
	    if(order.getVersionTypeId()!=null && order.getVersionTypeId().equals(PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL)){
	        subject.append(ORDER_FILED);
	        //text=subject + " " + criminalCase.getDefendantName().trim() + ", " + caseAssignment.getDefendantId()+ ", " + order.getCurrentCourtId();
	    }
	    else{
	        //subject="Version #"+ order.getVersionNum() + " " + order.getVersionType().getDescription() + " Order filed";
	        subject.append(VERSION);
	        subject.append(order.getVersionNum());
	        subject.append(BLANK);
	        subject.append(order.getVersionType().getDescription());
	        subject.append(ORDER_FILED_TEXT);
	        //text=subject + " for Supervision" + criminalCase.getDefendantName().trim()+ ", " + criminalCase.getDefendantId() + ", " + order.getCriminalCaseId()+ ", " + order.getCurrentCourtId();
	    }
        text.append(subject);
        text.append(BLANK);
        //text.append(criminalCase.getDefendantName().trim());
        text.append(this.getDefendantName(caseAssignment.getCriminalCaseId()).trim());
        text.append(COMMA_SPACE);
        text.append(caseAssignment.getDefendantId());
        text.append(COMMA_SPACE);
        text.append(order.getCurrentCourtId());

	    createTask.setTaskSubject(subject.toString());
	    createTask.setTastText( text.toString() );
	    createTask.setDefendantId( caseAssignment.getDefendantId() );
	    createTask.setSuperviseeName( order.getPrintedName() );
	    createTask.setSupervisionOrderId( order.getOID() );
	    createTask.setCriminalCaseId( caseAssignment.getCriminalCaseId() );
	    createTask.setCourtId( order.getCurrentCourtId() );
	    createTask.setCaseAssignId( caseAssignment.getOID() );
	    if (workGroupName.equals(CS_INTAKE) || workGroupName.equals(OOC_INTAKE)){
	    	createTask.setTopic(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU);
	    	createTask.setScenario( CaseloadConstants.ASSIGN_PROGRAM_UNIT_PAGEFLOW );
	    } else {
	    	createTask.setTopic( CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP );
	    	createTask.setScenario( CaseloadConstants.ALLOCATE_SUPERVISOR_PAGEFLOW );
	    }
	    createTask.setWorkGroupId( workGroupId );
	    
		try {
              CSTask task = PDTaskHelper.createCSTask( createTask );
              TransactionManager.getInstance().removeUpdated(task);
             
            } catch (Exception e) {
                 throw e;
            }
	if (this.isDebug) {this.writeDebug("END createTask");}
    }


 
		private static String WORKGROUP_NOT_FOUND_FOR_POI = "WORKGROUP NOT FOUND FOR POI: ";
    	private static String WORKGROUP_NOT_FOUND = "WORKGROUP NAME NOT IN CSWORKGROUP TABLE: ";
    	
    private void createTaskToWorkgroup(CaseAssignmentMigr caseAssignment, Map orderMap, String poi, CriminalCase criminalCase) throws Exception {
        if (this.isDebug) {this.writeDebug("START createTaskToWorkgroup");}
//        StringBuffer sql = new StringBuffer(WORKGROUP_POI_SELECT);
//        sql.append(poi);
//        sql.append(QUOTE);
        if (caseAssignment == null){
        	return;
        }
        StringBuffer sql = new StringBuffer(WORKGROUP_PROGRAMUNIT_SELECT);
        sql.append(poi.trim());
        sql.append(QUOTE);
        sql.append(FOR_READ_ONLY);
        ResultSet rs =  null;
        Statement statement = null;
        try {
            statement = readConnection.createStatement();
            rs = statement.executeQuery(sql.toString());
            String workGroupName = null;
            WorkGroup workGroup = null;
            SupervisionOrder order = null;
            boolean poiWorkgroupFound = false;
            while (rs.next()){
            	poiWorkgroupFound = true;
                workGroupName = rs.getString(WORKGROUPNAME);
                workGroup = this.fetchWorkgroupByName(workGroupName);
                if (workGroup == null){
                	StringBuffer sb = new StringBuffer(WORKGROUP_NOT_FOUND);
                	sb.append(workGroupName);
                	this.writeError(sb.toString());
                } else {
                	order = (SupervisionOrder) orderMap.get(caseAssignment.getCriminalCaseId());
                	try {
						this.createTask(workGroupName, workGroup.getOID(), caseAssignment,
								order, criminalCase);
					} catch (Exception e) {
						throw e;
					}
                }
            }
            if (!poiWorkgroupFound){
            	StringBuffer sb = new StringBuffer(WORKGROUP_NOT_FOUND_FOR_POI);
            	sb.append(poi);
            	this.writeError(sb.toString());
            }
//            rs.close();
//            statement.close();
        } catch (SQLException e) {
           e.printStackTrace();
           throw e;
        } finally {
            try {
                rs.close();
                statement.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (this.isDebug) {this.writeDebug("END createTaskToWorkgroup");} 
    }
    /**
     * @param supervisee
     * @param caseAssignments
     * @param staffByPOIMap
     * @return
     */
    private void determineCaseloadCredit(SuperviseeMigr supervisee, List caseAssignments, Map staffByPOIMap) {
        if (this.isDebug) {this.writeDebug("START determineCaseloadCredit");}
        Map staffIdCasesMap = new HashMap();
        CaseAssignmentMigr caseAssignment = null;
        CSCDOrganizationStaffPosition staffPos = null;
        Map cloMap = new HashMap();
        Map csoMap = new HashMap();
        List caseList = null;
        
        for (int i = ZERO; i < caseAssignments.size(); i++) {
            caseAssignment = (CaseAssignmentMigr) caseAssignments.get(i);
            if (caseAssignment.getCaseState().equals(CaseloadConstants.PROGRAM_UNIT_ASSIGNED)){
                 supervisee.setCaseloadCreditStaffPositionId(null);
                 supervisee.setAssignedProgramUnitId(caseAssignment.getProgramUnitId());
                 supervisee.setProgramUnitAssignDate(caseAssignment.getProgramUnitAssignDate());
                 supervisee.setAssignedStaffPositionId(null);
                 if (this.isDebug) {this.writeDebug("END determineCaseloadCredit");}
                 return;
            } else {
                staffPos = this.getStaffPositionByOid(staffByPOIMap, caseAssignment.getAssignedStaffPositionId());
            }
            if (staffIdCasesMap.get(staffPos.getStaffPositionId()) == null){
                caseList = new ArrayList();
            } 
            caseList.add(caseAssignment);
            staffIdCasesMap.put(staffPos.getStaffPositionId(), caseList);
            if (staffPos.getJobTitleId().equals(CLO_JOB_TITLE_ID) 
                    || staffPos.getJobTitleId().equals(CLOFLOATER_JOB_TITLE_ID)){
                cloMap.put(staffPos.getStaffPositionId(), staffPos.getStaffPositionId());
            } else if (staffPos.getJobTitleId().equals(CSO_JOB_TITLE_ID)){
                csoMap.put(staffPos.getStaffPositionId(), staffPos.getStaffPositionId());
            }
        }
        String staffId = null;
        List aList = null;

        if (staffIdCasesMap.size() == ONE){ //All cases are assigned to the same position/poi
            Set aSet = staffIdCasesMap.keySet();
            aList = CollectionUtil.iteratorToList(aSet.iterator());
            staffId = (String) aList.get(ZERO);
            staffPos = (CSCDOrganizationStaffPosition) staffByOidMap.get(staffId);
            supervisee.setCaseloadCreditStaffPositionId(staffPos.getStaffPositionId());
            supervisee.setAssignedProgramUnitId(staffPos.getProgramUnitId());
            supervisee.setAssignedStaffPositionId(staffPos.getStaffPositionId());
            if (this.isDebug) {this.writeDebug("END determineCaseloadCredit");}
            return;
        }
        if (csoMap.size() > ZERO){ //Cases assigned to at least one CSO
            if (csoMap.size() == ONE){ //All cases are assigned to the same CSO
                aList = CollectionUtil.iteratorToList(csoMap.values().iterator());
                staffId = (String) aList.get(ZERO);
            } else {
                //Give credit to CSO of most recent case.
                staffId = this.findMostRecentAssignment(csoMap, staffIdCasesMap);
            }
            staffPos = (CSCDOrganizationStaffPosition) staffByOidMap.get(staffId);
            supervisee.setCaseloadCreditStaffPositionId(staffPos.getStaffPositionId());
            supervisee.setAssignedProgramUnitId(staffPos.getProgramUnitId());
            supervisee.setAssignedStaffPositionId(staffPos.getStaffPositionId());
            if (this.isDebug) {this.writeDebug("END determineCaseloadCredit");}
            return;
       }
        if (cloMap.size() > ZERO){ //There should never be cases assigned to different CLOs, but put this in just in case.
            if (cloMap.size() == ONE){ //All cases are assigned to the same CLO
                aList = CollectionUtil.iteratorToList(cloMap.values().iterator());
                staffId = (String) aList.get(ZERO);
            } else {
                //Give credit to CLO of most recent case.
                staffId = this.findMostRecentAssignment(cloMap, staffIdCasesMap);
            }
            staffPos = (CSCDOrganizationStaffPosition) staffByOidMap.get(staffId);
            supervisee.setCaseloadCreditStaffPositionId(staffPos.getStaffPositionId());
            supervisee.setAssignedProgramUnitId(staffPos.getProgramUnitId());
            supervisee.setAssignedStaffPositionId(staffPos.getStaffPositionId());
            if (this.isDebug) {this.writeDebug("END determineCaseloadCredit");}
            return;
        }
        if (this.isDebug) {this.writeDebug("END determineCaseloadCredit");}
    }
    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) {        
        if (this.isDebug) {this.writeDebug("START execute");}
        CaseAssignmentDataMigrationEvent migEvent = (CaseAssignmentDataMigrationEvent) event;
        isUpdate = migEvent.isUpdate();

        benchMarkFile = this.createFile("C:\\jzp.case.assignment.benchmark", benchMarkFile);
        exceptionFile = this.createFile("C:\\jzp.case.assignment.exceptions", exceptionFile);
        orderExceptionFile = this.createFile("C:\\jzp.case.assignment.order.exceptions", orderExceptionFile);
        
        this.buildNewCasesPOIMap();
                
        ResultSet rs = null;
        Statement statement = null;
        try {
                        
            this.init();
            
            StringBuffer sql = new StringBuffer("SELECT * FROM JIMS2.TEMPPMJZ ");
            sql.append("WHERE OID > ");
            sql.append(migEvent.getRestartKey());
            if (migEvent.getDefendantId() != null && !migEvent.getDefendantId().equals("00000000")){
                sql.append(" AND DEFENDANT_ID = ");
                sql.append(QUOTE);
                sql.append(migEvent.getDefendantId());
                sql.append(QUOTE);
                sql.append(" AND (STATUS <> 'C' OR STATUS IS NULL) ORDER BY OID");
            } else {
                sql.append(" AND (STATUS <> 'C' OR STATUS IS NULL) ORDER BY OID");
            }
            sql.append(FOR_READ_ONLY);
            
            statement = connection.createStatement();
            connection.commit();
            rs = statement.executeQuery(sql.toString());
            this.processLegacyCaseAssignments(rs);
            rs.close();
            statement.close();
            connection.commit();
            connection.close();
            readConnection.commit();
            readConnection.close();
            updateConnection.commit();
            updateConnection.close();
            benchMarkFile.close();
            exceptionFile.close();
            orderExceptionFile.close();
            debugFile.close();

        } catch (Exception e) {
            e.printStackTrace();
            try {
            	if (rs != null){
            		rs.close();
            	}
            	if (statement != null){
            		statement.close();
            	}
            	if (readConnection != null){
            		readConnection.commit();
            		readConnection.close();
            	}
            	if (connection != null){
            		connection.commit();
            		connection.close();
            	}
            	if (updateConnection != null){
            		updateConnection.commit();
            		updateConnection.close();
            	}
            	if (benchMarkFile != null){
            		benchMarkFile.close();
            	}
            	if (exceptionFile != null){
            		exceptionFile.close();
            	}
            	if (orderExceptionFile != null){
            		orderExceptionFile.close();
            	}
            	if (debugFile != null){
            		debugFile.close();
            	}
            } catch (Exception e1) {
                e1.printStackTrace();
            } 
        } 
   }
    /**
     * @param name
     * @return
     */
    public WorkGroup fetchWorkgroupByName(String name) {
        if (this.isDebug) {this.writeDebug("START fetchWorkgroupByName");}
        WorkGroup workGroup = null; 
//        GetWorkGroupsEvent requestEvent = new GetWorkGroupsEvent();
//        requestEvent.setAgencyId(CSCD);
//        requestEvent.setName(name);
//        requestEvent.setType(MI);
//        Iterator iter = WorkGroup.findAll(requestEvent);
//        List aList = CollectionUtil.iteratorToList(iter);
//        WorkGroup workGroup = null;
//        if (aList != null && aList.size() > ZERO){
//            workGroup = (WorkGroup) aList.get(ZERO);
//        }
        StringBuffer sql = new StringBuffer(WORKGROUP_SELECT);
        sql.append(name);
        sql.append(QUOTE);
        sql.append(FOR_READ_ONLY);
        ResultSet rs =  null;
        Statement statement = null;
        try {
            statement = readConnection.createStatement();
            rs = statement.executeQuery(sql.toString());
            while (rs.next()){
                workGroup = new WorkGroup();
                workGroup.setOID(rs.getString(WORKGROUP_ID));
            }
        } catch (Exception e){
        	e.printStackTrace();
         } finally {
            try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
         } 

        if (this.isDebug) {this.writeDebug("END fetchWorkgroupByName");}
        return workGroup;
    }    
    /**
     * @param officerMap (can be CLOs or CSOs)
     * @param staffIdCasesMap
     * @return
     */
    private String findMostRecentAssignment(Map officerMap, Map staffIdCasesMap) {
        //this.writeDebug("START findMostRecentAssignment");
        List officerStaffIdList = CollectionUtil.iteratorToList(officerMap.values().iterator());
        CaseAssignmentMigr mostRecentCase = null;
        CaseAssignmentMigr caseAssignment = null;
        String staffId = null;
       	for (int i = ZERO; i < officerStaffIdList.size(); i++) {
       		staffId = (String) officerStaffIdList.get(i);
       		List caseList = (List) staffIdCasesMap.get(staffId);
  			for (int j = ZERO; j < caseList.size(); j++) {
   				caseAssignment = (CaseAssignmentMigr) caseList.get(j);
   				if (mostRecentCase == null){
   					mostRecentCase = caseAssignment;
   				} else if (caseAssignment.getOfficerAssignDate().after(mostRecentCase.getOfficerAssignDate())){
      					mostRecentCase = caseAssignment;
   				}
   			}
        }
        //this.writeDebug("END findMostRecentAssignment");
        return mostRecentCase.getAssignedStaffPositionId();
    }
    
    public List getActiveSupervisionOrders(String defendantId){
	    //this.writeDebug("START getActiveSupervisionOrders");
	    /* if (agencyId == null || agencyId.equals(PDConstants.BLANK)){
	        agencyId = PDSecurityHelper.getUserAgencyId();
	    } 
	    
	    GetActiveSupervisionOrdersEvent reqEvent = new GetActiveSupervisionOrdersEvent();
	    reqEvent.setAgencyId(agencyId);
	    reqEvent.setDefendantId(padSpn(defendantId));
	    Iterator iter = SupervisionOrder.findAll(reqEvent);
	    
	    return CollectionUtil.iteratorToList(iter); */
        StringBuffer sql = new StringBuffer(SUP_ORDER_SELECT);
        sql.append(QUOTE);
        sql.append(defendantId);
        sql.append(QUOTE);
        sql.append(FOR_READ_ONLY);
        SupervisionOrder order = null;
        List aList = new ArrayList();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = readConnection.createStatement();
            rs = statement.executeQuery(sql.toString());
            while (rs.next()){
                order = new SupervisionOrder();
                order.setOID(rs.getString(SPRVISIONORDER_ID));
                order.setCriminalCaseId(rs.getString(COL_CRIMINALCASE_ID));
                order.setVersionNum(rs.getInt(VERSIONNUM));
                order.setVersionTypeId(rs.getString(VERSIONTYPECD));
                order.setCurrentCourtId(rs.getString(CURRENTCOURT_ID));
                aList.add(order);
                TransactionManager.getInstance().removeUpdated(order);
             }
//            statement.close();
//            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        //this.writeDebug("END getActiveSupervisionOrders");
        return aList;
	}

    private pd.supervision.administercaseload.CaseAssignment getCaseAssignment(String criminalCaseId) {
        //this.writeDebug("START getCaseAssignment");
        pd.supervision.administercaseload.CaseAssignment caseAssignment = null;
//        Iterator iter = pd.supervision.administercaseload.CaseAssignment.findAll(CRIMINALCASE_ID, criminalCaseId);
//        if (iter != null && iter.hasNext()){
//            caseAssignment = (pd.supervision.administercaseload.CaseAssignment) iter.next();
//        }
        
        StringBuffer sql = new StringBuffer(CASE_ASSIGN_SELECT);
        sql.append(QUOTE);
        sql.append(criminalCaseId);
        sql.append(QUOTE);
        sql.append(FOR_READ_ONLY);
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = readConnection.createStatement();
            rs = statement.executeQuery(sql.toString());
            if (rs.next()){
                caseAssignment = new pd.supervision.administercaseload.CaseAssignment();
            }
//            statement.close();
//            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        //this.writeDebug("END getCaseAssignment");
        return caseAssignment;
    }
    private String getCourtId(String courtNum, String cdi) {
        StringBuffer courtId = new StringBuffer();
        if (cdi.equals(CDI_002)){
            courtId.append(CTG_CC);
            courtId.append(this.prependZeroes(courtNum));
        } else if (cdi.equals(CDI_003)){
            courtId.append(CTG_CR);
            courtId.append(this.prependZeroes(courtNum));
        } else if (cdi.equals(CDI_010) || cdi.equals(CDI_020)){
            if (courtNum.substring(0).equals(JP_CRT)){
                courtId.append(CTG_JP);
                courtId.append(courtNum);
            } else {
                courtId.append(CTG_OC);
                courtId.append(courtNum);
            }
        } else if (cdi.equals(CDI_001)){
        	courtId.append(CTG_JP);
        	courtId.append(this.prependZeroes(courtNum));
        	StringBuffer sb = new StringBuffer(JP_COURT_NEEDED);
        	sb.append(courtNum);
        	this.writeError(sb.toString());
        }
        return courtId.toString();
    }
    private CriminalCase getCriminalCase(String criminalCaseId) {
        if (this.isDebug) {this.writeDebug("START CriminalCase.find " + criminalCaseId);}

        CriminalCase criminalCase = null;
        StringBuffer sql = new StringBuffer(CRIMINALCASE_SELECT);
        sql.append(QUOTE);
        sql.append(criminalCaseId.substring(0,3));
        sql.append(QUOTE);
        sql.append(AND_CM_KEY_CAS);
        sql.append(QUOTE);
        sql.append(criminalCaseId.substring(3));
        sql.append(QUOTE);
        sql.append(FOR_READ_ONLY);
        Statement statement = null;
        ResultSet rs = null;
        try {

        	statement = readConnection.createStatement();
            rs = statement.executeQuery(sql.toString());
            if (rs.next()){
                criminalCase = new CriminalCase();
                StringBuffer OID = new StringBuffer();
                OID.append(rs.getString(CM_KEY_CDI));
                OID.append(rs.getString(CM_KEY_CAS));
                criminalCase.setOID(OID.toString());
                String courtNum = rs.getString(CM_AA_CCR);
                criminalCase.setCourtId(this.getCourtId(courtNum, OID.substring(0,3)));
                criminalCase.setOffenseCodeId(rs.getString(CM_AA_COF));
                //criminalCase.setDefendantName(this.getDefendantName(criminalCaseId));
            }
//            statement.close();
//            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        if (this.isDebug) {this.writeDebug("END CriminalCase.find " + criminalCaseId);}

        return criminalCase;
    }
    private static final String RIGHT_PAREN = ")";
    private static final String SEMI_COLON = ";";
    
    private String getDefendantName(String criminalCaseId) {
        StringBuffer sql = new StringBuffer(DEFNAME_SELECT);
        sql.append(QUOTE);
        sql.append(criminalCaseId.substring(ZERO,THREE));
        sql.append(QUOTE);
        //sql.append(" AND CM_KEY_CAS = ");
        sql.append(AND_CM_KEY_CAS);
        sql.append(QUOTE);
        sql.append(criminalCaseId.substring(THREE));
        sql.append(QUOTE);
        sql.append(FOR_READ_ONLY);
        String defName = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = readConnection.createStatement();
            rs = statement.executeQuery(sql.toString());
            if (rs.next()){
                defName = rs.getString(CM_JJ_NAM);
                if (defName.indexOf(QUOTE) > ZERO){
                	defName = this.replaceBadChars(defName, QUOTE);
                }
                if (defName.indexOf(RIGHT_PAREN) > ZERO){
                	defName = this.replaceBadChars(defName, RIGHT_PAREN);
                 }
                if (defName.indexOf(SEMI_COLON) > ZERO){
                	defName = this.replaceBadChars(defName, SEMI_COLON);
                }

             }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
       return defName;
    }
    private String replaceBadChars(String defName, String badChar){
    	boolean finished = false;
    	int loc = 0;
    	StringBuffer sb = null;
    	while (!finished){
    		loc = defName.indexOf(badChar);
    		if (loc > 0){
    			loc = defName.indexOf(badChar);
    			sb = new StringBuffer(defName);
    			sb.replace(loc, loc + 1, PDConstants.BLANK);
    			defName = sb.toString();
    		} else {
    			finished = true;
    		}
    	}
    	return defName;
    }
    private String getProgramUnitByPOI(String poi) {
        if (this.isDebug) {this.writeDebug("START getProgramUnitByPOI");}
        if (orgPOIMap == null){
        	orgPOIMap = new HashMap();
         }
        orgPOIMap.get(poi);
        String programUnitId =  (String) orgPOIMap.get(poi);
        
        if (programUnitId == null){
        	StringBuffer sql = new StringBuffer(ORGANIZATION_SELECT);
        	sql.append(QUOTE);
        	sql.append(poi);
        	sql.append(QUOTE);
        	sql.append(FOR_READ_ONLY);
        	Statement statement = null;
        	ResultSet rs = null;        
        	try {
        		statement = readConnection.createStatement();
        		rs = statement.executeQuery(sql.toString());
        		if (rs.next()){
        			programUnitId = rs.getString(ORGANIZATION_ID);
        			orgPOIMap.put(poi, programUnitId);
        		}
        	} catch (SQLException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	} finally {
        		try {
        			statement.close();
        			rs.close();
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
        	}
        }
        if (this.isDebug) {this.writeDebug("END getProgramUnitByPOI");}
        return programUnitId;
    }
    private static final String USERPROFILE_ID = "USERPROFILE_ID";
    private CSCDOrganizationStaffPosition getStaffPosition(LegacyCaseAssignmentExtract legacyCaseAssignment, Map staffbyPOIMap) {
        //this.writeDebug("START getStaffPosition");
        CSCDOrganizationStaffPosition staffPos = null;
        CSCDOrganizationStaffPosition retiredPos = null;
        
        staffPos = (CSCDOrganizationStaffPosition) staffbyPOIMap.get(legacyCaseAssignment.getPoi().trim());
        if (staffPos == null){
            GetOrganizationStaffPositionByPOIEvent getByPOIEvent = new GetOrganizationStaffPositionByPOIEvent();
            getByPOIEvent.setAgencyId(CSCD);
            getByPOIEvent.setProbationOfficerId(legacyCaseAssignment.getPoi().trim());

            //Iterator staffIter = CSCDOrganizationStaffPosition.findAll(getByPOIEvent);
            /* if (staffIter != null && staffIter.hasNext()) {
            while (staffIter.hasNext()) {
                staffPos = (CSCDOrganizationStaffPosition) staffIter.next();
                if (PDCodeTableConstants.STAFF_STATUS_RETIRED.equals(staffPos.getStatusId())) {
                    retiredPos = staffPos;
                    staffPos = null;
                } else {
                    break;
                }
            }
            if (staffPos == null) {
     	       if (retiredPos == null) {
     	       } else {
     	           staffPos = retiredPos;
     	       }
            }
        }
        if (staffPos != null){
            staffbyPOIMap.put(legacyCaseAssignment.getPoi(), staffPos);
        } else {
            //this.postProbationOfficerError(legacyCaseAssignment);
        }*/

            StringBuffer sql = new StringBuffer(SELECT_STAFF_POS);
            sql.append(QUOTE);
            sql.append(legacyCaseAssignment.getPoi().trim());
            sql.append(QUOTE);
            sql.append(FOR_READ_ONLY);
            Statement statement = null;
            ResultSet rs = null;

            try {
                statement = readConnection.createStatement();
                rs = statement.executeQuery(sql.toString());
                Integer anInteger = null;
                while (rs.next()) {
                    //staffPos = (CSCDOrganizationStaffPosition) staffIter.next();
                    staffPos = new CSCDOrganizationStaffPosition();
                    staffPos.setProbationOfficerInd(rs.getString(PROBOFFICERIND));
                    staffPos.setProgramUnitId(rs.getString(UNIT_ID));
                    staffPos.setStaffPositionId(rs.getString(STAFFPOSITION_ID));
                    staffPos.setJobTitleId(rs.getString(JOBTITLE_ID));
                    anInteger = new Integer(rs.getInt(STATUS_ID));
                    staffPos.setStatusId(anInteger.toString());
                    staffPos.setUserProfileId(rs.getString(USERPROFILE_ID));
                    TransactionManager.getInstance().removeUpdated(staffPos);
                    if (STAFF_STATUS_RETIRED.equals(staffPos.getStatusId())) {
                        retiredPos = staffPos;
                        staffPos = null;
                    } else {
                        break;
                    }
                }
                if (staffPos == null) {
         	       if (retiredPos == null) {
         	       } else {
         	           staffPos = retiredPos;
         	       }
                }
//                statement.close();
//                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
					statement.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

           /* if (staffIter != null && staffIter.hasNext()) {
                while (staffIter.hasNext()) {
                    staffPos = (CSCDOrganizationStaffPosition) staffIter.next();
                    if (PDCodeTableConstants.STAFF_STATUS_RETIRED.equals(staffPos.getStatusId())) {
                        retiredPos = staffPos;
                        staffPos = null;
                    } else {
                        break;
                    }
                }
                if (staffPos == null) {
         	       if (retiredPos == null) {
         	       } else {
         	           staffPos = retiredPos;
         	       }
                }
            }
            if (staffPos != null){
                staffbyPOIMap.put(legacyCaseAssignment.getPoi(), staffPos);
            } else {
                //this.postProbationOfficerError(legacyCaseAssignment);
            }*/
        }
        if (staffPos != null){
            staffbyPOIMap.put(legacyCaseAssignment.getPoi().trim(), staffPos);
            if (staffByOidMap == null){
            	staffByOidMap = new HashMap();
            }
            staffByOidMap.put(staffPos.getStaffPositionId(), staffPos);
        }
        //this.writeDebug("END getStaffPosition");
        return staffPos;
    }
    private CSCDOrganizationStaffPosition getStaffPositionByOid(Map staffByPOIMap, String OID){
       // this.writeDebug("START getStaffPositionByOid");
//        if (staffByOidMap == null){
//            staffByOidMap = new HashMap();
//        } 
        CSCDOrganizationStaffPosition staffPos = null;
//        if (staffByOidMap.size() == ZERO){
//            List aList = CollectionUtil.iteratorToList(staffByPOIMap.values().iterator());
//            for (int i = ZERO; i < aList.size(); i++) {
//                staffPos = (CSCDOrganizationStaffPosition) aList.get(i);
//                staffByOidMap.put(staffPos.getStaffPositionId(), staffPos);
//            }
//        }
        staffPos = (CSCDOrganizationStaffPosition) staffByOidMap.get(OID);
        //this.writeDebug("END getStaffPositionByOid");
        return staffPos;
    }
    private final String SUPERVISEE_ID = "SUPERVISEE_ID";
    private Supervisee getSupervisee(String defendantId) {
        Supervisee supervisee = null;
        StringBuffer sql = new StringBuffer(SUPERVISEE_SELECT);
        sql.append(defendantId);
        sql.append(QUOTE);
        sql.append(FOR_READ_ONLY);
        ResultSet rs =  null;
        Statement statement = null;
        int anInt = 0;
        try {
            statement = readConnection.createStatement();
            rs = statement.executeQuery(sql.toString());
            while (rs.next()){
                supervisee = new Supervisee();
                supervisee.setDefendantId(rs.getString(DEFENDANT_ID));
                anInt = rs.getInt(SUPERVISEE_ID);
                supervisee.setOID(new Integer(anInt).toString());
            }
            statement.close();
            rs.close();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        TransactionManager tm = TransactionManager.getInstance();
        tm.removeUpdated(supervisee);
        return supervisee;
    }
    /**
     * @param soList
     * @return
     */
    private Map getSupervisionOrderMap(List soList) { 
        //this.writeDebug("START getSupervisionOrderMap");
        Map soMap = new HashMap();
        SupervisionOrder so = null;
        for (int i = ZERO; i < soList.size(); i++) {
            so = (SupervisionOrder) soList.get(i);
            soMap.put(so.getCriminalCaseId(), so);
        }
        //this.writeDebug("END getSupervisionOrderMap");
        return soMap;
    }
   
    /**
     * Retrieve UserProfile by JIMS logonId.
     * @param userProfileMap
     * @param userId
     * @return
     */
    private UserProfile getUserProfile(Map userProfileMap, String userId) {
        //this.writeDebug("START getUserProfile");
        UserProfile userProfile = (UserProfile) userProfileMap.get(userId);
        if (userProfile == null){
            userProfile = UserProfile.find(userId);
            if (userProfile != null){
                userProfileMap.put(userId, userProfile);
            }
        }
        //this.writeDebug("END getUserProfile");
        return userProfile;
    }
    private void init() throws ClassNotFoundException, SQLException
    {
        if (this.isDebug) {this.writeDebug("START init");}
        ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties("JDBC");

        System.out.println("Load driver: "+cProps.getDriver());
        Class.forName(cProps.getDriver());
        String conUrl = cProps.getTestUrl();
        connection = DriverManager.getConnection(conUrl, cProps.getUserID(), cProps.getPassword());
        connection.setReadOnly(true);
        updateConnection = DriverManager.getConnection(conUrl, cProps.getUserID(), cProps.getPassword());
        updateConnection.setAutoCommit(false);
        readConnection = DriverManager.getConnection(conUrl, cProps.getUserID(), cProps.getPassword());
        readConnection.setAutoCommit(false);
        logonId = cProps.getUserID();
        password = cProps.getPassword();
        if (this.isDebug) {this.writeDebug("END init");}
    }
    
    /**
     * @param staffIdCasesMap
     * @param staffMap
     * @return
     */
    private boolean isAnyCasesAssignedToNewCasesPOI(Map staffIdCasesMap, Map staffMap){
        //this.writeDebug("START isAnyCasesAssignedToNewCasesPOI");
        boolean isAssignedToNewCasesPOI = false;
        List aList = CollectionUtil.iteratorToList(staffIdCasesMap.values().iterator());
        List caseList = null;
        CaseAssignmentMigr caseAssignment = null;
        CSCDOrganizationStaffPosition staffPos = null;
       
        for (int i = ZERO; i < aList.size(); i++) {
            caseList = (List) aList.get(i);
            for (int j = ZERO; j < caseList.size(); j++) {
                caseAssignment = (CaseAssignmentMigr) caseList.get(i);
                staffPos = (CSCDOrganizationStaffPosition) staffMap.get(caseAssignment.getAssignedStaffPositionId());
                if (this.isNewCasesPOI(staffPos.getProbationOfficerInd())){
                    isAssignedToNewCasesPOI = true;
                    break;
                }
            }
            if (isAssignedToNewCasesPOI){
                break;
            }
        }
        //this.writeDebug("END isAnyCasesAssignedToNewCasesPOI");
        return isAssignedToNewCasesPOI;
    }
    private boolean isNewCasesPOI(String poi){
        //this.writeDebug("isNewCasesPOI");

        boolean isNewCasesPoi = false;
        
        if (newCasesPOIMap.get(poi.trim()) != null){
            isNewCasesPoi = true;
        }
        //this.writeDebug("isNewCasesPOI");
        return isNewCasesPoi;
    }
    private Object prependZeroes(String courtNum) {
        StringBuffer sb = new StringBuffer(courtNum);
        while (sb.length() < FOUR){
            sb.insert(ZERO, ZERO); 
        }
        return sb.toString();
    }
    /**
     * Validate case and create case assignment and case assignment history.
     * @param legacyCaseAssignments
     * @param staffMap
     * @param userMap
     * @throws Exception 
     */
    private CaseAssignmentMigr processCase(ArrayList legacyCaseAssignments, Map staffMap, Map userMap, Map orderMap) throws Exception {
        if (this.isDebug) {this.writeDebug("START processCase");}
        LegacyCaseAssignmentExtract thisLegacyCaseAssignment = null;
        LegacyCaseAssignmentExtract prevLegacyCaseAssignment = null;
        CaseAssignmentMigr caseAssignment = null;
        CSCDOrganizationStaffPosition staffPosition = null;
        //String orderId = null;
        SupervisionOrder order = null;
        Iterator iter = legacyCaseAssignments.iterator();
        if (iter != null && iter.hasNext()){
            CriminalCase criminalCase = this.validateCaseInfo(legacyCaseAssignments, staffMap);
            if (criminalCase != null){
                thisLegacyCaseAssignment = (LegacyCaseAssignmentExtract) iter.next();
                if (thisLegacyCaseAssignment.isCurrPoi()){
                    //Migrate active cases only
                    if (!PROBATION_TERMINATED.equals(thisLegacyCaseAssignment.getPoi())){
                        order = (SupervisionOrder) orderMap.get(thisLegacyCaseAssignment.getCriminalCaseId());
                        if (order != null){
                            if (this.isUpdate){
                                caseAssignment = this.createCaseAssignment(thisLegacyCaseAssignment, criminalCase, staffMap, orderMap);
                                this.createCaseAssignmentHistory(caseAssignment, legacyCaseAssignments, order.getCurrentCourtId(), userMap, staffMap);
                                if (this.isNewCasesPOI(thisLegacyCaseAssignment.getPoi())){
                                    this.createTaskToWorkgroup(caseAssignment, orderMap, thisLegacyCaseAssignment.getPoi(), criminalCase);
                                } else {
                                }
                            } else {
                                }
                        } else {
                            this.writeOrderError(NO_ACTIVE_SUPERVISION_ORDER, thisLegacyCaseAssignment, criminalCase);  
                        }
                    } else {
                        //Migrate active cases only
                        this.writeError(PROBATION_TERMINATED_CASE_NOT_MIGRATED, thisLegacyCaseAssignment);
                    }
                } else {
                    this.writeError(FIRST_RECORD_NOT_CURRENT_POI, thisLegacyCaseAssignment);
                }
            }
        }
        if (this.isDebug) {this.writeDebug("END processCase");}
        return caseAssignment;
    }
    
    /**
     * Read flat JZP extract and process case assignments by spn.
     * @param aList
     * @throws Exception 
     */
    private void processLegacyCaseAssignments(ResultSet rs) throws Exception{    
        if (this.isDebug) {this.writeDebug("START processLegacyCaseAssignments");}
        SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(CSCD, PDCodeTableConstants.STAFF_JOB_TITLE, PDCodeTableConstants.STAFF_JOB_TITLE_CLO);
        CLO_JOB_TITLE_ID = aCode.getOID();
        aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(CSCD, PDCodeTableConstants.STAFF_JOB_TITLE, PDCodeTableConstants.STAFF_JOB_TITLE_CSO);
        CSO_JOB_TITLE_ID = aCode.getOID();
        aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(CSCD, PDCodeTableConstants.STAFF_JOB_TITLE, PDCodeTableConstants.STAFF_JOB_TITLE_CLOFLOATER);
        CLOFLOATER_JOB_TITLE_ID = aCode.getOID();
        aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(CSCD, PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_RETIRED);
        STAFF_STATUS_RETIRED = aCode.getOID();

        int recordCounter = ZERO;
        int benchMarkCounter = ZERO;
        int commitCounter = ZERO;
       
        LegacyCaseAssignmentExtract legacyCaseAssignment = null;
        ArrayList legacyCaseAssignments = new ArrayList();
        
        String previousSpn = PDConstants.BLANK;
        Map userMap = new HashMap();
        Map staffMap = new HashMap();
        CSCDOrganizationStaffPosition staffPos = null;
        String errorMsg = null;
        Timestamp timeStamp = new Timestamp(new Date().getTime());
 
        try {  

            while (rs.next()){

                legacyCaseAssignment = this.createLegacyCaseAssignment(rs);
                recordCounter ++;
                benchMarkCounter++;
                commitCounter++;
                if (benchMarkCounter == ONE_HUNDRED){
                    this.writeBenchMark(legacyCaseAssignment.toString(), recordCounter);
                    benchMarkCounter = ZERO;
                    updateConnection.commit();
                }
                if (commitCounter == ONE_HUNDRED){
                    commitCounter = ZERO;
                    readConnection.commit();
                }
                //Collect all case assignments for a spn.
                if (legacyCaseAssignment.getSpn().equals(previousSpn)){
                    legacyCaseAssignments.add(legacyCaseAssignment);
                } else {
                    this.processSpn(legacyCaseAssignments, staffMap, userMap);
                    legacyCaseAssignments = new ArrayList();
                    legacyCaseAssignments.add(legacyCaseAssignment);
                    previousSpn = legacyCaseAssignment.getSpn();
                }
            }
        } catch (SQLException e1) {
            this.writeError(e1.getMessage());
            e1.printStackTrace();
            try {
                updateConnection.close();
                connection.close();
            } catch (SQLException e) {
                throw e;
            }
        } finally {
        }  
        //Process last spn
        try {
			this.processSpn(legacyCaseAssignments, staffMap, userMap);
		} catch (Exception e) {
			throw e;
		}
        if (this.isDebug) {this.writeDebug("END processLegacyCaseAssignments");  }
    }

	/**
     * Process case records for a spn.
     * @param legacyCaseAssignmentsForSpn
     * @param staffMap
     * @param userMap
	 * @throws Exception 
     */
    private void processSpn(ArrayList legacyCaseAssignmentsForSpn, Map staffMap, Map userMap) throws Exception {
        if (this.isDebug) {this.writeDebug("START processSpn");}
        LegacyCaseAssignmentExtract legacyCaseAssignment = null;
        Iterator iter = legacyCaseAssignmentsForSpn.iterator();
        List allCasesForSpnList = CollectionUtil.iteratorToList(iter);
        ArrayList legacyCaseAssignments = new ArrayList();
        String prevCriminalCase = null;
        CaseAssignmentMigr caseAssignment = null;
        Map caseAssignmentMap = new HashMap();
        
        if (allCasesForSpnList != null && allCasesForSpnList.size() > ZERO){
             legacyCaseAssignment = (LegacyCaseAssignmentExtract) allCasesForSpnList.get(ZERO); 
             //List soList = SupervisionOrderHelper.getActiveSupervisionOrders(legacyCaseAssignment.getSpn(), CSCD);
             List soList = this.getActiveSupervisionOrders(legacyCaseAssignment.getSpn());
             Map orderMap = this.getSupervisionOrderMap(soList);
            
             for (int i = ZERO; i < allCasesForSpnList.size(); i++) {
                legacyCaseAssignment = (LegacyCaseAssignmentExtract) allCasesForSpnList.get(i);
                if (legacyCaseAssignment.getCriminalCaseId().equals(prevCriminalCase)){
                    legacyCaseAssignments.add(legacyCaseAssignment);
                } else {
                    caseAssignment = this.processCase(legacyCaseAssignments, staffMap, userMap, orderMap);
                    legacyCaseAssignments = new ArrayList();
                    legacyCaseAssignments.add(legacyCaseAssignment);
                    prevCriminalCase = legacyCaseAssignment.getCriminalCaseId();
                }
                if (caseAssignment != null && caseAssignmentMap.get(caseAssignment.getCriminalCaseId()) == null){
                    caseAssignmentMap.put(caseAssignment.getCriminalCaseId(), caseAssignment);
                }
             }
            //Process last case
            caseAssignment = this.processCase(legacyCaseAssignments, staffMap, userMap, orderMap);
            if (caseAssignment != null && caseAssignmentMap.get(caseAssignment.getCriminalCaseId()) == null){
                caseAssignmentMap.put(caseAssignment.getCriminalCaseId(), caseAssignment);
            }
            if (caseAssignment != null && caseAssignmentMap.values().size() > ZERO && this.isUpdate){
                Collection coll = caseAssignmentMap.values();
                List caseAssignments = CollectionUtil.iteratorToList(coll.iterator());
                try {
					this.createSupervisee(caseAssignments, staffMap);
					this.updateTransactionStatus(caseAssignments);
				} catch (Exception e) {
					throw e;
				}
            }
        }
        if (this.isDebug) {this.writeDebug("END processSpn");}
    }
    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject) {

    }
    private void updateTransactionStatus(List caseAssignments) throws SQLException {
        if (this.isDebug) {this.writeDebug("START updateTransactionStatus");}
//        Connection connection = null;
        Statement statement = null;
        StringBuffer sqlQuery = null;
        try {
//            connection = DriverManager.getConnection(connURL.toString(),logonId, password);
//            connection.setAutoCommit(false);
            
            String updateSql = UPDATE_TEMPPMJZ_SQL;
            statement = updateConnection.createStatement();

            CaseAssignmentMigr caseAssignment = null;
     
            for (int i = ZERO; i < caseAssignments.size(); i++) {
                caseAssignment = (CaseAssignmentMigr) caseAssignments.get(i);
                sqlQuery = new StringBuffer(updateSql);
                sqlQuery.append(COL_CRIMINALCASE_ID);
                sqlQuery.append(EQUAL);
                sqlQuery.append(QUOTE);
                sqlQuery.append(caseAssignment.getCriminalCaseId());
                sqlQuery.append(QUOTE);
                sqlQuery.append(AND);
                sqlQuery.append(COL_DEFENDANT_ID);
                sqlQuery.append(EQUAL);
                sqlQuery.append(QUOTE);
                sqlQuery.append(caseAssignment.getDefendantId());
                sqlQuery.append(QUOTE);
                statement.executeUpdate(sqlQuery.toString());
            }
            updateConnection.commit();
            statement.close();
//            connection.commit();
//            connection.close();
        } catch (SQLException e) {
        	System.out.println(sqlQuery.toString());
        	System.out.println(e.getLocalizedMessage());
            //this.writeError(e.getMessage());
           /*  try {
                statement.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();*/
        	throw e;
        } 
        if (this.isDebug) {this.writeDebug("END updateTransactionStatus");}
    }
    private CriminalCase validateCaseInfo(ArrayList legacyCaseAssignments, Map staffMap) {
        if (this.isDebug) {this.writeDebug("START validateCaseInfo");}
        Iterator iter = legacyCaseAssignments.iterator();
        int rowCount = -1;
        int nextRow = 0;
        LegacyCaseAssignmentExtract thisLegacyCaseAssignment = null;
        LegacyCaseAssignmentExtract prevLegacyCaseAssignment = null;
        boolean badRecords = false;
        CSCDOrganizationStaffPosition staffPosition = null;

        CriminalCase criminalCase = null;

        if (iter != null && iter.hasNext())
            while (iter.hasNext()){
                rowCount ++;
                thisLegacyCaseAssignment = (LegacyCaseAssignmentExtract) iter.next();
                if (rowCount == ZERO){
                    //Determine if valid case.
                    //criminalCase = CriminalCase.find(thisLegacyCaseAssignment.getCriminalCaseId());
                    criminalCase = this.getCriminalCase(thisLegacyCaseAssignment.getCriminalCaseId());
                    if (criminalCase == null){
                        //this.postCaseNotFoundError(thisLegacyCaseAssignment);
                        this.writeError(CASE_NOT_FOUND_MSG, thisLegacyCaseAssignment);
                        badRecords = true;
                    }
                    if (PROBATION_TERMINATED.equals(thisLegacyCaseAssignment.getPoi().trim())){
                        if (legacyCaseAssignments.size() > ONE){
                            prevLegacyCaseAssignment = (LegacyCaseAssignmentExtract) legacyCaseAssignments.get(1);
                        } else {
                            //this.postNoProbationOfficerAssignmentError(thisLegacyCaseAssignment);
                            this.writeError(NO_PROBATION_OFFICER_ASSIGNMENT, thisLegacyCaseAssignment);
                            badRecords = true;
                        }
                    }
                } else if (PROBATION_TERMINATED.equals(thisLegacyCaseAssignment.getPoi().trim())){
                    nextRow = rowCount + ONE;
                    if (nextRow > legacyCaseAssignments.size()){
                        //this.postNoProbationOfficerAssignmentError(thisLegacyCaseAssignment);
                        this.writeError(NO_PROBATION_OFFICER_ASSIGNMENT, thisLegacyCaseAssignment);
                        badRecords = true;
                    }
                }

            //Determine if a staff position exists for POI.
            if (!PROBATION_TERMINATED.equals(thisLegacyCaseAssignment.getPoi().trim())){
                if (staffMap.get(thisLegacyCaseAssignment.getPoi().trim()) == null){
                    staffPosition = this.getStaffPosition(thisLegacyCaseAssignment, staffMap);
                    if (staffPosition == null){
                        //Determine if case is assigned to program unit and not staff position.
                        if (!this.isNewCasesPOI(thisLegacyCaseAssignment.getPoi().trim())){
                            if (thisLegacyCaseAssignment.isCurrPoi()){
                                //this.postProbationOfficerError(thisLegacyCaseAssignment);
                                this.writeError(PROBATION_OFFICER_NOT_FOUND, thisLegacyCaseAssignment);
                                badRecords = true;
                            } else {
                                //this.writeError(PROBATION_OFFICER_NOT_FOUND_NOT_CURRENT, thisLegacyCaseAssignment);
                            }
                        } 
                    } 
                }
            }
        }
        if (badRecords){
            criminalCase = null;
        } else if (this.getCaseAssignment(criminalCase.getOID()) != null){
            this.writeError(CASE_ASSIGNMENT_ALREADY_EXISTS, (LegacyCaseAssignmentExtract) legacyCaseAssignments.get(0));
            criminalCase = null;
        }
        if (this.isDebug) {this.writeDebug("END validateCaseInfo");}
        return criminalCase;
    }
    private void writeBenchMark(String msg, int recCounter){
        try {
            StringBuffer sb = new StringBuffer(recCounter);
            sb.append(recCounter);
            sb.append(PIPE);
            sb.append(DateUtil.dateToString(new Date(),UIConstants.DATETIME24_FMT_1));
            sb.append(PIPE);
         	sb.append(msg);
            sb.insert(ZERO, BENCHMARK);
            benchMarkFile.write(sb.toString());
            benchMarkFile.write(CARRIAGE_RETURN);
            System.out.println(sb.toString());
         
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    private void writeDebug(String msg){
        //if (isDebug && msg != null){
            try {
                StringBuffer sb = new StringBuffer(new Date().toString());
                sb.append(PIPE);
                sb.append(msg);
                debugFile.write(sb.toString());
                System.out.println(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        //}
    }
    /**
     * Post error
     * @param legacyCaseAssignment
     */
//    private void postCaseNotFoundError(LegacyCaseAssignmentExtract legacyCaseAssignment){
//        this.postError(CASE_NOT_FOUND_MSG, legacyCaseAssignment);
//    }
    private void writeError(String message){
        this.writeError(message, null);
    }
    private void writeError(String message, LegacyCaseAssignmentExtract legacyCaseAssignment){
        try {
            StringBuffer sb = new StringBuffer();
            if (legacyCaseAssignment != null){
            	sb.append(legacyCaseAssignment.toString());
            	sb.append(message);
        	} else {
        	    sb.append(message);
        	}
            exceptionFile.write(sb.toString());
            exceptionFile.write(CARRIAGE_RETURN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void writeOrderError(String message, LegacyCaseAssignmentExtract legacyCaseAssignment, CriminalCase criminalCase) {
        try {
            StringBuffer sb = new StringBuffer();
            if (legacyCaseAssignment != null){
            	sb.append(legacyCaseAssignment.toString());
            	sb.append(message);
            	if (criminalCase != null){
            		sb.append(PIPE);
            		sb.append(criminalCase.getCourtId());
            		sb.append(PIPE);
            		sb.append(criminalCase.getOffenseCodeId());
            	} else {
            	}
        	} else {
        	    sb.append(message);
        	}
            orderExceptionFile.write(sb.toString());
            orderExceptionFile.write(CARRIAGE_RETURN);
        } catch (IOException e) {
            e.printStackTrace();
        }		
	}

}
