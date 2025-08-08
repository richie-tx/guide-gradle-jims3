/*
 * Created on Feb 23, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.supervisionorder.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SupOrderConditionRelValueResponseEvent extends ResponseEvent implements Comparable
{
	private String variableElementTypeId;
	private String value;
	private boolean isFixed;
	private String name;
	private boolean isReference;
	private boolean enumerated;
	private String codeTableName;
	private String enumerationTypeId;
	private String valueId;
	private String valueType;
	private boolean likeConditionInd;

	/**
	 * @return
	 */
	public boolean isFixed()
	{
		return isFixed;
	}

	/**
	 * @return
	 */
	public boolean isReference()
	{
		return isReference;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @return
	 */
	public String getVariableElementTypeId()
	{
		return variableElementTypeId;
	}

	/**
	 * @param b
	 */
	public void setFixed(boolean b)
	{
		isFixed = b;
	}

	/**
	 * @param b
	 */
	public void setReference(boolean b)
	{
		isReference = b;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @param string
	 */
	public void setValue(String string)
	{
		value = string;
	}

	/**
	 * @param string
	 */
	public void setVariableElementTypeId(String string)
	{
		variableElementTypeId = string;
	}

	/**
	 * @return
	 */
	public String getCodeTableName()
	{
		return codeTableName;
	}

	/**
	 * @return
	 */
	public boolean isEnumerated()
	{
		return enumerated;
	}

	/**
	 * @return
	 */
	public String getEnumerationTypeId()
	{
		return enumerationTypeId;
	}

	/**
	 * @return
	 */
	public String getValueId()
	{
		return valueId;
	}

	/**
	 * @param string
	 */
	public void setCodeTableName(String string)
	{
		codeTableName = string;
	}

	/**
	 * @param b
	 */
	public void setEnumerated(boolean b)
	{
		enumerated = b;
	}

	/**
	 * @param string
	 */
	public void setEnumerationTypeId(String string)
	{
		enumerationTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setValueId(String string)
	{
		valueId = string;
	}

	/**
	 * @return
	 */
	public String getValueType()
	{
		return valueType;
	}

	/**
	 * @param string
	 */
	public void setValueType(String string)
	{
		valueType = string;
	}

	/**
	 * @return
	 */
	public boolean isLikeConditionInd()
	{
		return likeConditionInd;
	}

	/**
	 * @param b
	 */
	public void setLikeConditionInd(boolean b)
	{
		likeConditionInd = b;
	}

	public int compareTo(Object obj) throws ClassCastException {
		SupOrderConditionRelValueResponseEvent evt = (SupOrderConditionRelValueResponseEvent)obj;
		return name.compareTo(evt.getName());
	}
	
}
