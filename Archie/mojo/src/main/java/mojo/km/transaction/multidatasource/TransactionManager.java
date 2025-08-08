package mojo.km.transaction.multidatasource;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;

import mojo.km.config.ConnectionPoolProperties;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.DependencyProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.context.ContextManager;
import mojo.km.context.multidatasource.ConnectionException;
import mojo.km.context.multidatasource.IConnection;
import mojo.km.context.multidatasource.IMapping;
import mojo.km.logging.LogUtil;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.naming.ConnectionConstants;
import mojo.km.persistence.AttributeEvent;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.PrintUtilities;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * Responsible for handling event registration to enable mapping and manipulation of persistent
 * objects.
 */
public class TransactionManager
{
	private static final String BUFFER_MAPPING_CONTEXT = "BUFFER_MAPPING::";

	static private final Hashtable instances = new Hashtable();

	private static Hashtable pools = new Hashtable();

	private static final String REPORTING_CONTEXT = "REPORTING::";

	static private Hashtable<String, RetrieverEventManager> retrievers = new Hashtable<String, RetrieverEventManager>();

	static private Hashtable<String, SaverEventManager> savers = new Hashtable<String, SaverEventManager>();

	/**
	 * Adds a retreiver by persistent class.
	 * 
	 * @param methodName
	 * @param callbackObject
	 * @param event
	 * @param pClass
	 */
	static public void addRetriever(String methodName, IMapping callbackObject, IEvent event, Class pClass)
	{
		addRetriever(methodName, callbackObject, event, pClass.getName());
	}

	/**
	 * Used by application mapping class to add retriever callback to transaction manager.
	 * 
	 * @pre hasRetriever(callbackObject, methodName)
	 */
	static public void addRetriever(String methodName, IMapping callbackObject, IEvent event, String contextKey)
	{
		String key = event.hashKey() + contextKey;

		RetrieverEventManager mgr = null;
		if (retrievers.containsKey(key))
		{
			mgr = retrievers.get(key);
		}
		else
		{
			mgr = new RetrieverEventManager();
			retrievers.put(key, mgr);
		}
		mgr.addCallback(methodName, callbackObject);

	}

	/**
	 * @param save
	 * @param mapInstance
	 * @param contextKey
	 * @throws ClassNotFoundException
	 */
	private static void addSaver(SaveCallbackProperties aSave, IMapping aMapInstance, String aContextKey)
			throws ClassNotFoundException
	{
		List dependencies = aSave.getDependencies();
		int len = dependencies.size();
		Class[] parentDependencies = null;

		if (len > 0)
		{
			parentDependencies = new Class[len];
		}
		for (int j = 0; j < len; j++)
		{
			DependencyProperties dProps = (DependencyProperties) dependencies.get(j);
			parentDependencies[j] = Class.forName(dProps.getClassName());
		}

		SaverEventManager mgr = null;
		if (savers.containsKey(aContextKey))
		{
			mgr = savers.get(aContextKey);
		}
		else
		{
			mgr = new SaverEventManager();
			savers.put(aContextKey, mgr);
		}

		mgr.setParentDependencies(parentDependencies);

		String methodName = aSave.getMappingMethodName();

		mgr.addCallback(methodName, aMapInstance);
	}

	/**
	 * Factory instance of persistent object relative to a context
	 */
	static public TransactionManager getInstance()
	{
		TransactionManager instance;
		ContextManager obj = ContextManager.currentContext();
		if (instances.containsKey(obj))
		{
			instance = (TransactionManager) instances.get(obj);
		}
		else
		{
			if (retrievers.size() == 0)
			{
				initializeMapping();
			}

			instance = new TransactionManager();
			instances.put(obj, instance);
		}

		return instance;
	}

	static public boolean hasQueryEventRegistration(IEvent anEvent, Class persistentType)
	{
		String key = persistentType.getName();
		return hasQueryEventRegistration(anEvent, key);
	}

