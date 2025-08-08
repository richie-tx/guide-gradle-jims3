package messaging.administerassessments.query;

import mojo.km.messaging.RequestEvent;

public class GetNewestAssessmentEvent extends RequestEvent {

	public String getMasterAssessmentId() {
		return masterAssessmentId;
	}

	public void setMasterAssessmentId(String masterAssessmentId) {
		this.masterAssessmentId = masterAssessmentId;
	}

	private String masterAssessmentId;
}
