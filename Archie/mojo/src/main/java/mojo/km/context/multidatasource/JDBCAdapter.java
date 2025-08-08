package mojo.km.context.multidatasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Level;

import mojo.km.config.SaveCallbackProperties;
import mojo.km.config.CallbackProperties;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.logging.LogUtil;

/**
 * Responsible for handling JDBC datasource adapter
 * 
 * @author Jim Fisher
 */
public class JDBCAdapter extends AbstractAdapter
{
    private ConnectionProperties cProp;

    private String name;

    private Connection resource;

    public void addStatement(CallbackProperties eQuery, EntityMappingProperties eMap)
    {
        // TODO optimize query creation

        String s = eMap.getProperty("name");
        String x = eQuery.getConnectionName();

        String source = eQuery.getFileName();
        if (source == null)
        {
            return;
        }
        String connName = eQuery.getConnectionName();
        PreparedStatement lstatement = null;
        String lStatementName = null;
        StringBuffer columnList = new StringBuffer();
        StringBuffer whereList = new StringBuffer();
        StringBuffer values = new StringBuffer();
        StringBuffer updateList = new StringBuffer();
        Map parms = new HashMap();
        Map fields = new HashMap();
        Integer lPosSave = new Integer(1);
        Integer lPISave = new Integer(0);

        Iterator lPump = eQuery.getParmsIterator();
        while (lPump.hasNext())
        {
            ParmMappingProperties eParm = (ParmMappingProperties) lPump.next();
            String lParmIndex = eParm.getParmIndex();
            Integer lPI = new Integer(lParmIndex);
            if (lPI.intValue() > lPISave.intValue())
            {
                lPISave = lPI;
            }
            String lOIDName = eParm.getDataItemName();
            parms.put((Object) lPI, (Object) lOIDName);

        }

        whereList = new StringBuffer();
        int ix = lPISave.intValue();

        for (int iz = 1; iz <= ix; iz++)
        {
            Integer lPos = new Integer(iz);
            if (parms.get((Object) lPos) != null)
            {
                whereList.append(parms.get((Object) lPos));
            }
        }

        lPosSave = new Integer(0);
        Iterator lFump = eQuery.getFieldsIterator();
        while (lFump.hasNext())
        {
            FieldMappingProperties eParm = (FieldMappingProperties) lFump.next();

            String lPosition = eParm.getParmIndex();
            Integer lPos = new Integer(lPosition);
            if (lPos.intValue() > lPosSave.intValue())
            {
                lPosSave = lPos;

            }
            String lFieldName = eParm.getDataItemName();
            if (lPos.intValue() != 0)
            {
                fields.put((Object) lPos, (Object) lFieldName);
            }
        }

        // columnList is list of columns used by the select and insert sql
        // statements
        // updateList is field=value pairs used for the update sql statements
        // values is list of placeholders for the select statements
        columnList = new StringBuffer();
        updateList = new StringBuffer();
        values = new StringBuffer();
        ix = fields.size();
        String value = null;
        for (int iz = 0; iz <= lPosSave.intValue(); iz++)
        {
            Integer lPos = new Integer(iz);
            if (fields.get((Object) lPos) != null)
            {
                if (iz == ix)
                {
                    columnList.append((String) fields.get((Object) lPos));
                    values.append("?");
                    value = (String) fields.get((Object) lPos);
                    if (value.equals("CREATEUSER"))
                    {
                        value = "UPDATEUSER";
                    }
                    updateList.append(value);
                    updateList.append("= ?");
                }
                else
                {
                    columnList.append(fields.get((Object) lPos));
                    columnList.append(", ");
                    values.append("?, ");
                    value = (String) fields.get((Object) lPos);
                    if (value.equals("CREATEUSER"))
                    {
                        value = "UPDATEUSER";
                    }
                    // add jims2account stuff RAC.
                    // CREATEJIMS2USER OR UPDATEJIMS2USER WILL BE ADDED TO ALL UPDATES

                    if (value.equals("CREATEJIMS2USER"))
                    {
                        value = "UPDATEJIMS2USER";
                    }

                    updateList.append(value);
                    updateList.append("= ?, ");
                }
            }
        }

        try
        {
            StringBuffer lParmMap = new StringBuffer();
            if (eQuery instanceof EventQueryProperties)
            {
                EventQueryProperties eQueryP = (EventQueryProperties) eQuery;
                String anEvent = eQueryP.getEventName();
                String whereClause = eQueryP.getWhereClause();

                StringBuffer buffer = new StringBuffer();
                buffer.append(anEvent);
                buffer.append("::");
                buffer.append(eMap.getEntity());
                buffer.append("::");
                buffer.append(eMap.getContextKey());

                lStatementName = buffer.toString();

                if (eQuery.getMappingMethodName().startsWith("retrieve"))
                {
                    lParmMap.append("SELECT ");
                    lParmMap.append(columnList.toString());
                    lParmMap.append(" FROM ");
                    lParmMap.append(cProp.getDb2Schema());
                    lParmMap.append(".");
                    lParmMap.append(source);
                    if (whereClause.equals("none"))
                    {
                    }
                    else
                    {
                        lParmMap.append(" WHERE ");
                        lParmMap.append(whereClause);
                    }

                    if (!eQueryP.getConnectionName().equals("JDBCOracle"))
                    {
                        lParmMap.append(" FOR READ ONLY ");
                    }

                }

                lstatement = (PreparedStatement) resource.prepareStatement(lParmMap.toString());
            }
            else if (eQuery.getMappingMethodName().startsWith("save"))
            {
                SaveCallbackProperties eQueryS = (SaveCallbackProperties) eQuery;
                String whereClause = eQueryS.getWhereClause();
                lStatementName = "Insert::" + eMap.getEntity() + "::" + eMap.getContextKey();
                lParmMap = new StringBuffer();

                lParmMap.append("INSERT INTO ");
                lParmMap.append(cProp.getDb2Schema() + ".");
                lParmMap.append(source);
                lParmMap.append(" (");

                lParmMap.append(columnList);
                lParmMap.append(") VALUES (");
                lParmMap.append(values);
                lParmMap.append(")");

                //System.out.println("creating prepared statement named " + lStatementName);
                //System.out.println("creating prepared statement " + lParmMap.toString());

                lstatement = (PreparedStatement) resource.prepareStatement(lParmMap.toString());

                lParmMap = new StringBuffer();
                lParmMap.append("UPDATE ");
                lParmMap.append(cProp.getDb2Schema());
                lParmMap.append(".");
                lParmMap.append(source);
                lParmMap.append(" SET ");
                lParmMap.append(updateList);

                if ((whereClause == null) || (whereClause.equals("none")))
                {
                    //System.err.println("no where clause on update");
                }
                else
                {
                    lParmMap.append(" WHERE ");
                    lParmMap.append(whereClause);
                }

                lStatementName = "Update::" + eMap.getEntity() + "::" + eMap.getContextKey();
                //System.out.println("creating prepared statement named " + lStatementName);
                //System.out.println("creating prepared statement " + lParmMap.toString());
                lstatement = (PreparedStatement) resource.prepareStatement(lParmMap.toString());
            }

        }
        catch (SQLException e)
        {
            throw new ConnectionException(this.name, e);
        }
    }

