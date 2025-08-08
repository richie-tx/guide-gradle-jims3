//Source file:
//C:\\views\\CommonSupervision\\app\\src\\pd\\contact\\party\\transactions\\GetPartyCommand.java

package pd.contact.party.transactions;

import java.util.Collection;
import java.util.Iterator;

import pd.contact.party.PDPartyHelper;
import pd.contact.party.Party;
import messaging.contact.party.reply.PartyListResponseEvent;
import messaging.party.GetPartyDataEvent;
import messaging.party.GetPartyEvent;
import messaging.spnconsolidation.reply.SpnConsolidationErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetPartyCommand implements ICommand {

	/**
	 * @roseuid 452E79430008
	 */
	public GetPartyCommand() {

	}

	/**
	 * @param event
	 * @roseuid 452E637D0110
	 */
	public void execute(IEvent event) {

		GetPartyEvent thisEvent = (GetPartyEvent) event;
		Collection spnIter = thisEvent.getSpns();


		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		Party party = null;
		Iterator oidIter = spnIter.iterator();
		while (oidIter.hasNext()) {

			String spn = (String) oidIter.next();
			GetPartyDataEvent getPartyData = new GetPartyDataEvent();

			getPartyData.setSpn(spn);
			getPartyData.setCurrentNameInd("Y");

			party = Party.find(getPartyData);

			if (party != null) {
				PartyListResponseEvent partyInfo = PDPartyHelper.getPartyLightResponseEvent(party);

				dispatch.postEvent((IEvent) partyInfo);

			}else{
				this.sendSpnConsolidationErrorResponseEvent(getPartyData.getSpn());
			}
		}

	}

	private void sendSpnConsolidationErrorResponseEvent(String erroneousSpn) {

		SpnConsolidationErrorResponseEvent errorEvent = new SpnConsolidationErrorResponseEvent();
		errorEvent.setErroneousSpn(erroneousSpn);

		IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch1.postEvent((IEvent) errorEvent);

	}

	/**
	 * @param event
	 * @roseuid 452E637D011B
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 452E637D011D
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 452E637D011F
	 */
	public void update(Object anObject) {

	}

}
