package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import messaging.calendar.GetJuvenileAttendanceEvent;
import messaging.productionsupport.DeleteProductionSupportCalendarEventsEvent;
import messaging.productionsupport.reply.ProductionSupportCalendarResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.common.calendar.CalendarEvent;
import pd.common.calendar.CalendarEventContext;
import pd.supervision.calendar.ServiceEventAttendance;

public class DeleteProductionSupportCalendarEventsCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public DeleteProductionSupportCalendarEventsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	DeleteProductionSupportCalendarEventsEvent deleteCalendarEvent = (DeleteProductionSupportCalendarEventsEvent) event;
    	if (deleteCalendarEvent.getJuvenileId() != null ) {
    	    Iterator<CalendarEventContext> calendarEventContextIter = CalendarEventContext.findAll("calendarEventId", deleteCalendarEvent.getCalendarEventId());
    	    ArrayList<CalendarEventContext> calendarEventContextDeleteList = new ArrayList();
    	    while ( calendarEventContextIter.hasNext() ) {
    		CalendarEventContext context = (CalendarEventContext)calendarEventContextIter.next();
		if(context != null){
			if(context.getJuvenileId().equals(deleteCalendarEvent.getJuvenileId())){
				calendarEventContextDeleteList.add(context);
			}		
		}
    	    }
    	    // delete all JCCALEVENTCONT for a given juvenile_id and calevent_id
    	    for(CalendarEventContext deleteContext: calendarEventContextDeleteList){
    		if(deleteContext != null){
    		    deleteContext.delete();
    		    new Home().bind(deleteContext);
    		}
    	    }
    	    
    	    GetJuvenileAttendanceEvent getServiceAttendRecordEvent = new GetJuvenileAttendanceEvent();
    	    getServiceAttendRecordEvent.setServiceEventId(deleteCalendarEvent.getServiceEventId());
    	    getServiceAttendRecordEvent.setJuvenileId(deleteCalendarEvent.getJuvenileId());
    	    Iterator<ServiceEventAttendance> serviceEventAttendIter = ServiceEventAttendance.findAll(getServiceAttendRecordEvent);
    	    
    	    // delete all CSSERVATTEND found for a given juvenilie_id and servevent_id
    	    if(serviceEventAttendIter != null && serviceEventAttendIter.hasNext()){
		while(serviceEventAttendIter.hasNext()){
			ServiceEventAttendance serviceEventAttend = (ServiceEventAttendance)serviceEventAttendIter.next();
			if(serviceEventAttend != null){
			    serviceEventAttend.delete();
			    new Home().bind(serviceEventAttend);
			}
		}		
    	    }
    	} else {
    	    CalendarEvent calendarEvent = (CalendarEvent) CalendarEvent.find(deleteCalendarEvent.getCalendarEventId().toString());
    	    if(calendarEvent != null){
		calendarEvent.delete();
    	    }
    	}
		
 
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
    	
    }

}
