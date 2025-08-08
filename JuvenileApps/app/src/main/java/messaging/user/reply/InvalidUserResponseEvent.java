package messaging.user.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class InvalidUserResponseEvent extends ResponseEvent
{
	private String message = null;
	
	
	/**
	 * @return String 
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param value
	 */
	public void setMessage(String value)
	{
		message = value;
	}

}
