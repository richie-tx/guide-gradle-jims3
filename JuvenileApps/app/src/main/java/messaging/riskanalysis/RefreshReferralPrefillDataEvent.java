package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class RefreshReferralPrefillDataEvent extends RequestEvent {
	private String assessmentId;
	
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getAssessmentId() {
		return assessmentId;
	}
}