    /**
     * @see IConnection#close()
     */
    public void close()
    {
        if (resource != null)
        {
            try
            {
                resource.close();
            }
            catch (SQLException e)
            {
                throw new ConnectionException(this.name, e);
            }
        }
    }

    public Object getInsertStatement(SaveCallbackProperties sProps)
    {
        throw new UnsupportedOperationException("getInsertStatement has not been implemented.");

        /*
         * if (isBad()) { init(this.name); statements.clear(); }
         * 
         * String key = sProps.getInsertKey();
         * 
         * Statement statement = (Statement) statements.get(key); if (statement == null) { String queryString =
         * sProps.getInsertQuery(); LogUtil.log(Level.DEBUG, "prepare statement: "+queryString); try { statement =
         * this.resource.prepareStatement(queryString); statements.put(key, statement); } catch (SQLException e) { throw new
         * ConnectionException(this.name, e); } }
         * 
         * return statement;
         */
    }

    public Object getQueryStatement(EventQueryProperties qProps)
    {
        throw new UnsupportedOperationException("getQueryStatement has not been implemented.");

        /*
         * if (isBad()) { init(this.name); statements.clear(); }
         * 
         * String key = qProps.getQueryKey();
         * 
         * Statement statement = (Statement) statements.get(key); if (statement == null) { String queryString =
         * qProps.getQueryString(); LogUtil.log(Level.FINEST, "prepare statement: "+queryString);
         * 
         * try { statement = this.resource.prepareStatement(queryString); statements.put(key, statement); } catch (SQLException e) {
         * throw new ConnectionException(this.name, e); } }
         * 
         * return statement;
         */
    }

    public String getRegion()
    {
        return null;
    }

    /**
     * @see IConnection#getResource()
     */
    public Object getResource()
    {
        if (isBad())
        {
            init(name);
        }
        return resource;
    }

    public Object getStatement(String aQuery)
    {
        if (isBad())
        {
            init(this.name);
        }

        if (LogUtil.isTraceEnabled())
        {
            LogUtil.log(Level.TRACE, "prepare statement: " + aQuery);
        }

        Statement statement;
        try
        {
            statement = this.resource.prepareStatement(aQuery);
        }
        catch (SQLException e)
        {
            throw new ConnectionException(this.name, e);
        }

        return statement;

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

    /**
     * @see IConnection#init(String)
     */
    public void init(String aName)
    {
        this.name = aName;

        try
        {
            cProp = MojoProperties.getInstance().getConnectionProperties(name);

            String driver = cProp.getDriver();

            Class.forName(driver);

            open(cProp.getURL(), cProp.getUserID(), cProp.getPassword());
        }
        catch (ClassNotFoundException e)
        {
            throw new ConnectionException("Unable to load driver for: " + this.name, e);
        }
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
            Statement statement = resource.createStatement();
            retVal = resource.isClosed();
            if (retVal)
            {
                return retVal;
            }

            retVal = false;
        }
        catch (Exception e)
        {
            try
            {
                if (!resource.isClosed())
                {
                    resource.close();
                }
            }
            catch (Exception e2)
            {
            }
            throw new ConnectionException(this.name, e);
        }
        return retVal;
    }

    public void open(String URL, int iPort)
    {
    }

    /**
     * @see IConnection#open(String, String, String)
     */
    public void open(String URL, String userID, String password)
    {
        try
        {
            boolean autoCommit = false;

            resource = DriverManager.getConnection(URL, userID, password);
            resource.setAutoCommit(autoCommit);

        }
        catch (SQLException e)
        {
            String msg = "failed to create connection (" + this.name + ") to: " + URL;
            throw new ConnectionException(msg, e);
        }
    }

    public void setName(String aName)
    {
        this.name = aName;
    }
}
