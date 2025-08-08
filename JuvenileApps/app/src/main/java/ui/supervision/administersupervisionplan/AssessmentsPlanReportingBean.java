package ui.supervision.administersupervisionplan;

import java.util.Collection;

public class AssessmentsPlanReportingBean {
	private String assessementDate;
	private Collection riskAssessmentQuestionList;
	private Collection needAssessmentQuestionList;
	private String totalRiskScore;
	private String riskLevel;
	private String totalNeedsScore;
	private String needsLevel;
	private String spn;
	private String defendantName;
	private boolean reAssessment;
	private String pageHeading;
	
	
	public String getSpn() {
		return spn;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
	public String getAssessementDate() {
		return assessementDate;
	}
	public void setAssessementDate(String assessementDate) {
		this.assessementDate = assessementDate;
	}
	public Collection getRiskAssessmentQuestionList() {
		return riskAssessmentQuestionList;
	}
	public void setRiskAssessmentQuestionList(Collection riskAssessmentQuestionList) {
		this.riskAssessmentQuestionList = riskAssessmentQuestionList;
	}
	public Collection getNeedAssessmentQuestionList() {
		return needAssessmentQuestionList;
	}
	public void setNeedAssessmentQuestionList(Collection needAssessmentQuestionList) {
		this.needAssessmentQuestionList = needAssessmentQuestionList;
	}
	public String getTotalRiskScore() {
		return totalRiskScore;
	}
	public void setTotalRiskScore(String totalRiskScore) {
		this.totalRiskScore = totalRiskScore;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getTotalNeedsScore() {
		return totalNeedsScore;
	}
	public void setTotalNeedsScore(String totalNeedsScore) {
		this.totalNeedsScore = totalNeedsScore;
	}
	public String getNeedsLevel() {
		return needsLevel;
	}
	public void setNeedsLevel(String needsLevel) {
		this.needsLevel = needsLevel;
	}
	public String getDefendantName() {
		return defendantName;
	}
	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}
	public boolean isReAssessment() {
		return reAssessment;
	}
	public void setReAssessment(boolean reAssessment) {
		this.reAssessment = reAssessment;
	}
	public String getPageHeading() {
		return pageHeading;
	}
	public void setPageHeading(String pageHeading) {
		this.pageHeading = pageHeading;
	}
	
	
	
	

}
