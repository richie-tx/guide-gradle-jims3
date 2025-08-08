package messaging.riskanalysis;

import java.util.List;

import mojo.km.messaging.RequestEvent;

public class SaveFormulaEvent extends RequestEvent
{
	private List categories;
	private List recommendations;
	private String AssessmentType;
	private String riskFormulaId;
	
	public List getCategories() {
		return categories;
	}
	public void setCategories(List categories) {
		this.categories = categories;
	}
	public String getAssessmentType() {
		return AssessmentType;
	}
	public void setAssessmentType(String assessmentType) {
		AssessmentType = assessmentType;
	}
	public void setRiskFormulaId(String riskFormulaId) {
		this.riskFormulaId = riskFormulaId;
	}
	public String getRiskFormulaId() {
		return riskFormulaId;
	}
	public void setRecommendations(List recommendations) {
		this.recommendations = recommendations;
	}
	public List getRecommendations() {
		return recommendations;
	}
}
