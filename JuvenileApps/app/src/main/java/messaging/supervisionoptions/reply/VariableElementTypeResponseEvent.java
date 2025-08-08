/*
 * Created on Oct 10, 2005
 *
 */
package messaging.supervisionoptions.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class VariableElementTypeResponseEvent extends ResponseEvent implements Comparable
{
	private String agencyId;
	private String  codeTableName;
	private String description;
	private boolean enumration;
	private boolean isReference;
	private boolean isVolatile;
	private String name;
	private String sampleValue;
	private String type;
	private String  valueType;	
	private String variableElementTypeId;
	private boolean isCalculated;

/**
 * @return name
 */
	public int compareTo(Object obj) throws ClassCastException
		{
			VariableElementTypeResponseEvent evt = (VariableElementTypeResponseEvent)obj;
			String evtName = evt.getName().toUpperCase();
			String objName = name.toUpperCase();
			return objName.compareTo(evtName);
		}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
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
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return
	 */
	public boolean getIsReference()
	{
		return isReference;
	}

	/**
	 * @return Returns the isVolatile.
	 */
	public boolean getIsVolatile() {
		return isVolatile;
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
	public String getSampleValue()
	{
		return sampleValue;
	}

	/**
	 * @return
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @return
	 */
	public String getValueType()
	{
		return valueType;
	}
	
	/**
	 * @return
	 */
	public String getVariableElementTypeId()
	{
		return variableElementTypeId;
	}

	/**
	 * @return
	 */
	public boolean isEnumration()
	{
		return enumration;
	}

	/**
	 * 
	 */
	public void setAgencyId( String anID )
	{
		agencyId = anID;
	}

	/**
	 * @param string
	 */
	public void setCodeTableName(String string)
	{
		codeTableName = string;
	}

	/**
	 * 
	 */
	public void setDescription( String aDescription )
	{
		description = aDescription;
	}

	/**
	 * @param b
	 */
	public void setEnumration(boolean b)
	{
		enumration = b;
	}

	/**
	 * 
	 */
	public void setIsReference( boolean aIsReference )
	{
		isReference = aIsReference;
	}
	/**
	 * @param isVolatile The isVolatile to set.
	 */
	public void setIsVolatile(boolean isVolatile) {
		this.isVolatile = isVolatile;
	}

	/**
	 * 
	 */
	public void setName( String aName )
	{
		name = aName;
	}

	/**
	 * @param b
	 */
	public void setReference(boolean b)
	{
		isReference = b;
	}

	/**
	 * 
	 */
	public void setSampleValue( String aSampleValue )
	{
		sampleValue = aSampleValue;
	}

	/**
	 * @param string
	 */
	public void setType(String string)
	{
		type = string;
	}

	/**
	 * @param string
	 */
	public void setValueType(String string)
	{
		valueType = string;
	}

	/**
	 * 
	 */
	public void setVariableElementTypeId( String anID )
	{
		variableElementTypeId = anID;
	}

	/**
	 * @return the isCalculated
	 */
	public boolean getIsCalculated() {
		return isCalculated;
	}

	/**
	 * @param isCalculated the isCalculated to set
	 */
	public void setIsCalculated(boolean isCalculated) {
		this.isCalculated = isCalculated;
	}
}
