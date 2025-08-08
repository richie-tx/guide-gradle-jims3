/*
 * Created on Mar 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mojo.km.context.multidatasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import mojo.km.config.SaveCallbackProperties;
import mojo.km.config.CallbackProperties;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.exceptionhandling.ExceptionHandler;

/**
 * Responsible for handling JDBC datasource adapter
 * This is implementation for MS SQLServer 2005
 * 
 * @author Nick Popov
 */
public class JDBCMSSQLAdapter extends AbstractAdapter
{

    private ConnectionProperties cProp;
    private String name;

    private Connection resource;

    private Map statements = new HashMap();

    public void addStatement(CallbackProperties eQuery, EntityMappingProperties eMap)
    {
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

            /*
             * if (eQuery instanceof SaveCallbackProperties) {
             * setStringsToNumeric((SaveCallbackProperties) eQuery, eParm); }
             */

            String assoc = eParm.getAssociationType();

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
                    System.out.println("value = " + value);
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
                    // CREATEJIMS2USER OR UPDATEJIMS2USER WILL BE ADDED TO ALL
                    // UPDATES

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
                lStatementName = anEvent + "::" + eMap.getEntity() + "::" + eMap.getContextKey();
                if (columnList.equals(""))
                {
                    System.out.println("columnlist is empty for " + lStatementName);
                }

                if (eQuery.getMappingMethodName().startsWith("retrieve"))
                {
                    lStatementName = anEvent + "::" + eMap.getEntity() + "::" + eMap.getContextKey();
                    lParmMap.append("SELECT ");
                    lParmMap.append(columnList.toString());
                    lParmMap.append(" FROM ");
                    lParmMap.append(cProp.getDb2Schema());
                    lParmMap.append(source);
                    if (whereClause.equals("none"))
                    {
                    }
                    else
                    {
                        lParmMap.append(" WHERE ");
                        lParmMap.append(whereClause);
                    }                    
                }
                System.out.println("creating prepared statement named " + lStatementName);
                System.out.println("creating prepared statement " + lParmMap.toString());
                lstatement = (PreparedStatement) resource.prepareStatement(lParmMap.toString());
                statements.put(lStatementName, lstatement);
            }

