/*
 * Created on Feb 23, 2006
 *
  */
package pd.supervision.supervisionorder.transactions;

import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import messaging.supervisionorder.GetSupervisionOrderDetailsEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 */
public class GetSupervisionOrderDetailsCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event)
	{
		GetSupervisionOrderDetailsEvent requestEvent = (GetSupervisionOrderDetailsEvent) event;
		SupervisionOrder order = SupervisionOrder.find(requestEvent.getSupervisionOrderId());
		if (order != null)
		{
			IDispatch replyDispatch = EventManager.getSharedInstance(EventManager.REPLY);
			SupervisionOrderDetailResponseEvent re =
				SupervisionOrderHelper.getSupervisionOrderDetailResponseEvent(order, requestEvent);
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
