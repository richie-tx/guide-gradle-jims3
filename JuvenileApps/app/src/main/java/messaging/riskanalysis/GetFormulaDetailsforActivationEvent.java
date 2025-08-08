package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class GetFormulaDetailsforActivationEvent extends RequestEvent
{
	String formulaToBeActivatedId;

	public String getFormulaToBeActivatedId() {
		return formulaToBeActivatedId;
	}

	public void setFormulaToBeActivatedId(String formulaToBeActivatedId) {
		this.formulaToBeActivatedId = formulaToBeActivatedId;
	}
}
