package pd.juvenilecase.riskanalysis.transactions;

import java.util.List;

import naming.PDConstants;
import pd.juvenilecase.riskanalysis.RiskRecommendation;
import messaging.riskanalysis.GetFormulaRecommendationEvent;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;

public class GetFormulaRecommendationCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetFormulaRecommendationEvent reqEvent = (GetFormulaRecommendationEvent) anEvent;
		
		RiskRecommendation recommendation = null;
		
		if (reqEvent.getRecommendationId() != null && !reqEvent.getRecommendationId().equals(PDConstants.BLANK)){
			recommendation = RiskRecommendation.find(reqEvent.getRecommendationId());
			MessageUtil.postReply(recommendation.getResponseEvent());
		} else {
			List <RiskRecommendation> recommendations = 
				CollectionUtil.iteratorToList(RiskRecommendation.findAll("riskFormulaId", reqEvent.getRiskFormulaId()));
			for (int i = 0; i < recommendations.size(); i++) {
				recommendation = recommendations.get(i);
				MessageUtil.postReply(recommendation.getResponseEvent());
			}
			recommendations = null;
		}
		
		reqEvent = null;
		recommendation = null;
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
