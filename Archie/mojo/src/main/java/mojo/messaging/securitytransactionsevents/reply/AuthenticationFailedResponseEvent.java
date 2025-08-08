/*
 * Created on Nov 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mojo.messaging.securitytransactionsevents.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author racooper
 *  
 */
public class AuthenticationFailedResponseEvent extends ResponseEvent {
	private String errorType;

	private String errorMessage;

	/**
	 * @return Returns the errorMessage.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            The errorMessage to set.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return Returns the errorType.
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * @param errorType
	 *            The errorType to set.
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
}
