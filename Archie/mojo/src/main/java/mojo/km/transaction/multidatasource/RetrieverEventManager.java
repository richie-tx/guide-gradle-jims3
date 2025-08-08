package mojo.km.transaction.multidatasource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.WeakHashMap;

import mojo.km.caching.generic.CacheManager;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.PersistentObject;
import mojo.km.context.multidatasource.IMapping;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.naming.MappingConstants;
import mojo.km.config.CallbackProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.exceptionhandling.SourceCount;
import mojo.km.exceptionhandling.TableCountManager;

/**
 * Handles accessing all callbacks to retrieve events.
 */
public class RetrieverEventManager
{
	class CallbackManager
	{
		Method callback = null;

		IMapping mapping = null;

		boolean isRetrieve;
	}

	private static final String RETRIEVE_METHOD_NAME = "retrieve";

	// turn the source frequency tracking on by setthing this system property
	private static String MAX_COUNT_STRING = System.getProperty("jims2.log.sourcecount.max");

	private static int maxCount = -1;

	private static Map<String, TableCountManager> tableCount = Collections
			.synchronizedMap(new WeakHashMap<String, TableCountManager>());

	static
	{
		if (MAX_COUNT_STRING != null)
		{
			try
			{
				maxCount = Integer.valueOf(MAX_COUNT_STRING).intValue();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private CallbackManager exclusive;

	private List<CallbackManager> retrievers;

	public RetrieverEventManager()
	{
		this.retrievers = new ArrayList<CallbackManager>(1); // rare to have more than 1
		// retriever
	}

	void addCallback(String methodName, IMapping callbackObject)
	{
		Class[] parms = new Class[3];
		parms[0] = IEvent.class;
		parms[1] = Class.class;
		parms[2] = Map.class;
		try
		{
			CallbackManager cM = new CallbackManager();
			cM.mapping = callbackObject;
			if (RETRIEVE_METHOD_NAME.equals(methodName))
			{
				cM.isRetrieve = true;
			}
			else
			{
				cM.callback = callbackObject.getClass().getDeclaredMethod(methodName, parms);
			}

			if (callbackObject instanceof Cachable)
			{
				exclusive = cM;
			}
			else
			{
				retrievers.add(cM);
			}
		}
		catch (Exception e)
		{
			String msg = "Failure to add callback: " + methodName + " for " + callbackObject.getClass().getName() + ".";
			throw new AddCallbackException(msg, e);
		}
	}

	/**
	 * Keeps track of number of interactions of a persistent source on a single thread
	 * 
	 * @param aMapping
	 */
	private void checkFrequency(IMapping aMapping)
	{
		CallbackProperties cProps = aMapping.getCallback();

		StringBuilder builder = new StringBuilder();
		if (cProps.getConnectionName().equalsIgnoreCase(MappingConstants.JDBC))
		{
			builder.append(cProps.getSource());
		}
		else
		{
			builder.append(cProps.getSource());
			builder.append(cProps.getFileName());
		}
		String source = builder.toString();

		String threadName = Thread.currentThread().getName();
		TableCountManager manager = tableCount.get(threadName);

		if (manager == null)
		{
			manager = new TableCountManager(maxCount);
			tableCount.put(threadName, manager);
			manager.addTable(source);
		}
		else
		{
			SourceCount counter = manager.getTableCounter(source);
			counter.addCount();
			if (counter.error == false && counter.hasExceededThreshold())
			{
				String msg = source + " accessed over " + (counter.count - 1) + " times";
				ExceptionHandler.addWarning("Retriever Count", null, source, -1, "count threshold exceeded", msg);
			}
		}

	}

	public boolean isCaching()
	{
		return (exclusive != null);
	}

	List notify(IEvent anEvent, Class pType)
	{
		List finalResults = null;
		Map retval = null;
		try
		{

			if (exclusive != null)
			{
				finalResults = this.executeCallback(null, exclusive, anEvent, pType);

				if (finalResults.size() > 0)
				{
					return finalResults;
				}
			}
			int len = retrievers.size();

			// until all views are fixed to guarantee unique OID mappings, use multiple retriever impl
			if (len == -1)
			// single retriever (executeCallback returns results in List)
			{
				CallbackManager callbackMgr = retrievers.get(0);
				finalResults = this.executeCallback(null, callbackMgr, anEvent, pType);

				if (maxCount != -1)
				{
					this.checkFrequency(callbackMgr.mapping);
				}
			}
			else
			// multiple retrievers (executeCallback maintains results in retval reference)
			{
				finalResults = new ArrayList();
				retval = new HashMap();
				for (int i = 0; i < len; i++)
				{
					CallbackManager callbackMgr = retrievers.get(i);
					this.executeCallback(retval, callbackMgr, anEvent, pType);

					if (maxCount != -1)
					{
						this.checkFrequency(callbackMgr.mapping);
					}
				}

				finalResults.addAll(retval.values());
			}
		}
		catch (InvocationTargetException e)
		{
			String msg = "Error retrieving :: " + pType.getName() + " :: " + e.getMessage();
			RetrieveException outException = new RetrieveException(msg, e.getTargetException());
			throw outException;
		}
		catch (Exception e)
		{
			String msg = "Error retrieving :: " + pType.getName() + " :: " + e.getMessage();
			RetrieveException outException = new RetrieveException(msg, e);
			throw outException;
		}

		if (exclusive != null)
		// if entity is cacheable
		{
			IMapping mapping = (IMapping) exclusive.mapping;
			EntityMappingProperties eProps = mapping.getEntityMap();
			String contextKey = "";
			if (eProps != null)
			{
				contextKey = eProps.getContextKey();
			}

			int len = finalResults.size();
			for (int j = 0; j < len; j++)
			{
				PersistentObject pObj = (PersistentObject) finalResults.get(j);
				CacheManager.addEntity(pObj, anEvent, contextKey);
			}

		}
		return finalResults;
	}

	/**
	 * Facilitates the ability to execute IMapping methods directly if possible (99% of the time)
	 * 
	 * @param retval
	 * @param callbackMgr
	 * @param anEvent
	 * @param pType
	 * @return
	 * @throws Exception
	 */
	private List executeCallback(Map retval, CallbackManager callbackMgr, IEvent anEvent, Class pType) throws Exception
	{
		List results = null;
		if (callbackMgr.isRetrieve == true)
		{
			if (retval != null)
			// multiple retrievers (maintain results in retval reference)
			{
				callbackMgr.mapping.retrieve(anEvent, pType, retval);
			}
			else
			// single retriever (results returned in List, null retval reference)
			{
				results = callbackMgr.mapping.retrieve(anEvent, pType, null);
			}
		}
		else
		{
			Object[] parms = new Object[3];
			parms[0] = anEvent;
			parms[1] = pType;
			parms[2] = retval;
			results = (List) callbackMgr.callback.invoke(callbackMgr.mapping, parms);
		}

		return results;
	}

	MetaDataResponseEvent notifyMetaData(IEvent anEvent, Class pType)
	{
		Map retval = new HashMap();
		MetaDataResponseEvent resp = new MetaDataResponseEvent();

		CallbackManager callbackMgr = retrievers.get(0);

		try
		{
			resp = callbackMgr.mapping.retrieveMeta(anEvent, pType, retval);
		}
		catch (Exception e)
		{
			String msg = "Error retrieving metadata :: " + pType.getName() + " :: " + e.getMessage();
			RetrieveException outException = new RetrieveException(msg, e);
			throw outException;
		}

		return (resp);
	}

}
