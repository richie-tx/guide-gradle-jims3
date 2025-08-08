package mojo.km.scheduling.transactions;

import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;
import mojo.km.transaction.Transactional;
import messaging.scheduling.CalculateNextDateEvent;
import mojo.km.scheduling.ISchedule;
import java.util.Date;
import mojo.km.dispatch.EventManager;
import messaging.scheduling.DisplayNextDateEvent;

/**
 * Responsible for implementing behavior of analysis method calculateNextDate of control class
 * mojo.km.scheduling.transactions.CalendarController
 * 
 * @author Design detail addin
 * @version 1.0
 */
public class CalculateNextDateCommand implements ICommand, Transactional
{
    /** Default constructor */
    public CalculateNextDateCommand()
    {
    }

    /**
     * It is executed when event is posted from requesting context.
     * 
     * @param event -
     *            houses data for command operation.
     * @exception thrown
     *                if error in application behavior
     */
    public void execute(IEvent event) throws Exception
    {
        CalculateNextDateEvent lEvent = (CalculateNextDateEvent) event;
        String className = mojo.naming.CalendarConstants.getSchedulerClassName(lEvent.getFrequency());
        ISchedule schedule = (ISchedule) Class.forName(className).newInstance();
        Date nextDate = schedule.getNextRunDate(lEvent.getInitialDate());
        DisplayNextDateEvent lRetEvent = new DisplayNextDateEvent();
        lRetEvent.setDate(nextDate);
        EventManager.getSharedInstance(EventManager.REPLY).postEvent(lRetEvent);
    }

    /**
     * Upon command registration with context this method will get executed
     * 
     * @param event -
     *            sample event data.
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * Upon command unregistration from context this method will get executed
     * 
     * @param event -
     *            sample event
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * If command requires data before execute is called context will place the in command
     * 
     * @param object -
     *            housing input data
     */
    public void update(Object object)
    {
    }
}
