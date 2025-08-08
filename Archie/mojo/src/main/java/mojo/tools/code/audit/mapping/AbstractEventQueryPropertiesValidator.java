/*
 * Created on Jun 13, 2006
 *
 */
package mojo.tools.code.audit.mapping;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import mojo.km.config.ConnectionProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ParmMappingProperties;
import mojo.km.messaging.IEvent;
import mojo.km.naming.MappingConstants;
import mojo.tools.code.audit.AuditError;
import mojo.tools.code.audit.AuditResult;

/**
 * @author Jim Fisher
 * 
 */
public abstract class AbstractEventQueryPropertiesValidator implements IMappingValidator
{
	protected static final String BEGIN_BOLD = "#{begin_bold}";

	protected static final String END_BOLD = "#{end_bold}";

	private boolean cacheMapping = false;

	protected EventQueryProperties props;

	protected AuditResult result;

	private boolean validConnection = true;

	@SuppressWarnings("unused")
	private boolean validEvent = true;

	private boolean validMapping = true;

	private boolean validSource = true;

	protected PersistenceMappingValidator visitor;

	public AbstractEventQueryPropertiesValidator(EventQueryProperties aProps)
	{
		this.props = aProps;
		this.result = new AuditResult();
	}

	abstract public void accept(IMappingValidatorVisitor aVisitor);

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

	protected boolean validEntity;

