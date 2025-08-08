//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\GetJuvenileAssociateAddressessCommand.java

package pd.juvenilewarrant.transactions;

import java.util.Iterator;
import naming.PDJuvenileWarrantConstants;
import pd.juvenilewarrant.JuvenileAssociateAddress;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import messaging.juvenilewarrant.GetJuvenileAssociateAddressesEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
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
public class GetJuvenileAssociateAddressesCommand implements ICommand
{

	/**
	 * @roseuid 420A641501D4
	 */
	public GetJuvenileAssociateAddressesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 420A61440129
	 */
	public void execute(IEvent event)
	{
		GetJuvenileAssociateAddressesEvent warEvent = (GetJuvenileAssociateAddressesEvent) event;

		Iterator addresses = JuvenileAssociateAddress.findAll(PDJuvenileWarrantConstants.ASSOCIATE_NUM, warEvent.getAssociateNum());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (addresses.hasNext())
		{
			JuvenileAssociateAddress address = (JuvenileAssociateAddress) addresses.next();
			JuvenileAssociateAddressResponseEvent associateEvent = PDJuvenileWarrantHelper.getJuvenileAssociateAddressResponseEvent(address);
			dispatch.postEvent(associateEvent);
		}
	}

	/**
	 * @param event
	 * @roseuid 420A6144012B
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 420A6144012D
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 420A6144012F
	 */
	public void update(Object anObject)
	{

	}

}
