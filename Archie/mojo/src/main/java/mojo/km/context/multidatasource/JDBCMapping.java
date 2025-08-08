package mojo.km.context.multidatasource;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Level;

import mojo.km.config.CallbackProperties;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.logging.LogUtil;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.naming.MappingConstants;
import mojo.km.persistence.AttributeEvent;
import mojo.km.persistence.PersistentObject;

import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Reflection;
import mojo.km.utilities.TextUtil;
import mojo.km.security.IUserInfo;
import mojo.km.security.helper.SecurityUtil;
import mojo.tools.code.KeyWord;

public class JDBCMapping implements IMapping
{
	private static final char COMMA_CHAR = ',';

	// private static final String FOR_READ_ONLY = " FOR READ ONLY";

	private static final String FROM = " FROM ";

	private static Context initCtx;

	private static final boolean IS_CLIENT = "true".equalsIgnoreCase(System.getProperty("jims2.client"));

	// Common mapping constants

	private static final boolean IS_SERVER = "true".equalsIgnoreCase(System.getProperty("jims2.server"));

	// JDBC Constants
	private static final String SELECT = "SELECT ";

	private static final String WHERE = " WHERE ";

	private static final long MILLION = 1000000;

	private static final String MAX_QUERY_ELAPSED_TIME_STRING = System.getProperty("jims2.log.perf.jdbc");

	private static long maxQueryElapsedTime = -1;

	private static final boolean TRACK_NUMERIC_MAPPING_ERROR = true;

