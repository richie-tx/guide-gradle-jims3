//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\authentication\\UpdateACF2PasswordEvent.java

package mojo.messaging.securitytransactionsevents;

import mojo.km.messaging.RequestEvent;

public class UpdateACF2PasswordEvent extends RequestEvent
{
	public String logonId;
	public String password;
	public String newPassword;

	/**
	 * @roseuid 4399CD3C0022
	 */
	public UpdateACF2PasswordEvent()
	{

	}

	/**
	 * Access method for the logonId property.
	 * 
	 * @return   the current value of the logonId property
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * Sets the value of the logonId property.
	 * 
	 * @param aLogonId the new value of the logonId property
	 */
	public void setLogonId(String aLogonId)
	{
		logonId = aLogonId;
	}

	/**
	 * Access method for the password property.
	 * 
	 * @return   the current value of the password property
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * Sets the value of the password property.
	 * 
	 * @param aPassword the new value of the password property
	 */
	public void setPassword(String aPassword)
	{
		password = aPassword;
	}
	/**
	 * @return
	 */
	public String getNewPassword()
	{
		return newPassword;
	}

	/**
	 * @param string
	 */
	public void setNewPassword(String string)
	{
		newPassword = string;
	}

}