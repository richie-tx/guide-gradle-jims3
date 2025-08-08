package messaging.riskanalysis;

import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 */
public class SaveFormulaCategoryEvent extends RequestEvent
{
	private List categories;
	private String riskFormulaId;

	public List getCategories() {
		return categories;
	}

	public void setCategories(List categories) {
		this.categories = categories;
	}

	public void setRiskFormulaId(String riskFormulaId) {
		this.riskFormulaId = riskFormulaId;
	}

	public String getRiskFormulaId() {
		return riskFormulaId;
	}
}