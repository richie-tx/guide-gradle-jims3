package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportCalendarEventContextEvent;
import messaging.productionsupport.UpdateProductionSupportCalendarEventsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.common.calendar.CalendarEvent;
import pd.common.calendar.CalendarEventContext;
import pd.supervision.calendar.ServiceEvent;

public class UpdateProductionSupportCalendarEventsCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
	public UpdateProductionSupportCalendarEventsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("updateProductionSupportCalendarEvents");
    	UpdateProductionSupportCalendarEventsEvent updateCalendarEvent = (UpdateProductionSupportCalendarEventsEvent) event;
    	CalendarEvent calendarEvent = (CalendarEvent) CalendarEvent.find(updateCalendarEvent.getCalendarEventId());
    	if(updateCalendarEvent.getBeginDate() != null){
    		calendarEvent.setStartDatetime(updateCalendarEvent.getBeginDate());
    	}
    	if(updateCalendarEvent.getEndDate() != null){
    		calendarEvent.setEndDatetime(updateCalendarEvent.getEndDate());
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

}
