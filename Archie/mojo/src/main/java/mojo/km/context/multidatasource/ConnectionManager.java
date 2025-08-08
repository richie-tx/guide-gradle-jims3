package mojo.km.context.multidatasource;

import java.util.*;
import mojo.km.config.*;

/**
 * Resposible for factory and management of connection. If a connection is in
 * use another unused connection will be made available.
 * 
 * 
 * @author Eric A Amundson
 * @modelguid {77D49BD1-437B-423D-81CA-5D4144962806}
 */
public class ConnectionManager
{

    /**
     * Get an available connection or connection not currently in use
     * 
     * @modelguid {5D3735E7-77BC-4ADB-8785-568CF6CB5C7B}
     */
    static public IConnection getAvailableConnection(String name)
    {
        ConnectionProperties connProp = MojoProperties.getInstance().getConnectionProperties(name);
        String pooling = connProp.getPooling();
        IConnection connection = null;
        if (pooling.equalsIgnoreCase("Yes"))
        {
            ConnectionPool pool = null;
            if (!connectionPools.containsKey(name))
            {
                pool = new ConnectionPool();
                connection = createConnection(name);
                pool.available.add(connection);
                connectionPools.put(name, pool);
            }
            else
            {
                pool = (ConnectionPool) connectionPools.get(name);
            }

            if (pool.available.size() == 0)
            {
                IConnection aConnection = createConnection(name);
                pool.available.add(aConnection);
                connection = aConnection;
            }
            else
            {
                Iterator i = pool.available.iterator();
                if (i.hasNext())
                {
                    connection = (IConnection) i.next();
                }
                if (connection.isBad())
                {
                    pool.available.remove(connection);
                    connection = createConnection(name);
                    pool.available.add(connection);
                }
            }
        }
        else
        {
            connection = createConnection(name);
        }
        connection.resetUsage();
        return (IConnection) connection;
    }

    /** @modelguid {F178D680-A001-4E5E-B1E1-E13AE5739F07} */
    static public IConnection createConnection(String aName)
    {
        IConnection connection = null;
        try
        {
            String className = MojoProperties.getInstance().getConnectionProperties(aName).getConnectionAdapter();
            Class connectionClass = Class.forName(className);

            connection = (IConnection) connectionClass.newInstance();
            connection.setName(aName);
            //connection.init(name);
        }
        catch (Exception e)
        {
            throw new ConnectionException("Unable to create connection: " + aName);
        }

        return connection;
    }

    /** @modelguid {E89E80B5-CA8B-4ADE-884E-0383A57AACBC} */
    static class ConnectionPool
    {

        /** @modelguid {8C8E1652-7CED-4B28-A3E4-475436511DC6} */
        Set available = new HashSet();

        /** @modelguid {852952A9-7013-4984-912C-831CD40F6889} */
        Set unavailable = new HashSet();
    }

    static class ConnectionTester implements Runnable
    {
        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Runnable#run()
         */
        public void run()
        {
            // TODO Auto-generated method stub
            while (true)
            {
                try
                {
                    Collection pools = connectionPools.values();
                    for (Iterator i = pools.iterator(); i.hasNext();)
                    {
                        ConnectionPool pool = (ConnectionPool) i.next();
                        ArrayList badConnections = new ArrayList();
                        for (Iterator j = pool.available.iterator(); j.hasNext();)
                        {
                            IConnection connection = (IConnection) j.next();
                            if (connection.isBad())
                            {
                                badConnections.add(connection);
                            }
                        }
                        for (Iterator k = badConnections.iterator(); k.hasNext();)
                        {
                            IConnection connection = (IConnection) k.next();                            
                            pool.available.remove(connection);
                        }
                        badConnections = new ArrayList();
                        for (Iterator j = pool.unavailable.iterator(); j.hasNext();)
                        {
                            IConnection connection = (IConnection) j.next();
                            if (connection.isBad())
                            {
                                badConnections.add(connection);
                            }
                        }
                        for (Iterator k = badConnections.iterator(); k.hasNext();)
                        {
                            IConnection connection = (IConnection) k.next();
                            pool.unavailable.remove(connection);
                        }

                    }
                    Thread.sleep(60000);
                }
                catch (Throwable t)
                {
                }
            }
        }

    }

    {
        //ConnectionTester tester = new ConnectionTester();
        //Thread testThread = new Thread(tester);
        //testThread.start();
    }

    /** @modelguid {9C1DB159-800D-4B35-A4CC-BFCC1F4422AB} */
    static Hashtable connectionPools = new Hashtable();
}
