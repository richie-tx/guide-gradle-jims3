package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class GetFormulaDetailsEvent extends RequestEvent
{
	String riskFormulaId;

	public String getRiskFormulaId() {
		return riskFormulaId;
	}

	public void setRiskFormulaId(String riskFormulaId) {
		this.riskFormulaId = riskFormulaId;
	}
}
