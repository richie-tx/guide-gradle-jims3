package pd.contact.officer.transactions;

import java.util.Iterator;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfileByLogonIdEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;

public class GetOfficerProfileByLogonIdCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetOfficerProfileByLogonIdEvent profileEvt = (GetOfficerProfileByLogonIdEvent) event;
	Iterator<OfficerProfile> officerprofiles = OfficerProfile.findAll("logonId", profileEvt.getUserId());
	if (officerprofiles != null)
	{
	    OfficerProfileResponseEvent officerProfileResponseEvent = null;
	    while (officerprofiles.hasNext())
	    {
		OfficerProfile officerProfile = (OfficerProfile) officerprofiles.next();
		if (!officerProfile.getStatusId().equals("I") && !officerProfile.getStatusId().equals("INACTIVE"))
		{		   
		    officerProfileResponseEvent = PDOfficerProfileHelper.getBasicOfficerProfileResponseEvent(officerProfile);
		    dispatch.postEvent(officerProfileResponseEvent);
		}
	    }
	}

    }

}
