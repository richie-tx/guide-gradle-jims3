package messaging.authentication;

/**
 * @author dnikolis
 *
 * This class is used when a User logs out of the system.  
 * 
 * Change History:
 *
 * Author          	Date        	Explanation
 * Dnikolis			12/15/03		Added Javadocs
 * 
 */

import javax.security.auth.login.LoginContext;

import mojo.km.messaging.RequestEvent;

public class LogoutUserEvent extends RequestEvent {
   
   	private LoginContext loginContext;
	private String userID;
   
   	public LogoutUserEvent() {
    
   	}
   
	public LoginContext getLoginContext() {
		return this.loginContext;
   	}
   	
   	public void setLoginContext(LoginContext aLoginContext) {
   		this.loginContext = aLoginContext;
   	}
   	
   	public String getUserID() {
   		return this.userID;
   	}
   	
   	public void setUserID(String userID) {
   		this.userID = userID;
   	}
}

