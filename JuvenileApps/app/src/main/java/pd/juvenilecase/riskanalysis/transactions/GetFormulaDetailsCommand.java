package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.List;

import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskRecommendation;
import messaging.riskanalysis.GetFormulaDetailsEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.CollectionUtil;
import mojo.km.context.ICommand;

public class GetFormulaDetailsCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetFormulaDetailsEvent reqEvent = (GetFormulaDetailsEvent) anEvent;
		RiskFormula riskFormula = RiskFormula.find(reqEvent.getRiskFormulaId());
		
		List replies = new ArrayList();
		replies.add(riskFormula.getResponseEvent());
		
		List <RiskCategory> categories = RiskCategory.findAll("formulaId", reqEvent.getRiskFormulaId());
		RiskCategory category = null;
		
		for (int i = 0; i < categories.size(); i++) {
			category = categories.get(i);
			replies.add(category.getResponseEvent());
		}
		
		RiskRecommendation recommendation = null;
		List <RiskRecommendation> recommendations = CollectionUtil.iteratorToList(RiskRecommendation.findAll("riskFormulaId", reqEvent.getRiskFormulaId()));
		
		for (int i = 0; i < recommendations.size(); i++) {
			recommendation = recommendations.get(i);
			recommendation.getResponseEvent();
			replies.add(recommendation.getResponseEvent());
		}
		
		MessageUtil.postReplies(replies);
		
		reqEvent = null;
		riskFormula = null;
		replies = null;
		categories = null;
		category = null;
		recommendation = null;
		recommendations = null;
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
