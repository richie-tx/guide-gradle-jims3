package mojo.km.dispatch;

import java.util.Hashtable;
import mojo.km.messaging.IEvent;
import mojo.km.context.ContextManager;

/**
 * Responsible for implementing the posting of user events to a context listener.
 * 
 */
public class EventManager implements IDispatch
{
	private static Hashtable<ContextManager, PostEventStrategyManager> managerList = new Hashtable<ContextManager, PostEventStrategyManager>();

	/**
	 * Pass this property to the EventManager getSharedInstance call to enable posting to a request
	 * reply service.
	 */
	public static final String REPLY = "Reply";

	/**
	 * Pass this property to the EventManager getSharedInstance call to enable posting to a request
	 * reply service.
	 */
	public static final String REQUEST = "Request";

	private static PostEventStrategyManager sharedInstance = new PostEventStrategyManager();

	/**
	 * @param strategy -
	 *            strategy
	 * @param dispatch -
	 *            dispatch
	 */
	public static void addStrategy(String strategy, IDispatch dispatch)
	{
		ContextManager key = ContextManager.currentContext();
		if (key != null)
		{
			if (!managerList.containsKey(key))
			{
				managerList.put(key, new PostEventStrategyManager());
			}

			PostEventStrategyManager aManager = (PostEventStrategyManager) managerList.get(key);
			aManager.addStrategy(strategy, dispatch);
			return;
		}
		sharedInstance.addStrategy(strategy, dispatch);
	}

	/**
	 * Returns a singleton instance of this class.
	 * 
	 * @param strategy -
	 *            the name of the submission strategy.
	 * @return instance of this class.
	 */
	public static IDispatch getSharedInstance(String strategy)
	{
		ContextManager key = ContextManager.currentContext();
		if (!managerList.containsKey(key))
		{
			managerList.put(key, new PostEventStrategyManager());
		}
		EventManager eManager = new EventManager();
		eManager.currentDispatch = managerList.get(key).getStrategy(strategy);
		return eManager;
	}

	/**
	 * @param event -
	 *            event
	 * @return mojo.km.dispatch.IDispatch
	 */
	public static IDispatch getStrategy(IEvent event)
	{
		ContextManager key = ContextManager.currentContext();
		if (key != null)
		{
			if (!managerList.containsKey(key))
			{
				managerList.put(key, new PostEventStrategyManager());
			}

			PostEventStrategyManager aManager = managerList.get(key);

			return aManager.getStrategy(event);
		}
		return sharedInstance.getStrategy(event);
	}

	private IDispatch currentDispatch;

	public EventManager()
	{
	}

	/**
	 * Get any reply from event posting. Reply may be completion or status or a actual return of
	 * event.
	 * 
	 * @return - event
	 */
	public IEvent getReply()
	{
		if (currentDispatch != null)
		{
			return currentDispatch.getReply();
		}
		return new mojo.km.messaging.noop.NoReply();
	}

	/**
	 * Post the event to a context listener.
	 * 
	 * @param event -
	 *            the event submitted by a application partition.
	 */
	public void postEvent(IEvent event)
	{

		if (currentDispatch != null)
		{
			currentDispatch.postEvent(event);
		}
	}
}
