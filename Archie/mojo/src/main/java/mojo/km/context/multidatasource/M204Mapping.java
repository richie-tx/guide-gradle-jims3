package mojo.km.context.multidatasource;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;

import mojo.km.config.CallbackProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.config.PropertyBundleProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.logging.LogUtil;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.InfoMessageEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.naming.MappingConstants;
import mojo.km.persistence.AttributeEvent;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.Reflection;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.tools.code.KeyWord;

public class M204Mapping implements IMapping
{
	private static final String DEFAULT_JIMS2USERID = "DEFAULT@JIMS";

	private static final String DEFAULT_PASSWORD = "RCL@JIMS";

	private static final String DEFAULT_USERID = "JIRCL1";

	private static final String END_OF_DATA = "END-OF-DATA";

	private static final String M204_DATE_FORMAT = "yyyyMMdd";

	// M204 mapping constants
	private static final String M204_ERR_CODE_0347 = "M204.0347";

	private static final String M204_EXCEPTION = "M204_EXCEPTION:";

	private static final String M204_INFO_MSG = "M204_INFO";

	private static final String M204_NOT_FOUND = "M204_NOTFOUND";

	private static long MAX_QUERY_ELAPSED_TIME = -1;

	private static final String MAX_QUERY_ELAPSED_TIME_STRING = System.getProperty("jims2.log.perf.m204");

	private static final long MILLION = 1000000;

	private static final String OID_DELIM = PropertyBundleProperties.getInstance().getProperty("OIDDelim");

	private static final int OID_DELIM_LEN = OID_DELIM.length();

	private static final String START_OF_DATA = "START-OF-DATA";

