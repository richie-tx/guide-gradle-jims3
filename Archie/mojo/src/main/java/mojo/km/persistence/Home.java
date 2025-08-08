package mojo.km.persistence;

import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.transaction.multidatasource.TransactionManager;

/**
 * Performs some database activities such as db lookup of names objects and storing persistent
 * objects.
 */
public class Home implements IHome
{
	public Home()
	{		
	}

	/**
	 * Store the object in the database
	 * 
	 * @param storedObject
	 *            is the object to store.
	 * @return Object version of storedObject with object ID set.
	 * @pre storedObject != null
	 * @pre storedObject instanceof mojo.km.persistence.PersistentObject
	 */
	public Object bind(PersistentObject storedObject)
	{
		TransactionManager tm = TransactionManager.getInstance();
		tm.bindNew(storedObject);

		return storedObject;
	}

	/**
	 * Find the named object with the objectName in the database
	 * 
	 * @param oid
	 *            is the OID of the object to lookup.
	 * @param objectClass
	 *            is the class to search.
	 * @return Object that was found that matches input name.
	 * @exception ObjectNotFoundException
	 *                thrown if no object matches name.
	 * @pre oid != null
	 * @pre objectClass != null
	 */
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
	
	/**
	 * Finds all objects for a givent class in the database
	 * 
	 * @param attributeName
	 *            is the name of the attribute to compare.
	 * @param attributeValue
	 *            is the value of the named attribute to compare.
	 * @param objectClass
	 *            is the class to search.
	 * @return Iterator of objects found for the criteria.
	 * @exception ObjectNotFoundException
	 *                thrown if no object matches name.
	 * @pre objectClass != null
	 */
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
	
	public List findAllList(String attributeName, String attributeValue, String contextKey, Class pType)
	{
		AttributeEvent attrEvent = new AttributeEvent();
		attrEvent.setAttributeName(attributeName);
		attrEvent.setAttributeValue(attributeValue);
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieveList(pType, attrEvent, contextKey);
	}

	/**
	 * Finds all objects whose attribute value mathches the criteria with the objectName in the
	 * database
	 * 
	 * @param attributeName
	 *            is the name of the attribute to compare.
	 * @param attributeValue
	 *            is the value of the named attribute to compare.
	 * @param objectClass
	 *            is the class to search.
	 * @return Iterator of objects found for the criteria.
	 * @exception ObjectNotFoundException
	 *                thrown if no object matches name.
	 * @pre attributeName != null
	 * @pre !attributeName.equals("")
	 * @pre attributeValue != null
	 * @pre objectClass != null
	 */
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

	/**
	 * Finds the first object whose attribute value mathches the criteria with the objectName in the
	 * database
	 * 
	 * @param attributeName
	 *            is the name of the attribute to compare.
	 * @param attributeValue
	 *            is the value of the named attribute to compare.
	 * @param objectClass
	 *            is the class to search.
	 * @return Object that was found that matches input name.
	 * @pre attributeName != null
	 * @pre attributeValue != null
	 * @pre objectClass != null
	 * @pre !attributeName.equals("")
	 */
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

	/**
	 * Find all objects whose attribute values mathches the given expression in the database
	 * 
	 * @param event
	 *            housing data for configured query.
	 * @param objectClass
	 *            is the class to search.
	 * @return Iterator of objects found for the criteria.
	 */
	public Iterator findAll(IEvent anEvent, Class objectClass)
	{
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieve(objectClass, anEvent);
	}

	public List findAllList(IEvent anEvent, Class objectClass)
	{
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieveList(objectClass, anEvent);
	}
	
	public List findAllList(IEvent anEvent, Class objectClass, String aContextKey)
	{
		TransactionManager tm = TransactionManager.getInstance();
		return tm.retrieveList(objectClass, anEvent, aContextKey);
	}

	/**
	 * Remove the object from the database
	 * 
	 * @param storedObject
	 *            is the object to remove.
	 * @pre storedObject != null
	 */
	public void remove(Object storedObject)
	{
	}

	public MetaDataResponseEvent findMeta(IEvent anEvent, Class objectClass)
	{
		return TransactionManager.getInstance().retrieveMeta(objectClass, anEvent);
	}
	
	public MetaDataResponseEvent findMeta(IEvent anEvent, Class objectClass, String aContextKey)
	{
		return TransactionManager.getInstance().retrieveMeta(objectClass, anEvent, aContextKey);
	}
}
