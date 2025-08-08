// Source file:
// C:\\VIEWS\\ARCHPRODUCTION\\FRAMEWORK\\MOJO-JIMS2\\MOJO.JAVA\\src\\mojo\\km\\security\\AuthenticationFailedException.java

package mojo.km.security;

import mojo.km.messaging.exception.ReturnException;
import mojo.messaging.securitytransactionsevents.reply.AuthenticationFailedResponseEvent;

public class AuthenticationFailedException extends ReturnException {
	private String errorType;

	private String errorMessage;

	/**
	 * @roseuid 422F60D30097
	 */
	public AuthenticationFailedException(String s) {
		super(s);
	}

	public AuthenticationFailedException(String errorType, String errorMessage) {
		super(errorMessage);
		this.errorType = errorType;
		this.errorMessage = errorMessage;
	}

	/**
	 * @return
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @return
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * @param string
	 */
	public void setErrorMessage(String string) {
		errorMessage = string;
	}

	/**
	 * @param i
	 */
	public void setErrorType(String i) {
		errorType = i;
	}

	public void fill(AuthenticationFailedResponseEvent anEvent) {
		anEvent.setErrorType(this.errorType);
		anEvent.setErrorMessage(this.errorMessage);
	}
}
