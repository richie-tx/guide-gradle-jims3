/*
 * Created on May 17, 2006
 *
 */
package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class ValidateConditionChangeEvent extends RequestEvent
{
	private String action;
	private String conditionId;

	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}
	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @param aConditionId
	 */
	public void setConditionId(String aConditionId)
	{
		conditionId = aConditionId;
	}

}
