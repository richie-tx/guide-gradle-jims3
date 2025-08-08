package mojo.km.security.authentication;
import javax.security.auth.login.LoginException;

/**
 * @author dnikolis
 *
 * This class is used when a users profile is expired.
 *
 * Change History:
 *
 * Author          	Date        	Explanation
 * Dnikolis			12/03/03		Added Javadocs
 * 
 */
public class ExpiredProfileException extends LoginException {
	/**
	 * Constructor for ExpiredProfileException.
	 */
	public ExpiredProfileException() {
		super();
	}

	/**
	 * Constructor for ExpiredProfileException.
	 * @param message
	 */
	public ExpiredProfileException(String message) {
		super(message);
	}
}
