/*
 * Created on Oct 27, 2005
 *
 */
package mojo.km.caching;

/**
 * @author eamundson
 *  
 */
public class CacheException extends RuntimeException
{
    public CacheException(Throwable e)
    {
        super(e);
    }

    public CacheException(String msg)
    {
        super(msg);
    }

    public CacheException(String msg, Throwable e)
    {
        super(msg, e);
    }

}
