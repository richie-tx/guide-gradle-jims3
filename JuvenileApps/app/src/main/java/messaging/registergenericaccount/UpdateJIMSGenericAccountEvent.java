//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\registergenericaccount\\HandleGenericLogonIDSelectionEvent.java

package messaging.registergenericaccount;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class UpdateJIMSGenericAccountEvent extends RequestEvent 
{
	private String genericAccountId;
	private String statusId;
	private String logonId;
	private String password;
   
   /**
    * @roseuid 456220C80219
    */
   public UpdateJIMSGenericAccountEvent() 
   {
    
   }
	/**
	 * @return Returns the genericAccountId.
	 */
	public String getGenericAccountId() {
		return genericAccountId;
	}
	/**
	 * @param genericAccountId The genericAccountId to set.
	 */
	public void setGenericAccountId(String genericAccountId) {
		this.genericAccountId = genericAccountId;
	}
	/**
	 * @return Returns the logonId.
	 */
	public String getLogonId() {
		return logonId;
	}
	/**
	 * @param logonId The logonId to set.
	 */
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
}