	static public boolean hasQueryEventRegistration(IEvent anEvent, String contextKey)
	{
		if (retrievers.containsKey(anEvent.hashKey() + contextKey))
		{
			return true;
		}
		return false;
	}

	static public boolean hasSaverRegistration(Class persistentType)
	{
		return hasSaverRegistration(persistentType.getName());
	}

	static public boolean hasSaverRegistration(String contextKey)
	{
		if (savers.containsKey(contextKey))
		{
			return true;
		}
		return false;
	}

	/*
	 * This method will create the retriever and savers in the TransactionManager for use in
	 * persistence. Added method 05/25/04 mpatino
	 */
	private static synchronized void initializeMapping()
	{
		Iterator i = MojoProperties.getInstance().getEntityMaps();
		while (i.hasNext())
		{
			EntityMappingProperties eMap = (EntityMappingProperties) i.next();

			String contextKey = eMap.getContextKey();

			if (contextKey.startsWith(REPORTING_CONTEXT) || contextKey.startsWith(BUFFER_MAPPING_CONTEXT))
			{
				continue;
			}

			try
			{
				Class.forName(eMap.getEntity());
			}
			catch (Exception e)
			{
				LogUtil.log(Level.FATAL, "entity not found: " + eMap.getEntity());
				LogUtil.log(Level.FATAL, e);
				continue;
			}

			Iterator qCallbacks = eMap.getQueryCallbacks();
			while (qCallbacks.hasNext())
			{
				EventQueryProperties eQuery = (EventQueryProperties) qCallbacks.next();

				String mappingClassName = eQuery.getMappingClassName();

				try
				{
					Class mappingClass = Class.forName(mappingClassName);
					IMapping sMapInstance = (IMapping) mappingClass.newInstance();

					sMapInstance.setCallback(eQuery);
					sMapInstance.setEntityMap(eMap);
					sMapInstance.setConnectionName(eQuery.getConnectionName());

					// Create event object this way instead of EventFactory
					// because event configuration may not be in JIMS2.xml
					Class lEventClass = Class.forName(eQuery.getEventName().trim());
					IEvent event = (IEvent) lEventClass.newInstance();
					addRetriever(eQuery.getMappingMethodName(), sMapInstance, event, eMap.getContextKey());

				}
				catch (Exception e)
				{
					LogUtil.log(Level.FATAL, "error loading mapping class: \"" + mappingClassName + "\" contextKey: "
							+ eMap.getContextKey());
					LogUtil.log(Level.FATAL, e);
				}
			}
			Iterator sCallbacks = eMap.getSaveCallbacks();
			while (sCallbacks.hasNext())
			{
				IMapping sMapInstance = null;
				SaveCallbackProperties save = (SaveCallbackProperties) sCallbacks.next();
				try
				{
					String mappingClassName = save.getMappingClassName().trim();

					Class mappingClass = Class.forName(mappingClassName);

					sMapInstance = (IMapping) mappingClass.newInstance();

					sMapInstance.setCallback(save);
					sMapInstance.setEntityMap(eMap);
					sMapInstance.setConnectionName(save.getConnectionName());

					addSaver(save, sMapInstance, eMap.getContextKey());
				}
				catch (Exception e)
				{
					LogUtil.log(Level.FATAL, e);
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private static void logPoolState(String state, GenericObjectPool connPool, long time, String name)
	{
		if (LogUtil.isTraceEnabled() == true)
		{
			int numAvailable = connPool.getMaxActive() - connPool.getNumActive();

			StringBuilder buffer = new StringBuilder(40);
			buffer.append(state);
			buffer.append(name);
			buffer.append(" numActive: ");
			buffer.append(connPool.getNumActive());
			buffer.append(" numIdle: ");
			buffer.append(connPool.getNumIdle());
			buffer.append(" numAvailable: ");
			buffer.append(numAvailable);
			buffer.append(" maxActive: ");
			buffer.append(connPool.getMaxActive());
			buffer.append(" (");
			buffer.append(Thread.currentThread().getName());
			buffer.append(")");
			if (time > 0)
			{
				long elapsedtime = System.currentTimeMillis() - time;
				buffer.append(" | ");
				buffer.append(elapsedtime);
				buffer.append(" ms");
			}
			LogUtil.log(Level.TRACE, buffer.toString());
		}
	}

	synchronized static public void releaseConnection(String aName, IConnection aCon)
	{
		try
		{
			GenericObjectPool connPool = (GenericObjectPool) pools.get(aName);
			if (connPool != null)
			{
				connPool.returnObject(aCon);
			}
		}
		catch (Exception e)
		{
			LogUtil.log(Level.FATAL, "Unable to return connection back to pool.");
			LogUtil.log(Level.FATAL, e);
		}
	}

	private Set deleted = Collections.synchronizedSet(new HashSet());

	private boolean isMapping = false;

	private Set updated = Collections.synchronizedSet(new HashSet());

	TransactionManager()
	{
	}

	/**
	 * Used internally by persistent object to add the persistent object to update list. The update
	 * list is maintained to track changes during application work flow.
	 */
	public boolean addUpdated(PersistentObject persistentObj)
	{
		// first, check for key (OID)
		if (isMapping)
		{
			return false;
		}

		if (persistentObj.isNew())
		{
			updated.add(persistentObj);
		}
		else if (!updated.contains(persistentObj) && !deleted.contains(persistentObj))
		{
			if (persistentObj.isDeleted())
			{
				deleted.add(persistentObj);
			}
			else
			{
				updated.add(persistentObj);
			}
		}

		return true;
	}

	/**
	 * forceInsertUpdate on persistent object
	 * 
	 * @param persistentObj
	 */
	public void bindNew(PersistentObject pObj)
	{
		Iterator contextList = pObj.listContext();
		boolean isSaveable = false;
		if (contextList.hasNext())
		{
			while (contextList.hasNext())
			{
				String contextKey = (String) contextList.next();
				if (savers.containsKey(contextKey))
				{
					(savers.get(contextKey)).notify(pObj);
					isSaveable = true;
				}
			}
		}
		else
		{
			if (savers.containsKey(pObj.getContextKey()))
			{
				(savers.get(pObj.getContextKey())).notify(pObj);
				isSaveable = true;
			}
		}

		removeUpdated(pObj);
		pObj.setModified(false);
		if (!isSaveable)
		{
			throw new SaveException("Class " + pObj.getClass().getName() + " was not saved.");
		}
	}

	/**
	 * Clear list(s) of changed objects.
	 */
	public void clearUpdated()
	{
		updated.clear();
		deleted.clear();
	}

	/**
	 * Commit all changes to updated persistent objects.
	 * 
	 */
	public void commit()
	{
		LogUtil.log(Level.TRACE, "commit");
		isMapping = true;
		SaverEventManager eventManager;

		// process deletes first due to possible database constraints
		Object[] objects = deleted.toArray();
		int len = objects.length;
		for (int i = 0; i < len; i++)
		{
			PersistentObject pObj = (PersistentObject) objects[i];

			Iterator contextList = pObj.listContext();
			if (contextList.hasNext())
			{
				while (contextList.hasNext())
				{
					String contextKey = (String) contextList.next();
					if (savers.containsKey(contextKey))
					{
						eventManager = savers.get(contextKey);
						eventManager.notify(pObj);
					}
				}
			}
			else if (savers.containsKey(pObj.getContextKey()))
			{
				eventManager = savers.get(pObj.getContextKey());
				eventManager.notify(pObj);
			}

			pObj.clearReferences();

		}

		// deletes were successful, process the updates
		objects = updated.toArray();
		len = objects.length;
		for (int i = 0; i < len; i++)
		{
			PersistentObject pObj = (PersistentObject) objects[i];
			try
			{
				Iterator contextList = pObj.listContext();
				if (contextList.hasNext())
				{
					while (contextList.hasNext())
					{
						String contextKey = (String) contextList.next();
						if (savers.containsKey(contextKey))
						{
							eventManager = savers.get(contextKey);
							eventManager.notify(pObj);
						}
					}
				}
				else if (savers.containsKey(pObj.getContextKey()))
				{
					eventManager = savers.get(pObj.getContextKey());
					eventManager.notify(pObj);
				}

				pObj.clearReferences();

				// DEFECT (JIMS200041950): PersistentObj modified not set to
				// "false" after update
				pObj.setModified(false);
			}
			catch (SaveException e)
			{
				throw e;
			}
			catch (Exception e)
			{
				try{
					String msg = " Error saving :: " + pObj.getClass().getName() + " :: " + PrintUtilities.getStackTrace(e, null);
					SaveException outException = new SaveException(msg, e);
					throw outException;
				} catch (IOException ex){
					throw new SaveException(ex.getMessage());
				}

			}
		}

		// Clear the Updated and Deleted maps
		clearUpdated();

		isMapping = false;
	}

	/**
	 * Commit all changes to updated persistent objects.
	 * 
	 */
	public void commitClear()
	{
		LogUtil.log(Level.TRACE, "commit");
		isMapping = true;
		SaverEventManager eventManager;

		// process deletes first due to possible database constraints
		Object[] objects = deleted.toArray();
		int len = objects.length;
		for (int i = 0; i < len; i++)
		{
			PersistentObject pObj = (PersistentObject) objects[i];

			Iterator contextList = pObj.listContext();
			if (contextList.hasNext())
			{
				while (contextList.hasNext())
				{
					String contextKey = (String) contextList.next();
					if (savers.containsKey(contextKey))
					{
						eventManager = savers.get(contextKey);
						eventManager.notify(pObj);
					}
				}
			}
			else if (savers.containsKey(pObj.getContextKey()))
			{
				eventManager = savers.get(pObj.getContextKey());
				eventManager.notify(pObj);
			}

			pObj.clearReferences();

		}

		// deletes were successful, process the updates
		objects = updated.toArray();
		len = objects.length;
		for (int i = 0; i < len; i++)
		{
			PersistentObject pObj = (PersistentObject) objects[i];
			try
			{
				Iterator contextList = pObj.listContext();
				if (contextList.hasNext())
				{
					while (contextList.hasNext())
					{
						String contextKey = (String) contextList.next();
						if (savers.containsKey(contextKey))
						{
							eventManager = savers.get(contextKey);
							eventManager.notify(pObj);
						}
					}
				}
				else if (savers.containsKey(pObj.getContextKey()))
				{
					eventManager = savers.get(pObj.getContextKey());
					eventManager.notify(pObj);
				}

				pObj.clearReferences();

				// DEFECT (JIMS200041950): PersistentObj modified not set to
				// "false" after update
				pObj.setModified(false);
			}
			catch (SaveException e)
			{
				throw e;
			}
			catch (Exception e)
			{
				try{
					String msg = " Error saving :: " + pObj.getClass().getName() + " :: " + PrintUtilities.getStackTrace(e, null);
					SaveException outException = new SaveException(msg, e);
					throw outException;
				} catch (IOException ex){
					throw new SaveException(ex.getMessage());
				}

			}
			finally
			{
				// Clear the Updated and Deleted maps
				clearUpdated();
			}
		}

		isMapping = false;
	}

	/**
	 * @param connectionName
	 * @return
	 */
	private GenericObjectPool createPool(String aName)
	{
		/*
		 * maxActive controls the maximum number of objects that can be borrowed from the pool at
		 * one time. When non-positive, there is no limit to the number of objects that may be
		 * active at one time. When maxActive is exceeded, the pool is said to be exhausted.
		 * 
		 * maxIdle controls the maximum number of objects that can sit idle in the pool at any time.
		 * When negative, there is no limit to the number of objects that may be idle at one time.
		 * 
		 * whenExhaustedAction specifies the behaviour of the borrowObject() method when the pool is
		 * exhausted: - When WHEN_EXHAUSTED_FAIL, borrowObject() will throw a NoSuchElementException -
		 * When WHEN_EXHAUSTED_GROW, borrowObject() will create a new object and return
		 * it(essentially making maxActive meaningless.) - When whenExhaustedAction is
		 * WHEN_EXHAUSTED_BLOCK, borrowObject() will block (invoke Object.wait(long) until a new or
		 * idle object is available. If a positive maxWait value is supplied, the borrowObject()
		 * will block for at most that many milliseconds, after which a NoSuchElementException will
		 * be thrown. If maxWait is non-positive, the borrowObject() method will block indefinitely.
		 * When testOnBorrow is set, the pool will attempt to validate each object before it is
		 * returned from the borrowObject() method. (Using the provided factory's
		 * PoolableObjectFactory.validateObject(java.lang.Object) method.) Objects that fail to
		 * validate will be dropped from the pool, and a different object will be borrowed.
		 * 
		 * When testOnReturn is set, the pool will attempt to validate each object before it is
		 * returned to the pool in the returnObject(java.lang.Object) method. (Using the provided
		 * factory's PoolableObjectFactory.validateObject(java.lang.Object) method.) Objects that
		 * fail to validate will be dropped from the pool.
		 * 
		 * Optionally, one may configure the pool to examine and possibly evict objects as they sit
		 * idle in the pool. This is performed by an "idle object eviction" thread, which runs
		 * asychronously. The idle object eviction thread may be configured using the following
		 * attributes: - timeBetweenEvictionRunsMillis indicates how long the eviction thread should
		 * sleep before "runs" of examining idle objects. When non-positive, no eviction thread will
		 * be launched. - minEvictableIdleTimeMillis specifies the minimum amount of time that an
		 * object may sit idle in the pool before it is eligable for eviction due to idle time. When
		 * non-positive, no object will be dropped from the pool due to idle time alone. -
		 * testWhileIdle indicates whether or not idle objects should be validated using the
		 * factory's PoolableObjectFactory.validateObject(java.lang.Object) method. Objects that
		 * fail to validate will be dropped from the pool.
		 */
		ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties(aName);

		ConnectionPoolProperties poolProps = cProps.getPoolProperties();

		int maxActive = Integer.parseInt(poolProps.getMaxActive());
		int maxIdle = Integer.parseInt(poolProps.getMaxIdle());
		long maxWaitTime = Long.parseLong(poolProps.getMaxWaitTime());
		int minIdle = Integer.parseInt(poolProps.getMinIdle());

		byte whenExhausted = GenericObjectPool.WHEN_EXHAUSTED_GROW;

		if (ConnectionConstants.WHEN_EXHAUSTED_BLOCK.equalsIgnoreCase(poolProps.getWhenExhausted()))
		{
			whenExhausted = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;
		}
		else if (ConnectionConstants.WHEN_EXHAUSTED_FAIL.equalsIgnoreCase(poolProps.getWhenExhausted()))
		{
			whenExhausted = GenericObjectPool.WHEN_EXHAUSTED_FAIL;
		}

		long softMinEvictableIdleTimeMillis = Long.parseLong(poolProps.getEvictableIdleTime());

		boolean testOnBorrow = Boolean.getBoolean(ConnectionConstants.DEFAULT_TEST_ON_BORROW);

		boolean testOnReturn = Boolean.getBoolean(ConnectionConstants.DEFAULT_TEST_ON_RETURN);

		long timeBetweenEviction = Long.parseLong(ConnectionConstants.DEFAULT_TIME_BETWEEN_EVICTION_RUNS);
		// long timeBetweenEviction = Long.parseLong("-1");

		int numTestsPerEviction = Integer.parseInt(ConnectionConstants.DEFAULT_NUM_TESTS_PER_EVICTION_RUN);

		long evictableIdleTime = Long.parseLong(ConnectionConstants.DEFAULT_EVICTABLE_IDLE_TIME);

		boolean testWhileIdle = Boolean.getBoolean(ConnectionConstants.DEFAULT_TEST_WHILE_IDLE);

		PoolableObjectFactory factory = new PoolableConnectionFactory(aName);
		GenericObjectPool connPool = new GenericObjectPool(factory, maxActive, whenExhausted, maxWaitTime, maxIdle,
				minIdle, testOnBorrow, testOnReturn, timeBetweenEviction, numTestsPerEviction, evictableIdleTime,
				testWhileIdle, softMinEvictableIdleTimeMillis);

		return connPool;
	}

	public synchronized IConnection getConnection(String aConnectionName)
	{
		IConnection conn = null;
		try
		{
			GenericObjectPool connPool = null;

			if (pools.containsKey(aConnectionName))
			{
				connPool = (GenericObjectPool) pools.get(aConnectionName);
			}
			else
			{
				connPool = this.createPool(aConnectionName);
				pools.put(aConnectionName, connPool);
			}

			conn = (IConnection) connPool.borrowObject();
		}
		catch (ConnectionException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new ConnectionException(e);
		}
		return conn;
	}

	public boolean hasCachingRegistration(PersistentObject pObj, IEvent anEvent)
	{
		StringBuilder builder = new StringBuilder(40);
		builder.append(anEvent.hashKey());
		builder.append(pObj.getContextKey());
		String key = builder.toString();
		if (retrievers.containsKey(key))
		{
			RetrieverEventManager mgr = retrievers.get(key);
			return mgr.isCaching();
		}
		return false;
	}

	/**
	 * Contract check utilitiy method.
	 */
	protected boolean hasRetriever(Object callback, String methodName)
	{
		boolean returnVal = true;
		Class[] parms = new Class[2];
		parms[0] = IEvent.class;
		parms[1] = Class.class;
		try
		{
			Method theCallback = callback.getClass().getDeclaredMethod(methodName, parms);
			if (theCallback == null)
			{
				return false;
			}
		}
		catch (Exception e)
		{
			return false;
		}
		return returnVal;
	}

	/**
	 * Contract check utility method.
	 * 
	 * @return
	 */
	protected boolean hasSaver(Object callback, String methodName)
	{
		boolean returnVal = true;
		Class[] parms = new Class[1];
		parms[0] = PersistentObject.class;
		try
		{
			Method theCallback = callback.getClass().getDeclaredMethod(methodName, parms);
			if (theCallback == null)
			{
				return false;
			}
		}
		catch (Exception e)
		{
			return false;
		}
		return returnVal;
	}

	/**
	 * Used internally by mappings to remove the persistent object to update list. The update list
	 * list is maintained to track changes during application work flow.
	 */
	public void removeUpdated(PersistentObject persistentObj)
	{
		if (updated.contains(persistentObj))
		{
			updated.remove(persistentObj);
			persistentObj.setModified(false);
		}
		if (deleted.contains(persistentObj))
		{
			deleted.remove(persistentObj);
			persistentObj.setModified(false);
		}
	}

	/**
	 * Retrieve persistent objects relevant to input events.
	 */
	public List retrieveList(Class pType, IEvent anEvent)
	{
		StringBuilder builder = new StringBuilder(40);
		builder.append(anEvent.hashKey());
		builder.append(pType.getName());
		return this.retrieveWithKey(pType, anEvent, builder.toString());
	}
	
	/**
	 * Retrieve persistent objects relevant to input events.
	 */
	public Iterator retrieve(Class pType, IEvent anEvent)
	{		
		StringBuilder builder = new StringBuilder(40);
		builder.append(anEvent.hashKey());
		builder.append(pType.getName());
		List list = this.retrieveWithKey(pType, anEvent, builder.toString());
		return list.iterator();
	}

	private List retrieveWithKey(Class pType, IEvent anEvent, String key)
	{
		if (retrievers.containsKey(key))
		{
			RetrieverEventManager mgr = retrievers.get(key);

			isMapping = true;

			List retVal = mgr.notify(anEvent, pType);

			isMapping = false;

			return retVal;
		}
		else
		{
			throw new UnregisteredEventException("Query request using event = " + anEvent.getClass().getName()
					+ " has no retriever registered for persistent type = " + pType.getName());
		}
	}

	public Iterator retrieve(Class pType, IEvent anEvent, String contextKey)
	{
		StringBuilder builder = new StringBuilder(40);
		builder.append(anEvent.hashKey());
		builder.append(contextKey);
		List list = this.retrieveWithKey(pType, anEvent, builder.toString());
		return list.iterator();
	}
	
	public List retrieveList(Class pType, IEvent anEvent, String contextKey)
	{
		StringBuilder builder = new StringBuilder(40);
		builder.append(anEvent.hashKey());
		builder.append(contextKey);
		return this.retrieveWithKey(pType, anEvent, builder.toString());
	}
	
	public List retrieveList(Class pType, String attributeName, String attributeValue, String contextKey)
	{
		AttributeEvent attrEvent = new AttributeEvent();
		attrEvent.setAttributeName(attributeName);
		attrEvent.setAttributeValue(attributeValue);
		return retrieveList(pType, attrEvent, contextKey);
	}
	
	public List retrieveListClass(Class pType, IEvent anEvent, String contextKey)
	{
		StringBuilder builder = new StringBuilder(40);
		builder.append(anEvent.hashKey());
		builder.append(contextKey);
		return this.retrieveWithKey(pType, anEvent, builder.toString());
	}

	/**
	 * @param type
	 * @param anEvent
	 * @return
	 */
	public MetaDataResponseEvent retrieveMeta(Class pType, IEvent anEvent)
	{
		StringBuilder builder = new StringBuilder(40);
		builder.append(anEvent.hashKey());
		builder.append(pType.getName());		
		String key = builder.toString();
		
		if (retrievers.containsKey(key))
		{
			RetrieverEventManager mgr = retrievers.get(key);

			isMapping = true;
			
			MetaDataResponseEvent resp = mgr.notifyMetaData(anEvent, pType);

			isMapping = false;

			return resp;
		}
		else
		{
			throw new UnregisteredEventException("Query request using event = " + anEvent.getClass().getName()
					+ " has no retriever registered for persistent type = " + pType.getName());
		}
	}
	
	/**
	 * @param type
	 * @param anEvent
	 * @return
	 */
	public MetaDataResponseEvent retrieveMeta(Class pType, IEvent anEvent, String aContextKey)
	{
		StringBuilder builder = new StringBuilder(40);
		builder.append(anEvent.hashKey());
		builder.append(aContextKey);
		
		String key = builder.toString();
		
		if (retrievers.containsKey(key))
		{
			RetrieverEventManager mgr = retrievers.get(key);

			isMapping = true;
			
			MetaDataResponseEvent resp = mgr.notifyMetaData(anEvent, pType);

			isMapping = false;

			return resp;
		}
		else
		{
			throw new UnregisteredEventException("Query request using event = " + anEvent.getClass().getName()
					+ " has no retriever registered for persistent type = " + pType.getName());
		}
	}

	/**
	 * Handles rollback for the multidatasource management. Currently only clears list of changed
	 * objects.
	 * 
	 */
	public void rollback()
	{
		isMapping = false;
		clearUpdated();
	}
}
