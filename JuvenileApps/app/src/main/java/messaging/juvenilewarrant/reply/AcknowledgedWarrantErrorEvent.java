/*
 * Created on Oct 19, 2004
 */
package messaging.juvenilewarrant.reply;

import naming.*;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 */
public class AcknowledgedWarrantErrorEvent extends ResponseEvent
{
	private String message;
	
	public AcknowledgedWarrantErrorEvent()
	{
		this.setTopic(PDJuvenileWarrantConstants.ERROR_ACKNOWLEDGED_WARRANT_TOPIC);
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