/*
 * Created on Nov 8, 2006
 *
 */
package mojo.km.config;

/**
 * @author Jim Fisher
 */
public class ConnectionPoolProperties extends GenericProperties
{
    static public final String MAX_ACTIVE = "maxActive";

    static public final String MAX_IDLE = "maxIdle";

    static public final String MAX_WAIT_TIME = "maxWaitTime";
    
    static public final String WHEN_EXHAUSTED = "whenExhausted";

    static public final String EVICTABLE_IDLE_TIME = "evictableIdleTime";
    
    static public final String MIN_IDLE = "minIdle";
    
    public String getMaxActive()
    {
        return getProperty(MAX_ACTIVE);
    }
    
    public String getMaxIdle()
    {
        return getProperty(MAX_IDLE);
    }
    
    public String getMaxWaitTime()
    {
        return getProperty(MAX_WAIT_TIME);
    }
    
    public String getWhenExhausted()
    {
        return getProperty(WHEN_EXHAUSTED);
    }
    
    public String getEvictableIdleTime()
    {
        return getProperty(EVICTABLE_IDLE_TIME);
    }
    
    public String getMinIdle()
    {
        return getProperty(MIN_IDLE);
    }
    
    public void setMaxActive(String maxActive)
    {
        setProperty(MAX_ACTIVE, maxActive);
    }
    
    public void setMaxIdle(String maxIdle)
    {
        setProperty(MAX_IDLE, maxIdle);
    }
    
    public void setMinIdle(String minIdle)
    {
        setProperty(MIN_IDLE, minIdle);
    }
    
    public void setMaxWaitTime(String maxWaitTime)
    {
        setProperty(MAX_WAIT_TIME, maxWaitTime);
    }
    
    public void setWhenExhausted(String whenExhausted)
    {
        setProperty(WHEN_EXHAUSTED, whenExhausted);
    }
    
    public void setEvictableIdleTime(String anEvictableIdleTime)
    {
        setProperty(EVICTABLE_IDLE_TIME, anEvictableIdleTime);
    }
}
