/*
 * Created on May 15, 2007
 *
 */
package messaging.cscdstaffposition.reply;

import java.util.Date;

import mojo.km.utilities.Name;

/**
 * @author dgibler
 *
 */
public class StaffPositionHistoryResponseEvent extends CSCDSupervisionStaffResponseEvent {
    private String programUnitId;
    private Date updateDate;
    private String updateUserId;
    private Name updateUserName;
    /**
     * @return Returns the programUnitId.
     */
    public String getProgramUnitId() {
        return programUnitId;
    }
    /**
     * @return Returns the updateDate.
     */
    public Date getUpdateDate() {
        return updateDate;
    }
    /**
     * @return Returns the updateUserId.
     */
    public String getUpdateUserId() {
        return updateUserId;
    }
    /**
     * @param programUnitId The programUnitId to set.
     */
    public void setProgramUnitId(String programUnitName) {
        this.programUnitId = programUnitName;
    }
    /**
     * @param updateDate The updateDate to set.
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    /**
     * @param updateUserId The updateUserId to set.
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
    /**
     * @return Returns the updateUserName.
     */
    public Name getUpdateUserName() {
        return updateUserName;
    }
    /**
     * @param updateUserName The updateUserName to set.
     */
    public void setUpdateUserName(Name updateUserName) {
        this.updateUserName = updateUserName;
    }
}
