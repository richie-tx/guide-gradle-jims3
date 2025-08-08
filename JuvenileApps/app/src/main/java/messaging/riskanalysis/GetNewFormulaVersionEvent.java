package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class GetNewFormulaVersionEvent extends RequestEvent {
	private String assessmentTypeCd;

	public void setAssessmentTypeCd(String assessmentTypeCd) {
		this.assessmentTypeCd = assessmentTypeCd;
	}

	public String getAssessmentTypeCd() {
		return assessmentTypeCd;
	}
}
