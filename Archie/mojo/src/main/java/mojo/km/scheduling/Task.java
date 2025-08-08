package mojo.km.scheduling;

import java.util.Date;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.PersistentEvent;

/**
 * PersistentObject that houses Event posting information. Only one OID is allowed per event. The OIDValue is the value of the
 * OID, the OIDMutatorName is the name of the mutator that is supposed to be called on the event that will be factoried from the
 * Topic. The task object is meant to be very simple. All you get is one oid in the registered event. All custom logic such as
 * recurrences and error handling needs to be handled by client code.
 * 
 * @queueDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @stereotype Entity
 * @contextManagerName mojo.km.context.Default.DefaultContextManager
 * @workflowEnabled false
 * @author Egan Royal
 * @requestDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @pubSubDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @asyncDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @replyDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @connectionPool false
 * @debugMode false
 */
public class Task extends mojo.km.persistence.PersistentObject
{
    public static short PENDING = 0;

    public static short SUCCESS = 1;

    public static short ERROR = 2;

    private short executed;

    private Date firstNotificationDate;

    private Date nextNotificationDate;

    private PersistentEvent notificationEvent;

    private String notificationEventId;

    private String notificationEventName;

    private String scheduleClassName;

    private String taskName;

    /**
     * Public Constructor
     */
    public Task()
    {
    }

    /**
     * returns true if the Task has been executed.
     * 
     * @return short 0 if pending, 1 if success, 2 if error
     */
    public short getExecuted()
    {
        fetch();
        return this.executed;
    }

    /**
     * returns the first notification date
     * 
     * @return Date the first notification date registered
     */
    public Date getFirstNotificationDate()
    {
        fetch();
        return firstNotificationDate;
    }

    /**
     * returns the target date for execution.
     * 
     * @return Date The target date.
     */
    public Date getNextNotificationDate()
    {
        fetch();
        return nextNotificationDate;
    }

    public IEvent getNotificationEvent()
    {
        fetch();
        return this.notificationEvent;
    }

    /**
     * returns the schedule class name for this task
     * 
     * @return String the full class name of the scheduler for this task
     */
    public String getScheduleClassName()
    {
        fetch();
        return scheduleClassName;
    }

    public String getTaskName()
    {
        fetch();
        return this.taskName;
    }

    /**
     * mark the task as completed
     */
    public void markCompleted()
    {
        this.setExecuted(Task.SUCCESS);
    }
    
    public void markError()
    {
        this.setExecuted(Task.ERROR);
    }

    /**
     * sets the executed bit
     * 
     * @param executed
     *            The executed bit.
     */
    public void setExecuted(short anExecuted)
    {
        if (this.executed != anExecuted)
        {
            markModified();
        }
        this.executed = anExecuted;
    }

    /**
     * sets the first notification date
     * 
     * @param firstNotificationDate
     *            the first notification date
     */
    public void setFirstNotificationDate(Date firstNotificationDate)
    {
        if (this.firstNotificationDate == null || !this.firstNotificationDate.equals(firstNotificationDate))
        {
            markModified();
        }
        this.firstNotificationDate = firstNotificationDate;
    }

    /**
     * Sets the target date for execution.
     * 
     * @param date
     *            The target date.
     */
    public void setNextNotificationDate(Date nextNotificationDate)
    {
        if (this.nextNotificationDate != nextNotificationDate)
        {
            markModified();
        }
        this.nextNotificationDate = nextNotificationDate;
    }

    public void setNotificationEvent(PersistentEvent event)
    {
        if (this.notificationEvent == null || !this.notificationEvent.equals(event))
        {
            markModified();
        }
        this.notificationEvent = (PersistentEvent) event;
    }

    /**
     * sets the tasks scheduler class name
     * 
     * @param scheduleClassName
     *            the name of the class
     */
    public void setScheduleClassName(String scheduleClassName)
    {
        if (this.scheduleClassName == null || !this.scheduleClassName.equals(scheduleClassName))
        {
            markModified();
        }
        this.scheduleClassName = scheduleClassName;
    }

    public void setTaskName(String taskName)
    {
        if (this.taskName == null || !this.taskName.equals(taskName))
        {
            markModified();
        }
        this.taskName = taskName;
    }

    /**
     * unregister the task
     */
    public void unregister()
    {
        markModified();
        this.setExecuted(Task.SUCCESS);
    }

    /**
     * @return
     */
    public boolean isPending()
    {
        return this.getExecuted() == Task.PENDING;
    }
}
