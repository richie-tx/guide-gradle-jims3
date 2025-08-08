/*
 * Created on Oct 27, 2005
 *
 */
package mojo.km.caching.generic;

import mojo.km.caching.ICacheAccess;
import org.apache.jcs.JCS;

/**
 * @author eamundson
 *  
 */
public class JCSCacheAccess implements ICacheAccess
{
    private JCS jcs = null;

    public JCSCacheAccess(String region) throws mojo.km.caching.CacheException
    {
        init(region);
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.caching.ICacheAccess#get(java.lang.String)
     */
    public Object get(String id)
    {
        // TODO Auto-generated method stub
        return jcs.get(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.caching.ICacheAccess#put(java.lang.String, java.lang.Object)
     */
    public void put(String id, Object item) throws mojo.km.caching.CacheException
    {
        // TODO Auto-generated method stub
        try
        {
            jcs.put(id, item);
        }
        catch (org.apache.jcs.access.exception.CacheException e)
        {
            throw new mojo.km.caching.CacheException(e.getMessage());
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.caching.ICacheAccess#remove(java.lang.String)
     */
    public void remove(String id) throws mojo.km.caching.CacheException
    {
        // TODO Auto-generated method stub
        try
        {
            jcs.remove(id);
        }
        catch (org.apache.jcs.access.exception.CacheException e)
        {
            throw new mojo.km.caching.CacheException(e.getMessage());
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.caching.ICacheAccess#init(java.lang.String)
     */
    public void init(String region) throws mojo.km.caching.CacheException
    {
        // TODO Auto-generated method stub
        try
        {
            jcs = JCS.getInstance(region);
        }
        catch (org.apache.jcs.access.exception.CacheException e)
        {
            throw new mojo.km.caching.CacheException(e.getMessage());
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.caching.ICacheAccess#invalidate()
     */
    public void invalidate(String id) throws mojo.km.caching.CacheException
    {
        try
        {
            jcs.remove(id);
        }
        catch (org.apache.jcs.access.exception.CacheException e)
        {
            throw new mojo.km.caching.CacheException(e.getMessage());
        }
    }

}
