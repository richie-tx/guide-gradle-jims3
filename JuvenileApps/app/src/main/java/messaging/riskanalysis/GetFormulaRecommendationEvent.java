package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class GetFormulaRecommendationEvent extends RequestEvent
{
	private String recommendationId;
	private String riskFormulaId;

	public String getRecommendationId() {
		return recommendationId;
	}
	public void setRecommendationId(String recommendationId) {
		this.recommendationId = recommendationId;
	}
	public void setRiskFormulaId(String riskFormulaId) {
		this.riskFormulaId = riskFormulaId;
	}
	public String getRiskFormulaId() {
		return riskFormulaId;
	}
}
