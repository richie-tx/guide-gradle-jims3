/*
 * Created on Jun 13, 2006
 *
 */
package mojo.tools.code.audit.mapping;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.HashMap;

import mojo.km.config.ConnectionProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;

import mojo.km.config.ParmMappingProperties;
import mojo.km.naming.MappingConstants;
import mojo.tools.code.audit.AuditError;
import mojo.tools.code.audit.AuditResult;

/**
 * @author Jim Fisher
 * 
 */
public class EventQueryPropertiesJDBCValidator extends AbstractEventQueryPropertiesValidator
{
	private static final String VARCHAR_BOLD = BEGIN_BOLD + MappingConstants.VARCHAR + END_BOLD;

	private static final String UPPER = "UPPER";

	private static final String UPPER_BOLD = BEGIN_BOLD + "UPPER" + END_BOLD;

	private static final String UPPER_IN_WHERE = "UPPER function in where clause";

	private static final String METADATA_BY_COLUMN = "byColumn";

	private static final String METADATA_BY_INDEX = "byIndex";

	public EventQueryPropertiesJDBCValidator(EventQueryProperties aProps)
	{
		super(aProps);
	}

	public void accept(IMappingValidatorVisitor aVisitor)
	{
		this.visitor = (PersistenceMappingValidator) aVisitor;

		this.validate();

		aVisitor.visit(this);
	}

	private void setMetaData()
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

		String connectionName = this.props.getConnectionName();

