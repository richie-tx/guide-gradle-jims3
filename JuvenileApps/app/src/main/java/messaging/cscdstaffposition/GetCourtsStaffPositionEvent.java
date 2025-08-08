/*
 * Created on Aug 7, 2007
 *
 */
package messaging.cscdstaffposition;

import java.util.Collection;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetCourtsStaffPositionEvent extends RequestEvent {
    String agencyId;
    Collection courts;
    String jobTitleId;
    String staffPositionId;
    String statusId;
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @return Returns the courts.
     */
    public Collection getCourts() {
        return courts;
    }
    /**
     * @return Returns the jobTitleId.
     */
    public String getJobTitleId() {
        return jobTitleId;
    }
    /**
     * @return Returns the staffPositionId.
     */
    public String getStaffPositionId() {
        return staffPositionId;
    }
    /**
     * @return Returns the statusId.
     */
    public String getStatusId() {
        return statusId;
    }
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    /**
     * @param courts The courts to set.
     */
    public void setCourts(Collection courts) {
        this.courts = courts;
    }
    /**
     * @param jobTitleId The jobTitleId to set.
     */
    public void setJobTitleId(String jobTitleId) {
        this.jobTitleId = jobTitleId;
    }
    /**
     * @param staffPositionId The staffPositionId to set.
     */
    public void setStaffPositionId(String staffPositionId) {
        this.staffPositionId = staffPositionId;
    }
    /**
     * @param statusId The statusId to set.
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
