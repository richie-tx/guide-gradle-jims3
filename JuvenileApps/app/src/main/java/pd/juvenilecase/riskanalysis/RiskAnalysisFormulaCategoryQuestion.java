package pd.juvenilecase.riskanalysis;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class RiskAnalysisFormulaCategoryQuestion extends RiskQuestions {
	private String riskFormulaId;
	private RiskFormula riskFormula;

    private void initRiskFormula() 
	{
		if (riskFormula == null) 
		{
			riskFormula = (RiskFormula) new mojo.km.persistence.Reference(riskFormulaId, RiskFormula.class).getObject();
		}
	}
	
	public String getRiskFormulaId() 
	{
		fetch();
		return riskFormulaId;
	}
	
	public void setRiskFormulaId(String formulaId) 
	{
		if (this.riskFormulaId == null || !this.riskFormulaId.equals(formulaId)) 
		{
			markModified();
		}
		riskFormula = null;
		this.riskFormulaId = formulaId;
	}

	public RiskFormula getRiskFormula() 
	{
		fetch();
		initRiskFormula();
		return riskFormula;
	}
	
	public void setRiskFormula(RiskFormula formula)
	{
		if (this.riskFormula == null || !this.riskFormula.equals(formula)) 
		{
			markModified();
		}
		if (formula.getOID() == null) 
		{
			new mojo.km.persistence.Home().bind(formula);
		}
		setRiskFormulaId(formula.getOID());
		this.riskFormula = (RiskFormula) new mojo.km.persistence.Reference(formula).getObject();
	}
	
	public static Iterator findAll(String attributeName, String attributeValue){
		 IHome home = new Home();
		 Iterator iter = home.findAll(attributeName, attributeValue, RiskAnalysisFormulaCategoryQuestion.class);
		 return iter;
	
	}
}
