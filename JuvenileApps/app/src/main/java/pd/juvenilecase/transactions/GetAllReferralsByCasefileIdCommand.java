package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetAllReferralsByCasefileIdEvent;
import messaging.juvenilecase.GetReferralsByCasefileIdEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefileReferral;

/**
 * 
 * @author sthyagarajan
 *
 */
public class GetAllReferralsByCasefileIdCommand implements ICommand{

	
	/**
	 * @param event
	 * @roseuid 42791D5702FF
	 */
	public void execute(IEvent event)
	{
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		
		GetAllReferralsByCasefileIdEvent searchEvent = (GetAllReferralsByCasefileIdEvent)event;	
		
		Iterator<JuvenileCasefileReferral> JuvenileCasefileReferralItr = JuvenileCasefileReferral.findAll(searchEvent);
		while (JuvenileCasefileReferralItr.hasNext())
		{
			JuvenileCasefileReferral caseRef= JuvenileCasefileReferralItr.next();
			JuvenileCasefileReferralResponseEvent resp = caseRef.getValueObject();
			dispatch.postEvent(resp);
		}
		
	}

	/**
	 * @param event
	 * @roseuid 42791D570301
	 */
	public void onRegister(IEvent event)
	{

	}
}
