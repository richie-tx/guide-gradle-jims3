package mojo.km.dispatch;

import java.util.Hashtable;

import org.apache.log4j.Level;

import mojo.km.config.DispatchProperties;
import mojo.km.config.ServerProperties;
import mojo.km.config.ServiceRefProperties;
import mojo.km.logging.LogUtil;
import mojo.km.messaging.IEvent;

/**
 * Factories the post event strategy.
 * 
 */
public class PostEventStrategyManager
{
	/**
	 * @associates IDispatch
	 * @modelguid {FF041064-121F-473B-A700-1A794F80CBB7}
	 */
	private Hashtable<String, IDispatch> strategies = new Hashtable<String, IDispatch>();

	/**
	 * @param key
	 * @modelguid {FDD0097B-5D9D-4564-9DA8-64A8ED9CDA21}
	 */
	public PostEventStrategyManager()
	{
	}

	/**
	 * Return a post event strategy.
	 * 
	 * @param name
	 *            of strategy.
	 * @return IDispatch object
	 */
	public IDispatch getStrategy(String name)
	{
		IDispatch returnVal = null;
		if (strategies.containsKey(name))
		{
			returnVal = strategies.get(name);
		}
		else
		{
			throw new RuntimeException("Dispatch strategy not found for event topic: " + name);
		}
		return returnVal;
	}

	/**
	 * @param event
	 * @return
	 * @modelguid {F3368AA0-6AD9-47AB-9614-6435BAFCFD0F}
	 */
	public IDispatch getStrategy(IEvent event)
	{
		IDispatch returnVal = null;
		String name = event.getTopic();
		if (strategies.containsKey(name))
		{
			returnVal = strategies.get(name);
		}
		else
		{
			throw new RuntimeException("Dispatch strategy not found for event topic: " + name);
		}
		return returnVal;
	}

	/**
	 * Loads the adapter into cache store.
	 * 
	 * @param strategyName -
	 *            adapter name.
	 * @return a IDispatch object.
	 * @modelguid {FAA7B81C-58E7-4EB4-B8C7-4DA3EA922FC7}
	 */
	public IDispatch loadAdapter(String strategyName)
	{
		Class aClass = null;
		String className = null;
		try
		{
			className = DispatchProperties.getInstance().getProperty(strategyName);
			aClass = Class.forName(className);
		}
		catch (ClassNotFoundException x)
		{
			System.out.println("\nUnable to load event strategy :: " + className + ".");
			try
			{
				aClass = Class.forName("mojo.km.dispatch.CurrentContext.CurrentContextStrategy");
			}
			catch (ClassNotFoundException z)
			{
				System.out.println("\nUnable to load event strategy :: "
						+ "mojo.km.dispatch.CurrentContext.CurrentContextStrategy" + ".");
				// return null;
			}
		}
		IDispatch retVal = null;
		if (aClass != null)
		{
			try
			{
				retVal = (IDispatch) aClass.newInstance();
				strategies.put(strategyName, retVal);
			}
			catch (Exception e)
			{
				System.out.println("\nUnable to create event strategy :: " + className + ".");
				e.printStackTrace();
			}
		}
		return retVal;
	}

	/**
	 * @param name
	 * @param dispatch
	 */
	public void addStrategy(String name, IDispatch dispatch)
	{
		strategies.put(name, dispatch);
	}

	/**
	 * Loads the adapter into cache store.
	 * 
	 * @param event
	 * @return a IDispatch object.
	 */
	public IDispatch loadAdapter(IEvent event)
	{
		Class aClass = null;
		String className = null;
		IDispatch retVal = null;
		try
		{
			ServiceRefProperties serviceRef = ServerProperties.getInstance(event.getServer()).getServiceRefForEvent(
					event);
			className = serviceRef.getDispatchClass();
			aClass = Class.forName(className);

			if (aClass != null)
			{
				retVal = (IDispatch) aClass.newInstance();
				strategies.put(event.getTopic(), retVal);
			}
		}		
		catch (Exception e)
		{
			LogUtil.log(Level.ERROR, "\nUnable to load event strategy :: " + className);
			LogUtil.log(Level.ERROR, e);
		}
		return retVal;
	}
}
