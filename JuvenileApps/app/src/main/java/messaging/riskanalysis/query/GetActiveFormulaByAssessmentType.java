package messaging.riskanalysis.query;

import mojo.km.messaging.RequestEvent;

public class GetActiveFormulaByAssessmentType extends RequestEvent {
	private String assessmentType;

	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	public String getAssessmentType() {
		return assessmentType;
	}

}
