//Source file: C:\\views\\dev\\app\\src\\pd\\juvenile\\transactions\\GetJuvenileAbuseListCommand.java

package pd.juvenile.transactions;

import java.util.Iterator;

import pd.juvenile.JuvenileAbuse;
import messaging.juvenile.GetJuvenileAbuseListEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileAbuseListCommand implements ICommand
{

	/**
	 * @roseuid 42B18DBA0232
	 */
	public GetJuvenileAbuseListCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18B40012D
	 */
	public void execute(IEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator abuses = JuvenileAbuse.findJuvenileAbuses((GetJuvenileAbuseListEvent) event);
		while (abuses.hasNext())
		{
			JuvenileAbuse abuse = (JuvenileAbuse) abuses.next();
			JuvenileAbuseResponseEvent abuseResponse = abuse.getValueObject();
			dispatch.postEvent(abuseResponse);
		}
	}

	/**
	 * @param event
	 * @roseuid 42B18B400138
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18B40013A
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 42B18DBA0251
	 */
	public void update(Object updateObject)
	{

	}
}
