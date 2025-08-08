//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\GetJuvenileWarrantAssociatesCommand.java

package pd.juvenilewarrant.transactions;

import java.util.Collection;
import java.util.Iterator;

import pd.juvenilewarrant.JuvenileAssociate;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import messaging.juvenilewarrant.GetJuvenileWarrantAssociatesEvent;
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
public class GetJuvenileWarrantAssociatesCommand implements ICommand
{

	/**
	 * @roseuid 421A46D30242
	 */
	public GetJuvenileWarrantAssociatesCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 421A433E0042
	 */
	public void execute(IEvent event)
	{
		GetJuvenileWarrantAssociatesEvent warEvent = (GetJuvenileWarrantAssociatesEvent) event;

		JuvenileWarrant war = JuvenileWarrant.find(warEvent.getWarrantNum());
		Collection associates = war.getJuvenileAssociates();

		//Iterator associates = JuvenileAssociate.findAll(PDJuvenileWarrantConstants.WARRANT_NUM, warEvent.getWarrantNum());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		if (associates != null)
		{
			Iterator i = associates.iterator();

			while (i.hasNext())
			{
				JuvenileAssociate associate = (JuvenileAssociate) i.next();
				JuvenileAssociateResponseEvent associateEvent =
					PDJuvenileWarrantHelper.getJuvenileAssociateResponseEvent(associate);
				dispatch.postEvent(associateEvent);
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 421A433E004F
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @param event
	 * @roseuid 421A433E0051
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * @param updateObject
	 * @roseuid 421A46D30251
	 */
	public void update(Object updateObject)
	{
	}
}