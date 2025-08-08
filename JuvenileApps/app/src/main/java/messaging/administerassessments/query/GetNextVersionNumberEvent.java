/*
 * Created on Feb 8, 2008
 *
 */
package messaging.administerassessments.query;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetNextVersionNumberEvent extends RequestEvent {
private String masterAssessmentId;
/**
 * @return Returns the masterAssessmentId.
 */
public String getMasterAssessmentId() {
    return masterAssessmentId;
}
/**
 * @param masterAssessmentId The masterAssessmentId to set.
 */
public void setMasterAssessmentId(String masterAssessmentId) {
    this.masterAssessmentId = masterAssessmentId;
}
}
