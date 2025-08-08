/*
 * Created on Mar 3, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilewarrant.transactions;

import java.util.Iterator;

import pd.juvenilewarrant.JuvenileAssociateAddress;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import messaging.juvenilewarrant.GetJuvenileAssociatesAddressesForWarrantEvent;
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
public class GetJuvenileAssociatesAddressesForWarrantCommand
	implements ICommand
{

	/**
	 * @param event
	 */
	public void execute(IEvent event) throws Exception
	{
		GetJuvenileAssociatesAddressesForWarrantEvent addressEvent =
			(GetJuvenileAssociatesAddressesForWarrantEvent) event;

		Iterator addresses = JuvenileAssociateAddress.findAll(addressEvent);

		//	Get the IDispatch to post to
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (addresses.hasNext())

		{
			JuvenileAssociateAddress address = (JuvenileAssociateAddress) addresses.next();
			JuvenileAssociateAddressResponseEvent associateEvent =
				PDJuvenileWarrantHelper.getJuvenileAssociateAddressResponseEvent(address);
			dispatch.postEvent(associateEvent);
		}
/*		
		GetJuvenileAssociatesAddressesForWarrantEvent addressEvent =
			(GetJuvenileAssociatesAddressesForWarrantEvent) event;

		Iterator associates =
			JuvenileAssociate.findAll(
				PDJuvenileWarrantConstants.WARRANT_NUM,
				addressEvent.getWarrantNum());

		while (associates.hasNext())
		{

			JuvenileAssociate juvAssociates =
				(JuvenileAssociate) associates.next();
			String associatesNum = juvAssociates.getAssociateNum();
			if (associatesNum != null)
			{

				Iterator addresses =
					JuvenileAssociateAddress.findAll(
						PDJuvenileWarrantConstants.ASSOCIATE_NUM,
						associatesNum);

				IDispatch dispatch =
					EventManager.getSharedInstance(EventManager.REPLY);

				while (addresses.hasNext())
				{
					JuvenileAssociateAddress address =
						(JuvenileAssociateAddress) addresses.next();
					JuvenileAssociateAddressResponseEvent associateEvent =
						PDJuvenileWarrantHelper
							.getJuvenileAssociateAddressResponseEvent(
							address);
					dispatch.postEvent(associateEvent);
				}
			}
		}
*/
	}

	/**
	 * @param event
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param event
	 */
	public void update(Object updateObject)
	{

	}

}
