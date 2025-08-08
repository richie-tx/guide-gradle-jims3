/*
 * Created on Feb 8, 2006
 *
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author asrvastava
 *
 */
public class SupervisionOrderConditionRelValueEvent extends RequestEvent
{
	private boolean isFixed;
	private boolean isReference;
	private String value;
	private String variableElementTypeId;

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
	 * @return
	 */
	public boolean isFixed()
	{
		return isFixed;
	}

	/**
	 * @return Returns the isReference.
	 */
	public boolean isReference() {
		return isReference;
	}

	/**
	 * @param b
	 */
	public void setFixed(boolean b)
	{
		isFixed = b;
	}
	/**
	 * @param isReference The isReference to set.
	 */
	public void setReference(boolean isReference) {
		this.isReference = isReference;
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
}
