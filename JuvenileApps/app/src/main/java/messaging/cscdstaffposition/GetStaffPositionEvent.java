/*
 * Created on May 23, 2007
 *
 */
package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetStaffPositionEvent extends RequestEvent {
    private String agencyId;
    private String organizationId;
    private String positionTypeId;
    private String statusId;
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @return Returns the organizationId.
     */
    public String getOrganizationId() {
        return organizationId;
    }
    /**
     * @return Returns the positionTypeId.
     */
    public String getPositionTypeId() {
        return positionTypeId;
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
     * @param organizationId The organizationId to set.
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    /**
     * @param positionTypeId The positionTypeId to set.
     */
    public void setPositionTypeId(String positionTypeId) {
        this.positionTypeId = positionTypeId;
    }
    /**
     * @param statusId The statusId to set.
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
