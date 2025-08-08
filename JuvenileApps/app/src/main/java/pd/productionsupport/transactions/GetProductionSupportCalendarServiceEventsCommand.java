package pd.productionsupport.transactions;

import java.util.Date;

import messaging.productionsupport.GetProductionSupportCalendarServiceEventsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import pd.common.calendar.CalendarEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.calendar.ServiceEvent;

public class GetProductionSupportCalendarServiceEventsCommand implements ICommand
{
	/**
	 * @roseuid 45F1B0D902AD
	 */
	public GetProductionSupportCalendarServiceEventsCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 45F080FA035F
	 */
	public void execute(IEvent event)
	{
		GetProductionSupportCalendarServiceEventsEvent calendarServiceEvent = (GetProductionSupportCalendarServiceEventsEvent) event;
		ServiceEvent serviceEvent = (ServiceEvent) ServiceEvent.find(calendarServiceEvent.getServiceEventId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if(serviceEvent != null){
			CalendarServiceEventResponseEvent respEvent = this.getCalendarResponseEvent(serviceEvent);	
			dispatch.postEvent(respEvent);
		}
		
	}
	
	/**
	 * 
	 * @param serviceEvent
	 * @return
	 */
	public CalendarServiceEventResponseEvent getCalendarResponseEvent(ServiceEvent serviceEvent) {
		CalendarServiceEventResponseEvent servResp = new CalendarServiceEventResponseEvent();
		
		servResp.setServiceEventId(serviceEvent.getOID());
		servResp.setCalendarEventId(serviceEvent.getCalendarEventId());
		// get CalendarEvent relating 1-1 with service event
		if(serviceEvent.getCalendarEventId() != null){
			CalendarEvent calendarEvent = (CalendarEvent) CalendarEvent.find(serviceEvent.getCalendarEventId().toString());
			servResp.setStartDatetime(calendarEvent.getStartDatetime());
			servResp.setEndDatetime(calendarEvent.getEndDatetime());
		}
		servResp.setMaxAttendance(Integer.toString(serviceEvent.getEventMaximum()));
		servResp.setMinAttendance(Integer.toString(serviceEvent.getEventMinimum()));
		servResp.setEventStatusCode(serviceEvent.getEventStatusId());
		servResp.setEventTypeCode(serviceEvent.getEventTypeId());
		if(serviceEvent.getInstructorId() != null){
			servResp.setInstructorId(new Integer(serviceEvent.getInstructorId()));
		}
		servResp.setJuvUnitCd(serviceEvent.getJuvLocUnitId());
		if(serviceEvent.getJuvLocUnit() != null){
			servResp.setJuvUnitName(serviceEvent.getJuvLocUnit().getLocationUnitName());
		}
		if(serviceEvent.getServiceId() != null){
			servResp.setServiceId(serviceEvent.getServiceId());
		}else{
			servResp.setServiceId("0");
		}
		servResp.setEventComments(serviceEvent.getEventComments());
		servResp.setSchoolCd(serviceEvent.getSchoolCd());
		servResp.setInterviewId(serviceEvent.getInterviewId());
		if(serviceEvent.getMemberAddressId() != null){
			servResp.setMemberAddressId(new Integer(serviceEvent.getMemberAddressId()));
		}		
		servResp.setCurrentEnrollment(Integer.toString(serviceEvent.getCurrentEnrollment()));
		servResp.setFacilityCd(serviceEvent.getFacilityCd());
		if(serviceEvent.getMemberEmploymentId() != null){
			servResp.setMemberEmploymentId(new Integer(serviceEvent.getMemberEmploymentId()));
		}
		servResp.setContactFirstName(serviceEvent.getContactFirstName());
		servResp.setContactLastName(serviceEvent.getContactLastName());
		servResp.setSexOffenderRegistrantStr(serviceEvent.getSexOffender());
		servResp.setRestrictionsOther(serviceEvent.getRestrictOther());

		if(serviceEvent.getCreateUserID() != null){
			servResp.setCreateUser(serviceEvent.getCreateUserID());
		}
		if(serviceEvent.getCreateTimestamp() != null){
			servResp.setCreateDate(new Date(serviceEvent.getCreateTimestamp().getTime()));
		}
		if(serviceEvent.getUpdateUserID() != null){
			servResp.setUpdateUser(serviceEvent.getUpdateUserID());
		}
		if(serviceEvent.getUpdateTimestamp() != null){
			servResp.setUpdateDate(new Date(serviceEvent.getUpdateTimestamp().getTime()));
		}
		if(serviceEvent.getCreateJIMS2UserID() != null){
			servResp.setCreateJims2User(serviceEvent.getCreateJIMS2UserID());
		}
		if(serviceEvent.getUpdateJIMS2UserID() != null){
			servResp.setUpdateJims2User(serviceEvent.getUpdateJIMS2UserID());
		}
		
		return servResp;
		
	}

	/**
	 * @param event
	 * @roseuid 45F080FA036D
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @param event
	 * @roseuid 45F080FA036F
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * @param anObject
	 * @roseuid 45F080FA037C
	 */
	public void update(Object anObject)
	{
	}
}
