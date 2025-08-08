/*
 * Created on Dec 16, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 */
public class SaveJuvenileCaseSupervisionRuleValueRequestEvent extends RequestEvent
{

	private String  variableElementId;
	private String  value;


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
	public String getVariableElementId()
	{
		return variableElementId;
	}

	/**
	 * @param string
	 */
	public void setValue(String string)
	{
		value = string;
	}

	/**
	 * @param i
	 */
	public void setVariableElementId(String i)
	{
		variableElementId = i;
	}

}
