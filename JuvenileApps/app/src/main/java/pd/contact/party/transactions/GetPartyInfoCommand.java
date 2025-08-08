//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\transactions\\GetPartyInfoCommand.java

package pd.contact.party.transactions;

import pd.contact.party.PDPartyHelper;
import pd.contact.party.Party;
import messaging.contact.party.reply.PartyResponseEvent;
import messaging.domintf.contact.party.IParty;
import messaging.party.GetPartyDataEvent;
import messaging.party.GetPartyInfoEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import naming.PDConstants;

/**
 * @author dgibler
 *
 */
public class GetPartyInfoCommand implements ICommand
{
	/**
	 * @roseuid 416D2E6E02F4
	 */
	public GetPartyInfoCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 416BD7DE0376
	 */
	public void execute(IEvent event)
	{		
		GetPartyInfoEvent getPartyInfoEvent = (GetPartyInfoEvent) event;
		
		if (getPartyInfoEvent.getSpn() != null && !getPartyInfoEvent.getSpn().equals(PDConstants.BLANK))
		{
			GetPartyDataEvent getPartyDataEvent = new GetPartyDataEvent();
			getPartyDataEvent.setSpn(getPartyInfoEvent.getSpn());
			getPartyDataEvent.setCurrentNameInd("Y");
			Party party = Party.find(getPartyDataEvent);
			if (party != null)
			{
				IParty partyInfo = PDPartyHelper.getPartyResponseEvent(party);
				MessageUtil.postReply((PartyResponseEvent)partyInfo);
				PDPartyHelper.createPartyChildrenResponseEvents(party);
			}
		}
	}
	
	/**
	 * @param event
	 * @roseuid 416BD7DE0378
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @param event
	 * @roseuid 416BD7DE0381
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * @param anObject
	 * @roseuid 416BD7DE0383
	 */
	public void update(Object anObject)
	{
	}
}