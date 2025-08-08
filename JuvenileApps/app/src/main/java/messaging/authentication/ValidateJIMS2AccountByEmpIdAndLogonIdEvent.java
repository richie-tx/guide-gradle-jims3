//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\authentication\\GetJIMS2AccountEvent.java

package messaging.authentication;

import mojo.km.messaging.RequestEvent;

public class ValidateJIMS2AccountByEmpIdAndLogonIdEvent extends RequestEvent
{
	public String logonId;
	private String userAccountId;

	/**
	 * @roseuid 4399CD3A02A3
	 */
	public ValidateJIMS2AccountByEmpIdAndLogonIdEvent()
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
	 * @return Returns the userAccountId.
	 */
	public String getUserAccountId() {
		return userAccountId;
	}
	/**
	 * @param userAccountId The userAccountId to set.
	 */
	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}
}
