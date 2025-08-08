package messaging.riskanalysis;

import java.sql.Timestamp;

import mojo.km.messaging.RequestEvent;

public class GetRecentReferralRiskAnalysisByDateEvent extends RequestEvent {
	private String assessmentType;
	private String juvenileNum;
	private Timestamp riskDate;
	public String getAssessmentType() {
		return assessmentType;
	}
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	public Timestamp getRiskDate() {
		return riskDate;
	}
	public void setRiskDate(Timestamp riskDate) {
		this.riskDate = riskDate;
	}
}
