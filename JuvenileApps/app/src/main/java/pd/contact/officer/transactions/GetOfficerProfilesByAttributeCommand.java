
package pd.contact.officer.transactions;

import java.util.Iterator;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import pd.codetable.PDCodeHelper;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;

public class GetOfficerProfilesByAttributeCommand implements ICommand
{

	public GetOfficerProfilesByAttributeCommand()
	{

	}

	public void execute(IEvent event)
	{
		GetOfficerProfilesByAttributeEvent officerEvent = (GetOfficerProfilesByAttributeEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator<OfficerProfile> officerprofiles = OfficerProfile.findAll(officerEvent.getAttributeName(), officerEvent.getAttributeValue());
		if (officerprofiles != null)
		{
			while(officerprofiles.hasNext()){
				OfficerProfile officerProfile = (OfficerProfile)officerprofiles.next();
				OfficerProfileResponseEvent officerProfileResponseEvent = PDOfficerProfileHelper.getBasicOfficerProfileResponseEvent(officerProfile);
				dispatch.postEvent(officerProfileResponseEvent);
			}
		}
	}

	public void onRegister(IEvent event)
	{

	}

	public void onUnregister(IEvent event)
	{

	}

	public void update(Object anObject)
	{

	}
}