		if (metaData == null)
		{
			metaData = new HashMap();
			metaDataTypes = new HashMap();

			ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties(connectionName);

			Connection jdbcConnection = null;

			ResultSet rs = null;

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
					super.setValidSource(false);
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

					// cache meta data per source
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

				if (jdbcConnection != null)
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

	/**
	 * @return Returns the props.
	 */
	public EventQueryProperties getProps()
	{
		return props;
	}

	public AuditResult getResult()
	{
		return this.result;
	}

	public void validate()
	{
		String unitName = visitor.getCompilationUnit().getMainType().getQualifiedName();
		System.out.println("VALIDATING: " + unitName + "::" + props.getParent());
		String source;
		if (props.getSource() == null)
		{
			source = MappingConstants.NONE_UPPER;
		}
		else
		{
			source = props.getSource().trim();
		}

		StringBuffer msgBuffer = new StringBuffer();

		msgBuffer.append(source);

		msgBuffer.append(" (");
		msgBuffer.append(props.getConnectionName());
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

		boolean noValidate = eProps.isBufferMapping() || eProps.isReportMapping() || props.isFileIOMapping()
				|| isCacheMapping() || (isValidMapping() == false) || (isValidEntity() == false);

		if (noValidate == false)
		{
			// RULE: validate method
			this.validateMappingMethod();

			// RULE: validate source
			this.validateSource(unitName);

			// RULE: validate event name
			this.validateEventMapping(unitName);

			// RULE: validate connection name
			this.validateConnection(unitName);

			if (isValidSource() && isValidConnection())
			{
				// RULE: validate FieldMappings
				this.validateFieldMappings(clazz);

				this.validateParmMappings(clazz);
			}

			// RULE(warning): validate where clause
			this.validateVarcharWhere();
			this.validateUpperWhere();
		}
	}

	/**
	 * @param clazz
	 */
	private void validateParmMappings(Class aClazz)
	{
		// TODO create audit for OID and event queries, (Attribute, AllQuery -->
		// info for parms)

		Iterator p = props.getParmsIterator();

		String source = props.getSource().trim();
		Map metaData = visitor.getMetaData(source);
		Map metaDataTypes = visitor.getMetaDataTypes(source);

		Class clazz = null;

		switch (props.getQueryType())
		{
		case MappingConstants.EVENT_QUERY:
			try
			{
				clazz = Class.forName(props.getEventName());
			}
			catch (ClassNotFoundException e)
			{
				// this error is already getting recorded in
				// AbstractEventQueryPropertiesValidator.validateEventMapping
			}
			break;
		default:
			clazz = aClazz;
			break;
		}

		while (p.hasNext())
		{
			ParmMappingProperties pProps = (ParmMappingProperties) p.next();
			this.validateParmProperty(clazz, pProps);

			// RULE(error) validate database mapping
			this.validateParmColumn(pProps, metaData, metaDataTypes);
		}
	}

	private void validateParmColumn(ParmMappingProperties fProps, Map metaData, Map metaDataTypes)
	{
		String columnName = fProps.getDataItemName();

		boolean hasError = false;
		boolean hasWarning = false;

		String propertyType = fProps.getPropertyType();

		if (metaDataTypes == null)
		{
			System.err.println("metaDataTypes has not been set: " + fProps.getCallback().getSource());
		}
		else if (metaDataTypes.containsKey(columnName))
		{
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
			else if (fProps.getPropertyTypeInt() == MappingConstants.INVALID_DATATYPE)
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
		else
		{
			System.err.println("no metadata available for: " + columnName);
		}
	}

	public void validateSource(String unitName)
	{
		super.validateSource(unitName);

		if (this.isValidSource())
		{
			this.setMetaData();
		}
	}

	/**
	 * @param propertyType
	 * @param type
	 * @return
	 */
	private void validateColumn(FieldMappingProperties fProps, Map metaData, Map metaDataTypes)
	{
		String columnName = fProps.getDataItemName();
		String propertyType = fProps.getPropertyType();

		int columnTypeInt = -9999;

		String columnType = (String) metaData.get(columnName);

		if (columnType != null)
		{
			columnTypeInt = ((Integer) metaDataTypes.get(columnName)).intValue();
		}
		else
		{
			columnName = columnName.toUpperCase();

			if (columnName.indexOf(MappingConstants.COUNT_FUNC) != -1)
			{
				columnType = "INTEGER";
				columnTypeInt = Types.INTEGER;
			}
			if (columnName.indexOf(MappingConstants.MIN_FUNC) != -1
					|| columnName.indexOf(MappingConstants.MAX_FUNC) != -1
					|| columnName.indexOf(MappingConstants.SUM_FUNC) != -1
					|| columnName.indexOf(MappingConstants.AVG_FUNC) != -1)
			{
				try
				{
					columnName = this.extractColumn(columnName);
				}
				catch (Exception e)
				{
					// do nothing, the exeption will get reported further down
					// when the column is not found
				}

				if (metaData.containsKey(columnName))
				{
					columnType = (String) metaData.get(columnName);
					columnTypeInt = ((Integer) metaDataTypes.get(columnName)).intValue();
				}
			}
		}

		boolean hasError = false;
		boolean hasWarning = false;

		if (props.isFileIOMapping() == false)
		{
			if (metaData.containsKey(columnName) == false)
			{
				AuditError error = new AuditError(MappingConstants.MISSING_COLUMN, AuditError.ERROR);
				error.setMessage("column not in database: " + fProps);
				this.result.addError(error);
			}
			else
			{
				if ((propertyType.equalsIgnoreCase(MappingConstants.DATE_TYPE) || propertyType
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
				else if (propertyType.equalsIgnoreCase(MappingConstants.BOOLEAN_TYPE)
						&& columnTypeInt != Types.SMALLINT)
				{
					hasError = true;
				}
				else if (propertyType.equalsIgnoreCase(MappingConstants.LONG_TYPE) && columnTypeInt != Types.INTEGER
						&& columnTypeInt != Types.DECIMAL)
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
				else if (fProps.getPropertyTypeInt() == MappingConstants.INVALID_DATATYPE)
				{
					hasError = true;
				}

				if (hasError == true)
				{
					AuditError error = new AuditError(MappingConstants.INVALID_TYPE, AuditError.ERROR);
					error.setMessage("invalid type mapping: " + fProps + " columnType: " + columnType);
					this.result.addError(error);
				}
				else if (hasWarning == true)
				{
					AuditError error = new AuditError(MappingConstants.INVALID_TYPE, AuditError.WARNING);
					error.setMessage("potential type mapping mismatch: " + fProps + " columnType: " + columnType);
					this.result.addError(error);
				}

			}
		}
	}

	/**
	 * @param columnName
	 * @return
	 */
	private String extractColumn(String columnName)
	{
		int beginIndex = columnName.indexOf("(") + 1;
		int endIndex = columnName.lastIndexOf(")", columnName.length());

		return columnName.substring(beginIndex, endIndex);
	}

	private void validateFieldMappings(Class clazz)
	{
		Set columns = new HashSet();
		Set fields = new HashSet();
		List fieldList = new ArrayList();

		FieldMappingProperties oidProps = props.getFieldMap(MappingConstants.OID);
		if (oidProps == null)
		{
			AuditError error = new AuditError(MappingConstants.MISSING_FIELDS, AuditError.ERROR);
			error.setMessage("missing OID field mapping property");
			this.result.addError(error);
		}

		Iterator f = props.getFieldsIterator();

		if (f.hasNext() == false)
		{
			AuditError error = new AuditError(MappingConstants.MISSING_FIELDS, AuditError.ERROR);
			error.setMessage("missing field mapping properties: " + clazz.getName());
			this.result.addError(error);
		}
		else
		{
			String source = props.getSource().trim();
			Map metaData = visitor.getMetaData(source);
			Map metaDataTypes = visitor.getMetaDataTypes(source);
			while (f.hasNext())
			{
				FieldMappingProperties fProps = (FieldMappingProperties) f.next();

				boolean invalidIndex = false;

				Integer index = null;

				try
				{
					index = new Integer(fProps.getParmIndex());

					if (index.intValue() > 0)
					{
						invalidIndex = false;

						this.validateProperty(clazz, fProps);

						// RULE(info): check for dup fields
						if (fields.add(index) == false)
						{
							String propertyName = fProps.getPropertyName();
							AuditError error = new AuditError(MappingConstants.DUP_FIELD_INDEX, AuditError.WARNING);
							error.setMessage("duplicate field index (field property=" + propertyName + "): " + index);
							this.result.addError(error);
						}
					}
					else
					{
						invalidIndex = true;
					}
				}
				catch (NumberFormatException e)
				{
					invalidIndex = true;
				}

				if (invalidIndex == true)
				{
					String propertyName = fProps.getPropertyName();
					AuditError error = new AuditError(MappingConstants.INVALID_FIELD_INDEX, AuditError.ERROR);
					error.setMessage("invalid field index (field property=" + propertyName + "): "
							+ fProps.getParmIndex());
					this.result.addError(error);
				}
				else
				{
					fieldList.add(index);
				}

				String columnName = fProps.getDataItemName();

				// RULE(info): check for dup columns, columnName is checked in
				// another validation
				if (MappingConstants.NONE.equalsIgnoreCase(columnName) == false && columns.contains(columnName))
				{
					String propertyName = fProps.getPropertyName();
					AuditError error = new AuditError(MappingConstants.DUP_COLUMN, AuditError.INFO);
					error.setMessage("duplicate column (field property=" + propertyName + "): " + columnName);
					this.result.addError(error);
				}
				else
				{
					columns.add(columnName);
				}

				// RULE(error) validate database mapping
				this.validateColumn(fProps, metaData, metaDataTypes);
			}

			Collections.sort(fieldList);

			int len = fieldList.size();

			if (len > 0)
			{
				Integer prev = (Integer) fieldList.get(0);

				for (int i = 1; i < len; i++)
				{
					if (prev.intValue() > 0)
					{
						int value = ((Integer) fieldList.get(i)).intValue();

						if (value != prev.intValue() + 1)
						{
							AuditError error = new AuditError(MappingConstants.SKIPED_FIELD_INDEX, AuditError.ERROR);
							StringBuffer buffer = new StringBuffer();
							buffer.append(MappingConstants.SKIPED_FIELD_INDEX);
							buffer.append("(range ");
							buffer.append(prev);
							buffer.append(" to ");
							buffer.append(value);
							buffer.append(")");
							error.setMessage(buffer.toString());
							this.result.addError(error);
						}
					}
					prev = (Integer) fieldList.get(i);
				}
			}
		}
	}

	/**
	 * @param fProps
	 * @return
	 */
	private boolean checkSkip(FieldMappingProperties fProps)
	{
		boolean skip = false;
		if ("pd.codetable.Code".equals(props.getParent().getEntity()) == true
				&& "mojo.km.persistence.AttributeEvent".equals(props.getEventName()) == true
				&& "codeTableName".equals(fProps.getName()) == true)
		{
			skip = true;
		}
		return skip;
	}

	private void validateUpperWhere()
	{
		StringBuffer whereBuffer;
		if (props.getWhereClause() == null)
		{
			whereBuffer = new StringBuffer(MappingConstants.NONE_UPPER);
		}
		else
		{
			whereBuffer = new StringBuffer(props.getWhereClause().trim().toUpperCase());
		}

		if (whereBuffer.length() > 0 && whereBuffer.equals(MappingConstants.NONE_UPPER) == false)
		{
			List uppers = new ArrayList();

			int whereIndex = 0;

			while (whereIndex > -1)
			{
				whereIndex = whereBuffer.indexOf(UPPER, whereIndex);
				if (whereIndex > -1)
				{
					uppers.add(new Integer(whereIndex));
					whereIndex++;
				}
			}

			Iterator v = uppers.iterator();

			int varcharLen = UPPER.length();

			int offsetDif = UPPER_BOLD.length() - UPPER.length();

			int count = 0;

			while (v.hasNext())
			{
				Integer vIndex = (Integer) v.next();
				int intVIndex = vIndex.intValue() + (offsetDif * count);
				int endVIndex = intVIndex + varcharLen;
				whereBuffer = whereBuffer.replace(intVIndex, endVIndex, UPPER_BOLD);
				count++;
			}

			if (whereBuffer.indexOf(UPPER) > -1)
			{
				AuditError error = new AuditError(UPPER_IN_WHERE, AuditError.WARNING);
				error.setMessage("UPPER function(s) in WHERE clause: " + whereBuffer.toString());
				this.result.addError(error);
			}
		}
	}

	private void validateVarcharWhere()
	{
		StringBuffer whereBuffer;
		if (props.getWhereClause() == null)
		{
			whereBuffer = new StringBuffer(MappingConstants.NONE_UPPER);
		}
		else
		{
			whereBuffer = new StringBuffer(props.getWhereClause().trim().toUpperCase());
		}

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
