/*
 * Created on Jan 13, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.contact.officer.transactions;

import java.util.Iterator;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfileByIdEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetOfficerProfileByIdCommand implements ICommand
{
	/**
	 * @roseuid 42E67C2501F4
	 */
	public GetOfficerProfileByIdCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA70375
	 */
	public void execute(IEvent event)
	{
		GetOfficerProfileByIdEvent officerEvent = (GetOfficerProfileByIdEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator iter = OfficerProfile.findAll(officerEvent);
		while (iter.hasNext())
		{
			OfficerProfile officerProfile = (OfficerProfile) iter.next();
			OfficerProfileResponseEvent officerProfileResponseEvent = PDOfficerProfileHelper.getOfficerProfileResponseEvent(officerProfile);
			dispatch.postEvent(officerProfileResponseEvent);
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