/*
 * Created on Oct 10, 2005
 *
 */
package messaging.supervisionoptions.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class VariableElementResponseEvent extends ResponseEvent implements Cloneable, Comparable
{
	//	private String courtSupervisionOptionId;
	private String variableElementId;
	private String name;
	private String variableElementTypeId;
	private String courtId;
	private boolean isExceptionCourt;
	private boolean isFixed;
	private String value;

	private boolean enumeration;
	private boolean reference;
	private String codeTableName;
	private String valueType;
	private String enumerationTypeId;
	private Collection codes = new ArrayList();
	private String valueId;
	private boolean likeConditionInd;
	private boolean calculated;
	private String dataType;

	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}

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
	 * @param string
	 */
	public void setCourtId(String string)
	{
		courtId = string;
	}

	/**
	 * @param b
	 */
	public void setFixed(boolean b)
	{
		isFixed = b;
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
	public boolean isExceptionCourt()
	{
		return isExceptionCourt;
	}

	/**
	 * @param b
	 */
	public void setExceptionCourt(boolean b)
	{
		isExceptionCourt = b;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	public Object clone()
	{
		Object obj = null;
		try
		{
			obj = super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * @return
	 */
	public boolean isEnumeration()
	{
		return enumeration;
	}

	/**
	 * @return
	 */
	public boolean getEnumeration()
	{
		return enumeration;
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
	public void setEnumeration(boolean b)
	{
		enumeration = b;
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
	public String getCodeTableName()
	{
		return codeTableName;
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
	public void setCodeTableName(String string)
	{
		codeTableName = string;
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
	public String getEnumerationTypeId()
	{
		return enumerationTypeId;
	}

	/**
	 * @param string
	 */
	public void setEnumerationTypeId(String string)
	{
		enumerationTypeId = string;
	}

	/**
	 * @return
	 */
	public Collection getCodeValues()
	{
		return codes;
	}

	/**
	 * @param collection
	 */
	public void setCodeValues(Collection collection)
	{
		codes = collection;
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
	public void setValueId(String string)
	{
		valueId = string;
		setValueByValueId();
	}

	public void setValueByValueId()
	{
		Iterator it = codes.iterator();
		while (it.hasNext())
		{
			CodeResponseEvent cre = (CodeResponseEvent) it.next();
			String code = cre.getCode();
			if (valueId != null && valueId.equals(code))
			{
				this.setValue(cre.getDescription());
				break;
			} else {
				this.setValue("");
			}
		}
	}
	/**
	 * @return
	 */
	public String getVariableElementId()
	{
		return variableElementId;
	}

	/**
	 * @param string
	 */
	public void setVariableElementId(String string)
	{
		variableElementId = string;
	}

	public int compareTo(Object obj) throws ClassCastException {
		VariableElementResponseEvent evt = (VariableElementResponseEvent)obj;
		if(evt.getName()==null && this.name!=null){
			return 1;
		}
		else if(evt.getName()!=null && this.name==null){
				return -1;
		}
		else if(evt.getName()==null && this.name==null){
			return 0;
		}
		else{
			return name.compareTo(evt.getName());
		}
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

	public boolean isCalculated() {
		return calculated;
	}

	public String getDataType() {
		return dataType;
	}

	public void setCalculated(boolean calculated) {
		this.calculated = calculated;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
