package mojo.km.config;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;

import mojo.km.naming.MappingConstants;
import mojo.km.utilities.Reflection;

/**
 * Responsible for defining meta data related to map a persistent object's field to its related data
 * store.
 */
public class FieldMappingProperties extends GenericProperties
{
	static class Sorter implements Comparator
	{
		public int compare(Object obj1, Object obj2)
		{
			FieldMappingProperties fProps1 = (FieldMappingProperties) obj1;
			FieldMappingProperties fProps2 = (FieldMappingProperties) obj2;
			int result;
			if (fProps1.getIndex() < fProps2.getIndex())
			{
				result = -1;
			}
			else if (fProps1.getIndex() > fProps2.getIndex())
			{
				result = 1;
			}
			else
			{
				result = 0;
			}
			return result;
		}

	}

	public static final String APPENDFILLER = "appendFiller";

	public static final String ASSOCIATIONTYPE = "associationType";

	public static final String CARDPOSITION = "cardPosition";

	public static final String CLASSIFICATION = "classification";

	public static final String CODETABLENAME = "codeTableName";

	public static final String CONVERTWILDCARD = "convertWilcard";

	public static final String DATAITEMNAME = "dataItemName";

	public static final String FORMAT = "format";

	public static final String FORMATSTRING = "formatString";

	public static final String LENGTH = "length";

	public static final String NAME = "name";

	public static final String PADCHARACTER = "padChar";

	public static final String PARMINDEX = "parmIndex";

	public static final String POSITION = "position";

	public static final String PROPERTYNAME = "propertyName";

	public static final String PROPERTYTYPE = "propertyType";

	public static final String TEXT = "text";

	private Method accessor;

	private String appendFiller;

	private String associationType;

	protected CallbackProperties callback;

	private String cardPosition;

	private String classification;

	private String codeTableName;

	private String convertWildcard;

	private boolean convertWildStarToPercent;

	protected String dataItemName;

	private boolean dataItemNameNone;

	private String format;

	private String formatString;

	protected int index;

	private String length;

	private Method mutator;

	private String name;

	private String padChar;

	private String parmIndex;

	private String position;

	private boolean preserveCase;

	protected String propertyName;

	protected String propertyType;

	private int propertyTypeInt;

	private String text;

	private boolean wildcardAlways;

	private boolean wildcardIfBlank;

	/**
	 * @return Returns the accessor.
	 */
	public Method getAccessor()
	{
		return accessor;
	}

	public String getAppendFiller()
	{
		return this.appendFiller;
	}

	/**
	 * @return java.lang.String
	 * @roseuid 404DA1FD00C9
	 */
	public String getAssociationType()
	{
		return this.associationType;
	}

	/**
	 * @return Returns the callback.
	 */
	public CallbackProperties getCallback()
	{
		return callback;
	}

	public String getCardPosition()
	{
		return this.cardPosition;
	}

	public String getClassification()
	{
		return this.classification;
	}

	/**
	 * @deprecated
	 * @return
	 */
	public String getCodeTableName()
	{
		return this.codeTableName;
	}

	public String getConvertWildcard()
	{
		return this.convertWildcard;
	}

	/**
	 * @return java.lang.String
	 * @roseuid 404DA1FD00BE
	 */
	public String getDataItemName()
	{
		return this.dataItemName;
	}

	public String getFormat()
	{
		return this.format;
	}

	public String getFormatString()
	{
		return this.formatString;
	}

	public int getIndex()
	{
		return this.index;
	}

	public String getLength()
	{
		return this.length;
	}

	/**
	 * @return Returns the mutator.
	 */
	public Method getMutator()
	{
		return mutator;
	}

	public String getName()
	{
		return this.name;
	}

	public String getPadChar()
	{
		return this.padChar;
	}

	/**
	 * @return java.lang.String
	 * @roseuid 404DA1FD00AB
	 */
	public String getParmIndex()
	{
		return this.parmIndex;
	}

	public String getPosition()
	{
		return this.position;
	}

	/**
	 * @return java.lang.String
	 * @roseuid 404DA1FD00A2
	 */
	public String getPropertyName()
	{
		return this.propertyName;
	}

	/**
	 * @return java.lang.String
	 * @roseuid 404DA1FD00AA
	 */
	public String getPropertyType()
	{
		return this.propertyType;
	}

	/**
	 * @return Returns the propertyTypeInt.
	 */
	public int getPropertyTypeInt()
	{
		return this.propertyTypeInt;
	}

	public String getText()
	{
		return this.text;
	}

	/**
	 * @return Returns the convertWildStarToPercent.
	 */
	public boolean isConvertWildStarToPercent()
	{
		return convertWildStarToPercent;
	}

	public boolean isDataItemNameNone()
	{
		return this.dataItemNameNone;
	}

	/**
	 * @return Returns the preserveCase.
	 */
	public boolean isPreserveCase()
	{
		return preserveCase;
	}

