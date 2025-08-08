package pd.codetable.transactions;

import messaging.codetable.GetJuvenileDSM5CodeEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.PDCriminalCodeTableHelper;

public class GetJuvenileDSM5CodeCommand implements ICommand{

	@Override
	public void execute(IEvent event) throws Exception {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		PDCriminalCodeTableHelper.sendDSM5Code(dispatch, (GetJuvenileDSM5CodeEvent)event);
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
