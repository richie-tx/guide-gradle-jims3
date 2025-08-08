/*
 * Created on Nov 3, 2006
 *
 */
package messaging.supervisionorder.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class SupervisionPeriodResponseEvent extends ResponseEvent {
    private String agencyId;
    private String supervisionPeriodId;
    private Date supervisionBeginDate;
    private Date supervisionEndDate;
    
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @return Returns the previousSupervisionPeriodId.
     */
    public String getSupervisionPeriodId() {
        return supervisionPeriodId;
    }
    /**
     * @return Returns the supervisionBeginDate.
     */
    public Date getSupervisionBeginDate() {
        return supervisionBeginDate;
    }
    /**
     * @return Returns the supervisionEndDate.
     */
    public Date getSupervisionEndDate() {
        return supervisionEndDate;
    }
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String anAgencyId) {
        this.agencyId = anAgencyId;
    }
    /**
     * @param previousSupervisionPeriodId The previousSupervisionPeriodId to set.
     */
    public void setSupervisionPeriodId(String aSupervisionPeriodId) {
        this.supervisionPeriodId = aSupervisionPeriodId;
    }
    /**
     * @param supervisionBeginDate The supervisionBeginDate to set.
     */
    public void setSupervisionBeginDate(Date aSupervisionBeginDate) {
        this.supervisionBeginDate = aSupervisionBeginDate;
    }
    /**
     * @param supervisionEndDate The supervisionEndDate to set.
     */
    public void setSupervisionEndDate(Date aSupervisionEndDate) {
        this.supervisionEndDate = aSupervisionEndDate;
    }
}
