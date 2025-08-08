package mojo.km.persistence;

import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;

/**
 * Defines interface that enables entry point into persistent store.
 */
public interface IHome
{
	/**
	 * Store the object in the database
	 * 
	 * @param storedObject
	 *            is the object to store.
	 * @return Object version of storedObject with object ID set.
	 */
	public Object bind(PersistentObject storedObject);

	/**
	 * Find the named object with the objectName in the database
	 * 
	 * @param oid
	 *            is the OID of the object to lookup.
	 * @param objectClass
	 *            is the class to search.
	 * @return Object that was found that matches input name.
	 */
	public Object find(String oid, Class objectClass);
	
	public List findAllList(Class objectClass, String aContextKey);

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
	 */
	public Object find(String attributeName, Object attributeValue, Class objectClass);

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
	 */
	public Iterator findAll(Class objectClass);

	/**
	 * Find all objects whose attribute values mathches the given expression in the database
	 * 
	 * @param event
	 *            housing data for configured query.
	 * @param objectClass
	 *            is the class to search.
	 * @return Iterator of objects found for the criteria.
	 */
	public Iterator findAll(IEvent event, Class objectClass);

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
	 */
	public Iterator findAll(String attributeName, Object attributeValue, Class objectClass);

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
	 */
	public List findAllList(Class objectClass);

	/**
	 * Find all objects whose attribute values mathches the given expression in the database
	 * 
	 * @param event
	 *            housing data for configured query.
	 * @param objectClass
	 *            is the class to search.
	 * @return Iterator of objects found for the criteria.
	 */
	public List findAllList(IEvent event, Class objectClass);

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
	 */
	public List findAllList(String attributeName, Object attributeValue, Class objectClass);
	
	/**
	 * Finds all objects whose attribute value mathches the criteria with the objectName in the
	 * database
	 * 
	 * @param attributeName
	 *            is the name of the attribute to compare.
	 * @param attributeValue
	 *            is the value of the named attribute to compare.
	 * @param contextKey
	 * 			  specify a query
	 * @param pType
	 *            is the class to search.
	 * @return Iterator of objects found for the criteria.
	 */
	public List findAllList(String attributeName, String attributeValue, String contextKey, Class pType);

	/**
	 * Finds all objects whose attribute value mathches the criteria with the objectName in the
	 * database
	 * 
	 * @param attributeName
	 *            is the name of the attribute to compare.
	 * @param attributeValue
	 *            is the value of the named attribute to compare.
	 * @param contextKey
	 * 			  specify a query
	 * @param pType
	 *            is the class to search.
	 * @return Iterator of objects found for the criteria.
	 */
	public List findAllList(IEvent anEvent, Class pType, String contextKey);
	
	/**
	 * Find all objects whose attribute values mathches the given expression in the database
	 * 
	 * @param event
	 *            housing data for configured query.
	 * @param objectClass
	 *            is the class to search.
	 * @return Response Event containg Meta Data (Count)
	 * 
	 */
	public MetaDataResponseEvent findMeta(IEvent event, Class objectClass);
	
	/**
	 * Find all objects whose attribute values mathches the given expression in the database
	 * 
	 * @param event
	 *            housing data for configured query.
	 * @param objectClass
	 *            is the class to search.
	 * @param aContextKey
	 * 			  specify a query
	 * @return Response Event containg Meta Data (Count)
	 * 
	 */	
	public MetaDataResponseEvent findMeta(IEvent anEvent, Class pType, String aContextKey);

	/**
	 * Remove the object from the database
	 * 
	 * @param storedObject
	 *            is the object to remove.
	 */
	public void remove(Object storedObject);
}
