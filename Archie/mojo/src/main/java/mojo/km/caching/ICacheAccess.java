/*
 * Created on Oct 27, 2005
 *
 */
package mojo.km.caching;

/**
 * @author eamundson
 *  
 */
public interface ICacheAccess
{
    void init(String region) throws CacheException;

    Object get(String id);

    void put(String id, Object item) throws CacheException;

    void remove(String id) throws CacheException;
    
    void invalidate(String id) throws CacheException;
}
