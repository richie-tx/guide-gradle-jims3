/*
 * Created on Feb 17, 2006
 */
package pd.codetable.transactions;

import java.util.Iterator;

import pd.codetable.supervision.Magistrate;

import messaging.supervisionorder.reply.MagistrateResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 */
public class GetMagistratesCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) 
	{
		Iterator iter = Magistrate.findAll(event);
		Magistrate magistrate = null;
		MagistrateResponseEvent responseEvent = null;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		if (iter != null)
		{
			while (iter.hasNext())
			{
				magistrate = (Magistrate) iter.next();
				responseEvent = magistrate.getResponseEvent();
				dispatch.postEvent(responseEvent);
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
