package mojo.km.context.multidatasource;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import mojo.km.config.CallbackProperties;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.config.PropertyBundleProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.context.ContextManager;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.naming.SecurityConstants;
import mojo.km.persistence.AllQueryEvent;
import mojo.km.persistence.AttributeEvent;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.ISecurityManager;

import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.Reflection;
import mojo.km.utilities.TextUtil;
import mojo.km.security.IUserInfo;
import mojo.tools.code.KeyWord;

/**
 * Handles execution of data source io events.
 * 
 * @author Eric Amundson and Nick Popov
 */
public class JDBCMSSQLMapping implements IMapping
{

	/** @modelguid {99B903FF-F2A7-4156-AB63-BE79195CCA6E} */
	class CallbackManager
	{
		/** @modelguid {CACD7AA0-1C8A-4DF8-93CB-B3F5597347A9} */
		Method callback = null;

		/** @modelguid {CD1F9278-3D59-4E50-8AD1-391C6D5419AE} */
		Object obj = null;

	}

	private static final String BIG_DECIMAL_TYPE = "java.math.BigDecimal";

	private static final String BLOB_LOWER_TYPE = "blob";

	public static final String BOOLEAN_LOWER_TYPE = "boolean";

	public static final String BYTE_ARRAY_LOWER_TYPE = "byte[]";

	public static final String BYTE_LOWER_TYPE = "byte";

	public static final String CHAR_ARRAY_LOWER_TYPE = "char[]";

	private static final String CLOB_LOWER_TYPE = "clob";

	private static final char COMMA = ',';

	// Short term mapping constants until a case standard is established
	public static final String DATE_LOWER_TYPE = "date";

	public static final String DATE_TYPE = "Date";

	private static final String DATE_TYPE_LONG = "java.util.Date";

	// Debug constants
	private static final boolean DEBUG = true;

	private static final boolean DEBUG_PARM = true;

	private static final boolean DEBUG_PERF = true;

	private static final boolean DEBUG_QUERY = true;

	private static final boolean DEBUG_THREAD = false;

	private static final String DOUBLE_COLON = "::";

	public static final String DOUBLE_LOWER_TYPE = "double";

	public static final String DOUBLE_TYPE = "Double";

	public static final String EVENT_LOWER_TYPE = "event";

	public static final String FLOAT_LOWER_TYPE = "float";

	private static final String FROM = " FROM ";

	public static final String INT_TYPE = "int";

	private static final String JIMS2USER_TYPE = "jims2user";

	public static final String LONG_LOWER_TYPE = "long";

	public static final String NONE = "none";

	public static final String NUMERIC_TYPE = "Numeric";

	// Common mapping constants
	public static final String OID = "OID";

	private static final char PERIOD = '.';

	private static final String RIGHT_PAREN_EQUALS = ")=";

	// TODO Move mapping constants to a naming file

	// Security constants
	private static final String SECURITY_MANAGER = "ISecurityManager";

	private static final String SELECT = "SELECT ";

	// JDBC Constants
	private static final String SELECTCOUNT = "SELECT COUNT(*) ";

	public static final String SHORT_LOWER_TYPE = "short";

	public static final String STRING_ARRAY_LOWER_TYPE = "string[]";

	// Mapping data type constants
	public static final String STRING_TYPE = "String";

	private static final String STRING_TYPE_LONG = "java.lang.String";

	public static final String STRING_TYPE_LOWER = "string";

	private static final String TIMESTAMP_FORMAT = "yyyy-mm-dd hh:mm:ss.fffffffff";

	public static final String TIMESTAMP_TYPE = "Timestamp";

	private static final String VARCHAR_LEFT_PAREN = "VARCHAR(";

	private static final String WHERE = " WHERE ";

	static private HashMap whereClauseGenerators = new HashMap();

	private static final String WILD_CARD = "%";

	private static final String WILD_CARD_ALWAYS = "% Always";

	// NP not needed in the MS SQLServer 2005 server implementation
	// private static final String FOR_READ_ONLY = " FOR READ ONLY";

	private static final String WILD_CARD_IF_BLANK = "% if Blank";

	private CallbackProperties callBack = null;

	private String connectionName = null;

	private EntityMappingProperties entityMap = null;

	private Map keyFields = new HashMap();

	public JDBCMSSQLMapping()
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
	public JDBCMSSQLMapping(String aConnectionName)
	{
		connectionName = aConnectionName;
	}

	private PreparedStatement buildAttributeStatement(Connection connection, AttributeEvent anEvent, List paramsList)
			throws SQLException
	{
		PreparedStatement pStmt = null;

		StringBuffer queryBuffer = new StringBuffer();
		FieldMappingProperties fieldProps = null;

		// Build field column list
		String columnList = this.buildColumnList();

		// Build WHERE clause
		String source = callBack.getSource();
		ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties(this.connectionName);

		fieldProps = callBack.getFieldMap(anEvent.getAttributeName());
		String dataItem = fieldProps.getDataItemName();

		String propertyType = fieldProps.getPropertyType();

		// Assemble query components
		queryBuffer.append(SELECT);
		queryBuffer.append(columnList);
		queryBuffer.append(FROM);
		queryBuffer.append(cProp.getDb2Schema() + "." + source);
		queryBuffer.append(WHERE);
		queryBuffer.append(dataItem);
		queryBuffer.append(" = ?");
		// NP queryBuffer.append(FOR_READ_ONLY);

		String query = queryBuffer.toString();

		pStmt = connection.prepareStatement(query);

		ParmMappingProperties pProps = new ParmMappingProperties();
		pProps.setDataItemName(dataItem);
		pProps.setPropertyType(propertyType);
		pProps.setPropertyName("attributeValue");
		pProps.setParmIndex("1");
		paramsList.add(pProps);

		return pStmt;
	}

	private String buildColumnList()
	{
		FieldMappingProperties fieldProps = null;
		StringBuffer columnList = new StringBuffer();
		int fieldCount = callBack.getFieldCount();

		boolean breakLoop = false;

		// NP ?!?!? could we just use break instead do logical check ???
		for (int i = 1; i <= fieldCount && breakLoop == false; i++)
		{
			// append field to the SELECT column list
			fieldProps = (FieldMappingProperties) callBack.getFieldByParmIndexMap(String.valueOf(i));

			// fieldProps will be null if there is a parmIndex of zero in the
			// mapping
			if (fieldProps == null)
			{
				breakLoop = true;
			}
			else
			{
				columnList.append(fieldProps.getDataItemName());
				columnList.append(COMMA);
			}
		}

		// remove trailing comma if it exists
		int finalCharPos = columnList.length() - 1;
		char lastChar = columnList.charAt(finalCharPos);
		if (lastChar == COMMA)
		{
			columnList.deleteCharAt(finalCharPos);
		}

		return columnList.toString();
	}

