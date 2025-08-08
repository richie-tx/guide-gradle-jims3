package mojo.km.context.multidatasource;

import java.sql.*;

import mojo.km.config.CallbackProperties;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.exceptionhandling.ExceptionHandler;
import java.util.*;

/**
 * Responsible for handling JDBC datasource adapter
 * 
 * @author Eric A Amundson
 */
public class J204Adapter extends AbstractAdapter
{

    private ConnectionProperties cProp;
    private String name;

    /** @modelguid {BF79E49C-A572-4DBF-8D1D-4332DF734C98} */
    Connection resource = null;

    /** @modelguid {C5ECAD77-CECF-4347-B537-7ACB839D4BB4} */
    Map statements = new HashMap();

    private void addStatement(CallbackProperties eQuery, EntityMappingProperties eMap)
    {
        String s = eMap.getProperty("name");
        String x = eQuery.getConnectionName();
        //System.out.println("EntityName " + s + "ConnectionName = " + x);

        String source = eQuery.getFileName();
        if (source == null)
        {
            //	System.out.println("source is null");
            return;
        }
        String connName = eQuery.getConnectionName();
        PreparedStatement lstatement = null;
        String lStatementName = null;
        StringBuffer columnList = new StringBuffer();
        StringBuffer whereList = new StringBuffer();
        StringBuffer values = new StringBuffer();
        StringBuffer updateList = new StringBuffer();
        HashMap parms = new HashMap();
        HashMap fields = new HashMap();
        Integer lPosSave = new Integer(1);
        Integer indexSave = new Integer(0);

        List testList = new ArrayList();

        Iterator parmIter = eQuery.getParmsIterator();
        while (parmIter.hasNext())
        {
            ParmMappingProperties eParm = (ParmMappingProperties) parmIter.next();
            String lParmIndex = eParm.getParmIndex();
            Integer index = new Integer(lParmIndex);
            if (index.intValue() > indexSave.intValue())
            {
                indexSave = index;
            }
            String lOIDName = eParm.getDataItemName();
            //	System.out.println("PUMP=" + lPI.intValue() + lOIDName);
            parms.put((Object) index, (Object) lOIDName);

        }

        //What does whereList do?

        whereList = new StringBuffer();
        int ix = indexSave.intValue();
        //	System.out.println("Save PI = " + ix);

        for (int iz = 1; iz <= ix; iz++)
        {
            Integer lPos = new Integer(iz);
            if (parms.get((Object) lPos) != null)
            {
                whereList.append(parms.get((Object) lPos));
            }
        }

        lPosSave = new Integer(0);
        Iterator fieldIter = eQuery.getFieldsIterator();

        while (fieldIter.hasNext())
        {
            FieldMappingProperties eParm = (FieldMappingProperties) fieldIter.next();
            String associationType = eParm.getAssociationType();

            String lPosition = eParm.getParmIndex();
            Integer lPos = new Integer(lPosition);
            if (lPos.intValue() > lPosSave.intValue())
            {
                lPosSave = lPos;
            }
            String dataItemName = eParm.getDataItemName();

            if (lPos.intValue() != 0)
            {
                fields.put((Object) lPos, (Object) dataItemName);
            }

        }

        //columnList is list of columns used by the select and insert sql
        // statements
        //updateList is field=value pairs used for the update sql statements
        //values is list of placeholders for the select statements
        columnList = new StringBuffer();
        updateList = new StringBuffer();
        values = new StringBuffer();
        ix = fields.size();

        for (int iz = 0; iz <= lPosSave.intValue(); iz++)
        {

            Integer lPos = new Integer(iz);
            if (fields.get((Object) lPos) != null)
            {
                //If index equals size of list
                if (iz == ix)
                {
                    columnList.append((String) fields.get((Object) lPos));
                    values.append("?");
                    updateList.append(fields.get((Object) lPos));
                    updateList.append("= ?");
                }
                else
                {
                    columnList.append(fields.get((Object) lPos));
                    columnList.append(", ");
                    values.append("?, ");
                    updateList.append(fields.get((Object) lPos));
                    updateList.append("= ?, ");
                }

            }
        }

        try
        {
            StringBuffer lParmMap = new StringBuffer();
            if (eQuery instanceof EventQueryProperties) //what does this mean?
            {
                EventQueryProperties eQueryP = (EventQueryProperties) eQuery;
                String anEvent = eQueryP.getEventName();
                String whereClause = eQueryP.getWhereClause(); //here is what I
                // need to
                // replace
                lStatementName = anEvent + "::" + eMap.getEntity();
                if (columnList.equals(""))
                {
                    System.out.println("columnlist is empty for " + lStatementName);
                }

                if (eQuery.getMappingMethodName().startsWith("retrieve"))
                {
                    lStatementName = anEvent + "::" + eMap.getEntity() + "::" + eMap.getContextKey();
                    lParmMap.append("SELECT ");
                    lParmMap.append(columnList);
                    lParmMap.append(" FROM ");
                    //lParmMap.append(cProp.getDb2Schema() + ".");
                    lParmMap.append(source);
                    if (whereClause.equals("none"))
                    {
                        //	System.out.println("no where clause");
                    }
                    else
                    {
                        lParmMap.append(" WHERE ");
                        lParmMap.append(whereClause);
                    }
                    lParmMap.append(" FOR READ ONLY ");

                }
                System.out.println("creating prepared statement named " + lStatementName);
                System.out.println("creating prepared statement " + lParmMap.toString());
                lstatement = (PreparedStatement) resource.prepareStatement(lParmMap.toString());
                statements.put(lStatementName, lstatement);
            }
            //			this was commented out because we will not do updates with SQL
            // for 204 per Jason
            //			if (eQuery.getMappingMethodName().startsWith("save"))
            //			{
            //				SaveCallbackProperties eQueryS = (SaveCallbackProperties) eQuery;
            //				String whereClause = eQueryS.getWhereClause();
            //				//lStatementName = "Insert::" + eMap.getEntity();
            //				lStatementName = "Insert::" + eMap.getEntity() + "::" +
            // eMap.getContextKey();
            //				//
            //				lParmMap = new StringBuffer();
            //
            //				lParmMap.append("INSERT INTO ");
            //				//lParmMap.append(cProp.getDb2Schema() + ".");
            //				lParmMap.append(source);
            //				lParmMap.append(" (");
            //				lParmMap.append("RECTYPE, ");
            //				lParmMap.append(columnList.toString());
            //				lParmMap.append(") VALUES ('" + eQuery.getSource() + "', ");
            //				lParmMap.append(values.toString());
            //				lParmMap.append(")");
            //
            //				System.out.println("creating prepared statement named " +
            // lStatementName);
            //				System.out.println("creating prepared statement " +
            // lParmMap.toString());
            //
            //				lstatement = (PreparedStatement)
            // resource.prepareStatement(lParmMap.toString());
            //				statements.put(lStatementName, lstatement);
            //
            //				lParmMap = new StringBuffer();
            //				lParmMap.append("UPDATE ");
            //				// lParmMap.append(cProp.getDb2Schema() + ".");
            //				lParmMap.append(source);
            //				lParmMap.append(" SET ");
            //				lParmMap.append(updateList.toString());
            //
            //				if ((whereClause == null) || (whereClause.equals("none")))
            //				{
            //
            //				}
            //				else
            //				{
            //					lParmMap.append(" WHERE ");
            //					lParmMap.append(whereClause);
            //					//lParmMap.append(" AND RECTYPE="+ eQuery.getSource());
            //
            //				}
            //				lStatementName = "Update::" + eMap.getEntity()+ "::" +
            // eMap.getContextKey();
            //			
            //				System.out.println("creating prepared statement named " +
            // lStatementName);
            //				System.out.println("creating prepared statement " +
            // lParmMap.toString());
            //				lstatement = (PreparedStatement)
            // resource.prepareStatement(lParmMap.toString());
            //				statements.put(lStatementName, lstatement);
            //
            //			}

        }
        catch (SQLException e1)
        {
            String msg = this.name + " connection exception. Unable to create statement: ";
            msg += e1.getMessage();
            ConnectionException ce = new ConnectionException(msg);
            ExceptionHandler.executeCallbacks(ce);
        }
    }

