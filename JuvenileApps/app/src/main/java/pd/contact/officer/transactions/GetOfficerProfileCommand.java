//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\officer\\transactions\\GetOfficerProfileCommand.java

package pd.contact.officer.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.officer.reply.WorkDayResponseEvent;
import messaging.officer.GetOfficerProfileEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.contact.officer.WorkDay;

/**
 * @author mchowdhury
 * @description Get Officer profile 
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class GetOfficerProfileCommand implements ICommand
{
	/**
	 * @roseuid 42E67C2501F4
	 */
	public GetOfficerProfileCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA70375
	 */
	public void execute(IEvent event)
	{
		GetOfficerProfileEvent officerEvent = (GetOfficerProfileEvent) event;
		OfficerProfile officer = OfficerProfile.find(officerEvent.getOfficerProfileId());
		OfficerProfileResponseEvent officerProfileResponseEvent = PDOfficerProfileHelper.getOfficerProfileResponseEvent(officer);
		
		Collection workDays = officer.getWorkDays();
		Collection workDayResponseEvents = this.convertWorkDayEntityCollectionToResponseEventCollection(workDays);
		officerProfileResponseEvent.setWorkSchedules(workDayResponseEvents);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(officerProfileResponseEvent);
	}

	/**
	 * @param workDays
	 * @return collection of workday Response event
	 */
	private Collection convertWorkDayEntityCollectionToResponseEventCollection(Collection workDays)
	{
		Collection workDayResponseEvents = new ArrayList();
		Iterator iter = workDays.iterator();
		WorkDay workDay = null;
		while (iter.hasNext())
		{
			workDay = (WorkDay) iter.next();
			WorkDayResponseEvent workDayResponseEvent = PDOfficerProfileHelper.getWorkDayResponseEvent(workDay);
			workDayResponseEvents.add(workDayResponseEvent);
		}
		return workDayResponseEvents;
	}

	/**
	 * @param event
	 * @roseuid 42E65EA70377
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA70381
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42E65EA70383
	 */
	public void update(Object anObject)
	{

	}
}