/*
 * Created on Apr 25, 2008
 *
 */
package pd.supervision.administercaseload.datamigration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;


/**
 * @author dgibler
 *
 */
public class CaseAssignmentMigr {
    private String OID;
    private String acknowledgePositionId;
    private String acknowledgeRoleId;
    private String acknowledgeStatusId;
    private String acknowledgeUserId;
    private Date acknowlegeDate;
    public Date getAcknowlegeDate() {
		return acknowlegeDate;
	}
	public void setAcknowlegeDate(Date acknowlegeDate) {
		this.acknowlegeDate = acknowlegeDate;
	}
	private String criminalCaseId;
    private String defendantId;
    private Date officerAssignDate;
    private Date programUnitAssignDate;
    private String programUnitId;
    private String supervisionOrderId;
    private String supervisionStyleId;
    private Date terminationDate;
    private String assignedStaffPositionId;
    private String caseState;
    private String allocatedStaffPositionId;

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
     * @return Returns the acknowledgeRoleId.
     */
    public String getAcknowledgeRoleId() {
        return acknowledgeRoleId;
    }
    /**
     * @param acknowledgeRoleId The acknowledgeRoleId to set.
     */
    public void setAcknowledgeRoleId(String acknowledgeRoleId) {
        this.acknowledgeRoleId = acknowledgeRoleId;
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
     * @return Returns the caseState.
     */
    public String getCaseState() {
        return caseState;
    }
    /**
     * @param caseState The caseState to set.
     */
    public void setCaseState(String caseState) {
        this.caseState = caseState;
    }
    /**
     * @return Returns the criminalCaseId.
     */
    public String getCriminalCaseId() {
        return criminalCaseId;
    }
    /**
     * @param criminalCaseId The criminalCaseId to set.
     */
    public void setCriminalCaseId(String criminalCaseId) {
        this.criminalCaseId = criminalCaseId;
    }
    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId() {
        return defendantId;
    }
    /**
     * @param defendantId The defendantId to set.
     */
    public void setDefendantId(String defendantId) {
        this.defendantId = defendantId;
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
     * @return Returns the supervisionOrderId.
     */
    public String getSupervisionOrderId() {
        return supervisionOrderId;
    }
    /**
     * @param supervisionOrderId The supervisionOrderId to set.
     */
    public void setSupervisionOrderId(String supervisionOrderId) {
        this.supervisionOrderId = supervisionOrderId;
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
    /**
     * @return Returns the caseAssignmentId.
     */
    public String getOID() {
        return OID;
    }
    /**
     * @param caseAssignmentId The caseAssignmentId to set.
     */
    public void setOID(String caseAssignmentId) {
        this.OID = caseAssignmentId;
    }
    private static final String QUOTE = "'";
    private static final String COMMA = ",";
    private static final String QUOTECOMMA = "',";
    private static final String QUOTECOMMAQUOTE = "','";
    private static final String MIGRATED = "MIGRATED";
    private static final String COMMAQUOTE = ",'";
    private static final String SELECTIDENTITY = "SELECT IDENTITY_VAL_LOCAL() FROM SYSIBM.SYSDUMMY1";
    private static final String INSERT_CASEASSIGN = "INSERT INTO JIMS2.CSCASEASSIGN (ACKNOWLEDGSTATUSCD";
    private static final String SEL1 = ",ASSIGNSTAFFPOS_ID";
    private static final String SEL2 = ",CASESTATE,CREATEDATE,CREATEJIMS2USER,CREATEUSER,CRIMINALCASE_ID,DEFENDANT_ID";
    private static final String SEL3 = ",OFFICERASSIGNDATE";
    private static final String SEL4 = ",ORGANIZATION_ID";
    private static final String SEL5 = ",PROGUNITASSGNDATE";
    private static final String SEL6 = ",SPRVISIONORDER_ID,SUPERVISIONSTYLECD";
    private static final String SEL7 = ",TERMINATIONDATE";
    private static final String SEL8 = ",PAPERFILERECPOS_ID";
    private static final String SEL9 = ",PAPERFILERECUSR_ID";
    private static final String SEL10 = ",PAPERFILERECDATE";
    private static final String SEL11 = ") VALUES(";
    
    public CaseAssignmentMigr bind(Connection connection, String logonId, String password) throws SQLException{

       // Connection connection = null;
        String errorMsg = null;
        Timestamp timeStamp = new Timestamp(new Date().getTime());
        StringBuffer insertSql = null;
        try {
            StringBuffer fieldSelect = new StringBuffer(INSERT_CASEASSIGN);
            if (this.getAssignedStaffPositionId() != null){
                fieldSelect.append(SEL1);
            }
            fieldSelect.append(SEL2);
            if (this.getOfficerAssignDate() != null){
                fieldSelect.append(SEL3);
            }
            fieldSelect.append(SEL4);
            if (this.getProgramUnitAssignDate() != null){
                fieldSelect.append(SEL5);
            }
            fieldSelect.append(SEL6);
            if (this.getTerminationDate() != null){
                fieldSelect.append(SEL7);
            }
            if (this.getAcknowledgePositionId() != null){
                fieldSelect.append(SEL8);
            }
            if (this.getAcknowledgeUserId() != null){
                fieldSelect.append(SEL9);
            }
            if (this.getAcknowlegeDate() != null){
            	fieldSelect.append(SEL10);
            }
            fieldSelect.append(SEL11);
            
            insertSql = new StringBuffer(fieldSelect.toString());
            insertSql.append(QUOTE);
            insertSql.append(this.getAcknowledgeStatusId());
            insertSql.append(QUOTE);
            if (this.getAssignedStaffPositionId() != null){
                insertSql.append(COMMA);
                insertSql.append(this.getAssignedStaffPositionId());
            }
            insertSql.append(COMMAQUOTE);
            insertSql.append(this.getCaseState());
            insertSql.append(QUOTECOMMAQUOTE);
            insertSql.append(timeStamp);
            insertSql.append(QUOTECOMMAQUOTE);
            insertSql.append(MIGRATED);
            insertSql.append(QUOTECOMMAQUOTE);
            insertSql.append(logonId);
            insertSql.append(QUOTECOMMAQUOTE);
            insertSql.append(this.getCriminalCaseId());
            insertSql.append(QUOTECOMMAQUOTE);
            insertSql.append(this.getDefendantId());
            insertSql.append(QUOTE);
            if (this.getOfficerAssignDate() != null){
                insertSql.append(COMMAQUOTE);
                timeStamp = new Timestamp(this.getOfficerAssignDate().getTime());
                insertSql.append(timeStamp);
                insertSql.append(QUOTE);
            }
            insertSql.append(COMMA);
            insertSql.append(this.getProgramUnitId());
            if (this.getProgramUnitAssignDate() != null){
                insertSql.append(COMMAQUOTE);
                timeStamp = new Timestamp(this.getProgramUnitAssignDate().getTime());
                insertSql.append(timeStamp);
                insertSql.append(QUOTE);
            }
            insertSql.append(COMMA);
            insertSql.append(this.getSupervisionOrderId());
            insertSql.append(COMMAQUOTE);
            insertSql.append(this.getSupervisionStyleId());
            insertSql.append(QUOTE);
            if (this.getTerminationDate() != null){
                insertSql.append(COMMAQUOTE);
                timeStamp = new Timestamp(this.getTerminationDate().getTime());
                insertSql.append(timeStamp);
                insertSql.append(QUOTE);
            }
            if (this.getAcknowledgePositionId() != null){
            	insertSql.append(COMMA);
            	insertSql.append(this.getAcknowledgePositionId());
            }
            if (this.acknowledgeUserId != null){
            	insertSql.append(COMMAQUOTE);
            	insertSql.append(this.getAcknowledgeUserId());
            	insertSql.append(QUOTE);
            }
            if (this.acknowlegeDate != null){
                insertSql.append(COMMAQUOTE);
                timeStamp = new Timestamp(this.getAcknowlegeDate().getTime());
                insertSql.append(timeStamp);
                insertSql.append(QUOTE);
            }
            insertSql.append(")");
            
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertSql.toString());
            
            //PreparedStatement stump = connection.prepareStatement("SELECT IDENTITY_VAL_LOCAL() FROM SYSIBM.SYSDUMMY1");
            PreparedStatement stump = connection.prepareStatement(SELECTIDENTITY);

            ResultSet rSet = null;
            try
            {
                rSet = stump.executeQuery();

                if (rSet.next())
                {
                    String OID = rSet.getString(1);
                    this.setOID(OID);
                }
            }
            finally
            {
                if (rSet != null)
                {
                    rSet.close();
                    rSet = null;
                }
                stump.close();
                statement.close();
            }
            
        } catch (SQLException e) {
            errorMsg = e.getLocalizedMessage();
            System.out.println(insertSql.toString());
            e.printStackTrace();
            throw e;
        } 
        
        return this;
    }
    private static final String UPDATESTATEMENT = "UPDATE JIMS2.CSCASEASSIGN SET PROGUNITASSGNDATE = ";
    private static final String WHERECASEASSIGN_ID_EQUALS = " WHERE CASEASSIGN_ID = ";
    public CaseAssignmentMigr update(Connection connection, String logonId, String password){

         String errorMsg = null;
         Statement statement = null;
         Timestamp timeStamp = new Timestamp(new Date().getTime());
         try {
             StringBuffer updateSql = new StringBuffer(UPDATESTATEMENT);
             updateSql.append(QUOTE);
             timeStamp = new Timestamp(this.getProgramUnitAssignDate().getTime());
             updateSql.append(timeStamp);
             updateSql.append(QUOTE);
             updateSql.append(WHERECASEASSIGN_ID_EQUALS);
             updateSql.append(this.getOID());
             statement = connection.createStatement();
             statement.executeUpdate(updateSql.toString());
         } catch (SQLException e) {
             errorMsg = e.getLocalizedMessage();
             e.printStackTrace();
         } finally {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}        	 
        	 
         }
         
         return this;
     }   
}
