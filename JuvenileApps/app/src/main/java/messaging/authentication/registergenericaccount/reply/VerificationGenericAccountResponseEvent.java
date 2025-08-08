/*
 * Created on Nov 21, 2006
 */
package messaging.authentication.registergenericaccount.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class VerificationGenericAccountResponseEvent extends ResponseEvent
{
	private String message;
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}