/*
 * Create on Dec 9, 2005
 */

package messaging.security.authentication.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * author ugopinath
 */

public class LoginErrorResponseEvent extends ResponseEvent
{
	public String message;
	
	/**
	 * @return
	 */

	public String getMessage()
	{
		return message;
	}
	
	/**
	 * @param message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	

}