/*
 * Created on Oct 20, 2006
 *
 */
package mojo.km.transaction.multidatasource;

import mojo.km.context.multidatasource.ConnectionManager;
import mojo.km.context.multidatasource.IConnection;
import mojo.km.logging.LogUtil;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.log4j.Level;

/**
 * @author Jim Fisher
 *  
 */
public class PoolableConnectionFactory implements PoolableObjectFactory
{
    private String connectionName;

    private String destroyConnectionMsg;

    private String createConnectionMsg;

    public PoolableConnectionFactory(String aName)
    {
        this.connectionName = aName;
        this.createConnectionMsg = "createConnection " + aName;
        this.destroyConnectionMsg = "destroyConnection " + aName;
    }

    public Object makeObject() throws Exception
    {
        LogUtil.log(Level.TRACE, createConnectionMsg);
        return ConnectionManager.createConnection(connectionName);
    }

    public void destroyObject(Object anObject) throws Exception
    {
        LogUtil.log(Level.TRACE, destroyConnectionMsg);
        IConnection conn = (IConnection) anObject;
        if (conn != null)
        {
            conn.close();
        }
    }

    public boolean validateObject(Object anObject)
    {
        return true;
    }

    public void activateObject(Object anObject) throws Exception
    {
        // TODO No-op
    }

    public void passivateObject(Object anObject) throws Exception
    {
        // TODO No-op
    }

}
