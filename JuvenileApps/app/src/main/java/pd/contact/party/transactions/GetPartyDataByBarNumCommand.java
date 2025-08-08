//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\transactions\\GetPartyDataCommand.java

package pd.contact.party.transactions;

import messaging.contact.party.reply.PartyResponseEvent;
import messaging.party.GetPartyDataByBarNumEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.party.Party;

/**
 * @author dgibler
 *
 */
public class GetPartyDataByBarNumCommand implements ICommand
{

	/**
	 * @roseuid 416D2E6D0055
	 */
	public GetPartyDataByBarNumCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 41659D77003F
	 */
	public void execute(IEvent event)
	{
		GetPartyDataByBarNumEvent thisEvent = (GetPartyDataByBarNumEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		Party party = null;
		String barNum = thisEvent.getBarNum();
		party = Party.find(thisEvent);
		if (party != null)
		{
			PartyResponseEvent pre = new PartyResponseEvent();
			pre.setName(party.getName());
			dispatch.postEvent(pre);			
		}
	}

	/**
	 * @param event
	 * @roseuid 41659D770041
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 41659D770043
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 41659D770045
	 */
	public void update(Object anObject)
	{

	}

}
