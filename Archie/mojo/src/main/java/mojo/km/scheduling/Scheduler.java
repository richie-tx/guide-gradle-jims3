package mojo.km.scheduling;

import mojo.km.config.MojoProperties;
import mojo.km.context.ContextManager;

/**
 * Service that uses a sleeper thread to sleep and then call execute. Execute looks up and executes all of the Tasks that need to
 * be executed
 * 
 * @contextManagerName mojo.km.context.Default.DefaultContextManager
 * @requestDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @replyDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @asyncDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @queueDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @pubSubDispatch mojo.km.dispatch.CurrentContext.CurrentContextStrategy
 * @debugMode false
 * @connectionPool false
 * @workflowEnabled false
 * @stereotype Entity
 * 
 * @author Egan Royal, Jim Fisher
 */
public class Scheduler
{
    private static SleeperThread sleeperThread;

    // TODO Put this in configuration
    private long sleepTime = 1000 * 60; // default 1 minute

    private static Scheduler scheduler;

    private static final String SCHEDULER_SLEEP_TIME = "SchedulerSleepTime";

    /**
     * Private Constructor
     */
    private Scheduler()
    {
        ContextManager.currentContext();

        String sleepTimeString = MojoProperties.getInstance().getPropertyBundleProperties().getProperty(SCHEDULER_SLEEP_TIME);

        if (sleepTimeString != null && sleepTimeString.equals("") == false)
        {
            sleepTime = Long.parseLong(sleepTimeString);
        }

        sleeperThread = new SleeperThread(sleepTime);
        sleeperThread.start();
    }

    /**
     * returns the sleep time for the sleeper thread
     * 
     * @return long The sleep time in milliseconds
     */
    public long getSleepTime()
    {
        return sleeperThread.getSleepTime();
    }

    /**
     * sets the sleep time for the sleeper thread
     * 
     * @param sleepTime
     *            The sleep time in milliseconds
     */
    public void setSleepTime(long sleepTime)
    {
        sleeperThread.setSleepTime(sleepTime);
    }

    static Scheduler getInstance()
    {
        if (scheduler == null)
        {
            scheduler = new Scheduler();
        }
        return scheduler;
    }

    static public void main(String[] args)
    {        
        Scheduler.getInstance();
    }
}