	static
	{
		if (MAX_QUERY_ELAPSED_TIME_STRING != null)
		{
			try
			{
				MAX_QUERY_ELAPSED_TIME = Long.valueOf(MAX_QUERY_ELAPSED_TIME_STRING).longValue();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private CallbackProperties callBack;

	private String connectionName;

	private EntityMappingProperties entityMap;

	public M204Mapping()
	{
	}

	/**
	 * @param connectionName
	 */
	public M204Mapping(String aConnectionName)
	{
		super();
		connectionName = aConnectionName;
	}

	private String buildQueryString(StringBuilder queryBuffer, StringBuilder debugBuffer, IEvent anEvent,
			boolean addCount) throws Exception
	{
		IUserInfo user = null;
		String userId = null;
		String password = null;
		String JIMS2LogonId = null;

		ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");

		if (manager != null)
		{
			user = manager.getIUserInfo();
			if (user != null)
			{
				userId = user.getJIMSLogonId();
				JIMS2LogonId = user.getJIMS2LogonId();
				// no longer in use. Migrated to SM. Refer US #87188.
				//password = user.getPassword();
			}
			else
			{
				userId = DEFAULT_USERID;
				JIMS2LogonId = DEFAULT_JIMS2USERID;
				password = DEFAULT_PASSWORD;
			}
		}
		else
		{
			userId = DEFAULT_USERID;
			JIMS2LogonId = DEFAULT_JIMS2USERID;
			password = DEFAULT_PASSWORD;
		}

		StringBuilder queryProgramName = null;
		String queryFunction = null;
		StringBuilder queryParms = new StringBuilder();

		Iterator parms = callBack.getParmsIterator();

		Object[] args = {};

		while (parms.hasNext())
		{
			ParmMappingProperties parmProps = (ParmMappingProperties) parms.next();

			String parmName = parmProps.getName();

			if (parmName.equals("ProgramName"))
			{
				queryProgramName = new StringBuilder();
				queryProgramName.append(parmProps.getText());
			}
			else if (parmName.equals("Function"))
			{
				queryFunction = parmProps.getText();
			}
			else
			{
				Method fieldMethod = parmProps.getAccessor();

				Object getObj = fieldMethod.invoke(anEvent, args);

				if (getObj != null)
				{
					if (getObj instanceof String)
					{
						String oString = (String) getObj;
						if (oString.indexOf(OID_DELIM) > 0)
						{
							int ii = oString.indexOf(OID_DELIM) + OID_DELIM_LEN;
							oString = oString.substring(ii);
						}

						queryParms.append(parmProps.getDataItemName().substring(3));
						queryParms.append(KeyWord.EQUALS);
						queryParms.append(oString);
						queryParms.append(MappingConstants.SEMICOLON);
					}
					else if (getObj instanceof Date)
					{
						Date date = (Date) getObj;
						queryParms.append(parmProps.getDataItemName().substring(3));
						queryParms.append(KeyWord.EQUALS);
						queryParms.append(DateUtil.dateToString(date, M204_DATE_FORMAT));
						queryParms.append(MappingConstants.SEMICOLON);
					}
					else
					{
						String oString = getObj.toString();
						queryParms.append(parmProps.getDataItemName().substring(3));
						queryParms.append(KeyWord.EQUALS);
						queryParms.append(oString);
						queryParms.append(MappingConstants.SEMICOLON);
					}
				}
			}
		}
		if (anEvent instanceof AttributeEvent)
		{
			AttributeEvent lAttrEvent = (AttributeEvent) anEvent;
			String lAttrName = lAttrEvent.getAttributeName();
			String lAttrValue = lAttrEvent.getAttributeValue().toString();

			FieldMappingProperties fieldProperties = callBack.getFieldMap(lAttrName);

			queryParms.append(fieldProperties.getDataItemName().substring(3));
			queryParms.append(KeyWord.EQUALS);
			queryParms.append(lAttrValue);
			queryParms.append(KeyWord.SEMICOLON);
		}

		if (queryFunction == null)
		{
			queryFunction = "R";
		}

		queryParms.append("FUNCTION=");
		queryParms.append(queryFunction);
		queryParms.append(KeyWord.SEMICOLON);
		queryParms.append("JIMS2.LCUSER=");
		queryParms.append(JIMS2LogonId);
		if (addCount == true)
		{
			queryParms.append(";COUNT.ONLY=y");
		}
		queryParms.append(";EOF;");

		StringBuilder query = new StringBuilder(80);
		if ((userId != null) && (password != null))
		{
			query.append("logon ");
			query.append(userId);
			query.append(KeyWord.SEMICOLON);
			query.append(password);
			query.append(KeyWord.SEMICOLON);
		}
		if (queryProgramName == null)
		{
			queryProgramName = new StringBuilder(20);
			queryProgramName.append(callBack.getFileName());
			queryProgramName.append(KeyWord.SPACE);
			queryProgramName.append(callBack.getSource());
		}

		query.append(queryProgramName);

		query.append(KeyWord.SEMICOLON);
		query.append(queryParms.toString());

		debugBuffer.append(queryProgramName);
		debugBuffer.append(KeyWord.SEMICOLON);
		debugBuffer.append(queryParms.toString());

		queryBuffer.append(queryProgramName);
		queryBuffer.append(KeyWord.SEMICOLON);
		queryBuffer.append(queryParms.toString());

		return query.toString();

	}

	private void convertRecordsToPersistentObjects(Collection records, Map retVal, Class pType) throws Exception
	{
		if (records != null)
		{
			PersistentObject pObj = null;

			Iterator i = records.iterator();
			while (i.hasNext())
			{
				Map dataMap = (Map) i.next();

				String mapOid = (String) dataMap.get(MappingConstants.OID);

				if (retVal.containsKey(mapOid))
				{
					pObj = (PersistentObject) retVal.get(mapOid);
				}
				else
				{
					pObj = (PersistentObject) pType.newInstance();
				}

				Iterator fields = callBack.getFieldsIterator();
				while (fields.hasNext())
				{
					FieldMappingProperties fieldProps = (FieldMappingProperties) fields.next();

					String value = (String) dataMap.get(fieldProps.getName());

					setPersistentObject(pObj, value, fieldProps);
				}

				retVal.put(pObj.getOID(), pObj);
				pObj.setNotNew();
			}
		}
	}

	private PersistentObject createPersistentObject(String resultData, Map retVal, Class pType, Map dataMap)
			throws InstantiationException, IllegalAccessException
	{
		PersistentObject pObj = null;

		int commaPos = resultData.indexOf(",");
		if (commaPos > 0)
		{
			String position = resultData.substring(0, commaPos).trim();
			FieldMappingProperties props = (FieldMappingProperties) callBack.getFieldByPositionMap(position);
			if (props != null)
			{
				String columnName = props.getName();
				String columnValue = resultData.substring(commaPos + 1).trim();

				// check for OID in the first record, if it exists take
				// advantage for optimization
				if (MappingConstants.OID.equalsIgnoreCase(columnName))
				{

					Object obj = retVal.get(columnValue);
					if (obj == null)
					{
						pObj = (PersistentObject) pType.newInstance();
						pObj.setOID(columnValue);
					}
					else
					{
						pObj = (PersistentObject) obj;
					}
				}
				else
				{
					// OID is not first record, therefore use less performant
					// strategy
					dataMap.put(columnName, columnValue);
				}
			}
		}
		else
		{
			// ignore parse logic because of unexpected condition
			LogUtil.log(Level.DEBUG, "M204 message: " + resultData);
		}

		return pObj;
	}

	/*
	 * Used to set up inserts and updates
	 */
	private String formatQuery(StringBuilder debugBuffer, PersistentObject pObj) throws Exception
	{
		SaveCallbackProperties saveProps = (SaveCallbackProperties) this.callBack;
		StringBuilder queryParms = new StringBuilder(50);
		String queryFunction;

		// TODO optimize logic here
		if (pObj.isNew())
		{
			queryFunction = "C";
		}
		else
		{
			queryFunction = "U";
		}

		if (pObj.isDeleted())
		{
			queryFunction = "D";
		}

		Object[] parms = {};
		Object getObj;

		FieldMappingProperties[] fields = saveProps.getFieldsArray();
		for (int i = 0; i < fields.length; i++)
		{
			FieldMappingProperties fProps = (FieldMappingProperties) fields[i];
			if (fProps.isDataItemNameNone() == true)
			{
				continue;
			}

			Method accessor = fProps.getAccessor();
			if (accessor == null)
			{
				LogUtil.log(Level.WARN, "Missing accessor: " + fProps);
				String propertyName = fProps.getPropertyName();
				getObj = Reflection.invokeAccessorMethod(pObj, propertyName);
			}
			else
			{
				getObj = accessor.invoke(pObj, parms);
			}

			String temp = "";

			if (getObj != null)
			{
				StringBuilder buffer = new StringBuilder(30);
				buffer.append(fProps.getDataItemName().substring(3));
				buffer.append(KeyWord.EQUALS);
				if (getObj instanceof String)
				{
					String oString = (String) getObj;
					if (oString.indexOf(OID_DELIM) > 0)
					{
						int ii = oString.indexOf(OID_DELIM) + OID_DELIM_LEN;
						oString = oString.substring(ii);
					}
					buffer.append(oString);
					buffer.append(KeyWord.SEMICOLON);
					temp = buffer.toString();
				}
				else if (getObj instanceof Date)
				{
					Date dateObj = (Date) getObj;
					buffer.append(DateUtil.dateToString(dateObj, M204_DATE_FORMAT));
					buffer.append(KeyWord.SEMICOLON);
					temp = buffer.toString();
				}
				else
				{
					String oString = getObj.toString();
					buffer.append(oString);
					buffer.append(KeyWord.SEMICOLON);
					temp = buffer.toString();
				}
			}
			queryParms.append(temp);

		}

		// TODO - need to handle situation if queryProgram name doesn't get set

		// Have to go thru parms again to get data parms. Could not make it work
		// to get program name and data in one go-around.

		Iterator x = saveProps.getParmsIterator();

		String queryProgramName = null;

		while (x.hasNext())
		{
			ParmMappingProperties pProps = (ParmMappingProperties) x.next();
			String name = pProps.getName();

			if (name.equals("ProgramName"))
			{
				queryProgramName = pProps.getText();
			}
			else if (name.equals("Function"))
			{
				queryFunction = pProps.getText();
			}
			else
			{
				Method accessor = pProps.getAccessor();
				if (accessor == null)
				{
					LogUtil.log(Level.WARN, "Missing accessor: " + pProps);
					String propertyName = pProps.getPropertyName();
					getObj = Reflection.invokeAccessorMethod(pObj, propertyName);
				}
				else
				{
					getObj = accessor.invoke(pObj, parms);
				}

				if (getObj != null)
				{
					queryParms.append(pProps.getDataItemName().substring(3));
					queryParms.append(KeyWord.EQUALS);
					if (getObj instanceof String)
					{
						queryParms.append((String) getObj);
					}
					else if (getObj instanceof Date)
					{
						queryParms.append(DateUtil.dateToString((Date) getObj, M204_DATE_FORMAT));
					}
					else
					{
						queryParms.append(getObj.toString());
					}
					queryParms.append(KeyWord.SEMICOLON);
				}
			}
		}

		String userId;
		String password;
		String JIMS2LogonId;
		IUserInfo user = null;

		ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");

		if (manager != null)
		{
			user = manager.getIUserInfo();
		}

		if (user != null)
		{
			userId = user.getJIMSLogonId();
			// no longer in use. Migrated to SM. Refer US #87188.
			//password = user.getPassword(); 
			JIMS2LogonId = user.getJIMS2LogonId();
		}
		else
		{
			// TODO Should these defaults be set?
			userId = DEFAULT_USERID;
		//	password = DEFAULT_PASSWORD;
			JIMS2LogonId = DEFAULT_JIMS2USERID;
		}

		queryParms.append("FUNCTION=");
		queryParms.append(queryFunction);
		queryParms.append(KeyWord.SEMICOLON);
		queryParms.append("JIMS2.LCUSER=");
		queryParms.append(JIMS2LogonId);
		queryParms.append(KeyWord.SEMICOLON);
		queryParms.append("EOF;");

		StringBuilder queryBuffer = new StringBuilder(50);
		// no longer in use. Migrated to SM. Refer US #87188.
		/*if ((userId != null) && (password != null))
		{
			queryBuffer.append("LOGON ");
			queryBuffer.append(userId);
			queryBuffer.append(KeyWord.SEMICOLON);
			queryBuffer.append(password);
			queryBuffer.append(KeyWord.SEMICOLON);
		}*/

		StringBuilder suffixBuffer = new StringBuilder(50);

		if (queryProgramName == null)
		{
			suffixBuffer.append(saveProps.getFileName());
			suffixBuffer.append(KeyWord.SPACE);
			suffixBuffer.append(saveProps.getSource());
		}

		suffixBuffer.append(KeyWord.SEMICOLON);
		suffixBuffer.append(queryParms);

		queryBuffer.append(suffixBuffer);
		debugBuffer.append(suffixBuffer);

		return queryBuffer.toString();
	}

	public CallbackProperties getCallback()
	{
		return this.callBack;
	}

	public String getConnectionName()
	{
		return connectionName;
	}

	public EntityMappingProperties getEntityMap()
	{
		return this.entityMap;
	}

	private void handleInvalidDate(Object obj, FieldMappingProperties fProps, String value, String dateFormat)
	{
		InfoMessageEvent infoEvent = new InfoMessageEvent();

		StringBuilder buffer = new StringBuilder();
		buffer.append("Model 204 invalid date format: ");
		buffer.append(value);
		buffer.append(" (");
		buffer.append(obj.getClass().getName());
		buffer.append("): ");
		buffer.append(" expecting format: ");
		buffer.append(dateFormat);
		buffer.append(" fieldProperties: ");
		buffer.append(fProps);
		String msg = buffer.toString();

		// TODO Put invalid date condition in ErrorLog

		LogUtil.log(Level.ERROR, msg);
		infoEvent.setMessage(msg);
		EventManager.getSharedInstance(EventManager.REPLY).postEvent(infoEvent);
	}

	public void init(String key)
	{

	}

	private void loadPersistentData(Statement statement, String query, List retVal, Class pType) throws Exception
	{
		ResultSet rSet = null;
		InfoMessageEvent infoEvent = null;

		FieldMappingProperties fProps = null;

		Object[] obj = new Object[1];

		String resultData = null;

		try
		{
			rSet = statement.executeQuery(query);

			PersistentObject pObj = null;

			while (rSet.next())
			{
				resultData = rSet.getString(1);

				if (resultData == null)
				{
					continue;
				}

				obj[0] = null;

				if (resultData.startsWith(M204_EXCEPTION))
				{
					throw new M204Exception(resultData);
				}
				else if (resultData.startsWith(START_OF_DATA))
				{
					pObj = (PersistentObject) pType.newInstance();
					retVal.add(pObj);
				}
				else if (resultData.startsWith(END_OF_DATA))
				{
					pObj.setNotNew();
					// do nothing
				}
				else if (resultData.startsWith(M204_NOT_FOUND))
				{
					LogUtil.log(Level.DEBUG, "M204 MESSAGE: " + resultData);
				}
				else if (resultData.indexOf(M204_ERR_CODE_0347) > -1)
				{
					// do nothing
				}
				else if (resultData.startsWith(M204_INFO_MSG))
				{
					infoEvent = new InfoMessageEvent();
					infoEvent.setMessage(resultData);
				}
				else
				{
					int commaPos = resultData.indexOf(",");
					if (commaPos > 0)
					{
						String pos = resultData.substring(0, commaPos).trim();
						fProps = (FieldMappingProperties) callBack.getFieldByPositionMap(pos);

						if (fProps != null)
						{
							String value = resultData.substring(commaPos + 1).trim();
							switch (fProps.getPropertyTypeInt())
							{
							case MappingConstants.STRING_DATATYPE:
								obj[0] = value;
								break;
							case MappingConstants.DATE_DATATYPE:
								try
								{
									Date date = DateUtil.stringToDate(value, M204_DATE_FORMAT);
									obj[0] = date;
								}
								catch (ParseRuntimeException p)
								{
									this.handleInvalidDate(pObj, fProps, value, M204_DATE_FORMAT);
								}
								break;
							case MappingConstants.TIMESTAMP_DATATYPE:
								try
								{
									long millis = DateUtil.stringToDate(value, M204_DATE_FORMAT).getTime();
									Timestamp timestamp = new Timestamp(millis);
									obj[0] = timestamp;
								}
								catch (ParseRuntimeException p)
								{
									this.handleInvalidDate(pObj, fProps, value, M204_DATE_FORMAT);
								}
								break;
							case MappingConstants.INT_DATATYPE:
								obj[0] = new Integer(value);
								break;
							case MappingConstants.BOOLEAN_DATATYPE:
								obj[0] = new Boolean(value);
								break;
							case MappingConstants.BYTE_DATATYPE:
								obj[0] = new Byte(value);
								break;
							case MappingConstants.SHORT_DATATYPE:
								obj[0] = new Short(value);
								break;
							case MappingConstants.DOUBLE_DATATYPE:
								obj[0] = new Double(value);
								break;
							case MappingConstants.FLOAT_DATATYPE:
								obj[0] = new Float(value);
								break;
							case MappingConstants.LONG_DATATYPE:
								obj[0] = new Long(value);
								break;
							default:
								throw new M204Exception("Invalid mapping type: " + fProps);
							}

							Method mutator = fProps.getMutator();

							if (mutator != null)
							{
								mutator.invoke(pObj, obj);
							}
							else
							{
								String propertyName = fProps.getPropertyName();
								Reflection.invokeMutatorMethod(pObj, propertyName, obj[0]);
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			String msg = "Error setting M204 results on (" + pType.getName() + ") = " + obj[0] + ": " + resultData
					+ " properties: " + fProps;
			LogUtil.log(Level.ERROR, msg);
			LogUtil.log(Level.ERROR, e);
			throw e;
		}
		finally
		{
			if (rSet != null)
			{
				rSet.close();
			}

			if (infoEvent != null)
			{
				LogUtil.log(Level.INFO, infoEvent.getMessage());
				EventManager.getSharedInstance(EventManager.REPLY).postEvent(infoEvent);
			}
		}
	}

	private void loadPersistentData(Statement statement, String query, Map retVal, Class pType) throws Exception
	{
		boolean hasOID = false;
		ArrayList records = new ArrayList();
		ResultSet rSet = null;
		InfoMessageEvent infoEvent = null;

		try
		{
			rSet = statement.executeQuery(query);
			// this.debugMsg("open resultset "+rSet);

			Map dataMap = null;
			PersistentObject pObj = null;

			while (rSet.next())
			{
				String resultData = (String) rSet.getString(1);

				if (resultData == null)
				{
					continue;
				}

				if (resultData.startsWith(M204_EXCEPTION))
				{
					// EXCEPTION MESSAGE
					// TODO verify propogation of exceptions (confirm stack
					// traces are getting printed)
					throw new M204Exception(resultData);

				}
				else if (resultData.startsWith(START_OF_DATA))
				{
					// START OF RECORD
					if (rSet.next())
					{
						resultData = (String) rSet.getString(1);

						// initialize map or else only a single record will be
						// created
						dataMap = new HashMap();

						// attempt to create PO if OID is first in record set
						pObj = this.createPersistentObject(resultData, retVal, pType, dataMap);

						// set optimization strategy (if OID is first record
						// then the pObj can be set immediately)
						hasOID = (pObj != null);
					}
				}
				else if (resultData.startsWith(END_OF_DATA))
				{
					// END OF RECORD
					if (hasOID == true)
					{
						pObj.setNotNew();
						retVal.put(pObj.getOID(), pObj);
					}
					else
					{
						records.add(dataMap);
					}
				}
				else if (resultData.startsWith(M204_NOT_FOUND))
				{
					LogUtil.log(Level.DEBUG, "M204 MESSAGE: " + resultData);
				}
				else if (resultData.indexOf(M204_ERR_CODE_0347) > -1)
				{
					// do nothing
				}
				else if (resultData.startsWith(M204_INFO_MSG))
				{
					infoEvent = new InfoMessageEvent();
					infoEvent.setMessage(resultData);
				}
				else
				{
					// set pObj OR dataMap depending if OID was first record in
					// result set
					this.setRecord(resultData, dataMap, pObj);
				}
			}
		}
		finally
		{
			if (rSet != null)
			{
				rSet.close();
			}
		}

		if (hasOID == false)
		{
			// convert record maps into persistent objects (of class pType)
			this.convertRecordsToPersistentObjects(records, retVal, pType);
		}

		if (infoEvent != null)
		{
			LogUtil.log(Level.INFO, infoEvent.getMessage());
			EventManager.getSharedInstance(EventManager.REPLY).postEvent(infoEvent);
		}
	}

	/**
	 * @param pClass
	 * @param anEvent
	 * @return ArrayList
	 * @roseuid 407D8A4E0143
	 */
	public List retrieve(IEvent anEvent, Class pType, Map retVal)
	{
		long starttime = System.nanoTime();

		IConnection connection = TransactionManager.getInstance().getConnection(connectionName);
		Connection m204Connection = (Connection) connection.getResource();
		Statement statement = null;
		StringBuilder debugBuffer = new StringBuilder(256);

		EventQueryProperties qProps = (EventQueryProperties) this.callBack;

		debugBuffer.append(LogUtil.EXECUTE_QUERY);
		debugBuffer.append(" M204 (");
		String key = qProps.getQueryKey();
		debugBuffer.append(key);
		debugBuffer.append(");\n");

		String query = null;

		StringBuilder queryBuffer = new StringBuilder();

		ArrayList results = null;

		try
		{
			query = buildQueryString(queryBuffer, debugBuffer, anEvent, false);

			statement = m204Connection.createStatement();
			statement.setFetchSize(25000);

			if (retVal == null)
			{
				results = new ArrayList();
				loadPersistentData(statement, query, results, pType);
			}
			else
			{
				loadPersistentData(statement, query, retVal, pType);
				results = new ArrayList(retVal.size());
				results.addAll(retVal.values());
			}
		}
		catch (Exception e)
		{
			throw new M204Exception(e.getMessage(), e);
		}
		finally
		{
			long endtime = System.nanoTime();
			long elapsedTime = (endtime - starttime) / MILLION;

			debugBuffer.append("\nparms: | elapsedTime: ");
			debugBuffer.append(elapsedTime);
			debugBuffer.append(" ms");

			if (MAX_QUERY_ELAPSED_TIME != -1 && elapsedTime > MAX_QUERY_ELAPSED_TIME)
			{
				String shortDesc = "poor performance: " + elapsedTime + " ms";
				String longDesc = queryBuffer.toString();

				String sourceName = qProps.getSource() + "." + qProps.getFileName();
				ExceptionHandler.addWarning("M204 Perf", null, sourceName, elapsedTime, shortDesc, longDesc);
			}

			LogUtil.log(Level.DEBUG, debugBuffer.toString());

			try
			{
				if (statement != null)
				{
					statement.close();
				}
			}
			catch (SQLException e)
			{
				throw new M204Exception("Failed to close statement " + e.getMessage(), e);
			}
			finally
			{
				TransactionManager.releaseConnection(connectionName, connection);
			}
		}
		return results;
	}

	public MetaDataResponseEvent retrieveMeta(IEvent anEvent, Class pType, Map retVal)
	{
		long starttime = System.nanoTime();
		IConnection connection = TransactionManager.getInstance().getConnection(connectionName);
		Connection m204Connection = (Connection) connection.getResource();
		Statement statement = null;
		MetaDataResponseEvent infoEvent = new MetaDataResponseEvent();

		StringBuilder debugBuffer = new StringBuilder(256);

		EventQueryProperties qProps = (EventQueryProperties) this.callBack;

		try
		{
			debugBuffer.append("executeQuery M204 (");
			String key = qProps.getQueryKey();
			debugBuffer.append(key);
			debugBuffer.append(");\n");

			statement = m204Connection.createStatement();

			StringBuilder queryBuffer = new StringBuilder();

			String query = buildQueryString(queryBuffer, debugBuffer, anEvent, true);

			statement = m204Connection.createStatement();
			statement.setFetchSize(25000);

			ResultSet rSet = null;

			rSet = statement.executeQuery(query);

			while (rSet.next())
			{
				String resultData = rSet.getString(1);

				if (resultData == null)
				{
					continue;
				}

				if (resultData.startsWith("M204_INFO: ROW COUNT="))
				{
					int pos = resultData.indexOf("=");
					if (pos > 0)
					{
						String columnValue = resultData.substring(pos + 1).trim();
						infoEvent.setCount(Integer.parseInt(columnValue));
					}
				}
			}
		}
		catch (Exception e)
		{
			throw new M204Exception(e.getMessage(), e);
		}
		finally
		{
			long endtime = System.nanoTime();
			long elapsedTime = (endtime - starttime) / MILLION;

			debugBuffer.append("\nparms: | elapsedTime: ");
			debugBuffer.append(elapsedTime);
			debugBuffer.append(" ms");

			LogUtil.log(Level.DEBUG, debugBuffer.toString());

			try
			{
				statement.close();
			}
			catch (SQLException e1)
			{
				throw new M204Exception(e1.getMessage(), e1);
			}
			finally
			{
				TransactionManager.releaseConnection(connectionName, connection);
			}
		}
		return infoEvent;
	}

	/**
	 * @param perObj
	 * @roseuid 407D86DA00C9
	 */
	public void save(PersistentObject pObj)
	{
		long starttime = System.nanoTime();

		String key = "[key undefined]";
		String query = "[query undefined]";

		IConnection connection = TransactionManager.getInstance().getConnection(connectionName);

		SaveCallbackProperties sProps = (SaveCallbackProperties) this.callBack;

		StringBuilder debugBuffer = new StringBuilder(200);
		debugBuffer.append(LogUtil.EXECUTE_UPDATE);
		debugBuffer.append(" M204 (");

		try
		{
			// TODO optmize to get key from SaveCallbackProperties
			if (pObj.isNew())
			{
				key = sProps.getInsertQueryKey();
			}
			else if (pObj.isDeleted())
			{
				key = sProps.getDeleteQueryKey();
			}
			else
			{
				key = sProps.getUpdateQueryKey();
			}

			debugBuffer.append(key);
			debugBuffer.append(");\n");

			query = formatQuery(debugBuffer, pObj);

			Connection m204Connection = (Connection) connection.getResource();
			Statement statement = m204Connection.createStatement();

			statement.execute(query);

			ResultSet rSet = statement.getResultSet();

			Iterator fump = sProps.getFieldsIterator();
			HashMap fieldMappings = new HashMap();

			// For each field mapping, get its position attr. and store
			// both in a HashMap.
			while (fump.hasNext())
			{
				FieldMappingProperties lField = (FieldMappingProperties) fump.next();
				String lPos = lField.getPosition();
				fieldMappings.put(lPos, lField);
			}

			while (rSet.next())
			{
				String data1 = (String) rSet.getString(1);
				if (data1 == null)
				{
					continue;
				}
				else
				{
					int commaPos = data1.indexOf(",");

					if (data1.startsWith("M204_EXCEPTION"))
					{
						throw new M204Exception(data1);
					}
					else if (data1.startsWith("START-OF-DATA"))
					{
					}
					else if (data1.startsWith("END-OF-DATA"))
					{
						pObj.setNotNew();
					}
					else if (commaPos < 0)
					{
						LogUtil.log(Level.DEBUG, "M204 message: " + data1);
					}
					else if (commaPos > 0)
					{
						int comma = data1.indexOf(",");

						String position = data1.substring(0, comma).trim();
						FieldMappingProperties fProps = (FieldMappingProperties) fieldMappings.get(position);

						String value = data1.substring(comma + 1).trim();

						if (fProps != null)
						{
							setPersistentObject(pObj, value, fProps);
						}
					}
				}
			}
		}
		catch (M204Exception e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new M204Exception(e.getMessage(), e);
		}
		finally
		{
			long endtime = System.nanoTime();
			long elapsedTime = (endtime - starttime) / MILLION;

			debugBuffer.append("\n | elapsedTime: ");
			debugBuffer.append(elapsedTime);
			debugBuffer.append(" ms");
			LogUtil.log(Level.DEBUG, debugBuffer.toString());
			TransactionManager.releaseConnection(connectionName, connection);
		}
	}

	public void setCallback(CallbackProperties aCallBack)
	{
		this.callBack = aCallBack;
	}

	public void setConnectionName(String connName)
	{
		connectionName = connName;
	}

	public void setEntityMap(EntityMappingProperties anEntityMap)
	{
		this.entityMap = anEntityMap;
	}

	/**
	 * This method is used for multiple retrievers and savers (savers are always single).
	 * 
	 * @param perObj
	 * @param rSet
	 * @param propertyName
	 * @param propertyType
	 * @param index
	 * @param applicationType
	 * @param fieldName
	 * @throws Exception
	 */
	public void setPersistentObject(PersistentObject pObj, String value, FieldMappingProperties fProps)
			throws Exception
	{
		Object[] obj = new Object[1];

		switch (fProps.getPropertyTypeInt())
		{
		case MappingConstants.STRING_DATATYPE:
			obj[0] = value;
			break;
		case MappingConstants.DATE_DATATYPE:
			try
			{
				Date date = DateUtil.stringToDate(value, M204_DATE_FORMAT);
				obj[0] = date;
			}
			catch (ParseRuntimeException p)
			{
				this.handleInvalidDate(pObj, fProps, value, M204_DATE_FORMAT);
			}
			break;
		case MappingConstants.TIMESTAMP_DATATYPE:
			try
			{
				Timestamp timestamp = new Timestamp(DateUtil.stringToDate(value, M204_DATE_FORMAT).getTime());
				obj[0] = timestamp;
			}
			catch (ParseRuntimeException p)
			{
				this.handleInvalidDate(pObj, fProps, value, M204_DATE_FORMAT);
			}
			break;
		case MappingConstants.INT_DATATYPE:
			obj[0] = new Integer(value);
			break;
		case MappingConstants.BOOLEAN_DATATYPE:
			obj[0] = new Boolean(value);
			break;
		case MappingConstants.BYTE_DATATYPE:
			obj[0] = new Byte(value);
			break;
		case MappingConstants.SHORT_DATATYPE:
			obj[0] = new Short(value);
			break;
		case MappingConstants.DOUBLE_DATATYPE:
			obj[0] = new Double(value);
			break;
		case MappingConstants.FLOAT_DATATYPE:
			obj[0] = new Float(value);
			break;
		case MappingConstants.LONG_DATATYPE:
			obj[0] = new Long(value);
			break;
		default:
			throw new M204Exception("Invalid mapping type: " + fProps);
		}

		Method mutator = fProps.getMutator();

		if (mutator != null)
		{
			mutator.invoke(pObj, obj);
		}
		else
		{
			String propertyName = fProps.getPropertyName();
			Reflection.invokeMutatorMethod(pObj, propertyName, obj[0]);
		}
	}

	/**
	 * This method is used for multiple retrievers.
	 * 
	 * @param resultData
	 * @param dataMap
	 * @param pObj
	 * @throws Exception
	 */
	private void setRecord(String resultData, Map dataMap, PersistentObject pObj) throws Exception
	{
		int commaPos = resultData.indexOf(",");
		if (commaPos > 0)
		{
			// TODO validation check, position should be an integer
			String position = resultData.substring(0, commaPos).trim();

			FieldMappingProperties props = (FieldMappingProperties) callBack.getFieldByPositionMap(position);
			if (props != null)
			{
				// field value in M204 result set was found in mapping
				String columnName = props.getName();
				String columnValue = resultData.substring(commaPos + 1).trim();

				// find out if the persistent object been located by the OID
				// in the first record
				if (pObj == null)
				{
					dataMap.put(columnName, columnValue);
				}
				else
				{
					setPersistentObject(pObj, columnValue, props);
				}
			}
		}
		else
		{
			// ignore in parse logic because it is an unexpected condition
			LogUtil.log(Level.DEBUG, "M204 MESSAGE: " + resultData);
		}
	}

}
