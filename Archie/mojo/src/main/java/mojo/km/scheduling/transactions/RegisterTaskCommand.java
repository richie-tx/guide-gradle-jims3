package mojo.km.scheduling.transactions;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Home;
import mojo.km.scheduling.Task;
import messaging.scheduling.RegisterTaskEvent;
import java.util.Iterator;

/**
 * Responsible for implementing behavior of analysis method registerTask of control class
 * pd.calendar.calendartransactions.CalendarController
 * 
 * @author Design detail addin
 * @version 1.0
 * @testcase test.pd.calendar.calendartransactions.TestRegisterTaskCommand
 */
public class RegisterTaskCommand implements mojo.km.context.ICommand, mojo.km.transaction.Transactional
{

    /** Default constructor */
    public RegisterTaskCommand()
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
        RegisterTaskEvent lEvent = (RegisterTaskEvent) event;
        IHome home = new Home();

        System.out.println("Register task :: " + lEvent.getTaskName());
        
        if (lEvent.getTaskName() == null || lEvent.getTaskName().equals(""))
        {
            throw new Exception("Task name is required.");
        }
        else
        {
            Iterator i = home.findAll("taskName", lEvent.getTaskName(), Task.class);

            if (i.hasNext())
            {
                throw new Exception("Task already exists: " + lEvent.getTaskName());
            }
        }

        Task task = this.createTask(lEvent);        
        home.bind(task);
    }
    
    private Task createTask(RegisterTaskEvent anEvent)
    {
        Task task = new Task();
        
        task.setFirstNotificationDate(anEvent.getFirstNotificationDate());
        task.setNotificationEvent(anEvent.getNotificationEvent());
        task.setNextNotificationDate(anEvent.getNextNotificationDate());
        task.setScheduleClassName(anEvent.getScheduleClassName());
        task.setTaskName(anEvent.getTaskName());        
        
        return task;
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
