package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetJuvenileReferralDecisionCodeEvent;
import messaging.codetable.GetJuvenileReferralSourceEvent;
import messaging.codetable.criminal.reply.JuvenileReferralDecisionResponseEvent;
import messaging.codetable.criminal.reply.JuvenileReferralSourceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileReferralDecision;
import pd.codetable.criminal.JuvenileReferralSource;

public class GetJuvenileReferralDecisionCodeCommand implements ICommand {
	/**
	 * 
	 */
	public GetJuvenileReferralDecisionCodeCommand()
	{

	}
	public void execute(IEvent event) throws Exception {
	    
		GetJuvenileReferralDecisionCodeEvent getCodeEvent = (GetJuvenileReferralDecisionCodeEvent) event;

		Iterator iter = JuvenileReferralDecision.findAll("code",getCodeEvent.getCode());		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		JuvenileReferralDecisionResponseEvent response = null;
		
		if(iter.hasNext())
		{
		    JuvenileReferralDecision code = (JuvenileReferralDecision) iter.next();
		    response = code.valueObject();

		}
		dispatch.postEvent(response);
	}
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
	}
}