	/**
	 * @return
	 */
	public boolean isValid()
	{
		boolean valid = true;

		if (MappingConstants.JDBC.startsWith(this.callback.getConnectionName()))
		{
			String parmIndex = this.getParmIndex();
			if (parmIndex == null || "0".equals(parmIndex) || "".equals(parmIndex))
			{
				valid = false;
			}
		}
		else
		{
			String posIndex = this.getPosition();
			if (posIndex == null || "0".equals(posIndex) || "".equals(posIndex))
			{
				valid = false;
			}
		}

		return valid;
	}

	/**
	 * @return Returns the wildcardAlways.
	 */
	public boolean isWildcardAlways()
	{
		return wildcardAlways;
	}

	/**
	 * @return Returns the wildcardIfBlank.
	 */
	public boolean isWildcardIfBlank()
	{
		return wildcardIfBlank;
	}

	/**
	 * 
	 */
	void optimizeDataItem()
	{
		if (dataItemName == null)
		{
			this.dataItemName = MappingConstants.NONE;
		}
		else
		{
			this.dataItemName = this.dataItemName.trim();
		}
		this.dataItemNameNone = (MappingConstants.NONE.equalsIgnoreCase(this.dataItemName));
	}

	void optimizeDataType()
	{
		if (this.propertyType.equalsIgnoreCase(MappingConstants.STRING_TYPE)
				|| this.propertyType.equalsIgnoreCase(MappingConstants.STRING_TYPE_LONG))
		{
			this.setPropertyTypeInt(MappingConstants.STRING_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.JIMS2USER_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.JIMS2USER_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.USER_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.USER_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.DATE_TYPE)
				|| propertyType.equalsIgnoreCase(MappingConstants.DATE_TYPE_LONG))
		{
			this.setPropertyTypeInt(MappingConstants.DATE_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.TIMESTAMP_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.TIMESTAMP_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.INT_TYPE)
				|| (propertyType.equalsIgnoreCase(MappingConstants.INTEGER_TYPE)))
		{
			this.setPropertyTypeInt(MappingConstants.INT_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.NUMERIC_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.NUMERIC_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.BOOLEAN_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.BOOLEAN_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.LONG_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.LONG_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.SHORT_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.SHORT_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.DOUBLE_TYPE)
				|| propertyType.equalsIgnoreCase(MappingConstants.DOUBLE_TYPE_LONG))
		{
			this.setPropertyTypeInt(MappingConstants.DOUBLE_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.FLOAT_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.FLOAT_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.BYTE_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.BYTE_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.EVENT_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.EVENT_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.BLOB_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.BLOB_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.BYTE_ARRAY_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.BYTEARRAY_DATATYPE);
		}
		else if (propertyType.equalsIgnoreCase(MappingConstants.CLOB_TYPE))
		{
			this.setPropertyTypeInt(MappingConstants.CLOB_DATATYPE);
		}
		else if (this.isValid() == true)
		{
			this.setPropertyTypeInt(MappingConstants.INVALID_DATATYPE);

			// throw exception, because according to rules, this field should be mapped
			throw new LoadMappingPropertyException("Invalid propertyType: " + propertyType);
		}

		if (MappingConstants.OID.equals(this.propertyName)
				&& (this.propertyTypeInt != MappingConstants.STRING_DATATYPE && this.propertyTypeInt != MappingConstants.NUMERIC_DATATYPE))
		{
			String sourceId = null;
			if (this.getCallback() instanceof EventQueryProperties)
			{
				sourceId = ((EventQueryProperties) this.getCallback()).getEventName();
			}
			else
			{
				sourceId = "SaveCallback";
			}
			String sourceName = this.getCallback().getParent().getEntity();
			String shortDesc = "OID should map to String, it was actually mapped to a(n): " + this.getPropertyType();
			System.err.println("Mapping Error: entity: " + sourceName + " event: " + sourceId + " shortDesc: "
					+ shortDesc);
		}
	}

	void optimizeFormatting()
	{
		if (MappingConstants.PRESERVE_CASE.equalsIgnoreCase(this.formatString))
		{
			this.preserveCase = true;
		}
		else if (MappingConstants.WILD_CARD_IF_BLANK.equalsIgnoreCase(this.formatString))
		{
			this.wildcardIfBlank = true;
		}
		else if (MappingConstants.WILD_CARD_ALWAYS.equalsIgnoreCase(this.formatString))
		{
			this.wildcardAlways = true;
		}

		if (MappingConstants.CONVERT_WILDCARD_STAR_TO_PERCENT.equals(this.convertWildcard))
		{
			this.convertWildStarToPercent = true;
		}
	}

