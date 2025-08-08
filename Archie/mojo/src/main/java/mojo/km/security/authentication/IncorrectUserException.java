package mojo.km.security.authentication;

import javax.security.auth.login.LoginException;

/**
 * @author dnikolis
 *
* This class is used when a user enters an incorrect logon.
 *
 * Change History:
 *
 * Author          	Date        	Explanation
 * Dnikolis			12/03/03		Added Javadocs
 * 
 */
public class IncorrectUserException extends LoginException {
	/**
	 * Constructor for IncorrectUserException.
	 */
	public IncorrectUserException() {
		super();
	}

	/**
	 * Constructor for IncorrectUserException.
	 * @param message
	 */
	public IncorrectUserException(String message) {
		super(message);
	}
}

