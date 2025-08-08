package pd.juvenilecase.riskanalysis.transactions;

import java.util.List;

import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskRecommendation;
import messaging.riskanalysis.DeleteFormulaEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.context.ICommand;
import mojo.km.context.multidatasource.Home;

public class DeleteFormulaCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		DeleteFormulaEvent reqEvent = (DeleteFormulaEvent) anEvent;
		
		List <RiskCategory> categories = RiskCategory.findAll("formulaId", reqEvent.getRiskFormulaId());
	
		RiskCategory category = null;
		IHome home = new Home();
		
		for (int i = 0; i < categories.size(); i++) {
			category = categories.get(i);
			category.setFormulaId(null);
			home.bind(category);
		}
		
		List <RiskRecommendation> recommendations = 
			CollectionUtil.iteratorToList(RiskRecommendation.findAll("riskFormulaId", reqEvent.getRiskFormulaId()));

		RiskRecommendation recommendation = null;
		
		for (int i = 0; i < recommendations.size(); i++) {
			recommendation = recommendations.get(i);
			recommendation.setRiskFormulaId(null);
			home.bind(recommendation);
		}
		
		RiskFormula formula = RiskFormula.find(reqEvent.getRiskFormulaId());
		formula.delete();

		reqEvent = null;
		categories = null;
		category = null;
		home = null;
		recommendations = null;
		recommendation = null;
		formula = null;
		home = null;
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
