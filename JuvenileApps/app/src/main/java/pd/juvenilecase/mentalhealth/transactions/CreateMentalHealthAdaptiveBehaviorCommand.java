package pd.juvenilecase.mentalhealth.transactions;

import pd.juvenilecase.mentalhealth.JuvenileAdaptiveBehaviorResults;
import messaging.mentalhealth.CreateMentalHealthAdaptiveBehaviorEvent;
import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;

public class CreateMentalHealthAdaptiveBehaviorCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		CreateMentalHealthAdaptiveBehaviorEvent requestEvent = (CreateMentalHealthAdaptiveBehaviorEvent)anEvent;
		JuvenileAdaptiveBehaviorResults behaviorResults = new JuvenileAdaptiveBehaviorResults();
		behaviorResults.setTestSessId(requestEvent.getTestSessId());
		behaviorResults.setTestDate(requestEvent.getTestDate());
		behaviorResults.setTestNameId(requestEvent.getTestNameId());
		behaviorResults.setCommunicationScore(requestEvent.getCommunicationScore());
		behaviorResults.setCompositeScore(requestEvent.getCompositeScore());
		behaviorResults.setLivingScore(requestEvent.getLivingScore());
		behaviorResults.setSocialScore(requestEvent.getSocialScore());
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
