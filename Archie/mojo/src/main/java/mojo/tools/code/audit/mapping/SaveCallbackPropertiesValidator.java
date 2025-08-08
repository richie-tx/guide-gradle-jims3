/*
 * Created on Jun 13, 2006
 *
 */
package mojo.tools.code.audit.mapping;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mojo.km.config.ConnectionProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.context.multidatasource.IConnection;
import mojo.km.naming.MappingConstants;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.tools.code.audit.AuditError;
import mojo.tools.code.audit.AuditResult;

/**
 * @author Jim Fisher
 * 
 */
public class SaveCallbackPropertiesValidator implements IMappingValidator
{
	private static final String BEGIN_BOLD = "#{begin_bold}";

	private static final String DERIVED_COLUMN = "DERIVED";

	private static final String END_BOLD = "#{end_bold}";

	private static final String VARCHAR_BOLD = BEGIN_BOLD + MappingConstants.VARCHAR + END_BOLD;

	boolean cacheMapping;

	private boolean connectionValid;

	private boolean fileIOMapping = false;

	private SaveCallbackProperties props;

	private AuditResult result;

	private boolean sourceValid;

	protected boolean validEntity;

	private boolean validMapping = true;

	private PersistenceMappingValidator visitor;

	public SaveCallbackPropertiesValidator(SaveCallbackProperties aProps)
	{
		this.props = aProps;
		this.result = new AuditResult();
	}

	public void accept(IMappingValidatorVisitor aVisitor)
	{
		this.visitor = (PersistenceMappingValidator) aVisitor;

		this.validate();

		// validate parm and field mappings
		this.visitor.visit(this);
	}

