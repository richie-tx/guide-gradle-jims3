//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\transactions\\ValidatePartyIdNumbersCommand.java

package pd.contact.party.transactions;

import java.util.Iterator;

import messaging.domintf.contact.party.IParty;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.contact.party.PDPartyHelper;
import pd.contact.party.Party;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ValidatePartyIdNumbersCommand implements ICommand
{

	/**
	 * @roseuid 416D2E7003C0
	 */
	public ValidatePartyIdNumbersCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4166D68700DC
	 */
	public void execute(IEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		IHome home = new Home();
		Iterator iter = home.findAll(event,Party.class);
		
		if (iter != null)
		{
			while (iter.hasNext())
			{
				Party party = (Party) iter.next();
				IParty partyInfo = PDPartyHelper.getPartyResponseEvent(party);
				dispatch.postEvent((IEvent)partyInfo);
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 4166D68700DE
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4166D68700E0
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4166D68700EA
	 */
	public void update(Object anObject)
	{

	}

}
