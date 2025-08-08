package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetJuvenileReferralSourceEvent;
import messaging.codetable.criminal.reply.JuvenileReferralSourceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileReferralSource;

public class GetJuvenileReferralSourceCommand implements ICommand {
	/**
	 * 
	 */
	public GetJuvenileReferralSourceCommand()
	{

	}
	public void execute(IEvent event) throws Exception {
		GetJuvenileReferralSourceEvent getCodeEvent = (GetJuvenileReferralSourceEvent) event;

		Iterator  juvRefSource = JuvenileReferralSource.findAll("code", getCodeEvent.getCode());
        	while (juvRefSource.hasNext())
        	{
        	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        	    JuvenileReferralSourceResponseEvent replyEvent = new JuvenileReferralSourceResponseEvent();
        	    JuvenileReferralSource refSource = (JuvenileReferralSource) juvRefSource.next();
        	    if (refSource != null)
        	    {
        		replyEvent.setCode(refSource.getCode());
        		replyEvent.setDescription(refSource.getDescription());
        	    }
        	    dispatch.postEvent(replyEvent);
        	}
		    
		
		
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
