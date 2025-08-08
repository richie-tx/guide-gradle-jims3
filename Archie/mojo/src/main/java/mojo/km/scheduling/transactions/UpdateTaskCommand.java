package mojo.km.scheduling.transactions;

import java.util.Iterator;
import messaging.scheduling.RegisterTaskEvent;
import messaging.scheduling.UpdateTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
//import mojo.km.persistence.Expression;
import mojo.km.persistence.Home;
//import mojo.km.persistence.IExpression;
import mojo.km.persistence.IHome;
import mojo.km.transaction.Transactional;
import mojo.km.scheduling.Task;

/**
 * Responsible for implementing behavior of analysis method updateTask of control class
 * pd.calendar.calendartransactions.CalendarController
 * 
 * @author Design detail addin
 * @version 1.0
 */
public class UpdateTaskCommand implements ICommand, Transactional
{
    /** Default constructor */
    public UpdateTaskCommand()
    {
    }

    /**
     * Provides behavior for application requirements. It is executed when event is posted from requesting context.
     * 
     * @param event -
     *            houses data for command operation.
     * @exception thrown
     *                if error in application behavior
     */
    public void execute(IEvent anEvent) throws Exception
    {

        UpdateTaskEvent event = (UpdateTaskEvent) anEvent;
        RegisterTaskEvent registerTaskEvent = null;
        IHome home = new Home();
        if (event.getTaskName() == null)
        {
            throw new Exception("A Task Name must be provided for the update.");
        }

        Iterator i = home.findAll("taskName", event.getTaskName(), Task.class);

        if (i.hasNext())
        {
            Task lTask = (Task) i.next();
            copyProperties(event, lTask);
        }
        else if (event.getAutoCreate())
        {
            registerTask(event);
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

    /**
     * copies properties to Object
     * 
     * @param utEvent
     *            <code>UpdateTaskEvent</code>
     * @param aTask
     *            <code>Task</code>
     */
    private void copyProperties(UpdateTaskEvent utEvent, Task aTask)
    {
        if (utEvent.getFirstNotificationDate() != null)
        {
            aTask.setFirstNotificationDate(utEvent.getFirstNotificationDate());
        }
        if (utEvent.getNextNotificationDate() != null)
        {
            aTask.setNextNotificationDate(utEvent.getNextNotificationDate());
        }
        if (utEvent.getNotificationEvent() != null)
        {
            aTask.setNotificationEvent(utEvent.getNotificationEvent());
        }
        if ((utEvent.getScheduleClassName() != null) && !(utEvent.getScheduleClassName().trim().equals("")))
        {
            aTask.setScheduleClassName(utEvent.getScheduleClassName());
        }
    }

    /**
     * copies properties to Object
     * 
     * @param utEvent
     *            <code>UpdateTaskEvent</code>
     * @param rtEvent
     *            <code>RegisterTaskEvent</code>
     */
    private void copyProperties(UpdateTaskEvent utEvent, RegisterTaskEvent rtEvent)
    {
        rtEvent.setFirstNotificationDate(utEvent.getFirstNotificationDate());
        rtEvent.setNextNotificationDate(utEvent.getNextNotificationDate());
        rtEvent.setNotificationEvent(utEvent.getNotificationEvent());
        rtEvent.setScheduleClassName(utEvent.getScheduleClassName());
        rtEvent.setNotificationEvent(utEvent.getNotificationEvent());
        rtEvent.setTaskName(utEvent.getTaskName());
    }

    /**
     * Sends new event to be registered.
     */
    private void registerTask(UpdateTaskEvent utEvent)
    {
        RegisterTaskEvent rtEvent = new RegisterTaskEvent();
        copyProperties(utEvent, rtEvent);
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
    }

}
