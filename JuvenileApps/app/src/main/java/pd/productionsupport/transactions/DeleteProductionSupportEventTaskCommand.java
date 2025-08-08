package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import naming.ProductionSupportControllerServiceNames;

import messaging.productionsupport.DeleteProductionSupportCasefileEvent;
import messaging.productionsupport.DeleteProductionSupportEventTaskEvent;
import messaging.productionsupport.GetProductionSupportEventTasksEvent;
import messaging.productionsupport.reply.ProductionSupportCalendarResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import pd.common.calendar.CalendarEvent;
import pd.productionsupport.EventTask;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;

public class DeleteProductionSupportEventTaskCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public DeleteProductionSupportEventTaskCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("DeleteProductionSupportEventTaskEvent");
    	DeleteProductionSupportEventTaskEvent deleteEventTaskEvent = (DeleteProductionSupportEventTaskEvent) event;
		GetProductionSupportEventTasksEvent getEventTasksEvent =  new GetProductionSupportEventTasksEvent();
    	
    	getEventTasksEvent.setCasefileId(deleteEventTaskEvent.getCasefileId());
		Iterator eventTasksIter = EventTask.findAll(getEventTasksEvent);
		 if(deleteEventTaskEvent.getCasefileId() != null && !(deleteEventTaskEvent.getCasefileId().equals(""))) {
			   while(eventTasksIter.hasNext()){
				   EventTask eventTask = (EventTask)eventTasksIter.next();		
				   eventTask.delete();
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
