/*
 * Created on Dec 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import messaging.supervisionoptions.reply.VariableElementResponseEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RuleDetailResponseEvent extends RuleResponseEvent
{
	private String 	conditionLiteral;
	private String  additionalInformation;
	private Map variables = new HashMap();
	
	
	  
	
	/**
	 * @return
	 */
	public String getConditionLiteral()
	{
		return conditionLiteral;
	}

	/**
	 * @param string
	 */
	public void setConditionLiteral(String string)
	{
		conditionLiteral = string;
	}

	/**
	 * @return
	 */
	public String getAdditionalInformation()
	{
		return additionalInformation;
	}

	/**
	 * @param string
	 */
	public void setAdditionalInformation(String string)
	{
		additionalInformation = string;
	}

	/**
	 * @return
	 */
	public Collection getVariables()
	{
		return variables.values();
	}

	/**
	 * @param map
	 */
	public void setVariable( String name, VariableElementResponseEvent value )
	{
		variables.put( name, value );
	}

	

}
