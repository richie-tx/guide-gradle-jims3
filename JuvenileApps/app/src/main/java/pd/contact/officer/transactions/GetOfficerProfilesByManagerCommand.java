/*
 */
package pd.contact.officer.transactions;

import java.util.Iterator;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfilesByManagerEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;

public class GetOfficerProfilesByManagerCommand implements ICommand
{
	/**
	 * @roseuid 42E67C2501F4
	 */
	public GetOfficerProfilesByManagerCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA70375
	 */
	public void execute(IEvent event)
	{
		GetOfficerProfilesByManagerEvent officerEvent = (GetOfficerProfilesByManagerEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		OfficerProfile manager = OfficerProfile.find(officerEvent.getManagerId());
		if (manager!=null){
			Iterator iter = OfficerProfile.findAll("managerId",manager.getLogonId());
			while (iter.hasNext())
			{
				OfficerProfile officerProfile = (OfficerProfile) iter.next();
				OfficerProfileResponseEvent officerProfileResponseEvent = PDOfficerProfileHelper.getThinOfficerProfileResponseEvent(officerProfile);
				dispatch.postEvent(officerProfileResponseEvent);
			}
		}
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
	}
}