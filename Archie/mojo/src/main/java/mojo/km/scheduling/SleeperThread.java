package mojo.km.scheduling;

import java.util.Date;

import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

/**
 * Thread used to sleep and then wake up to execute the scheduler
 * 
 * @author: Egan Royal
 */
class SleeperThread extends Thread
{
    private boolean cancelled;

    private long sleepTime;

    private Scheduler scheduler;

    SleeperThread(long aSleepTime)
    {
        this.sleepTime = aSleepTime;
    }

    public void cancel()
    {
        this.cancelled = true;
    }

    /**
     * @return Returns the sleepTime.
     */
    public long getSleepTime()
    {
        return sleepTime;
    }

    /** sleep, then execute */
    public void run()
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        while (!cancelled)
        {
            try
            {
                Date currDate = new Date();
                System.out.println("Current timestamp: " + currDate);
                System.out.println("Sleep for " + sleepTime + " milliseconds");
                sleep(sleepTime);

                IEvent lEvent = new messaging.scheduling.ExecuteTasksEvent();
                dispatch.postEvent(lEvent);

                CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();

                MessageUtil.processReturnException(replyEvent);
            }
            catch (Throwable t)
            {
                t.printStackTrace();
            }
        }
    }

    /**
     * @param sleepTime
     *            The sleepTime to set.
     */
    public void setSleepTime(long sleepTime)
    {
        this.sleepTime = sleepTime;
    }
}
