/*
 * Created on Oct 19, 2004
 */
package messaging.contact.agency.reply;

import naming.PDContactConstants;
import mojo.km.messaging.ResponseEvent;

/**
 * @author asrvastava
 */
public class ValidateAgencyErrorEvent extends ResponseEvent
{
	private String message;
	
	public ValidateAgencyErrorEvent()
	{
		this.setTopic(PDContactConstants.ERROR_VALIDATE_AGENCY_EVENT_TOPIC);
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