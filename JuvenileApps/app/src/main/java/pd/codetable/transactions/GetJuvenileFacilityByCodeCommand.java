package pd.codetable.transactions;

import messaging.codetable.GetJuvenileFacilityByCodeEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.PDCriminalCodeTableHelper;
/*
 * 	Added for 12533 user story
 */
public class GetJuvenileFacilityByCodeCommand implements ICommand{

	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetJuvenileFacilityByCodeEvent reqEvent = (GetJuvenileFacilityByCodeEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		PDCriminalCodeTableHelper.getJuvenileFacilityByCode(dispatch, reqEvent.getCode());
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub

	}

}
