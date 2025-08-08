/*
 * Created on May 17, 2006
 *
 */
package pd.supervision.suggestedorder.transactions;

import java.util.Iterator;

import pd.supervision.supervisionorder.SupervisionOrder;
import messaging.suggestedorder.ValidateSuggestedOrderDeleteEvent;
import messaging.suggestedorder.reply.SuggestedOrderInUseResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 */
public class ValidateSuggestedOrderDeleteCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event)
	{
		ValidateSuggestedOrderDeleteEvent requestEvent = (ValidateSuggestedOrderDeleteEvent) event;
		String suggestedOrderId = requestEvent.getSuggestedOrderId();
		if (suggestedOrderId == null || suggestedOrderId.equals(""))
		{
			return;
		}
		Iterator iter = SupervisionOrder.findAll("suggestedOrderId", suggestedOrderId);
		SupervisionOrder supervisionOrder = null;
		if (iter != null && iter.hasNext())
		{
			while (iter.hasNext())
			{
				supervisionOrder = (SupervisionOrder) iter.next();
				if (supervisionOrder.isOrderInProgress())
				{
					SuggestedOrderInUseResponseEvent sore = new SuggestedOrderInUseResponseEvent();
					IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
					dispatch.postEvent(sore);
					break;
				}
			}
		}
	} /* (non-Javadoc)
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
