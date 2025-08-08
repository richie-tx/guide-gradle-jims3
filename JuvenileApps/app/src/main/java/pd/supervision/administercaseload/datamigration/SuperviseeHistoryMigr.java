/*
 * Created on Apr 28, 2008
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
public class SuperviseeHistoryMigr {
	private String type;

	private String superviseeId;

	private Date losEffectiveDate;

	private String supervisionLevelId;

	private String caseloadCreditStaffPositionId;

	private String assignedProgramUnitId;

	private String assignedStaffPositionId;
	
	private boolean currentlySupervised;
	
	private Date programUnitAssignDate;

    public Date getProgramUnitAssignDate() {
		return programUnitAssignDate;
	}
	public void setProgramUnitAssignDate(Date programUnitAssignDate) {
		this.programUnitAssignDate = programUnitAssignDate;
	}
	/**
     * @return Returns the assignedProgramUnitId.
     */
    public String getAssignedProgramUnitId() {
        return assignedProgramUnitId;
    }
    /**
     * @param assignedProgramUnitId The assignedProgramUnitId to set.
     */
    public void setAssignedProgramUnitId(String assignedProgramUnitId) {
        this.assignedProgramUnitId = assignedProgramUnitId;
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
     * @return Returns the caseloadCreditStaffPositionId.
     */
    public String getCaseloadCreditStaffPositionId() {
        return caseloadCreditStaffPositionId;
    }
    /**
     * @param caseloadCreditStaffPositionId The caseloadCreditStaffPositionId to set.
     */
    public void setCaseloadCreditStaffPositionId(
            String caseloadCreditStaffPositionId) {
        this.caseloadCreditStaffPositionId = caseloadCreditStaffPositionId;
    }
    /**
     * @return Returns the currentlySupervised.
     */
    public boolean isCurrentlySupervised() {
        return currentlySupervised;
    }
    /**
     * @param currentlySupervised The currentlySupervised to set.
     */
    public void setCurrentlySupervised(boolean currentlySupervised) {
        this.currentlySupervised = currentlySupervised;
    }
    /**
     * @return Returns the losEffectiveDate.
     */
    public Date getLosEffectiveDate() {
        return losEffectiveDate;
    }
    /**
     * @param losEffectiveDate The losEffectiveDate to set.
     */
    public void setLosEffectiveDate(Date losEffectiveDate) {
        this.losEffectiveDate = losEffectiveDate;
    }
    /**
     * @return Returns the superviseeId.
     */
    public String getSuperviseeId() {
        return superviseeId;
    }
    /**
     * @param superviseeId The superviseeId to set.
     */
    public void setSuperviseeId(String superviseeId) {
        this.superviseeId = superviseeId;
    }
    /**
     * @return Returns the supervisionLevelId.
     */
    public String getSupervisionLevelId() {
        return supervisionLevelId;
    }
    /**
     * @param supervisionLevelId The supervisionLevelId to set.
     */
    public void setSupervisionLevelId(String supervisionLevelId) {
        this.supervisionLevelId = supervisionLevelId;
    }
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
    private static String QUOTE = "'";
    private static String COMMA = ",";
    private static String QUOTECOMMA = "',";
    private static String QUOTECOMMAQUOTE = "','";
    private static String MIGRATED = "MIGRATED";
    private static String COMMAQUOTE = ",'";

    public SuperviseeHistoryMigr bind(Connection connection, String logonId, String password) throws SQLException{
        Timestamp timeStamp = new Timestamp(new Date().getTime());
        StringBuffer insertSql = null;
        StringBuffer fieldSelect = new StringBuffer("INSERT INTO JIMS2.CSSUPERVISEEHIST (SUPERVISEE_ID");
        StringBuffer valuesSelect = new StringBuffer("VALUES (");
        valuesSelect.append(this.getSuperviseeId());
        
        if (this.getAssignedProgramUnitId() != null){
           fieldSelect.append(",ASSIGNPROGUNIT_ID");
            valuesSelect.append(COMMA);
            valuesSelect.append(this.getAssignedProgramUnitId());
        }
        if (this.getProgramUnitAssignDate()!= null){
            fieldSelect.append(",PROGUNITASSGNDATE");
            valuesSelect.append(COMMAQUOTE);
            timeStamp = new Timestamp(this.getProgramUnitAssignDate().getTime());
            valuesSelect.append(timeStamp);
            valuesSelect.append(QUOTE);
         }
        if (this.getAssignedStaffPositionId() != null){
            fieldSelect.append(",ASSIGNSTAFFPOS_ID");
            valuesSelect.append(COMMA);
            valuesSelect.append(this.getAssignedStaffPositionId());
        }
        if (this.getCaseloadCreditStaffPositionId() != null){
            fieldSelect.append(",CREDITSTAFFPOS_ID");
            valuesSelect.append(COMMA);
            valuesSelect.append(this.getCaseloadCreditStaffPositionId());
        }
        if (this.getLosEffectiveDate() != null){
            fieldSelect.append(",LOSEFFDATE");
            valuesSelect.append(COMMAQUOTE);
            timeStamp = new Timestamp(this.getLosEffectiveDate().getTime());
            valuesSelect.append(timeStamp);
            valuesSelect.append(QUOTE);
        }
        if (this.getSupervisionLevelId() != null){
            fieldSelect.append(",SPVSEELOSCODE_ID");
            valuesSelect.append(COMMA);
            valuesSelect.append(this.getSupervisionLevelId());
        }
        fieldSelect.append(",TYPE");
        valuesSelect.append(COMMAQUOTE);
        valuesSelect.append(this.getType());
        valuesSelect.append(QUOTE);
        
        fieldSelect.append(",ISNOWSUPERVISED");
        valuesSelect.append(COMMA);
        if (this.isCurrentlySupervised()){
            valuesSelect.append(1);
        } else {
            valuesSelect.append(0);
        }

        fieldSelect.append(",CREATEDATE,CREATEJIMS2USER,CREATEUSER) ");
        timeStamp = new Timestamp(new Date().getTime());
        valuesSelect.append(COMMAQUOTE);
        valuesSelect.append(timeStamp);
        valuesSelect.append(QUOTECOMMAQUOTE);
        valuesSelect.append(MIGRATED);
        valuesSelect.append(QUOTECOMMAQUOTE);
        valuesSelect.append(logonId);
        valuesSelect.append("')");
        
        insertSql = new StringBuffer();
        
        insertSql.append(fieldSelect);
        insertSql.append(valuesSelect);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertSql.toString());
            statement.close();
        } catch (SQLException e) {
        	System.out.println(insertSql.toString());
        	System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
        return this;  
    }
}
