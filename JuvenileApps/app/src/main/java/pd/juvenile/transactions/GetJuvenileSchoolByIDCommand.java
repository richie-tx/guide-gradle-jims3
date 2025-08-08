package pd.juvenile.transactions;

import messaging.juvenile.GetJuvenileSchoolByIDEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileSchoolHistory;


public class GetJuvenileSchoolByIDCommand implements ICommand
{

	/**
	 * @roseuid 42B18DC600AB
	 */
	public GetJuvenileSchoolByIDCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18B3E032E
	 */
	public void execute(IEvent event)
	{
		GetJuvenileSchoolByIDEvent schoolEvent = (GetJuvenileSchoolByIDEvent) event;
		
		JuvenileSchoolHistory school = JuvenileSchoolHistory.find( schoolEvent.getSchoolId() );	
		JuvenileSchoolHistoryResponseEvent replyEvent = new JuvenileSchoolHistoryResponseEvent();
		if(school != null){
			replyEvent = school.getValueObject();
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
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
