/*
 * Created on May 15, 2007
 *
 */
package messaging.cscdstaffposition;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class StaffPositionReportingEvent extends RequestEvent {
    private Date beginDate;
    private Date endDate;
    private String positionStatusId;
    private String agencyId;
    private String programUnitId;
    private String staffPositionId;
    private String staffLogonId;
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    /**
     * @return Returns the beginDate.
     */
    public Date getBeginDate() {
        return beginDate;
    }
    /**
     * @param beginDate The beginDate to set.
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    /**
     * @return Returns the endDate.
     */
    public Date getEndDate() {
        return endDate;
    }
    /**
     * @param endDate The endDate to set.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    /**
     * @return Returns the positionStatusId.
     */
    public String getPositionStatusId() {
        return positionStatusId;
    }
    /**
     * @param positionStatusId The positionStatusId to set.
     */
    public void setPositionStatusId(String positionStatusId) {
        this.positionStatusId = positionStatusId;
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
     * @return Returns the staffPositionId.
     */
    public String getStaffPositionId() {
        return staffPositionId;
    }
    /**
     * @param staffPositionId The staffPositionId to set.
     */
    public void setStaffPositionId(String staffPositionId) {
        this.staffPositionId = staffPositionId;
    }
    /**
     * @return Returns the staffLogonId.
     */
    public String getStaffLogonId() {
        return staffLogonId;
    }
    /**
     * @param staffLogonId The staffLogonId to set.
     */
    public void setStaffLogonId(String staffLogonId) {
        this.staffLogonId = staffLogonId;
    }
}
