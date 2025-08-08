package mojo.km.context.multidatasource;

import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.AllQueryEvent;
import mojo.km.persistence.AttributeEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.OIDEvent;
import mojo.km.transaction.multidatasource.TransactionManager;

public class Home implements IHome
{
	public Home()
	{
	}

	public Object bind(mojo.km.persistence.PersistentObject storedObject)
	{
		TransactionManager tm = TransactionManager.getInstance();
		tm.bindNew(storedObject);

		return storedObject;
	}

	public Object find(String oid, Class objectClass)
	{
		OIDEvent anEvent = new OIDEvent();
		anEvent.setOID(oid);
		TransactionManager tm = TransactionManager.getInstance();
		List results = tm.retrieveList(objectClass, anEvent);
		Object result = null;
		if (results.size() > 0)
		{
			result = results.get(0);
		}
		return result;
	}
	
	public Object find(String oid, Class objectClass, String aContextKey)
	{
		OIDEvent anEvent = new OIDEvent();
		anEvent.setOID(oid);
		TransactionManager tm = TransactionManager.getInstance();
		List results = tm.retrieveList(objectClass, anEvent, aContextKey);
		Object result = null;
		if (results.size() > 0)
		{
			result = results.get(0);
		}
		return result;
	}

	public Iterator findAll(Class objectClass)
	{
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieve(objectClass, new AllQueryEvent());
	}

	public List findAllList(Class objectClass)
	{
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieveList(objectClass, new AllQueryEvent());
	}
	
	public List findAllList(Class objectClass, String aContextKey)
	{
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieveList(objectClass, new AllQueryEvent(), aContextKey);
	}

	public Iterator findAll(String attributeName, Object attributeValue, Class objectClass)
	{
		AttributeEvent anEvent = new AttributeEvent();
		anEvent.setAttributeName(attributeName);
		anEvent.setAttributeValue(attributeValue);
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieve(objectClass, anEvent);
	}

	public List findAllList(String attributeName, Object attributeValue, Class objectClass)
	{
		AttributeEvent anEvent = new AttributeEvent();
		anEvent.setAttributeName(attributeName);
		anEvent.setAttributeValue(attributeValue);
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieveList(objectClass, anEvent);
	}

	public Object find(String attributeName, Object attributeValue, Class objectClass)
	{
		AttributeEvent anEvent = new AttributeEvent();
		anEvent.setAttributeName(attributeName);
		anEvent.setAttributeValue(attributeValue);
		TransactionManager tm = TransactionManager.getInstance();
		List results = tm.retrieveList(objectClass, anEvent);
		Object result = null;
		if(results.size() > 0)
		{
			result = results.get(0);
		}
		return result;
	}

	public void remove(Object storedObject)
	{
	}

	public Iterator findAll(IEvent anEvent, Class aType)
	{
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieve(aType, anEvent);
	}

	public List findAllList(IEvent anEvent, Class aType)
	{
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieveList(aType, anEvent);
	}
	
	public List findAllList(IEvent anEvent, Class objectClass, String aContextKey)
	{
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieveList(objectClass, anEvent, aContextKey);
	}

	/**
	 * Checks contract for event to be used for query has mapping with the specific persistent type.
	 * 
	 * The mapping should exist within ReqPro requirements defined as a query mapping that traces to
	 * both the specified event (anEvent) and the specified persistent type (persistentType).
	 */
	protected boolean hasQueryEventRegistration(IEvent anEvent, Class persistentType)
	{
		return TransactionManager.hasQueryEventRegistration(anEvent, persistentType);
	}

	public MetaDataResponseEvent findMeta(IEvent anEvent, Class aType)
	{
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieveMeta(aType, anEvent);
	}

	public List findAllList(String attributeName, String attributeValue, String contextKey, Class pType)
	{
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieveList(pType, attributeName, attributeValue, contextKey);
	}
	
	public MetaDataResponseEvent findMeta(IEvent anEvent, Class objectClass, String aContextKey)
	{
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieveMeta(objectClass, anEvent, aContextKey);
	}
}