    /** @modelguid {BB635437-B0E0-456A-865F-7D2B6D452A31} */
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
        ArrayList parms = new ArrayList();
        ArrayList fields = new ArrayList();
        while (i.hasNext())
        {
            EntityMappingProperties eMap = (EntityMappingProperties) i.next();
            Iterator qCallbacks = eMap.getQueryCallbacks();
            while (qCallbacks.hasNext())
            {

                EventQueryProperties eQuery = (EventQueryProperties) qCallbacks.next();
                System.out.println("equery=" + eQuery.getName());
                String x = eQuery.getConnectionName();
                System.out.println("ConnectionName = " + x);
                if (x.equals(this.name))
                {
                    addStatement(eQuery, eMap);

                }

            }
            // this was commented out because we will not do updates with SQL
            // for 204 per Jason
            //			Iterator sCallbacks = eMap.getSaveCallbacks();
            //			while (sCallbacks.hasNext())
            //			{
            //
            //				SaveCallbackProperties sQuery = (SaveCallbackProperties)
            // sCallbacks.next();
            //				String x = sQuery.getConnectionName();
            //				//System.out.println("ConnectionName = " + x);
            //				if (x.equals(NAME))
            //				{
            //					addStatement(sQuery, eMap);
            //
            //				}
            //
            //			}

        }
    }

    public void close()
    {
        try
        {
            resource.close();
        }
        catch (SQLException e)
        {
            ConnectionException ce = new ConnectionException("Cannot load driver for J204: " + e.getMessage());
            ExceptionHandler.executeCallbacks(ce);
        }
    }

    protected void finalize()
    {
        close();
    }

    public Object getInsertStatement(SaveCallbackProperties sProps)
    {
        return null;
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

    //	public void addStatement(
    //		CallbackProperties eQuery,
    //		EntityMappingProperties eMap)
    //	{
    //		String s = eMap.getProperty("name");
    //		String x = eQuery.getConnectionName();
    //		//System.out.println("EntityName " + s + "ConnectionName = " + x);
    //
    //		String source = eQuery.getFileName();
    //		if (source == null)
    //		{
    //		// System.out.println("source is null");
    //			return;
    //		}
    //		String connName = eQuery.getConnectionName();
    //		PreparedStatement lstatement = null;
    //		String lStatementName = null;
    //		StringBuffer columnList = new StringBuffer();
    //		StringBuffer whereList = new StringBuffer();
    //		StringBuffer values = new StringBuffer();
    //		StringBuffer updateList = new StringBuffer();
    //		HashMap parms = new HashMap();
    //		//java.util.ArrayList fields = new java.util.ArrayList();
    //		HashMap fields = new HashMap();
    //		Integer lPosSave = new Integer(1);
    //		Integer lPISave = new Integer(0);
    //
    //		 		Iterator lPump = eQuery.getParms();
    //		 		while (lPump.hasNext()) {
    //		 			ParmMappingProperties eParm = (ParmMappingProperties) lPump.next();
    //		 			String lParmIndex = eParm.getParmIndex();
    //		 			Integer lPI = new Integer(lParmIndex);
    //		 			if (lPI.intValue() > lPISave.intValue()) {
    //		 				lPISave = lPI;
    //		 
    //		 			}
    //		 			String lOIDName = eParm.getDataItemName();
    //		 		// System.out.println("PUMP=" + lPI.intValue() + lOIDName);
    //		 			parms.put((Object)lPI, (Object) lOIDName);
    //		 
    //		 		}
    //		 
    //		 		
    //				whereList = new StringBuffer();
    //				int ix = lPISave.intValue();
    //			// System.out.println("Save PI = " + ix);
    //		
    //				for (int iz = 1; iz <= ix; iz++) {
    //					Integer lPos = new Integer(iz);
    //			// System.out.println("lPos = " + lPos);
    //					if (parms.get((Object)lPos) != null) {
    //			// System.out.println("I'm in " + parms.get((Object)lPos));
    //						whereList.append(parms.get((Object)lPos));
    //					}
    //				}
    //
    //		lPosSave = new Integer(0);
    //		Iterator lFump = eQuery.getFields();
    //		while (lFump.hasNext())
    //		{
    //			FieldMappingProperties eParm =
    //				(FieldMappingProperties) lFump.next();
    //			String assoc = eParm.getAssociationType();
    //			//if (assoc.equals("Reference") || assoc.equals("Collection")){
    //			// System.out.println("Association type = " + assoc + " - " +
    // eParm.getName() );
    //			//}else{
    //
    //			String lPosition = eParm.getParmIndex();
    //			Integer lPos = new Integer(lPosition);
    //			if (lPos.intValue() > lPosSave.intValue())
    //			{
    //				lPosSave = lPos;
    //
    //			}
    //			String lFieldName = eParm.getDataItemName();
    //			//fields.add((Object) lFieldName);
    //			if (lPos.intValue() != 0)
    //			{
    //				//fields.add(lPos.intValue(), (Object) lFieldName);
    //				fields.put((Object) lPos, (Object) lFieldName);
    //			}
    //		}
    //		//}
    //		//columnList is list of columns used by the select and insert sql
    // statements
    //		//updateList is field=value pairs used for the update sql statements
    //		//values is list of placeholders for the select statements
    //		columnList = new StringBuffer();
    //		updateList = new StringBuffer();
    //		values = new StringBuffer();
    //		 ix = fields.size();
    //
    //		for (int iz = 0; iz <= lPosSave.intValue(); iz++)
    //		{
    //
    //			Integer lPos = new Integer(iz);
    //			if (fields.get((Object) lPos) != null)
    //			{
    //
    //				if (iz == ix)
    //				{
    //					columnList.append((String) fields.get((Object) lPos));
    //					values.append("?");
    //					String value = (String) fields.get((Object) lPos);
    //					if (value.equals("CREATEUSER")){
    //						value = "UPDATEUSER";
    //					}
    //					updateList.append(value);
    //					updateList.append("= ?");
    //				}
    //				else
    //				{
    //					columnList.append(fields.get((Object) lPos));
    //					columnList.append(", ");
    //					values.append("?, ");
    //					updateList.append(fields.get((Object) lPos));
    //					updateList.append("= ?, ");
    //				}
    //
    //			}
    //		}
    //
    //		try
    //		{
    //			StringBuffer lParmMap = new StringBuffer();
    //			if (eQuery instanceof EventQueryProperties)
    //			{
    //				EventQueryProperties eQueryP = (EventQueryProperties) eQuery;
    //				String anEvent = eQueryP.getEventName();
    //				String whereClause = eQueryP.getWhereClause();
    //				lStatementName = anEvent + "::" + eMap.getEntity() + "::" +
    // eMap.getContextKey();
    //				if (columnList.equals("")){
    //					System.out.println("columnlist is empty for " + lStatementName);
    //				}
    //				
    //				if (eQuery.getMappingMethodName().startsWith("retrieve"))
    //				{
    //					lStatementName = anEvent + "::" + eMap.getEntity() + "::" +
    // eMap.getContextKey();
    //					lParmMap.append("SELECT ");
    //					lParmMap.append(columnList);
    //					lParmMap.append(" FROM ");
    //					lParmMap.append(cProp.getDb2Schema() + ".");
    //					lParmMap.append(source);
    //					if (whereClause.equals("none"))
    //					{
    //					// System.out.println("no where clause");
    //					}
    //					else
    //					{
    //						lParmMap.append(" WHERE ");
    //						lParmMap.append(whereClause);
    //					}
    //					lParmMap.append(" FOR READ ONLY ");
    //
    //				}
    //				System.out.println("creating prepared statement named " +
    // lStatementName);
    //				System.out.println("creating prepared statement " + lParmMap.toString());
    //				lstatement =
    //					(PreparedStatement) resource.prepareStatement(
    //						lParmMap.toString());
    //				statements.put(lStatementName, lstatement);
    //			}
    //
    //			if (eQuery.getMappingMethodName().startsWith("save"))
    //			{
    //				SaveCallbackProperties eQueryS =
    //					(SaveCallbackProperties) eQuery;
    //				String whereClause = eQueryS.getWhereClause();
    //				lStatementName = "Insert::" + eMap.getEntity()+ "::" +
    // eMap.getContextKey();
    //				lParmMap = new StringBuffer();
    //				//lParmMap.append("SELECT ");
    //				//lParmMap.append(whereList);
    //				//lParmMap.append(" FROM FINAL TABLE ");
    //				lParmMap.append("INSERT INTO ");
    //				lParmMap.append(cProp.getDb2Schema() + ".");
    //				lParmMap.append(source);
    //				lParmMap.append(" (");
    //				//lParmMap.append(columnList);
    //				//Changed this because it was blowing up for Anand. He said documentation
    // states
    //				//you must toString a StringBuffer before appending it to another
    // StringBuffer. 04/20/05
    //				lParmMap.append(columnList.toString());
    //				lParmMap.append(") VALUES (");
    //				lParmMap.append(values);
    //				lParmMap.append(")");
    //				
    //				System.out.println("creating prepared statement named " +
    // lStatementName);
    //			    System.out.println("creating prepared statement " + lParmMap.toString());
    //				   
    //				
    //				lstatement =
    //					(PreparedStatement) resource.prepareStatement(
    //						lParmMap.toString());
    //				statements.put(lStatementName, lstatement);
    //
    //				lParmMap = new StringBuffer();
    //				lParmMap.append("UPDATE ");
    //				lParmMap.append(cProp.getDb2Schema() + ".");
    //				lParmMap.append(source);
    //				lParmMap.append(" SET ");
    //				lParmMap.append(updateList);
    //
    //				if ((whereClause == null) || (whereClause.equals("none")))
    //				{
    //			// System.out.println("no where clause");
    //				}
    //				else
    //				{
    //					lParmMap.append(" WHERE ");
    //					lParmMap.append(whereClause);
    //				}
    //				lStatementName = "Update::" + eMap.getEntity() + "::" +
    // eMap.getContextKey();
    //				System.out.println("creating prepared statement named " +
    // lStatementName);
    //				System.out.println("creating prepared statement " + lParmMap.toString());
    //				lstatement =
    //					(PreparedStatement) resource.prepareStatement(
    //						lParmMap.toString());
    //				statements.put(lStatementName, lstatement);
    //
    //			}
    //
    //		}
    //		catch (SQLException e1)
    //		{
    //
    //			e1.printStackTrace();
    //		}
    //	}

    public String getRegion()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see IConnection#getResource()
     * @modelguid {93046052-340E-4528-A672-AFB0628FF96C}
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
     * @modelguid {0EE643A6-BED6-414B-83BC-83B83F9A52D0}
     */
    public Object getStatement(String key)
    {
        if (isBad())
        {
            init(this.name);
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
     * @modelguid {3C750030-C996-46CA-880E-0D925540C42B}
     */
    public void init(String aName)
    {
        this.setName(aName);
        cProp = MojoProperties.getInstance().getConnectionProperties(name);
        String driver = cProp.getDriver();

        try
        {
            statements.clear();
            if (cProp.getName().equals("J204"))
            {

                Class.forName(driver).newInstance();

                open(cProp.getURL(), cProp.getUserID(), cProp.getPassword());

                buildStatements();
            }
        }
        catch (ClassNotFoundException e)
        {
            String msg = this.name + " connection exception: Unable to load driver (" + driver + ")";
            ConnectionException ce = new ConnectionException(msg);
            ExceptionHandler.executeCallbacks(ce);
        }
        catch (InstantiationException e)
        {
            String msg = this.name + " connection exception: Unable to create driver (" + driver + ")";
            ConnectionException ce = new ConnectionException(msg);
            ExceptionHandler.executeCallbacks(ce);
        }
        catch (IllegalAccessException e)
        {
            String msg = this.name + " connection exception: Unable to create driver (" + driver + ")";
            ConnectionException ce = new ConnectionException(msg);
            ExceptionHandler.executeCallbacks(ce);
        }

    }

    /**
     * @see IConnection#isAvailable()
     * @modelguid {089E1CD7-CE26-4E8A-B3E6-C6E81A71CD1B}
     */
    public synchronized boolean isAvailable()
    {
        ConnectionManager.ConnectionPool pool = (ConnectionManager.ConnectionPool) ConnectionManager.connectionPools
                .get(name);
        return pool.available.contains(this);
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
            //			long currentTime = new java.util.Date().getTime();
            //			if ((currentTime - startTime) > timeout) {
            //				if (!resource.isClosed()) {
            //					resource.close();
            //				}
            //				return true;
            //			}
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
                ExceptionHandler.executeCallbacks(new J204Exception("J204_EXCEPTION:J204Adapter " + es.getMessage()));
                throw new J204Exception("J204_EXCEPTION:J204Adapter " + es.getMessage());

            }
            ExceptionHandler.executeCallbacks(new J204Exception("J204_EXCEPTION:J204Adapter " + e.getMessage()));
            throw new J204Exception("J204_EXCEPTION:J204Adapter " + e.getMessage());

        }
        return retVal;
    }

    /**
     * @see IConnection#makeAvailable()
     * @modelguid {55A0F619-D96E-4CFD-A999-D9A1308F2142}
     */
    public synchronized void makeAvailable()
    {
        ConnectionManager.ConnectionPool pool = (ConnectionManager.ConnectionPool) ConnectionManager.connectionPools
                .get(this.name);

        if (pool.unavailable.contains(this))
        {
            pool.unavailable.remove(this);
        }
        pool.available.add(this);
    }

    /**
     * @see IConnection#makeUnavailable()
     * @modelguid {5F3D7C1F-B79B-44CF-86BC-E0681952128E}
     */
    public synchronized void makeUnavailable()
    {
        ConnectionManager.ConnectionPool pool = (ConnectionManager.ConnectionPool) ConnectionManager.connectionPools
                .get(this.name);

        if (pool.available.contains(this))
        {
            pool.available.remove(this);
        }
        pool.unavailable.add(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.multidatasource.IConnection#open(java.lang.String, int)
     */
    public void open(String URL, int iPort)
    {
        // TODO Auto-generated method stub

    }

    /**
     * @see IConnection#open(String, String, String)
     * @modelguid {83289EEB-D22C-412F-ACD6-0FDF942A434C}
     */
    public void open(String URL, String userID, String password)
    {
        try
        {
            String connectionLit = URL + "/" + "SQL/" + userID + "/" + password;
            //System.out.println(connectionLit);

            //  Probably should get this from configuration
            boolean autoCommit = true;

            //			Properties connProp = new Properties ();
            //			connProp.setProperty( "CURSORHOLD" ,
            //									   ( new Integer (1)).toString());
            //connProp.setProperty( "UID" , userID);
            //connProp.setProperty( "PWD" , password);

            //resource = DriverManager.getConnection(URL, connProp);
            resource = DriverManager.getConnection(connectionLit);

            resource.setAutoCommit(autoCommit);

        }
        catch (SQLException e1)
        {
            String msg = "Unable to open J204 connection";
            ExceptionHandler.executeCallbacks(new ConnectionException(msg));

        }
    }

    public void setName(String aName)
    {
        this.name = aName;
    }
}
