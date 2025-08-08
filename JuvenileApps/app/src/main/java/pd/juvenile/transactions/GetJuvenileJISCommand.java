package pd.juvenile.transactions;

import java.util.Iterator;

import messaging.juvenile.GetJuvenileJISEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileJIS;

public class GetJuvenileJISCommand implements ICommand
{

	/**
	 * @roseuid 42B18DC600AB
	 */
	public GetJuvenileJISCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18B3E032E
	 */
	public void execute(IEvent event)
	{
		GetJuvenileJISEvent jisEvent = (GetJuvenileJISEvent) event;
		Iterator i = JuvenileJIS.findAll("juvenileNum", jisEvent.getJuvenileNum());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (i.hasNext())
		{
			JuvenileJIS jisRec = (JuvenileJIS) i.next();
			JuvenileJISResponseEvent replyEvent = new JuvenileJISResponseEvent();
			replyEvent.setAgency(jisRec.getAgency());
			replyEvent.setComments(jisRec.getComments());
			replyEvent.setEntryDate(jisRec.getEntryDate());
			replyEvent.setOtherAgency(jisRec.getOtherAgency());
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
