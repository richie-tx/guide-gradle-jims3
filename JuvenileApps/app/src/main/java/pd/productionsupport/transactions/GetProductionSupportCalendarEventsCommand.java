package pd.productionsupport.transactions;

import java.util.Date;

import messaging.productionsupport.GetProductionSupportCalendarEventsEvent;
import messaging.productionsupport.reply.ProductionSupportCalendarResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.common.calendar.CalendarEvent;

public class GetProductionSupportCalendarEventsCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public GetProductionSupportCalendarEventsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
		GetProductionSupportCalendarEventsEvent calendarEvent = (GetProductionSupportCalendarEventsEvent) event;
		CalendarEvent serviceEvent = (CalendarEvent) CalendarEvent.find(calendarEvent.getCalendarEventId().toString());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		ProductionSupportCalendarResponseEvent calendarResponseEvent = this.getCalendarResponseEvent(serviceEvent);
		dispatch.postEvent(calendarResponseEvent);
    	
 
    }

    /**
     * @param event
     * @roseuid 4526B083011E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4526B083012B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4526B083012D
     */
    public void update(Object anObject)
    {

    }
    
    private ProductionSupportCalendarResponseEvent getCalendarResponseEvent(CalendarEvent serviceEvent){
    	ProductionSupportCalendarResponseEvent calendarEvent = new ProductionSupportCalendarResponseEvent();
    	
    	if(serviceEvent != null){
	    	calendarEvent.setCalendarEventId(new Integer(serviceEvent.getOID()));
	    	calendarEvent.setBodyText(serviceEvent.getBodyText());
	    	calendarEvent.setCalendarEventType(serviceEvent.getCalendarEventType());
	    	
	    	calendarEvent.setStartDatetime(serviceEvent.getStartDatetime());
	    	calendarEvent.setEndDatetime(serviceEvent.getEndDatetime());
	    	calendarEvent.setLocation(serviceEvent.getLocation());
	    	calendarEvent.setStatus(serviceEvent.getStatus());
	    	calendarEvent.setSubject(serviceEvent.getSubject());
	    	calendarEvent.setTimeZone(serviceEvent.getTimeZone());
	    	calendarEvent.setCalendarEventId(serviceEvent.getCalendarEventId());
	    	calendarEvent.setCalendarEventDate(serviceEvent.getCalendarEventDate());
	    	calendarEvent.setCalendarSeriesId(serviceEvent.getCalendarEventSeriesId());
	    	calendarEvent.setCreatedBy(serviceEvent.getCreatedBy());
			if(serviceEvent.getCreateUserID() != null){
				calendarEvent.setCreateUser(serviceEvent.getCreateUserID());
			}
			if(serviceEvent.getCreateTimestamp() != null){
				calendarEvent.setCreateDate(new Date(serviceEvent.getCreateTimestamp().getTime()));
			}
			if(serviceEvent.getUpdateUserID() != null){
				calendarEvent.setUpdateUser(serviceEvent.getUpdateUserID());
			}
			if(serviceEvent.getUpdateTimestamp() != null){
				calendarEvent.setUpdateDate(new Date(serviceEvent.getUpdateTimestamp().getTime()));
			}
			if(serviceEvent.getCreateJIMS2UserID() != null){
				calendarEvent.setCreateJims2User(serviceEvent.getCreateJIMS2UserID());
			}
			if(serviceEvent.getUpdateJIMS2UserID() != null){
				calendarEvent.setUpdateJims2User(serviceEvent.getUpdateJIMS2UserID());
			}
	    	
	    	return calendarEvent;
    	}else{
    		return calendarEvent;
    	}
    	
    }

}
