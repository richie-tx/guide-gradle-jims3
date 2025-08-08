package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class RemoveQuestionEvent extends RequestEvent
{
	private String riskQuestionId;
	
	public RemoveQuestionEvent() {
		
	}

	public void setRiskQuestionId(String riskQuestionId) {
		this.riskQuestionId = riskQuestionId;
	}

	public String getRiskQuestionId() {
		return riskQuestionId;
	}
}
