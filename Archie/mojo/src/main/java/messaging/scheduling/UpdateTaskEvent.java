package messaging.scheduling;

import mojo.km.messaging.PersistentEvent;
import mojo.km.messaging.RequestEvent;
import java.util.Date;

/**
 * Responsible for housing data that will be sent to control command UpdateTaskCommand
 * 
 * @author Design detail addin
 * @version 1.0
 */
public class UpdateTaskEvent extends RequestEvent
{
    private boolean autoCreate;

    private Date firstNotificationDate;

    private Date nextNotificationDate;

    private PersistentEvent notificationEvent;

    private String OID;

    private String scheduleClassName;

    private String taskName;

    public UpdateTaskEvent()
    {
    }

    public boolean getAutoCreate()
    {
        return autoCreate;
    }

    public Date getFirstNotificationDate()
    {
        return firstNotificationDate;
    }

    public Date getNextNotificationDate()
    {
        return nextNotificationDate;
    }

    public PersistentEvent getNotificationEvent()
    {
        return notificationEvent;
    }

    public String getOID()
    {
        return OID;
    }

    public String getScheduleClassName()
    {
        return scheduleClassName;
    }

    public String getTaskName()
    {
        return taskName;
    }

    public void setAutoCreate(boolean autoCreate)
    {
        this.autoCreate = autoCreate;
    }

    public void setFirstNotificationDate(Date firstNotificationDate)
    {
        this.firstNotificationDate = firstNotificationDate;
    }

    public void setNextNotificationDate(Date nextNotificationDate)
    {
        this.nextNotificationDate = nextNotificationDate;
    }

    public void setNotificationEvent(PersistentEvent notificationEvent)
    {
        this.notificationEvent = notificationEvent;
    }

    public void setOID(String OID)
    {
        this.OID = OID;
    }

    public void setScheduleClassName(String scheduleClassName)
    {
        this.scheduleClassName = scheduleClassName;
    }

    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }
}
