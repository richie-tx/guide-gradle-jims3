/*
 * Created on Jan 23, 2008
 *
  */
package pd.supervision.supervisionorder.transactions;

import java.util.Iterator;

import messaging.supervisionorder.GetSprOrderConditionLiteralEvent;
import messaging.supervisionorder.reply.SupOrderConditionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionorder.SupervisionOrderCondition;

/**
 * @author mchowdhury
 */
public class GetSprOrderConditionLiteralCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event)
	{
		GetSprOrderConditionLiteralEvent requestEvent = (GetSprOrderConditionLiteralEvent) event;
		Iterator iter = SupervisionOrderCondition.findAll(requestEvent);
		if (iter.hasNext())
		{
			IDispatch replyDispatch = EventManager.getSharedInstance(EventManager.REPLY);
			SupervisionOrderCondition c = (SupervisionOrderCondition) iter.next();
			SupOrderConditionResponseEvent re = new SupOrderConditionResponseEvent();
			re.setResolvedDescription(c.getResolvedDescription());	
			re.setName(c.getName());
			replyDispatch.postEvent(re);
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
