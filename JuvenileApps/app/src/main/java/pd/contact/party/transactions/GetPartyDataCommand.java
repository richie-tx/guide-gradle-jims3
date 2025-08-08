//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\transactions\\GetPartyDataCommand.java

package pd.contact.party.transactions;

import messaging.domintf.contact.party.IParty;
import messaging.party.GetPartyDataEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.party.PDPartyHelper;
import pd.contact.party.Party;

/**
 * @author dgibler
 *
 */
public class GetPartyDataCommand implements ICommand
{

	/**
	 * @roseuid 416D2E6D0055
	 */
	public GetPartyDataCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 41659D77003F
	 */
	public void execute(IEvent event)
	{
		GetPartyDataEvent thisEvent = (GetPartyDataEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		Party party = null;
		String oid = thisEvent.getOID();
		if ((oid == null) || (oid.equals("")))
		{
			// If using the SPN to lookup the Party and it is not specified, default to the current name 
			if ((thisEvent.getSpn() != null) && (!thisEvent.getSpn().equals("")))
			{
				if ((thisEvent.getCurrentNameInd() == null)
					|| (thisEvent.getCurrentNameInd().equals("")))
				{
					thisEvent.setCurrentNameInd("Y");
				}
			}
			party = Party.find(thisEvent);
		}
		else
		{
			// use the OID if we have it
			party = Party.find(oid);

		}

		if (party != null)
		{
			IParty partyInfo = PDPartyHelper.getPartyResponseEvent(party);
			dispatch.postEvent((IEvent)partyInfo);
			if (!thisEvent.isThinResponse())
			{
				PDPartyHelper.createPartyChildrenResponseEvents(party);
			}
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
