/*
 * Created on Apr 25, 2008
 *
 */
package pd.supervision.administercaseload.datamigration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;


/**
 * @author dgibler
 *
 */
public class CaseAssignmentHistoryMigr {
	private String historyType;
	private Date terminationDate;
	private String programUnitId;
	private Date programUnitAssignDate;
	private String assignedStaffPositionId;
	private Date officerAssignDate;
	private String allocatedStaffPositionId;
	private String acknowledgePositionId;
	private String acknowledgeUserId;
	private Date acknowledgeDate;
	public Date getAcknowledgeDate() {
		return acknowledgeDate;
	}
	public void setAcknowledgeDate(Date acknowledgeDate) {
		this.acknowledgeDate = acknowledgeDate;
	}
	private String supervisionStyleId;
	private String acknowledgeStatusId;
	private String courtNum;
	private String caseAssignmentId;

    /**
     * @return Returns the acknowledgePositionId.
     */
    public String getAcknowledgePositionId() {
        return acknowledgePositionId;
    }
    /**
     * @param acknowledgePositionId The acknowledgePositionId to set.
     */
    public void setAcknowledgePositionId(String acknowledgePositionId) {
        this.acknowledgePositionId = acknowledgePositionId;
    }
    /**
     * @return Returns the acknowledgeStatusId.
     */
    public String getAcknowledgeStatusId() {
        return acknowledgeStatusId;
    }
    /**
     * @param acknowledgeStatusId The acknowledgeStatusId to set.
     */
    public void setAcknowledgeStatusId(String acknowledgeStatusId) {
        this.acknowledgeStatusId = acknowledgeStatusId;
    }
    /**
     * @return Returns the acknowledgeUserId.
     */
    public String getAcknowledgeUserId() {
        return acknowledgeUserId;
    }
    /**
     * @param acknowledgeUserId The acknowledgeUserId to set.
     */
    public void setAcknowledgeUserId(String acknowledgeUserId) {
        this.acknowledgeUserId = acknowledgeUserId;
    }
    /**
     * @return Returns the allocatedStaffPositionId.
     */
    public String getAllocatedStaffPositionId() {
        return allocatedStaffPositionId;
    }
    /**
     * @param allocatedStaffPositionId The allocatedStaffPositionId to set.
     */
    public void setAllocatedStaffPositionId(String allocatedStaffPositionId) {
        this.allocatedStaffPositionId = allocatedStaffPositionId;
    }
    /**
     * @return Returns the assignedStaffPositionId.
     */
    public String getAssignedStaffPositionId() {
        return assignedStaffPositionId;
    }
    /**
     * @param assignedStaffPositionId The assignedStaffPositionId to set.
     */
    public void setAssignedStaffPositionId(String assignedStaffPositionId) {
        this.assignedStaffPositionId = assignedStaffPositionId;
    }
    /**
     * @return Returns the courtNum.
     */
    public String getCourtNum() {
        return courtNum;
    }
    /**
     * @param courtNum The courtNum to set.
     */
    public void setCourtNum(String courtNum) {
        this.courtNum = courtNum;
    }
    /**
     * @return Returns the historyType.
     */
    public String getHistoryType() {
        return historyType;
    }
    /**
     * @param historyType The historyType to set.
     */
    public void setHistoryType(String historyType) {
        this.historyType = historyType;
    }
    /**
     * @return Returns the officerAssignDate.
     */
    public Date getOfficerAssignDate() {
        return officerAssignDate;
    }
    /**
     * @param officerAssignDate The officerAssignDate to set.
     */
    public void setOfficerAssignDate(Date officerAssignDate) {
        this.officerAssignDate = officerAssignDate;
    }
    /**
     * @return Returns the programUnitAssignDate.
     */
    public Date getProgramUnitAssignDate() {
        return programUnitAssignDate;
    }
    /**
     * @param programUnitAssignDate The programUnitAssignDate to set.
     */
    public void setProgramUnitAssignDate(Date programUnitAssignDate) {
        this.programUnitAssignDate = programUnitAssignDate;
    }
    /**
     * @return Returns the programUnitId.
     */
    public String getProgramUnitId() {
        return programUnitId;
    }
    /**
     * @param programUnitId The programUnitId to set.
     */
    public void setProgramUnitId(String programUnitId) {
        this.programUnitId = programUnitId;
    }
    /**
     * @return Returns the supervisionStyleId.
     */
    public String getSupervisionStyleId() {
        return supervisionStyleId;
    }
    /**
     * @param supervisionStyleId The supervisionStyleId to set.
     */
    public void setSupervisionStyleId(String supervisionStyleId) {
        this.supervisionStyleId = supervisionStyleId;
    }
    /**
     * @return Returns the terminationDate.
     */
    public Date getTerminationDate() {
        return terminationDate;
    }
    /**
     * @param terminationDate The terminationDate to set.
     */
    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }
    private static final String QUOTE = "'";
    private static final String COMMA = ",";
    private static final String QUOTECOMMA = "',";
    private static final String QUOTECOMMAQUOTE = "','";
    private static final String MIGRATED = "MIGRATED";
    private static final String COMMAQUOTE = ",'";
    private static final String SEL1 = "INSERT INTO JIMS2.CSCASEASSIGNHIST (ACKNOWLEDGSTATUSCD,";
    private static final String SEL2 = "ASSIGNSTAFFPOS_ID,";
    private static final String SEL3 = "CASEASSIGN_ID,COURT_ID,CREATEDATE,CREATEJIMS2USER,CREATEUSER,";
    private static final String SEL4 = "OFFICERASSIGNDATE,";
    private static final String SEL5 = "ORGANIZATION_ID,";
    private static final String SEL6 = "PROGUNITASSGNDATE,";
    private static final String SEL7 = "SUPERVISIONSTYLECD,";
    private static final String SEL8 = "TERMINATIONDATE,";
    private static final String SEL9 = "PAPERFILERECPOS_ID,";
    private static final String SEL10 = "PAPERFILERECUSR_ID,";
    private static final String SEL11 = "PAPERFILERECDATE,";
    private static final String SEL12 = "TYPE) VALUES(";
    
    public void bind(Connection connection, String logonId, String password) throws SQLException{
        //Connection connection = null;
        String errorMsg = null;
        Timestamp timeStamp = new Timestamp(new Date().getTime());
        StringBuffer insertSql = null;
        try {
            //connection = DriverManager.getConnection(connURL,logonId, password);
            StringBuffer fieldSelect = new StringBuffer(SEL1);
            if (this.getAssignedStaffPositionId() != null){
                fieldSelect.append(SEL2);
            }
            fieldSelect.append(SEL3);
            if (this.getOfficerAssignDate() != null){
                fieldSelect.append(SEL4);
            } 
            fieldSelect.append(SEL5);
            if (this.getProgramUnitAssignDate() != null){
                fieldSelect.append(SEL6);
            }
            fieldSelect.append(SEL7);
            if (this.getTerminationDate() != null){
                fieldSelect.append(SEL8);
            }
            if (this.getAcknowledgePositionId() != null){
            	fieldSelect.append(SEL9);
            }
            if (this.getAcknowledgeUserId() != null){
            	fieldSelect.append(SEL10);
            }
            if (this.getAcknowledgeDate() != null){
            	fieldSelect.append(SEL11);
            }
            fieldSelect.append(SEL12);

            insertSql = new StringBuffer(fieldSelect.toString());
            insertSql.append(QUOTE);
            insertSql.append(this.getAcknowledgeStatusId());
            insertSql.append(QUOTECOMMA);
            if (this.getAssignedStaffPositionId() != null){
                insertSql.append(this.getAssignedStaffPositionId());
                insertSql.append(COMMA);
            }
            insertSql.append(this.getCaseAssignmentId());
            insertSql.append(COMMAQUOTE);
            insertSql.append(this.getCourtNum());
            insertSql.append(QUOTECOMMAQUOTE);
            timeStamp = new Timestamp(new Date().getTime());
            insertSql.append(timeStamp);
            insertSql.append(QUOTECOMMAQUOTE);
            insertSql.append(MIGRATED);
            insertSql.append(QUOTECOMMAQUOTE);
            insertSql.append(logonId);
            insertSql.append(QUOTECOMMA);
            if (this.getOfficerAssignDate() != null){
                insertSql.append(QUOTE);
                timeStamp = new Timestamp(this.getOfficerAssignDate().getTime());
                insertSql.append(timeStamp);
                insertSql.append(QUOTE);
                insertSql.append(COMMA);
            }
            insertSql.append(this.getProgramUnitId());
            insertSql.append(COMMA);
            if (this.getProgramUnitAssignDate() != null){
                insertSql.append(QUOTE);
                timeStamp = new Timestamp(this.getProgramUnitAssignDate().getTime());
                insertSql.append(timeStamp);
                insertSql.append(QUOTE);
                insertSql.append(COMMA);
             }
            insertSql.append(QUOTE);
            insertSql.append(this.getSupervisionStyleId());
            insertSql.append(QUOTECOMMA);
            if (this.getTerminationDate() != null){
                insertSql.append(QUOTE);
                timeStamp = new Timestamp(this.getTerminationDate().getTime());
                insertSql.append(timeStamp);
                insertSql.append(QUOTE);
                insertSql.append(COMMA);
            }
            if (this.getAcknowledgePositionId() != null){
            	insertSql.append(this.getAcknowledgePositionId());
            	insertSql.append(COMMA);
            }
            if (this.getAcknowledgeUserId() != null){
            	insertSql.append(QUOTE);
            	insertSql.append(this.getAcknowledgeUserId());
            	insertSql.append(QUOTECOMMA);
            }
            if (this.getAcknowledgeDate() != null){
                insertSql.append(QUOTE);
                timeStamp = new Timestamp(this.getAcknowledgeDate().getTime());
                insertSql.append(timeStamp);
                insertSql.append(QUOTE);
                insertSql.append(COMMA);
            }

            insertSql.append(QUOTE);
            insertSql.append(this.getHistoryType());
            insertSql.append("')");
          
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertSql.toString());
            statement.close();
            //connection.close();
            
        } catch (SQLException e) {
            errorMsg = e.getLocalizedMessage();
            System.out.println(errorMsg);
            System.out.println(insertSql.toString());
            e.printStackTrace();
            throw e;
        }
    }
    /**
     * @return Returns the caseAssignmentId.
     */
    public String getCaseAssignmentId() {
        return caseAssignmentId;
    }
    /**
     * @param caseAssignmentId The caseAssignmentId to set.
     */
    public void setCaseAssignmentId(String caseAssignmentId) {
        this.caseAssignmentId = caseAssignmentId;
    }
}
