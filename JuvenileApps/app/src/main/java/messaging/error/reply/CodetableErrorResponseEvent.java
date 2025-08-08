/*
 * Created on May 02, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.error.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CodetableErrorResponseEvent extends ResponseEvent
{
	private String errorKey;
	private String message;
	private String parameterValue;

	/**
	 * @return Returns the errorKey.
	 */
	public String getErrorKey() {
		return errorKey;
	}
	/**
	 * @param errorKey The errorKey to set.
	 */
	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}
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
	/**
	 * @return Returns the parameterValue.
	 */
	public String getParameterValue() {
		return parameterValue;
	}
	/**
	 * @param parameterValue The parameterValue to set.
	 */
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
}
