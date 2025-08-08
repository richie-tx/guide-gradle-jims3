package mojo.km.security.authentication;

import javax.security.auth.login.LoginException;

/**
 * @author dnikolis
 *
 * This class is used when a user enters an incorrect password.
 *
 * Change History:
 *
 * Author          	Date        	Explanation
 * Dnikolis			12/03/03		Added Javadocs
 * 
 */
public class IncorrectPasswordException extends LoginException {
	/**
	 * Constructor for IncorrectPasswordException.
	 */
	public IncorrectPasswordException() {
		super();
	}

	/**
	 * Constructor for IncorrectPasswordException.
	 * @param message
	 */
	public IncorrectPasswordException(String message) {
		super(message);
	}
}

