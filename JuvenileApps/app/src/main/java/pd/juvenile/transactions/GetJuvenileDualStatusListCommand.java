//Source file: C:\\views\\dev\\app\\src\\pd\\juvenile\\transactions\\GetJuvenileAbuseListCommand.java

package pd.juvenile.transactions;

import java.util.Iterator;

import pd.juvenile.JuvenileAbuse;
import pd.juvenile.JuvenileDualStatus;
import messaging.juvenile.GetJuvenileAbuseListEvent;
import messaging.juvenile.GetJuvenileDualStatusListEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import messaging.juvenile.reply.JuvenileDualStatusResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileDualStatusListCommand implements ICommand
{

	/**
	 * @roseuid 42B18DBA0232
	 */
	public GetJuvenileDualStatusListCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18B40012D
	 */
	public void execute(IEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator dualStatuses = JuvenileDualStatus.findJuvenileDualStatuses((GetJuvenileDualStatusListEvent) event);
		while (dualStatuses.hasNext())
		{
		    	JuvenileDualStatus dual = (JuvenileDualStatus) dualStatuses.next();
			//JuvenileAbuseResponseEvent abuseResponse = dual.getValueObject();
			JuvenileDualStatusResponseEvent dualresponse=dual.getValueObject();
			dispatch.postEvent(dualresponse);
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
