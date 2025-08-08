package mojo.km.scheduling.transactions;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.context.ICommand;
import mojo.km.transaction.Transactional;
//import mojo.km.persistence.IExpression;
import mojo.km.persistence.IHome;
//import mojo.km.persistence.Expression;
import messaging.scheduling.UnregisterTaskEvent;
import java.util.Iterator;
import mojo.km.scheduling.Task;

/**
 * Responsible for implementing behavior of analysis method unregisterTask of control class
 * pd.calendar.calendartransactions.CalendarController
 * 
 * @author Design detail addin
 * @version 1.0
 */
public class UnregisterTaskCommand implements ICommand, Transactional
{
    /** Default constructor */
    public UnregisterTaskCommand()
    {
    }

    /**
     * Provides behavior for application requirements. It is executed when event is posted from
     * requesting context.
     * 
     * @param event -
     *            houses data for command operation.
     * @exception thrown
     *                if error in application behavior
     */
    public void execute(IEvent event) throws Exception
    {
        UnregisterTaskEvent lEvent = (UnregisterTaskEvent) event;

        IHome home = new Home();

        Iterator i = home.findAll("taskName", lEvent.getTaskName(), Task.class);
        if (i.hasNext())
        {
            Task task = (Task) i.next();
            task.unregister();
        }
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
