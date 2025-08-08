/*
 * Created on Aug 8, 2007
 *
 */
package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetStaffPositionsForSupervisionOrderEvent extends RequestEvent {
    String agencyId;
    String courtId;
    String jobTitleId;
    String statusId;
    boolean returnAssignedPositionsOnly = true;
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
     * @return Returns the courtId.
     */
    public String getCourtId() {
        return courtId;
    }
    /**
     * @param courtId The courtId to set.
     */
    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }
    /**
     * @return Returns the jobTitleId.
     */
    public String getJobTitleId() {
        return jobTitleId;
    }
    /**
     * @param jobTitleId The jobTitleId to set.
     */
    public void setJobTitleId(String jobTitleId) {
        this.jobTitleId = jobTitleId;
    }
    /**
     * @return Returns the statusId.
     */
    public String getStatusId() {
        return statusId;
    }
    /**
     * @param statusId The statusId to set.
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
	/**
	 * @return Returns the returnAssignedPositionsOnly.
	 */
	public boolean isReturnAssignedPositionsOnly() {
		return returnAssignedPositionsOnly;
	}
	/**
	 * @param returnAssignedPositionsOnly The returnAssignedPositionsOnly to set.
	 */
	public void setReturnAssignedPositionsOnly(boolean returnAssignedPositionsOnly) {
		this.returnAssignedPositionsOnly = returnAssignedPositionsOnly;
	}
}
