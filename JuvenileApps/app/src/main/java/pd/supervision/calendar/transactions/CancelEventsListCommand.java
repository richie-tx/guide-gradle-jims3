package pd.supervision.calendar.transactions;

import java.util.Iterator;
import messaging.calendar.CancelEventsListEvent;
import messaging.calendar.reply.ServiceEventCancellationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCalendarConstants;
import naming.UIConstants;
import pd.contact.officer.OfficerProfile;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;
import pd.km.util.Name;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventAttendance;
import pd.supervision.programreferral.JuvenileEventReferral;
import pd.supervision.programreferral.JuvenileProgramReferral;

/**
 * @author cc_vnarsingoju
 * 
 */
public class CancelEventsListCommand implements ICommand
{
	/*
	 * (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		CancelEventsListEvent listEvent = (CancelEventsListEvent)event;
		
		if( listEvent.getSearchType().equalsIgnoreCase( PDCalendarConstants.FUTURE_EVENTS_JUVENILE ) )
		{
			cancelJuvenileEvents(listEvent);
		}
		else if( listEvent.getSearchType().equalsIgnoreCase( PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER ) )
		{
			cancelServiceProviderEvents(listEvent);
		}
	}

	/*
	 * 
	 */
	private void cancelJuvenileEvents(CancelEventsListEvent listEvent) 
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		String eventId = listEvent.getServiceEventId();

		if( notNullNotEmptyString( eventId ) )
		{
			//Cancel ServiceEvent if not a pre-scheduled event
			if (!listEvent.getEventTypeCategory().equals(UIConstants.PRESCHEDULED_SERVICE_TYPE)) 
			{
				ServiceEvent serv = (ServiceEvent)ServiceEvent.find(eventId);
				serv.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_CANCELLED);
			}
				
			String attendStatus = "" ;
			Iterator<ServiceEventAttendance> attenIter = 
					ServiceEventAttendance.findAll("serviceEventId",	eventId);
			while( attenIter.hasNext() )
			{ // iterate all Juv's slated for this event ...
				ServiceEventAttendance eventAttendance = attenIter.next();
				attendStatus = eventAttendance.getAttendanceStatusCd().trim();
				
				if( (attendStatus.equals( PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED) ||
						attendStatus.equals( PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED) ) &&
						listEvent.getJuvenileId().equals(eventAttendance.getJuvenile().getJuvenileNum()) )
				{ // ... set attendance as excused
					ServiceEventCancellationResponseEvent respEvent = new ServiceEventCancellationResponseEvent();
					// Profile stripping fix - task 97643
					JuvenileCore juv = eventAttendance.getJuvenile();
					//
					Name name = new Name(juv.getFirstName(), juv.getMiddleName(), juv.getLastName());
					respEvent.setJuvenileName(name.getFormattedName());
					respEvent.setJuvenileId(juv.getJuvenileNum());
					respEvent.setOfficerLogonId(getOfficer(eventId, juv.getJuvenileNum()));
					eventAttendance.setAttendanceStatusCd(PDCalendarConstants.JUV_ATTEND_STATUS_CANCELLED);
					
					ServiceEvent serv = (ServiceEvent)ServiceEvent.find(eventId);
					int currEnroll = serv.getCurrentEnrollment();
					if( currEnroll != 0 ) 
					{
						serv.setCurrentEnrollment( (currEnroll < 1) ? 0 : (currEnroll -1) );
					}
					if (serv.getEventStatusId().equals(PDCalendarConstants.SERVICE_EVENT_STATUS_SCHEDULED))
					{
						serv.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_AVAILABLE);
					}
										
					dispatch.postEvent(respEvent);
				}
			} // while
		}
	}
	
	private void cancelServiceProviderEvents (CancelEventsListEvent listEvent) 
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		String eventId = listEvent.getServiceEventId();

		if( notNullNotEmptyString( eventId ) )
		{
			//Cancel Service Provider Event
			ServiceEvent serv = (ServiceEvent)ServiceEvent.find(eventId);
			serv.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_CANCELLED);

			String attendStatus = "" ;
			Iterator<ServiceEventAttendance> attenIter = 
					ServiceEventAttendance.findAll("serviceEventId",eventId);
			while( attenIter.hasNext() )
			{ // iterate all Juv's slated for this event ...
				ServiceEventAttendance eventAttendance = attenIter.next();
				attendStatus = eventAttendance.getAttendanceStatusCd().trim();
				
				if( attendStatus.equals( PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED) ||
						attendStatus.equals( PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED) )
				{ // ... set attendance as excused
					ServiceEventCancellationResponseEvent respEvent = new ServiceEventCancellationResponseEvent();
					// Profile stripping fix - task 97643
					JuvenileCore juv = eventAttendance.getJuvenile();
					//
					Name name = new Name(juv.getFirstName(), juv.getMiddleName(), juv.getLastName());
					respEvent.setJuvenileName(name.getFormattedName());
					respEvent.setJuvenileId(juv.getJuvenileNum());
					respEvent.setOfficerLogonId(getOfficer(eventId, juv.getJuvenileNum()));
					eventAttendance.setAttendanceStatusCd(PDCalendarConstants.JUV_ATTEND_STATUS_CANCELLED);
					dispatch.postEvent(respEvent);
				}
			} // while
		}
	}

	/*
	 * 
	 */
	private String getOfficer(String serviceEventId, String juvenileNum)
	{
		String officerId = "";

		Iterator<JuvenileEventReferral> iter = 
				JuvenileEventReferral.findAll("serviceEventId", serviceEventId);
		while( iter.hasNext() )
		{
			JuvenileEventReferral servEvent = iter.next();
			String progRefId = servEvent.getProgramReferralId();
			if( notNullNotEmptyString( progRefId ) )
			{
				JuvenileProgramReferral progRef = JuvenileProgramReferral.find(progRefId);
				if( !progRef.getReferralStatusCd().equalsIgnoreCase("CL") )
				{
					String juvId = progRef.getJuvenileId();
					if( notNullNotEmptyString(juvId) && juvId.equalsIgnoreCase(juvenileNum) )
					{
						officerId = getOfficerLogonId(progRef.getCasefileId());
						break ;
					}
				}
			}
		} // while

		return officerId;
	}

	/*
	 * 
	 */
	private String getOfficerLogonId(String casefileId)
	{
		String officerProfileId = "";
		String officerLogonId = "";
		JuvenileCasefile caseFile = JuvenileCasefile.find(casefileId);

		officerProfileId = (caseFile != null && 
				notNullNotEmptyString(caseFile.getProbationOfficerId())) ? 
				caseFile.getProbationOfficerId() : "" ;

		OfficerProfile officerProfile = (notNullNotEmptyString(officerProfileId)) ? 
				OfficerProfile.find(officerProfileId) : null;

		officerLogonId = (officerProfile != null) ? officerProfile.getLogonId() : "" ;

		return officerLogonId;
	}

	/*
	 * 
	 */
	public void onRegister(IEvent event)
	{
	}

	/*
	 * 
	 */
	public void onUnregister(IEvent event)
	{
	}

	/*
	 * 
	 */
	public void update(Object updateObject)
	{
	}

	/*
	 * 
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.trim().length() > 0) ) ; 
	}
}
