package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetAllJuvenileReferralSourcesEvent;
import messaging.codetable.criminal.reply.JuvenileReferralSourceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileReferralSource;

public class GetAllJuvenileReferralSourcesCommand implements ICommand {
	/**
	 * 
	 */
	public GetAllJuvenileReferralSourcesCommand()
	{

	}
	public void execute(IEvent event) throws Exception {
	    GetAllJuvenileReferralSourcesEvent getCodeEvent = (GetAllJuvenileReferralSourcesEvent) event;

		Iterator juvRefSourceIter = JuvenileReferralSource.findAll();		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		while (juvRefSourceIter.hasNext())
		{
		    JuvenileReferralSource refSource = (JuvenileReferralSource)juvRefSourceIter.next();
		    if( !"Y".equalsIgnoreCase(refSource.getInactiveInd()) )
		    {
			JuvenileReferralSourceResponseEvent replyEvent = new JuvenileReferralSourceResponseEvent();
			replyEvent.setCode(refSource.getCode());
			replyEvent.setDescription(refSource.getDescription());
			replyEvent.setCodeWithDescription(refSource.getCode()+" - " + refSource.getDescription());
			dispatch.postEvent(replyEvent);
		    }
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
