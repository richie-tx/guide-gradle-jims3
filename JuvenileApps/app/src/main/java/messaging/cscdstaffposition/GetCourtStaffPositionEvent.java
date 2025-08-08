/*
 * Created on Jul 12, 2007
 */
package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_rbhat
 */
public class GetCourtStaffPositionEvent extends RequestEvent {
    private String agencyId;
	
    private String courtId;
    private String jobTitleId;
    boolean returnAssignedPositionsOnly;
    private String statusId;
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
	
	/**
	 * @return Returns the courtId.
	 */
	public String getCourtId() {
		return courtId;
	}
    /**
     * @return Returns the jobTitleId.
     */
    public String getJobTitleId() {
        return jobTitleId;
    }
    /**
     * @return Returns the statusId.
     */
    public String getStatusId() {
        return statusId;
    }
    /**
     * @return Returns the returnAssignedPositionsOnly.
     */
    public boolean isReturnAssignedPositionsOnly() {
        return returnAssignedPositionsOnly;
    }
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
    /**
     * @param jobTitleId The jobTitleId to set.
     */
    public void setJobTitleId(String jobTitleId) {
        this.jobTitleId = jobTitleId;
    }
    /**
     * @param returnAssignedPositionsOnly The returnAssignedPositionsOnly to set.
     */
    public void setReturnAssignedPositionsOnly(
            boolean returnAssignedPositionsOnly) {
        this.returnAssignedPositionsOnly = returnAssignedPositionsOnly;
    }
    /**
     * @param statusId The statusId to set.
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
