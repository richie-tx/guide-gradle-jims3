package messaging.user.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 */
public class DuplicateUserResponseEvent extends ResponseEvent
{
	private String message = null;
	private String logonId;
	
	
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

	/**
	 * @return Returns the logonId.
	 */
	public String getLogonId() {
		return logonId;
	}
	/**
	 * @param logonId The logonId to set.
	 */
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
}
