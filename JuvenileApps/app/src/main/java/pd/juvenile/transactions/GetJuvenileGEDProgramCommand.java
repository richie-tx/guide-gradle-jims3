package pd.juvenile.transactions;

import java.util.Iterator;

import messaging.juvenile.GetJuvenileGEDProgramEvent;
import messaging.juvenile.reply.JuvenileGEDProgramResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileGEDProgram;

public class GetJuvenileGEDProgramCommand implements ICommand
{

	/**
	 * @roseuid 42B18DC600AB
	 */
	public GetJuvenileGEDProgramCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18B3E032E
	 */
	public void execute(IEvent event)
	{
		GetJuvenileGEDProgramEvent gedEvent = (GetJuvenileGEDProgramEvent) event;
		Iterator i = JuvenileGEDProgram.findJuvenileGEDProgram(gedEvent);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (i.hasNext())
		{
			JuvenileGEDProgram gedProgram = (JuvenileGEDProgram) i.next();
			JuvenileGEDProgramResponseEvent replyEvent = gedProgram.getValueObject();
			dispatch.postEvent(replyEvent);
		}
	}

	/**
	 * @param event
	 * @roseuid 42B18B3E0330
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18B3E033C
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 42B18DC600CB
	 */
	public void update(Object updateObject)
	{

	}
}
