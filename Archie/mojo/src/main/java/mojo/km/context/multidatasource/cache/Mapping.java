package mojo.km.context.multidatasource.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;

import mojo.km.caching.generic.CacheManager;
import mojo.km.caching.generic.StringConversionUtility;
import mojo.km.config.CallbackProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.context.multidatasource.IConnection;
import mojo.km.context.multidatasource.IMapping;
import mojo.km.context.multidatasource.IStatement;
import mojo.km.logging.LogUtil;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.AllQueryEvent;
import mojo.km.persistence.AttributeEvent;
import mojo.km.persistence.OIDEvent;
import mojo.km.persistence.PersistentObject;
import mojo.km.transaction.multidatasource.Cachable;
import mojo.tools.code.KeyWord;

/**
 * Mapping adapter for cache data store.
 * 
 * @author James McNabb
 */
public class Mapping implements IMapping, Cachable
{
	private String connectionName;

	private EntityMappingProperties entityMap;

	/**
	 * @param aConnectionName -
	 *            sets the attribute value defining the data source connection.
	 */
	public void setConnectionName(String aConnectionName)
	{
		connectionName = aConnectionName;
	}

	public Mapping()
	{
	}

	/**
	 * Method to be registered as the save callback whenever a Persistent Object is to be saved
	 * using a JDBC driver.
	 * 
	 * @param pObj -
	 *            the object to be saved.
	 * 
	 * @modelguid {465C7EF3-8C08-47C1-B3F3-27E762589075}
	 */
	public void save(PersistentObject pObj)
	{
		if (pObj.isDeleted())
		{
			if (LogUtil.getLogger().isTraceEnabled())
			{
				this.logDelete(pObj);
			}
			doDelete(pObj);
		}
		else
		{
			if (LogUtil.getLogger().isTraceEnabled())
			{
				this.logSave(pObj);
			}
			OIDEvent oidEvent = new OIDEvent();
			oidEvent.setOID((String) pObj.getOID());
			CacheManager.addEntity(pObj, oidEvent, pObj.getContextKey());
		}
		pObj.setModified(false);
	}

	@SuppressWarnings("unused")
	private void doInsert(PersistentObject pObj)
	{
		// cache the new object
		cache(pObj);
	}

	private void doDelete(PersistentObject pObj)
	{
		// remove the object from cache
		removeFromCache(pObj);
	}

	/**
	 * Method to be registered related to events that will be used to retrieve objects from a data
	 * store.
	 * 
	 * @param anEvent -
	 *            the event containing the query data.
	 * @param pType -
	 *            the Class type of the persistent objects to be retrieved.
	 * @return ArrayList of objects containing the persistent objects.
	 */
	public List retrieveWithOID(IEvent anEvent, Class pType, Map retVal)
	{
		// Since we have an OIDEvent coming in, parse the OID to get the code table name and
		// code requested. Format of OID will be CodeTableName##code.
		OIDEvent oidEvent = (OIDEvent) anEvent;
		String oid = oidEvent.getOID();
		if (oid == null)
		{
			throw new IllegalArgumentException("OID from OIDEvent is null.");
		}

		return readCache(pType, anEvent);
	}

	// -------------------------------------------------
	// these methods provide access to the CacheManager

	private void logDelete(PersistentObject pObj)
	{
		StringBuilder buffer = new StringBuilder(150);
		buffer.append(LogUtil.EXECUTE_UPDATE);
		buffer.append(" CACHE DELETE ");
		buffer.append(pObj.getClass().getName());
		buffer.append("\n");
		buffer.append("parms: OID=");
		buffer.append(pObj.getOID());
		LogUtil.log(Level.TRACE, buffer.toString());
	}

	private void logSave(PersistentObject pObj)
	{
		StringBuilder buffer = new StringBuilder(150);
		buffer.append(LogUtil.EXECUTE_UPDATE);
		buffer.append(" CACHE ");
		buffer.append(pObj.getClass().getName());
		buffer.append("\n");
		buffer.append("parms: OID=");
		buffer.append(pObj.getOID());
		LogUtil.log(Level.TRACE, buffer.toString());
	}

