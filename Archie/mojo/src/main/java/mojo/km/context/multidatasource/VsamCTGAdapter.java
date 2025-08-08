/*
 * Created on Jun 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package mojo.km.context.multidatasource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ibm.ctg.client.JavaGateway;

import mojo.km.config.ConnectionProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.exceptionhandling.ExceptionHandler;

/**
 * @author mpatino
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class VsamCTGAdapter extends AbstractAdapter
{

    private ConnectionProperties cProp;

    //Connection resource = null;
    private String name = "vsamctg";

    String region;

    JavaGateway resource;

    Map statements = new HashMap();

    private void buildStatements()
    {

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
        catch (IOException e)
        {
            ExceptionHandler.executeCallbacks(new ConnectionException(this.name));
        }
    }

    protected void finalize()
    {
        close();
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

    public String getRegion()
    {
        return region;
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
     * @see IConnection#getStatement(String)
     *  
     */
    public Object getStatement(String key)
    {
        return statements.get(key);
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
            String sPort = cProp.getPort();
            if (sPort != null)
            {
                Integer port = new Integer(sPort);
                region = cProp.getRegion();
                open(cProp.getURL(), port.intValue());
            }
            else
            {
                String msg = this.name + " connection has a missing port setting.";
                ExceptionHandler.executeCallbacks(new ConnectionException(msg));
            }

        }
        catch (Exception e)
        {
            String msg = "Unable to initialize " + aName;
            ExceptionHandler.executeCallbacks(new ConnectionException(msg));
        }

    }

    public boolean isBad()
    {
        if (resource == null)
        {
            return true;
        }
        return !resource.isOpen();
    }

    /**
     * @see IConnection#open(String, String, String)
     *  
     */
    public void open(String URL, int iPort)
    {
        try
        {
            resource = new JavaGateway(URL, iPort);
        }
        catch (IOException e1)
        {
            ExceptionHandler.executeCallbacks(new ConnectionException(this.name));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IConnection#open(java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public void open(String URL, String userID, String password)
    {
        // TODO Auto-generated method stub

    }

    public void setName(String aName)
    {
        this.name = aName;
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