	private String buildDynamicQuery(IEvent anEvent)
	{
		StringBuffer queryBuffer = new StringBuffer();
		String columnList = this.buildColumnList();

		if (columnList.equals(""))
		{
			this.debugMsg("dynamic query columnlist empty for " + callBack.getName());
			this.throwJDBCException("Error in SQL query");
		}

		ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties(this.connectionName);

		EventQueryProperties eventProps = (EventQueryProperties) callBack;
		String whereClause = invokeWhereClauseGenerator(eventProps, anEvent);

		queryBuffer.append(SELECT);
		queryBuffer.append(columnList);
		queryBuffer.append(FROM);
		queryBuffer.append(cProp.getDb2Schema());
		queryBuffer.append(PERIOD);
		queryBuffer.append(callBack.getSource());

		if (whereClause == null)
		{
			whereClause = eventProps.getWhereClause();
			whereClause = setWhereClauseValues(whereClause, eventProps, anEvent);
		}

		queryBuffer.append(WHERE);
		queryBuffer.append(whereClause);
		// NP queryBuffer.append(FOR_READ_ONLY);
		String query = queryBuffer.toString();

		return queryBuffer.toString();
	}

	private void buildParmEntry(StringBuffer aBuffer, int anIndex, Object aValue)
	{
		aBuffer.append(anIndex);
		aBuffer.append(KeyWord.EQUALS);
		if (aValue instanceof String)
		{
			aBuffer.append(KeyWord.SINGLE_QUOTE);
		}
		if (aValue == null)
		{
			aBuffer.append("null");
		}
		else
		{
			String valueString = aValue.toString();
			if (valueString.length() > 100)
			{
				aBuffer.append("[value too large for console]");
			}
			else
			{
				aBuffer.append(valueString);
			}
		}
		if (aValue instanceof String)
		{
			aBuffer.append(KeyWord.SINGLE_QUOTE);
		}
	}

	public String convertEventToString(IEvent event)
	{
		// String newEvent = null;
		PropertyBundleProperties propBundle = PropertyBundleProperties.getInstance();
		String delim = propBundle.getProperty("EventSerializationDelim");
		if (delim == null)
		{
			delim = "|";
			System.err.println("[JDBCMapping] Delimeter is blank - check Mojo.xml property = EventSerializationDelim");
		}
		/*
		 * Returns a string representation of the properties of the event. @return String
		 * representation of the properties of the event
		 */

		StringBuffer retVal = new StringBuffer(event.getClass().getName());
		retVal.append("::");
		List accessors = Reflection.getAccessors(event.getClass());
		int len = accessors.size();
		for (int i = 0; i < len; i++)
		{
			Method aMethod = (Method) accessors.get(i);
			String propName = Reflection.getPropertyName(aMethod);
			retVal.append(propName);
			retVal.append("=");
			Object o = Reflection.invokeAccessorMethod(event, propName);
			String s = new String();
			if (o != null)
			{
				if ((o instanceof Date) && (o instanceof Timestamp == false))
				{
					java.sql.Date sqlDate = new java.sql.Date(((Date) o).getTime());
					o = sqlDate;
				}
				s = o.toString();
			}
			retVal.append(s);
			if (i < len - 1)
			{
				retVal.append(delim);
			}
		}

		return retVal.toString();
	}

	public IEvent convertStringToEvent(String event)
	{
		// String newEvent = null;
		PropertyBundleProperties propBundle = PropertyBundleProperties.getInstance();
		String delim = propBundle.getProperty("EventSerializationDelim");
		if (delim == null)
		{
			delim = "|";
			System.err.println("[JDBCMapping] Delimeter is blank - check Mojo.xml property = EventSerializationDelim");
		}
		/*
		 * Returns a string representation of the properties of the event. @return String
		 * representation of the properties of the event
		 */
		StringBuffer sb = new StringBuffer(event);
		int eventNameIdx = event.indexOf("::");
		String eventName = event.substring(0, eventNameIdx);
		this.debugMsg("deserialize event: " + eventName);
		String restOfData = event.substring(eventNameIdx + 2);
		StringTokenizer st = new StringTokenizer(restOfData, delim);
		IEvent retVal = null;
		try
		{
			retVal = (IEvent) Class.forName(eventName).newInstance();
			while (st.hasMoreTokens())
			{
				String tok = st.nextToken();
				int idx = tok.indexOf("=");
				String propName = tok.substring(0, idx);
				String value = tok.substring(idx + 1);
				Reflection.invokeMutatorMethod(retVal, propName, value);
			}
		}
		catch (Exception e)
		{
			this.throwJDBCException("[JDBCMapping.convertStringToEvent] " + e.getMessage());
		}
		return retVal;
	}

