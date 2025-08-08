/*
 * Created on Feb 16, 2006
 */
package pd.supervision.supervisionorder.transactions;

import java.util.Date;

import naming.PDCodeTableConstants;
import pd.supervision.supervisionorder.SupervisionOrder;
import messaging.supervisionorder.PrepareToFileSupervisionOrderEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 */
public class PrepareToFileSupervisionOrderCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event)
	{
		PrepareToFileSupervisionOrderEvent requestEvent = (PrepareToFileSupervisionOrderEvent) event;
		SupervisionOrder order = SupervisionOrder.find(requestEvent.getSupervisionOrderId());
		if (order != null)
		{
			order.setOrderStatusId(PDCodeTableConstants.STATUS_PENDING_ID);
			order.setStatusChangeDate(new Date()); 
			order.setOrderPresentorFirstName(requestEvent.getPresentorFirstName());
			order.setOrderPresentorLastName(requestEvent.getPresentorLastName());
			order.setSignedByTypeId(requestEvent.getSignorType());
			order.setSignedByFirstName(requestEvent.getSignorFirstName());
			order.setSignedByLastName(requestEvent.getSignorLastName());
			order.setOrderFiledDate(new Date());
			order.setOrderSignedDate(requestEvent.getSignedDate());
			order.setOrderSignedByJudgeDate(requestEvent.getJudgeSignedDate());
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