	/**
	 * @param anEvent
	 * @param type
	 */
	private void logRetrieve(IEvent anEvent, Class pType)
	{
		StringBuilder buffer = new StringBuilder(150);
		buffer.append(LogUtil.EXECUTE_QUERY);
		buffer.append(" CACHE ");
		buffer.append(anEvent.getClass().getName());
		buffer.append(KeyWord.DOUBLE_COLON);
		buffer.append(pType.getName());
		buffer.append(KeyWord.DOUBLE_COLON);
		buffer.append(this.entityMap.getContextKey());
		buffer.append("\n");
		buffer.append("parms: ");
		if (anEvent instanceof OIDEvent)
		{
			buffer.append("OID=");
			OIDEvent event = (OIDEvent) anEvent;
			buffer.append(event.getOID());
		}
		else if (anEvent instanceof AttributeEvent)
		{
			AttributeEvent event = (AttributeEvent) anEvent;
			buffer.append(event.getAttributeName());
			buffer.append(KeyWord.EQUALS);
			buffer.append(event.getAttributeValue());
		}
		else if (anEvent instanceof AllQueryEvent)
		{
			// do nothing... no parms
		}
		else
		{
			buffer.append(StringConversionUtility.eventToString(anEvent));
		}
		LogUtil.log(Level.TRACE, buffer.toString());
	}

	private void cache(PersistentObject pObj)
	{
		CacheManager.addEntity(pObj);
	}

	private List readCache(Class pType, IEvent anEvent)
	{
		String contextKey = "";
		if (entityMap != null)
		{
			contextKey = entityMap.getContextKey();
		}

		ArrayList listResults;
		Collection results = CacheManager.getInstances(pType, anEvent, contextKey);
		if (results == null)
		{
			listResults = new ArrayList();
		}
		else
		{
			listResults = new ArrayList(results);
		}
		return listResults;
	}

	private void removeFromCache(PersistentObject pObj)
	{
		CacheManager.removeEntity(pObj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#setCallback(mojo.km.config.CallbackProperties)
	 */
	public void setCallback(CallbackProperties aCallbackProps)
	{
		this.callbackProps = aCallbackProps;
	}

	private CallbackProperties callbackProps;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getCallback()
	 */
	public CallbackProperties getCallback()
	{
		// TODO Auto-generated method stub
		return this.callbackProps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getDeleteStatement()
	 */
	public IStatement getDeleteStatement()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getUpdateStatement()
	 */
	public IStatement getUpdateStatement()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getInsertStatement()
	 */
	public IStatement getInsertStatement()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getQueryStatement()
	 */
	public IStatement getQueryStatement()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#init(java.lang.String)
	 */
	public void init(String key)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#setConnection(mojo.km.context.multidatasource.IConnection)
	 */
	public void setConnection(IConnection aConnection)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#setEntityMap(mojo.km.config.EntityMappingProperties)
	 */
	public void setEntityMap(EntityMappingProperties entityMap)
	{
		// TODO Auto-generated method stub
		this.entityMap = entityMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getEntityMap()
	 */
	public EntityMappingProperties getEntityMap()
	{
		// TODO Auto-generated method stub
		return entityMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getConnectionName()
	 */
	public String getConnectionName()
	{
		// TODO Auto-generated method stub
		return connectionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#retrieveMeta(mojo.km.messaging.IEvent,
	 *      java.lang.Class, java.util.Map)
	 */
	public MetaDataResponseEvent retrieveMeta(IEvent anEvent, Class pType, Map retVal)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#retrieve(mojo.km.messaging.IEvent,
	 *      java.lang.Class)
	 */
	public List retrieve(IEvent anEvent, Class pType)
	{
		if (LogUtil.getLogger().isTraceEnabled())
		{
			this.logRetrieve(anEvent, pType);
		}

		return readCache(pType, anEvent);
	}

	public List retrieve(IEvent anEvent, Class pType, Map retVal)
	{
		return retrieve(anEvent, pType);
	}

}
