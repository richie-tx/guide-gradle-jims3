package pd.juvenile.transactions;

import pd.juvenile.JuvenileCharterGED;
import messaging.juvenile.GetCharterGEDDetailsEvent;
import messaging.juvenile.reply.CharterGEDResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;

public class GetCharterGEDDetailsCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetCharterGEDDetailsEvent requestEvent = (GetCharterGEDDetailsEvent)anEvent;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		CharterGEDResponseEvent resp = new CharterGEDResponseEvent();
		JuvenileCharterGED charterGED = JuvenileCharterGED.find(requestEvent.getJuvenileCharterGEDId());
		CharterGEDResponseEvent responseGED = charterGED.getResponseEvent();
		if (responseGED != null)
		{
			dispatch.postEvent(resp);
		}
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
