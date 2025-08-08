/*
 * Created on Sep 29, 2005
 *
 */
package messaging.suggestedorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class SuggestedOrderCourtRequestEvent extends RequestEvent
{
	private String courtId;
	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}

	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		courtId = string;
	}

}
