package mojo.km.security.authentication;

import javax.security.auth.login.LoginException;

/**
 * @author dnikolis
 *
 * This class is used when a users profile is suspended.
 *
 * Change History:
 *
 * Author          	Date        	Explanation
 * Dnikolis			12/03/03		Added Javadocs
 * 
 */
public class SuspendedProfileException extends LoginException {
	/**
	 * Constructor for SuspendedProfileException.
	 */
	public SuspendedProfileException() {
		super();
	}

	/**
	 * Constructor for SuspendedProfileException.
	 * @param message
	 */
	public SuspendedProfileException(String message) {
		super(message);
	}
}

