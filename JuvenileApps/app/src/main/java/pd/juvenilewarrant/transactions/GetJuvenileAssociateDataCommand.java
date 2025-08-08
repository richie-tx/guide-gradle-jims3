//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\GetJuvenileAssociateDataCommand.java

package pd.juvenilewarrant.transactions;

import java.util.Collection;
import java.util.Iterator;

import pd.juvenilewarrant.JuvenileAssociate;
import pd.juvenilewarrant.JuvenileAssociateAddress;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.juvenilewarrant.GetJuvenileAssociateDataEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileAssociateDataCommand implements ICommand
{

	/**
	 * @roseuid 42231DBD02CE
	 */
	public GetJuvenileAssociateDataCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4208F245035C
	 */
	public void execute(IEvent event)
	{
		GetJuvenileAssociateDataEvent anEvent = (GetJuvenileAssociateDataEvent) event;

		JuvenileAssociate juvenileAssociate = JuvenileAssociate.find(anEvent.getAssociateNumber());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		if (juvenileAssociate != null)
		{

			JuvenileAssociateResponseEvent jaResponseEvent =
				PDJuvenileWarrantHelper.getJuvenileAssociateResponseEvent(juvenileAssociate);

			dispatch.postEvent(jaResponseEvent);

			Collection addresses = juvenileAssociate.getAddresses();
			Iterator i = addresses.iterator();
			while (i.hasNext())
			{
				JuvenileAssociateAddress juvAddress = (JuvenileAssociateAddress) i.next();
				JuvenileAssociateAddressResponseEvent are = PDJuvenileWarrantHelper.getJuvenileAssociateAddressResponseEvent(juvAddress);
				// TODO Remove after tested.
				//AddressResponseEvent are = PDAddressHelper.getAddressResponseEvent(juvAddress);
				dispatch.postEvent(are);
			}

		}

	}

	/**
	 * @param event
	 * @roseuid 4208F245035E
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4208F2450360
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 42231DBD02DE
	 */
	public void update(Object updateObject)
	{

	}
}
