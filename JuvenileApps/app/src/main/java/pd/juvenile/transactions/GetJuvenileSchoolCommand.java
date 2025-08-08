package pd.juvenile.transactions;

import java.util.Iterator;

import pd.juvenile.JuvenileSchoolHistory;

import messaging.juvenile.GetJuvenileSchoolEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileSchoolCommand implements ICommand
{

	/**
	 * @roseuid 42B18DC600AB
	 */
	public GetJuvenileSchoolCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18B3E032E
	 */
	public void execute(IEvent event)
	{
		GetJuvenileSchoolEvent schoolEvent = (GetJuvenileSchoolEvent) event;
		Iterator i = JuvenileSchoolHistory.findJuvenileSchoolHistory(schoolEvent);		

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (i.hasNext())
		{
			JuvenileSchoolHistory school = (JuvenileSchoolHistory) i.next();
			JuvenileSchoolHistoryResponseEvent replyEvent = school.getValueObject();
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
