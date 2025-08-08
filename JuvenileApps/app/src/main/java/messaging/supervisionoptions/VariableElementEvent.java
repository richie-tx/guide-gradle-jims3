/*
 * Created on Aug 16, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class VariableElementEvent  extends RequestEvent
{
	private String variableElementTypeId;
	public String type;
	private String value;

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
	public void setType(String string)
	{
		type = string;
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
