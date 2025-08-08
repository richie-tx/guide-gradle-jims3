/*
 * Created on Oct 29, 2007
 *
 */
package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetOrganizationStaffPositionByPOIEvent extends RequestEvent {
    
    private String agencyId;
    private String probationOfficerId;
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @return Returns the probationOfficerId.
     */
    public String getProbationOfficerId() {
        return probationOfficerId;
    }
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    /**
     * @param probationOfficerId The probationOfficerId to set.
     */
    public void setProbationOfficerId(String probationOfficerId) {
        this.probationOfficerId = probationOfficerId;
    }
}
