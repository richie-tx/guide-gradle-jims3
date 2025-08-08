package pd.juvenilecase.mentalhealth.transactions;

import pd.juvenilecase.mentalhealth.JuvenileAdaptiveBehaviorResults;
import messaging.mentalhealth.GetMentalHealthABTestDataEvent;
import messaging.mentalhealth.reply.ABTestResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;

public class GetMentalHealthABTestDataCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetMentalHealthABTestDataEvent abEvent = (GetMentalHealthABTestDataEvent)anEvent;
	   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   	JuvenileAdaptiveBehaviorResults abResults =  JuvenileAdaptiveBehaviorResults.find(abEvent.getTestId());
	   	ABTestResponseEvent abRespEvent = getABTestResponseEvent(abResults);
		dispatch.postEvent(abRespEvent);
	    
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
	
	private ABTestResponseEvent getABTestResponseEvent(JuvenileAdaptiveBehaviorResults juvenileAdaptiveBehaviorResults) {
		ABTestResponseEvent attRespEvent = new ABTestResponseEvent();
		attRespEvent.setTestDate(juvenileAdaptiveBehaviorResults.getTestDate());
		attRespEvent.setProgramReferralNum(juvenileAdaptiveBehaviorResults.getProgramReferralNum());
		attRespEvent.setServiceProviderName(juvenileAdaptiveBehaviorResults.getServiceProviderName());
		attRespEvent.setInstructorName(juvenileAdaptiveBehaviorResults.getInstrLastName()+ ", " +
				juvenileAdaptiveBehaviorResults.getInstrFirstName() + " " + juvenileAdaptiveBehaviorResults.getInstrMiddleName());
		attRespEvent.setTestName(juvenileAdaptiveBehaviorResults.getTestNameId());
		attRespEvent.setTestSessId(juvenileAdaptiveBehaviorResults.getTestSessId());
		attRespEvent.setTestId(juvenileAdaptiveBehaviorResults.getOID());
		attRespEvent.setServiceEventId(juvenileAdaptiveBehaviorResults.getServiceEventId());
		attRespEvent.setCommunicationScore(juvenileAdaptiveBehaviorResults.getCommunicationScore());
		attRespEvent.setCompositeScore(juvenileAdaptiveBehaviorResults.getCompositeScore());
		attRespEvent.setLivingScore(juvenileAdaptiveBehaviorResults.getLivingScore());
		attRespEvent.setSocialScore(juvenileAdaptiveBehaviorResults.getSocialScore());
		return attRespEvent;
	}
}
