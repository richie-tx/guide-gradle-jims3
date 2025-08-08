/*
 * Created on Feb 9, 2005
 */
package mojo.tools.requirements;

/**
 * @author mlawles
 * This class is used by the MappingXMLParser to store fields from the Mapping.XML
 */
public class MappingField
{
	private int position;
	private int gap;
	private String fieldName;
	private String entityName;
	private String type;  //field or parm
	private boolean isZero;
	private boolean isBlank;
	private String callBackType; //query or saver
	private String callBackName;
	private String propertyName;
	
	/**
	 * @return
	 */
	public String getFieldName()
	{
		return fieldName;
	}

	/**
	 * @return
	 */
	public int getGap()
	{
		return gap;
	}

	/**
	 * @return
	 */
	public int getPosition()
	{
		return position;
	}

	/**
	 * @param string
	 */
	public void setFieldName(String string)
	{
		fieldName = string;
	}

	/**
	 * @param i
	 */
	public void setGap(int i)
	{
		gap = i;
	}

	/**
	 * @param i
	 */
	public void setPosition(int i)
	{
		position = i;
	}

	/**
	 * @return
	 */
	public String getEntityName()
	{
		return entityName;
	}

	/**
	 * @param string
	 */
	public void setEntityName(String string)
	{
		entityName = string;
	}

	/**
	 * @return
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param string
	 */
	public void setType(String string)
	{
		type = string;
	}

	/**
	 * @return
	 */
	public boolean isZero()
	{
		return isZero;
	}

	/**
	 * @param b
	 */
	public void setZero(boolean b)
	{
		isZero = b;
	}

	/**
	 * @return
	 */
	public String getCallBackType()
	{
		return callBackType;
	}

	/**
	 * @param string
	 */
	public void setCallBackType(String string)
	{
		callBackType = string;
	}

	/**
	 * @return
	 */
	public String getCallBackName()
	{
		return callBackName;
	}

	/**
	 * @param string
	 */
	public void setCallBackName(String string)
	{
		callBackName = string;
	}

	/**
	 * @return
	 */
	public String getPropertyName()
	{
		return propertyName;
	}

	/**
	 * @param string
	 */
	public void setPropertyName(String string)
	{
		propertyName = string;
	}

	/**
	 * @return
	 */
	public boolean isBlank()
	{
		return isBlank;
	}

	/**
	 * @param b
	 */
	public void setBlank(boolean b)
	{
		isBlank = b;
	}

}
