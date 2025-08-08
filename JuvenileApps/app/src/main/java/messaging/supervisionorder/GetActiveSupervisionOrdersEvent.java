/*
 * Created on Apr 15, 2008
 *
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetActiveSupervisionOrdersEvent extends RequestEvent {
    private String agencyId;
    private String defendantId;
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
