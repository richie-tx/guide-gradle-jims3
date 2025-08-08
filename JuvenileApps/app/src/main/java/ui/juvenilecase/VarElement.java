/*
 * Created on Nov 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase;

import java.util.Collection;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class VarElement
{

	private String name;
	private String enumType;
	private boolean fixed;
	private boolean enumerated;
	private boolean reference;
	private String defaultValue;
	private String uiType;
	private Collection possibleValues;
	private int variableElementTypeId;
	private int variableElementId;
	/**
	 * @return
	 */
	public String getDefaultValue()
	{
		return defaultValue;
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
	public boolean isFixed()
	{
		return fixed;
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
	public String getUiType()
	{
		return uiType;
	}

	/**
	 * @param string
	 */
	public void setDefaultValue(String string)
	{
		defaultValue = string;
	}

	/**
	 * @param b
	 */
	public void setEnumerated(boolean b)
	{
		enumerated = b;
	}

	/**
	 * @param b
	 */
	public void setFixed(boolean b)
	{
		fixed = b;
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
	public void setUiType(String string)
	{
		uiType = string;
	}

	/**
	 * @return
	 */
	public Collection getPossibleValues()
	{
		return possibleValues;
	}

	/**
	 * @param collection
	 */
	public void setPossibleValues(Collection collection)
	{
		possibleValues = collection;
	}

	/**
	 * @return
	 */
	public String getEnumType()
	{
		return enumType;
	}

	/**
	 * @param string
	 */
	public void setEnumType(String string)
	{
		enumType = string;
	}

	/**
	 * @return
	 */
	public boolean isReference()
	{
		return reference;
	}

	/**
	 * @param b
	 */
	public void setReference(boolean b)
	{
		reference = b;
	}

	/**
	 * @return
	 */
	public int getVariableElementTypeId()
	{
		return variableElementTypeId;
	}

	/**
	 * @param i
	 */
	public void setVariableElementTypeId(int i)
	{
		variableElementTypeId = i;
	}

	/**
	 * @return
	 */
	public int getVariableElementId()
	{
		return variableElementId;
	}

	/**
	 * @param i
	 */
	public void setVariableElementId(int i)
	{
		variableElementId = i;
	}

}
