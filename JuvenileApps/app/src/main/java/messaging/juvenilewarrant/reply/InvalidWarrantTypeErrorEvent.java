/*
 * Created on Oct 19, 2004
 */
package messaging.juvenilewarrant.reply;

import naming.*;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 */
public class InvalidWarrantTypeErrorEvent extends ResponseEvent
{
	private String message;
	
	public InvalidWarrantTypeErrorEvent()
	{
		this.setTopic(PDJuvenileWarrantConstants.ERROR_INVALID_WARRANT_TYPE_TOPIC);
	}

	/**
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}
	
	/**
	 * @param string
	 */
	public void setMessage(String msg)
	{
		message = msg;
	}
}