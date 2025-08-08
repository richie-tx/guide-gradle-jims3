/*
 * Created on May 17, 2006
 *
 */
package pd.supervision.supervisionorder.transactions;

import java.util.Iterator;
import pd.supervision.supervisionorder.SupervisionOrderConditionView;
import messaging.supervisionoptions.reply.ConditionInUseEvent;
import messaging.supervisionorder.ValidateConditionInUseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author ryoung
 * Determines whether a condition is in use.  A condition that is in use may not be changed or deleted.
 */
public class ValidateConditionInUseCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event)
	{
		ValidateConditionInUseEvent requestEvent = ( ValidateConditionInUseEvent ) event;
		String conditionId = requestEvent.getConditionId();
		Iterator iter = null;
		
		requestEvent.setConditionId( conditionId );
		iter = SupervisionOrderConditionView.findAll( requestEvent );
		if( iter.hasNext() )
		{
			ConditionInUseEvent inUseEvent = new ConditionInUseEvent();
			//inUseEvent.setInUse( true );
			this.postConditionInUseEvent(inUseEvent);
		}

	}
	
	/**
	 * 
	 */
	private void postConditionInUseEvent(ConditionInUseEvent inUseEvent)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(inUseEvent);
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
