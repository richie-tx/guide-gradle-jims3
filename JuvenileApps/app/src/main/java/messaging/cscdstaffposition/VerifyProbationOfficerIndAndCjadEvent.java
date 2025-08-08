/*
 * Created on May 22, 2007
 *
 */
package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *  
 */
public class VerifyProbationOfficerIndAndCjadEvent extends RequestEvent {
    private String agencyId;
    private String cjadNum;
    private String probationOfficerInd;
    private String staffPositionId;
    private String statusId;
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @return Returns the cjadNum.
     */
    public String getCjadNum() {
        return cjadNum;
    }
    /**
     * @return Returns the probationOfficerInd.
     */
    public String getProbationOfficerInd() {
        return probationOfficerInd;
    }
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    /**
     * @param cjadNum The cjadNum to set.
     */
    public void setCjadNum(String cjadNum) {
        this.cjadNum = cjadNum;
    }
    /**
     * @param probationOfficerInd The probationOfficerInd to set.
     */
    public void setProbationOfficerInd(String probationOfficerInd) {
        this.probationOfficerInd = probationOfficerInd;
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
}
