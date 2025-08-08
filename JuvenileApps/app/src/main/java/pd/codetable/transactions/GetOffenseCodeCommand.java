/*
 * Created on Nov 10, 2005
 *
 */
package pd.codetable.transactions;

import java.util.Iterator;

import pd.codetable.criminal.OffenseCode;
import messaging.codetable.GetOffenseCodeEvent;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetOffenseCodeCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetOffenseCodeEvent getCodeEvent = (GetOffenseCodeEvent) event;
		getCodeEvent.setOffenseCode(getCodeEvent.getOffenseCode());
		
		Iterator iter = OffenseCode.findAll(getCodeEvent);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		while (iter.hasNext())
		{
			OffenseCode offenseCode = (OffenseCode) iter.next();
			OffenseCodeResponseEvent ocre = offenseCode.getResponseEvent();
			dispatch.postEvent(ocre);
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