            if (eQuery.getMappingMethodName().startsWith("save"))
            {
                SaveCallbackProperties eQueryS = (SaveCallbackProperties) eQuery;
                String whereClause = eQueryS.getWhereClause();
                lStatementName = "Insert::" + eMap.getEntity() + "::" + eMap.getContextKey();
                lParmMap = new StringBuffer();

                lParmMap.append("INSERT INTO ");
                lParmMap.append(cProp.getDb2Schema());
                lParmMap.append(source);
                lParmMap.append(" (");

                lParmMap.append(columnList);
                lParmMap.append(") VALUES (");
                lParmMap.append(values);
                lParmMap.append(")");

                System.out.println("creating prepared statement named " + lStatementName);
                System.out.println("creating prepared statement " + lParmMap.toString());

                lstatement = (PreparedStatement) resource.prepareStatement(lParmMap.toString());
                statements.put(lStatementName, lstatement);

                /* RRY commented out
                lParmMap = new StringBuffer();
                lParmMap.append("UPDATE ");
                lParmMap.append(cProp.getDb2Schema());
                lParmMap.append(source);
                lParmMap.append(" SET ");
                lParmMap.append(updateList);

                if ((whereClause == null) || (whereClause.equals("none")))
                {
                    System.err.println("no where clause on update");
                }
                else
                {
                    lParmMap.append(" WHERE ");
                    lParmMap.append(whereClause);
                }
                lStatementName = "Update::" + eMap.getEntity() + "::" + eMap.getContextKey();
                System.out.println("creating prepared statement named " + lStatementName);
                System.out.println("creating prepared statement " + lParmMap.toString());
                lstatement = (PreparedStatement) resource.prepareStatement(lParmMap.toString());
                statements.put(lStatementName, lstatement);*/
            }

        }
        catch (SQLException e1)
        {
            ConnectionException ce = new ConnectionException(this.name);
            ExceptionHandler.executeCallbacks(ce);
            throw ce;
        }
    }

    private void buildStatements()
    {
        String lStatementName;
        Iterator i = MojoProperties.getInstance().getEntityMaps();
        PreparedStatement lstatement = null;
        Integer lPISave = new Integer(1);
        StringBuffer columnList = new StringBuffer();
        StringBuffer whereList = new StringBuffer();
        StringBuffer values = new StringBuffer();
        StringBuffer updateList = new StringBuffer();
        java.util.ArrayList parms = new java.util.ArrayList();
        java.util.ArrayList fields = new java.util.ArrayList();
        while (i.hasNext())
        {
            EntityMappingProperties eMap = (EntityMappingProperties) i.next();
            Iterator qCallbacks = eMap.getQueryCallbacks();
            while (qCallbacks.hasNext())
            {
                EventQueryProperties eQuery = (EventQueryProperties) qCallbacks.next();
                String x = eQuery.getConnectionName();
                if (x.equals(this.name))
                {
                    addStatement(eQuery, eMap);
                }
            }

            Iterator sCallbacks = eMap.getSaveCallbacks();
            while (sCallbacks.hasNext())
            {
                SaveCallbackProperties sQuery = (SaveCallbackProperties) sCallbacks.next();
                String x = sQuery.getConnectionName();

                if (x.equals(name))
                {
                    addStatement(sQuery, eMap);
                }
            }
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
                ConnectionException ce = new ConnectionException(this.name);
                ExceptionHandler.executeCallbacks(ce);
                throw ce;
            }
        }
    }

    protected void finalize()
    {
        close();
    }

    /* (non-Javadoc)
     * @see mojo.km.context.multidatasource.AbstractAdapter#getInsertStatement(mojo.km.config.SaveCallbackProperties)
     */
    public Object getInsertStatement(SaveCallbackProperties sProps)
    {
        throw new UnsupportedOperationException("getInsertStatement has not been implemented.");
    }

    /* (non-Javadoc)
     * @see mojo.km.context.multidatasource.AbstractAdapter#getQueryStatement(int, mojo.km.config.EventQueryProperties)
     */
    public Object getQueryStatement(EventQueryProperties eProps)
    {
        throw new UnsupportedOperationException("getQueryStatement has not been implemented.");
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

    /* (non-Javadoc)
     * @see mojo.km.context.multidatasource.AbstractAdapter#getSaveStatement(int, mojo.km.config.SaveCallbackProperties)
     */
    public Object getSaveStatement(int type, SaveCallbackProperties sProps)
    {
        throw new UnsupportedOperationException("getSaveStatement has not been implemented.");
    }

    /**
     * @see IConnection#getStatement(String)
     */
    public Object getStatement(String key)
    {
        if (isBad())
        {
            init(this.name);
            statements.clear();
        }
        if (!statements.containsKey(key))
        {
            StringTokenizer keyTokens = new StringTokenizer(key, "::");
            if (keyTokens.countTokens() == 3)
            {
                String statementType = keyTokens.nextToken();
                String entityName = keyTokens.nextToken();
                String contextKey = keyTokens.nextToken();
                EntityMappingProperties eMap = MojoProperties.getInstance().getEntityMap(contextKey);
                Iterator qCallbacks = eMap.getQueryCallbacks();
                while (qCallbacks.hasNext())
                {
                    EventQueryProperties eQuery = (EventQueryProperties) qCallbacks.next();
                    String x = eQuery.getConnectionName();
                    if (x.equals(this.name))
                    {
                        addStatement(eQuery, eMap);
                    }
                }

                Iterator sCallbacks = eMap.getSaveCallbacks();
                while (sCallbacks.hasNext())
                {
                    SaveCallbackProperties sQuery = (SaveCallbackProperties) sCallbacks.next();
                    String x = sQuery.getConnectionName();
                    if (x.equals(this.name))
                    {
                        addStatement(sQuery, eMap);
                    }
                }
            }
        }
        return statements.get(key);
    }

    /* (non-Javadoc)
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
        this.setName(aName);

        try
        {
            statements.clear();
            cProp = MojoProperties.getInstance().getConnectionProperties(name);
            String driver = cProp.getDriver();
            if (cProp.getName().startsWith("JDBC"))
            {
                Class.forName(driver);

                open(cProp.getURL(), cProp.getUserID(), cProp.getPassword());
            }
        }
        catch (ClassNotFoundException e)
        {
            ConnectionException ce = new ConnectionException("Unable to load driver for: " + this.name);
            ExceptionHandler.executeCallbacks(ce);
            throw ce;
        }
    }

    /**
     * @see IConnection#isAvailable()
     */
    public synchronized boolean isAvailable()
    {
        return ((ConnectionManager.ConnectionPool) ConnectionManager.connectionPools.get(name)).available
                .contains(this);
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
            catch (Exception es)
            {
                ConnectionException ce = new ConnectionException(this.name);
                ExceptionHandler.executeCallbacks(ce);
                throw ce;
            }
        }
        return retVal;
    }

    /**
     * @see IConnection#makeAvailable()
     * @modelguid {55A0F619-D96E-4CFD-A999-D9A1308F2142}
     */
    public synchronized void makeAvailable()
    {
        if (((ConnectionManager.ConnectionPool) ConnectionManager.connectionPools.get(name)).unavailable.contains(this))
        {
            ((ConnectionManager.ConnectionPool) ConnectionManager.connectionPools.get(name)).unavailable.remove(this);
        }
        ((ConnectionManager.ConnectionPool) ConnectionManager.connectionPools.get(name)).available.add(this);
    }

    /**
     * @see IConnection#makeUnavailable()
     * @modelguid {5F3D7C1F-B79B-44CF-86BC-E0681952128E}
     */
    public synchronized void makeUnavailable()
    {
        if (((ConnectionManager.ConnectionPool) ConnectionManager.connectionPools.get(name)).available.contains(this))
        {
            ((ConnectionManager.ConnectionPool) ConnectionManager.connectionPools.get(name)).available.remove(this);
        }
        ((ConnectionManager.ConnectionPool) ConnectionManager.connectionPools.get(name)).unavailable.add(this);
    }

    public void open(String URL, int iPort)
    {
    }

    /**
     * @see IConnection#open(String, String,
     *      String)
     * @modelguid {83289EEB-D22C-412F-ACD6-0FDF942A434C}
     */
    public void open(String URL, String userID, String password)
    {
        try
        {
            boolean autoCommit = false;

            
            // NP ?!?!
            Properties connProp = new Properties();
            // connProp.setProperty("CURSORHOLD", (new Integer(1)).toString());
            connProp.setProperty("UID", userID);
            connProp.setProperty("PWD", password);
            // NP

            resource = DriverManager.getConnection(URL, userID, password);
            resource.setAutoCommit(autoCommit);

        }
        catch (SQLException e1)
        {
            ConnectionException ce = new ConnectionException(this.name);
            ExceptionHandler.executeCallbacks(ce);
            throw ce;
        }
    }

    public void setName(String aName)
    {
        this.name = aName;
    }
}
