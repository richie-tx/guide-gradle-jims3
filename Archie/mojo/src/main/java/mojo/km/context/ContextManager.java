package mojo.km.context;

import java.util.ArrayList;

import java.util.Hashtable;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.messaging.ITopic;
import mojo.km.naming.ServerNames;
import mojo.km.persistence.IPersistentObject;
import mojo.km.config.MojoProperties;
import mojo.km.config.ServerProperties;
import mojo.km.config.ServiceProperties;
import mojo.km.config.ServiceRefProperties;
import mojo.km.exceptionhandling.ExceptionHandler;

/**
 * Defines an interface to be used by any use case context. Examples would include data flow to a
 * user interface, orvflow to a business service.
 * 
 * @author Eric A Amundson
 */
public abstract class ContextManager
{
	private static Hashtable<String, ArrayList> contextList = new Hashtable<String, ArrayList>();

	protected static Hashtable contextManagers = new Hashtable();

	protected static String mCurrentUser = "UNAUTH";

	static public final String MOJO_SERVER_NAME_PROP = "mojo.server.name";

	//protected static ContextManager theCurrentContext;

	public static void clearContext()
	{
		Object aKey = InvocationSource.getSource();
		if (aKey != null)
		{
			contextList.remove(aKey);
			InvocationSource.removeSource();
		}
	}

	/**
	 * Return the current application context.
	 * 
	 * @return ContextManager the current executable context.
	 */
	public static ContextManager currentContext()
	{
		ContextManager relatedContext = InvocationSource.getSource();
		if (relatedContext == null)
		{
			setContext(ServerNames.GUI);
			relatedContext = InvocationSource.getSource();
		}
		return relatedContext;
	}	

	/**
	 * Get current context's user.
	 * 
	 * @return current user using context.
	 * @modelguid {563CC1D5-D380-4FFB-A1F4-9305C7697750}
	 */
	public static String getCurrentUser()
	{
		return mCurrentUser;
	}

	/**
	 * Return the server name associated with the context.
	 * 
	 * @return current logical name associated with context.
	 */
	public static String getServerName()
	{
		if (InvocationSource.getSource() == null)
		{
			return "";
		}
		return (InvocationSource.getSource()).mServerName;
	}

	/**
	 * @return
	 * @modelguid {40F2399C-43D7-444E-8F44-4492271A9B44}
	 */
	public static synchronized Session getSession()
	{
		return SessionManager.getSession();
	}

	/**
	 * Check to see if context is set.
	 * 
	 * @return true if context is set.
	 */
	public static synchronized boolean isSet()
	{
		return InvocationSource.hasSource();
	}

	public synchronized static void releaseCurrent()
	{
		Object aKey = InvocationSource.getSource();
		if (aKey != null)
		{
			/*ContextManager releasing = (ContextManager) aKey;
			ArrayList available = contextList.get(releasing.mServerName);
			available.add(releasing);*/
			InvocationSource.removeSource();
		}
	}

	/**
	 * Sets the current context to relate to the Server name passed.
	 * 
	 * @param serverName -
	 *            String holding name of server
	 */
	public static void setContext(String serverName)
	{
		Class aClass = null;
		String className = null;
		ArrayList<ContextManager> contextAvailable = null;
		ContextManager theCurrentContext=null;
		
/*		if (isSet())
		{
			if (serverName.equals(theCurrentContext.mServerName))
			{
				return;
			}
		}*/
		if (!contextList.containsKey(serverName))
		{
			contextAvailable = new ArrayList<ContextManager>();
			contextList.put(serverName, contextAvailable);
		}
		contextAvailable = contextList.get(serverName);
		/*if (contextAvailable.size() > 0)
		{
			theCurrentContext = contextAvailable.remove(0);
			InvocationSource.setSource(theCurrentContext);
		}
		else
		{*/
			ServerProperties lServer = ServerProperties.getInstance(serverName);
			if (lServer == null)
			{
				return;
			}
			try
			{
				className = lServer.getContextManager();
				aClass = Class.forName(className);
			}
			catch (ClassNotFoundException x)
			{
				System.out.println("\nUnable to load context manager :: " + className + ".");
				return;
			}
			try
			{
				theCurrentContext = (ContextManager) aClass.newInstance();
				theCurrentContext.mServerName = serverName;
				InvocationSource.setSource(theCurrentContext);
				theCurrentContext.initialize(serverName);
				getSession().setServerName(serverName);
			}
			catch (Throwable e)
			{
				e.printStackTrace();
			}
		//}
	}

	/**
	 * Set the current context's user ID.
	 * 
	 * @param userID -
	 *            login name of user.
	 */
	public static void setUserID(String userID)
	{
		mCurrentUser = userID;
	}

