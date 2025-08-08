package messaging.riskanalysis.query;

import mojo.km.messaging.RequestEvent;


public class GetFormulasByAssessmentTypeAndStatusEvent extends RequestEvent {
	String assessmentType;
	String statusCd;
	public String getAssessmentType() {
		return assessmentType;
	}
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
}
