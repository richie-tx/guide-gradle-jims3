/*
 * Created on May 7, 2007
 *
 */
package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetOrganizationDivisionManagersEvent extends RequestEvent {
    String agencyId;
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
}
