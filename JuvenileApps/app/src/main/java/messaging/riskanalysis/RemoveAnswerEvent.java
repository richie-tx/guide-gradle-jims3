package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class RemoveAnswerEvent extends RequestEvent
{
	private String riskAnswerId;
	
	public RemoveAnswerEvent() {
		
	}

	public void setRiskAnswerId(String riskAnswerId) {
		this.riskAnswerId = riskAnswerId;
	}

	public String getRiskAnswerId() {
		return riskAnswerId;
	}
	
}
