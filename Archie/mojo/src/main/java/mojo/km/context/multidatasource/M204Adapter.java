package mojo.km.context.multidatasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import mojo.km.config.ConnectionProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.exceptionhandling.ExceptionHandler;

/**
 * Responsible for handling JDBC datasource adapter
 * 
 * @author Robbie Cooper & Mary Patino
 *  
 */
public class M204Adapter extends AbstractAdapter
{
    private Connection resource;

    private Map statements;

    private String name;

    public M204Adapter()
    {
        super();
        this.statements = new HashMap();
    }

    /**
     * @see IConnection#close()
     *  
     */
    public void close()
    {
        try
        {
            resource.close();
        }
        catch (SQLException e)
        {
            ExceptionHandler.executeCallbacks(new ConnectionException(this.name));
        }

    }

    /**
     * @see IConnection#getResource()
     *  
     */
    public Object getResource()
    {
        if (isBad())
        {
            init(this.name);
        }
        return resource;
    }

    /**
     * @see IConnection#open(String, String, String)
     *  
     */
    public void open(String URL, String userID, String password)
    {
        try
        {
            //jdbc:j204://10.5.22.104:2505/RCL/ji005/xxxx
            StringBuffer buffer = new StringBuffer(30);
            buffer.append(URL);
            buffer.append("/RCL/");
            buffer.append(userID);
            buffer.append("/");
            buffer.append(password);
            resource = DriverManager.getConnection(buffer.toString());
        }
        catch (SQLException e)
        {
            throw new ConnectionException(this.name, e);
        }
    }

    private void buildStatements()
    {

    }

    /**
     * @see IConnection#init(String)
     *  
     */
    public void init(String aName)
    {
        try
        {
            this.setName(aName);

            ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties(name);
            if (cProp.getName().equals("M204"))
            {
                String driver = cProp.getDriver();
                Class.forName(driver);
                DriverManager.setLoginTimeout(10);
                open(cProp.getURL(), cProp.getUserID(), cProp.getPassword());
            }
        }
        catch (ClassNotFoundException e)
        {
            ConnectionException ce = new ConnectionException("Cannot load driver for M204: " + e.getMessage());
            ExceptionHandler.executeCallbacks(ce);
        }
    }

    /**
     * @see IConnection#getStatement(String)
     *  
     */
    public Object getStatement(String key)
    {
        if (isBad())
        {
            init(this.name);
        }
        return statements.get(key);
    }

    public void open(String URL, int iPort)
    {
    }

    public boolean isBad()
    {
        boolean retVal = true;
        try
        {
            if (resource == null)
            {
                return true;
            }
            // do not remove this it cleans up the connection if a logon becomes unusable
            // check with Jim F or Robbie
            // query: logon {userid};{password};J2CON TEST;
            String userId = "jircl1";
            String password = "rcl@jims";
            
			Statement statement = resource.createStatement();
            String query = "LOGON JIRCL1;RCL@JIMS;J2CON TEST;";            
            statement.execute(query);
            statement.close();
            retVal = resource.isClosed();			            
        }
        catch (Throwable e)
        {
            try
            {
                if (!resource.isClosed())
                {
                    resource.close();
                }
            }
            catch (Exception es)
            {
                ExceptionHandler.executeCallbacks(new ConnectionException("Cannot close M204 resource: "
                        + e.getMessage()));
            }

        }
        return retVal;
    }

    public String getRegion()
    {
        return null;
    }

    public void setName(String aName)
    {
        this.name = aName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.AbstractAdapter#getQueryStatement(int,
     *      mojo.km.config.EventQueryProperties)
     */
    public Object getQueryStatement(EventQueryProperties eProps)
    {
        throw new UnsupportedOperationException("getQueryStatement has not been implemented.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.AbstractAdapter#getInsertStatement(mojo.km.config.SaveCallbackProperties)
     */
    public Object getInsertStatement(SaveCallbackProperties sProps)
    {
        throw new UnsupportedOperationException("getInsertStatement has not been implemented.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.AbstractAdapter#getUpdateStatement(mojo.km.config.SaveCallbackProperties)
     */
    public Object getUpdateStatement(SaveCallbackProperties sProps)
    {
        throw new UnsupportedOperationException("getUpdateStatement has not been implemented.");
    }

}
