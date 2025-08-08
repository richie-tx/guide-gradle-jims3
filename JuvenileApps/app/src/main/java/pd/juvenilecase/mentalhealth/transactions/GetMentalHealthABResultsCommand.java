package pd.juvenilecase.mentalhealth.transactions;

import java.util.Iterator;

import pd.juvenilecase.mentalhealth.JuvenileAdaptiveBehaviorResults;

import messaging.mentalhealth.GetMentalHealthABResultsEvent;
import messaging.mentalhealth.reply.ABTestResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;

public class GetMentalHealthABResultsCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetMentalHealthABResultsEvent abEvent = (GetMentalHealthABResultsEvent)anEvent;
	   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   	Iterator abIterator =	JuvenileAdaptiveBehaviorResults.findAll(abEvent);
	   	while (abIterator.hasNext()) {
	   		JuvenileAdaptiveBehaviorResults juvenileABResults = (JuvenileAdaptiveBehaviorResults) abIterator.next();
			ABTestResponseEvent attRespEvent = getABTestResponseEvent(juvenileABResults);
			dispatch.postEvent(attRespEvent);
		}
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
	
	   /**
	 * @param juvenileAchievementResults
	 * @return
	 */
	private ABTestResponseEvent getABTestResponseEvent(JuvenileAdaptiveBehaviorResults juvenileAdaptiveBehaviorResults) {
		ABTestResponseEvent attRespEvent = new ABTestResponseEvent();
		attRespEvent.setTestDate(juvenileAdaptiveBehaviorResults.getTestDate());
		attRespEvent.setProgramReferralNum(juvenileAdaptiveBehaviorResults.getProgramReferralNum());
		attRespEvent.setServiceProviderName(juvenileAdaptiveBehaviorResults.getServiceProviderName());
		attRespEvent.setInstructorName(juvenileAdaptiveBehaviorResults.getInstrLastName() + ", " +
				juvenileAdaptiveBehaviorResults.getInstrFirstName() + " " + juvenileAdaptiveBehaviorResults.getInstrMiddleName());
		attRespEvent.setTestName(juvenileAdaptiveBehaviorResults.getTestNameId());
		attRespEvent.setTestSessId(juvenileAdaptiveBehaviorResults.getTestSessId());
		attRespEvent.setTestId(juvenileAdaptiveBehaviorResults.getOID());
		attRespEvent.setServiceEventId(juvenileAdaptiveBehaviorResults.getServiceEventId());
		attRespEvent.setCompositeScore(juvenileAdaptiveBehaviorResults.getCompositeScore());
		return attRespEvent;
	}

}