	/**
	 * @return
	 */
	protected boolean isValidEntity()
	{
		return validEntity;
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

	/**
	 * @return Returns the validCacheMapping.
	 */
	public boolean isCacheMapping()
	{
		return cacheMapping;
	}

	/**
	 * @return Returns the validConnection.
	 */
	public boolean isValidConnection()
	{
		return validConnection;
	}

	/**
	 * @return Returns the validSource.
	 */
	public boolean isValidSource()
	{
		return validSource;
	}

	/**
	 * @param validSource
	 *            The validSource to set.
	 */
	public void setValidSource(boolean validSource)
	{
		this.validSource = validSource;
	}

	abstract public void validate();

	/**
	 * @param unitName
	 * @param connectionName
	 */
	protected void validateConnection(String unitName)
	{
		String connectionName = props.getConnectionName();
		ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties(connectionName);
		if (cProps == null)
		{
			this.validConnection = false;
			AuditError error = new AuditError(MappingConstants.INVALID_CONNECTION, AuditError.ERROR);
			error.setMessage(MappingConstants.INVALID_CONNECTION + ": " + connectionName);
			this.result.addError(error);
		}
		else
		{
			validConnection = true;
		}
	}

	/**
	 * @param eventName
	 */
	protected void validateEventMapping(String aName)
	{
		String eventName = props.getEventName();
		if (eventName.length() == 0 || eventName.equalsIgnoreCase(MappingConstants.NONE))
		{
			this.validConnection = false;
			AuditError error = new AuditError(MappingConstants.INVALID_EVENT_NAME, AuditError.ERROR);
			error.setMessage(MappingConstants.INVALID_EVENT_NAME + ": " + eventName);
			this.result.addError(error);
			this.validEvent = false;
		}
		else
		{
			try
			{
				Class.forName(eventName);
				this.validEvent = true;
			}
			catch (ClassNotFoundException e)
			{
				this.validConnection = false;
				AuditError error = new AuditError(MappingConstants.INVALID_EVENT_NAME, AuditError.ERROR);
				error.setMessage(MappingConstants.INVALID_EVENT_NAME + ": " + eventName);
				this.result.addError(error);
				this.validEvent = false;
			}
		}
	}

	/**
	 * @param mappingClassName
	 */
	protected Class validateMappingClass(String aClassName)
	{
		cacheMapping = false;

		Class clazz = null;

		EntityMappingProperties eProps = props.getParent();

		if (eProps.isBufferMapping() == false && eProps.isReportMapping() == false)
		{
			String mappingClassName = props.getMappingClassName();

			if (mappingClassName.length() == 0 || mappingClassName.equalsIgnoreCase(MappingConstants.NONE))
			{
				AuditError error = new AuditError(MappingConstants.INVALID_MAPPING_CLASS, AuditError.ERROR);
				error.setMessage(MappingConstants.INVALID_MAPPING_CLASS + ": " + mappingClassName);
				error.setName(aClassName);
				this.result.addError(error);
				cacheMapping = false;
				validMapping = false;
			}
			else if (mojo.km.context.multidatasource.cache.Mapping.class.getName().equals(mappingClassName))
			{
				cacheMapping = true;
				validMapping = false;
			}
			else if (props.isFileIOMapping() == true)
			{
				AuditError error = new AuditError(MappingConstants.FILE_IO_MAPPING, AuditError.INFO);
				error.setMessage(MappingConstants.FILE_IO_MAPPING);
				this.result.addError(error);
				validMapping = true;
				cacheMapping = false;
			}
			else
			{
				cacheMapping = false;
				try
				{
					Class.forName(mappingClassName);
					validMapping = true;
				}
				catch (Exception e)
				{
					AuditError error = new AuditError(MappingConstants.INVALID_MAPPING_CLASS, AuditError.ERROR);
					error.setMessage(MappingConstants.INVALID_MAPPING_CLASS + ": " + mappingClassName);
					error.setName(aClassName);
					this.result.addError(error);
					validMapping = false;
				}
			}
		}
		return clazz;
	}

	private Method findMutator(List mutators, String propType)
	{
		Method m = null;
		int len = mutators.size();
		for (int i = 0; i < len; i++)
		{
			Method method = (Method) mutators.get(i);

			Class[] parmTypes = method.getParameterTypes();
			String parmType = parmTypes[0].getName();
			if (parmTypes.length == 1)
			{
				if (parmType.equals(propType))
				{
					m = method;
				}
			}
		}

		return m;
	}

	/**
	 * @param mappingMethodName
	 */
	protected void validateMappingMethod()
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

				Class[] parmTypes = new Class[3];
				parmTypes[0] = IEvent.class;
				parmTypes[1] = Class.class;
				parmTypes[2] = Map.class;

				mappingClass.getMethod(mappingMethodName, parmTypes);
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

	protected void validateParmProperty(Class clazz, ParmMappingProperties fProps)
	{
		Method accessor = mojo.km.utilities.Reflection.getAccessorMethod(clazz, fProps.getPropertyName());
		if (accessor == null)
		{
			AuditError error = new AuditError(MappingConstants.INVALID_PROPERTY, AuditError.ERROR);
			error.setMessage("parm event accessor does not exist (" + props.getEventName() + "): " + fProps);
			this.result.addError(error);
		}
	}

	protected void validateProperty(Class clazz, FieldMappingProperties fProps)
	{
		Method method = mojo.km.utilities.Reflection.getAccessorMethod(clazz, fProps.getPropertyName());
		if (method == null)
		{
			AuditError error = new AuditError(MappingConstants.INVALID_PROPERTY, AuditError.ERROR);
			error.setMessage("accessor does not exist: " + fProps);
			this.result.addError(error);
		}

		List mutators = mojo.km.utilities.Reflection.getMutatorMethods(clazz, fProps.getPropertyName());
		if (mutators.size() == 0)
		{
			AuditError error = new AuditError(MappingConstants.INVALID_PROPERTY, AuditError.ERROR);
			error.setMessage("mutator does not exist: " + fProps);
			this.result.addError(error);
		}
		else
		{
			if (mutators.size() > 0)
			{
				method = null;
				switch (fProps.getPropertyTypeInt())
				{
				case MappingConstants.STRING_DATATYPE:
					method = findMutator(mutators, "java.lang.String");
					break;
				case MappingConstants.JIMS2USER_DATATYPE:
					break;
				case MappingConstants.USER_DATATYPE:
					break;
				case MappingConstants.DATE_DATATYPE:
					method = findMutator(mutators, "java.util.Date");
					break;
				case MappingConstants.TIMESTAMP_DATATYPE:
					method = findMutator(mutators, "java.sql.Timestamp");
					if (method == null)
					{
						method = findMutator(mutators, "java.util.Date");
					}
					break;
				case MappingConstants.INT_DATATYPE:
					method = findMutator(mutators, "int");
					if (method == null)
					{
						method = findMutator(mutators, "java.lang.Integer");
					}
					break;
				case MappingConstants.NUMERIC_DATATYPE:
					method = findMutator(mutators, "java.lang.String");
					break;
				case MappingConstants.BOOLEAN_DATATYPE:
					method = findMutator(mutators, "boolean");
					if (method == null)
					{
						method = findMutator(mutators, "java.lang.Boolean");
					}
					break;
				case MappingConstants.LONG_DATATYPE:
					method = findMutator(mutators, "long");
					if (method == null)
					{
						method = findMutator(mutators, "java.lang.Long");
					}
					break;
				case MappingConstants.SHORT_DATATYPE:
					method = findMutator(mutators, "short");
					if (method == null)
					{
						method = findMutator(mutators, "java.lang.Short");
					}
					break;
				case MappingConstants.DOUBLE_DATATYPE:
					method = findMutator(mutators, "double");
					if (method == null)
					{
						method = findMutator(mutators, "java.lang.Double");
					}
					break;
				case MappingConstants.FLOAT_DATATYPE:
					method = findMutator(mutators, "float");
					if (method == null)
					{
						method = findMutator(mutators, "java.lang.Float");
					}
					break;
				case MappingConstants.BYTE_DATATYPE:
					method = findMutator(mutators, "byte");
					if (method == null)
					{
						method = findMutator(mutators, "java.lang.Byte");
					}
					break;
				case MappingConstants.EVENT_DATATYPE:
					method = (Method) mutators.get(0);
					break;
				case MappingConstants.BLOB_DATATYPE:
					method = findMutator(mutators, "[B");
					if (method == null)
					{
						method = findMutator(mutators, "java.lang.Object");
					}
					break;
				case MappingConstants.BYTEARRAY_DATATYPE:
					method = findMutator(mutators, "[B");
					break;
				case MappingConstants.CLOB_DATATYPE:
					method = findMutator(mutators, "java.lang.String");
					if (method == null)
					{
						method = findMutator(mutators, "java.lang.Object");
					}
					if (method == null)
					{
						method = findMutator(mutators, "clob");
					}
					if (method == null)
					{
						method = findMutator(mutators, "[C");
					}
					break;
				}

				if (method == null && fProps.getPropertyTypeInt() != MappingConstants.JIMS2USER_DATATYPE
						&& fProps.getPropertyTypeInt() != MappingConstants.USER_DATATYPE)
				{
					AuditError error = new AuditError(MappingConstants.INVALID_PROPERTY, AuditError.ERROR);
					error.setMessage("mutator exists, but has a type mismatch: " + fProps);
					this.result.addError(error);
				}
			}
		}
	}

	/**
	 * @param source
	 */
	protected void validateSource(String aName)
	{
		if (props.isFileIOMapping() == false)
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

			if (source.length() == 0 || source.equalsIgnoreCase(MappingConstants.NONE_UPPER))
			{
				AuditError error = new AuditError(MappingConstants.INVALID_SOURCE, AuditError.ERROR);
				error.setMessage(MappingConstants.INVALID_SOURCE + ": " + source);
				this.result.addError(error);
				this.validSource = false;
			}
			else
			{
				this.validSource = true;
			}
		}
		else
		{
			this.validSource = true;
		}
	}

	/**
	 * @return Returns the validMapping.
	 */
	public boolean isValidMapping()
	{
		return validMapping;
	}
}
