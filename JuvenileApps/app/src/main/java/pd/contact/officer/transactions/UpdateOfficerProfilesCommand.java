//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\officer\\transactions\\UpdateOfficerProfileCommand.java

package pd.contact.officer.transactions;

import java.util.Iterator;
import pd.contact.officer.OfficerProfile;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.UpdateOfficerProfilesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * 
 * 
 * @author rcooper
 * @description update a managerId for officer profiles 
 *
 */

public class UpdateOfficerProfilesCommand implements ICommand
{

	/**
	 * @roseuid 42E67C27035C
	 */
	public UpdateOfficerProfilesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA602D8
	 */
	public void execute(IEvent event)
	{
		UpdateOfficerProfilesEvent officerProfilesEvent = (UpdateOfficerProfilesEvent) event;
		Iterator officerProfiles = (Iterator)officerProfilesEvent.getUpdateOfficerProfilesEvents().iterator();
		OfficerProfileResponseEvent officerProfileEvent = null;
		while (officerProfiles.hasNext()){
			officerProfileEvent = (OfficerProfileResponseEvent)officerProfiles.next();
		
			OfficerProfile officerProfile = null;
			String officerId = officerProfileEvent.getOfficerProfileId();

			if (officerId != null && !(officerId.equals("")))
			{
				officerProfile = OfficerProfile.find(officerId);
				if(officerProfile != null){
					officerProfile.setManagerId(officerProfilesEvent.getNewManagerId());
					officerProfile.setManagerFirstName(officerProfilesEvent.getNewManagerFirstName());
					officerProfile.setManagerMiddleName(officerProfilesEvent.getNewManagerMiddleName());
					officerProfile.setManagerLastName(officerProfilesEvent.getNewManagerLastName());
				}
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 42E65EA602E3
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA602E5
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42E65EA602E7
	 */
	public void update(Object anObject)
	{
	}
}