	void optimizeReflection(Class aClass)
	{
		Method method = Reflection.getAccessorMethod(aClass, this.propertyName);
		this.setAccessor(method);

		if (method == null)
		{
			String sourceId = null;
			if (this.getCallback() instanceof EventQueryProperties)
			{
				sourceId = ((EventQueryProperties) this.getCallback()).getEventName();
			}
			else
			{
				sourceId = "SaveCallback";
			}
			String sourceName = this.getCallback().getParent().getEntity();
			String shortDesc = "Missing accessor: " + this.toString();
			System.err.println("Mapping Error: entity: " + sourceName + " event: " + sourceId + " shortDesc: "
					+ shortDesc);
		}

		List mutators = Reflection.getMutatorMethods(aClass, this.propertyName);
		if (mutators.size() > 0)
		{
			method = null;
			switch (this.propertyTypeInt)
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
				if(method == null)
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

			this.setMutator(method);

			if (method == null && this.propertyTypeInt != MappingConstants.JIMS2USER_DATATYPE
					&& this.propertyTypeInt != MappingConstants.USER_DATATYPE)
			{
				String sourceId = null;
				if (this.getCallback() instanceof EventQueryProperties)
				{
					sourceId = ((EventQueryProperties) this.getCallback()).getEventName();
				}
				else
				{
					sourceId = "SaveCallback";
				}
				String sourceName = this.getCallback().getParent().getEntity();
				String shortDesc = "Missing mutator: " + this.toString() + " expecting parmeter type on setter: "
						+ this.propertyType;
				System.err.println("Mapping Error: entity: " + sourceName + " event: " + sourceId + " shortDesc: "
						+ shortDesc);
			}
		}
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
	 * @roseuid 40571EA300CB
	 */
	public void refresh()
	{

	}

	/**
	 * @param method
	 */
	void setAccessor(Method anAccessor)
	{
		this.accessor = anAccessor;
	}

	public void setAppendFiller(String anAppendFiller)
	{
		this.appendFiller = anAppendFiller;
	}

	/**
	 * @param value
	 * @roseuid 404DA1FD00BF
	 */
	public void setAssociationType(String anAssociationType)
	{
		this.associationType = anAssociationType;
	}

	/**
	 * @param parentClass
	 *            The parentClass to set.
	 */
	public void setCallback(CallbackProperties aCallback)
	{
		this.callback = aCallback;
	}

	public void setCardPosition(String aCardPosition)
	{
		this.cardPosition = aCardPosition;
	}

	/**
	 * @param string
	 */
	public void setClassification(String aClassification)
	{
		this.classification = aClassification;
	}

	/**
	 * @deprecated
	 * @param aCodeTableName
	 */
	public void setCodeTableName(String aCodeTableName)
	{
		this.codeTableName = aCodeTableName;
	}

	public void setConvertWildcard(String aConvertWildcard)
	{
		this.convertWildcard = aConvertWildcard;
	}

	/**
	 * @param name
	 * @roseuid 404DA1FD00B4
	 */
	public void setDataItemName(String aDataItemName)
	{
		this.dataItemName = aDataItemName;
	}

	public void setFormat(String aFormat)
	{
		this.format = aFormat;
	}

	public void setFormatString(String aFormatString)
	{
		this.formatString = aFormatString;
	}

	/**
	 * @param index
	 *            The index to set.
	 */
	public void setIndex(int index)
	{
		this.index = index;
	}

	public void setLength(String aLength)
	{
		this.length = aLength;
	}

	void setMutator(Method aMutator)
	{
		this.mutator = aMutator;
	}

	public void setName(String aName)
	{
		this.name = aName;
	}

	public void setPadChar(String aPadChar)
	{
		this.padChar = aPadChar;
	}

	/**
	 * @param parmIndex
	 * @roseuid 404DA1FD00A0
	 */
	public void setParmIndex(String aParmIndex)
	{
		this.parmIndex = aParmIndex;
	}

	public void setPosition(String aPosition)
	{
		this.position = aPosition;
	}

	/**
	 * @param propertyName
	 * @roseuid 404DA1FD008D
	 */
	public void setPropertyName(String aPropertyName)
	{
		this.propertyName = aPropertyName;
	}

	/**
	 * @param propertyType
	 * @roseuid 404DA1FD0097
	 */
	public void setPropertyType(String aPropertyType)
	{
		this.propertyType = aPropertyType;
	}

	/**
	 * @param propertyTypeInt
	 *            The propertyTypeInt to set.
	 */
	public void setPropertyTypeInt(int aPropertyTypeInt)
	{
		this.propertyTypeInt = aPropertyTypeInt;
	}

	public void setText(String aText)
	{
		this.text = aText;
	}

	public String toString()
	{
		String parentClass = this.callback.getParent().getEntity();
		StringBuilder buffer = new StringBuilder(80);
		buffer.append("FieldMappingProperty: ");
		buffer.append(parentClass);
		buffer.append(" propertyName=");
		buffer.append(this.propertyName);
		buffer.append(" propertyType=");
		buffer.append(this.propertyType);
		buffer.append(" index=");
		buffer.append(this.index);
		buffer.append(" dataItemName=");
		buffer.append(this.dataItemName);
		return buffer.toString();
	}

	/**
	 * 
	 */
	public void optimizeIndex(String aConnectionName)
	{
		// convert parmIndex or position to int on "index" property
		if (aConnectionName.startsWith(MappingConstants.JDBC))
		{
			this.index = Integer.parseInt(this.parmIndex);
		}
		else
		{
			this.index = Integer.parseInt(this.position);
		}
	}

	/**
	 * @param preserveCase
	 *            The preserveCase to set.
	 */
	public void setPreserveCase(boolean formatString)
	{
		this.preserveCase = formatString;

	}
}
