package messaging.authentication;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis
 *
 * This class is used to validate a user.  
 * 
 * Change History:
 *
 * Author          	Date        	Explanation
 * Dnikolis			12/15/03		Added Javadocs
 * 
 */
public class ValidateUserEvent extends RequestEvent {
	private String password;
	private String status;
	
	private static final int PROFILE_ERROR = 0;
	private static final int PROFILE_ACTIVE = 1;
	private static final int PROFILE_EXPIRED = 2;
	private static final int PROFILE_SUSPENDED = 4;

	private int profileStatus;
	private boolean incorrectUser;
	private boolean incorrectPassword;

	public ValidateUserEvent() { }

	/**
	 * Returns the password.
	 * @return String
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Returns the status.
	 * @return String
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Sets the password.
	 * @param password The password to set
	 */
	public void setPassword(String aPassword) {
		this.password = aPassword;
	}

	/**
	 * Sets the status.
	 * @param status The status to set
	 */
	public void setStatus(String aStatus) {
		this.status = aStatus;
	}

	/**
	
	 */
	public void setProfileActive() {
		this.profileStatus = ValidateUserEvent.PROFILE_ACTIVE;
	}

	/**
	
	 */
	public void setProfileSuspended() {
		this.profileStatus = ValidateUserEvent.PROFILE_SUSPENDED;
	}

	/**
	
	 */
	public void setProfileExpired() {
		this.profileStatus = ValidateUserEvent.PROFILE_EXPIRED;
	}
	
	public void setGeneralProfileError() {
		this.profileStatus = ValidateUserEvent.PROFILE_ERROR;
	}

	/**
	
	 */
	public void setIncorrectPassword() {
		this.incorrectPassword = true;
	}

	/**
	
	 */
	public void setAuthenticated() {
		this.incorrectUser = false;
		this.incorrectPassword = false;
	}

	/**
	
	 */
	public void setIncorrectUser() {
		this.incorrectUser = true;
	}

	/**
	
	 */
	public boolean isProfileSuspended() {
		return (this.profileStatus == ValidateUserEvent.PROFILE_SUSPENDED);
	}

	/**
	
	 */
	public boolean isProfileExpired() {
		return (this.profileStatus == ValidateUserEvent.PROFILE_EXPIRED);
	}

	/**
	
	 */
	public boolean isProfileActive() {
		return (this.profileStatus == ValidateUserEvent.PROFILE_ACTIVE);
	}

	public boolean hasGeneralProfileError() {
		return (this.profileStatus == ValidateUserEvent.PROFILE_ERROR);
	}

	/**
	
	 */
	public boolean isIncorrectUser() {
		return this.incorrectUser;
	}

	/**
	
	 */
	public boolean isIncorrectPassword() {
		return this.incorrectPassword;
	}

	/**
	
	 */
	public boolean isAuthenticated() {
		return !(isIncorrectUser() || isIncorrectPassword());
	}
}
	
