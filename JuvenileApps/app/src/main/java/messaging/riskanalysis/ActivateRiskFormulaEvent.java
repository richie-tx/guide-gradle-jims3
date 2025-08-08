package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class ActivateRiskFormulaEvent extends RequestEvent
{
	private String riskFormulaId;

	public String getRiskFormulaId() {
		return riskFormulaId;
	}

	public void setRiskFormulaId(String riskFormulaId) {
		this.riskFormulaId = riskFormulaId;
	}
}