	private void getJDBCTableMetaData()
	{
		String source;
		if (props.getSource() == null)
		{
			source = MappingConstants.NONE_UPPER;
		}
		else
		{
			source = props.getSource().trim();
		}

		Map metaData = visitor.getMetaData(source);
		Map metaDataTypes = visitor.getMetaDataTypes(source);

		if (metaData == null)
		{
			metaData = new HashMap();
			metaDataTypes = new HashMap();

			ResultSet rs = null;

			ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties(MappingConstants.JDBC);

			Connection jdbcConnection = null;

			try
			{
				Class.forName(cProps.getDriver());
				jdbcConnection = DriverManager.getConnection(cProps.getTestUrl(), cProps.getUserID(), cProps
						.getPassword());
				jdbcConnection.setAutoCommit(false);

				DatabaseMetaData dbMetaData = jdbcConnection.getMetaData();

				rs = dbMetaData.getColumns(null, null, source, "%");

				boolean hasResults = rs.next();

				if (hasResults == false)
				{
					AuditError error = new AuditError(MappingConstants.INVALID_SOURCE, AuditError.ERROR);
					error.setMessage("invalid source: " + source);
					this.result.addError(error);
					this.sourceValid = false;
				}
				else
				{
					boolean done = false;
					while (done == false)
					{
						String columnName = rs.getString("COLUMN_NAME").trim();
						String dataType = rs.getString("TYPE_NAME");

						int typeInt = rs.getInt("DATA_TYPE");

						metaData.put(columnName, dataType);
						metaDataTypes.put(columnName, new Integer(typeInt));

						done = !rs.next();
					}
					visitor.addMetaData(source, metaData, metaDataTypes);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (rs != null)
				{
					try
					{
						rs.close();
					}
					catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if(jdbcConnection != null)
				{
					try
					{
						jdbcConnection.commit();
						jdbcConnection.close();
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void getM204MetaData()
	{
		int count = 0;

		String source;
		if (props.getSource() == null)
		{
			source = MappingConstants.NONE_UPPER;
		}
		else
		{
			source = props.getSource().trim();
		}

		String filename = props.getFileName();

		String key = filename + source;

		Map metaData = visitor.getMetaData(key);

		if (metaData == null)
		{
			metaData = new HashMap();

			String connectionName = this.props.getConnectionName();

			IConnection connection = TransactionManager.getInstance().getConnection(connectionName);

			// validate JDBC queries
			if (connection != null)
			{
				Statement stmt = null;
				ResultSet rs = null;

				try
				{
					Connection m204Connection = (Connection) connection.getResource();

					// j2metadata
					// rpc.returns;function=r;rpc=j2jjsms;rectype=juvenile;eof

					StringBuffer buffer = new StringBuffer();
					buffer.append("J2METADATA RPC.RETURNS;FUNCTION=R;JIMS2.LCUSER=null;rpc=");
					buffer.append(filename);
					buffer.append(";RECTYPE=");
					buffer.append(source);
					buffer.append(";EOF;");
					String query = buffer.toString();
					System.out.println("m204 metadata: " + query);

					stmt = m204Connection.createStatement();

					rs = stmt.executeQuery(query);

					while (rs.next())
					{
						String record = rs.getString(1);
						int commaPos = record.indexOf(",");
						if (commaPos > -1)
						{
							String pos = record.substring(0, commaPos);
							String columnName = record.substring(commaPos + 1);
							metaData.put(pos, columnName);
							count++;
						}
					}
					// TODO Possible put a null here
					visitor.addMetaData(key, metaData, new HashMap());
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				finally
				{
					if (rs != null)
					{
						try
						{
							rs.close();
							stmt.close();
						}
						catch (SQLException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					TransactionManager.releaseConnection(connectionName, connection);
				}

				if (count == 0)
				{
					AuditError error = new AuditError(MappingConstants.INVALID_SOURCE, AuditError.ERROR);
					StringBuffer buffer = new StringBuffer();
					buffer.append("invalid M204 query: file='");
					buffer.append(filename);
					buffer.append("' source='");
					buffer.append(source);
					buffer.append("'");
					error.setMessage(buffer.toString());
					this.result.addError(error);
					this.sourceValid = false;
				}
			}
		}
	}

	public AuditResult getResult()
	{
		return this.result;
	}

	private boolean isCacheMapping()
	{
		return this.cacheMapping;
	}

	public boolean isFileIOMapping()
	{
		return fileIOMapping;
	}

	/**
	 * @return
	 */
	private boolean isValidEntity()
	{
		return validEntity;
	}

	/**
	 * @return Returns the validMapping.
	 */
	public boolean isValidMapping()
	{
		return validMapping;
	}

	private void validate()
	{
		String unitName = visitor.getCompilationUnit().getMainType().getQualifiedName();
		System.out.println("VALIDATING: " + unitName + "::" + props.getParent());
		String source = props.getSource().trim();
		String connectionName = props.getConnectionName();

		StringBuffer msgBuffer = new StringBuffer();
		if (source.startsWith(MappingConstants.JDBC) == false)
		{
			msgBuffer.append(props.getFileName());
			msgBuffer.append("-->");
		}
		msgBuffer.append(source);

		msgBuffer.append(" (");
		msgBuffer.append(connectionName);
		msgBuffer.append(")");

		this.result.setMessage(msgBuffer.toString());

		// RULE: validate mapping class name
		this.validateMappingClass(unitName);

		EntityMappingProperties eProps = props.getParent();

		Class clazz = null;
		if (this.isValidMapping() == true)
		{
			clazz = this.validateEntity(eProps.getEntity());
		}

		boolean noValidate = eProps.isBufferMapping() || eProps.isReportMapping() || isFileIOMapping()
				|| isCacheMapping() || (isValidMapping() == false) || (isValidEntity() == false);

		if (noValidate == false)
		{
			// RULE: validate method
			this.validateMappingMethod();

			// RULE: validate source
			this.validateSource(unitName);

			// RULE: validate connection name
			this.validateConnection(unitName, connectionName);

			if (sourceValid == true && connectionValid == true)
			{
				// TODO RULE: validate ParmMappings

				// RULE: validate FieldMappings
				if (props.getConnectionName().equals(MappingConstants.JDBC))
				{
					this.validateJDBCFieldMappings(clazz);

					this.validateJDBCParmMappings(clazz);
				}
				else if (props.getConnectionName().equals(MappingConstants.M204))
				{
					this.validateM204FieldMappings(clazz);
				}
			}

			// RULE(warning): validate where clause
			this.validateWhere();
		}
	}

	/**
	 * @param clazz
	 */
	private void validateJDBCParmMappings(Class aClazz)
	{
		// TODO create audit for OID and event queries, (Attribute, AllQuery -->
		// info for parms)

		Iterator p = props.getParmsIterator();

		String source = props.getSource().trim();
		Map metaData = visitor.getMetaData(source);
		Map metaDataTypes = visitor.getMetaDataTypes(source);

		while (p.hasNext())
		{
			ParmMappingProperties pProps = (ParmMappingProperties) p.next();

			// RULE(error) validate database mapping
			this.validateJDBCParmColumn(pProps, metaData, metaDataTypes);
		}
	}

	private void validateJDBCParmColumn(ParmMappingProperties fProps, Map metaData, Map metaDataTypes)
	{
		String columnName = fProps.getDataItemName();
		if (metaData.containsKey(columnName) == false)
		{
			AuditError error = new AuditError(MappingConstants.MISSING_COLUMN, AuditError.ERROR);
			error.setMessage("parm column not in database: " + fProps);
			this.result.addError(error);
		}
		else
		{
			boolean hasError = false;
			boolean hasWarning = false;

			String propertyType = fProps.getPropertyType();
			int columnTypeInt = ((Integer) metaDataTypes.get(columnName)).intValue();

			if ((propertyType.equalsIgnoreCase(MappingConstants.STRING_TYPE)
					|| propertyType.equalsIgnoreCase(MappingConstants.STRING_TYPE_LONG)
					|| propertyType.equalsIgnoreCase(MappingConstants.USER_TYPE) || propertyType
					.equalsIgnoreCase(MappingConstants.JIMS2USER_TYPE))
					&& (columnTypeInt != Types.VARCHAR && columnTypeInt != Types.CHAR && columnTypeInt != Types.LONGVARCHAR))
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.NUMERIC_TYPE) && columnTypeInt != Types.INTEGER)
			{
				hasError = true;
			}
			else if ((propertyType.equalsIgnoreCase(MappingConstants.DATE_TYPE) || propertyType
					.equalsIgnoreCase(MappingConstants.DATE_TYPE_LONG))
					&& (columnTypeInt != Types.DATE && columnTypeInt != Types.TIMESTAMP))
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.TIMESTAMP_TYPE)
					&& (columnTypeInt != Types.TIMESTAMP))
			{
				hasError = true;
			}
			else if ((propertyType.equalsIgnoreCase(MappingConstants.INT_TYPE) || (propertyType
					.equalsIgnoreCase(MappingConstants.INTEGER_TYPE)))
					&& columnTypeInt != Types.INTEGER)
			{
				if (columnTypeInt == Types.SMALLINT)
				{
					hasWarning = true;
				}
				else
				{
					hasError = true;
				}
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.NUMERIC_TYPE) && columnTypeInt != Types.INTEGER)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.BOOLEAN_TYPE) && columnTypeInt != Types.SMALLINT)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.LONG_TYPE) && columnTypeInt != Types.INTEGER)
			{
				// TODO confirm this type
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.SHORT_TYPE) && columnTypeInt != Types.SMALLINT)
			{
				hasError = true;
			}
			else if ((propertyType.equalsIgnoreCase(MappingConstants.DOUBLE_TYPE) || propertyType
					.equalsIgnoreCase(MappingConstants.DOUBLE_TYPE_LONG))
					&& (columnTypeInt != Types.REAL && columnTypeInt != Types.DECIMAL))
			{
				// TODO Confirm this type
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.FLOAT_TYPE) && columnTypeInt != Types.FLOAT)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.BYTE_TYPE) && columnTypeInt != Types.SMALLINT)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.EVENT_TYPE) && columnTypeInt != Types.VARCHAR)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.BLOB_TYPE) && columnTypeInt != Types.BLOB)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.BYTE_ARRAY_TYPE) && columnTypeInt != Types.BLOB)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.CLOB_TYPE) && columnTypeInt != Types.CLOB)
			{
				hasError = true;
			}

			if (hasError == true)
			{
				String columnType = (String) metaData.get(columnName);
				AuditError error = new AuditError(MappingConstants.INVALID_TYPE, AuditError.ERROR);
				error.setMessage("invalid PARM type mapping: " + fProps + " columnType: " + columnType);
				this.result.addError(error);
			}
			else if (hasWarning == true)
			{
				String columnType = (String) metaData.get(columnName);
				AuditError error = new AuditError(MappingConstants.INVALID_TYPE, AuditError.WARNING);
				error.setMessage("potential PARM type mapping mismatch: " + fProps + " columnType: " + columnType);
				this.result.addError(error);
			}
		}
	}

	/**
	 * @param unitName
	 * @param connectionName
	 */
	private void validateConnection(String unitName, String connectionName)
	{
		ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties(connectionName);
		if (cProps == null)
		{
			this.connectionValid = false;
			AuditError error = new AuditError(MappingConstants.INVALID_CONNECTION, AuditError.ERROR);
			error.setMessage("invalid connection name: " + connectionName);
			this.result.addError(error);
		}
		else
		{
			connectionValid = true;
		}
	}

	/**
	 * @return
	 */
	protected Class validateEntity(String aUnitName)
	{
		Class clazz = null;
		try
		{
			clazz = Class.forName(aUnitName);
			validEntity = true;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			validEntity = false;
		}
		return clazz;
	}

	private void validateJDBCColumn(FieldMappingProperties fProps, Map metaData, Map metaDataTypes)
	{
		String columnName = fProps.getDataItemName();
		if (metaData.containsKey(columnName) == false)
		{
			AuditError error = new AuditError(MappingConstants.MISSING_COLUMN, AuditError.ERROR);
			error.setMessage("column not in database: " + fProps);
			this.result.addError(error);
		}
		else
		{
			boolean hasError = false;
			boolean hasWarning = false;

			String propertyType = fProps.getPropertyType();
			int columnTypeInt = ((Integer) metaDataTypes.get(columnName)).intValue();

			if ((propertyType.equalsIgnoreCase(MappingConstants.STRING_TYPE)
					|| propertyType.equalsIgnoreCase(MappingConstants.STRING_TYPE_LONG)
					|| propertyType.equalsIgnoreCase(MappingConstants.USER_TYPE) || propertyType
					.equalsIgnoreCase(MappingConstants.JIMS2USER_TYPE))
					&& (columnTypeInt != Types.VARCHAR && columnTypeInt != Types.CHAR && columnTypeInt != Types.LONGVARCHAR))
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.NUMERIC_TYPE) && columnTypeInt != Types.INTEGER)
			{
				hasError = true;
			}
			else if ((propertyType.equalsIgnoreCase(MappingConstants.DATE_TYPE) || propertyType
					.equalsIgnoreCase(MappingConstants.DATE_TYPE_LONG))
					&& (columnTypeInt != Types.DATE && columnTypeInt != Types.TIMESTAMP))
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.TIMESTAMP_TYPE)
					&& (columnTypeInt != Types.TIMESTAMP))
			{
				hasError = true;
			}
			else if ((propertyType.equalsIgnoreCase(MappingConstants.INT_TYPE) || (propertyType
					.equalsIgnoreCase(MappingConstants.INTEGER_TYPE)))
					&& columnTypeInt != Types.INTEGER && columnTypeInt != Types.DECIMAL)
			{
				if (columnTypeInt == Types.SMALLINT)
				{
					hasWarning = true;
				}
				else
				{
					hasError = true;
				}
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.NUMERIC_TYPE) && columnTypeInt != Types.INTEGER)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.BOOLEAN_TYPE) && columnTypeInt != Types.SMALLINT)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.LONG_TYPE)
					&& (columnTypeInt != Types.INTEGER && columnTypeInt != Types.DECIMAL))
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.SHORT_TYPE) && columnTypeInt != Types.SMALLINT)
			{
				hasError = true;
			}
			else if ((propertyType.equalsIgnoreCase(MappingConstants.DOUBLE_TYPE) || propertyType
					.equalsIgnoreCase(MappingConstants.DOUBLE_TYPE_LONG))
					&& (columnTypeInt != Types.REAL && columnTypeInt != Types.DECIMAL))
			{
				// TODO Confirm this type
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.FLOAT_TYPE) && columnTypeInt != Types.FLOAT)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.BYTE_TYPE) && columnTypeInt != Types.SMALLINT)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.EVENT_TYPE) && columnTypeInt != Types.VARCHAR)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.BLOB_TYPE) && columnTypeInt != Types.BLOB)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.BYTE_ARRAY_TYPE) && columnTypeInt != Types.BLOB)
			{
				hasError = true;
			}
			else if (propertyType.equalsIgnoreCase(MappingConstants.CLOB_TYPE) && columnTypeInt != Types.CLOB)
			{
				hasError = true;
			}

			if (hasError == true)
			{
				String columnType = (String) metaData.get(columnName);
				AuditError error = new AuditError(MappingConstants.INVALID_TYPE, AuditError.ERROR);
				error.setMessage("invalid type mapping: " + fProps + " columnType: " + columnType);
				this.result.addError(error);
			}
			else if (hasWarning == true)
			{
				String columnType = (String) metaData.get(columnName);
				AuditError error = new AuditError(MappingConstants.INVALID_TYPE, AuditError.WARNING);
				error.setMessage("potential type mapping mismatch: " + fProps + " columnType: " + columnType);
				this.result.addError(error);
			}
		}

	}

	private void validateJDBCFieldMappings(Class clazz)
	{
		String source = props.getSource().trim();

		Map metaData = visitor.getMetaData(source);
		Map metaDataTypes = visitor.getMetaDataTypes(source);

		Iterator f = props.getFieldsIterator();

		while (f.hasNext())
		{
			FieldMappingProperties fProps = (FieldMappingProperties) f.next();
			this.validateJDBCColumn(fProps, metaData, metaDataTypes);
			this.validateProperty(clazz, fProps);
		}
	}

	/**
	 * @param propertyType
	 * @param type
	 * @return
	 */
	private void validateM204Column(FieldMappingProperties fProps, Map metaData)
	{
		String columnName = fProps.getDataItemName();

		if ("mojo.km.utilities.FileIOMapping".equals(fProps.getCallback().getMappingClassName()) == false)
		{
			if (columnName.indexOf(DERIVED_COLUMN) > -1)
			{
				String propertyName = fProps.getPropertyName();
				AuditError error = new AuditError(MappingConstants.MISSING_COLUMN, AuditError.INFO);
				error.setMessage("derived field: property=" + propertyName + " dataItem=" + columnName);
				this.result.addError(error);
			}
			else if (metaData.containsKey(fProps.getPosition()) == true)
			{
				String metaColumnName = (String) metaData.get(fProps.getPosition());
				if (columnName.equals(metaColumnName) == false)
				{
					String propertyName = fProps.getPropertyName();
					AuditError error = new AuditError(MappingConstants.MISSING_COLUMN, AuditError.WARNING);
					StringBuffer buffer = new StringBuffer();
					buffer.append(MappingConstants.MISSING_COLUMN);
					buffer.append("(M204 dataItemName=");
					buffer.append(metaColumnName);
					buffer.append("): property=" + propertyName);
					buffer.append(" dataItem=" + columnName);
					buffer.append(" position=" + fProps.getPosition());
					error.setMessage(buffer.toString());
					this.result.addError(error);
				}
			}
			else
			{
				int positionInt = 9999;
				try
				{
					positionInt = Integer.parseInt(fProps.getPosition());
				}
				catch (Exception e)
				{
					// do nothing
				}
				if (positionInt < 500)
				{
					String propertyName = fProps.getPropertyName();
					AuditError error = new AuditError(MappingConstants.MISSING_COLUMN, AuditError.ERROR);
					error.setMessage("column not in database: property=" + propertyName + " dataItem=" + columnName);
					this.result.addError(error);
				}
			}
		}

	}

	private void validateM204FieldMappings(Class clazz)
	{
		String key = props.getFileName().trim() + props.getSource().trim();

		Map metaData = visitor.getMetaData(key);

		Iterator f = props.getFieldsIterator();

		while (f.hasNext())
		{
			FieldMappingProperties fProps = (FieldMappingProperties) f.next();
			this.validateM204Column(fProps, metaData);
			this.validateProperty(clazz, fProps);
		}
	}

	private Class validateMappingClass(String aClassName)
	{
		Class clazz = null;
		this.cacheMapping = false;
		String mappingClassName = props.getMappingClassName();

		if (mappingClassName.length() == 0 || mappingClassName.equalsIgnoreCase(MappingConstants.NONE))
		{
			AuditError error = new AuditError(MappingConstants.INVALID_MAPPING_CLASS, AuditError.ERROR);
			error.setMessage(MappingConstants.INVALID_MAPPING_CLASS + ": " + mappingClassName);
			error.setName(aClassName);
			this.result.addError(error);
			this.cacheMapping = false;
			this.validMapping = false;
		}
		else if (mojo.km.context.multidatasource.cache.Mapping.class.getName().equals(mappingClassName))
		{
			this.cacheMapping = true;
			this.validMapping = false;
		}
		else
		{
			try
			{
				clazz = Class.forName(mappingClassName);
				this.validMapping = true;
			}
			catch (Exception e)
			{
				AuditError error = new AuditError(MappingConstants.INVALID_MAPPING_CLASS, AuditError.ERROR);
				error.setMessage(MappingConstants.INVALID_MAPPING_CLASS + ": " + mappingClassName);
				error.setName(aClassName);
				this.result.addError(error);
				this.cacheMapping = false;
				this.validMapping = false;
			}
		}
		return clazz;
	}

	/**
	 * @param mappingMethodName
	 */
	private void validateMappingMethod()
	{
		String mappingClassName = props.getMappingClassName();
		String mappingMethodName = props.getMappingMethodName();

		if (mappingMethodName.length() == 0 || mappingMethodName.equalsIgnoreCase(MappingConstants.NONE))
		{
			AuditError error = new AuditError(MappingConstants.INVALID_MAPPING_METHOD, AuditError.ERROR);
			error.setMessage(MappingConstants.INVALID_MAPPING_METHOD + ": " + mappingMethodName);
			this.result.addError(error);
		}
		else
		{
			try
			{
				Class mappingClass = Class.forName(mappingClassName);

				Class[] parmTypes = new Class[1];
				parmTypes[0] = mojo.km.persistence.PersistentObject.class;

				Method mappingMethod = mappingClass.getMethod(mappingMethodName, parmTypes);
			}
			catch (ClassNotFoundException e)
			{
				// Ignore because this is handled by validateMappingClass
			}
			catch (Exception e)
			{
				AuditError error = new AuditError(MappingConstants.INVALID_MAPPING_METHOD, AuditError.ERROR);
				error.setMessage(MappingConstants.INVALID_MAPPING_METHOD + ": " + mappingMethodName);
				this.result.addError(error);
			}
		}
	}

	protected void validateProperty(Class clazz, FieldMappingProperties fProps)
	{
		Method accessor = mojo.km.utilities.Reflection.getAccessorMethod(clazz, fProps.getPropertyName());
		if (accessor == null)
		{
			AuditError error = new AuditError(MappingConstants.INVALID_PROPERTY, AuditError.ERROR);
			error.setMessage(clazz.getName() + "." + fProps.getPropertyName() + " field accessor does not exist");
			this.result.addError(error);
		}
	}

	/**
	 * @param source
	 */
	private void validateSource(String aName)
	{
		String source;
		if (props.getSource() == null)
		{
			source = MappingConstants.NONE_UPPER;
		}
		else
		{
			source = props.getSource().trim();
		}

		if (source.length() == 0 || source.equalsIgnoreCase(MappingConstants.NONE))
		{
			AuditError error = new AuditError(MappingConstants.INVALID_SOURCE, AuditError.ERROR);
			error.setMessage(MappingConstants.INVALID_SOURCE + ": " + source);
			this.result.addError(error);
			this.sourceValid = false;
		}
		else
		{
			this.sourceValid = true;
			if (MappingConstants.JDBC.equalsIgnoreCase(props.getConnectionName()))
			{
				// cache metadata
				this.getJDBCTableMetaData();
			}
			else if (MappingConstants.M204.equalsIgnoreCase(props.getConnectionName()))
			{
				// cache metadata
				this.getM204MetaData();
			}
		}
	}

	/**
	 * 
	 */
	private void validateWhere()
	{
		StringBuffer whereBuffer = new StringBuffer(props.getWhereClause().trim().toUpperCase());

		if (whereBuffer.length() > 0 && whereBuffer.equals(MappingConstants.NONE_UPPER) == false)
		{
			List varchars = new ArrayList();

			int whereIndex = 0;

			while (whereIndex > -1)
			{
				whereIndex = whereBuffer.indexOf(MappingConstants.VARCHAR, whereIndex);
				if (whereIndex > -1)
				{
					varchars.add(new Integer(whereIndex));
					whereIndex++;
				}
			}

			Iterator v = varchars.iterator();

			int varcharLen = MappingConstants.VARCHAR.length();

			int offsetDif = VARCHAR_BOLD.length() - MappingConstants.VARCHAR.length();

			int count = 0;

			while (v.hasNext())
			{
				Integer vIndex = (Integer) v.next();
				int intVIndex = vIndex.intValue() + (offsetDif * count);
				int endVIndex = intVIndex + varcharLen;
				whereBuffer = whereBuffer.replace(intVIndex, endVIndex, VARCHAR_BOLD);
				count++;
			}

			if (whereBuffer.indexOf(MappingConstants.VARCHAR) > -1)
			{
				AuditError error = new AuditError(MappingConstants.VARCHAR_IN_WHERE, AuditError.WARNING);
				error.setMessage("VARCHAR function(s) in WHERE clause: " + whereBuffer.toString());
				this.result.addError(error);
			}
		}
	}
}