	private String createKey(String operation, Class clazz, String contextKey)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(operation);
		buffer.append(DOUBLE_COLON);
		buffer.append(clazz.getName());
		buffer.append(DOUBLE_COLON);
		buffer.append(contextKey);
		return buffer.toString();
	}

	private void debugMsg(String message)
	{
		if (DEBUG == true)
		{
			System.out.println(message);
			// LogUtil.log(Level.DEBUG, message);
		}

	}

	private void debugQuery(IEvent anEvent)
	{
		StringBuffer queryBuffer = new StringBuffer();
		FieldMappingProperties fieldProps = null;

		// Build field column list
		String columnList = this.buildColumnList();

		// Build WHERE clause
		String source = callBack.getSource();
		ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties(this.connectionName);

		String where;

		StringBuffer parmBuffer = new StringBuffer();

		if (anEvent instanceof AttributeEvent)
		{
			AttributeEvent attrEvent = (AttributeEvent) anEvent;
			fieldProps = callBack.getFieldMap(attrEvent.getAttributeName());
			where = WHERE + fieldProps.getDataItemName() + " = ?";
			this.buildParmEntry(parmBuffer, 1, attrEvent.getAttributeValue());
		}
		else if (anEvent instanceof AllQueryEvent)
		{
			where = "";
		}
		else
		{
			EventQueryProperties eProps = entityMap.getEventQueryProperties(anEvent, this.connectionName);

			where = eProps.getWhereClause().trim();

			if (where.toLowerCase().equals(NONE) == false && where.equals("") == false)
			{
				where = WHERE + eProps.getWhereClause();

				Iterator p = eProps.getParmsIterator();
				while (p.hasNext())
				{
					FieldMappingProperties pProps = (FieldMappingProperties) p.next();
					String pName = pProps.getPropertyName();
					Object inObject = Reflection.invokeAccessorMethod(anEvent, pName);
					try
					{
						int index = Integer.parseInt(pProps.getParmIndex());
						this.buildParmEntry(parmBuffer, index, inObject);
						if (p.hasNext())
						{
							parmBuffer.append(KeyWord.COMMA);
						}
					}
					catch (Exception e)
					{
						System.err.println("Invalid parm index: '" + pProps.getParmIndex() + "'");
					}
				}
			}
			else
			{
				where = "";
			}
		}

		// Assemble query components
		queryBuffer.append(SELECT);
		queryBuffer.append(columnList);
		queryBuffer.append(FROM);
		queryBuffer.append(cProp.getDb2Schema() + "." + source);
		queryBuffer.append(where);
		// queryBuffer.append(FOR_READ_ONLY);

		String query = queryBuffer.toString();

		this.debugMsg(query);
		this.debugMsg("parms: " + parmBuffer);
	}

	private void doDelete(PersistentObject pObj, IConnection connection) throws SQLException
	{
		CallableStatement st;
		Connection jdbcConnection = (Connection) connection.getResource();

		SaveCallbackProperties eventProps = (SaveCallbackProperties) getCallback();

		Iterator k = eventProps.getParmsIterator();

		ParmMappingProperties parmProps = (ParmMappingProperties) k.next();
		if (parmProps == null)
		{
			throw new JDBCException("[JDBCMapping.doDelete] Error in doDelete - ParmMappingProperties are null");
		}
		else
		{
			String columnName = parmProps.getDataItemName();
			String oid = null;

			oid = (String) pObj.getOID().toString();

			String fileName = eventProps.getSource();
			String connName = eventProps.getConnectionName();
			ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties(connName);
			String schema = cProps.getDb2Schema() + ".";
			String inStr = "DELETE FROM " + schema + fileName + " WHERE " + columnName + " = " + oid;

			this.debugMsg("Delete query: " + inStr);

			ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get(
					SecurityConstants.SECURITY_MANAGER);
			String userId = null;
			String JIMS2LogonId = null;
			if (manager != null)
			{
				IUserInfo user = manager.getIUserInfo();
				userId = user.getJIMSLogonId();
				JIMS2LogonId = user.getJIMS2LogonId();
			}
			// else
			// {
			// userId = JIRDS_USER_ID;
			// }

			// String str = "{ CALL JIMS2.AUDITDEL(?,?,?,?) }";
			String str = "{ CALL JIMS2.AUDITDELETES( ?, ?, ?, ?, ?) } ";
			this.debugMsg("execute delete stored procedure: " + str);

			st = jdbcConnection.prepareCall(str);
			st.setString(1, userId);
			st.setString(2, JIMS2LogonId);
			st.setString(3, schema + fileName);
			st.setString(4, columnName);
			st.setString(5, oid);
			st.execute();
			st.close();
			jdbcConnection.commit();
		}

	}

	private void doInsert(PersistentObject pObj, IConnection connection) throws SQLException
	{
		long starttime = 0;
		long endtime = 0;

		String preparedKey = this.createKey("Insert", pObj.getClass(), entityMap.getContextKey());
		PreparedStatement statement = (PreparedStatement) connection.getStatement(preparedKey);

		if (statement == null)
		{
			this.throwJDBCException("[JDBCMapping.doInsert] cound not find prepared statement - " + preparedKey);
		}
		SaveCallbackProperties eventProps = null;
		Connection jdbcConnection = (Connection) connection.getResource();

		statement.clearParameters();
		eventProps = (SaveCallbackProperties) getCallback();
		Iterator i = eventProps.getFieldsIterator();
		while (i.hasNext())
		{
			FieldMappingProperties parmProps = (FieldMappingProperties) i.next();
			this.setParameter(statement, pObj, parmProps);
		}

		if (DEBUG_PERF == true)
		{
			starttime = System.currentTimeMillis();
		}
		statement.executeUpdate();

		/*if (pObj.getOID() == null)
		{
			Statement stump = jdbcConnection.createStatement();
			ResultSet rSet = stump.executeQuery("SELECT IDENTITY_VAL_LOCAL() FROM SYSIBM.SYSDUMMY1");

			while (rSet.next())
			{
				String OID = rSet.getString(1);
				pObj.setOID(OID);
			}
			rSet.close();
			stump.close();
		}*/
		TransactionManager.getInstance().removeUpdated(pObj);
		pObj.setModified(false);

		if (DEBUG_PERF == true)
		{
			endtime = System.currentTimeMillis();

			double elapsedtime = ((double) endtime - (double) starttime) / (double) 1000.0;

			System.err.println("Insert performance: " + preparedKey + " in " + elapsedtime + " seconds.\n");
		}

	}

	private void doUpdate(PersistentObject pObj, IConnection connection) throws SQLException
	{
		long starttime = 0;
		long endtime = 0;

		if (DEBUG_PERF == true)
		{
			starttime = System.currentTimeMillis();
		}

		// String preparedKey = "Update::" + pObj.getClass().getName() + "::" +
		// entityMap.getContextKey();
		String preparedKey = this.createKey("Update", pObj.getClass(), entityMap.getContextKey());
		PreparedStatement statement = (PreparedStatement) connection.getStatement(preparedKey);
		// "Update::" + pObj.getClass().getName() + "::" +
		// entityMap.getContextKey());
		if (statement == null)
		{
			this.throwJDBCException("[JDBCMapping.doUpdate] could not find prepared statement - " + preparedKey);
		}
		Connection jdbcConnection = (Connection) connection.getResource();

		statement.clearParameters();
		SaveCallbackProperties eventProps = (SaveCallbackProperties) getCallback();

		// Set query fields
		Iterator i = eventProps.getFieldsIterator();
		while (i.hasNext())
		{
			FieldMappingProperties parmProps = (FieldMappingProperties) i.next();
			setParameter(statement, pObj, parmProps);
		}

		// Set query parms
		Iterator k = eventProps.getParmsIterator();
		while (k.hasNext())
		{
			ParmMappingProperties parmProps = (ParmMappingProperties) k.next();
			setParameter(statement, pObj, parmProps);
		}

		statement.executeUpdate();
		TransactionManager.getInstance().removeUpdated(pObj);
		pObj.setModified(false);
		jdbcConnection.commit();

		if (DEBUG_PERF == true)
		{
			endtime = System.currentTimeMillis();

			double elapsedtime = ((double) endtime - (double) starttime) / (double) 1000.0;

			System.err.println("update performance: " + preparedKey + " in " + elapsedtime + " seconds.\n");
		}

	}

	private void executeQuery(PreparedStatement statement, IEvent anEvent, Map retVal, Class pType, Iterator params)
			throws SQLException, InstantiationException, IllegalAccessException
	{
		this.debugQuery(anEvent);
		while (params.hasNext())
		{
			FieldMappingProperties parmProps = (FieldMappingProperties) params.next();
			setParameter(statement, anEvent, parmProps);
		}

		ResultSet rSet = null;

		try
		{
			rSet = statement.executeQuery();

			this.loadPersistentData(rSet, retVal, pType);
		}
		finally
		{
			if (rSet != null)
			{
				rSet.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getCallback()
	 */
	public CallbackProperties getCallback()
	{

		return this.callBack;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getConnectionName()
	 */
	public String getConnectionName()
	{
		// TODO Auto-generated method stub
		return connectionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#retrieve(mojo.km.messaging.IEvent,
	 *      java.lang.Class, java.util.Map)
	 */
	// public Map retrieve(IEvent anEvent, Class pType, Map retVal) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getDeleteStatement()
	 */
	public IStatement getDeleteStatement()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getEntityMap()
	 */
	public EntityMappingProperties getEntityMap()
	{
		return this.entityMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getInsertStatement()
	 */
	public IStatement getInsertStatement()
	{
		// TODO Auto-generated method stub
		return null;
	}

	private Object getObjectValue(int columnPosition, String columnName, String propertyType, ResultSet rSet)
	{
		Object returnObject = null;

		try
		{
			returnObject = rSet.getObject(columnPosition);

			if (propertyType.equals(STRING_TYPE) || propertyType.equals("java.lang.String"))
			{
				if (returnObject != null)
				{
					returnObject = String.valueOf(returnObject);
				}
			}
			else if (propertyType.equals(DATE_TYPE) || propertyType.equals("java.util.Date"))
			{
				returnObject = rSet.getTimestamp(columnPosition);
			}
			else if (propertyType.equals(TIMESTAMP_TYPE))
			{
				returnObject = rSet.getTimestamp(columnPosition);
			}
			else if (propertyType.equals(INT_TYPE))
			{
				returnObject = new Integer(rSet.getInt(columnPosition));
			}
			else if (propertyType.equals(BOOLEAN_LOWER_TYPE))
			{
				returnObject = new Boolean(rSet.getBoolean(columnPosition));
			}
			else if (propertyType.equals(LONG_LOWER_TYPE))
			{
				returnObject = new Long(rSet.getLong(columnPosition));
			}
			else if (propertyType.equals(SHORT_LOWER_TYPE))
			{
				returnObject = new Short(rSet.getShort(columnPosition));
			}
			else if (propertyType.equals(DOUBLE_LOWER_TYPE))
			{
				returnObject = new Double(rSet.getDouble(columnPosition));
			}
			else if (propertyType.equals(FLOAT_LOWER_TYPE))
			{
				returnObject = new Float(rSet.getFloat(columnPosition));
			}
			else if (propertyType.equals(BYTE_LOWER_TYPE))
			{
				returnObject = new Byte(rSet.getByte(columnPosition));
			}
			else if (propertyType.equals(CLOB_LOWER_TYPE))
			{
				returnObject = (Clob) returnObject;
			}
			else if (propertyType.equals(BLOB_LOWER_TYPE))
			{
				returnObject = (Blob) returnObject;
			}
			else if (propertyType.equals(EVENT_LOWER_TYPE))
			{
				returnObject = rSet.getString(columnPosition);
			}
			else if (propertyType.equals(BIG_DECIMAL_TYPE))
			{
				returnObject = rSet.getBigDecimal(columnPosition);
			}

		}
		catch (SQLException e)
		{
			this.throwJDBCException("JDBCMapping " + e.getMessage());
		}

		return returnObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getQueryStatement()
	 */
	public IStatement getQueryStatement()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#getUpdateStatement()
	 */
	public IStatement getUpdateStatement()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/** @modelguid {751715FA-6F99-4005-8759-5639FC797FBC} */
	public void init()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#init(java.lang.String)
	 */
	public void init(String key)
	{
		// TODO Auto-generated method stub

	}

	private String invokeWhereClauseGenerator(EventQueryProperties eventProps, IEvent anEvent)
	{
		String whereClause = "";
		if (eventProps.getWhereClauseGenerator() == null)
		{
			return null;
		}
		if (eventProps.getWhereClauseGenerator().equals(""))
		{
			return null;
		}
		if (eventProps.getWhereClauseGeneratorMethod() == null)
		{
			return null;
		}
		if (eventProps.getWhereClauseGeneratorMethod().equals(""))
		{
			return null;
		}

		// TODO How is the whereClauseGenerator being used?
		CallbackManager cMgr = null;
		if (whereClauseGenerators.containsKey(eventProps))
		{
			cMgr = (CallbackManager) whereClauseGenerators.get(eventProps);
		}
		try
		{
			Class whereClauseUtility = Class.forName(eventProps.getWhereClauseGenerator());
			Object anObject = whereClauseUtility.newInstance();
			Class[] parms = new Class[1];
			parms[0] = IEvent.class;
			Method callback = anObject.getClass().getMethod(eventProps.getWhereClauseGeneratorMethod(), parms);
			cMgr = new CallbackManager();
			cMgr.callback = callback;
			cMgr.obj = anObject;

		}
		catch (Exception e)
		{
			return null;
		}
		if (cMgr != null)
		{
			// TODO Is this try-catch needed?
			try
			{
				Object[] parms = new Object[1];
				parms[0] = anEvent;
				whereClause = (String) cMgr.callback.invoke(cMgr.obj, parms);
			}
			catch (Exception callbackException)
			{
				return null;
			}
		}
		return whereClause;
	}

	private void loadPersistentData(ResultSet rSet, Map retVal, Class pType) throws SQLException,
			InstantiationException, IllegalAccessException
	{
		while (rSet.next())
		{
			FieldMappingProperties fieldProps = (FieldMappingProperties) callBack.getFieldMap(OID);

			int oidIndex = Integer.parseInt(fieldProps.getParmIndex());

			PersistentObject pObj = null;
			Object mapOid = rSet.getObject(oidIndex);

			if (retVal.containsKey(mapOid))
			{
				pObj = (PersistentObject) retVal.get(mapOid);
			}
			else
			{
				pObj = (PersistentObject) pType.newInstance();
			}

			int fieldCount = callBack.getFieldCount();

			for (int i = 1; i <= fieldCount; i++)
			{
				String parmIndex = String.valueOf(i);

				fieldProps = (FieldMappingProperties) callBack.getFieldByParmIndexMap(parmIndex);

				if (fieldProps == null)
				{
					this.debugMsg("[JDBCMapping.loadPersistentObject] no field props for index: " + i);
				}
				else
				{
					String columnName = fieldProps.getDataItemName();
					String propName = fieldProps.getPropertyName();
					String propType = fieldProps.getPropertyType();

					Object value = getObjectValue(i, columnName, propType, rSet);

					setPersistentObject(pObj, fieldProps, value);
				}
			}

			pObj.setNotNew();
			retVal.put(pObj.getOID(), pObj);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#retrieve(mojo.km.messaging.IEvent,
	 *      java.lang.Class)
	 */
	public List retrieve(IEvent anEvent, Class pType)
	{
		throw new UnsupportedOperationException("List " + this.getClass().getName()
				+ ".retrieve(IEvent, Class) has not been implemented.");
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
	 * @modelguid {A6C9EB53-FCE3-4238-A81D-084B48A3344A}
	 */
	public List retrieve(IEvent anEvent, Class pType, Map retVal)
	{
		long starttime = 0;
		long endtime = 0;

		PropertyBundleProperties propBundle = PropertyBundleProperties.getInstance();

		IConnection connection = TransactionManager.getInstance().getConnection(connectionName);

		Connection jdbcConnection = (Connection) connection.getResource();

		PreparedStatement statement = null;

		String eventClass = anEvent.getClass().getName();

		String queryKey = this.createKey(eventClass, pType, entityMap.getContextKey());

		if (DEBUG_QUERY)
		{
			this.debugMsg("queryKey: " + queryKey);
		}

		try
		{
			Iterator params = null;

			if (anEvent instanceof AttributeEvent)
			{
				List parmsList = new java.util.ArrayList();

				statement = this.buildAttributeStatement(jdbcConnection, (AttributeEvent) anEvent, parmsList);

				params = parmsList.iterator();
			}
			else
			{
				params = callBack.getParmsIterator();

				statement = (PreparedStatement) connection.getStatement(queryKey);
			}

			if (statement == null)
			{
				this.throwJDBCException("could not find prepared statement for: " + queryKey);
			}

			// set the prepared statement for specialized query execution
			statement.clearParameters();

			if (DEBUG_PERF == true)
			{
				starttime = System.currentTimeMillis();
			}

			this.executeQuery(statement, anEvent, retVal, pType, params);

			keyFields.clear();

			if (DEBUG_PERF == true)
			{
				endtime = System.currentTimeMillis();

				double elapsedtime = ((double) endtime - (double) starttime) / (double) 1000.0;

				System.err.println("retrieve performance: " + queryKey + " in " + elapsedtime + " seconds.\n");
			}
		}
		catch (Exception e)
		{
			try
			{
				jdbcConnection.rollback();
			}
			catch (SQLException sqlE)
			{
			}
			throw new JDBCException(e);
		}
		finally
		{
			try
			{
				jdbcConnection.commit();
				if (anEvent instanceof AttributeEvent && statement != null)
				{
					statement.close();
				}
			}
			catch (SQLException e)
			{
				this.throwJDBCException(e, "Failed to close statement");
			}
			finally
			{
				TransactionManager.releaseConnection(connectionName, connection);
			}
		}
		return null;
	}

	public MetaDataResponseEvent retrieveMeta(IEvent anEvent, Class pType, Map retVal)
	{
		long starttime = 0;
		long endtime = 0;

		PropertyBundleProperties propBundle = PropertyBundleProperties.getInstance();
		String whereClause = null;
		IConnection connection = TransactionManager.getInstance().getConnection(connectionName);
		StringBuffer queryBuffer = new StringBuffer();
		Connection jdbcConnection = (Connection) connection.getResource();
		EventQueryProperties eProps = entityMap.getEventQueryProperties(anEvent, this.connectionName);

		ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties(this.connectionName);
		Iterator params = null;
		List parmsList = new java.util.ArrayList();
		if (anEvent instanceof AttributeEvent)
		{
			AttributeEvent aEvent = (AttributeEvent) anEvent;
			FieldMappingProperties fieldProps = callBack.getFieldMap(aEvent.getAttributeName());
			String dataItem = fieldProps.getDataItemName();

			String propertyType = fieldProps.getPropertyType();

			// Assemble query components
			queryBuffer.append(SELECTCOUNT);

			queryBuffer.append(FROM);
			queryBuffer.append(cProp.getDb2Schema() + "." + callBack.getSource());
			queryBuffer.append(WHERE);
			queryBuffer.append(dataItem);
			queryBuffer.append(" = ?");

			ParmMappingProperties pProps = new ParmMappingProperties();
			pProps.setDataItemName(dataItem);
			pProps.setPropertyType(propertyType);
			pProps.setPropertyName("attributeValue");
			pProps.setParmIndex("1");
			parmsList.add(pProps);
		}
		else
		{

			if (eProps.getMappingMethodName().equals("retrieveWithDynamicSQL"))
			{
				whereClause = invokeWhereClauseGenerator(eProps, anEvent);
			}

			queryBuffer.append(SELECTCOUNT);

			queryBuffer.append(FROM);
			queryBuffer.append(cProp.getDb2Schema());
			queryBuffer.append(PERIOD);
			queryBuffer.append(callBack.getSource());

			if (whereClause == null)
			{
				whereClause = eProps.getWhereClause();
				whereClause = setWhereClauseValues(whereClause, eProps, anEvent);
			}

			queryBuffer.append(WHERE);
			queryBuffer.append(whereClause);
			// queryBuffer.append(FOR_READ_ONLY);
		}
		String query = queryBuffer.toString();
		String eventClass = anEvent.getClass().getName();

		String queryKey = this.createKey(eventClass, pType, entityMap.getContextKey());

		if (DEBUG_QUERY)
		{
			this.debugMsg("queryKey: " + queryKey);
		}
		ResultSet rSet = null;
		PreparedStatement statement = null;
		MetaDataResponseEvent resp = null;
		try
		{

			if (anEvent instanceof AttributeEvent)
			{

				params = parmsList.iterator();
			}
			else
			{
				params = callBack.getParmsIterator();

			}
			statement = jdbcConnection.prepareStatement(query);

			if (DEBUG_PERF == true)
			{
				starttime = System.currentTimeMillis();
			}

			while (params.hasNext())
			{
				FieldMappingProperties parmProps = (FieldMappingProperties) params.next();
				setParameter(statement, anEvent, parmProps);
			}

			resp = new MetaDataResponseEvent();
			int count = 0;
			rSet = statement.executeQuery();

			if (rSet.next())
			{
				count = rSet.getInt(1);
			}

			resp.setCount(count);

			keyFields.clear();

			if (DEBUG_PERF == true)
			{
				endtime = System.currentTimeMillis();

				double elapsedtime = ((double) endtime - (double) starttime) / (double) 1000.0;

				System.err.println("retrieve count performance: " + queryKey + " in " + elapsedtime + " seconds.\n");
			}
		}
		catch (Exception e)
		{
			try
			{
				jdbcConnection.rollback();
			}
			catch (SQLException sqlE)
			{
			}
			throw new JDBCException(e);
		}
		finally
		{
			try
			{
				jdbcConnection.commit();
				if (rSet != null)
				{
					rSet.close();
				}
				statement.close();
			}
			catch (SQLException e)
			{
				this.throwJDBCException(e, "Failed to close statement");
			}
			finally
			{
				TransactionManager.releaseConnection(connectionName, connection);
			}
		}

		return resp;
	}

	public Map retrieveWithDynamicSQL(IEvent anEvent, Class pType, Map retVal)
	{
		Statement statement = null;
		ResultSet rSet = null;

		IConnection connection = null;
		Connection jdbcConnection = null;

		try
		{
			Integer lPosSave = new Integer(0);
			String lStatementName = null;

			// columnList is list of columns used by the select and insert sql
			// statements
			// updateList is field=value pairs used for the update sql
			// statements
			// values is list of placeholders for the select statements
			StringBuffer updateList = new StringBuffer();
			StringBuffer values = new StringBuffer();

			String query = this.buildDynamicQuery(anEvent);

			if (DEBUG_QUERY == true)
			{
				this.debugMsg("Dynamic SQL statement = " + query);
			}

			connection = TransactionManager.getInstance().getConnection(connectionName);
			jdbcConnection = (Connection) connection.getResource();
			statement = (Statement) jdbcConnection.createStatement();
			rSet = statement.executeQuery(query);
			jdbcConnection.commit();

			if (rSet == null)
			{
				return retVal;
			}
			// Process result set and create a 'row' in a HashMap for each JDBC
			// record that comes back
			loadPersistentData(rSet, retVal, pType);

			keyFields.clear();
		}
		catch (Exception e)
		{
			try
			{
				if (jdbcConnection != null)
				{
					jdbcConnection.rollback();
				}
			}
			catch (SQLException sqlE)
			{
				sqlE.printStackTrace();
			}
			this.throwJDBCException(e, "JDBCMapping: " + e.getMessage());
		}
		finally
		{
			try
			{
				jdbcConnection.commit();
				if (rSet != null)
				{
					rSet.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				TransactionManager.releaseConnection(connectionName, connection);
			}
			catch (SQLException e)
			{
				this.throwJDBCException(e);
			}
		}

		return retVal;
	}

	/**
	 * Method to be registered as the save callback whenever a Persistent Object is to be saved
	 * using a JDBC driver.
	 * 
	 * @param pObj -
	 *            the object to be saved.
	 * 
	 * @modelguid {465C7EF3-8C08-47C1-B3F3-27E762589075}
	 */
	public void save(PersistentObject pObj)
	{
		IConnection connection = TransactionManager.getInstance().getConnection(connectionName);
		String transType = "unknown";
		try
		{
			if (pObj.isNew())
			{

				transType = "INSERT";
				// Unsupported for the MS SQLServer 2005 implementation
				// DO not know why this was here. RRY
				doInsert( pObj, connection );
				//throw new UnsupportedOperationException("INSERT is unsupported for MS SQLServer 2005 implementation");
			}
			else if (pObj.isDeleted())
			{
				transType = "DELETE";
				// Unsupported for the MS SQLServer 2005 implementation
				// doDelete(pObj, connection);
				throw new UnsupportedOperationException("DELETE is unsupported for MS SQLServer 2005 implementation");
			}
			else
			{

				transType = "UPDATE";
				// Unsupported for the MS SQLServer 2005 implementation
				// doUpdate(pObj, connection);
				//throw new UnsupportedOperationException("UPDATE is unsupported for MS SQLServer 2005 implementation");
			}
		}
		catch (Exception e)
		{
			try
			{
				Connection con = (Connection) connection.getResource();
				con.rollback();
			}
			catch (SQLException sqlE)
			{
				sqlE.printStackTrace();
			}
			this.throwJDBCException(e, "Failure on " + transType + ": " + e.getMessage());
		}
		finally
		{
			try
			{
				Connection con = (Connection) connection.getResource();
				con.commit();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			TransactionManager.releaseConnection(connectionName, connection);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.multidatasource.IMapping#setConnection(mojo.km.context.multidatasource.IConnection)
	 */
	public void setConnection(IConnection aConnection)
	{
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

	private void setNull(String propertyType, PreparedStatement statement, FieldMappingProperties fMap, int index)
			throws SQLException
	{
		int sqlType = Types.VARCHAR;
		if (propertyType.equalsIgnoreCase(STRING_TYPE) || propertyType.equalsIgnoreCase(STRING_TYPE_LONG)
				|| propertyType.equalsIgnoreCase(JIMS2USER_TYPE))
		{
			sqlType = Types.VARCHAR;
		}
		else if (propertyType.equalsIgnoreCase(INT_TYPE) || propertyType.equalsIgnoreCase(NUMERIC_TYPE))
		{
			sqlType = Types.INTEGER;
		}
		else if (propertyType.equalsIgnoreCase(DATE_TYPE) || propertyType.equalsIgnoreCase(DATE_TYPE_LONG)
				|| propertyType.equalsIgnoreCase(TIMESTAMP_TYPE))
		{
			sqlType = Types.TIMESTAMP;
		}
		else if (propertyType.equalsIgnoreCase(BYTE_LOWER_TYPE))
		{
			sqlType = Types.SMALLINT;
		}
		else if (propertyType.equalsIgnoreCase(LONG_LOWER_TYPE))
		{
			sqlType = Types.BIGINT;
		}
		else if (propertyType.equalsIgnoreCase(FLOAT_LOWER_TYPE))
		{
			sqlType = Types.REAL;
		}
		else if (propertyType.equalsIgnoreCase(DOUBLE_TYPE))
		{
			sqlType = Types.DOUBLE;
		}
		else if (propertyType.equalsIgnoreCase(BLOB_LOWER_TYPE))
		{
			sqlType = Types.BLOB;
		}
		else if (propertyType.equalsIgnoreCase(CLOB_LOWER_TYPE))
		{
			sqlType = Types.CLOB;
		}
		else if (propertyType.equalsIgnoreCase(BYTE_ARRAY_LOWER_TYPE))
		{
			sqlType = Types.BLOB;
		}
		else if (propertyType.equalsIgnoreCase(CHAR_ARRAY_LOWER_TYPE))
		{
			sqlType = Types.CLOB;
		}
		else
		{
			String msg = "Invalid property type exception: propertyType=" + propertyType + " index=" + index;
			this.throwJDBCException(msg);
		}
		statement.setNull(index, sqlType);
	}

	public void setParameter(PreparedStatement statement, Object anEvent, FieldMappingProperties parmProps)
	{
		if (parmProps.getDataItemName().toLowerCase().equals(NONE))
		{
			return;
		}

		String propertyName = parmProps.getPropertyName();
		String propertyType = parmProps.getPropertyType();
		String index = parmProps.getParmIndex();

		try
		{

			int i = Integer.parseInt(index);
			Object inObject = Reflection.invokeAccessorMethod(anEvent, propertyName);
			// System.out.println("property value = "+ inObject);
			if (DEBUG_PARM == true)
			{
				String msg = "parms: " + propertyName + " : " + propertyType + " : " + index + " : \"" + inObject
						+ "\"";
				this.debugMsg(msg);
			}

			if (propertyType.equals("user"))
			{
				ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get(SECURITY_MANAGER);
				String userId = "JIRCL1";
				String JIMS2LogonId = "TESTING@JIMS.NET";
				if (manager != null)
				{
					IUserInfo user = manager.getIUserInfo();
					if (user != null)
					{
						userId = user.getJIMSLogonId();

					}
				}

				if (userId != null)
				{
					inObject = userId;
					statement.setString(i, userId);
					Reflection.invokeMutatorMethod(anEvent, propertyName, userId);
					PersistentObject pObj = (PersistentObject) anEvent;
					this.debugMsg(" userid = " + userId);
				}
			}
			if (propertyType.equals("JIMS2User"))
			{
				ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get(SECURITY_MANAGER);
				String userId = null;
				String JIMS2LogonId = null;
				if (manager != null)
				{
					IUserInfo user = manager.getIUserInfo();
					if (user != null)
					{
						JIMS2LogonId = user.getJIMS2LogonId();
					}
				}

				if (JIMS2LogonId != null)
				{
					inObject = JIMS2LogonId;
					statement.setString(i, JIMS2LogonId);
					Reflection.invokeMutatorMethod(anEvent, propertyName, JIMS2LogonId);
					PersistentObject pObj = (PersistentObject) anEvent;
					this.debugMsg(" jims2 user id = " + JIMS2LogonId);
				}
			}

			if (inObject == null)
			{
				if (propertyType.equals(STRING_TYPE) || propertyType.equals(STRING_TYPE_LONG))
				{
					String fmtString = parmProps.getFormatString();
					if (WILD_CARD_IF_BLANK.equals(fmtString) || WILD_CARD_ALWAYS.equals(fmtString))
					{
						inObject = WILD_CARD;
					}
				}

				if (inObject == null)
				{
					setNull(propertyType, statement, parmProps, i);
				}
				else
				{
					statement.setString(i, (String) inObject);
				}
				return;
			}

			if (propertyType.equals(NUMERIC_TYPE))
			{
				Object numVal = inObject;
				String val = "";
				if (numVal != null)
				{
					val = numVal.toString();
				}
				if (val.equals(""))
				{
					val = parmProps.getText();
				}
				try
				{
					int anInt = Integer.parseInt(val);
					statement.setInt(i, anInt);
				}
				catch (NumberFormatException nfe)
				{
					this.debugMsg("[JDBCMapping.setParameter] NumberFormatException: propertyName=" + propertyName
							+ " propertyType=" + propertyType);
					statement.setNull(i, Types.INTEGER);
				}
			}
			else if (propertyType.equals(STRING_TYPE) || propertyType.equals("java.lang.String"))
			{
				String lValue = (String) inObject;
				String fmtString = parmProps.getFormatString();
				if (lValue == null)
				{

					if (fmtString.equals(WILD_CARD_IF_BLANK))
					{
						lValue = WILD_CARD;
					}
					else if (fmtString.equals(WILD_CARD_ALWAYS))
					{
						lValue = WILD_CARD;
					}
				}

				if (lValue != null)
				{

					if (fmtString != null)
					{
						if (!fmtString.equals("Preserve Case"))
						{
							lValue = lValue.toUpperCase();
						}

						if (fmtString.equals(WILD_CARD_IF_BLANK) && lValue.equals(""))
						{
							lValue = WILD_CARD;
						}
						else if (fmtString.equals(WILD_CARD_ALWAYS))
						{
							lValue = lValue + WILD_CARD;
						}
					}

					if (parmProps.getConvertWildcard() != null
							&& parmProps.getConvertWildcard().equals("Convert * to %"))
					{
						lValue = lValue.replace('*', '%');
					}
				}

				try
				{
					statement.setString(i, lValue);
				}
				catch (Throwable t)
				{
					t.printStackTrace();
					// TODO Is this the appropriate exception level?
					Object numVal = inObject;
					String val = "";
					if (numVal != null)
					{
						val = numVal.toString();
					}
					int anInt = Integer.parseInt(val);
					statement.setInt(i, anInt);
				}
			}
			else if (propertyType.equals(DATE_TYPE) || propertyType.equals("java.util.Date"))
			{
				Date date1 = (Date) (inObject);
				java.sql.Date date2 = null;
				Timestamp tsDate2 = null;
				if (date1 != null)
				{
					date2 = new java.sql.Date(date1.getTime());
					tsDate2 = new Timestamp(date1.getTime());
				}
				try
				{
					statement.setTimestamp(i, tsDate2);
				}
				catch (Throwable e)
				{
					statement.setDate(i, date2);
				}

			}
			else if (propertyType.equals(INT_TYPE))
			{
				statement.setInt(i, ((Integer) inObject).intValue());
			}
			else if (propertyType.equals(BOOLEAN_LOWER_TYPE))
			{
				statement.setBoolean(i, ((Boolean) inObject).booleanValue());
			}
			else if (propertyType.equals(LONG_LOWER_TYPE))
			{
				statement.setLong(i, ((Long) inObject).longValue());
			}
			else if (propertyType.equals(SHORT_LOWER_TYPE))
			{
				statement.setShort(i, ((Short) inObject).shortValue());
			}
			else if (propertyType.equals(DOUBLE_LOWER_TYPE))
			{
				statement.setDouble(i, ((Double) inObject).doubleValue());
			}
			else if (propertyType.equals(FLOAT_LOWER_TYPE))
			{
				statement.setFloat(i, ((Float) inObject).floatValue());
			}
			else if (propertyType.equals(BYTE_LOWER_TYPE))
			{
				statement.setByte(i, ((Byte) inObject).byteValue());
			}
			else if (propertyType.equals(BLOB_LOWER_TYPE))
			{
				InputStream inputStream = null;
				Blob c = (Blob) inObject;
				if (c != null)
				{
					inputStream = c.getBinaryStream();
					statement.setBinaryStream(i, inputStream, (int) (c.length()));
				}
				else
				{
					statement.setBlob(i, c);
				}

			}
			else if (propertyType.equals(BYTE_ARRAY_LOWER_TYPE))
			{
				byte[] bytes = (byte[]) inObject;
				ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
				statement.setBinaryStream(i, bais, (int) bytes.length);
			}
			else if (propertyType.equals(CLOB_LOWER_TYPE) || propertyType.equals(CHAR_ARRAY_LOWER_TYPE))
			{
				String charString = (String) inObject;
				Reader reader = new CharArrayReader(charString.toCharArray());
				statement.setCharacterStream(i, reader, (int) charString.length());
			}
			else if (propertyType.equals(EVENT_LOWER_TYPE))
			{
				IEvent ev = (IEvent) inObject;
				String value = null;
				if (ev != null)
				{
					value = convertEventToString(ev);
				}
				statement.setString(i, value);
			}
			else
			{
				statement.setObject(i, inObject);
			}
		}
		catch (Exception e)
		{
			this.debugMsg("[JDBCMapping.setParameter] exception: propertyName=" + propertyName + " propertyType="
					+ propertyType);
			this.throwJDBCException(e);
		}
	}

	/** @modelguid {8EA59217-9804-4ED9-95F1-5EC65B3F7D99} */
	public void setPersistentObject(PersistentObject pObj, FieldMappingProperties fieldProps, Object value)
	{
		String propertyName = fieldProps.getPropertyName();
		String propertyType = fieldProps.getPropertyType();
		String index = fieldProps.getParmIndex();
		String fieldName = fieldProps.getDataItemName();

		if (fieldName.toLowerCase().equals(NONE))
		{
			return;
		}

		try
		{
			int i = Integer.parseInt(index);

			String OID = "";

			if (propertyType.equals(STRING_TYPE) || propertyType.equals(STRING_TYPE_LONG))
			{
				if (value != null)
				{
					Reflection.invokeMutatorMethod(pObj, propertyName, value);
				}

			}
			else if (propertyType.equals(DATE_TYPE) || propertyType.equals(DATE_TYPE_LONG))
			{
				Timestamp tsDate = null;
				if (value instanceof java.sql.Date)
				{
					java.sql.Date value1 = (java.sql.Date) value;
					tsDate = new Timestamp(value1.getTime());
				}
				else
				{
					tsDate = (Timestamp) value;
				}
				Date date2 = null;
				if (tsDate != null)
				{
					date2 = new Date(tsDate.getTime());
				}

				Reflection.invokeMutatorMethod(pObj, propertyName, date2);
			}
			else if (propertyType.equals(TIMESTAMP_TYPE))
			{
				Reflection.invokeMutatorMethod(pObj, propertyName, (Timestamp) value);
			}
			else if (propertyType.equals(INT_TYPE))
			{
				Reflection.invokeMutatorMethod(pObj, propertyName, new Integer((String) value.toString()));
			}
			else if (propertyType.equals(BOOLEAN_LOWER_TYPE))
			{
				Reflection.invokeMutatorMethod(pObj, propertyName, (Boolean) value);
			}
			else if (propertyType.equals(LONG_LOWER_TYPE))
			{
				Reflection.invokeMutatorMethod(pObj, propertyName, (Long) value);
			}
			else if (propertyType.equals(SHORT_LOWER_TYPE))
			{
				Reflection.invokeMutatorMethod(pObj, propertyName, (Short) value);
			}
			else if (propertyType.equals(DOUBLE_LOWER_TYPE))
			{
				Reflection.invokeMutatorMethod(pObj, propertyName, (Double) value);
			}
			else if (propertyType.equals(FLOAT_LOWER_TYPE))
			{
				Reflection.invokeMutatorMethod(pObj, propertyName, (Float) value);
			}
			else if (propertyType.equals(BYTE_LOWER_TYPE))
			{
				Reflection.invokeMutatorMethod(pObj, propertyName, (Byte) value);
			}
			else if (propertyType.equals(EVENT_LOWER_TYPE))
			{
				String event = (String) value;
				if (event != null)
				{
					IEvent ev = convertStringToEvent(event);
					Reflection.invokeMutatorMethod(pObj, propertyName, ev);
				}
			}
			else if (propertyType.equals(BLOB_LOWER_TYPE))
			{
				Reflection.invokeMutatorMethod(pObj, propertyName, (Blob) value);
			}
			else if (propertyType.equals(BYTE_ARRAY_LOWER_TYPE))
			{
				if (value != null)
				{
					Blob blob = (Blob) value;
					byte[] bytes = blob.getBytes(1, (int) blob.length());
					Reflection.invokeMutatorMethod(pObj, propertyName, bytes);
				}
			}
			else if (propertyType.equals(CLOB_LOWER_TYPE) || propertyType.equals(CHAR_ARRAY_LOWER_TYPE))
			{
				if (value != null)
				{
					Clob clob = (Clob) value;
					Reader reader = clob.getCharacterStream();
					char[] chars = new char[(int) clob.length()];
					reader.read(chars);
					String charString = String.valueOf(chars);
					Reflection.invokeMutatorMethod(pObj, propertyName, charString);
				}
			}
			else
			{
				Reflection.invokeMutatorMethod(pObj, propertyName, (String) value);
			}
		}
		catch (Exception e)
		{
			String className = pObj.getClass().getName();
			String msg = "JDBC exception retrieving: (" + className + ") propertyName=" + propertyName
					+ " propertyType=" + propertyType + " index=" + index + " fieldName=" + fieldName;
			this.throwJDBCException(msg);
		}
	}

	private String setWhereClauseValues(String whereClause, EventQueryProperties eventProps, IEvent anEvent)
	{
		Iterator parms = eventProps.getParmsIterator();
		byte[] retVal = null;
		while (parms.hasNext())
		{
			ParmMappingProperties parmProps = (ParmMappingProperties) parms.next();
			String propName = parmProps.getPropertyName();
			String replacedValue = "<" + propName + ">";
			String value = "";
			if (parmProps.getPropertyType().toLowerCase().indexOf(STRING_TYPE_LOWER) > -1)
			{
				value = "'" + Reflection.invokeAccessorMethod(anEvent, propName) + "'";
			}
			else if (parmProps.getPropertyType().toLowerCase().indexOf(STRING_ARRAY_LOWER_TYPE) > -1)
			{
				String[] strings = (String[]) Reflection.invokeAccessorMethod(anEvent, propName);
				for (int i = 0; i < strings.length; i++)
				{
					value = value + "'" + strings[i] + "'";
					if (i < (strings.length - 1))
					{
						value += ",";
					}
				}
			}
			else if (parmProps.getPropertyType().toLowerCase().indexOf(DATE_LOWER_TYPE) > -1)
			{
				Date aDate = (Date) (Reflection.invokeAccessorMethod(anEvent, propName));
				Timestamp tsDate = null;
				if (aDate != null)
				{
					tsDate = new Timestamp(aDate.getTime());
				}

				// TODO Consider using a toString... a 'null' value would be of
				// no use here
				value = "" + tsDate;

			}
			else
			{
				// TODO Consider using a toString... a 'null' value would be of
				// no use here
				value = "" + (Reflection.invokeAccessorMethod(anEvent, propName));
			}
			retVal = TextUtil.searchAndReplace(whereClause.getBytes(), replacedValue, value);
		}
		return new String(retVal);
	}

	private void throwJDBCException(Exception e) throws JDBCException
	{
		this.throwJDBCException(e, null);
	}

	private void throwJDBCException(Exception e, String message) throws JDBCException
	{
		this.debugMsg("JDBC exception thread tag");
		e.printStackTrace();
		JDBCException jdbcException = null;
		if (e != null && e instanceof JDBCException)
		{
			jdbcException = (JDBCException) e;
		}
		else if (e != null)
		{
			jdbcException = new JDBCException(e);
		}
		else if (message == null)
		{
			jdbcException = new JDBCException(e.getMessage());
		}
		else
		{
			jdbcException = new JDBCException(message);
		}

		// ExceptionHandler.executeCallbacks(jdbcException);
		throw jdbcException;
	}

	private void throwJDBCException(String message) throws JDBCException
	{
		this.throwJDBCException(new JDBCException(message), message);
	}
}
