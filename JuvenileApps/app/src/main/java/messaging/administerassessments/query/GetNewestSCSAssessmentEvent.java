/*
 * Created on Mar 26, 2008
 *
 */
package messaging.administerassessments.query;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetNewestSCSAssessmentEvent extends RequestEvent {
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
}