	static
	{
		if (MAX_QUERY_ELAPSED_TIME_STRING != null)
		{
			try
			{
				maxQueryElapsedTime = Long.valueOf(MAX_QUERY_ELAPSED_TIME_STRING).longValue();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	// private static final String JIMS2USER_TYPE = "jims2user";

	static
	{
		if (IS_SERVER == true)
		{
			LogUtil.log(Level.DEBUG, "JDBC: using Universal Driver via J2EE client");
		}
		else if (IS_CLIENT == true)
		{
			LogUtil.log(Level.DEBUG, "JDBC: using Universal Driver via WAS");
		}
		else
		{
			LogUtil.log(Level.DEBUG, "JDBC: using DriverManager");
		}
	}

	private CallbackProperties callBack;

	private String connectionName;

	private EntityMappingProperties entityMap;

	public JDBCMapping()
	{
	}

	/**
	 * Constructor
	 * 
	 * @param aConnectionName -
	 *            moJo properties value defining a data source connection.
	 * 
	 * @modelguid {62C10729-59A3-46EA-891A-38DCA42AB6ED}
	 */
	public JDBCMapping(String aConnectionName)
	{
		super();
		connectionName = aConnectionName;
	}

	private PreparedStatement buildAttributeStatement(Connection connection, AttributeEvent anEvent,
			ParmMappingProperties[] parms, StringBuilder logBuffer, StringBuilder whereBuffer) throws SQLException
	{
		EventQueryProperties qProps = (EventQueryProperties) callBack;

		FieldMappingProperties fieldProps = qProps.getFieldMap(anEvent.getAttributeName());
		String dataItem = fieldProps.getDataItemName();

		ParmMappingProperties pProps = new ParmMappingProperties();
		pProps.setDataItemName(dataItem);

		if (fieldProps.getPropertyTypeInt() == MappingConstants.INT_DATATYPE)
		{
			pProps.setPropertyType(MappingConstants.NUMERIC_TYPE);
			pProps.setPropertyTypeInt(MappingConstants.NUMERIC_DATATYPE);
		}
		else
		{
			pProps.setPropertyType(fieldProps.getPropertyType());
			pProps.setPropertyTypeInt(fieldProps.getPropertyTypeInt());
		}

		pProps.setPropertyName("attributeValue");
		pProps.setParmIndex("1");
		pProps.setIndex(1);
		pProps.setPreserveCase(fieldProps.isPreserveCase());
		parms[0] = pProps;

		this.buildQueryForDebug(anEvent, parms, logBuffer);

		StringBuilder queryBuffer = new StringBuilder(256);

		queryBuffer.append(qProps.getQueryString());
		queryBuffer.append(dataItem);
		queryBuffer.append(" = ?");
		if(anEvent.isWithUR()){
			queryBuffer.append(" WITH UR ");
		}
		queryBuffer.append(MappingConstants.FOR_READ_ONLY);

		whereBuffer.append(dataItem);
		whereBuffer.append(" = ?");

		String query = queryBuffer.toString();

		return connection.prepareStatement(query);
	}

	/**
	 * @return
	 */
	private String buildColumnList()
	{
		// TODO optimize
		FieldMappingProperties fieldProps;
		StringBuilder columnList = new StringBuilder(256);
		int fieldCount = callBack.getFieldCount() - 1;
		FieldMappingProperties[] fields = callBack.getFieldsArray();
		for (int i = 0; i < fieldCount; i++)
		{
			// append field to the SELECT column list
			fieldProps = fields[i];
			columnList.append(fieldProps.getDataItemName());
			columnList.append(COMMA_CHAR);
		}

		fieldProps = fields[fieldCount];
		columnList.append(fieldProps.getDataItemName());

		return columnList.toString();
	}

	private String buildDynamicQuery(IEvent anEvent) throws Exception
	{
		StringBuilder queryBuffer = new StringBuilder();
		String columnList = this.buildColumnList();

		if (columnList.equals(""))
		{
			throw new JDBCException("Error in building dynamic SQL query");
		}

		ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties(this.connectionName);

		EventQueryProperties eventProps = (EventQueryProperties) callBack;
		String whereClause = invokeWhereClauseGenerator(eventProps, anEvent);
        RequestEvent aEvent = (RequestEvent) anEvent;
		queryBuffer.append(SELECT);
		queryBuffer.append(columnList);
		queryBuffer.append(FROM);
		queryBuffer.append(cProp.getDb2Schema());
		queryBuffer.append(KeyWord.PERIOD);
		queryBuffer.append(callBack.getSource());
		queryBuffer.append(WHERE);
		queryBuffer.append(whereClause);
		if(aEvent.isWithUR()){
			queryBuffer.append(" WITH UR ");
		}
		queryBuffer.append(MappingConstants.FOR_READ_ONLY);
		return queryBuffer.toString();
	}

	private void buildQueryForDebug(IEvent anEvent, ParmMappingProperties[] parms, StringBuilder queryBuffer)
	{
		ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties(this.connectionName);

		EventQueryProperties qProps = entityMap.getEventQueryProperties(anEvent, this.connectionName);

		queryBuffer.append("FROM ");
		queryBuffer.append(cProp.getDb2Schema());
		queryBuffer.append(KeyWord.PERIOD);
		queryBuffer.append(qProps.getSource());
        RequestEvent aEvent = (RequestEvent)anEvent; 
		switch (qProps.getQueryType())
		{
		case MappingConstants.ATTRIBUTE_QUERY:
			FieldMappingProperties pProps = parms[0];
			queryBuffer.append(MappingConstants.WHERE);
			queryBuffer.append(pProps.getDataItemName());
			queryBuffer.append(" = ?");
			if(aEvent.isWithUR()){
				queryBuffer.append(" WITH UR ");
			}
			queryBuffer.append(MappingConstants.FOR_READ_ONLY);
			break;

		case MappingConstants.ALL_QUERY:
			if(aEvent.isWithUR()){
				queryBuffer.append(" WITH UR ");
			}
			queryBuffer.append(MappingConstants.FOR_READ_ONLY);
			break;

		default: // for OID and Event Queries
			if (qProps.hasWhere())
			{
				queryBuffer.append(MappingConstants.WHERE);
				queryBuffer.append(qProps.getWhereClause());
			}
			if(aEvent.isWithUR()){
				queryBuffer.append(" WITH UR ");
			}
			queryBuffer.append(MappingConstants.FOR_READ_ONLY);
			break;

		}

		queryBuffer.append(";\n");
	}

	private void closeResources(Connection con, Statement stmt, ResultSet rs)
	{
		if (con != null)
		{
			try
			{
				con.commit();

				if (rs != null)
				{
					rs.close();
					rs = null;
				}

				if (stmt != null)
				{
					stmt.close();
					stmt = null;
				}
			}
			catch (SQLException e)
			{
				try
				{
					con.rollback();
				}
				catch (SQLException se)
				{
					LogUtil.log(Level.FATAL, se);
				}
				throw new JDBCException(e);
			}
			finally
			{
				try
				{
					con.close();
				}
				catch (SQLException e)
				{
					LogUtil.log(Level.FATAL, e);
				}
			}
		}
	}

	/**
	 * @param mapping
	 * @param connection
	 * @param props
	 * @param props2
	 * @throws SQLException
	 */
	private Statement doDelete(PersistentObject pObj, Connection aConnection, StringBuilder queryBuffer)
			throws SQLException
	{
		ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties(this.connectionName);

		SaveCallbackProperties sProps = (SaveCallbackProperties) this.callBack;

		String key = sProps.getDeleteQueryKey();
		queryBuffer.append(key);
		queryBuffer.append(");\n");

		List parmsList = sProps.getParmList();
		if (parmsList.size() != 1)
		{
			throw new JDBCException("must have one ParmMapping for delete of: " + pObj.getClass().getName());
		}

		StringBuilder tableBuffer = new StringBuilder(25);
		tableBuffer.append(cProps.getDb2Schema());
		tableBuffer.append(KeyWord.PERIOD);
		tableBuffer.append(this.callBack.getSource());
		String tableName = tableBuffer.toString();

		ParmMappingProperties pProps = (ParmMappingProperties) parmsList.get(0);
		String columnName = pProps.getDataItemName();

		String OID = pObj.getOID();

		queryBuffer.append("FROM ");
		queryBuffer.append(tableName);
		queryBuffer.append(MappingConstants.WHERE);
		queryBuffer.append(columnName);
		queryBuffer.append(KeyWord.EQUALS);
		queryBuffer.append(OID);

		IUserInfo user = SecurityUtil.getCurrentUser();

		String str = "{ CALL JIMS2.AUDITDELETES( ?, ?, ?, ?, ?) } ";

		queryBuffer.append("\nparms: 1,");
		queryBuffer.append(columnName);
		queryBuffer.append(KeyWord.EQUALS);
		queryBuffer.append(OID);

		CallableStatement st = aConnection.prepareCall(str);
		st.setString(1, user.getJIMSLogonId());
		st.setString(2, user.getJIMS2LogonId());
		st.setString(3, tableName);
		st.setString(4, columnName);
		st.setString(5, OID);
		st.execute();

		TransactionManager.getInstance().removeUpdated(pObj);

		return st;
	}

	private void doInsert(PreparedStatement pStmt, PersistentObject pObj, Connection aConnection,
			StringBuilder queryBuffer, ConnectionProperties cProps) throws Exception
	{
		SaveCallbackProperties sProps = (SaveCallbackProperties) this.callBack;

		String key = sProps.getInsertQueryKey();
		queryBuffer.append(key);
		queryBuffer.append(");\n");
		queryBuffer.append("INSERT INTO ");
		this.setUpdateDebug(false, queryBuffer, cProps, sProps);

		IUserInfo user = SecurityUtil.getCurrentUser();

		queryBuffer.append("parms: ");

		FieldMappingProperties[] fProps = this.callBack.getFieldsArray();
		this.setParameters(pStmt, pObj, fProps, user, queryBuffer);

		queryBuffer.append(";\n");

		pStmt.executeUpdate();

		if (pObj.getOID() == null)
		{
			PreparedStatement stump = aConnection.prepareStatement("SELECT IDENTITY_VAL_LOCAL() FROM SYSIBM.SYSDUMMY1");
			ResultSet rSet = null;
			try
			{
				rSet = stump.executeQuery();

				if (rSet.next())
				{
					String OID = rSet.getString(1);
					pObj.setOID(OID);
				}
			}
			finally
			{
				if (rSet != null)
				{
					rSet.close();
					rSet = null;
				}
				stump.close();
			}
		}
		TransactionManager.getInstance().removeUpdated(pObj);
		pObj.setModified(false);
		pObj.setNotNew();
	}

	private void doUpdate(PreparedStatement pStmt, PersistentObject pObj, Connection aConnection,
			StringBuilder queryBuffer) throws Exception
	{
		ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties(this.connectionName);

		SaveCallbackProperties sProps = (SaveCallbackProperties) this.callBack;

		String key = sProps.getUpdateQueryKey();
		queryBuffer.append(key);
		queryBuffer.append(");\n");
		queryBuffer.append("UPDATE ");
		this.setUpdateDebug(true, queryBuffer, cProps, sProps);

		List parmsList = sProps.getParmList();
		if (parmsList.size() < 1)
		{
			throw new JDBCException("must have one ParmMapping for update of: " + pObj.getClass().getName());
		}

		SaveCallbackProperties eventProps = (SaveCallbackProperties) getCallback();

		IUserInfo user = SecurityUtil.getCurrentUser();

		// Set query fields
		FieldMappingProperties[] fields = eventProps.getFieldsArray();
		setParameters(pStmt, pObj, fields, user, queryBuffer);

		// Set query parms
		fields = eventProps.getParmsArray();
		setParameters(pStmt, pObj, fields, user, queryBuffer);

		pStmt.executeUpdate();
		TransactionManager.getInstance().removeUpdated(pObj);
		pObj.setModified(false);
	}

	public CallbackProperties getCallback()
	{

		return this.callBack;
	}

	public Connection getConnection()
	{
		ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties(this.connectionName);
		Connection resource;
		try
		{
			if (IS_CLIENT == true)
			{
				String conStr = "java:comp/env/" + cProps.getURL();

				Hashtable<String, String> env = new Hashtable<String, String>();
				env.put(Context.PROVIDER_URL, "iiop://localhost:2809/");
				env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
				initCtx = new InitialContext(env);

				DataSource ds = (DataSource) initCtx.lookup(conStr);
				resource = ds.getConnection(cProps.getUserID(), cProps.getPassword());
			}
			else if (IS_SERVER == true)
			{
				String conStr = cProps.getURL();
				if (LogUtil.isTraceEnabled())
				{
					LogUtil.log(Level.TRACE, "lookup connection: " + conStr);
				}

				if (initCtx == null)
				{
					initCtx = new InitialContext();
				}

				DataSource ds = (DataSource) initCtx.lookup(conStr);
				resource = ds.getConnection();
			}
			else
			{
				try
				{
					Class.forName(cProps.getDriver());
					resource = DriverManager.getConnection(cProps.getTestUrl(), cProps.getUserID(), cProps
							.getPassword());
					resource.setAutoCommit(false);
				}
				catch (ClassNotFoundException e)
				{
					throw new ConnectionException("Unable to load driver for: JDBC " + e);
				}
			}

			resource.setAutoCommit(false);
		}
		catch (SQLException e)
		{
			String msg = "failed to create connection (" + cProps.getName() + ") to: " + cProps.getURL();
			LogUtil.log(Level.FATAL, e);
			e.printStackTrace(System.out);
			throw new ConnectionException(msg, e);
		}
		catch (NamingException e)
		{
			String msg = "failed to create connection (" + cProps.getName() + ") to: " + cProps.getURL();
			LogUtil.log(Level.FATAL, e);
			e.printStackTrace(System.out);
			throw new ConnectionException(msg, e);
		}
		return resource;
	}

	public String getConnectionName()
	{
		return connectionName;
	}

	public EntityMappingProperties getEntityMap()
	{
		return this.entityMap;
	}

	public void init()
	{
	}

	public void init(String key)
	{
		// TODO Auto-generated method stub
	}

	private String invokeWhereClauseGenerator(EventQueryProperties eventProps, IEvent anEvent) throws Exception
	{
		if (eventProps.hasWhereClauseGenerator() == false)
		{
			StringBuilder msg = new StringBuilder();
			msg.append("whereClauseGenerator has not been configured:");
			msg.append("\nentity: " + eventProps.getParent().getEntity());
			msg.append("\ncontextKey: " + eventProps.getParent().getContextKey());
			msg.append("\nevent: " + anEvent.getClass().getName());
			throw new JDBCException(msg.toString());
		}

		Class whereClauseUtility = Class.forName(eventProps.getWhereClauseGenerator());
		Object anObject = whereClauseUtility.newInstance();
		Class[] parms = new Class[1];
		parms[0] = IEvent.class;

		Method callback = anObject.getClass().getMethod(eventProps.getWhereClauseGeneratorMethod(), parms);

		Object[] objParms = new Object[1];
		objParms[0] = anEvent;

		return (String) callback.invoke(anObject, objParms);
	}

	private void loadPersistentData(ResultSet rSet, Map retVal, ArrayList results, Class pType) throws SQLException
	{
		FieldMappingProperties fProps = null;

		// List fields = callBack.getFields();
		FieldMappingProperties[] fields = callBack.getFieldsArray();
		int fieldCount = fields.length;

		try
		{
			while (rSet.next())
			// iterate through each record
			{
				PersistentObject pObj = null;
				if (retVal != null)
				{
					fProps = (FieldMappingProperties) callBack.getFieldMap(MappingConstants.OID);

					Object mapOid = rSet.getObject(fProps.getIndex());

					// has this persistentObject already been retrieved in another retriever?
					if (retVal.containsKey(mapOid))
					{
						pObj = (PersistentObject) retVal.get(mapOid);
					}
					else
					{
						pObj = (PersistentObject) pType.newInstance();
						retVal.put(mapOid, pObj);
					}
				}
				else
				{
					pObj = (PersistentObject) pType.newInstance();
					results.add(pObj);
				}

				// to avoid creating a new record
				pObj.setNotNew();

				Object[] obj = new Object[1];

				for (int f = 0; f < fieldCount; f++)
				{
					fProps = fields[f];

					// reset obj parm
					obj[0] = null;

					int propertyTypeInt = fProps.getPropertyTypeInt();

					int i = fProps.getIndex();

					switch (propertyTypeInt)
					{
					case MappingConstants.USER_DATATYPE:
					case MappingConstants.JIMS2USER_DATATYPE:
					case MappingConstants.STRING_DATATYPE:
						obj[0] = rSet.getString(i);
						break;

					case MappingConstants.DATE_DATATYPE:
						obj[0] = rSet.getTimestamp(i);
						if (obj[0] != null)
						{
							Timestamp dateStamp = (Timestamp) obj[0];
							obj[0] = new Date(dateStamp.getTime());
						}
						break;

					case MappingConstants.TIMESTAMP_DATATYPE:
						obj[0] = rSet.getTimestamp(i);
						break;

					case MappingConstants.BOOLEAN_DATATYPE:
						obj[0] = new Boolean(rSet.getBoolean(i));
						break;

					case MappingConstants.EVENT_DATATYPE:
						obj[0] = rSet.getString(i);
						if (obj[0] != null)
						{
							String eventValue = (String) obj[0];
							obj[0] = MessageUtil.convertStringToEvent(eventValue);
						}
						break;

					case MappingConstants.INT_DATATYPE:
						obj[0] = new Integer(rSet.getInt(i));
						break;

					case MappingConstants.LONG_DATATYPE:
						obj[0] = new Long(rSet.getLong(i));
						break;

					case MappingConstants.BYTE_DATATYPE:
						obj[0] = new Byte(rSet.getByte(i));
						break;

					case MappingConstants.DOUBLE_DATATYPE:
						obj[0] = new Double(rSet.getDouble(i));
						break;

					case MappingConstants.SHORT_DATATYPE:
						obj[0] = new Short(rSet.getShort(i));
						break;

					case MappingConstants.FLOAT_DATATYPE:
						obj[0] = new Float(rSet.getFloat(i));
						break;

					case MappingConstants.BLOB_DATATYPE:
					case MappingConstants.BYTEARRAY_DATATYPE:
						obj[0] = rSet.getBlob(i);
						if (obj[0] != null)
						{
							Blob blob = (Blob) obj[0];
							obj[0] = blob.getBytes(1, (int) blob.length());
						}
						break;

					case MappingConstants.CLOB_DATATYPE:
					case MappingConstants.CHARARRAY_DATATYPE:
						obj[0] = rSet.getClob(i);
						if (obj[0] != null)
						{
							Clob clob = (Clob) obj[0];
							Reader reader = new BufferedReader(clob.getCharacterStream());
							char[] chars = new char[(int) clob.length()];
							reader.read(chars);
							obj[0] = String.valueOf(chars);
						}
						break;

					default:
						StringBuilder buffer = new StringBuilder(75);
						buffer.append("\ncallback: ");
						buffer.append(this.callBack);
						buffer.append("\nInvalid mapping type: ");
						buffer.append(fProps);
						String msg = buffer.toString();
						throw new JDBCException(msg);

					}

					String propertyName = fProps.getPropertyName();
					Reflection.invokeMutatorMethod(pObj, propertyName, obj[0]);
					
// TODO Reflection Optimization - implement once issues are fixed
// *****************
//					Method mutator = fProps.getMutator();
//					if (mutator != null && obj[0] != null)
//					{
//						mutator.invoke(pObj, obj);
//					}
//					else if(mutator == null)
//					{
//						String propertyName = fProps.getPropertyName();
//						Reflection.invokeMutatorMethod(pObj, propertyName, obj[0]);
//						LogUtil.log(Level.WARN, "mutator not found: " + fProps);
//					}
// ******************
					
				}

			}
		}
		catch (JDBCException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			StringBuilder buffer = new StringBuilder(75);
			buffer.append(e.getMessage());
			buffer.append("\ncallback: ");
			buffer.append(this.callBack);
			buffer.append("\nerror setting property for: ");
			buffer.append(fProps);
			String msg = buffer.toString();
			throw new JDBCException(msg, e);
		}
	}

	/**
	 * Method to be registered related to events that will be used to retrieve objects from a data
	 * store.
	 * 
	 * @param anEvent -
	 *            the event containing the query data.
	 * @param pType -
	 *            the Class type of the persistent objects to be retrieved.
	 * @return ArrayList of objects containing the persistent objects.
	 */
	public List retrieve(IEvent anEvent, Class pType, Map retVal)
	{
		long starttime = System.nanoTime();

		// variables for handling/reporting exceptions and during "finally"
		// execution
		Connection connection = null;
		PreparedStatement pStmt = null;
		ResultSet rSet = null;

		// build queryBuffer for log, default to 256 bytes
		StringBuilder queryBuffer = new StringBuilder(256);

		// TODO optimize when Adapter has been optimize
		EventQueryProperties qProps = (EventQueryProperties) callBack;

		String key = qProps.getQueryKey();

		queryBuffer.append(LogUtil.EXECUTE_QUERY);
		queryBuffer.append(" JDBC (");
		queryBuffer.append(key);
		queryBuffer.append(");\n");

		ParmMappingProperties[] parms;

		StringBuilder parmBuffer = new StringBuilder(50);
		parmBuffer.append("parms: ");

		StringBuilder whereBuffer = new StringBuilder();

		ArrayList results = null;

		try
		{
			connection = this.getConnection();

			if (anEvent instanceof AttributeEvent)
			{
				parms = new ParmMappingProperties[1];
				AttributeEvent attrEvent = (AttributeEvent) anEvent;
				pStmt = this.buildAttributeStatement(connection, attrEvent, parms, queryBuffer, whereBuffer);
			}
			else
			{
				parms = callBack.getParmsArray();

				this.buildQueryForDebug(anEvent, parms, queryBuffer);

				whereBuffer.append(qProps.getWhereClause());

				pStmt = (PreparedStatement) connection.prepareStatement(qProps.getQueryString());
			}

			if (pStmt == null)
			{
				throw new JDBCException("could not find prepared statement for: " + key);
			}

			// set the prepared statement for specialized query execution
			pStmt.clearParameters();

			setParameters(pStmt, anEvent, parms, null, parmBuffer);

			if (parmBuffer.length() == 0)
			{
				parmBuffer.append("[no parms]");
			}

			rSet = pStmt.executeQuery();

			results = new ArrayList();

			this.loadPersistentData(rSet, retVal, results, pType);
		}
		catch (JDBCException e)
		{
			throw (JDBCException) e;
		}
		catch (Exception e)
		{
			throw new JDBCException(e);
		}
		finally
		{
			this.closeResources(connection, pStmt, rSet);

			long endtime = System.nanoTime();
			long elapsedTime = (endtime - starttime) / MILLION;

			queryBuffer.append(parmBuffer);
			queryBuffer.append("elapsedTime: ");
			queryBuffer.append(elapsedTime);
			queryBuffer.append(" ms");
			queryBuffer.append(" Number of Objects Returned:(");
			queryBuffer.append(retVal.size());
			queryBuffer.append(")");

			String logString = queryBuffer.toString();

			if (maxQueryElapsedTime != -1 && elapsedTime > maxQueryElapsedTime)
			{
				whereBuffer.append(" | ");
				whereBuffer.append(parmBuffer);
				String shortDesc = "poor performance: " + elapsedTime + " ms";
				ExceptionHandler.addWarning("JDBC SELECT Perf", null, qProps.getSource(), elapsedTime, shortDesc,
						whereBuffer.toString());
			}

			LogUtil.log(Level.DEBUG, logString);
		}
		return results;
	}

	public MetaDataResponseEvent retrieveMeta(IEvent anEvent, Class pType, Map retVal)
	{
		long starttime = System.currentTimeMillis();

		StringBuilder buffer = new StringBuilder(256);

		StringBuilder queryBuffer = new StringBuilder();
		Connection connection = null;
		EventQueryProperties qProps = entityMap.getEventQueryProperties(anEvent, this.connectionName);

		String whereClause = qProps.getWhereClause();

		ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties(this.connectionName);

		FieldMappingProperties[] parms;

		StringBuilder parmBuffer = new StringBuilder(50);

		ResultSet rSet = null;
		PreparedStatement statement = null;
		MetaDataResponseEvent resp = null;

		IUserInfo user = SecurityUtil.getCurrentUser();

		queryBuffer.append(MappingConstants.SELECT_COUNT_FROM);
		queryBuffer.append(cProp.getDb2Schema());
		queryBuffer.append(KeyWord.PERIOD);
		queryBuffer.append(callBack.getSource());

		try
		{
			if (anEvent instanceof AttributeEvent)
			{
				AttributeEvent aEvent = (AttributeEvent) anEvent;
				FieldMappingProperties fieldProps = callBack.getFieldMap(aEvent.getAttributeName());
				String dataItem = fieldProps.getDataItemName();

				String propertyType = fieldProps.getPropertyType();

				// Assemble query components
				queryBuffer.append(WHERE);
				queryBuffer.append(dataItem);
				queryBuffer.append(" = ?");

				ParmMappingProperties pProps = new ParmMappingProperties();
				pProps.setDataItemName(dataItem);
				pProps.setPropertyType(propertyType);
				pProps.setPropertyName("attributeValue");
				pProps.setParmIndex("1");
				pProps.setPreserveCase(fieldProps.isPreserveCase());

				parms = new FieldMappingProperties[1];
				parms[0] = pProps;
			}
			else
			{
				if (qProps.getMappingMethodName().equals("retrieveWithDynamicSQL"))
				{
					whereClause = invokeWhereClauseGenerator(qProps, anEvent);
				}

				if (whereClause == null)
				{
					whereClause = setWhereClauseValues(qProps, anEvent);
				}
                RequestEvent aEvent = (RequestEvent)anEvent;
				queryBuffer.append(WHERE);
				queryBuffer.append(whereClause);
				if(aEvent.isWithUR()){
					queryBuffer.append(" WITH UR ");
				}
				queryBuffer.append(MappingConstants.FOR_READ_ONLY);

				parms = callBack.getParmsArray();
			}

			String query = queryBuffer.toString();

			String key = qProps.getQueryKey();

			buffer.append(LogUtil.EXECUTE_QUERY);
			buffer.append("Meta JDBC (");
			buffer.append(key);
			buffer.append(");\n");
			buffer.append(query);

			connection = this.getConnection();

			statement = connection.prepareStatement(query);

			setParameters(statement, anEvent, parms, user, parmBuffer);

			resp = new MetaDataResponseEvent();
			int count = 0;
			rSet = statement.executeQuery();

			if (rSet.next())
			{
				count = rSet.getInt(1);
			}

			resp.setCount(count);
		}
		catch (JDBCException e)
		{
			throw (JDBCException) e;
		}
		catch (InvocationTargetException e)
		{
			Throwable target = e.getTargetException();
			LogUtil.log(Level.DEBUG, "has exception");
			throw new JDBCException(target.getMessage(), target);
		}
		catch (Exception e)
		{
			throw new JDBCException(e);
		}
		finally
		{
			this.closeResources(connection, statement, rSet);

			long elapsedTime = System.currentTimeMillis() - starttime;

			buffer.append(";\nparms: ");
			buffer.append(parmBuffer);
			buffer.append("elapsedTime: ");
			buffer.append(elapsedTime);
			buffer.append(" ms");

			LogUtil.log(Level.DEBUG, buffer.toString());
		}

		return resp;
	}

	public List retrieveWithDynamicSQL(IEvent anEvent, Class pType, Map retVal)
	{
		long starttime = System.nanoTime();

		Statement statement = null;
		ResultSet rSet = null;

		ArrayList results = new ArrayList();

		Connection connection = null;

		// build queryBuffer for log, default to 256 bytes
		StringBuilder queryBuffer = new StringBuilder(256);

		String query = null;

		EventQueryProperties qProps = (EventQueryProperties) this.callBack;

		try
		{
			query = this.buildDynamicQuery(anEvent);

			connection = this.getConnection();

			statement = (Statement) connection.createStatement();

			String key = qProps.getQueryKey();

			queryBuffer.append("executeDynQuery JDBC (");
			queryBuffer.append(key);
			queryBuffer.append(");\n");
			queryBuffer.append(query);
			queryBuffer.append(";\n");

			rSet = statement.executeQuery(query);

			if (rSet == null)
			{
				return results;
			}

			loadPersistentData(rSet, retVal, results, pType);
		}
		catch (JDBCException e)
		{
			LogUtil.log(Level.DEBUG, "has exception");
			throw e;
		}
		catch (InvocationTargetException e)
		{
			Throwable target = e.getTargetException();
			LogUtil.log(Level.DEBUG, "has exception");
			throw new JDBCException(target.getMessage(), target);
		}
		catch (Exception e)
		{
			LogUtil.log(Level.DEBUG, "has exception");
			throw new JDBCException(e.getMessage(), e);
		}
		finally
		{
			this.closeResources(connection, statement, rSet);

			long endtime = System.nanoTime();
			long elapsedTime = (endtime - starttime) / MILLION;

			if (maxQueryElapsedTime != -1 && elapsedTime > maxQueryElapsedTime)
			{
				int whereIndex = query.indexOf("WHERE") + 6;
				String where = query.substring(whereIndex, query.length());
				String shortDesc = "poor performance: " + elapsedTime + " ms";
				ExceptionHandler.addWarning("JDBC Dyn SELECT Perf", null, qProps.getSource(), elapsedTime, shortDesc,
						where);
			}

			queryBuffer.append("parms: | elapsedTime: ");
			queryBuffer.append(elapsedTime);
			queryBuffer.append(" ms");
			queryBuffer.append(" Number of Objects Returned:(");
			queryBuffer.append(retVal.size());
			queryBuffer.append(")");
			LogUtil.log(Level.DEBUG, queryBuffer.toString());
		}

		return results;
	}

	/**
	 * @param connection
	 */
	private void rollbackTransaction(Connection connection)
	{
		if (connection != null)
		{
			try
			{
				connection.rollback();
			}
			catch (SQLException sqlE)
			{
				LogUtil.log(Level.FATAL, sqlE);
			}
		}
	}

	/**
	 * Method to be registered as the save callback whenever a Persistent Object is to be saved
	 * using a JDBC driver.
	 * 
	 * @param pObj -
	 *            the object to be saved.
	 * 
	 */
	public void save(PersistentObject pObj)
	{
		String transType = "unknown";

		SaveCallbackProperties sProps = (SaveCallbackProperties) this.callBack;

		StringBuilder queryBuffer = new StringBuilder(200);

		queryBuffer.append("executeUpdate JDBC (");

		long starttime = System.currentTimeMillis();

		ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties(this.connectionName);

		Connection connection = null;
		Statement stmt = null;

		try
		{
			connection = this.getConnection();

			if (pObj.isNew())
			{
				stmt = connection.prepareStatement(sProps.getInsertQuery());
				doInsert((PreparedStatement) stmt, pObj, connection, queryBuffer, cProps);
			}
			else if (pObj.isDeleted())
			{
				stmt = doDelete(pObj, connection, queryBuffer);
			}
			else
			{
				stmt = connection.prepareStatement(sProps.getUpdateQuery());
				doUpdate((PreparedStatement) stmt, pObj, connection, queryBuffer);
			}
		}
		catch (JDBCException e)
		{
			this.rollbackTransaction(connection);
			throw (JDBCException) e;
		}
		catch (Exception e)
		{
			this.rollbackTransaction(connection);
			throw new JDBCException("Failure on " + transType, e);
		}
		finally
		{
			this.closeResources(connection, stmt, null);

			long elapsedTime = System.currentTimeMillis() - starttime;

			queryBuffer.append(" elapsedTime: ");
			queryBuffer.append(elapsedTime);
			queryBuffer.append(" ms");
			LogUtil.log(Level.DEBUG, queryBuffer.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#setCallback(mojo.km.config.CallbackProperties)
	 */
	public void setCallback(CallbackProperties aCallBack)
	{
		this.callBack = aCallBack;

	}

	public void setConnectionName(String aConnectionName)
	{
		connectionName = aConnectionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#setEntityMap(mojo.km.config.CallbackProperties)
	 */
	public void setEntityMap(EntityMappingProperties entityMap)
	{
		this.entityMap = entityMap;

	}

	private void setNull(PreparedStatement statement, FieldMappingProperties aProps, int anIndex) throws SQLException
	{
		int sqlType;

		switch (aProps.getPropertyTypeInt())
		{
		case MappingConstants.STRING_DATATYPE:
		case MappingConstants.USER_DATATYPE:
		case MappingConstants.JIMS2USER_DATATYPE:
			sqlType = Types.VARCHAR;
			break;
		case MappingConstants.INT_DATATYPE:
		case MappingConstants.NUMERIC_DATATYPE:
			sqlType = Types.INTEGER;
			break;
		case MappingConstants.DATE_DATATYPE:
		case MappingConstants.TIMESTAMP_DATATYPE:
			sqlType = Types.TIMESTAMP;
			break;

		case MappingConstants.BYTE_DATATYPE:
			sqlType = Types.SMALLINT;
			break;

		case MappingConstants.FLOAT_DATATYPE:
			sqlType = Types.REAL;
			break;
		case MappingConstants.DOUBLE_DATATYPE:
			sqlType = Types.DOUBLE;
			break;

		case MappingConstants.LONG_DATATYPE:
			sqlType = Types.BIGINT;
			break;

		case MappingConstants.BYTEARRAY_DATATYPE:
		case MappingConstants.BLOB_DATATYPE:
			sqlType = Types.BLOB;
			break;

		case MappingConstants.CHARARRAY_DATATYPE:
		case MappingConstants.CLOB_DATATYPE:
			sqlType = Types.CLOB;
			break;

		default:
			throw new JDBCException("invalid type mapping for setting JDBC null: " + aProps.toString());
		}

		statement.setNull(anIndex, sqlType);
	}

	public void setParameters(PreparedStatement statement, Object anEvent, FieldMappingProperties[] parms,
			IUserInfo aUser, StringBuilder parmBuffer) throws Exception
	{
		FieldMappingProperties pProps = null;
		try
		{
			for (int p = 0; p < parms.length; p++)
			{
				pProps = parms[p];
				String propertyName = pProps.getPropertyName();
				int propertyTypeInt = pProps.getPropertyTypeInt();

				Object inObject = Reflection.invokeAccessorMethod(anEvent, propertyName);
				int i = pProps.getIndex();

				parmBuffer.append(i);
				parmBuffer.append(",");
				parmBuffer.append(pProps.getDataItemName());
				parmBuffer.append(KeyWord.EQUALS);

				if (inObject == null && propertyTypeInt != MappingConstants.USER_DATATYPE
						&& propertyTypeInt != MappingConstants.JIMS2USER_DATATYPE)
				// handle null parameters
				{
					if (propertyTypeInt == MappingConstants.STRING_DATATYPE
							&& (pProps.isWildcardIfBlank() || pProps.isWildcardAlways()))
					{
						inObject = MappingConstants.PERCENT_WILD_CARD;
					}

					if (inObject == null)
					{
						parmBuffer.append("[null]");
						setNull(statement, pProps, i);
					}
					else
					{
						parmBuffer.append(KeyWord.DOUBLE_QUOTE);
						parmBuffer.append((String) inObject);
						parmBuffer.append(KeyWord.DOUBLE_QUOTE);
						statement.setString(i, (String) inObject);
					}

				}
				else
				// set not-null parameters and user properties
				{
					PersistentObject pObj;
					switch (propertyTypeInt)
					{
					case MappingConstants.STRING_DATATYPE:
						String stringValue = (String) inObject;
						if(stringValue != null){
						   stringValue = stringValue.trim(); 
						}
						if (pProps.isPreserveCase() == false)
						{
							stringValue = stringValue.toUpperCase();
						}

						if (pProps.isWildcardIfBlank() && stringValue.equals(""))
						{
							stringValue = MappingConstants.PERCENT_WILD_CARD;
						}
						else if (pProps.isWildcardAlways())
						{
							stringValue = stringValue + MappingConstants.PERCENT_WILD_CARD;
						}

						if (pProps.isConvertWildStarToPercent())
						{
							stringValue = stringValue.replace('*', '%');
						}

						parmBuffer.append(KeyWord.DOUBLE_QUOTE);
						parmBuffer.append(stringValue);
						parmBuffer.append(KeyWord.DOUBLE_QUOTE);

						statement.setString(i, stringValue);
						break;

					case MappingConstants.NUMERIC_DATATYPE:
						String val;
						if (inObject instanceof String)
						{
							val = (String) inObject;
							if (val.equals(""))
							{
								val = pProps.getText();
								try
								{
									Integer.parseInt(val);
								}
								catch (NumberFormatException ne)
								{
									if (TRACK_NUMERIC_MAPPING_ERROR)
									{
										this.addNumericMappingError(anEvent, val, pProps);
									}
									val = null;
									int sqlType = Types.INTEGER;
									statement.setNull(i, sqlType);
									parmBuffer.append("{null}");
									break;
								}
							}
						}
						else
						{
							val = inObject.toString();
						}
						int anInt = Integer.parseInt(val);

						parmBuffer.append(anInt);

						statement.setInt(i, anInt);
						break;

					case MappingConstants.JIMS2USER_DATATYPE:
						// aUser depends on SecurityUtil always return a non-null IUserInfo
						pObj = (PersistentObject) anEvent;
						if (pObj.isNew() == false && pObj.isDeleted() == false)
						// update scenario
						{
							inObject = aUser.getJIMS2LogonId();
							pObj.setUpdateJIMS2UserID((String) inObject);
						}
						else if (pObj.isNew() == true)
						// insert scenario
						{
							if (inObject == null)
							// check for 'null' in case not previously set in doInsert
							{
								inObject = aUser.getJIMS2LogonId();
							}
							pObj.setCreateJIMS2UserID((String) inObject);
						}

						parmBuffer.append(KeyWord.DOUBLE_QUOTE);
						parmBuffer.append((String) inObject);
						parmBuffer.append(KeyWord.DOUBLE_QUOTE);

						statement.setString(i, (String) inObject);

						break;

					case MappingConstants.USER_DATATYPE:
						// aUser depends on SecurityUtil always return a non-null IUserInfo
						pObj = (PersistentObject) anEvent;
						if (pObj.isNew() == false && pObj.isDeleted() == false)
						// update scenario
						{
							inObject = aUser.getJIMSLogonId();
							pObj.setUpdateUserID((String) inObject);
						}
						else if (pObj.isNew() == true)
						// insert scenario
						{
							if (inObject == null)
							// check for 'null' in case not previously set in doInsert
							{
								inObject = aUser.getJIMSLogonId();
							}
							pObj.setCreateUserID((String) inObject);
						}

						parmBuffer.append(KeyWord.DOUBLE_QUOTE);
						parmBuffer.append((String) inObject);
						parmBuffer.append(KeyWord.DOUBLE_QUOTE);

						statement.setString(i, (String) inObject);

						break;

					case MappingConstants.TIMESTAMP_DATATYPE:
					case MappingConstants.DATE_DATATYPE:
						Date date1 = (Date) (inObject);
						Timestamp tsDate = new Timestamp(date1.getTime());

						parmBuffer.append(tsDate.toString());

						statement.setTimestamp(i, tsDate);
						break;

					case MappingConstants.INT_DATATYPE:
						int intValue = ((Integer) inObject).intValue();
						parmBuffer.append(intValue);
						statement.setInt(i, intValue);
						break;

					case MappingConstants.BOOLEAN_DATATYPE:
						boolean booleanValue = ((Boolean) inObject).booleanValue();
						parmBuffer.append(booleanValue);
						statement.setBoolean(i, booleanValue);
						break;

					case MappingConstants.LONG_DATATYPE:
						long longValue = ((Long) inObject).longValue();
						parmBuffer.append(longValue);
						statement.setLong(i, longValue);
						break;

					case MappingConstants.SHORT_DATATYPE:
						short shortValue = ((Short) inObject).shortValue();
						parmBuffer.append(shortValue);
						statement.setShort(i, shortValue);
						break;

					case MappingConstants.DOUBLE_DATATYPE:
						double doubleValue = ((Double) inObject).doubleValue();
						parmBuffer.append(doubleValue);
						statement.setDouble(i, doubleValue);
						break;

					case MappingConstants.FLOAT_DATATYPE:
						float floatValue = ((Float) inObject).floatValue();
						parmBuffer.append(floatValue);
						statement.setFloat(i, floatValue);
						break;

					case MappingConstants.BYTE_DATATYPE:
						byte byteValue = ((Byte) inObject).byteValue();
						parmBuffer.append(byteValue);
						statement.setByte(i, byteValue);
						break;

					case MappingConstants.BYTEARRAY_DATATYPE:
						byte[] bytes = (byte[]) inObject;
						parmBuffer.append("[");
						parmBuffer.append(bytes.length);
						parmBuffer.append(" bytes]");

						ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
						statement.setBinaryStream(i, bais, (int) bytes.length);
						break;

					case MappingConstants.CLOB_DATATYPE:
						String charString = (String) inObject;
						parmBuffer.append("[");
						parmBuffer.append(charString.length());
						parmBuffer.append(" chars]");
						Reader reader = new CharArrayReader(charString.toCharArray());
						statement.setCharacterStream(i, reader, (int) charString.length());
						break;

					case MappingConstants.EVENT_DATATYPE:
						IEvent ev = (IEvent) inObject;
						parmBuffer.append(ev.getClass().getName());
						String value = null;
						if (ev != null)
						{
							value = MessageUtil.convertEventToString(ev);
						}
						statement.setString(i, value);
						break;

					default:
						parmBuffer.append("[invalid parm] | ");
						throw new JDBCException("Invalid mapping type: " + pProps);
					}
				}

				parmBuffer.append(" | ");
			}
		}
		catch (Exception e)
		{
			String msg;
			if (pProps != null)
			{
				StringBuilder buffer = new StringBuilder();
				buffer.append("failure to set QUERY parms:");
				buffer.append("\ncallback: " + this.callBack);
				buffer.append("\nparm props: " + pProps);
				buffer.append("\n");
				buffer.append(e.getMessage());
				msg = buffer.toString();
			}
			else
			{
				msg = e.getMessage();
			}
			throw new JDBCException(msg, e);
		}
	}

	private void addNumericMappingError(Object anEvent, String val, FieldMappingProperties pProps)
	{
		String msg = "Query parameter has invalid numeric value: " + val + " | parm props: " + pProps;
		String sourceId = null;
		String sourceName = this.callBack.getSource();
		if (anEvent instanceof PersistentObject)
		{
			sourceId = ((PersistentObject) anEvent).getOID();
		}
		ExceptionHandler.addError("JDBC Error", sourceId, sourceName, -1, "Invalid numeric mapping", msg);
	}

	/**
	 * @param queryBuffer
	 * @param props
	 * @param props2
	 * @return
	 */
	private void setUpdateDebug(boolean nonInsert, StringBuilder queryBuffer, ConnectionProperties cProps,
			SaveCallbackProperties sProps)
	{
		queryBuffer.append(cProps.getDb2Schema());
		queryBuffer.append(KeyWord.PERIOD);
		queryBuffer.append(sProps.getSource());
		if (nonInsert)
		{
			queryBuffer.append(MappingConstants.WHERE);
			queryBuffer.append(sProps.getWhereClause());
		}
		queryBuffer.append("\n");
	}

	private String setWhereClauseValues(EventQueryProperties eventProps, IEvent anEvent)
	{
		List parms = eventProps.getParms();
		int len = parms.size();
		byte[] retVal = null;
		String whereClause = eventProps.getWhereClause();
		for (int p = 0; p < len; p++)
		{
			ParmMappingProperties parmProps = (ParmMappingProperties) parms.get(p);
			String propName = parmProps.getPropertyName();

			StringBuilder replacedValue = new StringBuilder(30);
			replacedValue.append("<");
			replacedValue.append(propName);
			replacedValue.append(">");
			StringBuilder value = new StringBuilder();

			Object inObject = Reflection.invokeAccessorMethod(anEvent, propName);

			if (inObject == null)
			{
				value.append("null");
			}
			else
			{
				switch (parmProps.getPropertyTypeInt())
				{
				case MappingConstants.STRING_DATATYPE:
					value.append("'");
					value.append((String) inObject);
					value.append("'");
					break;

				case MappingConstants.DATE_DATATYPE:
					Date aDate = (Date) (Reflection.invokeAccessorMethod(anEvent, propName));
					Timestamp tsDate = new Timestamp(aDate.getTime());
					value.append(tsDate.toString());
					break;

				default:
					value.append(Reflection.invokeAccessorMethod(anEvent, propName));
					break;
				}
			}

			retVal = TextUtil.searchAndReplace(whereClause.getBytes(), replacedValue.toString(), value.toString());
		}
		return String.valueOf(retVal);
	}

}
