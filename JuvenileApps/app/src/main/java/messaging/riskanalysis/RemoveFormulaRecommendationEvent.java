package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class RemoveFormulaRecommendationEvent extends RequestEvent
{
	private String recommendationId;

	public String getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(String recommendationId) {
		this.recommendationId = recommendationId;
	}
	
}
