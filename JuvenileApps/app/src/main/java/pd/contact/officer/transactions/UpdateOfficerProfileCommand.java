//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\officer\\transactions\\UpdateOfficerProfileCommand.java

package pd.contact.officer.transactions;

import java.util.Collection;
import java.util.Iterator;
import naming.LogonControllerServiceNames;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.OfficerProfileAddress;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.contact.officer.WorkDay;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;
import messaging.authentication.GetJIMS2AccountByOfficerIdEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.UpdateOfficerProfileEvent;
import messaging.officer.WorkDayRequestEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * 
 * 
 * @author mchowdhury
 * @description update or create an officer profile 
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class UpdateOfficerProfileCommand implements ICommand
{

	/**
	 * @roseuid 42E67C27035C
	 */
	public UpdateOfficerProfileCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA602D8
	 */
	public void execute(IEvent event)
	{
		UpdateOfficerProfileEvent officerProfileEvent = (UpdateOfficerProfileEvent) event;
		OfficerProfile officerProfile = null;
		String officerId = officerProfileEvent.getOfficerProfileId();

		if (officerId != null && !(officerId.equals("")))
		{
			officerProfile = OfficerProfile.find(officerId);
			if(officerProfile != null){
				officerProfile.setOfficerProfile(officerProfileEvent);

				this.processWorkDays(officerProfileEvent, officerProfile);
				this.processWorkAddress(officerProfileEvent, officerProfile);
				String officerOid = officerProfile.getOID().toString();
				
				
				//Get JIMS2AccountByOfficerIdEvent
				
				GetJIMS2AccountByOfficerIdEvent reqEvent =
					(GetJIMS2AccountByOfficerIdEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNTBYOFFICERID);
				reqEvent.setOfficerId(officerOid);
				Iterator jims2AccountTypes = JIMS2AccountType.findAll(reqEvent);

				while (jims2AccountTypes.hasNext()) {
					JIMS2AccountType jims2AccountType = (JIMS2AccountType) jims2AccountTypes.next();
					if (jims2AccountType != null) {
						String jims2AccountOid = jims2AccountType.getJIMS2AccountId();
						JIMS2Account jims2Account = JIMS2Account.find(jims2AccountOid);
						if (jims2Account != null) {
							jims2Account.setLastName(officerProfileEvent.getLastName());
							jims2Account.setFirstName(officerProfileEvent.getFirstName());
							jims2Account.setDepartmentId(officerProfileEvent.getDepartmentId());
							jims2Account.setMiddleName(officerProfileEvent.getMiddleName());
						}
					}
				}
			}
		}
		else
		{
			officerProfile = new OfficerProfile();
			officerProfile.setOfficerProfile(officerProfileEvent);
			IHome home = new Home();
			home.bind(officerProfile);
			if (officerProfileEvent.getStreetName() != null && !(officerProfileEvent.getStreetName().equals("")))
			{
				this.createWorkAddress(officerProfileEvent, officerProfile);
			}
			this.createWorkDays(officerProfileEvent, officerProfile);
		}
		//The response event is needed when an officer is created for a Juvenile Warrant
		OfficerProfileResponseEvent replyEvent =
			PDOfficerProfileHelper.getThinOfficerProfileResponseEvent(officerProfile);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(replyEvent);

	}

	/**
	 * @param officerProfileEvent
	 * @param officerProfile
	 */
	private void createWorkDays(UpdateOfficerProfileEvent officerProfileEvent, OfficerProfile officerProfile)
	{
		Collection workDayRequestEvents = officerProfileEvent.getWorkDayRequestEvents();
		if (workDayRequestEvents != null && workDayRequestEvents.size() > 0)
		{
			Iterator iter = workDayRequestEvents.iterator();
			WorkDay workDay = null;

			while (iter.hasNext())
			{
				WorkDayRequestEvent workDayRequestEvent = (WorkDayRequestEvent) iter.next();
				workDay = new WorkDay();
				workDay = PDOfficerProfileHelper.setWorkDay(workDay, workDayRequestEvent);
				workDay.setOfficerProfileId(officerProfile.getOID().toString());
				officerProfile.insertWorkDays(workDay);
			}
		}
	}

	/**
	 * @param officerProfileEvent
	 * @param officerProfile
	 */
	private void createWorkAddress(UpdateOfficerProfileEvent officerProfileEvent, OfficerProfile officerProfile)
	{

		OfficerProfileAddress workAddress = new OfficerProfileAddress();
		PDOfficerProfileHelper.setOfficerWorkAddress(workAddress, officerProfileEvent);
		workAddress.setOfficerProfileId(officerProfile.getOID().toString());
		officerProfile.insertAddresses(workAddress);

	}

	private void processWorkDays(UpdateOfficerProfileEvent officerProfileEvent, OfficerProfile officerProfile)
	{
		Collection workDayRequestEvents = officerProfileEvent.getWorkDayRequestEvents();
		if (workDayRequestEvents != null && workDayRequestEvents.size() > 0)
		{
			Iterator iter = workDayRequestEvents.iterator();
			WorkDay workDay = null;
			while (iter.hasNext())
			{
				WorkDayRequestEvent workDayRequestEvent = (WorkDayRequestEvent) iter.next();
				if (workDayRequestEvent.getWorkScheduleId() != null
					&& !workDayRequestEvent.getWorkScheduleId().equals(""))
				{
					workDay = WorkDay.find(workDayRequestEvent.getWorkScheduleId());
				}
				else
				{
					workDay = new WorkDay();
					workDay.setOfficerProfileId(officerProfile.getOID().toString());
				}
				workDay = PDOfficerProfileHelper.setWorkDay(workDay, workDayRequestEvent);
				officerProfile.insertWorkDays(workDay);
			}
		}
		else
		{
			Iterator existingWorkDays = officerProfile.getWorkDays().iterator();
			while (existingWorkDays.hasNext())
			{
				WorkDay existingWorkDay = (WorkDay) existingWorkDays.next();
				existingWorkDay.delete();
			}
		}
	}
	
	/**
	 * @param officerProfileEvent
	 * @param officerProfile
	 */
	private void processWorkAddress(UpdateOfficerProfileEvent officerProfileEvent, OfficerProfile officerProfile)
	{
		OfficerProfileAddress workAddress = null;
		String workAddressId = null;
		Iterator addresses = officerProfile.getAddresses().iterator();
		while (addresses.hasNext())
		{
			workAddress = (OfficerProfileAddress) addresses.next();
		}
		if (workAddress != null)
		{
			workAddressId = workAddress.getAddressId();
		}
		if (workAddressId != null && !(workAddressId.equals("")))
		{
			if (officerProfileEvent.getStreetName() != null && !(officerProfileEvent.getStreetName().equals("")))
			{
				PDOfficerProfileHelper.setOfficerWorkAddress(workAddress, officerProfileEvent);
				officerProfile.insertAddresses(workAddress);
			}
			else
			{
				workAddress.delete();
			}
		}
		else
		{
			this.createWorkAddress(officerProfileEvent, officerProfile);
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