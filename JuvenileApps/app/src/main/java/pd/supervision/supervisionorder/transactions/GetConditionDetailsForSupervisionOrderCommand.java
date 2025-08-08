/*
 * Created on Feb 13, 2006
 *
 */
package pd.supervision.supervisionorder.transactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import messaging.supervisionorder.GetConditionDetailsForSupervisionOrderEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 */
public class GetConditionDetailsForSupervisionOrderCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event)
	{
		GetConditionDetailsForSupervisionOrderEvent requestEvent = (GetConditionDetailsForSupervisionOrderEvent) event;
		String court = requestEvent.getCourtId();
		Condition condition = null;
		String conditionId = null;
		Collection conditions = requestEvent.getConditions();
		Map referenceVariableMap = requestEvent.getReferenceVariableMap();
		SupervisionOrder order = SupervisionOrder.find(requestEvent.getOrderId());
		if (conditions != null)
		{
			Iterator iter = conditions.iterator();
			while (iter.hasNext())
			{
				conditionId = (String) iter.next();
				condition = Condition.find(conditionId);
				if (condition != null)
				{
					SupervisionOrderHelper.postConditionDetailRespEvent(order, condition, court, referenceVariableMap);   
				}
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
