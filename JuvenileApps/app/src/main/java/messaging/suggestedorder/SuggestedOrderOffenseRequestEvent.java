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
public class SuggestedOrderOffenseRequestEvent extends RequestEvent
{
	private String offenseId;
	/**
	 * @return
	 */
	public String getOffenseId()
	{
		return offenseId;
	}

	/**
	 * @param string
	 */
	public void setOffenseId(String string)
	{
		offenseId = string;
	}

}
