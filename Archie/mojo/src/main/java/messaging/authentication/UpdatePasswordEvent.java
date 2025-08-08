package messaging.authentication;

/**
 * @author dnikolis
 *
 * This class is used when a user updates their password.  
 * 
 * Change History:
 *
 * Author          	Date        	Explanation
 * Dnikolis			12/15/03		Added Javadocs
 * 
 */

import mojo.km.messaging.RequestEvent;

public class UpdatePasswordEvent extends RequestEvent {
   private String LogonID;
   private String password;
   private String newPassword;
    
   /**
	 * Constructor for UpdatePasswordEvent.
	 */
   	public UpdatePasswordEvent() {
		super();
   	}
   
   	/**
	 * @see mojo.km.utilities.IOIDEvent#setOID(java.lang.String)
	 */
	public void setOID(String arg0) {
	}	

	/**
	 * @see mojo.km.utilities.IOIDEvent#getOID()
	 */
	public String getOID() {
		return null;
	}
	
	/**
	 * @return
 	 */
	public String getLogonID() {
		return LogonID;
	}

	/**
 	 * @return
 	 */
	public String getPassword() {
		return password;
	}

	/**
 	 * @param string
 	 */
	public void setLogonID(String string) {
		LogonID = string;
	}

	/**
 	 * @param string
 	 */
	public void setPassword(String string) {
		password = string;
	}
	
	/**
 	* @return
 	*/
	public String getNewPassword() {
		return newPassword;
	}

	/**
 	* @param string
 	*/
	public void setNewPassword(String string) {
		newPassword = string;
	}
}

