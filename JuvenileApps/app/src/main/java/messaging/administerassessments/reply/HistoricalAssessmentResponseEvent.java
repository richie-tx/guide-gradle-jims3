/*
 * Created on Apr 14, 2008
 *
 */
package messaging.administerassessments.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class HistoricalAssessmentResponseEvent extends ResponseEvent {
    private Date priorSupervisionPeriodBeginDate;
    private Date priorSupervisionPeriodEndDate;
    /**
     * @return Returns the priorSupervisionPeriodBeginDate.
     */
    public Date getPriorSupervisionPeriodBeginDate() {
        return priorSupervisionPeriodBeginDate;
    }
    /**
     * @param priorSupervisionPeriodBeginDate The priorSupervisionPeriodBeginDate to set.
     */
    public void setPriorSupervisionPeriodBeginDate(
            Date priorSupervisionPeriodBeginDate) {
        this.priorSupervisionPeriodBeginDate = priorSupervisionPeriodBeginDate;
    }
    /**
     * @return Returns the priorSupervisionPeriodEndDate.
     */
    public Date getPriorSupervisionPeriodEndDate() {
        return priorSupervisionPeriodEndDate;
    }
    /**
     * @param priorSupervisionPeriodEndDate The priorSupervisionPeriodEndDate to set.
     */
    public void setPriorSupervisionPeriodEndDate(
            Date priorSupervisionPeriodEndDate) {
        this.priorSupervisionPeriodEndDate = priorSupervisionPeriodEndDate;
    }
}
