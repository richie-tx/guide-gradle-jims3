/*
 * Created on Mar 18, 2008
 */
package messaging.administerassessments.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class AssessmentResponseEvent extends ResponseEvent {
	private String masterAssessmentId;
    private String assessmentId;
    /**
     * @return Returns the assessmentId.
     */
    public String getAssessmentId() {
        return assessmentId;
    }
    /**
     * @param assessmentId The assessmentId to set.
     */
    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }
    
    /**
     * @param assessmentId
     */
    public AssessmentResponseEvent(String masterAssessmentId, String assessmentId) {
        super();
        this.masterAssessmentId = masterAssessmentId;
        this.assessmentId = assessmentId;
    }
	/**
	 * @return the masterAssessmentId
	 */
	public String getMasterAssessmentId() {
		return masterAssessmentId;
	}
	/**
	 * @param masterAssessmentId the masterAssessmentId to set
	 */
	public void setMasterAssessmentId(String masterAssessmentId) {
		this.masterAssessmentId = masterAssessmentId;
	}
}
