/*
 * Created on Sep 27, 2004
 *
 */
package mojo.km.context.multidatasource;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.tools.code.KeyWord;

/**
 * @author eamundson
 *  
 */
public abstract class AbstractAdapter implements IConnection
{
    private String name;

    protected long startTime;

    protected long timeout;

    public AbstractAdapter()
    {
        this.startTime = new java.util.Date().getTime();
        this.timeout = 120000;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IConnection#close()
     */
    abstract public void close();
    
    public String createKey(String operation, EntityMappingProperties eProps)
    {
        StringBuffer buffer = new StringBuffer(50);
        buffer.append(operation);
        buffer.append(KeyWord.DOUBLE_COLON);
        buffer.append(eProps.getEntity());
        buffer.append(KeyWord.DOUBLE_COLON);
        buffer.append(eProps.getContextKey());
        return buffer.toString();
    }

    abstract public Object getInsertStatement(SaveCallbackProperties sProps);        

    abstract public Object getQueryStatement(EventQueryProperties eProps);

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IConnection#getRegion()
     */
    abstract public String getRegion();

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IConnection#getResource()
     */
    abstract public Object getResource();
    
    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IConnection#getStatement(java.lang.String)
     */
    abstract public Object getStatement(String key);

    abstract public Object getUpdateStatement(SaveCallbackProperties sProps);

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IConnection#init(java.lang.String)
     */
    abstract public void init(String name);

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IConnection#isBad()
     */
    abstract public boolean isBad();

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IConnection#open(java.lang.String, int)
     */
    abstract public void open(String URL, int iPort);

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IConnection#open(java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    abstract public void open(String URL, String userID, String password);

    public void resetUsage()
    {
        this.startTime = new java.util.Date().getTime();
    }
}