	protected String mCurrentSessionID = "NONE";

	protected String mServerName = "ContextManager";

	protected Session session = null;

	protected String sourceID = "";

	protected IEventBroadcaster theBusinessEventBroadcaster;

	public ContextManager()
	{
	}

	public String getCurrentSessionID()
	{
		return mCurrentSessionID;
	}

	/**
	 * Return the event broadcaster for the context.
	 * 
	 * @return broadcaster being utilized by the context.
	 */
	public IEventBroadcaster getEventBroadcaster()
	{
		return theBusinessEventBroadcaster;
	}

	/**
	 * Return request source id from incoming event.
	 * 
	 */
	public String getSourceID()
	{
		return sourceID;
	}

	/**
	 * Check to see if listener exist for a specific hashKey into the event table.
	 * 
	 * @param hashKey -
	 *            lookup key for registered listener.
	 * @return true if listener is registered.
	 */
	public abstract boolean hasListener(String hashKey);

	/**
	 * Initialize specific context startup event listeners/commands.
	 * 
	 * @param serverName -
	 *            name of server to initialize.
	 */
	public void initialize(String serverName)
	{
		ServerProperties lServer = ServerProperties.getInstance(serverName);
		if (lServer != null)
		{
			for (Iterator serviceRefs = lServer.getServiceRefs(); serviceRefs.hasNext();)
			{
				ServiceRefProperties lServiceRef = (ServiceRefProperties) serviceRefs.next();
				String serviceName = lServiceRef.getName();
				ServiceProperties lService = MojoProperties.getInstance().getService(serviceName);
				if (lService == null)
				{
					System.out.println("Service '" + serviceName + "' does not exist.");
					continue;
				}
				ICommand command = null;
				IEvent event = null;
				Iterator commandNames = lService.getCommands();
				String eventName = lService.getEventName();
				try
				{
					if (eventName.equals(""))
					{
						continue;
					}
					Class eventClass = Class.forName(eventName);
					event = (IEvent) eventClass.newInstance();
					while (commandNames.hasNext())
					{
						String commandName = (String) commandNames.next();
						Class commandClass = Class.forName(commandName);
						command = (ICommand) commandClass.newInstance();
						event.setTopic(serviceName);
						registerEventListener(command, event);
					}
				}
				catch (Throwable e)
				{
					if (e != null)
					{
						ExceptionHandler.executeCallbacks(e);
					}
				}
			}
		}
		else
		{
			// error: serverName property not found
		}
	}

	/**
	 * Return the class for the persistent object deligate related to the context.
	 * 
	 * @param obj -
	 *            object to be managed by deligate.
	 * @return IPersistentObject - deligate persistent object.
	 * @modelguid {2BA9FF6C-5D74-4E48-ABCA-A4DE9E96868D}
	 */
	public IPersistentObject newPersistentObject(mojo.km.persistence.PersistentObject obj)
	{
		return (IPersistentObject) null;
	}

	/**
	 * Post a business event to the event manager.
	 * 
	 * @param event
	 *            is the business event.
	 * @modelguid {45E451B6-313C-49B9-864E-838B153A03A4}
	 */
	public abstract void postEvent(IEvent event);

	/**
	 * Post a business event to the event manager.
	 * 
	 * @param hashKey -
	 *            event classname and topic combo
	 * @param event -
	 *            serialized event object.
	 * @modelguid {B495A0A8-78E7-4841-9800-0598E54328FA}
	 */
	public void postEvent(String hashKey, byte[] event)
	{
	}

	/**
	 * Register a lister to listen for any event of the type defined by the "event" input parameter.
	 * When calling this method construct a event of the proper type and set its symbol value. Any
	 * event sent of this type with the specified hashKey will be received by the input listener.
	 * 
	 * @param listener
	 *            use against the event.
	 * @param event
	 *            the event that will be listened for.
	 * @modelguid {C89FD78B-1143-4FB9-8080-85CAA86FD55A}
	 */
	public abstract void registerEventListener(ICommand listener, ITopic event);

	public void resetListeners()
	{
	}

	public void setCurrentSessionID(String id)
	{
		mCurrentSessionID = id;
	}

	/**
	 * Initialize a new event broadcaster.
	 * 
	 * @param broadcaster
	 *            to be utilized by the context.
	 * @modelguid {DC374BEA-3FAD-4E65-B4E4-04D9CAB81531}
	 */
	public void setEventBroadcaster(IEventBroadcaster broadcaster)
	{
	}

	/**
	 * Set the source id from the incoming event.
	 * 
	 * @modelguid {52561077-D5BD-43B7-A3E8-C48A9A5176C8}
	 */
	public void setSourceID(String aSourceID)
	{
		sourceID = aSourceID;
	}
}
