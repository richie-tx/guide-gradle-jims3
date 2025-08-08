package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class DeleteFormulaEvent extends RequestEvent
{
	private String riskFormulaId;

	public String getRiskFormulaId() {
		return riskFormulaId;
	}

	public void setRiskFormulaId(String assessmentId) {
		this.riskFormulaId = assessmentId;
	}
}
