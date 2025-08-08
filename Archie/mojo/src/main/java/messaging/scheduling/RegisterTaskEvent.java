package messaging.scheduling;

import java.util.Date;
import mojo.km.messaging.PersistentEvent;

/**
 * Responsible for housing data that will be sent to control command RegisterTaskCommand
 * 
 * @author Design detail addin
 * @version 1.0
 */
public class RegisterTaskEvent extends mojo.km.messaging.PersistentEvent
{

    public RegisterTaskEvent()
    {
    }

    /**
     * returns the first notification date
     * 
     * @return date the first notification date
     */
    public Date getFirstNotificationDate()
    {
        return this.firstNotificationDate;
    }

    /**
     * sets the first notification date
     * 
     * @param date
     *            the new date
     */
    public void setFirstNotificationDate(Date date)
    {
        this.firstNotificationDate = date;
    }

    /**
     * returns the next notification date
     * 
     * @return Date the next notification date
     */
    public Date getNextNotificationDate()
    {
        return nextNotificationDate;
    }

    /**
     * sets the next notification date
     * 
     * @param nextNotificationDate
     *            the new date
     */
    public void setNextNotificationDate(Date nextNotificationDate)
    {
        this.nextNotificationDate = nextNotificationDate;
    }

    /**
     * returns the scheduler class name for this task
     * 
     * @return String the scheduler class name
     */
    public String getScheduleClassName()
    {
        return scheduleClassName;
    }

    /**
     * sets the scheduler class name
     * 
     * @param scheduleClassName
     *            the new scheduler class name. Please note that the class must implement
     *            pd.calendar.ISchedule
     * @see pd.calendar.ISchedule
     */
    public void setScheduleClassName(String scheduleClassName)
    {
        this.scheduleClassName = scheduleClassName;
    }

    public void setNotificationEvent(PersistentEvent event)
    {
        this.notificationEvent = event;
    }

    public PersistentEvent getNotificationEvent()
    {
        return this.notificationEvent;
    }

    public String getTaskName()
    {
        return this.taskName;
    }

    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    //Take out repeat attribute - mtp
    //	public boolean getRepeat()
    //	{
    //		return this.repeat;
    //	}
    //
    //	/**
    //	 * sets the task repeat flag - task will be repeated until unregistered.
    //	 * @param repeat
    //	 *
    //	 * */
    //	public void setRepeat(boolean repeat)
    //	{
    //		this.repeat = repeat;
    //	}

    private PersistentEvent notificationEvent;

    private Date firstNotificationDate;

    private Date nextNotificationDate;

    private String scheduleClassName;

    private String taskName;
    //private boolean repeat;

}
