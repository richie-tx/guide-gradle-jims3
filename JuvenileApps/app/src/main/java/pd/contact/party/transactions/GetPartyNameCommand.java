package pd.contact.party.transactions;

import pd.contact.party.Party;
import messaging.contact.party.reply.PartyResponseEvent;
import messaging.party.GetPartyDataEvent;
import messaging.party.GetPartyNameEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class GetPartyNameCommand implements ICommand {

	public void execute(IEvent event) {
		GetPartyNameEvent getNameEvent = (GetPartyNameEvent) event;
		
		GetPartyDataEvent getPartyDataEvent = new GetPartyDataEvent();
		getPartyDataEvent.setSpn(getNameEvent.getSpn());
		getPartyDataEvent.setThinResponse(true);
		
		getPartyDataEvent.setNameSeqNum(getNameEvent.getNameSeqNum());
		Party party = Party.find(getPartyDataEvent);
		
		if (party != null){
			PartyResponseEvent re = new PartyResponseEvent();
			re.setFirstName(party.getFirstName());
			re.setMiddleName(party.getMiddleName());
			re.setLastName(party.getLastName());
			re.setNamePtr(party.getNamePtr());
			re.setNameSeqNum(party.getNameSeq());
			MessageUtil.postReply(re);
		}

	}

}
