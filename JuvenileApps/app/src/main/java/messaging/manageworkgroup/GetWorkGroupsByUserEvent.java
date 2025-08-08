/*
 * Created on Jun 26, 2007
 *
 */
package messaging.manageworkgroup;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetWorkGroupsByUserEvent extends RequestEvent {
    String agencyId;
    String logonId;
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
     * @return Returns the logonId.
     */
    public String getLogonId() {
        return logonId;
    }
    /**
     * @param logonId The userId to set.
     */
    public void setLogonId(String logonId) {
        this.logonId = logonId;
    }
}
