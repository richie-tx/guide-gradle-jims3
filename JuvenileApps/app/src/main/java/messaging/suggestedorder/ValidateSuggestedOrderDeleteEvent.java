/*
 * Created on May 17, 2006
 *
 */
package messaging.suggestedorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class ValidateSuggestedOrderDeleteEvent extends RequestEvent
{
	private String suggestedOrderId;
	/**
	 * @return
	 */
	public String getSuggestedOrderId()
	{
		return suggestedOrderId;
	}

	/**
	 * @param aSuggestedOrderId
	 */
	public void setSuggestedOrderId(String aSuggestedOrderId)
	{
		suggestedOrderId = aSuggestedOrderId;
	}

}
