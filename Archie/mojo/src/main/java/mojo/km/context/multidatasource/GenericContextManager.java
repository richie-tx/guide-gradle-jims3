package mojo.km.context.multidatasource;

import mojo.km.context.ContextManager;
import mojo.km.context.ICommand;
import mojo.km.context.IGenericContext;

import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.dispatch.PostEventStrategyManager;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.IPersistentObject;

public class GenericContextManager extends ContextManager implements IGenericContext
{

	protected static GenericContextManager sharedInstance;

	/**
	 * Gets a shared instance of a BusinessContextManager
	 * 
	 * @return BusinessContextManager shared instance.
	 */
	/*public static GenericContextManager getSharedInstance()
	{
		init();
		return sharedInstance;
	}

	private static void init()
	{
		if (sharedInstance == null)
		{
			sharedInstance = new GenericContextManager();
			theCurrentContext = sharedInstance;
		}
	}*/

	private IDispatch trueReply = null;

	public GenericContextManager()
	{
	}

	public IDispatch getTrueReply()
	{
		return trueReply;
	}

	/**
	 * Check to see if listener exist for a specific hashKey into the event table.
	 * 
	 * @param hashKey -
	 *            event class name and topic pair listener is registered under.
	 * @return true if listener is registered.
	 */
	public synchronized boolean hasListener(String hashKey)
	{
		return theBusinessEventBroadcaster.hasListener(hashKey);
	}

	/**
	 * Initialize specific context startup event listeners/commands.
	 * 
	 * @param serverName -
	 *            name of server to initialize.
	 */
	public void initialize(String serverName)
	{
		mServerName = serverName;
		trueReply = (new PostEventStrategyManager()).loadAdapter(EventManager.REPLY);
		theBusinessEventBroadcaster = new GenericEventBroadcaster();
	}

	/**
	 * Return Home deligate if context manages persistent objects.
	 * 
	 * @return IHome - new home deligate for toplink persistent object.
	 */
	public IHome newHome()
	{
		return (IHome) new Home();
	}

	/**
	 * Return the class for the persistent object deligate related to the context.
	 * 
	 * @param obj -
	 *            object to create deligate for
	 * @return deligate object.
	 */
	public IPersistentObject newPersistentObject(mojo.km.persistence.PersistentObject obj)
	{
		return (IPersistentObject) new PersistentObject(obj);
	}

	/**
	 * Post a business event to the event manager.
	 * 
	 * @param event
	 *            is the business event.
	 * @modelguid {338642A2-AF1D-455B-85FD-506E7DE66D18}
	 */
	public void postEvent(IEvent event)
	{
		if (event instanceof RequestEvent)
		{
			RequestEvent rEvent = (RequestEvent) event;
			ContextManager.setUserID(rEvent.getUserID());
		}
		theBusinessEventBroadcaster.fireEvent(event);
		IEvent replyFinal = ((GenericEventBroadcaster) this.theBusinessEventBroadcaster).getFinalReply();
		trueReply.postEvent(replyFinal);
	}

	/**
	 * Register a lister to listen for any business event of the type defined by the "event" input
	 * parameter. When calling this method construct a event of the proper type and set its service
	 * value. Any event sent of this type with the specified service value will be received by the
	 * input listener.
	 * 
	 * @param listener
	 *            use against the event.
	 * @param event
	 *            the event that will be listened for.
	 */
	public void registerEventListener(ICommand listener, mojo.km.messaging.ITopic event)
	{
		String hashKey = event.hashKey();
		theBusinessEventBroadcaster.addListener(listener, hashKey);
	}
}
