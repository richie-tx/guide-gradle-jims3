package ui.supervision.administersupervisionplan;

import java.util.Collection;

public class ForceFieldAnalysisReportingBean {
	private String assessementDate;
	private Collection forceFieldsList;
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
	public Collection getForceFieldsList() {
		return forceFieldsList;
	}
	public void setForceFieldsList(Collection forceFieldsList) {
		this.forceFieldsList = forceFieldsList;
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
