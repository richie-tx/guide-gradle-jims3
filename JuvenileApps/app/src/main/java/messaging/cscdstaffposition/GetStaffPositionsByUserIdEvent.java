/*
 * Created on Aug 23, 2007
 *
 */
package messaging.cscdstaffposition;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetStaffPositionsByUserIdEvent extends RequestEvent {
    private String agencyId;
    private Collection logonIds;
    private String statusId;
    /**
     * @param logonId
     */
    public void addLogonId(String logonId){
        if (logonIds == null){
            logonIds = new ArrayList();
        }
        logonIds.add(logonId);
    }
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @return Returns the logonIds.
     */
    public Collection getLogonIds() {
        return logonIds;
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
     * @param logonIds The logonIds to set.
     */
    public void setLogonIds(Collection logonIds) {
        this.logonIds = logonIds;
    }
    /**
     * @param statusId The statusId to set.
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
