package pd.contact.officer.transactions;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.SearchOfficerProfilesByNameEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.officer.OfficerProfile;

import java.util.*;
public class SearchOfficerProfilesByNameCommand implements ICommand
{
    public void execute(IEvent event) throws Exception{
	 IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	 SearchOfficerProfilesByNameEvent searchEvent = ( SearchOfficerProfilesByNameEvent)event;
   	 Iterator profileIter = OfficerProfile.findAll(searchEvent);
   	 while ( profileIter.hasNext()){
   	     OfficerProfile officerProfile = (OfficerProfile)profileIter.next();
   	     OfficerProfileResponseEvent officerProfileResponseEvent = new OfficerProfileResponseEvent();
   	     officerProfileResponseEvent.setFirstName(officerProfile.getFirstName());
   	     officerProfileResponseEvent.setMiddleName(officerProfile.getMiddleName());
   	     officerProfileResponseEvent.setLastName(officerProfile.getLastName());
   	     officerProfileResponseEvent.setDepartmentId(officerProfile.getDepartmentId());
   	     officerProfileResponseEvent.setUserId(officerProfile.getLogonId());
   	     dispatch.postEvent(officerProfileResponseEvent);
   	 }

    }
	 
}



