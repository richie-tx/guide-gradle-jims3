package messaging.riskanalysis;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveFormulaRecommendationEvent extends RequestEvent
{
	private String assessmentTypeName;
	private boolean custody;
	private Date entryDate;
	private String formulaId;
	private int maxScore;
	private int minScore;
	private String recommendationId;
	private String recommendationName;
	private String recommendationText;
	private String resultGroupId;
	
	public String getAssessmentTypeName() {
		return assessmentTypeName;
	}
	public void setAssessmentTypeName(String assessmentTypeName) {
		this.assessmentTypeName = assessmentTypeName;
	}
	public boolean isCustody() {
		return custody;
	}
	public void setCustody(boolean custody) {
		this.custody = custody;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getFormulaId() {
		return formulaId;
	}
	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}
	public int getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	public int getMinScore() {
		return minScore;
	}
	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}
	public String getRecommendationId() {
		return recommendationId;
	}
	public void setRecommendationId(String recommendationId) {
		this.recommendationId = recommendationId;
	}
	public String getRecommendationName() {
		return recommendationName;
	}
	public void setRecommendationName(String recommendationName) {
		this.recommendationName = recommendationName;
	}
	public String getRecommendationText() {
		return recommendationText;
	}
	public void setRecommendationText(String recommendationText) {
		this.recommendationText = recommendationText;
	}
	public String getResultGroupId() {
		return resultGroupId;
	}
	public void setResultGroupId(String resultGroupId) {
		this.resultGroupId = resultGroupId;
	}
}
