/*
 * Created on May 17, 2006
 *
 */
package pd.supervision.supervisionoptions.transactions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import naming.PDJuvenileCaseConstants;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderCondition;
import messaging.supervisionoptions.ValidateConditionChangeEvent;
import messaging.supervisionoptions.reply.ConditionInUseEvent;
import messaging.supervisionorder.GetSupervisionOrdersFromConditionEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 * Determines whether a condition is in use.  A condition that is in use may not be changed or deleted.
 */
public class ValidateConditionChangeCommand implements ICommand
{
	private static String CONDITION_ID = "conditionId";
	private static String BLANK = "";
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event)
	{
		ValidateConditionChangeEvent requestEvent = (ValidateConditionChangeEvent) event;
		String conditionId = requestEvent.getConditionId();
		String action = requestEvent.getAction();
		if (conditionId == null || conditionId.equals(BLANK))
		{
			return;
		}

		this.checkForConditionInUse(conditionId);

	}

	/**
	 * @param conditionId
	 */
	private void checkForConditionInUse(String conditionId)
	{
		Iterator iter = null;
		//Check to see if condition is used in a supervision order
		//iter = SupervisionOrderCondition.findAll(CONDITION_ID, conditionId);
		//Changed to use event query to prevent problems caused in framework with attribute query
		//when the attribute is an integer.

		GetSupervisionOrdersFromConditionEvent anEvent = new GetSupervisionOrdersFromConditionEvent();
		anEvent.setConditionId(conditionId);
		iter = SupervisionOrderCondition.findAll(anEvent);
		if(iter.hasNext())
		{
			ConditionInUseEvent inUseEvent = new ConditionInUseEvent();
			Collection courts = getInUseCourts(conditionId);
			inUseEvent.setCourtIds(courts);
			this.postConditionInUseEvent(inUseEvent);
		}
		else
		{
			//Check to see if condition is used in juvenile casework
			iter = JuvenileCaseSupervisionRule.findAll(CONDITION_ID, conditionId);
			if (iter != null && iter.hasNext())
			{
				ConditionInUseEvent inUseEvent = new ConditionInUseEvent();
				inUseEvent.insertCourtId(PDJuvenileCaseConstants.RULES_DEFAULT_COURT);
				this.postConditionInUseEvent(inUseEvent);
			}
		}

	}

	private Collection getInUseCourts(String conditionId){
		Set courts = new HashSet();

		Iterator supervisionOrders = SupervisionOrder.findAllByCondition(conditionId);
		while(supervisionOrders.hasNext()){
			SupervisionOrder order = (SupervisionOrder)supervisionOrders.next();
			courts.add(order.getOrderCourtId());
		}
		return courts;
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
