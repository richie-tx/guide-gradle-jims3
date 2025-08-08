//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileTraitsByTypeCommand.java

package pd.juvenilecase.transactions;

import java.util.Iterator;

import pd.juvenilecase.JuvenileTrait;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileTraitsByTypeCommand implements ICommand
{

	/**
	 * @roseuid 42A7588500D6
	 */
	public GetJuvenileTraitsByTypeCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A70E2E0177
	 */
	public void execute(IEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator i = JuvenileTrait.findAll(event);
		while (i.hasNext())
		{
			JuvenileTrait trait = (JuvenileTrait) i.next();
			JuvenileTraitResponseEvent replyEvent = trait.getValueObject();
			dispatch.postEvent(replyEvent);
		}
	}

	/**
	 * @param event
	 * @roseuid 42A70E2E0185
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A70E2E0187
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A70E2E0189
	 */
	public void update(Object anObject)
	{

	}

}
