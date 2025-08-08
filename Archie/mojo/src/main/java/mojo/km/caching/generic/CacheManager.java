package mojo.km.caching.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mojo.km.config.AppProperties;
import mojo.km.config.CacheProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.messaging.IEvent;
import mojo.km.naming.CacheLevel;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.Reflection;
import mojo.km.persistence.OIDEvent;
import mojo.km.caching.ICacheAccess;
import mojo.km.caching.CacheException;

/**
 * @modelguid {D221CC39-E438-4A08-B6C9-E12B292EA348}
 */
public class CacheManager
{
    /**
     * Helper for caching those types handled by cache.
     */
    class TypeHelper implements Serializable
    {
        Map instances = new HashMap();

        int lastSize = 0;
    }

    private static boolean cachingEnabled = AppProperties.getInstance().enableCache();

    private static Hashtable instances = new Hashtable();

    private static Hashtable remoteInstances = new Hashtable();

    private static final String SYSTEM = "systemCache";

    /**
     * adds all objects in the collection to the system cache. This method
     * expects objects of type mojo.km.persistence.PersistentObject.
     * 
     * @param elements
     */
    public static void add(Collection elements, IEvent anEvent)
    {
        if (elements.isEmpty())
        {
            return;
        }
        Object pObj = elements.iterator().next();
        if (isCachable(pObj.getClass()))
        {
            Map typeMap = getTypeMap(pObj.getClass());
            String key = StringConversionUtility.eventToString(anEvent);
            TypeHelper tHelper = null;
            if (typeMap.containsKey(key))
            {
                tHelper = (TypeHelper) typeMap.get(key);
            }
            else
            {
                tHelper = getInstance(pObj.getClass()).new TypeHelper();
                typeMap.put(key, tHelper);
            }
            Iterator i = elements.iterator();
            while (i.hasNext())
            {
                PersistentObject aPObj = (PersistentObject) i.next();
                tHelper.instances.put(aPObj.getOID(), aPObj);
            }
            tHelper.lastSize = tHelper.lastSize + elements.size();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.caching.ICacheManager#add(java.lang.Object,
     *      java.lang.String)
     */
    public static void add(Object entity, String id)
    {
        // TODO Auto-generated method stub
        if (!isCachable(entity.getClass()))
        {
            return;
        }
        // always adding a new (just created by the user) instance
        Map typeMap = getTypeMap(entity.getClass());
        addNPTypeInstance(typeMap, entity, id);

    }

    private static void add(PersistentObject pObj)
    {
        if (isCachable(pObj.getClass()))
        {
            Map typeMap = getTypeMap(pObj.getClass());
            String oid = "" + pObj.getOID();
            if (!typeMap.containsKey(oid))
            {
                TypeHelper tHelper = getInstance(pObj.getClass()).new TypeHelper();
                typeMap.put(oid, tHelper);
                tHelper.instances.put(pObj.getOID(), pObj);
                tHelper.lastSize++;
            }
        }
    }

    private static void add(PersistentObject pObj, String contextKey)
    {
        if (isCachable(pObj.getClass()))
        {
            Map typeMap = getTypeMap(pObj.getClass());
            String oid = contextKey + "::" + pObj.getOID();
            if (!typeMap.containsKey(oid))
            {
                TypeHelper tHelper = getInstance(pObj.getClass()).new TypeHelper();
                typeMap.put(oid, tHelper);
                tHelper.instances.put(pObj.getOID(), pObj);
                tHelper.lastSize = tHelper.lastSize + 1;
            }
        }
    }

    /**
     * @see mojo.km.caching.ICacheManager#add(Object, String)
     * @modelguid {B8A8E2B5-7EE1-445F-87CC-A818C923DECC}
     * @param pObj
     * @roseuid 404DA1F4010B
     */
    public static void addEntity(PersistentObject pObj)
    {
        if (!isCachable(pObj.getClass()))
        {
            return;
        }
        // always adding a new (just created by the user) instance
        String oid = pObj.getOID();
        addEntityWithKey(pObj, oid);
        Map typeMap = getTypeMap(pObj.getClass());
        for (Iterator keys = typeMap.keySet().iterator(); keys.hasNext();)
        {
            String key = (String) keys.next();
            if (key.equals(oid))
            {
                continue;
            }
            IEvent event = StringConversionUtility.stringToEvent(key);
            EntityMappingProperties entityProps = MojoProperties.getInstance().getEntityMap(pObj.getClass().getName());

            EventQueryProperties eventProps = entityProps.getEventQueryProperties(event.getClass().getName());
            Iterator parms = eventProps.getParmsIterator();
            boolean meetsCriteria = true;
            while (parms.hasNext())
            {
                String propName = ((ParmMappingProperties) parms.next()).getPropertyName();
                String pVal = ("" + Reflection.invokeAccessorMethod(pObj, propName)).trim();
                String eVal = ("" + Reflection.invokeAccessorMethod(event, propName)).trim();
                if (!pVal.equals(eVal))
                {
                    meetsCriteria = false;
                    break;
                }
            }
            if (meetsCriteria)
            {
                TypeHelper tHelper = (TypeHelper) typeMap.get(key);
                tHelper.instances.put(pObj.getOID(), pObj);
            }
        }
    }

    /**
     * @see mojo.km.caching.ICacheManager#add(Object, String)
     * @param pObj
     *            The PersistentObject to add to cache
     * @param anEvent
     *            The event containing the criteria for the object to add to
     *            cache
     */
    public static void addEntity(PersistentObject pObj, IEvent anEvent, String contextKey)
    {
        if (anEvent instanceof OIDEvent == false)
        {
            OIDEvent oidEvent = new OIDEvent();
            oidEvent.setOID(pObj.getOID());
            addEntityWithKey(pObj, StringConversionUtility.eventToString(oidEvent), contextKey);
        }
        addEntityWithKey(pObj, StringConversionUtility.eventToString(anEvent), contextKey);
    }

    private static void addEntityWithKey(PersistentObject pObj, String key)
    {
        if (isCachable(pObj.getClass()))
        {
            Map typeMap = getTypeMap(pObj.getClass());
            addTypeInstance(typeMap, pObj, key);
        }
    }

    private static void addEntityWithKey(PersistentObject pObj, String key, String contextKey)
    {
        if (isCachable(pObj.getClass()))
        {
            Map typeMap = getTypeMap(pObj.getClass());
            addTypeInstance(typeMap, pObj, contextKey + "::" + key);
        }
    }

    private static void addNPTypeInstance(Map typeMap, Object pObj, String key)
    {
        TypeHelper tHelper = null;
        if (typeMap.containsKey(key))
        {
            tHelper = (TypeHelper) typeMap.get(key);
        }
        else
        {
            tHelper = getInstance(pObj.getClass()).new TypeHelper();
            typeMap.put(key, tHelper);
        }
        tHelper.instances.put("" + pObj.hashCode(), pObj);
        tHelper.lastSize++;
    }

    private synchronized static void addTypeInstance(Map typeMap, PersistentObject pObj, String key)
    {
        TypeHelper tHelper = null;
        if (typeMap.containsKey(key))
        {
            tHelper = (TypeHelper) typeMap.get(key);
        }
        else
        {
            tHelper = getInstance(pObj.getClass()).new TypeHelper();
            typeMap.put(key, tHelper);
        }
        tHelper.instances.put(pObj.getOID(), pObj);

        tHelper.lastSize++;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.caching.ICacheManager#get(java.lang.String)
     */
    public static Object get(Class pType, String id)
    {
        // TODO Auto-generated method stub
        Object storedObject = null;
        if (isCachable(pType))
        {
            CacheManager mgr = getInstance(pType);
            // first, check cache
            String typeName = pType.getName();
            // get object from cache
            if (mgr.hasType(typeName))
            {
                Map typeMap = (Map) mgr.cacheAccess.get(typeName);
                if (typeMap.containsKey(id))
                {
                    TypeHelper tHelper = (TypeHelper) typeMap.get(id);
                    storedObject = tHelper.instances.values().iterator().next();
                }
            }
        }
        return storedObject;
    }

    public static String getCacheKey(Object pObj)
    {
        return pObj.getClass().getName();
    }

    private static CacheLevel getCacheLevel(Class pType)
    {
        // get cache properties
        CacheProperties cacheProps = MojoProperties.getInstance().getCacheProperties();
        return cacheProps.getCacheLevel(pType.getName());
    } 

    /**
     * @see mojo.km.caching.ICacheManager#getEntity(String)
     * @modelguid {B14BA5C8-D58B-4CE3-BB51-33AA162EBA0A}
     * @param oid
     * @return java.lang.Object
     * @roseuid 404DA1F40115
     */
    public static PersistentObject getEntityByOID(Class pType, String oid)
    {
        PersistentObject storedObject = null;
        if (isCachable(pType))
        {
            CacheManager mgr = getInstance(pType);
            // first, check cache
            String typeName = pType.getName();
            // get object from cache
            if (mgr.hasType(typeName))
            {
                Map typeMap = (Map) mgr.cacheAccess.get(typeName);
                if (typeMap.containsKey(oid))
                {
                    TypeHelper tHelper = (TypeHelper) typeMap.get(oid);
                    storedObject = (PersistentObject) tHelper.instances.values().iterator().next();
                    //					System.out.println(
                    //						"Read object with OID > " + oid + " of type " +
                    // pType.getName() + " from cache.");
                }
            }
        }
        return storedObject;
    }

    /**
     * @see mojo.km.caching.ICacheManager#getEntity(String)
     * @modelguid {B14BA5C8-D58B-4CE3-BB51-33AA162EBA0A}
     * @param oid
     * @return java.lang.Object
     * @roseuid 404DA1F40115
     */
    public static PersistentObject getEntityByOID(Class pType, String oid, String contextKey)
    {
        PersistentObject storedObject = null;
        if (isCachable(pType))
        {
            CacheManager mgr = getInstance(pType);
            // first, check cache
            String typeName = pType.getName();
            // get object from cache
            if (mgr.hasType(typeName))
            {
                Map typeMap = (Map) mgr.cacheAccess.get(typeName);
                String outOID = contextKey + "::" + oid;
                if (typeMap.containsKey(outOID))
                {
                    TypeHelper tHelper = (TypeHelper) typeMap.get(outOID);
                    storedObject = (PersistentObject) tHelper.instances.values().iterator().next();
                    //					System.out.println(
                    //						"Read object with OID > " + outOID + " of type " +
                    // pType.getName() + " from cache.");
                }
            }
        }
        return storedObject;
    }

    private static CacheManager getInstance(Class pType)
    {
        CacheProperties cProps = MojoProperties.getInstance().getCacheProperties();
        
        String typeName = pType.getName();
        
        String region = cProps.getRegion(typeName);
        
        if (instances.containsKey(region) && !cProps.getIsRemote(typeName))
        {
            return (CacheManager) instances.get(region);
        }
        else if (remoteInstances.containsKey(region) && cProps.getIsRemote(typeName))
        {
            return (CacheManager) remoteInstances.get(region);
        }
        ICacheAccess cacheAccess = null;
        CacheManager returnVal = null;
        if (!cProps.getIsRemote(typeName))
        {
            try
            {
                cacheAccess = new JCSCacheAccess(region);
            }
            catch (CacheException e)
            {
                e.printStackTrace();
                return null;
            }
            returnVal = new CacheManager(cacheAccess);
            instances.put(region, returnVal);
        }
        else
        {
            cacheAccess = new RemoteCacheAccess(typeName);
            returnVal = new CacheManager(cacheAccess);
            remoteInstances.put(region, returnVal);
        }
        return returnVal;
    }

    public static List getInstances(Class pType)
    {
        List storedObjects = new ArrayList();
        if (isCachable(pType))
        {
            CacheManager mgr = getInstance(pType);
            // get object from cache
            if (mgr.hasType(pType.getName()))
            {
                Map typeMap = (Map) mgr.cacheAccess.get(pType.getName());
                for (Iterator i = typeMap.values().iterator(); i.hasNext();)
                {
                    TypeHelper tHelper = (TypeHelper) i.next();
                    storedObjects.addAll(tHelper.instances.values());
                }
            }
        }
        return storedObjects;
    }

    public static Collection getInstances(Class pType, IEvent anEvent)
    {
        Collection storedObjects = null;
        if (isCachable(pType))
        {
            CacheManager mgr = getInstance(pType);
            // get object from cache
            if (mgr.hasType(pType.getName()))
            {
                Map typeMap = (Map) mgr.cacheAccess.get(pType.getName());
                TypeHelper tHelper = (TypeHelper) typeMap.get(StringConversionUtility.eventToString(anEvent));
                if (tHelper != null)
                {
                    storedObjects = tHelper.instances.values();
                }
            }
        }
        return storedObjects;
    }

    public static Collection getInstances(Class pType, IEvent anEvent, String contextKey)
    {
        Collection storedObjects = null;
        //if (isCachable(pType)) {
        CacheManager mgr = getInstance(pType);
        // get object from cache
        if (mgr.hasType(pType.getName()))
        {
            Map typeMap = (Map) mgr.cacheAccess.get(pType.getName());
            TypeHelper tHelper = (TypeHelper) typeMap.get(contextKey + "::"
                    + StringConversionUtility.eventToString(anEvent));
            if (tHelper != null)
            {
                storedObjects = tHelper.instances.values();
            }
        }
        //}
        return storedObjects;
    }

    private static Map getTypeMap(Class pType)
    {
        CacheManager mgr = getInstance(pType);
        Map typeMap = null;
        String typeName = pType.getName();
        if (mgr.hasType(typeName))
        {
            typeMap = (Map) mgr.cacheAccess.get(typeName);
        }
        else
        {
            typeMap = new ConcurrentHashMap();
            // add the new type to cache
            try
            {
                mgr.cacheAccess.put(typeName, typeMap);
            }
            catch (CacheException e)
            {
                // not much we can do here
                return null;
            }
        }
        return typeMap;
    }

    public static void invalidate(Class pType)
    {
        if (CacheManager.isCachable(pType) == true)
        {
            CacheManager mgr = getInstance(pType);
            if (mgr != null)
            {
                String typeName = pType.getName();
                if (mgr.cacheAccess.get(typeName) != null)
                {
                    try
                    {
                        mgr.cacheAccess.invalidate(typeName);
                    }
                    catch (CacheException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static boolean isCachable(Class pType)
    {
        // first, ensure caching is not disabled
        if (!cachingEnabled)
        {
            return false;
        }
        // check for cachability
        CacheLevel level = getCacheLevel(pType);
        if (level == null || CacheLevel.NON_CACHABLE.equals(level))
        {
            return false;
        }
        return true;
    }

    /**
     * @see mojo.km.caching.ICacheManager#get(String)
     * @param id
     */
    public static void remove(Class type, String id)
    {
        try
        {
            CacheManager mgr = getInstance(type);
            mgr.cacheAccess.remove(id);
            Map typeMap = (Map) mgr.cacheAccess.get(type.getName());
            typeMap.remove(id);
        }
        catch (CacheException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @see mojo.km.caching.ICacheManager#get(String)
     * @param id
     */
    public static void removeEntity(PersistentObject pObj)
    {
        if (isCachable(pObj.getClass()))
        {
            String cacheKey = CacheManager.getCacheKey(pObj);
            CacheManager mgr = getInstance(pObj.getClass());
            // get object from cache
            if (mgr.hasType(cacheKey))
            {
                Map typeMap = (Map) mgr.cacheAccess.get(cacheKey);
                for (Iterator i = typeMap.values().iterator(); i.hasNext();)
                {
                    TypeHelper tHelper = (TypeHelper) i.next();
                    if (tHelper.instances.containsKey(pObj.getOID()))
                    {
                        tHelper.instances.remove(pObj.getOID());
                        tHelper.lastSize--;
                    }
                }
            }
        }
    }

    private ICacheAccess cacheAccess = null;

    private CacheProperties cacheProps = null;

    private CacheManager(ICacheAccess cacheAccess)
    {

        this.cacheAccess = cacheAccess;
    }

    /**
     * @see mojo.km.caching.ICacheManager#clear()
     * @modelguid {D2F579D1-F72F-4EAF-B04E-C4F2ECEFD3D9}
     * @roseuid 404DA1F4010E
     */
    public void clear()
    {
        //cacheAccess.dispose();
    }

    /**
     * @see mojo.km.caching.ICacheManager#hasElement(String)
     * @modelguid {CA0BD3F3-03C3-44ED-B506-6ACF2E8BF880}
     * @param key
     * @return boolean
     * @roseuid 404DA1F4011F
     */
    private boolean hasElement(Class pType, String key)
    {
        Map typeMap = (Map) cacheAccess.get(pType.getName());
        if (typeMap != null)
        {
            return typeMap.containsKey(key);
        }
        return false;
    }

    /**
     * @see mojo.km.caching.ICacheManager#hasElement(String)
     * @modelguid {CA0BD3F3-03C3-44ED-B506-6ACF2E8BF880}
     * @param key
     * @return boolean
     * @roseuid 404DA1F4011F
     */
    private boolean hasType(String pType)
    {
        return cacheAccess.get(pType) != null;
    }

    private PersistentObject readCache(Object anOID, Class pType)
    {
        PersistentObject storedObject = null;
        if (isCachable(pType))
        {
            CacheManager mgr = getInstance(pType);
            // first, check cache
            String cacheKey = pType.getName();
            // get object from cache
            if (mgr.hasType(cacheKey))
            {
                Map typeMap = (Map) mgr.cacheAccess.get(cacheKey);
                if (typeMap.containsKey(anOID))
                {
                    TypeHelper tHelper = (TypeHelper) typeMap.get(anOID);
                    storedObject = (PersistentObject) tHelper.instances.values().iterator().next();
                    //					System.out.println(
                    //						"Read object with OID > " + anOID + " of type " +
                    // pType.getName() + " from cache.");
                }
            }
        }
        return storedObject;
    }

}
