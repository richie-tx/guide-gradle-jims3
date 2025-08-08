/*
 * Created on Sep 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.suggestedorder.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class SuggestedOrderResponseEvent extends ResponseEvent
{
	private String suggestedOrderId;
	private String orderName;
	private String orderDescription;
	private String includedConditionsId;
	private String includedConditionsDescription;

	/**
	 * @return
	 */
	public String getIncludedConditionsDescription()
	{
		return includedConditionsDescription;
	}

	/**
	 * @return
	 */
	public String getIncludedConditionsId()
	{
		return includedConditionsId;
	}

	/**
	 * @return
	 */
	public String getOrderDescription()
	{
		return orderDescription;
	}

	/**
	 * @return
	 */
	public String getOrderName()
	{
		return orderName;
	}

	/**
	 * @return
	 */
	public String getSuggestedOrderId()
	{
		return suggestedOrderId;
	}

	/**
	 * @param string
	 */
	public void setIncludedConditionsDescription(String string)
	{
		includedConditionsDescription = string;
	}

	/**
	 * @param string
	 */
	public void setIncludedConditionsId(String string)
	{
		includedConditionsId = string;
	}

	/**
	 * @param string
	 */
	public void setOrderDescription(String string)
	{
		orderDescription = string;
	}

	/**
	 * @param string
	 */
	public void setOrderName(String string)
	{
		orderName = string;
	}

	/**
	 * @param string
	 */
	public void setSuggestedOrderId(String string)
	{
		suggestedOrderId = string;
	}

}
