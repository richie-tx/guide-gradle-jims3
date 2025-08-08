/*
 * Created on Apr 28, 2008
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
public class SuperviseeMigr {
	private String defendantId;
	private Date losEffectiveDate;
	private String supervisionLevelId;
	private String caseloadCreditStaffPositionId;
	private String assignedProgramUnitId;
	private String assignedStaffPositionId;
	private boolean currentlySupervised;
	private String OID;
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
    private static String QUOTE = "'";
    private static String COMMA = ",";
    private static String QUOTECOMMA = "',";
    private static String QUOTECOMMAQUOTE = "','";
    private static String MIGRATED = "MIGRATED";
    private static String COMMAQUOTE = ",'";

    public SuperviseeMigr bind(Connection connection, String logonId, String password) throws SQLException{
        Timestamp timeStamp = null;
        StringBuffer updateSQL = new StringBuffer("UPDATE JIMS2.CSSUPERVISEE SET ");

       boolean prevFields=false;
        if (this.getAssignedProgramUnitId() != null){
           updateSQL.append("ASSIGNPROGUNIT_ID=");
           updateSQL.append(this.getAssignedProgramUnitId());
           prevFields=true;
        }
        if (this.getAssignedStaffPositionId() != null){
            updateSQL.append(",ASSIGNSTAFFPOS_ID=");
            updateSQL.append(this.getAssignedStaffPositionId());
            prevFields=true;
        }
        if (this.getCaseloadCreditStaffPositionId() != null){
            updateSQL.append(",CREDITSTAFFPOS_ID=");
            updateSQL.append(this.getCaseloadCreditStaffPositionId());
            prevFields=true;
        }
        if (this.getProgramUnitAssignDate()!= null){
            updateSQL.append(",PROGUNITASSGNDATE=");
            updateSQL.append(QUOTE);
            timeStamp = new Timestamp(this.getProgramUnitAssignDate().getTime());
            updateSQL.append(timeStamp);
            updateSQL.append(QUOTE);
            prevFields=true;
         }
        if (prevFields){
        	 updateSQL.append(",ISNOWSUPERVISED=");
        } else {
         updateSQL.append("ISNOWSUPERVISED=");
        }
        if (this.isCurrentlySupervised()){
            updateSQL.append(1);
        } else {
            updateSQL.append(0);
        }
        timeStamp = new Timestamp(new Date().getTime());
        updateSQL.append(",UPDATEDATE='");
        updateSQL.append(timeStamp);
        updateSQL.append(QUOTE);
        updateSQL.append(",UPDATEJIMS2USER='");
        updateSQL.append(MIGRATED);
        updateSQL.append(QUOTE);
        updateSQL.append(",UPDATEUSER='");
        updateSQL.append(logonId);
        updateSQL.append(QUOTE);

        updateSQL.append(" WHERE SUPERVISEE_ID=");
        updateSQL.append(this.getOID());
        
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateSQL.toString());
            
//            PreparedStatement stump = connection.prepareStatement("SELECT IDENTITY_VAL_LOCAL() FROM SYSIBM.SYSDUMMY1");
//            ResultSet rSet = null;
//            try {
//                rSet = stump.executeQuery();
//
//                if (rSet.next())
//                {
//                    String OID = rSet.getString(1);
//                    this.setOID(OID);
//                }
//            }
//            finally
//            {
//                if (rSet != null)
//                {
//                    rSet.close();
//                    rSet = null;
//                }
//                stump.close();
//                statement.close();
//            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        	System.out.println(e.getLocalizedMessage());
        	System.out.println(updateSQL.toString());
            e.printStackTrace();
            throw e;
        }
  
        return this;
 
    }
    /* public SuperviseeMigr bind(Connection connection, String logonId, String password){
        Timestamp timeStamp = null;
        StringBuffer fieldSelect = new StringBuffer("INSERT INTO JIMS2.CSSUPERVISEE (DEFENDANT_ID");
        StringBuffer valuesSelect = new StringBuffer("VALUES ('");
        valuesSelect.append(this.getDefendantId());
        valuesSelect.append(QUOTE);
        
        if (this.getAssignedProgramUnitId() != null){
            fieldSelect.append(",ASSIGNPROGUNIT_ID");
            valuesSelect.append(COMMA);
            valuesSelect.append(this.getAssignedProgramUnitId());
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
        if (this.getProgramUnitAssignDate()!= null){
            fieldSelect.append(",PROGUNITASSGNDATE");
            valuesSelect.append(COMMAQUOTE);
            timeStamp = new Timestamp(this.getProgramUnitAssignDate().getTime());
            valuesSelect.append(timeStamp);
            valuesSelect.append(QUOTE);
         }
        if (this.getSupervisionLevelId() != null){
            fieldSelect.append(",SPVSEELOSCODE_ID");
            valuesSelect.append(COMMA);
            valuesSelect.append(this.getSupervisionLevelId());
        }
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
        
        StringBuffer insertSql = new StringBuffer();
        insertSql.append(fieldSelect);
        insertSql.append(valuesSelect);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertSql.toString());
            
            PreparedStatement stump = connection.prepareStatement("SELECT IDENTITY_VAL_LOCAL() FROM SYSIBM.SYSDUMMY1");
            ResultSet rSet = null;
            try {
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  
        return this;
 
    } */

    /**
     * @return Returns the oID.
     */
    public String getOID() {
        return OID;
    }
    /**
     * @param oid The oID to set.
     */
    public void setOID(String oid) {
        OID = oid;
    }
}