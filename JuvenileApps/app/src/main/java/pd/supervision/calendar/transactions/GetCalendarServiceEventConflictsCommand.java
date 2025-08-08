package pd.supervision.calendar.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import naming.PDCalendarConstants;

import messaging.calendar.GetCalendarServiceEventConflictsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import pd.common.calendar.CalendarManager;
import pd.common.calendar.CalendarRetriever;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import pd.supervision.calendar.CalendarEventBuilder;
import pd.supervision.calendar.ServiceEventContext;

public class GetCalendarServiceEventConflictsCommand implements ICommand
{

    /**
     * @roseuid 44805C58019A
     */
    public GetCalendarServiceEventConflictsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 447F49B4034B
     */
    public void execute(IEvent event)
    {
        GetCalendarServiceEventConflictsEvent calendarServicesEventConflictsEvent = (GetCalendarServiceEventConflictsEvent) event;

        CalendarRetriever retriever = getCalendarRetriever(calendarServicesEventConflictsEvent.getRetriever());

        Date tempDate = calendarServicesEventConflictsEvent.getStartDatetime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(tempDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        tempDate = cal.getTime();
        retriever.setStartDatetime(tempDate);

        tempDate = calendarServicesEventConflictsEvent.getEndDatetime();
        cal.setTime(tempDate);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        tempDate = cal.getTime();
        retriever.setEndDatetime(tempDate);

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        CalendarManager cm = new CalendarManager();
        Object ret = cm.getCalendarEvents(retriever);
        Iterator i = (Iterator) ret;

        List calendarEvents = CollectionUtil.iteratorToList(i);

        List serviceEvents = new ArrayList();
        int len = calendarEvents.size();
        for(int j = 0; j < len; j++)
        {
            ServiceEventContext e = (ServiceEventContext) calendarEvents.get(j);
            
            if( e.getEventStatusId() != null )
            {
                if ((!e.getEventStatusId().equals(PDCalendarConstants.SERVICE_EVENT_STATUS_CANCELLED))  &&
                		(e.getJuvLocUnitId() == calendarServicesEventConflictsEvent.getServiceLocationId() ||
                	     e.getInstructorId() == calendarServicesEventConflictsEvent.getInstructorId()))
                {
                    serviceEvents.add(e);
                }
            }
        }

        if (serviceEvents.size() > 0)
        {
            //List responses = PDSupervisionCalendarHelper.getCalendarServiceEventResponseEvents(serviceEvents, "N/A");
            
            CalendarEventBuilder builder = new CalendarEventBuilder();
            builder.setCalDetailLevel("N/A");

            builder.setServiceEventContexts(serviceEvents);
            builder.build();
            List responses = (List) builder.getResult();
            
            len = responses.size();
            for(int j = 0;j < len; j++)
            {
                CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent) responses.get(j);
                dispatch.postEvent(resp);
            }
        }

    }

    /**
     * @param event
     * @roseuid 447F49B4034D
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 447F49B4035A
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 447F49B4035C
     */
    public void update(Object anObject)
    {

    }

    /**
     * Instantiate the Calendar Retriever. The class must implement the CalendarRetriever abstract
     * class.
     * 
     * @param retriever
     * @return ICalendarRetriever
     */
    private CalendarRetriever getCalendarRetriever(String retriever)
    {
        try
        {

            Class c = Class.forName(retriever);
            CalendarRetriever retrieverClass = (CalendarRetriever) c.newInstance();

            return retrieverClass;

        }
        catch (ClassNotFoundException cnfe)
        {
            throw new RuntimeException("[GetCalendarEventsCommand] Retrieve Class Not Found: " + retriever);
        }
        catch (InstantiationException ie)
        {
            throw new RuntimeException("[GetCalendarEventsCommand] Instantiation Exception: " + retriever);
        }
        catch (IllegalAccessException iae)
        {
            throw new RuntimeException("[GetCalendarEventsCommand] Illegal Access Exception: " + retriever);
        }
        catch (Exception e)
        {
            throw new RuntimeException("[GetCalendarEventsCommand] Exception creating the Calendar Retriever: "
                    + retriever);
        }
    }
}