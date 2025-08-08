package pd.supervision.calendar.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.calendar.GetServiceLocationServiceEventsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import pd.common.calendar.CalendarManager;
import pd.common.calendar.CalendarRetriever;
import messaging.calendar.ICalendarAttribute;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import pd.supervision.calendar.PDSupervisionCalendarHelper;

public class GetServiceLocationServiceEventsCommand implements ICommand
{

    /**
     * @roseuid 451156810105
     */
    public GetServiceLocationServiceEventsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 45104B4B00C5
     */
    public void execute(IEvent event)
    {

        GetServiceLocationServiceEventsEvent calendarServicesEventsEvent = (GetServiceLocationServiceEventsEvent) event;

        CalendarRetriever retriever = getCalendarRetriever(calendarServicesEventsEvent.getRetriever());

        // Calendar Attributes
        ICalendarAttribute[] as = new ICalendarAttribute[calendarServicesEventsEvent.getCalendarAttributes().size()];
        calendarServicesEventsEvent.getCalendarAttributes().toArray(as);
        retriever.setCalendarAttributes(as);

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        CalendarManager cm = new CalendarManager();
        Iterator ret = (Iterator) cm.getCalendarEvents(retriever);        

        if (ret != null)
        {
            List calendarEvents = CollectionUtil.iteratorToList(ret);
            List responses = PDSupervisionCalendarHelper.getCalendarServiceEventResponseEvents(calendarEvents, "N/A");
            Iterator respIter = responses.iterator();
            while (respIter.hasNext())
            {
                CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent) respIter.next();
                dispatch.postEvent(resp);
            }
        }
    }

    /**
     * @param event
     * @roseuid 45104B4B00D3
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 45104B4B00D5
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 45104B4B00D7
     */
    public void update(Object anObject)
    {

    }

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
